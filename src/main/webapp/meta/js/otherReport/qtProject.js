/** ************************公共部分***************************** */
var jsonMap = null;
var initCityDist = false;

$(function() {
	// 初始化查询
	initCityDist = true;
	var searchParamMap = CommonUtil.getCookie("SCENE_ESTATE_LIST_SEARCH");
	if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
	initDateRangeBind('#countDateTypeKbn', '#countDateStart', '#countDateEnd');
	if (!jsonMap || !jsonMap["realityCityNo"]) initList();
	cityLinkageIsService(setCity, setDist);
});

/** **************************demo js*************************** */
SceneEstate = function() {
};

/**
 * 初始化查询
 */
initList = function() {
	httpGet('qtProjectListForm', 'LoadCxt', BASE_PATH + '/qtProject/q', function() {

		pageInfo("qtProjectListForm", function() {
			initList();
		});
	});
	initCityDist = false;
	
};
/**
 * 查询
 * 
 */
SceneEstate.search = function() {
	$('#curPage').val('1');
	initList();
};

//重置输入内容
SceneEstate.reset = function() {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
    $('#districtNo').find('option').remove();
    SceneEstate.search();
};


//填充储存的表单值
SceneEstate.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	
	$("#qtProjectListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#qtProjectListForm").find("select").each(function () {
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
