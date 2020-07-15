/** ************************公共部分***************************** */
$(function() {

	// 市区域板块
	//linkage();
	linkagePartyB();
	linkageActualOperation();
	//Add By NingChao 2017/04/07 Start 
	if($("#oldContractNo").val()!=undefined){
		//合同開始日期设置
		SetDateLifeStart();

		//门店信息设置
		SetStoreInfo();
		
		//保存续签画面带过来的图片ID
		var strFileRecordMainIds=$("#fileRecordMainIds").val().split(',');
		var strFileTypeIds=$("#fileTypeIds").val().split(',');		
		for(var i=0;i<strFileTypeIds.length;i++)
		{
			fileRecordMainIds.push(strFileRecordMainIds[i]);
			/*if(strFileTypeIds[i]=="1")
			{
				oaFileIdBusiness.push(strFileRecordMainIds[i]);
			}
			if(strFileTypeIds[i]=="2")
			{
				oaFileIdCards.push(strFileRecordMainIds[i]);
			}
			if(strFileTypeIds[i]=="3")
			{
				oaFileIdPhotos.push(strFileRecordMainIds[i]);
			}
			if(strFileTypeIds[i]=="4")
			{
				oaFileIdStores.push(strFileRecordMainIds[i]);
			}
			if(strFileTypeIds[i]=="5")
			{
				oaFileIdInstalls.push(strFileRecordMainIds[i]);
			}
			if(strFileTypeIds[i]=="6")
			{
				oaFileIdOthers.push(strFileRecordMainIds[i]);
			}*/
		}
		//arraySet();
	}
	//Add By NingChao 2017/04/07 End
	
	//加载城市/区域
	linkDistrict("cityNo","districtNo");
	linkAccountCity("accountProvinceNo","accountCityNo");//加载省市城市
	$("#errorTip").html("").hide();
	$("#errorTip2").html("").hide();
});


