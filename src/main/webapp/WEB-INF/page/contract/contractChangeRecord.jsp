<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/contract/contractChange.js?_v=${vs}"></script>
	<style type="text/css">
		.text-overflow {
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
			width: 100%;
		}
	</style>
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
				<li><a href="#"  class="a_hover">>合同终止</a></li>
			</ul>
		</div>
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/contract/contractLeftMenu.jsp"
				flush="true">
				<jsp:param value="110402" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content">
				<div class="page-content">
				<br>
					<h4>
						<strong>合同终止列表</strong>
						<a href="${ctx}/contract?searchParam=1" class="btn btn-primary">返回</a>
						<div class="text-right">
						<!-- 门店未进行业绩撤销不允许终止 -->
						<c:if test="${storeCancelSate eq 'Y'}">
							 <c:choose>
							 	<c:when test="${empty contentlist}">
							 		<c:if test="${contractInfo.contractStatus eq 10403}">
										<a type="button" class="btn btn-primary" style="float:right; margin-right:10px;" href="${ctx}/stopcontract/toAdd/${contractId}/${contractStatus}" >申请终止</a>
									</c:if>
							 	</c:when>
							 	 <c:otherwise>
							 	 	<c:if test="${contractInfo.contractStatus eq 10403}">
										<a type="button" class="btn btn-primary" style="float:right; margin-right:10px;" href="${ctx}/stopcontract/toAdd/${contractId}/${contractStatus}" >申请终止</a>
									</c:if>
							 	 </c:otherwise>
							 </c:choose>
						</c:if>
                    	<input type="hidden" name="contractId" id="contractId" value="${contractId}">
                    </div>
					</h4>
					
					
                    
					<div class="" style="height: auto;">
					<table class="table table-striped table-hover table-bordered">
			            <tr>
			                <th style="display:none"><input value="checkbox" type="checkbox"></th>
			                <th style="width:150px">合同终止申请编号</th>
			                <th style="width:150px">经纪公司</th>
							<th style="width:100px">终止类型</th>
			                <th style="width:70px">门店数</th>
							<th style="width:120px">申请人</th>
							<th style="width:100px">申请日期</th>
			                <th style="width:150px">审核状态</th>
			                <th>操作</th>
			            </tr>
			            
				            <c:forEach items="${contentlist}" var="list">
							<tr class="J_eatateItem" data-hidenumberflg="0">
									<td>${list.contractStopNo}</td>
									<td title="${list.oldUpdateCompanyName}" class="text-overflow">${list.oldUpdateCompanyName}</td>
									<td>${list.stopTypeStr}</td>
									<td>${list.storeNumber}</td>
									<td>${list.userCreateName}(${list.userCode})</td>
									<%--<td> </td>--%>
									<td>
										<fmt:parseDate var="dateCreate" value="${list.dateCreate}" pattern="yyyy-MM-dd HH:mm:ss"/>
										<fmt:formatDate value="${dateCreate}" pattern="yyyy-MM-dd"/>
									</td>
								<td>
									<c:if test="${list.cancelFlag eq 0}">${list.approveStateName}</c:if>
									<c:if test="${list.cancelFlag eq 1}">作废</c:if>
								</td>
									<td><a href="${ctx}/stopcontract/toView/${list.id}/${contractId}/${contractStatus}">查看</a>
											<c:if test="${list.cancelFlag eq 0 && ((list.approveState eq 0 && (empty list.submitOAStatus || list.submitOAStatus eq 21201 || list.submitOAStatus eq 21204)) || (list.approveState eq 3 && list.submitOAStatus ne 21202))}">
												<a href="${ctx}/stopcontract/toEdit/${list.id}/${contractId}/${contractStatus}"> 编辑</a>
												<c:if test="${list.cancelFlag eq 0}">
													<a href="javascript:void(0)" onclick="ContractChange.delete('${list.id}','${contractId}','${contractStatus}')">作废</a>
												</c:if>
												<c:if test="${userInfo.userCode eq oaOperator.operCode}">
													<a href="javascript:void(0)"
													   onClick="ContractChange.submitOaApprove(this,'${list.id}','${contractId}','${oaOperator.isCombine}','${contractStatus}','${list.changeType}')">
														提交审核</a>
												</c:if>
											</c:if>
											<%--<c:if test="${list.approveState eq 1}">
												<a href="javascript:void(0)" onClick="ContractChange.getChgOAAuthStatus('${list.flowId}','${contractId}','${contractStatus}','${list.changeType}')">获取审核状态</a>
											</c:if>--%>
											<c:if test="${list.approveState eq 3}">
												<a href="javascript:ContractChange.getOAOpinions('${list.flowId}')"> 查看原因</a>
											</c:if>
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



