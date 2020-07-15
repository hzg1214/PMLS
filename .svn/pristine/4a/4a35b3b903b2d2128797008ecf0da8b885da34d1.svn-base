<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div>
	<table class="table table-striped table-hover table-bordered">
		<tr>
		    <th>序号</th>
			<th scope="col">房友账号</th>
			<th scope="col">操作类型</th>
			<th width="" scope="col">操作日期</th>
			<th width="" scope="col">操作人(工号)</th>
		</tr>

		<c:forEach items="${contentlist}" var="list" varStatus="status">
			<tr>
			    <td>${status.index + 1}</td>
				<td align="center">${list.fangyouNo}</td>
				<td align="center">
					<input type="hidden" name="operType" id="operType" value="${list.operType}">
					 <c:if test="${list.operType eq 1}">
					 	绑定
					 </c:if> 
					 <c:if test="${list.operType eq 0}">
					 	解绑
					 </c:if> 
				</td>
				<td align="center">${list.dateCreate}</td>
				<c:if test="${empty list.userName}">
				   <td align="center">Admin</td>
				</c:if>
				<c:if test="${!empty list.userName}">
				<td align="center">${list.userName}(${list.userCode})</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	${pageInfo.html}
</div>
<c:if test="${fn:length(contentlist) le 0}" >
	<div class="nodata"><i class="icon-hdd"></i><p>暂无数据...</p></div>
</c:if>