$(function(){
	//上传开始
	var options = {
		"url":BASE_PATH + '/file/uploadCommonFile',
		//"oaUrl":BASE_PATH + "/contracts/oa/upload",
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


//初始化数组设值
function arraySet() {

	/*var ary = $("#fileRecordMainIds").val().split(",");
	for (var i = 0; i < ary.length; i++) {
		fileRecordMainIds.push(ary[i]);
	}
*/
	//
	var oaFileIdBusiness = $("#oaFileIdBusiness").val().split("|");
	for (var i = 0; i < oaFileIdBusiness.length; i++) {
		$("#oaFileIdBusiness" + i).val(oaFileIdBusiness[i]);
	}
	//
	var oaFileIdCard = $("#oaFileIdCard").val().split("|");
	for (var i = 0; i < oaFileIdCard.length; i++) {
		$("#oaFileIdCard" + i).val(oaFileIdCard[i]);
	}
	//
	var oaFileIdPhoto = $("#oaFileIdPhoto").val().split("|");
	for (var i = 0; i < oaFileIdPhoto.length; i++) {
		$("#oaFileIdPhoto" + i).val(oaFileIdPhoto[i]);
	}
	//
	var oaFileIdStore = $("#oaFileIdStore").val().split("|");
	for (var i = 0; i < oaFileIdStore.length; i++) {
		$("#oaFileIdStore" + i).val(oaFileIdStore[i]);
	}
	//
	var oaFileIdInstall = $("#oaFileIdInstall").val().split("|");
	for (var i = 0; i < oaFileIdInstall.length; i++) {
		$("#oaFileIdInstall" + i).val(oaFileIdInstall[i]);
	}
	//
	var oaFileIdOther = $("#oaFileIdOther").val().split("|");
	for (var i = 0; i < oaFileIdOther.length; i++) {
		$("#oaFileIdOther" + i).val(oaFileIdOther[i]);
	}
}

//Add By NingChao 2017/04/07 Start 
//续签画面门店信息取得设置
function SetStoreInfo(){
	var sId = $("#storeId").val();
	var contractType = $("#contractType").val();
	
	$("#relateStoreTableId tr:not(.isShowClass)",window.document).remove();
	var checkUrl = BASE_PATH + "/store/qrRenew";
	var checkParams = {
			storeId : sId
	};

	ajaxGet(
			checkUrl,
			checkParams,
			function(data) {
				if (data.returnValue != undefined) {

					// 门店个数
					var storeNum = data.returnValue.length;

					// 合作门店赋值
					$("#storeNum").val(storeNum);

					var htmls = "";
					for (var i = 0; i < storeNum; i++) {
						var storeId = data.returnValue[i].storeId;
						if(sId != storeId)
						{
							continue;
						}
						var maintainer = data.returnValue[i].maintainer==null?"":data.returnValue[i].maintainer;
						var maintainerName = data.returnValue[i].maintainerName==null?"":data.returnValue[i].maintainerName;
						var contactsName = data.returnValue[i].contactsName==null?"":data.returnValue[i].contactsName;
						var mobilPhone= data.returnValue[i].mobilePhone==null?"":data.returnValue[i].mobilePhone;
						var manitainerId=data.returnValue[i].storeMaintainerId;
						var btypeStore2 = data.returnValue[i].btypeStore;
						var btypeStoreName2 = data.returnValue[i].btypeStoreName;
						var storeLevel = "";
						var bTypeStore = "";
						if(data.returnValue[i].abtypeStore == 19901)
						{//甲类门店
							storeLevel = "甲类";
						}else if(data.returnValue[i].abtypeStore == 19902)
						{//乙类门店
							storeLevel = "乙类("+btypeStoreName2+")";
							bTypeStore = btypeStore2;
						}
						else
						{
							storeLevel  = "--";
						}
						if(contractType == 10307){
							var htmlShow ="<td style='display:none'>"+ storeLevel+ "</td>"
						}else {
							var htmlShow ="<td>"+ storeLevel+ "</td>"
						}		
						htmls += "<tr><td data-storeno="+data.returnValue[i].storeNo+">"
								+ data.returnValue[i].name
//								+ "</td><td>"
//								+ data.returnValue[i].districtName
								+ "</td><td>"
								+ data.returnValue[i].addressDetail
								+ "</td><td>"
//								+ data.returnValue[i].dateCreate
//								+ "</td><td>"
								+data.returnValue[i].maintainerName+
								"<input type='hidden' id='maintainer"+storeId+"' name='maintainer"+storeId+"' attr='mtc' value='"+data.returnValue[i].maintainer+"'>"
								/*+ "</td><td><input style='border: 1px solid #ccc;border-radius: 4px;height:28px' size='10' width='30px' id='maintainerName"+storeId+"' name='maintainerName"+storeId+"' value='"+ maintainerName+"' notnull='true' readOnly='true' attr='mtc'>" +
										"<input type='hidden' id='maintainer"+storeId+"' name='maintainer"+storeId+"'  value='"+ maintainer +"'>" +
										"<input type='hidden' id='maintainerId"+storeId+"' name='maintainerId"+storeId+"' value='"+ manitainerId +"'>" +*/
										/*"<button type='button' style='float:right;line-height:1' class='btn btn-primary' onclick='javascript:relateUser(2,"+storeId+");'>选择</button>" */
								+ "</td><td>" 
								+"<input style='border: 1px solid #ccc;border-radius: 4px;height:28px' size='10' id='contactName"+storeId+"' name='contactName"+storeId+"' value='"+ contactsName +"' notnull='true' attr='ctname'>"
								+"</td><td>" 
								+"<input style='border: 1px solid #ccc;border-radius: 4px;height:28px' size='15' maxlength='11' id='contactPhoneNo"+storeId+"' name='contactPhoneNo"+storeId+"' value='"+ mobilPhone +"' notnull='true' attr='ctphone'>"
								
								+ "</td>" 
								+htmlShow
								
								+"<td style='display:none'> <input name='storetype"
								+ data.returnValue[i].storeId
								+"' id='storetype"
								+ data.returnValue[i].storeId
								+ "' type='hidden' value='"
								+ data.returnValue[i].abtypeStore
								+ "'></td>"
								
								+"<td style='display:none'> <input name='storetypeBlst"
								+ data.returnValue[i].storeId
								+"' id='storetypeBlst"
								+ data.returnValue[i].storeId
								+ "' type='hidden' value='"
								+ bTypeStore
								+ "'></td>"
								
								+"<td style='display:none'> <input name='storeIds' id='storeIds"
								+ data.returnValue[i].storeId
								+ "' type='hidden' value='"
								+ data.returnValue[i].storeId
								+ "'></td>"
								
								
								+"</tr>";
					}
					$(
							"#relateStoreTableId",
							window.document)
							.append(
									htmls);
				}
			},
			function(data) {
				Dialog
						.alertError(data.returnMsg);
			});
}
//Add By NingChao 2017/04/07 End

//Add By tong 2017/04/07 Start 
/**
*合同续签的日期计算
*/
function SetDateLifeStart()
{
	var startDate=$("#dateLifeStart").val();
	if(startDate!="" && startDate!=undefined)
	{
		 startDate = new Date(startDate);
	     startDate = +startDate + 1000*60*60*24;
	     startDate = new Date(startDate);
	     var nextStartDate = startDate.getFullYear()+"-"+PrefixInteger((startDate.getMonth()+1),2)+"-"+PrefixInteger(startDate.getDate(),2);
	     $("#dateLifeStart").val(nextStartDate);		     
	}	 
}

//日期格式化
function PrefixInteger(num, n) {
  return (Array(n).join(0) + num).slice(-n);

}
//Add By tong 2017/04/07 End
/** **************************demo js*************************** */
Contract = function() {
};

// 定义数组
var fileRecordMainIds = new Array();
/*// 定义数组，存放ＯＡ附件
var oaFileIdBusiness = new Array();
var oaFileIdCards = new Array();
var oaFileIdPhotos = new Array();
var oaFileIdStores = new Array();
var oaFileIdInstalls = new Array();
var oaFileIdOthers = new Array();
var oaArr = [ oaFileIdBusiness, oaFileIdCards, oaFileIdPhotos, oaFileIdStores,
		oaFileIdInstalls, oaFileIdOthers ];
var tagArr = [ 'Business', 'Card', 'Photo', 'Store', 'Install', 'Other' ];
var picArr = [ 'yingye.jpg', 'faren.jpg', 'hetong.jpg', 'store.png',
		'anzhang.jpg', 'qita.jpg' ];*/

/**
 * 创建
 */
Contract.add = function() {
	
	//判断门店在途保证金
	var flag = false;
	$("#relateStoreTableId td[data-storeno]").each(function(i,n){
		var storeNo = ($(this).data("storeno"));
		
		$.ajax({
			url : BASE_PATH+"/storeDeposit/checkStoreDepositByStoreNo",
			data : {"storeNo":storeNo},
			type : 'GET',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data && data.returnCode != '200') {
					Dialog.alertError(data.returnMsg);
					flag = true;
					return false;
				}
			},
			error:function(data){
				var returnMsg = data.returnMsg;
				if (!returnMsg) {
					returnMsg = "校验保证金失败";
				}
				Dialog.alertError(returnMsg);
				flag = true;
				return false;
			}
		});
	});
	if(flag){
		return;
	}
	
	//判断图片数量是否一致
	/*if(!checkPicNum()){
		Dialog.alertError("图片上传出现异常，请删除重新上传。");
		return false;
	}*/
	var url = BASE_PATH + '/contract';
	
	var partyB = $("#partyB").val();
	var lealPerson = $("#lealPerson").val();
	var partyBCityName = $("#partyBCityName").val();
	var partyBDistrictName = $("#partyBDistrictName").val();
	var partyBAddress = $("#partyBAddress").val();
	var registrId = $("#registrId").val();
	if(partyB == "" || lealPerson== "" || partyBCityName=="" || partyBDistrictName == "" || partyBAddress == "" || registrId == "") {
		Dialog.alertError("选择的经纪公司信息不全，需中心总权限在公司详情中进行补充！");
		return;
	}
	if(registrId != "" && (registrId.length !=15 && registrId.length !=18)) {
		Dialog.alertError("选择的经纪公司统一社会信用代码不准确，需中心总权限在公司详情中进行修改!");
		return;
	}
	/*if ($("#partyB").val() == "") {
		Dialog.alertInfo("请选择公司");
		return;
	}*/
	if ($("#partyBcityNo").val() == "" || $("#partyBdistrictNo").val() == "") {
		Dialog.alertInfo("请选择公司地址的城市行政区");
		return;
	}
	
	/*if (($.trim($('#dateLifeStart').val()) != '' && $.trim($('#dateLifeEnd')
			.val()) != '')) {
		if ($.trim($('#dateLifeStart').val()) > $.trim($('#dateLifeEnd').val())) {
			Dialog.alertInfo("合同生效日期必须小于到期日期");
			return;
		}
	}*/
	
	// 校验联系电话是否是11位数字
	var phone = $("#agentContact").val().trim();
	if(phone == "") {
		Dialog.alertInfo("中介联系方式不能为空!");
		return;
	}
	if(!checkPhoneNumber(phone))
	{
		Dialog.alertInfo("中介联系方式格式错误!");
		return;
	}
	
	var contractType = $('input[name="contractType"]:checked').val();
	if ($('input[name="contractType"]:checked').val() == undefined
			|| $('input[name="contractType"]:checked').val() == "") {
		//Mod By NingChao 2017/04/07 Start
		//Dialog.alertInfo("请选择合作协议书类型");
		Dialog.alertInfo("请选择合作模式");
		//Mod By NingChao 2017/04/07 End
		return;
	}
	
	//Add By NingChao 2017/04/07 Start
	if (contractType != 10307 &&  ($('input[name="agreementType"]:checked').val() == undefined
			|| $('input[name="agreementType"]:checked').val() == "")) {
		Dialog.alertInfo("请选择合作协议书类型");
		return;
	}
	if(contractType != 10307){
		var agreementType = $('input[name="agreementType"]:checked').val();
		$("#agreementType").val(agreementType);
	}else {
		$("#agreementType").val(11006);
	}
	//Add By NingChao 2017/04/07 End
	
	
	if ((contractType==10306 || contractType==10301 || contractType==10307) && ($('input[name="oaApproveType"]:checked').val() == undefined
			|| $('input[name="oaApproveType"]:checked').val() == "")) {
		Dialog.alertInfo("请选择OA审批流程类别");
		return;
	}
	
	//授牌类型 hzg
	var shoupaiType = $('input[name="shoupaiType"]:checked').val();
	if(contractType==10307){
		if (shoupaiType == undefined || shoupaiType == "") {
			Dialog.alertInfo("请选择授牌类型");
			return;
		}
	}
	
	//合作模式为授牌且授牌类型为渠道  开户信息为必填 2019/05/29  hzg
	var accountName = trimStr($("#accountName").val().trim());//开户名
	var accountProvinceNo = $("#accountProvinceNo").val();//开户省编号
	var accountProvinceNameStr = $('#accountProvinceNo option:selected').text();//开户省名称
	if(accountProvinceNo != null && accountProvinceNo != '' && accountProvinceNo != undefined){
		$("#accountProvinceName").val(accountProvinceNameStr);
	}
	var accountProvinceName = $("#accountProvinceName").val();
	var accountCityNo = $("#accountCityNo").val();//开户市编号
	var accountCityNameStr = $('#accountCityNo option:selected').text();//开户市名称
	if(accountCityNo != null && accountCityNo != '' && accountCityNo != undefined){
		$("#accountCityName").val(accountCityNameStr);
	}
	var accountProvinceName = $("#accountCityName").val();
	var bankAccount = trimStr($("#bankAccount").val());//开户行
	var companyBankNo = trimStr($("#companyBankNo").val().trim());//银行账号
//	if(contractType==10307 && shoupaiType == 24603){
//		if(accountName == null || accountName == '' || accountName == undefined){
//			Dialog.alertInfo("请输入开户名");
//			return;
//		}else if(accountProvinceNo == null || accountProvinceNo == '' || accountProvinceNo == undefined){
//			Dialog.alertInfo("请选择开户省市");
//			return;
//		}else if(accountCityNo == null || accountCityNo == '' || accountCityNo == undefined){
//			Dialog.alertInfo("请选择开户省市");
//			return;
//		}else if(bankAccount == null || bankAccount == '' || bankAccount == undefined){
//			Dialog.alertInfo("请输入开户行");
//			return;
//		}else if(companyBankNo == null || companyBankNo == '' || companyBankNo == undefined){
//			Dialog.alertInfo("请输入银行账号");
//			return;
//		}
//	}
	if(accountName == null || accountName == undefined  || accountName == 'undefined'){
		accountName ='';
	}
	if(bankAccount == null || bankAccount == undefined || bankAccount == 'undefined'){
		bankAccount ='';
	}
	if(companyBankNo == null || companyBankNo == undefined  || companyBankNo == 'undefined'){
		companyBankNo ='';
	}
	$("#accountName").val(accountName);
	$("#bankAccount").val(bankAccount);
	$("#companyBankNo").val(companyBankNo);
	//end
	
	//日期 dateLifeStart dateLifeEnd
	var checkDateFlag = checkDate($("#dateLifeStart").val(),$("#dateLifeEnd").val()) ;//$("#dateLifeStart").val();
	if (contractType == 10302 &&  checkDateFlag <=0)  {
		Dialog.alertInfo("合同周期至少超过一年");
		return;
	}
//Add By GuoPengFei 2017/05/25 合规性 start	
	if($("#ContractTypeB2A").val() == undefined)
	{
		/*if (($('input[name="ContractTypeB2A"]:checked').val() == undefined
				|| $('input[name="ContractTypeB2A"]:checked').val() == "")) {
			Dialog.alertInfo("请选择是否乙类转甲类");
			return;
		}*/
	}	
//Add By GuoPengFei 2017/05/25 合规性 end
	
	var storeIds = "";
	$('input[name="storeIds"]').each(function() {
		storeIds = storeIds + "," + $(this).val();
	});
	
	if (storeIds != "" && storeIds.length > 0) {
		storeIds = storeIds.substring(1, storeIds.length);
	}
	//Add By GuoPengFei 2017/05/25 合规性 start
	var stores= new Array();  
	stores=storeIds.split(",");  
	
	if($("#ContractTypeB2A").val() == undefined)
	{	
		var contractTypeB2A = $('input[name="ContractTypeB2A"]:checked').val();
		if((contractTypeB2A == '20201') && (stores.length > 1))
		{//乙类转甲类
			Dialog.alertInfo("乙类转甲类时，门店信息中只能有一个门店");
			return;
		}
	}
	//Add By GuoPengFei 2017/05/25 合规性 end
	
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
		Dialog.alertInfo("联系人不能为空!");
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
		Dialog.alertInfo("联系电话不能为空!");
		return;
	}
	if(!ctphoneVeryCheck){
		Dialog.alertInfo("联系电话格式错误!");
		return;
	}
	var Atypecount= 0 ;
	//Add By GuoPengFei 2017/05/25 合规性 start
	//if((contractType!=10306 && contractType!=10301) && $("#ContractTypeB2A").val() == undefined)
	if((contractType!=10307  && contractType!=10306 && contractType!=10301) && $("#ContractTypeB2A").val() == undefined)		
	{
		var hasBStoreType = false;
		for (i=0;i<stores.length ;i++ ) 
		{ 
			var seltype=document.getElementById("storetype" + stores[i])
			  
			var seltypevalue= seltype.options[seltype.options.selectedIndex].value;
			if(seltypevalue == "")
			{
				Dialog.alertInfo("请选择门店资质等级");
				return;
			}
			
			if(seltypevalue == '19902')
			{//乙类门店
				hasBStoreType = true;
			}else{
				Atypecount++;
			}
			
			if((contractTypeB2A == '20201') && (seltypevalue == '19902'))
			{//乙类转甲类  && 乙类门店有
				Dialog.alertInfo("乙类转甲类时，门店资质等级只能为甲类");
				return;
			}
			
			var selbval = $("#storetypeBlst" + stores[i]).val();
			if((selbval == "") && (seltypevalue == '19902'))
			{
				Dialog.alertInfo("请选择门店资质等级为乙类的类别！");
				return;
			}
			
		} 
		if(contractType!=10307) {
			if(hasBStoreType == true) {//乙类门店有
				//$("#oaApproveType").val(17102);
				setoaApproveType(17102);
			} else {//乙类门店无
				//$("#oaApproveType").val(17101);
				setoaApproveType(17101);
			}
		}else{
			//hzg 合作模式为授牌 ，默认oa审批流程为标准17101(标准)
			$("#OAapproval").hide();
			$("input[name='oaApproveType'][value='17101']").attr("checked",true); //合作模式为授牌 ，默认oa审批流程为标准17101
		}
		var companyCityName=$('#companyCityName').val();
		if(companyCityName != '广州'){
			//只能有一个甲类
			if(Atypecount>1){
				Dialog.alertInfo("合同新签时，只能有一个为甲类门店！")
				return;
			}
		}
	}
	//Add By GuoPengFei 2017/05/25 合规性 end	
	
	
	
	// 前5项文件资料必须上传
	// for (var i = 0; i < oaArr.length - 1; i++) {
	// if (!oaArr[i].length) {
	// Dialog.alertInfo("请上传文件资料！");
	// return;
	// }
	// }

	if(!handlerFileInfo()){//草签时处理文件信息
		return;
	}
	
	// 只check合同 照片
	if($('#fileIdPhotoBox> .item-photo-list').length && $('#fileIdPhotoBox> .item-photo-list').length>0){}
	else{
		Dialog.alertInfo("请上传合同照片！");
		return;
	}
	/*if($('#noticeBox> .item-photo-list').length && $('#noticeBox> .item-photo-list').length>0){}
	else{
		Dialog.alertInfo("请上传《重要提示函》照片！");
		return;
	}*/
	/*if ($("#oaFileIdPhoto").val()=="") {
		Dialog.alertInfo("请上传合同照片！");
		return;
	}*/

	// 设置门店list
	Contract.setStoreList();

	var contractType = $("input[name='contractType']:checked").val();

	// 一、校验改合同中关联的门店是否已被锁定
	var checkUrl = BASE_PATH + "/contract/check";
	var checkParams = {
		storeIdArray : $("#storeIdArray").val(),
		contractType : contractType
		//Add By NingChao 2017/04/07 Start
		,originalContractNo : $("#originalContractNo").val()
		,agreementType : agreementType
		,agreementNo : $("#agreementNo").val()
		//Add By NingChao 2017/04/07 End
	};
	systemLoading("", true);

	// 校验输入信息
	if (Validator.validForm("contractAddForm")) {

		// check门店
		ajaxGet(checkUrl, checkParams, function(data) {

			// 新增合同
			httpPost('contractAddForm', url, function(data) {
				//Add By NingChao 2017/04/07 Start
				if($("#oldContractNo").val()!=undefined){
					contractGotoStore(BASE_PATH + "/store",BASE_PATH + "/store");
				}else{
				//Add By NingChao 2017/04/07 End
					location.href = BASE_PATH + "/contract";
				}
			}, function(data) {
				Dialog.alertError(data.returnMsg);
				systemLoaded();
			});

		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});

	} else {
		systemLoaded();
	}

};


