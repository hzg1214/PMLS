<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<c:if test="${fn:length(contentlist) gt 0}"><div id="tbCompanyInfoDetail" class="s-scoll-y" style="height: auto;"></c:if>
<c:if test="${fn:length(contentlist) le 0}"><div id="tbCompanyInfoDetail" class="s-scoll-y" style="height: auto;"></c:if>
<table id="tbCompanyInfoDetail" class="table table-striped table-hover table-bordered" style="width:2000px; font-size:12px;">
    <tbody>
    <tr style="text-align: center;font-weight: bolder;">
        <td style="vertical-align: middle;width:50px" >序号</td>
        
        <th style="vertical-align: middle;width:80px">归属城市</th>
        <th style="vertical-align: middle;width:150px">公司名称</th>
        <th style="vertical-align: middle;width:160px">公司注册地址</th>
        <th style="vertical-align: middle;width:100px">公司经营地址</th>
        <th style="vertical-align: middle;width:80px">法人</th>
        
        <th style="vertical-align: middle;width:80px">成立年限</th>
        <th style="vertical-align: middle;width:160px">统一社会信用代码</th>
        <th style="vertical-align: middle;width:80px">对接人</th>
        <th style="vertical-align: middle;width:100px">对接人电话</th>
        <th style="vertical-align: middle;width:80px">门店数量</th>
        
        <th style="vertical-align: middle;width:80px">员工数量</th>
        <th style="vertical-align: middle;width:100px">渠道分类</th>
        <th style="vertical-align: middle;width:160px">可承接项目类型</th>
        <th style="vertical-align: middle;width:120px">资源覆盖范围</th>
        <th style="vertical-align: middle;width:120px">特有资源</th>
        
        <th style="vertical-align: middle;width:120px">一二手联动规模</th>
        <th style="vertical-align: middle;width:120px">合作密切开发商</th>
        
        <th style="vertical-align: middle;width:160px">与我司合作项目数量</th>
        <th style="vertical-align: middle;width:130px">与我司合作大定业绩（万元）</th>
        <th style="vertical-align: middle;width:130px">与我司合作成销业绩（万元）</th>
    </tr>

    <c:forEach items="${contentlist}" var="list" varStatus="status">
        <tr>
            <td>${(pageInfo.curPage-1)*10+status.count}</td>
            
            <td title="${list.cityName}" class="text-overflow">${list.cityName}</td>
            <td title="${list.companyName}" class="text-overflow">${list.companyName}</td>
            <td title="${list.addressDetail}" class="text-overflow">${list.addressDetail}</td>
            <td title="${list.companyAddress}" class="text-overflow">${list.companyAddress}</td>
            <td title="${list.legalPerson}" class="text-overflow">${list.legalPerson}</td>
            
            <td title="${list.establishYear}" class="text-overflow">${list.establishYear}</td>
            <td title="${list.businessLicenseNo}" class="text-overflow">${list.businessLicenseNo}</td>
            <td title="${list.dockingPeo}" class="text-overflow">${list.dockingPeo}</td>
            <td title="${list.dockingPeoTel}" class="text-overflow">${list.dockingPeoTel}</td>
            <td title="${list.storeNumber}" class="text-overflow">${list.storeNumber}</td>
            
            <td title="${list.comStaffNum}" class="text-overflow">${list.comStaffNum}</td>
            <td title="${list.channelTypeName}" class="text-overflow">${list.channelTypeName}</td>
            <td title="${list.undertakeTypeName}" class="text-overflow">${list.undertakeTypeName}</td>
            <td title="${list.resourcesRange}" class="text-overflow">${list.resourcesRange}</td>
            <td title="${list.specificResources}" class="text-overflow">${list.specificResources}</td>
            
            <td title="${list.lnkScaleName}" class="text-overflow">${list.lnkScaleName}</td>
            <td title="${list.closeDeveloper}" class="text-overflow">${list.closeDeveloper}</td>
            
            <td title="${list.cooperationNum}" class="text-overflow">${list.cooperationNum}</td>
            <td>
            	<fmt:formatNumber value="${list.roughAmount}" pattern="#,##0.00" maxFractionDigits="2"/>
            </td>
            <td>
            	<fmt:formatNumber value="${list.dealAmount}" pattern="#,##0.00" maxFractionDigits="2"/>
            </td>
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
