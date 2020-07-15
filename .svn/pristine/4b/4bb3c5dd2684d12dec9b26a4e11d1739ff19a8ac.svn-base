/** ************************公共部分***************************** */
$(function() {
	// 初始化查询
	initList();
});

/** **************************Contacts js*************************** */

/**
 * 初始化查询
 */
initList = function() {
	httpGet('storeContractListForm', 'LoadCxt',
			BASE_PATH + '/store/contract/q', function() {
				pageInfo("storeContractListForm", function() {
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


