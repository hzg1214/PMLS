<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/store/signContractList.js?_v=${vs}"></script>
<script type="text/javascript"
	src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
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
				<li><a href="#"  class="a_hover">>门店签约</a></li>
			</ul>
		</div>
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/store/storeLeftMenu.jsp"
				flush="true">
				<jsp:param value="110402" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content">
				<div class="page-content">
				<input type="hidden" id="renewFlag" name="renewFlag" value="${storedata.renewFlag}" />
				<input type="hidden" id="neededRenew" name="neededRenew" value="${storedata.neededRenew}" />
					<h4>
						<strong>签约历史</strong>
						<a type="button" class="btn btn-primary" href="${ctx}/store?searchParam=1">返回</a>
					</h4>
					
					<!-- 搜索条件区域 -->
					<form id="storeContractListForm">
						<input type="hidden" name="storeId" id="storeId"
							value="${storeId}">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="form-inline">
									<div class="col-md-12">
										<!-- <div class="form-group">
											<select class="form-control" title="" id="conditionId"
												name="conditionId">
												<option value="1">公司名称</option>
												<option value="2">协议书编号</option>
											</select>
										</div>
										<div class="form-group">
											<label class="sr-only">Name</label> <input type="text"
												class="form-control" id="name" name="name" placeholder="请输入"
												value="">
										</div> -->
										<div class="form-group">
											<label>合同编号</label> <input type="text"
												class="form-control"
												name="contractNo" id="contractNo"
												placeholder="请输入" />
										</div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<div class="form-group">
											<label>签约日期</label> <input type="text"
												class="calendar-icon form-control w100"
												name="dateCreateStart" id="dateCreateStart"
												onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
												readonly="readonly" class="ipttext Wdate" />
										</div>
										<div class="form-group">
											<input type="text" class="calendar-icon form-control w100"
												name="dateCreateEnd" id="dateCreateEnd"
												onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
												readonly="readonly" class="ipttext Wdate" />
										</div>
										<button type="button" class="btn btn-primary" id="J_search"
											onclick="javascript:search();">搜索</button>
									</div>
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
