/** ************************公共部分***************************** */

$(function () {
    initOrganizationEvent();
    initRegion();
    initMultiSelect();
    fileInterval();
});

/** **************************demo js*************************** */
LinkZjcbDetail = function () {
};

/**
 * 初始化查询
 */
initList = function () {

    systemLoading("#contentAll", true);

    var params = {};

    httpGetWithParams('LinkZjcbDetailListForm', 'LoadCxt', params, BASE_PATH + '/linkZjcbDetail/queryLinkZjcbDetailList', function () {

        pageInfo("LinkZjcbDetailListForm", function () {
            initList();
        });
        systemLoaded("#contentAll");
    }, "1");
};
/**
 * 查询
 *
 */
LinkZjcbDetail.search = function () {

    var flag = validSelect();
    if (!flag) {
        return false;
    }

    $('#curPage').val('1');
    initList();
};

LinkZjcbDetail.export = function () {

    var flag = validSelect();
    if (!flag) {
        return false;
    }

    var organization = $("#organization").val();
    var regionCodes = $("#region").find(".multi-select-value").val();
    var areaCityCodes = $("#areaCity").find(".multi-select-value").val();
    var cityIds = $("#city").find(".multi-select-value").val();
    var centerIds = $("#centerGroup").find(".multi-select-value").val();
    var estateNm = $("#estateNm").val();
    //var estateIds = $("#estateNm").find(".multi-select-value").val();

    var cookieName = guid();
    $("#btn-reset").attr("disabled", true);
    // showExprotLoading(cookieName);

    var url = LOC_RES_BASE_PATH + "/linkZjcbDetail/exportLinkZjcbDetailAjax?"
        + "organization=" + organization
        + "&regionCodes=" + regionCodes
        + "&areaCityCodes=" + areaCityCodes
        + "&cityIds=" + cityIds
        + "&centerIds=" + centerIds
//        + "&projectNo=" + $("#projectNo").val()
        + "&estateNm=" + estateNm
        + "&reportId=" + $("#reportId").val()
//        + "&dateStart=" + $("#dateStart").val()
//        + "&dateEnd=" + $("#dateEnd").val()
        + "&dateStage=" + $("#dateStage").val()
        + "&cookieName=" + cookieName;

    $.ajax({
        type: "GET",
        url: url,
        async: true,
        dataType: "json",
        contentType: 'application/json',
        success: function (data) {
            var dataStr = data.message;
            systemLoading('#contentAll', true, dataStr);
        },
        error: function () {
        }
    });

    window.setTimeout(function () {
        $("#btn-reset").attr("disabled", false);
    }, 10000);

};

function fileInterval() {
    var url = BASE_PATH + '/expendReport/fileIntervalByName/' + "linkZjcbDetail_filepath";

    $.ajax({
        type: "GET",
        url: url,
        async: true,
        dataType: "json",
        contentType: 'application/json',
        success: function (data) {
            var fileId = data.fileId;
            console.log("fileId:" + fileId);
            if (fileId != undefined && fileId != null && fileId != '' && fileId != 0) {
                downLoadExcel2(fileId);
            }
        },
        error: function () {
        }
    });

    setTimeout(fileInterval, 10000);

};

function downLoadExcel2(fileId) {
    console.log("fileId=" + fileId);
    var url = BASE_PATH + '/expendReport/downLoadExcel2?fileId=' + fileId;
    window.location.href = url;
    var url = BASE_PATH + '/expendReport/queryReportSize/'+"linkZjcbDetail_filepath";
    $.ajax({
        type: "GET",
        url: url,
        async : true,
        dataType:"json",
        contentType: 'application/json',
        success: function(data){
            $("#reportSize").text(data.userReportSize);
        },
        error:function(){
        }
    });
    systemLoaded('#contentAll');
};


