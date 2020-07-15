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
            font-size: 10px;
            font-style: normal;
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

        .w250 {
            width: 250px !important;
            padding: 0px
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

        .normalClass {
            height: 33px;
            line-height: 35px;
            padding: 0px 3px;
            margin-left: 24px;
        }
        .layui-table-link {
            cursor: pointer;
        }

    </style>
</head>
<body>
<script type="application/javascript">
    var reportInfo = ${info};
    console.log(${info});
</script>

<div class="layui-card">
    <div class="layui-card-body">

        <div class="layui-row blockBody">
            <div class="layui-col-xs6">
                <div class="blockTitle">批量成销</div>
            </div>
            <div class="layui-col-xs6 blockBtn">
                <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>
            </div>
        </div>

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: -20px;">
            <legend>基本信息</legend>
        </fieldset>

        <div class="layui-row">
            <div class="layui-col-xs12 lable-right pl-30">
                <b>项目编号：${info.projectNo}&nbsp;&nbsp;&nbsp;楼盘名称：${info.estateNm}</b>
            </div>
            <input type="hidden" id="estateId" value="${info.estateId}">
            <input type="hidden" id="projectNo" value="${info.projectNo}">
            <input type="hidden" id="batchId" value="${info.batchId}">
            <input type="hidden" id="switchDate" value="${switchDate}"/>
            <input type="hidden" id="taxRate" value="${citySetting.taxRate}">

            <div style=" display: none">
                <select id="cityAccountProject">
                    <option value="">请选择</option>
                    <c:forEach items="${cityAccountProject}" var="list" varStatus="status">
                        <option value="${list.accountProjectNo}" data-id="${list.accountProjectNo}"
                                data-name="${list.accountProject}">${list.accountProject}</option>
                    </c:forEach>
                </select>
            </div>

        </div>

        <div class="layui-row pl-30">
            <div class="layui-col-xs1 lable-right"><i>*</i>成销日期：</div>
            <div class="layui-col-xs2 lable-right " style="padding: 0px">

                <input type="text" id="saleDate" name="saleDate" lay-verify="required|saleDate"
                       value="${sdk:ymd2(info.saleDate)}" placeholder="请选择"
                       autocomplete="off" lay-filter="saleDate" class="layui-input">
            </div>
            <div class="layui-col-xs3">&nbsp;</div>
            <div class="layui-col-xs1 lable-right">结算日期：</div>
            <div class="layui-col-xs2 lable-right " style="padding: 0px">
                <input type="text" id="settlementDate" name="settlementDate"
                       lay-verify="required|settlementDate"
                       value="${sdk:ymd2(info.settlementDate)}" placeholder="请选择"
                       autocomplete="off" lay-filter="settlementDate" class="layui-input">
            </div>
            <div class="layui-col-xs3">&nbsp;</div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs12 lable-right pl-30">
                <b>成销房源列表</b>
                <button type="button" class="layui-btn layui-btn-normal normalClass" data-type="export"><span
                        style="font-size: 10px;">导出批量成销数据</span></button>
                <button type="button" class="layui-btn layui-btn-normal normalClass" data-type="import"
                        id="historyDataFile"><span style="font-size: 10px;">
                    导入批量成销数据
                </span></button>
            </div>
        </div>

        <div class="layui-row">
            <div class="layui-col-xs12 lable-right pl-30">
                <table id="reportTable" name="reportTable" lay-size="sm" lay-filter="reportTable"></table>
                (<i>批量成销操作不适用多返佣对象的房源</i>)
            </div>
        </div>

    </div>
</div>
<div class="layui-card">
    <div class="layui-card-body">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>附件</legend>
        </fieldset>

        <div class="layui-upload" id="uploadSaleImg">
            <div class="layui-col-xs2 lable-left"><i>*</i>成销确认书/佣金结算资料：</div>
            <div class="layui-col-xs10 lable-right" style="padding: 0px;">
                <button type="button" class="layui-btn uploadImg">上传</button>
                <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px">
                    <div class="layui-upload-list upload_img_list">
                        <%--<c:if test="${not empty info.fileList}">--%>
                            <%--<c:set var="fileSize" value="0"/>--%>
                            <%--<c:forEach items="${info.fileList}" var="list" varStatus="status">--%>
                                <%--<c:if test="${list.fileTypeId eq 1025}">--%>
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

<script src="${ctx}/meta/pmls/js/scene/sceneTrade/batchSuccessSale.js?v=${vs}"></script>
</body>
</html>