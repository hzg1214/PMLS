<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <style>
        .w90 {
            width: 90px !important;
        }

        /*.layui-form-label {*/
        /*    width: 100px;*/
        /*}*/

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
        <!-- Main content -->
        <div class="layui-card-body">
            <div class="layui-form">
                <div class="layui-form-item">
                    <input type="hidden" id="importType" name="importType" value="${importType}">

                    <div class="layui-inline">
                        <label class="layui-form-label">架构年份</label>
                        <div class="layui-input-inline">
                            <select id="org" name="org" lay-verify="required" lay-filter="year">
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">城市</label>
                        <div class="layui-input-inline">
                            <select id="cityNo" name="cityNo" lay-verify="required" lay-search="" lay-filter="cityNo">
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">可发月份</label>
                        <div class="layui-input-inline">
                            <input type="text" name="date" id="date" lay-verify="date" placeholder="yyyy-MM"
                                   autocomplete="off" class="layui-input" >
                        </div>
                    </div>

                    <div class="layui-inline" style="display: none">
                        <label class="layui-form-label">业务类型</label>
                        <div class="layui-input-inline">
                            <select id="businessType" name="businessType" lay-verify="required" lay-search=""
                                    lay-filter="businessType">
                                <option value="19201">联动</option>
                            </select>
                        </div>
                    </div>

<%--                  <div class="layui-inline toolbar">
                        <button class="layui-btn" data-type="reload">查询</button>
                        <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</div>


<div>
    <div class="layui-card">
        <div class="layui-card-header">
            <div class="layui-inline toolbar">
             <shiro:hasPermission name="/pmlsKfCommission:BTN_EXPORT">
                <button class="layui-btn"  data-type="export">导出</button>
             </shiro:hasPermission>
             <shiro:hasPermission name="/pmlsKfCommission:BTN_IMPORT">
                 <button class="layui-btn" id="btnImport" data-type="import">导入</button>
             </shiro:hasPermission>
             <button class="layui-btn" data-type="reload">查询</button>
             <button class="layui-btn" data-type="reset">重置</button>
            </div>
        </div>
        <div class="layui-card-body">
            <table id="contentTable" lay-size="sm" lay-filter="content"></table>
        </div>
    </div>
</div>


<script type="text/javascript" src="${ctx}/meta/pmls/js/commission/commissionKf.js?_v=${vs}"></script>

</body>
</html>
