<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择合作方品牌</title>
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
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">合作方品牌</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" autocomplete="off" id="developerBrandCode" placeholder="合作方编号、名称" lay-verify="">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">合作方类型</label>
                    <div class="layui-input-inline">
                        <select id="partner" name="partner" lay-verify="partner" lay-filter="partner">
                            <option value="">请选择</option>
                            <c:forEach items="${partnerList}" var="list">
                                <option value="${list.dicCode}">${list.dicValue}</option>
                            </c:forEach>
                        </select>
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

<script src="${ctx}/meta/pmls/js/developer/openDialogDeveloperBrandList.js?v=${vs}"></script>
</html>

