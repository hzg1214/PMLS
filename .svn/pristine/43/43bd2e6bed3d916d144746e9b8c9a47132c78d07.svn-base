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

        .pl-30 {
            padding-left: 30px
        }

        .layui-form-label {
            width: 120px !important;
        }

        .w300 {
            width: 300px !important;
            text-align: left;
        }

        .w500 {
            width: 500px !important;
            text-align: left;
        }

        .renderInput {
            width: 100%;
            height: 90%
        }

        .mc {
            text-align: center;
        }

        .ml {
            text-align: left;
        }

        .mr {
            text-align: right;
        }

        .layui-table-link {
            cursor: pointer;
        }

    </style>
</head>
<body>

<div class="layui-card">
    <div class="layui-card-body">
        <div class="layui-row blockBody">
            <div class="layui-col-xs6">
                <div class="blockTitle">批量请款</div>
            </div>
            <div class="layui-col-xs6 blockBtn">
                <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: -20px">
            <legend>基本信息</legend>
        </fieldset>

        <div class="layui-form">
            <div class="layui-form-item layui-row">
                <div class="layui-col-xs8 " style="padding-left: 80px">
                    <label><b>项目编号：${estate.projectNo} &nbsp;&nbsp;&nbsp;楼盘名称：${estate.estateNm}</b> </label>
                    <input type="hidden" id="estateId" name="estateId" value="${estate.estateId}">
                    <input type="hidden" id="estateNm" name="estateNm" value="${estate.estateNm}">
                    <input type="hidden" id="projectNo" name="projectNo" value="${estate.projectNo}">
                    <div style=" display: none">
                        <select id="oaAccountProject">
                            <option value="">请选择</option>
                            <c:forEach items="${oaAccountProjectList}" var="list" varStatus="status">
                                <option value="${list.lnkaccountProjectCode}"
                                        data-name="${list.lnkAccountProject}">${list.lnkaccountProjectCode}_${list.lnkAccountProject}</option>
                            </c:forEach>
                        </select>
                    </div>

                </div>
            </div>
            <div class="layui-form-item layui-row">
                <input type="hidden" id="id" name="id">
                <input type="hidden" id="estateCityNo" name="estateCityNo">
                <input type="hidden" id="cityNo" name="cityNo">

                <input type="hidden" id="isSpecialProject" name="isSpecialProject">

                <div class="layui-col-xs6">
                    <label class="layui-form-label"><i>*</i>入账日期</label>
                    <div class="layui-input-inline">
                        <input type="text" id="recordTime" name="recordTime" lay-verify="required|recordTime"
                               autocomplete="off" lay-filter="recordTime" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-xs6">
                    <label class="layui-form-label"><i>*</i>预计付款日期</label>
                    <div class="layui-input-inline">
                        <input type="text" id="predictPayTime" name="predictPayTime"
                               autocomplete="off" lay-verify="required|predictPayTime" lay-filter="predictPayTime"
                               class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item layui-row">
                <div class="layui-col-xs6">
                    <label class="layui-form-label"><i>*</i>付款方式</label>
                    <div class="layui-input-inline">
                        <select id="payType" name="payType" lay-verify="payType" lay-filter="payType">
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="companyInfoList"></div>

<div class="layui-card">
    <div class="layui-card-body">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>附件</legend>
        </fieldset>

        <div class="layui-upload" id="uploadImg">
            <div class="layui-col-xs2 lable-left"><i>*</i>成销确认书/佣金结算资料：</div>
            <div class="layui-col-xs10 lable-right" style="padding: 0px">
                <button type="button" class="layui-btn uploadImg">上传</button>
                <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px; ">
                    <div class="layui-upload-list upload_img_list">
                        <%--<c:if test="${not empty info.fileList}">--%>
                            <%--<c:set var="fileSize" value="0"/>--%>
                            <%--<c:forEach items="${info.fileList}" var="list" varStatus="status">--%>
                                <%--<c:if test="${list.fileTypeId eq 1032}">--%>
                                    <%--<dd class="item_img" id="${list.fileRecordMainId}"--%>
                                        <%--data-id="${list.fileRecordMainId}">--%>
                                        <%--<div class="operate">--%>
                                            <%--<i onclick="UPLOAD_IMG_DEL('${list.fileRecordMainId}')"--%>
                                               <%--class="close layui-icon"></i></div>--%>
                                        <%--<img src="${list.fileAbbrUrl}" class="img">--%>
                                        <%--<input type="hidden" name="dzd_img[]"--%>
                                               <%--value="${list.fileAbbrUrl}">--%>
                                    <%--</dd>--%>
                                <%--</c:if>--%>
                            <%--</c:forEach>--%>
                        <%--</c:if>--%>
                    </div>
                </blockquote>
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left"></div>
            <div class="layui-col-xs10 lable-right">
                <button class="layui-btn layui-btn-normal" data-type="submit">提交</button>
                <button class="layui-btn layui-btn-normal" data-type="save">保存</button>
                <button class="layui-btn layui-btn-primary" data-type="cancel">取消</button>
            </div>
        </div>
    </div>
</div>
</div>
</div>
<script src="${ctx}/meta/pmls/js/scene/sceneExpent/batchExpent.js?v=${vs}"></script>
</body>
