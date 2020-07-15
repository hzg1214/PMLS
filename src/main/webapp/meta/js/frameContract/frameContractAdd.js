$(function() {
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
FrameContractAdd = function () {
    dialog: null;
};
FrameContractAdd.close = function () {

	FrameContractAdd.dialog.close();
};

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
		var url = BASE_PATH + '/frameContract';
		if (Validator.validForm("frameContractAddForm")){
			httpPost('frameContractAddForm', url, function(data) {
				location.href = BASE_PATH + "/frameContract";
			}, function(data) {
				Dialog.alertError(data.returnMsg);
				systemLoaded();
			});
		}
	}
}
//更新
function updateSubmit(id){
	if(checkForAdd()){
		handlerFileInfo();
		if (Validator.validForm("frameContractAddForm")){
			$.ajax({
				url:BASE_PATH+"/frameContract/update",
				data: $('#frameContractAddForm').serialize(),
				type:"post",
				success:function(data){
					if(data){
						Dialog.alertInfo("框架合同修改成功");
						location.href = BASE_PATH + "/frameContract";
						return;
					}
				},
				error:function(){
					Dialog.alertError("框架合同修改失败");
				}
			});
		}
	}
}

//门店选择验证
function checkForAdd(){
	
	var comapanyName = $("#comapanyName").val();
	var cityNm = $("#cityNm").val();
	var districtNm = $("#districtNm").val();
	var address = $("#address").val();
	var businessLicenseNo = $("#businessLicenseNo").val();
	var agreementTypeVal= $('input:radio[name="agreementType"]:checked').val();
	var reAgreeFlagVal= $('input:radio[name="reAgreeFlag"]:checked').val();
	if(agreementTypeVal == null || agreementTypeVal==undefined || agreementTypeVal==''){
		 Dialog.alertInfo("请选择框架协议类型！");
		 return false;
	}
	if(comapanyName == "" || cityNm== "" || districtNm=="" || address == "" || businessLicenseNo == "") {
		Dialog.alertInfo("选择的经纪公司信息不全，需中心总权限在公司详情中进行补充！");
		return false;
	}
	if(businessLicenseNo != "" && (businessLicenseNo.length !=15 && businessLicenseNo.length !=18)) {
		Dialog.alertInfo("选择的经纪公司统一社会信用代码不准确，需中心总权限在公司详情中进行修改!");
		return false;
	}
	
	var dateLifeStart = $("#dateLifeStart").val();
	if(dateLifeStart == "") {
		Dialog.alertInfo("请填写合同开始日期!");
		return false;
	}
	var dateLifeEnd = $("#dateLifeEnd").val();
	if(dateLifeEnd == "") {
		Dialog.alertInfo("请填写合同到期日期!");
		return false;
	}
	
	if(reAgreeFlagVal == null || reAgreeFlagVal==undefined || reAgreeFlagVal==''){
		Dialog.alertInfo("请选择是否自动续约！");
		return false;
	}
	var signDate = $("#signDate").val();
	if(signDate == "") {
		Dialog.alertInfo("请填写合同签订日期!");
		return false;
	}
	
	if($("#accountProvinceNo").val()==""||$("#accountCityNo").val()==""){
        Dialog.alertInfo("请选择开户省市！");
        return false;
	}

	var accountNm = $("#accountNm").val();
	if(accountNm == "") {
		Dialog.alertInfo("请输入开户名!");
		return false;
	}
	var bankAccountNm = $("#bankAccountNm").val();
	if(bankAccountNm == "") {
		Dialog.alertInfo("请输入开户行!");
		return false;
	}
	var bankAccount = $("#bankAccount").val();
	if(bankAccount == "") {
		Dialog.alertInfo("请输入银行帐号!");
		return false;
	}
	/*if(!/^\d+$/.test(bankAccount)){
		Dialog.alertInfo("银行帐号请输入数字!");
		return false;
	}*/
	var partyBNm = $("#partyBNm").val();
	if(partyBNm == "") {
		Dialog.alertInfo("请输入乙方授权代表!");
		return false;
	}
	var partyBTel = $("#partyBTel").val();
	if(partyBTel == "") {
		Dialog.alertInfo("请输入乙方联系方式!");
		return false;
	}
//	var fyAccountNm = $("#fyAccountNm").val();
//	if(fyAccountNm == "") {
//		Dialog.alertInfo("请输入房友帐号信息接收人!");
//		return false;
//	}
//	var fyAccountNmTel = $("#fyAccountNmTel").val();
//	if(fyAccountNmTel == "") {
//		Dialog.alertInfo("请输入接收人联系方式!");
//		return false;
//	}
	
	// 校验联系电话是否是11位数字
	if(!checkPhoneNumber(partyBTel)){
		Dialog.alertInfo("乙方联系方式格式错误!");
		return false;
	}
//	if(!checkPhoneNumber(fyAccountNmTel)){
//		Dialog.alertInfo("接收人联系方式格式错误!");
//		return false;
//	}
	
	
	//验证是否上传营业执照
	if($('#fileBusiness> .item-photo-list').length && $('#fileBusiness> .item-photo-list').length>0){}
	else{
		Dialog.alertInfo("请上传营业执照附件！");
		return false;
	}
	//验证上传合同附件
	if($('#fileContract> .item-photo-list').length && $('#fileContract> .item-photo-list').length>0){}
	else{
		Dialog.alertInfo("请上传合同附件！");
		return false;
	}
	return true;
	
}

