/** ************************公共部分***************************** */
var jsonMap = null;
var initCityDist = false;
$(function() {
	// 初始化查询
	initCityDist = true;
	getEmpList();
	initCooperationStartDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');
	getDateKbnList();
	getcommissionStatus();
	var searchParamMap = CommonUtil.getCookie("ESTATE_LIST_SEARCH");
	if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
	if (!jsonMap || !jsonMap["realityCityNo"]) initList();
	cityLinkageIsService(setCity, setDist);
});

/** **************************demo js*************************** */
Estate = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('EstateListForm', 'LoadCxt', BASE_PATH + '/estate/q', function() {

		pageInfo("EstateListForm", function() {
			initList();
		});

	});
	initCityDist = false;
};
/**
 * 查询
 * 
 */
Estate.search = function() {
	$('#curPage').val('1');
	initList();
};

Estate.setContractType = function(type) {
	$('#contractType').val(type);
};

/**
 * 获取人员列表
 */
function getEmpList() {
	$("#deptId").change(
	function() {
		if ($("#deptId").val() != '') {
			var url = BASE_PATH + "/estate/empList/" + $("#deptId").val();
			var params = {};

			ajaxGet(url, params, function(data) {
				var result = "<option value=''>请选择录入人</option>";
				$.each(data.returnValue, function(n, value) {
					result += "<option value='" + value.userId + "'>"
							+ value.userName + "</option>";
				});
				$("#empId").html('');
				$("#empId").append(result);
			}, function() {
			});
		} else {
			var result = "<option value=''>请选择录入人</option>";
			$("#empId").html('');
			$("#empId").append(result);
		}
	});
}

/**
 * 获取日期类型列表
 */
function getDateKbnList() {
	$("#dateTypeKbn").change(
	function() {
		$("#dateKbn").val("");
		$("#dateStart").val("");
		$("#dateEnd").val("");
		var kbn = 132;
		if ($("#dateTypeKbn").val() == 13101) {
			kbn = 132;
			initCooperationStartDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');
		} else if ($("#dateTypeKbn").val() == 13102) {
			kbn = 133;
			initCooperationEndDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');
		} else if ($("#dateTypeKbn").val() == 13103) {
			kbn = 132;
			initCooperationStartDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');
		}
		var url = BASE_PATH + "/estate/dateKbnList/" + kbn;
		var params = {};

		ajaxGet(url, params, function(data) {
			var result = "<option value=''>请选择日期类型</option>";
			$.each(data.returnValue, function(n, value) {
				result += "<option value='" + value.dicCode + "'>"
						+ value.dicValue + "</option>";
			});
			$("#dateKbn").html('');
			$("#dateKbn").append(result);
		}, function() {
		});
	});
}

/*reset = function() {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
};*/

EstateRelease = function() {
	dialog: null;
};
/**
 * 发布
 */
EstateRelease.release=function(id){
	var url = BASE_PATH + "/estate/torelease";
	var params = {
			id:id
	};
	var dialogOptions = {
			width : 700,
			ok : function() {
				// 确定
				var releaseDt = $("#releaseDt").val();
				var releaseFlg = $("input[name='releaseFlg']:checked").val();  
				if(releaseFlg == 0 && (releaseDt == '' || releaseDt == null))
				{
					$(".fc-warning").html('<font color="red" font-size="15px">请选择时间</font>');
					return false;
				}
				var aurl = BASE_PATH + '/estate/release/'+id;
				var aparams = $("#estateReleaseForm").serialize();
				systemLoading("", true);
				httpPut(aurl, aparams, function(data) {
					location.href = BASE_PATH + "/estate?searchParam=1";
				}, function(data) {
					Dialog.alertError(data.returnMsg);
					systemLoaded();
					return false;
				});
				return true;
			},
			okVal : '发布',
			cancel : true,
			cancelVal : '返回'
	};
	Dialog.ajaxOpenDialog(url, params, "发布设置", function(dialog, resData) {
		dialog.position('50%', '25%');
		EstateRelease.dialog = dialog;
	}, dialogOptions);
};

EstateDown = function() {
	dialog: null;
};

/**
 * 下架
 */
