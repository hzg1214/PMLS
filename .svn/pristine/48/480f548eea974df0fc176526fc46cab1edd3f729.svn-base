/*
 * 初始化查询页面
 * */
$(function () {

    initOrganizationEvent();
    initRegion();
    initMultiSelect();
    initDate();
    $("#stage").find('.multi-select-item').find(":checkbox").each(function () {
        if (this.value == "initialDate")
            this.click();
    });
});






Dialog.alertSuccess = function () {
    dialog: null;
}

/**
 * 初始化查询
 */
initList = function () {

    systemLoading("#contentAll", true);

    var params = {
    }

    httpGetWithParams('preservedetail', 'LoadCxt', params, BASE_PATH + '/storePreserve/queryStorePreserveSummary', function () {

        pageInfo("preservedetail", function () {
            initList();
        });
        systemLoaded("#contentAll");
    }, "1");
};

//查询门店维护明细
function searchStorePreserve() {
   var quarterText = $("#quarterText").val();
   var regionCodes = $("input[name=regionCodes]").val();
//    if (isBlank(regionCodes)) {
//        systemLoaded("#contentAll");
//        Dialog.alertInfo('请选择区域!', true);
//        return false;
//    }
   var flag = validSelect();
   if(!flag){
       return false;
   }
    if (isBlank(quarterText)) {
        systemLoaded("#contentAll");
        Dialog.alertInfo('请选择查询的季度!', true);
        return false;
    }
    initList();
}

function showExportDiv(data) {
    Dialog.alertInfo("您导出文件名是：" + data.filePath + "，请到导出文件列表功能中下载!", true);
}

function exportPreserveDetail() {
    var regionCodes = $("input[name=regionCodes]").val();
    var organization = $("#organization").val(); //架构年份
    var regionCodes = $("#region").find(".multi-select-value").val(); //归属区域
    var areaCityCodes = $("#areaCity").find(".multi-select-value").val(); //归属城市
    var cityIds = $("#city").find(".multi-select-value").val(); //归属城市
    var centerGroupIds = $("#centerGroup").find(".multi-select-value").val();//归属中心
    var year = $("#year").val(); //查询年份
    var shopState = $("#shopState").val(); //门店状态
    var isPerson = $("#isPerson").val();  //是否导出到个人
    var personal = $("#personal").val();
    var quarterIds = $("#quarterIds").val();
//    if (isBlank(regionCodes)) {
//        systemLoaded("#contentAll");
//        Dialog.alertInfo('请选择区域!', true);
//        return false;
//    }
    var flag = validSelect();
    if(!flag){
        return false;
    }
    if (isBlank(year)) {
        systemLoaded("#year");
        Dialog.alertInfo('请选择年份!', true);
        return false;
    }
    if (isBlank(quarterIds)) {
        systemLoaded("#quarterIds");
        Dialog.alertInfo('请选择季度!', true);
        return false;
    }
    var cookieName = guid();
    // $("#btn-reset").attr("disabled",true);
    showExprotLoading(cookieName, "#contentAll");

    window.location.href = BASE_PATH + "/storePreserve/exportSummary?"
        + "&organization=" + organization
        + "&regionCodes=" + regionCodes
        + "&areaCityCodes=" + areaCityCodes
        + "&cityIds=" + cityIds
        + "&centerIds=" + centerGroupIds
        + "&year=" + year
        + "&isPerson=" + isPerson
        + "&shopState=" + shopState
        + "&personal=" + personal
        + "&quarterIds=" + quarterIds
        + "&cookieName=" + cookieName;
    // window.setTimeout(function(){
    //     $("#btn-reset").attr("disabled",false);
    // },12000);
}
/**
 *
 */
function initDate() {
    var date = new Date;
    var str = "";
    var year = date.getFullYear();
//    for (let i = 2018; i < year + 1 ; i++) {
    for (let i = year ; i > 2017 ; i--) {
        str += '<option value="' + i + '">'+ i + '</option>'
    }
    $("#year").empty();
    $("#year").append(str);
    appendQuarter($("#year").val());
}
function monitorYear(obj) {
    console.log(obj.value);
    appendQuarter(obj.value);
}

function appendQuarter(obj) {
    var date = new Date;
    var month = date.getMonth() + 1;
    var qt = Math.ceil(month/3);
    var str = "";
    var quarter = ["一季度","二季度","三季度","四季度"];
    var Q = ["Q1","Q2","Q3","Q4"];
    $("#quarterSelect").empty();
    $("#quarterIds").attr("value",'');
    $("#quarterText").val("");
    if (obj == '2018'){
        str += ' <li class="multi-select-item">';
        str += '    <label><input type="checkbox" value="Q4" data-text="四季度"><span>四季度</span></label>';
        str += ' </li>';
    }else{
        str += ' <li class="multi-select-item">';
        str += '     <label><input type="checkbox" class="multi-select-checkall" ><span>全部</span></label>';
        str += ' </li>';
        for (let i = 0; i < qt; i++) {
            str += ' <li class="multi-select-item">';
            str += '    <label><input type="checkbox" value='+Q[i]+' data-text='+quarter[i]+'><span>'+quarter[i]+'</span></label>';
            str += ' </li>';
        }
    }
    $("#quarterSelect").append(str);
}
//查询季度
$('#selectQuarter').find('.multi-select').multiSelect({
    check: function ($instance) {
    }
});
//门店筛选
$('#selectShop').find('.multi-select').multiSelect({
    check: function ($instance) {
    }
});
