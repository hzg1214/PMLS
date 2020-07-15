/** ************************公共部分***************************** */
$(function() {
	
	// 初始化查询
	ExchangeCenter.initList();
});

/** **************************exchangeCenter js*************************** */
ExchangeCenter = function() {
};

/**
 * 初始化查询
 */
ExchangeCenter.initList = function() {
	httpGet('exchangeCenterForm', 'LoadCxtCenterId', BASE_PATH
			+ '/contract/center/list', function() {
		
		pageInfo("exchangeCenterForm", function() {
			ExchangeCenter.initList();
		});
	});
};



