window.hideFlag = false;
var dataLength ;
var updateId;
$(function() {
//	//市区域板块
//	citylinkage();
	initDetail();
	var estateId = $("#estateId").val();
	if($("#houseTypeFlag").val() == "0"){
		hideFlag = true;
	}
	//initRule(estateId);
	// initType(estateId);
	// initPhoto(estateId);
	// initSupport(estateId);
	getcommissionStatus();
	//houseTypeHide();
});

/**
 * 初始化楼盘基本信息和详情
 */
initDetail=function(){
	var id=$("#id").val();
	httpGetNoLoading('estateEditForm', 'loadDetail', BASE_PATH + '/estate/qd/'+id+'/', function() {
	});
};

/**
 * 初始化楼盘联动规则
 */
initRule=function(estateId){
	httpGetNoLoading('estateEditForm', 'loadRule', BASE_PATH + '/estate/qr/'+estateId+'/', function() {
	});
};

/**
 *初始化楼盘在售户型 
 */
initType=function(estateId){
	httpGetNoLoading('', 'loadType', BASE_PATH + '/estate/qt/'+estateId+'/', function() {
	});
};

/**
 *初始化楼盘相册
 */
initPhoto=function(estateId){
	httpGetNoLoading('estateEditForm', 'loadPhoto', BASE_PATH + '/estate/qp/'+estateId+'/', function() {
		// photoUploader();
	});
};

/**
 *初始化楼盘周边配套
 */
initSupport=function(estateId){
	var auditStatus = $("#auditStatus").val();
	httpGetNoLoading('estateEditForm', 'loadSupport', BASE_PATH + '/estate/qs/'+estateId+'/'+auditStatus, function() {
	});
};

/**
 *编辑保存
 */
edit=function(){
	//保存时审核状态为未提交
	$("#auditStatus").val(12904);
	
	if(checkForAdd()){
		var url = BASE_PATH + '/estate/update/'+$("#id").val();
		if(Validator.validFormDataType("estateEditForm")){
			
			var params = $("#estateEditForm").serialize();
			systemLoading("", true);
			httpPut(url, params, function(data) {
				if(data.returnCode == 200){
					location.href = BASE_PATH + "/estate";
				}else{
					Dialog.alertError(data.returnMsg);
				}
			}, function(data) {
				Dialog.alertError(data.returnMsg);
				systemLoaded();
			});
		}
	}
};

/**
 *编辑提交
 */
editSubmit=function(){
	var id=$("#id").val();
	updateId = id;
	//提交时审核状态为未审核
	/*if($('.estateTypeEdit').size() == 0){
		Dialog.alertError("请完善户型信息！");
	}else{*/
		if (checkItem(2) == false) {
			return false;
		}
		$("#auditStatus").val(12901);
		if(Validator.validForm("estateEditForm")){
			//查询核算主体
			var estateCityNo =  $("#cityNo").val();
			if(isNullOrEmpty(estateCityNo)) {
				$.ajax({
					url:BASE_PATH + "/cashBill/getLnkAccountProjectList",
					async: false,
					type:"get",
					data:{
						estateCityNo	: estateCityNo
					},
					success:function(data){
						if(data) {
							data = JSON.parse(data);
							var length =  data.returnValue.length +"";
							if("1" ==  length) {
								dataLength = 1;
								$("#lnkAccountProjectCode").val(data.returnValue[0].lnkaccountProjectCode);
								$("#lnkAccountProject").val(data.returnValue[0].lnkAccountProject);
							}else {
								dataLength = 2;
							}
						}
					}
				});
			}
			if( 1 ==  dataLength) {
				submitOA(updateId);
			} else {
				//弹窗选择
				var url_a = BASE_PATH+"/estate/toChooseAccountProject";
				var params = {estateCityNo:estateCityNo};
				var dialogOptions = {
						width:600
				};
				Dialog.ajaxOpenDialog(url_a, params, "选择考核主体", function(dialog, resData) {
					EstateType.dialog = dialog;
				}, dialogOptions);
			}
		}
};


/**************************在售户型***********************/
EstateType = function() {
	dialog: null;
};

