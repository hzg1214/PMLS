<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<div class="" style="height: auto;">
    <table class="table table-striped table-hover table-bordered">
        <col style="width:120px;">
        <col style="width:80px;">
        <col style="width:100px;">
        <col style="width:160px;">
        <col style="width:80px;">
        <col style="width:80px;">
        <col style="width:70px;">
        <col style="width:70px;">
        <col style="width:100px;">
        <col style="width:80px;">
        <col style="width:80px;">
        <col style="width:100px;">
        <tr>
            <th>工单编号</th>
            <th>归属城市</th>
            <th>公司编号</th>
            <th>公司名称</th>
            <th>一级分类</th>
            <th>二级分类</th>
            <th>经办人</th>
            <th>创建人</th>
            <th>创建日期</th>
            <th>问题状态</th>
            <th>核验状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${contentlist}" var="list">
            <tr class="J_eatateItem" data-hidenumberflg="0">
                <td title="${list.orderNo}" class="text-overflow"><a href="${ctx}/keFuOrder/getKeFuOrderById/${list.id}">${list.orderNo}</a></td>
                <td>${list.cityName}</td>
                <td style="text-align:center;" title="${list.companyNo}" class="text-overflow">${list.companyNo}</td>
                <td style="text-align:left;" title="${list.companyName}" class="text-overflow">${list.companyName}</td>
                <td style="text-align:left;" title="${list.categoryOneNm}" class="text-overflow">${list.categoryOneNm}</td>
                <td style="text-align:left;" title="${list.categoryTwoNm}" class="text-overflow">${list.categoryTwoNm}</td>
                <td>${list.userName}</td>
                <td>${list.createName}</td>
                <td>${sdk:ymd2(list.dateCreate)}</td>
                <td>${list.dealStatusNm}</td>
                <td>${list.checkStatusNm}</td>
                <td>
                    <a href="${ctx}/keFuOrder/getKeFuOrderById/${list.id}">查看</a>
                    <!-- 核验通过的不可以编辑 -->
                    <c:if test="${list.checkStatus ne 24302}">
                        <!-- 问题状态为未处理时,点击编辑后，除归属城市、经纪公司、门店，其他可修改 -->
                        <c:if test="${list.dealStatus eq  24201}">
                            <!-- 有权限或者自己创建的可以编辑 -->
                            <c:choose>
	                            <c:when test="${list.userCreate eq userInfo.userId}">
		                            <a href="${ctx}/keFuOrder/toEidtKeFuOrder/${list.id}/0"> 编辑</a>
	                            </c:when>
	                            <c:otherwise>
	                            	<shiro:hasPermission name="/keFuOrder:KF_EDIT">
		                            	<a href="${ctx}/keFuOrder/toEidtKeFuOrder/${list.id}/0"> 编辑</a>
		                            </shiro:hasPermission>
	                            </c:otherwise>
	                        </c:choose>
                        </c:if>

                        <!-- 问题状态为处理中和处理完成时，点击编辑后，仅经办人项可修改。-->
                        <c:if test="${list.dealStatus eq  24202 || list.dealStatus eq  24203}">
	                        <c:choose>
	                            <c:when test="${list.userCreate eq userInfo.userId}">
		                            <a href="${ctx}/keFuOrder/toEidtKeFuOrder/${list.id}/1"> 编辑</a>
	                            </c:when>
	                            <c:otherwise>
	                            	<shiro:hasPermission name="/keFuOrder:KF_EDIT">
		                            	<a href="${ctx}/keFuOrder/toEidtKeFuOrder/${list.id}/1"> 编辑</a>
		                            </shiro:hasPermission>
	                            </c:otherwise>
	                        </c:choose>
                        </c:if>
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
