<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div>
    <table class="table table-striped table-hover table-bordered">
        <col style="width:50px;">
        <col style="width:100px;">
        <col style="width:100px;">
        <col style="width:100px;">
        <col style="width:100px;">
        <col style="width:100px;">
        <col style="width:100px;">
        <tr>
            <th scope="col">序号</th>
            <th scope="col">归属城市</th>
            <th scope="col">核算主体编号</th>
            <th scope="col">核算主体</th>
            <th scope="col">创建人</th>
            <th scope="col">创建时间</th>
            <th scope="col">操作</th>
        </tr>

        <c:forEach items="${contentlist}" var="list">
            <tr>
                <td align="center">${list.rowNum}</td>
                <td align="center">${list.cityName}</td>
                <td align="center">${list.accountProjectNo}</td>
                <td align="center">${list.accountProject}</td>
                <td align="center">${list.userNameCreate}</td>
                <td align="center">
                        ${sdk:ymd2(list.dateCreate)}
                </td>
                <td align="center">
                    <div class="mailopre">
                        <a href="javascript:AccountProject.modify('${list.id}')">编辑</a>
                        <a href="javascript:AccountProject.remove('${list.id}')">删除</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
    ${pageInfo.html}
</div>
<c:if test="${fn:length(contentlist) le 0}">
    <div class="nodata"><i class="icon-hdd"></i>
        <p>暂无数据...</p></div>
</c:if>