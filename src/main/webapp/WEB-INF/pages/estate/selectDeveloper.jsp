<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择开发商</title>
    <%@include file="../common/common.jsp" %>

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
                <input type="hidden" class="layui-input" id="partner"  name="partner" value="${partner}" placeholder="请输入" lay-verify="">
                <input type="hidden" class="layui-input" id="pageType"  name="pageType" value="${pageType}" placeholder="请输入" lay-verify="">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">
                            <c:choose>
                                <c:when test="${pageType=='developer'}">
                                    合作方编号
                                </c:when>
                                <c:otherwise>
                                    开发商编号
                                </c:otherwise>
                            </c:choose>
                        </label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="developerCode"  placeholder="请输入" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">
                            <c:choose>
                                <c:when test="${pageType=='developer'}">
                                    合作方名称
                                </c:when>
                                <c:otherwise>
                                    开发商名称
                                </c:otherwise>
                            </c:choose>
                        </label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="developerName" placeholder="请输入" lay-verify="">
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

<script type="text/javascript" src="${ctx}/meta/pmls/js/estate/selectDeveloper.js?_v=${vs}"></script>
