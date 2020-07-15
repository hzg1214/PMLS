/**************************公共部分***************************** */

/****************************demo js*************************** */
/**
 * 上传图片pageType(-9标示新增)
 */
function upload(self, type, num, pageType) {

	var formId = "";
	var showImgId = "";

	if (-9 == pageType) {

		if ($("#companyId").val() == "") {
			Dialog.alertInfo("请先选择客户");
			// 移除input，重新添加
			var item = $(self).parent();
			$(self).remove();
			item
					.append("<input class='inputstyle' type='file' id='btnfileUpload' name='fileName' onchange='upload(this, "
							+ type + "," + num + "," + pageType + ");'>");
			return false;
		}

		formId = "contractAdd" + tagArr[type - 1] + "Form" + num;
		showImgId = "#show" + tagArr[type - 1] + "Img" + num;
		$("#companyIdBusiness" + num).val($("#companyId").val());
		$("#companyIdBusiness" + num).val("");

	} else {

		formId = "contractAdd" + tagArr[type - 1] + "Form" + num;
		showImgId = "#show" + tagArr[type - 1] + "Img" + num;
		$("#companyId" + tagArr[type - 1] + num).val($("#companyId").val());
		$("#companyId" + tagArr[type - 1] + num).val("");
	}

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
		systemLoading("", true);
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
		// 文件上传，先上传ＯＡ文件系统,在上传房友文件系统
		Contract.uploadToOa(formId, self, type, num, pageType, fyurl);
	}
};

