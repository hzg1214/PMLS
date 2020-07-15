<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/store/storeRelateUtil.js?_v=${vs}"></script>
<script type="text/javascript"
	src="${ctx}/meta/js/relate/relateStores.js?_v=${vs}"></script>
<script type="text/javascript"
	src="${ctx}/meta/js/company/gpstoreList.js?_v=${vs}"></script>
<script type="text/javascript"
	src="${ctx}/meta/js/company/gpcompanyStoreList.js?_v=${vs}"></script>	
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container">
		<div class="crumbs">
			<ul>
				<li><a href="#"  class="a_hover">公司管理</a></li>
				<li><a href="#"  class="a_hover">>公司详情</a></li>
				<li><a href="#"  class="a_hover">>公盘门店</a></li>
			</ul>
		</div>
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/company/companyLeftMenu.jsp"
				flush="true">
				<jsp:param value="110408" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content">
				<div class="page-content">
					<h4>
						<strong>公盘门店</strong>
						<a type="button" class="btn btn-primary" style="margin-left:10px;" href="${ctx}/companys?searchParam=1">返回</a>
						<shiro:hasPermission name="/companys:COMPANY_CT_STORE">
							<c:if test="${gpCompanyStore eq 'true'}">
								<a type="button" class="btn btn-primary"  href="javascript:gpcompanyStore();" >关联公盘门店</a>
							</c:if>
						</shiro:hasPermission>
					</h4>
					<!-- 搜索条件区域 -->
					<form id="gpcompanyStoreListForm">
						<input type="hidden" name="companyId" id="companyId"
							value="${companyId}">
						<input type="hidden" name="companyName" id="companyName"
							value="${companyName}">
						<input type="hidden" name="userCreate" id="userCreate"
							value="${userCreate}">
						<input type="hidden" name="districtsNo" value="${districtsNo}">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="form-inline">
									<div class="col-md-9">
									<div class="form-group">
											<label class="">门店名称</label> <input type="text"
												class="form-control" id="name" name="name" placeholder="请输入">
										</div>
										<!-- 默认排序字段、排序类型 -->
										<input type="hidden" name="orderName" value="dateCreate">
										<input type="hidden" name="orderType" value="DESC">
										<button type="button" class="btn btn-primary" id="J_search"
											onclick="javascript:search();">搜索</button>
									</div>
									
                 					<shiro:hasPermission name="/companys:CY_GP_STORE">
                 						<c:if test="${storelist.dateLifeEnd < nowDate || storelist.contractStatus eq null || storelist.contractStatus eq 10405 || storelist.contractStatus eq 10406}">
										<div class="col-md-3 text-right" style="display:none">
											<button type="button" class="btn btn-primary"
												onclick="javascript:relateStores();">关联公盘门店</button>
										</div>
										</c:if>
									</shiro:hasPermission>
								</div>
							</div>
						</div>
						<!-- 异步加载列表内容 -->
						<div id="load_gpcontent">
							<div id="LoadCxtgpComStore"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
