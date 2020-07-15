$(function() {
	
});

Audit = function() {
	dialog: null;
};

Audit.close = function() {
	Audit.dialog.close();
};

/**
 * 审核通过
 */
function auditYes(id){
	Dialog.confirm("确认审核通过？",function(){
		var url = BASE_PATH + '/estate/audit/'+id;
		var params = $("#estateAuditYesForm").serialize();
		systemLoading("", true);
		httpPut(url, params, function(data) {
			location.href = BASE_PATH + "/estate?searchParam=1";
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});
	},function(){
		
	});
}

/**
 * 审核不通过
 */
function auditNo(id){
	var url = BASE_PATH + "/estate/toauditno";
	var params = {
			id:id
	};
	var dialogOptions = {
			ok : function() {
				// 确定
				var aurl = BASE_PATH + '/estate/audit/'+id;
				var aparams = $("#estateAuditNoForm").serialize();
				systemLoading("", true);
				httpPut(aurl, aparams, function(data) {
					location.href = BASE_PATH + "/estate?searchParam=1";
				}, function(data) {
					Dialog.alertError(data.returnMsg);
					systemLoaded();
					return false;
				});
				return true;
			},
			okVal : '确定不通过',
			cancel : true,
			cancelVal : '返回'
	};
	Dialog.ajaxOpenDialog(url, params, "审核不通过说明", function(dialog, resData) {
		Audit.dialog = dialog;
	}, dialogOptions);
}