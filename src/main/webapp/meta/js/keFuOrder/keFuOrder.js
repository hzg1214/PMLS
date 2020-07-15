$(function(){
	getCityList();
	//初始化多选框
	$('.multi-select').multiSelect({check: function($instance){
		//初始化
	}});
	$(".lockHandle2").removeAttr("style");
	//一级分类与二级分类联动
	$("#categoryOne").change(function () {
		var url = BASE_PATH +  "/keFuOrder/getCategoryTwo";
		var categoryOne= $("#categoryOne").val();
		if("" !=categoryOne){
			var params = {categoryOne:categoryOne};
			ajaxGet(url, params, function(data) {
				var result = "<option value=''>请选择</option>";
				$.each(data.returnValue, function(n, value) {
					result += "<option value='" + value.dicCode + "'>"
					+ value.dicValue + "</option>";
				});
				$("#categoryTwo").html('');
				$("#categoryTwo").append(result);
			}, function() {
			});
		}else {
			var result = "<option value=''>请选择</option>";
			$("#categoryTwo").html('');
			$("#categoryTwo").append(result);
		}
	});
	var options = {
		"url":BASE_PATH + '/file/uploadCommonFile',
		"isDeleteImage":false,//删除时校验checkEditImage
		"isAddImage":true,   //添加时校验checkEditImage
		"isCommonFile":true  //公共上传文件
	};
	photoUploader(options,null,null,null);
});
RelateUtil = function () {
    dialog: null;
};
//关闭，取消
RelateUtil.close = function() {
	RelateUtil.dialog.close();
};
/**
 * 新增变更--保存事件
 * 
 */
function doSave(type) {
	$("input[type='radio']").removeAttr("disabled");
	$("input[type='checkbox']").removeAttr("disabled");
	if(!Validator.validForm("keFuOrderAddForm")){
	    return false;
	}
	var cityNo = $('#cityNo').val();
	if (cityNo == null || cityNo == "" || cityNo == undefined) {
		Dialog.alertInfo("请选择归属城市！");
		return false;
	}
	//产品类型
	var selectConsultProduct = $('#selectConsultProduct').val();
	if (selectConsultProduct == null || selectConsultProduct == "" || selectConsultProduct == undefined) {
		Dialog.alertInfo("请选择咨询产品名称！");
		return false;
	}
	var questionLevel= $('input:radio[name="questionLevel"]:checked').val();
	if (questionLevel == null || questionLevel == "" || questionLevel == undefined) {
		Dialog.alertInfo("请选择工单优先级！");
		return false;
	}
	var operatorValue= $('#operatorValue').val();
	if (operatorValue == null || operatorValue == "" || operatorValue == undefined) {
		Dialog.alertInfo("请选择经办人！");
		return false;
	}
	//门店
	var storeId = $("#storeInfo").find("option:selected").val();
	$("#storeId").val(storeId);
	/*var consultProduct = selectConsultProduct.replaceAll(";", ",");
	$("#consultProduct").val(consultProduct);
	$("#selectConsultProduct").val("");
	$("#selectConsultProductNm").val("");*/
	if("1"== type){
		$("#dealStatus").val("24201");
	}else if("2"== type){
		$("#dealStatus").val("24202");
	}else if("3"== type){
		$("#dealStatus").val("24203");
	}
	handlerFileInfo();
	var url = BASE_PATH + '/keFuOrder/saveKeFuOrder';
	systemLoading("", true);
	httpPost('keFuOrderAddForm', url, function(data) {
		if(data.returnCode == 200){
			Dialog.alertSuccess();
			location.href = BASE_PATH + "/keFuOrder";
		}else{
			$("input[type='radio']").attr("disabled","disabled");
			$("input[type='checkbox']").attr("disabled","disabled");
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		}
	}, function(data) {
		$("input[type='radio']").attr("disabled","disabled");
		$("input[type='checkbox']").attr("disabled","disabled");
		Dialog.alertError(data.returnMsg);
		systemLoaded();
	});
}


