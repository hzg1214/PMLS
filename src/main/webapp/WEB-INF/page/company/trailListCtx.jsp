<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div>
	<table class="table table-striped table-hover table-bordered">
		<tr>
			<th scope="col">跟进主题</th>
			<th scope="col">跟进时间</th>
			<th width="" scope="col">跟进人</th>
			<th width="" scope="col">跟进类型</th>
			<th width="" scope="col">操作</th>
		</tr>
		
		<c:forEach items="${contentlist}" var="list">
			<tr>
				<td align="center">${list.title}</td>
				<td align="center">${list.dateCreate}</td>
				<td align="center">${list.userNameCreate}</td>
				<td align="center">${list.followTypeName}</td>
				<td align="center">
					<div class="mailopre">
						<a href="javascript:detail(${list.followId});">查看</a> 
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	${pageInfo.html}
</div>
<c:if test="${fn:length(contentlist) le 0}" >
	<div class="nodata"><i class="icon-hdd"></i><p>暂无数据...</p></div>
</c:if>