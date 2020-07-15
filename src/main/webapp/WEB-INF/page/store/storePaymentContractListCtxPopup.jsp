<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;width:750px;">
	<table class="table table-striped table-bordered table-hover">
		<col style="width:40px;">
		<col style="width:auto;">
		<col style="width:auto;">
		<col style="width:auto;">
		<col style="width:auto;">
		<tr>
			<th></th>
			<th>合同编号</th>
			<th>协议书编号</th>
			<th>经纪公司</th>
			<th>创建日期</th>
			<th>合同状态</th>
		</tr>
		<c:forEach items="${contractInfo}" var="list">
			<tr class="J_eatateItem" data-hidenumberflg="0">
				<td><input type="radio" class="selectContractId" name="contractId" value="${list.id}">
					<input type="hidden" id="contractStatus" value="${list.contractStatus}">
				</td>
				<td>${list.contractNo}</td>
				<td>${list.agreementNo}</td>
				<td>${list.companyName}</td>
				<td>${sdk:ymd2(list.dateCreate)}</td>
				<td>${list.contractStatusName}</td>
			</tr>
		</c:forEach>
	</table>
</div>
${pageInfo.html}

<c:if test="${fn:length(contractInfo) le 0}">
	<div class="nodata">
		<i class="icon-hdd"></i>
		<p>暂无数据...</p>
	</div>
</c:if>