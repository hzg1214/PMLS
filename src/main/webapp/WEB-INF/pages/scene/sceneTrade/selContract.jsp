<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择分销合同</title>
    <%@include file="../../common/common.jsp" %>

    <style>
        body{padding: 0px; /*overflow-y: scroll;*/}
        .searchForm{
            padding-top: 5px;
        }
    </style>
</head>
<script type="application/javascript">

</script>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form searchForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">合同</label>
                        <div class="layui-input-inline">
                            <input type="text" id="contractNo" name="contractNo"  autocomplete="off" class="layui-input" placeholder="请输入合同编号、名称">
                        </div>
                        <input type="hidden" name="projectNo" id="projectNo" value="${reqMap.projectNo}">
						<input type="hidden" name="companyNo" id="companyNo" value="${reqMap.companyNo}">
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">合同类型</label>
                        <div class="layui-input-inline">
                            <select id="htType" name="htType" lay-filter="htType">
                                <option value="">请选择</option>
                                <option value="返佣">返佣</option>
                                <option value="垫佣">垫佣</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button type="button" class="layui-btn" data-type="reload">搜索</button>
                    </div>
                </div>
            </form>
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/meta/pmls/js/scene/sceneTrade/selContract.js?_v=${vs}"></script>
</body>
</html>

