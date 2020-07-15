var $;
var fyJsonDto={};
var reportInfo={};
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        formSelects = layui.formSelects,
        layer = layui.layer;
        $ = layui.$;
        
    
    var progress = $("#progress").val();
    var brokeragePage = $("#brokeragePage").val();
    
    if(window.fyData!=''){
    	//fyJsonDto=eval('(' + window.fyData + ')');
        fyJsonDto=eval('(' + window.fyData.replace(/[\r\n]/g,"\\n") + ')');
    }
    if(progress == '13505' && brokeragePage == '22003'){//结佣
    	jyFyInit(fyJsonDto);
    }else if(progress == '13505'){//成销
    	cxFyInit(fyJsonDto);
    }
    //初始化附件预览
    var uploadDkImgList=[],//带看
	    uploadDDImgList=[],//大定
	    uploadJFImgList=[],//甲方项目负责人名片
	    uploadYJImgList=[];//成销确认书/佣金结算资料
    if(window.reportInfoDto!=''){
    	//reportInfo=eval('(' + window.reportInfoDto + ')');
        reportInfo=eval('(' + window.reportInfoDto.replace(/[\r\n]/g,"\\n") + ')');
    }
    if(reportInfo!=null && reportInfo.fileList!=null && reportInfo.fileList.length>0){
        for(var i=0;i<reportInfo.fileList.length;i++){
            if(reportInfo.fileList[i].fileTypeId=='1022'){
            	uploadDkImgList.push(reportInfo.fileList[i]);
            }else if(reportInfo.fileList[i].fileTypeId=='1023'){
            	uploadDDImgList.push(reportInfo.fileList[i]);
            }else if(reportInfo.fileList[i].fileTypeId=='1024'){
            	uploadJFImgList.push(reportInfo.fileList[i]);
            }else if(reportInfo.fileList[i].fileTypeId=='1025'){
            	uploadYJImgList.push(reportInfo.fileList[i]);
            }
        }
    }
    //初始化加载图片
    if('13502' == progress || '13504'==progress){//带看或大定
    	//带看单
    	loadImageList('upload_dk_list',uploadDkImgList,false);
    }
    
    if('13504'==progress){//大定
    	
    	//大定单
    	loadImageList('upload_dd_list',uploadDDImgList,false);
    	
    	//甲方项目负责人名片
    	loadImageList('upload_project_list',uploadJFImgList,false);
    }
    
    if('13505'==progress){
    	//成销确认书/佣金结算资料
    	loadImageList('upload_cxImg_list',uploadYJImgList,false);
    }
    
    function cxFyInit(fyJsonDto){
    	table.render({
            elem: '#reportCxFyTable'
            , cols: setCxCol()
            , data:fyJsonDto
            , id: 'cxFyReload'
            , width : 825
            , even: false //开启隔行背景
            , page: false
            , limits: [10, 20, 30]
            , limit: 1000 //默认采用60
            , method: 'post'
            , request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
        });
    }
    
    function jyFyInit(fyJsonDto){
    	table.render({
    		elem: '#reportJyFyTable'
    			, cols: setJyCol()
    			, data:fyJsonDto
    			, id: 'jyFyReload'
//    				,height: 40
    				,totalRow: true
    				, even: true //开启隔行背景
    				, page: false
    				, limits: [10, 20, 30]
    	, limit: 1000 //默认采用60
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
    		{field: 'companyName', title: '返佣对象', width: 260, align: 'center'},
    		{field: 'yjfyBefTaxAmount', title: '应计返佣税前（元）', width: 230, align: 'center',
    			templet: function (d) {
    				if (d.yjfyBefTaxAmount == null) {
    					return "-";
    				} else {
    					return formatMoney(d.yjfyBefTaxAmount);
    				}
    			}
    		},
    		{field: 'yjfyAftTaxAmount', title: '应计返佣税后（元）', width: 200, align: 'center',
    			templet: function (d) {
    				if (d.yjfyAftTaxAmount == null) {
    					return "-";
    				} else {
    					return formatMoney(d.yjfyAftTaxAmount);
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
	            {type: 'numbers', title: '序号', width: 45,style: 'font-size:10px', align: 'center',fixed: 'left', unresize: true, totalRowText: ''},
	            {field: 'companyName', title: '返佣对象', style: 'font-size:10px',width: 170, align: 'center', unresize: true, totalRowText: ''},
	            {field: 'fyDate', title: '结佣日期',style: 'font-size:10px', width: 100, align: 'center', unresize: true, totalRowText: '合计',
	                templet: function (d) {
	                    if (d.fyDate == null) {
	                        return "-";x
	                    } else {
	                        return formatDate(d.fyDate, "yyyy-MM-dd");
	                    }
	                }
	            },
	            {field: 'sjfyBefTaxAmount', title: '实际返佣税前金额（元）', style: 'font-size:10px',width: 130, align: 'center',  totalRow: true,
	            	templet: function (d) {
	                    if (d.sjfyBefTaxAmount == null) {
	                        return "-";
	                    } else {
	                        return formatMoney(d.sjfyBefTaxAmount);
	                    }
	                }
	            },
	            {field: 'sjfyAftTaxAmount', title: '实际返佣税后金额（元）',style: 'font-size:10px', width: 130, align: 'center',  totalRow: true,
	            	templet: function (d) {
	                    if (d.sjfyAftTaxAmount == null) {
	                        return "-";
	                    } else {
	                        return formatMoney(d.sjfyAftTaxAmount);
	                    }
	                }
	            },
	            {field: 'operateName', title: '操作人',style: 'font-size:10px', width: 90, align: 'center'},
	            {field: 'operateDate', title: '操作时间', style: 'font-size:10px',width: 150, align: 'center',
	            	templet: function (d) {
	            		if (d.operateDate == null) {
	            			return "-";
	            		} else {
	            			return formatDate(d.operateDate, "yyyy-MM-dd hh:mm:ss");
	            		}
	            	}
	            }
	        ]
        var cols = [];
        cols.push(row);
        return cols;
    }
//    if('13502' == progress || '13504'==progress){//带看或大定
//    	//带看单
//    	setTimeout(function () {
//    		var viewer = new Viewer(document.getElementById('upload_dk_list'),{
//    			url:'data-original'
//    		});
//    	},100);
//    }
//    
//    if('13504'==progress){//大定
//    	
//    	//大定单
//    	setTimeout(function () {
//    		var viewer = new Viewer(document.getElementById('upload_dd_list'),{
//    			url:'data-original'
//    		});
//    	},100);
//    	
//    	//甲方项目负责人名片
//    	setTimeout(function () {
//    		var viewer = new Viewer(document.getElementById('upload_project_list'),{
//    			url:'data-original'
//    		});
//    	},100);
//    }
//    
//    if('13505'==progress){
//    	//成销确认书/佣金结算资料
//    	setTimeout(function () {
//    		var viewer = new Viewer(document.getElementById('upload_cxImg_list'),{
//    			url:'data-original'
//    		});
//    	},100);
//    }

});
