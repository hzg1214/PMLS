<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<c:if test="${fn:length(contentlist) gt 0}"><div id="tblStoreDespositSerial" class="s-scoll-y" style="height: auto;"></c:if>
<c:if test="${fn:length(contentlist) le 0}"><div id="tblStoreDespositSerial" class="s-scoll-y" style="height: auto;"></c:if>
<table id="tblStoreDespositSerial" class="table table-striped table-hover table-bordered" style="width:2000px; font-size:12px;">
    <tbody>
    <tr style="text-align: center;font-weight: bolder;">
        <td style="vertical-align: middle;width:60px" >序号</td>
        <th style="vertical-align: middle;width:80px">城市</th>
        <th style="vertical-align: middle;width:160px">核算主体</th>
        <th style="vertical-align: middle;width:100px">门店编号</th>
        <th style="vertical-align: middle;width:160px">门店名称</th>
        
        <th style="vertical-align: middle;width:110px">门店所属中心</th>
        <th style="vertical-align: middle;width:110px">经纪公司编号</th>
        <th style="vertical-align: middle;width:180px">经纪公司</th>
        <th style="vertical-align: middle;width:160px">协议书编号</th>
        <th style="vertical-align: middle;width:160px">OA单号</th>
        <th style="vertical-align: middle;width:130px">供应商编号</th>
        <th style="vertical-align: middle;width:180px">供应商名称</th>
        
        <th style="vertical-align: middle;width:100px">实收金额</th>
        <th style="vertical-align: middle;width:100px">实付金额</th>
        <th style="vertical-align: middle;width:80px">单据类型</th>
    </tr>

    <c:forEach items="${contentlist}" var="list" varStatus="status">
        <tr>
            <td>${(pageInfo.curPage-1)*10+status.count}</td>
            <td title="${list.cityName}" class="text-overflow">${list.cityName}</td>
            <td title="${list.accountProject}" class="text-overflow">${list.accountProject}</td>
            <td title="${list.storeNo}" class="text-overflow">${list.storeNo}</td>
            <td title="${list.storeName}" class="text-overflow">${list.storeName}</td>
            <td title="${list.groupName}" class="text-overflow">${list.groupName}</td>
            
            <td title="${list.companyNo}" class="text-overflow">${list.companyNo}</td>
            <td title="${list.companyName}" class="text-overflow">${list.companyName}</td>
            <td title="${list.agreementNo}" class="text-overflow">${list.agreementNo}</td>
            <td title="${list.oaNo}" class="text-overflow">${list.oaNo}</td>
            <td title="${list.providerCode}" class="text-overflow">${list.providerCode}</td>
            <td title="${list.providerName}" class="text-overflow">${list.providerName}</td>
            
            <td>
            	<fmt:formatNumber value="${list.receiveAmout}" pattern="#,##0.00" maxFractionDigits="2"/>
            </td>
            <td>
            	<fmt:formatNumber value="${list.paymentAmout}" pattern="#,##0.00" maxFractionDigits="2"/>
            </td>
            <td title="${list.orderTypeVal}" class="text-overflow">${list.orderTypeVal}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>

${pageInfo.html}

<c:if test="${fn:length(contentlist) le 0}">
    <div class="nodata">
        <i class="icon-hdd"></i>
        <p>暂无数据...</p>
    </div>
</c:if>
