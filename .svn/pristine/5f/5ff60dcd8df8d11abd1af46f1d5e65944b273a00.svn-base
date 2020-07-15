
/**************************公共部分***************************** */
ContractChangeCom = function() {
};

//点击终止类型显示 上传附件 ,反之则隐藏
function endChange() {  
    var selectValue = $("select[name='stopType']").val();  
    if(selectValue == 16401 ){  
        $("#stopContractBoxPc").show();
        $("#cancellateBoxPc").show();
        $("#surrenderBoxPc").hide();
    }  
    if(selectValue == 16406){
    	$("#stopContractBoxPc").show();
        $("#surrenderBoxPc").hide();  
        $("#cancellateBoxPc").hide();  
    }  
    if(selectValue == 16407){  
    	$("#surrenderBoxPc").show(); 
    	$("#stopContractBoxPc").hide();
    	 $("#cancellateBoxPc").hide();
    }  
}  

function divClick(obj,i,j){
	var depositBalance = $("#"+i).val();
	var receivedAmount = $("#"+j).val();
	var receivedAmount2 = "";
	if (receivedAmount != null && receivedAmount != undefined && receivedAmount != "") { 
		if(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(receivedAmount)){
			if (receivedAmount.indexOf(".")< 0) {
				receivedAmount2=receivedAmount+".00";
			}else{
				receivedAmount2=receivedAmount;
			}
		} else {
			$("#"+j).val("");
		}
	} else{
		$("#"+j).val("");
	}
	if(depositBalance == 21303){
		$("#"+obj).attr("disabled",false);
		 $("#"+obj).css("background-color", "");
		 $("#"+obj).val("");
		/*$("#"+obj).focus();*/
	}else if(depositBalance == 21301){
		$("#"+obj).attr("disabled",true);
		$("#"+obj).val(receivedAmount2);
		$("#"+obj).css("background-color", "#F9F9F9");
	}else if(depositBalance == 21302){
		$("#"+obj).attr("disabled",true);
		$("#"+obj).val("0.00");
		$("#"+obj).css("background-color", "#F9F9F9");
	}else{
		$("#"+obj).attr("disabled",true);
		$("#"+obj).val("");
		$("#"+obj).css("background-color", "#F9F9F9");
	}
}
function myChange(obj,i,j){
	var depositBalance = $("#"+i).val();
	var receivedAmount = $("#"+j).val();
	var receivedAmount2 = "";
	if (receivedAmount != null &&  receivedAmount != undefined &&  receivedAmount !="") { 
		if(/^[0-9]+([.]{1}[0-9]+){0,1}$/.test(receivedAmount)){
			if (receivedAmount.indexOf(".")< 0) {
				receivedAmount2=receivedAmount+".00";
			}else{
				receivedAmount2=receivedAmount;
			}
		}else {
			$("#"+j).val("");
		}
	}else{
		$("#"+j).val("");
	}
	 if(depositBalance == 21301){
		$("#"+obj).attr("disabled",true);
		$("#"+obj).val(receivedAmount2);
		$("#"+obj).css("background-color", "#F9F9F9");
	}
	$("#"+j).val(receivedAmount2);
}


/**
 * 上传图片
 */
