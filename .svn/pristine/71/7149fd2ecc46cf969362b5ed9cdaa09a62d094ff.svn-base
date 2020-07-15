<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;">
<table class="table table-striped table-hover table-bordered">
    <tr>
        <th style="width: 155px;">收款编号</th>
        <th style="width: 140px;">合同编号</th>
        <th style="width:140px;">经纪公司</th>
		<th style="width:120px;">收款类型</th>
		<th style="width:100px;">收款方式</th>
        <th style="width:100px;">收款金额(元)</th>
        <th style="width:80px;">收款人</th>
        <th style="width: 110px;">申请时间</th>
        <th style="width:120px;">OA收款审核状态</th>
        <th style="width:100px;">操作</th>
    </tr>

    <c:forEach items="${contentlist}" var="list">
        <tr>
            <td style="text-align:center;" title="${list.receiveNo}" class="text-overflow">
				<a href="${ctx}/storeReceive/${list.id}/${list.companyName}">${list.receiveNo}</a>
			</td>
            <td style="text-align:center;" title="${list.contractNo}"  class="text-overflow">
				${list.contractNo}
			</td>
            <td style="text-align:left;" title="${list.companyName}" class="text-overflow">
				${list.companyName}
			</td>
			<td style="text-align:center;">
				${list.feeTypeNm}
			</td>
			<td style="text-align:center;">
				${list.receiveTypeNm}
			</td>
            <td style="text-align:center;" title="${list.totalAmount}">
				<fmt:formatNumber type="number" value='${list.totalAmount}' pattern="0.00" maxFractionDigits="2"/>
			</td>
            <td style="text-align:center;" title="${list.userName}" class="text-overflow">
				${list.userName}
			</td>
            <td style="text-align:center;" title="${list.dateCreate}" class="text-overflow">
				<%-- ${list.dateCreate} --%>
				${sdk:ymd2(list.dateCreate)}
			</td>
			<c:if test="${list.status ne 20}">
				<td style="text-align:center;" title="${list.oaStatusName}" class="text-overflow">
						${list.oaStatusName}
				</td>
			</c:if>
            <c:if test="${list.status eq 20}">
				<td style="text-align:center;">-</td>
			</c:if>
            <td align="center">
				<div class="mailopre">
					<a href="${ctx}/storeReceive/${list.id}/${list.companyName}">查看</a>
					<%-- <a href="javascript:void(0)" onclick="removeTr(${list.id});">删除</a> --%>
					<shiro:hasPermission name="/storeReceive:SUBMIT_OA">
						<c:choose>
	                      <c:when test="${list.receiveType==17909}">
	                         <c:if test="${list.submitOaStatus==21204}">
								<a href="javascript:void(0)" onclick="submitTr(${list.id},${list.submitOaStatus});">提交OA</a>
							</c:if>
	                      </c:when>
	                      <c:when test="${list.receiveType==17906}">
	                         <c:if test="${list.submitOaStatus==21204 and list.status == 30}">
								<a href="javascript:void(0)" onclick="submitTr(${list.id},${list.submitOaStatus});">提交OA</a>
							</c:if>
	                      </c:when>
	                     </c:choose>
					</shiro:hasPermission>
					<c:if test="${list.approveStatus eq 21604}">
						<a href="javascript:void(0);" onclick="StoreReceive.getOAOpinions('${list.flowId}')">原因</a>
					</c:if>
                    <c:if test="${userId eq list.userIdCreate}">
                        <c:if test="${list.approveStatus eq 21603 and list.receiveType eq 17909 and list.reverseId eq null}">
                            <a href="javascript:void(0);" onclick="StoreReceive.toCreateReverse('${list.id}')">红冲</a>
                        </c:if>
                    </c:if>
                    <c:if test="${userId ne list.userIdCreate}">
                        <shiro:hasPermission name="/storeReceive:REVERSE_OA">
                            <c:if test="${list.approveStatus eq 21603 and list.receiveType eq 17909 and list.reverseId eq null}">
                                <a href="javascript:void(0);" onclick="StoreReceive.toCreateReverse('${list.id}')">红冲</a>
                            </c:if>
                        </shiro:hasPermission>
                    </c:if>
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