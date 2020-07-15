/** ************************公共部分***************************** */
$(function() {

	// 初始化查询
	initList();
});

/** **************************demo js*************************** */
Student = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('studentForm', 'LoadCxt', BASE_PATH + '/students/q', function() {

		pageInfo("studentForm", function() {
			initList();
		});

	});
};

/**
 * 查询
 * 
 */
Student.search = function() {
	$('#curPage').val('1');
	initList();
};

/**
 * 创建
 */
Student.add = function() {

	var url = BASE_PATH + '/students';

	httpPost('stuAddForm', url, function(data) {

		location.href = BASE_PATH + "/students";

	}, function(data) {
		Dialog.alertError(data.returnMsg);
		systemLoaded();
	});

};

/**
 * 修改
 */
Student.update = function() {

	var id = $("#id").val();

	var url = BASE_PATH + '/students/' + id;

	var params = $("#stuEditForm").serialize();

	httpPut(url, params, function(data) {

		location.href = BASE_PATH + "/students";

	}, function(data) {

	});

};

/**
 * 删除
 */
Student.remove = function(id) {

	var result = "是否确定删除此学生?</h3>";

	var params = {
		'id' : id
	};

	Dialog.confirm(result, function() {

		httpDelete(BASE_PATH + "/students/" + id, params, function(data) {
            
			//刷新页面
			Student.search();

		}, function(data) {
			Dialog.alertError(data.returnMsg);
		});
	});
};