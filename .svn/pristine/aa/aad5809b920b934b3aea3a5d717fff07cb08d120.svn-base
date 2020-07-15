<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择楼室号</title>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>

    <style>
        body .layui-layer-content {
            overflow: visible !important;
        }
    </style>
</head>

<body>

<div class="layui-card">
    <div class="layui-card-body">
        <div class="layui-form ">
            <input type="hidden" id="projectNo" value="${projectNo}"/>
            <input type="hidden" id="oldBuildingNoId" value="${oldBuildingNoId}"/>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">楼室号</label>
                    <div class="layui-input-inline">
                        <input type="text" id="buildingNo" name="buildingNo" class="layui-input" placeholder="请输入">
                    </div>
                </div>
                <div class="layui-inline toolbar">
                    <button type="button" class="layui-btn" data-type="reload">查询</button>
                </div>
            </div>
        </div>
        <div id="mainTable" lay-filter="mainTable"></div>
    </div>
</div>

</body>

<script src="${ctx}/meta/pmls/js/scene/sceneTrade/selectBuildingNoPage.js?v=${vs}"></script>
</html>

