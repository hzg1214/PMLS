/** ************************公共部分***************************** */

$(function() {

	arraySet();

	// 市区域板块
	//linkage();
	linkagePartyB();
	linkageActualOperation();
	//Add 2017/04/10 cning 续签编辑不显示原合同编号--->
	if($("#originalContractNo").val()=="")
	{
		$("#ocn").hide();	
	}
	//Add 2017/04/10 cning 续签编辑不显示原合同编号<---
	dafalutInfo();
	
	//加载城市/区域
	linkDistrict("cityNo","districtNo");
	linkAccountCity("accountProvinceNo","accountCityNo");//加载省市城市
	Contract.changeShoupaiType();
	$("#errorTip").html("").hide();
	$("#errorTip2").html("").hide();
});

$(function(){
	//上传开始
	var options = {
		"url":BASE_PATH + '/file/uploadCommonFile',
		"oaUrl":BASE_PATH + "/contracts/oa/upload",
		"isDeleteImage":false,//删除时校验checkEditImage
		"isAddImage":true,   //添加时校验checkEditImage
		"isCommonFile":true,  //公共上传文件
		"afterDelete":true //是否需要后删除图片
	};
	photoUploader(options,null,null,null);
	//上传结束
	//开始轮询
//	fileInterval();
	//轮询结束
});

Contract = function() {
};

var fileRecordMainIds = new Array();

/*// 定义数组，存放ＯＡ附件
var oaFileIdBusinesss = new Array();
var oaFileIdCards = new Array();
var oaFileIdPhotos = new Array();
var oaFileIdStores = new Array();
var oaFileIdInstalls = new Array();
var oaFileIdOthers = new Array();
var oaArr = [ oaFileIdBusinesss, oaFileIdCards, oaFileIdPhotos, oaFileIdStores,
		oaFileIdInstalls, oaFileIdOthers ];
var tagArr = [ 'Business', 'Card', 'Photo', 'Store', 'Install', 'Other' ];
var picArr = [ 'yingye.jpg', 'faren.jpg', 'hetong.jpg', 'store.png',
		'anzhang.jpg', 'qita.jpg' ];*/

// 初始化数组设值
function arraySet() {

	var ary = $("#fileRecordMainIds").val().split(",");
	for (var i = 0; i < ary.length; i++) {
		fileRecordMainIds.push(ary[i]);
	}

	/*//
	var oaFileIdBusiness = $("#oaFileIdBusiness").val().split("|");
	for (var i = 0; i < oaFileIdBusiness.length; i++) {
		oaFileIdBusinesss.push(oaFileIdBusiness[i]);
		$("#oaFileIdBusiness" + i).val(oaFileIdBusiness[i]);
	}
	//
	var oaFileIdCard = $("#oaFileIdCard").val().split("|");
	for (var i = 0; i < oaFileIdCard.length; i++) {
		oaFileIdCards.push(oaFileIdCard[i]);
		$("#oaFileIdCard" + i).val(oaFileIdCard[i]);
	}
	//
	var oaFileIdPhoto = $("#oaFileIdPhoto").val().split("|");
	for (var i = 0; i < oaFileIdPhoto.length; i++) {
		oaFileIdPhotos.push(oaFileIdPhoto[i]);
		$("#oaFileIdPhoto" + i).val(oaFileIdPhoto[i]);
	}
	//
	var oaFileIdStore = $("#oaFileIdStore").val().split("|");
	for (var i = 0; i < oaFileIdStore.length; i++) {
		oaFileIdStores.push(oaFileIdStore[i]);
		$("#oaFileIdStore" + i).val(oaFileIdStore[i]);
	}
	//
	var oaFileIdInstall = $("#oaFileIdInstall").val().split("|");
	for (var i = 0; i < oaFileIdInstall.length; i++) {
		oaFileIdInstalls.push(oaFileIdInstall[i]);
		$("#oaFileIdInstall" + i).val(oaFileIdInstall[i]);
	}
	//
	var oaFileIdOther = $("#oaFileIdOther").val().split("|");
	for (var i = 0; i < oaFileIdOther.length; i++) {
		oaFileIdOthers.push(oaFileIdOther[i]);
		$("#oaFileIdOther" + i).val(oaFileIdOther[i]);
	}*/
}

/**
 * 修改
 */
