<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/contract/contractChange.js?_v=${vs}"></script> 
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="110404" name="navSelectId" />
	</jsp:include>
	<div class="container">
		<div class="crumbs">
			<ul>
				<li><a href="#"  class="a_hover">合同管理</a></li>
				<li><a href="#"  class="a_hover">>合同详情</a></li>
				<li><a href="#"  class="a_hover">>合同变更</a></li>
			</ul>
		</div>
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/contract/contractLeftMenu.jsp"
				flush="true">
				<jsp:param value="110404" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content">
				<div class="page-content">
				<br>
					<h4>
						<strong>合同变更列表</strong>
						<a href="${ctx}/contract?searchParam=1" class="btn btn-primary">返回</a>
						<div class="text-right">
                    	<input type="hidden" name="contractId" id="contractId" value="${contractId}">
                    </div>
					</h4>
					
					
                    
					<div class="" style="height: auto;">
					<table class="table table-striped table-hover table-bordered">
			            <tr>
			                <th style="display:none"><input value="checkbox" type="checkbox"></th>
			                <th style="width:180px">合同变更申请编号</th>
			                <th style="width:150px">变更类型</th>
			                <th style="width:150px">审核状态</th>
			                <th style="width:150px">变更时间</th>
			                <th style="width:150px">申请人</th>
			                <th>操作</th>
			            </tr>
			            
				            <c:forEach items="${contentList}" var="list">
							<tr class="J_eatateItem" data-hidenumberflg="0">
									<td>${list.contractStopNo}</td>
									<td>${list.changeTypeNm}</td>
									<td>
										<c:if test="${list.cancelFlag eq 1}">作废</c:if>
										<c:if test="${list.cancelFlag eq 0}">
											<c:choose>
												<c:when test="${list.approveState eq 0}">待提交审核</c:when>
												<c:when test="${list.approveState eq 1}">审核中</c:when>
												<c:when test="${list.approveState eq 2}">审核通过</c:when>
												<c:when test="${list.approveState eq 3}">审核不通过</c:when>
											</c:choose>
										</c:if>
									</td>
									<td>
										<fmt:parseDate value="${list.dateCreate}" var="dateCreate" pattern="yyyy-MM-dd HH:mm:ss"/>
										<fmt:formatDate value="${dateCreate}" pattern="yyyy-MM-dd"></fmt:formatDate>
									</td>
									<td>${list.userName}（${list.userCode}）</td>
									<td>
										<c:if test="${list.cancelFlag eq 1}">
											<a href="${ctx}/stopcontract/toView/${list.id}/${contractId}/${contractStatus}">查看</a>
										</c:if>
										<c:if test="${list.changeType eq 17002 and list.cancelFlag eq 0}">
											<a href="${ctx}/stopcontract/toView/${list.id}/${contractId}/${contractStatus}">查看</a>
											<c:if test="${(list.approveState eq 0 && (empty list.submitOAStatus || list.submitOAStatus eq 21201 || list.submitOAStatus eq 21204)) || (list.approveState eq 3 && list.submitOAStatus ne 21202)}">
												<a href="${ctx}/stopcontract/toEdit/${list.id}/${contractId}/${contractStatus}"> 编辑</a>
												<%--<a href="javascript:void(0)" onclick="ContractChange.delete('${list.id}','${contractId}','${contractStatus}')">删除</a>--%>
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
										</c:if>
										<c:if test="${list.changeType eq 17003 and list.cancelFlag eq 0}">
											<a href="${ctx}/contractChangeNew/toView/${list.id}/${contractId}/${contractStatus}">查看</a>
											<c:if test="${(list.approveState eq 0 && (empty list.submitOAStatus || list.submitOAStatus eq 21201 || list.submitOAStatus eq 21204)) || (list.approveState eq 3 && list.submitOAStatus ne 21202)}">
												<a href="${ctx}/contractChangeNew/toEdit/${list.id}/${contractId}/${contractStatus}"> 编辑</a>
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
										</c:if>
										<c:if test="${list.changeType eq 17004 and list.cancelFlag eq 0}">
											<a href="${ctx}/storeRelocation/toView/${list.id}/${contractId}/${contractStatus}">查看</a>
											<c:if test="${(list.approveState eq 0 && (empty list.submitOAStatus || list.submitOAStatus eq 21201 || list.submitOAStatus eq 21204)) || (list.approveState eq 3 && list.submitOAStatus ne 21202)}">
												<a href="${ctx}/storeRelocation/toStoreRelocation/${list.id}/${contractId}/${contractStatus}"> 编辑</a>
												<c:if test="${userInfo.userCode eq oaOperator.operCode}">
													<a href="javascript:void(0)"
													   onClick="ContractChange.submitOaApprove(this,'${list.id}','${contractId}','${oaOperator.isCombine}','${contractStatus}','${list.changeType}')">
														提交审核</a>
												</c:if>
											</c:if>
											
											<c:if test="${list.approveState eq 3}">
												<a href="javascript:ContractChange.getOAOpinions('${list.flowId}')"> 查看原因</a>
											</c:if>
										</c:if>
										<c:if test="${(list.submitOAStatus eq 21201 || list.approveState eq 3) && list.cancelFlag eq 0}">
											<a href="javascript:void(0)" onclick="ContractChange.delete('${list.id}','${contractId}','${contractStatus}')">作废</a>
										</c:if>
									</td>
							</tr>	
							</c:forEach>
				    	</table>
				    	<c:if test="${fn:length(contentList) le 0}">
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



