/** ************************公共部分***************************** */

$(function () {
    initOrganizationEvent();
    initRegion();
    initMultiSelect();

    initCityList();
});

/** **************************demo js*************************** */
/**
 * 初始化查询
 */
initList = function () {

    systemLoading("#contentAll",true);

    var params = {

    };

    httpGetWithParams('achieveChangeListForm', 'LoadCxt', params, BASE_PATH + '/achieveChange/queryList', function () {

        pageInfo("achieveChangeListForm", function () {
            initList();
        });
        systemLoaded("#contentAll");
    },"1");
};


var achieveChange = {
    search: function () {

        var flag = validSelect();
        if(!flag){
            return false;
        }

        $('#curPage').val('1');
        initList();
    },
    export: function () {

        var flag = validSelect();
        if(!flag){
            return false;
        }

        var organization = $("#organization").val();
        var regionCodes = $("#region").find(".multi-select-value").val();
        var areaCityCodes = $("#areaCity").find(".multi-select-value").val();
        var cityIds = $("#city").find(".multi-select-value").val();
        var centerGroupIds = $("#centerGroup").find(".multi-select-value").val();
        var cityNos = $("#cityNo").find(".multi-select-value").val();

        var cookieName = guid();
        $("#btn-reset").attr("disabled",true);
        showExprotLoading(cookieName);

        window.location.href = LOC_RES_BASE_PATH + '/achieveChange/export?'
            + "organization=" + organization
            + "&regionCodes=" + regionCodes
            + "&areaCityCodes=" + areaCityCodes
            + "&cityIds=" + cityIds
            + "&centerIds=" + centerGroupIds
            + "&cityNos=" + cityNos
            + '&storeName=' + $("#storeName").val()
            + "&cookieName=" + cookieName;
        window.setTimeout(function(){
            $("#btn-reset").attr("disabled",false);
        },10000);
    }
};