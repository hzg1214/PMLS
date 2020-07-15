<%@ page contentType="text/html;charset=UTF-8"
	trimDirectiveWhitespaces="true" language="java"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>业绩调整</title>
<%@include file="../../common/common.jsp"%>
<style type="text/css">
.lable-left {
	margin-left: 100px;
}
.inputClass {
	background: rgb(221, 221, 221);
	min-height: 36px;
	     width: 250px;
}
.topDiv {
	padding-top: 20px;
    margin-top: -20px;
}
.textLeft {
	text-align: left;
}
</style>
</head>
<script type="application/javascript">
</script>
<body>
<div>
	<div class="layui-card">
	<div class="achievePage">
		<div class="layui-form myForm" style="margin-top: 5px;">
			<input type="hidden" name="reportId" id="reportId"
				value="${reportId}"> <input type="hidden" name="estateId"
				id="estateId" value="${estateId}"> <input type="hidden"
				name="companyId" id="companyId" value="${companyId}"> <input
				type="hidden" name="customerId" id="customerId"
				value="${customerId}"> <input type="hidden" name="relateId"
				id="relateId" value="${relateId}"> <input type="hidden"
				name="customerNm" id="customerNm" value="${customerNm}"> <input
				type="hidden" id="customerTel" name="customerTel"
				value="${customerTel}"> <input type="hidden"
				id="oldContactId" name="oldContactId" value="${oldContactId}">

			<input type="hidden" id="oldContactNm" name="oldContactNm"
				value="${oldContactNm}"> <input type="hidden"
				id="oldCenterGroupId" name="oldCenterGroupId"
				value="${oldCenterGroupId}"> <input type="hidden"
				id="oldCenterGroupName" name="oldCenterGroupName"
				value="${oldCenterGroupName}"> <input type="hidden"
				name="userCode" id="userCode" value="${userCode}"> <input
				type="hidden" name="userName" id="userName" value="${userName}">
			<input type="hidden" name="userIdCreate" id="userIdCreate"
				value="${userIdCreate}">
			<input type="hidden" name="oldFyCenterId" id="oldFyCenterId"
				value="${fyCenterId}">
			<input type="hidden" name="oldFyCenterName" id="oldFyCenterName"
				value="${fyCenterName}">
			<input type="hidden" name="htedition" id="htedition"
				value="">
			<input type="hidden" name="branchId" id="branchId"
				value="${branchId}">
				
			<input type="hidden" name="projectNo" id="projectNo"
				value="${reqMap.projectNo}">
			<input type="hidden" name="companyNo" id="companyNo"
				value="${reqMap.companyNo}">
				
			<input type="hidden" name="projectCityNos" id="projectCityNos"
				value="${projectCityNos}">
			<input type="hidden" id="auditDate" name="auditDate" value="${reqMap.auditDate}">
        	<input type="hidden" id="currDate" name="currDate" value="${reqMap.currDate}">
        	<input type="hidden" id="cooperFlag" name="cooperFlag" value="${reqMap.cooperFlag}">
        	<input type="hidden" id="dateFlag" name="dateFlag" value="${dateFlag}">
			<div class="topDiv">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>变更前业绩</legend>
			</fieldset>
			</div>
				<div class="layui-form-item" style="margin-left: 45px;">
					<label class="layui-form-label" style="width: 120px !important;">业绩归属人：</label>
					<div class="layui-input-inline">
						<label class="layui-form-label textLeft">${oldContactNm}</label>
					</div>
				</div>
				<div class="layui-form-item" style="margin-left: 45px;margin-top: -15px; margin-bottom: -5px;">
					<label class="layui-form-label" style="width: 120px !important;">业绩归属中心：</label>
					<div class="layui-input-inline">
						<label class="layui-form-label textLeft">${oldCenterGroupName}</label>
					</div>
				</div>
