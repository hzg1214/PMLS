var formSelects;
var form;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,

        table = layui.table,
        layer = layui.layer,

        $ = layui.$;
    form = layui.form;
    formSelects = layui.formSelects;

    laydate.render({
        elem: '#startDate',
        type: 'date',
        trigger: 'click',
        format: 'yyyy-MM',
        done: function (value, date, endDate) {
            var startDate = new Date(value).getTime();
            if ($('#endDate').val() != '') {
                var endTime = new Date($('#endDate').val()).getTime();
                if (endTime < startDate) {
                    layer.msg('开始时间不能大于结束时间');
                    $('#startDate').val('');
                }
            }
        }
    });

    laydate.render({
        elem: '#endDate',
        type: 'date',
        trigger: 'click',
        format: 'yyyy-MM',
        done: function (value, date, endDate) {
            if ($('#startDate').val() != "") {
                var startDate = new Date($('#startDate').val()).getTime();
                var endTime = new Date(value).getTime();
                if (endTime < startDate) {
                    layer.msg('结束时间不能小于开始时间');
                    $('#endDate').val('');
                }
            }
        }
    });

    laydate.render({
        elem: '#startDateProject',
        type: 'date',
        trigger: 'click',
        format: 'yyyy-MM-dd',
        done: function (value, date, endDate) {
            var startDate = new Date(value).getTime();
            if ($('#endDateProject').val() != '') {
                var endTime = new Date($('#endDateProject').val()).getTime();
                if (endTime < startDate) {
                    layer.msg('开始时间不能大于结束时间');
                    $('#startDateProject').val('');
                }
            }
        }
    });

    laydate.render({
        elem: '#endDateProject',
        type: 'date',
        trigger: 'click',
        format: 'yyyy-MM-dd',
        done: function (value, date, endDate) {
            if ($('#startDateProject').val() != "") {
                var startDate = new Date($('#startDateProject').val()).getTime();
                var endTime = new Date(value).getTime();
                if (endTime < startDate) {
                    layer.msg('结束时间不能小于开始时间');
                    $('#endDateProject').val('');
                }
            }
        }
    });
    var defaultYear = new Date().getFullYear();

    // 组织架构
    organizationLinkAge()

    //初始化-项目状态
    initProjectStatus();
    
    fileInterval();

    //查询维度 1、项目  归属中心置灰 ，隐藏起始月份和截止月份
    //      2、考核  放开归属中心，隐藏起始日期和截止日期
    form.on('select(searchType)', function (data) {
        initSearchType(data.value);
    });

    initSearchType(1)

    function initSearchType(searchType) {
        //  查询维度 = 项目
        if (1 == searchType) {
            // 成本中心灰掉，禁止选择 成本中心
            //formSelects.disabled('centerGroup');
            $(".noProject").hide();
            $(".project").show();
        }
        // 查询维度 = 考核
        else if (2 == searchType || 3 == searchType) {
            //formSelects.undisabled("centerGroup")
            $(".project").hide();
            $(".noProject").show();
        }
    }

    //初始换楼盘城市和区域、地址
    cityLinkageIsService(null, function () {
        form.render();
        //城市选择事件
        form.on('select(realityCityNo)', function (data) {
            console.log(data);
            $('#districtNo').val('');
            $('#address').val('');
            if (data.value != null && data.value != '') {
                districtLinkageIsService(data.value, null, function () {
                    form.render();
                });
            }
        });
    });


    //初始化-项目状态
    function initProjectStatus() {
        var result = [];
        var allInitProjectStatus = [];
        result.push({"name": '跟单', "value": '20301'});
        result.push({"name": '签约', "value": '20302'});
        result.push({"name": '结案', "value": '20303'});
        result.push({"name": '取消跟单', "value": '20304'});
        allInitProjectStatus.push('20301');
        allInitProjectStatus.push('20302');
        allInitProjectStatus.push('20303');
        allInitProjectStatus.push('20304');
        formSelects.data('projectStatus', 'local', {arr: result});
        formSelects.btns('projectStatus', [
            {
                icon: "xm-iconfont icon-quanxuan",
                name: '全选',
                click: function (id) {
                    //点击后的操作
                    layui.formSelects.value(id, allInitProjectStatus);
                }
            }, {
                icon: "xm-iconfont icon-qingkong",
                name: '清空',
                click: function (id) {
                    formSelects.value(id, [])
                }
            }
        ]);
    }

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
                if(optionsData !=null){
                	reloadData(optionsData);
                }
            }
        },export: function () {
            if (valid()) {
	                var optionsData = getParam();
	                if(optionsData !=null){
		                var cookieName = guid();
		
		                url = LOC_RES_BASE_PATH + '/pmlsLinkProjectDetail/exportLinkProjectDetailAjax?'
		                    + "organization=" + optionsData.organization
		                    + "&regionCodes=" + optionsData.regionCodes
		                    + "&areaCityCodes=" + optionsData.areaCityCodes
		                    + "&cityIds=" + optionsData.cityIds
		                    //+ "&centerIds=" + optionsData.centerGroupIds
		                    + '&projectStatus=' + optionsData.projectStatus
		                    + "&estateNm=" + optionsData.estateNm
		                    + "&projectNo=" + optionsData.projectNo
		                    + "&realityCityNo=" + optionsData.realityCityNo
		                    + "&districtNo=" + optionsData.districtNo
		                    + "&startDate=" + optionsData.startDate
		                    + "&endDate=" + optionsData.endDate
		
		                    + "&searchType=" + optionsData.searchType
		                    + "&startDateProject=" + optionsData.startDateProject
		                    + "&endDateProject=" + optionsData.endDateProject
		                    + "&address=" + optionsData.address
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
	                    error: function () {
	                    }
	                });
	
	                window.setTimeout(function () {
	                    $("#btnExport").attr("disabled", false);
	                }, 10000);
	            }
            }
        }
    };


    //获取参数
    function getParam() {
        var optionsData = {};
        var estateNm = trimStr($("#estateNm").val());//楼盘名称
        var projectNo = trimStr($("#projectNo").val());//项目编号
        var address = trimStr($("#address").val());//楼盘地址
        if(isIncludeBlank(estateNm)){
        	parent.layer.alert('楼盘名称中间有空格，请重新输入！',{
        		icon: 2,
        		title: '提示'
        	});
        	return ;
        }
        if(isIncludeBlank(projectNo)){
        	parent.layer.alert('项目编号中间有空格，请重新输入！',{
        		icon: 2,
        		title: '提示'
        	});
        	return ;
        }
        if(isIncludeBlank(address)){
        	parent.layer.alert('楼盘地址中间有空格，请重新输入！',{
        		icon: 2,
        		title: '提示'
        	});
        	return ;
        }
        var organization = $("#orgYear").val();
        var searchType = $("#searchType").val();//1、项目（业绩） 2、考核 3、项目（成本）
        var regionCodes = getXMSelectIds(formSelects.value('region'));
        var areaCityCodes = getXMSelectIds(formSelects.value('areaCity'));
        var cityIds = getXMSelectIds(formSelects.value('city'));
        //var centerGroupIds = getXMSelectIds(formSelects.value('centerGroup'));
        var projectStatus = getXMSelectIds(formSelects.value('projectStatus'));//项目状态
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        var startDateProject = $("#startDateProject").val();
        var endDateProject = $("#endDateProject").val();
        var realityCityNo = $("#realityCityNo").val();//楼盘城市
        var districtNo = $("#districtNo").val();//楼盘区域
        

        optionsData.organization = organization;
        optionsData.searchType = searchType;
        optionsData.regionCodes = regionCodes;
        optionsData.areaCityCodes = areaCityCodes;
        optionsData.cityIds = cityIds;
        //optionsData.centerGroupIds = centerGroupIds;
        optionsData.projectNo = projectNo;
        optionsData.projectStatus = projectStatus;
        optionsData.startDate = startDate;
        optionsData.endDate = endDate;
        optionsData.startDateProject = startDateProject;
        optionsData.endDateProject = endDateProject;
        optionsData.estateNm = estateNm;
        optionsData.realityCityNo = realityCityNo;
        optionsData.districtNo = districtNo;
        optionsData.address = address;

        return optionsData;
    }

    function getXMSelectIds(vals) {
        var ids = [];
        if (vals.length > 0) {
            for (var i = 0; i < vals.length; i++) {
                ids.push(vals[i].value);
            }
        }
        return ids.join(",");
    }

    function reloadData(optionsData) {
    	tableRender();
        var index = layer.load(2);
        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsLinkProjectDetail/querylinkProjectDetailList',
            cols: setCols(),
            //height: window.innerHeight - $(".layui-table-view").offset().top - 10,
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
        var row1 = [], row2 = [], row3 = [];
        var searchType = $("#searchType").val();//1、项目（业绩） 2、考核 3、项目（成本）
        row1.push(
                {type: 'numbers', title: '序号',width: '60px!important;', align: 'center', rowspan: 3},
                {field: 'regionName', title: '项目归属区域', width: 150, align: 'center', rowspan: 3, style:'text-align: center'},
                {field: 'areaCityName', title: '项目归属城市', width: 150, align: 'center', rowspan: 3, style:'text-align: center'},
                {field: 'cityGroupName', title: '项目所在城市', width: 150, align: 'center', rowspan: 3, style:'text-align: center'},
                {field: 'centerGroupName', title: '项目归属部门', width: 150, align: 'center', rowspan: 3, style:'text-align: center'},
                {field: 'performCityName', title: '报备归属城市', width: 150, align: 'center', rowspan: 3, style:'text-align: center'}
            );

        if (2 == searchType) {//考核
            // row1.push(
            //     {field: 'performCenterName', title: '报备归属中心', width: 150, align: 'center', rowspan: 3, style:'text-align: center'}
            // );
            row1.push(
                {field: 'hblName', title: '考核主体', width: 150, align: 'center', rowspan: 3, style:'text-align: center'}
            );
        }
        row1.push(
                {field: 'estateNm', title: '楼盘名', width: 150, align: 'center', rowspan: 3, colspan: 1, style:'text-align: left'},
                {field: 'cityName', title: '城市', width: 150, align: 'center', rowspan: 3, colspan: 1, style:'text-align: center'},
                {field: 'districtName', title: '区域', width: 150, align: 'center', rowspan: 3, colspan: 1, style:'text-align: center'},
                {field: 'address', title: '地址', width: 150, align: 'center', rowspan: 3, colspan: 1, style:'text-align: left'},
                {field: 'projectNo', title: '项目编号', width: 150, align: 'center', rowspan: 3, colspan: 1, style:'text-align: center'},
                {field: 'partnerName', title: '合作类型', width: 150, align: 'center', rowspan: 3, colspan: 1, style:'text-align: center'},
                {field: 'partnerNm', title: '合作方名称', width: 150, align: 'center', rowspan: 3, colspan: 1, style:'text-align: left'},
                {field: 'bigCustomerFlagVakue', title: '是否大客户', width: 150, align: 'center', rowspan: 3, colspan: 1, style:'text-align: center'},
                {field: 'bigCustomerName', title: '大客户简称', width: 150, align: 'center', rowspan: 3, colspan: 1, style:'text-align: left'},
                {field: 'particularFlagVakue', title: '是否独家', width: 150, align: 'center', rowspan: 3, colspan: 1, style:'text-align: center'},
                {field: 'directSignFlagVakue', title: '是否直签', width: 150, align: 'center', rowspan: 3, colspan: 1, style:'text-align: center'},
                //{field: 'yjdyAmount', title: '垫佣金额', width: 150, align: 'center', rowspan: 3, colspan: 1, style:'text-align: right'},
                {field: 'estateCycle', title: '合作周期', width: 150, align: 'center', rowspan: 3, colspan: 1},
                {field: 'projectStatusName', title: '项目状态', width: 150, align: 'center', rowspan: 3, colspan: 1},
                {field: 'mgtKbnName', title: '物业类型', width: 150, align: 'center', rowspan: 3, colspan: 1},
                {field: 'cooperationModeName', title: '合作模式', width: 150, align: 'center', rowspan: 3, colspan: 1}
            );
        if (2 == searchType || 3 == searchType) {
            row1.push(
                {field: '', title: '当期实际(不含结转)', width: 150, align: 'center', colspan: 27, rowspan: 1},
                {field: '', title: '总累计', width: 150, align: 'center', colspan: 26, rowspan: 1}
            );
        }else{
            row1.push(
                {field: '', title: '当期实际(不含结转)', width: 150, align: 'center', colspan: 12, rowspan: 1},
                {field: '', title: '总累计', width: 150, align: 'center', colspan: 12, rowspan: 1}
            );
        }
        row2.push(
            {field: '', title: '大定', width: 150, align: 'center', colspan: 3, rowspan: 1},
            {field: '', title: '成销', width: 150, align: 'center', colspan: 3, rowspan: 1},
            {field: '', title: '应计收入（税前）', width: 150, align: 'center', colspan: 3, rowspan: 1}
        );
        if (2 == searchType || 3 == searchType) {
            row2.push(
                {field: 'curESHProfit', title: '税后净收', width: 150, align: 'center', colspan: 1, rowspan: 2, style:'text-align: right'},
                {field: '', title: '财务费用', width: 150, align: 'center', colspan: 5, rowspan: 1},
                {field: 'curLRProfit', title: '利润', width: 150, align: 'center', colspan: 1, rowspan: 2 , style:'text-align: right'}
            );
        }
        row2.push({field: '', title: '实际回款', width: 150, align: 'center', colspan: 3, rowspan: 1});

        if (2 == searchType || 3 == searchType) {
            row2.push(
                {field: '', title: '比例', width: 150, align: 'center', colspan: 8, rowspan: 1}
            )
        }
        row2.push(
            {field: '', title: '大定', width: 150, align: 'center', colspan: 3, rowspan: 1},
            {field: '', title: '成销', width: 150, align: 'center', colspan: 3, rowspan: 1},
            {field: '', title: '应计收入（税前）', width: 150, align: 'center', colspan: 3, rowspan: 1}
        )
        if (2 == searchType || 3 == searchType) {
            row2.push(
                {field: 'accESHProfit', title: '税后净收', width: 150, align: 'center', colspan: 1, rowspan: 2 , style:'text-align: right'},
                {field: '', title: '财务费用', width: 150, align: 'center', colspan: 5, rowspan: 1},
                {field: 'accLRProfit', title: '利润', width: 150, align: 'center', colspan: 1, rowspan: 2 , style:'text-align: right'}
            )
        }
        row2.push({field: '', title: '实际回款', width: 150, align: 'center', colspan: 3, rowspan: 1});
        if (2 == searchType || 3 == searchType) {
            row2.push({field: '', title: '比例', width: 150, align: 'center', colspan: 7, rowspan: 1});
        }
        row3.push(
            {field: 'curRoughNum', title: '套数', width: 150, align: 'center'},
            {field: 'curRoughArea', title: '面积', width: 150, align: 'center'},
            {field: 'curRoughAmount', title: '金额', width: 150, align: 'center', style:'text-align: right'},
            {field: 'curDealNum', title: '套数', width: 150, align: 'center'},
            {field: 'curDealArea', title: '面积', width: 150, align: 'center'},
            {field: 'curDealAmount', title: '金额', width: 150, align: 'center', style:'text-align: right'},
            {field: 'curEPTIncome', title: '收入', width: 150, align: 'center', style:'text-align: right'},
            {field: 'curEPTCommission', title: '返佣', width: 150, align: 'center', style:'text-align: right'},
            {field: 'curEPTProfit', title: '净收', width: 150, align: 'center', style:'text-align: right'}
        );
        if (2 == searchType || 3 == searchType) {
            row3.push(
                {field: 'curFCAC01012', title: '附加税', width: 150, align: 'center', style:'text-align: right'},
                {field: 'curNYAmount', title: '内佣', width: 150, align: 'center', style:'text-align: right'},
                {field: 'curFCAC01028', title: '运维', width: 150, align: 'center', style:'text-align: right'},
                {field: 'curCost', title: '线下成本', width: 150, align: 'center', style:'text-align: right'},
                {field: 'curFCAC01127', title: '资金成本', width: 150, align: 'center', style:'text-align: right'}
            );
        }
        row3.push(
            {field: 'curAPTIncome', title: '收入', width: 150, align: 'center', style:'text-align: right'},
            {field: 'curAPTCommission', title: '返佣', width: 150, align: 'center', style:'text-align: right'},
            {field: 'curAPTProfit', title: '净收', width: 150, align: 'center', style:'text-align: right'}
        );
        if (2 == searchType || 3 == searchType) {
            row3.push(
                {field: 'qcjsRate', title: '期初净收比例', width: 150, align: 'center'},
                {field: 'curMSFRate', title: '毛收费率', width: 150, align: 'center'},
                {field: 'curJSBRate', title: '净收比例', width: 150, align: 'center'},
                {field: 'curNYZRate', title: '内佣占比', width: 150, align: 'center'},
                {field: 'curYWZRate', title: '运维占比', width: 150, align: 'center'},
                {field: 'curXXCBZRate', title: '线下成本占比', width: 150, align: 'center'},
                {field: 'curZJCBZRate', title: '资金成本占比', width: 150, align: 'center'},
                {field: 'curMLRate', title: '毛利率', width: 150, align: 'center'}
            );
        }
        row3.push(
            {field: 'accRoughNum', title: '套数', width: 150, align: 'center'},
            {field: 'accRoughArea', title: '面积', width: 150, align: 'center'},
            {field: 'accRoughAmount', title: '金额', width: 150, align: 'center', style:'text-align: right'},
            {field: 'accDealNum', title: '套数', width: 150, align: 'center'},
            {field: 'accDealArea', title: '面积', width: 150, align: 'center'},
            {field: 'accDealAmount', title: '金额', width: 150, align: 'center', style:'text-align: right'},
            {field: 'accEPTIncome', title: '收入', width: 150, align: 'center', style:'text-align: right'},
            {field: 'accEPTCommission', title: '返佣', width: 150, align: 'center', style:'text-align: right'},
            {field: 'accEPTProfit', title: '净收', width: 150, align: 'center', style:'text-align: right'}
        );
        if (2 == searchType || 3 == searchType) {
            row3.push(
                {field: 'accFCAC01012', title: '附加税', width: 150, align: 'center', style:'text-align: right'},
                {field: 'accNYAmount', title: '内佣', width: 150, align: 'center', style:'text-align: right'},
                {field: 'accFCAC01028', title: '运维', width: 150, align: 'center', style:'text-align: right'},
                {field: 'accCost', title: '线下成本', width: 150, align: 'center', style:'text-align: right'},
                {field: 'accFCAC01127', title: '资金成本', width: 150, align: 'center', style:'text-align: right'}
            );
        }
        row3.push(
            {field: 'accAPTIncome', title: '收入', width: 150, align: 'center', style:'text-align: right'},
            {field: 'accAPTCommission', title: '返佣', width: 150, align: 'center', style:'text-align: right'},
            {field: 'accAPTProfit', title: '净收', width: 150, align: 'center', style:'text-align: right'}
        );
        if (2 == searchType || 3 == searchType) {
            row3.push(
                {field: 'accMSFRate', title: '毛收费率', width: 150, align: 'center'},
                {field: 'accJSBRate', title: '净收比例', width: 150, align: 'center'},
                {field: 'accNYZRate', title: '内佣占比', width: 150, align: 'center'},
                {field: 'accYWZRate', title: '运维占比', width: 150, align: 'center'},
                {field: 'accXXCBZRate', title: '线下成本占比', width: 150, align: 'center'},
                {field: 'accZJCBZRate', title: '资金成本占比', width: 150, align: 'center'},
                {field: 'accMLRate', title: '毛利率', width: 150, align: 'center'}
            );
        }
        var cols = [];
        cols.push(row1);
        cols.push(row2);
        cols.push(row3);
        return cols;
    }

    //校验
    function valid() {

    	var organization = $("#orgYear").val();
    	var areaCityCodes = getXMSelectOutIds(formSelects.value('areaCity'));//归属城市
    	var region = getXMSelectOutIds(formSelects.value('region'));//归属区域
    	var city = getXMSelectOutIds(formSelects.value('city'));//所在城市
        return checkComm(organization,areaCityCodes,region,city);
    }

    function fileInterval() {
        var url = BASE_PATH + '/expendReport/fileIntervalByName/' + "linkProjectDetail_filepath";

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
        var url = BASE_PATH + '/expendReport/queryReportSize/' + "linkProjectDetail_filepath";
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

