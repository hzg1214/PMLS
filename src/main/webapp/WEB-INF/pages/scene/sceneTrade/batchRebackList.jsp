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
            height:33px;
            line-height: 35px;
            padding: 0px 3px;
            margin-left: 24px;
        }
        .layui-table-link {
            cursor: pointer;
        }

    </style>
</head>
<script type="application/javascript">
    var batchRebackInfo = '${batchReback}';
</script>
<body>
<div class="layui-card">
    <div class="layui-card-body">

        <div class="layui-row blockBody">
            <div class="layui-col-xs6">
                <div class="blockTitle">批量退房</div>
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
                <b>项目编号：${batchReback.projectNo}&nbsp;&nbsp;&nbsp;楼盘名称：${batchReback.estateNm}</b>
            </div>
            <input type="hidden" id="estateId" value="${batchReback.estateId}">
            <input type="hidden" id="projectNo" value="${batchReback.projectNo}">
            <input type="hidden" id="batchId" value="${batchReback.batchId}">
            <input type="hidden" id="estateNm" value="${reqMap.estateNm}">
            <input type="hidden" id="switchDate" value="${switchDate}"/>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs12 lable-right pl-30">
                <b>退房订单列表</b>
                <button type="button"  class="layui-btn layui-btn-normal normalClass"  data-type="export"><span style="font-size: 10px;">导出批量退房数据</span></button>
                <button type="button" class="layui-btn layui-btn-normal normalClass" data-type="import" id="historyDataFile"><span style="font-size: 10px;">
                    导入批量退房数据
                </span></button>
            </div>
        </div>

        <div class="layui-row">
            <div class="layui-col-xs12 lable-right pl-30">
                <table id="reportTable" name="reportTable" lay-size="sm" lay-filter="reportTable"></table>
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
            <div class="layui-col-xs2 lable-left"><i>*</i>退房资料：</div>
            <div class="layui-col-xs10 lable-right" style="padding: 0px;">
                <button type="button" class="layui-btn uploadImg">上传</button>
                <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px">
                    <div class="layui-upload-list upload_img_list">
                        <%--<c:if test="${not empty batchReback.fileList}">--%>
                            <%--<c:set var="fileSize" value="0"/>--%>
                            <%--<c:forEach items="${batchReback.fileList}" var="list" varStatus="status">--%>
                                <%--<c:if test="${list.fileTypeId eq 1065}">--%>
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

<script src="${ctx}/meta/pmls/js/scene/sceneTrade/batchRebackList.js?v=${vs}"></script>
</body>
</html>