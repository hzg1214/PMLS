/************初始化************/

Dialog.alertSuccess = function(){
	dialog: null;
}

ContractB2A = function(){
	
}

$(function() {
	arraySet();
	linkDistrict("companyCity","companyDistrict");
	linkDistrict("cityNo","districtNo");
	linkDistrict("storeCity","storeDistrict");
	linkDistrict("newCompanyAddressCityNo","newCompanyAddressDistrictNo");
	defaultInfo();
	$("#companyrange").change(function(){
		if(this.checked==false){
			if($("#companychange").checked == false)
			{
				clearCompany();
			}
			if($("#storechange").checked == false){
				clearStore();
			}
			//clearMainChange();
		}
	});
	$("#companychange").change(function(){
		if(this.checked==false){				
			if($("#storechange").checked == false){
				clearStore();
			}
			clearCompany();
			//clearMainChange();
		}
	});
	$("#storechange").change(function(){
		if(this.checked==false){
			if($("#companychange").checked == false)
			{
				clearCompany();
			}
		  clearStore();
		  //clearMainChange();
		}
	});
	$("#mainchange").change(function(){
		if(this.checked==true){
			clearStore();
			clearCompany();
			if($("#newAgreementNo").val()=="")
			{
				$("#newDateLifeStart").val("");
				$("#newDateLifeEnd").val("");	
			}
		}else
		{
			//clearMainChange();
		}
	});
	
	
});

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
});

/***********初始化*************/
/** ************* 合同终止页面上传附件-变量 start js ********** */
// 定义数组
var fileRecordMainIds = new Array();
/*var contractChangePicIds = new Array();*/

var tagArr = [ 'ChangeSupplement','NewBusinessLicense',
		       'InformationPublicity','ConfirmationSheet','StorePhotos','ChangeOthers', 
		       'TransferRights','NewSigning','MainInformationPublicity','MainChangeOthers'  ];
var picArr = [ 'biangengbuchong.PNG','xinyingyezhizhao.PNG','wangzhanjietu.PNG','fangyouqueren.PNG',
		       'store.png','qita.png','quanliyiwu.PNG','xinqianhezuo.PNG', 'wangzhanjietu.PNG','qita.png'];




/** ************* 合同终止页面上传附件-变量 end js *********** */
//初始化数组设值
function arraySet() {
	var ary = $("#fileRecordMainIds").val().split(",");
	for (var i = 0; i < ary.length; i++) {
		fileRecordMainIds.push(ary[i]);
	}
	/*var ary = $("#contractChangePicIds").val().split(",");
	for (var i = 0; i < ary.length; i++) {
		contractChangePicIds.push(ary[i]);
	}
	contractChangePicIds.remove("");*/
	fileRecordMainIds.remove("");	
  }


/**
 * 编辑变更--更新事件
 * 
 * @param id
 *            合同变更ID
 * @returns {Boolean}
 */
function doEdit(id,contractStatus) {
	
	var contractId = $("#contractId").val();
	
	if(checkdata()) 
	{
		if(newCheckInfo())
		{			
			
		  handlerFileInfo();
		  //对门店地址中空格去除，需要时打开
		  //$("#storeAddress").val($("#storeAddress").val().replace(/[ ]/g,""));
		  var params = $("#stopContractForm").serialize();
		  
		  var url = BASE_PATH + '/stopcontract/doUpdate';
		  
		  if($("#mainchange:checked").length != 1){
				$("#changeMain input[type='text']").each(function(){
					   $(this).val("");
				   });
				$("#newCompanyAddressDistrictNo").val("");
				$("#newCompanyAddressCityNo").val("");
				$("#cityNo").val("");
				$("#districtNo").val("");
			}
		  
		  // 发起请求
		  httpPut(url, params, function(data) {
			  // 跳转至变更记录页面
			  location.href = BASE_PATH + '/contractChangeNew/list/'
					+ contractId+"/"+contractStatus;
		  }, function(data) {
			  Dialog.alertError(data.returnMsg);
			  systemLoaded();
			  return false;
		  });
		}
	}
}

function defaultInfo(){
	   
	    var company = $('#companyRegister').val();
	    var store = $('#storeRegister').val();
	    var main = $('#sign').val();
	    
	    if(company == 1){
	    	$("#storeChangeEnclosure").hide();
	    	 $("#companyChangeAddress").show();
	    	 $("#signEnclosure").show();
	    	 $("#signChangeEnclosure").hide();
	    	 $("#changeMain").hide();
	    }
	   
	    if(store == 1)
	    {	
	    	$("#signEnclosure").show();
	    	$("#storeChangeAddress").show();
	    	$("#storeChangeEnclosure").show();
	    	$("#signChangeEnclosure").hide();
	    	$("#changeMain").hide();
	    } 	
	    
	    if(main == 1)
	    {
	    	 $("#signChangeEnclosure").show();
	    	 $("#signEnclosure").hide();
	    	 $("#changeMain").show();
	    }
}

