var qtReportInfoDto={};
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        $ = layui.$;

    init();
    

    function init() {
    	if(window.qtReportInfo!=''){
    		//qtReportInfoDto=eval('(' + window.qtReportInfo + ')');
            qtReportInfoDto=eval('(' + window.qtReportInfo.replace(/[\r\n]/g,"\\n") + ')');
    	}
        var reportNo = $("#reportNo").val();

        // 进度
        reportProgressTableRender(qtReportInfoDto);

        // 日志
        reportLogTableRender(qtReportInfoDto.qtLogList);

    }

    //进度
    function reportProgressTableRender(qtReportInfoDto) {
    	
    	var newArray=[];
    	var qtReportDetail = qtReportInfoDto.qtReportDetailList;
    	
    	if(qtReportDetail!=null && qtReportDetail.length>0){
    		for(var i=0;i<qtReportDetail.length;i++){
    			if(qtReportDetail[i].businessType != '27303'){//排除退成销
    				newArray.push(qtReportDetail[i]);
    			}
    		}
    		//结佣  添加数据
    		if(qtReportInfoDto.brokerageStatus == '22002' || qtReportInfoDto.brokerageStatus == '22003'){
    			
    			var brokerageDate = $("#brokerageDate").val();//业务节点时间
    			var brokerageUserName = $("#brokerageUserName").val();//操作人
    			var brokerageUptDate = $("#brokerageUptDate").val();//操作时间
    			var brokerageCrtDate = $("#brokerageCrtDate").val();
    			
    			var brokerageReportDetail = {};
    			brokerageReportDetail.businessType='27308';//进度
    			brokerageReportDetail.businessDate=brokerageDate;//业务节点时间
    			brokerageReportDetail.userName=brokerageUserName;//操作人
    			//操作时间
    			if(brokerageUptDate != null && brokerageUptDate != "" && brokerageUptDate != undefined){
    				brokerageReportDetail.uptDate=brokerageUptDate;
    			}else{
    				brokerageReportDetail.uptDate=brokerageCrtDate;
    			}
    			newArray.push(brokerageReportDetail);
    		}
    	}
    	
        table.render({
            elem: '#qtReportProgressTable'
            , cols: setReportProgressCols()
//            , url: BASE_PATH + '/sceneTrade/report/' + reportId + '/progress'
            , data:newArray
            , height: 'full'
            , id: 'progressReload'
            , even: false //开启隔行背景
            , page: false
            , width: 1100
            , limit: 1000 //默认采用60
            , method: 'post'
            , request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
        });
    }

    //日志
    function reportLogTableRender(logInfoJsonDto) {
        table.render({
            elem: '#qtReportLogTable'
            , cols: setReportLogCols()
            , data:logInfoJsonDto
            , id: 'logReload'
            , even: false //开启隔行背景
            , page: false
            , width: 1100
            , limits: [10, 20, 30]
            , limit: 10 //默认采用60
            , method: 'post'
            , request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
        });
    }

    //进度
    function setReportProgressCols() {
    	var validStatus = $("#validStatus").val();//是否有效
    	var id = $("#id").val();//主表id
    	var brokerageStatus = $("#brokerageStatus").val();//是否结佣状态
    	var row = [];
	        row = [
	            {field: 'businessType', title: '进度', width: 120, align: 'center',
	            	templet: function (d) {
	                    if (d.businessType == '27301') {
	                        return "报备";
	                    } else if(d.businessType == '27302'){
	                    	return "成销";
	                    }else if(d.businessType == '27308'){
	                    	return "结佣";
	                    }
	                }
	            },
	            {field: 'validStatus', title: '确认状态', width: 172, align: 'center',
	            	templet: function (d) {
	                    if (validStatus == 0) {
	                        return "有效";
	                    } else if(validStatus == 1){
	                    	return "无效";
	                    }
	                }
	            },
	            {
	                field: 'businessDate', title: '业务节点发生时间', width: 220, align: 'center',
	                templet: function (d) {
	                    if (d.businessDate == null || d.businessDate == "") {
	                        return "";
	                    } else {
	                        return formatDate(d.businessDate, "yyyy-MM-dd");
	                    }
	                }
	            },
	            {field: 'userName', title: '操作人', width: 180, align: 'center'},
	            {
	                field: 'uptDate', title: '日期', width: 200, align: 'center',
	                templet: function (d) {
	                    if (d.uptDate == null) {
	                        return formatDate(d.crtDate, "yyyy-MM-dd");
	                    } else {
	                        return formatDate(d.uptDate, "yyyy-MM-dd");
	                    }
	                }
	            },
	            {title: '操作', align: 'center',width: 200,
	            	templet: function(row) {
	                    var showContent='';
	                    if(row.businessType != '27308'){//不是结佣
	                    	showContent+='<a class="layui-btn layui-btn-xs" style="width:60px;" onclick="toOperDetail(' + id +','+row.businessType+ ')">查看</a>';
	                    }else if(brokerageStatus == '22002' || brokerageStatus == '22003'){
	                    	showContent+='<a class="layui-btn layui-btn-xs" style="width:60px;" onclick="toOperDetail(' + id +','+ brokerageStatus+ ')">查看</a>';
	                    }
	                    return showContent; //列渲染
	                }
	            }
	        ]
        var cols = [];
        cols.push(row);
        return cols;
    }

    //日志
    function setReportLogCols() {
        var row = [
            {type: 'numbers', title: '序号', width: 80, align: 'center'},
            {field: 'opMemo', title: '变更内容', width: 620, align: 'left'},
            {
                field: 'logUserName', title: '操作人(工号)', width: 200, align: 'center',
                templet: function (d) {
                    var userName = d.logUserName == null ? "" : d.logUserName;
                    var userCode = d.logUserCode == null ? "" : d.logUserCode;
                    return userName + "(" + userCode + ")";
                }
            },
            {field: 'crtDate', title: '操作时间', align: 'center'}
        ]
        var cols = [];
        cols.push(row);
        return cols;
    }

});


//查看
function toOperDetail(qtReportId,operStatus){
	var url = "";
	var title = "";
	if(operStatus == '27301'){
		title = "报备";
	}else if(operStatus == '27302'){
		title = "成销";
	}else if(operStatus == '22003' || operStatus == '22002'){
		title = "结佣";
	}
	url = ctx + '/pmlsQtReport/toOperDetail?id='+qtReportId+'&operStatus='+operStatus;
    parent.layer.open({
        type: 2,
//        btn: ['确认', '取消'],
        title: title,
        area: ['950px', '600px'],
        content: url,
        yes: function(index, layero){
        }
    });
    
}
//取消返回上一个页面
function goBack(){
	parent.redirectTo('delete',null,null);
}


