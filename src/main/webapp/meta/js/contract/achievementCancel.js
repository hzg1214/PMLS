/**
 *  业绩撤销js
 */
$(function() {
	
});
AchievementCancel = function() {
	dialog: null;
};
//全选
AchievementCancel.selectAll = function(self) {
	if(self.checked){
		$("input[name='isCancel']").prop('checked',true);
	}else{
		$("input[name='isCancel']").prop('checked',false);
	}
};
//单选
AchievementCancel.selectThis = function(self) {
	var checkAll = true;
	$("input[name='isCancel']").each(function(index,val){
		
		if(!val.checked){
			checkAll = false;
		}
		
	});
	if(checkAll){
		$("input[name='isCancelAll']").prop("checked",true);
	}else{
		$("input[name='isCancelAll']").prop("checked",false);
	}
	
};

/**
 * 撤销
 */
AchievementCancel.goAchievementCancelAdd = function(contractId , contractStatus) {
	var url = BASE_PATH + '/achievement/cancel/goAchievementCancelAdd';
	var params = {
		contractId:contractId
	};
	var headMsg = "业绩撤销";
	var dialogOptions = {
			width : 800,
			
		ok : function() {
			var storeIds = "";
			var storeNo;
			$("input:checkbox[name=isCancel]:checked").each(function(){
				var storeId = $(this).val();
				storeIds = storeIds+storeId+',';
				
				//门店停业上报审核中，已停业，不允许撤销
				if($(this).attr("businessStatus") == '20902' || $(this).attr("businessStatus") == '20903'){
					storeNo = $(this).attr("storeNo");
					return false;
				}
			});
			if(storeIds ==""){
				$("#warningSpan").text("请选择要撤销的门店!");
				return false;
			}
			
			if(storeNo){
				$("#warningSpan").text("门店编号为:"+storeNo+"的门店正在停业上报中,不允许撤销!");
				return false;
			}
			
			//去掉最后一个逗号
			storeIds = storeIds.substring(0, storeIds.length-1);
			
			var cancelReason =$.trim($("#cancelReason").val());
			if(cancelReason == ""){
				$("#warningSpan").text("请填写撤销原因!");
				return false;
			}
			
			//是否是跨区经办人,是，验证，不是不验证
			var isCombine = $("#isCombine").val();
			/*var busNo =$("#busNo").val();
			if("true" == isCombine){
				if(busNo == "" || busNo == undefined){
					$("#warningSpan").text("请选择事业部区域!");
					return false;
				}
			}*/

			var contractTypeName =$.trim($("#contractTypeName").val());
			var companyName =$("#companyName").val();
			
			if("true" != isCombine){
				var params = {
						contractId:contractId,
						storeIds:storeIds,
						cancelReason:cancelReason,
						busNo:$("#busNo").val(),
						contractTypeName:contractTypeName,
						companyName:companyName
					};
				reback = AchievementCancel.saveCancel(params,contractId,contractStatus);
				return reback;
			}else{
				var url = BASE_PATH + '/relate/oaoperate';
				var params={};
				ajaxGet(
						url,
						params,
						function(data) {
							$("#busNo").val(data.returnValue);
							var params = {
									contractId:contractId,
									storeIds:storeIds,
									cancelReason:cancelReason,
									busNo:data.returnValue,
									contractTypeName:contractTypeName,
									companyName:companyName
								};
							reback = AchievementCancel.saveCancel(params,contractId,contractStatus);
							return reback;
						});
			}
		},
		okVal : '提交审核',
		cancel : true,
		cancelVal : '取消'
	};
	Dialog.ajaxOpenDialog(url, params, headMsg, function(dialog, resData) {
		
		dialog.position('50%', '25%');
		AchievementCancel.dialog = dialog;
	}, dialogOptions);
};

/**
 * 撤销提交审核
 * @param contractId
 * @param storeIds
 */
AchievementCancel.saveCancel = function(params, contractId, contractStatus){
	systemLoading("", true);
	var url = BASE_PATH + '/achievement/cancel/application';
	restPost(url, params, function(data) {
		systemLoaded();
		location.href = BASE_PATH + '/achievement/cancel/'+contractId+"/"+contractStatus;
	}, function(data){
		systemLoaded();
	});
};

/**
 * 关闭，取消
 */
AchievementCancel.closeCancel = function() {
	AchievementCancel.dialog.close();
};

/**
 * 撤销变更
 */
