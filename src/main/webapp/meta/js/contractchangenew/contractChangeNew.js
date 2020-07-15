$(function(){
    linkDistrict("cityNo","districtNo");

    if($("#cityNo").val()){
        var url = BASE_PATH +  "/linkages/city/" + $("#cityNo").val();
        var params = {};
        ajaxGet(url, params, function(data) {
            var result = "<option value=''>请选择区域</option>";
            $.each(data.returnValue, function(n, value) {
                if(value.districtNo == $("#districtNo").data("value")){
                    result += "<option value='" + value.districtNo + "' selected>"+ value.districtName + "</option>";
                }else{
                    result += "<option value='" + value.districtNo + "'>"+ value.districtName + "</option>";
                }

            });
            $("#districtNo").html('');
            $("#districtNo").append(result);
        }, function() {
        });
    }

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
    //上传结束
    //开始轮询
//	fileInterval();
    //轮询结束
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

//【注意：此处ContractB2A不允许更名】
ContractB2A = function(){
}
//选择公司弹层
ContractB2A.popCompany=function(params,callBackFun) {

    var url = BASE_PATH + '/relate/company';
    var title = '关联客户';

    var dialogOptions = {

        width : 800,
        /* height : 760, */

        ok : function() {
            if(typeof(callBackFun) == "function"){
                var selectCompany = $(':radio[name=selectCompanyId]:checked');
                callBackFun(selectCompany.val());
            }
        },
        okVal : '确定',
        cancel : true,
        cancelVal : '取消'
    };

    var params ={"oldCompanyId":$("#oldCompanyId").val()}

    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

        dialog.position('50%', '25%');

        RelateUtil.dialog = dialog;

    }, dialogOptions);
}
//选择公司回调
ContractB2A.companyPopCallBack=function(companyId){
    if(!companyId){
        return false;
    }
    $.getJSON(BASE_PATH+"/companys/detailJson/"+companyId, function(data){
        if(!data.returnData.companyName||!data.returnData.legalPerson||!data.returnData.address||!data.returnData.businessLicenseNo){
            Dialog.alertError("选择的经纪公司信息不全，需中心总权限在公司详情中进行补充！");
            return false;
        }

        $("#newCompanyId").val(companyId);
        $("#newCompanyName").val(data.returnData.companyName);
        $("#newLegalPerson").val(data.returnData.legalPerson);
        $("#newCompanyAddressCityNo").val(data.returnData.cityNo);
        $("#newCompanyAddressDistrictNo").val(data.returnData.districtNo);
        $("#newCompanyAddress").val(data.returnData.address);
        $("#address3").val(data.returnData.cityName+data.returnData.districtName+data.returnData.address);
    });
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
    if(!Validator.validForm("contractChangeForm")){
        return false;
    }

    if(!$("input[name='threePartChangeType']:checked").val()){
        Dialog.alertInfo("三方变更类型不能为空",false);
        return false;
    }

    var threeType = $("input[name='threePartChangeType']:checked").val();
    if($("#abTypeStore").val() == '19901' && threeType == '23003'){
        Dialog.alertInfo("当前门店资质等级为甲类，三方变更类型只能选甲转甲或甲转乙");
        return false;
    }else if($("#abTypeStore").val() == '19902' && threeType != '23003'){
        Dialog.alertInfo("当前门店资质等级为乙类，三方变更类型只能选乙转乙");
        return false;
    }

    if(($("#storeAbType").val()== 19902 && threeType == 23001) ||
        ($("#storeAbType").val()== 19901 && threeType != 23001)){
        Dialog.alertInfo("三方变更类型与门店资质等级不匹配");
        return false;
    }

    if (!fnCheckImage()) {
        return false;
    }

    handlerFileInfo();

    var url = BASE_PATH + '/contractChangeNew/saveContractChange';
    systemLoading("", true);
    httpPost('contractChangeForm', url, function(data) {
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
    if(!($('#transfereeBox> .item-photo-list').length && $('#transfereeBox> .item-photo-list').length>0)){
        Dialog.alertInfo("请上传受让方营业执照！");
        return false;
    }
    if(!($('#webImgBox> .item-photo-list').length && $('#webImgBox> .item-photo-list').length>0)){
        Dialog.alertInfo("请上传国家企业信用信息公示系统网站截图！");
        return false;
    }
    if(!($('#idCardBox> .item-photo-list').length && $('#idCardBox> .item-photo-list').length>0)){
        Dialog.alertInfo("请上传受让方法人身份证！");
        return false;
    }
    if(!($('#threePartBox> .item-photo-list').length && $('#threePartBox> .item-photo-list').length>0)){
        Dialog.alertInfo("请上传三方变更协议！");
        return false;
    }
    if(!($('#receiveBox> .item-photo-list').length && $('#receiveBox> .item-photo-list').length>0)){
        Dialog.alertInfo("请上传转让方保证金收据！");
        return false;
    }

    return true;
}