<!-- 				<div class="layui-form-item" style="margin-left: 45px;"> -->
<!-- 					<label class="layui-form-label" style="width: 120px !important;">房友中心：</label> -->
<!-- 					<div class="layui-input-inline"> -->
<%-- 						<label class="layui-form-label textLeft">${fyCenterName}</label> --%>
<!-- 					</div> -->
<!-- 				</div> -->

			<div style="padding-top: 20px;">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>变更后业绩</legend>
			</fieldset>
			</div>
			<div class="layui-form-item" id="isContract" style="margin-left: 45px;">
				<label class="layui-form-label" style="width: 120px !important;"><span
					class="redSpan">*</span>分销合同</label>
				<div class="layui-input-inline" >
					<input type="text" id="contract" name="contract"
						style="width: 320px;" lay-verify="required" readOnly="true" disabled="true"
						placeholder="请选择" autocomplete="off" class="layui-input" /> <input
						type="hidden" name="contract" id="contract" value="">
					<span class="fc-warning" id="errorMsg1"></span>
				</div>
				<button type="button" class="layui-btn" style="margin-left: 130px;"
					data-type="selContract">选择</button>
				<input type="hidden" name="contractNo" id="contractNo" value="">
				<input type="hidden" name="contractName" id="contractName" value="">
				<input type="hidden" name="htType" id="htType" value="">
				<input type="hidden" name="dateLifeStart" id="dateLifeStart" value="">
				<input type="hidden" name="dateLifeEnd" id="dateLifeEnd" value="">
				<input type="hidden" name="htedition" id="htedition" value="">
			</div>
			<div class="layui-form-item" style="margin-left: 45px;">
				<label class="layui-form-label" style="width: 120px !important;"><span
					class="redSpan">*</span>业绩归属人</label>
				<div class="layui-input-inline" >
					<input type="text" id="inputLinkUserName" name="inputLinkUserName"
						style="width: 320px;" lay-verify="required" readOnly="true" disabled="true"
						placeholder="请选择" autocomplete="off" class="layui-input" />
					<input type="hidden" name="linkUserName" id="linkUserName" value="">
					<input type="hidden" name="linkUserCode" id="linkUserCode" value="">
					<span class="fc-warning" id="errorMsg2"></span>
				</div>
				<button type="button" class="layui-btn" style="margin-left: 130px;"
					data-type="selUser">选择</button>
			</div>
			<div class="layui-form-item" style="margin-left: 45px;">
				<label class="layui-form-label" style="width: 120px !important;"><span
					class="redSpan">*</span>业绩归属中心</label>
				<div class="layui-input-inline">
					<input class="inputClass" placeholder=""  type="centerName" name="centerName" id="centerName"
					 style="border: 1px solid #DDD;background-color: #F5F5F5;color: #ACA899;width: 317px;" readonly="true" disabled="true" value="">
					<input type="hidden" name="newCenterName" id="newCenterName" value=""> 
					<input type="hidden" name="newCenterCode" id="newCenterCode" value="">
				</div>
			</div>
<!-- 			<div class="layui-form-item" id="isFyCenter" style="margin-left: 45px;"> -->
<!-- 				<label class="layui-form-label" style="width: 120px !important;"><span -->
<!-- 					class="redSpan">*</span>房友中心</label> -->
<!-- 				<div class="layui-input-inline" style="width: 320px !important;"> -->
<!-- 					<select id="fyCenterId" name="fyCenterId"  placeholder="请选择" -->
<!-- 						lay-verify="fyCenterId" lay-search="" > -->
<!-- 						<option value="">请选择</option> -->
<!-- 					</select>  -->
<!-- 					<input type="hidden" name="newFyCenterId" id="newFyCenterId" value="">  -->
<!-- 					<input type="hidden" name="newFyCenterName" id="newFyCenterName" value=""> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div class="layui-form-item" id="isFyCenter" style="margin-left: 45px;"> -->
<!-- 				<label class="layui-form-label" style="width: 120px !important;"><span -->
<!-- 					class="redSpan">*</span>房友中心</label> -->
<!-- 				<div class="layui-input-inline" > -->
<!-- 					<input type="text" id="fyCenterId" name="fyCenterId" -->
<!-- 						style="width: 320px;" lay-verify="required" readOnly="true" disabled="true"-->
<!-- 						placeholder="请选择" autocomplete="off" class="layui-input" /> -->
<!-- 					<input type="hidden" name="newFyCenterId" id="newFyCenterId" value=""> -->
<!-- 					<input type="hidden" name="newFyCenterName" id="newFyCenterName" value=""> -->
<!-- 				</div> -->
<!-- 				<button type="button" class="layui-btn" style="margin-left: 130px;" -->
<!-- 					data-type="selFyUser">选择</button> -->
<!-- 			</div> -->
			<div class="layui-form-item" style="margin-left: 45px;margin-bottom: 0px;">
				<label class="layui-form-label" style="width: 120px !important;"><span
					class="redSpan">*</span>变更原因</label>
				<div class="layui-input-inline">
					<textarea name="changeReason" id="changeReason" notnull="true"
						style="width: 318px; height: 86px;margin-bottom: 58px;"></textarea>
				</div>
			</div>
		</div>
	</div>
	</div>
</div>
	<script
		src="${pageContext.request.contextPath}/meta/pmls/js/scene/sceneTrade/openDialogEditAchieve.js?v=${vs}"></script>
</body>
</html>
