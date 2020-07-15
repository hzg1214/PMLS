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
			<th style="width: 160px;">撤销编号</th>
			<th>门店编号</th>
			<th>门店名称</th>
			<th>门店地址</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>审核状态</th>
			<th style="width: 60px;">操作</th>
		</tr>
		<c:forEach items="${data}" var="list">
			<tr class="J_eatateItem" data-hidenumberflg="0">
				<td>${list.cancelNo}</td>
				<td>${list.storeNo}</td>
				<td class="text-overflow" title="${list.storeName}">${list.storeName}</td>
				<td class="text-overflow" title="${list.addressDetail}">${list.addressDetail}</td>
				<td>${list.userName}(${list.userCode})</td>
                <td>${fn:substring(list.dateCreate,0,10)}</td>
                <td>${list.approveStatusNm}</td>
				<td>
					<div class="mailopre">
						<a href="javascript:BizStop.view('${list.id}')">查看</a>
					</div>
				</td>

			</tr>
		</c:forEach>

	</table>
</div>

${pageInfo.html}

<c:if test="${fn:length(data) le 0}">
	<div class="nodata">
		<i class="icon-hdd"></i>
		<p>暂无数据...</p>
	</div>
</c:if>
