var formSelects;
var form;
var titleJson='';
layui.use(['element', 'laydate','table', 'form', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;
    form = layui.form;
    formSelects = layui.formSelects;

    var defaultYear = new Date().getFullYear();

    // 组织架构
    organizationLinkAge(defaultYear)

    fileInterval();


    form.render('select'); //刷新select选择框渲染

    function tableRender() {
	    table.render({
	        elem: '#contentTable'
	        , cols: setCols()
	        , id: 'contentReload'
	        , data: []
	        , loading: false
	        , height: 'full'
	        , even: false //开启隔行背景
	        , page: true
	        , limits: [10, 20, 30]
	        , limit: 10 //默认采用60
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
	    });
    }

    var active = {
        reload: function () {
            if (valid()) {
                var optionsData = getParam();
                var roughDimension = optionsData.roughDimension;//大定数据-维度
                if(roughDimension=='weekDimension'){
                	getTitle(optionsData);
                }
        		initTable();
                if(optionsData !=null){
                	reloadData(optionsData);
                }
            }
        },
        export: function () {
            var flag = checkExport('storeDetail_filepath');
            if(!flag){
                parent.layer.alert('您的经纪公司明细报表正在排队生成中，不能重复导出!', {
                    icon: 2,
                    title: '提示'
                });
                return false;
            }
            if (valid()) {
                var optionsData = getParam();
                if(optionsData !=null){
	                var cookieName = guid();
	                url = LOC_RES_BASE_PATH + '/rptStoreDetail/exportStoreDetailAjax?'
	                    + "organization=" + optionsData.organization
	                    + "&areaCityCodes=" + optionsData.areaCityCodes
	                    + "&cityIds=" + optionsData.cityIds
	                    + "&brandType=" + optionsData.brandType
	                    + "&isFyStore=" + optionsData.isFyStore
	                    + "&storeNo=" + optionsData.storeNo
	                    + "&companyNo=" + optionsData.companyNo
	
	                    + '&roughYear=' + optionsData.roughYear
	                    + "&roughDimension=" + optionsData.roughDimension
	                    
	                    + "&cookieName=" + cookieName;
	                $.ajax({
	                    type: "GET",
	                    url: url,
	                    async: true,
	                    dataType: "json",
	                    contentType: 'application/json',
	                    success: function (data) {
	                        var dataStr = data.message;
                            layer.msg(dataStr, {
                                icon: 16,
                                shade: 0.4,
                                time:false //取消自动关闭
                            });

                        },
	                    error: function (data) {
	                        parent.msgError(data.message);
	                    }
	                });
	
	                window.setTimeout(function () {
	                    $("#btnExport").attr("disabled", false);
	                }, 10000);
                }
            }
        },
        import: function () {

        }
    };
    
    function initTable(){
    	table.render({
            elem: '#mainTable'
            , cols: setCols()
            , id: 'contentReload'
            , data: []
            , height: 'full'
            , even: false //开启隔行背景
            , page: true
            , limits: [10,20,30]
            , limit: 10 //默认采用10
            , method: 'post'
            ,loading:false
            ,request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
            ,response: {
                statusName: 'returnCode' //数据状态的字段名称，默认：code
                ,statusCode: 200 //成功的状态码，默认：0
                ,msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                ,countName: 'totalCount' //数据总数的字段名称，默认：count
                ,dataName: 'returnData' //数据列表的字段名称，默认：data
            }
        });
    }

    //校验
    function valid() {
        var areaCityCodes = getXMSelectOutIds(formSelects.value('areaCity'));//归属城市
        if (isEmpty(areaCityCodes)) {
        	parent.layer.alert('请选择归属城市！', {
                icon: 2,
                title: '提示'
            });
            return false;
        }
        var roughYear = $('#roughYear').val();//大定数据-年份
        var roughDimension = $('#roughDimension').val();//大定数据-维度
        if (isEmpty(roughYear) || isEmpty(roughDimension)) {
        	parent.layer.alert('请选择大定数据！', {
        		icon: 2,
        		title: '提示'
        	});
        	return false;
        }
        return true;
    }

    //获取参数
    function getParam() {
        var optionsData = {};
        var organization = $("#orgYear").val();//架构年份
        var areaCityCodes = getXMSelectOutIds(formSelects.value('areaCity'));//归属城市
        var cityIds = getXMSelectOutIds(formSelects.value('city'));//所在城市
        var brandType = $("#brandType").val();//业务类型
        var isFyStore = $("#isFyStore").val();//是否房友门店 1，0
        var storeNo = trimStr($("#storeNo").val());//门店编号、名称
        var companyNo = trimStr($("#companyNo").val());//经纪公司编号、名称
        var roughYear = $("#roughYear").val();//大定数据-年份
        var roughDimension = $("#roughDimension").val();//大定数据-维度
        if (isIncludeBlank(storeNo)) {
        	parent.layer.alert('门店中间有空格，请重新输入！', {
        		icon: 2,
        		title: '提示'
        	});
        	return ;
        }
        if (isIncludeBlank(companyNo)) {
            parent.layer.alert('经纪公司中间有空格，请重新输入！', {
                icon: 2,
                title: '提示'
            });
            return ;
        }

        optionsData.organization = organization;
        optionsData.areaCityCodes = areaCityCodes;
        optionsData.cityIds = cityIds;
        optionsData.brandType = brandType;
        optionsData.isFyStore = isFyStore;
        optionsData.storeNo = storeNo;
        optionsData.companyNo = companyNo;
        optionsData.roughYear = roughYear;
        optionsData.roughDimension = roughDimension;

        return optionsData;
    }

    function reloadData(optionsData) {
    	tableRender();
        var index = layer.load(2);
        table.reload('contentReload', {
            url: BASE_PATH + '/rptStoreDetail/queryStoreDetailList',
            cols: setCols(),
            //height: window.innerHeight - $(".layui-table-view").offset().top - 30,
            where: optionsData,
            page:{
                curr: 1 //重新从第 1 页开始
            },
            done: function () {
                layer.close(index);
            }
        });
    }

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function setCols() {
        var row1 = [], row2 = [];
        var roughYear = $("#roughYear").val();
        var roughDimension = $("#roughDimension").val();
        row1.push(
            {field: 'rowNum', title: '序号', width: 60, align: 'center', colspan: 1,rowspan: 2},
            {field: 'regionName', title: '归属区域', width: 150, align: 'center', colspan: 1,rowspan: 2},
            {field: 'areaCityName', title: '归属城市', width: 150, align: 'center', colspan: 1,rowspan: 2},
            {field: 'acCityName', title: '所在城市', width: 150, align: 'center', colspan: 1,rowspan: 2},
            {field: 'storeNo', title: '门店编号', width: 150, align: 'center', colspan: 1,rowspan: 2},
            {field: 'storeName', title: '门店名称', width: 300, align: 'center', colspan: 1,rowspan: 2
            	, templet: function (d) {
                    return "<div style='text-align: left'>" + d.storeName + "</div>";
                }
            },
            {field: 'brandTypeStr', title: '业务类型', width: 150, align: 'center', colspan: 1,rowspan: 2},
            {field: 'storeAddress', title: '门店地址', width: 300, align: 'center', colspan: 1,rowspan: 2
            	, templet: function (d) {
                    return "<div style='text-align: left'>" + d.storeAddress + "</div>";
                }
            },
            {field: 'isFyStoreStr', title: '是否房友门店', width: 150, align: 'center', colspan: 1,rowspan: 2},
            {field: 'comCompanyNo', title: '所属公司编号', width: 150, align: 'center', colspan: 1,rowspan: 2},
            {field: 'companyName', title: '所属公司名称', width: 300, align: 'center', colspan: 1,rowspan: 2
            	, templet: function (d) {
            		if(d.companyName!=null && d.companyName!=""){
            			return "<div style='text-align: left'>" + d.companyName + "</div>";
            		}else{
            			return "";
            		}
                }
            },
            {field: 'pmlsGroupName', title: '联动维护中心', width: 150, align: 'center', colspan: 1,rowspan: 2},
            {field: 'pmlsMaintainName', title: '联动维护人', width: 150, align: 'center', colspan: 1,rowspan: 2},
            {field: 'pmlsMaintainCode', title: '维护人工号', width: 150, align: 'center', colspan: 1,rowspan: 2}
        );
        
        //年维度
        if(roughDimension=='yearDimension'){
        	row1.push(
        			{field: '', title: '大定金额',  width: 450,align: 'center', colspan: 3,rowspan: 1}
        	);
        	row2.push(
        			{field: 'totalAmount', title: '总累计', width: 150, align: 'center', colspan: 1,rowspan: 1
        				,templet: function (d) {
                            if (d.preTotal == null && d.thisTotal == null) {
                                return "";
                            } else {
                                return d.totalAmount;
                            }
                        }
        			},
        			{field: 'preTotal', title: roughYear + '年以前', width: 150, align: 'center', colspan: 1,rowspan: 1
        				,templet: function (d) {
                            if (d.preTotal == null || d.preTotal == "" || d.preTotal=='0.00') {
                                return "";
                            } else {
                                return d.totalAmount;
                            }
                        }
        			},
        			{field: 'thisTotal', title: roughYear + '年新增', width: 150, align: 'center', colspan: 1,rowspan: 1}
        	);
        //月维度
        }else if(roughDimension=='monthDimension'){
        	row1.push(
        			{field: '', title: '大定金额',  align: 'center', colspan: 15,rowspan: 1}
        	);
        	row2.push(
        			{field: 'totalAmount', title: '总累计', width: 150, align: 'center', colspan: 1,rowspan: 1
        				,templet: function (d) {
                            if (d.preTotal == null && d.thisTotal == null) {
                                return "";
                            } else {
                                return d.totalAmount;
                            }
                        }
        			},
        			{field: 'preTotal', title: roughYear + '年以前', width: 150, align: 'center', colspan: 1,rowspan: 1
        				,templet: function (d) {
                            if (d.preTotal == null || d.preTotal == "" || d.preTotal=='0.00') {
                                return "";
                            } else {
                                return d.totalAmount;
                            }
                        }
        			},
        			{field: 'thisTotal', title: roughYear + '年新增', width: 150, align: 'center', colspan: 1,rowspan: 1
        				,templet: function (d) {
                            if (d.thisTotal == null || d.thisTotal == "" || d.thisTotal=='0.00') {
                                return "";
                            } else {
                                return d.totalAmount;
                            }
                        }
        			},
        			
        			{field: 'monthAmount1', title: roughYear +'年1月', width: 150, align: 'center', colspan: 1,rowspan: 1},
        			{field: 'monthAmount2', title: roughYear +'年2月', width: 150, align: 'center', colspan: 1,rowspan: 1},
        			{field: 'monthAmount3', title: roughYear +'年3月', width: 150, align: 'center', colspan: 1,rowspan: 1},
        			
        			{field: 'monthAmount4', title: roughYear +'年4月', width: 150, align: 'center', colspan: 1,rowspan: 1},
        			{field: 'monthAmount5', title: roughYear +'年5月', width: 150, align: 'center', colspan: 1,rowspan: 1},
        			{field: 'monthAmount6', title: roughYear +'年6月', width: 150, align: 'center', colspan: 1,rowspan: 1},
        			
        			{field: 'monthAmount7', title: roughYear +'年7月', width: 150, align: 'center', colspan: 1,rowspan: 1},
        			{field: 'monthAmount8', title: roughYear +'年8月', width: 150, align: 'center', colspan: 1,rowspan: 1},
        			{field: 'monthAmount9', title: roughYear +'年9月', width: 150, align: 'center', colspan: 1,rowspan: 1},
        			
        			{field: 'monthAmount10', title: roughYear +'年10月', width: 150, align: 'center', colspan: 1,rowspan: 1},
        			{field: 'monthAmount11', title: roughYear +'年11月', width: 150, align: 'center', colspan: 1,rowspan: 1},
        			{field: 'monthAmount12', title: roughYear +'年12月', width: 150, align: 'center', colspan: 1,rowspan: 1}
        	);
        //周维度
        }else if(roughDimension=='weekDimension'){
        	if(titleJson.weekDate54 !=null){
            	row1.push(
            			{field: '', title: '大定金额', align: 'center', colspan: 57,rowspan: 1}
            	);
            }
        	else if(titleJson.weekDate53 !=null){
        		row1.push(
        				{field: '', title: '大定金额', align: 'center', colspan: 56,rowspan: 1}
        		);
        	}
        	else if(titleJson.weekDate52 !=null){
        		row1.push(
        				{field: '', title: '大定金额', align: 'center', colspan: 55,rowspan: 1}
        		);
        	}
        	
        	row2.push(
	        	{field: 'totalAmount', title: '总累计', width: 150, align: 'center', colspan: 1,rowspan: 1
	        		,templet: function (d) {
                        if (d.preTotal == null && d.thisTotal == null) {
                            return "";
                        } else {
                            return d.totalAmount;
                        }
                    }
	        	},
				{field: 'preTotal', title: roughYear + '年以前', width: 150, align: 'center', colspan: 1,rowspan: 1
	        		,templet: function (d) {
                        if (d.preTotal == null || d.preTotal == "" || d.preTotal=='0.00') {
                            return "";
                        } else {
                            return d.totalAmount;
                        }
                    }
				},
				{field: 'thisTotal', title: roughYear + '年新增', width: 150, align: 'center', colspan: 1,rowspan: 1
					,templet: function (d) {
                        if (d.thisTotal == null || d.thisTotal == "" || d.thisTotal=='0.00') {
                            return "";
                        } else {
                            return d.totalAmount;
                        }
                    }
				},
				
				{field: 'weekAmount1', title: titleJson.weekDate1, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount2', title: titleJson.weekDate2, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount3', title: titleJson.weekDate3, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount4', title: titleJson.weekDate4, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount5', title: titleJson.weekDate5, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount6', title: titleJson.weekDate6, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount7', title: titleJson.weekDate7, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount8', title: titleJson.weekDate8, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount9', title: titleJson.weekDate9, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount10', title: titleJson.weekDate10, width: 250, align: 'center', rowspan: 1, colspan: 1},

				{field: 'weekAmount11', title: titleJson.weekDate11, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount12', title: titleJson.weekDate12, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount13', title: titleJson.weekDate13, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount14', title: titleJson.weekDate14, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount15', title: titleJson.weekDate15, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount16', title: titleJson.weekDate16, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount17', title: titleJson.weekDate17, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount18', title: titleJson.weekDate18, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount19', title: titleJson.weekDate19, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount20', title: titleJson.weekDate20, width: 250, align: 'center', rowspan: 1, colspan: 1},

				{field: 'weekAmount21', title: titleJson.weekDate21, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount22', title: titleJson.weekDate22, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount23', title: titleJson.weekDate23, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount24', title: titleJson.weekDate24, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount25', title: titleJson.weekDate25, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount26', title: titleJson.weekDate26, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount27', title: titleJson.weekDate27, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount28', title: titleJson.weekDate28, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount29', title: titleJson.weekDate29, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount40', title: titleJson.weekDate30, width: 250, align: 'center', rowspan: 1, colspan: 1},

				{field: 'weekAmount31', title: titleJson.weekDate31, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount32', title: titleJson.weekDate32, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount33', title: titleJson.weekDate33, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount34', title: titleJson.weekDate34, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount35', title: titleJson.weekDate35, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount36', title: titleJson.weekDate36, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount37', title: titleJson.weekDate37, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount38', title: titleJson.weekDate38, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount39', title: titleJson.weekDate39, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount40', title: titleJson.weekDate40, width: 250, align: 'center', rowspan: 1, colspan: 1},

				{field: 'weekAmount41', title: titleJson.weekDate41, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount42', title: titleJson.weekDate42, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount43', title: titleJson.weekDate43, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount44', title: titleJson.weekDate44, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount45', title: titleJson.weekDate45, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount46', title: titleJson.weekDate46, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount47', title: titleJson.weekDate47, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount48', title: titleJson.weekDate48, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount49', title: titleJson.weekDate49, width: 250, align: 'center', rowspan: 1, colspan: 1},
				{field: 'weekAmount50', title: titleJson.weekDate50, width: 250, align: 'center', rowspan: 1, colspan: 1},

				{field: 'weekAmount51', title: titleJson.weekDate51, width: 250, align: 'center', rowspan: 1, colspan: 1}
        	);
        	if(titleJson.weekDate52 !=null){
            	row2.push(
            			{field: 'weekAmount52', title: titleJson.weekDate52, width: 250, align: 'center', rowspan: 1, colspan: 1}
            	);
            }
        	if(titleJson.weekDate53 !=null){
        		row2.push(
        				{field: 'weekAmount53', title: titleJson.weekDate53, width: 250, align: 'center', rowspan: 1, colspan: 1}
        		);
        	}
        	if(titleJson.weekDate54 !=null){
        		row2.push(
        				{field: 'weekAmount54', title: titleJson.weekDate54, width: 250, align: 'center', rowspan: 1, colspan: 1}
        		);
        	}
        }

        var cols = [];
        cols.push(row1);
        cols.push(row2);
        return cols;
    }

    function fileInterval() {
        var url = BASE_PATH + '/expendReport/fileIntervalByName/' + "storeDetail_filepath";

        $.ajax({
            type: "GET",
            url: url,
            async: true,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                var fileId = data.fileId;
                console.log("fileId:" + fileId);
                if (fileId != undefined && fileId != null && fileId != '' && fileId != 0) {
                    downLoadExcel2(fileId);
                }
            },
            error: function () {
            }
        });

        window.setTimeout(fileInterval, 30000);

    };

    function downLoadExcel2(fileId) {
        console.log("fileId=" + fileId);
        var url = BASE_PATH + '/expendReport/downLoadExcel2?fileId=' + fileId;
        window.parent.location.href = url;
        var url = BASE_PATH + '/expendReport/queryReportSize/' + "storeDetail_filepath";
        $.ajax({
            type: "GET",
            url: url,
            async: true,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                $("#reportSize").text(data.userReportSize);
                layer.closeAll();
                if ("0" == data.userReportSize) {
                    $("#exportMsg").hide();
                } else {
                    $("#exportMsg").show();
                }
            },
            error: function () {
            }
        });
    };

});

/**
 * 获取表头
 * @param optionsData
 * @returns
 */
function getTitle(optionsData){
	$.ajax({
        url: ctx + '/rptCompanyDetail/getTitle',
        type: 'post',
        data:optionsData,
        dataType: 'json',
        async:false,
        success: function (data) {
            parent.layer.closeAll();
            console.log(data);
            if (data.returnCode == 200){
            	titleJson=data.returnData;
            }else{
            	parent.layer.alert('查询失败', {icon: 2, title:'提示'});
            }
        }
    });
}

