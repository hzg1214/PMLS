<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<body>
	<form id="maintainerForm">
		<input type="hidden" name="storeId" id="storeId" value="${storeId}">
		<div style="height:20px; line-height: 20px; text-align:center;">
			<span id="msgId" style="color:red"></span>
		</div>
		<table class="table-sammary">
			<col style="width:95px;">
			<col style="width:auto;">
			<col style="width:95px;">
			<col style="width:auto;">
			<tr>
				<td class="talabel required">维护人工号</td>
				<td>
					<input type="text" name="maintainer" class="form-control w220" id="maintainer" value="" dataType="normal" notnull="true">
					<span class="fc-warning"></span>
				</td>
				<td class="talabel required">维护人姓名</td>
				<td>
					<input type="text" name="maintainerName" class="form-control w220" id="maintainerName" value="" dataType="normal" notnull="true">
					<span class="fc-warning"></span>
				</td>
			</tr>
			<tr>
				<td class="talabel required">维护开始日期</td>
				<td>
					<input type="text" class="form-control calendar-icon  ipttext Wdate w220" name="dateMtcStart" id="dateMtcStart" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" class="" notnull="true" value="">
					<span class="fc-warning"></span>
				</td>
				<td class="talabel required">门店联系人</td>
				<td>
					<input name="contactName" class="form-control w220" id="contactName" value="" dataType="normal" notnull="true">
					<span class="fc-warning"></span>
				</td>
			</tr>
			<tr>
				<td class="talabel required">联系方式</td>
				<td>
					<input name="contactPhoneNo" class="form-control w220" id="contactPhoneNo" value="" dataType="phone" notnull="true" maxlength="20">
					<span class="fc-warning"></span>
				</td>
			</tr>
		</table>
	</form>
</body>