EstateType.close = function() {
	EstateType.dialog.close();
};

/**
 * 跳转新增在售户型
 */
EstateType.toAddEstateType=function(){
	var id=$("#id").val();
	var estateId=$("#estateId").val();
	var url = BASE_PATH + "/estate/toct";
	var params = {
			estateId:estateId
	};
	var dialogOptions = {
			ok : function() {
				// 确定
				var reback = EstateType.addEstateType(id,estateId);
				systemLoaded();
				return reback;
			},
			okVal : '确定',
			cancel : true,
			cancelVal : '返回'
	};
	Dialog.ajaxOpenDialog(url, params, "添加在售户型", function(dialog, resData) {
		EstateType.dialog = dialog;
		// photoUploader();
	}, dialogOptions);
};

/**
 * 跳转户型编辑页面
 */
EstateType.toEditEstateType=function(typeId){
	var id=$("#id").val();
	var url = BASE_PATH + "/estate/tout";
	var params = {
			typeId:typeId
	};
	var dialogOptions = {
			ok : function() {
				// 确定
				var reback = EstateType.updateEstateType(id,typeId);
				systemLoaded();
				return reback;
			},
			okVal : '确定',
			cancel : true,
			cancelVal : '返回'
	};
	Dialog.ajaxOpenDialog(url, params, "编辑在售户型", function(dialog, resData) {
		EstateType.dialog = dialog;
		// photoUploader();
	}, dialogOptions);
};

/**
 * 新增在售户型
 */
EstateType.addEstateType=function(id,estateId){
	if ($('input[name="directionKbn"]:checked').val() == undefined
			|| $('input[name="directionKbn"]:checked').val() == "") {
		$('#errormsg').html("请选择朝向");
		return false;
	} else {
		$('#errormsg').html("");
	}
	// if($("#uploadPhotoId6").val() == undefined || $("#uploadPhotoId6").val() == ""){
	// 	$('#errormsg').html("请选择户型图");
	// 	return false;
	// }else {
	// 	$('#errormsg').html("");
	// }
    /*if ($(".houseTypePhotos").find(".item_photo_uploader").size() <= 1){
        $('#errormsg').html("请选择户型图");
        return false;
    }else {
    	$('#errormsg').html("");
    }*/
	var url = BASE_PATH + '/estate/ct';
	var backUrl = "/estate/u/"+id;
	// 校验输入信息
	if (Validator.validForm("estateTypeAddForm")) {
		systemLoading("", true);
		httpPost('estateTypeAddForm', url, function(data) {
			initType(estateId);
			EstateType.close();
			systemLoaded();
//			location.href = BASE_PATH + backUrl;
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
			return false;
		});
	} else {
		systemLoaded();
		return false;
	}
	return true;
};

/**
 * 编辑在售户型
 */
EstateType.updateEstateType=function(id,typeId){
	if ($('input[name="directionKbn"]:checked').val() == undefined
			|| $('input[name="directionKbn"]:checked').val() == "") {
		$('#errormsg').html("请选择朝向");
		return false;
	} else {
		$('#errormsg').html("");
	}
	// if($("#uploadPhotoId6").val() == undefined || $("#uploadPhotoId6").val() == ""){
	// 	$('#errormsg').html("请选择户型图");
	// 	return false;
	// }else {
	// 	$('#errormsg').html("");
	// }
    /*if ($(".houseTypePhotos").find(".item_photo_uploader").size() <= 1){
        $('#errormsg').html("请选择户型图");
        return false;
    }else {
        $('#errormsg').html("");
    }*/
	var url = BASE_PATH + '/estate/ut';
	var backUrl = "/estate/u/"+id;
	var params = $("#estateTypeEditForm").serialize();
	// 校验输入信息
	if (Validator.validForm("estateTypeEditForm")) {
		systemLoading("", true);
		httpPut(url, params, function(data) {
//			location.href = BASE_PATH + backUrl;
			var estateId = $("#estateId").val();
			initType(estateId);
			EstateType.close();
			systemLoaded();
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
			return false;
		});
	} else {
		systemLoaded();
		return false;
	}
	return false;
};

