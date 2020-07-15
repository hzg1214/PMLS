<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
    <script type="text/javascript" src="${ctx}/meta/js/gpContract/gpContractDetail.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
    <style>
        .icon-explain{
            width: 16px;
            height: 16px;
            display: inline-block;
            vertical-align: middle;
            position: relative;
            text-align: center;
            color: #fff;
            font-style: normal;
            font-size: 10px;
            line-height: 17px;
        }
        .icon-explain::after, .icon-explain::before{
            display: block;
            width: 100%;
            height: 100%;
        }
        .icon-explain::before {
            content: "\20";
            border-radius: 100%;
            background-color: #4A9AFB;
        }
        .icon-explain::after {
            content: "?";
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -8px;
            margin-left: -8px;
        }
    </style>
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
            <li><a href="#" class="a_hover">>公盘合同列表</a></li>
            <li><a href="#" class="a_hover">>公盘合同详情</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="page-content">
            <input type="hidden" id="centerId" name="centerId" value="${centerId}"/>
            <input type="hidden" id="id" name="id" value="${gpContract.id}"/>
            <input type="hidden" id="companyId" name="companyId" value="${gpContract.companyId}"/>
            <input type="hidden" id="storeIdArray" name="storeIdArray"/>
            <input type="hidden" id="contractId" value="${gpContractId}"/>
            <!-- 合同状态 -->
            <input type="hidden" id="contractStatus" value="${contractStatus}"/>
            <!-- 合同oaId -->
            <input type="hidden" id="flowId" value="${gpContract.flowId}"/>
            <h4 class="border-bottom pdb10">
                <strong>公盘合同详情</strong>
                <a href="${ctx}/gpContract?searchParam=1" class="btn btn-primary">返回</a>
                <%-- <shiro:hasPermission name="/gpContract:GP_EDITBANKINFO">
                	<c:if test="${gpContract.contractStatus eq 10402 || gpContract.contractStatus eq 10403}">
                    	<a href="javascript:GpContract.editBankInfoMode()" class="btn btn-primary" style="margin-right:16px">银行信息变更</a>
                    </c:if>
                </shiro:hasPermission> --%>
                <!-- 经办人才能对 (处于审核中、审核不同过的) 的合同 有 提交审核和获取审核状态权限 -->
                <shiro:hasPermission name="/gpContract:GP_SUBMITTOOA">
                    <c:if test="${not empty oaOperator.operCode}">
                        <!-- 处于审核中、审核不同过的 -->
                        <c:if test="${(gpContract.contractStatus eq 10401&&(empty gpContract.submitOAStatus || gpContract.submitOAStatus eq 21201 || gpContract.submitOAStatus eq 21204)) || (gpContract.contractStatus eq 10404 && gpContract.submitOAStatus ne 21202)}">
                            <a href="javascript:GpContract.toAudit('${gpContract.id}','${oaOperator.isCombine}');"
                               class="btn btn-primary" style="margin-right:10px;">提交审核</a>
                        </c:if>
                    </c:if>
                </shiro:hasPermission>
                <shiro:hasPermission name="/GpCtStop:GP_STOP">
                	<c:if test="${gpContract.contractStatus eq 10403}">
					<a type="button" class="btn btn-primary" style="float:right; margin-right:10px;" 
							href="${ctx}/gpContractChange/toAddGpContractChange/${gpContract.id}/${gpContract.partyB}/${gpContract.gpContractNo}">公盘合同终止</a>
					</c:if>
				</shiro:hasPermission>
                <shiro:hasPermission name="/ct:OPERATE_STATUS">
                    <c:if test="${gpContract.contractStatus eq 10402}">
                        <a href="javascript:void(0)" onclick="operateChangeCt(${gpContract.id});" id="operateChangeCt" class="btn btn-primary" style="margin-right:10px;">运营维护合同状态</a>
                    </c:if>
                </shiro:hasPermission>
            </h4>
            <div style="margin-bottom:20px;">
                <p>
                    <strong style="font-size:16px;">基本信息</strong>
                </p>
                <table class="table-sammary">
                    <col style="width:140px;">
                    <col style="width:auto;">
                    <col style="width:140px;">
                    <col style="width:auto;">
                    <tr>
                        <td class="talabel">公盘合同编号：</td>
                        <td>${gpContract.gpContractNo}</td>
                        <td class="talabel">甲方公司：</td>
                        <td>${gpContract.partyB}</td>
                    </tr>
                    <tr>
                        <td class="talabel">统一社会信用代码：</td>
                        <td>${gpContract.registerId}</td>
                        <td class="talabel">法定代表/负责人：</td>
                        <td>${gpContract.legalPerson}</td>
                    </tr>
                    <tr>
                        <td class="talabel">公司注册地址：</td>
                        <td colspan="3">${gpContract.partyAddressDetail}</td>
                    </tr>
                    <tr>
                        <td class="talabel">合同类别：</td>
                        <td>${gpContract.contractTypeNm}</td>
                    </tr>
                    <tr>
                        <td class="talabel">我方全称：</td>
                        <td>${gpContract.ourFullName}</td>
                        <td class="talabel">公盘协议书编号：</td>
                        <td>${gpContract.gpAgreementNo}</td>
                    </tr>
                    <tr>
                        <td class="talabel"><i class="icon-explain" title="该甲方公司所有的有效的B模式合同保证金到账金额之和-保证金退款金额之和"></i>&nbsp;翻牌到账保证金：</td>
                        <td>${gpContract.deposit}</td>
                    </tr>
                    <tr>
                        <td class="talabel">公盘门店数：</td>
                        <td>${gpContract.storeNum}</td>
                        <td class="talabel">保证金：</td>
                        <td>
                            <c:if test="${not empty gpContract.depositFee}">
                                ￥<fmt:formatNumber type="number" value="${gpContract.depositFee}" pattern="0.00" maxFractionDigits="2"/>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">合同生效日期：</td>
                        <td>${sdk:ymd2(gpContract.dateLifeStart)}</td>
                        <td class="talabel">合同到期日期：</td>
                        <td>${sdk:ymd2(gpContract.dateLifeEnd)}</td>
                    </tr>
                    <tr>
                        <td class="talabel">甲方授权代表：</td>
                        <td>${gpContract.partyBNm}</td>
                        <td class="talabel">甲方联系方式：</td>
                        <td>${gpContract.partyBTel}</td>
                    </tr>
                    <tr>
                        <td class="talabel">业绩归属人：</td>
                        <td>${gpContract.exPerson}</td>
                        <td class="talabel">业绩归属中心：</td>
                        <td>${gpContract.centerName}</td>
                    </tr>
                    <tr>
                        <td class="talabel">开户名：</td>
                        <td>${gpContract.accountNm}</td>
                        <td class="talabel">开户省市：</td>
                        <td>${gpContract.accountProvinceNm}${gpContract.accountCityNm}</td>
                    </tr>
                    <tr>
                        <td class="talabel">开户行：</td>
                        <td>${gpContract.bankAccountNm}</td>
                        <td class="talabel">银行账号：</td>
                        <td>${gpContract.bankAccount}</td>
                    </tr>
                    <tr>
                        <td class="talabel" style="vertical-align: text-top;">合同备注：</td>
                        <td colspan="3">${gpContract.remark}</td>
                    </tr>
                    <tr>
                        <td class="talabel" style="vertical-align: text-top;">公盘合同OA编号：</td>
                        <td colspan="3">${gpContract.oaNo}</td>
                    </tr>
                    <tr>
                        <c:if test="${!(gpContract.contractStatus eq 10403 && gpContract.submitOAStatus eq 21201)}">
                            <td class="talabel">提交OA状态：</td>
                            <c:if test="${gpContract.submitOAStatus eq null}">
                                <td>-</td>
                            </c:if>
                            <c:if test="${gpContract.submitOAStatus eq 21202||gpContract.submitOAStatus eq 21204}">
                                <td style="color:red;">${gpContract.submitOAStatusNm}</td>
                            </c:if>
                            <c:if test="${gpContract.submitOAStatus eq 21201||gpContract.submitOAStatus eq 21203}">
                                <td>${gpContract.submitOAStatusNm}</td>
                            </c:if>
                        </c:if>
                        <td class="talabel">创建时间：</td>
                        <td>${gpContract.dateCreate}</td>
                    </tr>
                    <tr>
                        <td class="talabel">合同审核状态：</td>
                        <td>${gpContract.contractStatusNm}</td>
                        <td class="talabel">审核通过时间：</td>
                        <td>${gpContract.performDate}</td>
                    </tr>
                    <tr>
                        <td class="talabel">保证金状态：</td>
                        <td>${gpContract.depositStatusNm}</td>
                    </tr>
                </table>
                <p>
                    <strong style="font-size:16px;">门店</strong>
                </p>
                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                    <tr>
                        <th style="width:100px;">门店编号</th>
                        <th>门店名称</th>
                        <th>门店维护人</th>
                        <th>门店负责人</th>
                        <th>负责人电话</th>
                        <%--<th>资质等级</th>--%>
                    </tr>
                    <c:forEach items="${gpContract.storeDetails}" var="storelist">
                        <tr>
                            <input type="hidden" name="storeId" id="storeId" value="${storelist.storeId }">
                            <td>${storelist.storeNo}</td>
                            <td>${storelist.name}</td>
                            <td>${storelist.maintainerName}</td>
                            <td>${storelist.storeManager}</td>
                            <td>${storelist.storeManagerPhone}</td>
                            <%--<td>
                                <c:if test="${empty storelist.abtypeStore || storelist.abtypeStore eq 0}"> -- </c:if>
                                <c:if test="${storelist.abtypeStore eq 19901}">甲类</c:if>
                                <c:if test="${storelist.abtypeStore eq 19902 }">乙类 (${storelist.btypeStoreName})</c:if>
                            </td>--%>
                        </tr>
                    </c:forEach>
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
                                        <c:if test="${not empty gpContract.fileBusinessList }">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${gpContract.fileBusinessList}" var="list" varStatus="status">
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
                                        <c:if test="${not empty gpContract.fileIdCardList }">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${gpContract.fileIdCardList}" var="list" varStatus="status">
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
                                        <c:if test="${not empty gpContract.fileContractList }">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${gpContract.fileContractList}" var="list" varStatus="status">
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
                                        授权委托书（确认易居房友15日可支付房源方分成佣金）
                                    </h4>
                                    <div class="thumb-xs-box" id="fileProxyBox">
                                        <c:if test="${not empty gpContract.fileProxyList}">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${gpContract.fileProxyList}" var="list" varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="授权委托书" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                                        直联盘勾选表
                                    </h4>
                                    <div class="thumb-xs-box" id="fileCheckBox">
                                        <c:if test="${not empty gpContract.fileCheckList}">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${gpContract.fileCheckList}" var="list" varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="授权委托书" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                                        易居房友经纪业务共享平台规则
                                    </h4>
                                    <div class="thumb-xs-box" id="fileRuleBox">
                                        <c:if test="${not empty gpContract.fileRuleList}">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${gpContract.fileRuleList}" var="list" varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="授权委托书" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                <c:if test="${not empty gpContract.fileAccountChangeList}">
                <tr>
                    <td colspan="2">
                        <div class="pdl10 pdb0">
                                    <h4 class="thumb-title">
                                        账户变更申请书
                                    </h4>
                                    <div class="thumb-xs-box" id="fileAccountChangeBox">
                                       
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${gpContract.fileAccountChangeList}" var="list" varStatus="status">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
                                                        <span class="thumb-img">
                                                            <span class="thumb-img-container">
                                                                <span class="thumb-img-content">
                                                                    <img alt="账户变更申请书" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                </c:if>
                <tr>
                    <td colspan="2">
                        <div class="pdl10 pdb0">
                                    <h4 class="thumb-title">
                                        其他
                                    </h4>
                                    <div class="thumb-xs-box" id="fileOtherBox">
                                        <c:if test="${not empty gpContract.fileOtherList }">
                                            <c:set var="fileSize" value="0"/>
                                            <c:forEach items="${gpContract.fileOtherList}" var="list" varStatus="status">
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
<form id="bankAdjustSubimtForm" style="height: auto;width: auto;display:none" action="${ctx}/report/editBankInfoMode">
	<!-- 弹窗返回需要 -->
	<input type = "hidden"  name = "gpContractId" value = "${gpContract.id}"/>
	<!-- 弹窗传递的参数 -->
	<input type = "hidden"  name = "companyId" value = "${gpContract.companyId}"/>
	<input type = "hidden"  name = "companyName" value = "${gpContract.companyName}"/>
	<input type = "hidden"  name = "partyB" value = "${gpContract.partyB}"/>
	<!-- 开户名 -->
	<input type = "hidden"  name = "accountNm" value = "${gpContract.accountNm}"/>
	<!-- 开户行 -->
	<input type = "hidden"  name = "bankAccountNm" value = "${gpContract.bankAccountNm}"/>
	<!-- 银行账号 -->
	<input type = "hidden"  name = "bankAccount" value = "${gpContract.bankAccount}"/>
	
	<input type = "hidden"  name = "accountProvinceNo" value = "${gpContract.accountProvinceNo}"/>
	<input type = "hidden"  name = "accountProvinceNm" value = "${gpContract.accountProvinceNm}"/>
	<input type = "hidden"  name = "accountCityNo" value = "${gpContract.accountCityNo}"/>
	<input type = "hidden"  name = "accountCityNm" value = "${gpContract.accountCityNm}"/>
	
</form>
</body>

</html>
