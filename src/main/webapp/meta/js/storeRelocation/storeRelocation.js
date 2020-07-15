var saveFlag = true;
$(function(){
	$("#warningMsg").html("");
    //上传开始
    var options = {
        "url":BASE_PATH + '/file/uploadCommonFile',
        //"oaUrl":BASE_PATH + "/stopcontract/oa/upload",
        "isDeleteImage":false,//删除时校验checkEditImage
        "isAddImage":true,   //添加时校验checkEditImage
        "afterDelete":true,
        "isCommonFile":true  //公共上传文件
    };
    photoUploader(options,null,null,null);
});

//对上传的图片进行限制
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

//门店资质等级选择
function fnChangeAbTypeStore(obj){
    var abTypeStore = $(obj).val();
    if(abTypeStore == '19901'){
        $("#bTypeStore").hide();
    }else{
        $("#bTypeStore").show();
    }
}

//保存
function fnSave() {
    if(!Validator.validForm("storeRelocationForm")){
        return false;
    }
    if(!saveFlag){
    	Dialog.alertInfo("该门店地址已存在，请重新录入！");
    	return false;
    }
    if (!fnCheckImage()) {
        return false;
    }
    handlerFileInfo();
    var url = BASE_PATH + '/storeRelocation/saveStoreRelocation';
    systemLoading("", true);
    httpPost('storeRelocationForm', url, function(data) {
        if(data.returnCode == 200){
            Dialog.alertSuccess();
            location.href = BASE_PATH + "/contractChangeNew/list/"+$("#contractId").val()+"/"+$("#contractStatus").val();
        }else{
            Dialog.alertError(data.returnMsg);
            systemLoaded();
        }
    }, function(data) {
        Dialog.alertError(data.returnMsg);
        systemLoaded();
    });
}

function fnCheckImage() {
    if(!($('#fileComplement> .item-photo-list').length && $('#fileComplement> .item-photo-list').length>0)){
        Dialog.alertInfo("请上传补充协议！");
        return false;
    }
    if(!($('#fileFyConfirmation> .item-photo-list').length && $('#fileFyConfirmation> .item-photo-list').length>0)){
        Dialog.alertInfo("请上传房友确认单！");
        return false;
    }
    if(!($('#fileStorePhoto> .item-photo-list').length && $('#fileStorePhoto> .item-photo-list').length>0)){
        Dialog.alertInfo("请上传门店照片！");
        return false;
    }
    return true;
}
function checkAddress(){
	$("#warningMsg").html("");
	var flag = false;
	var checkAddress = $("#storeAddress").val();
	var contractStopId = $("#id").val();
	var storeId = $("#storeId").val();
	var storeCity = $("#storeCity").val();
	var storeDistrict  = $("#storeDistrict").find("option:selected").val();
	var checkUrl = BASE_PATH + '/storeRelocation/checkStoreAddress';
	if(checkAddress != undefined && checkAddress !=null && checkAddress !='') {
		$.ajax({
			type: "GET",
			url: checkUrl,
			async : false,
			dataType:"json",
			data:$.param({
				storeId:storeId,
				contractStopId:contractStopId,
				storeCity : storeCity,
				storeDistrict : storeDistrict,
				storeAdresss:checkAddress
			}),
			contentType: 'application/json',
			success: function(data){
				if(data.returnCode=='200'){
					var returnData = data.returnData;
					if(returnData == "0"){//为2不能通过
						flag = true;
					}else {
						flag = false;
					}
				}
			}
		});
		if(!flag){
			$("#warningMsg").html("");
			$("#warningMsg").text(" 该门店地址已存在，请重新录入！");
			saveFlag = false;
			return false;
		}else{
			saveFlag = true;
		}
	}
}