/**
 * 删除在售户型
 */
EstateType.delEstateType=function(typeId){
	var url=BASE_PATH+'/estate/dt/'+typeId;
	var params = {
			typeId:typeId
	};
	var id=$("#id").val();
	var backUrl = "/estate/u/"+id;
	
	Dialog.confirm("确认删除本条户型记录？",function(){
		httpDelete(url, params, function(data) {
//			location.href = BASE_PATH + backUrl;
			var estateId = $("#estateId").val();
			initType(estateId);
			EstateType.close();
			systemLoaded();
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
			return false;
		});
	},function(){
		
	});
};
/**
 * 弹出合作模式窗口
 */
EstateType.popupCooperationMode=function(id, name, mode){
	var url = BASE_PATH + '/estate/popupCoMode/' + id + '/' + name + '/' + mode;
	var params = {
	};
	var dialogOptions = {
		width:600
	};
	Dialog.ajaxOpenDialog(url, params, "修改合作模式", function(dialog, resData) {
		EstateType.dialog = dialog;
	}, dialogOptions);
};
/**
 * 修改合作模式
 */
EstateType.changeCooperationMode=function(id){
	var reason = $("#cooperationChangeReason").val();
	if (reason==null || reason.length==0) {
//		Dialog.alertError("请填写原因！");
		$("#warning-noReason").html("请填写原因！");
		return;
	}
	var url = BASE_PATH + '/estate/changeCoMode';
	var params = $("#estateTypeAddForm").serialize();
	
	httpPost("estateTypeAddForm", url, function(data) {
		location.href = BASE_PATH + "/estate/" + id;
	}, function(data) {
		Dialog.alertError(data.returnMsg);
		systemLoaded();
	});
	EstateType.dialog.close();
};

EstateType.closePopup = function(){
	EstateType.dialog.close();
}
/**
 * 弹出项目发布窗口
 * /popupReleaseCity/{id}/{estateId}/{cityNo}/{cityNm}
 */

EstateType.popupReleaseChangeCity=function(id,estateId, cityNo, cityNm){
	var url = BASE_PATH + '/estate/popupReleaseCity/' + id + '/' + estateId + '/' + cityNo +'/' + cityNm;
	var params = {
	};
	var dialogOptions = {
			width:600
	};
	Dialog.ajaxOpenDialog(url, params, "项目发布城市变更", function(dialog, resData) {
		EstateType.dialog = dialog;
	}, dialogOptions);
};
/**
 * 修改项目发布城市
 */
EstateType.changeReleaseCity=function(id){ //busNo == "" || busNo == undefined
	var relseaseCity = $("#select_newRelseaseCityNo").val();
	//$("#warning-noCity").html("");
	if (relseaseCity==null || relseaseCity.length==0 || relseaseCity.length == undefined) {
//		Dialog.alertError("请填写原因！");
		$("#warning-noCity").html("请选择变更的城市！");
		return;
	}
	/*if(relseaseCity !=null){
		
	}*/
	var url = BASE_PATH + '/estate/releaseCity';
	var params = $("#releaseCityForm").serialize();
	
	httpPost("releaseCityForm", url, function(data) {
		location.href = BASE_PATH + "/estate/" + id;
	}, function(data) {
		Dialog.alertError(data.returnMsg);
		systemLoaded();
	});
	EstateType.dialog.close();
};




EstateType.popupSalesStatusChangeMode=function(id, name, auditStatus,salesStatus){
	var url = BASE_PATH + '/estate/popupStatusMode/' + id + '/' + name 
				+ '/' + auditStatus + '/' + salesStatus;
	var params = {};
	var dialogOptions = {
			width:600
	};
	Dialog.ajaxOpenDialog(url, params, "销售状态变更", function(dialog, resData) {
		EstateType.dialog = dialog;
	}, dialogOptions);
};
/**
 * 修改销售状态
 */
