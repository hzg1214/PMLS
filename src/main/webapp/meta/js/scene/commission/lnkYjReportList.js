$(function () {
	initList();
});

LnkYjReport = function () {
};

/**
 * 初始化查询
 */
initList = function () {

    httpGet('LnkYjReportListForm', 'LoadCxt', BASE_PATH + '/lnkYjReport/getLnkYjReprotList', function () {
        pageInfo("LnkYjReportListForm", function () {
            initList();
        });
    });
};
/**
 * 查询
 */
LnkYjReport.search = function () {
    if (!check()) {
        return;
    }
    $('#curPage').val('1');
    initList();
};

function check() {
    var businessType = $('#businessType').val();
    var statrDate = $('#countDateStart').val();
    var endStart = $('#countDateEnd').val();
    if (statrDate != '' && endStart == '') {
        Dialog.alertInfo('请选择结束日期!', true);
        return false;
    }
    if (statrDate == '' && endStart != '') {
        Dialog.alertInfo('请选择开始日期!', true);
        return false;
    }

    return true;

}

//checkbox 全选/取消全选  
var isCheckAll = false;  
function swapCheck() {  
    if (isCheckAll) {  
        $("input[type='checkbox']:visible").each(function() {  
            this.checked = false;  
        });  
        isCheckAll = false;  
    } else {  
        $("input[type='checkbox']:visible").each(function() {  
            this.checked = true;  
        });  
        isCheckAll = true;  
    }  
}

//清空条件
reset = function () {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
    $('form input[type="checkbox"]').attr("checked", false);
};
LnkYjReport.reset = function () {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
    $('form input[type="checkbox"]').attr("checked", false);
    window.location.href = BASE_PATH + '/lnkYjReport';
};


/**
 * 点击返佣维护对象
 */
LnkYjReport.achieveMentChange = function(){

    var checkFlag = $(".selectReport").is(':checked');
    if(!checkFlag){
        Dialog.alertError("请至少选择一个需要返佣维护的记录！")
        return;
    }

    var ids = "";
    var reprotinfoStr = "";
    var projectNo = "";
    $(".selectReport").each(function(){
        if($(this).is(':checked')){   	
            ids += $(this).val()+",";
            var reportId = "#reportId"+$(this).val();
//            alert($(reportId).val());
            
            
            id = $(this).val();
            var reportIdStr = "#reportId"+$(this).val();
            var reportId = $(reportIdStr).val();
            projectNo = $("#projectNo"+$(this).val()).val();
            reprotinfoStr += id+"#"+reportId+",";
            
        }
    });
//    alert(reprotinfoStr);
    if(reprotinfoStr != ""){
    	reprotinfoStr = reprotinfoStr.substr(0,reprotinfoStr.length-1);
    }
	var flag;
	$('input.selectReport:checked').each(function(i,j){
		var	firstShopCenter = $.trim($('input.selectReport:checked:first').closest('tr').children('td:eq(2)').text()),
		shopCenter = $.trim($(this).closest('tr').children('td:eq(2)').text());
		if(firstShopCenter !== shopCenter){
			Dialog.alertError("选择的房源数据不为同一项目，请重新选择！");
			flag = true;
			return false;
		}
		return true;
	});
	if(flag){
		return;
	}
//	var storeCenterId = $('input:checked:first').siblings('input#storeCenterId').val();
    var url = BASE_PATH + '/lnkYjReport/maintenance';
    var title = '返佣对象维护';
    var params = {
    	reprotinfoStr : reprotinfoStr, 
    	projectNo : projectNo 
    };
    var dialogOptions = {

        width : 800,

        ok : function() {
            var reback = LnkYjReport.saveMaintenance();
            return reback;
        },
        okVal : '保存',
        cancel : true,
        cancelVal : '取消'
    };

    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

        dialog.position('50%', '25%');
        LnkYjReport.dialog = dialog;

    }, dialogOptions);
}

/**
 * 保存业绩
 */
