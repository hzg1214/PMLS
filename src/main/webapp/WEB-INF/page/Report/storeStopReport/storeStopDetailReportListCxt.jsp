<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto; overflow-x:scroll;">
    <table class="table table-striped table-hover table-bordered" style="width:6000px; font-size:12px;">
            <tr style="text-align: center;font-weight: bolder;">
             <td rowspan="2" style="vertical-align: middle;width:50px;">序号</td>
             <td colspan="5">业绩信息</td>
             <td colspan="7">公司信息</td>
             <td colspan="8">门店信息</td>
             <td colspan="9">合同信息</td>
             <td colspan="6">保证金信息</td>
			</tr>
            
            <tr>
                <%--<th style="display:none"><input value="checkbox" type="checkbox"></th>--%>
               	<!--业绩信息-->
				<th >业绩归属城市</th>
                <th >业绩归属事业部</th>
                <th >业绩归属中心</th>                
                <th >业绩归属组</th>
                <th >业绩归属人</th>
				<!--公司信息-->
                <th>公司编号</th>
                <th>公司名称</th>
                <th>公司经营地址</th>
				<th>法人</th>
                <th style="width:130px;">公司注册地址</th>
                <th>营业执照编码</th>            
                <th>营业执照性质</th>
				<!--门店信息-->
                <th>门店编号</th>
                <th>挂牌名称</th>
                <th>所属行政区</th> 
                <th>门店所属中心</th> 
                <th>门店地址</th>
                <th>门店资质等级</th>
                <th>门店数</th>
                <th>终止原因</th>
				<!--合同信息-->
                <th>合同编号</th>
                <th>合作模式</th>
                <th>协议书编号</th>
                <th>合作开始日期</th>
                <th>合作到期日期</th>
                <th>草签日期</th>
                <th>OA审核通过日期</th>
                <th>OA审核终止日期</th>  
                <th>合同审核状态</th>
				<!--保证金信息-->
				<th>应收金额</th>
                <th>到账金额</th>
                <th>未收金额</th>
                <th>到账日期</th>
                <th>退款金额</th>
                <th>退款日期</th>
			</tr>
            <c:forEach items="${reportlist}" var="list"  varStatus="status">
				<c:if test="${list.storeCnt == -1}" ><tr class="J_eatateItem" data-hidenumberflg="0" style="color: red"></c:if>
		        <c:if test="${list.storeCnt == 1}" ><tr class="J_eatateItem" data-hidenumberflg="0"></c:if>
					<!-- (当前页-1*每页显示数)+从1开始自增 -->
					<!--业绩信息-->
					<td style="width: 50px;">${list.rowNum}</td>
					<td>${list.performanceCity}</td>
					<td style="text-align:center;">${list.performanceDepartment}</td>
					<td style="text-align:center;">${list.performanceCenter}</td>
					<td>${list.performanceGroup}</td>
					<td>${list.performancePeople}</td>
					<!--公司信息-->
					<td>${list.companyNo}</td>
					<td style="text-align:center;" title="${list.companyName}">
						${fn:substring(list.companyName, 0, 5)}
						<c:if test="${fn:length(list.companyName) >= '5'}">
							...
						</c:if>
					</td>
					<td style="text-align:center;" title="${list.address}">
						${fn:substring(list.address, 0, 5)}
						<c:if test="${fn:length(list.address) >= '5'}">
							...
						</c:if>
					</td>
					<td>${list.legalPerson}</td>
					<td style="text-align:center;" title="${list.businessLicenseCompanyAddress}">
						${fn:substring(list.businessLicenseCompanyAddress, 0, 5)}
						<c:if test="${fn:length(list.businessLicenseCompanyAddress) >= '5'}">
							...
						</c:if>
					</td>
					<td style="text-align:center;" title="${list.businessLicenseNo}">
						${fn:substring(list.businessLicenseNo, 0, 5)}
						<c:if test="${fn:length(list.businessLicenseNo) >= '5'}">
							...
						</c:if>
					</td>
					<td>${list.businessLicenseType }</td>
					<!--门店信息-->
					<td>${list.storeNo}</td>
					<td style="text-align:center;" title="${list.storeName}">
						${fn:substring(list.storeName, 0, 5)}
						<c:if test="${fn:length(list.storeName) >= '5'}">
							...
						</c:if>
					</td>
					<td>${list.districtName}</td>
					<td>${list.storeGroupName}</td>
					<td style="text-align:center;" title="${list.addressDetail}">
						${fn:substring(list.addressDetail, 0, 5)}
						<c:if test="${fn:length(list.addressDetail) >= '5'}">
							...
						</c:if>
					</td>
					<td>${list.abtypeStore}</td>
					<td>${list.storeCnt }</td>
					<td>${list.sortName }</td>
					<!--合同信息-->
					<td>${list.contractNo}</td>
					<td>${list.cooperateMode }</td>
					<td>${list.agreementNo }</td>
					<td>${list.dateLifeStart }</td>
					<td>${list.dateLifeEnd }</td>
					<td>${list.dateCreate }</td>
					<td>${list.performDate }</td>
					<td>${list.dateUpdate }</td>
					<td>${list.contractCheckStatusName }</td>
					<!--保证金信息-->
					<td>${list.depositFee }</td>
					<td>${list.arrivalDepositFee }</td>
					<td>${list.uncollectedAccount }</td>
					<td>${list.dateArrivalAct }</td>
					<td>${list.refundAmount }</td>
					<td>${list.refundDate }</td>
			</c:forEach>
    </table>
</div>

${pageInfo.html}

<c:if test="${fn:length(reportlist) le 0}">
	<div class="nodata">
		<i class="icon-hdd"></i>
		<p>暂无数据...</p>
	</div>
</c:if>
