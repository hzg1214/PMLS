
/** ************* 合同终止页面上传附件-变量 start js ********** */
$(function(){
	//上传开始
	var options = {
		"url":BASE_PATH + '/file/uploadCommonFile',
		//"oaUrl":BASE_PATH + "/stopcontract/oa/upload",
		"isDeleteImage":false,//删除时校验checkEditImage
		"isAddImage":true,   //添加时校验checkEditImage
		"isCommonFile":true  //公共上传文件
	};
	photoUploader(options,null,null,null);
	//上传结束
	//开始轮询
	//轮询结束
	$("#errorTip").html("&nbsp;");
	var ptBackAmount = $("#ptBackAmount").val();
	if(!(ptBackAmount == null || ptBackAmount == "" || ptBackAmount == undefined)) {
		$("#ptBackAmount").val(parseFloat(ptBackAmount).toFixed(2));
	}
});

/**
 * 新增变更--保存事件
 * 
 * @param contractId 合同ID
 * @returns {Boolean}
 */
function doSave() {
	 if(!Validator.validForm("stopGpContractForm")){
	        return false;
	 }
	var stopType = $('#stopType').val();
	if (stopType == null || stopType == "" || stopType == undefined) {
		Dialog.alertInfo("请选择【终止类型】！");
		return false;
	}
	if (this.getStoreIdArray()) {
		var stopDate = $('#stopDate').val();
		if (stopDate == null || stopDate == "" || stopDate == undefined) {
			Dialog.alertInfo("请选择【终止时间】！");
			return false;
		}
		var ptBackAmount = $('#ptBackAmount').val();
		if (ptBackAmount == null || ptBackAmount == "" || ptBackAmount == undefined) {
			Dialog.alertInfo("请选择【预计退款金额】！");
			return false;
		}
		var stopReason = $('#stopReason').val();
		if (stopReason == null || stopReason == "" || stopReason == undefined) {
			Dialog.alertInfo("请填写【终止具体原因】！");
			return false;
		}
		var stopDescribe = $('#stopDescribe').val();
		if (stopDescribe == null || stopDescribe == "" || stopDescribe == undefined) {
			Dialog.alertInfo("请填写【终止方案阐述】！");
			return false;
		}
		if (!fnCheckImage()) {
	        return false;
		}
		handlerFileInfo();
		var url = BASE_PATH + '/gpContractChange/saveGpContractChange';
		systemLoading("", true);
	    httpPost('stopGpContractForm', url, function(data) {
	        if(data.returnCode == 200){
	            Dialog.alertSuccess();
	            location.href = BASE_PATH + "/gpContractChange";
	        }else{
	            Dialog.alertError(data.returnMsg);
	            systemLoaded();
	        }
	    }, function(data) {
	        Dialog.alertError(data.returnMsg);
	        systemLoaded();
	    });
	}
}

/**
 * 获取选中的门店ID
 */
function getStoreIdArray() {
	// 门店ID
	var storeIds = "";
	// 获取所有门店
	$("input[name = storeChk]").each(function(){
		if ($(this).prop('checked')){
			var storeId =$(this).val();
			storeIds += storeId + ",";
		}
	});
	// 将最后一个逗号移除
	if (storeIds != "" && storeIds.length > 0) {
		storeIds = storeIds.substring(0, storeIds.length - 1);
	}
	// 将选中的门店ID放入数组中
	$("#storeIdArray").val(storeIds);
	if ($("#storeIdArray").val() == "") {
		Dialog.alertInfo("请选择门店！");
		return false;
	}
	return true;
}


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
   
//处理文件信息
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
function fnCheckImage() {
    if(!($('#stopContractBox> .item-photo-list').length && $('#stopContractBox> .item-photo-list').length>0)){
        Dialog.alertInfo("请上传终止合作协议书/单方解除函照片！");
        return false;
    }
    return true;
}
function setTwoNumberDecimal(o) {
    o.value = parseFloat(o.value).toFixed(2);
    console.log(o.value);
}
