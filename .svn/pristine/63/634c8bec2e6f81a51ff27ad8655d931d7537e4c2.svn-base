<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<div class="" style="height: auto;">
    <table class="table table-striped table-hover table-bordered" style="font-size:12px;">
        <tr style="text-align: center;font-weight: bolder;">
            <%--<td  style="vertical-align: middle;width:50px;">序号</td>--%>
            <td colspan="2"  style="vertical-align: middle;width:160px;">科目</td>
            <c:if test="${not empty Q1}">
                <td  style="vertical-align: middle;width:110px;">${Q1}</td>
            </c:if>
            <c:if test="${not empty Q2}">
                <td  style="vertical-align: middle;width:110px;">${Q2}</td>
            </c:if>
            <c:if test="${not empty Q3}">
                <td  style="vertical-align: middle;width:110px;">${Q3}</td>
            </c:if>
            <c:if test="${not empty Q4}">
                <td  style="vertical-align: middle;width:110px;">${Q4}</td>
            </c:if>

        </tr>

        <tr>

        </tr>
        <c:if test="${not empty reportList}">
            <c:forEach items="${reportList}" var="list" varStatus="status">
                <tr>
                    <%--<td rowspan="14" style="vertical-align: middle;width: 60px">${status.index + 1}</td>--%>
                    <td rowspan="${fn:length(list.infoList) + 1}" style="vertical-align: middle;width: 60px">${list.name}</td>
                </tr>
                <c:forEach items="${list.infoList}" var="infoList" varStatus="infoStatus">
                    <tr>
                        <td style="text-align: left">${fn:replace(infoList.accountName," ","&nbsp&nbsp&nbsp&nbsp")}</td>
                        <c:if test="${not empty Q1}">
                            <c:if test="${infoList.q1 != null and infoList.q1!='0.000000'}">
                                <c:if test="${infoStatus.first}">
                                    <td> <fmt:formatNumber value="${infoList.q1*100}" pattern="0.00" maxFractionDigits="2"/> %</td>
                                </c:if>
                                <c:if test="${!infoStatus.first}">
                                    <td> <fmt:formatNumber value="${infoList.q1}" pattern="#,###"/></td>
                                </c:if>
                            </c:if>
                            <c:if test="${infoList.q1 == null or infoList.q1 == '0.000000'}">
                                <td>-</td>
                            </c:if>
                        </c:if>

                        <c:if test="${not empty Q2}">
                            <c:if test="${infoList.q2 != null and infoList.q2!='0.000000'}">
                                <c:if test="${infoStatus.first}">
                                    <td> <fmt:formatNumber value="${infoList.q2*100}" pattern="0.00" maxFractionDigits="2"/> %</td>
                                </c:if>
                                <c:if test="${!infoStatus.first}">
                                    <td> <fmt:formatNumber value="${infoList.q2}" pattern="#,###"/></td>
                                </c:if>
                            </c:if>
                            <c:if test="${infoList.q2 == null or infoList.q2 == '0.000000'}">
                                <td>-</td>
                            </c:if>
                        </c:if>

                        <c:if test="${not empty Q3}">
                            <c:if test="${infoList.q3 != null and infoList.q3!='0.000000'}">
                                <c:if test="${infoStatus.first}">
                                    <td> <fmt:formatNumber value="${infoList.q3*100}" pattern="0.00" maxFractionDigits="2"/> %</td>
                                </c:if>
                                <c:if test="${!infoStatus.first}">
                                    <td> <fmt:formatNumber value="${infoList.q3}" pattern="#,###"/></td>
                                </c:if>
                            </c:if>
                            <c:if test="${infoList.q3 == null or infoList.q3 == '0.000000'}">
                                <td>-</td>
                            </c:if>
                        </c:if>

                        <c:if test="${not empty Q4}">
                            <c:if test="${infoList.q4 != null and infoList.q4!='0.000000'}">
                                <c:if test="${infoStatus.first}">
                                    <td> <fmt:formatNumber value="${infoList.q4*100}" pattern="0.00" maxFractionDigits="2"/> %</td>
                                </c:if>
                                <c:if test="${!infoStatus.first}">
                                    <td> <fmt:formatNumber value="${infoList.q4}" pattern="#,###"/></td>
                                </c:if>
                            </c:if>
                            <c:if test="${infoList.q4 == null or infoList.q4 == '0.000000'}">
                                <td>-</td>
                            </c:if>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:forEach>
        </c:if>
    </table>
</div>

<%--${pageInfo.html}--%>

<c:if test="${fn:length(reportList) le 0}">
    <div class="nodata">
        <i class="icon-hdd"></i>
        <p>暂无数据...</p>
    </div>
</c:if>
