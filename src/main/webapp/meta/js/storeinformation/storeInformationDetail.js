/** ************************公共部分***************************** */
var orgCodeTemp = "1082"; //2017Code
$(function () {
	initCityList(orgCodeTemp);
});

/**
 * 初始化所属城市
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
		var cityNo = $('#city').find('input[type=checkbox]:gt(0):checked').map(function() {
			  return $(this).val();
		}).get().join(',');
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
 * 初始化归属中心
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
/** **************************demo js*************************** */
storeInformationDetail = function () {
};

/**
 * 初始化查询
 */
initList = function () {

    systemLoading("#contentAll",true);

    var params = {
    		"cityNo": cityNoStr.replaceAll(",", "','"),
    		"centerIdStr": centerIdStr
    };

    httpGetWithParams('storeInformationDetailListForm', 'LoadCxt', params, BASE_PATH + '/storeInformationDetail/queryInformationDetailList', function () {

        pageInfo("storeInformationDetailListForm", function () {
            initList();
        });
        systemLoaded("#contentAll");
    },"1");
};
/**
 * 查询
 *
 */
storeInformationDetail.search = function () {

    var flag = validSelect();
    if(!flag){
        return false;
    }

    //归属城市必填
    if(cityNoStr == '' || cityNoStr == null || cityNoStr == undefined){
    	 Dialog.alertError("请选择归属城市");
    	 return false;
    }
    
    $('#curPage').val('1');
    initList();
};

storeInformationDetail.reset=function (pageType) {
	resetCenter();
	$("#storeInformationDetailListForm :text").val("");
	$("input[type='checkbox']").prop("checked", false);
	$("#storeInformationDetailListForm").find("select").val("");
//	$("#storeInformationDetailListForm").find("select").remove();
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
//	storeInformationDetail.search();
};


storeInformationDetail.export = function () {

    var flag = validSelect();
    if(!flag){
        return false;
    }

    var cityNo = $("#cityId").find(".multi-select-value").val();
    var cityNoStr = cityNo.replaceAll(";", "','");
    //归属城市必填
    if(cityNoStr == '' || cityNoStr == null || cityNoStr == undefined){
    	 Dialog.alertError("请选择归属城市");
    	 return false;
    }
    
    var centerId = $("#centerId").find(".multi-select-value").val();
//    var centerIdStr = centerId.replaceAll(";", "','");
    var cookieName = guid();
    $("#btn-reset").attr("disabled",true);
    showExprotLoading(cookieName);

    window.location.href = LOC_RES_BASE_PATH + '/storeInformationDetail/exportStoreInformationDetailList?'
        + "cityNo=" + cityNoStr
        + "&centerIdStr=" + centerIdStr
        + "&storeCityName=" + $("#storeCityName").val()
        + "&storeNo=" + $("#storeNo").val()
        + "&companyNo=" + $("#companyNo").val()
        + "&maintainerId=" + $("#maintainerId").val()
        + "&dealDateStart=" + $("#dealDateStart").val()
        + "&dealDateEnd=" + $("#dealDateEnd").val()
        + "&cookieName=" + cookieName;
    window.setTimeout(function(){
        $("#btn-reset").attr("disabled",false);
    },10000);

}


