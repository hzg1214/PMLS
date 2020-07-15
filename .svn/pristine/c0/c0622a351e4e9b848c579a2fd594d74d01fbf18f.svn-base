<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
</head>
<script type="text/javascript">
</script>
<body>
       <div class="" style="height: auto;">
                    <table class="table table-striped table-hover table-bordered" style="table-layout: auto;">
                        <tr>
                            <th>序号</th>
                            <th>修改项目</th>
                            <th>修改时间</th>
                            <th>修改人(工号)</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${contentlist}" var="list" varStatus="status">
                            <tr class="J_eatateItem" data-hidenumberflg="0">
                                <td>
                                    ${status.index + 1}
                                </td>
                                <td>${list.updateIteam}</td>
                                <td>${list.updateDate}</td>
                                <td>${list.userName}(${list.userCode})</td>
                                <td><a href="javascript:void(0)" onclick="relateStoreLog(${list.id},${list.storeId})">查看</a></td>
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
</body>
</html>
