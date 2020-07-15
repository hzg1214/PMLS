<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <title>维护返佣对象</title>
    <style>
        body {
            padding: 0px;
            overflow: hidden;
        }

        i {
            color: #FF0000;
        }

        .dialogForm .layui-form-label {
            width: 150px;
        }

        .dialogForm .layui-input-inline {
            width: 320px;
        }

        .w500 {
            width: 500px !important;
            text-align: left;
        }
    </style>
</head>
<body>

<div class="layui-card">
    <div class="layui-card-body" style="height: 200px">
        <div class="layui-form dialogForm">

            <input type="hidden" id="ids" name="ids" value="${ids}">
            <input type="hidden" id="reportIds" name="reportIds" value="${reportIds}">
            <input type="hidden" id="projectNo" name="projectNo" value="${projectNo}">

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label w500">
                        <b>您已经选择<i style="font-size:18px;">${size}</i>条报备房源信息，请指定返佣对象，默认报备经纪公司无需指定。</b>
                    </label>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label"><i>*</i>返佣对象二：</label>
                    <div class="layui-input-inline">
                        <input type="text" readonly disabled id="inputCompanyName" name="inputCompanyName" class="layui-input"
                               data-companyId="" data-companyNo="" placeholder="经纪公司">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layui-btn-normal" data-type="selectCompanyInfo">选择</button>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">返佣对象三：</label>
                    <div class="layui-input-inline">
                        <input type="text" readonly disabled id="inputCompanyNameTwo" name="inputCompanyNameTwo"
                               class="layui-input" data-companyId="" data-companyNo="" placeholder="经纪公司">
                    </div>
                </div>
                <div class="layui-inline">
                    <button class="layui-btn layui-btn-normal" data-type="selectCompanyInfoTwo">选择</button>
                    <button class="layui-btn layui-btn-normal" data-type="clearCompanyInfoTwo">清除</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/meta/pmls/js/commission/lnkYjReportListPopup.js?v=${vs}"></script>
</body>
</html>
