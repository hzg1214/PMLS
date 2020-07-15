$(function() {
	$(".errorTip2").html("").hide();
	amountSum();

    //上传附件

    var options = {
        "url":BASE_PATH + '/file/uploadCommonFile',
        //"oaUrl":BASE_PATH + "/stopcontract/oa/upload",
        "isDeleteImage":false,//删除时校验checkEditImage
        "isAddImage":true,   //添加时校验checkEditImage
        "isCommonFile":true  //公共上传文件
    };
//调用方法进行上传
    photoUploader(options,null,null,null);
});
//控制输入的数值只能输入整数或者两位以内的小数
function myNumberic(e,len) {
	//$(".errorTip2").html("").hide();
    var obj=e.srcElement || e.target;
    var dot=obj.value.indexOf(".");
    len =(typeof(len)=="undefined")?2:len;
    var  key=e.keyCode|| e.which;
    if(key==8 || key==9 || key==46 || (key>=37  && key<=40))//这里为了兼容Firefox的backspace,tab,del,方向键
        return true;
    if (key<=57 && key>=48) { //数字
        if(dot==-1)//没有小数点
            return true;
        else if(obj.value.length<=dot+len)//小数位数
            return true;
        } else if((key==46) && dot==-1){//小数点
            return true;
    }  
    //$("#errorTip2").html("退款数额请填写大于0的整数或者两位以内的小数！").show();
    return false;
}
//checkbox 全选/取消全选  
var isCheckAll = false;  
function swapCheck() {  
    if (isCheckAll) {  
        $("input[type='checkbox']:not(:disabled)").each(function() {  
            this.checked = false;
            amountSum();
        });  
        isCheckAll = false;  
    } else {  
        $("input[type='checkbox']:not(:disabled)").each(function() {  
            this.checked = true;
            appendAmountSum(1);
        });  
        isCheckAll = true;  
    }  
} 

function amountSum(){
	//计算总的金额
	var totalCount = 0.00;
   	$('#totalAmountSum2').html('');
   	$("input[name=totalAmountSum]").val(totalCount.toFixed(2));  
   	$("#totalAmountSum2").append('本次合计退款金额 &nbsp;&nbsp;￥'+ '<strong>'+totalCount.toFixed(2)+'</strong>');
}
//动态计算应退金额
function appendAmountSum(changeFlag) {  
	var totalCount = 0.00;
	$("input[name = storeChk]").each(function(){
		if ($(this).prop('checked')){
			var storeId =$(this).val();
			var amount = $('input[name="amount'+storeId+'"]').val();
			if(amount != undefined && amount != null && amount != ''){
				amount = parseFloat(amount);
				totalCount += amount;
			   	if(isNaN(totalCount)) {  
			   		totalCount =0.00;
			    } 
			}
		}
	});
	$("input[name=totalAmountSum]").val(totalCount.toFixed(2));
	$('#totalAmountSum2').html('');
	$("#totalAmountSum2").append('本次合计退款金额 &nbsp;&nbsp;￥'+ '<strong>'+totalCount.toFixed(2)+'</strong>');

}

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


//保存
function addSubmit(){
	if(checkForAdd()){
		handlerFileInfo();
		var url = BASE_PATH + '/storePayment';
		httpPost('storePaymentForm', url, function(data) {
			location.href = BASE_PATH + "/storePayment";
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});
	}
}

//门店选择验证
function checkForAdd(){
	
	//退款方式验证
	var paymentType = $("#paymentType").val();
	if(!paymentType){
		Dialog.alertInfo("请选择退款方式！");
		return false;
	}
	//退款时间验证
	var backDate = $("#backDate").val();
	if(!backDate){
		Dialog.alertInfo("请选择退款时间！");
		return false;
	}
	
	var flag = true;
	var flag1 = true;
	var flag2 = true;
	var flag3 = true;
	var storeIds = "";
	var refundStateFlag = 1;
	// 获取所有门店
	$("input[name = storeChk]").each(function(){
		if ($(this).prop('checked')){
			var storeId =$(this).val();
			storeIds += storeId + ",";
			var totalAmount = $('input[name="totalAmount'+storeId+'"]').val();
			var paymentAmount = $('input[name="paymentAmount'+storeId+'"]').val();
			var amount = $('input[name="amount'+storeId+'"]').val();
			if(amount== ""  ){
				flag = false;
			}
			if(totalAmount== "" || totalAmount == null){
				flag2 = false;
			}
			if(paymentAmount== ""  || paymentAmount == null){
				flag3 = false;
			}
			if(parseFloat(amount) >parseFloat(totalAmount)){
				flag1 = false;
			}
		}
		if(parseFloat(amount) == parseFloat(totalAmount)){
			refundStateFlag = 0;
		}else{
			refundStateFlag = 1;
		}
	});
	$("#refundStateFlag").val(refundStateFlag);
	if(!flag){
		Dialog.alertInfo("请填写本次退款数额！");
		return false;
	}
	if(!flag1){
		Dialog.alertInfo("本次退款款金额不能大于退款金额！");
		return false;
	}
	if(!flag2){
		Dialog.alertInfo("应退款款金额不能为空！");
		return false;
	}
	if(!flag3){
		Dialog.alertInfo("已退金额不能为空！");
		return false;
	}
	
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
	//验证上传附件
	if($('#fileRecordMainAttachment> .item-photo-list').length && $('#fileRecordMainAttachment> .item-photo-list').length>0){}
	else{
		Dialog.alertInfo("请上传附件！");
		return false;
	}
	return true;
	
}

