/** ************************公共部分***************************** */
$(function() {
});

/** ***************************************************** */
Maintenance = function() {
};

/**
 * 初始化查询
 */
Maintenance.initList = function() {

	httpGet('achieveMentUserForm', 'LoadCxtPopup', BASE_PATH + '/storeMaintenance/maintenanceUser/getList', function() {

		pageInfo("achieveMentUserForm", function() {
			Maintenance.initList();
		});

	});
};

/**
 * 查询
 * 
 */
Maintenance.search = function() {
	$('#curPage').val('1');
	Maintenance.initList();
};