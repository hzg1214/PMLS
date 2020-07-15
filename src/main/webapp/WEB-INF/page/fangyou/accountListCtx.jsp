<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;">
    <input type="hidden" id="J_accountCount" value="${contentlist.size()}"/>
    
    <table class="table table-striped table-hover table-bordered">
        <tr>
            <th>账号</th>
            <th>联系方式</th>
            <th>修改时间</th>
        </tr>
        <c:forEach items="${contentlist}" var="list">
            <tr class="J_eatateItem" data-hidenumberflg="0">
                <td>${list.empName}</td>
                <td>${list.eel}</td>
                <td>${list.date}</td>
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

