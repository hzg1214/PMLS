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

        .layui-form-item{
            padding-bottom: 0px!important;
            margin-bottom:0px!important;
        }

        .lable-form-right {
            width: 400px !important;
            text-align: left;
        }

        .myForm .layui-form-label {
            width: 150px;
        }

        .myForm .layui-inline {
            width: 420px;
        }

        .w250 {
            width: 250px !important;
        }

    </style>
</head>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">

            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">客户详情</div>
                </div>
                <div class="layui-col-xs6 blockBtn">
                    <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>
                </div>
            </div>

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: -20px;">
                <legend>基本信息</legend>
            </fieldset>

            <div class="layui-form myForm">
                <div class="layui-form-item ">
                    <label class="layui-form-label"> 客户：</label>
                    <label class="layui-form-label lable-form-right ">${customerNm} &nbsp; ${customerTel}</label>
                    <input type="hidden" id="customerNm" value="${customerNm}">
                    <input type="hidden" id="customerTel" value="${customerTel}">
                </div>
                <div class="layui-form-item ">
                    <label class="layui-form-label">客户有效性：</label>
                    <label class="layui-form-label lable-form-right ">${vaild}</label>
                </div>
                <div class="layui-form-item ">
                    <label class="layui-form-label">报备套数：</label>
                    <label class="layui-form-label lable-form-right ">${registerCnt}</label>
                </div>
                <div class="layui-form-item ">
                    <label class="layui-form-label">成交套数：</label>
                    <label class="layui-form-label lable-form-right ">${succSaleCnt}</label>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-card">
        <div class="layui-card-body">

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>订单</legend>
            </fieldset>

            <div class="layui-form">
                <div class="layui-row">
                    <table id="contentTable" lay-size="sm" lay-filter="content"></table>
                </div>
            </div>
        </div>
    </div>

    <script src="${ctx}/meta/pmls/js/scene/sceneTrade/customDetail.js?v=${vs}"></script>
</body>
