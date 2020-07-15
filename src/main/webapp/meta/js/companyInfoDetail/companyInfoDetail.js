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
		cityNoStr = cityNo;
		if (isBlank(cityNo)){
			return;
		}
	}
});


function resetCity(){
	cityNoStr = '';
	$('#cityId').find('.multi-select-item').not(':first').remove();
	$('#cityId').find('.multi-select-checkall').removeAttr("checked");
	$('#cityId').find('.multi-select-value').val('');
	$('#cityId').find('.multi-select-text').val('');
	$('#cityId').find('.multi-select-text').text('');
}

CompanyInfoDetail = function () {
};

/**
 * 初始化查询
 */
initList = function () {
    systemLoading("#contentAll",true);
    var params = {
    		"cityNoStr": cityNoStr.replaceAll(",", ",")
    };
    httpGetWithParams('companyInfoDetailForm', 'LoadCxt', params, BASE_PATH + '/companyInfoDetail/queryCompanyInfoDetailList', function () {
        pageInfo("companyInfoDetailForm", function () {
            initList();
        });
        systemLoaded("#contentAll");
    },"1");
};
/**
 * 查询
 *
 */
CompanyInfoDetail.search = function () {
	if(checkForSearch()){
		$('#curPage').val('1');
	    initList();
	}
};

CompanyInfoDetail.reset=function (pageType) {
	$("#companyInfoDetailForm :text").val("");
	$("input[type='checkbox']").prop("checked", false);
	$("#companyInfoDetailForm").find("select").val("");
	cityNoStr = '';
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
};

CompanyInfoDetail.export = function () {
    var cityNo = $("#cityId").find(".multi-select-value").val();
    var cityNoStr2 = cityNo.replaceAll(";", ",");
    //归属城市必填
    if(checkForSearch()){
    	var cookieName = guid();
    	$("#btn-reset").attr("disabled",true);
    	showExprotLoading(cookieName);
    	window.location.href = LOC_RES_BASE_PATH + '/companyInfoDetail/exportCompanyInfoDetailList?'
    	+ "cityNoStr=" + cityNoStr2
    	+ "&companyNo=" + $("#companyNo").val()
    	+ "&companyName=" + $("#companyName").val()
    	+ "&dateCreateStart=" + $("#dateCreateStart").val()
    	+ "&dateCreateEnd=" + $("#dateCreateEnd").val()
    	+ "&address=" + $("#address").val()
    	+ "&switchType=" + $("#switchType").val()
    	+ "&cookieName=" + cookieName;
    	window.setTimeout(function(){
    		$("#btn-reset").attr("disabled",false);
    	},10000);
    }
}
function checkForSearch(){
	var dateCreateStart = $("#dateCreateStart").val();
	var dateCreateEnd = $("#dateCreateEnd").val();
	if(cityNoStr == '' || cityNoStr == null || cityNoStr == undefined){
   	 	Dialog.alertInfo("请选择归属城市");
   	 	return false;
	}
	if(dateCreateStart == ""){
		Dialog.alertInfo("请选择查询开始日期!");
		return false;
	}
	if(dateCreateEnd == ""){
		Dialog.alertInfo("请选择查询截止日期!");
		return false;
	}
	return true;
};
function checkDate(self) {
    if ($("#dateCreateEnd").val() != ''
        && $("#dateCreateStart").val() > $("#dateCreateEnd").val()) {
        Dialog.alertError("查询日期起始日不能大于结束日！");
        $(self).val('');
    }
};
