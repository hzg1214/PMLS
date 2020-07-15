<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;width:560px;">
	<table class="table table-striped table-bordered table-hover">
		<col style="width:50px;">
		<col style="width:auto;">
		<col style="width:auto;">
		<tr>
			<th></th>
			<th>人员编号</th>
			<th>人员姓名</th>
		</tr>

		<c:forEach items="${linkUserList}" var="list">
			<tr class="J_eatateItem" data-hidenumberflg="0">
				<td><input type="radio" class="selectCenterId" name="selectUser" data-userCode="${list.userCode}" 
				data-userName="${list.userName}"
				data-userId="${list.userId}"
				value="${list.userCode}"></td>
				<td>${list.userCode}</td>
				<td>${list.userName}</td>
			</tr>
		</c:forEach>
	</table>
</div>
${pageInfo.html}

<c:if test="${fn:length(linkUserList) le 0}">
	<div class="nodata">
		<i class="icon-hdd"></i>
		<p>暂无数据...</p>
	</div>
</c:if>