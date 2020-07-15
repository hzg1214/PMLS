/*
 * 初始化查询页面
 * */
$(function () {

    initOrganizationEvent();
    initRegion();
    initMultiSelect();

    $("#stage").find('.multi-select-item').find(":checkbox").each(function () {
        if (this.value == "initialDate")
            this.click();
    });
    fileInterval();
});


//处理业务阶段多选
$('#stage').find('.multi-select').multiSelect({
    check: function ($instance) {
    }
});

/**
 * 验证输入时间是否超过30天
 */
function checkDate() {
    var startDate = new Date($("#startDate").val());
    var endDate = new Date($("#endDate").val());
    var initDate = new Date("2017-03-01");

    if (startDate == null || endDate == null) {
        Dialog.alertInfo('请选择日期!', true);
        return false;
    }

    var date = Math.abs(parseInt((startDate - endDate) / 1000 / 3600 / 24));
    if (date > 30) {
        // alert("时间超多30天");
    }

    return true;
}

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

    httpGetWithParams('expanddetail', 'LoadCxt', params, BASE_PATH + '/expendReport/query', function () {

        pageInfo("expanddetail", function () {
            initList();
        });
        systemLoaded("#contentAll");
    }, "1");
};

//查询拓展明细
function searchExpandDetail() {

    var stage = $("#stage").find(".multi-select-value").val();
    if (isBlank(stage)) {
        systemLoaded("#contentAll");
        Dialog.alertInfo('请选择业务阶段!', true);
        return false;
    }

    var flag = validSelect();
    if (!flag) {
        return false;
    }

    $('#curPage').val('1');
    initList();
}

function showExportDiv(data) {
    Dialog.alertInfo("您导出文件名是：" + data.filePath + "，请到导出文件列表功能中下载!", true);
}

function exportExpandDetail() {

    var stage = $("#stage").find(".multi-select-value").val();
    if (isBlank(stage)) {
        Dialog.alertInfo('请选择业务阶段!', true);
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
        bdate = formatDate(new Date(startDate));
    }
    if (!isBlank(endDate)) {
        edate = formatDate(new Date(endDate));
    }

    var cookieName = guid();
    $("#btn-reset").attr("disabled",true);
    /*showExprotLoading(cookieName, "#contentAll");*/

    var url = BASE_PATH + "/expendReport/exportAjax?"
        + "&stages=" + stage
        + "&startDate=" + bdate
        + "&endDate=" + edate
        + "&contractNo=" + $("#contractNo").val()
        + "&contractTypes=" + $("#contractTypes").val()
        + "&businessPlaceTypes=" + $("#businessPlaceTypes").val()
        + "&storeSignStatuses=" + $("#storeSignStatuses").val()
        + "&storeSizeScales=" + $("#storeSizeScales").val()
        + "&storeTypes=" + $("#storeTypes").val()
        + "&storeNo=" + $("#storeNo").val()
        + "&storeCityName=" + $("#storeCityName").val()
        + "&storeAddress=" + $("#storeAddress").val()
        + "&company=" + $("#company").val()
        + "&organization=" + organization
        + "&regionCodes=" + regionCodes
        + "&areaCityCodes=" + areaCityCodes
        + "&cityIds=" + cityIds
        + "&centerIds=" + centerGroupIds
        + "&maintainer=" + encodeURI($("#maintainer").val())
        + "&cookieName=" + cookieName;

    $.ajax({
        type: "GET",
        url: url,
        async : true,
        dataType:"json",
        contentType: 'application/json',
        success: function(data){
            var dataStr = data.message;
            systemLoading('#contentAll',true,dataStr);
        },
        error:function(){
        }
    });
    window.setTimeout(function(){
        $("#btn-reset").attr("disabled",false);
    },12000);
}


function fileInterval(){
    var url = BASE_PATH + '/expendReport/fileIntervalByName/'+"exReport_filepath";

    $.ajax({
        type: "GET",
        url: url,
        async : true,
        dataType:"json",
        contentType: 'application/json',
        success: function(data){
            var fileId = data.fileId;
            console.log("fileId:"+fileId);
            if(fileId!=undefined && fileId!=null && fileId!='' && fileId!=0){
                downLoadExcel2(fileId);
            }
        },
        error:function(){
        }
    });

    setTimeout(fileInterval,10000);

};

function downLoadExcel2(fileId){
    console.log("fileId="+fileId);
    var url = BASE_PATH + '/expendReport/downLoadExcel2?fileId='+fileId;
    window.location.href = url;
    var url = BASE_PATH + '/expendReport/queryReportSize/'+"exReport_filepath";
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

////选择合同类型
$('#contractType').find('.multi-select').multiSelect({
    check: function ($instance) {

    }
});
////选择经营场所类型
$('#businessPlaceType').find('.multi-select').multiSelect({
    check: function ($instance) {

    }
});

////选择签约状态
$('#storeSignStatus').find('.multi-select').multiSelect({
    check: function ($instance) {

    }
});

////选择门店规模
$('#storeSizeScale').find('.multi-select').multiSelect({
    check: function ($instance) {

    }
});

//选择门店类型
$('#storeType').find('.multi-select').multiSelect({
    check: function ($instance) {

    }
});

function toContractDetail(id, status) {
    var dialogOptions = {
        cancel: false
    };

    Dialog.ajaxOpenDialog(BASE_PATH + "/contract/contractDetailPop/" + id + "/" + status, {}, "合同详情", function (dialog, resData) {
        dialog.position('50%', '50%');
        Dialog.dialog = dialog;
    }, dialogOptions);
}

function toContractDetailPre(contractNo) {

    $.getJSON(BASE_PATH + "/contract/contractDetailPopPre/" + contractNo, function (data) {
        var dialogOptions = {
            cancel: false
        };

        Dialog.ajaxOpenDialog(BASE_PATH + "/contract/contractDetailPop/" + data.returnValue.id + "/" + data.returnValue.contractStatus, {}, "合同详情", function (dialog, resData) {
            dialog.position('50%', '50%');
            Dialog.dialog = dialog;
        }, dialogOptions);
    });
}