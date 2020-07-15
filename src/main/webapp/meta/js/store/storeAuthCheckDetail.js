/** ************************公共部分***************************** */
$(function() {

	// 初始化查询
});

/** ***************************************************** */
RelateUtil = function() {
	dialog: null;
};
RelateUtil.close = function () {
	RelateUtil.dialog.close();
};
/**
 * 审核通过
 */
function auditPass(id,storeId) {
	Dialog.confirm("确认审核通过吗？", function() {
		$.ajax({
			url:BASE_PATH + '/storeAuthCheck/auditPass',
			data: {id:id,storeId:storeId},
			type:"post",
			success:function(data){
				debugger;
				if (data && $.parseJSON(data).returnCode == "200") {
					location.href = BASE_PATH + "/storeAuthCheck";
					return;
				} else {
					Dialog.alertError($.parseJSON(data).returnMsg);
				}
			}
		});
	}/*,function () {
		StoreAuthCheck.close();
	}*/);

}
/**
 * 审核退回
 */
function auditReturn(id,storeId) {
	var url=BASE_PATH + '/storeAuthCheck/toAuditReturn/'+id+'/'+storeId;
	var params={};
	var dialogOptions = {
		width : 300,
		height : 100,
		ok : function() {
			// 确定
			var reback = RelateUtil.saveReturnReason(id,storeId);
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
RelateUtil.saveReturnReason = function(id,storeId){
	if (Validator.validForm("auditReturnForm")) {
		var param = $("#auditReturnForm").serialize();
		var url=BASE_PATH + '/storeAuthCheck/auditReturn';
		httpPost('auditReturnForm', url, function(data) {
			location.href = BASE_PATH + "/storeAuthCheck";
		}, function(data) {
			Dialog.alertError(data.returnMsg);
		});
	} else {
		return false;
	}
};