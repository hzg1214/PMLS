<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/gpCsMemberContract/gpCsMemberContractList.js?_v=${vs}"></script>
</head>
<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>
<div class="container theme-hipage ng-scope">
    <div class="crumbs">
        <ul style="margin-right:150px;">
            <li><a href="#" class="a_hover">合同管理</a></li>
            <li><a href="#" class="a_hover">>公盘初始会员合同列表</a></li>
            <li><a href="#" class="a_hover">>公盘初始会员合同详情</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="page-content">
            <input type="hidden" id="id" name="id" value="${csMember.id}"/>
            <input type="hidden" id="companyId" name="companyId" value="${csMember.companyId}"/>
            <h4 class="border-bottom pdb10">
                <strong>公盘初始会员合同详情</strong>
                <a href="${ctx}/gpCsMemberContract?searchParam=1" class="btn btn-primary">返回</a>
                <shiro:hasPermission name="/gpCsMemberContract:SUBMITTOOA">
                    <c:if test="${not empty oaOperator.operCode}">
                        <!-- 处于审核中、审核不同过的 -->
                        <c:if test="${(csMember.approveState eq 0 &&(empty csMember.submitOAStatus || csMember.submitOAStatus eq 21201 || csMember.submitOAStatus eq 21204)) || (csMember.approveState eq 3 && csMember.submitOAStatus ne 21202)}">
                            <a href="javascript:void(0);" onclick="submitgpCsMember('${csMember.id}');"
                               class="btn btn-primary" style="margin-right:10px;" id="submitToOA">提交审核</a>
                        </c:if>
                    </c:if>
                </shiro:hasPermission>
                <shiro:hasPermission name="/ct:OPERATE_STATUS">
                    <c:if test="${csMember.approveState eq 1}">
                        <a href="javascript:void(0)" onclick="operateChangeCt(${csMember.id});" id="operateChangeCt" class="btn btn-primary" style="margin-right:10px;">运营维护合同状态</a>
                    </c:if>
                </shiro:hasPermission>
            </h4>
            <div style="margin-bottom:20px;">
                <p>
                    <strong style="font-size:16px;">基本信息</strong>
                </p>
                <table class="table-sammary">
                    <col style="width:155px;">
                    <col style="width:auto;">
                    <col style="width:150px;">
                    <col style="width:auto;">
                    <tr>
                        <td class="talabel">初始会员申请编号：</td>
                        <td>${csMember.gpCsMemberContractNo}</td>
                        <td class="talabel">甲方公司：</td>
                        <td>${csMember.partyB}</td>
                    </tr>
                    <tr>
                        <td class="talabel">统一社会信用代码：</td>
                        <td>${csMember.registerId}</td>
                        <td class="talabel">法定代表/负责人：</td>
                        <td>${csMember.legalPerson}</td>
                    </tr>
                    <tr>
                        <td class="talabel">公司注册地址：</td>
                        <td colspan="3">${csMember.partyBCityName}${csMember.partyBDistrictName}${csMember.partyBAddress}</td>
                    </tr>
                    <tr>
                        <td class="talabel">我方全称：</td>
                        <td>${csMember.ourFullName}</td>
                        <td class="talabel">初始会员协议书编号：</td>
                        <td>${csMember.agreementNo}</td>
                    </tr>
                    <tr>
                        
                        <td class="talabel">初始会员费：</td>
                        <td>
                            <c:if test="${not empty csMember.csMemberAmount}">
                                ￥<fmt:formatNumber type="number" value="${csMember.csMemberAmount}" pattern="0.00" maxFractionDigits="2"/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">甲方授权代表：</td>
                        <td>${csMember.partyBNm}</td>
                        <td class="talabel">甲方联系方式：</td>
                        <td>${csMember.partyBTel}</td>
                    </tr>
                    <tr>
                        <td class="talabel">业绩归属人：</td>
                        <td>${csMember.exPerson}</td>
                        <td class="talabel">业绩归属中心：</td>
                        <td>${csMember.centerName}</td>
                    </tr>
                    <tr>
                        <td class="talabel">开户名：</td>
                        <td>${csMember.accountNm}</td>
                        <td class="talabel">开户省市：</td>
                        <td>${csMember.accountProvinceNm}${csMember.accountCityNm}</td>
                    </tr>
                    <tr>
                        <td class="talabel">开户行：</td>
                        <td>${csMember.bankAccountNm}</td>
                        <td class="talabel">银行账号：</td>
                        <td>${csMember.bankAccount}</td>
                    </tr>
                    <tr>
                        <td class="talabel" style="vertical-align: text-top;">合同备注：</td>
                        <td colspan="3">${csMember.remark}</td>
                    </tr>
                    <tr>
                        <td class="talabel" style="vertical-align: text-top;">公盘初始会员OA编号：</td>
                        <td colspan="3">${csMember.oaNo}</td>
                    </tr>
                    <tr>
                        <c:if test="${!(csMember.contractStatus eq 10403 && csMember.submitOAStatus eq 21201)}">
                            <td class="talabel">提交OA状态：</td>
                            <c:if test="${csMember.submitOAStatus eq null}">
                                <td>-</td>
                            </c:if>
                            <c:if test="${csMember.submitOAStatus eq 21202||csMember.submitOAStatus eq 21204}">
                                <td style="color:red;">${csMember.submitOAStatusNm}</td>
                            </c:if>
                            <c:if test="${csMember.submitOAStatus eq 21201||csMember.submitOAStatus eq 21203}">
                                <td>${csMember.submitOAStatusNm}</td>
                            </c:if>
                        </c:if>
                        <td class="talabel">创建时间：</td>
                        <td>${csMember.dateCreate}</td>
                    </tr>
                    <tr>
                        <td class="talabel">合同审核状态：</td>
                        <td>${csMember.approveStatusNm}</td>
                        <td class="talabel">审核通过时间：</td>
                        <td>${csMember.approvePassDate}</td>
                    </tr>
                </table>
                
            </div>
            <div class="page-content">
                <h4>
                    <strong>附件</strong>
                </h4>
            </div>
            <table class="table-sammary" name="Viewerbox">
                <col style="width:145px;">
                <col style="width:auto">
                <tr>
                    <td colspan="2">
                        <div class="pdl10 pdb0">
                                    <h4 class="thumb-title">
                                        营业执照
                                    </h4>
                                    <div class="thumb-xs-box" id="fileBusinessBox">
                                        <c:if test="${not empty csMember.fileBusinessList}">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${csMember.fileBusinessList}" var="list" varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="营业执照" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                <tr>
                    <td colspan="2">
                        <div class="pdl10 pdb0">
                                    <h4 class="thumb-title">
                                        法人身份证
                                    </h4>
                                    <div class="thumb-xs-box" id="fileIdCardBox">
                                        <c:if test="${not empty csMember.fileIdCardList }">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${csMember.fileIdCardList}" var="list" varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="法人身份证" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                <tr>
                    <td colspan="2">
                        <div class="pdl10 pdb0">
                                    <h4 class="thumb-title">
                                        公盘系统服务协议
                                    </h4>
                                    <div class="thumb-xs-box" id="fileContractBox">
                                        <c:if test="${not empty csMember.fileContractList }">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${csMember.fileContractList}" var="list" varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="公盘系统服务协议" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                <tr>
                    <td colspan="2">
                        <div class="pdl10 pdb0">
                                    <h4 class="thumb-title">
                                        初始会员协议
                                    </h4>
                                    <div class="thumb-xs-box" id="fileProxyBox">
                                        <c:if test="${not empty csMember.csMemberFileList}">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${csMember.csMemberFileList}" var="list" varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="初始会员协议" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                <tr>
                    <td colspan="2">
                        <div class="pdl10 pdb0">
                                    <h4 class="thumb-title">
                                        初始会员费付款凭证
                                    </h4>
                                    <div class="thumb-xs-box" id="fileAccountChangeBox">
                                       
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${csMember.csMemberPayFileList}" var="list" varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="初始会员费付款凭证" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                <tr>
                    <td colspan="2">
                        <div class="pdl10 pdb0">
                                    <h4 class="thumb-title">
                                        其他
                                    </h4>
                                    <div class="thumb-xs-box" id="fileOtherBox">
                                        <c:if test="${not empty csMember.othersFileList}">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${csMember.othersFileList}" var="list" varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="其他" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
        </div>
    </div>
</div>
</body>

</html>
