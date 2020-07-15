<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/contacts/contactsList.js?_v=${vs}"></script>
<script type="text/javascript"
	src="${ctx}/meta/js/contacts/contacts.js?_v=${vs}"></script>
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
				<li><a href="#"  class="a_hover">>联系人</a></li>
			</ul>
		</div>
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/store/storeLeftMenu.jsp" flush="true">
				<jsp:param value="110405" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content">
				<div class="page-content">
					<h4>
						<strong>联系人</strong>
						<a type="button" class="btn btn-primary" href="${ctx}/store?searchParam=1">返回</a>
					</h4>
					<!-- 搜索条件区域 -->
					<form id="contactsForm">
						<input type="hidden" name="storeId" id="storeId"
							value="${storeId}">
						<input type="hidden" name="userCreate" id="userCreate"
							value="${userCreate}">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="form-inline">
									<div class="col-md-9">
										<div class="form-group">
											<label class="">联系人姓名</label> <input type="text"
												class="form-control" id="name" name="name">
										</div>
										<!-- 默认排序字段、排序类型 -->
										<input type="hidden" name="orderName" value="IsDefault">
										<input type="hidden" name="orderType" value="DESC">
										<button type="button" class="btn btn-primary" id="J_search"
											onclick="javascript:search();">搜索</button>
									</div>
									<!-- 门店新建联系人按钮隐藏 -->
									<!-- <div class="col-md-3 text-right">
										<a type="button" class="btn btn-primary"
											href="javascript:Contacts.toAddCntcts();">新建</a>
									</div> -->
								</div>
							</div>
						</div>
						<!-- 异步加载列表内容 -->
						<div id="load_content">
							<div id="LoadCxt"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
