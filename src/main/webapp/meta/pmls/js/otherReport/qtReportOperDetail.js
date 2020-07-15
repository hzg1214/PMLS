var $;
var yjJsonDto={};
var reportInfo={};
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        formSelects = layui.formSelects,
        layer = layui.layer;
        $ = layui.$;
        
        //初始化附件预览
		var uploadCXImgList=[],//成销确认书/佣金结算资料
		    uploadFYImgList=[];//返佣确认函
	    if(window.reportInfoDto!=''){
	    	//reportInfo=eval('(' + window.reportInfoDto + ')');
            reportInfo=eval('(' + window.reportInfoDto.replace(/[\r\n]/g,"\\n") + ')');
	    }
	    if(reportInfo!=null && reportInfo.fileList!=null && reportInfo.fileList.length>0){
	        for(var i=0;i<reportInfo.fileList.length;i++){
	            if(reportInfo.fileList[i].fileTypeId=='1064'){
	            	uploadFYImgList.push(reportInfo.fileList[i]);
	            }else if(reportInfo.fileList[i].fileTypeId=='1025'){
	            	uploadCXImgList.push(reportInfo.fileList[i]);
	            }
	        }
	    }
        
    var operStatus = $("#operStatus").val();
    
    if(operStatus == '22003' || operStatus == '22002' || operStatus == '27302'){//结佣或成销
	    //成销确认书/佣金结算资料
    	loadImageList('upload_cxImg_list',uploadCXImgList,false);
		//返佣确认函
		loadImageList('upload_fyImg_list',uploadFYImgList,false);
    }
    
    if(window.yjReport!=''){
    	//yjJsonDto=eval('(' + window.yjReport + ')');
        yjJsonDto=eval('(' + window.yjReport.replace(/[\r\n]/g,"\\n") + ')');

    }
    if(operStatus == '22003' || operStatus == '22002'){//结佣
    	jyFyInit(yjJsonDto);
    }else if(operStatus == '27302'){//成销
    	cxFyInit(yjJsonDto);
    }
    
    function cxFyInit(yjJsonDto){
    	table.render({
            elem: '#qtReportCxFyTable'
            , cols: setCxCol()
            , data:yjJsonDto
            , id: 'cxFyReload'
            , width : 825
            , even: false //开启隔行背景
            , page: false
            , limit: 1000 //默认采用60
            , method: 'post'
            , request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
        });
    }
    
    function jyFyInit(yjJsonDto){
    	table.render({
    		elem: '#qtReportJyFyTable'
    			, cols: setJyCol()
    			, data:yjJsonDto
    			, id: 'jyFyReload'
//    				,height: 40
//    				,totalRow: true
    				, even: false //开启隔行背景
    				, page: false
    				, limits: [10, 20, 30]
    	, limit: 10 //默认采用60
    	, method: 'post'
    		, request: {
    			pageName: 'curPage' //页码的参数名称，默认：page
    				, limitName: 'pageLimit' //每页数据量的参数名，默认：limit
    		}
    	});
    }
    
    function setCxCol() {//成销
    	var row = [];
    	row = [
    		{type: 'numbers', title: '序号', width: 130, align: 'center'},
    		{field: 'companyName', title: '返佣对象', width: 260, align: 'center',
    			templet: function (d) {
    				if ( d.companyNo != null) {
    					return d.companyName+"("+d.companyNo+")";
    				} else {
    					return "-";
    				}
    			}
    		},
    		{field: 'yjbefTaxAmount', title: '应计返佣税前（元）', width: 230, align: 'center',
    			templet: function (d) {
    				if (d.yjbefTaxAmount == null) {
    					return "-";
    				} else {
    					return formatMoney(d.yjbefTaxAmount);
    				}
    			}
    		},
    		{field: 'yjaftTaxAmount', title: '应计返佣税后（元）', width: 200, align: 'center',
    			templet: function (d) {
    				if (d.yjaftTaxAmount == null) {
    					return "-";
    				} else {
    					return formatMoney(d.yjaftTaxAmount);
    				}
    			}
    		}
    		]
    	var cols = [];
    	cols.push(row);
    	return cols;
    }
    
    function setJyCol() {//结佣
    	var row = [];
	        row = [
	            {type: 'numbers', title: '序号', width: 60,style: 'font-size:10px', align: 'center'},
	            {field: 'companyName', title: '返佣对象', style: 'font-size:10px',width: 170, align: 'center',
	    			templet: function (d) {
	    				if ( d.companyNo != null) {
	    					return d.companyName(d.companyNo);
	    				} else {
	    					return "-";
	    				}
	    			}
	            },
	            {field: 'yjbefTaxAmount', title: '应计返佣税前(元)',style: 'font-size:10px', width: 130, align: 'center',
	                templet: function (d) {
	                    if (d.yjbefTaxAmount == null) {
	                        return "-";
	                    } else {
	                        return formatMoney(d.yjbefTaxAmount);
	                    }
	                }
	            },
	            {field: 'yjaftTaxAmount', title: '应计返佣税后(元)', style: 'font-size:10px',width: 130, align: 'center',  
	            	templet: function (d) {
	                    if (d.yjaftTaxAmount == null) {
	                        return "-";
	                    } else {
	                        return formatMoney(d.yjaftTaxAmount);
	                    }
	                }
	            },
	            {field: 'sjbefTaxAmount', title: '实际返佣税前(元)',style: 'font-size:10px', width: 130, align: 'center',
	            	templet: function (d) {
	                    if (d.sjbefTaxAmount == null) {
	                        return "-";
	                    } else {
	                        return formatMoney(d.sjbefTaxAmount);
	                    }
	                }
	            },
	            {field: 'sjaftTaxAmount', title: '实际返佣税后(元)',style: 'font-size:10px', width: 130, align: 'center',
	            	templet: function (d) {
	                    if (d.sjaftTaxAmount == null) {
	                        return "-";
	                    } else {
	                        return formatMoney(d.sjaftTaxAmount);
	                    }
	                }
	            },
	            {field: 'operDetailDate', title: '操作日期', style: 'font-size:10px',width: 120, align: 'center',
	            	templet: function (d) {
	            		if (d.operDetailDate == null) {
	            			return "-";
	            		} else {
	            			return formatDate(d.operDetailDate, "yyyy-MM-dd");
	            		}
	            	}
	            }
	        ]
        var cols = [];
        cols.push(row);
        return cols;
    }

//    if(operStatus == '22003' || operStatus == '22002' || operStatus == '27302'){//结佣或成销
//	    //成销确认书/佣金结算资料
//		setTimeout(function () {
//			var viewer = new Viewer(document.getElementById('upload_cxImg_list'),{
//				url:'data-original'
//			});
//		},100);
//		//返佣确认函
//		setTimeout(function () {
//			var viewer = new Viewer(document.getElementById('upload_fyImg_list'),{
//				url:'data-original'
//			});
//		},100);
//    }
});
