<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;">
    <table class="table table-striped table-hover table-bordered">
        <col style="width:145px;">
        <col style="width:145px;">
        <col style="width:150px;">
        <col style="width:100px;">
        <col style="width:160px;">
        <col style="width:80px;">
        <col style="width:100px;">
        <col style="width:100px;">
        <col style="width:130px;">
        <tr>
            <th>初始会员申请编号</th>
            <th>OA编号</th>
            <th>初始会员协议书编号</th>
            <th>经纪公司编号</th>
            <th>经纪公司</th>
            <th>创建人</th>
            <th>创建日期</th>
            <th>审核状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${contentlist}" var="list">
            <tr class="J_eatateItem" data-hidenumberflg="0">
                <td title="${list.gpCsMemberContractNo}" class="text-overflow"><a href="${ctx}/gpCsMemberContract/getGpCsMemberContractById/${list.id}">${list.gpCsMemberContractNo}</a></td>
                <td style="text-align:center;" title="${list.oaNo}" class="text-overflow">${list.oaNo}</td>
                <td style="text-align:left;" title="${list.agreementNo}" class="text-overflow">${list.agreementNo}</td>
                <td>${list.companyNo}</td>
                <td style="text-align:left;" title="${list.partyB}" class="text-overflow">${list.partyB}</td>
                <td>${list.userName}</td>
                <td>${sdk:ymd2(list.dateCreate)}</td>
                <td>${list.approveStatusNm}</td>
                <td>
                    <a href="${ctx}/gpCsMemberContract/getGpCsMemberContractById/${list.id}">查看</a>
                    <c:if test="${list.approveState eq 0 || list.approveState eq 3}">
                        <!-- 经办人有编辑权限 -->
                        <c:if test="${not empty oaOperator.operCode}">
                            <!-- 编辑 -->
                            <a href="${ctx}/gpCsMemberContract/toEidtGpCsMemberContract/${list.id}"> 编辑</a>
                        </c:if>

                        <!-- 非经办人则自己有编辑权限 -->
                        <c:if test="${empty oaOperator.operCode}">
<%--                             <c:if test="${list.userIdCreate eq userInfo.userId}"> --%>
<%--                                 <a href="${ctx}/gpCsMemberContract/toEidtGpCsMemberContract/${list.id}"> 编辑</a> --%>
<%--                             </c:if> --%>
                            <c:choose>
	                            <c:when test="${list.userIdCreate eq userInfo.userId}">
                                	<a href="${ctx}/gpCsMemberContract/toEidtGpCsMemberContract/${list.id}"> 编辑</a>
	                            </c:when>
	                            <c:otherwise>
	                                <shiro:hasPermission name="/GpCsMemberContract:EDIT">
                                		<a href="${ctx}/gpCsMemberContract/toEidtGpCsMemberContract/${list.id}"> 编辑</a>
	                                </shiro:hasPermission>
	                            </c:otherwise>
                        	</c:choose>
                        </c:if>
                    </c:if>
                    <!-- （草签状态的合同、审核失败的合同）自己有作废权限，有作废权限的可以作废   -->
                    <c:if test="${(list.approveState eq 0) or (list.approveState eq 3)}">
                        <c:choose>
                            <c:when test="${list.userIdCreate eq userInfo.userId}">
                                <a href="javascript:GpCsMemberContract.cancel('${list.id}');"> 作废</a>
                            </c:when>
                            <c:otherwise>
                                <shiro:hasPermission name="/GpCsMemberContract:CANCEL">
                                    <a href="javascript:GpCsMemberContract.cancel('${list.id}');"> 作废</a>
                                </shiro:hasPermission>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <%-- <shiro:hasPermission name="/GpContractStop:GP_SUMBIT">
	                    <c:if test="${(list.approveState eq 0 &&(list.submitOAStatus eq 21201 || list.submitOAStatus eq 21204)) || (list.approveState eq 3 && list.submitOAStatus ne 21202)}">
							   <a href="javascript:void(0)" onclick="submitTr('${list.id}');">提交审核</a>
						</c:if>
					</shiro:hasPermission> --%>
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
