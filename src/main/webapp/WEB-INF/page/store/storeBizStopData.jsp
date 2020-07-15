<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<div class="" style="height: auto;">
	<table class="table table-striped table-hover table-bordered">
		<tr>
			<th>门店编号</th>
			<th>门店名称</th>
			<th>门店地址</th>
			<th>维护人</th>
			<th>合作模式</th>
			<th>合同状态</th>
			<th>营业状态</th>
			<th>上报时间</th>
			<th>停业审核时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${data}" var="list">
			<tr class="J_eatateItem" data-hidenumberflg="0">
				<td>${list.storeNo}</td>
				<td>${list.name}</td>
				<td>${list.address}</td>
				<td>${list.maintainerName}</td>
				<td><c:if test="${empty list.contractTypeName}">未签</c:if>${list.contractTypeName}</td>
				<td><c:if test="${empty list.contractStatusName}">-</c:if>
				${list.contractStatusName}</td>
				<td>${list.bussinessStatusName}</td>
                <td>${list.dateCreate}</td>
                <td>${list.auditTime}</td>
				<td>
					<div class="mailopre">
						<a href="javascript:BizStop.showBizStopInfo('${list.stopId}')">查看</a>
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
