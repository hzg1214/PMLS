/** ************************公共部分***************************** */

$(function() {
	// 初始化查询
	//initList();
	//setCalendar();
	//选择文件后，进行导入
	$("#historyDataFile").change(function(){
		if($(this).val() == "")
		{
			return;
		}
		dataImport();
	});
});

/** **************************demo js*************************** */
ScenePadCommission = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('ScenePadCommissionListForm', 'LoadCxt', BASE_PATH + '/scenePadCommission/qScenePadCommission', function() {

		pageInfo("ScenePadCommissionListForm", function() {
			initList();
		});

        $('#check-all').change(function() {
            if ($(this).prop('checked')){
                $('.check').each(function () {
                    $(this).prop('checked', true)
                });
            }else {
                $('.check').each(function () {
                    $(this).prop('checked', false)
                });
            }
        });

        //表前几列冻结
        var width = $("#divReport").width;
        var height = $("#divReport").height();
        FixTable("padCommissionTbl", 3, 1150, height);
	});
};
/**
 * 查询
 * 
 */
ScenePadCommission.search = function() {
	if(!check())
	{
		return;
	}
	$('#curPage').val('1');
	initList();
};

function check()
{
	var businessType = $('#businessType').val();
	if(businessType == '')
	{
		Dialog.alertInfo('请选择业务阶段!',true);
		return false;
	}

	var statrDate = $('#countDateStart').val();
	var endStart = $('#countDateEnd').val();
	var start = statrDate.substring(0,4);
	var end   = endStart.substring(0,4);
	if(statrDate == '')
	{
		Dialog.alertInfo('请选择开始日期!',true);
		return false;
	}
	if(endStart == '')
	{
		Dialog.alertInfo('请选择结束日期!',true);
		return false;
	}
	if(statrDate > endStart)
	{
		Dialog.alertInfo('开始日期不能大于结束日期!',true);
		return false;
	}

	var estateType = $('#estateType').val();
	if(estateType == '')
	{
		Dialog.alertInfo('请选择模板类型!',true);
	    return false;
	}



	return true;

}

/**
 * 导入弹出选择文件对话框
 * 
 */
ScenePadCommission.imput = function() {
	var estateType = $('#estateType').val();
	if(estateType == '')
	{
		Dialog.alertError('请选择模板类型!');
	    return false;
	}
	$("#estateTypeImput").val(estateType);
	
	$("#historyDataFile").click();
};

//导入
function dataImport()
{

	var url= BASE_PATH + "/scenePadCommission/imput/";
	systemLoading("", true);
	httpPost("imputForm",url, function(data) {
		$("#historyDataFile").val('');
		systemLoaded();
		Dialog.alertError(data.returnMsg);

	}, function(data) {
		$("#historyDataFile").val('');
		Dialog.alertError(data.returnMsg);
		systemLoaded();
		return false;
	});	
};

ScenePadCommission.output = function(){
	if(!check())
	{
		return;
	}
	var url = BASE_PATH + '/scenePadCommission/exportCheck';
	systemLoading("", true);
	$("#btn-output").attr("disabled",true);
	var param = {
		
	}
	httpPost("ScenePadCommissionListForm", url, function(data) {
		window.location.href = BASE_PATH + '/scenePadCommission/export?projectDepartmentId=' + $("#projectDepartmentId").val()
			+ "&countDateStart=" +$("#countDateStart").val()
			+ "&countDateEnd=" + $("#countDateEnd").val()
			+ "&businessType=" + $("#businessType").val()
			+ "&searchKey=" + $("#searchKey").val() + "&estateType=" + $("#estateType").val()
			+ "&estateNmKey=" + $("#estateNmKey").val() + "&reportId=" + $("#reportId").val();
		systemLoaded();
		$('#btn-output').removeAttr("disabled");
	}, function(data) {
		Dialog.alertError(data.returnMsg);
		systemLoaded();
		$('#btn-output').removeAttr("disabled");
	});	
};

//清空条件
reset = function() {
	$('select').each(function(key, value) {
		$(value).find('option').prop('selected', false);
		$(value).find('option:first').prop('selected', true);
	});
	$('form input[type="text"]').val('');
	$('form input[type="checkbox"]').attr("checked", "checked");
};
ScenePadCommission.reset = function() {
	$('select').each(function(key, value) {
		$(value).find('option').prop('selected', false);
		$(value).find('option:first').prop('selected', true);
	});
	$('form input[type="text"]').val('');
	$('form input[type="checkbox"]').attr("checked", "checked");
	window.location.href = BASE_PATH + '/scenePadCommission/index';
};

ScenePadCommission.del = function() {
    var ids = [];
    $('.check').each(function() {
        if($(this).prop('checked')){
            if (this.dataset.id != 0) {
                ids.push(this.dataset.id);
            }
        }
    });
    if (ids.length == 0) {
        Dialog.alertError('没有需要删除的数据！');
        return;
    }

    var param = {'ids': ids.toString()}

    Dialog.confirm('确定要删除吗？', function() {

        restPost(BASE_PATH + "/scenePadCommission/del", param, function(data) {
            Dialog.alertInfo(data.returnMsg);
            ScenePadCommission.search();
        }, function (data) {
            Dialog.alertInfo(data.returnMsg);
        });
    });
};
