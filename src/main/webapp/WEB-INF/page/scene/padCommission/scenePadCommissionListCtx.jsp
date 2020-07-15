<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;">
    <c:if test="${fn:length(contentlist) gt 0}">
    <div id="divReport" class="s-scoll-y" style="margin-bottom:20px;"></c:if>
        <c:if test="${fn:length(contentlist) le 0}">
        <div id="divReport" class="s-scoll-y" style="height: auto;"></c:if>
        <table id="padCommissionTbl" class="table table-striped table-hover table-bordered" style="width:3500px; font-size:12px;">
        <%--<col style="width:50px">--%>
        <col style="width:50px">
		<col style="width:150px">
		<col style="width:150px">
		<col style="width:150px"> 
		<col style="width:100px">
		<col style="width:100px">
		<col style="width:100px">
		<col style="width:100px">
        <col style="width:100px">

        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <col style="width:100px">
        <tr>
            <%--<th rowspan="2" style="vertical-align: middle;"><input type="checkbox" id="check-all"></th>--%>
            <th rowspan="2" style="vertical-align: middle;">序号</th>
            <%--<th rowspan="2" style="vertical-align: middle;">项目编号</th>--%>
            <th rowspan="2" style="vertical-align: middle;">楼盘名</th>   
            <th rowspan="2" style="vertical-align: middle;">报备编号</th>   
            <th rowspan="2" style="vertical-align: middle;">楼室号</th>   
            <th rowspan="2" style="vertical-align: middle;">套数</th>
            <th rowspan="2" style="vertical-align: middle;">大定总价</th>
            <th rowspan="2" style="vertical-align: middle;">大定日期</th>
            <th rowspan="2" style="vertical-align: middle;">成销总价</th>   
            <th rowspan="2" style="vertical-align: middle;">成销日期</th>
            <th colspan="2" style="vertical-align: middle;">应垫佣金小计</th>
            <th colspan="3" style="vertical-align: middle;">应垫佣金</th>
            <th colspan="3" style="vertical-align: middle;">应垫佣金调整1</th>
            <th colspan="3" style="vertical-align: middle;">应垫佣金调整2</th>
            <th colspan="3" style="vertical-align: middle;">应垫佣金调整3</th>
            <th colspan="2" style="vertical-align: middle;">实垫佣金小计</th>
            <th colspan="3" style="vertical-align: middle;">实垫佣金</th>
            <th colspan="3" style="vertical-align: middle;">实垫佣金调整1</th>
            <th colspan="3" style="vertical-align: middle;">实垫佣金调整2</th>
		</tr>
        <tr>
            <th style="vertical-align: middle;">税前</th>
            <th style="vertical-align: middle;">税后</th>
            <th style="vertical-align: middle;">税前</th>
            <th style="vertical-align: middle;">税后</th>
            <th style="vertical-align: middle;">日期</th>
            <th style="vertical-align: middle;">税前</th>
            <th style="vertical-align: middle;">税后</th>
            <th style="vertical-align: middle;">日期</th>
            <th style="vertical-align: middle;">税前</th>
            <th style="vertical-align: middle;">税后</th>
            <th style="vertical-align: middle;">日期</th>
            <th style="vertical-align: middle;">税前</th>
            <th style="vertical-align: middle;">税后</th>
            <th style="vertical-align: middle;">日期</th>
            <th style="vertical-align: middle;">税前</th>
            <th style="vertical-align: middle;">税后</th>
            <th style="vertical-align: middle;">税前</th>
            <th style="vertical-align: middle;">税后</th>
            <th style="vertical-align: middle;">日期</th>
            <th style="vertical-align: middle;">税前</th>
            <th style="vertical-align: middle;">税后</th>
            <th style="vertical-align: middle;">日期</th>
            <th style="vertical-align: middle;">税前</th>
            <th style="vertical-align: middle;">税后</th>
            <th style="vertical-align: middle;">日期</th>
        </tr>
        <c:forEach items="${contentlist}" var="list">
                <%--<td><input type="checkbox" class="check" data-id="${list.reportdyId}"></td>--%>
                <td>${list.rowNo}</td>
			    <%--<td>${list.projectNo}</td>--%>
				<td title="${list.estateNm}" class="text-overflow">${list.estateNm}</td>
				<td>${list.reportId}</td>
				<td>${list.buildingNo}</td>
				<td>${list.num}</td>
                <td>${list.roughAmount}</td>
                <td>${list.roughDate}</td>
                <td>${list.dealAmount}</td>
                <td>${list.dealDate}</td>

                <td>${list.befTaxYjAmountTotal}</td>
                <td>${list.aftTaxYjAmountTotal}</td>
                <td>${list.befTaxYjAmount}</td>
                <td>${list.aftTaxYjAmount}</td>
                <td>${list.yjRecordDate}</td>
                <td>${list.befTaxYjAmount1}</td>
                <td>${list.aftTaxYjAmount1}</td>
                <td>${list.yjRecordDate1}</td>
                <td>${list.befTaxYjAmount2}</td>
                <td>${list.aftTaxYjAmount2}</td>
                <td>${list.yjRecordDate2}</td>
                <td>${list.befTaxYjAmount3}</td>
                <td>${list.aftTaxYjAmount3}</td>
                <td>${list.yjRecordDate3}</td>

                <td>${list.befTaxSjAmountTotal}</td>
                <td>${list.aftTaxSjAmountTotal}</td>
                <td>${list.befTaxSjAmount}</td>
                <td>${list.aftTaxSjAmount}</td>
                <td>${list.sjRecordDate}</td>
                <td>${list.befTaxSjAmount1}</td>
                <td>${list.aftTaxSjAmount1}</td>
                <td>${list.sjRecordDate1}</td>
                <td>${list.befTaxSjAmount2}</td>
                <td>${list.aftTaxSjAmount2}</td>
                <td>${list.sjRecordDate2}</td>
			</tr>
		</c:forEach>  
    </table>
    </div>
</div>

${pageInfo.html}

<c:if test="${fn:length(contentlist) le 0}">
	<div class="nodata">
		<i class="icon-hdd"></i>
		<p>暂无数据...</p>
	</div>
</c:if>
