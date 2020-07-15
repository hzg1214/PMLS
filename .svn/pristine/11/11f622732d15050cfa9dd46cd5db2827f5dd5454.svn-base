/** ************************公共部分***************************** */
$(function() {
	Contacts.companyId = $("#companyId").val();
	Contacts.userCreate = $("#userCreate").val();
	$("#name").bind('keydown', stop).bind('keypress', stop);
	function stop(event) { 
		if (event.keyCode == 13) {
			event.stopPropagation();
		    return false;	
		}
		return true;
	};  
});

/** **************************Contacts js*************************** */
Contacts = function() {
	dialog : null;
	companyId: companyId;
	userCreate:userCreate;
};

/**
 * 新建联系人
 */
Contacts.toAddCntcts = function() {

	var url = BASE_PATH + '/contacts/c/'+$('#storeId').val()+'/'+$('#userCreate').val();
	var title = '新建联系人';

	var params = {};
	var dialogOptions = {

		/* width : 800, */
		/* height : 760, */

		ok : function() {

			// 确定
			var reback = Contacts.add();

			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};

	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

		// dialog.position('50%', '25%');

		Contacts.dialog = dialog;

	}, dialogOptions);

};

/**
 * 创建
 */
Contacts.add = function() {

	var url = BASE_PATH + '/contacts';

	// 校验输入信息
	if (Validator.validForm("contactsAddForm")) {
		systemLoading("", true);
		httpPost('contactsAddForm', url, function(data) {

			location.href = BASE_PATH + "/contacts/store/"
					+ $('#storeId').val()+"/" + $('#userCreate').val();

		}, function(data) {
			Dialog.alertError(data.returnMsg);
			return false;
			systemLoaded();
		});
		systemLoaded();
	} else {
		return false;
		systemLoaded();
	}

	return true;

};

/**
 * 编辑联系人
 */
Contacts.toUpdateCntcts = function(contactsId) {

	var url = BASE_PATH + '/contacts/u/' + contactsId;
	var title = '编辑联系人';

	var params = {};

	var dialogOptions = {

		/* width : 800, */
		/* height : 760, */

		ok : function() {

			// 确定
			var reback = Contacts.update(contactsId);

			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};

	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

		// dialog.position('50%', '25%');

		Contacts.dialog = dialog;

	}, dialogOptions);

};

/**
 * 编辑
 */
Contacts.update = function(contactsId) {

	var url = BASE_PATH + '/contacts/' + contactsId;

	var params = $("#contactsEditForm").serialize();

	// 校验输入信息
	if (Validator.validForm("contactsEditForm")) {
		systemLoading("", true);
		httpPut(url, params, function(data) {

			location.href = BASE_PATH + "/contacts/store/"
			+ $('#storeId').val()+"/" + $('#userCreate').val();

		}, function(data) {

			return false;
		});
		systemLoaded();
	} else {
		return false;
		systemLoaded();
	}

	return true;

};

/**
 * 查看联系人
 */
Contacts.toViewCntcts = function(contactsId) {

	var url = BASE_PATH + '/contacts/' + contactsId;
	var title = '查看联系人';

	var params = {};

	var dialogOptions = {

		cancel : true,
		cancelVal : '取消'
	};

	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

		Contacts.dialog = dialog;

	}, dialogOptions);

};

/**
 * 删除
 */
Contacts.remove = function(id) {

	var result = "是否确定删除此联系人?</h3>";

	var params = {
		'id' : id
	};

	Dialog.confirm(result, function() {

		httpDelete(BASE_PATH + "/contacts/" + id, params, function(data) {

			location.href = BASE_PATH + "/contacts/store/"
			+ $('#storeId').val()+"/" + $('#userCreate').val();

		}, function(data) {
			Dialog.alertError(data.returnMsg);
		});
	});
};


/**
 * 返回、取消
 */
Contacts.back = function() {

	location.href = BASE_PATH + "/contacts";

};

/**
 * 关闭弹窗
 */
Contacts.close = function() {
	Contacts.dialog.close();
};