EstateType.changeSalesStatusMode=function(id){
	if (Validator.validForm("salesStatusChangeForm")) {
		var url = BASE_PATH + '/estate/changeStatusMode';
		var params = $("#salesStatusChangeForm").serialize();
		
		httpPost("salesStatusChangeForm", url, function(data) {
			location.href = BASE_PATH + "/estate/" + id;
		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});
		EstateType.dialog.close();
	}
};

/**
 *
 * @param id
 */
EstateType.popupDetail = function (id) {
    var url = BASE_PATH + '/estate/popupEdit';
    var params = {id: id};
    var dialogOptions = {
        ok: function () {
            // 确定
            var reback = detailUpdate(id);
            return reback;
        },
        okVal: '保存',
        cancel: true,
        cancelVal: '返回'
    };
    Dialog.ajaxOpenDialog(url, params, "项目信息变更", function (dialog, resData) {
        EstateType.dialog = dialog;
    }, dialogOptions);
};

function detailUpdate(id) {
	//校验
	if($("#estateNm").is(':checked')== false
		&& $("#estateAddress").is(':checked') == false
		&& $("#salesStatus").is(':checked')==false
        && $("#businessMode").is(':checked') == false
		&& $("#partnerNm").is(':checked') == false
		&& $("#devCompany").is(':checked')==false)
	{
		$("#checkChange").html('<font color="red">请选择修改项目</font>');
		return false
	}else
	{
		$("#checkChange").html('<font color="red"></font>');
	}
	//楼盘名称
	if($("#estateNm").is(':checked') && $("#oldName").val() == $("#newName").val())
	{
		$("#checkName").html('<font color="red">修改后的楼盘名称和原楼盘名称一样!</font>');
		return false
	}else
	{
		$("#checkName").html('<font color="red"></font>');
	}
	//项目地址
	if($("#estateAddress").is(':checked') && ($("#oldCityNo").val() == $("#realityCityNo").val()) && ($("#oldDistrictNo").val() == $("#districtNo").val()) && ($("#oldAddress").val() == $("#newAddress").val()))
	{
		$("#checkAddress").html('<font color="red">修改后项目地址和原项目地址一样!</font>');
		return false
	}else{
		$("#checkAddress").html('<font color="red"></font>');
	}
	//销售状态
	if($("#salesStatus").is(':checked') && $("#oldSalesStatus").val() == $("input[name='newSalesStatus']:checked").val()){
		$("#checkSalesStatus").html('<font color="red">修改后销售状态和原销售状态一样!</font>');
		return false
	}else{
		$("#checkSalesStatus").html('<font color="red"></font>');
	}
	//业务模式
    if($("#businessMode").is(':checked') && $("#oldBusinessMode").val() == $("input[name='newBusinessMode']:checked").val()){
        $("#checkBusinessMode").html('<font color="red">修改后业务模式和原业务模式一样!</font>');
        return false
    }else{
        $("#checkBusinessMode").html('<font color="red"></font>');
    }
	//合作方
	if($("#partnerNm").is(':checked') && $("#oldPartnerNm").val() == $("#newPartnerNm").val())
	{
		$("#checkPartnerNm").html('<font color="red">修改后的合作方和原合作方一样!</font>');
		return false
	}else
	{
		$("#checkPartnerNm").html('<font color="red"></font>');
	}
	//开发商
	if($("#devCompany").is(':checked') && $("#oldDevCompany").val() == $("#newDevCompany").val())
	{
		$("#checkDevCompany").html('<font color="red">修改后的开发商和原开发商一样!</font>');
		return false
	}else
	{
		$("#checkDevCompany").html('<font color="red"></font>');
	}

    var url = BASE_PATH + '/estate/updatePopupDetail';
    if (Validator.validForm("estatePopupForm")) {
        systemLoading("", true);
        httpPost("estatePopupForm", url, function (data) {
            location.href = BASE_PATH + "/estate/" + id;
        }, function (data) {
            Dialog.alertError(data.returnMsg);
            systemLoaded();
        });
    } else {
        systemLoaded();
        return false;
    }
    return true;
}

