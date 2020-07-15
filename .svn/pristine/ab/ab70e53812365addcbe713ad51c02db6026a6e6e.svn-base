<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择开户信息</title>
    <%@include file="../common/common.jsp" %>

    <style>
        body{padding: 0px; /*overflow-y: scroll;*/}
        .searchForm{
            padding-top: 5px;
        }
    </style>
</head>
<script type="application/javascript">
    var companyNo='${companyNo}';
</script>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form searchForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">开户名</label>
                        <div class="layui-input-inline">
                            <input type="text" id="accountNm" name="accountNm" autocomplete="off" class="layui-input" placeholder="开户名">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">开户行</label>
                        <div class="layui-input-inline">
                            <input type="text" id="bankAccountNm" name="bankAccountNm" autocomplete="off" class="layui-input" placeholder="开户行">
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

<script type="text/javascript" src="${ctx}/meta/pmls/js/borrowMoneyFrameContract/selectBankInfoPage.js?v=${vs}"></script>
