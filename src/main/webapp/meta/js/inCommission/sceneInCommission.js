/** ************************公共部分***************************** */

$(function() {
	// 初始化查询
	initList();
	initDateRangeBind('#countDateTypeKbn', '#countDateStart', '#countDateEnd');
	cityLinkageIsService();
});

/** **************************demo js*************************** */
SceneInCommission = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('SceneInCommissionListForm', 'LoadCxt', BASE_PATH + '/sceneInCommission/qSceneInCommission', function() {

		pageInfo("SceneInCommissionListForm", function() {
			initList();
		});

	});
};
/**
 * 查询
 * 
 */
SceneInCommission.search = function() {
	$('#curPage').val('1');
	initList();
};


/**
 * 导入
 * 
 */
SceneInCommission.imput = function() {
//    $('select').each(function (key, value) {
//        $(value).find('option').prop('selected', false);
//        $(value).find('option:first').prop('selected', true);
//    });
//    $('form input[type="text"]').val('');
//    $('#districtNo').find('option').remove();
};

/**
 * 导出
 * 
 */
SceneInCommission.output = function() {
//    $('select').each(function (key, value) {
//        $(value).find('option').prop('selected', false);
//        $(value).find('option:first').prop('selected', true);
//    });
//    $('form input[type="text"]').val('');
//    $('#districtNo').find('option').remove();
};

reset = function() {
	$('select').each(function(key, value) {
		$(value).find('option').prop('selected', false);
		$(value).find('option:first').prop('selected', true);
	});
	$('form input[type="text"]').val('');
	$('form input[type="checkbox"]').attr("checked", "checked");
};