//点击公司经营范围变更事件
function manageChange(obj) {
	if (obj.checked) {
		$("#signEnclosure").show();
		$("#signChangeEnclosure").hide();
		if($("#storechange:checked").length == 1){
			$("#storeChangeEnclosure").show();
		}
		$("#mainchange").attr("checked", false);
		$("#companyrange").val("1");
		$("#changeMain").hide();
		//clearMainChange();
		$("#companyNameChangeRadio").show();
	} else {
		$("#companyrange").val("0");
	}

}
// 点击公司注册地址变更出现 出现变更公司注册地址 不选则隐藏
function companyChange(obj){
	   if(obj.checked){
		   $("#signEnclosure").show();
		   $("#signChangeEnclosure").hide();
		   if($("#storechange:checked").length == 1){
				$("#storeChangeEnclosure").show();
		   }	  
		   $("#companyChangeAddress").show();
		   $("#mainchange").attr("checked",false);
		   $("#companychange").val("1");
		   $("#changeMain").hide();	
		   //clearMainChange();
		   $("#companyNameChangeRadio").show();
	 }else{
	       $("#companyChangeAddress").hide();
	       $("#companychange").val("0");
	 }
	}
//点击门店注册地址变更出现 出现变更门店地址  不选则隐藏
function storeChange(obj){
	   if(obj.checked){
		   $("#storeChangeAddress").show();
		   $("#storeChangeEnclosure").show();
		   $("#signEnclosure").show();
		   //$("#mainchange").attr("checked",false);
		   $("#signChangeEnclosure").hide();
		   $("#storechange").val("1");
		   //$("#changeMain").hide();	
		   //clearMainChange()
		   if($("#mainchange:checked").length == 1){
			   $("#signEnclosure").hide();
			   $("#signChangeEnclosure").show();
			   $("#storeChangeEnclosure").hide();
			   $("#companyNameChangeRadio").hide();
		   }else{
			   $("#companyNameChangeRadio").show();
		   }
	   }else{
	       $("#storeChangeAddress").hide();
	       $("#storeChangeEnclosure").hide();
	       $("#storechange").val("0");
	       if($("#mainchange:checked").length == 1){
	    	   $("#signChangeEnclosure").show();
		   }
	 }
	}
//点击签约主题变更出现 出现签约主体变更 上传附件 ,反之则隐藏
function signChange(obj){
	   if(obj.checked){
		   $("#signChangeEnclosure").show();
		   $("#storeChangeEnclosure").hide();
		   $("#signEnclosure").hide();
		   $("#companyChangeAddress").hide();
		   $("#companyNameChangeNo").click();
		   $("#companyNameChangeRadio").hide();
		   $("#companyNameChange").hide();
		   //$("#storeChangeAddress").hide();
		   $(".check[id!='storechange']").attr("checked",false);
		   $("#mainchange").val("1");
		   $("#changeMain").show();
	   }else{
		   $("#signChangeEnclosure").hide();
		   $("#signAndStoreChangeEnclosure").hide();
	       $("#signEnclosure").show();
	       if($("#storechange:checked").length == 1){
	    	   $("#storeChangeEnclosure").show();
	       }
	       $("#mainchange").val("0");
	       $("#changeMain").hide();
	       $("#companyNameChangeRadio").show();
	 }
	}

ContractB2A.companyNameChange=function(obj){
	if(obj.id=="companyNameChangeYes"){
		$("#companyNameChange").show();
	}else{
		$("#companyNameChange").hide();
		$("#companyNameChange #newUpdateCompanyName").val("");
	}
}


