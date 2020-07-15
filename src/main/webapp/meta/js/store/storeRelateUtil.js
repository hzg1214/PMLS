RelateUtil = function() {
	dialog: null;
};
/**
 * 关联事业部人员
 */
function relateUser() {

	var url = BASE_PATH + '/relate/user';
	var title = '关联事业部人员';

	var orderName = $("#orderName").val();
	var orderType = $("#orderType").val();
	var params = {
		orderName : orderName,
		orderType : orderType
	};
	var dialogOptions = {

		width : 800,
		/* height : 760, */

		ok : function() {

			// 确定
			var reback = RelateUtil.chooseUser();

			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};

	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

		dialog.position('50%', '25%');

		RelateUtil.dialog = dialog;

	}, dialogOptions);

}

// 关联客户
RelateUtil.chooseUser = function() {
	// 关联后，客户展示到父页面上
	var storeId = $("#storeId").val();
	var selectedMaintainerId=null;
	var selectedMaintainerName=null;
	$('input:radio[name="selectUserId"]:checked').each(
			function() {
				$(this).parent().siblings().each(
						function(i) {
							if (i == 0) {
								selectedMaintainerId = $(this).html();
							}
							if (i == 1) {
								selectedMaintainerName = $(this).html();
							}
							
						});
			});
	var dbMaintainerId = $("#maintainerId").val();
	if (selectedMaintainerId !== dbMaintainerId) {
		// 1.向storeMaintainer表插入数据
		RelateUtil.insertStoreMaintainer(storeId,selectedMaintainerId);
		// 2.更新store表的maintainer
		RelateUtil.updateStore(storeId,selectedMaintainerId,selectedMaintainerName);
	}
};

// 关闭弹窗
RelateUtil.close = function() {
	RelateUtil.dialog.close();
};

// 更新新的维护人到store表
RelateUtil.updateStore = function(storeId, maintainer, maintainerName) {
	var url = BASE_PATH + '/store/maintainer/' + storeId;
	var param = {
		maintainer : maintainer,
		maintainerName:maintainerName
	};
	httpPut(url, param, function(data) {
		location.href = BASE_PATH + '/store/' + storeId;;
	});
};
// 向门店维护人关系表插入数据
RelateUtil.insertStoreMaintainer = function(storeId, maintainer) {
	var url = BASE_PATH + '/storeMaintainer';
	var params = {
		storeId : storeId,
		maintainer : maintainer
	};
	restPost(url, params, function() {
	});
};

