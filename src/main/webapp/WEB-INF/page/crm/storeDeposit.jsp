<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<body>
	<form id="depositForm">
		<div class="form-group">
			<label for="auditReturnReason">请填写驳回原因：</label>
			<input name="storeId" value="${storeId}" style="display: none;">
			<input name="storeName" value="${storeName}" style="display: none;">
			<input name="storeNo" value="${storeNo}" style="display: none;">
			<input name="userCode" value="${userCode}" style="display: none;">
			<input name="auditReturnReason" id="auditReturnReason" value="" dataType="text" notnull="true">
			<br><span class="fc-warning"></span>
		</div>
	</form>
</body>
