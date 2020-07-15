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
    <style>
        i {
            color: #FF0000;
        }

        .lable-left {
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            padding: 10px 0;
        }

        .lable-right {
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
        }


    </style>
</head>
<body>


<div class="layui-card">
    <div class="layui-card-body">

        <div class="layui-row blockBody">
            <div class="layui-col-xs6">
                <div class="blockTitle">返佣对象记录查看</div>
            </div>
            <div class="layui-col-xs6 blockBtn">
                <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: -20px">
            <legend>基本信息</legend>
        </fieldset>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">订单编号：</div>
            <div class="layui-col-xs4 lable-right">${yjReportInfo.reportId}</div>

            <div class="layui-col-xs2 lable-left">楼盘名称：</div>
            <div class="layui-col-xs4 lable-right">${yjReportInfo.estateNm}</div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">经纪公司：</div>
            <div class="layui-col-xs4 lable-right">${yjReportInfo.companyNm}</div>
            <div class="layui-col-xs2 lable-left">门店地址：</div>
            <div class="layui-col-xs4 lable-right">${yjReportInfo.addressDetail}</div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">客户姓名：</div>
            <div class="layui-col-xs4 lable-right">${yjReportInfo.customerNm}</div>
            <div class="layui-col-xs2 lable-left">手机号码：</div>
            <div class="layui-col-xs4 lable-right">${yjReportInfo.customerTel}</div>
        </div>

        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">最新进度：</div>
            <div class="layui-col-xs6 lable-right">${yjReportInfo.dicValue}</div>
        </div>
        <c:forEach items="${yjReportInfo.companys}" var="list" varStatus="index">

            <div class="layui-row">
                <c:if test="${index.count == 1}">
                    <div class="layui-col-xs2 lable-left">返佣对象：</div>
                </c:if>
                <c:if test="${index.count != 1}">
                    <div class="layui-col-xs2 lable-left"></div>
                </c:if>
                <c:if test="${list.companyNo != null}">
                    <div class="layui-col-xs6 lable-right">${list.companyName}&nbsp;&nbsp;(${list.companyNo})</div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
<div class="layui-card">
    <div class="layui-card-body">

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>维护记录</legend>
        </fieldset>

        <div class="layui-form">

            <script>
                var companyMatins = ${yjReportInfo.companyMatins};
                console.log(${yjReportInfo.companyMatins});
            </script>

            <div class="layui-row">
                <table id="contentTable" lay-size="sm" lay-filter="content"></table>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/meta/pmls/js/commission/lnkYjReportDetail.js?v=${vs}"></script>
</body>
</html>
