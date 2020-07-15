<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
</head>

<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container theme-hipage ng-scope">
		<div class="crumbs">
			<ul style="margin-right:150px;">
				<li><a href="#"  class="a_hover">联动管理</a></li>
				<li><a href="#"  class="a_hover">>佣金管理</a></li>
				<li><a href="#"  class="a_hover">>请款单详情</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="page-content">
				<input type="hidden" id="receiveId" name="receiveId" value="${receiveId}"/>
				<h4 class="border-bottom pdb10">
					<strong>请款单详情</strong>
					<a href="${ctx}/cashBill?searchParam=1" class="btn btn-primary">返回</a>
				</h4>
				<table class="table-sammary">
						<col style="width:135px;">
						<col style="width:320px">
						<col style="width:130px;">
						<col style="width:auto">
						<tr>
							<td class="talabel">请款单编号：</td>
							<td>${cashBillInfo.cashBillNo}</td>
							<td class="talabel">项目名称：</td>
							<td>
								${cashBillInfo.estateNm}
									&nbsp;
								${cashBillInfo.projectNo}
							</td>
						</tr>
						<tr>
							<td class="talabel">经纪公司：</td>
							<td>
								${cashBillInfo.companyName}
									&nbsp;
								${cashBillInfo.companyNo}
							</td>
							<td class="talabel">不含税请款金额：</td>
							
							<td>
								<c:choose>
									<c:when test="${empty cashBillInfo.amountNoTax}">
										-
									</c:when>
									<c:otherwise>
										￥<fmt:formatNumber type="number" value="${cashBillInfo.amountNoTax}" pattern="0.00" maxFractionDigits="2"/>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							
							<td class="talabel">税额：</td>
							<td>
								<c:choose>
									<c:when test="${empty cashBillInfo.amountTax}">
										-
									</c:when>
									<c:otherwise>
										￥<fmt:formatNumber type="number" value="${cashBillInfo.amountTax}" pattern="0.00" maxFractionDigits="2"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td class="talabel">本次请款金额：</td>
							<td>
								<c:choose>
									<c:when test="${empty cashBillInfo.amountTotal}">
										-
									</c:when>
									<c:otherwise>
										￥<fmt:formatNumber type="number" value="${cashBillInfo.amountTotal}" pattern="0.00" maxFractionDigits="2"/>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td class="talabel">申请人：</td>
							<td>${cashBillInfo.userName}</td>
							<td class="talabel">申请日期：</td>
							<td>
								${sdk:ymd2(cashBillInfo.applyTime)}
							</td>
						</tr>
						<tr>
							<td class="talabel">提交OA状态：</td>
								<c:choose>
									<c:when test="${cashBillInfo.submitOaStatus eq '21204' || cashBillInfo.submitOAStatus eq '21204'}">
										<td style="color:red;">${cashBillInfo.submitOaStatusNm}</td>
									</c:when>
									<c:otherwise>
										<td>${cashBillInfo.submitOaStatusNm}</td>
									</c:otherwise>
								</c:choose>
						</tr>
						
					</table>
				<div class="page-content" style="height: 60px;" >
                    <h4  style="margin-right: 15px;margin-left:15px;font-size:16px;height: 45px;">
                        <strong>请款房源列表</strong>
                    </h4>
                </div>
                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered"  style="margin-right: 15px;margin-left:15px;width:1140px">
                    <tr>
                    	<th style="width:60px;">序号</th>
                        <th style="width:130px;">报备编号</th>
                        <th style="width:80px;">楼市号</th>
                        <th style="width:80px;">客户姓名</th>
                        <th style="width:100px;">销售面积</th>
                        <th style="width:100px;">大定总价</th>
                        <th style="width:100px;">成销总价</th>
                    </tr>
                    <c:forEach items="${cashBillInfo.reportDetails}" var="reportList" varStatus="index">
                        <tr>
                        	<td>${index.count}</td>
                            <td><%-- <a href="javascript:void(0)"
                                   onclick="contractGotoStore('${ctx}/store/${storelist.storeId}','/store')">${storelist.storeNo}</a> --%>
                              ${reportList.reportNo}
                            </td>
                            <td>${reportList.buildingNo}</td>
                            <td>${reportList.customerNm}</td>
                            <td><fmt:formatNumber type="number" value="${reportList.area}" pattern="0.00" maxFractionDigits="2"/></td>
                            <td><fmt:formatNumber type="number" value="${reportList.roughAmount}" pattern="0.00" maxFractionDigits="2"/></td>
                            <td><fmt:formatNumber type="number" value="${reportList.dealAmount}" pattern="0.00" maxFractionDigits="2"/></td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="page-content" style="height: 60px;margin-left:15px;">
                   <div id="amountSum" style="margin-left:580px;">
                   		&nbsp;&nbsp;<strong id="areaSum">
                   			销售面积总计&nbsp;:<fmt:formatNumber type="number" value="${cashBillInfo.areaSum}" pattern="0.00" maxFractionDigits="2"/>&nbsp;㎡
                   		</strong>
                   		&nbsp;&nbsp;<strong id="roughAmountSum">
                   			大定总价总计&nbsp;:￥<fmt:formatNumber type="number" value="${cashBillInfo.roughAmountSum}" pattern="0.00" maxFractionDigits="2"/>
                   		</strong>
                   		&nbsp;&nbsp;<strong id="dealAmountSum">
                   			成销总价总计&nbsp;:￥<fmt:formatNumber type="number" value="${cashBillInfo.dealAmountSum}" pattern="0.00" maxFractionDigits="2"/>
                   		</strong>
                   </div>
                </div>
			</div>
		</div>
	</div> 
</body>
</html>
