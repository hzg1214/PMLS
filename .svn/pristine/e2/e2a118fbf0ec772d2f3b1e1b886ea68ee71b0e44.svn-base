$(function () {

    initOrganizationEvent();
    initRegion();
    initMultiSelect();

    initCityList(null,1,"1");
});

Dialog.alertSuccess = function () {
    dialog: null;
}

initList = function () {
    systemLoading("#contentAll",true);
    httpGet('storeListForm', 'LoadCxt', BASE_PATH + '/noSignedStore/q', function () {

        pageInfo("storeListForm", function () {
            initList();
        });
        systemLoaded("#contentAll");
    },"1");
};

function searchList() {

    var flag = validSelect();
    if(!flag){
        return false;
    }

    var startDate = $("#dateCreateStart").val();
    var endDate = $("#dateCreateEnd").val();

    if (startDate > endDate) {
        Dialog.alertError('结束日期不能大于起始日期!', true);
        return;
    }

    $('#curPage').val('1');
    initList();
}

//选择合同终止原因
$('#contractStop').find('.multi-select').multiSelect({
    check: function ($instance) {
    }
});

//导出
function noSignExport() {

    var flag = validSelect();
    if(!flag){
        return false;
    }

    var organization = $("#organization").val();
    var regionCodes = $("#region").find(".multi-select-value").val();
    var areaCityCodes = $("#areaCity").find(".multi-select-value").val();
    var cityIds = $("#city").find(".multi-select-value").val();
    var centerGroupIds = $("#centerGroup").find(".multi-select-value").val();
    var dueCause = $("#contractStop").find(".multi-select-value").val();
    var cityNos = $("#cityNo").find(".multi-select-value").val();

    var cookieName = guid();
    $("#btn-reset").attr("disabled",true);
    showExprotLoading(cookieName,"#contentAll");

    window.location.href = BASE_PATH + "/noSignedStore/export?"
        + "dateCreateStart=" + $("#dateCreateStart").val()
        + "&dateCreateEnd=" + $("#dateCreateEnd").val()
        + "&organization=" + organization
        + "&regionCodes=" + regionCodes
        + "&areaCityCodes=" + areaCityCodes
        + "&cityIds=" + cityIds
        + "&centerIds=" + centerGroupIds
        + "&cityNos=" + cityNos
        + "&searchKey=" + $("#searchKey").val()
        + "&dueCause=" + dueCause
        + "&cookieName=" + cookieName;
    window.setTimeout(function(){
        $("#btn-reset").attr("disabled",false);
    },10000);
}




