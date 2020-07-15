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

		formId = "companyEdit" + tagArr[type - 1] + "Form" + num;
		showImgId = "#show" + tagArr[type - 1] + "Img" + num;
		$("#companyIdBusiness" + num).val($("#companyId").val());
		$("#companyIdBusiness" + num).val("");

	} else {

		formId = "companyEdit" + tagArr[type - 1] + "Form" + num;
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
		Company.uploadToOa(formId, self, type, num, pageType, fyurl);
	}
};

// 文件上传，到OA文件系统
Company.uploadToOa = function(formId, self, type, num, pageType, fyurl) {

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
				Company.setUploaded(type, data, num);

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
Company.setUploaded = function(type, data, num) {

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
		} 
		fileRecordMainIds.remove(rmValue);
	}
	//Add By NingChao 2017/04/07 End	
	$("#fileRecordMainIds").val(fileRecordMainIds);
	$("#fileRecordMainId" + tagArr[type - 1] + num).val(data.fileRecordMainId);
	
};

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
		var rmValue = $("#fileRecordMainIdBusiness" + num).val();

		fileRecordMainIds.remove(rmValue);
	}

	$("#fileRecordMainIds").val(fileRecordMainIds);

	if (num2 <= 1) {
		resetImg(type, num);
	} else {
		$(obj).closest("form").remove();
	}

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