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
<script type="application/javascript">
    <%--var progressStatusList='${progressStatusList}';//借佣进度--%>
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <div class="layui-form-item"  style="margin-bottom:0px;">

                    <div class="layui-inline">
                        <label class="layui-form-label">编号</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="jyapplyNo" autocomplete="off" placeholder="请输入" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">渠道公司</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="companyName" autocomplete="off" placeholder="请输入" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">楼盘名称</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="projectName" autocomplete="off" placeholder="请输入" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">是否逾期</label>
                        <div class="layui-input-inline">
                            <select id="yqFlag">
                                <option value="">请选择</option>
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">还款状态</label>
                        <div class="layui-input-inline">
                            <select id="planStatus">
                                <option value="">请选择</option>
                                <option value="28401">待还款</option>
                                <option value="28402">已还款</option>
                            </select>
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
<script src="${pageContext.request.contextPath}/meta/pmls/js/borrowMoney/hkPlanList.js?v=${vs}"></script>
</body>
</html>

