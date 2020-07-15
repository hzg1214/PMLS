ContractChange = function() {
};

/**
 * 获取审核批注原因
 */
ContractChange.getOAOpinions = function(flowId) {

	var url = BASE_PATH + "/contracts/oa/opinions/" + flowId;

	var params = {};

	ajaxGet(url, params, function(data) {

		var showContent = "<table class=\"table table-striped table-hover table-bordered\">";
		showContent  = showContent+"<th>审批人</th><th>审批意见</th><th>审批时间</th>";
		var returnValue = data.returnValue;
		for (var i = 0; i < returnValue.length; i++) {
			showContent = showContent+"<tr>";
			showContent = showContent + "<td width=\"200\">"+returnValue[i].empname+"(" + returnValue[i].empnumber + ")"+"</td>";
			showContent = showContent + "<td width=\"600\">"+returnValue[i].content+"</td>";
			showContent = showContent + "<td width=\"200\">"+returnValue[i].create_date.replace("T"," ")+"</td>";
			showContent = showContent+"</tr>";
		}
		showContent = showContent+"</table>";
		$.dialog({
			width:900,
			content : showContent,
			lock : true,
			okVal : '确定',
			ok : function() {
				return;
			}
		});

	}, function(data) {

		var returnMsg = data.returnMsg;
		Dialog.alertError(returnMsg);
	});

};

/**
 * 获取OA审核状态
 */
ContractChange.getChgOAAuthStatus = function(flowId, contractId,contractStatus, chgType) {
	systemLoading("", true);
	var url = BASE_PATH + "/changeOAContract/oa/changestate/" + flowId;
	var sucUrl = BASE_PATH + "/contract/changeRecord/"+contractId+"/"+contractStatus;
	if('17002' == chgType){ // 17002:乙类转甲类
		url = BASE_PATH + "/changeOAContract/oa/changestate/btoa/" + flowId;
        sucUrl = BASE_PATH + "/contractChangeNew/list/"+contractId+"/"+contractStatus;
	}
    if('17003' == chgType){
        url = BASE_PATH + "/changeOAContract/oa/changestate/threePart/"+ flowId;
        sucUrl = BASE_PATH + "/contractChangeNew/list/"+contractId+"/"+contractStatus;
    }
	
	var params = {};
	ajaxGet(url, params, function(data) {
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
			location.href = sucUrl;
		}
		systemLoaded();
	}, function(data) {
		var returnMsg = data.returnMsg;
		if (!returnMsg) {
			returnMsg = "获取审核状态失败";
		}
		Dialog.alertError(returnMsg);
		systemLoaded();
	});
};

/**
 * 提交审核操作
 */
ContractChange.submitOaApprove = function($this,contractChangeId, contractId,isCombine,contractStatus,chgType) {
	var checkFlag = true;
	if('17001' == chgType){
		var url_check = BASE_PATH +'/stopcontract/getDepositBalance';
		$.ajax({
			url:url_check,
			async: false,
			type:"get",
			data:{
				id:contractChangeId,
				contractId:contractId
			},
			success:function(data){
				data = JSON.parse(data);
				if(data[0].depositBalance == null || data[0].depositBalance ==''){
					checkFlag = false;
				}
			}
		});
		if(!checkFlag){
			Dialog.alertError("提交前请填写保证金余额处理方式");
			return false;
		}		
	}
	
	
	$($this).hide();
	if ("true" != isCombine) {
		ContractChange.audit(contractChangeId,contractId,contractStatus,chgType);

	} else {
		var url = BASE_PATH + '/relate/oaoperate';
		var params={};
		ajaxGet(
				url,
				params,
				function(data) {
					ContractChange.busNo = data.returnValue;
					// 提交审核
					ContractChange.audit(contractChangeId,contractId,contractStatus,chgType);
				});
	}

};

ContractChange.audit = function(contractChangeId,contractId,contractStatus,chgType) {
	// Loading加载中...
	systemLoading("", true);

	var url = BASE_PATH + "/changeOAContract/oa/" + contractChangeId+"/"+contractId;
	var sucUrl = BASE_PATH + "/contract/changeRecord/"+contractId+"/"+contractStatus;
	if('17002' == chgType){ // 17002:乙类转甲类
		url = BASE_PATH + "/changeOAContract/oa/btoa/" + contractChangeId+"/"+contractId;
        sucUrl = BASE_PATH + "/contractChangeNew/list/"+contractId+"/"+contractStatus;
	}
	if('17003' == chgType){
        url = BASE_PATH + "/changeOAContract/oa/threePart/" + contractChangeId+"/"+contractId;
        sucUrl = BASE_PATH + "/contractChangeNew/list/"+contractId+"/"+contractStatus;
	}
	if('17004' == chgType){//门店迁址
		url = BASE_PATH + "/storeRelocation/toOaStoreRelocationSubmit/" + contractChangeId+"/"+contractId;
		sucUrl = BASE_PATH + "/contractChangeNew/list/"+contractId+"/"+contractStatus;
	}
	
	var params = {
			busNo : ContractChange.busNo
			//2017/06/27 cning Add Start
			,chgType:chgType
			//2017/06/27 cning Add Etart
	};

	ajaxGet(url, params, function(data) {
		location.href = sucUrl;
	}, function(data) {
		var returnMsg = data.returnMsg;
		Dialog.alertError(returnMsg);
		systemLoaded();
	});
};

/**
 * 删除变更记录
 * add by haidan 2018/3/22
 */
ContractChange.delete = function (id, contractId, contractStatus) {
	Dialog.confirm("是否确认作废？", function() {
		systemLoading("", true);
		var url = BASE_PATH + "/stopcontract/delete/";
		var params = {id:id};
		// 发起请求
		httpPut(url, params, function (data) {
			// 跳转至变更记录页面
			location.href = BASE_PATH + '/contractChangeNew/list/' + contractId + "/" + contractStatus;
		}, function (data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});
	},function () {
		
	});
};