function upload(self, type, num) {
	systemLoading("", true);
	var formId = "";
	var showImgId = "";

	formId = "contractChange" + tagArr[type - 1] + "Form" + num;
	showImgId = "#show" + tagArr[type - 1] + "Img" + num;

	var fileSize = 0;

	var photoExt = self.value.substr(self.value.lastIndexOf(".")).toLowerCase();// 获得文件后缀名
	if (photoExt != '.jpg' && photoExt != '.png' && photoExt != '.pdf') {
		Dialog.alertInfo("请上传后缀名为jpg、png、pdf的文件!");
		self.value = null;
		systemLoaded();
		return;
	}

	var file = null;
	var filePath = null;

	try {
		var isIE = /msie/i.test(navigator.userAgent) && !window.opera;

		if (isIE && !self.files) {
			filePath = self.value;
			var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
			file = fileSystem.GetFile(filePath);
			fileSize = file.Size;
		} else {
			file = self.files[0];
			fileSize = file.size;
		}
		fileSize = Math.round(fileSize / 1024 * 100) / 100 / 1024; // 单位为KB
	} catch (e) {
	}
	if (fileSize > 5) {
		Dialog.alertInfo("所选文件不能大于5M！");
		self.value = null;
		systemLoaded();
		return;
	} else {
		if (window.FileReader) {
			var reader = new FileReader();
			reader.readAsDataURL(file);
			reader.onload = function(e) {
				$(showImgId).find('img').first().attr("src", this.result);// this.result

				$(showImgId).find('.fileName').first().text(file.name);
			};
		} else {
			// IE下获取文件地址回显
			var url;
			var fileobj = $(showImgId).parents().find('#btnfileUpload');
			fileobj.select();
			fileobj.blur();
			url = document.selection.createRange().text;
			$(showImgId).find('img').first().attr("src", url);// this.result
			var fileName = filePath;
			var startIndex = fileName.lastIndexOf("\\");
			var endIndex = fileName.lastIndexOf(".");
			var fileName1 = fileName.substr(startIndex + 1, endIndex);
			$(showImgId).find('.fileName').first().text(fileName1);
		}

		var fyurl = BASE_PATH + '/file/uploadCommonFile';
		/*// 文件上传，先上传ＯＡ文件系统,在上传房友文件系统
		ContractChangeCom.uploadToOa(formId, self, type, num, fyurl);*/

		//文件上传
		httpPost(formId, fyurl, function(data) {
			// 文件上传成功后，数据组织
			ContractChangeCom.setUploaded(type, data, num);
			systemLoaded();
			// 移除Input重新添加
			removeInput(self, type, num);
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
			// 移除图片
			$(self).closest("form").parent().find("a").first().click();
			$(self).closest("form").find('em').find('img').first().click();
		});
	}
};

/*// 文件上传，到OA文件系统
ContractChangeCom.uploadToOa = function(formId, self, type, num, fyurl) {

	var url = BASE_PATH + "/stopcontract/oa/upload";

	httpPost(formId, url, function(data) {
		var picId = null;
		// 返回的文件Id
		var fileRecordMainId = data.returnValue;
		if(data.picId !=null &&data.picId != ""){
			picId = data.picId;
		}
		// 如果为空则弹出提示
		if (!fileRecordMainId) {
			Dialog.alertError("图片上传至OA服务器失败，请重新上传或检查OA权限");
			systemLoaded();
			// 移除图片
			$(self).closest("form").parent().find("a").first().click();
			$(self).closest("form").find('em').find('img').first().click();
			return;

		} else {

			// 1、文件上传，房友文件系统
			httpPost(formId, fyurl, function(data) {
				
				// 文件上传成功后，数据组织
				ContractChangeCom.setUploaded(type, data, num);
				
				if(picId != null){
					//更新到页面
					contractChangePicIds.push(picId);
					// 给每个区域上传附件的文件设值
					$("#contractChangePicId" + tagArr[type - 1] + num).val(picId);
					$("#contractChangePicIds").val(contractChangePicIds);
				}
				
				systemLoaded();

				// 移除Input重新添加
				removeInput(self, type, num);

			}, function(data) {
				Dialog.alertError(data.returnMsg);
				systemLoaded();
				// 移除图片
				$(self).closest("form").parent().find("a").first().click();
				$(self).closest("form").find('em').find('img').first().click();
			});
		}

	}, function(data) {
		Dialog.alertError("图片上传至OA服务器失败，请重新上传或检查OA权限");
		systemLoaded();
		// 移除图片
		$(self).closest("form").parent().find("a").first().click();
		$(self).closest("form").find('em').find('img').first().click();
	});
};*/

// 文件上传成功后，数据组织
ContractChangeCom.setUploaded = function(type, data, num) {

	fileRecordMainIds.remove($("#fileRecordMainId" + tagArr[type - 1] + num)
			.val());

	fileRecordMainIds.push(data.fileRecordMainId);
	$("#fileRecordMainIds").val(fileRecordMainIds);

	$("#fileRecordMainId" + tagArr[type - 1] + num).val(data.fileRecordMainId);
};

