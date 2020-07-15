<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择维护人</title>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>

    <style>
        body{padding: 0px; /*overflow-y: scroll;*/}
        .searchForm{
            padding-top: 5px;
        }
    </style>
</head>
<script type="application/javascript">

</script>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form searchForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <input type="hidden" name="storeCenterId" id="storeCenterId" value="${reqMap.storeCenterId}">
                        <input type="hidden" name="storeId" id="storeId" value="${reqMap.storeId}">
                        <input type="hidden" name="acCityNo" id="acCityNo" value="${reqMap.acCityNo}">
                        <label class="layui-form-label">维护人</label>
                        <div class="layui-input-inline">
                            <input type="text" id="maintainerName" name="maintainerName" class="layui-input" placeholder="维护人姓名、工号">
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button type="button" class="layui-btn" data-type="reload">查询</button>
                    </div>
                </div>
            </form>
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/meta/pmls/js/store/relateStoreUser.js?_v=${vs}"></script>
</body>
</html>

