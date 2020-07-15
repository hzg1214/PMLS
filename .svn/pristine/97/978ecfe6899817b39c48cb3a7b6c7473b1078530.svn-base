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

	httpGetWithParams('storeAuditForm', 'LoadCxt', params, BASE_PATH + '/storeAudit/getStoreList', function () {

		pageInfo("storeAuditForm", function () {
			initList();
		});

	});

	/*httpGet('storeAuditForm', 'LoadCxt', BASE_PATH + '/storeAudit/getStoreList', function() {
		pageInfo("storeAuditForm", function() {
			initList();
		});

	});*/
};

/**
 * 查询
 * 
 */
search = function() {
	$('#curPage').val('1');
	initList();
};


StoreAudit = function () {
	dialog: null;
};

StoreAudit.showStoreAuditInfo = function (storeId) {
	if(StoreAudit.dialog!=null && !StoreAudit.dialog.closed){
		StoreAudit.dialog.close();
	}
	var url = LOC_RES_BASE_PATH + '/storeAudit/getStoreById/' + storeId;
	var title = '';
	var params = {};
	var dialogOptions = {
		zIndex : 1000
	};

	Dialog.ajaxOpenDialog(url, params, title, function (dialog) {
		StoreAudit.dialog = dialog;
	}, dialogOptions);

};

StoreAudit.close = function () {
	StoreAudit.dialog.close();
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
StoreAudit.reset=function (pageType) {
	$("#storeAuditForm :text").val("");
	$("input[type='checkbox']").prop("checked", false);
	$("#storeAuditForm").find("select").val("");
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
	search();
};