//移除input，重新添加
function removeInput(self, type, num) {
	var item = $(self).parent();
	$(self).remove();
	item.append("<input class='inputstyle' type='file' id='btnfileUpload' name='fileName' onchange='upload(this, "
					+ type + "," + num +");'>");
}

/**
 * 移除
 * 
 */
function delFile(obj, type, num) {

	var num2 = $(obj).closest('form').parent().children('form').size();
//	// 删除旧值
//	var index = oaArr[type - 1].indexOf($("#oaFileId" + tagArr[type - 1] + num)
//			.val());
//	if (index >= 0) {
//		oaArr[type - 1].splice(index, 1);
//	}
//	$("#oaFileId" + tagArr[type - 1]).val(oaArr[type - 1]);
	
	//移除记录
	fileRecordMainIds.remove($("#fileRecordMainId" + tagArr[type - 1] + num).val());
	$("#fileRecordMainIds").val(fileRecordMainIds);

	/*// 删除图片ID集合中的旧值
	contractChangePicIds.remove($("#contractChangePicId" + tagArr[type - 1] + num).val());
	$("#contractChangePicIds").val(contractChangePicIds);*/
	
	if (num2 <= 1) {
		resetImg(type, num);
		$("#fileRecordMainId" + tagArr[type - 1] + num).removeAttr("value");
		//$("#contractChangePicId" + tagArr[type - 1] + num).removeAttr("value");
	} else {
		$(obj).closest("form").remove();
	}
}

function resetImg(type, num) {
	$("#show" + tagArr[type - 1] + 'Img' + num + ' a')
			.first()
			.html(
					"<img src='"
							+ BASE_PATH
							+ "/meta/images/"
							+ picArr[type - 1]
							+ "' alt='' class='img'><br /><label class='fileName'></label>");
}

//新增 附件
function addPhotoFileRow(obj, type, fileType) {

	var num = RndNum(4);// 随机数
	var num2 = $(obj).parent().children('form').size();
	if (num2 > 9) {
		Dialog.alertInfo("最多添加10个附件");
		return;
	}
	var html = "";
	html += "<form id='contractChange" + tagArr[type - 1] + "Form" + num + "' >";
	html += "<input type='hidden'  id ='contractChangePicId" + tagArr[type - 1] + num
			+ "'  name ='contractChangePicId'>";

	// fangyou
	html += "<input type='hidden' name='fileRecordMainId' id='fileRecordMainId"
			+ tagArr[type - 1] + num + "'  />";

	// OA
	html += "<input type='hidden' name='oaFileId' id='oaFileId"
			+ tagArr[type - 1] + num + "'  />";
	// 设置文件类型
    if(fileType != null && fileType != '')
    {
    	html += "<input type='hidden' name='fileTypeId' value='" + fileType + "' />";
    }else
    {
    	html += "<input type='hidden' name='fileTypeId' value='100" + type + "' />";
    }

	html += "<input type='hidden' name='fileSourceId' value='4' />";
	html += "<ul class='list-inline form-inline'>";
	html += "<li>";
	html += "<div class='form-group'>";
	html += "<label class='fw-normal w120 text-right'></label>";

	html += "<span id='show" + tagArr[type - 1] + "Img" + num + "'>";

	html += "<a href='javascript:void(0)' target=' _blank' style='  display: inline-block;  height: auto; vertical-align: -webkit-baseline-middle;'>";

	html += "<img src='" + BASE_PATH + "/meta/images/" + picArr[type - 1]
			+ "' alt='' class='img'><br/><label class='fileName'></label>";

	html += " </a>";

	html += " <em><img  id='imgDelId" + num + "'  src='" + BASE_PATH
			+ "/meta/images/delete1.png' onclick='delFile(this," + type + ","
			+ num + ")'></em>";

	html += "</span>";
	html += "</div>";
	html += " </li>";
	html += " <li><span class='fc-warning'>(总大小不超过5M)</span></li>";
	html += " </ul>";
	html += "<ul class='list-inline form-inline pdb20'>";
	html += "<li>";
	html += "<div class='form-group'>";
	html += "<label class='fw-normal w100 text-right'></label>";
	html += " <div class='form-group'>";
	html += " <span class='abso_file btn btn-default'>浏览...</span>";

	html += " <input class='inputstyle' type='file' id='btnfileUpload' name='fileName' onchange='upload(this, "
			+ type + "," + num +");'>";

	html += "</div>";
	html += "</div>";
	html += "</li>";
	html += " </ul>";

	$(obj).parent().append(html);
}
/**
 * 获取随机数
 */
