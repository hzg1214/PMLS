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
		<div class="crumbs">
			<ul style="margin-right:150px;">
				<li><a href="#"  class="a_hover">合同管理</a></li>
				<li><a href="#"  class="a_hover">>保证金退款</a></li>
				<li><a href="#"  class="a_hover">>退款详情</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="page-content">
				<input type="hidden" id="receiveId" name="receiveId" value="${receiveId}"/>
				<h4 class="border-bottom pdb10">
					<strong>保证金退款详情</strong>
					<a href="${ctx}/storePayment?searchParam=1" class="btn btn-primary">返回</a>
				</h4>
				<h4
					style="margin-right: 15px;margin-left:15px;border-bottom :1px dashed #e1e1e1;font-size:14px;height: 45px;"">
					<tr>&nbsp;&nbsp;&nbsp;
						<td class="talabel">编号：</td>
							<td>${storeBackinfo.storePayment.paymentNo}</td>
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
							<td>${storeBackinfo.storePayment.contractNo}</td>
							<td class="talabel">协议书编号：</td>
							<td>
								${storeBackinfo.storePayment.agreementNo}
							</td>
							<td class="talabel">合作模式：</td>
							<td>${storeBackinfo.storePayment.contractTypeName}</td>
						</tr>
						<tr>
							<td class="talabel">经纪公司：</td>
							<td>${storeBackinfo.storePayment.companyName}</td>
							<td class="talabel">退款日期：</td>
							<td>${sdk:ymd2(storeBackinfo.storePayment.refundDate)}</td>
							<%-- <td class="talabel">审核状态：</td>
							<td>${storeBackinfo.storePayment.approveStatusNm}</td> --%>
							<td class="talabel">退款方式：</td>
							<td>${storeBackinfo.storePayment.paymentTypeNm}</td>
						</tr>
						<tr>
							
							<td class="talabel">退款金额：</td>
							<td>
								<c:if test="${not empty storeBackinfo.storePayment.totalAmount}">
								 ￥<fmt:formatNumber type="number" value="${storeBackinfo.storePayment.totalAmount}" pattern="0.00" maxFractionDigits="2"/>
								</c:if>
							</td>
							<td class="talabel">申请人：</td>
							<td>${storeBackinfo.storePayment.userName}</td>
							<td class="talabel">申请时间：</td>
							<td>${storeBackinfo.storePayment.dateCreate}</td>
						</tr>
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
                        <th>应退金额（元）</th>
                        <th>已退金额（元）</th>
                        <th>本次退款金额（元）</th>
                    </tr>
                    <c:forEach items="${storeBackinfo.storeDetails}" var="storelist">
                        <tr>
                            <input type="hidden" name="storeId" id="storeId" value="${storelist.storeId }">
                            <td><a href="javascript:void(0)"
                                   onclick="contractGotoStore('${ctx}/store/${storelist.storeId}','/store')">${storelist.storeNo}</a>
                            </td>
                            <td>${storelist.name}</td>
                            <td>${storelist.addressDetail}</td>
                            <td><fmt:formatNumber type="number" value="${storelist.totalAmount}" pattern="0.00" maxFractionDigits="2"/></td>
                            <td><fmt:formatNumber type="number" value="${storelist.paymentAmount}" pattern="0.00" maxFractionDigits="2"/></td>
                            <td><fmt:formatNumber type="number" value="${storelist.amount}" pattern="0.00" maxFractionDigits="2"/></td>
                            <%-- <td><a href="javascript:void(0)" onclick="showSignHis(${storelist.storeId})">查看</a></td> --%>
                        </tr>
                    </c:forEach>
                </table>
                <!-- <div class="page-content" >
                    <h4 style="margin-right: 15px;margin-left:15px;">
                        <strong>附件</strong>
                    </h4>
                </div> -->
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
                                            <c:if test="${not empty storeBackinfo.fileRecordMainAttachment }">
                                                <c:set var="fileSize" value="0"/>
                                                <c:forEach items="${storeBackinfo.fileRecordMainAttachment}" var="list"
                                                           varStatus="status">
                                                    <c:set var="fileSize" value="${fileSize + 1}"/>
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="附件" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
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
	</div> 
</body>
</html>
