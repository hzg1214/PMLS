<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;">
    <table class="table table-striped table-hover table-bordered">
            <tr>
            	<th style="width: 50px;"><input type="checkbox" onclick="swapCheck()" />
            	</th>
                <th style="width:150px">报备编号</th>
                <th style="width:120px">楼盘名称</th>
                <th style="width:150px">经纪公司</th>
                <th style="width:180px">门店地址</th>
                <th style="width:80px">客户</th>
                <th style="width:120px">客户手机号</th>
                <th style="width:100px">最新进度</th>
                <th style="width:150px">返佣对象</th>
                <th style="width:80px">操作</th>
            </tr>
            <c:forEach items="${contentlist}" var="list">
				<tr class="J_eatateItem" data-hidenumberflg="0">
					<td><input type="checkbox" value="${list.id}" class="selectReport" />
	            	</td>
					<td >${list.ReportId}</td>
					<input type="hidden" id="reportId${list.id}" name="reportId" value="${list.ReportId}">
					<input type="hidden" id="estateId${list.id}" name="estateId" value="${list.estateId}">
					<input type="hidden" id="projectNo${list.id}" name="projectNo" value="${list.projectNo}">
					<td title="${list.EstateNm}" class="text-overflow">${list.EstateNm}<input type="hidden" id="estateNo" value="${list.projectNo}"></td>
					<td title="${list.CompanyNm}" class="text-overflow">${list.CompanyNm}</td>
                    <td title="${list.AddressDetail}" class="text-overflow">${list.AddressDetail}</td>
                    <td title="${list.CustomerNm}" class="text-overflow">${list.CustomerNm}</td>
                    <td>${list.CustomerTel}</td>
                    <td>${list.dicValue}</td>
                    <td title="${list.CompanyName}" class="text-overflow">${list.CompanyName}</td>
					<td align="center">
						<div class="mailopre">
							<a href="${ctx}/lnkYjReport/getYjReportDetaileById/${list.ReportId}">查看</a>
							<c:if test="${list.brokerageStatus == 0 }">
								<a href="${ctx}/lnkYjReport/u/${list.ReportId}">编辑</a>
							</c:if>
						</div>
					</td>
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
