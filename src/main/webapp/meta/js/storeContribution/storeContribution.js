$(function () {

    initOrganizationEvent();
    initRegion();
    initMultiSelect();

    //initList();
});

storeContribution = function () {

};


$('#stage').find('.multi-select').multiSelect({
    check: function ($instance) {
    }
});

/**
 * 验证输入时间
 */
function checkDate() {
    var startDate = new Date($("#startDate").val());
    var endDate = new Date($("#endDate").val());

    if (startDate == null || endDate == null) {
        Dialog.alertInfo('请选择日期!', true);
        return false;
    }

    return true;
}

/**
 * 初始化查询
 */
initList = function () {

    systemLoading("#contentAll", true);
    var params = {}

    httpGetWithParams('storeContributionForm', 'LoadCxt', params, BASE_PATH + '/storeContribution/q', function () {
        pageInfo("storeContributionForm", function () {
            initList();
        });
        systemLoaded("#contentAll");
    }, "1");
}


/**
 * 查询
 */
storeContribution.search = function () {
    var stage = $("#stage").find(".multi-select-value").val();
    if (isBlank(stage)) {
        systemLoaded("#contentAll");
        Dialog.alertInfo('请选择业务类型!', true);
        return false;
    }

    var flag = validSelect();
    if (!flag) {
        return false;
    }

    initList();
}

/**
 * 导出
 */
storeContribution.export = function () {

    var stage = $("#stage").find(".multi-select-value").val();
    if (isBlank(stage)) {
        Dialog.alertInfo('请选择业务类型!', true);
        return false;
    }

    var flag = validSelect();
    if (!flag) {
        return false;
    }

    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();

    var organization = $("#organization").val();
    var regionCodes = $("#region").find(".multi-select-value").val();
    var areaCityCodes = $("#areaCity").find(".multi-select-value").val();
    var cityIds = $("#city").find(".multi-select-value").val();
    var centerGroupIds = $("#centerGroup").find(".multi-select-value").val();
    var stage = $("#stage").find(".multi-select-value").val();

    var bdate = "";
    var edate = "";
    if (!isBlank(startDate)) {
        bdate = startDate;
    }
    if (!isBlank(endDate)) {
        edate = endDate;
    }

    var cookieName = guid();
    $("#btn-reset").attr("disabled", true);
    showExprotLoading(cookieName, "#contentAll");

    window.location.href = BASE_PATH + "/storeContribution/export?"
        + "&stages=" + stage
        + "&startDate=" + bdate
        + "&endDate=" + edate
        + "&organization=" + organization
        + "&regionCodes=" + regionCodes
        + "&areaCityCodes=" + areaCityCodes
        + "&cityIds=" + cityIds
        + "&centerIds=" + centerGroupIds
        + "&cookieName=" + cookieName;
    window.setTimeout(function () {
        $("#btn-reset").attr("disabled", false);
    }, 12000);
}


/**
 * 搜索保存的check
 * @param selectors = {organization : "#organization" , region : "#region",areaCity : "#areaCity",city : "#city",centerGroup : "#centerGroup",followAim : "#followAim"};
 * @returns {boolean}
 */
function validSelect(selectors) {
    if (!isBlank(selectors)) {

        if (isBlank(selectors.organization)) {
            selectors.organization = "#organization";
        }
        if (isBlank(selectors.region)) {
            selectors.region = "#region";
        }
        if (isBlank(selectors.areaCity)) {
            selectors.areaCity = "#areaCity";
        }
        if (isBlank(selectors.city)) {
            selectors.city = "#city";
        }
        if (isBlank(selectors.centerGroup)) {
            selectors.centerGroup = "#centerGroup";
        }

    } else {
        selectors = {
            organization: "#organization",
            region: "#region",
            areaCity: "#areaCity",
            city: "#city",
            centerGroup: "#centerGroup"
        };
    }
    var organization = $(selectors.organization).val();
    var regionCodes = $(selectors.region).find(".multi-select-value").val();
    var areaCitys = $(selectors.areaCity).find(".multi-select-value").val();
    var cityIds = $(selectors.city).find(".multi-select-value").val();

    if ("2017" == organization) {
        if (isBlank(cityIds)) {
            systemLoaded("#contentAll");
            Dialog.alertInfo("架构年份为2017时，所在城市必选！");
            return false;
        }
    } else if ("2018" == organization) {
        if (isBlank(regionCodes)) {
            systemLoaded("#contentAll");
            Dialog.alertInfo("架构年份为2018时，归属区域必选！");
            return false;
        }
    } else if ("2019" == organization) {
//    	if (isBlank(regionCodes)) {
        if (isBlank(areaCitys)) {
            systemLoaded("#contentAll");
//            Dialog.alertInfo("架构年份为2019时，归属区域必选！");
            Dialog.alertInfo("架构年份为2019时，归属城市必选！");
            return false;
        }
    }

    return true;
}