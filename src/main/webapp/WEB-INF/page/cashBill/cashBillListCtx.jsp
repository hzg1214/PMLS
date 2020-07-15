<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;">
<table class="table table-striped table-hover table-bordered">
    <tr>
        <th style="width: 150px;">请款编号</th>
        <th style="width:100px;">项目编号</th>
        <th style="width:120px;">项目名称</th>
        <th style="width: 160px;">经纪公司</th>
        <th style="width:100px;">请款金额</th>
        <th style="width:80px;">申请人</th>
        <th style="width:100px;">申请日期</th>
<!--         <th style="width:100px;">OA提交状态</th> -->
        <th style="width:100px;">审核状态</th>
        <th style="width:120px;">操作</th>
    </tr>

    <c:forEach items="${contentlist}" var="list">
        <tr>
            <td style="text-align:center;" title="${list.cashBillNo}" class="text-overflow">
				<a href="${ctx}/cashBill/${list.comParentId}/${list.proParentId}">${list.cashBillNo}
				</a>
			</td>
            <td style="text-align:left;" title="${list.projectNo}" class="text-overflow">
				${list.projectNo}
			</td>
            <td style="text-align:center;" title="${list.estateNm}" class="text-overflow">
				${list.estateNm}
			</td>
            <td style="text-align:center;" title="${list.companyName}" class="text-overflow">
				${list.companyName}
			</td>
            <td style="text-align:center;" title="${list.amountTotal}" class="text-overflow">
				<fmt:formatNumber type="number" value="${list.amountTotal}" pattern="0.00" maxFractionDigits="2"/>
			</td>
			<td style="text-align:center;" title="${list.userName}" class="text-overflow">
				${list.userName}
			</td>
			<%-- <c:if test="${!empty list.applyTime}"> --%>
	            <td style="text-align:center;" title="${list.applyTime}" class="text-overflow">
					<%-- ${sdk:ymd2(list.applyTime)} --%>
					${sdk:ymd2(list.applyTime)}
				</td>
			<%-- </c:if> --%>
            <td style="text-align:center;" title="${list.approveStatusNm}" class="text-overflow">
				${list.approveStatusNm}
			</td>
<!--             <td align="center"> -->
<!-- 				<div class="mailopre"> -->
<%-- 					<a href="${ctx}/cashBill/${list.comParentId}/${list.proParentId}">查看</a> --%>
<%-- 					<c:if test="${list.submitOaStatus==21204}"> --%>
<%-- 						<a href="javascript:void(0)" onclick="submitTr('${list.estateId}','${list.companyId}','${list.proParentId}');">提交OA</a> --%>
<%-- 					</c:if> --%>
<!-- 				</div> -->
<!-- 			</td> -->
            <td align="center">
				<div class="mailopre">
<!-- 				1、审核中、审核通过、审核不通过：操作列出现查看按钮。
					提交状态为提交失败：操作列出现提交OA按钮+查看按钮。
					草稿+ 提交状态为提交成功：操作列出现查看按钮
					2、佣金管理---请款单，如果请款单是草稿状态，增加删除按钮。
					3、删除按钮增加权限控制，请款单创建人默认有删除按钮权限。 -->
					   <a href="${ctx}/cashBill/${list.comParentId}/${list.proParentId}">查看</a>
					   <c:if test="${(list.approveStatus==25401 || empty list.approveStatus) && list.submitOaStatus==21204}"> 
							<c:choose>
	                            <c:when test="${userInfo.userCode eq list.userCode}">
									<a href="javascript:void(0)" onclick="submitTr('${list.estateId}','${list.companyId}','${list.proParentId}');">提交OA</a>
		                            <a href="javascript:void(0)" onclick="removeTr('${list.estateId}','${list.comParentId}','${list.proParentId}');">删除</a>
	                            </c:when>
	                            <c:otherwise>
									<shiro:hasPermission name="/cashBill:QK_DEL">
										<a href="javascript:void(0)" onclick="submitTr('${list.estateId}','${list.companyId}','${list.proParentId}');">提交OA</a>
										<a href="javascript:void(0)" onclick="removeTr('${list.estateId}','${list.comParentId}','${list.proParentId}');">删除</a>
				                    </shiro:hasPermission>
	                            </c:otherwise>
	                        </c:choose>
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