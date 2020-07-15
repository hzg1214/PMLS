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
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<style type="text/css">
.jc{
font-weight:bold;
}


</style>
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
				<p>
					<strong>基本信息</strong>
				</p>
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
								(${cashBillInfo.projectNo})
							</td>
						</tr>
						<tr>
							<td class="talabel">经纪公司：</td>
							<td>
								<c:choose> 
								  <c:when test="${!empty cashBillInfo.companyNo}">   
										${cashBillInfo.companyName}&nbsp;(${cashBillInfo.companyNo})
								  </c:when> 
								  <c:otherwise>   
								  </c:otherwise> 
								</c:choose> 
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
									<c:when test="${empty cashBillInfo.taxAmountTotal}">
										-
									</c:when>
									<c:otherwise>
										￥<fmt:formatNumber type="number" value="${cashBillInfo.taxAmountTotal}" pattern="0.00" maxFractionDigits="2"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td class="talabel">本次请款金额：</td>
							<td>
								<c:choose>
									<c:when test="${empty cashBillInfo.requestAmountTotal}">
										-
									</c:when>
									<c:otherwise>
										￥<fmt:formatNumber type="number" value="${cashBillInfo.requestAmountTotal}" pattern="0.00" maxFractionDigits="2"/>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td class="talabel">核算主体：</td>
							<td>${cashBillInfo.accountProjectNo}_${cashBillInfo.accountProject}</td>
							<td class="talabel">框架协议编号：</td>
							<td>${cashBillInfo.frameOaNo}</td>
						</tr>
						<tr>
							<td class="talabel">供应商：</td>
							<td>
								<c:choose> 
								  <c:when test="${!empty cashBillInfo.vendorCode}">   
										${cashBillInfo.vendorName}&nbsp;(${cashBillInfo.vendorCode})
								  </c:when> 
								  <c:otherwise>   
								  </c:otherwise> 
								</c:choose> 
							</td>
							<td class="talabel">收款银行：</td>
							<td>${cashBillInfo.receiveBankName}</td>
						</tr>
						<tr>
							<td class="talabel">银行账号：</td>
							<td>${cashBillInfo.receiveBankAccountCardCode}</td>
							<td class="talabel">收款户名：</td>
							<td>${cashBillInfo.receiveBankAccountName}</td>
						</tr>
						
						<tr>
							<td class="talabel">收款省市：</td>
							<td>${cashBillInfo.receiveBankProvinceName}&nbsp;${cashBillInfo.receiveBankCityName}</td>
							<td class="talabel">入账日期：</td>
							<td>
								${sdk:ymd2(cashBillInfo.recordDate)}
							</td>
						</tr>
						<tr>
							<td class="talabel">预计付款日期：</td>
							<td>${sdk:ymd2(cashBillInfo.predictPayTime)}</td>
							<td class="talabel">付款方式：</td>
							<td>${cashBillInfo.payTypeNm}</td>
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
							<c:if test="${not empty cashBillInfo.oaNo}">
								<td class="talabel">OA单编号：</td>
								<td>${cashBillInfo.oaNo}</td>
							</c:if>
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
							<td class="talabel">审核状态：</td>
							<td>${cashBillInfo.approveStatusNm}</td>
							<td class="talabel">审核通过日期：</td>
							<td>
								${sdk:ymd2(cashBillInfo.approveTime)}
							</td>
						</tr>
					</table>
				<div class="page-content" style="height: 60px;" >
                    <h4  style="margin-right: 15px;margin-left:8px;font-size:14px;height: 45px;">
                        <strong>请款房源列表</strong>
                    </h4>
                </div>
                <div id="divReport" style="height: auto;width: 100%">
	                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered"  style=" padding:0px; margin-right: 15px;margin-left:15px;width:1800px;font-size: 12px;">
	                    <tr style="height:25px">
	                    	<td rowspan="2"  style="padding:0px;width:60px; text-align:center;vertical-align:middle;">序号</td>
	                        <td rowspan="2"  style="padding:0px;width:145px; text-align:center;vertical-align:middle;">报备编号</td>
	                        <td rowspan="2"  style="padding:0px;width:100px; text-align:center;vertical-align:middle;">楼室号</td>
	                        <td rowspan="2"  style="padding:0px;width:80px; text-align:center;vertical-align:middle;">客户姓名</td>
	                        <td rowspan="2"  style="padding:0px;width:100px; text-align:center;vertical-align:middle;">销售面积</td>
	                        <td rowspan="2"  style="padding:0px;width:100px; text-align:center;vertical-align:middle;">大定总价</td>
	                        <td rowspan="2"  style="padding:0px;width:100px; text-align:center;vertical-align:middle;">成销总价</td>
	                    	<td colspan="6"  style="padding:0px; text-align:center;vertical-align:middle;">累计税前</td>
							<td rowspan="2"  style="padding:0px; text-align:center;vertical-align:middle;">含税请款金额</td>
							<td rowspan="2"  style="padding:0px; text-align:center;vertical-align:middle;">税额</td>
							<td rowspan="2"  style="padding:0px; text-align:center;vertical-align:middle;">请款类别</td>
							<td rowspan="2"  style="padding:0px; text-align:center;vertical-align:middle;">考核主体</td>
							<td rowspan="2"  style="padding:0px; text-align:center;vertical-align:middle;">说明</td>
						</tr>
						<tr style="height:25px">
							<td  align="center">应计收入</td>
							<td  align="center">应计返佣</td>
							<td  align="center">应计垫佣</td>
							<td  align="center">实收收入</td>
							<td  align="center">实际返佣</td>
							<td  align="center">实际垫佣</td>
						</tr>
	                    <c:forEach items="${cashBillInfo.reportDetails}" var="reportList" varStatus="index">
	                        <tr style="height:28px">
	                        	<td style="padding:0px; text-align:center;vertical-align:middle;">${index.count}</td>
	                            <td style="padding:0px; text-align:center;vertical-align:middle;"><%-- <a href="javascript:void(0)"
	                                   onclick="contractGotoStore('${ctx}/store/${storelist.storeId}','/store')">${storelist.storeNo}</a> --%>
	                              ${reportList.reportNo}
	                            </td>
	                            <td style="padding:0px; text-align:center;vertical-align:middle;" title="${reportList.buildingNo}" class="text-overflow">${reportList.buildingNo}</td>
	                            <td style="padding:0px; text-align:center;vertical-align:middle;" title="${reportList.customerNm}" class="text-overflow">${reportList.customerNm}</td>
	                            <td style="padding:0px; text-align:center;vertical-align:middle;"><fmt:formatNumber type="number" value="${reportList.area}" pattern="0.00" maxFractionDigits="2"/></td>
	                            <td style="padding:0px; text-align:center;vertical-align:middle;"><fmt:formatNumber type="number" value="${reportList.roughAmount}" pattern="0.00" maxFractionDigits="2"/></td>
	                            <td style="padding:0px; text-align:center;vertical-align:middle;"><fmt:formatNumber type="number" value="${reportList.dealAmount}" pattern="0.00" maxFractionDigits="2"/></td>
	                            
	                            <td style="padding:0px; text-align:center;vertical-align:middle;"><fmt:formatNumber type="number" value="${reportList.sqYjsrAmount}" pattern="0.00" maxFractionDigits="2"/></td>
	                            <td style="padding:0px; text-align:center;vertical-align:middle;"><fmt:formatNumber type="number" value="${reportList.sqYjfyAmount}" pattern="0.00" maxFractionDigits="2"/></td>
	                            <td style="padding:0px; text-align:center;vertical-align:middle;"><fmt:formatNumber type="number" value="${reportList.sqYjdyAmount}" pattern="0.00" maxFractionDigits="2"/></td>
	                            <td style="padding:0px; text-align:center;vertical-align:middle;"><fmt:formatNumber type="number" value="${reportList.sqSjsrAmount}" pattern="0.00" maxFractionDigits="2"/></td>
	                            <td style="padding:0px; text-align:center;vertical-align:middle;"><fmt:formatNumber type="number" value="${reportList.sqSjfyAmount}" pattern="0.00" maxFractionDigits="2"/></td>
	                            <td style="padding:0px; text-align:center;vertical-align:middle;"><fmt:formatNumber type="number" value="${reportList.sqSjdyAmount}" pattern="0.00" maxFractionDigits="2"/></td>
	                            <td style="padding:0px; text-align:center;vertical-align:middle;"><fmt:formatNumber type="number" value="${reportList.requestAmount}" pattern="0.00" maxFractionDigits="2"/></td>
	                            <td style="padding:0px; text-align:center;vertical-align:middle;"><fmt:formatNumber type="number" value="${reportList.taxAmount}" pattern="0.00" maxFractionDigits="2"/></td>
	                            <td style="padding:0px; text-align:center;vertical-align:middle;">
	                            	<c:if test="${reportList.requestType ne null && reportList.requestType==1}">返佣</c:if>
									<c:if test="${reportList.requestType ne null && reportList.requestType==2}">垫佣</c:if>
	                            </td>
	                            <td title="${reportList.checkBodyName}" class="text-overflow">${reportList.checkBodyName}</td>
	                            <td title="${reportList.memo}" class="text-overflow">${reportList.memo}</td>
	                        </tr>
	                    </c:forEach>
	                    <tr name="total" style="height:28px">
							<td style="border-right: 1px solid #fff;"></td>
			                <td style="text-align: right;border-right: 1px solid #fff;font-weight:bold">合计</td>
			                <td style="border-right: 1px solid #fff;"></td>
			                <td></td>
							<td class="jc" ><fmt:formatNumber type="number" value="${cashBillInfo.areaTotal}" pattern="0.00" maxFractionDigits="2"/></td> 		
							<td class="jc" ><fmt:formatNumber type="number" value="${cashBillInfo.roughAmountTotal}" pattern="0.00" maxFractionDigits="2"/></td> 		
							<td class="jc" ><fmt:formatNumber type="number" value="${cashBillInfo.dealAmountTotal}" pattern="0.00" maxFractionDigits="2"/></td> 		
							<td class="jc" ><fmt:formatNumber type="number" value="${cashBillInfo.sqYjsrAmountTotal}" pattern="0.00" maxFractionDigits="2"/></td> 		
							<td class="jc" ><fmt:formatNumber type="number" value="${cashBillInfo.sqYjfyAmountTotal}" pattern="0.00" maxFractionDigits="2"/></td> 		
							<td class="jc" ><fmt:formatNumber type="number" value="${cashBillInfo.sqYjdyAmountTotal}" pattern="0.00" maxFractionDigits="2"/></td> 		
							<td class="jc" ><fmt:formatNumber type="number" value="${cashBillInfo.sqSjsrAmountTotal}" pattern="0.00" maxFractionDigits="2"/></td> 		
							<td class="jc" ><fmt:formatNumber type="number" value="${cashBillInfo.sqSjfyAmountTotal}" pattern="0.00" maxFractionDigits="2"/></td> 		
							<td class="jc" ><fmt:formatNumber type="number" value="${cashBillInfo.sqSjdyAmountTotal}" pattern="0.00" maxFractionDigits="2"/></td> 		
							<td class="jc" ><fmt:formatNumber type="number" value="${cashBillInfo.requestAmountTotal}" pattern="0.00" maxFractionDigits="2"/></td> 		
							<td class="jc" ><fmt:formatNumber type="number" value="${cashBillInfo.taxAmountTotal}" pattern="0.00" maxFractionDigits="2"/></td> 		
							<td></td>
							<td></td>
						</tr>
	                </table>
                </div>
                <c:if test="${not empty cashBillInfo.cashBillFileList }">
	                <div class="page-content">
	                    <h4 style="font-size:14px;">
	                        <strong><i>*</i>成销确认书/佣金结算资料</strong>
	                    </h4>
	                </div>
	            </c:if>
	            <table class="table-sammary" name="Viewerbox">
	                <col style="width:145px;">
	                <tr>
	                    <td colspan="2">
	                        <div class="pdl10 pdb0">
	                            <div class="thumb-xs-box">
	                                <c:if test="${not empty cashBillInfo.cashBillFileList }">
	                                    <c:set var="fileSize" value="0"/>
	                                    <c:forEach items="${cashBillInfo.cashBillFileList}" var="list" varStatus="status">
	
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
			</div>
		</div>
	</div> 
</body>
<script>
    $(function () {
         var width = $("#divReport").width;
         var height = $("#divReport").height();
        

         $("#relateStoreTableId td").css("padding","0px");
         $("#relateStoreTableId td").css("vertical-align","middle"); 
         $("#relateStoreTableId").css("font-size","12px");
         //$("#relateStoreTableId_tableColumnClone").css("font-size","12px");
         
         FixTable("relateStoreTableId", 4, 1160, height+40);
         
        
         //$("#relateStoreTableId_tableColumnClone td").css("padding","0px");       
         //$("#relateStoreTableId_tableColumnClone td").css("vertical-align","middle");
         
    });
</script>
</html>
