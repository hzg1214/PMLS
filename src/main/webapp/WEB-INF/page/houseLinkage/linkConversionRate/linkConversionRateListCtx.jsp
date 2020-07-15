<%--
  Created by IntelliJ IDEA.
  User: haidan
  Date: 2019/8/28
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="s-scoll-y" style="height: auto;">
    <table class="table table-striped table-hover table-bordered" style="max-width: none;width:1585px;font-size: 12px;">
    <tbody class="s-w100">
    <tr>
        <th rowspan="2" style="vertical-align: middle;width: 45px;">序号</th>
        <th rowspan="2" style="vertical-align: middle;width: 90px;">归属区域</th>
        <th rowspan="2" style="vertical-align: middle;width: 90px;">归属城市</th>
        <%--<th rowspan="2" style="vertical-align: middle;width: 130px;">归属中心</th>--%>
        <th rowspan="2" style="vertical-align: middle;width: 140px;">项目编号</th>
        <th rowspan="2" style="vertical-align: middle;width: 140px;">项目名称</th>

        <th colspan="8" style="vertical-align: middle;width: 960px;">当期</th>
    </tr>
    <tr>
        <th style="vertical-align: middle;width: 150px;">结转大定</th>
        <th style="vertical-align: middle;width: 150px;">新增大定</th>
        <th style="vertical-align: middle;width: 150px;">累计大定</th>
        <th style="vertical-align: middle;width: 150px;">新增成销</th>
        <th style="vertical-align: middle;width: 120px;">结转成销转化率</th>
        <th style="vertical-align: middle;width: 120px;">新增成销转化率</th>
        <th style="vertical-align: middle;width: 120px;">整体成销转化率</th>
        <th style="vertical-align: middle;width: 120px;">大定转成销周期</th>
    </tr>

    <c:forEach items="${contentlist}" var="list">
    <c:if test="${list.estateName == '合计'}" ><tr style="font-weight: bold"></c:if>
    <c:if test="${list.estateName != '合计'}" ><tr></c:if>

        <td style="vertical-align: middle">${list.rowNum}</td>
        <td style="vertical-align: middle">${list.gsRegion}</td>
        <td style="vertical-align: middle">${list.gsCity}</td>
        <%--<td style="vertical-align: middle">${list.gsCenter}</td>--%>
        <td style="vertical-align: middle">${list.projectNo}</td>
        <td style="vertical-align: middle">${list.estateName}</td>

        <td style="vertical-align: middle">${list.dqJzdd}</td>
        <td style="vertical-align: middle">${list.dqXzdd}</td>
        <td style="vertical-align: middle">${list.dqLjdd}</td>
        <td style="vertical-align: middle">${list.dqXzcx}</td>
        <td style="vertical-align: middle">${list.dqJzcxRate}</td>
        <td style="vertical-align: middle">${list.dqXzcxRate}</td>
        <td style="vertical-align: middle">${list.dqZtcxRate}</td>
        <td style="vertical-align: middle">${list.dqDdzcxzq}</td>
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
