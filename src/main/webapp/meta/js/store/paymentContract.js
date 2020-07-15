/** ************************公共部分***************************** */
$(function() {
});

/** ***************************************************** */
PaymentContract = function() {
};

/**
 * 初始化查询
 */
PaymentContract.initList = function() {

	httpGet('contractInfoForm', 'LoadCxtPopup', BASE_PATH + '/storePayment/getPaymentContractList', function() {

		pageInfo("contractInfoForm", function() {
			PaymentContract.initList();
		});

	});
};

/**
 * 查询
 * 
 */
PaymentContract.search = function() {
	$('#curPage').val('1');
	PaymentContract.initList();
};