function openTimeChange(obj){
	var openTime = $(obj).val();
	if(openTime==1441){
		$("#ulOpenTime").show();
		$("#opentimeShow").removeClass("w130");
		$("#opentimeShow").addClass("w100");
		$("#opentimeShow").html('<i>*</i>实际开盘日期：');

	}else if(openTime==1442){
		$("#ulOpenTime").show();
		$("#opentimeShow").removeClass("w100");
		$("#opentimeShow").addClass("w130");
		$("#opentimeShow").html('<i>*</i>预计开盘日期：');

	}else if(openTime == 1443){
		$("#ulOpenTime").hide();
		$("#openTime").val('');
	}
};

EstateType.closePopup = function(){
	EstateType.dialog.close();
}
/**************************在售户型***********************/


function depChange(){
	if ($("#deptId").val() != '') {
		var url = BASE_PATH + "/estate/empList/" + $("#deptId").val();
		var params = {};

		ajaxGet(url, params, function(data) {
			var result = "<option value=''>请选择我方负责人</option>";
			$.each(data.returnValue, function(n, value) {
				result += "<option value='" + value.userId + "'>"
						+ value.userName + "</option>";
			});
			$("#empId").html('');
			$("#empId").append(result);
		}, function() {
		});
	} else {
		var result = "<option value=''>请选择我方负责人</option>";
		$("#empId").html('');
		$("#empId").append(result);
	}
}

EstateAdd = function() {
};

/**
 * 初始化查询
 */
EstateAdd.initList = function() {

    httpGet('selectFromOpForm', 'LoadCxt', BASE_PATH + '/estate/selectFromOp/q', function() {

        pageInfo("selectFromOpForm", function() {
            EstateAdd.initList();
        });

    });
};

/**
 * 查询
 *
 */
EstateAdd.search = function() {
    $('#curPage').val('1');
    EstateAdd.initList();
};

