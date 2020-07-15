<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<div class="" style="height: auto;">
	<table class="table table-striped table-hover table-bordered">
		<tr>
			<th>门店编号</th>
			<th>门店名称</th>
			<th>门店地址</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>门店状态</th>
			<th>审核通过时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${contentlist}" var="list">
			<tr class="J_eatateItem" data-hidenumberflg="0">
				<td>${list.storeNo}</td>
				<td>${list.storeName}</td>
				<td>${list.addressDetail}</td>
				<td>${list.userNameCreate}</td>
				<td>${list.dateCreate}</td>
				<td>${list.auditStatusName}</td>
                <td>${list.auditDate}</td>
				<td>
					<div class="mailopre">
						<a href="javascript:StoreAudit.showStoreAuditInfo('${list.storeId}')">查看</a>
					</div>
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
