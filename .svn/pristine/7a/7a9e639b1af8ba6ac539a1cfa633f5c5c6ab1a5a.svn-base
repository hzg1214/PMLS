$(function() {
	initHead();
});

function initHead(){
	$("li[id^='nav']").mouseenter(function(){
		$('li[id^="nav"]').removeClass('active');
		$(this).addClass('open');
	});
	$("li[id^='nav']").mouseleave(function(){
		$('li[id^="nav"]').removeClass('open');
		$('#nav'+navSelectId).addClass('active');
	});
}

// 岗位下拉变动
function postChange(postId) {
	//var postId = $("#postId").val();
	var url = BASE_PATH + "/user/changePost";
	var params = {
		selectedPostId : postId
	};
	ajaxGet(url, params, function(data) {
		var returnVal = data.returnValue;
		window.location.href = BASE_PATH + returnVal;
	}, function(data) {
	});
}

// 顶部菜单点击
function menuClick(authId, authName, actionUrl) {
	var url = BASE_PATH + "/user/clickmenu";
	var params = {
		authId : authId,
		authName : authName,
		actionUrl : actionUrl
	};

	ajaxGet(url, params, function(data) {
		window.location.href = BASE_PATH + actionUrl;
	}, function(data) {
	});
}

//顶部菜单跳转
function toMenuClick(authId, authName, actionUrl) {
	var url = BASE_PATH + "/user/clickmenu";
	var params = {
		authId : authId,
		authName : authName,
		actionUrl : actionUrl
	};

	ajaxGet(url, params, function(data) {

	}, function(data) {
	});
}
/**
 * 意见反馈js
 */
Feeback = function() {
	dialog: null;
};
Feeback.close = function() {
	Feeback.dialog.close();
};

function toFeeback() {
	var dialogOptions = {
		ok : function() {
			if(!Validator.validForm("feebackForm")){
				return false;
			}
			systemLoading("", true);
			$.post(BASE_PATH+"/feeback/add",$("#feebackForm").serialize(), function(result) {
				systemLoaded();
				if (result && result.returnCode == '200') {
					$('#dialog-feeback').dialog('close');
					Dialog.alertSuccess("反馈成功");
				} else {
					Dialog.alertError(result.returnMsg);
				}
			}, 'json');
		},
		okVal : '提交',
		cancel : true,
		cancelVal : '取消'
	};
	
	Dialog.ajaxOpenDialog(BASE_PATH + "/feeback/feeback", {}, "意见反馈", function(
			dialog, resData) {
		dialog.position('50%', '50%');
		Feeback.dialog = dialog;
	}, dialogOptions);
}