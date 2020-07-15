/** ************************公共部分***************************** */
$(function() {
	
//	//初始化基础店铺信息	companyBaseInfo
//	var companyId = $("#companyId").val();
//	httpGet('contactsForm', 'LoadBaseInfo', BASE_PATH + '/companys/'+companyId, function() {
//	});
	
	// 初始化查询
	initList();
});

/** **************************Contacts js*************************** */

/**
 * 初始化查询
 */
initList = function() {
	httpGet('companyContractListForm', 'LoadCxt', BASE_PATH + '/companys/contract/q',
			function() {

				pageInfo("companyContractListForm", function() {
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

