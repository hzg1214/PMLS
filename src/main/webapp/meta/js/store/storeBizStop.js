/** ************************公共部分***************************** */
var orgCodeTemp = "1082"; //2017Code
$(function() {
	initCityList(orgCodeTemp);
	// 初始化查询
	initList();
});


/**
 * 初始化城市
 * @param orgCode
 */
function initCityList(orgCode) {
	resetCity();
	var url = BASE_PATH + "/commons/queryCityList";
	var params = {orgCode: orgCode};
	ajaxGet(url, params, function (data) {
		var list = data.returnValue;
		if (list != null && list.length > 0) {
			var html = '';
			for (var i = 0; i < list.length; i++) {
				html = html + '<li class="multi-select-item"> <label><input type="checkbox" value="' + list[i].cityNo + '" data-text="' + list[i].cityName + '"><span>' + list[i].cityName + '</span></label> </li>';
			}

			$('#cityId').find('.multi-select-list').append(html);
		}
	}, function () {
	});
}

var cityNoStr = '';

$('#city').find('.multi-select').multiSelect({
	check: function ($instance) {
		var cityNo = $instance.value.toString();
		if (isBlank(cityNo)){
			resetCenter();
			return;
		}
		cityNoStr = cityNo;
		initCenterList(orgCodeTemp);
	}
});

var centerIdStr = '';
$('#center').find('.multi-select').multiSelect({
	check: function ($instance) {
		centerIdStr = $instance.value.toString();
	}
});

/**
 * 初始化中心
 * @param orgCode
 */
function initCenterList(orgCode) {

	resetCenter();
	var url = BASE_PATH + "/commons/queryCenterList";

	ajaxGet(url, {cityNo: cityNoStr.replaceAll(",", "','"),orgCode: orgCode}, function (data) {
		var list = data.returnValue;
		if (list != null && list.length > 0) {
			var html = '';
			for (var i = 0; i < list.length; i++) {
				html = html + '<li class="multi-select-item"> <label><input type="checkbox" value="' + list[i].centerId + '" data-text="' + list[i].centerName + '"><span>' + list[i].centerName + '</span></label> </li>';
			}

			$('#centerId').find('.multi-select-list').append(html);
		}

	}, function () {
	});
}



/**
 * 初始化查询
 */
initList = function() {
	var params = {
		"cityNo": cityNoStr.replaceAll(",", "','"),
		"centerIdStr": centerIdStr
	};

	httpGetWithParams('storeBizStopForm', 'LoadCxt', params, BASE_PATH + '/storeBizStop/list', function () {

		pageInfo("storeBizStopForm", function () {
			initList();
		});

	});
};

BizStop = function () {
	dialog: null;
};

BizStop.search = function() {
	$('#curPage').val('1');
	initList();
};

BizStop.showBizStopInfo = function (id) {
	if(BizStop.dialog!=null && !BizStop.dialog.closed){
		BizStop.dialog.close();
	}
	var url = LOC_RES_BASE_PATH + '/storeBizStop/view/' + id;
	var title = '';
	var params = {};
	var dialogOptions = {
		zIndex : 1000
	};

	Dialog.ajaxOpenDialog(url, params, title, function (dialog) {
		BizStop.dialog = dialog;
	}, dialogOptions);

};

BizStop.close = function () {
	BizStop.dialog.close();
};


function resetCity(){
	cityNoStr = '';
	$('#cityId').find('.multi-select-item').not(':first').remove();
	$('#cityId').find('.multi-select-checkall').removeAttr("checked");
	$('#cityId').find('.multi-select-value').val('');
	$('#cityId').find('.multi-select-text').val('');
	$('#cityId').find('.multi-select-text').text('');
}

function resetCenter(){
	centerIdStr = '';
	$('#centerId').find('.multi-select-item').not(':first').remove();
	$('#centerId').find('.multi-select-checkall').removeAttr("checked");
	$('#centerId').find('.multi-select-value').val('');
	$('#centerId').find('.multi-select-text').val('');
	$('#centerId').find('.multi-select-text').text('');
}

BizStop.reset=function (pageType) {
	$("#storeBizStopForm :text").val("");
	$("input[type='checkbox']").prop("checked", false);
	$("#storeBizStopForm").find("select").val("");
	cityNoStr = '';centerIdStr = '';
	var url = BASE_PATH + "/commons/clearSearchParam?pageType="+ pageType;
	$.ajax({
		type: "GET",
		url: url,
		async : true,
		dataType:"json",
		success: function(data){//成功与否都不处理

		},
		error:function(){

		}
	});
	BizStop.search();
};

BizStopAud = function() {
	dialog: null;
};

/**
 * 审核通过
 */
BizStopAud.auditPass = function(stopId,storeId,storeName,userId,address) {
	Dialog.confirm("确认审核通过吗？", function() {
		var url=BASE_PATH + '/storeBizStop/auditPass';
		var paramsData={"stopId":stopId,"storeId":storeId,"storeName":storeName,"userId":userId,"address":address};
		ajaxGet(url,paramsData,function(data) {
				if(data && data.returnCode == '200'){
					location.href= BASE_PATH +"/storeBizStop";
					Dialog.alertSuccess(data.returnMsg);
				}else{
					Dialog.alertError(data.returnMsg);
				}
			},
			function(data) {
				Dialog.alertError(data.returnMsg);
			}
		);
	});

}
/**
 * 审核退回
 */
BizStopAud.auditReturn=function(stopId,storeId,storeName,userId,address) {
	var url=BASE_PATH + '/storeBizStop/toRejectStop';
	var params={"stopId":stopId,"storeId":storeId,"storeName":storeName,"userId":userId,"address":address};
	var dialogOptions = {
		width : 300,
		height : 100,
		ok : function() {
			// 确定
			var reback = BizStopAud.saveAudit(stopId,storeId);
			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};
	Dialog.ajaxOpenDialog(url, params, "驳回原因", function(dialog, resData) {
		RelateUtil.dialog = dialog;
	}, dialogOptions);

}
//提交驳回原因并更改审核状态
BizStopAud.saveAudit = function(stopId,storeId){
	if (Validator.validForm("rejectStopForm")) {
		var param = $("#rejectStopForm").serialize();
		var url=BASE_PATH + '/storeBizStop/rejectStop';
		ajaxGet(url,param,function(data) {
				if(data && data.returnCode == '200'){
					location.href= BASE_PATH +"/storeBizStop";
					Dialog.alertSuccess(data.returnMsg);
				}else{
					Dialog.alertError(data.returnMsg);
				}
			},
			function(data) {
				Dialog.alertError(data.returnMsg);
			}
		);
	} else {
		return false;
	}
};