EstateDown.down=function(id){
	var url = BASE_PATH + "/estate/todown";
	var params = {
			id:id
	};
	var dialogOptions = {
			width : 700,
			ok : function() {
				// 确定
				var aurl = BASE_PATH + '/estate/down/'+id;
				var aparams = $("#estateDownForm").serialize();
				systemLoading("", true);
				httpPut(aurl, aparams, function(data) {
					location.href = BASE_PATH + "/estate?searchParam=1";
				}, function(data) {
					Dialog.alertError(data.returnMsg);
					systemLoaded();
					return false;
				});
				return true;
			},
			okVal : '确定下架',
			cancel : true,
			cancelVal : '返回'
	};
	Dialog.ajaxOpenDialog(url, params, "下架说明", function(dialog, resData) {
		dialog.position('50%', '25%');
		EstateDown.dialog = dialog;
	}, dialogOptions);
};

/**
 * 撤回
 */
Estate.backOff=function(id){
	Dialog.confirm("确认撤回？",function(){
		var url = BASE_PATH + '/estate/backoff/'+id;
		var params = $("#EstateListForm").serialize();
		systemLoading("", true);
		httpPut(url, params, function(data) {
			location.href = BASE_PATH + "/estate?searchParam=1";
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});
	},function(){
		
	});
};

/**
 * 跟单
 */
Estate.startProject=function(id){
	Dialog.confirm("确认跟单？",function(){
		var url = BASE_PATH + '/estate/startProject/'+id;
		var params = $("#EstateListForm").serialize();
		systemLoading("", true);
		httpPut(url, params, function(data) {
			location.href = BASE_PATH + "/estate?searchParam=1";
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		})
	},function(){
	});
};

/**
 * 取消跟单
 */
Estate.startCancel=function(id){
	Dialog.confirm("确认取消跟单？",function(){
		var url = BASE_PATH + '/estate/startCancel/'+id;
		var params = $("#EstateListForm").serialize();
		systemLoading("", true);
		httpPut(url, params, function(data) {
			location.href = BASE_PATH + "/estate?searchParam=1";
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});
	});
};

/**
 * 结案
 */
Estate.endProject=function(id){
	Dialog.confirm("确认结案？",function(){
		var url = BASE_PATH + '/estate/endProject/'+id;
		var params = $("#EstateListForm").serialize();
		systemLoading("", true);
		httpPut(url, params, function(data) {
			location.href = BASE_PATH + "/estate?searchParam=1";
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});
	});
};

/**
 * 取消结案
 */
Estate.endCancel=function(id){
	Dialog.confirm("确认取消结案？",function(){
		var url = BASE_PATH + '/estate/endCancel/'+id;
		var params = $("#EstateListForm").serialize();
		systemLoading("", true);
		httpPut(url, params, function(data) {
			location.href = BASE_PATH + "/estate?searchParam=1";
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});
	});
};

/**
 * 重设筛选框
 */
Estate.reset = function() {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
        $('#districtNo').find('option').remove();
    });
    $('form input[type="text"]').val('');
    Estate.search();
};

//填充储存的表单值
Estate.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	
	$("#EstateListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#EstateListForm").find("select").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	//筛选
	var contractType = jsonMap["contractType"];
	if (contractType != undefined && contractType != null && contractType != "") {
		$("#divContractType").find("a").removeClass("active");
		$("#divContractType").find("a").eq(contractType).addClass("active");
		$("#divContractType").find("a").eq(contractType).click();
	}
	var curPage = jsonMap["curPage"];
    if (curPage > 1) {
        $('#curPageTemp').val(curPage);
        $('#sysPageChaneTemp').val(jsonMap["sysPageChane"]);
    }
};

setCity = function(){
	if (jsonMap && jsonMap.hasOwnProperty("realityCityNo")) $("#realityCityNo").val(jsonMap["realityCityNo"]).change();
};

setDist = function(){
	if (jsonMap && jsonMap.hasOwnProperty("districtId")) $("#districtNo").val(jsonMap["districtId"]);
	if (initCityDist) initList();
};

EstateRelease.alertCantRelease = function(){
	Dialog.alertInfo("整合项目需要测算完成后，才可上架！");
}

//发布窗口关闭
EstateRelease.closePopup = function(){
	EstateRelease.dialog.close();
}

//下架窗口关闭
EstateDown.closePopup = function(){
	EstateDown.dialog.close();
}
