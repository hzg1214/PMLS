var jsonMap = null;
/** ************************公共部分***************************** */
$(function () {
    initList();
    var searchParamMap = CommonUtil.getCookie("STOREPAYMENT_LIST_SEARCH");
	if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
});

/** **************************demo js*************************** */

StorePayment = function () {
    dialog: null;
};

/**
 * 初始化查询
 */
initList = function () {

    var params = {

    };
    httpGet('storePaymentListForm', 'LoadCxt', BASE_PATH + '/storePayment/getList', function () {

        pageInfo("storePaymentListForm", function () {
            initList();
        });

    });

};
/**
 * 查询
 *
 */
StorePayment.search = function () {
    $('#curPage').val('1');
    initList();
};

StorePayment.close = function () {

	StorePayment.dialog.close();
};
 
StorePayment.reset=function (pageType) {
	$("#storePaymentListForm :text").val("");
	$("#storePaymentListForm").find("select").val("");
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
	StorePayment.search();
};
function removeTr(id) {
	$.ajax({
		url:BASE_PATH+"/storePayment/update",
		data:$.param({
			id:id
		}),
		type:"post",
		success:function(data){
			if(data){
				Dialog.alertInfo("删除成功");
				initList();
				return;
			}
		},
		error:function(){
			Dialog.alertError("删除失败");
		}
	})
	
};
function submitTr(id,submitOaStatus) {
	$.ajax({
		url:BASE_PATH+"/storePayment/update",
		data:$.param({
			id:id,
			submitOaStatus:submitOaStatus
		}),
		type:"post",
		success:function(data){
			if(data){
				Dialog.alertInfo("提交成功");
				initList();
				return;
			}
		},
		error:function(){
			Dialog.alertError("提交失败");
		}
	})
	
};
function addPayment() {
	    var url = BASE_PATH + '/storePayment/toPaymentContractList';
	    var title = '请选择退款合同';
	    var params = {
	    };
	    var dialogOptions = {

	        width : 800,
	        ok : function() {
	            var reback = StorePayment.toInsertPayment();
	            return reback;
	        },
	        okVal : '确定',
	        cancel : true,
	        cancelVal : '取消'
	    };

	    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
	        dialog.position('50%', '25%');
	        StorePayment.dialog = dialog;

	    }, dialogOptions);
	
};

StorePayment.toInsertPayment = function(){

    var selectRadio = $("input.selectContractId:checked");
    var selectContractId = selectRadio.val();
    if(isBlank(selectContractId)){
        $("#errorMsg").text("请先选择一个退款合同!");
        $("#errorMsg").css("visibility","initial");
        return false;
    }
    location.href = BASE_PATH + '/storePayment/toInsertPayment/'+ selectContractId;
    
}

//填充储存的表单值
StorePayment.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	$("#storePaymentListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#storePaymentListForm").find("select").each(function () {
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


