$(function() {
	$("#errormsg").empty();
	$("#errormsg1").empty();
	$("#errormsg2").empty();
});
AchievementView= function() {
};
AchievementView.close=function(){
	AchievementView.dialog.close();
};
AchievementView.closePopup = function(){
	AchievementView.dialog.close();
};
/**
 * 跳转页面
 * @param id
 */
AchievementView.editAchievementMode=function(){
	var modFlagControl = $("#modFlagControl").val();//是否已关帐  1是 0否
	if(modFlagControl != null && modFlagControl != undefined && modFlagControl == 1){
		Dialog.alertError("该报备房源大定日期已关账，不允许调整业绩信息！");
		return false;
	}
	
	var  customerFromValue = $("#customerFromValue").val();
	if(17405 != customerFromValue){
		var url = BASE_PATH + '/report/editAchievementMode';
		var params =$("#achievementAdjustSubimtForm").serialize();
		var dialogOptions = {
				width:650
		};
		Dialog.ajaxOpenDialog(url, params, "业绩变更", function(dialog, resData) {
			AchievementView.dialog = dialog;
		}, dialogOptions);
	}else {
		Dialog.alertError("报备来源于有房通的数据，如需调整业绩信息，需先在CRM中维护联动人员中心，然后在友房通后台更新业绩信息!");
		//$("#editAchievementMode").hide();
	}
};
/**
 * 保存业绩调整
 */
AchievementView.saveAchievementAdjut=function(estateId,companyId,customerId,relateId,fromType){
	//var linkUserCode = $("#linkUser").find("option:selected").val();
	var linkUserCode = $("#linkUserCode").val();
	var newCenterId = $("#newCenterId").find("option:selected").val();
	var newCenterName = $("#newCenterId").find("option:selected").text();
	$("#newCenterCode").val(newCenterId);
	$("#newCenterName").val(newCenterName);
	//老中心和老的业绩归属人
	var oldContactId = $("input:hidden[name='oldContactId']").val();
	var oldCenterGroupId = $("input:hidden[name='oldCenterGroupId']").val();
	if(linkUserCode == oldContactId  && oldCenterGroupId == newCenterId) {
		//未变更不处理
		AchievementView.dialog.close();
		return;
	}
	if (Validator.validForm("achievementAdjustForm")) {
		var url = BASE_PATH + '/report/saveAchievementAdjut';
		var params = $("#achievementAdjustForm").serialize();
		httpPost("achievementAdjustForm", url, function(data) {
			location.href = BASE_PATH + "/report/" + estateId+'/'+companyId+'/'+customerId+'/'+relateId+'/'+fromType;
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});
		AchievementView.dialog.close();
	};
};
/**
 * 跳转页面
 * @param id
 */
AchievementView.editCustomerInfoMode=function(){
	var url = BASE_PATH + '/report/editCustomerInfoMode';
	var params =$("#customerAdjustSubimtForm").serialize();
	var dialogOptions = {
			width:780
	};
	Dialog.ajaxOpenDialog(url, params, "客户信息调整", function(dialog, resData) {
		AchievementView.dialog = dialog;
	}, dialogOptions);
};
/**
 * 保存业绩调整
 */
AchievementView.saveCustomerInfoAdjut=function(estateId,companyId,customerId,relateId,fromType){
	
	var oldCustomerNm = $("#oldCustomerNm").val();
	var oldCustomerTel = $("#oldCustomerTel").val();
	var oldCustomerNmTwo = $("#oldCustomerNmTwo").val();
	var oldCustomerTelTwo = $("#oldCustomerTelTwo").val();
	
	var customerNm = $("#customerNm").val();
	var customerTel = $("#customerTel").val();
	var customerNmTwo = $("#customerNmTwo").val();
	var customerTelTwo = $("#customerTelTwo").val();
	if(customerNm==undefined || customerNm==null || customerNm =='') {
		return false;
	}
	if(customerTel==undefined || customerTel==null || customerTel =='') {
		return false;
	}
	if(oldCustomerNm == customerNm && oldCustomerTel == customerTel && oldCustomerNmTwo== customerNmTwo&& oldCustomerTelTwo == customerTelTwo ){
		$("#errorMsg").empty().html("客户信息未做更改！");
		return false;
	}
	if($("#customerNmTwo").val() !="") {
		if ($("#customerTelTwo").val()==undefined || $("#customerTelTwo").val()==null || $("#customerTelTwo").val()=='') {
			$("#errorMsg2").empty().html("客户已填写，客户手机必须填写！");
			$("#customerTelTwo").focus();
			return false;
		}
	}
	if($("#customerTelTwo").val() !="") {
		if ($("#customerNmTwo").val()==undefined || $("#customerNmTwo").val()==null || $("#customerNmTwo").val()=='') {
			$("#errorMsg1").empty().html("客户手机已填写，客户必须填写！");
			$("#customerNmTwo").focus(); 
			return false;
		}
	}
	if (Validator.validForm("customerAdjustForm")) {
		var url = BASE_PATH + '/report/saveCustomerInfoAdjut';
		var params = $("#customerAdjustForm").serialize();
		httpPost("customerAdjustForm", url, function(data) {
			location.href = BASE_PATH + "/report/" + estateId+'/'+companyId+'/'+customerId+'/'+relateId+'/'+fromType;
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});
		AchievementView.dialog.close();
	};
};
	