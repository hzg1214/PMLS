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
            <div class="layui-form">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">架构年份</label>
                        <div class="layui-input-inline">
                            <select id="orgYear" name="orgYear" lay-verify="required" lay-filter="orgYear">
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>

                    <%--<div class="layui-inline">
                        <label class="layui-form-label">查询维度</label>
                        <div class="layui-input-inline">
                            <select id="serachKey" name="serachKey" lay-verify="serachKey" lay-filter="serachKey">
                                <option value="y">年度</option>
                                <option value="q">季度</option>
                            </select>
                        </div>
                    </div>--%>

                    <div class="layui-inline">
                        <label class="layui-form-label">查询周期</label>
                        <div id="divYearly"class="layui-input-inline">
                            <select id="yearly" name="yearly" lay-verify="required" lay-filter="yearly">
                                <option value="">请选择</option>
                            </select>
                        </div>
                        <%--<div id="divQuarter" class="layui-input-inline" style="display: none">
                            <select id="quarter" name="quarter" xm-select="quarter"
                                    xm-select-height="30px" xm-select-search="" xm-select-skin="normal"
                                    xm-select-show-count='1'>
                                <option value="1">Q1</option>
                                <option value="2">Q2</option>
                                <option value="3">Q3</option>
                                <option value="4">Q4</option>
                            </select>
                        </div>--%>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">归属区域</label>
                        <div class="layui-input-inline">
                            <select id="region" name="region" xm-select="region"
                                    xm-select-height="30px" xm-select-search="" xm-select-skin="normal"
                                    xm-select-show-count='1'>
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">归属城市</label>
                        <div class="layui-input-inline">
                            <select id="areaCity" name="areaCity" xm-select="areaCity"
                                    xm-select-height="30px" xm-select-search="" xm-select-skin="normal"
                                    xm-select-show-count='1'>
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">所在城市</label>
                        <div class="layui-input-inline">
                            <select id="city" name="city" xm-select="city"
                                    xm-select-height="30px" xm-select-search="" xm-select-skin="normal"
                                    xm-select-show-count='1'>
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">项目名称</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" id="estateNmKey"
                                   name="estateNmKey"
                                   maxlength="50" placeholder="请输入" lay-verify="estateNmKey"
                                   lay-filter="estateNmKey">
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button class="layui-btn" id="btnSearch" data-type="reload">查询</button>
                        <button class="layui-btn" id="btnExport" data-type="export">导出</button>
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

<script type="text/javascript"
        src="${ctx}/meta/pmls/js/statisticsReport/linkConversionRate/linkConversionRate.js?_v=${vs}"></script>

<script type="text/javascript" src="${ctx}/meta/pmls/js/common/reportComm.js?_v=${vs}"></script>
</body>
</html>
