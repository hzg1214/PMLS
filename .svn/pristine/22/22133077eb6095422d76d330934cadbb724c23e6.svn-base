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

        .layui-table-link {
            cursor: pointer;
        }
    </style>
</head>

<body>

<div >
    <div class="layui-card">
        <!-- Main content -->
        <div class="layui-card-body">
            <div class="layui-form">
                <input type="hidden" id="searchParmCache" name="searchParmCache" value="1">
                <input type="hidden" id="curPageTemp" name="curPageTemp" value="1">
                <input type="hidden" id="sysPageChaneTemp" name="sysPageChaneTemp" value="1">
                <input type="hidden" id="userCode" name="userCode" value="${userCode}">


                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">编号</label>
                        <div class="layui-input-inline">
                            <input type="text" name="cashValues" autocomplete="off" id="cashValues" lay-verify="cashValues" class="layui-input"
                                   placeholder="请款编号、结算书编号">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">订单编号</label>
                        <div class="layui-input-inline">
                            <input type="text" name="reportNo" autocomplete="off" id="reportNo" lay-verify="reportNo" class="layui-input"
                                   placeholder="请输入">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">渠道公司</label>
                        <div class="layui-input-inline">
                            <input type="text" name="companyValues" autocomplete="off" id="companyValues" lay-verify="companyValues" class="layui-input"
                                   placeholder="渠道编号、渠道公司">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">项目</label>
                        <div class="layui-input-inline">
                            <input type="text" name="projectValues" autocomplete="off" id="projectValues" lay-verify="projectValues" class="layui-input"
                                   placeholder="项目编号、项目名称">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">审核状态</label>
                        <div class="layui-input-inline">
                            <select id="approveStatus" name="approveStatus" lay-verify="required" lay-search="" lay-filter="approveStatus">
                                <option value="">请选择或搜索选择</option>
                                <option value="25401">草稿</option>
                                <option value="25402">审核中</option>
                                <option value="25403">审核通过</option>
                                <option value="25404">审核不通过</option>
                                <option value="25405">作废</option>
                            </select>
                        </div>
                    </div>

                    <div class="layui-inline toolbar">
                        <button class="layui-btn" data-type="reload">查询</button>
                        <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                    </div>

                    <shiro:hasPermission name="/pmlsCashBill:QK_DEL">
                        <input type="hidden" name="QK_DEL" id="QK_DEL" value="1">
                        <input type="hidden" name="QK_INVALID" id="QK_INVALID" value="1">
                    </shiro:hasPermission>
                </div>
            </div>
        </div>
    </div>
</div>

<div>
    <div class="layui-card">
        <div class="layui-card-header">
            <div class="layui-btn-group toolbar">

                <shiro:hasPermission name="/pmlsCashBill:QKSQ_ADD">
                    <button class="layui-btn" data-type="addPmlsCashBill">新建请款单</button>
                </shiro:hasPermission>
            </div>
        </div>
        <div class="layui-card-body">
            <table id="contentTable" lay-size="sm" lay-filter="content"></table>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/meta/pmls/js/commission/pmlsCashBillList.js?_v=${vs}"></script>

</body>
</html>