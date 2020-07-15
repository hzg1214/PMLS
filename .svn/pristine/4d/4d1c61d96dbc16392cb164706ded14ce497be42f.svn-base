<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择公司名称</title>
    <%@include file="../common/common.jsp" %>

    <style>
        body{padding: 0px; /*overflow-y: scroll;*/}
        .searchForm{
            padding-top: 5px;
        }
    </style>
</head>
<script type="application/javascript">
//     var estatePosition = '${estatePosition}';
</script>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form searchForm">
                <input type="hidden" id="companyType" value="${reqMap.companyType}"/>
                <input type="hidden" id="parentCompanyNo" value="${reqMap.parentCompanyNo}"/>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">公司编号</label>
                        <div class="layui-input-inline">
                            <input type="text" id="companyNo"  autocomplete="off" name="companyNo" class="layui-input" placeholder="公司编号">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">公司名称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="companyName"  autocomplete="off" name="companyName" class="layui-input" placeholder="公司名称">
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button type="button" class="layui-btn" data-type="reload">查询</button>
                    </div>
                </div>
            </form>
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>

</div>

</body>
</html>

<script type="text/javascript" src="${ctx}/meta/pmls/js/borrowMoneyFrameContract/selectCompanyPage.js?v=${vs}"></script>
