<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <title>房友新房分销系统</title>

</head>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <input type="hidden" name="storeId" id="storeId" value="${storeId}">
            <div id="logListTable" lay-filter="logListTable"></div>
        </div>
    </div>
</div>
<script src="${ctx}/meta/pmls/js/store/storeLogList.js?v=${vs}"></script>

</body>
</html>