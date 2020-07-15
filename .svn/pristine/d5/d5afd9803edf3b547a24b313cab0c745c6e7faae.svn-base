<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<div class="" style="height: auto;">
	<table class="table table-striped table-hover table-bordered">
		<tr>
			<th>维护人(工号)</th>
			<th>手机号</th>
			<th>邮箱</th>
			<th>开始维护时间</th>
			<th>设置人(工号)</th>
		</tr>
		<c:forEach items="${contentlist}" var="list">
			<tr class="J_eatateItem" data-hidenumberflg="0">
				<td>${list.userName}(${list.userCode})</td>
				<td>${list.cellphone}</td>
				
				<td style="text-align:left;" title="${list.email}">${fn:substring(list.email, 0, 30)}
					<c:if test="${fn:length(list.email) >= '30'}">
					...
					</c:if>
				</td>
				<td>${list.dateMtcStart}</td>
				<td>${list.setUserName}(${list.setUserCode})</td>
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