Contract.update = function() {
	//验证图片数量

	/*if(!checkPicNum()){
		Dialog.alertError("图片上传出现异常，请删除重新上传。");
		return false;
	};*/
	
	var id = $("#id").val();
	var url = BASE_PATH + '/contract/' + id;

	var storeIds = "";
	$('input[name="storeIds"]').each(function() {
		storeIds = storeIds + "," + $(this).val();
	});
	if (storeIds != "" && storeIds.length > 0) {
		storeIds = storeIds.substring(1, storeIds.length);
	}
	if ($("#partyBcityNo").val() == "" || $("#partyBdistrictNo").val() == "") {
		Dialog.alertInfo("请选择公司地址的城市区域");
		return false;
	}

	/*if (($.trim($('#dateLifeStart').val()) != '' && $.trim($('#dateLifeEnd')
			.val()) != '')) {
		if ($.trim($('#dateLifeStart').val()) > $.trim($('#dateLifeEnd').val())) {
			Dialog.alertInfo("合同生效日期必须小于到期日期");
			return false;
		}
	}*/

	var contractType = $("#contractType").val();
	if (!contractType) {	
		//Mod 2017/04/10 cning --->
		//Dialog.alertInfo("请选择合作协议书类型");
		Dialog.alertInfo("请选择合作模式");
		//Mod 2017/04/10 cning <---
		return false;
	}
	
	var shoupaiType = $('input[name="shoupaiType"]:checked').val();
	if(contractType ==10307) {
		if (($('input[name="oaApproveType"]:checked').val() == undefined
				|| $('input[name="oaApproveType"]:checked').val() == "")) {
			Dialog.alertInfo("请选择OA审批流程类别");
			return;
		}
		//授牌类型 hzg
		if (shoupaiType == undefined || shoupaiType == "") {
			Dialog.alertInfo("请选择授牌类型");
			return;
		}
	}
	
	
	//合作模式为授牌且授牌类型为渠道  开户信息为必填 2019/05/29  hzg
	var accountName = trimStr($("#accountName").val());//开户名
	
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
	var companyBankNo = trimStr($("#companyBankNo").val());//银行账号
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
	
	$("#storeIdArray").val(storeIds);
	if ($("#storeIdArray").val() == "") {
		Dialog.alertInfo("请关联门店信息");
		return false;
	}

	//Add By GuoPengFei 2017/05/25 合规性 start
	var stores= new Array();  
	stores=storeIds.split(",");  
	var Atypecount= 0 ;
	if((contractType!=10306 && contractType!=10301 && contractType!=10307) )		
	{
		if($("#ContractTypeB2A").val() == undefined)
		{
			/*if (($('input[name="ContractTypeB2A"]:checked').val() == undefined
					|| $('input[name="ContractTypeB2A"]:checked').val() == "")) {
				Dialog.alertInfo("请选择是否乙类转甲类");
				return;
			}*/
		}	
		
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
			var contractTypeB2A = $('input[name="ContractTypeB2A"]:checked').val();
			if(contractTypeB2A == '20201' && hasBStoreType == true)
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
			if(hasBStoreType == true){//乙类门店有
				//$("#oaApproveType").val(17102);
				setoaApproveType(17102);
			}else{//乙类门店无
				//$("#oaApproveType").val(17101); 
				setoaApproveType(17101);
			}
		}
		var companyCityName=$("#companyCityName").val();
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
	// return false;
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
		Dialog.alertInfo("请上传重要提示函照片！");
		return;
	}*/
	/*if ($("#oaFileIdPhoto").val()=="") {
		Dialog.alertInfo("请上传合同照片！");
		return;
	}*/

	// var contractType = $("input[name='contractType']:checked").val();

	// 一、校验改合同中关联的门店是否已被锁定
	// var checkUrl = BASE_PATH + "/contract/check";
	// var checkParams = {
	// storeIdArray : $("#storeIdArray").val(),
	// contractType : contractType
	// };

	var params = $("#contractEditForm").serialize();

	systemLoading("", true);
	// 校验输入信息
	if (Validator.validForm("contractEditForm")) {
		// check门店
		// ajaxGet(checkUrl, checkParams, function(data) {
		httpPut(url, params, function(data) {
			location.href = BASE_PATH + "/contract";
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});

		// }, function(data) {
		// Dialog.alertError(data.returnMsg);
		// systemLoaded();
		// });

	} else {
		systemLoaded();
	}
};
//判断图片数量是否一致
function checkPicNum(){
	oaFileIdBusinesss.remove("");
	oaFileIdCards.remove("");
	oaFileIdPhotos.remove("");
	oaFileIdStores.remove("");
	oaFileIdInstalls.remove("");
	oaFileIdOthers.remove("");
	var oaFileLength =oaFileIdBusinesss.length + oaFileIdCards.length + oaFileIdPhotos.length + oaFileIdStores.length + 
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
		$("#"+btype).attr("value",20003);
	}
	else
	{
		$("#"+btypename).hide();
		$("#"+btypename).attr("value",'');
		$("#"+btype).attr("value",'');
	}
}

//合作模式初始化事件
function dafalutInfo(){
	var contractType=document.getElementById('contractType').value;
		if(contractType==10301||contractType==10306){
			$("#storeGrade").hide();
			$(".storeGradedata").hide();
			$("#B2Achange").hide();
		}
		if(contractType==10302||contractType==10304){
			$("#OAapproval").hide();
			if(contractType==10304)
			{
				$("#B2Achange").hide();
			}
		}
		if(contractType==10307){
			$("#OAapproval").show();
			$("#agreementTypeId").hide();
			$("#storeGrade").hide();
			$(".storeGradedata").hide();
			$("#B2Achange").hide();
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
   
//草签时处理文件信息
function handlerFileInfo(){
	var bol = true;
	/*//营业证,身份证,合同照片,门店照片,房友系统申请安装单,其它
	var oaFileIdBusiness = "";
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
	if(contractType != 10307){//合作模式不为授牌,隐藏授牌类型
		$('#shoupaiTypeLi').hide();
	}else{
		//授牌类型
		$('#shoupaiTypeLi').show();
		//oa审批流程  隐藏
		$('#OAapproval').hide();
		var shoupaiType = $('input[name="shoupaiType"]:checked').val();
		if(contractType == 10307 && shoupaiType == 24603){//合作模式为授牌，授牌类型为渠道
			//合作模式选择授牌   给开户信息添加 *
//			$("#accountNameLabel").addClass("required");//开户名
//			$("#accountProvinceNoLabel").addClass("required");//开户省市
//			$("#bankAccountLabel").addClass("required");//开户行
//			$("#companyBankNoLabel").addClass("required");//开户账号
		}else{
			//合作模式选择不是授牌   开户信息去掉 *
			$("#accountNameLabel").removeClass("required");//开户名
			$("#accountProvinceNoLabel").removeClass("required");//开户省市
			$("#bankAccountLabel").removeClass("required");//开户行
			$("#companyBankNoLabel").removeClass("required");//开户账号
		}
	}
		
};


//去掉前后空格
function trimStr(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}