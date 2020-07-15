var jsonMap = null;
$(function() {
    // 初始化查询
    ReportToValided.initList();
    initReportDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');

    var searchParamMap = CommonUtil.getCookie("REPORTTOVALIDED_LIST");
    if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
    if (!jsonMap || !jsonMap["realityCityNo"]) ReportToValided.initList();
    cityLinkageIsService(setCity, setDist);
});

setCity = function(){
    if (jsonMap && jsonMap.hasOwnProperty("realityCityNo")) $("#realityCityNo").val(jsonMap["realityCityNo"]).change();
};

setDist = function(){
    if (jsonMap && jsonMap.hasOwnProperty("districtId")) $("#districtNo").val(jsonMap["districtId"]);
    if (initCityDist) initList();
};

ReportToValided = function() {
};

/**
 * 初始化查询
 */
ReportToValided.initList = function() {

    if($("#roughAuditStatusSelect").val()){
        $("#roughAuditStatus").val($("#roughAuditStatusSelect").val());
    }else{
        $("#roughAuditStatus").val("'1','2'");
    }

    httpGet('ReportToValidedForm', 'LoadCxt', BASE_PATH + '/reportToValid/valided/q', function() {

        pageInfo("ReportToValidedForm", function() {
            ReportToValided.initList();
        });
        //表前几列冻结
        var height = $("#divReport").height();
        FixTable("tblReport", 4, 1150, height);
    });
};
/**
 * 查询
 *
 */
ReportToValided.search = function() {
    $('#curPage').val('1');
    ReportToValided.initList();
};

ReportToValided.reset = function() {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
    ReportToValided.search();
};


//填充储存的表单值
ReportToValided.setSearchParams = function (searchParamMap) {
    var jsonMap = JSON.parse(searchParamMap);

    $("#ReportToValidedForm").find("input").each(function () {
        var searchKey = $(this).attr("id");
        var searchValue = jsonMap[searchKey];
        if (searchValue != undefined && searchValue != null && searchValue != '') {
            $("#" + searchKey).val(searchValue);
        }
    });
    $("#ReportToValidedForm").find("select").each(function () {
        var searchKey = $(this).attr("id");
        var searchValue = jsonMap[searchKey];
        if (searchValue != undefined && searchValue != null && searchValue != '') {
            $("#" + searchKey).val(searchValue);
        }
    });
    //筛选
    var signDateType = jsonMap["signDateType"];
    if (signDateType != undefined && signDateType != null && signDateType != "") {
        $("#divSignType").find("a").removeClass("active");
        $("#divSignType").find("a").eq(signDateType).addClass("active");
        $("#divSignType").find("a").eq(signDateType).click();
    }
    var curPage = jsonMap["curPage"];
    if (curPage > 1) {
        $('#curPageTemp').val(curPage);
        $('#sysPageChaneTemp').val(jsonMap["sysPageChane"]);
    }
};