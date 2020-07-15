/** ************************公共部分***************************** */
$(function() {
	// 初始化查询
	initList();
});

/**
 * 初始化查询
 */
initList = function() {
	
	httpGet('maintainerHisForm', 'LoadCxt', BASE_PATH + '/storeMaintainer/qMaintainerHis', function() {

		pageInfo("maintainerHisForm", function() {
			initList();
		});

	});
};

/**
 * 查询
 * 
 */
search = function() {
	$('#curPage').val('1');
	initList();
};

