
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp"%>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
    <link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/meta/js/report/expand/multi.select.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/permission/permissionList.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>

</head>

<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId" />
</jsp:include>


<div class="container theme-hipage ng-scope" role="main">
    <h4 class="border-bottom pdb10" style="margin-right: -15px;margin-left:-15px;"><strong>权限管理</strong></h4>
    <div class="row">
        <ul class="nav nav-tabs nav-tabs-header">
            <li role="presentation">
                <a href="${ctx}/dataAuthority/dataAuthorityList">数据权限配置</a>
            </li>
            <li role="presentation" class="active">
                <a href="${ctx}/permission/permissionList">功能权限配置</a>
            </li>
        </ul>
        <div class="page-content">
            <form  id="LoadPermissionForm">

                <div class="panel panel-default" style="margin-bottom:10px;">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="form-group" style="margin-right: 1%;">
                                工号：

                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="userCode" name="userCode"  placeholder="请输入工号" size="32">
                            </div>
                            <div class="form-group" style="margin-right: 1%;">
                                模块名称：

                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="functionName" name="functionName"  placeholder="请输入模块名称" size="32">
                            </div>

                            <button type="button" class="btn btn-primary" id="J_search" onclick="javascript:Permission.search();">查询</button>
                            <button type="button" id="btn-reset" class="btn btn-default" onclick="javascript:Permission.reset()">重置</button>
                        </div>

                        <div class="form-inline">
                        </div>
                    </div>
                </div>
                <div id="errorMessage" style="color: red;margin-bottom: 5px; visibility: hidden; height: 20px;"></div>
                <div>操作按钮：

                    <button type="button" class="btn btn-primary" id="insertData" onclick="javascript:Permission.insert();">新增</button>
                    <button type="button" class="btn btn-primary" id="deleteData" onclick="javascript:Permission.delete();">删除</button>
                </div>
                <!-- 异步加载列表内容 -->
                <div id="load_content">
                    <div id="LoadPermissionList"></div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    var searchParamMap = '${searchParamMap}';
    if (searchParamMap != undefined && searchParamMap != null && searchParamMap != '') {
        Estate.setSearchParams(searchParamMap);
    }
</script>

</html>









