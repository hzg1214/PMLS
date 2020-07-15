/** ************************公共部分***************************** */

$(function() {
	// 初始化查询
	setPrevCondition();
	initList();
	initDateRangeBind('#countDateTypeKbn', '#countDateStart', '#countDateEnd');
	var divHeight=$("#detailList").height();
    if(divHeight>450){
        $("#detailList").addClass("s-scoll-y");
    }
});

/** **************************demo js*************************** */
SceneStatisticCompany = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('SceneStatisticCompanyListForm', 'LoadCxt', BASE_PATH + '/sceneStatisticEstate/qSceneStatisticCompany', function() {

		pageInfo("SceneStatisticCompanyListForm", function() {
			initList();
		});

	});
};
/**
 * 查询
 * 
 */
SceneStatisticCompany.search = function() {
	$('#curPage').val('1');
	initList();
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

reset = function() {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
};


//填充储存的表单值
SceneStatisticCompany.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	
	$("#SceneStatisticCompanyListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#SceneStatisticCompanyListForm").find("select").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	//筛选
	var signDateType = jsonMap["signDateType"];
	if (signDateType != undefined && signDateType != null && signDateType != "") {
		$("#divSignType").find("a").removeClass("active");
		$("#divSignType").find("a").eq(signDateType).addClass("active");
		$("#divSignType").find("a").eq(signDateType).click();
	}
	var curPage = jsonMap["curPage"];
    if (curPage > 1) {
        $('#curPageTemp').val(curPage);
        $('#sysPageChaneTemp').val(jsonMap["sysPageChane"]);
    }
};

