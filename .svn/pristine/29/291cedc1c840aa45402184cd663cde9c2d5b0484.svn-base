$(function () {
    // 初始化查询
    initList();
    var searchParamMap = CommonUtil.getCookie("KEFUORDER_LIST_SEARCH");
	if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
	getCityList();
	$("#categoryOne").change(function () {
		var url = BASE_PATH +  "/keFuOrder/getCategoryTwo";
		var categoryOne= $("#categoryOne").val();
		if("" !=categoryOne){
			var params = {categoryOne:categoryOne};
			ajaxGet(url, params, function(data) {
				var result = "<option value=''>请选择</option>";
				$.each(data.returnValue, function(n, value) {
					result += "<option value='" + value.dicCode + "'>"
					+ value.dicValue + "</option>";
				});
				$("#categoryTwo").html('');
				$("#categoryTwo").append(result);
			}, function() {
			});
		}else {
			var result = "<option value=''>请选择</option>";
			$("#categoryTwo").html('');
			$("#categoryTwo").append(result);
		}
	});
});
KeFuOrder = function () {
    dialog: null;
};

/**
 * 初始化查询
 */
initList = function () {
    httpGet('keFuOrderForm', 'LoadCxt', BASE_PATH + '/keFuOrder/getKeFuOrderList', function () {
        pageInfo("keFuOrderForm", function () {
            initList();
        });
    });
};
/**
 * 查询
 *
 */
KeFuOrder.search = function () {
	if(checkForSearch()){
		$('#curPage').val('1');
		initList();
	}
};

KeFuOrder.setSearchParams = function (searchParamMap) {
    var jsonMap = JSON.parse(searchParamMap);
    $("#keFuOrderForm").find("input").each(function () {
        var searchKey = $(this).attr("id");
        var searchValue = jsonMap[searchKey];
        if (searchValue != undefined && searchValue != null && searchValue != '') {
            $("#" + searchKey).val(searchValue);
        }
    });
    $("#keFuOrderForm").find("select").each(function () {
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

KeFuOrder.reset = function (pageType) {
    $("#keFuOrderForm :text").val("");
    $("#keFuOrderForm").find("select").val("");
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
    KeFuOrder.search();
};
function getCityList(){
	var url = BASE_PATH + "/commons/queryCityList";
	var orgCode = "1082";
	var params = {orgCode: orgCode};
	ajaxGet(url, params, function (data) {
		var list = data.returnValue;
		if (list != null && list.length > 0) {
			//var auth ="";
			var result = "<option value=''>请选择</option>";
			$.each(data.returnValue, function(n, value) {
				result += "<option value='" + value.cityNo + "'>"
						+ value.cityName + "</option>";
				// auth += value.cityId + ","
			});
			$("#cityNo").html('');
			$("#cityNo").append(result);
            //$("#authCityIds").val(auth+"0");
		}

	}, function () {
	});
}
KeFuOrder.export = function () {
    //归属城市必填
    if(checkForSearch()){
    	var cookieName = guid();
    	$("#btn-reset").attr("disabled",true);
    	showExprotLoading(cookieName);
    	window.location.href = LOC_RES_BASE_PATH + '/keFuOrder/exportKeFuOrderList?'
    	+ "cityNo=" + $("#cityNo").val()
    	+ "&storeCity=" + $("#storeCity").val()
    	+ "&dateCreate=" + $("#dateCreate").val()
		+ "&dateCreateEnd=" + $("#dateCreateEnd").val()
        + "&companyValue=" + $("#companyValue").val()
    	+ "&categoryOne=" + $("#categoryOne").val()
    	+ "&categoryTwo=" + $("#categoryTwo").val()
        + "&userName=" + $("#userName").val()
		+ "&dealStatus=" + $("#dealStatus").val()
    	+ "&checkStatus=" + $("#checkStatus").val()
    	+ "&cookieName=" + cookieName;
    	window.setTimeout(function(){
    		$("#btn-reset").attr("disabled",false);
    	},10000);
    }
}
function checkForSearch(){
	/*var cityNoStr = $("#cityNo").val();
	if(cityNoStr == '' || cityNoStr == null || cityNoStr == undefined){
   	 	Dialog.alertInfo("请选择归属城市");
   	 	return false;
	}*/
	return true;
};
KeFuOrder.tabClick = function(){
	$("#taskType0,#taskType1,#taskType2,#taskType3").click(function(){
		$('#curPage').val('1');
        var selectId = this.id;
        $("#keFuOrderForm :text").val("");
        $("#keFuOrderForm").find("select").val("");
        if(selectId == "taskType0"){
        	$("#presentation0").removeClass("active");$("#presentation1").removeClass("active");
            $("#presentation2").removeClass("active");$("#presentation3").removeClass("active");
            $("#presentation0").addClass("active");
        }else if(selectId == "taskType1"){
            $("#dealStatus").val("24201");//未处理
            $("#presentation0").removeClass("active");$("#presentation1").removeClass("active");
            $("#presentation2").removeClass("active");$("#presentation3").removeClass("active");
            $("#presentation1").addClass("active");
        }else if(selectId == "taskType2"){
        	 $("#checkStatus").val("24301");//未核验
        	 $("#presentation0").removeClass("active");$("#presentation1").removeClass("active");
             $("#presentation2").removeClass("active");$("#presentation3").removeClass("active");
             $("#presentation2").addClass("active");
        }else if(selectId == "taskType3"){
        	 $("#checkStatus").val("24302");//核验通过
        	 $("#presentation0").removeClass("active");$("#presentation1").removeClass("active");
             $("#presentation2").removeClass("active");$("#presentation3").removeClass("active");
	         $("#presentation3").addClass("active");
        }
        initList();
    });
}