/**
 * 设置门店list
 */
Contract.setStoreList = function() {

	var storeIds = "";
	$('input[name="storeIds"]').each(function() {
		storeIds = storeIds + "," + $(this).val();
	});
	if (storeIds != "" && storeIds.length > 0) {
		storeIds = storeIds.substring(1, storeIds.length);
	}
	$("#storeIdArray").val(storeIds);
};

//去掉前后空格
function trimStr(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

//判断图片数量是否一致
function checkPicNum(){
	oaFileIdBusiness.remove("");
	oaFileIdCards.remove("");
	oaFileIdPhotos.remove("");
	oaFileIdStores.remove("");
	oaFileIdInstalls.remove("");
	oaFileIdOthers.remove("");
	var oaFileLength =oaFileIdBusiness.length + oaFileIdCards.length + oaFileIdPhotos.length + oaFileIdStores.length + 
						oaFileIdInstalls.length + oaFileIdOthers.length;
	if(fileRecordMainIds.length != oaFileLength){
		return false;
	}
	return true;
}



$('#districtName').multiSelect('fetch', null);

/**
 * 门店资质等级变更时间
 * @param data
 * @returns
 */
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
//合作模式点击事件
Contract.changeContract = function(obj){//10302 B
	var dicCode = $(obj).val();
	$("#agreementTypeId").show();
	
	//选择合作模式 清空授牌类型radio
	$('input:radio[name=shoupaiType]').attr('checked',false);
//	10301	A
//	10302	B
//	10304	A转B
//	10306	D
//	10307	授牌
	if(dicCode==10301||dicCode==10306){//storetype
		$("#B2Achange").hide();
		$("#storeGrade").hide();
		$(".storeGradedata").hide();
		$("#OAapproval").show();
		$('input:radio[name=oaApproveType]').attr('checked',false);
		
		var SelectArr = $(".storetype");
		for (var i = 0; i < SelectArr.length; i++) {
			 SelectArr[i].options[0].selected = true; 
		}
		$(".c3").attr("value",'');
		$(".c3").hide();
		$(".cn3").val('');
		dafalutInfo();
	}else{
		$("#B2Achange").show();
		$("#storeGrade").show();
	}
	if(dicCode==10302||dicCode==10304){
		//$("#B2Achange").show();
		$("#storeGrade").show();
		$(".storeGradedata").show();
		$("#shoupaiTypeLi").hide();

		$("#OAapproval").hide();
		$('input:radio[name=ContractTypeB2A]').attr('checked',false);

		var SelectArr = $(".storetype");
		 for (var i = 0; i < SelectArr.length; i++) 
		 {
			 SelectArr[i].options[0].selected = true; 
		 }
		$(".c3").attr("value",'');
		$(".c3").hide();
		$(".cn3").val('');
		if(dicCode==10304){
			 $("#B2Achange").hide();
			 dafalutInfo();
		 }
		
	}else{
		$("#B2Achange").hide();
	}
	if(dicCode==10307){//授牌
		$("#storeGrade").show();
		$("#agreementTypeId").hide();
		$(".storeGradedata").show();
		$("#OAapproval").hide();
		$("#storeGrade").hide();
		$(".storeGradedata").hide();
		$("#shoupaiTypeLi").show();
		$("input[name='oaApproveType'][value='17101']").attr("checked",true); //合作模式为授牌 ，默认oa审批流程为标准17101
		
		
		dafalutInfo();
		/*var SelectArr = $(".storetype");
		for (var i = 0; i < SelectArr.length; i++) {
			SelectArr[i].options[0].selected = true; 
		}
		$(".c3").attr("value",'');
		$(".c3").hide();
		$(".cn3").val('');*/
	}else{
		$("#B2Achange").hide();
	}
};
//是否乙类转甲类 默认选 否
function dafalutInfo(){
	var selects=document.getElementsByName("ContractTypeB2A");
	for (var i=0;i<selects.length;i++){
		if(selects[i].value=="20202"){
			selects[i].checked=true;
			break;
		}
	}
}

function setoaApproveType(type)
{
	var selects=document.getElementsByName("oaApproveType");
	for (var i=0;i<selects.length;i++){
		if(selects[i].value==type){
			selects[i].checked=true;
			break;
		}
	}
}

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
	/*var oaFileIdBusiness = "";
	var oaFileIdCard = "";
	var oaFileIdPhoto = "";
	var oaFileIdStore = "";
	var oaFileIdInstall = "";
	var oaFileIdOther = "";*/
	var fileRecordMainIds = "";
	$("input[name=fileRecordMainIdHidden]").each(function(){
		/*var typeId=$(this).parent().find("input[name=fileTypeId]").val();
		var oaFileId = $(this).parent().find("input[name=oaFileId]").val();
		if($(this).val()=="" || oaFileId==""){
			Dialog.alertError("图片上传出现异常，请删除重新上传。");
			bol = false;
			return false;
		}
		if(typeId=="1"){
			oaFileIdBusiness +=","+oaFileId;
		}else if(typeId=="2"){
			oaFileIdCard +=","+oaFileId;
		}else if(typeId=="3"){
			oaFileIdPhoto +=","+oaFileId;
		}else if(typeId=="4"){
			oaFileIdStore +=","+oaFileId;
		}else if(typeId=="5"){
			oaFileIdInstall +=","+oaFileId;
		}else if(typeId=="6"){
			oaFileIdOther +=","+oaFileId;
		}*/
		if($(this).val()==""){
			Dialog.alertError("图片上传出现异常，请删除重新上传。");
			bol = false;
			return false;
		}
		fileRecordMainIds += ","+$(this).val();
	})
	/*if(oaFileIdBusiness!=""){
		oaFileIdBusiness = oaFileIdBusiness.substring(1);
	}
	if(oaFileIdCard!=""){
		oaFileIdCard = oaFileIdCard.substring(1);
	}
	if(oaFileIdPhoto!=""){
		oaFileIdPhoto = oaFileIdPhoto.substring(1);
	}
	if(oaFileIdStore!=""){
		oaFileIdStore = oaFileIdStore.substring(1);
	}
	if(oaFileIdInstall!=""){
		oaFileIdInstall = oaFileIdInstall.substring(1);
	}
	if(oaFileIdOther!=""){
		oaFileIdOther = oaFileIdOther.substring(1);
	}*/
	if(fileRecordMainIds!=""){
		fileRecordMainIds = fileRecordMainIds.substring(1);
	}
	
	/*$("#oaFileIdBusiness").val(oaFileIdBusiness);
	$("#oaFileIdCard").val(oaFileIdCard);
	$("#oaFileIdPhoto").val(oaFileIdPhoto);
	$("#oaFileIdStore").val(oaFileIdStore);
	$("#oaFileIdInstall").val(oaFileIdInstall);
	$("#oaFileIdOther").val(oaFileIdOther);*/
	$("#fileRecordMainIds").val(fileRecordMainIds);
	
	return bol;
} 
function checkDate(dateLifeStart,dateLifeEnd){
	var firstDate=new Date(Date.parse(dateLifeStart.replace(/-/g, '/'))) ;
	var secondDate=new Date(Date.parse(dateLifeEnd.replace(/-/g, '/'))) ;
	
	var firstDate = new Date(firstDate.getFullYear() ,firstDate.getMonth()+1,firstDate.getDate());
	var secondDate = new Date(secondDate.getFullYear() ,secondDate.getMonth()+1,secondDate.getDate());
	var years=0;
	while (firstDate.setFullYear(firstDate.getFullYear() + 1) <= secondDate){
	    years++;
	}
	return  years;
	//alert("相差" + years + "年");
}

