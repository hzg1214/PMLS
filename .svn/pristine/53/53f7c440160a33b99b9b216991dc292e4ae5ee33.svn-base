<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;">
<table class="table table-striped table-hover table-bordered">
    <tr>
        <th style="width: 145px;">退款编号</th>
        <th style="width: 130px;">合同编号</th>
        <th style="width:265px;">经纪公司</th>
        <th style="width:120px;">退款金额（元）</th>
        <th style="width:100px;">申请人</th>
        <th style="width: 110px;">申请时间</th>
        <th style="width:130px;">操作</th>
    </tr>

    <c:forEach items="${contentlist}" var="list">
        <tr>
            <td style="text-align:center;" title="${list.paymentNo}" class="text-overflow">
				<a href="${ctx}/storePayment/${list.id}">${list.paymentNo}</a>
			</td>
            <td style="text-align:center;" title="${list.contractNo}"  class="text-overflow">
				${list.contractNo}
			</td>
            <td style="text-align:left;" title="${list.companyName}"  class="text-overflow">
				${list.companyName}
			</td>
            <td style="text-align:center;" title="${list.totalAmount}">
				<fmt:formatNumber type="number" value='${list.totalAmount}' pattern="0.00" maxFractionDigits="2"/>
			</td>
            <td style="text-align:center;" title="${list.userName}" class="text-overflow">
				${list.userName}
			</td>
            <td style="text-align:center;" title="${list.dateCreate}">
				<%-- ${list.dateCreate} --%>
				${sdk:ymd2(list.dateCreate)}
			</td>
            <td align="center">
				<div class="mailopre">
					<a href="${ctx}/storePayment/${list.id}">查看</a>
					<%-- <a href="javascript:void(0)" onclick="removeTr(${list.id});">删除</a> --%>
					<shiro:hasPermission name="/storePayment:SUBMIT_OA">
						<c:choose>
	                      <c:when test="${list.paymentType==17909}">
	                         <c:if test="${list.submitOaStatus==21204}">
								<a href="javascript:void(0)" onclick="submitTr(${list.id},${list.submitOaStatus});">提交OA</a>
							</c:if>
	                      </c:when>
	                      <c:when test="${list.paymentType==17906}">
	                         <c:if test="${list.submitOaStatus==21204 and list.status == 30}">
								<a href="javascript:void(0)" onclick="submitTr(${list.id},${list.submitOaStatus});">提交OA</a>
							</c:if>
	                      </c:when>
	                     </c:choose>
                     </shiro:hasPermission>
				</div>
			</td>
        </tr>
    </c:forEach>
</table>
</div>
${pageInfo.html}

<c:if test="${fn:length(contentlist) le 0}">
    <div class="nodata"><i class="icon-hdd"></i>
        <p>暂无数据...</p></div>
</c:if>

<!-- 遮罩层(辅助) -->
<div class="shade"></div>