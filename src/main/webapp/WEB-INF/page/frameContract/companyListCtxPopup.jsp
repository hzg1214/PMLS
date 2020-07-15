<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;width:750px">
	<table class="table table-striped table-bordered table-hover">
		<col style="width:40px;">
		<col style="width:120px;">
		<col style="width:auto;">
		<col style="width:auto;">
		<tr>
			<th></th>
			<th>公司编号</th>
			<th>公司名称</th>
			<th style="display: none;">公司来源</th>
			<th style="display: none;">公司状态</th>
			<th>注册地址</th>
		</tr>

		<c:forEach items="${contentlist}" var="list">
			<tr class="J_eatateItem" data-hidenumberflg="0">
				<td><input type="radio" name="selectCompanyId"  id="selectCompanyId"
						data-companyNo="${list.companyNo}" 
						data-companyName="${list.companyName}" 
						data-companyAcCityNo="${list.acCityNo}" 
						data-companyCityNo="${list.cityNo}" 
						data-companyCityNm="${list.cityNm}" 
						data-companyDistrictNo="${list.districtNo}" 
						data-companyDistrictNm="${list.districtNm}" 
						data-companyAddress="${list.address}" 
						data-businessLicenseNo="${list.businessLicenseNo}" 
						data-companyLealPerson="${list.legalPerson}" 
						data-companyContactTel="${list.contactNumber}" 
						value="${list.id}">
					<input type="hidden" name="selectCompanyNo" value="${list.companyNo}">
					<input type="hidden" name="acCityNo" value="${list.acCityNo}">
				</td>
				<td>${list.companyNo}</td>
				<td style="text-align:left;" title="${list.companyName}">
					${list.companyName}
				</td>
				<td style="display: none;">${list.sourceName}</td>
				<td style="display:none">${list.cityNo}</td>
				<td style="display:none">${list.districtNo}</td>
				<td style="display:none">${list.areaNo}</td>
				<td style="display:none">${list.address}</td>
				<td style="display:none">${list.cityName}</td>
				<td style="display:none">${list.districtName}</td>
				<td style="display:none">${list.areaName}</td>
				<td style="display:none">${list.contractType}</td>
				<td style="display:none">${list.contactsPhone}</td><!-- 中介联系电话 -->
				<td style="display: none;">${list.companyStatusName}</td>
				<td style="text-align:left;" title="${list.cityNm}${list.districtNm}<%-- ${list.areaName} --%>${list.address}">
					${list.cityNm}${list.districtNm}${list.address}
				</td>
				<td style="display:none">${list.businessLicenseNo}</td>
				<td style="display:none">${list.legalPerson}</td>				
				<td style="display:none">${list.acCityNo}</td>
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