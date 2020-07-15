<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/contract/achievementCancel.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container">
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
						<strong>合同撤销详情</strong>
						<a href="${ctx}/achievement/cancel/${contractId}/${contractStatus}" class="btn btn-primary">返回</a>
						<shiro:hasPermission name="/ct:OPERATE_AUDIT">
							<c:if test="${content.approveState eq 17302}">
								<a href="javascript:void(0)" onclick="AchievementCancel.operateAuditCt('${content.achievementCancelNo}','${contractId}','${contractStatus}');" id="operateAuditCt" class="btn btn-primary" style="margin-right:10px;">运营维护审核状态</a>
							</c:if>
						</shiro:hasPermission>
					</h4>
					<ul class="list-inline form-inline">
			            <li>
			               <div class="form-group">
			                   <label class="fw-normal w140 text-right">合同撤销申请编号：</label>
			                   ${content.achievementCancelNo}
			               </div>
			           </li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="form-group">
								<label class="fw-normal w140 text-right">变更合同编号：</label>
								${content.contractNo}
							</div>
						</li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="form-group">
								<label class="fw-normal w140 text-right">经纪公司名称：</label>
								${content.companyName}
							</div>
						</li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="form-group">
								<label class="fw-normal w140 text-right" >撤销原因：</label>
								${content.cancelReason}
							</div>
						</li>
					</ul>
					<ul class="list-inline half form-inline">
						<li>
			             	<div class="form-group">
			              		<label class="fw-normal w140 text-right" >申请人：</label>
			                  	${content.userCreateName}(${content.userCode})
			             	</div>
						</li>
						<li>
							<div class="form-group">
								<label class="fw-normal w140 text-right">申请日期：</label>
								${content.dateCreate}
							</div>
						</li>
					</ul>
					<ul class="list-inline half form-inline">
						<li>
							<div class="form-group">
								<label class="fw-normal w140 text-right">审批状态：</label>
								${content.approveStateName}
							</div>
						</li>
						<li>
							<div class="form-group">
								<label class="fw-normal w140 text-right" >审核通过日期：</label>
								${content.approveDate}
							</div>
						</li>
					</ul>
					<%--<ul class="list-inline form-inline">
						<li>
			             	<div class="form-group">
			              		<label class="fw-normal w140 text-right">备注：</label>
			                  	<c:if test="${content.remarks eq 'null'}"></c:if>
			                  	<c:if test="${content.remarks ne 'null'}">${content.remarks}</c:if>
			             	</div>
						</li>
					</ul>--%>
					<p><strong>撤销门店列表</strong></p>
					<table id="cancelStoreTableId" class="table table-striped table-hover table-bordered">
						<tr>
							<th>门店编号</th>
							<th>门店名称</th>
							<th>门店区域</th>
							<th>门店地址</th>
							<th>合作模式</th>
							<th>装修进度</th>
							<th>装修日期</th>
						</tr>
		           <c:forEach items="${content.storelist}" var="store"  varStatus="listIndex">
	                 	<tr>
							<td>${store.storeNo}</td>
							<td>${store.name}</td>
							<td>${store.districtName}</td>
							<td>${store.address}</td>
							<td>${store.contractTypeName}</td>
							<td><c:choose>
								<c:when test="${store.decorationStatusName eq '-'}">--</c:when>
								<c:otherwise>${store.decorationStatusName}</c:otherwise>
							</c:choose></td>
							<td><c:choose>
								<c:when test="${store.updateDateStr eq '-'}">--</c:when>
								<c:otherwise>${store.updateDateStr}</c:otherwise>
							</c:choose></td>
	                 	</tr>
					</c:forEach> 
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>