/**
 * 选择省之后加载城市列表
 * @param cityId
 * @param districtId
 * @returns
 */
function linkAccountCity(cityId, districtId)
{
	$("#"+cityId+"").change(function(){	
		$("#"+districtId+" option").remove();
		
	    var cityNo = $("#"+cityId).val();
	    if(cityNo == null || cityNo == "")
    	{
	    	var html = "<option value='' selected>请选择城市</option>";
	    	$('#'+districtId).append(html);
	    	return false;
    	}
	    
	    var url = BASE_PATH +  "/cityCascade/city";
	    var params = {provinceNo:cityNo};
	    ajaxGet(url, params, function(data) {
			var result = "<option value=''>请选择</option>";
			$.each(data.returnValue, function(n, value) {
				result += "<option value='" + value.cityNo + "'>"
						+ value.cityName + "</option>";
			});
			$("#"+districtId).html('');
			$("#"+districtId).append(result);
		}, function() {
		});	   
	})
}
   
//授牌类型点击事件 2019/05/29 hzg
Contract.changeShoupaiType = function(shoupaiType){//
	//合作模式
	var contractType = $('input[name="contractType"]:checked').val();
	//授牌类型
	var shoupaiType = $('input[name="shoupaiType"]:checked').val();
	if(contractType == 10307 && shoupaiType == 24603){//合作模式为授牌，授牌类型为渠道
		//合作模式选择授牌   给开户信息添加 *
//		$("#accountNameLabel").addClass("required");//开户名
//		$("#accountProvinceNoLabel").addClass("required");//开户省市
//		$("#bankAccountLabel").addClass("required");//开户行
//		$("#companyBankNoLabel").addClass("required");//开户账号
	}else{
		//合作模式选择不是授牌   开户信息去掉 *
		$("#accountNameLabel").removeClass("required");//开户名
		$("#accountProvinceNoLabel").removeClass("required");//开户省市
		$("#bankAccountLabel").removeClass("required");//开户行
		$("#companyBankNoLabel").removeClass("required");//开户账号
	}
		
};
   
   