$(function () {
    // 初始化查询
    initList();
    var searchParamMap = CommonUtil.getCookie("GPCSMEMBER_LIST_SEARCH");
	if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
});
GpCsMemberContract = function () {
    dialog: null;
};

/**
 * 初始化查询
 */
initList = function () {
    httpGet('gpCsMemberContractForm', 'LoadCxt', BASE_PATH + '/gpCsMemberContract/getGpCsMemberContractList', function () {
        pageInfo("gpCsMemberContractForm", function () {
            initList();
        });
    });
};
/**
 * 查询
 *
 */
GpCsMemberContract.search = function () {
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
 * 初始会员合同新增
 */
GpCsMemberContract.create = function () {
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
                    location.href = BASE_PATH + '/gpCsMemberContract/toAddGpCsMemberContract/' + centerId + '/' + centerName;
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
        }else {
            centerId = data.returnValue[0].exchangeCenterId;
            centerName = data.returnValue[0].exchangeCenterName;
            location.href = BASE_PATH + '/gpCsMemberContract/toAddGpCsMemberContract/' + centerId + '/' + centerName;
        }
    }, function (data) {
        Dialog.alertError(data.returnMsg);
        systemLoaded();
    });
};
Contract = function() {
	dialog: null;
};
//关闭，取消
Contract.closeSplit = function() {
	Contract.dialog.close();
};
/**
 * 初始会员合同撤销
 */
GpCsMemberContract.cancel = function (id) {
    Dialog.confirm("确定要作废？", function () {
        var url = BASE_PATH + "/gpCsMemberContract/cancel";
        var params = {id:id};
        ajaxGet(url, params, function (data) {
            location.href = BASE_PATH + "/gpCsMemberContract";
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

GpCsMemberContract.setSearchParams = function (searchParamMap) {
    var jsonMap = JSON.parse(searchParamMap);
    $("#gpCsMemberContractForm").find("input").each(function () {
        var searchKey = $(this).attr("id");
        var searchValue = jsonMap[searchKey];
        if (searchValue != undefined && searchValue != null && searchValue != '') {
            $("#" + searchKey).val(searchValue);
        }
    });
    $("#gpCsMemberContractForm").find("select").each(function () {
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

GpCsMemberContract.reset = function (pageType) {
    $("#gpCsMemberContractForm :text").val("");
    $("#gpCsMemberContractForm").find("select").val("");
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
    GpCsMemberContract.search();
};

function submitgpCsMember(id) {
	$("#submitToOA").hide();
	var url = BASE_PATH + "/gpCsMemberContract/submitGpCsMemberContractOa";
	 var params = {id:id};
	 ajaxGet(url, params, function (data) {
		 if (data && data.returnCode == '200') {
			 Dialog.alertInfoToUrl("公盘初始会员提交审核成功！", BASE_PATH + '/gpCsMemberContract/getGpCsMemberContractById/'+id,true);
			 //location.href = BASE_PATH + "/gpCsMemberContract/getGpCsMemberContractById/"+id;
		 }
	 }, function (data) {
       Dialog.alertError(data.returnMsg);
       systemLoaded();
    });
}

function operateChangeCt(contractId) {
    if(!isBlank(contractId)) {
        systemLoading("", true);
        $.ajax({
            url:BASE_PATH+"/gpCsMemberContract/operateChangeCt",
            data:$.param({
                id:contractId
            }),
            type:"post",
            success:function(data){
                data = JSON.parse(data);
                if(data && data.returnCode == '200'){
                    Dialog.alertSuccess("状态变更成功!");
                    $("#operateChangeCt").hide();
                    systemLoaded();
                    location.href = BASE_PATH + "/gpCsMemberContract";
                    //location.href = BASE_PATH + "/contract/contractId/contractStatus";
                }
            },
            error:function(){
                Dialog.alertError("状态变更失败");
                $("#operateChangeCt").hide();
                systemLoaded();
            }
        });
    }

};
