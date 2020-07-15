/** ************************公共部分***************************** */
$(function() {

	// 初始化查询
	ReceiveBank.initList();
});

/** ***************************************************** */
ReceiveBank = function() {
};

/**
 * 初始化查询
 */
ReceiveBank.initList = function() {

	httpGet('receiveBankForm', 'LoadCxt', BASE_PATH + '/cashBill/getOAReceiveBankList/q', function() {

		pageInfo("receiveBankForm", function() {
			ReceiveBank.initList();
		});

	});
};

/**
 * 查询
 * 
 */
ReceiveBank.search = function() {
	$('#curPage').val('1');
	ReceiveBank.initList();
};