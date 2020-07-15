/** ************************公共部分***************************** */
var jsonMap = null;
var progress = [];
$(function() {
	// 初始化查询
	initList();
	initReportDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');
    
    $("input[name='status']:checkbox").each(function () {
        $(this).click(function () {
            if ($(this).prop('checked') == true) {
            	if($(this).attr('value').indexOf('220')!=-1){
            		$("#brokerageStatus").val($(this).attr('value'));
            	}else{
            		progress.push($(this).attr('value'));
            	}
            } else {
            	if($(this).attr('value').indexOf('220')!=-1){
            		$("#brokerageStatus").val('');
            	}else{
            		progress.splice(progress.indexOf($(this).attr('value')), 1);
            	}
            }
            $('#progress').val(progress.join());
            console.log(progress.join());
            console.log($("#brokerageStatus").val());
        });
    });
});

/** **************************demo js*************************** */
qtProjectDetail = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('qtProjectDetailListForm', 'LoadCxt', BASE_PATH + '/qtReport/q', function() {

		pageInfo("qtProjectDetailListForm", function() {
			initList();
		});
        //表前几列冻结
		var width = $("#divReport").width;
		var height = $("#divReport").height();
//		FixTable("tblReport", 4, 1150, height);
	});
};
/**
 * 查询
 * 
 */
qtProjectDetail.search = function() {
	$('#curPage').val('1');
	initList();
};

qtProjectDetail.reset = function() {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
    $('form input[type="checkbox"]').attr("checked", false);
    $('#progress').val('');
    progress = [];
    $("#brokerageStatus").val('');
    qtProjectDetail.search();
};


//填充储存的表单值
qtProjectDetail.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	
	if (jsonMap["progress"]) {
        var statusArr = jsonMap["progress"].split(",");
        if (statusArr.length > 0) {
            var selectedArr = [];
            for (var i = 0; i < statusArr.length / 2; i++) {
                selectedArr[i] = statusArr[i * 2] + "," + statusArr[i * 2 + 1]
            }
            progress = selectedArr;
            for (var j = 0; j < selectedArr.length; j++) {
                $("input[value='" + selectedArr[j] + "']").attr("checked", "checked");
            }
        }
    }
	$("#qtProjectDetailListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#qtProjectDetailListForm").find("select").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
};




function validQtReport(reportId){
	var url = BASE_PATH + '/qtReport/validQtReport/'+reportId;
	Dialog.confirm("确认要无效吗？", function() {
		systemLoading("", true);
		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			success: function (data) {
				systemLoaded();
				if (data.returnCode == 200 || data.returnCode == '200') {
					qtProjectDetail.search();
				} else {

				}
			},
			error: function () {
				//报错提示操作失败
				systemLoaded();
			}
		});
	});
}