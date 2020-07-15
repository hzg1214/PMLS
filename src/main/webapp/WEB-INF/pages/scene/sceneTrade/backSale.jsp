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

        .w250 {
            width: 250px !important;
            padding: 0px
        }
    </style>


</head>
<body>
<div class="layui-card">
    <div class="layui-card-body">
        <div class="layui-row blockBody">
            <div class="layui-col-xs6">
                <div class="blockTitle">退房</div>
            </div>
            <div class="layui-col-xs6 blockBtn">
                <button type="button" class="layui-btn layui-btn-primary" data-type="goback">返回</button>
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: -20px">
            <legend>基本信息</legend>
        </fieldset>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left"><b>项目编号：${info.projectNo}</b></div>
            <div class="layui-col-xs10 lable-right">
                <b> &nbsp;&nbsp;&nbsp;楼盘名称：${info.estateNm}</b>
            </div>
            <input type="hidden" id="id" name="id" lay-verify="id" class="layui-input"
                   value="${info.id}">
            <input type="hidden" id="estateId" name="estateId" lay-verify="estateId" class="layui-input"
                   value="${info.estateId}">
            <input type="hidden" id="reportId" name="reportId" lay-verify="reportId" class="layui-input"
                   value="${info.reportId}">
            <input type="hidden" id="switchDate" value="${switchDate}"/>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">订单编号：</div>
            <div class="layui-col-xs4 lable-right">${info.reportId}</div>
            <div class="layui-col-xs2 lable-left">经纪公司：</div>
            <div class="layui-col-xs4 lable-right">${info.companyNm}</div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">客户姓名：</div>
            <div class="layui-col-xs4 lable-right">${info.customerNm}</div>
            <div class="layui-col-xs2 lable-left">手机号码：</div>
            <div class="layui-col-xs4 lable-right">${info.customerTel}</div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">客户姓名：</div>
            <div class="layui-col-xs4 lable-right">${info.customerNmTwo}</div>
            <div class="layui-col-xs2 lable-left">手机号码：</div>
            <div class="layui-col-xs4 lable-right">${info.customerTelTwo}</div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">是否包销房源：</div>
            <div class="layui-col-xs4 lable-right">
                <c:if test="${info.isWrap eq 1}">
                    是
                </c:if>
                <c:if test="${info.isWrap ne 1}">
                    否
                </c:if>
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">物业类型：</div>
            <div class="layui-col-xs4 lable-right">${info.wyTypName}</div>
            <div class="layui-col-xs2 lable-left">楼室号：</div>
            <div class="layui-col-xs4 lable-right">${info.buildingNo}</div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">大定面积(㎡)：</div>
            <div class="layui-col-xs4 lable-right">${info.roughArea}</div>
            <div class="layui-col-xs2 lable-left">大定总价(元)：</div>
            <div class="layui-col-xs4 lable-right"><fmt:formatNumber value="${info.roughAmount}" pattern="#,###.00"/></div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">大定日期：</div>
            <div class="layui-col-xs4 lable-right">${sdk:ymd2(info.roughInputDate)}</div>
            <div class="layui-col-xs2 lable-left">成销面积(㎡)：</div>
            <div class="layui-col-xs4 lable-right">${info.area}</div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left">成销总价(元)：</div>
            <div class="layui-col-xs4 lable-right"><fmt:formatNumber value="${info.dealAmount}" pattern="#,###.00"/> </div>
            <div class="layui-col-xs2 lable-left">成销日期：</div>
            <div class="layui-col-xs4 lable-right">${sdk:ymd2(info.bizOperDate)}</div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left"><i>*</i>退房日期：</div>
            <div class="layui-col-xs4 lable-right w250">
                <input type="text" id="dealBackDate" 　name="dealBackDate" lay-verify="dealBackDate"
                       autocomplete="off" lay-filter="dealBackDate" placeholder="请选择" class="layui-input">
            </div>
        </div>
    </div>
</div>
<div class="layui-card">
    <div class="layui-card-body">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>附件</legend>
        </fieldset>
        <div class="layui-upload" id="uploadDealBackImg">
            <div class="layui-row">
                <div class="layui-col-xs2 lable-left"><i>*</i>退房资料：</div>
                <div class="layui-col-xs10 lable-right" style="padding: 0px">
                    <button type="button" class="layui-btn uploadImg">上传</button>
                    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                        <div class="layui-upload-list upload_img_list"></div>
                    </blockquote>
                </div>
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-col-xs2 lable-left"></div>
            <div class="layui-col-xs10 lable-right">
                <button class="layui-btn layui-btn-normal" data-type="save">确定</button>
                <button class="layui-btn layui-btn-primary" data-type="cancel">取消</button>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/meta/pmls/js/scene/sceneTrade/backSale.js?v=${vs}"></script>

</body>
</html>