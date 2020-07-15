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

        .lable-form-right {
            width: 400px !important;
            text-align: left;
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


<div class="layui-card">
    <div class="layui-card-body">
        <div class="layui-row blockBody">
            <div class="layui-col-xs6">
                <div class="blockTitle">新增带看</div>
            </div>
            <div class="layui-col-xs6 blockBtn">
                <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: -20px">
            <legend>基本信息</legend>
        </fieldset>
        <div class="layui-form myForm" style="margin-top:20px;">
            <div class="layui-form-item">
                <label class="layui-form-label"><b>项目编号：${info.projectNo}</b></label>
                <label class="layui-form-label lable-form-right"><b>楼盘名称：${info.estateNm}</b></label>
                <input type="hidden" id="id" name="id" lay-verify="id" class="layui-input"
                       value="${info.id}">
                <input type="hidden" id="estateId" name="estateId" lay-verify="estateId" class="layui-input"
                       value="${info.estateId}">
                <input type="hidden" id="reportId" name="reportId" lay-verify="reportId" class="layui-input"
                       value="${info.reportId}">
                <input type="hidden" id="switchDate" value="${switchDate}"/>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">订单编号：</label>
                <label class="layui-form-label lable-form-right">${info.reportId}</label>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">公司：</label>
                <label class="layui-form-label lable-form-right">${info.companyNm}</label>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><i>*</i>客户姓名</label>
                <div class="layui-inline">
                    <input type="text" id="customerNm" name="customerNm" lay-verify="required|customerNm"
                           lay-filter="customerNm" maxlength="50" autocomplete="off" value="${info.customerNm}"
                           class="layui-input" placeholder="请输入">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><i>*</i>客户手机号</label>
                <div class="layui-inline">
                    <input type="text" id="customerTel" name="customerTel" lay-verify="required|customerTel"
                           lay-filter="customerTel" maxlength="11" autocomplete="off" value="${info.customerTel}"
                           class="layui-input" placeholder="请输入11位电话号码">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">客户姓名</label>
                <div class="layui-inline">
                    <input type="text" id="customerNmTwo" name="customerNmTwo" lay-verify="customerNmTwo"
                           lay-filter="customerNmTwo" maxlength="50" autocomplete="off" value="${info.customerNmTwo}"
                           class="layui-input" placeholder="请输入">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">客户手机号</label>
                <div class="layui-inline">
                    <input type="text" id="customerTelTwo" name="customerTelTwo" lay-verify="customerTelTwo"
                           lay-filter="customerTelTwo" maxlength="11" autocomplete="off" value="${info.customerTelTwo}"
                           class="layui-input" placeholder="请输入11位电话号码">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><i>*</i>带看日期</label>
                <div class="layui-inline w250">
                    <input type="text" id="seeDate" name="seeDate" lay-verify="required|seeDate"
                           autocomplete="off" lay-filter="seeDate" class="layui-input" placeholder="请选择">
                </div>
            </div>

        </div>
    </div>
</div>
<div class="layui-card">
    <div class="layui-card-body">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>附件</legend>
        </fieldset>

        <div class="layui-form enclosureForm">
            <div class="layui-form-item">
                <label class="layui-form-label"><i>*</i>带看单</label>
                <div class="layui-input-block">
                    <div class="layui-upload" id="uploadWatchImg">
                        <button type="button" class="layui-btn uploadImg">上传</button>
                        <i>注：带看单必须包含中介名称、客户名称、客户电话、驻场签字和客户签字信息。</i>
                        <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                            <div class="layui-upload-list upload_img_list"></div>
                        </blockquote>
                    </div>
                </div>
            </div>
        </div>
        <div class="operationPageToolbar">
            <button class="layui-btn layui-btn-normal" data-type="save">确定</button>
            <button class="layui-btn layui-btn-primary" data-type="cancel">取消</button>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/meta/pmls/js/scene/sceneTrade/watchAdd.js?v=${vs}"></script>
</body>
</html>
