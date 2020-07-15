var form;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
        var element = layui.element,
            laydate = layui.laydate,
            table = layui.table,
            layer = layui.layer,
            formSelects = layui.formSelects,
            $ = layui.$;
        form = layui.form;

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
                , even: false //开启隔行背景
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
            var row = [
                {field: 'projectNo', fixed: 'left', title: '项目编号', width: 160, align: 'center'},
                {
                    field: 'estateNm', fixed: 'left', title: '楼盘名称', width: 250, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.estateNm + "</div>";
                    }
                },
                {
                    field: 'realityCityNm', title: '楼盘城市', width: 150, align: 'center', templet: function (d) {
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
                {field: 'bbCount', title: '报备', width: 130, align: 'center'},
                {field: 'cxCount', title: '成销', width: 130, align: 'center'},
                {field: 'jyCount', title: '结佣', width: 130, align: 'center'},
                {
                    title: '操作', fixed: 'right', align: 'center',  templet: function (d) {
                        var ret = "<div style='text-align: left'>"
                        ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:showDetail(\"" + d.estateId + "\")'>查看</a>";
//                      只有审核状态为通过,并且发布过[发布时间不为Null]的才能报备
                        if(d.auditStatus == 12903 && d.releaseDt != null){
                        	ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:qtReportAdd(\"" + d.id + "\")'>报备</a>";
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
                    optionsData.projectDepartmentId = $("#projectDepartmentId").val();
                    var projectNo = trimStr($("#projectNo").val());
                    var estateNm = trimStr($("#estateNm").val());
                    if(isIncludeBlank(projectNo)){
                    	parent.layer.alert('项目编号中间有空格，请重新输入！',{icon: 2, title:'提示'});
                    	return;
                    }
                    if(isIncludeBlank(estateNm)){
                    	parent.layer.alert('项目名称中间有空格，请重新输入！',{icon: 2, title:'提示'});
                    	return;
                    }
                    optionsData.projectNo = projectNo;
                    optionsData.estateNm = estateNm;
                    sessionStorage.removeItem('QTPROJECT_SEARCH');
                    reloadData(optionsData);
                }
            },
            reset: function () {
                $("#projectDepartmentId").val("");
                $("#projectNo").val("");
                $("#estateNm").val("");
                form.render('select');
                active.reload()
            }
        };

        $('.toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        function reloadData(optionsData) {
            var index = layer.load(2);
            
            var sessionData=sessionStorage.getItem('QTPROJECT_SEARCH');
            var pageIndex=1;
            if(sessionData!=null && sessionData!=''){
            	// 楼盘归属项目部
                projectDepartmentLinkageIsService(function () {
                	$("#projectDepartmentId").val(optionsData.projectDepartmentId);
                    form.render('select')
                });
                optionsData=JSON.parse(sessionData);
                pageIndex=JSON.parse(sessionData).curr;
                $("#projectNo").val(optionsData.projectNo);
                $("#estateNm").val(optionsData.estateNm);
                form.render();
            }
            
            //tableRender();
            table.reload('contentReload', {
                url: BASE_PATH + '/pmlsQtProject/qtProjectList',
                cols: setCols(),
                height: window.innerHeight - $(".layui-table-view").offset().top - 10,
                where: optionsData,
                page:{
                    curr: pageIndex //重新从第 1 页开始
                },
                done: function (res, curr, count) {
                    layer.close(index);
                    if(!optionsData){
                        optionsData={};
                    }
                    optionsData.curr=curr;
                    sessionStorage.setItem("QTPROJECT_SEARCH",JSON.stringify(optionsData));
                }
            });
        }

        function valid() {
            return true;
        }

    }
);


// 查看
function showDetail(estateId) {

	var url = BASE_PATH + "/pmlsQtProject/qSceneRecognition?estateId=" + estateId;
    parent.redirectTo('append', url, '项目详情');
}

// 报备
function qtReportAdd(id) {
    var url= BASE_PATH + "/pmlsQtReport/toQtReport/"+id
    parent.redirectTo('append',url,'新增报备');
}