/** **************************demo js*************************** */
$(function() {
	$("#errorMsg").find(".fc-warning").empty().html("");
});

GpContract = function () {
    dialog: null;
    busNo: null;
};
GpContract.close = function () {
    Contract.dialog.close();
};

// 提交审核初始化方法
GpContract.toAudit = function (id, isCombine) {
    /** 判断门店营业状态 */
    var storeNoList = [];
    $("[name='storeId']").each(function (i, n) {
        storeNoList[i] = $(this).val();
    });
    var flag = true;
    /** 传递到后台进行门店状态的判断 */
    $.ajax({
        url: BASE_PATH + '/store/q/businessStatus',
        contentType: 'application/json;charset=utf-8', //设置请求头信息
        type: "post",
        dataType: "json",
        data: JSON.stringify(storeNoList),
        success: function (data) {
            if (data != null && data != undefined && data.length > 0) {
                $.each(data, function (i, n) {
                    if (n.businessStatus == "20902") {
                        Dialog.alertError("门店" + n.name + "处于停业申请中");
                        flag = false;
                        return false;
                    } else if (n.businessStatus == "20903") {
                        Dialog.alertError("门店" + n.name + "已停业");
                        flag = false;
                        return false;
                    }
                });

                if (flag) {
                    //check photo
                    var info = "";
                    var fileBusiness = $('#fileBusinessBox> .item-photo-list').length;
                    var fileIdCard = $('#fileIdCardBox> .item-photo-list').length;
                    var fileContract = $('#fileContractBox> .item-photo-list').length;
                    var fileProxy = $('#fileProxyBox> .item-photo-list').length;

                    if (!fileBusiness || !fileIdCard || !fileContract) {
                        if (fileBusiness == 0) {
                            info += "营业执照";
                        }
                        if (fileIdCard == 0) {
                            if (info != "") {
                                info += "、法人身份证";
                            } else {
                                info += "法人身份证";
                            }
                        }
                        if (fileContract == 0) {
                            if (info != "") {
                                info += "、公盘系统服务协议";
                            } else {
                                info += "公盘系统服务协议";
                            }
                        }
                       /* if (fileProxy == 0) {
                        	if (info != "") {
                        		info += "、授权委托书";
                        	} else {
                        		info += "授权委托书";
                        	}
                        }*/
                        if (info != "") {
                            info += "不能为空！请编辑添加保存后，再提交审核。"
                            Dialog.alertError(info);
                            return;
                        }
                    }
                    // 非跨区经办
                    if ("true" != isCombine) {
                        GpContract.toOAAuth(id);
                    } else {
                        var url = BASE_PATH + '/relate/oaoperate';
                        var params = {};
                        ajaxGet( url, params, function (data) {
                                GpContract.busNo = data.returnValue;
                                // 提交审核
                                GpContract.toOAAuth(id);
                            });
                    }
                }
            }
        }
    })
};

/**
 * 提交审核,,调用OA接口
 */
GpContract.toOAAuth = function (id) {
    // 设置门店list
    GpContract.setStoreList();
    var url = BASE_PATH + "/gpContract/oa/" + id;

    var params = {
        busNo: GpContract.busNo,
        flowId: $("#flowId").val(),
        companyId: $("#companyId").val(),
        storeIdArray: $("#storeIdArray").val()
    };

    ajaxGet(url, params, function (data) {
        location.href = BASE_PATH + "/gpContract";
        /*// 三、审核通过，锁客、锁门店、创建房友账号、更新状态为审核中
         Contract.oaAuthedToLock(id);*/
    }, function (data) {
        Dialog.alertError(data.returnMsg);
        systemLoaded();
    });

};

/**
 * 设置门店list
 */
GpContract.setStoreList = function () {

    var storeIds = "";
    $('input[name="storeIds"]').each(function () {
        storeIds = storeIds + "," + $(this).val();
    });
    if (storeIds != "" && storeIds.length > 0) {
        storeIds = storeIds.substring(1, storeIds.length);
    }
    $("#storeIdArray").val(storeIds);
};

/**
 * 跳转到银行调整页面页面
 * @param id
 */
GpContract.editBankInfoMode=function(){
	var url = BASE_PATH + '/gpContract/editBankInfoMode';
	var params =$("#bankAdjustSubimtForm").serialize();
	var dialogOptions = {
			width:780
	};
	Dialog.ajaxOpenDialog(url, params, "银行信息变更", function(dialog, resData) {
		GpContract.dialog = dialog;
	}, dialogOptions);
};
/**
 * 保存银行信息变更
 */
GpContract.saveBankInfoAdjut=function(gpContractId){
	
	var partyB = $("#partyB").val();
	var oldAccountNm = $("#oldAccountNm").val();
	var accountNm = $("#accountNm").val();
	if(gpContractId==undefined || gpContractId==null || gpContractId =='') {
		return false;
	}
	if(companyId==undefined || companyId==null || companyId =='') {
		return false;
	}
	// check图片
	if(partyB == oldAccountNm && accountNm != partyB) {
		if($('#fileAccountChange> .item-photo-list').length && $('#fileAccountChange> .item-photo-list').length>0){}
		else{
			$("#errorMsg").find(".fc-warning").empty().html("请上传账户变更申请书!");
			return false;
		}
	}
	if (Validator.validForm("bankAdjustForm")) {
		var url = BASE_PATH + '/gpContract/saveBankInfoAdjut';
		var params = $("#bankAdjustForm").serialize();
		handlerFileInfo();
		httpPost("bankAdjustForm", url, function(data) {
			location.href = BASE_PATH + "/gpContract/" + gpContractId;
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});
		GpContract.dialog.close();
	};
};
GpContract.closePopup = function(){
	GpContract.dialog.close();
};
function checkEditImage(files){
    var bol = true;
    var fileSize = 0;

    for( var i = 0 ; i < files.length ; i++ ){
        fileSize = files[i].size;
        var photoExt = files[i].name.substr(files[i].name.lastIndexOf(".")).toLowerCase();// 获得文件后缀名
        if (photoExt != '.jpg' && photoExt != '.png') {
            Dialog.alertInfo("请上传后缀名为jpg、png的文件!");
            systemLoaded();
            bol = false;
            return bol;
        }

        if (fileSize > 5000*1024) {
            Dialog.alertInfo("所选文件不能大于5M！");
            self.value = null;
            systemLoaded();
            bol = false;
            return bol;
        }
    }
    return bol;
}
function handlerFileInfo(){
    var bol = true;
    //附件其它
    var fileRecordMainIds = "";
    $("input[name=fileRecordMainIdHidden]").each(function(){
        if($(this).val()==""){
            Dialog.alertError("图片上传出现异常，请删除重新上传。");
            bol = false;
            return false;
        }
        fileRecordMainIds += ","+$(this).val();
    })
    if(fileRecordMainIds!=""){
        fileRecordMainIds = fileRecordMainIds.substring(1);
    }
    $("#fileRecordMainIds").val(fileRecordMainIds);
    return bol;
}

function operateChangeCt(contractId) {
    if(!isBlank(contractId)) {
        systemLoading("", true);
        $.ajax({
            url:BASE_PATH+"/gpContract/operateChangeCt",
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
                    location.href = BASE_PATH + "/gpContract";
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