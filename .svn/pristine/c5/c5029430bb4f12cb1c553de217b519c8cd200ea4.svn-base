/** ************************公共部分***************************** */

$(function() {
	// 初始化查询
	//initList();
	//setCalendar();
	//选择文件后，进行导入
	$("#historyDataFile").change(function(){
		if($(this).val() == "")
		{
			return;
		}
		dataImport();
	})

    /*$("#businessType").change(function () {
        $('#estateType').each(function(key, value) {
            $(value).find('option').prop('selected', false);
            $(value).find('option:first').prop('selected', true);
        });
        if($("#businessType").val()=='DD'){
            $("#estateType option[value='24501']").hide();
            $("#estateType option[value='24504']").hide();
            $("#estateType option[value='24505']").hide();
        }else{
            $("#estateType option[value='24501']").show();
            $("#estateType option[value='24504']").show();
            $("#estateType option[value='24505']").show();
        }
    });*/
});

/** **************************demo js*************************** */
SceneInCommission = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('SceneInCommissionListForm', 'LoadCxt', BASE_PATH + '/sceneLinkCommission/qSceneLinkCommission', function() {

		pageInfo("SceneInCommissionListForm", function() {
			initList();
		});

	});
};
/**
 * 查询
 * 
 */
SceneInCommission.search = function() {
	if(!check())
	{
		return;
	}
	
	$('#curPage').val('1');
	initList();
};

function check()
{
	var statrDate = $('#countDateStart').val();
	var endStart = $('#countDateEnd').val();
	var start = statrDate.substring(0,4);
	var end   = endStart.substring(0,4);
	if(statrDate == '')
	{
		Dialog.alertInfo('请选择开始日期!',true);
	    return false;
	}
	if(endStart == '')
	{
		Dialog.alertInfo('请选择结束日期!',true);
	    return false;
	}
	if(statrDate > endStart)
	{
		Dialog.alertInfo('开始日期不能大于结束日期!',true);
	    return false;
	}
//	if(start != end)
//	{
//		Dialog.alertInfo('请选择同一年份日期!',true);
//	    return false;
//	}
	
	$('#years').val(start);
	var yearNow = $("#yearNow").val();
//	if (year != yearNow && (start != year || end != year))
//	{
//		Dialog.alertInfo('请选择业务年份中的日期!',true);
//	    return false;
//	}
	var estateType = $('#estateType').val();
	if(estateType == '')
	{
		Dialog.alertInfo('请选择模板类型!',true);
	    return false;
	}
	$('#years').val(start);
	return true;

}

/**
 * 导入弹出选择文件对话框
 * 
 */
SceneInCommission.imput = function() {	
	var estateType = $('#estateType').val();
	if(estateType == '')
	{
		Dialog.alertError('请选择模板类型!');
	    return false;
	}
	$("#estateTypeImput").val(estateType);
	
	$("#historyDataFile").click();
};

//导入
function dataImport()
{
	var statrDate = $('#countDateStart').val();
	var endStart = $('#countDateEnd').val();
	var url= BASE_PATH + "/sceneLinkCommission/imput/";
	systemLoading("", true);
	httpPost("imputForm",url, function(data) {
		$("#historyDataFile").val('');
		systemLoaded();
		Dialog.alertError(data.returnMsg);
		if(statrDate != '' && endStart != '')
		{
			SceneInCommission.search();
		}
	}, function(data) {
		$("#historyDataFile").val('');
		Dialog.alertError(data.returnMsg);
		systemLoaded();
		return false;
	});	
};

SceneInCommission.output = function(){
	if(!check())
	{
		return;
	}
	var url = BASE_PATH + '/sceneLinkCommission/exportCheck';
	systemLoading("", true);
	$("#btn-output").attr("disabled",true);
	var param = {
		
	}
	httpPost("SceneInCommissionListForm", url, function(data) {
		window.location.href = BASE_PATH + '/sceneLinkCommission/export?cityNo=' + $("#cityNo").val() + "&projectDepartmentId=" + $("#projectDepartmentId").val()
	    + "&searchKey=" + $("#searchKey").val() + "&reportId=" + $("#reportId").val() + "&countDateStart=" +$("#countDateStart").val()
	    + "&countDateEnd=" + $("#countDateEnd").val() + "&estateType=" + $("#estateType").val()
		+ "&estateNmKey=" + $("#estateNmKey").val()+"&businessType=" + $("#businessType").val();
		systemLoaded();
		$('#btn-output').removeAttr("disabled");
	}, function(data) {
		Dialog.alertError(data.returnMsg);
		systemLoaded();
		$('#btn-output').removeAttr("disabled"); 
	});	
};

//清空条件
reset = function() {
	$('select').each(function(key, value) {
		$(value).find('option').prop('selected', false);
		$(value).find('option:first').prop('selected', true);
	});
	$('form input[type="text"]').val('');
	$('form input[type="checkbox"]').attr("checked", "checked");
};
SceneInCommission.reset = function() {
	$('select').each(function(key, value) {
		$(value).find('option').prop('selected', false);
		$(value).find('option:first').prop('selected', true);
	});
	$('form input[type="text"]').val('');
	$('form input[type="checkbox"]').attr("checked", "checked");
	if($("#flagHref").val() == '0'){
		window.location.href = BASE_PATH + '/sceneCommission';
	}
	if($("#flagHref").val() == '1'){
		window.location.href = BASE_PATH + '/sceneInCommission';
	}
};

//时间控件范围控制
/*setCalendar = function(){
	$("#year").unbind("change");
	var yearNow = $("#yearNow").val();
	$("#year").on("change", function(){
		var value = $(this).val();
//		if (value == yearNow-1){
//			$("#countDateStart").removeAttr("onFocus");
//			$("#countDateStart").attr("onFocus", "WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'" + value + "-1-1',maxDate:'" + value + "-12-31'})");
//			$("#countDateEnd").removeAttr("onFocus");
//			$("#countDateEnd").attr("onFocus", "WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'" + value + "-1-1',maxDate:'" + value + "-12-31'})");
//		}else{
//			$("#countDateStart").removeAttr("onFocus");
//			$("#countDateStart").attr("onFocus", "WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})");
//			$("#countDateEnd").removeAttr("onFocus");
//			$("#countDateEnd").attr("onFocus", "WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\\'countDateStart\\')}',maxDate:'%y-%M-%d'})");
//		}
		if(value == yearNow){
			$("#countDateStart").removeAttr("onFocus");
			$("#countDateStart").attr("onFocus", "WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})");
			$("#countDateEnd").removeAttr("onFocus");
			$("#countDateEnd").attr("onFocus", "WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\\'countDateStart\\')}',maxDate:'%y-%M-%d'})");
		}else{
			$("#countDateStart").removeAttr("onFocus");
			$("#countDateStart").attr("onFocus", "WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'2016-1-1',maxDate:'" + value + "-12-31'})");
			$("#countDateEnd").removeAttr("onFocus");
			$("#countDateEnd").attr("onFocus", "WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'2016-1-1',maxDate:'" + value + "-12-31'})");
		}
			
	});
};*/
