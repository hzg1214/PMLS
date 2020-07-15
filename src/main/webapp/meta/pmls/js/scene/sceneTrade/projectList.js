layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
        var element = layui.element,
            laydate = layui.laydate,
            form = layui.form,
            table = layui.table,
            layer = layui.layer,
            formSelects = layui.formSelects,
            $ = layui.$;

        var addPermission = NullToZero($("#addPermission").val());

        init();

        function init() {
            // 楼盘归属项目部
            projectDepartmentLinkageIsService(function () {
                form.render('select')
            });

            form.render('select'); //刷新select选择框渲染

            tableRender();

            reloadData();

        }

        function tableRender() {
            table.render({
                elem: '#mainTable'
                , cols: setCols()
                //, url: BASE_PATH + '/sceneTrade/qSceneEstate'
                , id: 'contentReload'
                , page: true
                , height: "full"
                , limits: [10, 20, 30]
                , limit: 10 //默认采用60
                , method: 'post'
                , loading: true
                , request: {
                    pageName: 'curPage' //页码的参数名称，默认：page
                    , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
                }
                , response: {
                    statusName: 'returnCode' //数据状态的字段名称，默认：code
                    , statusCode: 200 //成功的状态码，默认：0
                    , msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                    , countName: 'totalCount' //数据总数的字段名称，默认：count
                    , dataName: 'returnData' //数据列表的字段名称，默认：data
                }
            });
        }

        function setCols() {
            var row = [
                {field: 'projectNo', fixed: 'left', title: '项目编号', width: 110, align: 'center'},
                {
                    field: 'estateNm', fixed: 'left', title: '楼盘名称', width: 200, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.estateNm + "</div>";
                    }
                },
                {
                    field: 'realityCityNm', title: '楼盘城市', width: 130, align: 'center', templet: function (d) {
                        var realityCityNm = d.realityCityNm;
                        var countryNm = d.countryNm
                        var estatePosition = d.estatePosition
                        if (estatePosition == 0 || estatePosition == "0") {
                            return realityCityNm;
                        } else {
                            return countryNm;
                        }
                    }
                },
                {field: 'relationUnconfirm', title: '报备', width: 100, align: 'center'},
                {field: 'pledgedUnconfirm', title: '带看', width: 100, align: 'center'},
                {field: 'signUnconfirm', title: '大定', width: 100, align: 'center'},
                {field: 'signConfirm', title: '成销', width: 100, align: 'center'},
                {field: 'brokerageConfirm', title: '结佣', width: 100, align: 'center'},
                {
                    title: '操作', fixed: 'right', align: 'center', width: 110, templet: function (d) {
                        var ret = "<div style='text-align: left'>"
                        ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectList.showDetail(\"" + d.estateId + "\")'>查看</a>";

                        // 如果有权限
                        if (addPermission == "1") {
                            // 不是[20303-结案]，不是[20304-取消跟单]， 必须[12903-审核通过]，必须有发布时间
                            if (d.projectStatus != 20303 && d.projectStatus != 20304 && d.auditStatus == 12903 && !isEmpty(d.releaseDt)) {
                                ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectList.reportAdd(\"" + d.estateId + "\")'>报备</a>";
                            }
                        }
                        ret += "</div>"
                        return ret;
                    }
                }
            ];
            var cols = [];
            cols.push(row);
            return cols;
        }

        var active = {
            reload: function () {
                if (valid()) {
                    var optionsData = {};
                    //optionsData.projectDepartmentId = $("#projectDepartmentId").val();
                    optionsData.projectNo = trimStr($("#projectNo").val());
                    //optionsData.estateNm = trimStr($("#estateNm").val());

                    sessionStorage.removeItem('PMLS_SCENE_TRADE_PROJECT_SEARCH');
                    reloadData(optionsData);
                }
            },
            reset: function () {
                //$("#projectDepartmentId").val("");
                $("#projectNo").val("");
                //$("#estateNm").val("");
                form.render('select');
                active.reload();
            }
        };

        $('.toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        function reloadData(optionsData) {
            //var index = layer.load(2);

            var sessionData = sessionStorage.getItem('PMLS_SCENE_TRADE_PROJECT_SEARCH');
            var pageIndex = 1;
            if (sessionData != null && sessionData != '') {
                optionsData = JSON.parse(sessionData);
                pageIndex = optionsData.curr;
                $("#projectNo").val(optionsData.projectNo);
            }

            table.reload('contentReload', {
                url: BASE_PATH + '/sceneTrade/qSceneEstate',
                cols: setCols(),
                height: window.innerHeight - $(".layui-table-view").offset().top - 10,
                where: optionsData,
                page: {
                    curr: pageIndex //重新从第 1 页开始
                },
                done: function (res, curr, count) {
                    //layer.close(index);
                    if (!optionsData) {
                        optionsData = {};
                    }
                    optionsData.curr = curr;
                    sessionStorage.setItem("PMLS_SCENE_TRADE_PROJECT_SEARCH", JSON.stringify(optionsData));
                }
            });
        }

        function valid() {
            return true;
        }

    }
);

var projectList = {}
// 查看
projectList.showDetail = function (estateId) {
    var url = BASE_PATH + "/sceneTrade/projectDetail/" + estateId;
    parent.redirectTo('append', url, '订单列表');
}

// 报备
projectList.reportAdd = function (estateId) {
    var url = BASE_PATH + "/sceneTrade/reportAdd/" + estateId;
    parent.redirectTo('append', url, '新增订单');
}