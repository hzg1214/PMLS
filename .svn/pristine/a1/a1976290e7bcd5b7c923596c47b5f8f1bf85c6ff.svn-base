<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;">
    <c:if test="${fn:length(contentlist) gt 0}">
    <div id="divReport" class="s-scoll-y" style="margin-bottom:20px;"></c:if>
    <c:if test="${fn:length(contentlist) le 0}">
        <div id="divReport" class="s-scoll-y" style="height: auto;"></c:if>
            <table id="tblReport" class="table table-striped table-hover table-bordered" style="width:2100px;">
                <tbody class="s-w100">
                <col style="width:100px">
                <col style="width:110px">
                <col style="width:120px">
                <col style="width:150px">
                <%--<col style="width:50px">
                <col style="width:150px">--%>
                <col style="width:80px">
                <col style="width:160px">
                <col style="width:150px">
                <col style="width:160px">
                <col style="width:80px">
                <col style="width:80px">
                <col style="width:120px">
                <col style="width:80px">
                <col style="width:145px">
                <col style="width:145px">
                <col style="width:120px">
                <tr>
                    <th style="border-left:none;">客户编号</th>
                    <th>客户</th>
                    <th>客户手机</th>
                    <%--<th>城市</th>
                    <th>类型</th>--%>
                    <th>楼盘名称</th>
                    <th>楼盘城市</th>
                    <th>楼盘地址</th>
                    <th>经纪公司</th>
                    <th>门店地址</th>
                    <th>最新进度</th>
                    <th>确认状态</th>
                    <th>楼室号</th>
                    <th>面积</th>
                    <th>大定总价</th>
                    <th>成交总价</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${contentlist}" var="list">
                <tr class="J_eatateItem" data-hidenumberflg="0">
                    <td style="border-left:none"><a
                            href="${ctx}/custom/${list.estateId}/${list.companyId}/${list.customerId}">${list.customerId}</a>
                    </td>
                    <td style="text-align:left;" title="${list.customerNm}">${fn:substring(list.customerNm, 0, 5)}
                        <c:if test="${fn:length(list.customerNm) >= '6'}">
                            ...
                        </c:if>
                    </td>
                    
                    <td>${list.customerTel}</td>
                    <td style="text-align:left;" title="${list.estateNm}" class="text-overflow">
                        ${list.estateNm}
                    </td>
                     <td>${list.realityCityNm}</td>
                    <td style="text-align:left;" title="${list.address}" class="text-overflow">
                        ${list.address}
                    </td>

                    <td style="text-align:left;" title="${list.companyNm}" class="text-overflow">
                    	${list.companyNm}
                    </td>
                    <td style="text-align:left;" title="${list.storeAddress}" class="text-overflow">
                        ${list.storeAddress}
                    </td>
                    <c:choose>
                        <c:when test="${list.brokerageStatus eq '22002' or list.brokerageStatus eq '22003'}">
                            <td>结佣</td>
                        </c:when>
                        <c:otherwise>
                            <td>${list.latestProgressNm}</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${list.confirmStatusNm}</td>
                    <td>${list.buildingNo}</td>
                    <td><c:if test="${list.area eq null}">0</c:if>${list.area}㎡</td>
                    <td><c:if test="${list.roughAmount eq null}">0</c:if><fmt:formatNumber value="${list.roughAmount}"
                                                                                           pattern="#.00"/>元
                    </td>
                    <td><c:if test="${list.dealAmount eq null}">0</c:if><fmt:formatNumber value="${list.dealAmount}"
                                                                                          pattern="#.00"/>元
                    </td>
                    <td>
                        <a href="${ctx}/custom/${list.estateId}/${list.companyId}/${list.customerId}">查看</a>
                    </td>
                    <td style="display:none">${list.id}</td>
                    <td style="display:none">${list.cityNo}</td>
                    <td style="display:none">${list.estateType}</td>
                    <td style="display:none">${list.latestProgress}</td>
                    <td style="display:none">${list.confirmStatus}</td>
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
