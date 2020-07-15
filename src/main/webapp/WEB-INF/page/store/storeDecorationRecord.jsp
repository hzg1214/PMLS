<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/store/storeDecorationRacord.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container">
		<div class="crumbs">
			<ul>
				<li><a href="#"  class="a_hover">门店管理</a></li>
				<li><a href="#"  class="a_hover">>门店详情</a></li>
				<li><a href="#"  class="a_hover">>门店装修</a></li>
			</ul>
		</div>
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/store/storeLeftMenu.jsp"
				flush="true">
				<jsp:param value="110406" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content">
				<div class="page-content">
					<h4>
						<strong>门店装修</strong>
						<a type="button" class="btn btn-primary" href="${ctx}/store?searchParam=1">返回</a>
						<%-- <div class="float-right">
							<!-- 最新门店装修状态为 翻牌装修完成才可再申请,可过滤掉合同状态为A或C的门店 -->
							<c:if test="${upToDateDecorationStatus eq 16304 && contractStatus eq 10403}">
							  	<!-- 当前人为经办人 -->
								<c:if test="${ isOperator eq 'yes'}">
									<a type="button" style="float:right; margin-right:10px;" href="javascript:storeDecorationRecord.toAdd(${storeId},${userInfo.userId} );" class="btn btn-primary">申请装修</a>
								</c:if>		
							 </c:if>
						</div> --%>
					</h4>
					</br>
					<form id="decorationForm">	
					<!-- 门店Id -->
					<input type="hidden" id="storeId" name="storeId" value="${storeId} ">
					<input type="hidden" id="userId" name="userId" value="${userInfo.userId} ">			
					</form>				                    
					<div class="" style="height: auto;">
					<table class="table table-striped table-hover table-bordered">
			            <tr>
			                <th style="display:none"><input value="checkbox" type="checkbox"></th>
			                <th>装修编号</th>
			                <th>合同编号</th>
			                <th>合作模式</th>
			                <th>公司名称</th>
			                <th>申请人</th>
			                <th>申请时间</th>
			                <th>装修类型</th>
			                <th>装修进度</th>
			                <th>付款状态</th>
			            </tr>
			            
				            <c:forEach items="${content}" var="list">
							<tr class="J_eatateItem" data-hidenumberflg="0">
									<td>${list.decorationNo}</td>
									<td>${list.contractNo}</td>
									<td>${list.contractTypeName}</td>
									<td>${list.companyName}</td>
									<td>
									<c:if test="${! empty list.userCode && ! empty list.userName}">${list.userName}(${list.userCode})</c:if>
									<c:if test="${empty list.userCode || empty list.userName}"> - </c:if>
									</td>
									<td>${list.dateCreate}</td>
									<td>
										<c:choose>
											<c:when test="${list.decorationType eq 17701}">
												自费装修
											</c:when>
											<c:when test="${list.decorationType eq 17702}">
												我司装修
											</c:when>
											<c:otherwise>
												-
											</c:otherwise>
										</c:choose>
									</td>
									<td>${list.decorationStatusName}</td>
									<td>
										<c:choose>
											<c:when test="${! empty list.paymentStatusName}">
												${list.paymentStatusName}
											</c:when>
											<c:otherwise>
												
											</c:otherwise>
										</c:choose>
									</td>
							</tr>	
							</c:forEach>
				    	</table>
				    </div>
<c:if test="${fn:length(content) le 0}">
	<div class="nodata">
		<i class="icon-hdd"></i>
		<p>暂无数据...</p>
	</div>
</c:if>
${pageInfo.html}
				</div>
			</div>
		</div>	
</div>
</body>
</html>


