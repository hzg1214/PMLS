/** ************************公共部分***************************** */
$(function() {

	// 初始化查询
	FrmAgreementList.initList();
});

/** ***************************************************** */
FrmAgreementList = function() {
};

/**
 * 初始化查询
 */
FrmAgreementList.initList = function() {

	httpGet('frmAgreementListForm', 'LoadCxt', BASE_PATH + '/cashBill/getOAFrmAgreementList/q', function() {

		pageInfo("frmAgreementListForm", function() {
			FrmAgreementList.initList();
		});

	});
};

/**
 * 查询
 * 
 */
FrmAgreementList.search = function() {
	$('#curPage').val('1');
	FrmAgreementList.initList();
};