/** ************************公共部分***************************** */
$(function () {
    initOrganizationEvent();
    initRegion();
    initMultiSelect();
    initFollowAim();

    initCityList();
});

/** **************************demo js*************************** */


FollowDetail = function () {
    dialog: null;
};

/**
 * 初始化查询
 */
initList = function () {

    systemLoading("#contentAll", true);

    var params = {};

    httpGetWithParams('followDetailForm', 'LoadCxt', params, BASE_PATH + '/followDetail/query', function () {

        pageInfo("followDetailForm", function () {
            initList();
        });
        systemLoaded("#contentAll");
    }, "1");

};
/**
 * 查询
 *
 */
FollowDetail.search = function () {

    var flag = validSelect();
    if (!flag) {
        return false;
    }

    $('#curPage').val('1');
    initList();
};

FollowDetail.export = function () {

    var flag = validSelect();
    if (!flag) {
        return false;
    }

    var organization = $("#organization").val();
    var regionCodes = $("#region").find(".multi-select-value").val();
    var areaCityCodes = $("#areaCity").find(".multi-select-value").val();
    var cityIds = $("#city").find(".multi-select-value").val();
    var centerGroupIds = $("#centerGroup").find(".multi-select-value").val();
    var cityNos = $("#cityNo").find(".multi-select-value").val();
    var followAimCodes = $("#followAim").find(".multi-select-value").val();

    var cookieName = guid();
    $("#J_export").attr("disabled", true);
    showExprotLoading(cookieName);

    window.location.href = LOC_RES_BASE_PATH + "/followDetail/export?"
        + "organization=" + organization
        + "&regionCodes=" + regionCodes
        + "&areaCityCodes=" + areaCityCodes
        + "&cityIds=" + cityIds
        + "&centerIds=" + centerGroupIds
        + "&cityNos=" + cityNos
        + "&followAimCodes=" + followAimCodes
        + "&contractStatus=" +  $('#contractStatus').val()
        + "&decorateStatus=" +  $('#decorateStatus').val()
        + "&store=" + $('#store').val()
        + "&follower=" + $('#follower').val()
        + "&dateStart=" + $('#dateStart').val()
        + "&dateEnd=" + $('#dateEnd').val()
        + "&cookieName=" + cookieName;
    window.setTimeout(function () {
        $("#J_export").attr("disabled", false);
    }, 10000);
};

FollowDetail.showMap = function (followerId, followDate) {

    var url = LOC_RES_BASE_PATH + '/followDetail/showMap/' + followerId + '/' + followDate;
    var title = '';
    var params = {};
    var dialogOptions = "";

    Dialog.ajaxOpenDialog(url, params, title, function (dialog) {
        FollowDetail.dialog = dialog;
    }, dialogOptions);

};

FollowDetail.getSignDetail = function (storeNo, signageNo, storeName, storeAddress, follower, followDate, followAim, followDesc, signPictureRefId, decorateStatus) {
    var url = LOC_RES_BASE_PATH + '/followDetail/getSignDetail';
    var title = '';
    var params = {
        'storeNo': storeNo,
        'signageNo': signageNo,
        'storeName': storeName,
        'storeAddress': storeAddress,
        'follower': follower,
        'followDate': followDate,
        'followAim': followAim,
        'followDesc': followDesc,
        'signPictureRefId': signPictureRefId,
        'decorateStatus': decorateStatus
    };
    var dialogOptions = "";

    Dialog.ajaxOpenDialog(url, params, title, function (dialog) {
        FollowDetail.dialog = dialog;
    }, dialogOptions);

};

FollowDetail.close = function () {

    FollowDetail.dialog.close();
};
