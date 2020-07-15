/** ************************公共部分***************************** */
var jsonMap = null;
var initCityDist = false;

$(function() {
	// 初始化查询
	initCityDist = true;
	initDateRangeBind('#countDateTypeKbn', '#countDateStart', '#countDateEnd');
	var searchParamMap = CommonUtil.getCookie("SCENE_STAT_LIST_SEARCH");
	if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
	if (!jsonMap || !jsonMap["realityCityNo"]) initList();
	cityLinkageIsService(setCity, setDist);
	
	var divHeight=$("#detailList3").height();
    if(divHeight>450){
        $("#detailList3").addClass("s-scoll-y");
    }
});

/** **************************demo js*************************** */
SceneStatisticEstate = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('SceneStatisticEstateListForm', 'LoadCxt', BASE_PATH + '/sceneStatisticEstate/qSceneStatisticEstate', function() {

		pageInfo("SceneStatisticEstateListForm", function() {
			initList();
		});

	});
	initCityDist = false;
};
/**
 * 查询
 * 
 */
SceneStatisticEstate.search = function() {
	$('#curPage').val('1');
	initList();
};

SceneStatisticEstate.reset = function() {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
    $('#districtNo').find('option').remove();
};

//填充储存的表单值
SceneStatisticEstate.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	
	$("#SceneStatisticEstateListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#SceneStatisticEstateListForm").find("select").each(function () {
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

setCity = function(){
	if (jsonMap && jsonMap.hasOwnProperty("realityCityNo")) $("#realityCityNo").val(jsonMap["realityCityNo"]).change();
};

setDist = function(){
	if (jsonMap && jsonMap.hasOwnProperty("districtNo")) $("#districtNo").val(jsonMap["districtNo"]);
	if (initCityDist) initList();
};
