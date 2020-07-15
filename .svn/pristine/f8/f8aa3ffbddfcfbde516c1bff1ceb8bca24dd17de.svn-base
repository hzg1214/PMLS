<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<c:if test="${fn:length(contentlist) gt 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
<c:if test="${fn:length(contentlist) le 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
<table class="table table-striped table-hover table-bordered" style="max-width: none;width:5500px;font-size: 12px;">
    <tbody class="s-w100">
    <tr>
        <th rowspan="2" style="vertical-align: middle;width: 60px;">序号</th>
        <th rowspan="2" style="vertical-align: middle;width: 120px;">业绩归属城市</th>
        <th rowspan="2" style="vertical-align: middle;width: 120px;">业绩所在城市</th>
        <th rowspan="2" style="vertical-align: middle;width: 120px;">业绩归属中心</th>
        <th rowspan="2" style="vertical-align: middle;width: 120px;">项目归属城市</th>
        <th rowspan="2" style="vertical-align: middle;width: 120px;">项目编号</th>
        <th rowspan="2" style="vertical-align: middle;width: 180px;">项目名称</th>
        <th rowspan="2" style="vertical-align: middle;width: 140px;">报备编号</th>
        <th rowspan="2" style="vertical-align: middle;width: 120px;">楼室号</th>
        <th colspan="3" style="vertical-align: middle;">期初<br>${qcTimeStage}</th>
        <th colspan="3" style="vertical-align: middle;width: 510px;">资金成本</th>
        <c:forEach var="i" begin="1" end="12" step="1">
			<th colspan="3" style="vertical-align: middle;">${i}月</th>
		</c:forEach>
        
    </tr>
	<tr>
        <th style="vertical-align: middle;width: 100px;">返佣</th>
        <th style="vertical-align: middle;width: 100px;">垫佣</th>
        <th style="vertical-align: middle;width: 100px;">回款</th>

		<th style="vertical-align: middle;width: 170px;">总累计<br>${zljTimeStage}</th>
		<th style="vertical-align: middle;width: 170px;">当年以前<br>${dnyqTimeStage}</th>
        <th style="vertical-align: middle;width: 170px;">当年新增<br>${dnxzTimeStage}</th>
        <c:forEach var="i" begin="1" end="12" step="1">
			<th style="vertical-align: middle;width: 100px;">超3个月<br/>垫佣金额</th>
			<th style="vertical-align: middle;width: 100px;">超3个月<br/>垫佣回款</th>
			<th style="vertical-align: middle;width: 100px;">资金成本</th>
		</c:forEach>
	</tr>

    <c:forEach items="${contentlist}" var="list">
        <c:if test="${list.suitNum == -1}" ><tr class="J_eatateItem" data-hidenumberflg="0" style="color: red"></c:if>
        <c:if test="${list.suitNum == 1}" ><tr class="J_eatateItem" data-hidenumberflg="0"></c:if>

        <td style="vertical-align: middle">${list.rowNum}</td>
        <td style="vertical-align: middle">${list.areaCityName}</td>
        <td style="vertical-align: middle">${list.cityGroupName}</td>
        <td style="vertical-align: middle">${list.centerGroupName}</td>
        <td style="vertical-align: middle">${list.projectCityName}</td>
        <td style="vertical-align: middle">${list.projectNo}</td>
        <td style="text-align:center;" title="${list.estateNm}">
                ${fn:substring(list.estateNm, 0, 10)}
            <c:if test="${fn:length(list.estateNm) >= '10'}">
                ...
            </c:if>
        </td>
        <td style="vertical-align: middle">${list.reportId}</td>
        <td style="text-align:center;" title="${list.buildingNo}">
                ${fn:substring(list.buildingNo, 0, 7)}
            <c:if test="${fn:length(list.buildingNo) >= '7'}">
                ...
            </c:if>
<%--             <c:if test="${empty list.buildingNo}"> --%>
<!--                 - -->
<%--             </c:if> --%>
        </td>

        <td style="vertical-align: middle">${list.qcfy}</td>
        <td style="vertical-align: middle">${list.qcdy}</td>
        <td style="vertical-align: middle">${list.qchk}</td>

        <td style="vertical-align: middle">${list.totalAmount}</td>
        <td style="vertical-align: middle">${list.preTotal}</td>
        <td style="vertical-align: middle">${list.thisTotal}</td>
        
        <td style="vertical-align: middle">${list.dyjan}</td>
		<td style="vertical-align: middle">${list.dyhkjan}</td>
		<td style="vertical-align: middle">${list.zjcbjan}</td>
		
		<td style="vertical-align: middle">${list.dyfeb}</td>
		<td style="vertical-align: middle">${list.dyhkfeb}</td>
		<td style="vertical-align: middle">${list.zjcbfeb}</td>
		
		<td style="vertical-align: middle">${list.dymar}</td>
		<td style="vertical-align: middle">${list.dyhkmar}</td>
		<td style="vertical-align: middle">${list.zjcbmar}</td>
		
		<td style="vertical-align: middle">${list.dyapr}</td>
		<td style="vertical-align: middle">${list.dyhkapr}</td>
		<td style="vertical-align: middle">${list.zjcbapr}</td>
		
		<td style="vertical-align: middle">${list.dymay}</td>
		<td style="vertical-align: middle">${list.dyhkmay}</td>
		<td style="vertical-align: middle">${list.zjcbmay}</td>
		
		<td style="vertical-align: middle">${list.dyjun}</td>
		<td style="vertical-align: middle">${list.dyhkjun}</td>
		<td style="vertical-align: middle">${list.zjcbjun}</td>
		
		<td style="vertical-align: middle">${list.dyjul}</td>
		<td style="vertical-align: middle">${list.dyhkjul}</td>
		<td style="vertical-align: middle">${list.zjcbjul}</td>
		
		<td style="vertical-align: middle">${list.dyaug}</td>
		<td style="vertical-align: middle">${list.dyhkaug}</td>
		<td style="vertical-align: middle">${list.zjcbaug}</td>
		
		<td style="vertical-align: middle">${list.dysep}</td>
		<td style="vertical-align: middle">${list.dyhksep}</td>
		<td style="vertical-align: middle">${list.zjcbsep}</td>
		
		<td style="vertical-align: middle">${list.dyoct}</td>
		<td style="vertical-align: middle">${list.dyhkoct}</td>
		<td style="vertical-align: middle">${list.zjcboct}</td>
		
		<td style="vertical-align: middle">${list.dynov}</td>
		<td style="vertical-align: middle">${list.dyhknov}</td>
		<td style="vertical-align: middle">${list.zjcbnov}</td>
		
		<td style="vertical-align: middle">${list.dydec}</td>
		<td style="vertical-align: middle">${list.dyhkdec}</td>
		<td style="vertical-align: middle">${list.zjcbdec}</td>
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
