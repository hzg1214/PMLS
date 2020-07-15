/**
 * Created by haidan on 2019/9/25.
 */
keFuWjEvaluation = function () {
};

keFuWjEvaluation.exportWj = function () {
    var year = $("#year").val();
    if (year == "" || year == "undefined" || year == null) {
        Dialog.alertError("请选择年份");
        return false;
    }
    var quarter = $("#quarter").val();
    if (quarter == "" || quarter == "undefined" || quarter == null) {
        Dialog.alertError("请选择季度");
        return false;
    }
    var acCityNo = $("#acCityNo").val();
    if (acCityNo == "" || acCityNo == "undefined" || acCityNo == null) {
        Dialog.alertError("请选择归属城市");
        return false;
    }
    var cityNo = $("#cityNo").val();
    var store = $("#store").val();
    var company = $("#company").val();
    var cookieName = guid();
    $("#btn-output").attr("disabled",true);
    systemLoading("", true);
    window.location.href = LOC_RES_BASE_PATH + '/keFuWjEvaluation/exportWjEvaluation?'
        + "year=" + year
        + "&quarter=" + quarter
        + "&acCityNo=" + acCityNo
        + "&cityNo=" + cityNo
        + "&store=" + store
        + "&company=" + company
        + "&cookieName=" + cookieName;
    systemLoaded();
    window.setTimeout(function () {
        systemLoaded();
        $('#btn-output').removeAttr("disabled");
    }, 10000);
};


keFuWjEvaluation.reset = function () {
    $("#keFuWjForm :text").val("");
    $("#keFuWjForm").find("select").val("");
    $('#acCityNo').next().find('.searchable-select-items .searchable-select-item').removeClass('selected');
    $($('#acCityNo').next().find('.searchable-select-items .searchable-select-item').get(0)).addClass('selected')
    $('#acCityNo').next().find('.searchable-select-holder').text('请选择')
    $('#cityNo').next().find('.searchable-select-items .searchable-select-item').removeClass('selected');
    $($('#cityNo').next().find('.searchable-select-items .searchable-select-item').get(0)).addClass('selected')
    $('#cityNo').next().find('.searchable-select-holder').text('请选择')

};
