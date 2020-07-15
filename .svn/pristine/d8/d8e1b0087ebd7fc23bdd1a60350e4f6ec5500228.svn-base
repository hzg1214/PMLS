<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<c:if test="${fn:length(contentlist) gt 0}"><div class="s-scoll-y" style="height: 450px;"></c:if>
<c:if test="${fn:length(contentlist) le 0}"><div class="s-scoll-y" style="height: auto;"></c:if>
<div class="" style="height: auto; overflow-x:scroll;width:1150px">
<table class="table table-striped table-hover table-bordered" style="width:100%;font-size: 12px;">


<!-- <table class="table table-striped table-hover table-bordered" style="width:100%;font-size: 12px;"> -->
    <tbody class="s-w100">
    <tr>
        <th style="vertical-align: middle" width="8%">变更类型</th>
        <th style="vertical-align: middle" width="8%">门店编号</th>
        <th style="vertical-align: middle" width="16%">门店名称</th>
        <th style="vertical-align: middle" width="10%">变更前</th>
        <th style="vertical-align: middle" width="10%">变更后</th>
        <th style="vertical-align: middle" width="13%">变更时间</th>
        <th style="vertical-align: middle" width="10%">操作人</th>
        <th style="vertical-align: middle">变更原因</th>

    </tr>

    <c:forEach items="${contentlist}" var="list">
    <tr class="J_eatateItem" data-hidenumberflg="0">
        <td style="vertical-align: middle"><c:if test="${list.type == 'M'}">维护人 </c:if><c:if test="${list.type == 'C'}">交易中心</c:if></td>

        <td style="vertical-align: middle">${list.storeNo}</td>
        <td style="vertical-align: middle">${list.name}</td>
        <td style="vertical-align: middle">${list.changebefore}</td>
        <td style="vertical-align: middle">${list.changeafter}</td>
        <td style="vertical-align: middle">${sdk:hmsfs(list.dateCreate)}</td>
        <td style="vertical-align: middle">${list.usercreate}</td>

        <td style="vertical-align: middle">${list.reason}</td>
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