// 设置装修状态
function setDecorationState() {
	var storeId = $("#storeId").val();
	var url = BASE_PATH + '/store/toDecoration/'+storeId;
	var params = {
	};
	var dialogOptions = {
		width : 950, 
		height : 170,
		ok : function() {
			// 确定
			var reback = RelateUtil.saveDecorationState(storeId);
			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};
	Dialog.ajaxOpenDialog(url, params, "装修状态", function(dialog, resData) {
		RelateUtil.dialog = dialog;
	}, dialogOptions);
}

//更新装修状况到store表
RelateUtil.saveDecorationState = function(storeId){
	if (Validator.validForm("decorationStateForm")) {
		var url = BASE_PATH + '/store/decorationUpt/'+storeId;
		var param = $("#decorationStateForm").serialize();
		httpPut(url, param, function(data) {
			location.href = BASE_PATH + '/store/' + storeId;
		});
	} else {
		return false;
	}
};

//设置保证金
function setDeposit(){
	var storeId = $("#storeId").val();
	var url = BASE_PATH + '/store/toDeposit/'+storeId;
	var params = {
	};
	var dialogOptions = {
		width : 300, 
		height : 100,
		ok : function() {
			// 确定
			var reback = RelateUtil.saveDeposit(storeId);
			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};
	Dialog.ajaxOpenDialog(url, params, "保证金", function(dialog, resData) {
		RelateUtil.dialog = dialog;
	}, dialogOptions);
}

//更新保证金到store表
RelateUtil.saveDeposit = function(storeId){
	if (Validator.validForm("depositForm")) {
		var url = BASE_PATH + '/store/depositUpt/'+storeId;
		var param = $("#depositForm").serialize();
		httpPut(url, param, function(data) {
			location.href = BASE_PATH + '/store/' + storeId;
		});
	} else {
		return false;
	}
};

//设置装修翻牌费
function setDecoractionFee(){
	var storeId = $("#storeId").val();
	var url = BASE_PATH + '/store/toDecoractionFee/'+storeId;
	var params = {
	};
	var dialogOptions = {
		width : 800, 
		height : 120,
		ok : function() {
			// 确定
			var reback = RelateUtil.saveDecoractionFee(storeId);
			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};
	Dialog.ajaxOpenDialog(url, params, "装修翻牌费", function(dialog, resData) {
		RelateUtil.dialog = dialog;
	}, dialogOptions);
}

//更新保证金到store表
RelateUtil.saveDecoractionFee = function(storeId){
	if (Validator.validForm("decorationFeeForm")) {
		var url = BASE_PATH + '/store/decorationFeeUpt/'+storeId;
		var param = $("#decorationFeeForm").serialize();
		httpPut(url, param, function(data) {
			location.href = BASE_PATH + '/store/' + storeId;
		});
	} else {
		return false;
	};
};


/**
 * 关联门店
 */
function relateStores() {
	var url = BASE_PATH + '/relate';
	var title = '关联门店';
	var params = {
	};
	var dialogOptions = {
		width : 800,
		ok : function() {
			// 确定
			var reback = RelateUtil.relateStore();
			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};
	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
		dialog.position('50%', '25%');
		RelateUtil.dialog = dialog;
	}, dialogOptions);
}

//关联门店
RelateUtil.relateStore = function() {
	// 关联后，门店展示到父页面上
	var htmls = "";
	var storeIdArr = "";
	$('input[name="storeIds"]:checked')
			.each(
					function() {
						if ($("#storeIds" + $(this).val()).val() == $(this)
								.val()) {
							return true;
						}
						htmls += "<tr>";
						$(this).parent().siblings().each(function(i) {
							if (i == 0) {
								storeIdArr = storeIdArr+$(this).children().val()+",";
							}
							if (i == 2) {
								//storeName
								htmls += "<td>" + $(this).html() + "</td>";
							}
							if (i == 4) {
								//storeAddress
								htmls += "<td>" + $(this).html() + "</td>";
							}
							if (i == 6) {
								//storeCrtDate
								htmls += "<td>" + $(this).html() + "</td>";
							}
						});
						htmls += "<td style='display:none'> <input name='storeIds' id='storeIds"
								+ $(this).val()
								+ "' type='hidden' value='"
								+ $(this).val() + "'></td>";
						htmls += "</tr>";
					});
	$("#relateStoreTableId", window.parent.document).append(htmls);
	var companyId = $("#companyId").val();
	storeIdArr = storeIdArr.substring(0, storeIdArr.length-1);
	if (storeIdArr == "") {
		return;
	}
	saveCompanyStore(storeIdArr,companyId);
};

/**
 * 保存公司门店关系
 * @param storeIdArr
 * @param companyId
 */
function saveCompanyStore (storeIdArr,companyId) {
	var url = BASE_PATH + '/companyStore';
	var params = {
			storeIdArr:storeIdArr,
		companyId:companyId
	};
	ajaxGet(url, params, function(data) {
		loadCxt();
	}, function(data) {
		Dialog.alertError(data.returnMsg);
	});
};


/**
 * 取消关联门店
 * @param storeIdArr
 * @param companyId
 */
function cancelCompanyStore (storeId,companyId,fyIdEmpty,row) {
	var msg = "";
//	if (!fyIdEmpty){
//		msg = "要取消关联的门店已存在门店和房友账号关系，如有必要可在门店中进行房友账号的解绑或重新绑定<br/>";
//	}
	var storeNo=$(row).parent().parent().find("td").eq(1).find("a").text();
	var storeName=$(row).parent().parent().find("td").eq(2).text();
    var companyName = $("#companyName").val();
	msg += "请确认是否取消门店：" + storeNo.trim() + " "+ storeName.trim() +"和公司：" + companyName + "的关联？";
	Dialog.confirm(msg, function() {
		var url = BASE_PATH + '/companyStore/deleteRelate';
		var params = {
			storeId:storeId,
			companyId:companyId
		};
		httpPut(url,params,function(data){
			loadCxt();
		});
	}, function() {
		
	});
};

/**
 * 删除公司门店关系
 * @param storeIdArr
 * @param companyId
 */
function deleteCompanyStore (storeId,companyId) {
	Dialog.confirm("是否确认删除关联门店？", function() {

		var url = BASE_PATH + '/companyStore/deleteRelate';
		var params = {
			storeId:storeId,
			companyId:companyId
		};
		
		httpPut(url,params,function(data){
			loadCxt();
		});

	}, function() {

	});
}

/*
 *   公盘关系知道
 */


///**
// * 取消关联门店
// * @param storeIdArr
// * @param companyId
// */
function cancelgpCompanyStore (storeId,companyId,row) {
	var msg = "";
//	if (!fyIdEmpty){
//		msg = "要取消关联的门店已存在门店和房友账号关系，如有必要可在门店中进行房友账号的解绑或重新绑定<br/>";
//	}
	var storeNo=$(row).parent().parent().find("td").eq(1).find("a").text();
	var storeName=$(row).parent().parent().find("td").eq(2).text();
    var companyName = $("#companyName").val();
	msg += "请确认是否取消门店：" + storeNo.trim() + " "+ storeName.trim() +"和公司：" + companyName + "的公盘关联？";
	Dialog.confirm(msg, function() {
		var url = BASE_PATH + '/companyStore/deletegpRelate';
		var params = {
			storeId:storeId,
			companyId:companyId
		};
		httpPut(url,params,function(data){
			loadGpCxt();
		});
	}, function() {
		
	});
};

/**
 * 删除公司门店关系
 * @param storeIdArr
 * @param companyId
 */
function deletegpCompanyStore (storeId,companyId) {
	Dialog.confirm("是否确认删除关联公盘门店？", function() {

		var url = BASE_PATH + '/companyStore/deletegpRelate';
		var params = {
			storeId:storeId,
			companyId:companyId
		};
		
		httpPut(url,params,function(data){
			loadGpCxt();
		});

	}, function() {

	});
}


function loadCxt() {
	httpGet('companyStoreListForm', 'LoadCxtComStore', BASE_PATH
			+ '/companys/store/q', function() {
		pageInfo("companyStoreListForm", function() {
			loadCxt();
		});
	});
};

function loadGpCxt() {
	httpGet('gpcompanyStoreListForm', 'LoadCxtgpComStore', BASE_PATH
			+ '/companys/gpstore/q', function() {
		pageInfo("gpcompanyStoreListForm", function() {
			loadCxt();
		});
	});
};

/**
 * 设置维护人页面
 */
function goMaintainer() {
	var storeId = $("#storeId").val();
	var url = BASE_PATH + '/store/toMaintainer';
	var params = {
		storeId:storeId
	};
	var dialogOptions = {
		width : 800, 
		height : 120,
		ok : function() {
			// 确定
			var reback = saveMaintainer(storeId);
			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};
	Dialog.ajaxOpenDialog(url, params, "维护人", function(dialog, resData) {
		RelateUtil.dialog = dialog;
	}, dialogOptions);
}

function saveMaintainer(storeId){
	if (Validator.validForm("maintainerForm")) {
		var url = BASE_PATH + '/store/maintainerSave';
		var param = $("#maintainerForm").serialize();
		ajaxGet(url, param, function(data) {
			location.href = BASE_PATH + '/store/' + storeId;
		}, function(data){
			if(data != null && data.returnMsg != null){
				$("#msgId").empty().html(data.returnMsg);
				return false;
				/*Dialog.alertError(data.returnMsg);*/
			}
		});
	} else {
		return false;
	};
	return false;
}


/**
 * 无需续签/需续签Flag修正
 * 
 */
function updateNeededRenew(storeId,neededRenew){
	
	var url = BASE_PATH + "/store/ndr/" + storeId + "/" + neededRenew;
	var params={};
	
	ajaxGet(url, params, function(data) {
		location.href = BASE_PATH+'/store/'+storeId;
	});
}