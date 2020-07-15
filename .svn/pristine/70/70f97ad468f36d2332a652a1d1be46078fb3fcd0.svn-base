<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择收入类合同</title>
    <%@include file="../common/common.jsp" %>

    <style>
        body{padding: 0px; /*overflow-y: scroll;*/}
        .searchForm{
            padding-top: 5px;
        }
    </style>
</head>
<script type="application/javascript">
    var projectNo = '${projectNo}';
</script>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form searchForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">合同编号</label>
                        <div class="layui-input-inline">
                            <input type="text" id="htOaNo" name="htOaNo" class="layui-input"   lay-verify="" placeholder="请输入">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">合同类型</label>
                        <div class="layui-input-inline">
                            <select id="contractType" name="contractType" lay-verify="required" lay-search="">
                                <option value="">请选择</option>
                                <option value="收入合同">收入合同</option>
                                <option value="进场确认函">进场确认函</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button type="button" class="layui-btn" data-type="reload">查询</button>
<%--                        <button type="reset" class="layui-btn">重置</button>--%>
                    </div>
                </div>
            </form>
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>

</div>

</body>
</html>
<script type="text/html" id="htContentTpl">
    收入标的&nbsp;：&nbsp;{{d.incomeSubject}}；
    收入结算条件&nbsp;：&nbsp;{{d.commissionMemo}}；
    收入结算描述&nbsp;：&nbsp;{{d.commissionMemoRemark}}；
    返佣标准&nbsp;：&nbsp;{{d.rtnCommission}}；
    返佣结算条件&nbsp;：&nbsp;{{d.rtnCommissionMemo}}
</script>

<script type="text/javascript" src="${ctx}/meta/pmls/js/cooperation/selectEstateHt.js?_v=${vs}"></script>
