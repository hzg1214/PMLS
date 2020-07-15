/** ************************公共部分***************************** */

$(function() {
	// 初始化查询
	initList();
	initReportDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');
	var divHeight=$("#detailList2").height();
    if(divHeight>450){
        $("#detailList2").addClass("s-scoll-y");
    }
});

/** **************************demo js*************************** */
StatisticDetail = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('StatisticDetailListForm', 'LoadCxt', BASE_PATH + '/statistic/qStatisticDetail', function() {

		pageInfo("StatisticDetailListForm", function() {
			initList();
		});

	});
};
/**
 * 查询
 * 
 */
StatisticDetail.search = function() {
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


