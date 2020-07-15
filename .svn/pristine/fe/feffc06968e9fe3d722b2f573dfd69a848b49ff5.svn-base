<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/company/company.js?_v=${vs}"></script>
</head>

<body>
	<!-- 关联的门店id 集合 -->
	<input type="hidden" id="storeIdArray" name="storeIdArray" />

	<div class="page-content">
		<h4>
			<strong>基本信息</strong>
		</h4>
		<ul class="list-inline half form-inline">
			<li>
				<div class="form-group">
					<label class="fw-normal w100 text-right" for="companyName">公司名称：</label>${info.companyName}
				</div>
			</li>

			<li>
				<div class="form-group">
					<label class="fw-normal w100 text-right" for="companyNo">公司编号：</label>${info.companyNo}
				</div>
			</li>
		</ul>
		<ul class="list-inline half form-inline">
			<li>
				<div class="form-group">
					<label class="fw-normal w100 text-right">公司来源：</label>${info.sourceName}
				</div>
			</li>
		</ul>

		<ul class="list-inline half form-inline">
			<li>
				<div class="form-group">
					<label class="fw-normal w100 text-right">经纪人数：</label>${info.predictAccountCount}
				</div>
			</li>
			<li>
				<div class="form-group">
					<label class="fw-normal w100 text-right">公司规模：</label>${info.companyScaleName}
				</div>
			</li>
		</ul>

		<ul class="list-inline half form-inline">
			<li>
				<div class="form-group">
					<label class="fw-normal w100 text-right">原系统：</label>${info.originalVersionsName}
				</div>
			</li>
		</ul>

		<ul class="list-inline form-inline pdb20">
			<li>
				<div class="form-group">
					<label class="fw-normal w100 text-right">公司注册地址：</label>
					${info.cityName} ${info.districtName} ${info.areaName}
					${info.address}
				</div>
			</li>
		</ul>
	</div>
</body>

</html>
