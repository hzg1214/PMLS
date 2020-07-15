var laydate;
var form;
var layer;
var table;
var titleJson='';
var active;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        formSelects = layui.formSelects,
        $ = layui.$;
    table = layui.table;
    layer = layui.layer;
    laydate = layui.laydate;
    form = layui.form;
    
    init();
    
    function init() {
    	//初始化-版本
    	var performYear = $('#performYear').val();
    	var performMonth = $('#performMonth').val();
    	//初始化-版本周
    	var performWeekNo = $('#performWeekNo').val();
    	getWeekVersion(performYear,performMonth,performWeekNo);

    	if(performMonth<10){
    		performMonth="0"+performMonth;
    	}
    	var yearMonthVersion = performYear+performMonth;
    	//初始化-版本年月
    	initDateToYearMonthVersion(yearMonthVersion);
    	//初始化-年月
    	initDateToYearMonth();
    	
    	form.render('select');
    }
    
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

//    reloadData({});//初始化加载表格

    active = {
        reload:function(){
        	if (valid()) {
        		var optionsData=getParam();
        		console.log(optionsData);
        		getTitle(optionsData);
        		initTable();
        		reloadData(optionsData);
        	}
        },
        reset:function () {
            $("#projectDepartmentId").val('');
            $("#projectNo").val('');
            $("#trackType").val('');
            $("#yearMonth").val('');
            $("#yearMonthVersion").val('');
            $("#weekVersion").val('');
            form.render('select');
            table.render({
                elem: '#mainTable'
                , cols: []
                , id: 'contentReload'
            });
//            active.reload();
        },
        export: function () {
        	if (valid()) {
        		var optionsData=getParam();
        		var index = parent.layer.load(2,{shade: 0.1});
                var url = BASE_PATH + '/remittanceTrack/exportCheck?projectDepartmentId=' + 
                optionsData.projectDepartmentId + "&projectNo=" + optionsData.projectNo
                + "&trackType=" + optionsData.trackType + "&year=" + optionsData.year
                + "&month=" + optionsData.month + "&dataMonth=" + optionsData.dataMonth
        		+ "&yearMonthVersion=" + optionsData.yearMonthVersion + "&weekVersion=" + optionsData.weekVersion
        		+ "&sortNo=" + optionsData.sortNo+ "&cityNo=" + optionsData.cityNo;
                $.ajax({
                    type: "GET",
                    url: url,
                    dataType:"json",
                    contentType: 'application/json',
                    success: function (data) {
                        if (data.returnCode == 200 || data.returnCode == '200') {
                            window.location.href = BASE_PATH + '/remittanceTrack/export?projectDepartmentId=' + 
                            optionsData.projectDepartmentId + "&projectNo=" + optionsData.projectNo
                            + "&trackType=" + optionsData.trackType + "&year=" + optionsData.year
                            + "&month=" + optionsData.month + "&dataMonth=" + optionsData.dataMonth
                    		+ "&yearMonthVersion=" + optionsData.yearMonthVersion + "&weekVersion=" + optionsData.weekVersion
                    		+ "&sortNo=" + optionsData.sortNo + "&weekNo=" + optionsData.weekNo+ "&cityNo=" + optionsData.cityNo;
                            parent.layer.closeAll();
                        } else {
                            parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'},function () {
                                parent.layer.closeAll();
                            });
                            return false;
                        }
                    },
                    error: function () {
                        parent.layer.alert("查询数据失败", {icon: 2, title: '提示'},function () {
                            parent.layer.closeAll();
                        });
                        return false;
                    }
                });
        	}
        },
        import: function () {
                layer.open({
                    type: 2,
                    title: 'EXCEL导入',
                    area: ['500px', '400px'],
                    content: BASE_PATH + '/remittanceTrack/toView'
                });
        }
    };

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

  //获取参数
    function getParam() {
    	var optionsData = {};
    	var projectDepartmentId = $("#projectDepartmentId").val();//联动项目部
        var projectNo = encodeURIComponent(trimStr($("#projectNo").val()));//项目
        var trackType = $("#trackType").val();//回款类型
        var yearMonth = $("#yearMonth").val(); //年月
        var yearMonthVersion = $("#yearMonthVersion").val(); //关账版本
        var weekVersion = $("#weekVersion").val(); //关账版本
        var year = yearMonth.split("-")[0];
        var month = yearMonth.split("-")[1];
        if(month<10){
    		month=month.substring(1);
    	}
        var sortNo=$('#weekVersion').find("option:selected").attr("sortNo");
        $('#sortNo').val(sortNo);
        var weekNo = $("#weekNo").val(); //导入用到
        optionsData.projectDepartmentId = projectDepartmentId;
        optionsData.projectNo = projectNo;
        optionsData.trackType = trackType;
        optionsData.year = year;
        optionsData.month = month;
        optionsData.dataMonth = month;
        optionsData.yearMonthVersion = yearMonthVersion;
        optionsData.weekVersion = weekVersion;
        optionsData.sortNo = sortNo;
        optionsData.weekNo = weekNo;
        optionsData.cityNo = $('#cityNo').val();
        return optionsData;
    }

    //重置
    function resetValue(id) {
        formSelects.data(id, 'local', {arr: []});
    }
    
});

