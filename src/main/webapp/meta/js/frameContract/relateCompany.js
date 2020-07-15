/** ************************公共部分***************************** */
$(function() {

	// 初始化查询
	FrameRelateCompany.initList();
});
/** ***************************************************** */
FrameRelateCompany = function() {
	dialog: null;
};
// 关闭，取消
FrameRelateCompany.close = function() {
	FrameRelateCompany.dialog.close();
};

/**
 * 初始化查询
 */
FrameRelateCompany.initList = function() {

	httpGet('relateCompanyForm', 'LoadCxt', BASE_PATH + '/frameContract/getFrameContractCompanyList', function() {

		pageInfo("relateCompanyForm", function() {
			FrameRelateCompany.initList();
		});

	});
};

/**
 * 查询
 * 
 */
FrameRelateCompany.search = function() {
	$('#curPage').val('1');
	FrameRelateCompany.initList();
};

/**
 * 关联公司
 */
function relateCompanyList() {
	
	var agreementTypeVal= $('input:radio[name="agreementType"]:checked').val();
	if(agreementTypeVal == null){
		 Dialog.alertError("请选择框架协议类型！");
		 return false;
	}
	var url = BASE_PATH + '/frameContract/toChooseFrameContractCompanyList';
	var title = '请选择公司';
	var params = {
			agreementType:agreementTypeVal
	};
	var dialogOptions = {
		width : 800,
		ok : function() {
			var reback = FrameRelateCompany.relateCompany();
			if(!reback){
                FrameRelateCompany.close();
              /*  Dialog.alertError("选择的经纪公司信息不全，需中心总权限在公司详情中进行补充");*/
                systemLoading();
			}
			return reback;
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};

	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {
		dialog.position('50%', '25%');
		FrameRelateCompany.dialog = dialog;
	}, dialogOptions);

}

/** 选择后 点击确认触发事件 */
FrameRelateCompany.relateCompany = function() {
	var selectCompany	=	$(':radio[name="selectCompanyId"]:checked');
	//公司编号与Id
	var selectCompanyId = selectCompany.val();
	    if(isBlank(selectCompanyId)){
	        $("#errorMsg").text("请先选择一个公司!");
	        $("#errorMsg").css("visibility","initial");
	        return false;
	    }
	var companyNo = selectCompany.attr('data-companyNo');
	//公司名称
	var companyName = $.trim(selectCompany.attr('data-companyName'));
	//城市
	var companyCityNm = selectCompany.attr('data-companyCityNm');
	var companyCityNo = selectCompany.attr('data-companyCityNo');
	//业绩归属城市编号
	var companyAcCityNo = selectCompany.attr('data-companyAcCityNo');
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
	if(companyName == "" || businessLicenseNo== "" ||  companyAcCityNo == "" ||
			companyLealPerson == "" || companyContactTel == "" ||
			companyCityNm == "" || companyDistrictNm == "" || companyAddress == "" || companyNo == ""){
        Dialog.alertInfoToUrl2("选择的经纪公司信息不全，需中心总权限在公司详情中进行补充！", BASE_PATH + '/frameContract/toInsertFrameContract',true);
		return false;
	}
	if(businessLicenseNo.length!=15 && businessLicenseNo.length!=18){
		Dialog.alertInfoToUrl2("选择的经纪公司统一社会信用代码不准确，需中心总权限在公司详情中进行修改！", BASE_PATH + '/frameContract/toInsertFrameContract',true);
        return false;
	}
	/** 赋值 */
	$("#companyId").val(selectCompany.val());
	$("#frameCompanyNo").val(companyNo);
	$("#companyNo").val(companyNo);
	//公司名称
	$("#comapanyName").val(companyName);
	//城市名称
	$("#cityNm").val(companyCityNm);
	//城市编号
	$("#companyCityNo").val(companyCityNo);
	//业绩归属城市编号
	$("#companyAcCityNo").val(companyAcCityNo);
	//区域编号
	$("#companyDistrictNo").val(companyDistrictNo);
	$("#districtNm").val(companyDistrictNm);
	//详情地址
	$("#address").val(companyAddress);
	//统一社会信用代码
	$("#businessLicenseNo").val(businessLicenseNo);
	//法人
	$("#lealPerson").val(companyLealPerson);
	//联系电话
	$("#companyContactTel").val(companyContactTel);
	$.ajax({
		url:BASE_PATH+"/frameContract/getOldFtBankInfo",
		type:"get",
		data:$.param({
			companyNo:companyNo
		}),
		success:function(data){
			if(data.length > 0 ){
				data = JSON.parse(data);
					//开户名
					$("#accountNm").val(data.accountNm);
					//开户省市
					$("#accountProvinceNo").val(data.accountProvinceNo);
					$("#accountProvinceNm").val(data.accountProvinceNm);
					var accountCityNo = data.accountCityNo;
					var url = BASE_PATH +  "/cityCascade/city";
			        var params = {provinceNo:data.accountProvinceNo};
			        ajaxGet(url, params, function(data1) {
			            var result = "<option value=''>请选择城市</option>";
			            $.each(data1.returnValue, function(n, value) {
			            	if(value.cityNo == accountCityNo) {
			            		result += "<option value='" + value.cityNo + "' selected>"
			                    + value.cityName + "</option>";
			            	}else {
			            		result += "<option value='" + value.cityNo + "'>"
			                    + value.cityName + "</option>";
			            	}
			            });
			            $("#accountCityNo").html('');
			            $("#accountCityNo").append(result);
			        }, function() {
			        });
					$("#accountCityNm").val(data.accountCityNm);
					//$("#accountCityNo").val(data.accountCityNo);
					//开户行
					$("#bankAccountNm").val(data.bankAccountNm);
					//银行账号
					$("#bankAccount").val(data.bankAccount);
			}
			
		}
	});
	return true;
};
// 关闭弹窗
FrameRelateCompany.close = function() {
	FrameRelateCompany.dialog.close();
};
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