LnkYjReport.saveMaintenance = function(){
    var ids = $("#ids").val();
    var reportIds = $("#reportIds").val();
    var estateId = $("#estateId").val();
    var companyNo = $("#companyCode").val();
    var companyNoTwo = $("#companyCodeTwo").val();
//    var selectRadio = $("input.selectCenterId:checked");
//    var selectCenterId = selectRadio.val();
    var inputCompanyName = $("#inputCompanyName").val().trim();
    var inputCompanyNameTwo = $("#inputCompanyNameTwo").val().trim();
    if(isBlank(inputCompanyName)){
    	$("#errorMsg").text("返佣对象二必填!");
        $("#errorMsg").css("visibility","initial");
        return false;
    }
    
//    if(inputCompanyName == inputCompanyNameTwo){
//    	$("#inputCompanyNameTwo").val("");
//    	$("#inputCompanyName").val("");
//    	$("#errorMsg").text("返佣对象一不能与返佣对象二一样");
//        $("#errorMsg").css("visibility","initial");
//    	return false;
//    }
	if((companyNoTwo == null || companyNoTwo == '' || companyNoTwo == undefined) 
			&& (inputCompanyNameTwo != null && inputCompanyNameTwo != '' && inputCompanyNameTwo != undefined)){
		$("#inputCompanyNameTwo").val("");
		$("#errorMsg").text("经纪公司录入有误，请输入关键字搜索后选择对应的经纪公司！");
		 $("#errorMsg").css("visibility","initial");
		return false;
	}
	if((companyNo == null || companyNo == '' || companyNo == undefined) 
			&& (inputCompanyName != null && inputCompanyName != '' && inputCompanyName != undefined)){
		$("#inputCompanyName").val("");
		$("#errorMsg").text("经纪公司录入有误，请输入关键字搜索后选择对应的经纪公司！");
		 $("#errorMsg").css("visibility","initial");
		return false;
	}
	
//	if(company == inputCompanyName){
//		Dialog.alertError("返佣对象二与返佣对象一不能一样");
//		return false;
//	}
	if(inputCompanyNameTwo != null && inputCompanyNameTwo != ''){
		if(inputCompanyName == inputCompanyNameTwo){
			$("#inputCompanyNameTwo").val("");
			$("#errorMsg").text("返佣对象二与返佣对象三不能一样");
			 $("#errorMsg").css("visibility","initial");
			return false;
		}
	}
//		if(company == inputCompanyNameTwo){
//			Dialog.alertError("返佣对象三与返佣对象一不能一样");
//			return false;
//		}
    var url = BASE_PATH + '/lnkYjReport/saveMaintenance';
    var params = {
        ids : ids,
        reportIds : reportIds,
        yjReportFirst : inputCompanyName,
        yjReportSecond : inputCompanyNameTwo,
        companyNo : companyNo,
        estateId : estateId,
        companyNoTwo : companyNoTwo
    }
//    restPost(url, params, function(data){
////        console.log(data);
//        Dialog.alertInfo(data.returnMsg);
//        initList();
//    }, function(data){
//        if(data.returnCode != "200"){
//            Dialog.alertError(data.returnMsg);
//        }
//    })
    $.ajax({
		url : url,
		data : params,
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			if (data && data.returnCode == '200') {
//				data.returnType = 1;
				Dialog.alertInfo(data.returnMsg,false);
		        initList();
			}
			if (data && data.returnCode != '200') {
				Dialog.alertError(data.returnMsg,false);
			}
		}
	});
}

LnkYjReport.close = function () {
	LnkYjReport.dialog.close();
}
//填充储存的表单值
LnkYjReport.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	$("#LnkYjReportListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#LnkYjReportListForm").find("select").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
    if (jsonMap["objNum"]) {
        $("input[name='objNum']").attr("checked", "checked");
    }
	var curPage = jsonMap["curPage"];
    if (curPage > 1) {
        $('#curPageTemp').val(curPage);
        $('#sysPageChaneTemp').val(jsonMap["sysPageChane"]);
    }
};