AchievementCancel.goAchievementCancelEdit = function(achievementCancelNo,contractStatus,contractId) {
	var url = BASE_PATH + '/achievement/cancel/goAchievementCancelEdit';
	var params = {
		contractId:contractId,
		achievementCancelNo:achievementCancelNo
	};
	var headMsg = "业绩撤销变更";
	var dialogOptions = {
			width : 800,
			
		ok : function() {
			var storeIds = "";
			$("input:checkbox[name=isCancel]:checked").each(function(){
				var storeId = $(this).val();
				storeIds = storeIds+storeId+',';
			});
			if(storeIds ==""){
				$("#warningSpan").text("请选择要撤销的门店!");
				return false;
			}
			//去掉最后一个逗号
			storeIds = storeIds.substring(0, storeIds.length-1);
			
			var cancelReason =$.trim($("#cancelReason").val());
			if(cancelReason == ""){
				$("#warningSpan").text("请填写撤销原因!");
				return false;
			}
			
			//是否是跨区经办人,是，验证，不是不验证
			var isCombine = $("#isCombine").val();
			/*var busNo =$("#busNo").val();
			if("true" == isCombine){
				if(busNo == "" || busNo == undefined){
					$("#warningSpan").text("请选择事业部区域!");
					return false;
				}
			}*/
			
			var contractTypeName =$.trim($("#contractTypeName").val());
			var companyName =$("#companyName").val();
			
			if("true" == isCombine){
				var params = {
						contractId:contractId,
						storeIds:storeIds,
						cancelReason:cancelReason,
						busNo:$("#busNo").val(),
						contractTypeName:contractTypeName,
						companyName:companyName,
						achievementCancelNo:achievementCancelNo
					};
				var reback = AchievementCancel.saveEditCancel(params,contractId,contractStatus);
				return reback;
			}else{
				var url = BASE_PATH + '/relate/oaoperate';
				var params={};
				ajaxGet(
						url,
						params,
						function(data) {
							$("#busNo").val(data.returnValue);
							var params = {
									contractId:contractId,
									storeIds:storeIds,
									cancelReason:cancelReason,
									busNo:data.returnValue,
									contractTypeName:contractTypeName,
									companyName:companyName
								};
							reback = AchievementCancel.saveCancel(params,contractId,contractStatus);
							return reback;
						});
			}
		},
		okVal : '提交审核',
		cancel : true,
		cancelVal : '取消'
	};
	Dialog.ajaxOpenDialog(url, params, headMsg, function(dialog, resData) {
		
		dialog.position('50%', '25%');
		AchievementCancel.dialog = dialog;
	}, dialogOptions);
};

/**
 * 撤销变更提交审核
 * @param contractId
 * @param storeIds
 */
AchievementCancel.saveEditCancel = function(params, contractId, contractStatus){
	systemLoading("", true);
	var url = BASE_PATH + '/achievement/cancel/editApplication';
	restPost(url, params, function(data) {
		systemLoaded();
		location.href = BASE_PATH + '/achievement/cancel/'+contractId+"/"+contractStatus;
	}, function(data){
		systemLoaded();
	});
};


/**
 * 获取OA审核状态
 */
AchievementCancel.getCancelOAAuthStatus = function(flowId,achievementCancelNo, contractId,contractStatus) {
	systemLoading("", true);
	var url = BASE_PATH + "/achievement/oa/changestate/" + flowId;
	var params = {
			     "achievementCancelNo":achievementCancelNo,
			     "contractId":contractId,
			     "contractStatus":contractStatus
			};
	ajaxGet(url, params, function(data) {
		systemLoaded();
		var returnValue = data.returnValue;
		if (1 == returnValue) {
			Dialog.alertInfo("流程未发出，待发", true);
		} else if (3 == returnValue) {
			Dialog.alertInfo("流程发出，暂未处理", true);
		} else if (4 == returnValue) {
			Dialog.alertInfo("流程部分处理", true);
		} else if (6 == returnValue) {
			Dialog.alertInfo("被退回上一个节点", true);
		} else if (7 == returnValue) {
			Dialog.alertInfo("被上个节点取回", true);
		} else if (0 == returnValue) {
			Dialog.alertInfo("流程结束,审核通过", true);
		} else if (5 == returnValue) {
			Dialog.alertInfo("流程被撤销", true);
		} else if (15 == returnValue) {
			Dialog.alertInfo("流程终止", true);
		} else {
			Dialog.alertInfo("未获取到状态", true);
		}
		if (0 == returnValue || 5 == returnValue || 15 == returnValue) {
			location.href = BASE_PATH + "/achievement/cancel/"+contractId+"/"+contractStatus;
		}
	}, function(data) {
		systemLoaded();
		var returnMsg = data.returnMsg;
		if (!returnMsg) {
			returnMsg = "获取审核状态失败";
		}
		Dialog.alertError(returnMsg);
	});
};


AchievementCancel.operateAuditCt = function (achievementCancelNo, contractId, contractStatus) {
	if(!isBlank(achievementCancelNo)) {
		systemLoading("", true);
		$.ajax({
			url:BASE_PATH+"/achievement/operateAuditCt",
			data:$.param({
				achievementCancelNo:achievementCancelNo
			}),
			type:"post",
			success:function(data){
				data = JSON.parse(data);
				if(data && data.returnCode == '200'){
					Dialog.alertSuccess("状态变更成功!");
					$("#operateAuditCt").hide();
					systemLoaded();
					location.href = BASE_PATH + '/achievement/cancel/'+contractId+"/"+contractStatus;
				}
			},
			error:function(){
				Dialog.alertError("状态变更失败");
				$("#operateChangeCt").hide();
				systemLoaded();
			}
		});
	}

};