//check新加字段是否填写
function newCheckInfo()
{	
	//绑定按钮选中
	var mainchange=document.getElementById("mainchange").checked; 
	if(mainchange == true)
	{
	 var newCompanyId = $("#newCompanyId").val();
     if(newCompanyId == null || newCompanyId =='')
     {
    	 Dialog.alertError("请选择新公司");
    	 return false;
     }
	
	
     var legalPerson = $("#newLegalPerson").val();
     if(legalPerson == null || legalPerson == '')
     {
    	 Dialog.alertError("公司法定代表人不能为空");
    	 return false;
     }
     
     var companyAddressDistrictNo = $("#newCompanyAddressDistrictNo").val();
     if(companyAddressDistrictNo == null || companyAddressDistrictNo == '')
     {
    	 Dialog.alertError("公司注册区域不能为空");
    	 return false;
     }
     
     var companyAddress = $("#newCompanyAddress").val();
     if(companyAddress == null || companyAddress == '')
     {
    	 Dialog.alertError("公司注册具体地址不能为空");
    	 return false;
     }
     
     var agreementType = $("input[name='agreementType']:checked").val()
     if(agreementType == null || agreementType == ''){
    	 Dialog.alertError("请选择合作协议书类型");
    	 return false;
     }
     
     var agreementNo = $("#newAgreementNo").val();
     if(agreementNo == null || agreementNo == '')
     {
    	 Dialog.alertError("请填写新合作协议书编号");
    	 return false;
     }
     
     var dateLifeStart = $("#newDateLifeStart").val();
     if(dateLifeStart == null || dateLifeStart == '')
     {
    	 Dialog.alertError("请填写新合同合作开始日期");
    	 return false;
     }
     
     var dateLifeEnd = $("#newDateLifeEnd").val();
     if(dateLifeEnd == null || dateLifeEnd == '')
     {
    	 Dialog.alertError("请填写新合同合作到期日期"); 
    	 return false;
     }
     var authRepresentative = $("#authRepresentative").val();
     if(authRepresentative == null || authRepresentative == '')
     {
    	 Dialog.alertError("请填写甲方授权代表"); 
    	 return false;
     }
     var agentContact = $("#agentContact").val();
     if(agentContact == null || agentContact == '')
     {
    	 Dialog.alertError("请填写联系方式"); 
    	 return false;
     }
     var totleDepositFee = $("#totleDepositFee").val();
     if(totleDepositFee == null || totleDepositFee == '')
     {
    	 Dialog.alertError("请填写保证金金额"); 
    	 return false;
     }
     var penaltyFee = $("#penaltyFee").val();
     if(penaltyFee == null || penaltyFee == '')
     {
    	 Dialog.alertError("请填写违约保证金金额"); 
    	 return false;
     }
	}
     return true;
}

//选择非主体变更时清空主体变更的信息
function clearMainChange(){
	$("#changeMain input[type='text']").each(function(){
		   $(this).val("");
	   });
	$("#newCompanyAddressDistrictNo").val("");
	$("#newCompanyAddressCityNo").val("");
}

//公司注册地址变更
function clearCompany()
{
	$("#companyChangeAddress input[type='text']").each(function(){
		   $(this).val("");
	});
	$("#companyDistrict").val("");
	$("#companyCity").val("");
}

function clearStore()
{
	$("#storeChangeAddress input[type='text']").each(function(){
		   $(this).val("");
	});
	$("#storeDistrict").val("");
	$("#storeCity").val("");
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
		if (photoExt != '.jpg' && photoExt != '.png' ) {
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
    var mainchange=document.getElementById("mainchange").checked;
    var selectedFileArea="";
    if(mainchange){
        selectedFileArea = "#signChangeEnclosure input[name=fileRecordMainIdHidden]"
	}else{
        selectedFileArea = "#signEnclosure input[name=fileRecordMainIdHidden]"
	}
    $(selectedFileArea).each(function(){
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
		$("#address1").val(data.returnData.cityName);
		$("#address2").val(data.returnData.districtName);
		$("#address3").val(data.returnData.address);
	});
}

ContractB2A.checkAgreementNo=function(obj){
	var agreementNo = obj.value.trim();
	if(!agreementNo){
		return false;
	}
	var checkUrl = BASE_PATH + "/contract/check";
	var checkParams = {
		"agreementNo" : agreementNo,
		"checkType" : "1"
	};
	systemLoading("", true);
	
	ajaxGet(checkUrl, checkParams, function(data) {
		systemLoaded();
	}, function(data) {
		Dialog.alertError(data.returnMsg);
		$("#newAgreementNo").val('');
		systemLoaded();
	});
}

ContractB2A.checkStoreAddress=function(obj){
	var storeAddress = obj.value.trim();
	if(!storeAddress || !$("#storeCity").val() || !$("#storeDistrict").val()){
		return false;
	}
	var checkUrl = BASE_PATH + "/stopcontract/checkStoreAddress";
	var checkParams = {
		"contractChangeId":$("#contractChangeId").val(),
		"storeAddress" : storeAddress,
		"storeCity" : $("#storeCity").val(),
		"storeDistrict" : $("#storeDistrict").val()
	};
	systemLoading("", true);
	
	ajaxGet(checkUrl, checkParams, function(data) {
		systemLoaded();
	}, function(data) {
		Dialog.alertError(data.returnMsg);
		$("#storeAddress").val('');
		systemLoaded();
	});
}