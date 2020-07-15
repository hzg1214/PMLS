<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房友新房分销系统</title>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>

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
                <div class="layui-tab layui-tab-card" lay-filter="businessTab">
                    <ul class="layui-tab-title">
                        <li lay-id="all" act-type="all" class="layui-this">全部</li>
                        <li lay-id="channel" act-type="channel">渠道</li>
                        <input type="hidden" id="backTab" value="${backTab}">

                        <shiro:hasPermission name="/BUSINESS:EDIT">
                            <input type="hidden" id='editPermission' value="1"/>
                        </shiro:hasPermission>
                        <input type="hidden" id="myself_userId" value="${userId}">

                    </ul>
                    <div class="layui-tab-content" style="padding-top: 10px">
                        <div id="allTabItem" class="layui-tab-item layui-show">
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label">经纪公司</label>
                                    <div class="layui-input-inline">
                                        <input type="text" class="layui-input" id="all-companyNo"
                                               autocomplete="off"  placeholder="经纪公司编号、名称"
                                               lay-verify="">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">品牌</label>
                                    <div class="layui-input-inline">
                                        <input type="text" class="layui-input" id="all-brandName" placeholder="请输入"
                                               autocomplete="off"  lay-verify="">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">业务类型</label>
                                    <div class="layui-input-inline">
                                        <select id="all-brandType" name="all-brandType" lay-filter="all-brandType">
                                            <option value="">请选择</option>
                                            <option value="29401">分销渠道</option>
                                            <option value="1">借佣渠道</option>
                                            <option value="29402">其它</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="layui-inline toolbar">
                                    <button class="layui-btn" data-type="reload">查询</button>
                                    <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <table id="all-contentTable" lay-size="sm" lay-filter="all-content"></table>
                            </div>
                        </div>
                        <div id="channelTabItem" class="layui-tab-item ">
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label">经纪公司</label>
                                    <div class="layui-input-inline">
                                        <input type="text" class="layui-input" id="channel-companyNo"
                                               autocomplete="off"  placeholder="经纪公司编号、名称"
                                               lay-verify="">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">品牌</label>
                                    <div class="layui-input-inline">
                                        <input type="text" class="layui-input" id="channel-brandName" placeholder="请输入"
                                               autocomplete="off" lay-verify="">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">类型</label>
                                    <div class="layui-input-inline">
                                        <select id="channel-isProcuration" name="channel-isProcuration"
                                                lay-filter="channel-isProcuration">
                                            <option value="">请选择</option>
                                            <option value="0">非借佣渠道</option>
                                            <option value="1">借佣渠道</option>
                                        </select>
                                    </div>
                                </div>


                                <div class="layui-inline toolbar">
                                    <button class="layui-btn" data-type="reload">查询</button>
                                    <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                                </div>
                            </div>
                            <shiro:hasPermission name="/pmlsQD:QD_ADD">
                                <div class="layui-form-item">
                                    <button class="layui-btn" data-type="addBusiness">新建借佣渠道</button>
                                </div>
                            </shiro:hasPermission>
                            <div class="layui-form-item">
                                <table id="channel-contentTable" lay-size="sm" lay-filter="channel-content"></table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/meta/pmls/js/channelBusiness/businessManagerList.js?v=${vs}"></script>
</body>
</html>

