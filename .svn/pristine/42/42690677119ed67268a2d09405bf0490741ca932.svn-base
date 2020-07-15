/** ************************公共部分***************************** */
$(function() {

});

/** **************************demo js*************************** */
Contract = function() {
	dialog: null;
	busNo: null;
};
Contract.close = function(){
	Contract.dialog.close();
}

/*function checksubmitdata(){
	//营业执照
	if($('#fileIdBusinessBox> .item-photo-list').length && $('#fileIdBusinessBox> .item-photo-list').length>0){}
	else{
		Dialog.alertInfo("请上传营业执照后再提交！");
		return false;
	}
	//法人身份证
	if($('#fileIdCardBox> .item-photo-list').length && $('#newSigningBox> .item-photo-list').length>0){}
	else{
		Dialog.alertInfo("请上传法人身份证照片后再提交！");
		return false;
	}
	//合同照片
	if($('#fileIdPhotoBox> .item-photo-list').length && $('#fileIdPhotoBox> .item-photo-list').length>0){}
	else{
		Dialog.alertInfo("请上传合同照片后再提交！");
		return;
	}
	// 门店照片
	if($('#fileIdStoreBox> .item-photo-list').length && $('#fileIdStoreBox> .item-photo-list').length>0){}
	else{
		Dialog.alertInfo("请上传门店照片后再提交！");
		return;
	}
	//房友确认单
	if($('#fileIdInstallBox> .item-photo-list').length && $('#fileIdInstallBox> .item-photo-list').length>0){}
	else{
		Dialog.alertInfo("请上传房友确认单照片后再提交！");
		return false;
	}
	
	// 重要提示函
	if($('#fileIdNoticeBox> .item-photo-list').length && $('#fileIdNoticeBox> .item-photo-list').length>0){}
	else{
		Dialog.alertInfo("请上传《重要提示函》照片后再提交！");
		return;
	}
	
	 return true;
};*/

