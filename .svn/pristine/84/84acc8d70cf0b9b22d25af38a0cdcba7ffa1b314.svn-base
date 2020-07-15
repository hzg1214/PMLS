<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=1.0&ak=${sysConfig.baiduApiKey}"></script>
<script type="text/javascript"
	src="${ctx}/meta/js/fangyou/fangyouAccount.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container">
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/store/storeLeftMenu.jsp"
				flush="true">
				<jsp:param value="110409" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content">
				<div class="page-content">
					<h4>
						<strong>房友账号</strong>
					    <a type="button" class="btn btn-primary" href="${ctx}/store">返回</a>
					</h4>
					<form id="fangYouDetailForm">
						<div style="height:50px;">
								<div style="width:200px;float:left ;padding-top: 10px">
									门店使用房友账号：<c:if test="${content.openStatus ne 0}">${content.fangyouNo}</c:if> 
								</div>
								<div style="margin-left: 50px; float:left;padding-top: 10px">	
								状态：						
								<%-- <c:if test="${content.openStatus eq 0}">
								 	未开通
								 </c:if>  --%>
								 <c:if test="${content.openStatus eq 1}">
								 	开通中
								 </c:if> 
								 <c:if test="${content.openStatus eq 2}">
								 	已开通
								 </c:if> 
							 </div>
							<shiro:hasPermission name="/store:STORE_FY_BIND">
							    <c:if test="${content.openStatus ne 1}">
								<a class="btn btn-primary" id="bundling" style="float:right; margin-right:10px;" href="javascript:void(0);" >解绑房友账号</a>
							    </c:if> 
							</shiro:hasPermission>
						</div>
						<div class="" style="height: auto;">
							<div class="" style="height: auto;" id="LoadfangYouCtx"></div>
						</div>
						<input type="hidden" id="storeId" name="storeId" value="${store.storeId}" />
						<input type="hidden" id="storeNo" name="storeNo" value="${store.storeNo}" />
						<input type="hidden" id="name" name="name" value="${store.name}" />
						<input type="hidden" id="operType" name="operType" value="${content.operType}" />
						<input type="hidden" id="fangyouNo" name="fangyouNo" value="${content.fangyouNo}" />
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
