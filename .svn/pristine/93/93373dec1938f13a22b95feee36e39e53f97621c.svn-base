/** ************************公共部分***************************** */
$(function() {

	// 初始化查询
	initList();
});

/** **************************demo js*************************** */
Contract = function() {
	dialog: null;
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('contractForm', 'LoadCxt', BASE_PATH + '/contract/q', function() {

		pageInfo("contractForm", function() {
			initList();
		});

	});
};
/**
 * 查询
 * 
 */
Contract.search = function() {
	$('#curPage').val('1');
	initList();
};

Contract.setSignDateType = function(type) {
	$('#signDateType').val(type);
};

function checkDate(self) {
	if ($("#dateCreateEnd").val() != ''
			&& $("#dateCreateStart").val() > $("#dateCreateEnd").val()) {
		Dialog.alertError("签约日期起始日不能大于结束日！");
		$(self).val('');
	}
};

/**
 * 获取OA审核状态
 */
Contract.getOAAuthStatus = function(flowId, id, companyId) {
	systemLoading("", true);
	var url = BASE_PATH + "/contracts/oa/state/" + flowId + "/" + id + "/"
			+ companyId;

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
			location.href = BASE_PATH + "/contract";
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
 * 提交审核,,调用OA接口
 */
Contract.toOAUpload = function(id) {

	var url = BASE_PATH + "/contracts/oa/upload/" + id;

	var params = {
	// id : id
	};

	ajaxGet(url, params, function(data) {

	}, function(data) {

		var returnMsg = data.returnMsg;
		Dialog.alertError(returnMsg);
	});

};

/**
 * 合同撤销
 */
Contract.cancel = function(contract) {
	
	var flag = false;
	
	$.ajax({
		url : BASE_PATH+"/storeDeposit/checkStoreDeposit",
		data : {"contractId":contract},
		type : 'GET',
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data && data.returnCode != '200') {
				Dialog.alertError(data.returnMsg);
				flag = true;
			}
		},
		error:function(data){
			var returnMsg = data.returnMsg;
			if (!returnMsg) {
				returnMsg = "校验保证金失败";
			}
			Dialog.alertError(returnMsg);
			flag = true;
		}
	});
	
	if(flag){
		return;
	}

	Dialog.confirm("合同作废后，签约门店将解除签约状态，是否确定要作废？", function() {

		var url = BASE_PATH + "/contract/cancel/" + contract;
		var params = {};

		ajaxGet(url, params, function(data) {

			location.href = BASE_PATH + "/contract";

		}, function(data) {
			var returnMsg = data.returnMsg;
			if (!returnMsg) {
				returnMsg = "作废失败";
			}
			Dialog.alertError(returnMsg);
		});

	}, function() {

	});

};

/**
 * 获取审核批注原因
 */
