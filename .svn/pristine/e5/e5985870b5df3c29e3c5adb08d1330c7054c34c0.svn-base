/** ************************公共部分***************************** */
$(function() {
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

	$("#errorTip").html("").hide();

    //上传开始
    var options = {
        "url":BASE_PATH + '/file/uploadCommonFile',
        "afterDelete": true, //是否需要后删除图片
        "isDeleteImage":false,//删除时校验checkEditImage
        "isAddImage":true,   //添加时校验checkEditImage
        "isCommonFile":true  //公共上传文件
    };
    photoUploader(options,null,null,null);
    //上传结束
});

/** **************************demo js*************************** */
GpContract = function() {
};

// 定义数组
var fileRecordMainIds = new Array();

/**
 * 创建
 */
GpContract.add = function() {
	var url = BASE_PATH + '/gpContract';
	var centerId = $("#selectCenterName").find("option:selected").val();
	var centerName = $("#selectCenterName").find("option:selected").text();
	$("#centerId").val(centerId);
	$("#centerName").val(centerName);
	var partyB = $("#partyB").val();
	var legalPerson = $("#legalPerson").val();
	var partyBCityName = $("#partyBCityName").val();
	var partyBDistrictName = $("#partyBDistrictName").val();
	var partyBAddress = $("#partyBAddress").val();
	var registerId = $("#registerId").val();
	if(partyB == "" || legalPerson== "" || partyBCityName=="" || partyBDistrictName == "" || partyBAddress == "" || registerId == "") {
		Dialog.alertError("选择的经纪公司信息不全，需中心总权限在公司详情中进行补充！");
		return;
	}
	if(registerId != "" && (registerId.length !=15 && registerId.length !=18)) {
		Dialog.alertError("选择的经纪公司统一社会信用代码不准确，需中心总权限在公司详情中进行修改!");
		return;
	}
	if ($("#partyBcityNo").val() == "" || $("#partyBdistrictNo").val() == "") {
		Dialog.alertInfo("请选择公司地址的城市行政区");
		return;
	}

	// 校验联系电话是否是11位数字
	var phone = $("#partyBTel").val();
	if(phone == "") {
		Dialog.alertInfo("甲方联系方式不能为空!");
		return;
	}
	if(!checkPhoneNumber(phone))
	{
		Dialog.alertInfo("甲方联系方式格式错误!");
		return;
	}
	var bankAccount = $("#bankAccount").val();
	/*if("" != bankAccount) {
		if(!/^\d+$/.test(bankAccount)){
			Dialog.alertInfo("银行帐号请输入数字!");
			return false;
		}
	}*/
	var storeIds = "";
	$('input[name="storeIds"]').each(function() {
		storeIds = storeIds + "," + $(this).val();
	});
	
	if (storeIds != "" && storeIds.length > 0) {
		storeIds = storeIds.substring(1, storeIds.length);
	}

	$("#storeIdArray").val(storeIds);
	if ($("#storeIdArray").val() == "") {
		Dialog.alertInfo("请关联门店信息");
		return;
	};
	
	var mtcCheck = true;
	$('input[attr="mtc"]').each(function(i, o){
		var mtc = $.trim($(o).val());
		if(mtc == "") {
			mtcCheck = false;
			return false;
		}
	});
	if(!mtcCheck){
		Dialog.alertInfo("门店维护人信息不全，请先在门店中进行维护。");
		return;
	}
	
	var ctnameCheck = true;
	$('input[attr="ctname"]').each(function(i, o){
		var contactName = $.trim($(o).val());
		if(contactName == "") {
			ctnameCheck = false;
			return false;
		}
	});
	if(!ctnameCheck){
		Dialog.alertInfo("门店负责人不能为空！");
		return;
	}
	
	var ctphoneNullCheck = true;
	var ctphoneVeryCheck = true;
	$('input[attr="ctphone"]').each(function(i, o){
		var phone = $.trim($(o).val());
		if(phone == "") {
			ctphoneNullCheck = false;
			return false;
		}
		if(!checkPhoneNumber(phone))
		{
			ctphoneVeryCheck = false;
			return false;
		}
	});
	if(!ctphoneNullCheck){
		Dialog.alertInfo("门店负责人电话不能为空!");
		return;
	}
	if(!ctphoneVeryCheck){
		Dialog.alertInfo("门店负责人联系电话格式错误!");
		return;
	}

	/*var gradeCheck = true;
	$(".storetype").each(function(i, o){
		var gradeName = $.trim($(o).val());
		if(gradeName == "") {
			gradeCheck = false;
			return false;
		}else if(gradeName == 19902){
			var typeB = $(o).parent().find('input[attr="storetypeBlst"]').val();
			if(typeB == ""){
				gradeCheck = false;
				return false;
			}
		}
	});

	if(!gradeCheck){
		Dialog.alertInfo("门店资质等级不能为空!");
		return;
	}*/

	if(!handlerFileInfo()){//草签时处理文件信息
		return;
	}
	
	// check图片
	if($('#fileBusinessBox> .item-photo-list').length && $('#fileBusinessBox> .item-photo-list').length>0){}
	else{
		Dialog.alertInfo("请上传营业执照图片！");
		return;
	}
    if($('#fileIdCardBox> .item-photo-list').length && $('#fileIdCardBox> .item-photo-list').length>0){}
    else{
        Dialog.alertInfo("请上传身份证图片！");
        return;
    }
    if($('#fileContractBox> .item-photo-list').length && $('#fileContractBox> .item-photo-list').length>0){}
    else{
        Dialog.alertInfo("请上传公盘系统服务协议！");
        return;
    }
    /*if($('#fileProxyBox> .item-photo-list').length && $('#fileProxyBox> .item-photo-list').length>0){}
    else{
    	Dialog.alertInfo("请上传委托书！");
    	return;
    }*/
    if($('#fileRuleBox> .item-photo-list').length && $('#fileRuleBox> .item-photo-list').length>0){}
    else{
    	Dialog.alertInfo("请上传易居房友经纪业务共享平台规则！");
    	return;
    }
	systemLoading("", true);

	// 校验输入信息
	if (Validator.validForm("gpAddForm")) {
        // 新增合同
        httpPost('gpAddForm', url, function(data) {
            location.href = BASE_PATH + "/gpContract";
        }, function(data) {
            Dialog.alertError(data.returnMsg);
            systemLoaded();
        });
	} else {
		systemLoaded();
	}

};

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
   
//草签时处理文件信息
function handlerFileInfo(){
	var bol = true;
	//营业证,身份证,合同照片,门店照片,房友系统申请安装单,其它
	var fileRecordMainIds = "";
	$("input[name=fileRecordMainIdHidden]").each(function(){
		if($(this).val()==""){
			Dialog.alertError("图片上传出现异常，请删除重新上传。");
			bol = false;
			return false;
		}
		fileRecordMainIds += ","+$(this).val();
	});
	if(fileRecordMainIds!=""){
		fileRecordMainIds = fileRecordMainIds.substring(1);
	}
	$("#fileRecordMainIds").val(fileRecordMainIds);
	
	return bol;
}

function storetypeChange(data)
{
	var name = data.name;
	var selectdata = data.options[data.selectedIndex].value;
	var btypename = name.replace("storetype", "bTypenamelst");
	var btype = name.replace("storetype", "storetypeBlst");
	if(selectdata == 19902)
	{
		$("#"+btypename).show();
		$("#"+btypename).attr("value","乙3");
		$("#"+btype).val(20003);
	}
	else
	{
		$("#"+btypename).hide();
		$("#"+btypename).attr("value",'');
		$("#"+btype).val('');
	}
}
   
   
   
   
   
   
   