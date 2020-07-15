/** ************************公共部分***************************** */
$(function() {
});

/** ***************************************************** */
RelateProjectLeaderUtil = function() {
	dialog: null;
};
// 关闭，取消
RelateProjectLeaderUtil.close = function() {
	RelateProjectLeaderUtil.dialog.close();
};
/**
 * 关联部门负责人
 */
function relateProjectLeader() {

	var url = BASE_PATH + '/estate/projectLeaderChoose';
	var title = '关联项目负责人';

	var orderType = $("#orderType").val();
	var params = {
		/*orderName : orderName,
		orderType : orderType*/
	};
	var dialogOptions = {
		width : 800,
		ok : function() {
			var reback = RelateProjectLeaderUtil.relateLeader();
			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};

	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
		dialog.position('50%', '25%');
		RelateProjectLeaderUtil.dialog = dialog;

	}, dialogOptions);

}

/** 选择项目负责人后 点击确认触发事件 */
RelateProjectLeaderUtil.relateLeader = function() {
	var selectCompany	=	$(':radio[name="selectUserId"]:checked');
	var siblingsVar = selectCompany.parent().siblings();
	/** 赋值 */
	//$("#empId1").val(siblingsVar.eq(0).html());
	$("#empId").val(selectCompany.val());
	//去除空字符
	$("#empId1").val($.trim(siblingsVar.eq(1).html()));
};
// 关闭弹窗
RelateProjectLeaderUtil.close = function() {
	RelateProjectLeaderUtil.dialog.close();
};

