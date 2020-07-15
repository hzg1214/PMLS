/** ************************公共部分***************************** */
$(function() {

	// 初始化查询
	RelateStoreUser.initList();
});

/** ***************************************************** */
RelateStoreUser = function() {
};

/**
 * 初始化查询
 */
RelateStoreUser.initList = function() {

	httpGet('relateStoreUserForm', 'LoadCxt', BASE_PATH + '/relate/storeuser/q', function() {

		pageInfo("relateStoreUserForm", function() {
			RelateStoreUser.initList();
		});

	});
};

/**
 * 查询
 * 
 */
RelateStoreUser.search = function() {
	$('#curPage').val('1');
	RelateStoreUser.initList();
};