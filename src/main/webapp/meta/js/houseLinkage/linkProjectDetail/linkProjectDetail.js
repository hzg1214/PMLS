var projectStatusStr = "";

/** ************************公共部分***************************** */
$(function () {
    initOrganizationEvent();
    initRegion();
    initMultiSelect();

    cityLinkageIsService();
    searchTypeFun();
});

/** **************************demo js*************************** */
LinkDetail = function () {
};

/**
 * 初始化查询
 */
initList = function () {

    systemLoading("#contentAll",true);

    var params = {

    };

    httpGetWithParams('LinkProjectDetailListForm', 'LoadCxt', params, BASE_PATH + '/linkDetail/querylinkProjectDetailList', function () {

        pageInfo("LinkProjectDetailListForm", function () {
            initList();
        });
        systemLoaded("#contentAll");
    },"1");
};

////选择项目阶段
$('#projectStatusMulti').find('.multi-select').multiSelect({check: function($instance){

}});

/**
 * 查询
 *
 */
LinkDetail.search = function () {

    var flag = validSelect();
    if(!flag){
        return false;
    }

    $('#curPage').val('1');
    initList();
};

LinkDetail.export = function () {

    var flag = validSelect();
    if(!flag){
        return false;
    }

    var searchType = $("#searchType").val();
    var startDateProject = $("#startDateProject").val();
    var endDateProject = $("#endDateProject").val();

    var organization = $("#organization").val();
    var regionCodes = $("#region").find(".multi-select-value").val();
    var areaCityCodes = $("#areaCity").find(".multi-select-value").val();
    var cityIds = $("#city").find(".multi-select-value").val();
    var centerGroupIds = $("#centerGroup").find(".multi-select-value").val();
    var projectStatus = $("#projectStatus").find(".multi-select-value").val();
    var districtNo = $("#districtNo").val()

    var cookieName = guid();
    $("#btn-reset").attr("disabled",true);
    showExprotLoading(cookieName);

    window.location.href = LOC_RES_BASE_PATH + '/linkDetail/exportLinkProjectDetal?'
        + "organization=" + organization
        + "&regionCodes=" + regionCodes
        + "&areaCityCodes=" + areaCityCodes
        + "&cityIds=" + cityIds
        + "&centerIds=" + centerGroupIds
        + '&projectStatus=' + projectStatus
        + "&estateNm=" + $("#estateNm").val()
        + "&projectNo=" + $("#projectNo").val()
        + "&realityCityNo=" + $("#realityCityNo").val()
        + "&districtNo=" + (isBlank(districtNo) ? "" : districtNo)
        + "&startDate=" + $("#startDate").val()
        + "&endDate=" + $("#endDate").val()

        + "&searchType=" + searchType
        + "&startDateProject=" + startDateProject
        + "&endDateProject=" + endDateProject

        + "&address=" + encodeURI($("#address").val())
        + "&cookieName=" + cookieName;
    window.setTimeout(function(){
        $("#btn-reset").attr("disabled",false);
    },10000);
}



//1:项目
function searchTypeFun() {
    $(".form-group.noProject").hide();
    $(".form-group.project").show();
    $("#centerGroup").find("input[type=\"text\"]").attr("disabled", true);//禁用业绩归属中心

    $("#searchType").change(function () {
        var searchTypeVal = $("#searchType").val();
        if(searchTypeVal!='2'){
            $(".form-group.noProject").hide();
            $(".form-group.project").show();


            $("#centerGroup").find("input[type=\"text\"]").attr("disabled", true);//禁用业绩归属中心
        }else{
            $(".form-group.noProject").show();
            $(".form-group.project").hide();
            $("#centerGroup").find("input[type=\"text\"]").attr("disabled", false);//恢复业绩归属中心
        }
    });
}