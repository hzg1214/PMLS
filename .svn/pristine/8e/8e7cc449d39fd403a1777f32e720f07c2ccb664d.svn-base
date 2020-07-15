<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
    <script type="text/javascript" src="${ctx}/meta/js/keFuOrder/keFuOrderDetail.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
</head>
<script>
    function htmlEscape(html) {
        var escapeMap = {
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#x27;',
            '`': '&#x60;'
        };
        var source = '(?:' + Object.keys(escapeMap).join('|') + ')',
            testRegexp = new RegExp(source),
            replaceRegexp = new RegExp(source, 'g'),
            string = html == null ? '' : '' + html;
        return testRegexp.test(string) ? string.replace(replaceRegexp, function (match) {
            return escapeMap[match];
        }) : string;
    }
</script>
<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>
<div class="container theme-hipage ng-scope">
    <div class="crumbs">
        <ul style="margin-right:150px;">
            <li><a href="#" class="a_hover">客服督导</a></li>
            <li><a href="#" class="a_hover">>工单管理列表</a></li>
            <li><a href="#" class="a_hover">>工单查看</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="page-content">
            <h4 class="border-bottom pdb10">
                <strong>工单查看</strong>
                <a href="${ctx}/keFuOrder?searchParam=1" class="btn btn-primary">返回</a>
                <!-- 经办人才能对 (问题状态未处理、处理中) 的工单 有 回复 权限 -->
                <!-- 处于未处理、处理中的 -->
                <c:if test="${(keFuOrder.dealStatus ne 24203)}">
                    <c:choose>
                        <c:when test="${keFuOrder.userCode eq userInfo.userCode}">
                                <a class="btn btn-primary" id="reply"
                                   onclick="javascript:gdReply('${keFuOrder.id}','${keFuOrder.dealStatus}','${keFuOrder.userCode}','${keFuOrder.userCreate}');"
                                   style="margin-right:10px;">回复</a>
                            <a class="btn btn-primary" id="reAlert"
                               onclick="javascript:reAlert('${keFuOrder.id}');"
                               style="margin-right:10px;">提醒经办人</a>
                        </c:when>
                        <c:otherwise>
                            <shiro:hasPermission name="/KEFUORDER:GD_REPLY">
                                    <a class="btn btn-primary" id="reply"
                                       onclick="javascript:gdReply('${keFuOrder.id}','${keFuOrder.dealStatus}','${keFuOrder.userCode}','${keFuOrder.userCreate}');"
                                       style="margin-right:10px;">回复</a>
                                    <a class="btn btn-primary" id="reAlert"
                                       onclick="javascript:reAlert('${keFuOrder.id}');"
                                       style="margin-right:10px;">提醒经办人</a>
                            </shiro:hasPermission>
                        </c:otherwise>
                    </c:choose>
                </c:if>



                 <shiro:hasPermission name="/KEFUORDER:GD_CHECK">
                <!-- 回复处理完成、核验未完成 -->
                    <c:if test="${(keFuOrder.dealStatus eq 24203) && (keFuOrder.checkStatus ne 24302)}">
                        <a class="btn btn-primary"
                           onclick="javascript:gdCheck('${keFuOrder.id}','${keFuOrder.dealStatus}','${keFuOrder.checkStatus}','${keFuOrder.userCode}','${keFuOrder.userCreate}');"
                           style="margin-right:10px;">核验</a>
                    </c:if>
                </shiro:hasPermission>
            </h4>
            <div style="margin-bottom:20px;">
                <p>
                    <strong style="font-size:16px;">基本信息</strong>
                </p>
                <table class="table-sammary">
                    <col style="width:130px;">
                    <col style="width:auto;">
                    <col style="width:130px;">
                    <col style="width:auto;">
                    <tr>
                        <td class="talabel">工单编号：</td>
                        <td>${keFuOrder.orderNo}</td>
                    </tr>
                    <tr>
                        <td class="talabel">归属城市：</td>
                        <td>${keFuOrder.cityName}</td>
                        <td class="talabel">门店城市：</td>
                        <td>${keFuOrder.storeCityName}</td>
                    </tr>
                    <tr>
                        <td class="talabel">经纪公司：</td>
                        <td>${keFuOrder.companyName}</td>
                    </tr>
                    <tr>
                        <td class="talabel">门店：</td>
                        <td>${keFuOrder.storeName}</td>
                        <td class="talabel">门店地址：</td>
                        <td>${keFuOrder.storeAddress}</td>
                    </tr>
                    <tr>
                        <td class="talabel">客户姓名：</td>
                        <td>${keFuOrder.customerName}</td>
                        <td class="talabel">客户电话：</td>
                        <td>${keFuOrder.customerTel}</td>
                    </tr>
                    <tr>
                        <td class="talabel">咨询产品名称：</td>
                        <td>${keFuOrder.consultProductNm}</td>
                    </tr>
                    <tr>
                        <td class="talabel">问题一级分类：</td>
                        <td>${keFuOrder.categoryOneNm}</td>
                        <td class="talabel">问题二级分类：</td>
                        <td>${keFuOrder.categoryTwoNm}</td>
                    </tr>
                    <tr>
                        <td class="talabel">问题概要：</td>
                        <td>${keFuOrder.questionTopic}</td>
                    </tr>
                    <tr>
                        <td class="talabel">问题描述：</td>
                        <td>${keFuOrder.questionDesc}</td>
                    </tr>
                    <tr>
                        <td class="talabel">工单优先级：</td>
                        <td>${keFuOrder.questionLevelNm}</td>
                        <td class="talabel">经办人：</td>
                        <td>${keFuOrder.userName}</td>
                    </tr>
                    <tr>
                        <td class="talabel">创建人：</td>
                        <td>${keFuOrder.createName}</td>
                        <td class="talabel">创建日期：</td>
                        <td>${keFuOrder.dateCreate}</td>
                    </tr>
                    <tr>
                        <td class="talabel">问题状态：</td>
                        <td>${keFuOrder.dealStatusStr}</td>
                        <td class="talabel">核验状态：</td>
                        <td>${keFuOrder.checkStatusStr}</td>
                    </tr>
                    <c:if test="${(keFuOrder.checkStatus eq 24302)}">

                    <tr>
                        <td class="talabel">核验人：</td>
                        <td>${keFuOrder.verifyUserName}</td>
                        <td class="talabel">核验日期：</td>
                        <td>${keFuOrder.checkDate}</td>
                    </tr>
                    </c:if>
            </table>
            <c:if test="${not empty keFuOrder.orderFileList }">
                <div class="page-content">
                    <h4>
                        <strong>附件</strong>
                    </h4>
                </div>
            </c:if>
            <table class="table-sammary" name="Viewerbox">
                <col style="width:145px;">
                <tr>
                    <td colspan="2">
                        <div class="pdl10 pdb0">
                            <div class="thumb-xs-box">
                                <c:if test="${not empty keFuOrder.orderFileList }">
                                    <c:set var="fileSize" value="0"/>
                                    <c:forEach items="${keFuOrder.orderFileList}" var="list" varStatus="status">

                                        <c:set var="fileSize" value="${fileSize + 1}"/>
                                        <div class="thumb-xs-list item-photo-list">
                                            <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                <span class="thumb-img">
                                                    <span class="thumb-img-container">
                                                        <span class="thumb-img-content">
                                                            <img alt="附件" data-original="${list.url50}"
                                                                 src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                        </span>
                                                    </span>
                                                </span>
                                            </a>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
            <div class="page-content">
                <h4>
                    <strong>工单回复</strong>
                </h4>
            </div>
            <table class="table-sammary" name="Viewerbox">
                <c:if test="${not empty keFuOrder.keFuOrderCkDtos }">
                <c:forEach items="${keFuOrder.keFuOrderCkDtos}" var="listDto" varStatus="status">
                    <tr >
                        <td>
                            <div class="pdl10 pdb0" style="border-bottom:1px dashed #e1e1e1">
                                <div class="thumb-xs-box" id="fileBusinessBox">
                                    <c:set var="fileSize" value="0"/>
                                    <table border="0" cellpadding="0" >
                                        <colgroup>
                                            <col style="width:120px">
                                            <col style="width:auto">
                                        </colgroup>
                                        <tr >
                                            <td><strong style="font-size:14px;">${listDto.ckUserName}(${listDto.userCode})</strong></td>
                                            <td><strong style="font-size:14px;">${listDto.ckDate}</strong></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="color:#888888">
                                                    <script>
                                                        document.write(htmlEscape("${listDto.ckDesc}"));
                                                    </script>
                                            </td>
                                        </tr>
                                    </table>
                                    <c:forEach items="${listDto.orderCkFileList}" var="list" varStatus="status">
                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                    <div class="thumb-xs-list item-photo-list">
                                        <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="附件" data-original="${list.url50}"
                                                                         src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                </span>
                                                            </span>
                                                        </span>
                                        </a>
                                    </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </c:if>
            </table>
        </div>
        </div>
    </div>
</div>
</body>

</html>
