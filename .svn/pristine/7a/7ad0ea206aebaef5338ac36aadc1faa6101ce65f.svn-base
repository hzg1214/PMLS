<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<c:if test="${fn:length(contentlist) gt 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
<c:if test="${fn:length(contentlist) le 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
<table class="table table-striped table-hover table-bordered" style="font-size: 12px;">
    <tbody class="s-w100">
    <tr>
<!--         <th rowspan="3" style="vertical-align: middle;width: 45px;">序号</th> -->
        <th rowspan="1" style="vertical-align: middle;width: 100px;">归属城市</th>
        <th rowspan="1" style="vertical-align: middle;width: 100px;">所属中心</th>
        <th rowspan="1" style="vertical-align: middle;width: 100px;">门店城市</th>
        <th rowspan="1" style="vertical-align: middle;width: 100px;">门店编号</th>
        <th rowspan="1" style="vertical-align: middle;width: 160px;">门店名称</th>
        <th rowspan="1" style="vertical-align: middle;width: 160px;">门店地址</th>
        <th rowspan="1" style="vertical-align: middle;width: 100px;">经营场所</th>
        <th rowspan="1" style="vertical-align: middle;width: 100px;">门店规模</th>
        <th rowspan="1" style="vertical-align: middle;width: 100px;">维护人员</th>
        <th rowspan="1" style="vertical-align: middle;width: 100px;">维护人员工号</th>
        <th rowspan="1" style="vertical-align: middle;width: 100px;">合作模式</th>
        <th rowspan="1" style="vertical-align: middle;width: 100px;">公司编号</th>
        <th rowspan="1" style="vertical-align: middle;width: 180px;">所属公司</th>
        <th rowspan="1" style="vertical-align: middle;width: 100px;">创建人</th>
        <th rowspan="1" style="vertical-align: middle;width: 100px;">创建时间</th>
    </tr>

    <c:forEach items="${contentlist}" var="list">
        <tr class="J_eatateItem" data-hidenumberflg="0">
<%--             <td style="vertical-align: middle">${list.rowNum}</td> --%>
            <td style="vertical-align: middle" title="${list.acCityName}" class="text-overflow">${list.acCityName}</td>
            <td style="vertical-align: middle" title="${list.centerName}" class="text-overflow">${list.centerName}</td>
            <td style="vertical-align: middle" title="${list.cityName}" class="text-overflow">${list.cityName}</td>
            <td style="vertical-align: middle" title="${list.storeNo}" class="text-overflow">${list.storeNo}</td>
            <td style="vertical-align: middle" title="${list.storeName}" class="text-overflow">${list.storeName}</td>
            <td style="vertical-align: middle" title="${list.addressDetail}" class="text-overflow">${list.addressDetail}</td>
            <td style="vertical-align: middle" title="${list.bussinessPlace}" class="text-overflow">${list.bussinessPlace}</td>
            <td style="vertical-align: middle" title="${list.storeSizeScale}" class="text-overflow">${list.storeSizeScale}</td>
            <td style="vertical-align: middle" title="${list.maintainerName}" class="text-overflow">${list.maintainerName}</td>
            <td style="vertical-align: middle" title="${list.maintainer}" class="text-overflow">${list.maintainer}</td>
            <td style="vertical-align: middle" title="${list.contractTypeName}" class="text-overflow">${list.contractTypeName}</td>
            <td style="vertical-align: middle" title="${list.companyNo}" class="text-overflow">${list.companyNo}</td>
            <td style="vertical-align: middle" title="${list.companyName}" class="text-overflow">${list.companyName}</td>
            <td style="vertical-align: middle" title="${list.userName}" class="text-overflow">${list.userName}</td>
            <td style="vertical-align: middle" title="${list.dateCreate}" class="text-overflow">${list.dateCreate}</td>
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
