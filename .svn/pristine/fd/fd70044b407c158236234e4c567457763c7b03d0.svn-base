<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择冲抵房源</title>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>

    <style>
        body {
            padding: 0px;
        }
        .layui-table-link {
            cursor: pointer;
        }
    </style>
</head>

<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form">
                <input type="hidden" id="companyNo" name="companyNo" class="layui-input" value="${companyNo}">
                <input type="hidden" id="reportNoList" name="reportNoList" class="layui-input" value="${reportNoList}">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">关键字</label>
                        <div class="layui-input-inline">
                            <input type="text" id="searchKey" name="searchKey" class="layui-input" placeholder="报备编号、客户姓名、项目、楼市号">
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button type="button" class="layui-btn" data-type="search">查询</button>
                    </div>
                </div>
            </form>
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>
<script src="${ctx}/meta/pmls/js/scene/sceneExpent/offsetInfoListPopup.js?v=${vs}"></script>
</body>