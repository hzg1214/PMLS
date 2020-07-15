/**
 * Add By cning 2017.7.11
 */
$(function(){
	initList();
	
})

CompanyLog = function() {
};

/**
 * 初始化查询
 */
initList = function() {
	httpGet('logListForm', 'LoadCxt', BASE_PATH
			+ '/companys/log/q', function() {
		pageInfo("logListForm", function() {
			initList();
		});
	});
};

function toLogDetail(logId)
{
	var url = BASE_PATH + "/companys/lg";	
	var params = {
			logId : logId,
	};
	var dialogOptions = {
			cancel : true,
			cancelVal : '取消',
			width:400,
			height:300
	};
	// 跳转至修改公司信息
	Dialog.ajaxOpenDialog(url, params, "查看公司信息", function(dialog, resData) {
		CompanyLog.dialog = dialog;
	}, dialogOptions);
}

/**
 * 返回、取消
 */
CompanyLog.back = function() {
	var companyId=$("#companyId").val();
	location.href = BASE_PATH + "/companys/log/"+companyId;

};

CompanyLog.close = function(){
	CompanyLog.dialog.close();
}
