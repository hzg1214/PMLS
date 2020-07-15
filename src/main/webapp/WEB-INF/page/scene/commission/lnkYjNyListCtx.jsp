<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;">
    <c:if test="${fn:length(contentlist) gt 0}">
    <div id="divReport" class="s-scoll-y" style="margin-bottom:20px;"></c:if>
        <c:if test="${fn:length(contentlist) le 0}">
        <div id="divReport" class="s-scoll-y" style="height: auto;"></c:if>
        <table id="lnkYjNyListDataTable" class="table table-striped table-hover table-bordered" style="width:2600px; font-size:12px;">
        <%--<col style="width:50px">--%>
        <col style="width:50px"><%--序号--%>
        <col style="width:90px"><%--项目编号--%>
        <col style="width:150px"><%--项目名称--%>
        <col style="width:150px"><%--报备编号--%>
        <col style="width:90px"><%--经纪公司编号--%>
        <col style="width:200px"><%--经纪公司--%>
        <col style="width:150px"><%--楼室号--%>
        <col style="width:50px"><%--套数--%>
        <col style="width:100px"><%--成销总价--%>
        <col style="width:100px"><%--成销日期--%>
            <col style="width:100px"><%--收入类型--%>

        <col style="width:100px"><%--税前--%>
        <col style="width:100px"><%--税后--%>
        <col style="width:100px"><%--税前--%>
        <col style="width:100px"><%--税后--%>
        <col style="width:100px"><%--日期--%>
        <col style="width:100px"><%--税前--%>
        <col style="width:100px"><%--税后--%>
        <col style="width:100px"><%--日期--%>
        <col style="width:100px"><%--税前--%>
        <col style="width:100px"><%--税后--%>
        <col style="width:100px"><%--日期--%>
        <col style="width:100px"><%--税前--%>
        <col style="width:100px"><%--税后--%>
        <col style="width:100px"><%--日期--%>
        <col style="width:100px"><%--税前--%>
        <col style="width:100px"><%--税后--%>
        <col style="width:100px"><%--日期--%>
        <col style="width:100px"><%--税前--%>
        <col style="width:100px"><%--税后--%>
        <col style="width:100px"><%--日期--%>
        <col style="width:100px"><%--税前--%>
        <col style="width:100px"><%--税后--%>
        <col style="width:100px"><%--日期--%>
        <tr>
            <%--<th rowspan="2" style="vertical-align: middle;"><input type="checkbox" id="check-all"></th>--%>
            <th rowspan="2" style="vertical-align: middle;">序号</th>
            <th rowspan="2" style="vertical-align: middle;">项目编号</th>
            <th rowspan="2" style="vertical-align: middle;">项目名称</th>
            <th rowspan="2" style="vertical-align: middle;">报备编号</th>
            <th rowspan="2" style="vertical-align: middle;">经纪公司编号</th>
            <th rowspan="2" style="vertical-align: middle;">经纪公司</th>
            <th rowspan="2" style="vertical-align: middle;">楼室号</th>   
            <th rowspan="2" style="vertical-align: middle;">套数</th>
            <th rowspan="2" style="vertical-align: middle;">成销总价</th>   
            <th rowspan="2" style="vertical-align: middle;">成销日期</th>
                <th rowspan="2" style="vertical-align: middle;">收入类型</th>
            <th colspan="2" style="vertical-align: middle;">应计内佣小计</th>
            <th colspan="3" style="vertical-align: middle;">应计内佣</th>
            <th colspan="3" style="vertical-align: middle;">应计内佣调整1</th>
            <th colspan="3" style="vertical-align: middle;">应计内佣调整2</th>
            <th colspan="3" style="vertical-align: middle;">应计内佣调整3</th>
            <th colspan="3" style="vertical-align: middle;">应计内佣调整4</th>
            <th colspan="3" style="vertical-align: middle;">应计内佣调整5</th>
            <th colspan="3" style="vertical-align: middle;">应计内佣调整6</th>
		</tr>
        <tr>
            <th style="vertical-align: middle;">岗位佣金</th>
            <th style="vertical-align: middle;">团佣</th>
            <th style="vertical-align: middle;">岗位佣金</th>
            <th style="vertical-align: middle;">团佣</th>
            <th style="vertical-align: middle;">日期</th>
            <th style="vertical-align: middle;">岗位佣金</th>
            <th style="vertical-align: middle;">团佣</th>
            <th style="vertical-align: middle;">日期</th>
            <th style="vertical-align: middle;">岗位佣金</th>
            <th style="vertical-align: middle;">团佣</th>
            <th style="vertical-align: middle;">日期</th>
            <th style="vertical-align: middle;">岗位佣金</th>
            <th style="vertical-align: middle;">团佣</th>
            <th style="vertical-align: middle;">日期</th>
            <th style="vertical-align: middle;">岗位佣金</th>
            <th style="vertical-align: middle;">团佣</th>
            <th style="vertical-align: middle;">日期</th>
            <th style="vertical-align: middle;">岗位佣金</th>
            <th style="vertical-align: middle;">团佣</th>
            <th style="vertical-align: middle;">日期</th>
            <th style="vertical-align: middle;">岗位佣金</th>
            <th style="vertical-align: middle;">团佣</th>
            <th style="vertical-align: middle;">日期</th>
        </tr>
        <c:forEach items="${contentlist}" var="list">
            <c:if test="${list.num eq -1}">
                <tr style="color:red;">
            </c:if>
            <c:if test="${list.num eq 1}">
                <tr>
            </c:if>
                <%--<td><input type="checkbox" class="check" data-id="${list.reportdyId}"></td>--%>
                <td>${list.rowNo}</td>
                <td>${list.projectNo}</td>
				<td title="${list.estateNm}" class="text-overflow">${list.estateNm}</td>
				<td>${list.reportId}</td>
                <td>${list.companyNo}</td>
                <td>${list.companyName}</td>
				<td>${list.buildingNo}</td>
				<td>${list.num}</td>
                <td>${list.dealAmount}</td>
                <td>${list.dealDate}</td>
            <td>${list.incomeStatusStr}</td>

                <td>${list.postAmountTotal}</td>
                <td>${list.totalAmountTotal}</td>
                <td>${list.postAmount}</td>
                <td>${list.totalAmount}</td>
                <td>${list.recordDate}</td>
                <td>${list.postAmount1}</td>
                <td>${list.totalAmount1}</td>
                <td>${list.recordDate1}</td>
                <td>${list.postAmount2}</td>
                <td>${list.totalAmount2}</td>
                <td>${list.recordDate2}</td>
                <td>${list.postAmount3}</td>
                <td>${list.totalAmount3}</td>
                <td>${list.recordDate3}</td>
                <td>${list.postAmount4}</td>
                <td>${list.totalAmount4}</td>
                <td>${list.recordDate4}</td>
                <td>${list.postAmount5}</td>
                <td>${list.totalAmount5}</td>
                <td>${list.recordDate5}</td>
                <td>${list.postAmount6}</td>
                <td>${list.totalAmount6}</td>
                <td>${list.recordDate6}</td>
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
