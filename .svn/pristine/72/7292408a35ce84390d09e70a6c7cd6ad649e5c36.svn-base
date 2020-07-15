<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<c:if test="${fn:length(contentlist) gt 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
<c:if test="${fn:length(contentlist) le 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
<table class="table table-striped table-hover table-bordered" style="max-width: none;width:2500px;font-size: 12px;">
    <tbody class="s-w100">
    <tr>
        <th style="vertical-align: middle;width: 45px;">序号</th>
        <th style="vertical-align: middle;width: 120px;">业绩归属区域</th>
        <th style="vertical-align: middle;width: 120px;">业绩归属城市</th>
        <th style="vertical-align: middle;width: 120px;">业绩所在城市</th>
        <th style="vertical-align: middle;width: 120px;">项目归属城市</th>
        <th style="vertical-align: middle;width: 120px;">收入归属城市</th>
        <th style="vertical-align: middle;width: 120px;">项目编号</th>
        <th style="vertical-align: middle;width: 120px;">楼盘名</th>
        <th style="vertical-align: middle;width: 140px;">报备编号</th>
        <th style="vertical-align: middle;">楼室号</th>
        <th style="vertical-align: middle;">总累计资金成本</th>
        <th style="vertical-align: middle;">当年以前资金成本</th>
        <th style="vertical-align: middle;">${year}资金成本</th>
        <th >1月资金成本</th>
        <th >2月资金成本</th>
        <th >3月资金成本</th>
        <th >4月资金成本</th>
        <th >5月资金成本</th>
        <th >6月资金成本</th>
        <th >7月资金成本</th>
        <th >8月资金成本</th>
        <th >9月资金成本</th>
        <th >10月资金成本</th>
        <th >11月资金成本</th>
        <th >12月资金成本</th>
        
    </tr>

    <c:forEach items="${contentlist}" var="list">
        <c:if test="${list.suitNum == -1}" ><tr class="J_eatateItem" data-hidenumberflg="0" style="color: red"></c:if>
        <c:if test="${list.suitNum == 1}" ><tr class="J_eatateItem" data-hidenumberflg="0"></c:if>

        <td style="vertical-align: middle">${list.rowNum}</td>
        <td style="vertical-align: middle">${list.regionName}</td>
        <td style="vertical-align: middle">${list.areaCityName}</td>
        <td style="vertical-align: middle">${list.cityGroupName}</td>
        <td style="vertical-align: middle">${list.projectCityName}</td>
        <td style="vertical-align: middle">${list.srCityName}</td>
        <td style="vertical-align: middle">${list.projectNo}</td>
<%--         <td style="vertical-align: middle">${list.estateNm}</td> --%>
        <td style="text-align:center;" title="${list.estateNm}">
                ${fn:substring(list.estateNm, 0, 5)}
            <c:if test="${fn:length(list.estateNm) >= '5'}">
                ...
            </c:if>
        </td>
        <td style="vertical-align: middle">${list.reportId}</td>
<%--         <td style="vertical-align: middle">${list.buildingNo}</td> --%>
        <td style="text-align:center;" title="${list.buildingNo}">
                ${fn:substring(list.buildingNo, 0, 5)}
            <c:if test="${fn:length(list.buildingNo) >= '5'}">
                ...
            </c:if>
        </td>
        <td style="vertical-align: middle">${list.totalAmount}</td>
        <td style="vertical-align: middle">${list.preTotal}</td>
        <td style="vertical-align: middle">${list.thisTotal}</td>
        <td style="vertical-align: middle">${list.jan}</td>
        <td style="vertical-align: middle">${list.feb}</td>
        <td style="vertical-align: middle">${list.mar}</td>
        <td style="vertical-align: middle">${list.apr}</td>
        <td style="vertical-align: middle">${list.may}</td>
        <td style="vertical-align: middle">${list.jun}</td>
        <td style="vertical-align: middle">${list.jul}</td>
        <td style="vertical-align: middle">${list.aug}</td>
        <td style="vertical-align: middle">${list.sep}</td>
        <td style="vertical-align: middle">${list.oct}</td>
        <td style="vertical-align: middle">${list.nov}</td>
        <td style="vertical-align: middle">${list.dec}</td>
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
