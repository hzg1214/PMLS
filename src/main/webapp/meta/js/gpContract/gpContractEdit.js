/** ************************公共部分***************************** */

$(function () {
    arraySet();
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
        "url": BASE_PATH + '/file/uploadCommonFile',
        "oaUrl": BASE_PATH + "/contracts/oa/upload",
        "isDeleteImage": false,//删除时校验checkEditImage
        "isAddImage": true,   //添加时校验checkEditImage
        "isCommonFile": true,  //公共上传文件
        "afterDelete": true //是否需要后删除图片
    };
    photoUploader(options, null, null, null);
});

GpContract = function () {
};

var fileRecordMainIds = new Array();

// 初始化数组设值
function arraySet() {
    var ary = $("#fileRecordMainIds").val().split(",");
    for (var i = 0; i < ary.length; i++) {
        fileRecordMainIds.push(ary[i]);
    }
}

/**
 * 修改
 */
GpContract.update = function () {
    var id = $("#id").val();
    var url = BASE_PATH + '/gpContract/' + id;

    var storeIds = "";
    $('input[name="storeIds"]').each(function () {
        storeIds = storeIds + "," + $(this).val();
    });
    if (storeIds != "" && storeIds.length > 0) {
        storeIds = storeIds.substring(1, storeIds.length);
    }
    if ($("#partyBcityNo").val() == "" || $("#partyBdistrictNo").val() == "") {
        Dialog.alertInfo("请选择公司地址的城市区域");
        return false;
    }
	var bankAccount = $("#bankAccount").val();
	/*if("" != bankAccount) {
		if(!/^\d+$/.test(bankAccount)){
			Dialog.alertInfo("银行帐号请输入数字!");
			return false;
		}
	}*/
    $("#storeIdArray").val(storeIds);
    if ($("#storeIdArray").val() == "") {
        Dialog.alertInfo("请关联门店信息");
        return false;
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

    if (!handlerFileInfo()) {//草签时处理文件信息
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
    /*//授权委托书
    if($('#fileProxyBox> .item-photo-list').length && $('#fileProxyBox> .item-photo-list').length>0){}
    else{
    	Dialog.alertInfo("请上传授权委托书！");
    	return;
    }*/
    if($('#fileRuleBox> .item-photo-list').length && $('#fileRuleBox> .item-photo-list').length>0){}
    else{
        Dialog.alertInfo("请上传易居房友经纪业务共享平台规则！");
        return;
    }
    var centerId = $("#selectCenterName").find("option:selected").val();
	var centerName = $("#selectCenterName").find("option:selected").text();
	$("#centerId").val(centerId);
	$("#centerName").val(centerName);
    systemLoading("", true);
    // 校验输入信息
    if (Validator.validForm("gpEditForm")) {
        httpPost('gpEditForm', url, function(data) {
            location.href = BASE_PATH + "/gpContract";
        }, function(data) {
            Dialog.alertError(data.returnMsg);
            systemLoaded();
        });
    } else {
        systemLoaded();
    }
};

function checkEditImage(files) {
    var bol = true;
    var fileSize = 0;

    for (var i = 0; i < files.length; i++) {
        fileSize = files[i].size;
        var photoExt = files[i].name.substr(files[i].name.lastIndexOf(".")).toLowerCase();// 获得文件后缀名
        if (photoExt != '.jpg' && photoExt != '.png') {
            Dialog.alertInfo("请上传后缀名为jpg、png的文件!");
            systemLoaded();
            bol = false;
            return bol;
        }

        if (fileSize > 5000 * 1024) {
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
function handlerFileInfo() {
    var bol = true;
    var fileRecordMainIds = "";
    $("input[name=fileRecordMainIdHidden]").each(function () {
        if ($(this).val() == "") {
            Dialog.alertError("图片上传出现异常，请删除重新上传。");
            bol = false;
            return false;
        }
        fileRecordMainIds += "," + $(this).val();
    });
    if (fileRecordMainIds != "") {
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
