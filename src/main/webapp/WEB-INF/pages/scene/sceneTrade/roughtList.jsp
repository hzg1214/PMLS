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
        .w90 {
            width: 90px !important;
        }
        .w120 {
            width: 120px !important;
        }
        .layui-tab-content .layui-form-label {
            width: 95px !important;
        }
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
                <div class="layui-tab layui-tab-card" lay-filter="roughtTab">
                    <ul class="layui-tab-title">
                        <li lay-id="checking" act-type="checking" class="layui-this">待审核</li>
                        <li lay-id="audited" act-type="audited">已审核</li>
                        <input type="hidden" id="backTab" value="${backTab}">
                    </ul>
                    <div class="layui-tab-content" style="padding-top: 10px">
                        <div id="checkingTabItem" class="layui-tab-item layui-show">
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label">归属项目部</label>

                                    <div class="layui-input-inline">
                                        <select id="checking-projectDepartmentId"
                                                name="checking-projectDepartmentId"
                                                lay-verify="checking-projectDepartmentId"
                                                lay-filter="checking-projectDepartmentId">
                                            <option value="">请选择</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">订单编号</label>
                                    <div class="layui-input-inline">
                                        <input type="text" id="checking-reportId" name="checking-reportId"
                                               lay-verify="checking-reportId" lay-filter="checking-reportId"
                                               autocomplete="off" class="layui-input" placeholder="请输入">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">报备来源</label>
                                    <div class="layui-input-inline">
                                        <select id="checking-customerFrom" name="checking-customerFrom"
                                                lay-verify="checking-customerFrom"
                                                lay-filter="checking-customerFrom">
                                            <option value="">请选择</option>
                                            <option value="17401">PC</option>
                                            <option value="17405">友房通</option>
                                        </select>
                                    </div>
                                </div>
                                <%--<div class="layui-inline">--%>
                                    <%--<label class="layui-form-label">经纪公司</label>--%>
                                    <%--<div class="layui-input-inline">--%>
                                        <%--<input type="text" id="checking-companyNm" name="checking-companyNm"--%>
                                               <%--lay-verify="checking-companyNm" lay-filter="checking-companyNm"--%>
                                               <%--autocomplete="off" class="layui-input" placeholder="请输入">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="layui-inline">--%>
                                    <%--<label class="layui-form-label">楼室号</label>--%>
                                    <%--<div class="layui-input-inline">--%>
                                        <%--<input type="text" id="checking-buildingNo" name="checking-buildingNo"--%>
                                               <%--lay-verify="checking-buildingNo" lay-filter="checking-buildingNo"--%>
                                               <%--lay-filter="checking-companyNm" autocomplete="off" class="layui-input"--%>
                                               <%--placeholder="请输入">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="layui-inline">--%>
                                    <%--<label class="layui-form-label">客户</label>--%>
                                    <%--<div class="layui-input-inline">--%>
                                        <%--<input type="text" id="checking-customerNm" name="checking-customerNm"--%>
                                               <%--lay-verify="checking-customerNm" autocomplete="off"--%>
                                               <%--lay-filter="checking-customerNm" class="layui-input"--%>
                                               <%--placeholder="请输入">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div class="layui-inline">
                                    <label class="layui-form-label">大定日期</label>
                                    <div class="layui-input-inline w90">
                                        <input type="text" name="checking-dateStart" id="checking-dateStart"
                                               lay-verify="checking-dateStart" autocomplete="off"
                                               lay-filter="checking-dateStart" class="layui-input" placeholder="开始日期">
                                    </div>
                                    <div class="layui-input-inline w90">
                                        <input type="text" name="checking-dateEnd" id="checking-dateEnd"
                                               lay-verify="checking-dateEnd" autocomplete="off"
                                               lay-filter="checking-dateEnd"
                                               class="layui-input" placeholder="结束日期">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">关键字</label>
                                    <div class="layui-input-inline w120">
                                        <select id="checking-keyTypeKbn" name="checking-keyTypeKbn" lay-filter="checking-keyTypeKbn">
                                            <option value="">请选择</option>
                                            <option value="0">项目</option>
                                            <option value="2">经纪公司</option>
                                            <option value="3">楼室号</option>
                                            <option value="4">客户</option>
                                            <option value="5">业绩归属人</option>
                                        </select>
                                    </div>
                                    <div class="layui-input-inline">
                                        <input type="text" id="checking-keyword" name="checking-keyword"
                                               lay-verify="checking-keyword" lay-filter="checking-keyword" autocomplete="off"
                                               class="layui-input" placeholder="请输入">
                                    </div>
                                </div>
                                <div class="layui-inline toolbar">
                                    <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
                                    <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <table id="checking-contentTable" lay-size="sm" lay-filter="checking-content"></table>
                            </div>
                        </div>
                        <div id="auditedTabItem" class="layui-tab-item ">
                            <div class="layui-form-item">
                                <div class="layui-inline">
                                    <label class="layui-form-label">归属项目部</label>

                                    <div class="layui-input-inline">
                                        <select id="audited-projectDepartmentId"
                                                name="audited-projectDepartmentId"
                                                lay-verify="audited-projectDepartmentId"
                                                lay-filter="audited-projectDepartmentId">
                                            <option value="">请选择</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">订单编号</label>
                                    <div class="layui-input-inline">
                                        <input type="text" id="audited-reportId" name="audited-reportId"
                                               lay-verify="audited-reportId" lay-filter="audited-reportId"
                                               autocomplete="off"
                                               class="layui-input" placeholder="请输入">
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">报备来源</label>
                                    <div class="layui-input-inline">
                                        <select id="audited-customerFrom" name="audited-customerFrom"
                                                lay-verify="audited-customerFrom"
                                                lay-filter="audited-customerFrom">
                                            <option value="">请选择</option>
                                            <option value="17401">PC</option>
                                            <option value="17405">友房通</option>
                                        </select>
                                    </div>
                                </div>
                                <%--<div class="layui-inline">--%>
                                    <%--<label class="layui-form-label">经纪公司</label>--%>
                                    <%--<div class="layui-input-inline">--%>
                                        <%--<input type="text" id="audited-companyNm" name="audited-companyNm"--%>
                                               <%--lay-verify="audited-companyNm" autocomplete="off"--%>
                                               <%--lay-filter="audited-companyNm" class="layui-input"--%>
                                               <%--placeholder="请输入">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="layui-inline">--%>
                                    <%--<label class="layui-form-label">楼室号</label>--%>
                                    <%--<div class="layui-input-inline">--%>
                                        <%--<input type="text" id="audited-buildingNo" name="audited-buildingNo"--%>
                                               <%--lay-verify="audited-buildingNo" autocomplete="off"--%>
                                               <%--lay-filter="audited-buildingNo" class="layui-input"--%>
                                               <%--placeholder="请输入">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="layui-inline">--%>
                                    <%--<label class="layui-form-label">客户</label>--%>
                                    <%--<div class="layui-input-inline">--%>
                                        <%--<input type="text" id="audited-customerNm" name="audited-customerNm"--%>
                                               <%--lay-verify="audited-customerNm" autocomplete="off"--%>
                                               <%--lay-filter="audited-customerNm" class="layui-input"--%>
                                               <%--placeholder="请输入">--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <div class="layui-inline">
                                    <label class="layui-form-label">审核状态</label>
                                    <div class="layui-input-inline">
                                        <select id="audited-roughAuditStatus"
                                                name="audited-roughAuditStatus"
                                                lay-verify="audited-roughAuditStatus"
                                                lay-filter="audited-roughAuditStatus">
                                            <option value="">请选择</option>
                                            <option value="1">审核通过</option>
                                            <option value="2">审核驳回</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <label class="layui-form-label">关键字</label>
                                    <div class="layui-input-inline w120">
                                        <select id="audited-keyTypeKbn" name="audited-keyTypeKbn" lay-filter="audited-keyTypeKbn">
                                            <option value="">请选择</option>
                                            <option value="0">项目</option>
                                            <option value="2">经纪公司</option>
                                            <option value="3">楼室号</option>
                                            <option value="4">客户</option>
                                            <option value="5">业绩归属人</option>
                                        </select>
                                    </div>
                                    <div class="layui-input-inline">
                                        <input type="text" id="audited-keyword" name="audited-keyword"
                                               lay-verify="audited-keyword" lay-filter="audited-keyword" autocomplete="off"
                                               class="layui-input" placeholder="请输入">
                                    </div>
                                </div>
                                <div class="layui-inline toolbar">
                                    <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
                                    <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <table id="audited-contentTable" lay-size="sm" lay-filter="audited-content"></table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="${ctx}/meta/pmls/js/scene/sceneTrade/roughtList.js?v=${vs}"></script>

</body>
</html>