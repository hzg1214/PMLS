/** ************************公共部分***************************** */

$(function() {

});


LnkYjReport = function() {
};

/**
 * 保存编辑修改
 */
LnkYjReport.save = function() {
	
	var ReportId = $("#reportId").val();
	var url = BASE_PATH + '/lnkYjReport/saveEdit/' + ReportId;
	$('input[name="companyName"]').each(function(i) {
		if(i==1){
			$("#inputCompanyName").val($(this).val());
			$("#companyNo").val($("#companyCode"+2).val());
		}
		if(i==2){
			$("#inputCompanyNameTwo").val($(this).val());
			$("#companyNoTwo").val($("#companyCode"+3).val());
		}
	});
	var companyNo = $("#companyNo").val();
	var companyNoTwo = $("#companyNoTwo").val();
	var company = $("input[name='companyName']").val().trim();//返佣对象一
	var inputCompanyName = $("#inputCompanyName").val().trim();//返佣对象二
	var inputCompanyNameTwo = $("#inputCompanyNameTwo").val().trim();//返佣对象三
	
//	if(inputCompanyName == null || inputCompanyName == '' || inputCompanyName == undefined){
//		Dialog.alertError("请输入返佣对象二");
//		return false;
//	}
	if((companyNoTwo == null || companyNoTwo == '' || companyNoTwo == undefined) 
			&& (inputCompanyNameTwo != null && inputCompanyNameTwo != '' && inputCompanyNameTwo != undefined)){
		$("#fyCompanyName3").val("");
		$("#errorMsg4").empty().html("");
		Dialog.alertError("经纪公司录入有误，请输入关键字搜索后选择对应的经纪公司！");
		return false;
	}
	if((companyNo == null || companyNo == '' || companyNo == undefined) 
			&& (inputCompanyName != null && inputCompanyName != '' && inputCompanyName != undefined)){
		$("#fyCompanyName2").val("");
		$("#errorMsg4").empty().html("");
		Dialog.alertError("经纪公司录入有误，请输入关键字搜索后选择对应的经纪公司！");
		return false;
	}
	
	if(company == inputCompanyName){
		$("#fyCompanyName2").val("");
		$("#errorMsg4").empty().html("");
		Dialog.alertError("返佣对象二与返佣对象一不能一样");
		return false;
	}
	if(inputCompanyNameTwo != null && inputCompanyNameTwo != ''){
		if(inputCompanyName == null || inputCompanyName == ''){
			Dialog.alertError("请输入返佣对象二");
			return false;
		}
		if(inputCompanyName == inputCompanyNameTwo){
			$("#fyCompanyName3").val("");
			$("#errorMsg4").empty().html("");
			Dialog.alertError("返佣对象三与返佣对象二不能一样");
			return false;
		}
		if(company == inputCompanyNameTwo){
			$("#fyCompanyName2").val("");
			$("#errorMsg4").empty().html("");
			Dialog.alertError("返佣对象三与返佣对象一不能一样");
			return false;
		}
	}
//	var params = $("#lnkYjReportEditForm").serialize();
//	return false;
//	systemLoading("", true);
//	// 校验输入信息
//	if (Validator.validForm("lnkYjReportEditForm")) {
//
//		httpPut(url, params, function(data) {
//			location.href = BASE_PATH + "/lnkYjReport";
//		}, function(data) {
//			Dialog.alertError(data.returnMsg);
//			systemLoaded();
//		});
//
//
//	} else {
//		systemLoaded();
//	}
	
	var params = $("#lnkYjReportEditForm").serialize();
	$.ajax({
		url : url,
		data : params,
		type : 'PUT',
		dataType : 'json',
		success : function(data) {
			if (data && data.returnCode == '200') {
				systemLoaded();
				location.href = BASE_PATH + "/lnkYjReport?searchParam=1";
			}else{
				Dialog.alertError(data.returnMsg);
				systemLoaded();
			}
		},
		error:function(data){
//			$("#fyCompanyName2").val("");
//			$("#fyCompanyName3").val("");
			Dialog.alertError(data.returnMsg);
		}
	});
	
};

/**
 * 保存业绩
 */
LnkYjReport.saveEditMaintenance = function(){
    var ids = $("#ids").val();
    var reportIds = $("#reportId").val();
    var estateId = $("#estateId").val();
    var companyNo = $("#companyCode").val();
    var companyNoTwo = $("#companyCodeTwo").val();
//    var selectRadio = $("input.selectCenterId:checked");
//    var selectCenterId = selectRadio.val();
    var yjReportFirst = $("#inputCompanyName").val();
    var yjReportSecond = $("#inputCompanyNameTwo").val();
    if(isBlank(yjReportFirst)){
    	$("#errorMsg").text("返佣对象一必填!");
        $("#errorMsg").css("visibility","initial");
        return false;
    }

    var url = BASE_PATH + '/lnkYjReport/saveMaintenance';
    var params = {
        ids : ids,
        reportIds : reportIds,
        yjReportFirst : yjReportFirst,
        yjReportSecond : yjReportSecond,
        companyNo : companyNo,
        estateId : estateId,
        companyNoTwo : companyNoTwo
    }
    restPost(url, params, function(data){
        console.log(data);
        initList();
    }, function(data){
        if(data.returnCode != "200"){
            Dialog.alertError(data.returnMsg);
        }
    })
}


