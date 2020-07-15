/** ************************公共部分***************************** */
$(function() {
//	//初始化基础店铺信息	companyBaseInfo
//	var companyId = $("#companyId").val();
//	httpGet('contactsForm', 'LoadBaseInfo', BASE_PATH + '/companys/'+companyId, function() {
//	});
	
//	Contacts.companyId = $("#companyId").val();
	
	// 初始化查询
	initList();
});

/** **************************Contacts js*************************** */
//Contacts = function() {
//	companyId: companyId;
//};

/**
 * 初始化查询
 */
initList = function() {
	
	httpGet('contactsForm', 'LoadCxt', BASE_PATH + '/contacts/q', function() {

		pageInfo("contactsForm", function() {
			initList();
		});

	});
};

/**
 * 查询
 * 
 */
search = function() {
	$('#curPage').val('1');
	initList();
};