Contract.getOAOpinions = function(flowId) {
	var url = BASE_PATH + "/contract/oa/opinions/" + flowId;
	var params = {};
	ajaxGet(url, params, function(data) {
		var returnValue = data.returnValue;
	    var showContent = "<span class=\"\" style=\"float:right\">"+ 
"<a type=\"button\" href=\"javascript:void(0)\"  class=\"btn btn-default chacha\" style=\"float: right;margin-top: -5px;\">&times;</a>"+
"<h4 style=\"font-size:16px\"  class=\"border-bottom pdb10\"><strong>合同审批意见</strong></h4>";
		showContent += "<table class=\"table table-striped table-hover table-bordered\" border=\"1\" style=\"width:900px;\" id=\"logTable\"></table>";
		/*var html="<tr><th style=\"text-align: center;width:120px; border-right:none;\">合同审批意见</th>"+
		"<th style=\"text-align: center;width:380px;border-left:none;border-right:none;\"></th>"+
		"<th style=\"text-align: center;width:150px;border-left:none;border-right:none;\"></th>"+
		"<th style=\"text-align: center;width:250px; border-left:none;\"><a type=\"button\" href=\"javascript:void(0)\"  class=\"btn btn-default chacha\" style=\"float: right;margin-top: -5px;\">&times;</a></th>"+
		"</tr>";*/
		var html="<tr><th width=\"100px\" style=\"text-align: center\">序号</th>"+
		"<th width=\"400px\" style=\"text-align: center\">审批意见</th>"+
		"<th width=\"150px\" style=\"text-align: center\">审批人</th>"+
		"<th width=\"250px\" style=\"text-align: center\">审批日期</th>"+
		"</tr>";
		if(returnValue!=null){
			for (var i = 0; i < returnValue.length; i++) {
				html += "<tr>"+
				"<td style=\"text-align: center;border-right:0\" >"+(i+1)+"</td>"+
				"<td style=\"text-align: left\">"+returnValue[i].content+"</td>"+
				"<td style=\"text-align: center\">"+returnValue[i].empname+"</td>"+
				"<td style=\"text-align: center\">"+returnValue[i].create_date.replace("T"," ")+"</td>"+
				"</tr>";
			}
		}
		
		$.dialog({
			content : showContent,
			ok : function() {
				return;
			}
		});
		$('#ldg_lockmask').show();
		$("#logTable").html(html);
		$('.ui_state_highlight').hide();
	}, function(data) {

		var returnMsg = data.returnMsg;
		Dialog.alertError(returnMsg);
	});
};
/*Contract.getOAOpinions = function(flowId) {
	var url = BASE_PATH + "/contract/oa/opinions/" + flowId;
	var params = {};
	ajaxGet(url, params, function(data) {
		var returnValue = data.returnValue;
		var showContent = "<table class=\"table table-striped table-hover table-bordered\" border=\"1\" style=\"width:900px;\" id=\"logTable\"></table>";
		//var html = "<tr><th colspan=\"4\"  style=\"text-align: left\">合同审批意见<a type=\"button\" href=\"javascript:void(0)\"  class=\"btn btn-default chacha\" style=\"float: right;margin-top: -5px;\">&times;</a></th></tr>";
		var html="<tr><th style=\"text-align: center;width:120px; border-right:none;\">合同审批意见</th>"+
		"<th style=\"text-align: center;width:380px;border-left:none;border-right:none;\"></th>"+
		"<th style=\"text-align: center;width:150px;border-left:none;border-right:none;\"></th>"+
		"<th style=\"text-align: center;width:250px; border-left:none;\"><a type=\"button\" href=\"javascript:void(0)\"  class=\"btn btn-default chacha\" style=\"float: right;margin-top: -5px;\">&times;</a></th>"+
		"</tr>";
		html+="<tr><th width=\"100px\" style=\"text-align: center\">序号</th>"+
		"<th width=\"400px\" style=\"text-align: center\">审批意见</th>"+
		"<th width=\"150px\" style=\"text-align: center\">审批人</th>"+
		"<th width=\"250px\" style=\"text-align: center\">审批日期</th>"+
		"</tr>";
		if(returnValue!=null){
			for (var i = 0; i < returnValue.length; i++) {
				html += "<tr>"+
				"<td style=\"text-align: center;border-right:0\" >"+(i+1)+"</td>"+
				"<td style=\"text-align: center\">"+returnValue[i].content+"</td>"+
				"<td style=\"text-align: center\">"+returnValue[i].empname+"</td>"+
				"<td style=\"text-align: center\">"+returnValue[i].create_date.replace("T"," ")+"</td>"+
				"</tr>";
			}
		}
		
		$.dialog({
			content : showContent,
			ok : function() {
				return;
			}
		});
		$('#ldg_lockmask').show();
		$("#logTable").html(html);
		$('.ui_state_highlight').hide();
	}, function(data) {
		
		var returnMsg = data.returnMsg;
		Dialog.alertError(returnMsg);
	});
};
*/$(document).on('click', '.chacha', function(){
	$('.ui_state_highlight').click();
});

/**
 * 
 */
Contract.undo = function() {

	Dialog.alertInfo("撤销合同，请去OA系统发起撤销！");

};

/**
 * 删除
 */
Contract.remove = function(id) {

	var result = "是否确定作废此合同?</h3>";

	var params = {
		'id' : id
	};

	Dialog.confirm(result, function() {

		httpDelete(BASE_PATH + "/contract/" + id, params, function(data) {
			location.href = BASE_PATH + "/contract";
		}, function(data) {
			Dialog.alertError(data.returnMsg);
		});
	});
};

/**
 * 调用OA接口,获取ＯＡ模板
 */
Contract.getOATemplate = function(flowId) {

	var url = BASE_PATH + "/contracts/oa/template/" + flowId;

	var params = {
	};

	ajaxGet(url, params, function(data) {

	}, function(data) {

		var returnMsg = data.returnMsg;
		Dialog.alertError(returnMsg);
	});

};

/**
 * 分账
 */
