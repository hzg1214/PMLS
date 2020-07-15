/** ************************公共部分***************************** */
$(function() {

	// 初始化查询
	RelateUser.initList();
});

/** ***************************************************** */
RelateUser = function() {
};

/**
 * 初始化查询
 */
RelateUser.initList = function() {

	httpGet('relateUserForm', 'LoadCxt', BASE_PATH + '/relate/user/q', function() {

		pageInfo("relateUserForm", function() {
			RelateUser.initList();
		});

	});
};

/**
 * 查询
 * 
 */
RelateUser.search = function() {
	$('#curPage').val('1');
	RelateUser.initList();
};