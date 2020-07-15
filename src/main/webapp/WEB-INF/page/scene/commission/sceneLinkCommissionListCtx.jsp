<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;">
    <c:if test="${fn:length(contentlist) gt 0}">
    <div id="divReport" class="s-scoll-y" style="margin-bottom:20px;"></c:if>
        <c:if test="${fn:length(contentlist) le 0}">
        <div id="divReport" class="s-scoll-y" style="height: auto;"></c:if>
        <table id="YSSRCommissionTbl" class="table table-striped table-hover table-bordered" style="width:2500px; font-size:12px;">
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
            <col style="width:100px"><%--成销日期--%>

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
                <c:if test="${estateType=='24501'}">
                    <th colspan="2" style="vertical-align: middle;">应收收入小计</th>
                    <th colspan="3" style="vertical-align: middle;">应收收入</th>
                    <th colspan="3" style="vertical-align: middle;">应收收入调整1</th>
                    <th colspan="3" style="vertical-align: middle;">应收收入调整2</th>
                    <th colspan="3" style="vertical-align: middle;">应收收入调整3</th>
                    <th colspan="3" style="vertical-align: middle;">应收收入调整4</th>
                    <th colspan="3" style="vertical-align: middle;">应收收入调整5</th>
                    <th colspan="3" style="vertical-align: middle;">应收收入调整6</th>
                </c:if>
                <c:if test="${estateType=='24502'}">
                    <th colspan="2" style="vertical-align: middle;">应计收入小计</th>
                    <th colspan="3" style="vertical-align: middle;">应计收入</th>
                    <th colspan="3" style="vertical-align: middle;">应计收入调整1</th>
                    <th colspan="3" style="vertical-align: middle;">应计收入调整2</th>
                    <th colspan="3" style="vertical-align: middle;">应计收入调整3</th>
                    <th colspan="3" style="vertical-align: middle;">应计收入调整4</th>
                    <th colspan="3" style="vertical-align: middle;">应计收入调整5</th>
                    <th colspan="3" style="vertical-align: middle;">应计收入调整6</th>
                </c:if>
                <c:if test="${estateType=='24503'}">
                    <th colspan="2" style="vertical-align: middle;">应计返佣小计</th>
                    <th colspan="3" style="vertical-align: middle;">应计返佣</th>
                    <th colspan="3" style="vertical-align: middle;">应计返佣调整1</th>
                    <th colspan="3" style="vertical-align: middle;">应计返佣调整2</th>
                    <th colspan="3" style="vertical-align: middle;">应计返佣调整3</th>
                    <th colspan="3" style="vertical-align: middle;">应计返佣调整4</th>
                    <th colspan="3" style="vertical-align: middle;">应计返佣调整5</th>
                    <th colspan="3" style="vertical-align: middle;">应计返佣调整6</th>
                </c:if>
                <c:if test="${estateType=='24504'}">
                    <th colspan="2" style="vertical-align: middle;">应计实收小计</th>
                    <th colspan="3" style="vertical-align: middle;">应计实收</th>
                    <th colspan="3" style="vertical-align: middle;">应计实收调整1</th>
                    <th colspan="3" style="vertical-align: middle;">应计实收调整2</th>
                    <th colspan="3" style="vertical-align: middle;">应计实收调整3</th>
                    <th colspan="3" style="vertical-align: middle;">应计实收调整4</th>
                    <th colspan="3" style="vertical-align: middle;">应计实收调整5</th>
                    <th colspan="3" style="vertical-align: middle;">应计实收调整6</th>
                </c:if>
                <c:if test="${estateType=='24505'}">
                    <th colspan="2" style="vertical-align: middle;">实际返佣小计</th>
                    <th colspan="3" style="vertical-align: middle;">实际返佣</th>
                    <th colspan="3" style="vertical-align: middle;">实际返佣调整1</th>
                    <th colspan="3" style="vertical-align: middle;">实际返佣调整2</th>
                    <th colspan="3" style="vertical-align: middle;">实际返佣调整3</th>
                    <th colspan="3" style="vertical-align: middle;">实际返佣调整4</th>
                    <th colspan="3" style="vertical-align: middle;">实际返佣调整5</th>
                    <th colspan="3" style="vertical-align: middle;">实际返佣调整6</th>
                </c:if>
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
            <th style="vertical-align: middle;">日期</th>
            <th style="vertical-align: middle;">税前</th>
            <th style="vertical-align: middle;">税后</th>
            <th style="vertical-align: middle;">日期</th>
            <th style="vertical-align: middle;">税前</th>
            <th style="vertical-align: middle;">税后</th>
            <th style="vertical-align: middle;">日期</th>
        </tr>
        <c:forEach items="${contentlist}" var="list">
            <c:if test="${list.num ge 0}">
                <tr>
            </c:if>
            <c:if test="${list.num lt 0}">
                <tr style="color:#f00">
            </c:if>
                <%--<td><input type="checkbox" class="check" data-id="${list.reportdyId}"></td>--%>
                <td>${list.rowNo}</td>
                <td>${list.projectNo}</td>
				<td title="${list.estateNm}" class="text-overflow">${list.estateNm}</td>
				<td>${list.reportId}</td>
                <td>${list.companyNo}</td>
                <td>${list.companyName}</td>
				<td>${list.buildingNo}</td>
            <c:if test="${empty list.defaultFlag || list.defaultFlag==0}">
                <td>${list.num}</td>
                <td>${list.dealAmount}</td>
            </c:if>
            <c:if test="${list.defaultFlag==1}">
                <td></td>
                <td></td>
            </c:if>

                <td>${list.dealDate}</td>
                <td>${list.inComeName}</td>

                <td>${list.befTaxAmountTotal}</td>
                <td>${list.aftTaxAmountTotal}</td>
                <td>${list.befTaxAmount}</td>
                <td>${list.aftTaxAmount}</td>
                <td>${list.recordDate}</td>
                <td>${list.befTaxAmount1}</td>
                <td>${list.aftTaxAmount1}</td>
                <td>${list.recordDate1}</td>
                <td>${list.befTaxAmount2}</td>
                <td>${list.aftTaxAmount2}</td>
                <td>${list.recordDate2}</td>
                <td>${list.befTaxAmount3}</td>
                <td>${list.aftTaxAmount3}</td>
                <td>${list.recordDate3}</td>
                <td>${list.befTaxAmount4}</td>
                <td>${list.aftTaxAmount4}</td>
                <td>${list.recordDate4}</td>
                <td>${list.befTaxAmount5}</td>
                <td>${list.aftTaxAmount5}</td>
                <td>${list.recordDate5}</td>
                <td>${list.befTaxAmount6}</td>
                <td>${list.aftTaxAmount6}</td>
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
