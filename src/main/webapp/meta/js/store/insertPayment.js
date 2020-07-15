/** ************************公共部分***************************** */
$(function() {
});

/** ***************************************************** */
InsertPayment = function() {
};

/**
 * 初始化查询
 */
InsertPayment.initList = function() {

	httpGet('contractInfoForm', 'LoadCxtPopup', BASE_PATH + '/storePayment/getPaymentContractList', function() {

		pageInfo("contractInfoForm", function() {
			InsertPayment.initList();
		});

	});
};

/**
 * 查询
 * 
 */
InsertPayment.search = function() {
	$('#curPage').val('1');
	InsertPayment.initList();
};

//checkbox 全选/取消全选  
var isCheckAll = false;  
function swapCheck() {  
    if (isCheckAll) {  
        $("input[type='checkbox']:visible").each(function() {  
            this.checked = false;  
        });  
        isCheckAll = false;  
    } else {  
        $("input[type='checkbox']:visible").each(function() {  
            this.checked = true;  
        });  
        isCheckAll = true;  
    }  
} 