function checkEditImage(files){
	var bol = true;
	var fileSize = 0;
	for( var i = 0 ; i < files.length ; i++ ){  
		fileSize = files[i].size;
		var photoExt = files[i].name.substr(files[i].name.lastIndexOf(".")).toLowerCase();// 获得文件后缀名
		if (photoExt != '.jpg' && photoExt != '.png') {
			Dialog.alertInfo("请上传后缀名为jpg、png的文件!");
			systemLoaded();
			bol = false;
			return bol;
		}
		if (fileSize > 5000*1024) {
			Dialog.alertInfo("所选文件不能大于5M！");
			self.value = null;
			systemLoaded();
			bol = false;
			return bol;
		}
	}
	
	
	return bol;
}
   
//处理文件信息
function handlerFileInfo(){
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
function relateCompanyInfo(type) {
	//var cityNo = $("#cityNo option:selected").val();
	var cityNo = $("#cityNo").val();
	if (cityNo == null || cityNo == "" || cityNo == undefined) {
		Dialog.alertInfo("请选择归属城市！");
		return false;
	}
	
	var url = BASE_PATH + '/relate/company';
	var title = '公司信息';
	var params = {
			type : type,
			keFuOrderCity:cityNo
	};
	
	var dialogOptions = {
			
			width : 800,
			ok : function() {
				// 确定
				var reback= RelateUtil.relateOrderCompany();
				return reback;
			},
			okVal : '确定',
			cancel : true,
			cancelVal : '取消'
	};
	
	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
		dialog.position('50%', '25%');
		RelateUtil.dialog = dialog;
	}, dialogOptions);
	
}
RelateUtil.relateOrderCompany = function() {
	var selectCompany = $(':radio[name=selectCompanyId]:checked');
	var companyId = selectCompany.val();
	if(isBlank(companyId)){
        $("#errorMsg").text("请先选择一个公司!");
        $("#errorMsg").css("visibility","initial");
        return false;
    }
	var checkUrl = BASE_PATH+ "/store/qr";
	$.ajax({
		url : checkUrl,
		type : 'GET',
		data:$.param({
			companyId : companyId
		}),
		async:false,
		dataType : 'json',
		success : function(data) {
			if (data && data.returnCode == '200') {
                handleKeFuOrder(selectCompany,data);
                return ;
            }
		}
	});
};
function handleKeFuOrder(selectCompany, resultData) {
	/** 判断返回是否成功 判断returnCode 是否有值 有的话 说明返回报错 没有就说明返回成功 */
	if (resultData.returnCode != null && resultData.returnCode != undefined && resultData.returnCode != "" && resultData.returnCode != 200) {
		Dialog.alertInfo(resultData.returnMsg,false,true);
		return ;
	}
	/** 返回的值 即Store对象的List */
	var returnValue = resultData.returnValue;//返回的值

	/**
	 * 是否已创建
	 */
	var companyNo = selectCompany.attr('data-companyNo');
	//公司名称
	var companyName = $.trim(selectCompany.attr('data-companyName'));
	//城市
	var companyCityNm = selectCompany.attr('data-companyCityNm');
	var companyCityNo = selectCompany.attr('data-companyCityNo');
	//区域
	var companyDistrictNo = selectCompany.attr('data-companyDistrictNo');
	var companyDistrictNm = selectCompany.attr('data-companyDistrictNm');
	//详情地址
	var companyAddress = selectCompany.attr('data-companyAddress');
	//统一社会信用代码
	var businessLicenseNo =  selectCompany.attr('data-businessLicenseNo');
	//法人
	var companyLealPerson = selectCompany.attr('data-companyLealPerson');
	//联系电话
	var companyContactTel = selectCompany.attr('data-companyContactTel');
	if(companyName == "" || businessLicenseNo== ""  || companyLealPerson == "" || companyContactTel == "" ||
			companyCityNm == "" || companyDistrictNm == "" || companyAddress == "" || companyNo == ""){
		Dialog.alertInfoToUrl2("选择的经纪公司信息不全，需中心总权限在公司详情中进行补充！", BASE_PATH + '/keFuOrder/toAddKeFuOrder' ,true);
		return ;
	}
	if(businessLicenseNo.length!=15 && businessLicenseNo.length!=18){
		Dialog.alertInfoToUrl2("选择的经纪公司统一社会信用代码不准确，需中心总权限在公司详情中进行修改！", BASE_PATH + '/keFuOrder/toAddKeFuOrder',true);
		return ;
	}
	$("#companyId").val(selectCompany.val());
	$("#companyInfo").val(companyName);
	$("#companyNo1").val(companyNo);
    if (returnValue && returnValue.length!=0) {
        var dataLength =  returnValue.length;
		/** 异步加载门店列表 */
		var html = "<option value=''>请选择...</option>";
		$.each(returnValue,function(i,n){
			if(dataLength > 1) {
				html += "<option value='" + n.storeId + "'>"
				+ n.name +"("+n.storeNo+" "+n.address+")" + "</option>";
			}
			if(dataLength > 0 && dataLength < 2) {
				html += "<option value='" + n.storeId + "' selected>"
				+ n.name +" ("+n.storeNo+" "+n.address+")" + "</option>";
				$("#storeId").val(n.storeId);
			}
			
		});
		$("#storeInfo").empty().append(html);
	}
}
LinkAchieveChange2 = function () {
    dialog: null;
};
//关闭，取消
LinkAchieveChange2.close = function() {
	LinkAchieveChange2.dialog.close();
};
function toChooseOperator(){
    var url = BASE_PATH + '/keFuOrder/toChooseOperator';
    var title = '选择经办人';
    // var cityNo = $("#cityNo").val();
	// if (cityNo == null || cityNo == "" || cityNo == undefined) {
	// 	Dialog.alertInfo("请选择归属城市！");
	// 	return false;
	// }
    var params = {};
    var dialogOptions = {
        width : 600,
        ok : function(data) {
        	var selectRadio = $("input.selectCenterId:checked");
            var selectUser = selectRadio.val();
            if(isBlank(selectUser)){
                $("#errorMsg").text("请先选择一个经办人!");
                $("#errorMsg").css("visibility","initial");
                return false;
            }
            var userCode = $(':radio[name="selectUser"]:checked').attr("data-userCode");
            var userName = $(':radio[name="selectUser"]:checked').attr("data-userName");
            $("#userCode").val(userCode);
            $("#operatorValue").val(userCode);
            $("#operatorName").val(userName);
        },
        okVal : '确定',
        cancel : true,
        cancelVal : '取消'
    };

    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
        dialog.position('50%', '25%');
        LinkAchieveChange2.dialog = dialog;
    }, dialogOptions);

}

