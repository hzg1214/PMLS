<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;">
	<table class="table table-striped table-hover table-bordered">
		<col style="width:80px">
		<col style="width:310px">
		<col style="width:60px">
		<col style="width:310px">
		<c:if test="${1 ne switchTypeValue}"><col style="width:150px"></c:if>
		<col style="width:80px">
		<col style="width:100px">
		<col style="width:80px">
		<tr>
			<!-- <th class="text-center"><input value="checkbox" type="checkbox"></th> -->
			<th>公司编号</th>
			<th>公司名称</th>
			<th>门店数</th>
			<th>注册地址</th>
			<c:if test="${1 ne switchTypeValue}"><th>业务类型</th></c:if>
			<th>创建人</th>
			<th>创建时间</th>
			<th class="text-center">操作</th>
		</tr>

		<c:forEach items="${contentlist}" var="list">

			<tr class="J_eatateItem" data-hidenumberflg="0">
				<td><a href="${ctx}/companys/${list.id}">${list.companyNo}</a></td>
				<td style="text-align:left;" title="${list.companyName}" class="text-overflow">
					${list.companyName}
				</td>
				<td class="text-overflow">${list.storeCount}</td>
				<td style="text-align:left;" title="${list.cityName}${list.districtName}${list.address}" class="text-overflow">
					${list.cityName}${list.districtName}${list.address}
				</td>
				<c:if test="${1 ne switchTypeValue}">
					<td class="text-overflow">${list.bizTypeNm}</td>
				</c:if>
				<td class="text-overflow">${list.userCreateName}</td>
				<td class="text-overflow">${sdk:ymd2(list.dateCreate)}</td>
				<td class="text-center">
				    <a href="${ctx}/companys/${list.id}">查看</a>
					<!-- 谁的客户谁才有编辑权限 -->
					<%--<c:if test="${list.userCreate eq userInfo.userId}">
						<a href="${ctx}/companys/u/${list.id}">编辑</a>
					</c:if>--%>
					
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