Contract.goSplit = function(contractId) {
	var url = BASE_PATH + '/contract/toSplit';

	var params = {
		contractId:contractId
	};
	var headMsg = "保证金分账";
	var dialogOptions = {
		ok : function() {
			var storeIds = "";
			$("input:checkbox[name=isArrivalAct]:checked").each(function(){
				var storeId = $(this).val();
				storeIds = storeIds+storeId+',';
			});
			if(storeIds ==""){
				$("#warningSpan").text("请选择要分账的门店!");
				return false;
			}
			//校验金额是否变化
			var check =Contract.checkRestDepositFee(contractId);
			if(!check || check == undefined){
				$("#warningSpan").text("未分账金额已更新，请刷新页面后再分账!");
				return false;
			}else{
				storeIds = storeIds.substring(0, storeIds.length-1);
				// 保存之前 check oms到账保证金和crm收取的是否符合条件
				var arrivalDepositFeeDb = $("#arrivalDepositFeeDb").val(); // db里到账的保证金
				var depositFeeDb = $("#depositFeeDb").val(); // db里一个门店的保证金
				var nums = storeIds.split(",").length;
				if(Contract.checkDepositFee(arrivalDepositFeeDb,depositFeeDb,nums)){
					var reback = Contract.saveSplit(contractId,storeIds);
					return reback;
				}else{
					$("#warningSpan").text("OMS到账保证金 不能小于 CRM分账保证金!");
					return false;
				}
			};
			
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};
	Dialog.ajaxOpenDialog(url, params, headMsg, function(dialog, resData) {
		Contract.dialog = dialog;
	}, dialogOptions);
};

/**
 * check oms到账保证金和crm收取的是否符合条件
 */
Contract.checkDepositFee = function(arrivalDepositFeeDb,depositFeeDb,nums){
	if(arrivalDepositFeeDb < depositFeeDb*nums) {
		return false;
	}
	return true;
};

/**
 * check 剩余保证金是否一致
 */
Contract.checkRestDepositFee = function(contractId){
	var checkUrl = BASE_PATH + '/contract/checkRestDeposit';
	var checkparams = {
			contractId:contractId
		};
	var restSplitDepositFee =$("#restSplitDepositFeeSplit").val();
	var boo = false;
		//保存前校验剩余金额
		$.ajax({
			url : checkUrl,
			data : checkparams,
			type : 'GET',
			async:false,
			dataType : 'json',
			success : function(data) {
				if (data && data.returnCode == '200') {
					if(Number(data.returnValue) != Number(restSplitDepositFee)){
						boo = false;
					}else{
						boo = true;
					};
				}else{
					boo = false;
				}
			}
		});
		
		return boo;
};

/**
 * 分账保存
 * @param contractId
 * @param storeIds
 */
Contract.saveSplit = function(contractId,storeIds){
	systemLoading("", true);
	var url = BASE_PATH + '/contract/saveSplit';
	var params = {
			contractId:contractId,
			storeIds:storeIds
		};
	
	ajaxGet(url, params, function(data) {
		systemLoaded();
		location.href = BASE_PATH + '/contract';
	}, function(data){
		location.href = BASE_PATH + '/contract';
		systemLoaded();
	});
};


//新增合同
Contract.create = function()
{
	/**
	 * 取得用户所属中心
	 * @returns
	 */

	systemLoading("", true);
	var url= BASE_PATH + "/contract/center";
	ajaxGet(url, null, function(data) {
		var centerId="";
		var centerName="";
		if(data.returnValue.length <= 0)
		{
			systemLoaded();
			Dialog.alertInfo("登录用户没有所属中心不能新签合同！");
			return;
		}
		if(data.returnValue.length > 1)
		{
			var url = BASE_PATH + '/contract/center/list';
			var title = '选择所属中心';

			var params = {};
			var dialogOptions = {

				ok : function() {
					centerId="";
					centerName="";
					$("#errorId").hide();
					$("input[type=radio][name=chb_select]").each(function(){
				        if($(this).is(':checked')){
				        	centerId = $(this).next().val();
				        	centerName=$(this).parent().parent().find("td").eq(1).text().trim();
				        }
				    });
					if(centerId=="")
					{
						//Dialog.alertInfo("请选择所属中心后再草签！");
						$("#errorId").show();
						return false;
					}
					location.href  = BASE_PATH + '/contract/c/'+centerId+'/'+centerName;
					return true;
				},
				okVal : '确定',
				cancel : true,
				cancelVal : '取消',
				width : 550
			};

			Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

				Contract.dialog = dialog;

			}, dialogOptions);
		}
		else
		{
			centerId = data.returnValue[0].exchangeCenterId;
			centerName = data.returnValue[0].exchangeCenterName;
			location.href  = BASE_PATH + '/contract/c/'+centerId+'/'+centerName;
		}
	}, function(data) {
		Dialog.alertError(data.returnMsg);
		systemLoaded();
	});
}



//关闭，取消
Contract.closeSplit = function() {
	Contract.dialog.close();
};


Contract.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);

	$("#contractForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#contractForm").find("select").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	//筛选
	var signDateType = jsonMap["signDateType"];
	if (signDateType != undefined && signDateType != null && signDateType != "") {
		$("#divSignType").find("a").removeClass("active");
		$("#divSignType").find("a").eq(signDateType).addClass("active");
		$("#divSignType").find("a").eq(signDateType).click();
	}
	var curPage = jsonMap["curPage"];
    if (curPage > 1) {
        $('#curPageTemp').val(curPage);
        $('#sysPageChaneTemp').val(jsonMap["sysPageChane"]);
    }
};

Contract.reset=function (pageType) {
	$("#contractForm :text").val("");
	$("#contractForm").find("select").val("");
	$("#originalContractdistinction").val(0);
	$("#depositFeeStatus").val(0);
	$("#Valid").val(0);
	$("#refundState").val(0);
	$("#isaTob").val(0);
	var url = BASE_PATH + "/commons/clearSearchParam?pageType=" + pageType;
	$.ajax({
		type: "GET",
		url: url,
		async: true,
		dataType: "json",
		success: function (data) {//成功与否都不处理

		},
		error: function () {

		}
	});
	Contract.search();
};