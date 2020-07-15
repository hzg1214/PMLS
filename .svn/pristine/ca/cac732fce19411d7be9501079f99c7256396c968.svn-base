$(function() {
	initMultiSelectM();
	initList();
});

AccountProject = function() {
	dialogCreate: null;
	dialogModify: null;

};

/**
 * 初始化查询
 */
initList = function() {
	httpGet('accountProjectListForm', 'LoadCxt', BASE_PATH + '/accountProject/q',
			function() {
				pageInfo("accountProjectListForm", function() {
					initList();
				});

			});
};

/**
 * 查询
 */
AccountProject.search = function() {
	$('#curPage').val('1');
	initList();
};

/**
 * 核算主体对话框
 */
AccountProject.create = function() {

	var url = LOC_RES_BASE_PATH + '/accountProject/create';
	var title = '核算主体维护';
	var params = {};

	var dialogOptions = {
		width : 500,
		ok : function() {
			var reback = AccountProject.save();
			return reback;
		},
		okVal : '保存',
		cancel : true,
		cancelVal : '取消',
		zIndex : 1000
	};

	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
		AccountProject.dialogCreate = dialog;
	}, dialogOptions);

}

AccountProject.closeCreate = function() {
	if (AccountProject.dialogCreate != null && !AccountProject.dialogCreate.closed) {
		AccountProject.dialogCreate.close();
	}
}

/**
 * 新增保存处理
 */
AccountProject.save = function() {
	if (Validator.validForm("accountProjectCreateForm")) {
		var selectCityNo = $("#newCityNo").val();
		var newAccountProjectNos = $("#newAccountProjectNos").val();
		var newAccountProjectNoKey = $("#newAccountProjectNoKey").val();
//		var accountProject = $("#accountProject").val();
		if(newAccountProjectNos == '' || newAccountProjectNos == null || newAccountProjectNos == undefined){
			$("#newAccountProjectNoStr").text("不能为空");
			$("#newAccountProjectNoStr").css("visibility","initial");
			$("#newAccountProjectNoStr").show();
			if($("#newWarningMsg").text() !=""){
				$("#newWarningMsg").hide();
			}
			 return false;
		}

		var url = BASE_PATH + '/accountProject/save';
		var params = {
			cityNo : selectCityNo,
//			accountProject : accountProject,
			accountProjectNos : newAccountProjectNoKey
		}
		return asyncPost(url, params, "#newWarningMsg");
	} else {
		return false;
	}
}

function asyncPost(url, params, message) {

	if (url.indexOf("?") > 0) {
		url = url + "&" + rnd();
	} else {
		url = url + "?" + rnd();
	}
	$(message).empty();
	var reback = false;
	systemLoading("", true);
	$.ajax({
		url : url,
		data : params,
		type : 'POST',
		async : false,
		dataType : 'json',
		success : function(data) {
			systemLoaded();
			if (data && data.returnCode == '200') {
				initList();
				reback = true;
			} else {
				$("#newWarningMsg").show();
				$("#oldWarningMsg").show();
				$(message).empty().html(data.returnMsg);
				if($("#newAccountProjectNoStr").text() != ""){
					$("#newAccountProjectNoStr").css("display","none");
				}
				if($("#editAccountProjectNoStr").text() != ""){
					$("#editAccountProjectNoStr").css("display","none");
				}
				reback = false;
			}
		},
		error : function(data) {
			systemLoaded();
			$(message).empty().html("操作失败");
			systemLoaded();
			reback = false;
		}
	});

	return reback;
}

/**
 * 编辑画面对话框
 */
AccountProject.modify = function(id) {

	var url = LOC_RES_BASE_PATH + '/accountProject/modify/' + id;
	var title = '核算主体维护';
	var params = {};
	var dialogOptions = {
		width : 500,
		ok : function() {
			var reback = AccountProject.update(id);
			return reback;
		},
		okVal : '保存',
		cancel : true,
		cancelVal : '取消',
		zIndex : 1000
	};

	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
		AccountProject.dialogModify = dialog;
	}, dialogOptions);

}

AccountProject.closeModify = function() {
	if (AccountProject.dialogModify != null && !AccountProject.dialogModify.closed) {
		AccountProject.dialogModify.close();
	}
}

/**
 * 编辑保存处理
 */
AccountProject.update = function(yfId) {

	if (Validator.validForm("accountProjectModifyForm")) {
		var editAccountProjectNos = $("#editAccountProjectNos").val();
		if(editAccountProjectNos == '' || editAccountProjectNos == null || editAccountProjectNos == undefined){
			$("#editAccountProjectNoStr").text("不能为空");
			$("#editAccountProjectNoStr").css("visibility","initial");
			$("#editAccountProjectNoStr").show();
			if($("#oldWarningMsg").text() !=""){
				$("#oldWarningMsg").hide();
			}
			 return false;
		}
		var url = BASE_PATH + '/accountProject/update';
		var selectCityNo = $("#editCityNo").val();
		var params = {
			id : yfId,
			cityNo : selectCityNo,
			accountProjectNos : editAccountProjectNos
		}
		return asyncPost(url, params, "#oldWarningMsg");

	} else {
		return false;
	}
}

/**
 * 删除处理
 */
AccountProject.remove = function(yfId) {

	var result = "是否确定删除此信息?</h3>";

	var params = {
		id : yfId
	};

	Dialog.confirm(result, function() {

		restPost(BASE_PATH + "/accountProject/delete", params, function(data) {
			initList();
		}, function(data) {
			Dialog.alertError(data.returnMsg);
		});
	});
};

// 重置输入内容
AccountProject.reset = function() {
	$('select').each(function(key, value) {
		$(value).find('option').prop('selected', false);
		$(value).find('option:first').prop('selected', true);
	});
	$('form input[type="text"]').val('');
	$("#centerId .multi-select-item input").attr("checked", false);
	AccountProject.search();
};

/**
 * 初始化下拉框
 * 
 * @param selector
 * @param selectors
 * @returns
 */
function initMultiSelectM(selector, selectors) {
	$("div[name^='group']").find('.multi-select').multiSelect({
		check : function($instance) {
		}
	});

}

/**
 * 清空下拉框
 * 
 * @param selCenter
 * @returns
 */
function clearMultiSelectM(selCenter) {
	$("#" + selCenter).find(".multi-select-item").not(':first').remove();
	$("#" + selCenter).find('.multi-select-checkall').removeAttr("checked");
	$("#" + selCenter).find('.multi-select-value').val('');
	$("#" + selCenter).find('.multi-select-text').val('');
	$("#" + selCenter).find('.multi-select-text').text('');
}