EstateAdd.selectFromOp = function() {
    var url = BASE_PATH + '/estate/selectFromOp';
    var title = '关联项目';

    var params = {"estatePosition":$("#estatePosition").val()};

    var dialogOptions = {
        width : 800,
        ok : function() {
            EstateAdd.dealVal();
        },
        okVal : '确定',
        cancel : true,
        cancelVal : '取消'
    };

    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

        dialog.position('50%', '25%');

        EstateAdd.dialog = dialog;

    }, dialogOptions);
};
EstateAdd.close = function() {
    EstateAdd.dialog.close();
};
EstateAdd.dealVal=function(){
    var selected = $("input[name='estateId']:checked");
    if(selected.length == 0){
        return ;
    }
    var opEstateId = selected.val();
    var opEstateNm = selected.data("estatenm");

    var checkUrl = BASE_PATH + '/estate/selectFromOpCheck';
    ajaxGet(checkUrl,{"opEstateId":opEstateId,"estId":$("#id").val()},function(data){
        $("#opEstateId").val(opEstateId);
        $("#opEstateNm").val(opEstateNm);
        $("#estateNm").val(opEstateNm);

        //国家
        var countryNm=selected.data("countrynm");
        if($("#estatePosition").val() == "1"){
            $("#countryNo").find("option[countryNm="+countryNm+"]").attr("selected",true);
        }

        //城市
        var cityNo = selected.data("cityno");
        if(cityNo != ""){
            $('.selectpicker').selectpicker('val',cityNo);
            //$("#realityCityNo").trigger("change");

            //此处需要同步加载
            var url = BASE_PATH + "/linkages/city/" + $("#realityCityNo").val();
            var params = {};

            $.ajax({
                url : url,
                data : params,
                async : false,
                type : 'GET',
                dataType : 'json',
                success : function(data) {
                    if (data && data.returnCode == '200') {
                        var result = "<option value=''>请选择行政区</option>";
                        $.each(data.returnValue, function(n, value) {
                            result += "<option value='" + value.districtNo + "'>"
                                + value.districtName + "</option>";
                        });
                        $("#districtNo").html('');
                        $("#districtNo").append(result);
                    }
                }
            });
        }
        //区域
        var districtId = selected.data("districtid");
        if(districtId != ""){
            $("#districtNo").find("option[value="+districtId+"]").attr("selected",true);
        }

        $("#address").val(selected.data("address"));
        //开盘日期
        var openTime = new Date();
        if(selected.data("opentime") != ""){
            openTime.setTime(selected.data("opentime"));
            $("#openTime").val(openTime.format("yyyy-MM"));
        }

        //交房日期
        var houseTransferTime = new Date();
        if(selected.data("housetransfertime") != ""){
            houseTransferTime=new Date(selected.data("housetransfertime"));
            $("#houseTransferTime").val(houseTransferTime.format("yyyy-MM"));
        }

        //合作期
        var cooperationDtStart = new Date();
        if(selected.data("cooperationdtstart") != ""){
            cooperationDtStart.setTime(selected.data("cooperationdtstart"));
            $("#cooperationDtStart").val(cooperationDtStart.format("yyyy-MM-dd"));
        }
        var cooperationDtEnd = new Date();
        if(selected.data("cooperationdtend") != ""){
            cooperationDtEnd.setTime(selected.data("cooperationdtend"));
            $("#cooperationDtEnd").val(cooperationDtEnd.format("yyyy-MM-dd"));
        }

        //总价段
        $("#salePriceUnitMin").val(selected.data("salepriceunitmin"));
        $("#salePriceUnitMax").val(selected.data("salepriceunitmax"));
        //标签
        var markHtml = '<input type="text" class="form-control w125" name="mark[0]" placeholder="" maxlength="4" value="{markVal}">'
            + '<span class="btn btn-link btn-add-input-tag" onclick="delMark(this)"> 删除</span>';
        $.each(selected.data("mark").split(";"),function(i,n){
            if(n == ""){
                return false;
            }
            if(i>1){
                var tempHtml = markHtml.replace(/mark\[0\]/g, "mark[" + i + "]").replace("{markVal}",n);
                $("#mark").parent().append(tempHtml);
            }else{
                $("#mark").val(n);
            }
        });
        //预售许可
        $("#preSalePermitKbn"+selected.data("presalepermitkbn")).attr("checked",true);
        //项目简介
        $("#projectDescription").val(selected.data("projectdescription"));

		/*//产权年限
		 $.each(selected.data("ownyearkbnstr").split(";"),function(i,n){
		 if(n == ""){
		 return false;
		 }
		 $("input[ownYearNm='"+n+"']").attr("checked",true);
		 });
		 //装修情况，无法处理
		 $("#decorationKbnCB").val(selected.data("decorationkbnstr"));
		 //物业类型
		 $("#typeKbnCB").val(selected.data("typekbnstr"));*/

        $("#houseCnt").val(selected.data("housecnt"));
        $("#parkCnt").val(selected.data("parkcnt"));
        $("#mgtCompany").val(selected.data("mgtcompany"));
        $("#rateFAR").val(selected.data("ratefar"));
        $("#rateGreen").val(selected.data("rategreen"));
        $("#mgtPrice").val(selected.data("mgtprice"));
        $("#"+selected.data("salesstatus")).attr("checked",true);

        //合作方
        $("#"+selected.data("partner")).attr("checked",true);
        $("#partnerNm").val(selected.data("partnernm"));
        $("#partnerContractNm").val(selected.data("partnercontractnm"));
        $("#partnerContractTel").val(selected.data("partnercontracttel"));
        //开发商
        $("#devCompany").val(selected.data("devcompany"));
        $("#developerNameBigNo").val(selected.data("devcompany"));
        //户型
        $("#countF").val(selected.data("countf"));
        $("#countT").val(selected.data("countt"));
        $("#countW").val(selected.data("countw"));
        $("#buildSquare").val(selected.data("buildsquare"));
        $("#"+selected.data("directionkbn")).attr("checked",true);
    },function(data){
        $("#opEstateId").val("");
        $("#opEstateNm").val("");
        $("#estateNm").val("");
        Dialog.alertError(data.returnMsg);
        systemLoaded();
    })
};

/**
 * 选中修改楼盘名称
 * @param obj
 */
function changeEstateNm(obj){
    if(obj.checked)
    {
        $("#changeName").show();
    }else{
        $("#changeName").hide();
        $("#newName").val("");
    }
}
/**
 * 选中修改项目地址
 * @param obj
 */
