<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房友新房分销系统</title>
    <%@include file="../../common/common.jsp" %>

    <style>
        body {
            padding: 0px; /*overflow-y: scroll;*/
        }

        .layui-btn-mini {
            height: 22px;
            line-height: 22px;
            padding: 0 5px;
            font-size: 12px;
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
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: -70px;">
                        <label class="layui-form-label">中心</label>
                        <div class="layui-input-inline">
                            <select id="centerGroup" name="centerGroup" xm-select="centerGroup"
                                    xm-select-height="30px" xm-select-search="" xm-select-max="10"
                                    xm-select-show-count='1' xm-select-skin="normal">
                                <option cityId="" value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline" style="margin-left: -35px;">
                        <label class="layui-form-label">业务人员</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" autocomplete="off" id="userCode"
                                   placeholder="请输入业务人员工号、姓名" lay-verify="">
                        </div>
                    </div>

                    <div class="layui-inline toolbar">
                        <button class="layui-btn" data-type="reload">查询</button>
                        <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                    </div>

                </div>
            </div>
            <div>
                <div class="layui-card">
                    <div class="layui-card-header">
                        <div class="layui-btn-group toolbar">
                            <button class="layui-btn" data-type="addUser">新建人员权限</button>
                        </div>
                    </div>
                    <div class="layui-card-body">
                        <div id="mainTable" lay-filter="mainTable"></div>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/basicInformation/personnelPermissions/personnelPermissions.js?v=${vs}"></script>
</body>
</html>
