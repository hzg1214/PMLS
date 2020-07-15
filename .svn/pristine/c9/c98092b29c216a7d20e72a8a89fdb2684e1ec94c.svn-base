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
        }

        i {
            color: #FF0000;
        }

        .dialogForm .layui-form-label {
            width: 120px;
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
    <div class="layui-card-body">
        <div class="layui-form dialogForm">

            <input type="hidden" id="reportId" name="reportId" value="${yjReportInfo.reportId}"/>
            <input type="hidden" id="projectNo" name="projectNo" value="${yjReportInfo.projectNo}">

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">报备编号：</label>
                    <label class="layui-form-label w500">${yjReportInfo.reportId}</label>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">客户：</label>
                    <label class="layui-form-label w500">${yjReportInfo.customerNm}</label>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">客户电话：</label>
                    <label class="layui-form-label w500">${yjReportInfo.customerTel}</label>
                </div>
            </div>

            <c:forEach items="${yjReportInfo.companys}" var="list" varStatus="index">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">
                        <c:choose>
                            <c:when test="${index.count == 1}">
                                返佣对象一：
                            </c:when>
                            <c:when test="${index.count == 2}">
                                返佣对象二：
                            </c:when>
                            <c:otherwise>
                                返佣对象三：
                            </c:otherwise>
                        </c:choose>
                    </label>

                    <div class="layui-input-inline">
                        <input type="text" id="inputCompanyName${index.count}"  class="layui-input"
                               value="${list.companyName}"
                               data-companyNo="${list.companyNo}" placeholder="经纪公司">
                    </div>
                </div>
                <c:if test="${index.count != 1}">
                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-normal" data-type="selectCompanyInfo${index.count}">选择
                        </button>
                        <button class="layui-btn layui-btn-normal" data-type="clearCompanyInfo${index.count}">清除
                        </button>
                    </div>
                </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/meta/pmls/js/commission/lnkYjReportEdit.js?v=${vs}"></script>
</body>
</html>