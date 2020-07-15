<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
</head>

<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container theme-hipage ng-scope">
		<%--<div class="crumbs">--%>
			<%--<ul style="margin-right:150px;">--%>
				<%--<li><a href="#"  class="a_hover">合同管理</a></li>--%>
				<%--<li><a href="#"  class="a_hover">>保证金收款</a></li>--%>
				<%--<li><a href="#"  class="a_hover">>收款详情</a></li>--%>
			<%--</ul>--%>
		<%--</div>--%>
		<div class="row">
			<div class="page-content">
				<input type="hidden" id="receiveId" name="receiveId" value="${receiveId}"/>
				<input type="hidden" id="orderTypeFlag" name="orderTypeFlag" value="${orderTypeFlag}"/>
				<h4 class="border-bottom pdb10">
					<strong>收款详情</strong>
					<a href="${ctx}/storeReceive?searchParam=1" style="float: right;"><img src="${ctx}/meta/images/crm/close_page.png">  </a>
				</h4>
				<h4
					style="margin-right: 15px;margin-left:15px;border-bottom :1px dashed #e1e1e1;font-size:14px;height: 45px;"">
					<tr>&nbsp;&nbsp;&nbsp;
						<td class="talabel">编号：</td>
							<td>${storeReceiveinfo.storeReceive.receiveNo}</td>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:if test="${'17906' eq storeReceiveinfo.storeReceive.receiveType}"><td class="talabel" >支付流水号：</td>
							<td>
								 ${storeReceiveinfo.storeReceive.paySeq}
								</c:if>
							</td>
					</tr>
				</h4>
				<table class="table-sammary">
						<col style="width:135px;">
						<col style="width:auto">
						<col style="width:130px;">
						<col style="width:auto">
						<col style="width:145px;">
						<col style="width:auto">
						<tr>
							<td class="talabel">合同编号：</td>
							<td>${storeReceiveinfo.storeReceive.contractNo}</td>
							<td class="talabel">协议书编号：</td>
							<td>
								${storeReceiveinfo.storeReceive.agreementNo}
							</td>
							<td class="talabel">合作模式：</td>
							<td>${storeReceiveinfo.storeReceive.contractTypeName}</td>
						</tr>
						<tr>
							<td class="talabel">经纪公司：</td>
							<td>${storeReceiveinfo.storeReceive.companyName}</td>
							<td class="talabel">收款时间：</td>
							<td>${storeReceiveinfo.storeReceive.dateCreate}</td>
							<td class="talabel">状态：</td>
							<td>${storeReceiveinfo.storeReceive.approveStatusNm}</td>
							<%--<td class="talabel">提交OA状态：</td>
							<c:if test="${storeReceiveinfo.storeReceive.submitOaStatus eq 21202||storeReceiveinfo.storeReceive.submitOaStatus eq 21204}">
								<td style="color:red;">${storeReceiveinfo.storeReceive.submitOaStatusNm}</td>
							</c:if>
							<c:if test="${storeReceiveinfo.storeReceive.submitOaStatus eq 21201||storeReceiveinfo.storeReceive.submitOaStatus eq 21203}">
								<td>${storeReceiveinfo.storeReceive.submitOaStatusNm}</td>
							</c:if>--%>
						</tr>
						<tr>
							<td class="talabel">收款类型：</td>
							<td>${storeReceiveinfo.storeReceive.feeTypeNm}</td>
							<td class="talabel">收款方式：</td>
							<td>${storeReceiveinfo.storeReceive.receiveTypeNm}</td>
							<td class="talabel">收款金额：</td>
							<td>
								<c:if test="${not empty storeReceiveinfo.storeReceive.totalAmount}">
								 ￥<fmt:formatNumber type="number" value="${storeReceiveinfo.storeReceive.totalAmount}" pattern="0.00" maxFractionDigits="2"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="talabel">收款人：</td>
							<td>${storeReceiveinfo.storeReceive.userName}</td>
							<td class="talabel" style="vertical-align: text-top;">OA收款编号：</td>
							<td style="vertical-align: text-top;">${storeReceiveinfo.storeReceive.oaNo}</td>
							<td class="talabel" style="vertical-align: text-top;">核算主体：</td>
							<td style="vertical-align: text-top;">
								<c:if test="${storeReceiveinfo.storeReceive.accountProjectCode ne null}">
									${storeReceiveinfo.storeReceive.accountProject}/${storeReceiveinfo.storeReceive.accountProjectCode}
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="talabel" style="vertical-align: text-top;">入账日期：</td>
							<td style="vertical-align: text-top;">${sdk:ymd2(storeReceiveinfo.storeReceive.dateRecorded)}</td>
							<%--<td class="talabel">审核状态：</td>
							<td>${storeReceiveinfo.storeReceive.approveStatusNm}</td>
							<td class="talabel">审核通过时间：</td>
							<td>${storeReceiveinfo.storeReceive.approvePassDate}</td>
							<c:if test="${'17906' eq storeReceiveinfo.storeReceive.receiveType}">
								<td class="talabel">对账时间：</td>
								<td>${storeReceiveinfo.storeReceive.confirmTime}</td>
							</c:if>--%>
							<td class="talabel" style="vertical-align: text-top;">账户名：</td>
							<td style="vertical-align: text-top;">${storeReceiveinfo.storeReceive.accountName}</td>
							<td class="talabel" style="vertical-align: text-top;">红冲状态：</td>
							<td style="vertical-align: text-top;">
								<c:if test="${storeReceiveinfo.storeReceive.reverseApproveStatusNm ne null}">${storeReceiveinfo.storeReceive.reverseApproveStatusNm}</c:if>
								<c:if test="${storeReceiveinfo.storeReceive.reverseApproveStatusNm eq null}">无</c:if>
							</td>
						</tr>
					<%--<c:if test="${storeReceiveinfo.storeReceive.reverseId ne null}">
						<tr>
							<td class="talabel" style="vertical-align: text-top;">是否红冲：</td>
							<td style="vertical-align: text-top;">是</td>
							<td class="talabel" style="vertical-align: text-top;">红冲OA编号：</td>
							<td style="vertical-align: text-top;">${storeReceiveinfo.storeReceive.reverseOaNo}</td>
							<td class="talabel" style="vertical-align: text-top;">红冲状态：</td>
							<td style="vertical-align: text-top;">${storeReceiveinfo.storeReceive.reverseApproveStatusNm}</td>
						</tr>
                        <c:if test="${storeReceiveinfo.storeReceive.reverseApproveStatus eq 21603 or storeReceiveinfo.storeReceive.reverseApproveStatus eq 21604}">
                            <tr>
                                <td class="talabel">审核时间：</td>
                                <td>${storeReceiveinfo.storeReceive.reverseApproveDate}</td>
                                <td class="talabel">审核原因：</td>
                                <td>${storeReceiveinfo.storeReceive.reverseApproveDesc}</td>
                            </tr>
                        </c:if>
					</c:if>--%>
					<%--<tr>
						<td class="talabel">备注：</td>
						<td colspan="3">${storeReceiveinfo.storeReceive.remark}</td>
					</tr>--%>
					</table>
				<div class="page-content" style="height: 60px;" >
                    <h4  style="margin-right: 15px;margin-left:15px;font-size:16px;height: 45px;">
                        <strong>门店信息</strong>
                    </h4>
                </div>
                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered" style="margin-right: 15px;margin-left:15px;width:1140px">
                    <tr>
                        <th style="width:130px;">门店编号</th>
                        <th style="width:220px;">门店名称</th>
                        <th style="width:300px;">门店地址</th>
                        <th>应收金额（元）</th>
                        <th>已收金额（元）</th>
                        <th>本次收款金额（元）</th>
                    </tr>
                    <c:forEach items="${storeReceiveinfo.storeDetails}" var="storelist">
                        <tr>
                            <input type="hidden" name="storeId" id="storeId" value="${storelist.storeId }">
                            <td><a href="javascript:void(0)"
                                   onclick="contractGotoStore('${ctx}/store/${storelist.storeId}','/store')">${storelist.storeNo}</a>
                            </td>
                            <td>${storelist.name}</td>
                            <td>${storelist.addressDetail}</td>
                            <td><fmt:formatNumber type="number" value="${storelist.totalAmount}" pattern="0.00" maxFractionDigits="2"/></td>
                            <td><fmt:formatNumber type="number" value="${storelist.receiveAmount}" pattern="0.00" maxFractionDigits="2"/></td>
                            <td><fmt:formatNumber type="number" value="${storelist.amount}" pattern="0.00" maxFractionDigits="2"/></td>
                            <%-- <td><a href="javascript:void(0)" onclick="showSignHis(${storelist.storeId})">查看</a></td> --%>
                        </tr>
                    </c:forEach>
                </table>
                <div class="page-content" style="margin-left: 15px;">
                    本次合计收款金额&nbsp;&nbsp;
					<c:if test="${not empty storeReceiveinfo.storeReceive.totalAmount}">
						<strong>￥<fmt:formatNumber type="number" value="${storeReceiveinfo.storeReceive.totalAmount}" pattern="0.00" maxFractionDigits="2"/></strong>
					</c:if>
                </div>
                <c:if test="${'17909' eq storeReceiveinfo.storeReceive.receiveType}">
                <table class="table-sammary" name="Viewerbox">
                    <col style="width:145px;">
                    <col style="width:auto">
                    <tr>
                        <td colspan="2">

                            <div class="" role="main">
                                <div>
                                    <div class="pd10" >
                                        <h4 class="thumb-title" style="font-size:16px;">
                                            附件
                                        </h4>
                                        <div class="thumb-xs-box" id="fileRecordMainAttachment">
                                            <c:if test="${not empty storeReceiveinfo.fileRecordMainAttachment }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${storeReceiveinfo.fileRecordMainAttachment}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="银行凭证" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
                   </table></c:if>
			</div>
		</div>
	</div> 
</body>
</html>
