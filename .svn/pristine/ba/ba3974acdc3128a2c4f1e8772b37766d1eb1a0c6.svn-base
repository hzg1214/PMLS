<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<style type="text/css">
	.text-overflow{
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		width:100%;
	}
</style>
<div class="" style="height: auto;">
	<table class="table table-striped table-hover table-bordered">
		<tr>
			<th style="width: 150px;">授牌验收申请编号</th>
			<th>门店编号</th>
			<th>门店名称</th>
			<th>门店地址</th>
			<th>维护人</th>
			<th>创建时间</th>
			<th style="width: 110px;">授牌验收审核状态</th>
			<th style="width: 60px;">操作</th>
		</tr>
		<c:forEach items="${contentlist}" var="list">
			<tr class="J_eatateItem" data-hidenumberflg="0">
				<td>${list.authCheckNo}</td>
				<td>${list.storeNo}</td>
				<td class="text-overflow" title="${list.storeName}">${list.storeName}</td>
				<td class="text-overflow" title="${list.addressDetail}">${list.addressDetail}</td>
				<td>${list.maintainerName}(${list.maintainer})</td>
                <td>${sdk:ymd2(list.dateCreate)}</td>
                <td>${list.checkStatusNm}</td>
				<td>
					<div class="mailopre">
						<a href="javascript:StoreAuthCheck.getStoreAuthCheckInfoById('${list.id}')">查看</a>
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
