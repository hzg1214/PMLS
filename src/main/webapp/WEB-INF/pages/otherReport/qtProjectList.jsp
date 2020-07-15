<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房友新房分销系统</title>
    <%@include file="../common/common.jsp" %>

    <style>
        body{padding: 0px; /*overflow-y: scroll;*/}
    </style>
</head>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
	                <div class="layui-form-item" style="margin-top:10px;">
	
	                    <div class="layui-inline">
	                        <label class="layui-form-label">归属项目部</label>
	                        <div class="layui-input-inline">
	                            <select id="projectDepartmentId" name="projectDepartmentId"
	                                    lay-verify="projectDepartmentId" lay-filter="projectDepartmentId">
	                                <option value="">请选择</option>
	                            </select>
	                        </div>
	                    </div>
	                    <div class="layui-inline">
	                        <label class="layui-form-label">项目编号</label>
	                        <div class="layui-input-inline">
	                            <input type="text" id="projectNo" name="projectNo" autocomplete="off" lay-verify="projectNo"
	                                   lay-filter="projectNo" class="layui-input" placeholder="请输入">
	                        </div>
	                    </div>
	                    <div class="layui-inline">
	                        <label class="layui-form-label">项目名称</label>
	                        <div class="layui-input-inline">
	                            <input type="text" id="estateNm" name="estateNm" autocomplete="off" lay-verify="estateNm" lay-filter="estateNm"
	                                   class="layui-input" placeholder="请输入">
	                        </div>
	                    </div>
	                    <div class="layui-inline toolbar">
	                        <button class="layui-btn" data-type="reload">查询</button>
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

<script src="${ctx}/meta/pmls/js/otherReport/qtProjectList.js?v=${vs}"></script>


</body>