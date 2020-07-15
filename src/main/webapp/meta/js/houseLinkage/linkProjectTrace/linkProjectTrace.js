var projectStatusStr = "";

/** ************************公共部分***************************** */
$(function () {
    fileInterval();
});

/** **************************demo js*************************** */
ProjectTrace = function () {
};

/**
 * 初始化查询
 */
initList = function () {

    /*if(!$("#accountAgeYear").val()||!$("#accountAgeMonth").val()){
        Dialog.alertInfo("账龄年份、月份必选",true);
        return false;
    }*/

    systemLoading("#contentAll",true);

    var params = {

    };

    httpGetWithParams('LinkProjectTraceListForm', 'LoadCxt', params, BASE_PATH + '/linkProjectTrace/querylinkProjectTraceList', function () {

        pageInfo("LinkProjectTraceListForm", function () {
            initList();
        });

        //表前几列冻结
        var height = $("#divProjectTrace").height();
        FixTable("tblProjectTrace", 3, 1150, height);

        systemLoaded("#contentAll");
    },"1");
};

/**
 * 查询
 *
 */
ProjectTrace.search = function () {
    $('#curPage').val('1');

    if(!checkDate()) return;
    initList();
};

ProjectTrace.export = function () {

    /*if(!$("#accountAgeYear").val()||!$("#accountAgeMonth").val()){
        Dialog.alertInfo("账龄年份、月份必选",true);
        return false;
    }*/

    if(!checkDate()) return;

    var cityNo = $("#cityNo").val();
    var projectNo = $("#projectNo").val();
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val()

    var cookieName = guid();
    $("#btn-reset").attr("disabled",true);
    // showExprotLoading(cookieName);

    var url = LOC_RES_BASE_PATH + '/linkProjectTrace/exportLinkProjectTraceAjax?'
        + "cityNo=" + cityNo
        + "&projectNo=" + projectNo
        + "&startDate=" + startDate
        + "&endDate=" + endDate
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
    
    window.setTimeout(function(){
        $("#btn-reset").attr("disabled",false);
    },10000);
}

function checkDate() {
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();

    if(!startDate && !endDate){
        return true;
    }

    if(startDate && !endDate){
        Dialog.alertError("结束年月必填");
        return false;
    }

    if(!startDate && endDate){
        Dialog.alertError("开始年月必填");
        return false;
    }

    if(startDate == endDate){
        Dialog.alertError("结束年月应大于开始年月");
        return false;
    }

    return true;
}

function fileInterval() {
    var url = BASE_PATH + '/expendReport/fileIntervalByName/' + "linkDetail_filepath";

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
    var url = BASE_PATH + '/expendReport/queryReportSize/'+"linkDetail_filepath";
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


