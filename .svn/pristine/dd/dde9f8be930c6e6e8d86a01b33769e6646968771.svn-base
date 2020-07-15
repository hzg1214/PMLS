/** ************************公共部分***************************** */
var jsonMap = null;
var initCityDist = false;

$(function() {
	// 初始化查询
	initCityDist = true;
	initDateRangeBind('#countDateTypeKbn', '#countDateStart', '#countDateEnd');
	initCooperationStartDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');
	getDateKbnList();
	var searchParamMap = CommonUtil.getCookie("ESTATE_STAT_LIST_SEARCH");
	if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
	if (!jsonMap || !jsonMap["realityCityNo"]) initList();
	cityLinkageIsService(setCity, setDist);
});

/** **************************demo js*************************** */
Statistic = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('StatisticListForm', 'LoadCxt', BASE_PATH + '/statistic/q', function() {

		pageInfo("StatisticListForm", function() {
			initList();
		});
		var width = $("#divStatistic").width();
		var height = $("#divStatistic").height();
		FixTable("tbStatistic", 2, 1150, height);

	});
	initCityDist = false;
};
/**
 * 查询
 * 
 */
Statistic.search = function() {
	$('#curPage').val('1');
	initList();
};
Statistic.reset = function() {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
        $('#districtNo').find('option').remove();
    });
    $('form input[type="text"]').val('');
    Statistic.search();
};

/**
 * 获取日期类型列表
 */
function getDateKbnList() {
	$("#dateTypeKbn").change(
	function() {
		$("#dateKbn").val("");
		$("#dateStart").val("");
		$("#dateEnd").val("");
		var kbn = 132;
		if ($("#dateTypeKbn").val() == 14001) {
			kbn = 132;
			initCooperationStartDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');
		} else if ($("#dateTypeKbn").val() == 14002) {
			kbn = 133;
			initCooperationEndDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');
		}
		var url = BASE_PATH + "/statistic/dateKbnList/" + kbn;
		var params = {};

		ajaxGet(url, params, function(data) {
			var result = "<option value=''>请选择日期类型</option>";
			$.each(data.returnValue, function(n, value) {
				result += "<option value='" + value.dicCode + "'>"
						+ value.dicValue + "</option>";
			});
			$("#dateKbn").html('');
			$("#dateKbn").append(result);
		}, function() {
		});
	});
}


//填充储存的表单值
Statistic.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	
	$("#StatisticListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#StatisticListForm").find("select").each(function () {
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
	if (jsonMap && jsonMap.hasOwnProperty("districtId")) $("#districtNo").val(jsonMap["districtId"]);
	if (initCityDist) initList();
};
