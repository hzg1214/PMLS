<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;">
<table class="table table-striped table-hover table-bordered">
    <tr>
        <th style="width: 50px;"></th>
        <th style="width: 160px;">报备编号</th>
        <th style="width: 110px;">楼盘城市</th>
        <th style="width: 140px;">楼盘</th>
        <th>经纪公司</th>
        <th style="width: 100px;">业绩归属人</th>
        <th style="width: 120px;">客户</th>
        <th style="width: 100px;">最新进度</th>
        <th style="width: 100px;">确认状态</th>
        <th style="width: 100px;">操作</th>
    </tr>

    <c:forEach items="${contentlist}" var="list">
        <tr>
            <td><input type="checkbox" value="${list.id}" class="selectReport" /></td>
            <td>${list.reportId}</td>
            <td>${list.realityCityName}</td>
            <td style="text-align:left;" title="${list.estateNm}">${fn:substring(list.estateNm, 0, 8)}
                <c:if test="${fn:length(list.estateNm) >= '9'}">
                    ...
                </c:if>
            </td> 
            <td style="text-align:left;" title="${list.companyNm}">${fn:substring(list.companyNm, 0, 11)}
                <c:if test="${fn:length(list.companyNm) >= '12'}">
                    ...
                </c:if>
            </td>
            <td>${list.contactNm}</td>
            <td style="text-align:left;" title="${list.customerNm}">${fn:substring(list.customerNm, 0, 7)}
                <c:if test="${fn:length(list.customerNm) >= '8'}">
                    ...
                </c:if>
            </td>
            <td>${list.latestProgressName}</td>
            <td>${list.confirmStatusName}</td>
            <td><a href="${ctx}/report/${list.estateId}/${list.companyId}/${list.customerId}/${list.id}/1">查看</a></td>
        </tr>
    </c:forEach>
</table>
</div>
${pageInfo.html}

<c:if test="${fn:length(contentlist) le 0}">
    <div class="nodata"><i class="icon-hdd"></i>
        <p>暂无数据...</p></div>
</c:if>

<!-- 遮罩层(辅助) -->
<div class="shade"></div>