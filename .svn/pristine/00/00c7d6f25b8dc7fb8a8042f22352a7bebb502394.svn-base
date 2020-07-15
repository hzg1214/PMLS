/** ************************公共部分***************************** */
var urlFlag=0;
$(function() {

	// 初始化查询
});

/** ***************************************************** */
RelateUtil = function() {
	dialog: null;
};

/**
 * 关联门店
 */
function relateStores(type) {

	var url = BASE_PATH + '/relate';
	var title = '关联门店';

	var orderName = $("#orderName").val();
	var orderType = $("#orderType").val();
	var params = {
		orderName : orderName,
		orderType : orderType,
		type : type
	};
	var dialogOptions = {

		width : 800,
		/* height : 760, */

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

// 关闭，取消
RelateUtil.close = function() {
	RelateUtil.dialog.close();
};

// 关联门店
RelateUtil.relateStore = function() {

	// 关联后，门店展示到父页面上
	var htmls = "";
	$('input[name="storeIds"]:checked')
			.each(
					function() {
						if ($("#storeIds" + $(this).val()).val() == $(this)
								.val()) {
							return true;
						}
						htmls += "<tr>";
						$(this).parent().siblings().each(function(i) {
							if (i > 1 && i != 5) {
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
	var storeList = $("#relateStoreTableId",window.parent.document).find("tr").length-1;
	$("#storeCount", window.parent.document).val(storeList);
	
};

// 移除门店
function removeTr(obj,type) {

	// 移除自己
	$(obj).parent().parent().remove();
//	if (type=="company") {
//		var storeCount = $("#storeCount").val();
//		if(removeTr > 0){
//			$("#storeCount").val(storeCount - 1);
//		}
//	}
	// 1 是合同中删除标记
	if (type=="1") {
		// 合作门店数重新计算
		var storeNum = $("#storeNum").val();
		$("#storeNum").val(storeNum - 1);

		// 总保证金重新计算
		calculateTotleDepositFee();
	}
	//公盘中删除
	if(type=='2'){
        // 合作门店数重新计算
        var storeNum = $("#storeNum").val();
        $("#storeNum").val(storeNum - 1);
    }
};

/**
 * 设置关联门店
 */
function setRelateStore() {

	// 校验创客前，必先关联门店
	var storeIds = "";
	$('input[name="storeIds"]').each(function() {
		storeIds = storeIds + "," + $(this).val();
	});
	if (storeIds != "" && storeIds.length > 0) {
		storeIds = storeIds.substring(1, storeIds.length);
	}
	$("#storeIdArray").val(storeIds);

}

/**
 * 校验是否关联了门店
 */
function checkRelateStore() {

	// 校验创客前，必先关联门店
	var relateStores = $("#storeIdArray").val();
	if (!relateStores) {
		Dialog.alertInfo("请关联门店信息");
		return false;
	} else {
		return true;
	}

}

/**
 * 关联客户
 */
function relateCompany(type) {

	// if(type == "fromContract"){
	// if ($("#partyB").val() != "") {
	// Dialog.alertInfo("客户已选定，不能修改");
	// return false;
	// }
	// }
	var url = BASE_PATH + '/relate/company';
	var title = '关联客户';

	var orderName = $("#orderName").val();
	var orderType = $("#orderType").val();
	var params = {
		orderName : orderName,
		orderType : orderType,
		type:type
	};

	var dialogOptions = {

		width : 800,
		/* height : 760, */

		ok : function() {

			// 确定
			var reback;
			/** 
			 * 2017-09-27 crm新增 门店停业状态  选择公司后 门店联动 需要剔除掉 已停业的门店
			 * estateReportAdd.jsp 页面点击 选择公司按钮 弹出公司 pop框 
			 * 
			 * */
			if (type == "fromLinkage") {
				reback = RelateUtil.relateCompanyForLinkage();
			} else {
				reback = RelateUtil.relateCompany();
			}
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
function relateCompany(type,releaseCityNo,releaseCityNoflag) {
	
	// if(type == "fromContract"){
	// if ($("#partyB").val() != "") {
	// Dialog.alertInfo("客户已选定，不能修改");
	// return false;
	// }
	// }
	var url = BASE_PATH + '/relate/company';
	var title = '关联客户';
	
	var orderName = $("#orderName").val();
	var orderType = $("#orderType").val();
	var params = {
			orderName : orderName,
			orderType : orderType,
			type:type,
			releaseCityNo:releaseCityNo,
			releaseCityNoflag:releaseCityNoflag
	};
	
	var dialogOptions = {
			
			width : 800,
			/* height : 760, */
			
			ok : function() {
				
				// 确定
				var reback;
				/** 
				 * 2017-09-27 crm新增 门店停业状态  选择公司后 门店联动 需要剔除掉 已停业的门店
				 * estateReportAdd.jsp 页面点击 选择公司按钮 弹出公司 pop框 
				 * 
				 * */
				if (type == "fromLinkage") {
					reback = RelateUtil.relateCompanyForLinkage();
				} else if(type == "fromContract"){
					reback = RelateUtil.relateCompany();
				}else if(type == "fromGpContract"){//公盘公司列表
					reback = RelateUtil.relateCompanyGP();
				}else if(type == "fromGpCsContract"){//公盘初始会员公司列表
					reback = RelateUtil.relateCsMemberCompany();
				}
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

/** 点击选择公司按钮后 弹出dialog 选择完公司后 点击确认按钮 触发以下事件 */
RelateUtil.relateCompany = function() {
	var selectCompany = $(':radio[name=selectCompanyId]:checked');
	var companyId = selectCompany.val();
	var checkUrl = BASE_PATH+ "/store/qr";
	var checkParams = {companyId : companyId};
    $.ajax({
        url : checkUrl,
        data : checkParams,
        type : 'GET',
		async:false,
        dataType : 'json',
        success : function(data) {
            if (data && data.returnCode == '200') {
                dealWithCallBack(selectCompany,data);
                return;
            }
            Dialog.alertError(data.returnMsg);
        }
    });
};


function dealWithCallBack(selectCompany,data) {
        /** 判断返回是否成功 判断returnCode 是否有值 有的话 说明返回报错 没有就说明返回成功 */
        if(data.returnCode != null && data.returnCode != undefined && data.returnCode != "" && data.returnCode != 200){
            Dialog.alertInfo(data.returnMsg,false,true);
            return;
        }
        /** 返回的值 即Store对象的List */
        var returnValue = data.returnValue;
        if(returnValue == null || returnValue.length==0){
            Dialog.alertInfoToUrl("公司未关联门店，请在公司信息中关联门店后再新建合同！", BASE_PATH + '/contract',true);
            return;
        }
        if (returnValue != undefined && returnValue != null) {
            /**
             * 公司的城市编码
             */
            var companyCityNo = returnValue[0].companyCityNo;
            $('#companyCityNo').val(companyCityNo);
            var companyCityName = returnValue[0].companyCityName;
            $('#companyCityName').val(companyCityName);
            /** 合同状态 门店状态的标识 */
            var hasSign=false,isBusinessStatus=false;
            /** 合同状态,门店状态,城市编码*/
            var contractType,businessStatus;

            /** 循环遍历StoreDtoList 判断门店状态 */
            $.each(returnValue,function(i,n){
                businessStatus	=	n.businessStatus;
                /** 判断所有的门店的营业状态 如果所有的门店都停业或者处在停业申请中 需要弹框提示 */
                if(businessStatus != '20902' && businessStatus != '20903'){
                    isBusinessStatus = true;
                    return false;
                }
            });
            if(!isBusinessStatus){
                Dialog.alertInfo("所选择的公司旗下的门店均已停业或停业上报申请中，请选择其他公司签约！",false,true);
                return;
            }

            /** 循环遍历StoreDtoList 判断合同状态 */
            $.each(returnValue,function(i,n){
                contractType 	=	n.contractType;
                /** 判断所有的门店的合同状态 */
                if(n.businessStatus == '20901' && ( contractType==0 || contractType==null || contractType=='10301')){
                    hasSign = true;
                    return false;
                }
            });
            if(!hasSign){
                Dialog.alertInfo("该公司所有门店已签约，请选择其他公司进行签约！",false,true);
                return;
            }

            /** 赋值companyId */
            $("#companyId").val(selectCompany.val());
            var companyNo = selectCompany.attr('data-companyNo');
        	if(companyNo !="" && companyNo != null && companyNo != undefined ) {
        		$("#companyInfoNo").val(companyNo);
        	}
            /** 赋值公司名称 */
            var siblingsVar = selectCompany.parent().siblings();

            var  businessLicenseNostr= $.trim(siblingsVar.eq(14).html()) ;
            if(businessLicenseNostr == "" || $.trim(siblingsVar.eq(15).html()) == ""
                || $.trim(siblingsVar.eq(1).html()) == "" || $.trim(siblingsVar.eq(6).html()) == ""
                || siblingsVar.eq(7).html() == "" || siblingsVar.eq(8).html() == ""){
                Dialog.alertInfo("选择的经纪公司信息不全，需中心总权限在公司详情中进行补充！",false,true);
                return;
            }

            if(businessLicenseNostr.length!=15&&businessLicenseNostr.length!=18){
                Dialog.alertInfo("选择的经纪公司统一社会信用代码不准确，需中心总权限在公司详情中进行修改！",false,true);
                return;
            }

            $("#partyB").val($.trim(siblingsVar.eq(1).html()));
            $("#partyBcityNo").val(siblingsVar.eq(3).html());
            $("#partyBdistrictNo").val(siblingsVar.eq(4).html());
            $("#partyBAddress").val(siblingsVar.eq(6).html());
            $("#partyBCityName").val(siblingsVar.eq(7).html());
            $("#partyBDistrictName").val(siblingsVar.eq(8).html());
            /** 营业执照编号 */
            $("#registrId").val(siblingsVar.eq(14).html());
            /** 法人代表 */
            $("#lealPerson").val(siblingsVar.eq(15).html());

            /** 清空合作门店表格中的tr */
            $("#relateStoreTableId tr:gt(0)").remove();

            /** 合作门店信息 table 生成 */
            var validStoreNum = 0;
            var templateData  = {};
            var param		  = [];
            $.each(data.returnValue,function(i,n){
                var contractType = n.contractType;
                /** 不显示已经签约的非A类合同门店 */
                if(contractType!=0 && contractType!=null && contractType!='10301') return true;
                /** 不显示已停业或者停业申请中的门店 */
                if(n.businessStatus == '20902' || n.businessStatus == '20903') return true;
                /** 合作门店数量 */
                param[validStoreNum] = {};
                param[validStoreNum].storeNo 	= 	n.storeNo;
                param[validStoreNum].storeId 	= 	n.storeId;
                param[validStoreNum].name 		= 	n.name;
                param[validStoreNum].addressDetail = n.addressDetail;
                param[validStoreNum].maintainerName = n.maintainerName;
                param[validStoreNum].maintainer = n.maintainer;
                param[validStoreNum].addressDetail = n.addressDetail;
                param[validStoreNum].storeManager = n.storeManager;
                param[validStoreNum].storeManagerPhone = n.storeManagerPhone;
                var abtypeStore = n.abtypeStore;
                var aType 		= abtypeStore == 19901 ? "selected='selected'" : "";
                var bType 		= abtypeStore == 19902 ? "selected='selected'" : "";
                var bTypediv 	= abtypeStore == 19902 ? "display:inline-block":"display:none";

                var bTypenamelst = n.btypeStoreName;
                if(bTypenamelst == null){
                    bTypenamelst = '';
                }else if(abtypeStore == 19902){
                    bTypenamelst = "乙3";
                }
                var bTypelst = n.btypeStore;
                if(bTypelst == null){
                    bTypelst = '';
                }else if(abtypeStore == 19902){
                    bTypelst = "20003";
                }

                param[validStoreNum].aType = aType;
                param[validStoreNum].bType = bType;
                param[validStoreNum].bTypediv = bTypediv;
                param[validStoreNum].bTypenamelst = bTypenamelst;
                param[validStoreNum].bTypelst = bTypelst;
                validStoreNum++;

            });
            /** 赋值传递到template模板里边的参数 */
            templateData.list = param;
            /** 显示合作门店数量 */
            $("#storeNum").val(validStoreNum);
            console.log(templateData);
            /** 将生成的表格tr放入table中 */
            var html = template('template_relateStoreTable', templateData);
            $("#relateStoreTableId tbody").append(html);
            /** 总保证金计算 */
            var flag = 1;
            calculateTotleDepositFee(flag);
        }
    }

function hasBtype(btypelst, bType)
{
	var rtn = "";
	var strs= new Array();  
	strs=btypelst.split(";");  
	for (i=0;i<strs.length ;i++ ) 
	{ 
		if (strs[i]== bType)
		{
			rtn = strs[i];
		}
	} 
	return rtn;
}

/** 新房联动选择公司后 点击确认触发事件 */
RelateUtil.relateCompanyForLinkage = function() {
	var selectCompany	=	$(':radio[name="selectCompanyId"]:checked');
	var siblingsVar = selectCompany.parent().siblings();
	
	/** 赋值 */
	$("#reportCompanyId").val(siblingsVar.eq(0).html());
	/*$("#reportCompanyNm").val(siblingsVar.eq(1).html());*/
	//去除空字符
	$("#reportCompanyNm").val($.trim(siblingsVar.eq(1).html()));
	
	var companyId = selectCompany.val();

	var checkUrl = BASE_PATH + "/store/qr";
	var checkParams = {
		companyId : companyId
	};

	/** 异步加载门店列表 */
	ajaxGet(checkUrl,checkParams,function(data) {
		if (data.returnValue != undefined) {
			var html = "<option value=''>请选择...</option>";
			$.each(data.returnValue,function(i,n){
				html+=	"<option value='"+n.storeId + "," 
						+ n.name + "," + n.maintainer + "," 
						+ n.maintainerName + ","+n.cityNo+"' businessStatus='"+n.businessStatus+"'>"
						+n.address+"("+n.acCityName+")"+"</option>";
			});
			$("#storeNm").empty().append(html);
			$("#tmpContactId").val("");
			$("#tmpContactNm").val("");
		}
	},
	function(data) {
		Dialog.alertError(data.returnMsg);
	});
};

/**
 * 门店选择事件
 */
function storeSelect(elem){
	/** 判断门店状态 */
//	var businessStatus = $(':selected',elem).attr("businessStatus");
//	if(businessStatus == '20902'){
//		Dialog.alertError("该门店正在停业申报中");
//		/** 下拉单还原到请选择按钮 */
//		$('option:eq(0)',elem).prop("selected","selected");
//		return;
//	}else if(businessStatus == '20903'){
//		Dialog.alertError("该门店已停业");
//		$('option:eq(0)',elem).prop("selected","selected");
//		return;
//	}
	
	var url = BASE_PATH + "/storeMaintainer/chkmaintainer";
	var value = elem.value;

	if(value == ""){
		return;
	}

	var splitValue = value.split(",");
	var params = {
		storeId:splitValue[0]
	}

    ajaxGet(url,params,function(data) {
    	$("#tmpContactNm").val(splitValue[3]);
    	$("#tmpContactId").val(splitValue[2]);
    	var url = BASE_PATH + "/report/getLinkUserCenter";
		var params = {linkUserCode:splitValue[2]};
		ajaxGet(url, params, function(data2) {
			var dataLength =  data2.returnValue.length;
			var result = "<option value=''>请选择业绩归属中心</option>";
			$.each(data2.returnValue, function(n, value) {
				if(dataLength > 1) {
					result += "<option value='" + value.exchangeCenterId + "'>"
					+ value.exchangeCenterName + "</option>";
				}
				if(dataLength > 0 && dataLength < 2) {
					result += "<option value='" + value.exchangeCenterId + "' selected>"
					+ value.exchangeCenterName + "</option>";
				}
			});
			$("#achieveCenter").empty();
			//$("#achieveCenter").html('');
			$("#achieveCenter").append(result);
		}, function() {
		});
	},function(data) {
        Dialog.alertError(data.returnMsg);
	});
}


/**
 * 关联事业部人员
 * type:1.选择业绩归属人 2.选择维护人
 */
function relateUser(type,storeId) {

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
			var reback = RelateUtil.relateUser(type,storeId);

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

// 关联客户  type:1.选择业绩归属人 2.选择维护人
RelateUtil.relateUser = function(type,storeId) {

	// 关联后，客户展示到父页面上
	
	var userId = $('input:radio[name=selectUserId]:checked').val();
	// 点击的是门店列表里的选择
	if (storeId != null) {
		$("#maintainerId"+storeId).val(userId);
	}
	$('input:radio[name="selectUserId"]:checked').each(
			function() {
				
				$(this).parent().siblings().each(
						function(i) {
							if (i == 0) {
								if (type == 1) {
									$("#expandingPersonnelId").val($(this).html());
								} 
								if (type == 2) {
									$("#maintainer"+storeId).val($(this).html());
								}
							}
							if (i == 1) {
								if (type == 1) {
									$("#expandingPersonnel").val($(this).html());
								}
								if (type == 2) {
									$("#maintainerName"+storeId).val($(this).html());
								}
							}
						});
			});
};

// 关闭弹窗
RelateUtil.close = function() {
	RelateUtil.dialog.close();
};


/**
 * 门店维护人
 */
function relateStoreUser(storeId,storeCenterId) {
	var url = BASE_PATH + '/relate/storeuser';
	var title = '门店维护人';
	var orderName = $("#orderName").val();
	var orderType = $("#orderType").val();
	var params = {
		orderName : orderName,
		orderType : orderType,
		"storeCenterId": storeCenterId
	};
	var dialogOptions = {
		width : 800,
		ok : function() {
			// 确定
			var reback = RelateUtil.relateStoreUser(storeId);
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

};
RelateUtil.relateStoreUser = function(storeId) {
	var maintainer = "";
	var maintainerName = "";
	// 关联后，客户展示到父页面上
	$('input:radio[name="selectUserId"]:checked').each(function() {
		$(this).parent().siblings().each(function(i) {
			if (i == 0) {
				maintainer = $(this).html();
			}
			if (i == 1) {
				maintainerName = $(this).html();
			}
		});
	});
	if(maintainer=="" && maintainerName==""){
		location.href = BASE_PATH + '/store/'+storeId;
		return false;
	}
	
	// 保存选择的维护人
	var saveUrl = BASE_PATH + '/storeMaintainer/savemt';
	systemLoading("", true);
	var param = {"storeId":storeId,
			"maintainer":maintainer,
			"maintainerName":maintainerName,
		};
	$.ajax({
		url : saveUrl, // action目标
		type : 'POST',
		data : param ,
		//dataType : "json",
		success : function(data) {
			var returndata = eval("("+data+")");
			var returnCode = returndata.returnCode;
			systemLoaded();
			if("200" == returnCode){
				location.href = BASE_PATH + '/store/'+storeId;
			}
			else{
				var msg = returndata.returnMsg;
				if(!msg){
					msg = "您分配的人员与当前维护人相同，请选择其他人员！"
				}
				Dialog.alertError(msg);
			}
			
		},
		error:function(data){
			Dialog.alertError(data.returnMsg);
		}
	});
};

/** 公盘点击选择公司按钮后 弹出dialog 选择完公司后 点击确认按钮 触发以下事件 */
RelateUtil.relateCompanyGP = function() {
	var selectCompany = $(':radio[name=selectCompanyId]:checked');
	var companyId = selectCompany.val();
	var checkUrl = BASE_PATH+ "/store/gpqr";
	var checkParams = {companyId : companyId};
	$.ajax({
		url : checkUrl,
		data : checkParams,
		type : 'GET',
		async:false,
		dataType : 'json',
		success : function(data) {
			if (data && data.returnCode == '200') {
				dealGpStore(selectCompany,data);
				return;
			}
			Dialog.alertError(data.returnMsg);
		}
	});
};

function dealGpStore(selectCompany, data) {
	/** 判断返回是否成功 判断returnCode 是否有值 有的话 说明返回报错 没有就说明返回成功 */
	if (data.returnCode != null && data.returnCode != undefined && data.returnCode != "" && data.returnCode != 200) {
		Dialog.alertInfo(data.returnMsg,false,true);
		return;
	}
	/** 返回的值 即Store对象的List */
	var returnValue = data.returnValue;
	if (returnValue == null || returnValue.length == 0) {
		Dialog.alertInfoToUrl("公司未关联公盘门店，请在公司信息中关联公盘门店后再新建公盘合同！", BASE_PATH + '/gpContract',true);
		return;
	}
	if (returnValue != undefined && returnValue != null) {
		/** 合同状态 门店状态的标识 */
		var hasSign = false, isBusinessStatus = false;
		/** 合同状态,门店状态,城市编码*/
		var isGpSign, businessStatus;

		/** 循环遍历StoreDtoList 判断门店状态 */
		$.each(returnValue, function (i, n) {
			businessStatus = n.businessStatus;
			/** 判断所有的门店的营业状态 如果所有的门店都停业或者处在停业申请中 需要弹框提示 */
			if (businessStatus != '20902' && businessStatus != '20903') {
				isBusinessStatus = true;
				return false;
			}
		});
		if (!isBusinessStatus) {
            Dialog.alertInfo("所选择的公司旗下的门店均已停业或停业上报申请中，请选择其他公司签约！",false,true);
			return;
		}

		/** 循环遍历StoreDtoList 判断合同状态 */
		$.each(returnValue, function (i, n) {
            isGpSign = n.isGpSign;
			/** 判断所有的门店的合同状态 */
			if (n.businessStatus == '20901' && (isGpSign == 12502 || isGpSign == null)) {
				hasSign = true;
				return false;
			}
		});
		if (!hasSign) {
			Dialog.alertInfo("该公司所有门店已签约，请选择其他公司进行签约！",false,true);
			return;
		}

		/** 赋值companyId */
		$("#companyId").val(selectCompany.val());
		/** 赋值公司名称 */
		var siblingsVar = selectCompany.parent().siblings();

		var businessLicenseNostr = $.trim(siblingsVar.eq(14).html());
		if (businessLicenseNostr == "" || $.trim(siblingsVar.eq(15).html()) == ""
			|| $.trim(siblingsVar.eq(1).html()) == "" || $.trim(siblingsVar.eq(6).html()) == ""
			|| siblingsVar.eq(7).html() == "" || siblingsVar.eq(8).html() == "") {
			Dialog.alertInfo("选择的经纪公司信息不全，需中心总权限在公司详情中进行补充！",false,true);
			return;
		}

		if (businessLicenseNostr.length != 15 && businessLicenseNostr.length != 18) {
			Dialog.alertInfo("选择的经纪公司统一社会信用代码不准确，需中心总权限在公司详情中进行修改！",false,true);
			return;
		}

		$("#partyB").val($.trim(siblingsVar.eq(1).html()));
		$("#partyBcityNo").val(siblingsVar.eq(3).html());
		$("#partyBdistrictNo").val(siblingsVar.eq(4).html());
		$("#partyBAddress").val(siblingsVar.eq(6).html());
		$("#partyBCityName").val(siblingsVar.eq(7).html());
		$("#partyBDistrictName").val(siblingsVar.eq(8).html());
		/** 营业执照编号 */
		$("#registerId").val(siblingsVar.eq(14).html());
		/** 法人代表 */
		$("#legalPerson").val(siblingsVar.eq(15).html());

		/** 清空合作门店表格中的tr */
		$("#relateStoreTableId tr:gt(0)").remove();

		/** 合作门店信息 table 生成 */
		var validStoreNum = 0;
		var templateData = {};
		var param = [];
		$.each(data.returnValue, function (i, n) {
            var isGpSign = n.isGpSign;
			/** 不显示已经签约的门店 */
			if (isGpSign==12501) return true;
			/** 不显示已停业或者停业申请中的门店 */
			if (n.businessStatus == '20902' || n.businessStatus == '20903') return true;
            /** 不显示资质等级为乙1的门店*/
            //if(new RegExp('20001').test(n.btypeStore)) return true;
			/** 合作门店数量 */
			param[validStoreNum] = {};
			param[validStoreNum].storeNo = n.storeNo;
			param[validStoreNum].storeId = n.storeId;
			param[validStoreNum].name = n.name;
			param[validStoreNum].addressDetail = n.addressDetail;
			param[validStoreNum].maintainerName = n.maintainerName;
			param[validStoreNum].maintainer = n.maintainer;
			param[validStoreNum].addressDetail = n.addressDetail;
			param[validStoreNum].storeManager = n.storeManager;
			param[validStoreNum].storeManagerPhone = n.storeManagerPhone;

			var abtypeStore = n.abtypeStore;
			var aType = abtypeStore == 19901 ? "selected='selected'" : "";
			var bType = abtypeStore == 19902 ? "selected='selected'" : "";
			var bTypediv = abtypeStore == 19902 ? "display:inline-block" : "display:none";

			var bTypenamelst = n.btypeStoreName;
			if(bTypenamelst == null){
				bTypenamelst = '';
			}else if(abtypeStore == 19902){
				if(bTypenamelst.indexOf("乙3") != -1) bTypenamelst = "乙3";//如果资质等级包含乙3的就显示为乙3，不包含的原来是啥显示啥
				//bTypenamelst = "乙3";
			}
			var bTypelst = n.btypeStore;
			if(bTypelst == null){
				bTypelst = '';
			}else if(abtypeStore == 19902){
				if(bTypelst.indexOf("20003") != -1) bTypelst = "20003";
				//bTypelst = "20003";
			}
			param[validStoreNum].aType = aType;
			param[validStoreNum].bType = bType;
			param[validStoreNum].bTypediv = bTypediv;
			param[validStoreNum].bTypenamelst = bTypenamelst;
			param[validStoreNum].bTypelst = bTypelst;
			validStoreNum++;

		});
		/** 赋值传递到template模板里边的参数 */
		templateData.list = param;
		/** 显示合作门店数量 */
		$("#storeNum").val(validStoreNum);
		console.log(templateData);
		/** 将生成的表格tr放入table中 */
		var html = template('template_relateStoreTable', templateData);
		$("#relateStoreTableId tbody").append(html);
	}
    /**查询翻牌到账保证金*/
    var companyId = selectCompany.val();
    var depositUrl = BASE_PATH+ "/gpContract/queryDeposit";
    var checkParams = {companyId : companyId};
    $.ajax({
        url : depositUrl,
        data : checkParams,
        type : 'GET',
        async:false,
        dataType : 'json',
        success : function(data) {
            if (data && data.returnCode == '200') {
                $("#deposit").val(data.deposit);
            }
        }
    });
	 var fileUrl = BASE_PATH+ "/gpContract/queryFileList";
	 $.ajax({
		 url : fileUrl,
		 data : checkParams,
		 type : 'GET',
		 async:false,
		 dataType : 'json',
		 success : function(data) {
			 if (data && data.returnCode == '200') {
				 var data = data.Files.returnData;
                 if($("#fileBusinessBox div").length>1){
                     var index = $("#fileBusinessBox div").length-1;
                     $("#fileBusinessBox div:lt("+index+")").remove();
                 }

                 if(data!=null){
                     /*$.each(data.fileBusinessList, function(n, value) {
                         $("#fileBusinessBox").prepend(
                             "<div class='thumb-xs-list item-photo-list' >"+
                             " <a href='"+value.fileUrl+"'title='"+value.fileName+"' class='thumbnail swipebox'  target='_blank'>"+
                             " <span class='thumb-img'>"+
                             "<span class='thumb-img-container'>"+
                             "<span class='thumb-img-content'>"+
                             "<img alt='营业执照' data-original='"+value.url50+"' src='"+value.fileAbbrUrl+"' class='empPhoto'/>"+
                             "</span></span></span>"+
                             "<span class='thumb-bottom-roup'>"+
                             "<i class='icon down-icon'></i>"+
                             "<i class='icon remove-icon btn-remove-photo'></i>"+
                             "</span>"+"</a></div>");
                     });*/
                     $.each(data.fileBusinessList, function(n, value) {
                         $("#fileBusinessBox").prepend(
                             "<div class='thumb-xs-list item-photo-list'>"+
                             "<a href='"+value.fileUrl+"' class='thumbnail swipebox' target='_blank' title='"+value.fileName+"'>"+
                             "<span class='thumb-img'>"+
                             "<span class='thumb-img-container'>"+
                             "<span class='thumb-img-content'>"+
                             "<img alt='营业执照' src='"+value.fileAbbrUrl+"' class='empPhoto'/>"+
                             "</span>"+
                             "</span>"+
                             "</span>"+
                             "<span class='thumb-bottom-roup'>"+
                             "<i class='icon down-icon'></i>"+
                             "<i class='icon remove-icon btn-remove-photo'></i>"+
                             "</span>"+
                             "</a>"+
                             "<input type='hidden' name='limitSize' value='10'>"+
                             "<input type='file' id='file${fileSize}' class='btn-flie file-control hide' data-limit='10' multiple='multiple'/>"+
                             "<input type='hidden' name='fileRecordMainIdHidden' value='"+value.fileRecordMainId+"'/>"+
                             "<input type='hidden' name='fileTypeId' value='1'/>"+
                             "<input type='hidden' name='fileSourceId' value='12'/>"+
                             "</div>");
                     });
				 }
                 if($("#fileIdCardBox div").length>1){
                     var index = $("#fileIdCardBox div").length-1;
                     $("#fileIdCardBox div:lt("+index+")").remove();
                 }
                 if(data!=null){
                     /*$.each(data.fileIdCardList, function(n, value) {
                         $("#fileIdCardBox").prepend(
                             "<div class='thumb-xs-list item-photo-list' >"+
                             " <a href='"+value.fileUrl+"'title='"+value.fileName+"' class='thumbnail swipebox'  target='_blank'>"+
                             " <span class='thumb-img'>"+
                             "<span class='thumb-img-container'>"+
                             "<span class='thumb-img-content'>"+
                             "<img alt='法人身份证' data-original='"+value.url50+"' src='"+value.fileAbbrUrl+"' class='empPhoto'/>"+
                             "</span></span></span>"+
                             "<span class='thumb-bottom-roup'>"+
                             "<i class='icon down-icon'></i>"+
                             "<i class='icon remove-icon btn-remove-photo'></i>"+
                             "</span>"+
                             "</a></div>");
                     });*/
                     $.each(data.fileIdCardList, function(n, value) {
                         $("#fileIdCardBox").prepend(
                             "<div class='thumb-xs-list item-photo-list'>"+
                             "<a href='"+value.fileUrl+"' class='thumbnail swipebox' target='_blank' title='"+value.fileName+"'>"+
                             "<span class='thumb-img'>"+
                             "<span class='thumb-img-container'>"+
                             "<span class='thumb-img-content'>"+
                             "<img alt='法人身份证' src='"+value.fileAbbrUrl+"' class='empPhoto'/>"+
                             "</span>"+
                             "</span>"+
                             "</span>"+
                             "<span class='thumb-bottom-roup'>"+
                             "<i class='icon down-icon'></i>"+
                             "<i class='icon remove-icon btn-remove-photo'></i>"+
                             "</span>"+
                             "</a>"+
                             "<input type='hidden' name='limitSize' value='10'>"+
                             "<input type='file' id='file${fileSize}' class='btn-flie file-control hide' data-limit='10' multiple='multiple'/>"+
                             "<input type='hidden' name='fileRecordMainIdHidden' value='"+value.fileRecordMainId+"'/>"+
                             "<input type='hidden' name='fileTypeId' value='2'/>"+
                             "<input type='hidden' name='fileSourceId' value='12'/>"+
                             "</div>");
                     });
                 }
			 }
		 }
	 });
}

LinkAchieveChange2 = function () {
	dialog: null;
};
LinkAchieveChange2.close = function () {
	LinkAchieveChange2.dialog.close();
};
function achieveMentChange(editFlag){
    var url = BASE_PATH + '/gpContract/toLinkUserList';
    var title = '选择业绩归属人';
    if("1" != editFlag) {//1表示编辑
    	if(!$("#partyB").val()){
    		Dialog.alertInfo("请先选择公司");
    		return ;
    	}
    }
    var params = {
    };
    var dialogOptions = {
        width : 600,
        ok : function(data) {
        	var selectRadio = $("input.selectCenterId:checked");
            var selectUser = selectRadio.val();
            if(isBlank(selectUser)){
                $("#errorMsg").text("请先选择一个业绩归属人!");
                $("#errorMsg").css("visibility","initial");
                return false;
            }
            //var userCode = $(':radio[name="selectUser"]:checked').attr("data-userCode");
            var userName = $(':radio[name="selectUser"]:checked').attr("data-userName");
            var userId = $(':radio[name="selectUser"]:checked').attr("data-userId");
            //$('#tmpContactId').val(userId);
            $("#exPerson").val(userName);
            $("#exPersonId").val(userId);
            $('#exPerson').blur();
        },
        okVal : '确定',
        cancel : true,
        cancelVal : '取消'
    };

    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
        dialog.position('50%', '25%');
        LinkAchieveChange2.dialog = dialog;
    }, dialogOptions);

}
function relateCsCompany(type,releaseCityNo,releaseCityNoflag) {
	var url = BASE_PATH + '/relate/company';
	var title = '关联客户';
	
	var orderName = $("#orderName").val();
	var orderType = $("#orderType").val();
	var params = {
			orderName : orderName,
			orderType : orderType,
			type:type,
			releaseCityNo:releaseCityNo,
			releaseCityNoflag:releaseCityNoflag
	};
	var dialogOptions = {
			width : 800,
			ok : function() {
				// 确定
				var reback;
				if(type == "fromGpCsContract"){//公盘初始会员公司列表
					reback = RelateUtil.relateCsMemberCompany();
				}
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
/** 公盘初始会员点击选择公司按钮后 弹出dialog 选择完公司后 点击确认按钮 触发以下事件 */
RelateUtil.relateCsMemberCompany = function() {
	//$('#gpCsMemberContractAddForm')[0].reset();
	$("#csMemberFileBox1").show();
	$("#fileIdCardBox1").show();
	$("#fileContractBox1").show();
	$("#csMemberFileBox2").hide();
	$("#fileIdCardBox2").hide();
	$("#fileContractBox2").hide();
	$("#fileBusiness").empty();
	$("#fileIdCard").empty();
	$("#fileContract").empty();
	var selectCompany = $(':radio[name=selectCompanyId]:checked');
	var companyId = selectCompany.val();
	if(isBlank(companyId)){
        $("#errorMsg").text("请先选择一个公司!");
        $("#errorMsg").css("visibility","initial");
        return false;
    }
	
	var checkUrl = BASE_PATH+ "/gpCsMemberContract/getGpInfoByCompanyId";
	$.ajax({
		url : checkUrl,
		type : 'GET',
		data:$.param({
			companyId : companyId
		}),
		async:false,
		dataType : 'json',
		success : function(data) {
			if (data && data.returnCode == '200') {
                handleGpCsMember(selectCompany,data);
                return ;
            }
			//Dialog.alertInfoToUrl("甲方公司公盘保证金未到账，不能新建公盘初始会员合同！", BASE_PATH + '/gpCsMemberContract',true);
		}
	});
};
function handleGpCsMember(selectCompany, resultData) {
	if (resultData.returnCode != null && resultData.returnCode != undefined && resultData.returnCode != "" && resultData.returnCode != 200) {
		Dialog.alertInfo(resultData.returnMsg,false,true);
		return ;
	}
	var returnValue = resultData.returnValue;//返回的值
	if(returnValue == null || returnValue.length == 0){
		Dialog.alertInfoToUrl2("甲方公司公盘保证金未到账，不能新建公盘初始会员合同！", BASE_PATH + '/gpCsMemberContract',true);
		return ;
	}
	if (returnValue != undefined && returnValue != null) {
		var centerId = $("#centerId").val();
        var centerName = $("#centerName").val();
		var data = returnValue[0];
		/**
		 * 是否已创建
		 */
		var csMemberCtFlag = data.csMemberCtFlag;
		if(csMemberCtFlag >0){
			Dialog.alertInfoToUrl2("甲方公司公盘初始会员合同已创建，请勿重复提交！", BASE_PATH + '/gpCsMemberContract/toAddGpCsMemberContract/'+ centerId + '/' + centerName,true);
			return ;
		}
		
		var companyNo = selectCompany.attr('data-companyNo');
		//公司名称
		var companyName = $.trim(selectCompany.attr('data-companyName'));
		//城市
		var companyCityNm = selectCompany.attr('data-companyCityNm');
		var companyCityNo = selectCompany.attr('data-companyCityNo');
		//区域
		var companyDistrictNo = selectCompany.attr('data-companyDistrictNo');
		var companyDistrictNm = selectCompany.attr('data-companyDistrictNm');
		//详情地址
		var companyAddress = selectCompany.attr('data-companyAddress');
		//统一社会信用代码
		var businessLicenseNo =  selectCompany.attr('data-businessLicenseNo');
		//法人
		var companyLealPerson = selectCompany.attr('data-companyLealPerson');
		//联系电话
		var companyContactTel = selectCompany.attr('data-companyContactTel');
		if(companyName == "" || businessLicenseNo== ""  || companyLealPerson == "" || companyContactTel == "" ||
				companyCityNm == "" || companyDistrictNm == "" || companyAddress == "" || companyNo == ""){
			Dialog.alertInfoToUrl2("选择的经纪公司信息不全，需中心总权限在公司详情中进行补充！", BASE_PATH + '/gpCsMemberContract/toAddGpCsMemberContract/'+ centerId + '/' + centerName,true);
			return ;
		}
		if(businessLicenseNo.length!=15 && businessLicenseNo.length!=18){
			Dialog.alertInfoToUrl2("选择的经纪公司统一社会信用代码不准确，需中心总权限在公司详情中进行修改！", BASE_PATH + '/gpCsMemberContract/toAddGpCsMemberContract/'+ centerId + '/' + centerName,true);
			return ;
		}
		$("#companyId").val(selectCompany.val());
		$("#partyB").val(companyName);
		$("#partyBcityNo").val(companyCityNo);
		$("#partyBCityName").val(companyCityNm);
		$("#partyBdistrictNo").val(companyDistrictNo);
		$("#partyBDistrictName").val(companyDistrictNm);
		$("#partyBAddress").val(companyAddress);
		$("#registerId").val(businessLicenseNo);
		$("#legalPerson").val(companyLealPerson);
		$("#companyName1").val(companyName);
		$("#companyNo1").val(companyNo);
		//开户名
		$("#accountNm").val(data.accountNm);
		//开户省市
		$("#accountProvinceNo").val(data.accountProvinceNo);
		$("#accountProvinceNm").val(data.accountProvinceNm);
		var accountCityNo = data.accountCityNo;
		var url = BASE_PATH +  "/cityCascade/city";
        var params = {provinceNo:data.accountProvinceNo};
        ajaxGet(url, params, function(data1) {
            var result = "<option value=''>请选择城市</option>";
            $.each(data1.returnValue, function(n, value) {
            	if(value.cityNo == accountCityNo) {
            		result += "<option value='" + value.cityNo + "' selected>"
                    + value.cityName + "</option>";
            	}else {
            		result += "<option value='" + value.cityNo + "'>"
                    + value.cityName + "</option>";
            	}
            });
            $("#accountCityNo").html('');
            $("#accountCityNo").append(result);
        }, function() {
        });
		$("#accountCityNm").val(data.accountCityNm);
		//开户行
		$("#bankAccountNm").val(data.bankAccountNm);
		//银行账号
		$("#bankAccount").val(data.bankAccount);
		//甲方授权代表 
		$("#partyBNm").val(data.partyBNm);
		//甲方联系方式 
		$("#partyBTel").val(data.partyBTel);
		$("#gpContractId").val(data.id);
		//我方全称
		var url2 = BASE_PATH +  "/commons/queryFullNameList";
		var params2 = {};
		ajaxGet(url2, params2, function(data2) {
            var result = "";
            $.each(data2.returnValue, function(n, value) {
            	if(value.id == data.ourFullId) {
            		result += "<option value='" + value.id + "' selected>"
                    + value.name + "</option>";
            	}
            });
            $("#ourFullId").html('');
            $("#ourFullId").append(result);
        }, function() {
        });
		$("#csMemberFileBox1").hide();
		$("#fileIdCardBox1").hide();
		$("#fileContractBox1").hide();
		$.each(data.fileBusinessList, function(n, value) {
			$("#fileBusiness").append("<div class='thumb-xs-list item-photo-list' >"+
		               " <a href='"+value.fileUrl+"'title='"+value.fileName+"' class='thumbnail swipebox'  target='_blank'>"+
		                   " <span class='thumb-img'>"+
								"<span class='thumb-img-container'>"+
									"<span class='thumb-img-content'>"+
										"<img alt='营业执照' data-original='"+value.url50+"' src='"+value.fileAbbrUrl+"' class='empPhoto'/>"+
									"</span></span></span></a></div>");
        });
		$.each(data.fileIdCardList, function(n, value) {
			$("#fileIdCard").append("<div class='thumb-xs-list item-photo-list' >"+
		               " <a href='"+value.fileUrl+"'title='"+value.fileName+"' class='thumbnail swipebox'  target='_blank'>"+
		                   " <span class='thumb-img'>"+
								"<span class='thumb-img-container'>"+
									"<span class='thumb-img-content'>"+
										"<img alt='法人身份证' data-original='"+value.url50+"' src='"+value.fileAbbrUrl+"' class='empPhoto'/>"+
									"</span></span></span></a></div>");
		});
		$.each(data.fileContractList, function(n, value) {
			$("#fileContract").append("<div class='thumb-xs-list item-photo-list' >"+
               " <a href='"+value.fileUrl+"'title='"+value.fileName+"' class='thumbnail swipebox'  target='_blank'>"+
                   " <span class='thumb-img'>"+
						"<span class='thumb-img-container'>"+
							"<span class='thumb-img-content'>"+
								"<img alt='公盘系统服务协议' src='"+value.fileAbbrUrl+"' class='empPhoto'/>"+
							"</span></span></span></a></div>");
		});
		$("#fileBusinessBox2").show();
		$("#fileIdCardBox2").show();
		$("#fileContractBox2").show();
	
	}
}
//确定后跳转到url,解决双层弹框选择
Dialog.alertInfoToUrl2 = function(ctt, url,isSecond) {
	if(isSecond){
		$(".lockHandle2").show();
		$(".lockHandle2").css({"position":"fixed","left":"0px","top":"0px","width":"100%",
			"height":"100%","overflow":"hidden","z-index":"1981","background":"rgb(0, 0, 0)","opacity":"0.4"});
	}
	var infoDialogOp = {
		id : 'sysInfo',
		title : false,
		content : '<div class="sysAlert showSweetAlert"><h2>' + ctt
				+ '</h2></div>',
		lock : true,
		okVal : '确定',
		//ok : true
		ok : function() {
			location.href = url;
			return true;
		},
		modal: true
	};

	var dialog = $.dialog(infoDialogOp);

};

