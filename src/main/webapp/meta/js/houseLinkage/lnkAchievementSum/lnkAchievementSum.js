var projectStatusStr = "";

/** ************************公共部分***************************** */
$(function () {
    initOrganizationEvent();
    initRegion();
    initMultiSelect();

    cityLinkageIsService();
});

/** **************************demo js*************************** */
LnkAchievementSum = function () {
};
/**
 * 初始化查询
 */
initList = function () {

    systemLoading("#contentAll",true);

    var params = {

    };

    httpGetWithParams('LnkAchievementSumListForm', 'LoadCxt', params, BASE_PATH + '/lnkAchievementSum/queryLnkAchievementSumList', function () {

        pageInfo("LnkAchievementSumListForm", function () {
            initList();
        });
        systemLoaded("#contentAll");
    },"1");
};

/**
 * 查询
 *
 */
LnkAchievementSum.search = function () {

    var flag = validSelect();
    if(!flag){
        return false;
    }
    if(checkForAdd()){
    	$('#curPage').val('1');
    	initList();
    }
};

LnkAchievementSum.export = function () {

    var flag = validSelect();
    if(!flag){
        return false;
    }

    var organization = $("#organization").val();
    var regionCodes = $("#region").find(".multi-select-value").val();
    var areaCityCodes = $("#areaCity").find(".multi-select-value").val();
    var cityIds = $("#city").find(".multi-select-value").val();
    var centerGroupIds = $("#centerGroup").find(".multi-select-value").val();
	var deptIds = $("#dept").find(".multi-select-value").val();
    var projectCondition =  $("#projectCondition").val();
    var companyCondition =  $("#companyCondition").val();
    var storeCondition =  $("#storeCondition").val();
	var channelCondition = $("#channelCondition").val();
    if(checkForAdd()){
	    var cookieName = guid();
	    $("#btn-reset").attr("disabled",true);
	    showExprotLoading(cookieName);
	    window.location.href = LOC_RES_BASE_PATH + '/lnkAchievementSum/exportLnkAchievementSum?'
	        + "organization=" + organization
	        + "&regionCodes=" + regionCodes
	        + "&areaCityCodes=" + areaCityCodes
	        + "&cityIds=" + cityIds
	        + "&centerIds=" + centerGroupIds
			+ "&deptIds=" + deptIds
	        + "&startDate=" + $("#startDate").val()
	        + "&endDate=" + $("#endDate").val()
	        + "&projectCondition=" + (isBlank(projectCondition) ? "" : projectCondition)
	        + "&companyCondition=" + (isBlank(companyCondition) ? "" : companyCondition)
	        + "&storeCondition=" + (isBlank(storeCondition) ? "" : storeCondition)
			+ "&channelCondition=" + (isBlank(channelCondition) ? "" : channelCondition)
	        + "&cookieName=" + cookieName;
	    window.setTimeout(function(){
	        $("#btn-reset").attr("disabled",false);
	    },10000);
    }
}
LnkAchievementSum.reset=function () {
	$('select').each(function(key, value) {
		$(value).find('option').prop('selected', false);
		$(value).find('option:first').prop('selected', true);
	});
	$('form input[type="text"]').val('');
	$('form input[type="checkbox"]').attr("checked", false);
	window.location.href = BASE_PATH + '/lnkAchievementSum';
};
function checkForAdd(){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	
	if(startDate == ""){
		Dialog.alertInfo("请选择查询开始日期!");
		return false;
	}
	if(endDate == ""){
		Dialog.alertInfo("请选择查询截止日期!");
		return false;
	}
	return true;
};
function checkDate(self) {
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if (startDate != '' &&  endDate != '' && startDate > endDate) {
		Dialog.alertError("查询时间起始时间不能大于结束时间！");
		$("#endDate").val('');
		$("#startDate").val('');
	}
	var startDateStr = startDate.substr(0, 4);
	var endDateStr = endDate.substr(0, 4);
	if(startDate != '' &&  endDate != '' && startDateStr !=endDateStr){
		Dialog.alertError("查询日期筛选时不可跨年！");
		$("#endDate").val('');
		$("#startDate").val('');
	}
};

