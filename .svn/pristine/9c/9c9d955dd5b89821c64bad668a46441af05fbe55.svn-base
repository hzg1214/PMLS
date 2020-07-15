<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;">
    <c:if test="${fn:length(contentlist) gt 0}">
    <div id="divReport" class="s-scoll-y" style="margin-bottom:20px;"<%-- style="height: 500px;"--%>></c:if>
    <c:if test="${fn:length(contentlist) le 0}">
        <div id="divReport" class="s-scoll-y" style="height: auto;"></c:if>
            <table id="tblReport" class="table table-striped table-hover table-bordered" style="width:1500px;">
                <tbody class="s-w100">
                <col style="width:170px">
                <col style="width:130px">
                <col style="width:100px">
                <col style="width:100px">
                <col style="width:100px">
                <col style="width:150px">
                <col style="width:130px">
                <col style="width:135px">
                <col style="width:130px">
                <col style="width:120px">
                <tr>
                    <th>报备编号</th>
                    <th>收入类型</th>
                    <th>收入金额</th>
                    <th>最新进度</th>
                    <th>确认状态</th>
                    <th>报备日期</th>
                    <th>成销日期</th>
                    <th>成销金额</th>
                    <th>退成销日期</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${contentlist}" var="list">
                <tr class="J_eatateItem" data-hidenumberflg="0">
                    <td><a href="${ctx}/qtReport/${list.estateId}/${list.id}/${qtType}">${list.reportNo}</a></td>
                    <td>${list.srTypeName}</td>
                    <td><fmt:formatNumber value="${list.srAmount}" pattern="#,##0.00#"/></td>
                    <td>${list.reportStatusName}</td>
                    <td>${list.ackStatusName}</td>
                    <td>${sdk:ymd2(list.reportDate)}</td>
                    <td>${sdk:ymd2(list.dealDate)}</td>
                    <td><fmt:formatNumber value="${list.dealAmount}" pattern="#,##0.00#"/></td>
                    <td>${sdk:ymd2(list.backDealDate)}</td>
<!--                     无效/成销(只有报备状态、有效状态才能成销)、  退成销(只有成销、有效状态并且未结佣才能退成销)-->
                    <td>
						<a href="${ctx}/qtReport/${list.estateId}/${list.id}/${qtType}">查看</a>
						<c:choose>
						    <c:when test="${(list.reportStatus eq 27002 && list.validStatus eq 0 ) && (list.brokerageStatus ne 22002 && list.brokerageStatus ne 22003)}">
								<a href="${ctx}/qtSuccessSale/toDealBack/${list.id}">退成销</a>
						    </c:when>
						    <c:otherwise>
						        <c:if test="${list.reportStatus eq 27001 && list.validStatus eq 0}">
									<a href="${ctx}/qtSuccessSale/qtSuccessSaleHandle/${list.id}">成销</a>
                                    <a href="javascript:validQtReport(${list.id})"><i>无效</i></a>
								</c:if>
						    </c:otherwise>
						</c:choose>
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