// 提交审核初始化方法
Contract.toAudit = function(id, isCombine) {
	
	/** 判断门店营业状态 */
	var storeNoList = [];
	$("[name='storeId']").each(function(i,n){
		storeNoList[i] = $(this).val();
	});
	var flag = true;
	//判断合作模式为授牌、授牌类型必填
	var contractTypeNameValue = $('#contractTypeNameValue').val();//合作模式
	var shoupaiTypeValue = $('#shoupaiTypeValue').val();//授牌类型
	if(contractTypeNameValue != null && contractTypeNameValue != null 
			&& contractTypeNameValue != undefined && contractTypeNameValue == '授牌'){
		if(shoupaiTypeValue == "" || shoupaiTypeValue == null 
				|| shoupaiTypeValue == undefined){
			Dialog.alertError("请选择授牌类型后再提交OA审核");
			return false;
		}
	}
	/** 传递到后台进行门店状态的判断 */
	$.ajax({
		url :BASE_PATH + '/store/q/businessStatus',
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        type:"post",
        dataType:"json",
        data: JSON.stringify(storeNoList),
		success:function(data){
			if(data != null && data != undefined && data.length > 0){
				$.each(data,function(i,n){
					if(n.businessStatus == "20902"){
						Dialog.alertError("门店"+n.name+"处于停业申请中");
						flag = false;
						return false;
					}else if(n.businessStatus == "20903"){
						Dialog.alertError("门店"+n.name+"已停业");
						flag = false;
						return false;
					}
				});
				
				if(flag){
					//check photo
					var info = "";
					var fileIdBusiness = $('#fileIdBusinessBox> .item-photo-list').length;
					var fileIdCard = $('#fileIdCardBox> .item-photo-list').length;
					var fileIdPhoto = $('#fileIdPhotoBox> .item-photo-list').length;
					var fileIdStore = $('#fileIdStoreBox> .item-photo-list').length;
					var fileIdInstall = $('#fileIdInstallBox> .item-photo-list').length;
					var fileIdNotice = $('#fileIdNoticeBox> .item-photo-list').length;
					var fileIdComplement = $('#fileIdComplementBox> .item-photo-list').length;
					var contractTypeValue = $("#contractTypeValue").val();
					if(!fileIdBusiness||!fileIdCard||!fileIdPhoto||!fileIdStore||!fileIdInstall ||!fileIdNotice){
						if(fileIdBusiness == 0){
							info += "营业执照";
						}

						if(fileIdCard == 0 && contractTypeValue !=10307){
							if(info != ""){
								info += "、法人身份证";
							}else{
								info += "法人身份证";
							}
						}

						if(fileIdPhoto == 0 ){
							if(info != ""){
								info += "、合同照片";
							}else{
								info += "合同照片";
							}
						}

//						if(fileIdStore == 0 ){
//							if(info != ""){
//								info += "、门店照片";
//							}else{
//								info += "门店照片";
//							}
//						}

						//Add By NingChao 2017/04/07 Start
						if($("#contractType").val() != "10307"){
                            if($("#originalContractDistinction").val() == "18601"){
                                //Add By NingChao 2017/04/07 End
                                if(fileIdInstall == 0){
                                    if(info != ""){
                                        info += "、房友确认单";
                                    }else{
                                        info += "房友确认单";
                                    }
                                }
                            }
                            if(fileIdNotice == 0 ){
                                if(info != ""){
                                    info += "、重要提示函照片";
                                }else{
                                    info += "重要提示函照片";
                                }
                            }
						}

						if(info != ""){
							info += "不能为空！请编辑添加保存后，再提交审核。"
							Dialog.alertError(info);
							return;
						}
					}

					// 非跨区经办
					if ("true" != isCombine) {

						Contract.audit(id);

					} else {
						var url = BASE_PATH + '/relate/oaoperate';
						var params={};
						ajaxGet(
								url,
								params,
								function(data) {
									Contract.busNo = data.returnValue;
									// 提交审核
									Contract.audit(id);
								});
						/*var url = BASE_PATH + '/relate/oaoperate';
						var title = '选择事业部区域';

						var params = {};
						var dialogOptions = {

							ok : function() {
								alert(2);
								// 确定
								var reback = Contract.initAudit(id);

								return reback;
							},
							okVal : '确定',
							cancel : true,
							cancelVal : '取消'
						};

						Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

							Contract.dialog = dialog;

						}, dialogOptions);*/

					}
				}
			}
		}
	})
	
};

// check
/*Contract.initAudit = function(id) {

	var busNo = $("#busNo").val();
	if (!busNo) {
		$("#busNo").parent().find(".fc-warning").html("请选择事业部区域");
		return false;
	} else {
		Contract.busNo = busNo;

		// 提交审核
		Contract.audit(id);
	}
};*/

/**
 * 提交审核 （一、校验门店是否被锁定； 二、未锁定，调ＯＡ提交审核； 三、审核通过，锁客、锁门店、更新状态为审核中）
 */
Contract.audit = function(id) {

	// 设置门店list
	Contract.setStoreList();

	// 一、校验改合同中关联的门店是否已被锁定
	var checkUrl = BASE_PATH + "/contract/check";
	var checkParams = {
		storeIdArray : $("#storeIdArray").val(),
		contractType : $("#contractType").val()
	};

	systemLoading("", true);

//	ajaxGet(checkUrl, checkParams, function(data) {
	if(10306==$("#contractType").val()){
		Contract.oaAuthedToLock(id);
	}else{
		// 二、未锁定，调ＯＡ提交审核；
		Contract.toOAAuth(id);

//	}, function(data) {
//		Dialog.alertError(data.returnMsg);
//		systemLoaded();
//	});
	}
};

/**
 * 三、审核通过，锁客、锁门店、更新状态为审核中
 */
