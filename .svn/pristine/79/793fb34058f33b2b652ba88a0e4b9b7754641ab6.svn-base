<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/estateEdit.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/estateCommonNew.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/estateAudit.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>

<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/bootstrap-multiselect.css">
<script type="text/javascript" src="${ctx}/meta/js/common/bootstrap-multiselect.js?_v=${vs}"></script>
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
				<li><a href="#"  class="a_hover">>项目管理</a></li>
				<li><a href="#"  class="a_hover">>项目详情</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="page-content">
				<!-- 楼盘基本信息和详情 -->
				<h4 class="border-bottom pdb10">
					<strong>项目详情</strong>
					<c:if test="${otherReportType eq '1'}">
						<a href="${ctx}/qtProject?searchParam=1" class="btn btn-primary">返回</a>
					</c:if>
					<c:if test="${empty otherReportType}">
						<a href="${ctx}/estate?searchParam=1" class="btn btn-primary">返回</a>
					</c:if>
					<c:if test="${type eq 'detail' and estateInfo.estate.auditStatus eq 12903}">
						<shiro:hasPermission name="/estate:CHANGE_DETAIL">
							<a href="javascript:EstateType.popupDetail(${estateInfo.estate.id})" class="btn btn-primary" style="margin-right:12px">项目信息变更</a>
						</shiro:hasPermission>
					</c:if>
					<c:if test="${type eq 'detail'}">
						<shiro:hasPermission name="/estate:CHANGE_City">
							<a href="javascript:EstateType.popupReleaseChangeCity(${estateInfo.estate.id},'${estateInfo.estate.estateId}','${estateInfo.estate.cityNo}','${estateInfo.estate.cityNm}')"
								 class="btn btn-primary" style="margin-right:12px">项目发布城市变更</a>
						</shiro:hasPermission>
					</c:if>
					<c:if test="${type eq 'audit'}">
						<form id="estateAuditYesForm">
							<input type="hidden" name="auditStatus" value="12903"> <input type="hidden" name="id"
								value="${estateInfo.estate.id}">
						</form>
						<a href="javascript:auditNo('${estateInfo.estate.id}')" class="btn btn-primary" style="margin-right:12px">驳回</a>
						<a href="javascript:auditYes('${estateInfo.estate.id}')" class="btn btn-primary" style="margin-right:12px">通过</a>
					</c:if>
				</h4>
				<div style="margin-bottom:20px;">
					<p>
						<strong>基本信息</strong>
					</p>
					<table class="table-sammary">
						<col style="width:135px;">
						<col style="width:auto">
						<col style="width:130px;">
						<col style="width:auto">
						<col style="width:145px;">
						<col style="width:auto">
						<tr>
							<td class="talabel">项目编号：</td>
							<td>${estateInfo.estate.projectNo}</td>
							<td class="talabel">项目状态：</td>
							<td>
							   <c:if test="${20301 eq estateInfo.estate.projectStatus}">
							          跟单
							   </c:if>
								<c:if test="${20306 eq estateInfo.estate.projectStatus}">
									立项
								</c:if>
							   <c:if test="${20302 eq estateInfo.estate.projectStatus}">
							           签约
							   </c:if>
							   <c:if test="${20303 eq estateInfo.estate.projectStatus}">
							         结案
							   </c:if>
							   <c:if test="${20304 eq estateInfo.estate.projectStatus}">
							           取消跟单
							   </c:if>
							   <c:if test="${20305 eq estateInfo.estate.projectStatus}">
                                                                                
                               </c:if>
							</td>
							<td class="talabel">楼盘名称：</td>
							<td>${estateInfo.estate.estateNm}</td>
						</tr>
						<tr>
							<td class="talabel">备案名：</td>
							<td>${estateInfo.estate.recordName}</td>
							<td class="talabel">推广名：</td>
							<td>${estateInfo.estate.promotionName}</td>
							<td class="talabel">签约名：</td>
							<td>${estateInfo.estate.signName}</td>
						</tr>
						<tr>
							<td class="talabel">项目审核状态：</td>
							<td>${estateInfo.estate.auditStatusStr}</td>
							<td class="talabel">项目发布状态：</td>
							<td>${estateInfo.estate.releaseStatusStr}</td>
							<td class="talabel">项目归属城市：</td>
							<td>${estateInfo.estate.cityNm}</td>
						</tr>
						<tr>
							<td class="talabel" style="vertical-align: text-top;">业绩归属项目部：</td>
							<td style="vertical-align: text-top;">${estateInfo.estate.projectDepartment}</td>
							<td class="talabel" style="vertical-align: text-top;">项目地址：</td>
							<td colspan=3>
								<c:if test="${estateInfo.estate.estatePosition eq 0}">
									${estateInfo.estate.realityCityNm}${estateInfo.estate.districtNm}${estateInfo.estate.areaNm}${estateInfo.estate.address}
								</c:if>
								<c:if test="${estateInfo.estate.estatePosition eq 1}">
									${estateInfo.estate.countryNm}${estateInfo.estate.address}
								</c:if>
							</td>
						</tr>
						<tr>
						    <td class="talabel">业务模式：</td>
							<td>
								<c:if test="${25501 eq estateInfo.estate.businessModel}">标准</c:if>
								<c:if test="${25502 eq estateInfo.estate.businessModel}">非标准（保证金）</c:if>
								<c:if test="${25503 eq estateInfo.estate.businessModel}">非标准（诚意金）</c:if>
								<c:if test="${25504 eq estateInfo.estate.businessModel}">非标准（包销）</c:if>
								<c:if test="${25505 eq estateInfo.estate.businessModel}">非标准（前佣）</c:if>
								<c:if test="${25506 eq estateInfo.estate.businessModel}">非标准（全民经纪）</c:if>
								<c:if test="${25507 eq estateInfo.estate.businessModel}">非标准（其他）</c:if>
								<c:if test="${25508 eq estateInfo.estate.businessModel}">纯垫佣</c:if>
							</td>
							<td class="talabel">销售状态：</td>
							<td>${estateInfo.estate.salesStatusStr}</td>
							<td class="talabel">总价段：</td>
							<td>${estateInfo.estate.salePriceUnitMin}-${estateInfo.estate.salePriceUnitMax}万元/套</td>
						</tr>
						<tr>
						    <td class="talabel">合作方类型：</td>
							<td>${estateInfo.estate.partnerStr}</td>
							<td class="talabel">合作方名称：</td>
							<td>${estateInfo.estate.partnerNm}</td>
							<td class="talabel">是否可垫佣甲方：</td>
							<td><c:if test="${ 1 eq estateInfo.estate.custodyFlg}">是</c:if><c:if test="${null eq estateInfo.estate.custodyFlg or 0 eq estateInfo.estate.custodyFlg}">否</c:if></td>
						</tr>
						<tr>
							<c:if test="${null eq estateInfo.estate.custodyFlg or 0 eq estateInfo.estate.custodyFlg}">
								<td class="talabel">合作方对接人：</td>
								<td>${estateInfo.estate.partnerContactNm}</td>
								<td class="talabel">合作方对接人电话：</td>
								<td>${estateInfo.estate.partnerContactTel}</td>
							</c:if>
							<c:if test="${1 eq estateInfo.estate.custodyFlg}">
								<td class="talabel">垫佣甲方简称：</td>
								<td>${estateInfo.estate.mattressNailName}</td>
								<td class="talabel">合作方对接人：</td>
								<td>${estateInfo.estate.partnerContactNm}</td>
								<td class="talabel">合作方对接人电话：</td>
								<td>${estateInfo.estate.partnerContactTel}</td>
							</c:if>
						</tr>
						<tr>
						    <td class="talabel">是否独家：</td>
							<td>${estateInfo.estate.particularFlagStr}</td>
							<td class="talabel">是否直签：</td>
							<td>${estateInfo.estate.directSignFlagStr}</td>
						</tr>
						<tr>
						    <td class="talabel">我方负责人及电话：</td>
                            <td>${estateInfo.estate.sceneDeptNm}${estateInfo.estate.sceneEmpName}&nbsp;&nbsp;${estateInfo.estate.empTel}</td>
						    <td class="talabel">合作期：</td>
							<td>${sdk:ymd2(estateInfo.estate.cooperationDtStart)}至${sdk:ymd2(estateInfo.estate.cooperationDtEnd)}</td>
							<td class="talabel">是否垫佣：</td>
							<td><c:if test="${'true' eq estateInfo.estate.yjDy}">是</c:if>
								<c:if test="${'false' eq estateInfo.estate.yjDy}">否</c:if></td>
						</tr>
						<c:if test="${'true' eq estateInfo.estate.yjDy}">
							<tr>
								<td class="talabel">垫佣金额：</td>
								<td>${sdk:mf2(estateInfo.estate.dyAmount)}万元</td>
								<td class="talabel">垫佣周期：</td>
								<td>${sdk:ymd2(estateInfo.estate.dyStartDate)}至 ${sdk:ymd2(estateInfo.estate.dyEndDate)}</td>
							</tr>
						</c:if>
						<tr>
							<td class="talabel">预计当年大定金额：</td>
							<td>${sdk:mf2(estateInfo.estate.subscribedThisYear)}万元</td>
							<td class="talabel">预计明年大定金额：</td>
							<td>${sdk:mf2(estateInfo.estate.subscribedNextYear)}万元</td>
						</tr>
						<tr>
							<td class="talabel">项目发布城市：</td>
							<td>
								<c:if test="${1 eq releaseCityflag}">
									${releaseCityStr}
								</c:if>
								<c:if test="${0 eq releaseCityflag}">
									${estateInfo.estate.cityNm}
								</c:if>
							</td>
						</tr>
					</table>
				</div>
				<div class="btn-group btn-table-group">
					<a href="#Housingdetails" data-toggle="tab" class="btn btn-default active">楼盘详情</a>
					<a href="#Linkagerule" data-toggle="tab" class="btn btn-default">联动规则</a>
					<a href="#ChangeHistory" data-toggle="tab" class="btn btn-default">项目日志</a>
				</div>
				<div class="tab-content" style="border:1px solid #ddd; border-top:none; min-height:100px; _height:100px; padding:10px; margin-bottom:24px;">
					<div class="tab-pane active" id="Housingdetails">
						<table class="table-sammary">
							<col style="width:135px;">
							<col style="width:auto">
							<col style="width:130px;">
							<col style="width:auto">
							<col style="width:145px;">
							<col style="width:auto">
							<tr>
								<td class="talabel">开发商全称：</td>
								<td>${estateInfo.estate.developerName}</td>
								<td class="talabel">开发商简称：</td>
								<td>${estateInfo.estate.devCompany}</td>
								<td class="talabel">是否大客户：</td>
								<td>${estateInfo.estate.bigCustomerFlagStr}</td>
							</tr>
							<tr>
								<td class="talabel">开发商对接人：</td>
								<td>${estateInfo.estate.devCompanyBroker}</td>
								<td class="talabel">对接人电话：</td>
								<td>${estateInfo.estate.devCompanyBrokerTel}</td>
								<td class="talabel">物业类型：</td>
								<td>${estateInfo.estate.mgtKbnStr}</td>
							</tr>
							<tr>
								<td class="talabel">产权年限：</td>
								<td>${estateInfo.estate.ownYearKbnStr}</td>
							</tr>
						</table>
					</div>
					<div class="tab-pane" id="Linkagerule">
						<div style="margin-top: 12px;font-weight: 700;">合同</div>
						<table  width="100%" style="border:solid 1px #adadad;">
							<col style="width:10%;border-right:#adadad 1px solid">
							<col style="width:20%;border-right:#adadad 1px solid">
							<col style="width:70%;border-right:#adadad 1px solid">
							<tr style="height:28px;border-bottom:#adadad 1px solid">
								<td align="center">序号</td>
								<td align="center">合同周期</td>
								<td align="center">合同概要</td>
							</tr>
							<c:forEach items="${estateInfo.esateNowAndHistory}" var="estatehistory" varStatus="index">
								<!-- style="height:28px;border-bottom:#adadad 1px solid" -->
								<tr style="height:28px;border-bottom:#adadad 1px solid">
									<td align="center">${index.count}</td>
									<td align="center">
									${sdk:ymd2(estatehistory.cooperationDtStart)}至 ${sdk:ymd2(estatehistory.cooperationDtEnd)}</td>
									<td align="left"> 
										&nbsp;&nbsp;收入标的&nbsp;：&nbsp;${estatehistory.incomeSubject}</br>
										&nbsp;&nbsp;收入结算条件&nbsp;：&nbsp;${estatehistory.commissionConditionVal}</br>
										&nbsp;&nbsp;收入结算描述&nbsp;：&nbsp;${estatehistory.commissionMemo}</br>
										&nbsp;&nbsp;返佣标准&nbsp;：&nbsp;${estatehistory.rtnCommission}</br>
										&nbsp;&nbsp;返佣结算条件&nbsp;：&nbsp;${estatehistory.rtnCommissionMemo}</br>
									</td>
								</tr>
							</c:forEach>
						</table>
						<c:if test="${empty estateInfo.esateNowAndHistory}"><div class="nodata"><i class="icon-hdd"></i><p>暂无合同数据...</p></div></c:if>
						<c:if test="${not empty estateInfo.estateDyRecords}">
						<div style="margin-top: 12px;font-weight: 700;">垫佣</div>
							<table  width="100%" style="border:solid 1px #adadad;">
								<col style="width:10%;border-right:#adadad 1px solid">
								<col style="width:20%;border-right:#adadad 1px solid">
								<col style="width:70%;border-right:#adadad 1px solid">
								<tr style="height:28px;border-bottom:#adadad 1px solid">
									<td align="center">序号</td>
									<td align="center">垫佣周期</td>
									<td align="center">垫佣金额（万元）</td>
								</tr>
								<c:forEach items="${estateInfo.estateDyRecords}" var="item" varStatus="index">
									<!-- style="height:28px;border-bottom:#adadad 1px solid" -->
									<tr style="height:28px;border-bottom:#adadad 1px solid">
										<td align="center">${index.count}</td>
										<td align="center">
												${sdk:ymd2(item.dyStartDate)}至 ${sdk:ymd2(item.dyEndDate)}</td>
										<td align="center">${sdk:mf(item.dyAmount)}</td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
					</div>
					<div class="tab-pane" id="ChangeHistory">
						<table style="width:100%;border-left:#adadad 1px solid;border-top:#adadad 1px solid">
							<col style="width:10%;border-right:#adadad 1px solid">
							<col style="width:15%;border-right:#adadad 1px solid">
							<col style="width:40%;border-right:#adadad 1px solid">
							<col style="width:15%;border-right:#adadad 1px solid">
							<col style="width:30%;border-right:#adadad 1px solid">
							<tr style="height:28px;border-bottom:#adadad 1px solid">
								<td align="center">序号</td>
								<td align="center">类型</td>
								<td align="center">详细描述</td>
								<td align="center">操作人（工号）</td>
								<td align="center">操作时间</td>
							</tr>
							<c:forEach items="${estateInfo.estateChangeDetails}" var="estateChange">
								<tr style="height:28px;border-bottom:#adadad 1px solid">
									<td align="center">${estateChange.orderId}</td>
									<td align="center">${estateChange.changeName}</td>
									<td align="left" style="padding-left: 5px;">${estateChange.changeDetail}</td>
									<td align="center">${estateChange.operatorName}<c:if test="${estateChange.operatorCode ne 0}">（${estateChange.operatorCode}）</c:if></td>
									<td align="center">${estateChange.changeDate}</td>
								</tr>
							</c:forEach>
						</table>
						<c:if test="${empty estateInfo.estateChangeDetails}"><div class="nodata"><i class="icon-hdd"></i><p>暂无数据...</p></div></c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$(document).on("click",".btn-table-group .btn",function(){
				$(this).addClass("active").siblings().removeClass("active");
			});
		});
	</script>

</body>
</html>