//确定后跳转到url,解决双层弹框选择
Dialog.alertInfoToUrl2 = function(ctt, url,isSecond) {
	if(isSecond){
		$(".lockHandle2").show();
		$(".lockHandle2").css({"position":"fixed","left":"0px","top":"0px","width":"100%",
			"height":"100%","overflow":"hidden","z-index":"1981","background":"rgb(0, 0, 0)","opacity":"0.4"});
	}
	var infoDialogOp = {
		id : 'sysInfo',
		title : false,
		content : '<div class="sysAlert showSweetAlert"><h2>' + ctt
				+ '</h2></div>',
		lock : true,
		okVal : '确定',
		//ok : true
		ok : function() {
			location.href = url;
			return true;
		},
		modal: true
	};
	var dialog = $.dialog(infoDialogOp);

};
function getCityList(callbackCity, callbackDist){
	var url = BASE_PATH + "/commons/queryCityList";
	var orgCode = "1082";
	var params = {orgCode: orgCode};
	ajaxGet(url, params, function (data) {
		var list = data.returnValue;
		if (list != null && list.length > 0) {
			var result = "<option value=''>请选择</option>";
			$.each(data.returnValue, function(n, value) {
				result += "<option value='" + value.cityNo + "'>"
						+ value.cityName + "</option>";
			});
			$("#selectCity").html('');
			$("#selectCity").append(result);
			if($('.selectpicker').length > 0){
				$('.selectpicker').selectpicker('val', '');  
		        $('.selectpicker').selectpicker('refresh');
		        $('.selectpicker').selectpicker('render');
			}
			callbackCity ? callbackCity() : $.noop();
		}
	}, function () {
	});
	$("#selectCity").change(function() {
		var selectCityNm = $("#selectCity").find("option:selected").text();
		$("#selectCityNm").val(selectCityNm);
		$("#cityNo").val($("#selectCity").find("option:selected").val());
		$("#companyInfo").val("");
		$("#companyId").val("");
		$("#companyNo1").val("");
		$("#storeInfo").html('');
		$("#storeId").val("");
	});
}


