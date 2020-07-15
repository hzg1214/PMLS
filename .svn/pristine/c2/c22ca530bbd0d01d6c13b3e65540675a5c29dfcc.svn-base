/** ************************公共部分***************************** */
$(function() {

	// 初始化查询
	initList();

});

/** **************************Company js*************************** */
Company = function() {
};

/**
 * 初始化查询
 */
initList = function() {
	httpGet('companyForm', 'LoadCxt', BASE_PATH + '/companys/q', function() {

		pageInfo("companyForm", function() {
			initList();
		});
	});
};

/**
 * 查询
 * 
 */
Company.search = function() {
//	if(checkBusinessLicenseNo()){	
		$('#curPage').val('1');
		initList();
//	}else
//	{
//		Dialog.alertInfo("请输入正确的营业执照号");
//	}
	
};

/**
 * 筛选
 * 
 */
Company.filter = function(type, dicCode) {

	if ('contractType' == type) {
		$("#contractType").val(dicCode);
	} else if ('source' == type) {
		$("#sourceId").val(dicCode);
	} else if ('companyStatus' == type) {
		$("#companyStatus").val(dicCode);
	}

	Company.search();
};

/**
 * 导出客户
 */
Company.exportCompany = function() {
    httpGet4Export('companyForm', 'LoadCxt', LOC_RES_BASE_PATH + '/companys/exportCompany', function(data) {
        if (data) {
            $("#exportExcelDiv").append("<iframe id='exportExcelFrame' src='#'></iframe>");
            $("#exportExcelFrame").attr("src", LOC_RES_BASE_PATH + data);
            if (/msie/.test(navigator.userAgent.toLowerCase())) {
                //IE上使用
                $("#exportExcelFrame")[0].onreadystatechange = function () {
                    setTimeout(function () {
                        $("#exportExcelFrame").remove();
                    }, 1000);
                };
            } else {
                //非ie使用
                $("#exportExcelFrame").load(function () {
                    setTimeout(function () {
                        $("#exportExcelFrame").remove();
                    }, 1000);
                });
            }
        }
    });

};

/**
 * 营业执照的输入check
 */
//function checkBusinessLicenseNo()
//{
//	var falg = false;
//	var bno = $("#businessLicenseNo").val();
//    var checkbno = /^\s*[A-Z0-9]*\s*$/; 
//    if(bno != null && bno != '')
//    {
//		if(checkbno.test(bno))
//	    {
//		    falg = true;
//		    return falg;
//		}
//    }else
//    {
//    	falg = true;
//    	return falg;
//    }
//}

Company.setSearchParams = function (searchParamMap) {
    var jsonMap = JSON.parse(searchParamMap);
    $("#companyForm").find("input").each(function () {
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

Company.reset=function (pageType) {
    $("#companyForm :text").val("");
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
    Company.search();
};
Company.tabClick = function(){
	$("#taskType0,#taskType1,#taskType2").click(function(){
		$('#curPage').val('1');
        var selectId = this.id;
        $("#businessTypeId").show();
        $("#companyForm :text").val("");
        $("#bizType").val("");
        if(selectId == "taskType1"){
            $("#switchType").val("1");//翻盘公司
            $("#presentation1").removeClass("active");
            $("#presentation2").removeClass("active");
            $("#presentation3").removeClass("active");
            $("#presentation2").addClass("active");
            $("#businessTypeId").hide();
        }else if(selectId == "taskType2"){
            $("#switchType").val("2");//渠道公司
            $("#presentation1").removeClass("active");
            $("#presentation2").removeClass("active");
            $("#presentation3").removeClass("active");
            $("#presentation3").addClass("active");
            $("#businessTypeId").hide();
        }else if(selectId == "taskType0"){
        	 $("#switchType").val("0");//所有
        	 $("#presentation1").removeClass("active");
             $("#presentation2").removeClass("active");
             $("#presentation3").removeClass("active");
             $("#presentation1").addClass("active");
             $("#businessTypeId").show();
        }
        initList();
    });
}


