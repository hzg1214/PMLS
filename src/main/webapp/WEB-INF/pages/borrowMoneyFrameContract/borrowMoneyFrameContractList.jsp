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
                <div class="layui-form-item">

                    <div class="layui-inline">
                        <label class="layui-form-label">协议编号</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="searchValues" autocomplete="off" placeholder="请输入" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">创建日期</label>
                        <div class="layui-input-inline" style="width: 87px;margin-right: 5px;">
                            <input type="text" id="dateStart" class="layui-input" autocomplete="off" placeholder="开始日期"
                                   style="padding-left: 6px;">
                        </div>
                        <div class="layui-form-mid" style="margin-right: 5px;">-</div>
                        <div class="layui-input-inline" style="width: 87px;">
                            <input type="text" id="dateEnd" class="layui-input" autocomplete="off" placeholder="结束日期"
                                   style="padding-left: 6px;">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">公司名称</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="companyName" autocomplete="off" placeholder="请输入" lay-verify="">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item"  style="margin-bottom:0px;">

                    <div class="layui-inline">
                        <label class="layui-form-label">合同状态</label>
                        <div class="layui-input-inline">
                            <select id="approveState" lay-filter="approveState">
                                <option value="">请选择</option>
                                <option value="10401">草签</option>
                                <option value="10402">审核中</option>
                                <option value="10403">审核通过</option>
                                <option value="10404">审核未通过</option>
                                <option value="10405">作废</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">创建人</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="userName"  autocomplete="off" placeholder="请输入" lay-verify="">
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
        <div class="layui-card-header">
            <div class="layui-btn-group toolbar">

<%--                 <shiro:hasPermission name="/pmlsKJXY:KJXY_ADD"> --%>
                    <button class="layui-btn" data-type="addContract">新建框架协议</button>
<%--                 </shiro:hasPermission> --%>
            </div>
        </div>
        <div class="layui-card-body">
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/borrowMoneyFrameContract/borrowMoneyFrameContractList.js?v=${vs}"></script>
</body>
</html>

