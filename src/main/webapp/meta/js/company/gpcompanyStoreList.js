/** ************************公共部分***************************** */
$(function() {
	
	// 初始化查询
	GpCompanyStore.initList();

});

/** **************************CompanyStoreList js*************************** */
GpCompanyStore = function() {
};

/**
 * 初始化查询
 */
GpCompanyStore.initList = function() {
	httpGet('gpcompanyStoreForm', 'LoadCxtgpCompanyStore', BASE_PATH
			+ '/companys/gpcompanyStore/q', function() {
		
		pageInfo("gpcompanyStoreForm", function() {
			GpCompanyStore.initList();
		});
	});
};

/**
 * 查询
 * 
 */
GpCompanyStore.search = function() {
		$('#curPage').val('1');
		GpCompanyStore.initList();
};




