<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
    <script type="text/javascript" src="${ctx}/meta/js/contract/contractDetail.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
</head>
<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>
<div class="container">
    <form id="contractViewForm">
    	<div class="crumbs">
			<ul>
				<li><a href="#"  class="a_hover">合同管理</a></li>
				<li><a href="#"  class="a_hover">>合同详情</a></li>
				<li><a href="#"  class="a_hover">>基本信息</a></li>
			</ul>
		</div>
        <input type="hidden" id="centerId" name="centerId" value="${centerId}"/>
        <input type="hidden" id="id" name="id" value="${contractInfo.contract.id}"/>
        <input type="hidden" id="contractVersion" name="contractVersion" value="${contractInfo.contract.contractVersion}"/>
        <input type="hidden" id="companyId" name="companyId" value="${contractInfo.contract.companyId}"/>
        <input type="hidden" id="storeIdArray" name="storeIdArray"/>

        <input type="hidden" id="contractType" value="${contractInfo.contract.contractType}"/>
        <input type="hidden" id="coopType" value="${contractInfo.contract.coopType}"/>
        <input type="hidden" id="contractId" value="${contractId}"/>
        <!-- 合同状态 -->
        <input type="hidden" id="contractStatus" value="${contractStatus}"/>

        <!-- 合同oaId -->
        <input type="hidden" id="flowId" value="${contractInfo.contract.flowId}"/>


        <!-- 隐藏域，用于判定提交审核前必填图片是否都已上传 -->
        <!-- 营业执照:oaFileIdBusiness, 身份证或名片:oaFileIdCard, 合作协议书:oaFileIdPhoto,门店照片:oaFileIdStore=, 房友系统申请安装单:oaFileIdInstall, 其它oaFileIdOther  -->
        <input type="hidden" id="oaFileIdBusiness" value="${contractInfo.contract.oaFileIdBusiness}"/>
        <input type="hidden" id="oaFileIdCard" value="${contractInfo.contract.oaFileIdCard}"/>
        <input type="hidden" id="oaFileIdPhoto" value="${contractInfo.contract.oaFileIdPhoto}"/>
        <input type="hidden" id="oaFileIdStore" value="${contractInfo.contract.oaFileIdStore}"/>
        <input type="hidden" id="oaFileIdInstall" value="${contractInfo.contract.oaFileIdInstall}"/>
        <!-- Add by WangLei 2017/04/07 Start -->
        <input type="hidden" id="agreementType" value="${contractInfo.contract.agreementType}"/>
        <input type="hidden" id="contractTypeValue" id="contractTypeValue"  value="${contractInfo.contract.contractType}"/>
        <input type="hidden" id="contractTypeNameValue" id="contractTypeNameValue"  value="${contractInfo.contract.contractTypeName}"/>
        <input type="hidden" id="shoupaiTypeValue" id="shoupaiTypeValue"  value="${contractInfo.contract.shoupaiType}"/>
        <!-- Add by WangLei 2017/04/07 End  -->
        <div class="row article">
            <!-- 左侧菜单 -->
            <jsp:include page="/WEB-INF/page/contract/contractLeftMenu.jsp"
                         flush="true">
                <jsp:param value="110401" name="leftMenuSelectId"/>
            </jsp:include>
            <div class="col-md-10 content">
                <div class="page-content">
                    <h4>
                        <strong>基本信息</strong>
                        <a href="${ctx}/contract?searchParam=1" class="btn btn-primary">返回</a>
                        <c:choose>
                            <c:when test="${contractInfo.contract.contractType eq 10306}">
                            </c:when>
                            <c:otherwise>
                                <!-- 非C版 且 经办人才能对 (处于审核中、审核不同过的) 的合同 有 提交审核和获取审核状态权限 -->
                                <c:if test="${not empty oaOperator.operCode && contractInfo.contract.contractType ne 10303}">
                                    <!-- 处于审核中、审核不同过的 -->
                                    <c:if test="${(contractInfo.contract.contractStatus eq 10401&&(empty contractInfo.contract.submitOAStatus || contractInfo.contract.submitOAStatus eq 21201 || contractInfo.contract.submitOAStatus eq 21204)) || (contractInfo.contract.contractStatus eq 10404 && contractInfo.contract.submitOAStatus ne 21202)}">
                                            <a href="javascript:Contract.toAudit('${contractInfo.contract.id}','${oaOperator.isCombine}');"
                                               class="btn btn-primary" style="margin-right:10px;">提交审核</a>
                                    </c:if>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                        <shiro:hasPermission name="/contract:LOAD_ADDITIONAL">
	                        <c:if test="${(contractInfo.contract.contractType eq 10302 || contractInfo.contract.contractType eq 10304) && contractInfo.contract.contractStatus eq 10403}">
	                            <a href="javascript:Contract.toUploadAdditional('${contractInfo.contract.id}');" class="btn btn-primary" style="margin-right:10px;">上传补充协议</a>
	                        </c:if>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/ct:OPERATE_STATUS">
	                        <c:if test="${contractInfo.contract.contractStatus eq 10402}">
	                            <a href="javascript:void(0)" onclick="operateChangeCt(${contractInfo.contract.id});" id="operateChangeCt" class="btn btn-primary" style="margin-right:10px;">运营维护合同状态</a>
	                        </c:if>
                        </shiro:hasPermission>
                    </h4>
                </div>
                <table class="table-sammary">
                    <col style="width:130px;">
                    <col style="width:atuo;">
                    <col style="width:130px;">
                    <col style="width:atuo;">
                    <tr>
                        <td class="talabel">合同编号：</td>
                        <td>${contractInfo.contract.contractNo}</td>
                        <td class="talabel">甲方公司：</td>
                        <td>${contractInfo.contract.partyB}</td>
                    </tr>
                    <tr>
                        <td class="talabel">统一社会信用代码：</td>
                        <td>${contractInfo.contract.registrId}</td>
                        <td class="talabel">法定代表/负责人：</td>
                        <td>${contractInfo.contract.lealPerson}</td>
                    </tr>
                    <!-- Mod 2017/04/05 cning start -->
                    <%--  <ul class="list-inline half form-inline">
                  <li><div class="form-group"><label class="fw-normal w140 text-right">法定代表人：</label>${contractInfo.contract.lealPerson}</div></li>
                  <li><div class="form-group"><label class="fw-normal w140 text-right">合同状态：</label>${contractInfo.contract.contractStatusName}</div></li>
              </ul>

              <ul class="list-inline half form-inline">
                  <li><div class="form-group"><label class="fw-normal w140 text-right">公司注册地址：</label>${contractInfo.contract.partyAddressDetail}</div></li>
                  <li><div class="form-group"><label class="fw-normal w140 text-right">协议书编号：</label>${contractInfo.contract.agreementNo}</div></li>
              </ul> --%>

                    <tr>
                        <td class="talabel">公司注册地址：</td>
                        <td colspan="3">${contractInfo.contract.partyAddressDetail}</td>
                    </tr>
                    <tr>
                        <td class="talabel">合同类别：
                            <!-- Add By NingChao 2017/04/07 Start -->
                            <input type="hidden" id="originalContractDistinction"
                                   value="${contractInfo.contract.originalContractdistinction}"></td>
                        <!-- Add By NingChao 2017/04/07 End -->
                        <c:if test="${contractInfo.contract.originalContractdistinction eq 18601}">
                            <td>新签</td>
                        </c:if>
                        <c:if test="${contractInfo.contract.originalContractdistinction eq 18602}">
                            <td>续签</td>
                        </c:if>

                        <c:if test="${contractInfo.contract.originalContractdistinction eq 18602}">
                            <td class="talabel">原合同编号：</td>
                            <td>
                                <a href="${ctx}/contract/${fn:trim(contractInfo.contract.originalId)}/${fn:trim(contractStatus)}">${contractInfo.contract.originalContractNo}</a>
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td class="talabel">协议书编号：</td>
                        <td>${contractInfo.contract.agreementNo}</td>
                        <td class="talabel">合作模式：</td>
                        <td>${contractInfo.contract.contractTypeName}</td>
                    </tr>
                    <c:if test="${not empty contractInfo.contract.contractType && '10307' eq contractInfo.contract.contractType}">
	                    <tr>
	                        <td class="talabel">授牌类型：</td>
	                        <td>
	                        	<c:if test="${not empty contractInfo.contract.shoupaiType && '24603' eq contractInfo.contract.shoupaiType}">
	                        		渠道
	                        	</c:if>
	                        	<c:if test="${not empty contractInfo.contract.shoupaiType && '24602' eq contractInfo.contract.shoupaiType}">
	                        		社区
	                        	</c:if>
	                        	<c:if test="${not empty contractInfo.contract.shoupaiType && '24601' eq contractInfo.contract.shoupaiType}">
	                        		门店
	                        	</c:if>
	                        </td>
	                    </tr>
                    </c:if>
                    <tr>
                        <td class="talabel">甲方授权代表：</td>
                        <td>${contractInfo.contract.authRepresentative}</td>
                        <td class="talabel">联系方式：</td>
                        <td>${contractInfo.contract.agentContact}</td>
                    </tr>
                    <tr>
                        <td class="talabel">合同生效日期：</td>
                        <td>${sdk:ymd2(contractInfo.contract.dateLifeStart)}</td>
                        <td class="talabel">合同到期日期：</td>
                        <td>${sdk:ymd2(contractInfo.contract.dateLifeEnd)}</td>
                    </tr>
                    <tr>
                        <td class="talabel">每店保证金额：</td>
                        <td>￥<fmt:formatNumber type="number" value="${contractInfo.contract.depositFee}" pattern="0.00" maxFractionDigits="2"/></td>
                        <td class="talabel">合作门店数：</td>
                        <td>${contractInfo.contract.storeNum}</td>
                    </tr>
                    <tr>
                        <td class="talabel">总保证金：</td>
                        <td>
                        	<c:if test="${not empty contractInfo.contract.totleDepositFee}">
                        		￥<fmt:formatNumber type="number" value="${contractInfo.contract.totleDepositFee}" pattern="0.00" maxFractionDigits="2"/>
                        	</c:if>
                        	</td>
                        <td class="talabel">违约金金额：</td>
                        <td>
                        	<c:if test="${not empty contractInfo.contract.penaltyFee}">
                       			 ￥<fmt:formatNumber type="number" value="${contractInfo.contract.penaltyFee}" pattern="0.00" maxFractionDigits="2"/>
                       		</c:if>
                        </td>

                    </tr>
                    <tr>
                        <td class="talabel">客户公司账号：</td>
                        <td>${contractInfo.contract.companyBankNo}</td>
                        <td class="talabel">开户省市：</td>
                        <td>${contractInfo.contract.accountProvinceName}${contractInfo.contract.accountCityName}</td>
                    </tr>
                    <tr>
                        <td class="talabel">开户银行：</td>
                        <td>${contractInfo.contract.bankAccount}</td>
                        <td class="talabel">开户名：</td>
                        <td>${contractInfo.contract.accountName}</td>
                    </tr>
                    <tr>
                        <td class="talabel">甲方收件人：</td>
                        <td>${contractInfo.contract.recipients}</td>
                        <td class="talabel">甲方收件地址：</td>
                        <td>
                        	<c:if test="${not empty contractInfo.contract.recipientsAddressDetail}">
                        		${contractInfo.contract.recipientsAddressDetail}
                        	</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">OA审批流程类别：</td>
                        <td>${contractInfo.contract.oaApproveTypeName}</td>
                        <td class="talabel">是否乙转甲新签：</td>
                        <td>
                            <c:choose>
                                <c:when test="${empty contractInfo.contract.contractB2A}">-</c:when>
                                <c:otherwise>
                                    ${contractInfo.contract.contractB2AName}
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">到账保证金总额：</td>
                        <td>
                            <c:choose>
                                <c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
                                    -
                                </c:when>
                                <c:otherwise>
                                    ${contractInfo.contract.arrivalDepositFee}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="talabel">保证金到账状态：</td>
                        <td>
                            <c:choose>
                                <c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
                                    -
                                </c:when>
                                <c:otherwise>
                                    ${contractInfo.contract.depositFeeStateName}
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">保证金退款总额：</td>
                        <td>
                            <c:choose>
                                <c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
                                    -
                                </c:when>
                                <c:when test="${empty contractInfo.contract.totalRefundAmount}">-</c:when>
                                <c:otherwise>
                                    ${contractInfo.contract.totalRefundAmount}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="talabel">保证金退款状态：</td>
                        <td>
                            <c:choose>
                                <c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
                                    -
                                </c:when>
                                <c:when test="${empty contractInfo.contract.refundStateName}">-</c:when>
                                <c:otherwise>
                                    ${contractInfo.contract.refundStateName}
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">业绩归属拓展人：</td>
                        <td>${contractInfo.contract.expandingPersonnel}</td>
                        <td class="talabel">业绩归属中心：</td>
                        <td>${contractInfo.contract.centerName}</td>
                    </tr>
                    <tr>
                        <c:if test="${!(contractInfo.contract.contractStatus eq 10403 && contractInfo.contract.submitOAStatus eq 21201)}">
                            <td class="talabel">提交OA状态：</td>
                            <c:if test="${contractInfo.contract.submitOAStatus eq null}">
                                <td>-</td>
                            </c:if>
                            <c:if test="${contractInfo.contract.submitOAStatus eq 21202||contractInfo.contract.submitOAStatus eq 21204}">
                                <td style="color:red;">${contractInfo.contract.submitOAStatusName}</td>
                            </c:if>
                            <c:if test="${contractInfo.contract.submitOAStatus eq 21201||contractInfo.contract.submitOAStatus eq 21203}">
                                <td>${contractInfo.contract.submitOAStatusName}</td>
                            </c:if>
                        </c:if>
                        <td class="talabel">创建时间：</td>
                        <td>${contractInfo.contract.dateCreate}</td>
                    </tr>
                    <tr>
                        <td class="talabel">合同审核状态：</td>
                        <td>${contractInfo.contract.contractStatusName}</td>
                        <td class="talabel">审核通过时间：</td>
                        <td>${sdk:ymd2(contractInfo.contract.performDate)}</td>
                    </tr>
                </table>
                <div class="page-content">
                    <h4>
                        <strong>门店信息</strong>
                    </h4>
                </div>
                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                    <tr>
                        <th style="width:100px;">门店编号</th>
                        <th>门店名称</th>
                        <!-- <th>所在区域</th> -->
                        <th>门店地址</th>
                        <!-- <th>创建日期</th> -->
                        <th>门店资质等级</th>
                        <th>签约状态</th>
                        <th>是否到账</th>
                        <th>到账日期</th>
                        <th>退款金额</th>
                        <th>最后退款日期</th>
                        <th>退款状态</th>
                        <th>签约历史</th>
                    </tr>
                    <c:forEach items="${contractInfo.storeDetails}" var="storelist">
                        <tr>
                            <!--Add By WangLei 2017/04/07 Start-->
                            <input type="hidden" name="storeId" id="storeId" value="${storelist.storeId }">
                            <!--Add By WangLei 2017/04/07 Start!-->
                            <td><a href="javascript:void(0)"
                                   onclick="contractGotoStore('${ctx}/store/${storelist.storeId}','/store')">${storelist.storeNo}</a>
                            </td>
                            <td>${storelist.name}</td>
                                <%-- <td>${storelist.districtName}</td> --%>
                            <td>${storelist.addressDetailContract}</td>
                                <%-- <td>${storelist.dateCreate}</td> --%>
                            <td>
                                <c:choose>
                                    <c:when test="${empty storelist.abtypeStore}">-</c:when>
                                    <c:when test="${storelist.abtypeStore eq 19901}">
                                        甲类
                                    </c:when>
                                    <c:when test="${storelist.abtypeStore eq 19902}">
                                        乙类(${storelist.btypeStoreName})
                                    </c:when>
                                    <c:otherwise>
                                        -
                                    </c:otherwise>
                                </c:choose>


                            </td>
                            <td>${storelist.storeStateName}</td>
                            <td><c:choose>
                                <c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
                                    -
                                </c:when>
                                <c:when test="${storelist.isArrivalAct == 1}">已到账</c:when>
                                <c:otherwise>
                                    未到账
                                </c:otherwise>
                            </c:choose></td>
                            <td><c:choose>
                                <c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
                                    -
                                </c:when>
                                <c:when test="${empty storelist.dateArrivalAct}">-</c:when>
                                <c:otherwise>${sdk:ymd2(storelist.dateArrivalAct)}</c:otherwise>
                            </c:choose></td>
                            <td><c:choose>
                                <c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
                                    -
                                </c:when>
                                <c:when test="${empty storelist.refundAmount}">-</c:when>
                                <c:otherwise>${storelist.refundAmount}</c:otherwise>
                            </c:choose></td>
                            <td><c:choose>
                                <c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
                                    -
                                </c:when>
                                <c:when test="${empty storelist.refundDate}">-</c:when>
                                <c:otherwise>${sdk:ymd2(storelist.refundDate)}</c:otherwise>
                            </c:choose></td>
                            <td><c:choose>
                                <c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
                                    -
                                </c:when>
                                <c:when test="${empty storelist.refundStateName}">-</c:when>
                                <c:otherwise>${storelist.refundStateName}</c:otherwise>
                            </c:choose></td>
                            <td style='display:none'><input name='storeIds' id='storeIds${storelist.storeId}'
                                                            type='hidden' value='${storelist.storeId}'></td>
                            <td><a href="javascript:void(0)" onclick="showSignHis(${storelist.storeId})">查看</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <table class="table-sammary">
                    <col style="width:75px;">
                    <col style="width:auto">
                    <tr>
                        <td class="talabel">合同备注：</td>
                        <td>
                            <span class="fw-normal" name="remark" id="remark" readOnly="readOnly"
                                  style="word-break:break-all;word-wrap: break-word;resize: none;">${contractInfo.contract.remark}</span>
                        </td>
                    </tr>
                </table>
                <div class="page-content">
                    <h4>
                        <strong>附件</strong>
                    </h4>
                </div>
                <table class="table-sammary" name="Viewerbox">
                    <col style="width:145px;">
                    <col style="width:auto">
                    <tr>
                        <!-- <td class="talabel">营业执照：</td> -->
                        <td colspan="2">

                            <div class="" role="main">
                                <div>
                                    <div class="pd10" >
                                        <h4 class="thumb-title">
                                            营业执照
                                        </h4>
                                        <div class="thumb-xs-box" id="fileIdBusinessBox">
                                            <c:if test="${not empty contractInfo.fileRecordMainBusiness }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${contractInfo.fileRecordMainBusiness}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
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
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <!-- <td class="talabel">法人身份证：</td> -->
                        <td colspan="2">

                            <div class="" role="main">
                                <div>
                                    <div class="pd10" >
                                        <h4 class="thumb-title">
                                            法人身份证
                                        </h4>
                                        <div class="thumb-xs-box" id="fileIdCardBox">
                                            <c:if test="${not empty contractInfo.fileRecordMainCard }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${contractInfo.fileRecordMainCard}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
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
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">

                            <div class="" role="main">
                                <div>
                                    <div class="pd10" >
                                        <h4 class="thumb-title">
                                            合同照片
                                        </h4>
                                        <div class="thumb-xs-box" id="fileIdPhotoBox">
                                            <c:if test="${not empty contractInfo.fileRecordMainPhoto }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${contractInfo.fileRecordMainPhoto}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="合同照片" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
										   			</span>
									   			</span>
								   			</span>
                                                        </a>
                                                    </div>
                                                </c:forEach>

                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <!-- <td class="talabel">门店照片：</td> -->
                        <td colspan="2">

                            <div class="" role="main">
                                <div>
                                    <div class="pd10"  >
                                        <h4 class="thumb-title">
                                            门店照片
                                        </h4>
                                        <div class="thumb-xs-box" id="fileIdStoreBox">
                                            <c:if test="${not empty contractInfo.fileRecordMainStore }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${contractInfo.fileRecordMainStore}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="门店照片" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
										   			</span>
									   			</span>
								   			</span>
                                                        </a>
                                                    </div>
                                                </c:forEach>

                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">

                            <div class="" role="main">
                                <div>
                                    <div class="pd10" >
                                        <h4 class="thumb-title">
                                            房友确认单
                                        </h4>
                                        <div class="thumb-xs-box" id="fileIdInstallBox">
                                            <c:if test="${not empty contractInfo.fileRecordMainInstall }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${contractInfo.fileRecordMainInstall}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="房友确认单" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
										   			</span>
									   			</span>
								   			</span>
                                                        </a>
                                                    </div>
                                                </c:forEach>

                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">

                            <div class="" role="main">
                                <div>
                                    <div class="pd10" >
                                        <h4 class="thumb-title">
                                            重要提示函
                                        </h4>
                                        <div class="thumb-xs-box" id="fileIdNoticeBox">
                                            <c:if test="${not empty contractInfo.fileRecordMainNotice }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${contractInfo.fileRecordMainNotice}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="重要提示函" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
										   			</span>
									   			</span>
								   			</span>
                                                        </a>
                                                    </div>
                                                </c:forEach>

                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <c:if test="${not empty contractInfo.fileRecordMainComplement}">
                    <tr>
                        <td colspan="2">

                            <div class="" role="main">
                                <div>
                                    <div class="pd10" >
                                        <h4 class="thumb-title">
                                            补充协议
                                        </h4>
                                        <div class="thumb-xs-box" id="fileIdComplementBox">
                                            <c:if test="${not empty contractInfo.fileRecordMainComplement}"><!-- TODO -->
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${contractInfo.fileRecordMainComplement}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="补充协议" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
										   			</span>
									   			</span>
								   			</span>
                                                        </a>
                                                    </div>
                                                </c:forEach>

                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                    </c:if>
                    <tr>
                        <td colspan="2">

                            <div class="" role="main">
                                <div>
                                    <div class="pd10" >
                                        <h4 class="thumb-title">
                                            其他
                                        </h4>
                                        <div class="thumb-xs-box" id="fileIdOtherBox">
                                            <c:if test="${not empty contractInfo.fileRecordMainOther }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${contractInfo.fileRecordMainOther}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
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
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </form>
</div>
</body>

</html>
