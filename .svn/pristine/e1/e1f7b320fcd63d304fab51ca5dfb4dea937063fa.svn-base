<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;">
<table class="table table-striped table-hover table-bordered">
    <tr>
        <th style="width: 160px;">调整单编号</th>
        <th style="width:175px;">经纪公司</th>
        <th style="width:100px;">门店编号</th>
        <th style="width:100px;">门店</th>
        <th style="width: 175px;">创建时间</th>
        <th style="width:100px;">审批状态</th>
        <th style="width:130px;">操作</th>
    </tr>

    <c:forEach items="${contentlist}" var="list">
        <tr>
            <td style="text-align:center;" title="${list.receiveNo}" class="text-overflow">
				<a href="${ctx}/storeReceive/${list.id}/${list.companyName}">${list.receiveNo}</a>
			</td>
            <td style="text-align:left;" title="${list.companyName}" class="text-overflow">
				${list.companyName}
			</td>
            <td style="text-align:center;" title="${list.storeNo}" class="text-overflow">
				${list.storeNo}
			</td>
            <td style="text-align:center;" title="${list.dateCreate}" class="text-overflow">
				${list.storeName}
			</td>
            <td style="text-align:center;" title="${list.dateCreate}" class="text-overflow">
				${list.dateCreate}
			</td>
			<td style="text-align:center;" title="${list.oaStatusName}" class="text-overflow">
					${list.oaStatusName}
			</td>
            <td align="center">
				<div class="mailopre">
					<a href="${ctx}/storeReceive/${list.id}/${list.companyName}">查看</a>
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