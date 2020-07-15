/** ************************公共部分***************************** */
$(function () {

    // 初始化查询
    initList();
});

/** **************************demo js*************************** */
GpContract = function () {
    dialog: null;
};

/**
 * 初始化查询
 */
initList = function () {

    httpGet('gpContractForm', 'LoadCxt', BASE_PATH + '/gpContract/q', function () {

        pageInfo("gpContractForm", function () {
            initList();
        });
    });
};
/**
 * 查询
 *
 */
GpContract.search = function () {
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
GpContract.cancel = function (gpContractId) {
    Dialog.confirm("合同作废后，签约门店将解除签约状态，是否确定要作废？", function () {

        var url = BASE_PATH + "/gpContract/cancel/" + gpContractId;
        var params = {};

        ajaxGet(url, params, function (data) {
            location.href = BASE_PATH + "/gpContract";
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

//新增合同
GpContract.create = function () {
    /**
     * 取得用户所属中心
     * @returns
     */
    systemLoading("", true);
    var url = BASE_PATH + "/contract/center";
    ajaxGet(url, null, function (data) {
        var centerId = "";
        var centerName = "";
        if (data.returnValue.length <= 0) {
            systemLoaded();
            Dialog.alertInfo("登录用户没有所属中心不能新签合同！");
            return;
        }
        if (data.returnValue.length > 1) {
            var url = BASE_PATH + '/contract/center/list';
            var title = '选择所属中心';

            var params = {};
            var dialogOptions = {

                ok: function () {
                    centerId = "";
                    centerName = "";
                    $("#errorId").hide();
                    $("input[type=radio][name=chb_select]").each(function () {
                        if ($(this).is(':checked')) {
                            centerId = $(this).next().val();
                            centerName = $(this).parent().parent().find("td").eq(1).text().trim();
                        }
                    });
                    if (centerId == "") {
                        $("#errorId").show();
                        return false;
                    }
                    location.href = BASE_PATH + '/gpContract/c/' + centerId + '/' + centerName;
                    return true;
                },
                okVal: '确定',
                cancel: true,
                cancelVal: '取消',
                width: 550
            };

            Dialog.ajaxOpenDialog(url, params, title, function (dialog, resData) {

            	Contract.dialog = dialog;

            }, dialogOptions);
        }
        else {
            centerId = data.returnValue[0].exchangeCenterId;
            centerName = data.returnValue[0].exchangeCenterName;
            location.href = BASE_PATH + '/gpContract/c/' + centerId + '/' + centerName;
        }
    }, function (data) {
        Dialog.alertError(data.returnMsg);
        systemLoaded();
    });
};

GpContract.setSearchParams = function (searchParamMap) {
    var jsonMap = JSON.parse(searchParamMap);

    $("#gpContractForm").find("input").each(function () {
        var searchKey = $(this).attr("id");
        var searchValue = jsonMap[searchKey];
        if (searchValue != undefined && searchValue != null && searchValue != '') {
            $("#" + searchKey).val(searchValue);
        }
    });
    $("#gpContractForm").find("select").each(function () {
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

GpContract.reset = function (pageType) {
    $("#gpContractForm :text").val("");
    $("#gpContractForm").find("select").val("");
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
$(document).on('click', '.chacha', function(){
    $('.ui_state_highlight').click();
});
GpContract.getOAOpinions = function(flowId) {

    var url = BASE_PATH + "/contracts/oa/opinionsNew/" + flowId;
    var params = {};
    ajaxGet(url, params, function(data) {

        var showContent = "<span class=\"\" style=\"float:right\">"+
            "<a type=\"button\" href=\"javascript:void(0)\"  class=\"btn btn-default chacha\" style=\"float: right;margin-top: -5px;\">&times;</a>"+
            "<h4 style=\"font-size:16px\"  class=\"border-bottom pdb10\"><strong>公盘合同审批意见</strong></h4>";
        showContent += "<table class=\"table table-striped table-hover table-bordered\" border=\"1\" style=\"width:900px;\" id=\"logTable\"></table>";
        var returnValue = data.returnData;
        var html="<tr><th width=\"100px\" style=\"text-align: center\">序号</th>"+
            "<th width=\"400px\" style=\"text-align: center\">审批意见</th>"+
            "<th width=\"150px\" style=\"text-align: center\">审批人</th>"+
            "<th width=\"250px\" style=\"text-align: center\">审批日期</th>"+
            "</tr>";
        for (var i = 0; i < returnValue.length; i++) {
            html += "<tr>"+
                "<td style=\"text-align: center;border-right:0\" >"+(i+1)+"</td>"+
                "<td style=\"text-align: left\">"+returnValue[i].content+"</td>"+
                "<td style=\"text-align: center\">"+ returnValue[i].empName+"</td>"+
                "<td style=\"text-align: center\">"+returnValue[i].createDate.replace("T"," ")+"</td>"+
                "</tr>";
        }
        $.dialog({
            content : showContent,
            ok : function() {
                return;
            }
        });
        $('#ldg_lockmask').show();
        $("#logTable").html(html);
        $('.ui_state_highlight').hide();
    }, function(data) {

        var returnMsg = data.returnMsg;
        Dialog.alertError(returnMsg);
    });

};
Contract = function() {
	dialog: null;
};
//关闭，取消
Contract.closeSplit = function() {
	Contract.dialog.close();
};