var jsonMap = null;
/** ************************公共部分***************************** */
$(function () {
    initList();
    var searchParamMap = CommonUtil.getCookie("CASHBILL_LIST_SEARCH");
	if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
});

/** **************************demo js*************************** */

CashBillList = function () {
    dialog: null;
};

/**
 * 初始化查询
 */
initList = function () {

    var params = {

    };
    httpGet('cashBillListForm', 'LoadCxt', BASE_PATH + '/cashBill/getCashBillList', function () {

        pageInfo("cashBillListForm", function () {
            initList();
        });

    });

};
/**
 * 查询
 *
 */
CashBillList.search = function () {
    $('#curPage').val('1');
    initList();
};

CashBillList.close = function () {

	CashBillList.dialog.close();
};
//按钮重置各种条件
CashBillList.reset=function (pageType) {
	$("#cashBillListForm :text").val("");
	$("#cashBillListForm").find("select").val("");
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
	CashBillList.search();
};
//填充储存的表单值
CashBillList.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	$("#cashBillListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#cashBillListForm").find("select").each(function () {
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
//estateId/userCode/companyId
function submitTr(estateId,companyId,proParentId) {
    systemLoading("", true);
	if(!isBlank(estateId) && !isBlank(companyId)&& !isBlank(proParentId)) {
		$.ajax({
			url:BASE_PATH+"/cashBill/listToSubmitOa",
			data:$.param({
				estateId:estateId,
				companyId:companyId,
				proParentId:proParentId
			}),
			type:"post",
			success:function(data){
                systemLoaded();
				if(data){
					data = JSON.parse(data);
					var returnCode = data.returnCode;
					if("-1"==returnCode){
						var returnMsg = data.returnMsg;
						Dialog.alertInfo(returnMsg);
					}else {
						Dialog.alertInfo("请款单已提交");
						initList();
						return;
					}
				}
			},
			error:function(){
                systemLoaded();
			}
		})
	}
	
};

/**
 * 删除
 * @param estateId
 * @param companyId
 * @param proParentId
 * @returns
 */
function removeTr(estateId,comParentId,proParentId) {
//	systemLoading("", true);
	if(!isBlank(estateId) && !isBlank(comParentId)&& !isBlank(proParentId)) {
	    var result = "是否确定删除此信息?";

	    var params = {
    		estateId : estateId,
    		comParentId : comParentId,
    		proParentId :proParentId
	    };

	    Dialog.confirm(result, function() {

	        restPost(BASE_PATH + "/cashBill/remove", params, function(data) {
	            initList();
	        }, function(data) {
	            Dialog.alertError(data.returnMsg);
	        });
	    });
	}
	
};

