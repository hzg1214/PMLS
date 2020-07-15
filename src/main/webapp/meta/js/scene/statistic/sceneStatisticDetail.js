/** ************************公共部分***************************** */

$(function() {
	// 初始化查询
	setPrevCondition();
	initList();
	initDateRangeBind('#countDateTypeKbn', '#countDateStart', '#countDateEnd');
	var divHeight=$("#detailList2").height();
    if(divHeight>450){
        $("#detailList2").addClass("s-scoll-y");
    }
});

/** **************************demo js*************************** */
SceneStatisticDetail = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('SceneStatisticDetailListForm', 'LoadCxt', BASE_PATH + '/sceneStatisticEstate/qSceneStatisticDetail', function() {

		pageInfo("SceneStatisticDetailListForm", function() {
			initList();
		});

	});
};
/**
 * 查询
 * 
 */
SceneStatisticDetail.search = function() {
	$('#curPage').val('1');
	initList();
};

reset = function() {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
};

/**
 * 设置前页传递的查询条件
 */
setPrevCondition = function() {
	if ($('#prevCountDateStart').val() != null && $('#prevCountDateStart').val() != '' && $('#prevCountDateStart').val() != 'null') {
		$('#countDateStart').val($('#prevCountDateStart').val());
	}
	if ($('#prevCountDateEnd').val() != null && $('#prevCountDateEnd').val() != '' && $('#prevCountDateEnd').val() != 'null') {
		$('#countDateEnd').val($('#prevCountDateEnd').val());
	}
	if ($('#prevCountDateTypeKbn').val() != null && $('#prevCountDateTypeKbn').val() != '' && $('#prevCountDateTypeKbn').val() != 'null') {
		$('#countDateTypeKbn').val($('#prevCountDateTypeKbn').val());
	}
};

