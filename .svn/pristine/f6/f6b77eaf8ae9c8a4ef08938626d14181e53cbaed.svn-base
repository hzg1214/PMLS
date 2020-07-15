<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/contract/achievementCancel.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container">
		<div class="crumbs">
			<ul>
				<li><a href="#"  class="a_hover">合同管理</a></li>
				<li><a href="#"  class="a_hover">>合同详情</a></li>
				<li><a href="#"  class="a_hover">>合同撤销</a></li>
			</ul>
		</div>
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/contract/contractLeftMenu.jsp"
				flush="true">
				<jsp:param value="110403" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content">
				<div class="page-content">
					<br>
					<h4>
						<strong>合同撤销列表 </strong>
						<a href="${ctx}/contract?searchParam=1" class="btn btn-primary">返回</a>
						<c:if test="${isOperator eq true and contractInfo.contractType ne 10306}">
							<c:if test="${contractStatus eq 10403}">
								<a type="button" class="btn btn-primary"
									style="float:right; margin-right:10px;"
									href="javascript:void(0)" onclick="AchievementCancel.goAchievementCancelAdd('${contractId}','${contractStatus}')">撤销</a>
							</c:if>
						</c:if>
						<input type="hidden" name="contractId" id="contractId"
							value="${contractId}">
					</h4>
					<div class="" style="height: auto;">
						<table class="table table-striped table-hover table-bordered">
							<tr>
								<th style="display:none"><input value="checkbox"
									type="checkbox"></th>
								<th style="width: 130px;">合同撤销申请编号</th>
								<th>经纪公司</th>
								<th style="width: 101px;">门店撤销数量</th>
								<th style="width: 110px;">申请人</th>
								<th style="width: 95px;">申请日期</th>
								<th style="width: 80px;">审批状态</th>
								<th style="width: 101px;">审核通过日期</th>
								<th style="width: 135px;">操作</th>
							</tr>
							<c:forEach items="${contentlist}" var="list" >
								<tr class="J_eatateItem" data-hidenumberflg="0">
									<td>${list.achievementCancelNo}</td>
									<td>${list.companyName}</td>
									<td>${list.storeNum}</td>
									<td>${list.userCreateName}(${list.userCode})</td>
									<td>${sdk:ymd2(list.dateCreate)}</td>
									<td>${list.approveStateName}</td>
									<td>${sdk:ymd2(list.approveDate)}</td>
									<td><a href="${ctx}/achievement/toView/${contractStatus}/${list.achievementCancelNo}/${contractId}">查看</a>
										<%--<c:if test="${list.approveState eq '17302'}">
											<a  href="#" onclick="AchievementCancel.getCancelOAAuthStatus('${list.flowId}','${list.achievementCancelNo }','${contractId}','${contractStatus}')">获取审核状态</a>
										</c:if> --%>
										<%-- <c:if test="${list.approveState eq '17303'}">
											<a  href="#" onclick="AchievementCancel.goAchievementCancelEdit('${list.achievementCancelNo}','${contractStatus}','${contractId}')"">变更</a>
										</c:if>  --%>
									</td> 
								</tr>
							</c:forEach>
						</table>
						<c:if test="${fn:length(contentlist) le 0}">
							<div class="nodata">
								<i class="icon-hdd"></i>
								<p>暂无数据...</p>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
${pageInfo.html}



