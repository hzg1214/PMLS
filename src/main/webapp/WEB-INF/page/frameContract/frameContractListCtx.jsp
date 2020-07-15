<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;">
<table class="table table-striped table-hover table-bordered">
    <tr>
        <th style="width: 130px;">合同编号</th>
		<th style="width:100px;">经纪公司编号</th>
        <th style="width:270px;">经纪公司</th>
        <th style="width:100px;">创建人</th>
        <th style="width: 120px;">申请日期</th>
        <th style="width:100px;">审核状态</th>
        <th style="width:120px;">操作</th>
    </tr>

    <c:forEach items="${contentlist}" var="list">
        <tr>
            <td style="text-align:center;" title="${list.contractNo}" class="text-overflow">
				<a href="${ctx}/frameContract/${list.id}">${list.contractNo}</a>
			</td>
			<td style="text-align:center;" title="${list.companyNo}" class="text-overflow">
					${list.companyNo}
			</td>
            <td style="text-align:left;" title="${list.companyName}" class="text-overflow">
				${list.companyName}
			</td>
            <td style="text-align:center;" title="${list.userName}" class="text-overflow">
				${list.userName}
			</td>
            <td style="text-align:center;" title="${list.dateCreate}" class="text-overflow">
				<%-- ${sdk:ymd2(list.dateCreate)} --%>
				${list.format2}
			</td>
            <td style="text-align:center;" title="${list.approveStatusNm}" class="text-overflow">
				${list.approveStatusNm}
			</td>
            <td align="center">
				<div class="mailopre">
					<a href="${ctx}/frameContract/${list.id}">查看</a>
					<c:if test="${(list.approveState eq 10401 || list.approveState eq 10404) && list.autoToOa ne 1 }">
<%-- 							<a href="${ctx}/frameContract/u/${list.id}"> 编辑</a> --%>
						<c:choose> 
						  <c:when test="${list.userIdCreate eq userInfo.userId}"> 
							<a href="${ctx}/frameContract/u/${list.id}"> 编辑</a>
						  </c:when>  
						  <c:otherwise> 
						  	<shiro:hasPermission name="/frameContract:FC_EDIT">
								<a href="${ctx}/frameContract/u/${list.id}"> 编辑</a>
							</shiro:hasPermission>  
						  </c:otherwise> 
						</c:choose> 
						<c:choose> 
						  <c:when test="${list.userIdCreate eq userInfo.userId}"> 
						  	<a href="javascript:void(0)" onclick="cancel(${list.id});">作废</a>
						  </c:when>  
						  <c:otherwise> 
						  	<shiro:hasPermission name="/frameContract:FC_CANCEL">
								<a href="javascript:void(0)" onclick="cancel(${list.id});">作废</a>
							</shiro:hasPermission>  
						  </c:otherwise> 
						</c:choose>
					</c:if>
					<c:if test="${list.approveState eq 10404}">
						<a href="javascript:void(0)" onclick="FrameContract.getOAOpinions('${list.flowId}')">原因</a>
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