function valid() {
	var projectNo = trimStr($("#projectNo").val());//项目
    var trackType = $("#trackType").val();//回款类型
    if(isIncludeBlank(projectNo)){
    	parent.layer.alert('项目中间有空格，请重新输入！', {
            icon: 2,
            title: '提示'
        });
    	return false;
    }
    if(!checkBlank(trackType)){
    	parent.layer.alert('请选择模板类型！', {
            icon: 2,
            title: '提示'
        });
    	return false;
    }
    var yearMonth = $("#yearMonth").val(); //年月
    if(!checkBlank(yearMonth)){
    	parent.layer.alert('请选择年月！', {
            icon: 2,
            title: '提示'
        });
    	return false;
    }
    
    var yearMonthVersion = $("#yearMonthVersion").val(); //关账版本
    var weekVersion = $("#weekVersion").val(); //关账版本
    if(!checkBlank(yearMonthVersion) || !checkBlank(weekVersion)){
    	parent.layer.alert('请选择关账版本！', {
            icon: 2,
            title: '提示'
        });
    	return false;
    }
    return true;
}

/**
 * 获取表头
 * @param optionsData
 * @returns
 */
function getTitle(optionsData){
	$.ajax({
        url: ctx + '/remittanceTrack/getTitle',
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

//加载数据
function reloadData(optionsData){
    var index = parent.layer.load(2,{shade: 0.1});
    table.reload('contentReload', {
        url:ctx + '/remittanceTrack/queryRemitanceTrackList',
        cols:setCols(),
        where: optionsData,
        page:{
            curr: 1 //重新从第 1 页开始
        },
        done:function () {
            parent.layer.close(index);
        }
    });
}

//加载列表
function setCols() {
	var yearMonth = $('#yearMonth').val();
	var trackType = $('#trackType').val();
	var month = yearMonth.split("-")[1];
	if(month<10){
		month=month.substring(1);
	}
    var row1 = [], row2 = [];
    row1.push(
    	{type:'numbers',title: '序号',width:80, align: 'center',rowspan: 2},
        {field: 'projectNo',title: '项目编号',width:120, align: 'center',rowspan: 2},
        {field: 'projectName',title: '项目名称',width:250, align: 'center',rowspan: 2
        	, templet: function (d) {
                return "<div style='text-align: left'>" + d.projectName + "</div>";
            }
        },
        {field: 'partnerName',title: '合作方',width:250, align: 'center',rowspan: 2
        	, templet: function (d) {
                return "<div style='text-align: left'>" + d.partnerName + "</div>";
            }
        },
        {field: 'projectStatusStr',title: '项目状态',width:120, align: 'center',rowspan: 2}
    );
    if (trackType==0) {//预计
        row1.push(
            {field: 'hkTotal', title: month+'月预计回款合计', width: 150, align: 'center',rowspan: 2, style:'text-align: center'}
        );
    }else{
    	row1.push(
    			{field: 'hkTotal', title: month+'月实际回款合计', width: 150, align: 'center',rowspan: 2, style:'text-align: center'}
    	);
    }
    //第一周表头
    if(titleJson.weekDate1 !=null){
    	row1.push(
    			{field: '', title: titleJson.weekDate1, width: 150, align: 'center', rowspan: 1, colspan: 3}
    	);
    }
    //第二周表头
    if(titleJson.weekDate2 !=null){
    	row1.push(
    			{field: '', title: titleJson.weekDate2, width: 150, align: 'center', rowspan: 1, colspan: 3}
    	);
    }
    //第三周表头
    if(titleJson.weekDate3 !=null){
    	row1.push(
    			{field: '', title: titleJson.weekDate3, width: 150, align: 'center', rowspan: 1, colspan: 3}
    	);
    }
    //第四周表头
    if(titleJson.weekDate4 !=null){
    	row1.push(
    			{field: '', title: titleJson.weekDate4, width: 150, align: 'center', rowspan: 1, colspan: 3}
    	);
    }
    //第五周表头
    if(titleJson.weekDate5 !=null){
    	row1.push(
    			{field: '', title: titleJson.weekDate5, width: 150, align: 'center', rowspan: 1, colspan: 3}
    	);
    }
    //第六周表头
    if(titleJson.weekDate6 !=null){
    	row1.push(
    			{field: '', title: titleJson.weekDate6, width: 150, align: 'center', rowspan: 1, colspan: 3}
    	);
    }
    
    //第一周表头
    if(titleJson.weekDate1 !=null){
    	row2.push(
            {field: 'weekTotal1', title: '合计', width: 150, align: 'center'},
            {field: 'xjAccount1', title: '现金', width: 150, align: 'center'},
            {field: 'dfAccount1', title: '抵房', width: 150, align: 'center'}
        );
    }
    //第二周表头
    if(titleJson.weekDate2 !=null){
    	row2.push(
            {field: 'weekTotal2', title: '合计', width: 150, align: 'center'},
            {field: 'xjAccount2', title: '现金', width: 150, align: 'center'},
            {field: 'dfAccount2', title: '抵房', width: 150, align: 'center'}
        );
    }
    //第三周表头
    if(titleJson.weekDate3 !=null){
    	row2.push(
            {field: 'weekTotal3', title: '合计', width: 150, align: 'center'},
            {field: 'xjAccount3', title: '现金', width: 150, align: 'center'},
            {field: 'dfAccount3', title: '抵房', width: 150, align: 'center'}
        );
    }
    //第四周表头
    if(titleJson.weekDate4 !=null){
    	row2.push(
            {field: 'weekTotal4', title: '合计', width: 150, align: 'center'},
            {field: 'xjAccount4', title: '现金', width: 150, align: 'center'},
            {field: 'dfAccount4', title: '抵房', width: 150, align: 'center'}
        );
    }
    //第五周表头
    if(titleJson.weekDate5 !=null){
    	row2.push(
            {field: 'weekTotal5', title: '合计', width: 150, align: 'center'},
            {field: 'xjAccount5', title: '现金', width: 150, align: 'center'},
            {field: 'dfAccount5', title: '抵房', width: 150, align: 'center'}
        );
    }
    //第六周表头
    if(titleJson.weekDate6 !=null){
    	row2.push(
            {field: 'weekTotal6', title: '合计', width: 150, align: 'center'},
            {field: 'xjAccount6', title: '现金', width: 150, align: 'center'},
            {field: 'dfAccount6', title: '抵房', width: 150, align: 'center'}
        );
    }
    
    
    var cols = [];
    cols.push(row1);
    cols.push(row2);
    return cols;
}

//初始化-版本年月
function initDateToYearMonthVersion(yearMonthVersion){
	laydate.render({
		elem: '#yearMonthVersion',
		btns:['now','confirm'],
		value:yearMonthVersion,
		type: 'month',
		format: 'yyyyMM',
		trigger: 'click',
		done: function (value, date) {
			getWeekVersion(date.year,date.month);
		}
	});
}

//初始化-年月
function initDateToYearMonth(){
	laydate.render({
		elem: '#yearMonth',
		btns:['now','confirm'],
		type: 'month',
		format: 'yyyy-MM',
		trigger: 'click',
		done: function (value, date) {
			//初始化-周
//			getWeek(date.year,date.month);
		}
	});
}

//初始化-周
function getWeekVersion(year,month,weekNo){
	var url = BASE_PATH + "/remittanceTrack/getWeeks";
	var params ={year:year,month:month};
	ajaxGet(url, params, function (data) {
		var result = "<option value=''>请选择</option>";
		$.each(data.returnData, function (n, value) {
			result += "<option value='" + value.weekNo +"' sortNo='"+value.sortNo+  "' >第"+ value.weekNo + "周</option>";
		});
		$("#weekVersion").html('');
		$("#weekVersion").append(result);
		if(weekNo) {
            $('#weekVersion').val(weekNo);
        }else{
            $('#weekVersion').val('');
        }
		form.render();
	}, function () {

	});
}


function checkBlank(obj){
	if(obj!=null && obj!="" && obj!=undefined){
		return true;
	}
	return false;
}

/**
 * excel 导入用到
 */
var params = {
	    data: function () {
	    	var yearMonth = $("#yearMonth").val(); //年月
	    	var yearMonthVersion = $("#yearMonthVersion").val(); //关账版本
	    	var weekVersion = $("#weekVersion").val(); //关账版本
	    	var year = yearMonth.split("-")[0];
	    	var month = yearMonth.split("-")[1];
	    	if(month<10){
	    		month=month.substring(1);
	    	}
	    	var sortNo=$('#weekVersion').find("option:selected").attr("sortNo");
	    	$('#sortNo').val(sortNo);
	    	var weekNo = $("#weekNo").val(); //导入用到
	        var params = {
        		projectDepartmentId : $("#projectDepartmentId").val(),
        		projectNo : trimStr($("#projectNo").val()),
        		trackType : $("#trackType").val(),
        		year : year,
        		month : month,
        		dataMonth : month,
        		yearMonthVersion : yearMonthVersion,
        		weekVersion : weekVersion,
        		sortNo : sortNo,
        		weekNo : weekNo,
	            cityNo : $("#cityNo").val()
	        };
	        return params;
	    }
	};
