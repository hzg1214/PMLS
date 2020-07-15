layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;

    changeLogListInit();

    function changeLogListInit() {

        tableRender();
    }

    function tableRender() {
        table.render({
            elem: '#mattressControlRuleTable'
            , cols: setCols()
            , url: BASE_PATH + '/pmlsEstatePlan/queryMattressControlRuleList/' + $("#projectNo").val()
            , id: 'contentReload'
            , height: "full"
            , page: true
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
                {type:'numbers', title: '序号', width: 90, align: 'center'},
                {field:'oaId', title: 'OA编号', width: 200, align: 'center'},
                {field: 'isGlobalControl', title: '垫佣控制规则', width: 200, align: 'center'
                	,templet: function (d) {
                		if(d.isGlobalControl=='1'){
                			return '项目总控';
                		}else if(d.isGlobalControl=='0'){
                			return '房源单控';
                		}
                    }
                },
                {field: 'gCStartDate', title: '垫佣周期', width: 260, align: 'center',
                	templet: function (d) {
                        if (d.gCStartDate !='' && d.gCStartDate !=null ) {
                        	var returnDate = formatDate(d.gCStartDate, "yyyy-MM-dd")+'至~';
                        	if(d.gCEndDate !='' && d.gCEndDate !=null){
                        		returnDate = formatDate(d.gCStartDate, "yyyy-MM-dd")+'至'+formatDate(d.gCEndDate, "yyyy-MM-dd");
                        	}
                            return returnDate;
                        }else {
                            return '-';
                        }
                    }
                },
                {field: 'dyRatioPercent', title: '垫佣比例', width: 180,align: 'center'
                	,templet: function (d) {
                        if (d.dyRatioPercent !='' && d.dyRatioPercent !=null ) {
                            return d.dyRatioPercent+'%';
                        }else if(d.dyRatioPercent ==0){
                        	return '0%';
                        }
                    }
                },
                {field: 'dateCreate', title: '创建时间', align: 'center'
                	,templet: function (d) {
                        if (d.dateCreate !='' && d.dateCreate !=null ) {
                            return formatDate(d.dateCreate, "yyyy-MM-dd hh:mm:ss");
                        }
                    }
                }
            ]
            var cols = [];
            cols.push(row);
            return cols;
        }

});


