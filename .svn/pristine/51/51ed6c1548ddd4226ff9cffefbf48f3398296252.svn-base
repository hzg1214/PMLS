<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;">
    <table class="table table-striped table-hover table-bordered">
        <col style="width:135px;">
        <col style="width:160px;">
        <col style="width:140px;">
        <col style="width:110px;">
        <col style="width:110px;">
        <col style="width:80px;">
        <col style="width:110px;">
        <col style="width:100px;">
        <col style="width:120px;">
        <tr>
            <th>公盘合同编号</th>
            <th>OA编号</th>
            <th>公盘协议书编号</th>
            <th>经纪公司编号</th>
            <th>经纪公司</th>
            <th>创建人</th>
            <th>创建日期</th>
            <th>审核状态</th>
            <th>保证金状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${contentlist}" var="list">
            <tr class="J_eatateItem" data-hidenumberflg="0">
                <td><a href="${ctx}/gpContract/${list.id}">${list.gpContractNo}</a></td>
                <td style="text-align:center;" title="${list.oaNo}" class="text-overflow">${list.oaNo}</td>
                <td style="text-align:center;" title="${list.gpAgreementNo}" class="text-overflow">${list.gpAgreementNo}</td>
                <td>${list.companyNo}</td>
                <td style="text-align:left;" title="${list.companyName}" class="text-overflow">${list.companyName}</td>
                <td>${list.userName}</td>
                <td>${sdk:ymd2(list.dateCreate)}</td>
                <td>${list.contractStatusNm}</td>
                <td>${list.depositStatusNm}</td>
                <td>
                    <a href="${ctx}/gpContract/${list.id}">查看</a>
                    <c:if test="${list.contractStatus eq 10401 || list.contractStatus eq 10404}">
                        <!-- 经办人有编辑权限 -->
                        <c:if test="${not empty oaOperator.operCode}">
                            <!-- 流程编辑 -->
                            <a href="${ctx}/gpContract/u/${list.id}"> 编辑</a>
                        </c:if>

                        <!-- 非经办人则自己有编辑权限 -->
                        <c:if test="${empty oaOperator.operCode}">
<%--                           	<c:if test="${list.userCreate eq userInfo.userId}">  --%>
<%--                                  <a href="${ctx}/gpContract/u/${list.id}"> 编辑</a>  --%>
<%--                             </c:if> --%>
                            <c:choose>
	                            <c:when test="${list.userCreate eq userInfo.userId}">
	                               	<a href="${ctx}/gpContract/u/${list.id}"> 编辑</a>
	                            </c:when>
	                            <c:otherwise>
	                                <shiro:hasPermission name="/gpContract:GP_EDIT">
	                                	<a href="${ctx}/gpContract/u/${list.id}"> 编辑</a>
	                                </shiro:hasPermission>
	                            </c:otherwise>
                        	</c:choose>
                        </c:if>
                    </c:if>
                    <!-- （草签状态的合同、审核失败的合同）自己有作废权限，有作废权限的可以作废 -->
                    <c:if test="${(list.contractStatus eq 10404) or (list.contractStatus eq 10401)}">
                        <c:if test="${list.contractStatus eq 10404}">
                            <a href="javascript:GpContract.getOAOpinions('${list.flowId}');">原因</a>
                        </c:if>
                        <c:choose>
                            <c:when test="${list.userCreate eq userInfo.userId}">
                                <a href="javascript:GpContract.cancel('${list.id}');"> 作废</a>
                            </c:when>
                            <c:otherwise>
                                <shiro:hasPermission name="/gpContract:GP_CANCEL">
                                    <a href="javascript:GpContract.cancel('${list.id}');"> 作废</a>
                                </shiro:hasPermission>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
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
