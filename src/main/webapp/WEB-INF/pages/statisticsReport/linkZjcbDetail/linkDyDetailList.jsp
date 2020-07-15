<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="../../common/common.jsp" %>
    <style>
    </style>
</head>

<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <div class="layui-tab layui-tab-card" lay-filter="myTab">

                    <ul class="layui-tab-title">
                        <li>保证金&诚意金</li>
                        <li class="layui-this">垫佣</li>
                    </ul>

                    <div style="padding: 10px">
                        <div class="layui-form-item">
                            <div class="layui-inline">

                                <label class="layui-form-label">架构年份</label>
                                <div class="layui-input-inline">
                                    <select id="orgYear" name="orgYear" lay-verify="required" lay-filter="orgYear">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
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
                                <label class="layui-form-label">归属中心</label>
                                <div class="layui-input-inline">
                                    <select id="centerGroup" name="centerGroup" xm-select="centerGroup"
                                            xm-select-height="30px" xm-select-search="" xm-select-skin="normal"
                                            xm-select-show-count='1'>
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">项目</label>
                                <div class="layui-input-inline">
                                    <input type="text" class="layui-input" id="estateNm"  autocomplete="off" placeholder="请输入项目名称、编号"
                                           lay-verify="">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">报备编号</label>
                                <div class="layui-input-inline">
                                    <input type="text" class="layui-input" id="reportId"  autocomplete="off" placeholder="请输入"
                                           lay-verify="">
                                </div>
                            </div>
                            <%--<div class="layui-inline">--%>
                                <%--<label class="layui-form-label">期初</label>--%>
                                <%--<div class="layui-input-inline">--%>
                                    <%--<input type="text" class="layui-input" id="dateStage"  autocomplete="off" placeholder="请选择">--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="layui-inline toolbar">
                                <button class="layui-btn" id="btnSearch" data-type="reload">查询</button>
                                <button class="layui-btn" id="btnExport" data-type="export">导出</button>
                            </div>
                        </div>

                        <div class="form-inline" id="exportMsg"
                                <c:if test="${userReportSize eq 0}" > style="display: none" </c:if>
                        >
                            <li>
                                <label class="lab"
                                       style="width: 200px;font-size: 12px;font-weight: bold;color: red">您当前有<span
                                        id="reportSize">${userReportSize}</span>个文件正在下载中……</label>
                            </li>
                        </div>
                        <div>
                            <table id="contentTable" lay-size="sm" lay-filter="content"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript"
        src="${ctx}/meta/pmls/js/statisticsReport/linkZjcbDetail/linkDyDetail.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/pmls/js/common/reportComm.js?_v=${vs}"></script>
</body>
</html>
