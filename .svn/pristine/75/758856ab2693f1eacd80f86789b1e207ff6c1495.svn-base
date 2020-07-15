<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto; overflow-x:scroll;">
    <table class="table table-striped table-hover table-bordered" style="font-size:12px;">
        <col style="width:25px">
		<col style="width:120px">
		<col style="width:100px">
		<col style="width:50px">
		<col style="width:25px">
		<col style="width:50px">
		<col style="width:50px">
		<col style="width:50px">
        <col style="width:50px">
        <tr>
            <th style="vertical-align: middle;">序号</th>
            <th style="vertical-align: middle">楼盘名</th>
            <th style="vertical-align: middle;">报备编号</th>
            <th style="vertical-align: middle;">楼室号</th>
            <th style="vertical-align: middle;">套数</th>
            <th style="vertical-align: middle;">成销面积(m<sup>2</sup>)</th>
            <th style="vertical-align: middle;">成销总价(元)</th>
            <th style="vertical-align: middle;">成销日期</th>
            <th style="vertical-align: middle;">结算确认日期</th>
		</tr>
        <c:forEach items="${contentlist}" var="list">
                <td>${list.rowNo}</td>
				<td title="${list.estateNm}" style="text-align: left;" class="text-overflow">${list.estateNm}</td>
				<td>${list.reportId}</td>
				<td>${list.buildingNo}</td>
				<td>${list.num}</td>
                <td>${list.area}</td>
                <td>${list.dealAmount}</td>
                <td>${list.dealDate}</td>
                <td>${list.settleConfirmDate}</td>
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
