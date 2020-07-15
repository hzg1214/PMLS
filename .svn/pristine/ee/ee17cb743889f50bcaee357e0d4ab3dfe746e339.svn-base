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
	});
});

/** **************************demo js*************************** */
SceneYSSRCommission = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('SceneYSSRCommissionListForm', 'LoadCxt', BASE_PATH + '/sceneLinkCommission/qSceneLinkCommission', function() {

		pageInfo("SceneYSSRCommissionListForm", function() {
			initList();
		});

        $('#check-all').change(function() {
            if ($(this).prop('checked')){
                $('.check').each(function () {
                    $(this).prop('checked', true)
                });
            }else {
                $('.check').each(function () {
                    $(this).prop('checked', false)
                });
            }
        });

        //表前几列冻结
        var width = $("#divReport").width;
        var height = $("#divReport").height();
        FixTable("YSSRCommissionTbl", 3, 1150, height);
	});
};
/**
 * 查询
 * 
 */
SceneYSSRCommission.search = function() {
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

	var estateType = $('#estateType').val();
	if(estateType == '')
	{
		Dialog.alertInfo('请选择模板类型!',true);
	    return false;
	}



	return true;

}

/**
 * 导入弹出选择文件对话框
 * 
 */
SceneYSSRCommission.imput = function() {
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

	var url= BASE_PATH + "/sceneLinkCommission/imput/";
	systemLoading("", true);
	httpPost("imputForm",url, function(data) {
		$("#historyDataFile").val('');
		systemLoaded();
		Dialog.alertError(data.returnMsg);

	}, function(data) {
		$("#historyDataFile").val('');
		Dialog.alertError(data.returnMsg);
		systemLoaded();
		return false;
	});	
};

SceneYSSRCommission.output = function(){
	if(!check())
	{
		return;
	}
	var url = BASE_PATH + '/sceneLinkCommission/exportCheck';
	systemLoading("", true);
	$("#btn-output").attr("disabled",true);
	var param = {
		
	}
	httpPost("SceneYSSRCommissionListForm", url, function(data) {
		window.location.href = BASE_PATH + '/sceneLinkCommission/export?projectDepartmentId=' + $("#projectDepartmentId").val()
			+ "&countDateStart=" +$("#countDateStart").val()
			+ "&countDateEnd=" + $("#countDateEnd").val()
			+ "&searchKey=" + $("#searchKey").val() + "&estateType=" + $("#estateType").val()
			+ "&estateNmKey=" + $("#estateNmKey").val() + "&reportId=" + $("#reportId").val();
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
SceneYSSRCommission.reset = function() {
	$('select').each(function(key, value) {
		$(value).find('option').prop('selected', false);
		$(value).find('option:first').prop('selected', true);
	});
	$('form input[type="text"]').val('');
	$('form input[type="checkbox"]').attr("checked", "checked");
	window.location.href = BASE_PATH + '/sceneLinkCommission';
};

