/** ************************公共部分***************************** */
$(function() {
	
	// 初始化查询
	CompanyStore.initList();

});

/** **************************CompanyStoreList js*************************** */
CompanyStore = function() {
};

/**
 * 初始化查询
 */
CompanyStore.initList = function() {
	httpGet('companyStoreForm', 'LoadCxtCompanyStore', BASE_PATH
			+ '/companys/companyStore/q', function() {
		
		pageInfo("companyStoreForm", function() {
			CompanyStore.initList();
		});
	});
};

/**
 * 查询
 * 
 */
CompanyStore.search = function() {
		$('#curPage').val('1');
		CompanyStore.initList();
};




