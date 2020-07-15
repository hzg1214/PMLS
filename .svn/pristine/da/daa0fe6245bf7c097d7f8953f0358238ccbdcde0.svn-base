<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>

<link href="${ctx}/meta/css/multi.select.css" rel="stylesheet">
<script type="text/javascript"
	src="${ctx}/meta/js/expand/multi.select.js"></script>
<script type="text/javascript"
	src="${ctx}/meta/js/report/expand/expandDetailReport.js"></script>
  <script type="text/javascript" src="${ctx}/meta/js/expand/jquery.base64.js"></script>  
<script type="text/javascript" src="${ctx}/meta/js/expand/tableExport.js"></script> 

<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>


<style type="text/css">
	.btn-flat a:hover{
	text-decoration:none;
	}
	table td{
	vertical-align: middle !important;
	}
	.form-inline{
	overflow:inherit;
} 
</style>
</head>

<body>

	<!-- 头部 -->
	<%-- <jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/fixed-navbar.jsp"></jsp:include> --%>
    <jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>


	<div id="groupDiv" style="display:none;" >
		<div class="form-group" id="business">
			<label class="lab" ></label>
			<div class="multi-select" id="districtName"
				style="width: 181px;">
				<input type="hidden" class="multi-select-value"
					readonly="readonly"> <input type="text"
					class="multi-select-text" readonly="readonly"
					placeholder="请选择" style="width: 181px;">
				<ul class="multi-select-list">
					<li class="multi-select-item"><label><input
							type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<input type="hidden" id="hiddenInput" value="2"/>

	<div class="container theme-hipage ng-scope" role="main">
		<div class="row">
			<div style="margin-bottom: 11px;">
			  	<ul class="nav nav-tabs nav-tabs-header">
					<li role="presentation" class="active" onclick="goingDate()">
						<a href="#">拓展汇总表（时间段)</a>
					</li>
					<li role="presentation" onclick="goingDay()">
						<a href="#">拓展汇总表（日）</a>
					</li>
					<li role="presentation" onclick="goingWeek()">
						<a href="#">拓展汇总表（周）</a>
					</li>
					
				</ul>
			 </div>
			<div class="page-content">

				<div class="panel panel-default">
					<div class="search-title" style="text-indent: 5px;">
						拓展汇总表（时间段）
					</div>
					<div class="panel-body">
						<div class="form-inline">
							<div class="form-group">
								<label class="lab">日期</label>
								<div class="input-group date">
									<div class="input-group-addon">
										<i class="glyphicon glyphicon-time"></i>
									</div>
									<input type="text" class="multi-select-text" name="inDate1"
										id="inDate1"
										onClick="WdatePicker({isShowClear:true, readOnly:true,maxDate:'#F{$dp.$D(\'inDate2\')}',dateFmt:'yyyy-MM-dd'})"
										class="ipttext Wdate" style="width: 120px;" />
								</div>
								<span>-</span>
								<div class="input-group date">
									<div class="input-group-addon">
										<i class="glyphicon glyphicon-time"></i>
									</div>
									<input type="text" class="multi-select-text" name="inDate2"
										id="inDate2"
										onClick="WdatePicker({isShowClear:true, readOnly:true,minDate:'#F{$dp.$D(\'inDate1\')}',dateFmt:'yyyy-MM-dd'})"
										class="ipttext Wdate" 
										style="width: 120px;" />
								</div>
							</div>
							
							<div class="form-group" id="city">
								<label class="lab">城市</label>
								<div class="multi-select" id="districtName"
									style="width: 181px;">
									<input type="hidden" class="multi-select-value"
										readonly="readonly"> <input type="text"
										class="multi-select-text" readonly="readonly" placeholder="城市"
										style="width: 181px;">
									<ul class="multi-select-list">
										
									</ul>
								</div>
							</div>
							
							<!-- <div class="form-group" id="business">
								<label class="lab">事业部</label>
								<div class="multi-select" id="districtName"
									style="width: 181px;">
									<input type="hidden" class="multi-select-value"
										readonly="readonly"> <input type="text"
										class="multi-select-text" readonly="readonly"
										placeholder="事业部" style="width: 181px;">
									<ul class="multi-select-list">
										<li class="multi-select-item"><label><input
												type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
										</li>
									</ul>
								</div>
							</div> -->
							<div style="float:right" id="searchDiv">
							<div class="form-group">
								<button type="button" class="btn btn-primary btn-flat"
									id="search" onclick="searchCollect()">查询</button>
							</div>
							
							<div class="form-group" style=" margin-left: 10px;">
								<button type="button" class="btn btn-primary btn-flat"
									id="search" onclick="exportCollect()">导出</button>
							</div>
							</div>
							</div>
							<div class="form-inline">
							<!-- <div class="form-group" id="department">
								<label class="lab">部门</label>
								<div class="multi-select" id="districtName"
									style="width: 300px;">
									<input type="hidden" class="multi-select-value"
										readonly="readonly"> <input type="text"
										class="multi-select-text" readonly="readonly" placeholder="部门"
										style="width: 300px;">
									<ul class="multi-select-list">
										<li class="multi-select-item"><label><input
												type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
										</li>
									</ul>
								</div>
							</div>
							
							<div class="form-group" id="group">
								<label class="lab">小组</label>
								<div class="multi-select" id="districtName"
									style="width: 181px;">
									<input type="hidden" class="multi-select-value"
										readonly="readonly"> <input type="text"
										class="multi-select-text" readonly="readonly" placeholder="小组"
										style="width: 181px;">
									<ul class="multi-select-list">
										<li class="multi-select-item"><label><input
												type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
										</li>
									</ul>
								</div>
							</div> -->
							
							<form id="exportExpand" action="${ctx}/omsReport/exportExpandCollectList" method="post">			
								<div class="expandDetail">
									<input type="hidden" id="bdate" name="bdate" />
									<input type="hidden" id="edate" name="edate" />
									<input type="hidden" id="sType" name="sType" />
									<input type="hidden" id="performTeam" name="performTeam" />
									<input type="hidden" id="performTeam1" name="performTeam1" />
									<input type="hidden" id="performTeam2" name="performTeam2" />
									<input type="hidden" id="performTeam3" name="performTeam3" />
									<input type="hidden" id="performTeam4" name="performTeam4" />
									<input type="hidden" id="cityNames" name="cityNames" />
									<input type="hidden" id="type1" name="type1" />
									<input type="hidden" id="type2" name="type2" />
									<input type="hidden" id="type3" name="type3" />
									<input type="hidden" id="type4" name="type4" />
								</div>
							</form>
						</div>
						<div class="form-inlin">
					</div>
				</div>
			</div>
			<!-- 异步加载列表内容 -->
			<div id="load_content">
				<div id="LoadCxt"></div>
			</div>
			<div style="display: none;">
    				<ul id='pagings'></ul>
			</div>
		</div>
		
	</div>

</body>
<jsp:include page="../common/footer.jsp"></jsp:include>
	
</html>
