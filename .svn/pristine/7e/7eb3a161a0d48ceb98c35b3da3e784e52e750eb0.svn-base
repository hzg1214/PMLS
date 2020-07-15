<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto; overflow-x:scroll;">
    <table class="table table-striped table-hover table-bordered" style="width:6000px; font-size:12px;"> 
        <tr style="text-align: center;font-weight: bolder;">
	        <th colspan="8"  style="position:fiexd;">业绩信息</th>
	        <th colspan="28"  style="position:fiexd;">应计收入</th>
	        <th colspan="28"  style="position:fiexd;">应计返佣</th>
		</tr>
		<col style="width:150px">
		<col style="width:150px">
		<col style="width:150px">
		<col style="width:150px"> 
		<col style="width:100px">
		<col style="width:50px">
		<col style="width:100px">
		<col style="width:100px"> 
        <tr>     	
        	<th rowspan="2" style="vertical-align: middle;">编号</th>
            <th rowspan="2" style="vertical-align: middle;">项目编号</th>   
            <th rowspan="2" style="vertical-align: middle;">楼盘名</th>   
            <th rowspan="2" style="vertical-align: middle;">报备编号</th>   
            <th rowspan="2" style="vertical-align: middle;">楼室号</th>   
            <th rowspan="2" style="vertical-align: middle;">套数</th>   
            <th rowspan="2" style="vertical-align: middle;">成销总价</th>   
            <th rowspan="2" style="vertical-align: middle;">成销日期</th>   
            <!--  应计收入 -->
            <!--  应计返佣 -->
<% for (int i = 1; i<=2; i++) { %>
            <th colspan="2">小计</th>
            <th colspan="2">当年以前</th>
            <th colspan="2">${yaers}年1月</th>
            <th colspan="2">${yaers}年2月</th>
            <th colspan="2">${yaers}年3月</th>
            <th colspan="2">${yaers}年4月</th>
            <th colspan="2">${yaers}年5月</th>
            <th colspan="2">${yaers}年6月</th>
            <th colspan="2">${yaers}年7月</th>
            <th colspan="2">${yaers}年8月</th>
            <th colspan="2">${yaers}年9月</th>
            <th colspan="2">${yaers}年10月</th>
            <th colspan="2">${yaers}年11月</th>
            <th colspan="2">${yaers}年12月</th>
<% } %>
		</tr> 
         <tr>
<% for (int i = 1; i<=28; i++) { %>
			<th style="vertical-align: middle;">税前</th>
			<th style="vertical-align: middle;">税后</th>
<% } %>
		</tr> 
        <c:forEach items="${contentlist}" var="list">
			<c:if test="${list.num ge 0}">
				<tr class="J_eatateItem" data-hidenumberflg="0">
			</c:if>
        	<c:if test="${list.num lt 0}">
				<tr class="J_eatateItem" data-hidenumberflg="0" style="color:#f00">
			</c:if>
			    <td>${list.estateId}</td>
			    <td>${list.projectNo}</td>
				<td title="${list.estateNm}" class="text-overflow">${list.estateNm}</td>
				<td>${list.reportId}</td>
				<td>${list.buildingNo}</td>
				<td>${list.num}</td>
                <td>${list.dealAmount}</td>
                <td>${list.dealDate}</td>
                
                <td>${list.subTotalPreTax01}</td>
                <td>${list.subtotalAfterTax01}</td>
                <td>${list.beforeAmountPreTax01}</td>
                <td>${list.beforeAmountAfterTax01}</td>
                <td>${list.janPreTax01}</td>
                <td>${list.janAfterTax01}</td>
                <td>${list.febPreTax01}</td>
                <td>${list.febAfterTax01}</td>
                <td>${list.marPreTax01}</td>
                <td>${list.marAfterTax01}</td>
                <td>${list.aprPreTax01}</td>
                <td>${list.aprAfterTax01}</td>
                <td>${list.mayPreTax01}</td>
                <td>${list.mayAfterTax01}</td>
                <td>${list.junPreTax01}</td>
                <td>${list.junAfterTax01}</td>
                <td>${list.julPreTax01}</td>
                <td>${list.julAfterTax01}</td>
                <td>${list.augPreTax01}</td>
                <td>${list.augAfterTax01}</td>
                <td>${list.sepPreTax01}</td>
                <td>${list.sepAfterTax01}</td>
                <td>${list.octPreTax01}</td>
                <td>${list.octAfterTax01}</td>
                <td>${list.novPreTax01}</td>
                <td>${list.novAfterTax01}</td>
                <td>${list.decPreTax01}</td>
                <td>${list.decAfterTax01}</td>
                
                
                 <td>${list.subTotalPreTax02}</td>
                <td>${list.subtotalAfterTax02}</td>
                <td>${list.beforeAmountPreTax02}</td>
                <td>${list.beforeAmountAfterTax02}</td>
                <td>${list.janPreTax02}</td>
                <td>${list.janAfterTax02}</td>
                <td>${list.febPreTax02}</td>
                <td>${list.febAfterTax02}</td>
                <td>${list.marPreTax02}</td>
                <td>${list.marAfterTax02}</td>
                <td>${list.aprPreTax02}</td>
                <td>${list.aprAfterTax02}</td>
                <td>${list.mayPreTax02}</td>
                <td>${list.mayAfterTax02}</td>
                <td>${list.junPreTax02}</td>
                <td>${list.junAfterTax02}</td>
                <td>${list.julPreTax02}</td>
                <td>${list.julAfterTax02}</td>
                <td>${list.augPreTax02}</td>
                <td>${list.augAfterTax02}</td>
                <td>${list.sepPreTax02}</td>
                <td>${list.sepAfterTax02}</td>
                <td>${list.octPreTax02}</td>
                <td>${list.octAfterTax02}</td>
                <td>${list.novPreTax02}</td>
                <td>${list.novAfterTax02}</td>
                <td>${list.decPreTax02}</td>
                <td>${list.decAfterTax02}</td>      
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
