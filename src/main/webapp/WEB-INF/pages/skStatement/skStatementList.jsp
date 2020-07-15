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
        .layui-table-link {
            cursor: pointer;
        }

        .w90 {
            width: 90px !important;
        }

    </style>
</head>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <shiro:hasPermission name="/skStatement:allocate">
                    <input type="hidden" id='toAllocate' value="1"/>
                </shiro:hasPermission>
                <input type="hidden" id='userId' value="${userId}"/>
                <div class="layui-form-item" style="margin-bottom:0px;">
                    <div class="layui-inline">
                        <label class="layui-form-label">编号</label>
                        <div class="layui-input-inline">
                            <input type="text" id="oaNo" name="oaNo" lay-verify="oaNo" lay-filter="oaNo"
                                   autocomplete="off" class="layui-input" placeholder="收入编号、OA编号">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">考核主体</label>
                        <div class="layui-input-inline">
                            <input type="text" id="khCode" name="khCode" lay-verify="khCode" lay-filter="khCode"
                                   autocomplete="off" class="layui-input" placeholder="请输入">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">项目</label>
                        <div class="layui-input-inline">
                            <input type="text" id="projectNo" name="projectNo" lay-verify="projectNo"
                                   lay-filter="projectNo" autocomplete="off" class="layui-input"
                                   placeholder="项目编号、项目名称">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">客户</label>
                        <div class="layui-input-inline">
                            <input type="text" id="customerName" name="customerName" lay-verify="customerName"
                                   lay-filter="customerName" autocomplete="off" class="layui-input"
                                   placeholder="请输入">
                        </div>
                    </div>


                    <div class="layui-inline">
                        <label class="layui-form-label">入账日期</label>
                        <div class="layui-input-inline w90">
                            <input type="text" name="dateStart" id="dateStart" lay-verify="dateStart" autocomplete="off"
                                   lay-filter="dateStart" class="layui-input" placeholder="开始日期">
                        </div>
                        <div class="layui-input-inline w90">
                            <input type="text" name="dateEnd" id="dateEnd" lay-verify="dateEnd" autocomplete="off"
                                   lay-filter="dateEnd" class="layui-input" placeholder="结束日期">
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
        <div class="layui-card-body">
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>
<script src="${ctx}/meta/pmls/js/skStatement/skStatementList.js?v=${vs}"></script>

</body>
</html>