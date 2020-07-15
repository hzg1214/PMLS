<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择合同</title>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>

    <style>
        body {
            padding: 0px;
        }

        .searchForm {
            padding-top: 5px;
        }

        .w100 {
            width: 100px !important;
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
            <form class="layui-form searchForm">
                <input type="hidden" id="accountProjectCode" name="accountProjectCode" class="layui-input"
                       value="${accountProjectCode}">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label w100">合同编号</label>
                        <div class="layui-input-inline">
                            <input type="text" id="frameOaNo" name="frameOaNo" class="layui-input" placeholder="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">供应商名称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="vendorName" name="vendorName" class="layui-input" placeholder="">
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button type="button" class="layui-btn" data-type="search">查询</button>
                    </div>
                </div>
            </form>
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>
<script src="${ctx}/meta/pmls/js/scene/sceneExpent/frmAgreementListPopup.js?v=${vs}"></script>
</body>
</html>