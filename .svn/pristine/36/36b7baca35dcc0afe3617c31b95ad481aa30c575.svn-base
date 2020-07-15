/** ************************公共部分***************************** */
$(function() {

	// 初始化查询
	RelateStores.initList();
});

/** ***************************************************** */
RelateStores = function() {
};

/**
 * 初始化查询
 */
RelateStores.initList = function() {

	httpGet('relateStoresForm', 'LoadCxt', BASE_PATH + '/relate/q', function() {

		pageInfo("relateStoresForm", function() {
			RelateStores.initList();
		});

	});
};

/**
 * 查询
 * 
 */
RelateStores.search = function() {
	$('#curPage').val('1');
	RelateStores.initList();
};