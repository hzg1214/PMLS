/** ************************公共部分***************************** */
$(function() {
});

/** ***************************************************** */
ProjectLeader = function() {
};

/**
 * 初始化查询
 */
ProjectLeader.initList = function() {

	httpGet('achieveMentUserForm', 'LoadCxtPopup', BASE_PATH + '/estate/projectLeaderChoose/getList', function() {

		pageInfo("achieveMentUserForm", function() {
			ProjectLeader.initList();
		});

	});
};

/**
 * 查询
 * 
 */
ProjectLeader.search = function() {
	$('#curPage').val('1');
	ProjectLeader.initList();
};