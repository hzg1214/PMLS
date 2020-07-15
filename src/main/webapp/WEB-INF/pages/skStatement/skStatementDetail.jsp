<%--
  Created by IntelliJ IDEA.
  User: haidan
  Date: 2020/7/2
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>收入拆分详情</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .lable-left {
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            padding: 10px 0;
            width: 200px;
        }

        .lable-right {
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
            width: 300px;
        }
    </style>
</head>
<body>
<div>
    <div class="layui-card">
        <shiro:hasPermission name="/skStatement:cancel">
            <input type="hidden" id='toCancel' value="1"/>
        </shiro:hasPermission>
        <input type="hidden" id='userId' value="${userId}"/>
        <div class="layui-card-body">
            <div style="margin: 0 auto;">
                <div class="layui-row blockBody">
                    <div class="layui-col-xs6">
                        <div class="blockTitle">收入拆分查看</div>
                        <input type="hidden" id="skSerialNo" value="${info.skSerialNo}"/>
                    </div>
                    <div class="layui-col-xs6 blockBtn">
                        <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>
                    </div>
                </div>
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                    <legend>基本信息</legend>
                </fieldset>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">收入编号：</div>
                    <div class="layui-col-xs4 lable-right">${info.skSerialNo}</div>
                    <div class="layui-col-xs2 lable-left">OA编号：</div>
                    <div class="layui-col-xs4 lable-right">${info.oaNo}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">核算主体：</div>
                    <div class="layui-col-xs4 lable-right">${info.hsName}(${info.hsCode})</div>
                    <div class="layui-col-xs2 lable-left">考核主体：</div>
                    <div class="layui-col-xs4 lable-right">${info.khName}(${info.khCode})</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">项目：</div>
                    <div class="layui-col-xs4 lable-right">${info.projectName}(${info.projectNo})</div>
                    <div class="layui-col-xs2 lable-left">客户：</div>
                    <div class="layui-col-xs4 lable-right">${info.customerName}(${info.customerCode})</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">收款类型：</div>
                    <div class="layui-col-xs4 lable-right">${info.skKindName}</div>
                    <div class="layui-col-xs2 lable-left">入账日期：</div>
                    <div class="layui-col-xs4 lable-right">${info.recordDate}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">收款金额：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:mf2(info.skAmount_bef)}元</div>
                    <div class="layui-col-xs2 lable-left">已拆分金额：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:mf2(info.allocatedAmount_bef)}元</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">待拆分金额：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:mf2(info.stayAmount_bef)}元</div>
                </div>
            </div>
            <fieldset class="layui-elem-field layui-field-title">
                <legend>拆分记录</legend>
            </fieldset>
            <div style="margin-top: 1px;margin-bottom: 20px;margin-left: 50px;">
                <table id="allocateRecordTable" lay-size="sm" lay-filter="allocateRecordTable"></table>
            </div>
            <fieldset class="layui-elem-field layui-field-title">
                <legend>日志</legend>
            </fieldset>
            <div style="margin-top: 1px;margin-bottom: 20px;margin-left: 50px;">
                <table id="allocateLogTable" lay-size="sm" lay-filter="allocateLogTable"></table>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/skStatement/skStatementDetail.js?v=${vs}"></script>
</body>
</html>
