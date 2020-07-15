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
    <script type="text/javascript" src="${ctx}/meta/pmls/js/statisticsReport/projectDetail/projectDetail.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/pmls/js/common/reportComm.js?_v=${vs}"></script>
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
                            <select id="orgYear" name="orgYear" lay-verify="required" lay-filter="organization">
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
                        <label class="layui-form-label">归属项目部</label>
                        <div class="layui-input-inline">
                            <select id="projectDepartmentId" name="projectDepartmentId"
                                    lay-verify="projectDepartmentId" lay-filter="projectDepartmentId">
                                <option value="">请选择</option>
                                <c:forEach items="${rebacklist}" var="list">
                                    <option value="${list.projectDepartmentId}">${list.projectDepartment}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">项目状态</label>
                        <div class="layui-input-inline">
                            <select id="projectStatus" name="projectStatus" xm-select="projectStatus"
                                    lay-verify="projectStatus" lay-filter="projectStatus">
                                <option value="">请选择</option>
                                <option value="20301">跟单</option>
                                <option value="20306">立项</option>
                                <option value="20302">签约</option>
                                <option value="20303">结案</option>
                                <option value="20304">取消跟单</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">项目</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" id="estateNmKey"
                                   name="estateNmKey"
                                   maxlength="50" placeholder="项目编号、名称" lay-verify="estateNmKey"
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
</body>
</html>
