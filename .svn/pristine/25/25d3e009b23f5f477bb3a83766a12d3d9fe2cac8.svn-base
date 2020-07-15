$(function () {
    initOrganizationEvent();
    initRegion();
    initMultiSelect();
    LinkConversionRate.serachKeyChange();
});

LinkConversionRate = function () {
};
var qArr = ['1', '2', '3', '4', '1;2', '1;2;3', '1;2;3;4', '2;3', '2;3;4', '3;4'];
//选择季度
$('#quarterGroup').find('.multi-select').multiSelect({
    check: function ($instance) {
        var quarters = $instance.value.toString();
        if (isBlank(quarters)) {
            return;
        }
        var str = quarters.split(',');
        str.sort();
        quarters = str.join(';');
        if (qArr.indexOf(quarters) < 0) {
            Dialog.alertInfo("请选择连续的季度！");
            return false;
        }
    }
});

/**
 * 初始化列表
 */
LinkConversionRate.initList = function () {
    systemLoading("#contentAll", true);
    var params = {};
    httpGetWithParams('searchForm', 'LoadCxt', params, BASE_PATH + '/linkConversionRate/queryList', function () {

        pageInfo("searchForm", function () {
            LinkConversionRate.initList();
        });
        systemLoaded("#contentAll");
    }, "1");
};

/**
 * 查询列表
 */
LinkConversionRate.search = function () {
    if (!validSelect()) {
        return;
    }
    var serachKey = $("#serachKey").val();
    if (serachKey == 'q') {
        var quarters = $("#quarter").find(".multi-select-value").val();
        if (isBlank(quarters)) {
            Dialog.alertInfo("请选择季度！");
            return false;
        }
        var str = quarters.split(';');
        str.sort();
        quarters = str.join(';');
        if (qArr.indexOf(quarters) < 0) {
            Dialog.alertInfo("请选择连续的季度！");
            return false;
        }
        //$("#quarter").find(".multi-select-value").val(quarters);
    }

    $('#curPage').val('1');
    LinkConversionRate.initList();
};

LinkConversionRate.export = function () {
    if (!validSelect()) {
        return;
    }

    var organization = $("#organization").val();
    var serachKey = $("#serachKey").val();
    var yearly = $("#yearly").val();
    var quarters = $("#quarter").find(".multi-select-value").val();
    var regionCodes = $("#region").find(".multi-select-value").val();
    var areaCityCodes = $("#areaCity").find(".multi-select-value").val();
    var cityIds = $("#city").find(".multi-select-value").val();
    var centerGroupIds = $("#centerGroup").find(".multi-select-value").val();
    var estateNmKey = $("#estateNmKey").val();

    if (serachKey == 'q') {
        if (isBlank(quarters)) {
            Dialog.alertInfo("请选择季度！");
            return false;
        }
        var str = quarters.split(';');
        str.sort();
        quarters = str.join(';');
        if (qArr.indexOf(quarters) < 0) {
            Dialog.alertInfo("请选择连续的季度！");
            return false;
        }
    }

    systemLoading("", true);
    $("#btn-output").attr("disabled", true);
    var cookieName = guid();
    window.location.href = BASE_PATH + '/linkConversionRate/export?'
        + "organization=" + organization
        + "&serachKey=" + serachKey
        + "&yearly=" + yearly
        + "&quarter=" + quarters
        + "&regionCodes=" + regionCodes
        + "&areaCityCodes=" + areaCityCodes
        + "&cityIds=" + cityIds
        + "&centerIds=" + centerGroupIds
        + "&estateNmKey=" + estateNmKey
        + "&cookieName=" + cookieName;
    systemLoaded();
    $('#btn-output').removeAttr("disabled");
};

LinkConversionRate.serachKeyChange = function () {
    $("#serachKey").change(function () {
        var searchKey = $(this).val();
        if (searchKey == 'y') {
            $("#yearly").show();
            $("#yearly").attr("style", "width:150px;");
            $("#quarter").hide();
        } else if (searchKey == 'q') {
            $("#yearly").show();
            $("#yearly").attr("style", "width:80px");
            $("#quarter").show();
            $("#quarter").attr("style", "width:66px");
        }
    });
};


/**
 * 归属城市修改清空下拉框
 * @returns
 */
function changeValue(obj) {
    //所在城市
    clearMultiSelectM('city');
    //项目名称
    clearSelectM('estateNmKey');
}

/**
 * 所在城市修改清空下拉框
 * @returns
 */
function changeCity(obj) {
	//项目名称
	clearSelectM('estateNmKey');
}


/**
 * 清空下拉框
 *
 * @param selMen
 * @returns
 */
function clearMultiSelectM(selMen) {
    $("#" + selMen).find(".multi-select-item").not(':first').remove();
    $("#" + selMen).find('.multi-select-checkall').removeAttr("checked");
    $("#" + selMen).find('.multi-select-value').val('');
    $("#" + selMen).find('.multi-select-text').val('');
    $("#" + selMen).find('.multi-select-text').text('');
}

/**
 * 清空select下拉框
 * @param selMen
 * @returns
 */
function clearSelectM(selMen) {
    $('select[multiple="multiple"]').multiselect('clearSelection');
    $('select[multiple="multiple"]').multiselect('refresh');
    $("#"+selMen).val('');
}