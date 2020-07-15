/** ************************公共部分***************************** */
$(function() {

	// 初始化查询
	RelateCompany.initList();
});

/** ***************************************************** */
RelateCompany = function() {
};

/**
 * 初始化查询
 */
RelateCompany.initList = function() {

	httpGet('relateCompanyForm', 'LoadCxt', BASE_PATH + '/relate/company/q', function() {

		pageInfo("relateCompanyForm", function() {
			RelateCompany.initList();
		});

	});
};

/**
 * 查询
 * 
 */
RelateCompany.search = function() {
	$('#curPage').val('1');
	RelateCompany.initList();
};