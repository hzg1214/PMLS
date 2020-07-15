<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="../../common/common.jsp" %>
    <script src="${pageContext.request.contextPath}/meta/layui/lay/modules/newTable.js?v=${vs}"></script>
    <style>
    </style>
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
                    <div class="layui-inline">
                        <label class="layui-form-label">查询维度</label>
                        <div class="layui-input-inline">
                            <select id="searchType" name="searchType" lay-filter="searchType">
                                <option value="1">项目（业绩）</option>
                                <option value="2">考核</option>
                                <option value="3">项目（成本）</option>
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
                    <%--<div class="layui-inline">--%>
                        <%--<label class="layui-form-label">归属中心</label>--%>
                        <%--<div class="layui-input-inline">--%>
                            <%--<select id="centerGroup" name="centerGroup" xm-select="centerGroup" disabled="disabled"--%>
                                    <%--xm-select-height="30px" xm-select-search="" xm-select-skin="normal"--%>
                                    <%--xm-select-show-count='1'>--%>
                                <%--<option value="">请选择</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="layui-inline">
                        <label class="layui-form-label">项目编号</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" id="projectNo"
                                   placeholder="请输入" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">项目状态</label>
                        <div class="layui-input-inline">
                            <select id="projectStatus" name="projectStatus" xm-select="projectStatus"
                                    xm-select-height="30px" xm-select-search="" xm-select-skin="normal"
                                    xm-select-show-count='1'>
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline noProject">
                        <label class="layui-form-label">起始月份</label>
                        <div class="layui-input-inline">
                            <input type="text" name="startDate" id="startDate" autocomplete="off" lay-verify="startDate"
                                   placeholder="请选择" lay-verify="required" class="layui-input" readonly>
                        </div>
                    </div>
                    <div class="layui-inline noProject">
                        <label class="layui-form-label">截止月份</label>
                        <div class="layui-input-inline">
                            <input type="text" name="endDate" id="endDate" autocomplete="off" lay-verify="endDate"
                                   placeholder="请选择" lay-verify="required" class="layui-input" readonly>
                        </div>
                    </div>
                    <div class="layui-inline project">
                        <label class="layui-form-label">起始日期</label>
                        <div class="layui-input-inline">
                            <input type="text" name="startDateProject" autocomplete="off" id="startDateProject"
                                   lay-verify="startDateProject"
                                   placeholder="请选择" lay-verify="required" class="layui-input" readonly>
                        </div>
                    </div>
                    <div class="layui-inline project">
                        <label class="layui-form-label">截止日期</label>
                        <div class="layui-input-inline">
                            <input type="text" name="endDateProject" autocomplete="off" id="endDateProject"
                                   lay-verify="endDateProject"
                                   placeholder="请选择" lay-verify="required" class="layui-input" readonly>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">楼盘名称</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" id="estateNm"
                                   placeholder="请输入" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">楼盘城市</label>
                        <div class="layui-input-inline">
                            <select id="realityCityNo" name="realityCityNo" lay-verify="required"
                                    lay-search="" lay-filter="realityCityNo">
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">楼盘区域</label>
                        <div class="layui-input-inline">
                            <select id="districtNo" name="districtNo" lay-verify="required"
                                    lay-search="" lay-filter="districtNo">
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">楼盘地址</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" id="address" placeholder="请输入"
                                   lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button class="layui-btn" id="btnSearch" data-type="reload">查询</button>
                        <button class="layui-btn" id="btnExport" data-type="export">导出</button>
                    </div>
                </div>
                <div class="form-inline" id="exportMsg"
                           <c:if test="${userReportSize eq 0}" > style="display: none" </c:if>
                   >
                       <li>
                           <label class="lab" style="width: 200px;font-size: 12px;font-weight: bold;color: red">您当前有<span
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
<script type="text/javascript"
        src="${ctx}/meta/pmls/js/statisticsReport/linkDetail/linkProjectDetail.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/pmls/js/common/reportComm.js?_v=${vs}"></script>
</body>
</html>
