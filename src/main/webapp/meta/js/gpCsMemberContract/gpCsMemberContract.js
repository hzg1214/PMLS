var flag = 0;
$(function(){
	$(".lockHandle2").removeAttr("style");
	$("#accountProvinceNo").change(function () {
		$("#accountProvinceNm").val($("#accountProvinceNo option:selected").html());
		var url = BASE_PATH +  "/cityCascade/city";
		var params = {provinceNo:$("#accountProvinceNo").val()};
		ajaxGet(url, params, function(data) {
			var result = "<option value=''>请选择城市</option>";
			$.each(data.returnValue, function(n, value) {
				result += "<option value='" + value.cityNo + "'>"
					+ value.cityName + "</option>";
			});
			$("#accountCityNo").html('');
			$("#accountCityNo").append(result);
		}, function() {
		});
	});
	$("#accountCityNo").change(function () {
		$("#accountCityNm").val($("#accountCityNo option:selected").html());
	});

	$("#ourFullId").change(function () {
		$("#ourFullName").val($("#ourFullId option:selected").html());
	});

	//$("#errorTip").html("").hide();
	
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
	/*var ptBackAmount = $("#ptBackAmount").val();
	if(!(ptBackAmount == null || ptBackAmount == "" || ptBackAmount == undefined)) {
		$("#ptBackAmount").val(parseFloat(ptBackAmount).toFixed(2));
	}*/
	$("#csMemberFileBox1").show();
	$("#fileIdCardBox1").show();
	$("#fileContractBox1").show();
	$("#csMemberFileBox2").hide();
	$("#fileIdCardBox2").hide();
	$("#fileContractBox2").hide();
});

/**
 * 新增变更--保存事件
 * 
 * @param contractId 合同ID
 * @returns {Boolean}
 */
function doSave() {
	
	if(!Validator.validForm("gpCsMemberContractAddForm")){
	        return false;
	}
	var partyB = $('#partyB').val();
	if (partyB == null || partyB == "" || partyB == undefined) {
		Dialog.alertInfo("请选择公司！");
		return false;
	}
	var centerId = $("#selectCenterName").find("option:selected").val();
	var centerName = $("#selectCenterName").find("option:selected").text();
	$("#centerId").val(centerId);
	$("#centerName").val(centerName);
	
	$("#ourFullName").val($("#ourFullId option:selected").html());
	$("#accountProvinceNm").val($("#accountProvinceNo option:selected").html());
	$("#accountCityNm").val($("#accountCityNo option:selected").html());
	
	if (!fnCheckImage()) {
		return false;
	}
	handlerFileInfo();
	var url = BASE_PATH + '/gpCsMemberContract/saveGpCsMemberContract';
	systemLoading("", true);
	httpPost('gpCsMemberContractAddForm', url, function(data) {
		if(data.returnCode == 200){
			Dialog.alertSuccess();
			location.href = BASE_PATH + "/gpCsMemberContract";
		}else{
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		}
	}, function(data) {
		Dialog.alertError(data.returnMsg);
		systemLoaded();
	});
}


function checkEditImage(files){
	var bol = true;
	var fileSize = 0;
	if ($("#companyId").val() == "") {
		Dialog.alertInfo("请先选择甲方公司后，再上传附件！");
		return false;
	}
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
    if(!($('#csMemberFileBox> .item-photo-list').length && $('#csMemberFileBox> .item-photo-list').length>0)){
        Dialog.alertInfo("请上传初始会员协议照片！");
        return false;
    }
    if(!($('#csMemberPayBox> .item-photo-list').length && $('#csMemberPayBox> .item-photo-list').length>0)){
    	Dialog.alertInfo("请上传初始会员费付款凭证照片！");
    	return false;
    }
    return true;
}
function setTwoNumberDecimal(o) {
    o.value = parseFloat(o.value).toFixed(2);
    console.log(o.value);
}