function RndNum(n) {
	var rnd = "";
	for (var i = 0; i < n; i++) {
		if (i == 0) {
			var f = Math.floor(Math.random() * 10);
			if (f == 0) {
				rnd = rnd + 1;
			} else {
				rnd = rnd + f;
			}
		} else {
			rnd = rnd + Math.floor(Math.random() * 10);
		}
	}
	return rnd;
}

/**
 * 校验下拉框和图片数据是否填入
 */
 ContractChangeCom.chkCombobox = function() {
	 
	 /*if($('input[name="storeChk"]').prop('checked')){
		var ssss = $("#isReleaseFlag").val();
		$("#isReleaseFlag").val("16801");
		 
	 }else{
		$("#isReleaseFlag").val("16802");
	 } */ 
	 /*var count = 0;
	 var checkArrays = $('input[name="storeChk"]');
	 for(var i=0;i<checkArrays.length;i++){
		 if(checkArrays[i].checked == true){
			 count++;
		 }
	 }
	 if(count==checkArrays.length){
		 $("#isReleaseFlag").val("16801");
		 
	 }else{
		 $("#isReleaseFlag").val("16802");
	 }*/
		 
	// 变更类型
	/*var changeType = $('#changeType').val();
	if (changeType == null || changeType == "") {
		Dialog.alertInfo("请选择【变更类型】！");
		return false;
	}*/

	// 终止类型
	var stopType = $('#stopType').val();
	if (stopType == null || stopType == "") {
		Dialog.alertInfo("请选择【终止类型】！");
		return false;
	}
	/*// 是否一并解除
	var isReleaseFlag = $('#isReleaseFlag').val();
	if (isReleaseFlag == null || isReleaseFlag == "") {
		Dialog.alertInfo("请选择【是否一并解除】！");
		return false;
	}

	// 三方装修合同情况
	var decorateCNTSituate = $('#decorateCNTSituate').val();
	if (decorateCNTSituate == null || decorateCNTSituate == "") {
		Dialog.alertInfo("请选择【三方装修合同情况】！");
		return false;
	}

	// 装修情况
	var decorateSituate = $('#decorateSituate').val();
	if (decorateSituate == null || decorateSituate == "") {
		Dialog.alertInfo("请选择【装修情况】！");
		return false;
	}

	// 翻牌模式
	var flopMode = $('#flopMode').val();
	if (flopMode == null || flopMode == "") {
		Dialog.alertInfo("请选择【翻牌模式】！");
		return false;
	}

	// 是否A转B
	var aConvertBFlag = $('#aConvertBFlag').val();
	if (aConvertBFlag == null || aConvertBFlag == "") {
		Dialog.alertInfo("请选择【是否B转A】！");
		return false;
	}

	// 三方装修合同情况
	var decorateCNTSituate = $('#decorateCNTSituate').val();
	if (decorateCNTSituate == null || decorateCNTSituate == "") {
		Dialog.alertInfo("请选择【三方装修合同情况】！");
		return false;
	}*/
	/*var isPermanentValue = $('input[name="depositBalance"]:checked ').val();  
	if(isPermanentValue == 21303){
		var depositBackMoney =$("#depositBackMoney").val();
		var reg=/^[1-9]\d*$|^0$/; 
		if(reg.test(depositBackMoney)== false){
			Dialog.alertInfo("保证金退还金额只能填写数字！");
			return false;
		}*/
	var flag0 = true;
	var flag1 = true;
	var flag2 = true;
	var flag3 = true;
	var flag4 = true;
	$("input[name = storeChk]").each(function(){
		if ($(this).prop('checked')){
			var storeId =$(this).val();
			var reg=/^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$/; 
			var receivedAmount = $('input[name="receivedAmount'+storeId+'"]').val();
			if(receivedAmount== ""){
				flag1 = false;
			}
			if(reg.test(receivedAmount)== false){
				flag0 = false;
			}
			var depositBalance = $("select[name='depositBalance"+storeId+"']").val();  
			if(depositBalance == ""){
					flag2 = false;
			}
			if(depositBalance == 21301){
				var depositBackMoney = $('input[name="depositBackMoney'+storeId+'"]').val(receivedAmount);
			}
			if(depositBalance == 21302){
				var depositBackMoney = $('input[name="depositBackMoney'+storeId+'"]').val("");
			}
			if(depositBalance == 21303){
				var depositBackMoney = $('input[name="depositBackMoney'+storeId+'"]').val();
				if(depositBackMoney.trim()== null || depositBackMoney.trim() == "" ){
					flag3 = false;
				}
			if(reg.test(depositBackMoney.trim())== false){
					flag4 = false;
				}
				
			}
		}
	});
	if(!flag1){
		Dialog.alertInfo("请填写已收保证金数额！");
		return false;
	}
	if(!flag0){
		Dialog.alertInfo("已收保证金只能填写数字且小数位不能超过两位！");
		return false;
	}
	if(!flag2){
		Dialog.alertInfo("请选择保证金余额处理方式！");
		return false;
	}
	if(!flag3){
		Dialog.alertInfo("请填写保证金退还金额！");
		return false;
	}
	if(!flag4){
		Dialog.alertInfo("保证金退还金额只能填写数字且小数位不能超过两位！");
		return false;
	}
	
	
	/*$('input[attr="receiveAmount"]').each(function(i, o){
		var receiveAmount = $.trim($(o).val());
		if(receiveAmount == null || receiveAmount.trim() ==""){
			Dialog.alertInfo("请填写已收保证金数额！");
			return false;
		}
		var reg=/^[1-9]\d*$|^0$/;
		if(reg.test(receiveAmount)== false){
			Dialog.alertInfo("已收保证金数额只能填写数字！");
			return false;
		}
	});
	$('input[attr="depositBalance"]').each(function(i, o){
		var depositBalance = $.trim($(o).val());
		if(depositBalance == "") {
			Dialog.alertInfo("请选择保证金余额处理方式！");
			return false;
		}
	});
	
	$('input[attr="depositBackMoney"]').each(function(i, o){
		var depositBackMoney = $.trim($(o).val());
		if(depositBackMoney == null || depositBackMoney.trim() ==""){
			Dialog.alertInfo("请填写保证金退还金额！");
			return false;
		}
		var reg=/^[1-9]\d*$|^0$/;
		if(reg.test(depositBackMoney)== false){
			Dialog.alertInfo("保证金退还金额只能填写数字！");
			return false;
		}
	});*/
		
		
	
	var selectValue = $("select[name='stopType']").val();
	if(selectValue == 16401 || selectValue == 16406 ){ 
		 //终止合作协议书图片校验
		 if($('#stopContractBox> .item-photo-list').length && $('#stopContractBox> .item-photo-list').length>0){}
		 else{
			 Dialog.alertInfo("请上传终止合作协议书/单方解除函照片！");
			 return;
		 }
	}
	if(selectValue  == 16407 ){ 
		 //三方解约协议图片校验
		 if($('#surrenderBox> .item-photo-list').length && $('#surrenderBox> .item-photo-list').length>0){}
		 else{
			 Dialog.alertInfo("请上传合同权利义务转让三方协议书照片！");
			 return;
		 }
	}
	 //保证金收据图片校验
	 if($('#receiptBox> .item-photo-list').length && $('#receiptBox> .item-photo-list').length>0){}
	 else{
		 Dialog.alertInfo("请上传保证金收据照片！");
		 return;
	 }
	 //保证金收据图片校验
	 if($('#storePhoto> .item-photo-list').length && $('#storePhoto> .item-photo-list').length>0){}
	 else{
		 Dialog.alertInfo("请上传门店照片！");
		 return;
	 }
	 //已付装修款退换证明图片校验
	/* if($('#returnProveBox> .item-photo-list').length && $('#returnProveBox> .item-photo-list').length>0){}
	 else{
		 Dialog.alertInfo("请上传已付装修款退换证明照片！");
		 return;
	 }*/
	 if(selectValue == 16401){
		 //注销证明图片校验
		 if($('#cancellateBox> .item-photo-list').length && $('#cancellateBox> .item-photo-list').length>0){}
		 else{
			 Dialog.alertInfo("请上传注销证明照片！");
			 return;
		 }
	 }
	

	/*var stopContract = "";
	var surrender = "";
	var receipt = "";
	var returnProve = "";
	var cancellate = "";
	$(".thumb-xs-box").find("input[name=contractChangePicId]").each(function(){
		var contractChangePicId = $(this).val();
		var fileTypeId = $(this).parent().find("input[name=fileTypeId]").val();
		if(contractChangePicId != "" && contractChangePicId != null && contractChangePicId != undefined){
			if(fileTypeId=="1001"){
				stopContract = contractChangePicId;
			}else if(fileTypeId=="1002"){
				surrender = contractChangePicId;
			}else if(fileTypeId=="1003"){
				receipt = contractChangePicId;
			}else if(fileTypeId=="1004"){
				returnProve = contractChangePicId;
			}else if(fileTypeId=="1005"){
				cancellate = contractChangePicId;
			}
		}
	})
	//终止合作协议书图片校验
	if (stopContract == "" || stopContract == null || stopContract == undefined) {
		Dialog.alertInfo("请上传终止合作协议书照片！");
		return false;
	}
	
	//三方解约协议图片校验
	if (surrender == null || surrender == "" || surrender == undefined) {
		Dialog.alertInfo("请上传三方解约协议照片！");
		return false;
	}
	
	//保证金收据图片校验
	if (receipt == null || receipt == "" || receipt == undefined) {
		Dialog.alertInfo("请上传保证金收据照片！");
		return false;
	}
	
	//已付装修款退换证明图片校验
	if (returnProve == null || returnProve == "" || returnProve == undefined) {
		Dialog.alertInfo("请上传已付装修款退换证明照片！");
		return false;
	}
	
	//注销证明图片校验
	if (cancellate == null || cancellate == "" || cancellate == undefined) {
		Dialog.alertInfo("请上传注销证明照片！");
		return false;
	}*/

	return true;
}

