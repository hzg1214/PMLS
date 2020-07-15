<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/company/companyList.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container theme-hipage ng-scope" role="main">
		<h4 class="border-bottom pdb10" style="margin-right: -15px;margin-left:-15px;">
					<strong>公司管理</strong>
				</h4>
		<div class="row">
			<ul class="nav nav-tabs nav-tabs-header">
				<li role="presentation" class="active" id="presentation1">
					<a href="javascript:void(0);" id="taskType0">所有公司</a>
				</li>
				<li role="presentation"  id="presentation2">
					<a href="javascript:void(0);" id="taskType1">翻牌公司</a>
				</li>
				<li role="presentation"  id="presentation3">
					<a href="javascript:void(0);" id="taskType2">渠道公司</a>
				</li>
	        </ul>
	        
			<div class="page-content">
				
				<!-- 搜索条件区域 -->
				<form id="companyForm">
					<!-- 默认排序字段、排序类型 -->
					<input type="hidden" name="switchType" id="switchType" value="0">
					<input type="hidden" name="orderName" value="id">
					<input type="hidden" name="orderType" value="DESC">
					<input type="hidden" name="searchParmCache" value="1">
					<input type="hidden" id="curPageTemp" value="1">
					<input type="hidden" id="sysPageChaneTemp" value="1">
					<!-- 筛选条件隐藏域 -->
					<!-- <input type="hidden" name="sourceId" id="sourceId"> <input
						type="hidden" name="companyStatus" id="companyStatus"> <input
						type="hidden" name="contractType" id="contractType"> -->
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="form-inline">
								<div class="col-md-12">
									<div class="form-group">
										<label class="">公司编号</label> <input type="text"
											class="form-control" id="companyNo" name="companyNo">
									</div>
									<div class="form-group">
										<label class="">公司名称</label> <input type="text"
											class="form-control" id="companyName" name="companyName">
									</div>
									<div class="form-group">
										<label class="">统一社会信用代码</label> <input type="text"
											class="form-control" id="businessLicenseNo" name="businessLicenseNo">
									</div>
									<div class="form-group">
										<label class="">注册地址</label> <input type="text"
																				class="form-control" id="address" name="address">
									</div>
								</div>
							</div>
							<div class="form-inline">
								<div class="col-md-10">
									<div class="form-group" id="businessTypeId">
										<label class="">业务类型</label>
										<select class="form-control" id="bizType" name="bizType" style="width:177px;">
											<option value="">请选择</option>
											<option value="21801">房友门店</option>
											<option value="21802">新房联动</option>
										</select>
									</div>
								</div>
								<a type="button" class="btn btn-primary" id="J_search" onclick="javascript:Company.search();">搜索</a>&nbsp;
								<a type="button" class="btn btn-default" onclick="javascript:Company.reset('${list_search_page}');">重置</a>
							</div>
						</div>
					</div>
					<!-- 异步加载列表内容 -->
					<div id="load_content">
						<div id="LoadCxt"></div>
					</div>
				</form>

                <div id="exportExcelDiv" style="display:none"></div>
            </div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var searchParamMap = '${searchParamMap}';
	console.log(searchParamMap);
	if (searchParamMap != undefined && searchParamMap != null && searchParamMap != '') {
		Company.setSearchParams(searchParamMap);
	}
	Company.tabClick();
</script>
</html>
