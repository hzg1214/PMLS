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
				<li><a href="#"  class="a_hover">>保证金收款</a></li>
				<li><a href="#"  class="a_hover">>调整单详情</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="page-content">
				<input type="hidden" id="receiveId" name="receiveId" value="${receiveId}"/>
				<input type="hidden" id="orderTypeFlag" name="orderTypeFlag" value="${orderTypeFlag}"/>
				<h4 class="border-bottom pdb10">
					<strong>调整单详情</strong>
					<a href="${ctx}/storeReceive?searchParam=1&orderType=21402" style="float: right;"><img src="${ctx}/meta/images/crm/close_page.png"></a>
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
							<td class="talabel">经纪公司：</td>
							<td>${companyName}</td>
							<td class="talabel">创建时间：</td>
							<td>${storeReceiveinfo.storeReceive.dateCreate}</td>
							<td class="talabel">审核状态：</td>
							<td>${storeReceiveinfo.storeReceive.approveStatusNm}</td>
						</tr>
						<tr>
							<td class="talabel" style="vertical-align: text-top;">审核通过时间：</td>
							<td style="vertical-align: text-top;">${sdk:ymd2(storeReceiveinfo.storeReceive.approvePassDate)}</td>
							<td class="talabel" style="vertical-align: text-top;">调整单编号：</td>
							<td style="vertical-align: text-top;">${storeReceiveinfo.storeReceive.oaNo}</td>
							<td class="talabel" style="vertical-align: text-top;">核算主体：</td>
							<td style="vertical-align: text-top;">
								<c:if test="${storeReceiveinfo.storeReceive.accountProjectCode ne null}">
									${storeReceiveinfo.storeReceive.accountProject}/${storeReceiveinfo.storeReceive.accountProjectCode}
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="talabel" style="vertical-align: text-top;">调整单类型：</td>
							<td>${storeReceiveinfo.storeReceive.feeTypeNm}</td>
						</tr>
					</table>
				<div class="page-content" style="height: 60px;" >
                    <h4  style="margin-right: 15px;margin-left:15px;font-size:16px;height: 45px;">
                        <strong>门店信息</strong>
                    </h4>
                </div>
                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered" style="margin-right: 15px;margin-left:15px;width:1140px">
                    <tr>
                    	<th style="width:150px;">收入类别编码</th>
                    	<th style="width:150px;">收入类别</th>
                    	<th style="width:130px;">供应商编码</th>
                    	<th style="width:220px;">供应商名称</th>
                    	<th style="width:100px;">收款金额</th>
                        <th style="width:130px;">门店编号</th>
                        <th style="width:220px;">门店名称</th>
                    </tr>
                    <c:forEach items="${storeReceiveinfo.storeDetails}" var="storelist">
                        <tr>
                            <input type="hidden" name="storeId" id="storeId" value="${storelist.storeId }">
                            <td>${storelist.payoutId}</td>
                            <td>${storelist.payoutName}</td>
                            <td>${storelist.providerCode}</td>
                            <td>${storelist.providerName}</td>
                            <td><fmt:formatNumber type="number" value="${storelist.amount}" pattern="#,##0.00#" maxFractionDigits="2"/></td>
                            <td><a href="javascript:void(0)"
                                   onclick="contractGotoStore('${ctx}/store/${storelist.storeId}','/store')">${storelist.storeNo}</a>
                            </td>
                            <td>${storelist.name}</td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="page-content" style="height: 60px;margin-left:15px;">
                   <div>本次合计金额 &nbsp;&nbsp;￥ 0.00</div>
                </div>
			</div>
		</div>
	</div> 
</body>
</html>
