var jsonMap = null;
$(function() {
    // 初始化查询
    ReportToValid.initList();
    initReportDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');

    var searchParamMap = CommonUtil.getCookie("REPORTTOVALID_LIST");
    if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
    if (!jsonMap || !jsonMap["realityCityNo"]) ReportToValid.initList();
    cityLinkageIsService(setCity, setDist);
});

setCity = function(){
    if (jsonMap && jsonMap.hasOwnProperty("realityCityNo")) $("#realityCityNo").val(jsonMap["realityCityNo"]).change();
};

setDist = function(){
    if (jsonMap && jsonMap.hasOwnProperty("districtId")) $("#districtNo").val(jsonMap["districtId"]);
    if (initCityDist) initList();
};

ReportToValid = function() {
};

/**
 * 初始化查询
 */
ReportToValid.initList = function() {
	httpGet('ReportToValidForm', 'LoadCxt', BASE_PATH + '/reportToValid/q', function() {

		pageInfo("ReportToValidForm", function() {
            ReportToValid.initList();
		});
        //表前几列冻结
		var height = $("#divReport").height();
		FixTable("tblReport", 4, 1150, height);
	});
};
/**
 * 查询
 * 
 */
ReportToValid.search = function() {
	$('#curPage').val('1');
    ReportToValid.initList();
};

ReportToValid.reset = function() {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
    ReportToValid.search();
};


//填充储存的表单值
ReportToValid.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	
	$("#ReportToValidForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#ReportToValidForm").find("select").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	//筛选
	var signDateType = jsonMap["signDateType"];
	if (signDateType != undefined && signDateType != null && signDateType != "") {
		$("#divSignType").find("a").removeClass("active");
		$("#divSignType").find("a").eq(signDateType).addClass("active");
		$("#divSignType").find("a").eq(signDateType).click();
	}
	var curPage = jsonMap["curPage"];
    if (curPage > 1) {
        $('#curPageTemp').val(curPage);
        $('#sysPageChaneTemp').val(jsonMap["sysPageChane"]);
    }
};

ReportToValid.toPass=function (id,fromType,estateId,passFlag,isApproval,detailId) {
	var customerFromHide = $("#customerFromHide").val();
	if(17405==customerFromHide){
		var flag = false;
		var yfUrl = BASE_PATH + '/reportToValid/getYHApproveCheck/'+id;
		$.ajax({
			   type: "GET",
			   url: yfUrl,
			   async : false,
			   dataType:"json",
			   contentType: 'application/json',
			   success: function(data){
				   if(data.returnCode=='200'){
					 var returnData = data.returnData;
					 if(returnData==true){
						 flag = true;
					 }
				   }else{
					   
				   }
			   },
			   error:function(){
				   
			   }
		});
		
		if(!flag){
			Dialog.alertInfo("门店未绑定经纪公司，请驳回通知业务人员绑定!");
			return false;
		}
	}
	
	//passFlag为1，大定日期处于关帐月份
	if(passFlag == '1'){
		Dialog.alertInfo("大定日期已关账，请驳回让驻场人员修改!");
		return false;
	}
	//为1表示未立项
	if(isApproval == '1'){
		Dialog.alertInfo("该项目OA未立项，不允许大定审核通过！");
		return false;
	}
	//弹框(选择收入类型)
    var dialogOptions = {
        ok : function() {
            var inComeStatus = $("#inComeStatus").val();
            if(inComeStatus==""||inComeStatus==null||inComeStatus==undefined){
                $("#inComeStatusStr").text("请选择收入类型！");
                return false;
            }
            var params= {
                "id": id,
                "roughAuditStatus": "1",
                "roughInputDate": $("#roughInputDate").val(),
                "reportId": $("#reportId").val(),
                "companyNo": $("#companyNo").val(),
				"detailId":detailId,
                "inComeStatus":inComeStatus
            }
            ajaxGet(BASE_PATH + "/reportToValid/roughAudit", params, function(data) {
                if(data.returnCode == '200'){
                    Dialog.alertSuccess();
                    //window.location.reload();// = BASE_PATH + "/reportToValid/toView/"+id;
                    $("a[hideA]").hide();
                    $("#roughAuditStatus").html("审核通过");
                    if(fromType == '0'){
                        var url = BASE_PATH + '/report?searchParam=1';
                        window.location.href =url;
                    }
                    if(fromType == '0'){
                        var url = BASE_PATH + '/report';
                        window.location.href =url;
                    }
                    if(fromType == '1'){
                        var url = BASE_PATH + '/reportToValid';
                        window.location.href =url;
                    }
                    if(fromType == '2'){
                        var url = BASE_PATH + '/reportToValid/valided';
                        window.location.href =url;
                    }
                    if(fromType == '3'){
                        var url = BASE_PATH + '/sceneEstate/qSceneRecognition/'+estateId;
                        window.location.href =url;
                    }
                }else{
                    Dialog.alertError(data.returnMsg);
                }
            }, function(data) {
                Dialog.alertError(data.returnMsg);
            });
        },
        okVal : '确定',
        cancel : true,
        cancelVal : '取消',
        width:780
    };
    Dialog.ajaxOpenDialog(BASE_PATH + "/reportToValid/toInComeStatus/"+detailId,{"detailId":detailId},"收入类型选择",function(dialog){
        ReportToValid.dialog = dialog;
    },dialogOptions);
};

ReportToValid.toReject=function (id,fromType,estateId) {
    var dialogOptions = {
        ok : function() {
            return ReportToValid.reject(id,fromType,estateId);
        },
        okVal : '确定',
        cancel : true,
        cancelVal : '取消',
        width:780,
        height:300
    };
    Dialog.ajaxOpenDialog(BASE_PATH + "/reportToValid/toReject/"+id,{"id":id},"意见填写",function(dialog){
        ReportToValid.dialog = dialog;
    },dialogOptions);
};

ReportToValid.close = function(){
    ReportToValid.dialog.close();
}

ReportToValid.reject = function(id,fromType,estateId){
    if(!Validator.validForm("reportToValidRejectForm")){
        return false;
    }
    var params={
        "id":id,
        "roughAuditStatus":"2",
        "roughAuditDesc":$.trim($("#roughAuditDesc").val())
    }
    ajaxGet(BASE_PATH + "/reportToValid/roughAudit", params, function(data) {
        if(data.returnCode == '200'){
            Dialog.alertSuccess();
            //window.location.reload();// = BASE_PATH + "/reportToValid/toView/"+id;
            $("a[hideA]").hide();
            $("#roughAuditStatus").html("审核驳回");
            if(fromType == '0'){
            	var url = BASE_PATH + '/report?searchParam=1';
            	window.location.href =url;
            }
            if(fromType == '0'){
            	var url = BASE_PATH + '/report?searchParam=1';
            	window.location.href =url;
            }
            if(fromType == '1'){
            	var url = BASE_PATH + '/reportToValid?searchParam=1';
            	window.location.href =url;
            }
            if(fromType == '2'){
            	var url = BASE_PATH + '/reportToValid/valided?searchParam=1';
            	window.location.href =url;
            }
            if(fromType == '3'){
            	var url = BASE_PATH + '/sceneEstate/qSceneRecognition/'+estateId+'?searchParam=1';
            	window.location.href =url;
            }
        }else{
            Dialog.alertError(data.returnMsg);
        }
    }, function(data) {
        Dialog.alertError(data.returnMsg);
    });
}