FrameContractAdd.submitToOA = function (contractId) {
	//提交OA前判断其有几个核算主体，如果多个需选择，如果是单个直接提交
	if($("#autoToOa").val()!=1){
        var accountProjectLenth = $("#accountProjectLenth").val();
        if(accountProjectLenth != 1){
            var url_a = BASE_PATH+"/frameContract/toChooseAccountProject";
            var params = {contractId:contractId};
            var dialogOptions = {
                width:600
            };
            Dialog.ajaxOpenDialog(url_a, params, "选择考核主体", function(dialog, resData) {
                FrameContractAdd.dialog = dialog;
            }, dialogOptions);
        }else{
            var accountProject = $("#accountProject").val();
            var accountProjectCode = $("#accountProjectCode").val();
            $.ajax({
                url:BASE_PATH+"/frameContract/update",
                async: false,
                data:{
                    id : contractId,
                    accountProject	: accountProject,
                    accountProjectCode:accountProjectCode
                },
                type:"post",
                success:function(data){
                },
                error:function(){
                }
            });
            submitOA(contractId);
        }
	}else{
        submitOA(contractId);
	}
	
}
function changeFrameType(obj){
	var changeFrameType = $(obj).val();
	window.location.href = BASE_PATH+"/frameContract/toInsertFrameContract?changeFrameType="+changeFrameType;
}
function submitOA(contractId){
	$("#submitToOA").hide();
	ajaxGet(BASE_PATH+"/frameContract/submitToOA/"+contractId,null,function (data) {
       if(data.returnCode != "200"){
           Dialog.alertError(data.returnMsg);
       }else{
       	Dialog.alertSuccess("提交成功!");
           window.location = BASE_PATH+"/frameContract/"+data.returnData;
       }
   },function (data) {
	    if(data.returnMsg){
           Dialog.alertError(data.returnMsg);
       }
   });
}
/**
 * 选择考核主体更新到框架协议中
 */
FrameContractAdd.chooseAccountProject=function(contractId){ 
	var accountProjectMapping = $("#userAccountProject option:selected");
	//考核主体
	var accountProjectCode = accountProjectMapping.val();
	var accountProject = accountProjectMapping.text();
	if (accountProject==null || accountProject == "" || accountProject == undefined) {
		$("#warning-noCity").html("请选择核算主体！");
		return false;
	}
	$.ajax({
		url:BASE_PATH+"/frameContract/update",
		async: false,
		data:{
			id:contractId,
			accountProject	: accountProject,
			accountProjectCode:accountProjectCode
		},
		type:"post",
		success:function(data){
		},
		error:function(){
		}
	});
	FrameContractAdd.dialog.close();
	submitOA(contractId);
};

FrameContractAdd.closePopup = function(){
	FrameContractAdd.dialog.close();
}

function operateChangeCt(contractId) {
	if(!isBlank(contractId)) {
		systemLoading("", true);
		$.ajax({
			url:BASE_PATH+"/frameContract/operateChangeCt",
			data:$.param({
				id:contractId
			}),
			type:"post",
			success:function(data){
				data = JSON.parse(data);
				if(data && data.returnCode == '200'){
					Dialog.alertSuccess("状态变更成功!");
					$("#operateChangeCt").hide();
					systemLoaded();
					location.href = BASE_PATH + "/frameContract";
				}
			},
			error:function(){
				Dialog.alertError("状态变更失败");
				$("#operateChangeCt").hide();
				systemLoaded();
			}
		});
	}
	
};
