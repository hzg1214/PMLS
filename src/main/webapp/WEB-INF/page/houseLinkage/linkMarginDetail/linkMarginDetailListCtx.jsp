<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<c:if test="${fn:length(contentlist) gt 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
<c:if test="${fn:length(contentlist) le 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
<table class="table table-striped table-hover table-bordered" style="max-width: none;width:5500px;font-size: 12px;">
    <tbody class="s-w100">
    <tr>
        <th rowspan="2" style="vertical-align: middle;width: 60px;">序号</th>
        <th rowspan="2" style="vertical-align: middle;width: 120px;">项目归属城市</th>
        <th rowspan="2" style="vertical-align: middle;width: 120px;">成本中心</th>
        <th rowspan="2" style="vertical-align: middle;width: 120px;">项目编号</th>
        <th rowspan="2" style="vertical-align: middle;width: 150px;">项目名称</th>
        <th rowspan="2" style="vertical-align: middle;width: 150px;">预付款单号</th>
        <th rowspan="2" style="vertical-align: middle;width: 150px;">客商/供应商</th>
        <th rowspan="2" style="vertical-align: middle;width: 150px;">类型</th>
        <th rowspan="2" style="vertical-align: middle;width: 150px;">预计归还日期</th>
        
        <th rowspan="2" style="vertical-align: middle;width: 120px;">预付款金额</th>
        <th rowspan="2" style="vertical-align: middle;width: 120px;">实际付款金额</th>
        <th colspan="2" style="vertical-align: middle;">还款金额</th>
        <th rowspan="2" style="vertical-align: middle;width: 120px;">未收回金额</th>
        
        <th colspan="3" style="vertical-align: middle;">资金成本</th>
        <c:forEach var="i" begin="1" end="12" step="1">
			<th colspan="3" style="vertical-align: middle;">${i}月</th>
		</c:forEach>
    </tr>
	<tr>
		<th style="vertical-align: middle;width: 110px;">正常回款</th>
		<th style="vertical-align: middle;width: 110px;">核销</th>
		<th style="vertical-align: middle;width: 110px;">总累计</th>
		<th style="vertical-align: middle;width: 110px;">${year}以前</th>
        <th style="vertical-align: middle;width: 110px;">${year}新增</th>
        <c:forEach var="i" begin="1" end="12" step="1">
			<th style="vertical-align: middle;">超3个月保证金金额</th>
			<th style="vertical-align: middle;">超3个月保证金回款</th>
			<th style="vertical-align: middle;">资金成本</th>
		</c:forEach>
	</tr>
        
    <c:forEach items="${contentlist}" var="list">
        <c:if test="${list.suitNum == -1}" ><tr class="J_eatateItem" data-hidenumberflg="0" style="color: red"></c:if>
        <c:if test="${list.suitNum == 1}" ><tr class="J_eatateItem" data-hidenumberflg="0"></c:if>

        <td style="vertical-align: middle">${list.rowNum}</td>
        <td style="vertical-align: middle">${list.gsCityName}</td>
        <td style="vertical-align: middle">${list.costName}</td>
        <td style="vertical-align: middle">${list.projectNo}</td>
        <td style="text-align:center;" title="${list.projectName}">
                ${fn:substring(list.projectName, 0, 8)}
            <c:if test="${fn:length(list.projectName) >= '8'}">
                ...
            </c:if>
        </td>
        <td style="vertical-align: middle">${list.oaNo}</td>
        <td style="text-align:center;" title="${list.ksName}">
                ${fn:substring(list.ksName, 0, 8)}
            <c:if test="${fn:length(list.ksName) >= '8'}">
                ...
            </c:if>
        </td>
        <td style="vertical-align: middle">${list.marginTypeName}</td>
        <td style="vertical-align: middle">${sdk:ymd2(list.yfhkDate)}</td>
        
        <td style="vertical-align: middle">${list.oaAmount}</td>
        <td style="vertical-align: middle">${list.sjfkAmountTotal}</td>
        <td style="vertical-align: middle">${list.hkAmountTotal}</td>
        <td style="vertical-align: middle">${list.hxAmount}</td>
        <td style="vertical-align: middle">${list.cyAmountTotal}</td>
        
        <td style="vertical-align: middle">${list.totalAmount}</td>
        <td style="vertical-align: middle">${list.preTotal}</td>
        <td style="vertical-align: middle">${list.thisTotal}</td>
        
        <td style="vertical-align: middle">${list.marginjan}</td>
		<td style="vertical-align: middle">${list.marginhkjan}</td>
		<td style="vertical-align: middle">${list.marginCbjan}</td>
		
		<td style="vertical-align: middle">${list.marginfeb}</td>
		<td style="vertical-align: middle">${list.marginhkfeb}</td>
		<td style="vertical-align: middle">${list.marginCbfeb}</td>
		
		<td style="vertical-align: middle">${list.marginmar}</td>
		<td style="vertical-align: middle">${list.marginhkmar}</td>
		<td style="vertical-align: middle">${list.marginCbmar}</td>
		
		<td style="vertical-align: middle">${list.marginapr}</td>
		<td style="vertical-align: middle">${list.marginhkapr}</td>
		<td style="vertical-align: middle">${list.marginCbapr}</td>
		
		<td style="vertical-align: middle">${list.marginmay}</td>
		<td style="vertical-align: middle">${list.marginhkmay}</td>
		<td style="vertical-align: middle">${list.marginCbmay}</td>
		
		<td style="vertical-align: middle">${list.marginjun}</td>
		<td style="vertical-align: middle">${list.marginhkjun}</td>
		<td style="vertical-align: middle">${list.marginCbjun}</td>
		
		<td style="vertical-align: middle">${list.marginjul}</td>
		<td style="vertical-align: middle">${list.marginhkjul}</td>
		<td style="vertical-align: middle">${list.marginCbjul}</td>
		
		<td style="vertical-align: middle">${list.marginaug}</td>
		<td style="vertical-align: middle">${list.marginhkaug}</td>
		<td style="vertical-align: middle">${list.marginCbaug}</td>
		
		<td style="vertical-align: middle">${list.marginsep}</td>
		<td style="vertical-align: middle">${list.marginhksep}</td>
		<td style="vertical-align: middle">${list.marginCbsep}</td>
		
		<td style="vertical-align: middle">${list.marginoct}</td>
		<td style="vertical-align: middle">${list.marginhkoct}</td>
		<td style="vertical-align: middle">${list.marginCboct}</td>
		
		<td style="vertical-align: middle">${list.marginnov}</td>
		<td style="vertical-align: middle">${list.marginhknov}</td>
		<td style="vertical-align: middle">${list.marginCbnov}</td>
		
		<td style="vertical-align: middle">${list.margindec}</td>
		<td style="vertical-align: middle">${list.marginhkdec}</td>
		<td style="vertical-align: middle">${list.marginCbdec}</td>
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
