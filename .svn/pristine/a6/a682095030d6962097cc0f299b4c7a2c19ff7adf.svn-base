<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<table class="table table-striped table-hover table-bordered">
    <tr>
        <td colspan="3" style="vertical-align: middle">科目</td>
        <td style="vertical-align: middle">当期</td>
        <td style="vertical-align: middle">总累计</td>
    </tr>

    <c:forEach items="${contentlist}" var="list" varStatus="status">
        <tr>
            <c:if test="${status.index == 0}">
                <td rowspan="5" style="vertical-align: middle">${list.bizType}</td>
                <td rowspan="3" style="vertical-align: middle">${list.bizStage}</td>
                <c:if test="${list.align == 'left'}"><td style="font-weight: bold;text-align: left">${list.subjName}</td></c:if>
                <c:if test="${list.align == 'center'}"><td>${list.subjName}</td></c:if>
            </c:if>
            <c:if test="${status.index >= 1 && status.index <= 2}">
                <c:if test="${list.align == 'left'}"><td style="font-weight: bold;text-align: left">${list.subjName}</td></c:if>
                <c:if test="${list.align == 'center'}"><td>${list.subjName}</td></c:if>
            </c:if>
            <c:if test="${status.index == 3}">
                <td rowspan="2" style="vertical-align: middle">${list.bizStage}</td>
                <c:if test="${list.align == 'left'}"><td style="font-weight: bold;text-align: left">${list.subjName}</td></c:if>
                <c:if test="${list.align == 'center'}"><td>${list.subjName}</td></c:if>
            </c:if>
            <c:if test="${status.index == 4}">
                <c:if test="${list.align == 'left'}"><td style="font-weight: bold;text-align: left">${list.subjName}</td></c:if>
                <c:if test="${list.align == 'center'}"><td>${list.subjName}</td></c:if>
            </c:if>
            <c:if test="${status.index == 5}">
                <td rowspan="4" style="vertical-align: middle">${list.bizType}</td>
                <td rowspan="4" style="vertical-align: middle">${list.bizStage}</td>
                <c:if test="${list.align == 'left'}"><td style="font-weight: bold;text-align: left">${list.subjName}</td></c:if>
                <c:if test="${list.align == 'center'}"><td>${list.subjName}</td></c:if>
            </c:if>
            <c:if test="${status.index >= 6 && status.index <= 8}">
                <c:if test="${list.align == 'left'}"><td style="font-weight: bold;text-align: left">${list.subjName}</td></c:if>
                <c:if test="${list.align == 'center'}"><td>${list.subjName}</td></c:if>
            </c:if>
            <c:if test="${status.index == 9}">
                <td rowspan="14" style="vertical-align: middle">${list.bizType}</td>
                <td>${list.bizStage}</td>
                <c:if test="${list.align == 'left'}"><td style="font-weight: bold;text-align: left">${list.subjName}</td></c:if>
                <c:if test="${list.align == 'center'}"><td>${list.subjName}</td></c:if>
            </c:if>
            <c:if test="${status.index == 10}">
                <td rowspan="13" style="vertical-align: middle">${list.bizStage}</td>
                <c:if test="${list.align == 'left'}"><td style="font-weight: bold;text-align: left">${list.subjName}</td></c:if>
                <c:if test="${list.align == 'center'}"><td>${list.subjName}</td></c:if>
            </c:if>
            <c:if test="${status.index >= 11 && status.index <= 22}">
                <c:if test="${list.align == 'left'}"><td style="font-weight: bold;text-align: left">${list.subjName}</td></c:if>
                <c:if test="${list.align == 'center'}"><td>${list.subjName}</td></c:if>
            </c:if>
            <c:if test="${status.index == 23}">
                <td rowspan="4" style="vertical-align: middle">${list.bizType}</td>
                <td rowspan="2" style="vertical-align: middle">${list.bizStage}</td>
                <c:if test="${list.align == 'left'}"><td style="font-weight: bold;text-align: left">${list.subjName}</td></c:if>
                <c:if test="${list.align == 'center'}"><td>${list.subjName}</td></c:if>
            </c:if>
            <c:if test="${status.index == 24}">
                <c:if test="${list.align == 'left'}"><td style="font-weight: bold;text-align: left">${list.subjName}</td></c:if>
                <c:if test="${list.align == 'center'}"><td>${list.subjName}</td></c:if>
            </c:if>
            <c:if test="${status.index == 25}">
                <td rowspan="2" style="vertical-align: middle">${list.bizStage}</td>
                <c:if test="${list.align == 'left'}"><td style="font-weight: bold;text-align: left">${list.subjName}</td></c:if>
                <c:if test="${list.align == 'center'}"><td>${list.subjName}</td></c:if>
            </c:if>
            <c:if test="${status.index == 26}">
                <c:if test="${list.align == 'left'}"><td style="font-weight: bold;text-align: left">${list.subjName}</td></c:if>
                <c:if test="${list.align == 'center'}"><td>${list.subjName}</td></c:if>
            </c:if>
            <c:if test="${status.index == 27}">
                <td rowspan="2" style="vertical-align: middle">${list.bizType}</td>
                <td colspan="2">${list.bizStage}</td>
            </c:if>
            <c:if test="${status.index == 28}">
                <td colspan="2">${list.bizStage}</td>
            </c:if>

            <c:if test="${list.col1 == '0.00'}">
                <td>-</td>
            </c:if>
            <c:if test="${list.col1 != '0.00'}">
                <c:if test="${list.rowType == 'int'}">
                    <td><fmt:parseNumber integerOnly="true" value="${list.col1}"/></td>
                </c:if>
                <c:if test="${list.rowType == 'money'}">
                    <td><fmt:formatNumber value="${list.col1}" pattern="#,##0.00"/></td>
                </c:if>
                <c:if test="${list.rowType == 'percent'}">
                    <td>${list.col1}%</td>
                </c:if>
            </c:if>

            <c:if test="${list.col2 == '0.00'}">
                <td>-</td>
            </c:if>
            <c:if test="${list.col2 != '0.00'}">
                <c:if test="${list.rowType == 'int'}">
                    <td><fmt:parseNumber integerOnly="true" value="${list.col2}"/></td>
                </c:if>
                <c:if test="${list.rowType == 'money'}">
                    <td><fmt:formatNumber value="${list.col2}" pattern="#,##0.00"/></td>
                </c:if>
                <c:if test="${list.rowType == 'percent'}">
                    <td>${list.col2}%</td>
                </c:if>
            </c:if>
        </tr>
    </c:forEach>

</table>
</div>
