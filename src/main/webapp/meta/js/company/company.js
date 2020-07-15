/** ************************公共部分***************************** */
$(function() {
	
	// 初始化加载 城市、行政区、板块 三级联动
	linkage();
	
	$("#edit").click(function(){	
		var cId = $("#companyId").val();
		var url = BASE_PATH + "/companys/ed/";	
		var checkUrl = BASE_PATH + "/companys/checkCompany";
		var params = {
			companyId:cId
		};
		
		ajaxGet(checkUrl, params, function(data) {
			params.flag = data.returnValue;
			var dialogOptions = {
					ok : function() {
						// 确定
						var reback = companyEdit(cId);
						return reback;			
					},
					okVal : '保存',
					cancel : true,
					cancelVal : '返回',
					width:600,
					height:100
			};
			// 跳转至修改公司信息
			Dialog.ajaxOpenDialog(url, params, "修改公司信息", function(dialog, resData) {
				Company.dialog = dialog;
			}, dialogOptions);
				
			}, function(data) {
				Dialog.alertError(data.returnMsg);
				systemLoaded();
			});
	});

	//运营维护
    $("#operationEdit").click(function(){
        var cId = $("#companyId").val();
        var url = BASE_PATH + "/companys/toOperationEdit/";
        var params = {
            companyId:cId
        };

        var dialogOptions = {
            ok : function() {
                // 确定
                var reback = companyEdit(cId,1);
                return reback;
            },
            okVal : '保存',
            cancel : true,
            cancelVal : '取消',
            width:600,
            height:100
        };
        // 跳转至修改公司信息
        Dialog.ajaxOpenDialog(url, params, "运营维护公司信息", function(dialog, resData) {
            Company.dialog = dialog;
        }, dialogOptions);
    });
});

/** **************************Company js*************************** */
Company = function() {
};

/**
 * 创建
 */
var checkSubmitFlg = false;
Company.add = function() {

	var url = BASE_PATH + '/companys';
	
	// 校验联系电话是否是11位数字
	var phone = $("#contactNumber").val();
	if(phone == "") {
		Dialog.alertInfo("联系电话不能为空!");
		return;
	}
	if(!checkPhoneNumber(phone))
	{
		Dialog.alertInfo("联系电话格式错误!");
		return;
	}
	//给公司地址去除空格
	$("#address").val($("#address").val().replace(/[ ]/g,""));
	// 设置关联门店
	setRelateStore();

	if ($("#cityNo").val() == "" || $("#districtNo").val() == "") {
		Dialog.alertInfo("请选择公司地址的城市区域");
		return false;
	}
	
	// 校验输入信息
	if (Validator.validForm("companyAddForm") && checkRelateStore()) {
		var flag = checkRepeatSubmit();
		if(flag){
			return false;
		}
		systemLoading("", true);
		httpPost('companyAddForm', url, function(data) {

			location.href = BASE_PATH + "/companys";

		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});
		systemLoaded();
	} else {
		systemLoaded();
	}
};

/**
 * 修改
 */
Company.update = function() {

	var companyId = $("#companyId").val();

	var url = BASE_PATH + '/companys/' + companyId;
	
	// 校验联系电话是否是11位数字
	var phone = $("#contactNumber").val();
	if(phone == "") {
		Dialog.alertInfo("联系电话不能为空!");
		return;
	}
	if(!checkPhoneNumber(phone))
	{
		Dialog.alertInfo("联系电话格式错误!");
		return;
	}
	//给公司地址去除空格
	$("#address").val($("#address").val().replace(/[ ]/g,""));
	// 设置关联门店
	setRelateStore();

	if ($("#cityNo").val() == "" || $("#districtNo").val() == "") {
		Dialog.alertInfo("请选择公司地址的城市区域");
		return false;
	}
	
	var params = $("#companyEditForm").serialize();

	// 校验输入信息
	if (Validator.validForm("companyEditForm") && checkRelateStore()) {

		systemLoading("", true);
		
		httpPut(url, params, function(data) {

			location.href = BASE_PATH + "/companys";

		}, function(data) {
			Dialog.alertError(data.returnMsg);
			systemLoaded();
		});
		systemLoaded();
	} else {
		systemLoaded();
	}

};

/**
 * 删除
 */
Company.remove = function(companyId) {

	var result = "是否确定删除此联系人?</h3>";

	var params = {
		'id' : companyId
	};

	Dialog.confirm(result, function() {

		httpDelete(BASE_PATH + "/companys/" + companyId, params, function(data) {

			// 刷新页面
			Contacts.search();

		}, function(data) {
			Dialog.alertError(data.returnMsg);
		});
	});
};

/**
 * 返回、取消
 */
Company.back = function() {

	location.href = BASE_PATH + "/companys";

};

Company.close = function(){
	Company.dialog.close();
}

function checkRepeatSubmit() {
    if (!checkSubmitFlg) {
    	checkSubmitFlg = true;
        return false;//第一次提交
    } else {
        return true;//重复提交
    }
}

function companyEdit(companyId,flag)
{
	var url = BASE_PATH + '/companys/ec';
	
	//给公司地址去除空格
	$("#newAddress").val($("#newAddress").val().replace(/[ ]/g,""));

    if(flag != undefined){
        $("#companyEditForm").append("<input type='hidden' id='flag' name='flag' value='1'>")
    }

	var params = $("#companyEditForm").serialize();

	systemLoading("", true);
	httpPut(url, params, function(data) {
		Dialog.alertInfoToUrl("修改成功!",BASE_PATH + "/companys/" + companyId,true);
	}, function(data) {
		Dialog.alertError(data.returnMsg);
        $("#flag").remove();
		systemLoaded();
		return false;
	});
	
	systemLoaded();	
	return true;
}
