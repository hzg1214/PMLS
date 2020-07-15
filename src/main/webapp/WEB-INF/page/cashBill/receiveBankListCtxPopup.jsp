<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;width:750px;font-size: 12px;">
	<table class="table table-striped table-bordered table-hover" style="font-size: 14px;">
		<col style="width:30px;">
		<col style="width:200px;">
		<col style="width:200px;">
		<col style="width:200px;">
		<col style="width:100px;">
		<tr>
			<th></th>
			<th>银行名称</th>
			<th>银行账号</th>
			<th>银行户名</th>
			<th>省市</th>
		</tr>

		<c:forEach items="${contentlist}" var="list">
			<tr class="J_eatateItem" data-hidenumberflg="0">
				<td><input type="radio" name="selectCompanyId"
						data-bankName="${list.bankName}"
						data-bankAccountCardCode="${list.bankAccountCardCode}"
						data-bankAccountName="${list.bankAccountName}"
						data-provinceName="${list.provinceName}"
						data-cityName="${list.cityName}"
						data-serialNo="${list.serialNo}"
						value="${list.id}"></td>
				<td>${list.bankName}</td>
				<td>${list.bankAccountCardCode}</td>
				<td>${list.bankAccountName}</td>
				<td>${list.provinceName} ${list.provinceName}</td>
			</tr>
		</c:forEach>
	</table>
</div>
${pageInfo.html}

<c:if test="${fn:length(contentlist) le 0}">
	<div class="nodata">
		<i class="icon-hdd"></i>
		<p>暂无数据...</p>
	</div>
</c:if>