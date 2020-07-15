<%@ page contentType="text/html;charset=UTF-8"
	trimDirectiveWhitespaces="true" language="java"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>客户信息调整</title>
<%@include file="../../common/common.jsp"%>
<style type="text/css">
.lable-left {
	margin-left: 70px;
}

.labelWidth {
	width: 70px!important;
}
.itemClass {
	width: 520px!important;
	margin-left: 20px;
}
</style>
</head>
<script type="application/javascript">

</script>
<body>
	<div>
		<div class="layui-card">
		<div class="layui-card-body">
		<div class="layui-form myForm" style="margin-top: 20px;">
			<input type="hidden" name="reportId" id="reportId" value="${reportId}">
			<input type="hidden" name="relateId" id="relateId" value="${relateId}">
			<input type="hidden" id="oldCustomerNm" name="oldCustomerNm" value="${customerNm}">
			<input type="hidden" id="oldCustomerTel" name="oldCustomerTel" value="${customerTel}" >
			<input type="hidden" id="oldCustomerNmTwo" name="oldCustomerNmTwo" value="${customerNmTwo}" >
			<input type="hidden" id="oldCustomerTelTwo" name="oldCustomerTelTwo" value="${customerTelTwo}" >
			
			<input type="hidden" name="userCode" id="userCode" value="${userCode}" >
			<input type="hidden" name="userName" id="userName" value="${userName}" >
			<input type="hidden" name="userIdCreate" id="userIdCreate" value="${userIdCreate}" >
			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 20px;">
				<legend>变更前客户信息</legend>
			</fieldset>
			<div class="layui-row">
				<div class="layui-col-xs2 lable-left">客户：</div>
				<div class="layui-col-xs3 lable-right">${customerNm}</div>
				<div class="layui-col-xs2 lable-left"></div>
				<div class="layui-col-xs4 lable-right">${customerTel}</div>
			</div>
			<div class="layui-row" style="padding-top: 20px;">
				<div class="layui-col-xs2 lable-left">客户：</div>
				<div class="layui-col-xs3 lable-right">${customerNmTwo}</div>
				<div class="layui-col-xs2 lable-left"></div>
				<div class="layui-col-xs4 lable-right">${customerTelTwo}</div>
			</div>
			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 20px;">
				<legend>变更后客户信息</legend>
			</fieldset>
			<div class="layui-form-item itemClass" style="margin-top: 50px;">
				<div class="layui-inline">
					<label class="layui-form-label labelWidth"><span class="redSpan">*</span>客户</label>
					<div class="layui-input-inline" style="width:150px!important;">
						<input type="text" id="newCustomerNm" name="address"
							lay-verify="required" placeholder="请输入" autocomplete="off"
							class="layui-input">
					</div>
					<div class="layui-input-inline" style="width:150px!important;margin-left: 30px;">
						<input type="text" id="newCustomerTel" name="address"
							lay-verify="required" placeholder="请输入11位电话号码" maxlength="11" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-inline" style="margin-top: 30px;">
					<label class="layui-form-label labelWidth">客户</label>
					<div class="layui-input-inline" style="width:150px!important;">
						<input type="text" id="newCustomerNmTwo" name="address"
							lay-verify="required" placeholder="请输入" autocomplete="off"
							class="layui-input">
					</div>
					<div class="layui-input-inline" style="width:150px!important;margin-left: 30px;margin-bottom: 22px;">
						<input type="text" id="newCustomerTelTwo" name="address"
							lay-verify="required" placeholder="请输入11位电话号码" maxlength="11" autocomplete="off"
							class="layui-input">
					</div>
				</div>
			</div>
		</div>
		</div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/meta/pmls/js/scene/sceneTrade/openDialogEditCustomer.js?v=${vs}"></script>
</body>
</html>
