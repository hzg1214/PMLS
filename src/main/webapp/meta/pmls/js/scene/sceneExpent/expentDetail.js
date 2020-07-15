layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        $ = layui.$;

    init();
    function init(){
        tableRender();
    }

    function tableRender() {
        var  listJson = {};
        if(window.reportDetails!=''){
            //listJson=eval('(' + window.reportDetails + ')');
            listJson = eval('(' + window.reportDetails.replace(/[\r\n]/g,"\\n") + ')');
        }else{
        	listJson=[];
        }
        console.log(listJson);
        
        //offSetFlag:true 红冲  
//        var newArray = [];
//        var offSetArray = [];//红冲数组
//        if (listJson != null && listJson.length > 0) {
//            for (var i = 0; i < listJson.length; i++) {
//                if (listJson[i].offSetFlag) {
//                	offSetArray.push(listJson[i]);
//                }else{
//                	newArray.push(listJson[i]);
//                }
//            }
//        }

//        if(offSetArray.length==0){
//            $("#offSetContentTable").parents(".layui-card-body").hide()
//        }
        
        table.render({
            elem: '#contentTable'
            , cols: setCols()
            , id: 'contentReload'
            , height: 'full'
//            , width:'100%'
//            , data: newArray
            , data: listJson
//            ,totalRow: true
            , even: false //开启隔行背景
            , page: false
            , limit:1000
            , method: 'post'
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
            , done: function(res, curr, count){
               // $(".layui-table-fixed th").height($(".layui-table thead").height());
            	$('th').css({'font-size':'12px'})
            }
        });
        
//        table.render({
//        	elem: '#offSetContentTable'
//        		, cols: setCols()
//        		, id: 'offSetContentReload'
//        		, height: 'full'
//        		, width:'80%'
//        		, data: offSetArray
//                , limit:1000
//        		, totalRow: true
//        		, even: false //开启隔行背景
//        		, page: false
//        		, method: 'post'
//        		, request: {
//        			pageName: 'curPage' //页码的参数名称，默认：page
//        			, limitName: 'pageLimit' //每页数据量的参数名，默认：limit
//        		}
//        , response: {
//        	statusName: 'returnCode' //数据状态的字段名称，默认：code
//        	, statusCode: 200 //成功的状态码，默认：0
//        	, msgName: 'returnMsg' //状态信息的字段名称，默认：msg
//        	, countName: 'totalCount' //数据总数的字段名称，默认：count
//        	, dataName: 'returnData' //数据列表的字段名称，默认：data
//        }
//        , done: function(res, curr, count){
//        	// $(".layui-table-fixed th").height($(".layui-table thead").height());
//        	$('#contentTable').next().find('.layui-table-fixed .layui-table-body .layui-table tr:last td div:first').html('');
//        	$('th').css({'font-size':'12px'})
//        }
//        });
    }

    function setCols() {
        var row1 = [
            {type:'numbers',fixed:true,title: '序号',size: 12, width:60, align: 'center'
            	,templet: function (d) {
                    if (d.id == null) {
                        return "";
                    }else{
                    	return d.numbers;
                    }
                }
            },
            {field: 'reportNo', fixed:true, title: '订单编号',  width: 150, align: 'center'},
            {field: 'isGlobalControl', title: '垫佣控制规则',fixed:true, width: 120, align: 'center'
            	,templet: function (d) {
            		if(d.id!=null){
	            		if(d.isGlobalControl=='1'){
	            			return '项目总控';
	            		}else if(d.isGlobalControl=='0' || d.isGlobalControl==null || d.isGlobalControl==""){
	            			return '房源单控';
	            		}
            		}else{
            			return "";
            		}
            	}
            },
            {field: 'projectName', fixed:true, title: '项目',  width: 250, align: 'center'
            	,templet: function (d) {
            		if(d.id!=null){
	            		if (d.projectName == null || d.projectName =="" || d.projectName==undefined) {
	            			return "-";
	            		}else{
	            			return "<div style='text-align: left'>" + d.projectName + "</div>";
	            		}
            		}else{
            			return "合计";
            		}
            	}
            },
            {field: 'customerNm', title: '客户姓名',  width: 120, align: 'center'},
            {field: 'buildingNo', title: '楼室号',  width: 120, align: 'center'},
            {field: 'area', title: '面积（㎡）',  width: 120, align: 'center' 
                ,templet: function (d) {
                    if (d.area == null) {
                        return "-";
                    } else {
                        return "<div style='text-align: right'>" + formatMoney(d.area) + "</div>";
                    }
                }
            },
//            {field: 'roughAmount', title: '大定总价', rowspan: 2, width: 130, align: 'center',
//                templet: function (d) {
//                    if (d.roughAmount == null) {
//                        return "-";
//                    } else {
//                        return formatMoney(d.roughAmount);
//                    }
//                }},
            {field: 'dealAmount', title: '签约总价（元）',  width: 130, align: 'center'
                ,templet: function (d) {
                    if (d.dealAmount == null) {
                        return "-";
                    } else {
                        return "<div style='text-align: right'>" + formatMoney(d.dealAmount) + "</div>";
                    }
                }
            },
            {field: 'requestAmount', title: '含税请款金额（元）',  width: 160, align: 'center'
                ,templet: function (d) {
                    if (d.requestAmount == null) {
                        return "-";
                    } else {
                        return "<div style='text-align: right'>" + formatMoney(d.requestAmount) + "</div>";
                    }
                }
            },
            {field: 'taxAmount', title: '税额（元）', width: 130, align: 'center'
                ,templet: function (d) {
                    if (d.taxAmount == null) {
                        return "-";
                    } else {
                        return "<div style='text-align: right'>" + formatMoney(d.taxAmount) + "</div>";
                    }
                }
            },
            {field: 'requestType', title: '请款类别',  width: 100, align: 'center'
                ,templet: function (d) {
                	if(d.id!=null){
//                		if (d.requestType == 0) {
//                			return "借佣";
//                		} else 
                		if(d.requestType==1){
                			return "返佣";
                		}else if(d.requestType==2){
                			return "垫佣";
                		}else{
                			return "-";
                		}
                	}else{
                		return "";
                	}
                }
            },
            {field: 'accountProject', title: '核算主体',  width: 160, align: 'center'},
            {field: 'checkBodyName', title: '考核主体',  width: 250, align: 'center'},
            {field: 'memo', title: '说明',  width: 100, align: 'center'}
        ];
//        var row2 = [
//            {field: 'sqYjsrAmount', title: '应计收入', rowspan: 2, width: 130, align: 'center',totalRow: true,
//                templet: function (d) {
//                    if (d.sqYjsrAmount == null) {
//                        return "-";
//                    } else {
//                        return formatMoney(d.sqYjsrAmount);
//                    }
//                }},
//            {field: 'sqYjfyAmount', title: '应计返佣', rowspan: 2, width: 130, align: 'center',totalRow: true,
//                templet: function (d) {
//                    if (d.sqYjfyAmount == null) {
//                        return "-";
//                    } else {
//                        return formatMoney(d.sqYjfyAmount);
//                    }
//                }},
//            {field: 'sqYjdyAmount', title: '应计垫佣', rowspan: 2, width: 130, align: 'center',totalRow: true,
//                templet: function (d) {
//                    if (d.sqYjdyAmount == null) {
//                        return "-";
//                    } else {
//                        return formatMoney(d.sqYjdyAmount);
//                    }
//                }},
//            {field: 'sqSjsrAmount', title: '实收收入', rowspan: 2, width: 130, align: 'center',totalRow: true,
//                templet: function (d) {
//                    if (d.sqSjsrAmount == null) {
//                        return "-";
//                    } else {
//                        return formatMoney(d.sqSjsrAmount);
//                    }
//                }},
//            {field: 'sqSjfyAmount', title: '实际返佣', rowspan: 2, width: 130, align: 'center',totalRow: true,
//                templet: function (d) {
//                    if (d.sqSjfyAmount == null) {
//                        return "-";
//                    } else {
//                        return formatMoney(d.sqSjfyAmount);
//                    }
//                }},
//            {field: 'sqSjdyAmount', title: '实际垫佣', rowspan: 2, width: 130, align: 'center',totalRow: true,
//                templet: function (d) {
//                    if (d.sqSjdyAmount == null) {
//                        return "-";
//                    } else {
//                        return formatMoney(d.sqSjdyAmount);
//                    }
//                }}
//        ]
        var cols = [];
        cols.push(row1);
//        cols.push(row2);
        return cols;
    }


    var uploadFpImgList=[],uploadOtherImgList=[],uploadCxImgList=[];
    if(fileList!=null && fileList.length>0){
        for(var i=0;i<fileList.length;i++){
            if(fileList[i].fileTypeId=='1089'){
            	uploadFpImgList.push(fileList[i]);
            }else if(fileList[i].fileTypeId=='1090'){
            	uploadOtherImgList.push(fileList[i]);
            }else if(fileList[i].fileTypeId=='1032'){
            	uploadCxImgList.push(fileList[i]);
	        }
        }
        if(uploadCxImgList.length>0){
            $("#upload_cxImg_list").show();
        }
    }
    //成销确认书/佣金结算资料
    loadImageList('upload_cxImg_list',uploadCxImgList,false);
    //发票
    loadImageList('upload_fpImg_list',uploadFpImgList,false);
    //其它
    loadImageList('upload_qtImg_list',uploadOtherImgList,false);
//    setTimeout(function () {
//        var viewer = new Viewer(document.getElementById('upload_cxImg_list'),{
//            url:'data-original'
//        });
//    },100);
});

function  goback(){
    // var url = '/pmlsCashBill';
    // window.location = url
    parent.redirectTo('delete',null,null);
}