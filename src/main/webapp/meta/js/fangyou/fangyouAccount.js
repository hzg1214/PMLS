/**
 * create by cning 2017/07/05 房友账号解绑
 */
$(function() {
	
	initFangYouAccountList();
	
	//绑定房友账号
	$("#bundling").click(function(){	
		var operType=$("#operType").val();
		if(operType == "1"){
			
			var result = "是否确定解绑房友账号?</h3>";
			Dialog.confirm(result, function() {
				unBundling();
			});
			
		}else{
			bundling();
		}
		
		/*var url = BASE_PATH + '/store/status/'+storeId;
		var param={
				storeId : storeId
		}
	
		ajaxGet(url, param, function(data) {			
			if(data.auditStatus==20)
			{
				Dialog.alertInfo('门店状态已审核通过，不能进行此操作！');
				return;
			}else{
				bundling();
			}
		}, function(data) {
			systemLoaded();
		});*/
	})
});

/**
 * 加载房友账号解绑列表
 */
initFangYouAccountList = function() {
	httpGet('fangYouDetailForm', 'LoadfangYouCtx', BASE_PATH + '/fangyouAccount/q/'+storeId,
			function() {
				pageInfo("fangYouDetailForm", function() {
					
					initFangYouAccountList();
				});	
				
				var operType=$("#operType").val();
				if(operType == "1"){
					$("#bundling").html("解绑房友账号");
				}else{
					$("#bundling").html("绑定房友账号");
				}
			});
};

FangyouAccount= function() {
};

//绑定
function bundling(){
	var storeId = $("#storeId").val();
	var storeNo = $("#storeNo").val();
	var name = $("#name").val();
	var operType=$("#operType").val();
	if(operType=="")
	{
		operType="0";
	}
	var url = BASE_PATH + "/fangyouAccount/bundling/"+storeId+"/"+storeNo+"/"+name+"/"+operType;	
	var params = {
			storeId : storeId,
			storeNo : storeNo,
			name : name,
			operType : operType
	};
	
	var dialogOptions = {
			ok : function() {
				// 确定
				var reback = bundlingAdd();
				return reback;					
			},
			okVal : '确定',
			cancel : true,
			cancelVal : '返回'
	};
	// 跳转至房友账号绑定
	Dialog.ajaxOpenDialog(url, params, "房友账号绑定", function(dialog, resData) {
		FangyouAccount.dialog = dialog;
	}, dialogOptions);
}

/**
 * 绑定
 */
function bundlingAdd() {
	var storeId = $("#storeId").val();
	var url = BASE_PATH + '/fangyouAccount/bundling';
	var backUrl = "/fangyouAccount/account/"+storeId;
	// 校验输入信息
	if (Validator.validForm("fangyouAccountAddForm")) {
		systemLoading("", true);
		httpPost('fangyouAccountAddForm', url, function(data) {
			systemLoaded();
			location.href = BASE_PATH + backUrl;
			
		}, function(data) {
			
			Dialog.alertError(data.returnMsg);
			systemLoaded();
			return false;
		});
	} else {		
		systemLoaded();
		return false;
	}
	return true;
};

/**
 * 解绑
 */
function unBundling() {
	var storeId = $("#storeId").val();
	var url = BASE_PATH + '/fangyouAccount/bundling';
	var backUrl = "/fangyouAccount/account/"+storeId;
	httpPost('fangYouDetailForm', url, function(data) {
		systemLoaded();
		location.href = BASE_PATH + backUrl;
		
	}, function(data) {		
		Dialog.alertError(data.returnMsg);
		systemLoaded();
		return false;
	});

	return true;
};

FangyouAccount.close=function(){
	FangyouAccount.dialog.close();
}
