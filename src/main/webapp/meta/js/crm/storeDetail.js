/** ************************公共部分***************************** */
$(function() {

	// 初始化查询
});

/** ***************************************************** */
RelateUtil = function() {
	dialog: null;
};

/**
 * 审核通过
 */
function auditPass(storeId,storeName,storeNo,userCode) {
	Dialog.confirm("确认审核通过吗？", function() {
		var url=BASE_PATH + '/storeAudit/auditPass';
		var paramsData={storeId:storeId,storeName:storeName,storeNo:storeNo,userCode:userCode};
		ajaxGet(url,paramsData,function(data) {
				location.href= BASE_PATH +"/crm/index";
			},
			function(data) {
				Dialog.alertError(data.returnMsg);
			}
		);
	});

}
/**
 * 审核退回
 */
function auditReturn(storeId,storeName,storeNo,userCode) {
	var url=BASE_PATH + '/storeAudit/toDeposit/'+storeId+'/'+storeName+'/'+storeNo+'/'+userCode;
	var params={};
	var dialogOptions = {
		width : 300,
		height : 100,
		ok : function() {
			// 确定
			var reback = RelateUtil.saveDeposit(storeId);
			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};
	Dialog.ajaxOpenDialog(url, params, "驳回原因", function(dialog, resData) {
		RelateUtil.dialog = dialog;
	}, dialogOptions);

}
//提交驳回原因并更改审核状态
RelateUtil.saveDeposit = function(storeId){
	if (Validator.validForm("depositForm")) {
		var param = $("#depositForm").serialize();
		var url=BASE_PATH + '/storeAudit/auditReturn';
		ajaxGet(url,param,function(data) {
				location.href= BASE_PATH +"/crm/index";
			},
			function(data) {
				Dialog.alertError(data.returnMsg);
			}
		);
	} else {
		return false;
	}
};