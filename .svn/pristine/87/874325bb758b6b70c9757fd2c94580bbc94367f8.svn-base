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
    <style>
        .layui-table td, .layui-table th {
            font-size: 12px!important;
        }
    </style>
</head>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <input type="hidden" id="jssNo" name="jssNo" value="${reqMap.jssNo}">
                <div class="layui-form-item" style="margin-bottom:0px;">
                    <div class="layui-inline">
                        <label class="layui-form-label">关键词</label>
                        <div class="layui-input-inline">
                            <input type="text" id="searchKey" name="searchKey" lay-verify="searchKey"
                                   lay-filter="searchKey" autocomplete="off"
                                   class="layui-input" placeholder="客户姓名、楼室号">
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>

<script src="${ctx}/meta/pmls/js/scene/sceneExpent/jssDtlListPopup.js?v=${vs}"></script>

</body>
</html>