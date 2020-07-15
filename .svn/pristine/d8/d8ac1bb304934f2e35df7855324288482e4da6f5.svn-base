/**
 * Created by yinkun on 2018/8/14.
 */

$(function(){
    $("#content").css("height","500px");
    $("#content").css("overflow","auto");

    //不含税请款总计
    $("input[money1]").blur(function(){
        countMoneyNoTax();
    });

    //税额总计
    $("input[money2]").blur(function(){
        countMoneyTax();
    });

    var options = {
        "url":BASE_PATH + '/file/uploadCommonFile',
        "isDeleteImage":false,//删除时校验checkEditImage
        "isAddImage":true,   //添加时校验checkEditImage
        "isCommonFile":true  //公共上传文件
    };
    photoUploader(options,null,null,null);
});

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

function countMoneyNoTax() {
    var amountNoTaxTotal = 0.0;
    $("input[money1]").each(function(){
        var this_val = $(this).val();
        if(!this_val){
            this_val = 0;
        }
        if(parseFloat(this_val)){
            amountNoTaxTotal+=parseFloat(this_val);
        }
    });

    $("#amountTotalTd").html("￥"+number_format(amountNoTaxTotal, 2, ".", ","));
    $("#amountTotal").val(amountNoTaxTotal);
    $("#amountNoTaxTd").html("￥"+number_format(amountNoTaxTotal-parseFloat($("#amountTax").val()), 2, ".", ","));
    $("#amountNoTax").val(amountNoTaxTotal-parseFloat($("#amountTax").val()));

    //
}

function countMoneyTax() {
    var amountTaxTotal = 0.0;
    $("input[money2]").each(function(){
        var this_val = $(this).val();
        if(!this_val){
            this_val = 0;
        }
        if(parseFloat(this_val)){
            amountTaxTotal+=parseFloat(this_val);
        }
    });
    // $("#amountTaxTd").html("￥"+number_format(amountTaxTotal, 2, ".", ","));
    // $("#amountTax").val(amountTaxTotal);
    //
    // $("#amountTotalTd").html("￥"+number_format(amountTaxTotal+parseFloat($("#amountNoTax").val()), 2, ".", ","));
    // $("#amountTotal").val(amountTaxTotal+parseFloat($("#amountNoTax").val()));
    // $("#amountTotalTd").html("￥"+number_format(amountTaxTotal, 2, ".", ","));
    // $("#amountTotal").val(amountTaxTotal);
    $("#amountTaxTd").html("￥"+number_format(amountTaxTotal, 2, ".", ","));
    $("#amountTax").val(amountTaxTotal);
    $("#amountNoTaxTd").html("￥"+number_format($("#amountTotal").val()-amountTaxTotal, 2, ".", ","));
    $("#amountNoTax").val($("#amountTotal").val()-amountTaxTotal);


}

function removeFromCashBill(repRowId,comRowId,proRowId,index,obj) {
    ajaxGet(BASE_PATH + "/cashBill/removeFromCashBill",{"repRowId":repRowId,"comRowId":comRowId,"proRowId":proRowId},function (data) {
        Dialog.alertSuccess(data.returnMsg);
        var eleTr = $(obj).parent().parent();
        $(eleTr).remove();
        if($("#companyTb2_"+index).find("tr").length == 1){
            $("#companyTb2_"+index).remove();
            $("#companyTb1_"+index).remove();
            $("#companyHr_"+index).remove();
            countMoneyNoTax();
            countMoneyTax();
        }

        if($(".reportClass").length == 0){
            BatchCashBill.close();
        }
    },function (data) {
        Dialog.alertError(data.returnMsg);
    })
}

function saveCashBill(submitStatus) {
    systemLoading("#contentAll", true);
    $("input[type='button']").attr("disabled","disabled");
    //1是提交
    if(submitStatus == 1){
        $("#submitStatus").val(1);
        if(!Validator.validForm("cashBillForm")){
            $("input[type='button']").removeAttr("disabled");
            systemLoaded();
            return false;
        }

        if($('#fileIdPhotoToCashBill> .item-photo-list').length && $('#fileIdPhotoToCashBill> .item-photo-list').length>0){}
        else{
            $("#errMsg").html("请上传成销确认书/佣金结算资料！");
            $("input[type='button']").removeAttr("disabled");
            systemLoaded();
            return false;
        }
        var lnkAccountProjectCode = $("#lnkAccountProjectCode").find("option:selected").val();
        var lnkAccountProject = $("#lnkAccountProjectCode").find("option:selected").attr('data-lnkAccountProject');
        $("#lnkAccountProject").val(lnkAccountProject);
        if(!isNullOrEmpty(lnkAccountProjectCode)){
            $("#errMsg").html("请选择核算主体！");
            return false;
        }
    }else{
        $("#submitStatus").val(0);
    }
    handlerFileInfo();

    var url = BASE_PATH+"/cashBill/saveCashBill";
    var param = $("#cashBillForm").serialize();

    $.ajax({
        type: 'POST',
        url: url,
        data: param,
        dataType:"json",
        success:function(data) {
            if("200" == data.returnCode){
                Dialog.alertSuccess(data.returnMsg);
                BatchCashBill.close(1);
                systemLoaded();
            }
        },
        error:function(data) {
            $("#errMsg").html(data.returnMsg);
            $("input[type='button']").removeAttr("disabled");
            systemLoaded();
        }
    });
    /*ajaxGet(url,param,function(data){
        if("200" == data.returnCode){
            Dialog.alertSuccess(data.returnMsg);
            BatchCashBill.close(1);
            systemLoaded();
        }
    },function (data) {
        $("#errMsg").html(data.returnMsg);
        $("input[type='button']").removeAttr("disabled");
        systemLoaded();
    });*/
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
function isNullOrEmpty(obj){
    if(obj == null || obj == "" || obj == undefined){
        return false;
    }else{
        return true;
    }
}
