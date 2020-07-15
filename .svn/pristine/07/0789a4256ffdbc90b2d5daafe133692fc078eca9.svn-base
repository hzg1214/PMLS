$(function () {
    // 初始化查询
    initList();
    var searchParamMap = CommonUtil.getCookie("GPCONTRACTSTOP_LIST_SEARCH");
	if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
});
GpContractStop = function () {
    dialog: null;
};

/**
 * 初始化查询
 */
initList = function () {
    httpGet('gpContractStopForm', 'LoadCxt', BASE_PATH + '/gpContractChange/getGpContractStopList', function () {
        pageInfo("gpContractStopForm", function () {
            initList();
        });
    });
};
/**
 * 查询
 *
 */
GpContractStop.search = function () {
    $('#curPage').val('1');
    initList();
};

function checkDate(self) {
    if ($("#dateCreateEnd").val() != ''
        && $("#dateCreateStart").val() > $("#dateCreateEnd").val()) {
        Dialog.alertError("签约日期起始日不能大于结束日！");
        $(self).val('');
    }
};

/**
 * 合同撤销
 */
GpContractStop.cancel = function (gpContractStopId) {
    Dialog.confirm("确定要作废？", function () {
        var url = BASE_PATH + "/gpContractChange/cancel";
        var params = {id:gpContractStopId};
        ajaxGet(url, params, function (data) {
            location.href = BASE_PATH + "/gpContractChange";
        }, function (data) {
            var returnMsg = data.returnMsg;
            if (!returnMsg) {
                returnMsg = "作废失败";
            }
            Dialog.alertError(returnMsg);
        });
    }, function () {

    });

};



GpContractStop.setSearchParams = function (searchParamMap) {
    var jsonMap = JSON.parse(searchParamMap);
    $("#gpContractStopForm").find("input").each(function () {
        var searchKey = $(this).attr("id");
        var searchValue = jsonMap[searchKey];
        if (searchValue != undefined && searchValue != null && searchValue != '') {
            $("#" + searchKey).val(searchValue);
        }
    });
    $("#gpContractStopForm").find("select").each(function () {
        var searchKey = $(this).attr("id");
        var searchValue = jsonMap[searchKey];
        if (searchValue != undefined && searchValue != null && searchValue != '') {
            $("#" + searchKey).val(searchValue);
        }
    });
    var curPage = jsonMap["curPage"];
    if (curPage > 1) {
        $('#curPageTemp').val(curPage);
        $('#sysPageChaneTemp').val(jsonMap["sysPageChane"]);
    }
};

GpContractStop.reset = function (pageType) {
    $("#gpContractStopForm :text").val("");
    $("#gpContractStopForm").find("select").val("");
    var url = BASE_PATH + "/commons/clearSearchParam?pageType=" + pageType;
    $.ajax({
        type: "GET",
        url: url,
        async: true,
        dataType: "json",
        success: function (data) {//成功与否都不处理
        },
        error: function () {

        }
    });
    GpContract.search();
};
/*$(document).on('click', '.chacha', function(){
    $('.ui_state_highlight').click();
});*/
function submitTr(id) {
	var url = BASE_PATH + "/gpContractChange/toOaGPStopSubmit";
	 var params = {id:id};
	 ajaxGet(url, params, function (data) {
	        location.href = BASE_PATH + "/gpContractChange";
	 }, function (data) {
       Dialog.alertError(data.returnMsg);
       systemLoaded();
    });
}
