<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择返佣方案</title>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>

    <style>
        body .layui-layer-content {
            overflow: visible !important;
        }

        #planListPopup .layui-table-cell {
            height: auto;
            overflow: visible;
            text-overflow: inherit;
            white-space: normal;
        }

    </style>
</head>

<body>

<div class="layui-card">
    <div id="planListPopup" class="layui-card-body">
        <div class="layui-form ">
            <input type="hidden" id="projectNo" value="${projectNo}"/>
            <input type="hidden" id="reportId" value="${reportId}"/>
            <input type="hidden" id="companyNo" value="${companyNo}"/>
            <input type="hidden" id="dealDate" value="${dealDate}"/>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">方案名称</label>
                    <div class="layui-input-inline">
                        <input type="text" id="planName" name="planName" class="layui-input" placeholder="请输入方案名称">
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

<script src="${ctx}/meta/pmls/js/estate/selectFyplanListPopup.js?v=${vs}"></script>
</html>