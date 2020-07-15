<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/WEB-INF/page/common/meta.jsp"%>
	<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>

	<script type="text/javascript" src="${ctx}/meta/js/otherReport/qtReportDetail.js?_v=${vs}"></script>
	<style>
		.tran_step {
			display: table;
			padding: 0;
		}
		.tran_step>li {
			display: table-cell;
			width: 1%;
			text-align: center;
			vertical-align: middle;
			height: 46px;
			line-height: 46px;
			color: #fff;
			position: relative;
			background-color: #c2c6c7;
		}
		.fast-filing-step .tran_step>li.active {
			background-color: #337ab7;
		}
		.tran_step>li.active {
			background-color: #337ab7;
		}
		.tran_step > li:first-child {
			border-radius: 3px 0 0 3px;
		}
		.fast-filing-step .tran_step>li {
			height: 45px;
		}

		.tran_step a {
			color: #fff;
		}
		.step-item>a>span {
			display: block;
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
		}
		.step-item>a>span:before {
			content: attr(data-name);
			top: 65%;
		}
		.step-item>a>span:before, .step-item>a>span:after {
			color: #a1a1a1;
			font-size: 13px;
			position: absolute;
			left: 20px;
			top: 37px;
			left: 0;
			right: 0;
		}
		.step-item>a>span:after {
			content: attr(data-date);
			top: 70%;
		}
		.tran_step>li + li:before {
			content: "\20";
			display: block;
			width: 33px;
			height: 33px;
			border-width: 2px 2px 0 0;
			border-color: #fff;
			border-style: solid;
			position: absolute;
			top: 7px;
			left: -17px;
			-webkit-transform: rotate(45deg);
			transform: rotate(45deg);
			background-color:#c2c6c7;
		}
		.fast-filing-step .tran_step>li.active + li:before {
			background-color: #337ab7;
		}
		.tran_step>li.active + li:before {
			background-color: #337ab7;
		}
	</style>
</head>

<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
	<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
</jsp:include>


<form id = "ReportViewForm" >
	<input type = "hidden"  id = "id"   name = "id" value = "${reportInfo.report.id}"/>
	<input type = "hidden"  id = "customerFromValue"   name = "customerFromValue" value = "${reportInfo.report.customerFrom}"/>
	<input type = "hidden"  id = "estateId"   name = "estateId" value = "${estateId}"/>
	<div class="container theme-hipage ng-scope" role="main">
		<div class="crumbs">
			<ul style="margin-right:150px;">
				<li><a href="#"  class="a_hover">联动管理</a></li>
				<li><a href="#"  class="a_hover">>案场管理</a></li>
				<li><a href="#"  class="a_hover">>报备</a></li>
				<li><a href="#"  class="a_hover">>订单详情</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="page-content">
				<h4 class="border-bottom pdb10">
					<strong>订单详情</strong>
					<c:choose>
						<c:when test="${qtType != null && qtType eq 'qtProject'}">
							<a href="${ctx}/qtProject/qSceneRecognition/${estateId}?searchParam=1" class="btn btn-primary">返回</a>
						</c:when>
						<c:otherwise>
							<a href="${ctx}/qtReport?searchParam=1" class="btn btn-primary">返回</a>
						</c:otherwise>
					</c:choose>
				</h4>
				<table class="table-sammary">
					<col style="width:109px;">
					<col style="width:auto;">
					<col style="width:109px;">
					<col style="width:auto;">
					<col style="width:116px;">
					<col style="width:auto;">
					<tr>
						<td style="text-align: right;"><strong>项目编号：</strong></td>
						<td colspan="5"><strong>${qtReportInfo.estate.projectNo}</strong>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>楼盘名称：</strong>
							<strong>${qtReportInfo.estateNm}</strong>
						</td>
					</tr>
					<tr>
						<td class="talabel">报备编号：</td>
						<td>${qtReportInfo.reportNo}</td>
						<td class="talabel">合作方：</td>
						<c:choose>
							<c:when test="${not empty  qtReportInfo.partnerNm}">
								<td>${qtReportInfo.partnerNm}</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
						<td class="talabel">收入类型：</td>
						<td>${qtReportInfo.srTypeName}</td>
					</tr>
					<tr>
						<td class="talabel">最新进度：</td>
						<td>${qtReportInfo.reportStatusName}</td>
					</tr>
				</table>
				<div class="fast-filing-step" style="margin-top:10px;">
						<ul class="tran_step">
							<c:set var="flag1" value="0"></c:set>
							<c:forEach var="qtReportDetail" items="${qtReportInfo.qtReportDetailList}" varStatus="status">
								<c:if test="${qtReportDetail.businessType==27301}">
									<!-- 报备、未结佣 -->
									<li class="step-item active">
										<c:choose>
											<c:when
												test="${not empty qtReportDetail.businessDate}">
												<a><span
													data-date="${sdk:ymd2(qtReportDetail.businessDate)}  ${qtReportInfo.ackStatusName}">报备</span></a>
											</c:when>
											<c:otherwise>
												<a><span>报备</span></a>
											</c:otherwise>
										</c:choose>
									</li>
									<c:set var="flag1" value="1"></c:set>
								</c:if>
							</c:forEach>
							<c:if test="${flag1 == '0'}">
								<li class="">报备</li>
							</c:if>

							<c:set var="flag2" value="0"></c:set>
							<c:set var="reportStatusStr" value="${qtReportInfo.reportStatus}"></c:set>
							<c:forEach items="${qtReportInfo.qtReportDetailList}"
								var="qtReportDetail" varStatus="reportIndex">