//自定义方法，array删除
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

//自定义方法，array下标
Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val)
			return i;
	}
	return -1;
};

function checkdata()
{
	//绑定按钮选中
	var companyrange = document.getElementById("companyrange").checked;
	var companychange = document.getElementById("companychange").checked;
	var storechange = document.getElementById("storechange").checked;
	var mainchange=document.getElementById("mainchange").checked; 
	
	if(companyrange == false && storechange==false && companychange==false && mainchange==false)
	{
		Dialog.alertInfo("请选择乙转甲类型！");
		return false;
	}
	
	if (companychange == true) 
	{
		var companyCity = document.getElementById("companyCity");
		var index = companyCity.selectedIndex;
		// 城市
		if (companyCity.options[index].value == null
				|| companyCity.options[index].value == "") {
			Dialog.alertInfo("请选择变更后公司注册城市！");
			return false;
		}
		// 区域
		var companyDistrict = document.getElementById("companyDistrict");
		var index1 = companyDistrict.selectedIndex;
		if (companyDistrict.options[index1].value == null
				|| companyDistrict.options[index1].value == "") {
			Dialog.alertInfo("请选择变更后公司注册区域！");
			return false;
		}
		// 具体地址
		var companyAdresss = document.getElementById("companyAddress").value;
		if (companyAdresss == null || companyAdresss == "") {
			Dialog.alertInfo("请输入变更后公司注册具体地址信息！");
			return false;
		}
	}
	
	// 门店地址变更 选中后 check
	 if (storechange == true) 
	 {
		var storeCity = document.getElementById("storeCity");
		var index = storeCity.selectedIndex;
		// 城市
		if (storeCity.options[index].value == null
				|| storeCity.options[index].value == "") {
			Dialog.alertInfo("请选择变更后门店城市！！");
			return false;
		}
		// 区域
		var storeDistrict = document.getElementById("storeDistrict");
		var index1 = storeDistrict.selectedIndex;
		if (storeDistrict.options[index1].value == null
				|| storeDistrict.options[index1].value == "") {
			Dialog.alertInfo("请选择变更后门店区域！");
			return false;
		}
		// 具体地址
		var storeAdresss = document.getElementById("storeAddress").value;
		if (storeAdresss == null || storeAdresss == "") {
			Dialog.alertInfo("请输入变更后门店具体地址信息！");
			return false;
		}
	 }

	 //校验图片是否上传
	// 公司经营范围 选中后 check
	if (companyrange == true || (storechange==true && mainchange == false) || companychange==true){
		//变更补充条款
		/*if($('#stopContractBox> .item-photo-list').length && $('#stopContractBox> .item-photo-list').length>0){}
		else{
			Dialog.alertInfo("请上传变更补充条款照片！");
			return;
		}*/
		//新营业执照
		if($('#surrenderBox> .item-photo-list').length && $('#surrenderBox> .item-photo-list').length>0){}
		else{
			Dialog.alertInfo("请上传新营业执照照片！");
			return;
		}
		//国家企业信用信息公示系统网站截图
		if($('#receiptBox> .item-photo-list').length && $('#receiptBox> .item-photo-list').length>0){}
		else{
			Dialog.alertInfo("请上传国家企业信用信息公示系统网站截图照片！");
			return;
		}
	}
	// 门店地址变更 选中后 check
	if (storechange == true && mainchange == false)
	{
		// 房友确认单
		if($('#returnProveBox> .item-photo-list').length && $('#returnProveBox> .item-photo-list').length>0){}
		else{
			Dialog.alertInfo("请上传房友确认单照片！");
			return;
		}
		//门店照片
		if($('#cancellateBox> .item-photo-list').length && $('#cancellateBox> .item-photo-list').length>0){}
		else{
			Dialog.alertInfo("请上传门店照片！");
			return;
		}
	}
	//签约主体选中中CHECK
	if(mainchange==true)
	{
		//权利义务转让三方协议书
		if($('#transferRightsBox> .item-photo-list').length && $('#transferRightsBox> .item-photo-list').length>0){}
		else{
			Dialog.alertInfo("请上传权利义务转让三方协议书照片！");
			return false;
		}
		//新签合作协议书
		if($('#newSigningBox> .item-photo-list').length && $('#newSigningBox> .item-photo-list').length>0){}
		else{
			Dialog.alertInfo("请上传新签合作协议书照片！");
			return false;
		}
		//新营业执照
		if($('#surrenderBox> .item-photo-list').length && $('#surrenderBox> .item-photo-list').length>0){}
		else{
			Dialog.alertInfo("请上传新营业执照照片！");
			return;
		}
		//法人身份证
		if($('#legalPersonBox> .item-photo-list').length && $('#legalPersonBox> .item-photo-list').length>0){}
		else{
			Dialog.alertInfo("请上传法人身份证照片！");
			return;
		}
		//国家企业信用信息公示系统网站截图
		if($('#informationPublicityBox> .item-photo-list').length && $('#informationPublicityBox> .item-photo-list').length>0){}
		else{
			Dialog.alertInfo("请上传国家企业信用信息公示系统网站截图照片！");
			return false;
		}
		
		// 房友确认单
		if($('#returnProveBox> .item-photo-list').length && $('#returnProveBox> .item-photo-list').length>0){}
		else{
			Dialog.alertInfo("请上传房友确认单照片！");
			return;
		}
		//门店照片
		if($('#cancellateBox> .item-photo-list').length && $('#cancellateBox> .item-photo-list').length>0){}
		else{
			Dialog.alertInfo("请上传门店照片！");
			return;
		}
		//门店照片
		if($('#noticeBox> .item-photo-list').length && $('#noticeBox> .item-photo-list').length>0){}
		else{
			Dialog.alertInfo("请上传《重要提示函》照片！");
			return;
		}
	}
	 
	/*var stopContract = "";
	var surrender = "";
	var receipt = "";
	var returnProve = "";
	var cancellate = "";
	var transferRights = "";
	var newSigning = "";
	var informationPublicity = "";
	var identityCard = "";
	$(".thumb-xs-box").find("input[name=contractChangePicId]").each(function(){
		var contractChangePicId = $(this).val();
		var fileTypeId = $(this).parent().find("input[name=fileTypeId]").val();
		if(contractChangePicId != "" && contractChangePicId != null && contractChangePicId != undefined){
			if(fileTypeId=="1010"){
				stopContract = contractChangePicId;
			}else if(fileTypeId=="1011"){
				surrender = contractChangePicId;
			}else if(fileTypeId=="1012"){
				receipt = contractChangePicId;
			}else if(fileTypeId=="1013"){
				returnProve = contractChangePicId;
			}else if(fileTypeId=="1014"){
				cancellate = contractChangePicId;
			}else if(fileTypeId=="1016"){
				transferRights = contractChangePicId;
			}else if(fileTypeId=="1017"){
				newSigning = contractChangePicId;
			}else if(fileTypeId=="1018"){
				informationPublicity = contractChangePicId;
			}else if(fileTypeId=="2"){
				identityCard = contractChangePicId;
			}
		}
	})
	// 公司经营范围 选中后 check
	if (companyrange == true || (storechange==true && mainchange == false) || companychange==true){
		
		//变更补充条款 
		if (stopContract == "" || stopContract == null || stopContract == undefined) {
			Dialog.alertInfo("请上传变更补充条款照片！");
			return false;
		}
		// 新营业执照
		if (surrender == null || surrender == "" || surrender == undefined) {
			Dialog.alertInfo("请上传新营业执照照片！");
			return false;
		}
		
		//国家企业信用信息公示系统网站截图
		if (receipt == null || receipt == "" || receipt == undefined) {
			Dialog.alertInfo("请上传国家企业信用信息公示系统网站截图照片！");
			return false;
		}
		
	}
	// 门店地址变更 选中后 check
	 if (storechange == true) 
	 {
		// 房友确认单
		if (returnProve == null || returnProve == ""
				|| returnProve == undefined) {
			Dialog.alertInfo("请上传房友确认单照片！");
			return false;
		}
		// 门店照片
		if (cancellate == null || cancellate == "" || cancellate == undefined) {
			Dialog.alertInfo("请上传门店照片！");
			return false;
		}
	}
	 
	//签约主体选中中CHECK
	if(mainchange==true)
	{
		//权利义务转让三方协议书
		if (transferRights == null || transferRights == "" || transferRights == undefined) 
		{
			Dialog.alertInfo("请上传权利义务转让三方协议书照片！");
			return false;
		}
		
		//新签合作协议书及材料
		if (newSigning == null || newSigning == "" || newSigning == undefined) 
		{
			Dialog.alertInfo("请上传新签合作协议书照片！");
			return false;
		}
		
		// 新营业执照
		if (surrender == null || surrender == "" || surrender == undefined) {
			Dialog.alertInfo("请上传新营业执照照片！");
			return false;
		}
		
		// 法人身份证
		if (identityCard == null || identityCard == "" || identityCard == undefined) {
			Dialog.alertInfo("请上传新营业执照照片！");
			return false;
		}
		
		//国家企业信用信息公示系统网站截图
		if (informationPublicity == null || informationPublicity == "" || informationPublicity == undefined) 
		{
			Dialog.alertInfo("请上传国家企业信用信息公示系统网站截图照片！");
			return false;
		}
		
	}*/
	
	 return true;
};
