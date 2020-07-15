/** ************************公共部分***************************** */

$(function() {
	// 初始化查询
	initList();
	initDateRangeBind('#countDateTypeKbn', '#countDateStart', '#countDateEnd');
	cityLinkageIsService();
});

/** **************************demo js*************************** */
SceneCommission = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('SceneCommissionListForm', 'LoadCxt', BASE_PATH + '/sceneCommission/qSceneCommission', function() {

		pageInfo("SceneCommissionListForm", function() {
			initList();
		});

	});
};
/**
 * 查询
 * 
 */
SceneCommission.search = function() {
	$('#curPage').val('1');
	initList();
};


//重置输入内容
SceneCommission.reset = function() {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
    $('#districtNo').find('option').remove();
};

