/** ************************公共部分***************************** */

$(function() {
//	//初始化基础店铺信息	companyBaseInfo
//	var companyId = $("#companyId").val();
//	httpGet('contactsForm', 'LoadBaseInfo', BASE_PATH + '/companys/'+companyId, function() {
//	});

	// 初始化查询
	initList();
});

/** **************************Contacts js*************************** */

/**
 * 初始化查询
 */
initList = function() {
	httpGet('trailListForm', 'LoadCxt', BASE_PATH + '/companys/follow/q',
			function() {

				pageInfo("trailListForm", function() {
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

detail = function(followId) {
	var url = BASE_PATH + "/follow/" + followId;
	var params = {};
	var dialogOptions = {
		cancel : true,
		cancelVal : '返回'
	};
	// 跳转至跟进详情
	Dialog.ajaxOpenDialog(url, params, "跟进详情", function(dialog, resData) {
		Follow.dialog = dialog;
	}, dialogOptions);
};