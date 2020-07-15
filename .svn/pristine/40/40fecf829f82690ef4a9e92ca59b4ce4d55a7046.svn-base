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
            width: 390px !important;
            text-align: left;
        }

        .myForm .layui-inline {
            width: 420px;
        }

        .w250 {
            width: 250px !important;
        }

        .lable-right {
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
        }

        .lable-right-line {
            width: 390px !important;
            text-align: left;
            border-bottom: #adadad 1px solid;
        }

    </style>


</head>
<body>

<div class="layui-card">
    <div class="layui-card-body">
        <div class="layui-form myForm">


            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">新增订单</div>
                </div>
                <div class="layui-col-xs6 blockBtn">
                    <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>
                </div>
            </div>

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                <legend>基本信息</legend>
            </fieldset>

            <div class="layui-form myForm">
                <div class="layui-form-item">
                    <label class="layui-form-label"><b>项目编号：${info.projectNo}</b></label>
                    <label class="layui-form-label lable-form-right"><b>楼盘名称：${info.estateNm}</b></label>
                    <input type="hidden" id="estateId" name="estateId" lay-verify="estateId" class="layui-input"
                           value="${info.estateId}">
                    <input type="hidden" id="estateNm" name="estateNm" lay-verify="estateNm" class="layui-input"
                           value="${info.estateNm}">
                    <input type="hidden" id="switchDate" value="${switchDate}"/>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><i>*</i>公司</label>
                    <div class="layui-inline">
                        <input readonly disabled type="text" id="companyId" name="companyId"
                               lay-verify="required|companyId" data-companyId="" data-companyNo="" data-branchId=""
                               value="" autocomplete="off" placeholder="请选择" class="layui-input">
                    </div>
                    <div class="layui-inline" style="width: 100px!important;">
                        <button class="layui-btn layui-btn-normal" data-type="openDialogCompany">选择</button>
                    </div>
                </div>

                <div class="layui-form-item" id="divInputStore" style="display: none">
                    <label class="layui-form-label">门店</label>
                    <div class="layui-inline">
                        <input type="text" id="inputStoreId" name="inputStoreId" lay-verify="inputStoreId"
                               lay-filter="inputStoreId" value="" maxlength="50" autocomplete="off" value=""
                               class="layui-input" placeholder="请输入">
                    </div>
                </div>
                <div class="layui-form-item" id="divddlStore">
                    <label class="layui-form-label"><i>*</i>门店</label>
                    <div class="layui-inline">
                        <select id="ddlStoreId" name="ddlStoreId" lay-verify="ddlStoreId" lay-filter="ddlStoreId">
                            <option value="" data-storeNo="" data-storeName="">请选择</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><i>*</i>业绩归属人</label>
                    <%--<label class="layui-form-label lable-right-line" id="contactIdLabel">&nbsp;</label>--%>
                    <div class="layui-inline">
                        <input type="hidden" id="contactId" name="contactId" value="">
                        <input readonly disabled type="text" id="contactNm" name="contactNm" lay-verify="contactNm"
                               lay-filter="contactNm" maxlength="50" autocomplete="off" value="" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label"><i>*</i>业绩归属中心</label>
                    <div class="layui-inline">
                        <%--<label class="layui-form-label lable-right-line"id="centerGroupIdLabel">${info.centerName}</label>--%>
                            <input type="hidden" id="centerGroupId" name="centerGroupId" value="">
                            <input readonly disabled type="text" id="centerGroupName" name="centerGroupName"
                                   value="" lay-verify="centerGroupName" lay-filter="centerGroupName"
                                   maxlength="50" autocomplete="off" class="layui-input">


                            <input type="hidden" id="estateCenterId" name="estateCenterId" value="${info.centerId}">
                    </div>
                </div>

                <%--                <div class="layui-form-item">
                                    <label class="layui-form-label"><i>*</i>业绩归属人</label>
                                    <div class="layui-inline">
                                        <input readonly type="text" id="contactId" name="contactId" lay-verify="required|contactId"
                                               lay-filter="contactId" data-id="" value="" autocomplete="off"
                                               placeholder="请选择业绩归属人" class="layui-input">
                                    </div>
                                    <div class="layui-inline" style="width: 100px!important;">
                                        <button class="layui-btn layui-btn-normal" data-type="openDialogContact">选择</button>
                                    </div>

                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label"><i>*</i>业绩归属中心</label>
                                    <div class="layui-inline w250">
                                        <select id="centerGroupId" name="centerGroupId" lay-verify="centerGroupId"
                                                lay-filter="centerGroupId">
                                            <option value="">请选择业绩归属中心</option>
                                        </select>
                                    </div>
                                </div>--%>


                <div class="layui-form-item">
                    <label class="layui-form-label">报备经纪人</label>
                    <div class="layui-inline">
                        <input type="text" id="reportAgent" name="reportAgent" lay-verify="reportAgent"
                               lay-filter="reportAgent" maxlength="50" autocomplete="off" value="" class="layui-input"
                               placeholder="请输入">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">经纪人手机号</label>
                    <div class="layui-inline">
                        <input type="text" id="reportAgentTel" name="reportAgentTel"
                               lay-verify="required|phone|reportAgentTel" lay-filter="reportAgentTel"
                               maxlength="11" autocomplete="off" value=""
                               class="layui-input" placeholder="请输入11位电话号码">

                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><i>*</i>客户姓名</label>
                    <div class="layui-inline">
                        <input type="text" id="customerName" name="customerName" lay-verify="required|customerName"
                               maxlength="50" autocomplete="off" value="" lay-filter="customerName" class="layui-input"
                               placeholder="请输入">

                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><i>*</i>客户手机号</label>
                    <div class="layui-inline">
                        <input type="text" id="customerPhone" name="customerPhone" lay-verify="required|customerPhone"
                               lay-filter="customerPhone" autocomplete="off" maxlength="11" value=""
                               class="layui-input" placeholder="请输入11位电话号码">
                    </div>

                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">客户姓名</label>
                    <div class="layui-inline">
                        <input type="text" id="customerNmTwo" name="customerNmTwo" lay-verify="customerNmTwo"
                               lay-filter="customerNmTwo" autocomplete="off" maxlength="50" value=""
                               class="layui-input" placeholder="请输入">
                    </div>

                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">客户手机号</label>
                    <div class="layui-inline">
                        <input type="text" id="customerTelTwo" name="customerTelTwo" lay-verify="customerTelTwo"
                               lay-filter="customerTelTwo" autocomplete="off" maxlength="11" value=""
                               class="layui-input" placeholder="请输入11位电话号码">
                    </div>

                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><i>*</i>报备日期</label>
                    <div class="layui-inline w250">
                        <input type="text" id="reportDate" name="reportDate" lay-verify="required|reportDate"
                               autocomplete="off" lay-filter="reportDate" class="layui-input" placeholder="请选择">
                    </div>
                </div>
                <%--<div class="layui-form-item">--%>
                <%--<label class="layui-form-label">客户人数</label>--%>
                <%--<div class="layui-inline w250">--%>
                <%--<select id="customerNum" name="customerNum" lay-filter="customerNum">--%>
                <%--<option value="">请选择客户人数</option>--%>
                <%--<option value="16101">1人</option>--%>
                <%--<option value="16102">2人</option>--%>
                <%--<option value="16103">3人</option>--%>
                <%--<option value="16104">3人以上</option>--%>
                <%--</select>--%>

                <%--</div>--%>
                <%--</div>--%>


                <div class="operationPageToolbar">
                    <button class="layui-btn layui-btn-normal" data-type="save">保存</button>
                    <button class="layui-btn layui-btn-primary" data-type="cancel">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/meta/pmls/js/scene/sceneTrade/reportAdd.js?v=${vs}"></script>
</body>

</html>