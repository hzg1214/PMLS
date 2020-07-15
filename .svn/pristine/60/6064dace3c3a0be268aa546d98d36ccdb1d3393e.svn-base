/** ************************公共部分***************************** */

$(function () {

    initOrganizationEvent();
    initRegion();
    initMultiSelect();

    $("#sumBy").change(
        function () {
            var sumBy = $("#sumBy").val();
            if (sumBy == 'store') {
                $('#centerId').find('.multi-select-text').attr('disabled',false);
                $("#sortBy").html('');
                var result = '<option value="netSignEndDate" selected>网签完成</option><option value="roughAmount">大定金额</option>';
                $("#sortBy").append(result);
            } else if (sumBy == 'city') {
                $("#sortBy").html('');
                var result = '<option value="flopPassDate" selected>翻牌验收</option><option value="netSignEndDate">网签完成</option><option value="roughAmount">大定金额</option>';
                $("#sortBy").append(result);
                centerIdStr = '';
                $('#centerId').find('input[type="checkbox"]').removeAttr("checked");
                $('#centerId').find('.multi-select-value').val('');
                $('#centerId').find('.multi-select-text').val('');
                $('#centerId').find('.multi-select-text').attr('disabled',true);
            } else {
                $('#centerId').find('.multi-select-text').attr('disabled',false);
                $("#sortBy").html('');
                var result = '<option value="flopPassDate" selected>翻牌验收</option><option value="netSignEndDate">网签完成</option><option value="roughAmount">大定金额</option>';
                $("#sortBy").append(result);
            }
        }
    );

});

/** **************************demo js*************************** */
PerformanceQuery = function () {
    dialog: null;
};

/**
 * 初始化查询
 */
initList = function () {

    systemLoading("#contentAll",true);

    var params = {

    };

    httpGetWithParams('performanceQueryForm', 'LoadCxt', params, BASE_PATH + '/performanceQuery/query', function () {

        pageInfo("performanceQueryForm", function () {
            initList();
        });
        systemLoaded("#contentAll");
    },"1");
};
/**
 * 查询
 *
 */
PerformanceQuery.search = function () {

    var flag = validSelect();
    if(!flag){
        return false;
    }

    $('#curPage').val('1');
    initList();
};

PerformanceQuery.export = function () {

    var sumBy = $('#sumBy').val();
    var sortBy = $('#sortBy').val();
    var startDate = $('#startDate').val();
    var endDate = $('#endDate').val();
    var storeNo = $('#storeNo').val();
    var storeAddress = $('#storeAddress').val();

    var organization = $("#organization").val();
    var regionCodes = $("#region").find(".multi-select-value").val();
    var areaCityCodes = $("#areaCity").find(".multi-select-value").val();
    var cityIds = $("#city").find(".multi-select-value").val();
    var centerGroupIds = $("#centerGroup").find(".multi-select-value").val();


    var cookieName = guid();
    $("#btn-reset").attr("disabled",true);
    showExprotLoading(cookieName);

    window.location.href = LOC_RES_BASE_PATH + '/performanceQuery/export?'
        + 'startDate=' + startDate
        + '&endDate=' + endDate
        + "&organization=" + organization
        + "&regionCodes=" + regionCodes
        + "&areaCityCodes=" + areaCityCodes
        + "&cityIds=" + cityIds
        + "&centerIds=" + centerGroupIds
        + '&sumBy=' + sumBy
        + '&sortBy=' + sortBy
        + '&storeNo=' + storeNo
        + '&storeAddress=' + storeAddress
        + "&cookieName=" + cookieName;
    window.setTimeout(function(){
        $("#btn-reset").attr("disabled",false);
    },10000);
};

function checkDate(self) {
    if (!isBlank($("#startDate").val()) && !isBlank($("#endDate").val())) {
        if ($("#startDate").val() > $("#endDate").val()) {
            Dialog.alertError("起始日不能大于结束日！");
            $(self).val('');
        }
    }
    if (!isBlank($("#endDate").val())) {
        if (new Date() < new Date(Date.parse($("#endDate").val()))) {
            Dialog.alertError("结束日不能大于今天！");
            $("#endDate").val('');
        }
    }
    if (!isBlank($("#startDate").val())) {
        if (new Date() < new Date(Date.parse($("#startDate").val()))) {
            Dialog.alertError("开始日不能大于今天！");
            $("#startDate").val('');
        }
    }
}