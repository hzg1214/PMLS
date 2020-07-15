/** ************************公共部分***************************** */

$(function () {
    var date = new Date();
    var month = date.getMonth() + 1;
    $('#dateEnd').val(date.getFullYear() + '-' + (month < 10 ? '0' + month : month) + '-' + date.getDate());

    // 初始化查询
    initOrganizationEvent();
    initRegion();
    initMultiSelect();
});

/** **************************demo js*************************** */
PerformanceSumWeek = function () {
};

/**
 * 初始化查询
 */
initList = function () {

    systemLoading("#contentAll", true);

    var params = {};

    httpGetWithParams('PerformanceSumForm', 'LoadCxt', params, BASE_PATH + '/performanceSum/queryWeek', function () {

        //pageInfo("PerformanceSumForm", function () {
        //    initList();
        //});
        systemLoaded("#contentAll");
    }, "1");
};

/**
 * 查询
 *
 */
PerformanceSumWeek.search = function () {

    var flag = validSelect();
    if (!flag) {
        return false;
    }

    var dateFlag = validDate();
    if (!dateFlag) {
        return false;
    }

    $('#curPage').val("1");
    initList();
};

PerformanceSumWeek.export = function () {

    var flag = validSelect();
    if (!flag) {
        return false;
    }

    var dateFlag = validDate();
    if (!dateFlag) {
        return false;
    }

    var organization = $("#organization").val();
    var regionCodes = $("#region").find(".multi-select-value").val();
    var areaCityCodes = $("#areaCity").find(".multi-select-value").val();
    var cityIds = $("#city").find(".multi-select-value").val();
    var centerGroupIds = $("#centerGroup").find(".multi-select-value").val();
    var type = $("#type").val();
    var dateStart = $("#dateStart").val();

    var cookieName = guid();
    $("#btn-reset").attr("disabled",true);
    showExprotLoading(cookieName, "#contentAll");

    window.location.href = LOC_RES_BASE_PATH + "/performanceSum/exportWeek?"
        + "type=" + type
        + "&organization=" + encodeURI(organization)
        + "&regionCodes=" + encodeURI(regionCodes)
        + "&areaCityCodes=" + encodeURI(areaCityCodes)
        + "&cityIds=" + encodeURI(cityIds)
        + "&centerIds=" + encodeURI(centerGroupIds)
        + "&startDay=" + $("#startDay").val()
        + "&dateStart=" + dateStart
        + "&dateEnd=" + $("#dateEnd").val()
        + "&groupType=center"
        + "&cookieName=" + cookieName;
    window.setTimeout(function(){
        $("#btn-reset").attr("disabled",false);
    },10000);
}

function validDate() {
    var dateEnd = $("#dateEnd").val();
    if (isBlank(dateEnd)) {
        systemLoaded("#contentAll");
        Dialog.alertInfo("请先选择日期！");
        return false;
    }
    return true;
}