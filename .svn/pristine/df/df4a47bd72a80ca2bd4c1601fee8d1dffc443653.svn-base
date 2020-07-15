var active;
var layer;
layui.use(['element', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        form = layui.form,
        table = layui.table,
        $ = layui.$;
    layer = layui.layer;

    var toTermination = NullToZero($("#toTermination").val());
    var userId = NullToEmpty($("#userId").val());
    init();

    function init() {
        form.render('select'); //刷新select选择框渲染

        tableRender();

        reloadData();
    }

    function tableRender() {
        table.render({
            elem: '#mainTable'
            , cols: setCols()
            , id: 'contentReload'
            , data: []
            , page: true
            , height: "full"
            , limits: [10, 20, 30]
            , limit: 10 //默认采用60
            , method: 'post'
            , loading: false
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
        var row = [];
        row.push(
            {
                field: 'jssNo', fixed: true, title: '结算书编号', width: 150, align: 'center'
                , templet: function (d) {
                    return "<a class='layui-table-link' onclick='javascript:jsStatementList.showDetail(\"" + d.id + "\")'>" + d.jssNo + "</a>";
                }
            },
            {field: 'companyName', fixed: true, title: '渠道公司', width: 250, align: 'center', style: 'text-align: left'},
            {field: 'projectName', fixed: true, title: '项目名称', width: 160, align: 'center', style: 'text-align: left'},
            {
                field: 'contractYdTotalAmount',
                title: '合同约定金额（元）',
                width: 160,
                align: 'center',
                style: 'text-align: right'
                ,
                templet: function (d) {
                    var contractYdTotalAmount = d.contractYdTotalAmount;
                    return formatMoney(contractYdTotalAmount)
                }
            },
            {
                field: 'jsTotalAmount', title: '结算金额（元）', width: 160, align: 'center', style: 'text-align: right'
                , templet: function (d) {
                    return formatMoney(d.jsTotalAmount);
                }
            },
            {
                field: 'kpTotalAmount', title: '开票金额（元）', width: 160, align: 'center', style: 'text-align: right'
                , templet: function (d) {
                    return formatMoney(d.kpTotalAmount);
                }
            },
            {
                field: 'requestAmount', title: '已请款金额（元）', width: 160, align: 'center', style: 'text-align: right'
                , templet: function (d) {
                    return formatMoney(d.requestAmount);
                }
            },
            {field: 'approveStatusNm', title: '审核状态', width: 100, align: 'center'},
            {field: 'userCreateName', title: '创建人', width: 100, align: 'center'},
            {
                field: 'dateCreate', title: '创建日期', width: 120, align: 'center',
                templet: function (d) {
                    if (d.dateCreate == null) {
                        return "-";
                    } else {
                        return formatDate(d.dateCreate, "yyyy-MM-dd");
                    }
                }
            },
            {
                title: '操作', fixed: 'right', align: 'center', style: 'text-align: left', width: 155, templet: function (d) {
                    var ret = "<a class='layui-btn layui-btn-xs' onclick='javascript:jsStatementList.showDetail(\"" + d.id + "\")'>查看</a>";
                    // 审核状态: 10401-草稿
                    if (d.approveStatus == 10401 || d.approveStatus == 10404) {
                        ret += "<a class='layui-btn layui-btn-normal layui-btn-xs' onclick='javascript:jsStatementList.update(\"" + d.id + "\")'>编辑</a>";
                        ret += "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:jsStatementList.toInvalid(\"" + d.id + "\")'>作废</a>";
                    }
                    // 审核状态: 10403-审核通过
                    if (d.approveStatus == 10403 && d.requestAmount == 0) {
                        if (toTermination == "1" || d.userIdCreate == userId) {
                            ret += "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:jsStatementList.toTermination(\"" + d.id + "\")'>终止</a>";
                        }
                    }
                    return ret;
                }
            }
        );

        var cols = [];
        cols.push(row);
        return cols;
    }


    active = {
        addJsStatement: function () {
            jsStatementList.add();
        },
        reload: function () {
            var optionsData = {};
            optionsData.jssNo = trimStr($("#jssNo").val());
            optionsData.reportId = trimStr($("#reportId").val());
            optionsData.companyNo = trimStr($("#companyNo").val());
            optionsData.projectNo = trimStr($("#projectNo").val());
            optionsData.approveStatus = $("#approveStatus").val();

            sessionStorage.removeItem('PMLS_JS_STATEMENT_SERVICE_SEARCH');
            reloadData(optionsData);
        },
        reset: function () {
            $("#jssNo").val("");
            $("#reportId").val("");
            $("#companyNo").val("");
            $("#projectNo").val("");
            $("#approveStatus").val("");
            form.render('select'); // 刷新单选框渲染
            active.reload();
        }
    };


    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
        var index = layer.load(2);
        var sessionData = sessionStorage.getItem('PMLS_JS_STATEMENT_SERVICE_SEARCH');
        var pageIndex = 1;
        if (sessionData != null && sessionData != '') {
            optionsData = JSON.parse(sessionData);
            pageIndex = optionsData.curr;
            $("#jssNo").val(optionsData.jssNo);
            $("#reportId").val(optionsData.reportId);
            $("#companyNo").val(optionsData.companyNo);
            $("#projectNo").val(optionsData.projectNo);
            $("#approveStatus").val(optionsData.approveStatus);
            form.render('select'); // 刷新单选框渲染
        }

        table.reload('contentReload', {
            url: BASE_PATH + '/jsStatement/queryList',
            cols: setCols(),
            where: optionsData,
            height: window.innerHeight - $(".layui-table-view").offset().top - 10,
            page: {
                curr: pageIndex //重新从第 1 页开始
            },
            done: function (res, curr, count) {
                layer.close(index);
                if (!optionsData) {
                    optionsData = {};
                }
                optionsData.curr = curr;
                sessionStorage.setItem("PMLS_JS_STATEMENT_SERVICE_SEARCH", JSON.stringify(optionsData));
            }
        });
    }
});


var jsStatementList = {}

// 新增结算书
jsStatementList.add = function () {
    var url = BASE_PATH + "/jsStatement/create";
    parent.redirectTo('append', url, '结算书申请');
}

// 修改结算书
jsStatementList.update = function (id) {
    var url = BASE_PATH + "/jsStatement/modify/" + id;
    parent.redirectTo('append', url, '结算书申请');
}

// 结算书详情
jsStatementList.showDetail = function (id) {
    var url = BASE_PATH + "/jsStatement/jsStatementDetailPage?id=" + id
    parent.redirectTo('append', url, '结算书详情');
}

// 作废
jsStatementList.toInvalid = function (id) {

    var loadIndex = parent.layer.load(2, {shade: [0.1]});
    parent.msgConfirm("确认要作废吗?", function () {
            var url = BASE_PATH + "/jsStatement/toInvalid";
            var params = {
                id: id
            };
            var loadIndex = parent.layer.load(2, {shade: [0.1]});
            restPost(url, params
                , function (data) {
                    parent.layer.close(loadIndex);
                    if (data.returnData == 200) {
                        parent.msgProp("操作成功！");
                        active.reload();
                    }
                    else {
                        parent.msgAlert(data.returnMsg);
                        active.reload();
                    }
                }, function (data) {
                    parent.layer.close(loadIndex);
                    parent.msgAlert(data.returnMsg);
                });
        }
    )
}

// 终止
jsStatementList.toTermination = function (id) {
    parent.layer.open({
        type: 2
        , title: '结算书终止'
        , content: BASE_PATH + '/jsStatement/jsStatementTerminatPopup'
        , area: ['600px', '270px']
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {

            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            if (formData != null) {
                parent.layer.close(index);
                var terminationDesc = formData.terminationDesc;
                var url = BASE_PATH + "/jsStatement/toTermination";
                var params = {
                    "id": id,
                    "terminationDesc": terminationDesc
                }
                var loadIndex = parent.layer.load(2, {shade: [0.1]});
                restPost(url, params
                    , function (data) {
                        parent.layer.close(loadIndex);
                        if (data.returnData == 200) {
                            parent.msgProp("操作成功！");
                            active.reload();
                        }
                        else {
                            parent.msgAlert(data.returnMsg);
                            active.reload();
                        }
                    }, function (data) {
                        parent.layer.close(loadIndex);
                        parent.msgAlert(data.returnMsg);
                    });

            }

        }
    });
}




