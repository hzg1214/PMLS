<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/otherReport/qtReport.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	
	<div class="container theme-hipage ng-scope" role="main">
	<h4 class="border-bottom pdb10" style="margin-right: -15px;margin-left:-15px;"><strong>其他收入</strong></h4>
        <div class="row">
        	<ul class="nav nav-tabs nav-tabs-header">
	            <li role="presentation" >
	                <a href="${ctx}/qtProject">项目</a>
	            </li>
	            <li role="presentation" class="active">
	                <a href="${ctx}/qtReport">报备</a>
	            </li>
	        </ul>
            <div class="page-content">
                <form  id="qtReportListForm">
                	<input type="hidden" name="orderName" value="dateCreate">
					<input type="hidden" name="orderType" value="DESC">
					<input type="hidden" name="cityNo" value="${userInfo.cityNo}">
					<input type="hidden" name="searchParmCache" value="1">
					<input type="hidden" id="curPageTemp" value="1">
                    <input type="hidden" id="sysPageChaneTemp" value="1">
                    <input type="hidden" name="progress" id="progress" value="">
                    <input type="hidden" name="brokerageStatus" id="brokerageStatus" value="">
                    <div class="panel panel-default">
                    <div class="panel-body">
                    	<div class="form-inline">
							<div class="form-group">
	                        	<!-- 归属项目组 -->
	                        	<label class="control-label">归属项目部：</label>
	                            <select class="form-control" title="" style="width: 170px;" id="projectDepartmentId" name="projectDepartmentId" notnull="true" readonly>
	                                <option value="" selected>请选择</option>
	                                <c:forEach items="${rebacklist}" var="list">
	                                     <option value="${list.projectDepartmentId}">${list.projectDepartment}</option>
	                                </c:forEach>
	                           	</select>
	                        </div>
	                        <label class="control-label"  style="margin-left: 30px;">状态：</label>
							<div class="form-group">
								<input type="checkbox" value="27001" id="checkbox1" name="status">
								<label for="checkbox1" class="fon-normal small">报备</label>
								<input type="checkbox" value="27002" id="checkbox2" name="status">
								<label for="checkbox2" class="fon-normal small">成销</label> 
								<input type="checkbox" value="22003" id="checkbox3" name="status">
								<label for="checkbox3" class="fon-normal small">结佣</label>
							</div>
		                  
			                  <div class="form-group" style="margin-left: 30px;">
			                  	<!-- 日期类型 -->
<%-- 			                  	<t:dictSelect field="dateTypeKbn" id="dateTypeKbn" xmlkey="LOOKUP_Dic" dbparam="P1:137" nullOption="请选择日期类型" classvalue="10" ></t:dictSelect> --%>
			                  	<select class="multi-select-text" id="dateTypeKbn" name="dateTypeKbn" style="width: 130px;height: 34px;">
                                    <option value="">请选择日期类型</option>
                                    <option value="13701">报备日期</option>
                                    <option value="13704">成销日期</option>
                                    <option value="13705">结佣日期</option>
                                </select>
			                  </div>
			                  <div class="form-group">
			                    <div class="input-group">
			                    	<input  type="text" class="calendar-icon form-control w100" name="dateStart" id="dateStart" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate"/>
			                    </div>
			                  </div>
			                  <div class="form-group">
			                    <div class="input-group">
			                      	<input  type="text" class="calendar-icon form-control w100" name="dateEnd" id="dateEnd" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate"/>
			                    </div>
			                  </div>
		                </div>
		
		                <div class="form-inline">
							<div class="form-group">
								<label style="margin-left: 15px;">报备编号:</label> <input
									type="text" class="form-control" id="reportNo" name="reportNo"
									style="width: 170px; margin-left: 10px;" placeholder="报备编号"
									value=""/>
							</div>
							<div class="form-group" style="float: right;">
								<button type="button" class="btn btn-primary" id="J_search" onclick="javascript:Report.search();">查询</button>
								<button type="button" id="btn-reset" class="btn btn-default" onclick="javascript:Report.reset()">重置</button>
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
<script type="text/javascript">
	var searchParamMap = '${searchParamMap}';
	if (searchParamMap != undefined && searchParamMap != null && searchParamMap != '') {
		Report.setSearchParams(searchParamMap);
	}
</script>

</html>