// 文件上传，到OA文件系统
Contract.uploadToOa = function(formId, self, type, num, pageType, fyurl) {

	var url = BASE_PATH + "/contracts/oa/upload";

	httpPost(formId, url, function(data) {

		// 返回的文件Id
		var fileRecordMainId = data.returnValue;

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
				Contract.setUploaded(type, data, num);

				// 删除旧值
				var index = oaArr[type - 1].indexOf($(
						"#oaFileId" + tagArr[type - 1] + num).val());
				if (index >= 0) {
					oaArr[type - 1].splice(index, 1);
				}

				// 给每个区域上传附件的文件设值
				$("#oaFileId" + tagArr[type - 1] + num).val(fileRecordMainId);
				oaArr[type - 1].push(fileRecordMainId);
				$("#oaFileId" + tagArr[type - 1]).val(oaArr[type - 1]);
				systemLoaded();

				// 移除Input重新添加
				removeInput(self, type, num, pageType);

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

};

// 文件上传成功后，数据组织
Contract.setUploaded = function(type, data, num) {

	fileRecordMainIds.remove($("#fileRecordMainId" + tagArr[type - 1] + num)
			.val());

	fileRecordMainIds.push(data.fileRecordMainId);
	//Add By NingChao 2017/04/07 Start
	if($("#oldContractNo").val()!=undefined)
	{
		// 移除指定值
		var rmValue = "";
		if (1 == type) {
			rmValue = $("#fileRecordMainIdBusiness" + num).val();
			if($("#oldContractNo").val()!=undefined){
				oaFileIdBusiness.remove(rmValue);
			}
		} else if (2 == type) {
			rmValue = $("#fileRecordMainIdCard" + num).val();
			if($("#oldContractNo").val()!=undefined){
				oaFileIdCards.remove(rmValue);
			}
		} else if (3 == type) {
			rmValue = $("#fileRecordMainIdPhoto" + num).val();
			if($("#oldContractNo").val()!=undefined){
				oaFileIdPhotos.remove(rmValue);
			}
		} else if (4 == type) {
			rmValue = $("#fileRecordMainIdStore" + num).val();
			if($("#oldContractNo").val()!=undefined){
				oaFileIdStores.remove(rmValue);
			}
		} else if (5 == type) {
			rmValue = $("#fileRecordMainIdInstall" + num).val();
			if($("#oldContractNo").val()!=undefined){
				oaFileIdInstalls.remove(rmValue);
			}
		} else if (1020 == type) {
			rmValue = $("#fileRecordMainIdNotice" + num).val();
			if($("#oldContractNo").val()!=undefined){
				oaFileIdNotice.remove(rmValue);
			}
		} else if (6 == type) {
			rmValue = $("#fileRecordMainIdOther" + num).val();
			if($("#oldContractNo").val()!=undefined){
				oaFileIdOthers.remove(rmValue);
			}
		}
		fileRecordMainIds.remove(rmValue);
	}
	//Add By NingChao 2017/04/07 End	
	$("#fileRecordMainIds").val(fileRecordMainIds);
	$("#fileRecordMainId" + tagArr[type - 1] + num).val(data.fileRecordMainId);
	
};

// 新增 附件
function addBusinessFileRow(obj, type, pageType) {

	var num = RndNum(4);// 随机数
	var num2 = $(obj).parent().children('form').size();
	if (num2 > 9) {
		Dialog.alertInfo("最多添加10个附件");
		return;
	}
	var html = "";
	html += "<form id='contractAdd" + tagArr[type - 1] + "Form" + num + "' >";
	html += "<input type='hidden'  id ='companyId" + tagArr[type - 1] + num
			+ "'  name ='companyId'  value='" + $("#companyId").val() + "'>";

	// fangyou
	html += "<input type='hidden' name='fileRecordMainId' id='fileRecordMainId"
			+ tagArr[type - 1] + num + "'  />";

	// OA
	html += "<input type='hidden' name='oaFileId' id='oaFileId"
			+ tagArr[type - 1] + num + "'  />";
	// 设置文件类型
	html += "<input type='hidden' name='fileTypeId' value='" + type + "' />";

	html += "<input type='hidden' name='fileSourceId' value='1' />";
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
			+ num + ", " + pageType + ")'></em>";

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
			+ type + "," + num + "," + pageType + ");'>";

	html += "</div>";
	html += "</div>";
	html += "</li>";
	html += " </ul>";

	$(obj).parent().append(html);

}

/**
 * 移除
 * 
 */
function delFile(obj, type, num, pageType) {

	var num2 = $(obj).parent().parent().parent().parent().parent().parent()
			.parent().children('form').size();
	// 删除旧值
	var index = oaArr[type - 1].indexOf($("#oaFileId" + tagArr[type - 1] + num)
			.val());
	if (index >= 0) {
		oaArr[type - 1].splice(index, 1);
	}
	$("#oaFileId" + tagArr[type - 1]).val(oaArr[type - 1]);
	
	if (-9 == pageType) {
		fileRecordMainIds
				.remove($("#fileRecordMainId" + tagArr[type - 1] + num).val());
	} else {
		// 移除指定值
		var rmValue = "";
		if (1 == type) {
			rmValue = $("#fileRecordMainIdBusiness" + num).val();
			//Add By NingChao 2017/04/07 Start 
			if($("#oldContractNo").val()!=undefined){
				oaFileIdBusiness.remove(rmValue);
			}
		} else if (2 == type) {
			rmValue = $("#fileRecordMainIdCard" + num).val();
			//Add By NingChao 2017/04/07 Start 
			if($("#oldContractNo").val()!=undefined){
				oaFileIdCards.remove(rmValue);
			}
			//Add By NingChao 2017/04/07 End
		} else if (3 == type) {
			rmValue = $("#fileRecordMainIdPhoto" + num).val();
			//Add By NingChao 2017/04/07 Start 
			if($("#oldContractNo").val()!=undefined){
				oaFileIdPhotos.remove(rmValue);
			}
			//Add By NingChao 2017/04/07 End
		} else if (4 == type) {
			rmValue = $("#fileRecordMainIdStore" + num).val();
			//Add By NingChao 2017/04/07 Start 
			if($("#oldContractNo").val()!=undefined){
				oaFileIdStores.remove(rmValue);
			}
			//Add By NingChao 2017/04/07 End
		} else if (5 == type) {
			rmValue = $("#fileRecordMainIdInstall" + num).val();
			//Add By NingChao 2017/04/07 Start 
			if($("#oldContractNo").val()!=undefined){
				oaFileIdInstalls.remove(rmValue);
			}
			//Add By NingChao 2017/04/07 End
		} else if (1020 == type) {
			rmValue = $("#fileRecordMainIdNotice" + num).val();
			//Add By NingChao 2017/04/07 Start 
			if($("#oldContractNo").val()!=undefined){
				oaFileIdNotice.remove(rmValue);
			}
		} else if (6 == type) {
			rmValue = $("#fileRecordMainIdOther" + num).val();
			//Add By NingChao 2017/04/07 Start 
			if($("#oldContractNo").val()!=undefined){
				oaFileIdOthers.remove(rmValue);
			}
			//Add By NingChao 2017/04/07 End
		}
		fileRecordMainIds.remove(rmValue);
	}

	$("#fileRecordMainIds").val(fileRecordMainIds);

	if (num2 <= 1) {
		resetImg(type, num);
	} else {
		$(obj).closest("form").remove();
	}

	// if (num2 <= 1) {
	// resetImg(type, num);
	// } else {
	// $(obj).parent().parent().parent().parent().parent().parent().remove();
	// }
}

/**
 * 计算总保证金
 * 
 */
function calculateTotleDepositFee(flag) {
	var storeNum = 0;
	var depositFee = 0;
	var totleDepositFee = 0;
	if ($("#storeNum").val() != undefined && $("#storeNum").val() != ""
			&& isPosNumWithZero($("#storeNum").val()) == true) {
		storeNum = $("#storeNum").val();
	}
	if ($("#depositFee").val() != undefined && $("#depositFee").val() != "") {
		depositFee = $("#depositFee").val();
		if(!isPosNumWithZero($("#depositFee").val())){
			$("#depositFee").val("");
			$("#totleDepositFee").val("");
			$("#depositFee").focus();
			$("#errorTip").html(" 请输入整数!").show();
		}else{
			$("#errorTip").html("").hide();
			totleDepositFee = Number(Number(storeNum) * Number(depositFee));
			$("#totleDepositFee").val(totleDepositFee);
		}
	}else{
		if(flag != '1'){
			$("#errorTip").html(" 请输入整数!").show();
		}
		$("#totleDepositFee").val("");
	}
}
function listeningpenaltyFee() {
	var penaltyFee = 0;
	if ($("#penaltyFee").val() != undefined && $("#penaltyFee").val() != "") {
		penaltyFee = $("#penaltyFee").val();
		if(!isPosNumWithZero($("#penaltyFee").val())){
			$("#penaltyFee").val("");
			$("#penaltyFee").focus();
			$("#errorTip2").html(" 请输入整数!").show();
		}else{
			$("#errorTip2").html("").hide();
		}
	}else{
		$("#errorTip2").html(" 请输入整数!").show();
	}
	//$("#totleDepositFee").val(totleDepositFee.toFixed(2));
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

function resetImg(type, num) {
	$("#show" + tagArr[type - 1] + 'Img' + num + " a")
			.first()
			.html(
					"<img src='"
							+ BASE_PATH
							+ "/meta/images/"
							+ picArr[type - 1]
							+ "' alt='' class='img'><br /><label class='fileName'></label>");
}

// 移除input，重新添加
function removeInput(self, type, num, pageType) {
	var item = $(self).parent();
	$(self).remove();
	item
			.append("<input class='inputstyle' type='file' id='btnfileUpload' name='fileName' onchange='upload(this, "
					+ type + "," + num + "," + pageType + ");'>");
}
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};
Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val)
			return i;
	}
	return -1;
};