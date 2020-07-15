
$(function() {
});

keFuOrder = function() {

    dialogReply: null;
    dialogVerify: null;

};

/**
 * 回复弹框
 * @param id
 */
gdReply=function(id,dealStatus,userCode,userCreate){
    var url = BASE_PATH + '/keFuOrder/reply';
    var params = {
        id:id,
        dealStatus:dealStatus,
        userCode:userCode,
        userCreate:userCreate
    };
    var dialogOptions = {
        width:850
    };
    Dialog.ajaxOpenDialog(url, params, "工单回复", function(dialog, resData) {
        keFuOrder.dialogReply = dialog;
    }, dialogOptions);
}

/**
 * 核验弹框
 * @param id
 */
gdCheck=function(id,dealStatus,checkStatus,userCode,userCreate){
    var url = BASE_PATH + '/keFuOrder/verify';
    var params = {
        id:id,
        dealStatus:dealStatus,
        checkStatus:checkStatus,
        userCode:userCode,
        userCreate:userCreate
    };
    var dialogOptions = {
        width:850
    };
    Dialog.ajaxOpenDialog(url, params, "核验", function(dialog, resData) {
        keFuOrder.dialogVerify = dialog;
    }, dialogOptions);

}

keFuOrder.closeReplyPopup = function(){
	keFuOrder.dialogReply.close();
}

keFuOrder.closeVerifyPopup = function(){
    keFuOrder.dialogVerify.close();
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

/**
 * 保存回复
 */
keFuOrder.doSave = function(orderId){

	$(".fc-warning").empty();
    //回复内容
    var replyDesc = $("#replyDesc").val();
    if(replyDesc == '' || replyDesc == null || replyDesc==undefined ){
        $("#replyDescWarningMsg").empty().html("回复内容必填");
        $("#replyDescWarningMsg").show();
        return false;
    }
    if(replyDesc.length>1000){
        $("#replyDescWarningMsg").empty().html("回复内容过长");
        $("#replyDescWarningMsg").show();
        return false;
    }
    //问题状态
    var dealStatus = $("#dealStatus").val();
    if(dealStatus == '' || dealStatus == null || dealStatus==undefined ){
        $("#dealStatusWarningMsg").empty().html("请选择问题状态");
        $("#dealStatusWarningMsg").show();
        return false;
    }
    if (Validator.validForm("keFuOrderReplyForm")) {
        handlerFileInfo();
        var url = BASE_PATH + '/keFuOrder/saveKeFuReply';
        var params = $("#keFuOrderReplyForm").serialize();
        restPost(url, params, function(data) {
            if(data.returnCode == 200){
                systemLoaded();
                //Dialog.alertInfoToUrl("回复成功!",BASE_PATH + "/keFuOrder/getKeFuOrderById/"+orderId,true);
                keFuOrder.dialogReply.close();
                Dialog.alertSuccess();
                location.href = BASE_PATH + "/keFuOrder?searchParam=1";
            }
        }, function(data) {
            $("#errorReplayMsg").empty().html(data.returnMsg);
            $("#errorReplayMsg").show();
            systemLoaded();
        });

    };
}

keFuOrder.doVerify = function(orderId){
    $(".fc-warning").empty();
    //回复内容
    var verifyDesc = $("#checkDesc").val();
    if(checkDesc == '' || checkDesc == null || checkDesc==undefined ){
        $("#verifyDescWarningMsg").empty().html("核验内容必填");
        $("#verifyDescWarningMsg").show();
        return false;
    }
    if(checkDesc.length>1000){
        $("#checkDescWarningMsg").empty().html("核验内容过长");
        $("#checkDescWarningMsg").show();
        return false;
    }
    //问题状态
    var checkStatus = $("#checkStatus").val();
    if(checkStatus == '' || checkStatus == null || checkStatus==undefined ){
        $("#checkStatusWarningMsg").empty().html("请选择核验状态");
        $("#checkStatusWarningMsg").show()
        return false;
    }
    if (Validator.validForm("keFuOrderVerifyForm")) {
        handlerFileInfo();
        var url = BASE_PATH + '/keFuOrder/saveKeFuVerify';
        var params = $("#keFuOrderVerifyForm").serialize();
        restPost(url, params, function(data) {
            if(data.returnCode == 200){
                systemLoaded();
                ///Dialog.alertInfoToUrl("核验成功!",BASE_PATH + "/keFuOrder/getKeFuOrderById/"+orderId,true);
                keFuOrder.dialogVerify.close();
                Dialog.alertSuccess();
                location.href = BASE_PATH + "/keFuOrder?searchParam=1";
            }
        }, function(data) {
            $("#errorVerifyMsg").empty().html(data.returnMsg);
            $("#errorVerifyMsg").show()
            systemLoaded();
        });

    };

}

function reAlert(id){
    restPost(BASE_PATH + '/keFuOrder/reAlert', {"id":id}, function(data) {
        if(data.returnCode == 200){
            systemLoaded();
            Dialog.alertSuccess();
            $("#reAlert").attr("disabled","disabled");
        }
    }, function(data) {
        Dialog.alertError(data.returnMsg)
        systemLoaded();
    });
}

