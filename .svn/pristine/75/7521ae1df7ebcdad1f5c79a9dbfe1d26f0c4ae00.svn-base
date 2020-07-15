<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/scene/estate/sceneEstate.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	
	<div class="container theme-hipage ng-scope" role="main">
	<h4 class="border-bottom pdb10" style="margin-right: -15px;margin-left:-15px;"><strong>案场管理</strong></h4>
        <div class="row">
        	<ul class="nav nav-tabs nav-tabs-header">
	            <li role="presentation" class="active">
	                <a href="${ctx}/sceneEstate">项目</a>
	            </li>
				<li role="presentation">
					<a href="${ctx}/report">报备</a>
				</li>
				<li role="presentation">
					<a href="${ctx}/reportToValid">大定待审核</a>
				</li>
				<li role="presentation">
					<a href="${ctx}/reportToValid/valided">大定已审核</a>
				</li>
				<li role="presentation">
					<a href="${ctx}/custom">客户</a>
				</li>
	        </ul>
            <div class="page-content">
                <form  id="SceneEstateListForm">
                	<input type="hidden" name="orderName" value="dateCreate">
					<input type="hidden" name="orderType" value="DESC">
					<input type="hidden" name="cityNo" value="${userInfo.cityNo}">
					<input type="hidden" name="searchParmCache" value="1">
					<input type="hidden" id="curPageTemp" value="1">
                    <input type="hidden" id="sysPageChaneTemp" value="1">
                    <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
                        	<%--<div class="form-group" style="margin-right: 1%;">
                        		业绩归属城市：
	                            <label>${userInfo.cityName}</label>
                        		<!--项目所在城市区域 -->
                        	</div>--%>
                        	<div class="form-group">
								<label>楼盘城市</label>
                        		<select class="form-control" style="width:150px;" id="realityCityNo" name="realityCityNo"></select>
	                            <select class="form-control" style="width:150px;" title="" id="districtNo" name="districtNo"></select>
	                        </div>
	                        <div class="form-group">
	                        	<!-- 归属项目组 -->
	                            <select class="form-control" title="" id="projectDepartmentId" name="projectDepartmentId" notnull="true" readonly>
	                                <option value="" selected>请选择归属项目部</option>
	                                <c:forEach items="${rebacklist}" var="list">
	                                     <option value="${list.projectDepartmentId}">${list.projectDepartment}</option>
	                                </c:forEach>
                            	</select>
	                        </div>
	                        <div class="form-group">
				                <!-- 类型 -->
			                  	<t:dictSelect field="estateType" id="estateType" xmlkey="LOOKUP_Dic" dbparam="P1:134" nullOption="请选择类型" classvalue="10" ></t:dictSelect>
				            </div>
				            
	                    </div>
	                    <div class="form-inline">

			                <div class="form-group">
				              	<!-- 统计时间 -->
			                  	统计时间：
				                <div class="input-group">
				                  <input  type="text" class="calendar-icon form-control w100" name="countDateStart" id="countDateStart" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate"/>
				                </div>
				            </div>
				            <div class="form-group">
				                <div class="input-group">
				                	<input  type="text" class="calendar-icon form-control w100" name="countDateEnd" id="countDateEnd" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate"/>
				                </div>
				            </div>
				            <div class="form-group">
				                <input type="text" class="form-control w300" id="estateNm" name="estateNm"  placeholder="请输入项目编号/楼盘名称">
				            </div>
	
				            <button type="button" class="btn btn-primary" id="J_search" onclick="javascript:SceneEstate.search();">查询</button>
	                        <button type="button" id="btn-reset" class="btn btn-default" onclick="javascript:SceneEstate.reset()" >重置</button>
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
		SceneEstate.setSearchParams(searchParamMap);
	}
</script>

</html>