<!-- 								退成销/成销 -->
								<c:if test="${qtReportDetail.businessType eq '27302'}">
											<c:if test="${qtReportInfo.reportStatus eq '27001'}">
												<c:set var="roughAuditStatusNm" value="退成销"></c:set>
												<li class="step-item active">
													<c:choose>
														<c:when test="${not empty qtReportDetail.businessDate}">
															<a><span
																data-date="${sdk:ymd2(qtReportDetail.businessDate)}   ${roughAuditStatusNm}">成销</span></a>
														</c:when>
														<c:otherwise>
															<a><span>成销</span></a>
														</c:otherwise>
													</c:choose>
												</li>
											</c:if>
											<c:if test="${qtReportInfo.reportStatus ne '27001'}">
												<li class="step-item active">
													<c:choose>
														<c:when
															test="${not empty qtReportDetail.businessDate}">
															<a><span
																data-date="${sdk:ymd2(qtReportDetail.businessDate)} ${qtReportInfo.ackStatusName}">成销</span></a>
														</c:when>
														<c:otherwise>
															<a><span>成销</span></a>
														</c:otherwise>
													</c:choose>
												</li>
											</c:if>
								<c:set var="flag2" value="1"></c:set>
								</c:if>
							</c:forEach>
							<c:if test="${flag2 == '0'}"><li class="">成销</li></c:if>

							<c:set var="flag3" value="0"></c:set>
							<c:if
								test="${qtReportInfo.brokerageStatus == '22002' || qtReportInfo.brokerageStatus == '22003'}">
								<li class="step-item active"><c:choose>
										<c:when test="${qtReportInfo.reportStatusName!=''}">
											<a><span
												data-date="${qtReportInfo.reportStatusName} ${qtReportInfo.ackStatusName}">结佣</span></a>
										</c:when>
										<c:otherwise>
											<a><span>结佣</span></a>
										</c:otherwise>
									</c:choose></li>
								<c:set var="flag3" value="1"></c:set>
							</c:if>
							<c:if test="${flag3 == '0'}"><li class="">结佣</li></c:if>
						</ul>
					</div>
				<div style="margin-top:50px;" class="table table-bordered">
					<table class="table table-striped table-hover">
						<tr>
							<th>进度</th>
							<th>确认状态</th>
							<th>业务节点发生时间</th>
							<th>操作人</th>
							<th>操作时间</th>
							<th>操作</th>
						</tr>
						<c:forEach var="c" items="${qtReportInfo.qtReportDetailList}" varStatus="status">
							<c:if test="${c.businessType ne '27303' }">
								<tr>
									<td>
										<c:if test="${c.businessType eq '27301' }">
											报备
										</c:if>
										<c:if test="${c.businessType eq '27302' }">
											成销
										</c:if>
									</td>
									<c:choose>
										<c:when test="${qtReportInfo.validStatus == 0}">
											<td>有效</td>
										</c:when>
										<c:when test="${qtReportInfo.validStatus == 1}">
											<td>无效</td>
										</c:when>
									</c:choose>
									<td>${sdk:ymd2(c.businessDate)}</td>
									<td>${c.userName}</td>
									<c:choose>
										<c:when test="${not empty c.uptDate}">
											<td>${sdk:ymd2(c.uptDate)}</td>
										</c:when>
										<c:otherwise>
											<td>${sdk:ymd2(c.crtDate)}</td>
										</c:otherwise>
									</c:choose>
									<td>
										<a href="javascript:void(0);" onclick="QtReportDetail.toOperDetail(${qtReportInfo.id},${c.businessType})">查看</a>
									</td>
								</tr>
							</c:if>
						</c:forEach>
						<c:if test="${qtReportInfo.brokerageStatus == '22002' || qtReportInfo.brokerageStatus == '22003'}">
							<tr>
								<td>结佣</td>
								<c:choose>
									<c:when test="${qtReportInfo.validStatus == 0}">
										<td>有效</td>
									</c:when>
									<c:when test="${qtReportInfo.validStatus == 1}">
										<td>无效</td>
									</c:when>
								</c:choose>
								<td>${sdk:ymd2(qtReportInfo.brokerageDate)}</td>
								<td>${qtReportInfo.brokerageUserName}</td>
								<c:choose>
									<c:when test="${not empty qtReportInfo.uptDate}">
										<td>${sdk:ymd2(qtReportInfo.uptDate)}</td>
									</c:when>
									<c:otherwise>
										<td>${sdk:ymd2(qtReportInfo.crtDate)}</td>
									</c:otherwise>
								</c:choose>
								<td>
									<a href="javascript:void(0);" onclick="QtReportDetail.toOperDetail(${qtReportInfo.id},${qtReportInfo.brokerageStatus})">查看</a>
								</td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>


			<div class="page-content">
				<h4 class="border-bottom pdb10">
					<strong>日志</strong>
				</h4>
			</div>
			<table id="reportLogTableId" class="table table-striped table-hover table-bordered">
				<tr>
					<th style="width: 80px;">序号</th>
					<th>变更内容</th>
					<th style="width: 200px;">操作人(工号)</th>
					<th style="width: 250px;">操作时间</th>
				</tr>
				<c:forEach items="${qtReportInfo.qtLogList}" var="log" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td style="text-align:left">${log.opMemo}</td>
						<td>${log.logUserName}(${log.logUserCode})</td>
						<td>${sdk:ymd2(log.crtDate)}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</form>

</body>
</html>
