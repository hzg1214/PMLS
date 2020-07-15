<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<div class="" style="height: auto;">
    <table class="table table-striped table-hover table-bordered">
        <col style="width:150px;">
        <col style="width:260px;">
        <col style="width:auto;">
        <col style="width:80px;">
        <col style="width:80px;">
        <col style="width:120px;">
        <col style="width:auto;">
        <tr>
            <th>模板编号</th>
            <th>模板名称</th>
            <th>适用城市</th>
            <th>状态</th>
            <th>创建人</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${contentlist}" var="list">
         	<tr class="J_eatateItem" data-hidenumberflg="0">
                <td title="${list.wjCode}" class="text-overflow">${list.wjCode}</a></td>
                <td>${list.wjName}</td>
                <td style="text-align:center;" title="${list.wjCityName}" class="text-overflow">${list.wjCityName}</td>
                <td style="text-align:center;" title="${list.wjStatusStr}" class="text-overflow">${list.wjStatusStr}</td>
                <td style="text-align:center;" title="${list.userName}" class="text-overflow">${list.userName}</td>
                <td style="text-align:center;" title="${sdk:ymd2(list.dateCreate)}" class="text-overflow">${sdk:ymd2(list.dateCreate)}</td>
                <td>
                    <a href="javascript:KeFuWj.view(${list.id})">预览</a>
                    <!-- 如果问卷状态为草签  可以修改为未启用 24702 -->
                    <c:if test="${list.wjStatus == '24701'}">
                    	<a href="javascript:KeFuWj.finalize(${list.id})">定稿</a>
                    </c:if>
                    <a href="javascript:KeFuWj.remove(${list.id},${list.satisfactionNum})">删除</a>
                    <!-- 如果问卷状态未启用 24702 可以绑定城市 -->
                    <c:if test="${list.wjStatus == '24702'||list.wjStatus == '24703'}">
                        <a href="javascript:KeFuWj.modify(${list.id})">绑定城市</a>
                    </c:if>
                    <a href="javascript:KeFuWj.exportWj(${list.id})">下载模板</a>
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