function changeAddress(obj){
    if(obj.checked)
    {
        $("#changeAddress").show();
        $("#realityCityNo").change(
            function() {
                var realityCityNo = $("#realityCityNo").val();
                var realityCityNm = $("#realityCityNo").find("option:selected").text();
                $("#newCityNm").val(realityCityNm);
                $("#newDistrictNm").val('');
                if("请选择城市"==realityCityNm){
                    $("#districtNo").html('');
                    $("#newCityNm").val('');
                    $("#newDistrictNm").val('');
                    return false;
                }
                var url = BASE_PATH + "/linkages/city/" + realityCityNo;
                var params = {};

                ajaxGet(url, params, function(data) {
                    var result = "<option value=''>请选择区域</option>";
                    $.each(data.returnValue, function(n, value) {
                        result += "<option value='" + value.districtNo + "'>" + value.districtName + "</option>";
                    });
                    $("#districtNo").html('');
                    $("#districtNo").append(result);
                }, function() {
                });
            });
        $("#districtNo").change(
            function() {
                var districtNm = $("#districtNo").find("option:selected").text();
                $("#newDistrictNm").val(districtNm);
                if("请选择区域" == districtNm){
                    $("#newDistrictNm").val('');
                }
            });
    }else{
        $("#changeAddress").hide();
        $("#newAddress").val("");
    }
}
/**
 * 选中修改销售状态
 * @param obj
 */
function changeSalesStatus(obj){
    if(obj.checked)
    {
        $("#changeSalesStatus").show();
    }else{
        $("#changeSalesStatus").hide();
    }
}
/**
 * 选中修改合作模式
 * @param obj
 */
function changeBusinessMode(obj){
	if(obj.checked)
	{
		$("#changeBusinessMode").show();
		$("#newBusinessMode").change(
			function () {
				var newBusinessModeStr = $("#newBusinessMode").find("option:selected").text();
				$("#newBusinessModeStr").val(newBusinessModeStr);
				if ("请选择业务模式" == newBusinessModeStr){
					$("#newBusinessModeStr").val('');
				}
			}
		);
	}else{
		$("#changeBusinessMode").hide();
	}
}
/**
 * 选中修改合作方名称
 * @param obj
 */
function changePartnerNm(obj){
    if(obj.checked)
    {
        $("#changePartner").show();
    }else{
        $("#changePartner").hide();
        $("#newPartnerNm").val("");
    }
}
/**
 * 选中修改开发商名称
 * @param obj
 */
function changeDevCompany(obj){
    if(obj.checked)
    {
        $("#changeDeveloper").show();
    }else{
        $("#changeDeveloper").hide();
        $("#newDevCompany").val("");
    }
}

function submitOA(updateId){
	var url = BASE_PATH + '/estate/update/'+updateId;
	var params = $("#estateEditForm").serialize();
	systemLoading("", true);
	httpPut(url, params, function(data) {
		if(data.returnCode == 200){
			location.href = BASE_PATH + "/estate";
		}else{
			Dialog.alertError(data.returnMsg);
		}
	}, function(data) {
		Dialog.alertError(data.returnMsg);
		systemLoaded();
	});
}
/**
 * 选择考核主体
 */
EstateType.chooseAccountProject=function(){ 
	var accountProjectMapping = $("#lnkAccountProjectCode option:selected");
	//考核主体
	var accountProjectCode = accountProjectMapping.val();
	var lnkAccountProject = accountProjectMapping.attr('data-lnkAccountProject');
	if (accountProjectCode==null || accountProjectCode == "" || accountProjectCode == undefined) {
		$("#warning-noCity").html("请选择核算主体！");
		return false;
	}
	EstateType.dialog.close();
	$("#lnkAccountProjectCode").val(accountProjectCode);
	$("#lnkAccountProject").val(lnkAccountProject);
	submitOA(updateId);
};
function isNullOrEmpty(obj){
	if(obj == null || obj == "" || obj == undefined){
		return false;
	}else{
		return true;
	}
}