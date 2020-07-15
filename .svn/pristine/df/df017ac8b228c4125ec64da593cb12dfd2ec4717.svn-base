<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<c:if test="${fn:length(contentlist) gt 0}"><div id="tblStoreDespositSerial" class="s-scoll-y" style="height: auto;"></c:if>
<c:if test="${fn:length(contentlist) le 0}"><div id="tblStoreDespositSerial" class="s-scoll-y" style="height: auto;"></c:if>
<style type="text/css">
	.text-overflow {
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		width: 100%;
	}
</style>
<table id="tblStoreDespositSerial" class="table table-striped table-hover table-bordered" style="width:4460px; font-size:12px;">
    <tbody>
	 <tr style="text-align: center;font-weight: bolder;">
            <th rowspan="3" style="vertical-align: middle;width:60px" >序号</th>
            <th rowspan="3" style="vertical-align: middle;width:110px">项目归属区域</th>
            <th rowspan="3" style="vertical-align: middle;width:110px">项目归属城市</th>
            <th rowspan="3" style="vertical-align: middle;width:110px">项目所在城市</th>
            <th rowspan="3" style="vertical-align: middle;width:130px">项目归属部门</th>
            <th rowspan="3" style="vertical-align: middle;width:110px">报备归属城市</th>
            <th rowspan="3" style="vertical-align: middle;width:110px">报备归属中心</th>
            <th rowspan="3" style="vertical-align: middle;width:110px">项目编号</th>
            <th rowspan="3" style="vertical-align: middle;width:160px">项目名称</th>
            <th rowspan="3" style="vertical-align: middle;width:110px">公司编号</th>
            <th rowspan="3" style="vertical-align: middle;width:200px">公司名称</th>
            <th rowspan="3" style="vertical-align: middle;width:110px">门店编号</th>
            <th rowspan="3" style="vertical-align: middle;width:200px">门店名称</th>
            <th rowspan="3" style="vertical-align: middle;width:110px">经服/渠道</th>
            
            <th rowspan="3" style="vertical-align: middle;width:110px">门店类别</th>
            <th rowspan="3" style="vertical-align: middle;width:110px">门店区域</th>
            
            
            <c:if test="${empty startDate || empty endDate}">
            	<th colspan="8">当期累计</th>
            </c:if>
            <c:if test="${not empty startDate &&not empty endDate}">
            	<th colspan="8">当期累计(${startDate}至${endDate})</th>
            </c:if>
            <th colspan="8">当年累计</th>
            <th colspan="8">总累计</th>
        </tr>

        <tr>
            <th rowspan="2" style="vertical-align: middle;width:80px">报备</th>
            <th rowspan="2" style="vertical-align: middle;">带看</th>
            <th colspan="3" style="vertical-align: middle;">大定</th>
            <th colspan="3" style="vertical-align: middle;">成销</th>
            
            <th rowspan="2" style="vertical-align: middle;">报备</th>
            <th rowspan="2" style="vertical-align: middle;">带看</th>
            <th colspan="3" style="vertical-align: middle;">大定</th>
            <th colspan="3" style="vertical-align: middle;">成销</th>
            
            <th rowspan="2" style="vertical-align: middle;">报备</th>
            <th rowspan="2" style="vertical-align: middle;">带看</th>
            <th colspan="3" style="vertical-align: middle;">大定</th>
            <th colspan="3" style="vertical-align: middle;">成销</th>
           
        </tr>

        <tr>
            <th style="vertical-align: middle;">套数</th>
            <th style="vertical-align: middle;">面积</th>
            <th style="vertical-align: middle;">金额</th>
            <th style="vertical-align: middle;">套数</th>
            <th style="vertical-align: middle;">面积</th>
            <th style="vertical-align: middle;">金额</th>
            
            <th style="vertical-align: middle;">套数</th>
            <th style="vertical-align: middle;">面积</th>
            <th style="vertical-align: middle;">金额</th>
            <th style="vertical-align: middle;">套数</th>
            <th style="vertical-align: middle;">面积</th>
            <th style="vertical-align: middle;">金额</th>
            
            <th style="vertical-align: middle;">套数</th>
            <th style="vertical-align: middle;">面积</th>
            <th style="vertical-align: middle;">金额</th>
            <th style="vertical-align: middle;">套数</th>
            <th style="vertical-align: middle;">面积</th>
            <th style="vertical-align: middle;">金额</th>
            
        </tr>
    <c:forEach items="${contentlist}" var="list" varStatus="status">
        <tr>
            <td>${(pageInfo.curPage-1)*10+status.count}</td>
            <td title="${list.regionName}" class="text-overflow">${list.regionName}</td>
			<td title="${list.areaCityName}" class="text-overflow">${list.areaCityName}</td>
			<td title="${list.cityName}" class="text-overflow">${list.cityName}</td>
			<td title="${list.centerGroupName}" class="text-overflow">${list.centerGroupName}</td>
			<td title="${list.cityGroupName}" class="text-overflow">${list.cityGroupName}</td>
			<td title="${list.performCenterName}" class="text-overflow">${list.performCenterName}</td>
			<td title="${list.projectNo}" class="text-overflow">${list.projectNo}</td>
			<td title="${list.projectName}" class="text-overflow">${list.projectName}</td>
			<td title="${list.companyId}" class="text-overflow">${list.companyId}</td>
			<td title="${list.companyNm}" class="text-overflow">${list.companyNm}</td>
			<td title="${list.storeNo}" class="text-overflow">${list.storeNo}</td>
			<td title="${list.storeName}" class="text-overflow">${list.storeName}</td>
			<td title="${list.expenderName}" class="text-overflow">${list.expenderName}</td>
			<td title="${list.storeType}" class="text-overflow">${list.storeType}</td>
			<td title="${list.districtName}" class="text-overflow">${list.districtName}</td>
           <!--  当期 -->
            <td>
				<fmt:formatNumber value="${list.dq_bbNum}" pattern="#,##0" maxFractionDigits="0"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.dq_dkNum}" pattern="#,##0" maxFractionDigits="0"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.dq_ddNum}" pattern="#,##0" maxFractionDigits="0"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.dq_ddArea}" pattern="#,##0.00" maxFractionDigits="2"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.dq_ddAmount}" pattern="#,##0.00" maxFractionDigits="2"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.dq_cxNum}" pattern="#,##0" maxFractionDigits="0"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.dq_cxArea}" pattern="#,##0.00" maxFractionDigits="2"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.dq_cxAmount}" pattern="#,##0.00" maxFractionDigits="2"/>
			</td>
			<!-- 当年 -->
			<td>
				<fmt:formatNumber value="${list.dn_bbNum}" pattern="#,##0" maxFractionDigits="0"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.dn_dkNum}" pattern="#,##0" maxFractionDigits="0"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.dn_ddNum}" pattern="#,##0" maxFractionDigits="0"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.dn_ddArea}" pattern="#,##0.00" maxFractionDigits="2"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.dn_ddAmount}" pattern="#,##0.00" maxFractionDigits="2"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.dn_cxNum}" pattern="#,##0" maxFractionDigits="0"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.dn_cxArea}" pattern="#,##0.00" maxFractionDigits="2"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.dn_cxAmount}" pattern="#,##0.00" maxFractionDigits="2"/>
			</td>
			<!-- 总累计 -->
			<td>
				<fmt:formatNumber value="${list.lj_bbNum}" pattern="#,##0" maxFractionDigits="0"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.lj_dkNum}" pattern="#,##0" maxFractionDigits="0"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.lj_ddNum}" pattern="#,##0" maxFractionDigits="0"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.lj_ddArea}" pattern="#,##0.00" maxFractionDigits="2"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.lj_ddAmount}" pattern="#,##0.00" maxFractionDigits="2"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.lj_cxNum}" pattern="#,##0" maxFractionDigits="0"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.lj_cxArea}" pattern="#,##0.00" maxFractionDigits="2"/>
			</td>
			<td>
				<fmt:formatNumber value="${list.lj_cxAmount}" pattern="#,##0.00" maxFractionDigits="2"/>
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
