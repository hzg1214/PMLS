/** ************************公共部分***************************** */
$(function () {
    initOrganizationEvent();
    initRegion();
    initMultiSelect();
});

/** **************************demo js*************************** */
StoreStructure = function () {
    dialog: null;
};

/**
 * 初始化查询
 */
initList = function () {

    systemLoading("#contentAll",true);

    var params = {
    };

    httpGetWithParams('storeStructureForm', 'LoadCxt',params, BASE_PATH + '/storeStructure/query', function () {
        systemLoaded("#contentAll");
    },"1");
};
/**
 * 查询
 *
 */
StoreStructure.search = function () {

    var flag = validSelect();
    if(!flag){
        return false;
    }

    $('#curPage').val('1');
    initList();
};

StoreStructure.export = function () {

    var flag = validSelect();
    if(!flag){
        return false;
    }

    var organization = $("#organization").val();
    var regionCodes = $("#region").find(".multi-select-value").val();
    var areaCityCodes = $("#areaCity").find(".multi-select-value").val();
    var cityIds = $("#city").find(".multi-select-value").val();
    var centerGroupIds = $("#centerGroup").find(".multi-select-value").val();

    var cookieName = guid();
    $("#J_export").attr("disabled",true);
    showExprotLoading(cookieName);

    window.location.href = LOC_RES_BASE_PATH + '/storeStructure/export?' +
        'dateStart=' + $('#dateStart').val()
        + '&dateEnd=' + $('#dateEnd').val()
        + "&organization=" + organization
        + "&regionCodes=" + regionCodes
        + "&areaCityCodes=" + areaCityCodes
        + "&cityIds=" + cityIds
        + "&centerIds=" + centerGroupIds
        + "&cookieName=" + cookieName;
    window.setTimeout(function(){
        $("#J_export").attr("disabled",false);
    },10000);

};

function checkDate(self) {
    if ($("#dateEnd").val() != ''
        && $("#dateStart").val() > $("#dateEnd").val()) {
        Dialog.alertError("当期日期起始日不能大于结束日！");
        $(self).val('');
    }
};
