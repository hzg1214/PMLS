/** ************************公共部分***************************** */
var jsonMap = null;
var progress = [];
$(function() {
	// 初始化查询
	initList();
	initReportDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');
    $("input[name='status']:checkbox").click(function(){
    	if ($(this).prop('checked') == true) {
    		if($(this).attr('value').indexOf('220')!=-1){
        		$("#brokerageStatus").val($(this).attr('value'));
        	}else{
        		if(progress.indexOf($(this).attr('value'))==-1){
        			progress.push($(this).attr('value'));
        		}
        	}
    	}else{
    		if($(this).attr('value').indexOf('220')!=-1){
    			if($("#brokerageStatus").val().indexOf('220')!=-1){
    				$("#brokerageStatus").val('');
    			}
    		}else{
    			var index = progress.indexOf($(this).attr('value'));
        		progress.splice(index, 1)
    		}
    		
    	}
    	
    	$('#progress').val(progress.join());
        console.log(progress.join());
        console.log($("#brokerageStatus").val());
    })
});

/** **************************demo js*************************** */
Report = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('qtReportListForm', 'LoadCxt', BASE_PATH + '/qtReport/q', function() {

		pageInfo("qtReportListForm", function() {
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
Report.search = function() {
	$('#curPage').val('1');
	initList();
};

Report.reset = function() {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
    $('form input[type="checkbox"]').attr("checked", false);
    $('#progress').val('');
    progress = [];
    $("#brokerageStatus").val('');
    Report.search();
};


//填充储存的表单值
Report.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	
	if (jsonMap["progress"]) {
        var statusArr = jsonMap["progress"].split(",");
        if (statusArr.length > 0) {
            for (var j = 0; j < statusArr.length; j++) {
                $("input[value='" + statusArr[j] + "']").attr("checked", "checked");
            }
        }
        $("#progress").val(jsonMap["progress"]);
        progress = statusArr;
    }
	if(jsonMap["brokerageStatus"]){
		$("input[value='" + jsonMap["brokerageStatus"] + "']").attr("checked", "checked");
	}
	$("#qtReportListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#qtReportListForm").find("select").each(function () {
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
                    Report.search();
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
