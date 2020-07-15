/** ************************公共部分***************************** */

$(function() {
	// 初始化查询
	initList();
	initDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');
	$("input[name='status']:checkbox").each(
			function() 
			{
				$(this).click(function()
				{
					if ($(this).prop('checked') == true)
					{
						progress.push($(this).attr('value'));
					}
					else
					{
						progress.splice(progress.indexOf($(this).attr('value')), 1);
					}
					$('#progress').val(progress.join());
					console.log(progress.join());
				});
			});
	var divHeight=$("#detailList").height();
    if(divHeight>450){
        $("#detailList").addClass("s-scoll-y");
    }
});

/** **************************demo js*************************** */
SceneCommissionDetail = function() {
};

var progress = [];

/**
 * 初始化查询
 */
initList = function() {

	httpGet('SceneCommissionDetailListForm', 'LoadCxt', BASE_PATH + '/sceneCommission/qSceneCommissionDetail/q', function() {
		
		// 获得列表数据后 初始化CheckBox
		 $('#J_checkBoxAll').click(function () {
			 var currentStatus = $('#J_checkBoxAll').prop('checked');
	         $('.J_estateItem input[type="checkbox"]').prop('checked', currentStatus);
	     });
		 
		pageInfo("SceneCommissionDetailListForm", function() {
			initList();
		});

	});
};
/**
 * 查询
 * 
 */
SceneCommissionDetail.search = function() {
	$('#curPage').val('1');
	initList();
};

/**
 * 修改佣金
 * 
 */
SceneCommissionDetail.modify = function() {
	var type = 0;
	if (isItemSelected(type))
	{
		Dialog.confirm("确认要修改佣金吗？",
				function() {
					var url = BASE_PATH + "/sceneCommission/qSceneCommissionDetail/modify";
					var params = {reports: getReports(type)};
					submit(url, params);
				});
	}
};

/**
 * 确认结算
 * 
 */
SceneCommissionDetail.confirm = function() {
	var type = 1;
	if (isItemSelected(type))
	{
		Dialog.confirm("确认要结算吗？",
				function() {
					var url = BASE_PATH + "/sceneCommission/qSceneCommissionDetail/confirm";
					var params = {reports: getReports(type)};
					submit(url, params);
				});
		
	}
};

var operations = ['修改佣金', '确认结算'];
var itemList;
isItemSelected = function(type) {
	itemList = $('.J_estateItem input[type="checkbox"]:checked');
	if (itemList.length <= 0) {
    	Dialog.alertError('请选择需要' + operations[type] + '的项目');
    	return false;
    }
	return true;
};

//type: 0, modify; 1, confirm
var item;
getReports = function(type) {
	var reports = [];//reportid, progress, reward, accountReward
	var report = "";    	
	$(item).children().each(function(i) {
		if (i == 0 ) {		
			report += $(this).attr('value');				
		}
		else if (i == 6) {
			report += "," + $(this).attr('value');
		}
		else if (type == 0 && i == 7) {
			if ($(this).children().first().val() == '') {
				report += ",0";
			} else {
				report += "," + $(this).children().first().val();	
			}
		}
		else if (type == 1 && i == 8) {
			if ($(this).children().first().val() == '') {
				report += ",0";
			} else {
				report += "," + $(this).children().first().val();	
			}			
		}
	});
		reports.push(report);
	return reports.join('|');
};

checkAccount = function(self) {
	$(self).parent().siblings().each(function(i) {
		if (i == 7) {
			if (Number($(self).val()) > Number($(this).children().first().attr("oldvalue"))) {
				Dialog.alertError("结算金额不能超过奖励金额！");
				$(self).val($(self).attr("oldvalue"));
			}
			if (Number($(self).val()) <= Number($(self).attr("oldvalue"))) {
				Dialog.alertError("结算金额不能小于原结算金额！");
				$(self).val($(self).attr("oldvalue"));
			}
		}
	});
	
};

submit = function(url, params) {
	ajaxGet(url, params, function(data) {
		initList();
	}, function() {});
};

//重置输入内容
reset = function() {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
    $('form input[type="checkbox"]').attr("checked", "checked");
};

//返回佣金
back = function() {
	location.href =  BASE_PATH + '/sceneCommission';
};



/**
 * 修改佣金
 * 
 */
SceneCommissionDetail.modify2 = function(idx) {
	var type = 0;
	item = $('.J_estateItem')[idx];
		Dialog.confirm("确认要修改佣金吗？",
				function() {
					var url = BASE_PATH + "/sceneCommission/qSceneCommissionDetail/modify";
					var params = {reports: getReports(type)};
					submit(url, params);
				});
};

/**
 * 确认结算
 * 
 */
SceneCommissionDetail.confirm2 = function(idx) {
	var type = 1;
	item = $('.J_estateItem')[idx];
		Dialog.confirm("确认要结算吗？",
				function() {
					var url = BASE_PATH + "/sceneCommission/qSceneCommissionDetail/confirm";
					var params = {reports: getReports(type)};
					submit(url, params);
				});
};
