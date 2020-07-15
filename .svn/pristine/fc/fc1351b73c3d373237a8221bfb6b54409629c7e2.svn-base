<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <title>房友新房分销系统</title>
    <style>
        body {
            padding: 0px;
        }

        .w90 {
            width: 90px !important;
        }

        .w315 {
            width: 315px !important;
        }
    </style>
</head>

<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <input type="hidden" name="searchParmCache" value="1">
                <input type="hidden" name="countDateEndStr" id="countDateEndStr" value="${countDateEndStr}">
                <div class="layui-form-item">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">归属项目部</label>
                            <div class="layui-input-inline">
                                <select id="projectDepartmentId" name="projectDepartmentId" lay-verify="required"
                                        lay-filter="projectDepartmentId">
                                    <option value="" selected>请选择</option>
                                    <c:forEach items="${rebacklist}" var="list">
                                        <option value="${list.projectDepartmentId}">${list.projectDepartment}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">楼盘名称</label>
                            <div class="layui-input-inline">
                                <input type="text" id="estateNmKey" name="estateNmKey" lay-verify="estateNmKey"
                                       lay-filter="estateNmKey"
                                       class="layui-input" autocomplete="off" placeholder="请输入项目编号、楼盘名称">

                                <%--<select id="estateNmKey" name="estateNmKey" lay-verify="required" lay-search="" lay-filter="estateNmKey"--%>
                                <%--xm-select="estateNmKey" xm-select-search="" xm-select-show-count = '1' xm-select-height="30px" xm-select-skin="normal">--%>
                                <%--<option value="">请选择或搜索选择</option>--%>
                                <%--<c:forEach items="${estateList}" var="list">--%>
                                <%--<option value="${list}">${list}</option>--%>
                                <%--</c:forEach>--%>
                                <%--</select>--%>

                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">业务阶段</label>
                            <div class="layui-input-inline w90">
                                <select id="businessType" name="businessType" lay-verify="required"
                                        lay-search=""
                                        lay-filter="businessType">
                                    <option value="" selected>请选择</option>
                                    <option value="DD">大定</option>
                                    <option value="CX">成销</option>
                                </select>
                            </div>
                            <div class="layui-input-inline w90">
                                <input type="text" name="countDateStart" id="countDateStart"
                                       lay-verify="countDateStart" autocomplete="off"
                                       lay-filter="countDateStart" class="layui-input"
                                       placeholder="开始日期">
                            </div>
                            <div class="layui-input-inline w90">
                                <input type="text" name="countDateEnd" id="countDateEnd"
                                       lay-verify="countDateEnd" autocomplete="off"
                                       lay-filter="countDateEnd"
                                       class="layui-input"  placeholder="结束日期">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">订单编号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="reportId" id="reportId" lay-verify="reportId"
                                       autocomplete="off"
                                       class="layui-input" placeholder="请输入">
                            </div>
                            <div class="layui-input-inline w315">
                                <input type="text" name="searchKey" id="searchKey" lay-verify="searchKey"
                                       autocomplete="off"
                                       placeholder="请输入楼室号、客户姓名、电话" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <div class="layui-input-inline">
                                <input type="checkbox" name="objNum" id="objNum" lay-filter="objNum"
                                       lay-skin="primary" title="已维护多个返佣对象">
                            </div>
                        </div>
                        <div class="layui-inline toolbar">
                            <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
                            <button class="layui-btn layui-btn-normal" data-type="reset">重置</button>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div>
    <div class="layui-card">
        <div class="layui-card-header">
            <div class="layui-btn-group toolbar">
                <button class="layui-btn" data-type="achieveMentChange">维护返佣对象</button>
            </div>
        </div>
        <div class="layui-card-body">
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/meta/pmls/js/commission/lnkYjReportListPmls.js?_v=${vs}"></script>
</body>
</html>