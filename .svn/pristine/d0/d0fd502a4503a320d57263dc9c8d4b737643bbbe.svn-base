var table;
var form;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        layer = layui.layer,
        formSelects = layui.formSelects;
    	table = layui.table;
    	form = layui.form;
    // $ = layui.$;

    	projectDepartmentLinkageIsService(function () {
            form.render('select')
        });

        laydate.render({
            elem: '#dateStart',
            type: 'date',
            trigger: 'click',
            format: 'yyyy-MM-dd',
            done: function (value, date, endDate) {
                var startDate = new Date(value).getTime();
                if($('#dateEnd').val()!=''){
                    var endTime = new Date($('#dateEnd').val()).getTime();
                    if (endTime < startDate) {
                        layer.msg('开始时间不能大于结束时间');
                        $('#dateStart').val('');
                    }
                }
            }
        });

        laydate.render({
            elem: '#dateEnd',
            type: 'date',
            trigger: 'click',
            format: 'yyyy-MM-dd',
            done: function (value, date, endDate) {
                if($('#dateStart').val()!=""){
                    var startDate = new Date($('#dateStart').val()).getTime();
                    var endTime = new Date(value).getTime();
                    if (endTime < startDate) {
                        layer.msg('结束时间不能小于开始时间');
                        $('#dateEnd').val('');
                    }
                }
            }
        });

        table.render({
            elem: '#contentTable'
            , cols: setCols()
            , id: 'contentReload'
            , even: false //开启隔行背景
            , page: true
            , limits: [10, 20, 30]
            , limit: 10 //默认采用60
            , method: 'post'
            ,loading:false
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

        

        reloadData();
        
        var active= {
            reload: function () {
                console.info("reload");
                if (valid()) {
                    var optionsData = {};
                    optionsData.projectDepartmentId = $("#projectDepartmentId").val();
                    optionsData.reportNo = trimStr($("#reportNo").val());
                    optionsData.orderStatus = $("#orderStatus").val();
                    optionsData.dateTypeKbn = $("#dateTypeKbn").val();
                    optionsData.dateStart = $("#dateStart").val();
                    optionsData.dateEnd = $("#dateEnd").val();
                    sessionStorage.removeItem('QTREPORT_SEARCH');
                    reloadData(optionsData);
                }
            }
            , reset: function () {
                console.info("reset");
                $("#projectDepartmentId").val("");
                $("#reportNo").val("");
                $("#orderStatus").val("");
                $("#dateTypeKbn").val("");
                $("#dateStart").val("");
                $("#dateEnd").val("");
                form.render('select');
                active.reload();
            }
        };

        $('.toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        

       
        function valid() {
            return true;
        }

        
});

function reloadData(optionsData) {
	var index = layer.load(2);
	
	var sessionData=sessionStorage.getItem('QTREPORT_SEARCH');
	var pageIndex=1;
	if(sessionData!=null && sessionData!=''){
		optionsData=JSON.parse(sessionData);
		pageIndex=JSON.parse(sessionData).curr;
		// 楼盘归属项目部
		projectDepartmentLinkageIsService(function () {
			$("#projectDepartmentId").val(optionsData.projectDepartmentId);
			form.render('select')
		});
		$("#reportNo").val(optionsData.reportNo);
		$("#orderStatus").val(optionsData.orderStatus);
		$("#dateTypeKbn").val(optionsData.dateTypeKbn);
		$("#dateStart").val(optionsData.dateStart);
		$("#dateEnd").val(optionsData.dateEnd);
		form.render();
	}
	   var data = {};
	   if(optionsData != undefined){
		   data = optionsData;
	   }
	   var qtType = $("#qtType").val();
	   if(!isBlank(qtType) && qtType == 'qtProject'){
		   data.projectNo = $("#projectNo").val();
		   data.estateId = $("#estateId").val();
		   data.estateName = $("#estateName").val();
	   }
     
     table.reload('contentReload', {
         url: BASE_PATH + '/pmlsQtReport/qtReportList',
         cols: setCols(),
         height: window.innerHeight - $(".layui-table-view").offset().top - 10,
         where: data,
         page:{
             curr: pageIndex //重新从第 1 页开始
         },
         done: function (res, curr, count) {
             layer.close(index);
             if(!optionsData){
                 optionsData={};
             }
             optionsData.curr=curr;
             sessionStorage.setItem("QTREPORT_SEARCH",JSON.stringify(optionsData));
         }
     });
 }

//查看
function showDetail(estateId,id){
	var qtType = $("#qtType").val();
	if(isBlank(qtType)){
		qtType = "qtReport";
	}
    var url =ctx+"/pmlsQtReport/qtReportDetail/"+estateId+"/"+id+"/"+qtType;
    parent.redirectTo('append',url,'订单详情');
}    

function setCols() {
    var row = [
        {
            field: 'reportNo', fixed: true, title: '订单编号', width: 180, align: 'center', templet: function (d) {
                return  "<a class='layui-table-link' onclick='showDetail(\"" + d.estateId + "\",\"" + d.id +"\")'>"+d.reportNo+"</a>";
            }
        },
        {field: 'srTypeName', title: '收入类型', width: 120, align: 'center'},
        {field: 'srAmount', title: '收入金额', width: 120, align: 'center', style:'text-align: right',
        	templet: function (d) {
                return formatMoney2(d.srAmount);
            }
        },
        {field: 'reportStatusName', title: '最新进度', width: 120, align: 'left'},
        {field: 'ackStatusName', title: '确认状态', width: 150, align: 'center'},
        {
            field: 'reportDate', title: '报备日期', width: 150, align: 'center',
            templet: function (d) {
                if (d.reportDate == null) {
                    return "-";
                } else {
                    return formatDate(d.reportDate, "yyyy-MM-dd");
                }
            }
        },
        {field: 'dealDate', title: '成销日期', width: 150, align: 'center',
            templet: function (d) {
                if (d.dealDate == null) {
                    return "-";
                } else {
                    return formatDate(d.dealDate, "yyyy-MM-dd");
                }
            }
        },

        {field: 'dealAmount', title: '成销金额', width: 120, align: 'center',
        	templet: function (d) {
                return formatMoney2(d.dealAmount);
            }
        },

        {
            field: 'backDealDate', title: '退成销日期', width: 150, align: 'center',
            templet: function (d) {
                if (d.backDealDate == null) {
                    return "-";
                } else {
                    return formatDate(d.backDealDate, "yyyy-MM-dd");
                }
            }
        },
        {
            title: '操作', fixed: 'right', align: 'left',  width: 200,
            templet: function (d) {
                var ret = "<a class='layui-btn layui-btn-xs' onclick='showDetail(\"" + d.estateId + "\",\"" + d.id +"\")'>查看</a>";
                if ((d.reportStatus == 27002 && d.validStatus == 0) && d.brokerageStatus != 22002 && d.brokerageStatus != 22003) {
                    ret += " <a class='layui-btn layui-btn-xs' onclick='backDeal(\"" + d.id + "\")'>退成销</a>"
                }else{
                	if(d.reportStatus == 27001 && d.validStatus == 0){
                        ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:operateDeal(\"" + d.id + "\")'>成销</a>";
                		ret += " <a class='layui-btn layui-btn-danger layui-btn-xs' onclick='invalid(\"" + d.id +  "\")'>无效</a>"
                	}
                }
                return ret;
            }
        }
    ];
    var cols = [];
    cols.push(row);
    return cols;
}
                        
// 无效
function invalid(reportId) {
	var url = BASE_PATH + '/pmlsQtReport/validQtReport/'+reportId;
    parent.layer.confirm("确认要无效吗？",{icon: 3, title:'提示'},function(){
    	parent.layer.load(2);
            $.ajax({
                type: "POST",
                url: url,
                dataType: "json",
                success: function (data) {
                	parent.layer.closeAll();
                    console.log(data);
                    if (data.returnCode != 200){
                        parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                    }else{
                        parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'},function(){
                            parent.layer.closeAll();
                            reloadData();
                        });
                    }
                }
            });
    });
}

// 成销
function operateDeal(id) {
    var url= BASE_PATH + "/pmlsQtSuccessSale/qtSuccessSaleHandle/"+id
    parent.redirectTo('append',url,'成销');
}

//退成销
function backDeal(id) {
    var url= BASE_PATH + "/pmlsQtSuccessSale/toDealBack/"+id
    parent.redirectTo('append',url,'退成销');
}
