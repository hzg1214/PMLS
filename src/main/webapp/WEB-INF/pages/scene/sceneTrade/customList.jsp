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


</head>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">

                <div class="layui-form-item" style="margin-bottom:0px;">
                    <div class="layui-inline">
                        <label class="layui-form-label">客户</label>
                        <div class="layui-input-inline">
                            <input type="text" id="customerNm" name="customerNm"
                                   lay-verify="customerNm"
                                   lay-filter="customerNm" class="layui-input" placeholder="请输入客户/客户手机">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">客户有效性</label>
                        <div class="layui-input-inline">
                            <select id="vaild" name="vaild" lay-verify="vaild" lay-filter="vaild">
                                <option value="">请选择</option>
                                <option value="1">有效</option>
                                <option value="0">无效</option>
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
        <div class="layui-card-body">
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>

<script src="${ctx}/meta/pmls/js/scene/sceneTrade/customList.js?v=${vs}"></script>

</body>
</html>