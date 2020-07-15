/** ************************公共部分***************************** */
$(function() {
	$("#historyDataFile").change(function(){
		if($(this).val() == "")
		{
			return;
		}
        SettleConfirm.dataImport();
	})
});

/** **************************demo js*************************** */
SettleConfirm = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('SettleConfirmListForm', 'LoadCxt', BASE_PATH + '/settleConfirm/listCtx', function() {

		pageInfo("SettleConfirmListForm", function() {
			initList();
		});

	});
};
/**
 * 查询
 * 
 */
SettleConfirm.search = function() {
	if(!Validator.validForm("SettleConfirmListForm"))
	{
		return;
	}
	$('#curPage').val('1');
	initList();
};

SettleConfirm.import = function() {
    $("#historyDataFile").click();
};

//导入
SettleConfirm.dataImport = function()
{
	var url= BASE_PATH + "/settleConfirm/dataImport/";
	systemLoading("", true);
	httpPost("importForm",url, function(data) {
		$("#historyDataFile").val('');
		systemLoaded();
		Dialog.alertInfo(data.returnMsg,false);

	}, function(data) {
		$("#historyDataFile").val('');
		Dialog.alertError(data.returnMsg);
		systemLoaded();
		return false;
	});	
};

SettleConfirm.output = function(){
    if(!Validator.validForm("SettleConfirmListForm"))
    {
        return;
    }
	var url = BASE_PATH + '/settleConfirm/exportCheck';
	systemLoading("", true);
	$("#btn-output").attr("disabled",true);
	var param = {
		
	}
	httpPost("SettleConfirmListForm", url, function(data) {
		window.location.href = BASE_PATH + '/settleConfirm/export?projectDepartmentId=' + $("#projectDepartmentId").val()
			+ "&countDateStart=" +$("#countDateStart").val()
			+ "&countDateEnd=" + $("#countDateEnd").val()
			+ "&businessType=" + $("#businessType").val()
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
SettleConfirm.reset = function() {
	window.location.href = BASE_PATH + '/settleConfirm?searchParam=1';
};
