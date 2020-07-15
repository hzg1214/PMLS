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
	src="${ctx}/meta/js/follow/followList.js?_v=${vs}"></script>
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
				<li><a href="#"  class="a_hover">>跟进</a></li>
			</ul>
		</div>
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/store/storeLeftMenu.jsp"
				flush="true">
				<jsp:param value="110404" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content">
				<div class="page-content">
					<h4>
						<strong>跟进</strong>
						<a type="button" class="btn btn-primary" href="${ctx}/store?searchParam=1">返回</a>
						<%-- <a class="btn btn-primary" style="float:right; margin-right:10px;" href="javascript:Follow.toAdd('${storeId}')">新增跟进</a> --%>
					    
					</h4>
					<form id="storeDetailForm">
						<div class="" style="height: auto;">
							<div class="" style="height: auto;" id="LoadFollowCxt"></div>
						</div>
						<input type="hidden" name="storeId" value="${storeId}" />
						<input type="hidden" name="orderName" value="dateCreate">
						<input type="hidden" name="orderType" value="DESC">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
