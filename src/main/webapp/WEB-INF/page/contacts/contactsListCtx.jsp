<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<!-- <h4>
	<strong>首要联系人</strong>
</h4> -->
<div class="" style="height: auto;">
	<table class="table table-striped table-hover table-bordered">
		<tr>
			<!-- <th><input value="checkbox" type="checkbox"></th> -->
			<th>姓名</th>
			<th>手机号</th>
			<th>邮箱</th>
			<th>创建人</th>
			<th>操作</th>
		</tr>
	 	<!-- 自己和上级有权限查看和编辑联系人 -->
		<c:forEach items="${contentlist}" var="list">
			<tr class="J_eatateItem" data-hidenumberflg="0">
				<td>${list.name} <c:if test="${list.isDefault eq true}"><span class="impro-lianxiren small">首要联系人</span></c:if></td>
				<td>${list.mobilePhone}</td>
				<td style="text-align:left;" title="${list.email}">${fn:substring(list.email, 0, 20)}
					<c:if test="${fn:length(list.email) >= '20'}">
					...
					</c:if>
				</td>
				<td>${list.userName}</td>
				<td>
						<a href="javascript:Contacts.toUpdateCntcts(${list.id});"> 编辑</a> 
						<c:if test="${list.isDefault eq false}">
							<a href="javascript:Contacts.remove('${list.id}');">删除</a>
						</c:if>
						 <a href="javascript:Contacts.toViewCntcts(${list.id});">查看</a>
				</td>
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
