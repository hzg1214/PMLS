/** ************************公共部分***************************** */
var jsonMap = null;
$(function() {
	// 初始化查询
	initList();
	initReportDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');

    var searchParamMap = CommonUtil.getCookie("REPORT_LIST_SEARCH");
    if (searchParamMap) jsonMap = JSON.parse(searchParamMap);
    if (!jsonMap || !jsonMap["realityCityNo"]) initList();
    cityLinkageIsService(setCity, setDist);
});

setCity = function(){
    if (jsonMap && jsonMap.hasOwnProperty("realityCityNo")) $("#realityCityNo").val(jsonMap["realityCityNo"]).change();
};

setDist = function(){
    if (jsonMap && jsonMap.hasOwnProperty("districtId")) $("#districtNo").val(jsonMap["districtId"]);
    if (initCityDist) initList();
};

/** **************************demo js*************************** */
Report = function() {
};

/**
 * 初始化查询
 */
initList = function() {

	httpGet('ReportListForm', 'LoadCxt', BASE_PATH + '/report/q', function() {

		pageInfo("ReportListForm", function() {
			initList();
		});
        //表前几列冻结
		var width = $("#divReport").width;
		var height = $("#divReport").height();
		FixTable("tblReport", 4, 1150, height);
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
    Report.search();
};


//填充储存的表单值
Report.setSearchParams = function (searchParamMap) {
	var jsonMap = JSON.parse(searchParamMap);
	
	$("#ReportListForm").find("input").each(function () {
		var searchKey = $(this).attr("id");
		var searchValue = jsonMap[searchKey];
		if (searchValue != undefined && searchValue != null && searchValue != '') {
			$("#" + searchKey).val(searchValue);
		}
	});
	$("#ReportListForm").find("select").each(function () {
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


SceneRecognition = function() {
	dialog: null;
};

SceneRecognition.toSceneHouseInfo = function(reportId, status,estateId) {
	var url = null;
	var params = null;
	if (status == 13502 || status == 13503) {
        params={
            reportId : reportId
		}
		url = BASE_PATH + "/sceneEstate/toSceneHouseInfoAddOne/"+status;
	}
	// 点击大定
	if (status == 13504) {
		url = BASE_PATH + "/sceneEstate/toSceneHouseInfoAdd";
		params = {
				reportId : reportId,
				estateId : estateId
			};
	}
	if (status == 13505) {
		url = BASE_PATH + "/sceneEstate/toSceneHouseInfoEdit";
		params = {
			reportId : reportId,
			estateId : estateId
		};
	}
	var dialogOptions = {
			
			width : 500,
			height : 100,
		ok : function() {
			// 确定
			var reback = SceneRecognition.updateStatus(reportId, status,estateId);
			return reback;
		},
		okVal : '保存',
		cancel : true,
		cancelVal : '取消'
	};
	Dialog.ajaxOpenDialog(url, params, "流程节点", function(dialog, resData) {
		SceneRecognition.dialog = dialog;
	}, dialogOptions);
};

function handlerFileInfo(status){
	if(status == 13502 || status == 13504){
		//验证带单附件
		if($('#fileIdPhotoToSee> .item-photo-list').length && $('#fileIdPhotoToSee> .item-photo-list').length>0){
		}else{
			$("#errorMsg").find(".fc-warning").empty().html("请上传带看单");
			return false;
		}
		if(status == 13504){
			//验证大定单附件
			if($('#fileIdPhotoBox> .item-photo-list').length && $('#fileIdPhotoBox> .item-photo-list').length>0){
			}else{
				$("#errorMsg").find(".fc-warning").empty().html("请上传大定单");
				return false;
			}
			//验证甲方项目负责人名片附件
			if($('#fileIdPhotoCard> .item-photo-list').length && $('#fileIdPhotoCard> .item-photo-list').length>0){
			}else{
				$("#errorMsg").find(".fc-warning").empty().html("请上传甲方项目负责人名片");
				return false;
			}
		}
	}

    if(status == 13505){
        if($('#fileIdPhotoToDeal> .item-photo-list').length && $('#fileIdPhotoToDeal> .item-photo-list').length>0){
        }else{
            $("#errorMsg").find(".fc-warning").empty().html("请上传成销确认书/佣金结算资料");
            return false;
        }
    }

    var bol = true;
    var fileRecordMainIds = "";
    $("input[name=fileRecordMainIdHidden]").each(function(){
        if($(this).val()==""){
            Dialog.alertError("图片上传出现异常，请删除重新上传。");
            bol = false;
            return false;
        }
        fileRecordMainIds += ","+$(this).val();
    })
    if(fileRecordMainIds!=""){
        fileRecordMainIds = fileRecordMainIds.substring(1);
    }
    $("#fileRecordMainIds").val(fileRecordMainIds);

    return bol;
}

SceneRecognition.updateStatus = function(reportId, status, estateId) {
	$("#errorMsg").find(".fc-warning").empty();
	systemLoading("", true);
	var customerNm = $("#customerNm").val(); 
	var customerTel = $("#customerTel").val(); 
	var buildingNo = $("#buildingNo").val();
	var area = $("#area").val();
	var roughAmount = $("#roughAmount").val();
	var dealAmount = $("#dealAmount").val();
	var operateDate = $("#operateDate").val(); 
	var roughArea = $("#roughArea").val();
	var settleConfirmDate = $("#settleConfirmDate").val();

	var customerNmTwo = $("#customerNmTwo").val(); 
	var customerTelTwo = $("#customerTelTwo").val();

	var befYJSRAmount = $("#befYJSRAmount").val();
	befYJSRAmount = $.trim(befYJSRAmount).replaceAll(",", "");
	var aftYJSRAmount = $("#aftYJSRAmount").val();
	aftYJSRAmount = $.trim(aftYJSRAmount).replaceAll(",", "");

    var accountProjectNo = $("#accountProjectNo").val();

	var fySize = $("#fySize").val();
	var fyList = [];
    var falg = true;
    var falgIndex = 0;
	if (fySize != null && fySize != undefined) {
		for (var i = 0; i < fySize; i++) {
			var companyNo = $("#companyNo" + i).val();
			var befYJFYAmount = $("#befYJFYAmount" + i).val();
			befYJFYAmount = $.trim(befYJFYAmount).replaceAll(",", "");
			var aftYJFYAmount = $("#aftYJFYAmount" + i).val();
			aftYJFYAmount = $.trim(aftYJFYAmount).replaceAll(",", "");
            var befYJDYAmount = $("#befYJDYAmount" + i).val();
            befYJDYAmount = $.trim(befYJDYAmount).replaceAll(",", "");
            var aftYJDYAmount = $("#aftYJDYAmount" + i).val();
            aftYJDYAmount = $.trim(aftYJDYAmount).replaceAll(",", "");
            if(befYJDYAmount!=""&& befYJDYAmount!= undefined && befYJDYAmount != null && aftYJDYAmount!=""&& aftYJDYAmount!= undefined && aftYJDYAmount != null ){
                falg = true;
            }else if((befYJDYAmount==""|| befYJDYAmount== undefined || befYJDYAmount == null) && (aftYJDYAmount=="" || aftYJDYAmount== undefined || aftYJDYAmount == null)){
                falg = true;
            }else{
                falg = false;
                falgIndex = i+1;
                break;
			}
			var obj = {
				companyNo: companyNo,
				befYJFYAmount: befYJFYAmount,
				aftYJFYAmount: aftYJFYAmount,
                befYJDYAmount: befYJDYAmount,
                aftYJDYAmount: aftYJDYAmount
			};
			fyList.push(obj);
		}
	}

	if(customerNm!='' && customerNm !=undefined && customerNm!=null )customerNm=$.trim(customerNm);
	if(buildingNo!='' && buildingNo !=undefined && buildingNo!=null )buildingNo=$.trim(buildingNo);
	if(customerNmTwo!='' && customerNmTwo !=undefined && customerNmTwo!=null )customerNmTwo=$.trim(customerNmTwo);
		
		//13502
	if (status == 13502 ||status == 13504 || status == 13505) {
		if($("#customerNmTwo").val() !="") {
			if ($("#customerTelTwo").val()==undefined || $("#customerTelTwo").val()==null || $("#customerTelTwo").val()=='') {
				$("#errorMsg").find(".fc-warning").empty().html("客户已填写，客户手机必须填写！");
				systemLoaded();
				$("#customerTelTwo").focus();
				return false;
			}
		}
		if($("#customerTelTwo").val() !="") {
			if ($("#customerNmTwo").val()==undefined || $("#customerNmTwo").val()==null || $("#customerNmTwo").val()=='') {
				$("#errorMsg").find(".fc-warning").empty().html("客户手机已填写，客户必须填写！");
				systemLoaded();
				$("#customerNmTwo").focus(); 
				return false;
			}
		}
	}
	if(status == 13505){
		if(buildingNo==undefined || buildingNo==null || buildingNo==''){
			$("#errorMsg").find(".fc-warning").empty().html("楼室号不能为空！");
			systemLoaded();
			$("#buildingNo").focus(); 
			return false;
		}
	}

    if(!falg){
        $("#errorMsg").find(".fc-warning").empty().html("返佣对象"+falgIndex+"垫佣必须成对填写！");
        systemLoaded();
        $("#befYJDYAmount" + falgIndex-1).focus();
        $("#aftYJDYAmount" + falgIndex-1).focus();
        return false;
    }

    if(status == 13505){
        if(accountProjectNo == undefined || accountProjectNo == null || accountProjectNo == ''){
            $("#errorMsg").find(".fc-warning").empty().html("核算主体不能为空！");
            systemLoaded();
            $("#accountProjectNo").focus();
            return false;
        }
    }


    if(!handlerFileInfo(status)){
        systemLoaded();
        return false;
    }
    
    if(status == 13504){
        var flag = buildingNoRepeatCount(buildingNo,reportId,1);
        if(!flag){
        	systemLoaded();
//        	$("#errorMsg").find(".fc-warning").empty().html("该楼室号已报备，请勿重复录入！");
        	$("#buildingNo").focus(); 
    		return false;
        }
    }
   
	if (Validator.validForm("sceneHouseInfoForm")) {

		var params = {
			reportId : reportId,
			estateId : estateId,
			status : status,
			buildingNo : buildingNo,
			area : area,
			roughAmount : roughAmount,
			dealAmount : dealAmount,
			operateDate:operateDate,
			roughArea:roughArea,
			customerNm : customerNm,
			customerTel : customerTel,
			customerNmTwo : customerNmTwo,
			customerTelTwo : customerTelTwo,
            fileRecordMainIds : $("#fileRecordMainIds").val(),
			id : $("#id").val(),
            settleConfirmDate:settleConfirmDate,
			befYJSRAmount: befYJSRAmount,
			aftYJSRAmount: aftYJSRAmount,
            accountProjectNo:accountProjectNo,
			fyList: JSON.stringify(fyList)
		};

        var url = BASE_PATH + "/sceneEstate/qSceneRecognition/confirm/post";
        $.ajax({
            type: 'POST',
            url: url,
            data: params,
            dataType:"json",
            success:function(data) {
                systemLoaded();
                if (data.returnValue == 0) {
                    $("#errorMsg").find(".fc-warning").empty().html("操作失败");
                    return false;
                } else if(data.returnValue == 201){
                    $($(".ui_state_highlight").get(0)).attr("disabled",false);
                    $("#errorMsg").find(".fc-warning").empty().html(data.returnMsg);
                    return false;
                }else if(data.returnValue == 202){
                	$($(".ui_state_highlight").get(0)).attr("disabled","disabled");
                    $("#errorMsg").find(".fc-warning").empty().html(data.returnMsg);
                    return false;
                }else {
                    SceneRecognition.close();
                    var page = 1;
                    if($("#pageChane").length>0){
                        page = $("#pageChane").val();
                    }
                    SceneRecognition.search(page);
                }
            },
            error:function(data) {
                $("#errorMsg").find(".fc-warning").empty().html("操作失败");
                systemLoaded();
                return false;
            }
        });
	}
	setTimeout(function() {
		systemLoaded();
	}, 3000);
	return false;
};
SceneRecognition.rebackView = function(reportId, status,estateId) {
	var url = BASE_PATH + "/sceneEstate/rebackView/"+status;;
	var params = {"reportId":reportId};
	var dialogOptions = {
			width : 500, 
			height : 100,
		ok : function() {
			// 确定
			var reback = SceneRecognition.rebackToSee(reportId, status,estateId);
			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '返回'
	};
	Dialog.ajaxOpenDialog(url, params, "新房联动回退", function(dialog, resData) {
		SceneRecognition.dialog = dialog;
	}, dialogOptions);
};

var rebackRepeatFlg = false;
SceneRecognition.rebackToSee = function(reportId, status, estateId) {
	systemLoading("", true);
	var url = BASE_PATH + "/report/reback";
	var originUrl =  BASE_PATH + "/sceneEstate/qSceneRecognition/"+estateId;
	var operateDate = $("#operateDate").val(); 
	if (Validator.validForm("rebackForm")) {
		var params = {
				reportId : reportId,
				estateId : estateId,
				status : status,
				operateDate:operateDate
		};
		httpPut(url, params, function(data) {
			systemLoaded();
			if(data.returnValue == 201){
				//rebackRepeatFlg = false;
				$("#errorMsg").find(".fc-warning").empty().html(data.returnMsg);
				return false;
			}else if(data.returnValue == 500){
				//rebackRepeatFlg = false;
				$("#errorMsg").find(".fc-warning").empty().html("操作异常");
				return false;
			}else {
				SceneRecognition.close();
                var page = 1;
                if($("#pageChane").length>0){
                    page = $("#pageChane").val();
                }
                SceneRecognition.search(page);
				//location.href = originUrl;
			}
		}, function(data) {
			//rebackRepeatFlg = false;
			$("#errorMsg").find(".fc-warning").empty().html("操作失败");
			systemLoaded();
			return false;
		});
	}
	setTimeout(function() {
		systemLoaded();
	}, 3000);
	return false;
};
SceneRecognition.recognitionStatus = function(reportId, status,estateId) {
	var url = BASE_PATH + "/sceneEstate/qSceneRecognition/confirm";
	var params = {
		reportId : reportId,
		estateId : estateId,
		status : status
	};
	var msg = "";
	if (status == 13501) {
		msg = "确认要报备吗？";
	} else if (status == 13502) {
		msg = "确认要带看吗？";
	} else if (status == 13503) {
		msg = "确认要认筹吗？";
	} else if (status == 13504) {
		msg = "确认要大定吗？";
	} else if (status == 13505) {
		msg = "确认要成销吗？";
	} else if (status == 13506) {
		msg = "确认要退筹吗？";
	} else if (status == 13507) {
		msg = "确认要结佣吗？";
	} else if (status == 13602) {
		msg = "确认要无效吗？";
	}
	Dialog.confirm(msg, function() {
		ajaxGet(url, params, function(data) {
			if (data.returnValue == 0) {
				Dialog.alertError(data.returnMsg);
			} else {
                var page = 1;
                if($("#pageChane").length>0){
                    page = $("#pageChane").val();
                }
                SceneRecognition.search(page);
			}
		}, function() {
		});
	});
};
function checkRepeatSubmit() {
    if (!rebackRepeatFlg) {
    	rebackRepeatFlg = true;
        return false;//第一次提交
    } else {
        return true;//重复提交
    }
}
SceneRecognition.close = function() {
	SceneRecognition.dialog.close();
};
SceneRecognition.search = function(page) {
    if(page){
        $('#curPage').val(page);
    }else{
        $('#curPage').val('1');
    }
	initList();
};

