$(function() {
	cityCenter();
});

/**
 * 二级联动
 */
function cityCenter(callbackCity, callbackDist){
	var url = BASE_PATH + "/qtReport/getBasCitySettingList";
	var params = {};
	ajaxGet(url, params, function(data) {
		var result = "<option value=''>请选择城市</option>";
		$.each(data.returnValue, function(n, value) {
			result += "<option value='" + value.cityNo + "'>"
				+ value.cityName + "</option>";
		});
		$("#accCity").html('');
		$("#accCity").append(result);

		if($('.selectpicker').length > 0){
			$('.selectpicker').selectpicker('val', '');
			$('.selectpicker').selectpicker('refresh');
			$('.selectpicker').selectpicker('render');
		}

		callbackCity ? callbackCity() : $.noop();
	}, function() {
	});
	$("#accCity").change(

		function() {
			var accCity = $("#accCity").val();
			var realityCityNm = $("#accCity").find("option:selected").text();
			if("请选择城市"==realityCityNm){
				$("#centerId").html('');
				return false;
			}
			var url = BASE_PATH + "/qtReport/getAchivAchievementLevelSettingList/" + accCity;
			var params = {};

			ajaxGet(url, params, function(data) {
				var result = "<option value=''>请选择业绩归属中心</option>";
				$.each(data.returnValue, function(n, value) {
					result += "<option value='" + value.centerGroupId + "'>"
						+ value.centerGroupName + "</option>";
				});
				$("#centerId").html('');
				$("#centerId").append(result);
				callbackDist ? callbackDist() : $.noop();
			}, function() {
			});
		});
}





function reportAdd() {
	systemLoading("", true);

	var estateId = $("#estateId").val();
	var estateNm = $("#estateNm").val();
	var partnerNm = $("#partnerNm").val();

	var accCityNo = $("#accCity").find("option:selected").val();
	var centerGroupId = $("#centerId").find("option:selected").val();
	var centerGroupName = $("#centerId").find("option:selected").text();


	var srType = $("#srType").val();
	var srAmount = $("#srAmount").val();
	var reportDate = $("#reportDate").val();
	var memo = $("#memo").val();


	if (isNullEmpty(accCityNo)) {
		Dialog.alertError("请选择业绩归属城市！");
		systemLoaded();
		return false;
	}

	if (isNullEmpty(centerGroupId)) {
		Dialog.alertError("请选择业绩归属中心！");
		systemLoaded();
		return false;
	}

	if (isNullEmpty(srType)) {
		Dialog.alertError("请选择收入类型！");
		systemLoaded();
		return false;
	}

	if (isNullEmpty(srAmount)) {
		Dialog.alertError("请输入收入金额！");
		systemLoaded();
		return false;
	}

	if (isNullEmpty(reportDate)) {
		Dialog.alertError("请选择报备日期！");
		systemLoaded();
		return false;
	}

	var lnkQtReport = {};
	lnkQtReport.estateId = estateId;
	lnkQtReport.estateNm = estateNm;
	lnkQtReport.partnerNm = partnerNm;
	lnkQtReport.accCityNo = accCityNo;
	lnkQtReport.centerId = centerGroupId;
	lnkQtReport.centerName = centerGroupName;
	lnkQtReport.srType = srType;
	lnkQtReport.srAmount = srAmount;
	lnkQtReport.reportDate = reportDate;
	lnkQtReport.memo = memo;

	var url = BASE_PATH + '/qtReport/createReport';
	// 校验输入信息
	if (Validator.validForm("reportAddForm")) {
		$("#reportPcAddBtn").hide();

		$.ajax({
			type: "POST",
			url: url,
			contentType: 'application/json;charset=utf-8', //设置请求头信息
			data: JSON.stringify(lnkQtReport),
			dataType: "json",
			success: function (data) {
				systemLoaded();
				if (data.returnCode == 200 || data.returnCode == '200') {
					window.location.reload();
				} else {

				}
				$("#reportPcAddBtn").show();
			},
			error: function () {
				//报错提示操作失败
				systemLoaded();
				$("#reportPcAddBtn").show();
			}
		});
	}
	systemLoaded();
}

function isNullEmpty(obj){
	if(obj == null || obj == "" || obj == undefined){
		return true;
	}else{
		return false;
	}
}