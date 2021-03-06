/** ************************公共部分***************************** */
$(function() {
	// 初始化查询
	initList();
});

/** **************************Contacts js*************************** */

storeList = function() {
};

/**
 * 初始化查询
 */
initList = function() {
	httpGet('gpcompanyStoreListForm', 'LoadCxtgpComStore', BASE_PATH
			+ '/companys/gpstore/q', function() {
		pageInfo("gpcompanyStoreListForm", function() {
			initList();
		});
	});
};

/**
 * 查询
 * 
 */
search = function() {
	$('#curPage').val('1');
	initList();
};

/**
 * 关联门店
 */
gpcompanyStore = function()
{
	var cId = $("#companyId").val();
	var userCreate = $("#userCreate").val();
	var url = BASE_PATH + "/companys/gpcompanyStore/" + cId + "/"+userCreate;	
	var params = {
		companyId:cId
	};

	var dialogOptions = {
			ok : function() {
				// 确定
				return gpcompanyStoreAdd();
			},
			okVal : '保存',
			cancel : true,
			cancelVal : '返回',
			width:800
	}
	// 跳转至关联门店画面
	Dialog.ajaxOpenDialog(url, params, "关联公盘门店信息", function(dialog, resData) {
		storeList.dialog = dialog;
	}, dialogOptions);
	
}

function gpcompanyStoreAdd()
{
	var storeIds="";
	$("input[type=checkbox][name=chbStore]").each(function(){
        if($(this).is(':checked')){
        	storeIds += $(this).next().val() + ","; 
        }
    });
	if(storeIds.length<=0){
		return;
	}
	storeIds=storeIds.substring(0,storeIds.length-1);
	
	var url = BASE_PATH + '/companys/gpcompanyStore/add';
	var params = {
			storeIds : storeIds,
			companyId : $("#companyId").val()
	}

	systemLoading("", true);
	httpPut(url, params, function(data) {

		location.href = BASE_PATH + "/companys/gpstore/" + $("#companyId").val() + "/" + $("#userCreate").val();

	}, function(data) {
		Dialog.alertError(data.returnMsg);
		systemLoaded();
		return false;
	});
	
	systemLoaded();	
	return true;
}

storeList.close = function(){
	storeList.dialog.close();
}
