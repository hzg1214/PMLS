<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;width:750px;">
	<table class="table table-striped table-bordered table-hover">
		<col style="width:30px">
		<col style="width:110px;">
		<col style="width:130px">
		<col style="width:65px;">
		<col style="width:auto;">
		<col style="width:65px;">
		<col style="width:85px">
		<tr>
			<!-- <th class="text-center">
				<input value="checkbox" type="checkbox">
			</th> -->
			<!-- <th>门店名称</th>
			<th>所在地址</th>
			<th>联系人</th> -->
			<th></th>
			<th>门店编号</th>
			<th>门店名称</th>
            <th>所在区域</th>
            <th style="width:150px">门店地址</th>
            <th>门店状态</th>
            <th>创建日期</th>
            <th style="display:none">操作</th>
		</tr>

		<c:forEach items="${contentlist}" var="list">
			<tr class="J_eatateItem" data-hidenumberflg="0">
				<td class="text-center">
					<input type="checkbox" name="storeIds"   value="${list.storeId}"   <c:if test="${list.storeStatus eq 1}">disabled="disabled"</c:if>>
				</td>
				<%-- <td>${list.name}</td>
				<td>${list.districtName}</td>
				<td>--</td> --%>
				<td style="display:none"><input type="hidden" id="storeId" value="${list.storeId}"></td>
				<td>${list.storeNo}</td>
				<td style="text-align:left;" title="${list.name}">
						${fn:substring(list.name, 0, 10)}
						<c:if test="${fn:length(list.name) >= '10'}">
							...
						</c:if>
				</td>
           		<td>${list.districtName}</td>
           		<td style="text-align:left;" title="${list.addressDetail}">
						${fn:substring(list.addressDetail, 0, 10)}
						<c:if test="${fn:length(list.addressDetail) >= '10'}">
							...
						</c:if>
				</td>
           		<td>${list.storeStatusName}</td>
           		<td>${list.dateCreate}</td>
           		<td style="display:none"><a href='javascript:void(0)' onclick='removeTr(this)'>移除</a></td>
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