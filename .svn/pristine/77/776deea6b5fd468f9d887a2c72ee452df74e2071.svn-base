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

        body .layui-layer-content {
            overflow: visible !important;
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
            <div class="layui-form">
                <div class="layui-form-item" style="margin-bottom:0px;">

                    <div class="layui-inline" style="margin-left: -24px;">
                        <label class="layui-form-label">归属城市</label>
                        <div class="layui-input-inline">
                            <select id="cityNo" name="cityNo" lay-verify="cityNo" lay-filter="cityNo">
                                <option value="" selected="selected">请选择</option>
                                <c:forEach items="${cityList}" var="city">
                                    <option value="${city.cityNo}">${city.cityName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">核算主体</label>
                        <div class="layui-input-inline">
                            <input type="text" id="searchKey" name="searchKey" lay-verify="searchKey"
                                   lay-filter="searchKey" autocomplete="off" class="layui-input" placeholder="核算主体名称、编码">
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
                        <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div>
    <div class="layui-card">
        <div class="layui-card-header">
            <div class="layui-btn-group toolbar" style="margin-top: -20px;margin-left: 15px;">
                <button class="layui-btn"  data-type="add">新建核算主体</button>
            </div>
        </div>
        <div class="layui-card-body">
            <div class="layui-form-item" style="margin-top: -10px;margin-left: 15px;">
                <table id="contentTable" lay-size="sm" lay-filter="content"></table>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/meta/pmls/js/scene/sceneTrade/accountProject.js?v=${vs}"></script>
</body>
</html>