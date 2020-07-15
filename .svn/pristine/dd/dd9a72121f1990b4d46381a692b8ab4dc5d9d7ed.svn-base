$(function() {
	linkDistricts("storeCityNo","districtNo");
	// 初始化查询
	initList();
});

Store = function() {
};

/**
 * 初始化查询
 */
initList = function() {
	httpGet('storeListForm', 'LoadCxt', BASE_PATH + '/store/q', function() {
		pageInfo("storeListForm", function() {
			initList();
		});

	});
};

Store.setStoreDateType = function(type) {
	$('#storeDateType').val(type);
};

/**
 * 查询
 * 
 */
Store.search = function() {
	$('#curPage').val('1');
	initList();
};


function checkDate(self) {
	if ($("#dateCreateEnd").val() != ''
			&& $("#dateCreateStart").val() > $("#dateCreateEnd").val()) {
		Dialog.alertError("创建时间起始时间不能大于结束时间！");
		$(self).val('');
	}
};

//Add 2017/04/07 lei start -->
/**
 * 无需续签/需续签Flag修正
 * 
 */
Store.updateNeededRenew = function(storeId,neededRenew){
	
	var url = BASE_PATH + "/store/ndr/" + storeId + "/" + neededRenew;
	var params={};
	
	ajaxGet(url, params, function(data) {
		initList();
	});
};
//Add 2017/04/07 lei end -->

Store.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	$("#storeListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#storeListForm").find("select").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	//筛选
	var storeDateType = jsonMap["storeDateType"];
	if (storeDateType != undefined && storeDateType != null && storeDateType != "") {
		$("#divStoreType").find("a").removeClass("active");
		$("#divStoreType").find("a").eq(storeDateType).addClass("active");
		$("#divStoreType").find("a").eq(storeDateType).click();
	}
	var curPage = jsonMap["curPage"];
    if (curPage > 1) {
        $('#curPageTemp').val(curPage);
        $('#sysPageChaneTemp').val(jsonMap["sysPageChane"]);
    }
};

Store.reset=function (pageType) {
	$("#storeListForm :text").val("");
	$("#storeListForm").find("select").val("");
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
	Store.search();
};


function linkDistricts(cityId, districtId)
{
	$("#"+cityId+"").change(function(){	
		$("#"+districtId+" option").remove();
		
	    var cityNo = $("#"+cityId).val();
	    if(cityNo == null || cityNo == "")
    	{
	    	var html = "<option value='' selected>请选择</option>";
	    	$('#'+districtId).append(html);
	    	return false;
    	}
	    
	    var url = BASE_PATH +  "/linkages/city/" + cityNo;
	    var params = {};
	    ajaxGet(url, params, function(data) {
			var result = "<option value=''>请选择</option>";
			$.each(data.returnValue, function(n, value) {
				result += "<option value='" + value.districtNo + "'>"
						+ value.districtName + "</option>";
			});
			$("#"+districtId).html('');
			$("#"+districtId).append(result);
		}, function() {
		});	   
	})
}
