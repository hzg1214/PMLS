/** ************************公共部分***************************** */
var jsonMap = null;
$(function() {
	// 初始化查询
	initList();
	initReportDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');

    var searchParamMap = CommonUtil.getCookie("CUSTOM_LIST_SEARCH");
    if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
    if (!jsonMap || !jsonMap["realityCityNo"]) initList();
    cityLinkageIsService(setCity, setDist);
});

setCity = function(){
    if (jsonMap && jsonMap.hasOwnProperty("realityCityNo")) $("#realityCityNo").val(jsonMap["realityCityNo"]).change();
};

setDist = function(){
    if (jsonMap && jsonMap.hasOwnProperty("districtId")) $("#districtNo").val(jsonMap["districtId"]);
    if (initCityDist) initList();
};

/** **************************demo js*************************** */
Custom = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('CustomListForm', 'LoadCxt', BASE_PATH + '/custom/q', function() {

		pageInfo("CustomListForm", function() {
			initList();
		});
        //表前几列冻结
		var width = $("#divReport").width;
		var height = $("#divReport").height();
		FixTable("tblReport", 5, 1150, height);

	});
};
/**
 * 查询
 * 
 */
Custom.search = function() {
	$('#curPage').val('1');
	initList();
};

Custom.reset = function() {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
    Custom.search();
};


//填充储存的表单值
Custom.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	
	$("#CustomListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#CustomListForm").find("select").each(function () {
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
