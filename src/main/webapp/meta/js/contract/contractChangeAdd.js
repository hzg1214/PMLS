/** ************* 合同终止页面上传附件-变量 start js ********** */
ContractChangeAdd = function() {
};
// 定义数组
var fileRecordMainIds = new Array();
/*var contractChangePicIds = new Array();*/

var tagArr = [ 'StopContract', 'Surrender', 'Receipt', 'ReturnProve',
		'Cancellate' , 'Photos', 'Cooperate', 'OneStop', 'Others' ];
var picArr = [ 'zhongzhihezuo.png','sanfangjieyue.png', 'baozhengjinshouju.png', 'yifuzhuangxiukuantuihuan.png', 'zhuxiaozhengming.png',
          		'zhaopian.png', 'xina.png' ,'yishiyiyi.png', 'qita.png'];
/** ************* 合同终止页面上传附件-变量 end js *********** */

$(function(){
	//上传开始
	var options = {
		"url":BASE_PATH + '/file/uploadCommonFile',
		"oaUrl":BASE_PATH + "/stopcontract/oa/upload",
		"isDeleteImage":false,//删除时校验checkEditImage
		"isAddImage":true,   //添加时校验checkEditImage
		"isCommonFile":true  //公共上传文件
	};
	photoUploader(options,null,null,null);
	//上传结束
	//开始轮询
//	fileInterval();
	//轮询结束
	$("#errorTip").html("&nbsp;");
});

/**
 * 新增变更--保存事件
 * 
 * @param contractId
 *            合同ID
 * @returns {Boolean}
 */
function doSave(contractId,contractStatus) {

	// 校验文本输入框通过的场合
	if (Validator.validForm("stopContractForm")) {

		// 校验下拉框和图片数据是否填入
		if (ContractChangeCom.chkCombobox()) {

			// 获取选中的门店ID
			if (this.getStoreIdArray()) {
				
				handlerFileInfo();
				
				var url = BASE_PATH + '/stopcontract/doSave';
				// 发起请求
				httpPost('stopContractForm', url, function(data) {
					// 跳转至变更记录页面
					location.href = BASE_PATH + '/contract/changeRecord/'
							+ contractId+"/"+contractStatus;
				}, function(data) {
					Dialog.alertError(data.returnMsg);
					systemLoaded();
					return false;
				});
			} else {
				return false;
			}
		} else {
			return false;
		}
	} else {
		return false;
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
//	if ($("#companyId").val() == "") {
//		Dialog.alertInfo("请先选择客户");
//		return false;
//	}
	
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
	//营业证,身份证,合同照片,门店照片,房友系统申请安装单,其它
	/*var contractChangePicIds = "";*/
	var fileRecordMainIds = "";
	
	$("input[name=fileRecordMainIdHidden]").each(function(){
		if($(this).val()==""){
			Dialog.alertError("图片上传出现异常，请删除重新上传。");
			bol = false;
			return false;
		}
		/*var contractChangePicId = $(this).parent().find("input[name=contractChangePicId]").val();
		if($(this).val()=="" || contractChangePicId==""){
			Dialog.alertError("图片上传出现异常，请删除重新上传。");
			bol = false;
			return false;
		}*/

		fileRecordMainIds += ","+$(this).val();
		/*contractChangePicIds += ","+contractChangePicId;*/
	})
	
	/*if(contractChangePicIds!=""){
		contractChangePicIds = contractChangePicIds.substring(1);
	}*/
	if(fileRecordMainIds!=""){
		fileRecordMainIds = fileRecordMainIds.substring(1);
	}
	
	/*$("#contractChangePicIds").val(contractChangePicIds);*/
	$("#fileRecordMainIds").val(fileRecordMainIds);
	
	return bol;
}   

