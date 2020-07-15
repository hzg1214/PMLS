<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;width:750px;">
    <table class="table table-striped table-bordered table-hover" style="font-size: 14px;">
        <col style="width:40px;">
        <col style="width:180px;">
        <col style="width:250px;">
        <col style="width:100px;">
        <col style="width:100px;">
        <tr>
            <th></th>
            <th>协议OA编号</th>
            <th>供应商名称</th>
            <th>合作开始日期</th>
            <th>合作结束日期</th>
        </tr>

        <c:forEach items="${contentlist}" var="list">
            <tr class="J_eatateItem" data-hidenumberflg="0">
                <td><input type="radio" name="selectFrmAgreement"
                           data-frameOaNo="${list.frameOaNo}"
                           data-frameOaName="${list.frameOaName}"
                           data-vendorName="${list.vendorName}"
                           data-vendorCode="${list.vendorCode}"
                           data-vendorId="${list.vendorId}"
                           data-cooperateStartTime="${list.cooperateStartTime}"
                           data-cooperateEndTime="${list.cooperateEndTime}"
                ></td>
                <td>${list.frameOaNo}</td>
                <td> ${list.vendorName} </td>
                <td>${list.cooperateStartTime}</td>
                <td>${list.cooperateEndTime}</td>
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