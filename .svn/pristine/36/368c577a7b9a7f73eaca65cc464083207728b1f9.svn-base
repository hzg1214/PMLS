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
    </style>
</head>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <shiro:hasPermission name="/jsStatement:TERMINATION">
                    <input type="hidden" id='toTermination' value="1"/>
                </shiro:hasPermission>
                <input type="hidden" id='userId' value="${userId}"/>
                <div class="layui-form-item" style="margin-bottom:0px;">
                    <div class="layui-inline">
                        <label class="layui-form-label">结算书编号</label>
                        <div class="layui-input-inline">
                            <input type="text" id="jssNo" name="jssNo" lay-verify="jssNo" lay-filter="jssNo"
                                   autocomplete="off"
                                   class="layui-input" placeholder="请输入">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">订单编号</label>
                        <div class="layui-input-inline">
                            <input type="text" id="reportId" name="reportId" lay-verify="reportId" lay-filter="reportId"
                                   autocomplete="off"
                                   class="layui-input" placeholder="请输入">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">渠道公司</label>
                        <div class="layui-input-inline">
                            <input type="text" id="companyNo" name="companyNo" lay-verify="companyNo"
                                   lay-filter="companyNo" autocomplete="off"
                                   class="layui-input" placeholder="渠道公司、编号">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">项目</label>
                        <div class="layui-input-inline">
                            <input type="text" id="projectNo" name="projectNo" lay-verify="projectNo"
                                   lay-filter="projectNo" autocomplete="off"
                                   class="layui-input" placeholder="项目名称、编号">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">审核状态</label>
                        <div class="layui-input-inline">
                            <select id="approveStatus" name="approveStatus" lay-verify="approveStatus"
                                    lay-filter="approveStatus">
                                <option value="">请选择</option>
                                <option value="10401">草签</option>
                                <option value="10402">审核中</option>
                                <option value="10403">审核通过</option>
                                <option value="10404">审核未通过</option>
                                <option value="10405">作废</option>
                                <option value="10406">终止</option>
                            </select>
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
        <shiro:hasPermission name="/jsStatement:ADDJSSTATEMENT">
        <div class="layui-card-header">
            <div class="layui-btn-group toolbar">
                <button class="layui-btn" data-type="addJsStatement">新建结算书</button>
            </div>
        </div>
        </shiro:hasPermission>
        <div class="layui-card-body">
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>

<script src="${ctx}/meta/pmls/js/jsStatement/jsStatementList.js?v=${vs}"></script>

</body>
</html>