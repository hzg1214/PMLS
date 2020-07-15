<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/WEB-INF/page/common/meta.jsp"%>
	<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
	<link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/meta/js/report/expand/multi.select.js?_v=${vs}"></script>
	<script type="text/javascript" src="${ctx}/meta/js/performanceSum/performanceSumCommon.js?_v=${vs}"></script>
	<script type="text/javascript" src="${ctx}/meta/js/report/expand/storeStopDetailReport.js?_v=${vs}"></script>
	<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>

</head>
<style type="text/css"> 
table th{ 
	text-align: center; 
}
.form-inline{
	overflow:inherit;
} 
</style>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
            	<form  id="storeStopDetailForm">
                <h4 class="border-bottom pdb10"><strong>门店终止明细表</strong></h4>
                    <div class="panel panel-default">
                         <div class="panel-body" style="padding-bottom:10px; width: 1200px;">
                              <div class="form-inline">
								  <div class="form-group">
									  <label>架构年份</label>
									  <select class="form-control" title="" id="org" name="org"
											  notnull="true">
									  </select>
								  </div>

								  <div class="form-group" id="city">
									  <label>城市</label>
									  <div class="multi-select" id="cityId" name="cityId">
										  <input type="hidden" class="multi-select-value" readonly="readonly">
										  <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
												 style="width: 120px;">
										  <ul class="multi-select-list">
											  <li class="multi-select-item">
												  <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
											  </li>
										  </ul>
									  </div>
								  </div>
								  <div class="form-group" id="center">
									  <label id="centerLabel">中心</label>
									  <div class="multi-select" id="centerId" name="centerId">
										  <input type="hidden" class="multi-select-value" readonly="readonly">
										  <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
												 style="width: 120px;">
										  <ul class="multi-select-list">
											  <li class="multi-select-item">
												  <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
											  </li>
										  </ul>
									  </div>
								  </div>
								  <div class="form-group">
									  <label>当期</label>
									  <input type="text"  size="11"  class="calendar-icon form-control" name="dateStart"
											 id="dateStart"
											 readonly="readonly"
											 onchange="checkDate(this)"
											 onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
											 class="ipttext Wdate" /> 至
									  <input type="text"   size="11"  class="calendar-icon form-control" name="dateEnd"
											 id="dateEnd"
											 readonly="readonly"
											 onchange="checkDate(this)"
											 onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
											 class="ipttext Wdate" />
								  </div>

								  <div class="form-group" style="margin-left: 15px">
									  <button type="button" class="btn btn-primary" id="J_search"
											  onclick="javascript:StoreStop.search();">搜索
									  </button>
									  &nbsp;
									  <button type="button" class="btn btn-primary" id="J_export"
											  onclick="javascript:StoreStop.export();">导出
									  </button>
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
</body>

</html>
