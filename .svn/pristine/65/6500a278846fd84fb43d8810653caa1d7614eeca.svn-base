<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/estateCommon.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/estate.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	
	<div class="container theme-hipage ng-scope" role="main">
	<h4 class="border-bottom pdb10" style="margin-right: -15px;margin-left:-15px;"><strong>项目管理</strong></h4>
        <div class="row">
        	<ul class="nav nav-tabs nav-tabs-header">
	            <li role="presentation" class="active">
	                <a href="${ctx}/estate">项目</a>
	            </li>
	            <%--<li role="presentation">
	                <a href="${ctx}/statistic">统计</a>
	            </li>--%>
	        </ul>
            <div class="page-content">
                <form  id="EstateListForm">
                	<input type="hidden" name="contractType" id="contractType">
                	<input type="hidden" name="orderName" value="dateCreate">
					<input type="hidden" name="orderType" value="DESC">
					<input type="hidden" name="cityNo" value="${userInfo.cityNo}">
					<input type="hidden" name="searchParmCache" value="1">
					<input type="hidden" id="curPageTemp" value="1">
                    <input type="hidden" id="sysPageChaneTemp" value="1">
                    <div class="panel panel-default" style="margin-bottom:10px;">
                    <div class="panel-body">
                    	<div class="form-inline">
                    		<%--<div class="form-group" style="margin-right: 1%;">
                        		业绩归属城市：
	                            <label>${userInfo.cityName}</label>
                        	</div>--%>
                        	<div class="form-group">
								<label>楼盘城市</label>
	                            <select class="form-control" title="" id="realityCityNo" name="realityCityNo" notnull="true" style="width:130px;">
                           		</select>
	                        </div>
	                        <div class="form-group">
	                            <select class="form-control" title="" id="districtNo" name="districtId" notnull="true" style="width:130px;">
	                            </select>
	                        </div>
	                        <div class="form-group">
                        		<!-- 合作方类型 -->
	                            <t:dictSelect field="partner" id="partner" xmlkey="LOOKUP_Dic" dbparam="P1:128" nullOption="请选择合作方类型"  classvalue="10" > </t:dictSelect>
	                        </div>
	                        <div class="form-group">
	                        	<!-- 审核状态 -->
	                            <t:dictSelect field="auditStatus" id="auditStatus" xmlkey="LOOKUP_Dic" dbparam="P1:129" nullOption="请选择审核状态"  classvalue="10" ></t:dictSelect>
	                        </div>
	                        <div class="form-group">
	                        	<!-- 发布状态 -->
	                            <t:dictSelect field="releaseStatus" id="releaseStatus" xmlkey="LOOKUP_Dic" dbparam="P1:130" nullOption="请选择发布状态"  classvalue="10" ></t:dictSelect>
	                        </div>
	                        <div class="form-group">
	                        	<!-- 项目状态 -->
	                            <t:dictSelect field="projectStatus" id="projectStatus" xmlkey="LOOKUP_Dic" dbparam="P1:203" nullOption="请选择项目状态" classvalue="10"></t:dictSelect>
	                        </div>
	                        <div class="form-group">
	                        	<!-- 归属项目组 -->
<%-- 	                            <t:dictSelect field="projectDepartmentId" id="projectDepartmentId" xmlkey="LOOKUP_Dic" dbparam="P1:203" nullOption="请选择归属项目组" classvalue="10"></t:dictSelect> --%>
	                            <select class="form-control" title="" id="projectDepartmentId" name="projectDepartmentId" notnull="true" readonly>
	                                <option value="" selected>请选择归属项目部</option>
	                                <c:forEach items="${rebacklist}" var="list">
	                                     <option value="${list.projectDepartmentId}">${list.projectDepartment}</option>
	                                </c:forEach>
                            	</select>
<!--                             	<span class="fc-warning"></span> -->
	                        </div>
                        </div>
                        <div class="form-inline">
                        </div>
                        <div class="form-inline">
                        	<div class="form-group">
                        		<!-- 日期类型 -->
	                            <t:dictSelect field="dateTypeKbn" id="dateTypeKbn" xmlkey="LOOKUP_Dic" dbparam="P1:131" nullOption="请选择日期类型" classvalue="10" ></t:dictSelect>
	                        </div>
                            <div class="form-group">
                                <div class="input-group">
                                     <input  type="text" class="calendar-icon form-control w125" name="dateStart" id="dateStart" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                     <input  type="text" class="calendar-icon form-control w125" name="dateEnd" id="dateEnd" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate"/>
                                </div>
                            </div>
	                        <div class="form-group">
	                        	<!-- 合作模式-->
	                            <t:dictSelect field="businessModel" id="businessModel" xmlkey="LOOKUP_Dic" dbparam="P1:255" nullOption="请选择业务模式" classvalue="10"></t:dictSelect>
	                        </div>
                            <div class="form-group">
	                            <input type="text" class="form-control" id="estateNm" name="estateNm"  placeholder="请输入项目编号/楼盘名/创建人" size="32">
	                        </div>
                            <button type="button" class="btn btn-primary" id="J_search" onclick="javascript:Estate.search();">查询</button>
                            <button type="button" id="btn-reset" class="btn btn-default" onclick="javascript:Estate.reset()">重置</button>
                            <div class="form-group float-right">
								<a class="btn btn-primary" href="${ctx}/estate/c">新增楼盘</a>
							</div>
                        </div>
						
                    </div>
                </div>
                <div class="form-inline" style="margin-bottom:25px;">
						<div class="col-md-12">
							<div class="form-group label-radio-group" id="divContractType">
								<label style="width:56px;margin-left:17px;">筛选</label>
								<a class="btn active" href="${ctx}/estate">全部</a>
								<a class="btn" href="javascript:Estate.setContractType('1');Estate.search();">合同有效</a>
								<a class="btn" href="javascript:Estate.setContractType('2');Estate.search();">合同过期</a>
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
		Estate.setSearchParams(searchParamMap);
	}
</script>

</html>