Contract.oaAuthedToLock = function(id) {

	var url = BASE_PATH + '/contract/audit/' + id;
	var params = $("#contractViewForm").serialize();

	httpPut(url, params, function(data) {

		location.href = BASE_PATH + "/contract";

		// 创建房友账号
		//Contract.createFangyou();

	}, function(data) {
		Dialog.alertError(data.returnMsg);
		systemLoaded();
	});
};

/**
 * 四、创建房友账号
 */
Contract.createFangyou = function() {

	// 创建房友账号
	var createFangyouParams = {
		storeIdArray : $("#storeIdArray").val(),
		companyId : $("#companyId").val()
	};
	var createFangyouUrl = BASE_PATH + "/contract/createFangyou";

	ajaxGet(createFangyouUrl, createFangyouParams, function(data) {

	}, function(data) {
		Dialog.alertError(data.returnMsg);
		systemLoaded();
	});
};

/**
 * 提交审核,,调用OA接口
 */
Contract.toOAAuth = function(id) {

	var url = BASE_PATH + "/contracts/oa/" + id;
	
	var params = {
		busNo : Contract.busNo,
		flowId : $("#flowId").val()
		//Add by WangLei 2017/04/07 Start
		,storeId :$("#storeId").val()
	    //Add by WangLei 2017/04/07 End
		,companyId:$("#companyId").val()
		,storeIdArray:$("#storeIdArray").val()
		,contractVersion : $("#contractVersion").val()
	};

	ajaxGet(url, params, function(data) {
		location.href = BASE_PATH + "/contract";
		/*// 三、审核通过，锁客、锁门店、创建房友账号、更新状态为审核中
		Contract.oaAuthedToLock(id);*/

		
		
		
	}, function(data) {

		Dialog.alertError(data.returnMsg);
		systemLoaded();
	});

};

/**
 * 设置门店list
 */
Contract.setStoreList = function() {

	var storeIds = "";
	$('input[name="storeIds"]').each(function() {
		storeIds = storeIds + "," + $(this).val();
	});
	if (storeIds != "" && storeIds.length > 0) {
		storeIds = storeIds.substring(1, storeIds.length);
	}
	$("#storeIdArray").val(storeIds);
};

/**
 * 合同->门店信息 查看按钮
 * @param storeId
 */
function showSignHis(storeId) {
	var url = BASE_PATH + '/store/contract/q';
	var params = {
		storeId : storeId,
		popFlag : 1
	};
	var dialogOptions = {
		width:1200,
		height:"auto"
	};
	Dialog.ajaxOpenDialog(url, params, "签约历史", function(dialog, resData) {
		Contract.dialog = dialog;
	}, dialogOptions);
}

/**
 * 跳转上传补充协议
 */
Contract.toUploadAdditional = function(contractId) {
	var url = BASE_PATH + "/contract/toUploadAdditional/"+contractId;
	var dialogOptions = {
			
			width : 800,
			height : 100,
		ok : function() {
			// 确定
			return Contract.updateAdditional();
		},
		okVal : '确认',
		cancel : true,
		cancelVal : '取消'
	};
	var params = {
			contractId:contractId
	}
	Dialog.ajaxOpenDialog(url, params, "上传补充协议", function(dialog, resData) {
		Contract.dialog = dialog;
	}, dialogOptions);
};

Contract.updateAdditional = function(){
	
	systemLoading("", true);
	
	if(!handlerFileInfo()){
	    systemLoaded();
	    return false;
	}
	
	var url = BASE_PATH + '/contract/uploadAdditional/';
	var params = $("#contractAdditionalForm").serialize();
	
	ajaxGet(url, params, function(data) {
		window.location.reload();
	}, function(data) {
		Dialog.alertError(data.returnMsg);
		systemLoaded();
	});
}

function handlerFileInfo(){
    var bol = true;
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
			url:BASE_PATH+"/contract/operateChangeCt",
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
					location.href = BASE_PATH + "/contract";
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