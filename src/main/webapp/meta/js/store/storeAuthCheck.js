/** ************************公共部分***************************** */
var orgCodeTemp = "1082"; //2017Code
$(function() {
	initCityList(orgCodeTemp);
	// 初始化查询
	initList();
	var searchParamMap = CommonUtil.getCookie("STORE_AUTH_CHECK_LIST_SEARCH");
	if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
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

	httpGetWithParams('storeAuthCheckForm', 'LoadCxt', params, BASE_PATH + '/storeAuthCheck/getStoreAuthCheckList', function () {

		pageInfo("storeAuthCheckForm", function () {
			initList();
		});

	});
};
//
StoreAuthCheck = function () {
	dialog: null;
};

StoreAuthCheck.search = function() {
	$('#curPage').val('1');
	initList();
};
StoreAuthCheck.getStoreAuthCheckInfoById = function (id) {
	if(StoreAuthCheck.dialog!=null && !StoreAuthCheck.dialog.closed){
		StoreAuthCheck.dialog.close();
	}
	var url = LOC_RES_BASE_PATH + '/storeAuthCheck/getStoreAuthCheckInfoById/' + id;
	var title = '';
	var params = {};
	var dialogOptions = {
		zIndex : 1000
	};

	Dialog.ajaxOpenDialog(url, params, title, function (dialog) {
		StoreAuthCheck.dialog = dialog;
	}, dialogOptions);

};
StoreAuthCheck.close = function () {
	StoreAuthCheck.dialog.close();
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

StoreAuthCheck.reset=function (pageType) {//storeAuthCheckForm   storeStopCancelForm
	$("#storeAuthCheckForm :text").val("");
	$("input[type='checkbox']").prop("checked", false);
	$("#storeAuthCheckForm").find("select").val("");
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
	StoreAuthCheck.search();
};


StoreAuthCheck.setSearchParams = function (searchParamMap) {
    var jsonMap = JSON.parse(searchParamMap);
    $("#storeAuthCheckForm").find("input").each(function () {
        var searchKey = $(this).attr("id");
        var searchValue = jsonMap[searchKey];
        if (searchValue != undefined && searchValue != null && searchValue != '') {
            $("#" + searchKey).val(searchValue);
        }
    });
    $("#storeAuthCheckForm").find("select").each(function () {
        var searchKey = $(this).attr("id");
        var searchValue = jsonMap[searchKey];
        if (searchValue != undefined && searchValue != null && searchValue != '') {
            $("#" + searchKey).val(searchValue);
        }
    });
    var curPage = jsonMap["curPage"];
    if (curPage > 1) {
        $('#curPageTemp').val(curPage);
        $('#sysPageChaneTemp').val(jsonMap["sysPageChane"]);
    }
};