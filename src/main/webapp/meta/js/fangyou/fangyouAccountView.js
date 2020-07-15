$(function() {
});
FangyouAccountView= function() {
};
FangyouAccountView.close=function(){
	FangyouAccountView.dialog.close();
};
FangyouAccountView.closePopup = function(){
	FangyouAccountView.dialog.close();
};
/**
 * 跳转页面
 * @param id
 */
FangyouAccountView.editFyAcountMode=function(storeId,oldFyAcount,storeNo){
	var url = BASE_PATH + '/fangyouAccount/editFyAcountMode';
	var params = {fyAcount:oldFyAcount,storeId:storeId,storeNo:storeNo};
	var dialogOptions = {
			width:600
	};
	Dialog.ajaxOpenDialog(url, params, "编辑房友账号", function(dialog, resData) {
		FangyouAccountView.dialog = dialog;
	}, dialogOptions);
};
/**
 * 修改房友账号
 */
FangyouAccountView.changeFyAcount=function(storeId){
	var oldFyAcount = $("#oldFyAcount").val();
	var newFyAccount = $("#newFyAccount").val().trim();
	if(null != oldFyAcount && oldFyAcount!="" && oldFyAcount != undefined && oldFyAcount == newFyAccount) {
		//房友账号未变更不处理
		FangyouAccountView.dialog.close();
		return;
	}
	if(validFyAcount(newFyAccount)){
		if (Validator.validForm("fangyouAccountChangeForm")) {
			var url = BASE_PATH + '/fangyouAccount/changeFyAcount';
			var params = $("#fangyouAccountChangeForm").serialize();
			
			httpPost("fangyouAccountChangeForm", url, function(data) {
				location.href = BASE_PATH + "/fangyouAccount/toFangyouAccountView/" + storeId;
			}, function(data) {
				Dialog.alertError(data.returnMsg);
				systemLoaded();
			});
			FangyouAccountView.dialog.close();
		}
	};
};
function validFyAcount(newFyAccount){
	if(newFyAccount.length != 6 || !(/(^[1-9]\d*$)/.test(newFyAccount)) ){
		$("#errorTip").html("请输入6位数字！");
		$("#newFyAccount").focus();
		return false;
	}
	$("#errorTip").html("&nbsp;");
	return true;
}
