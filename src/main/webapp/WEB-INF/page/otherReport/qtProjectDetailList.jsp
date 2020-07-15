<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/otherReport/qtProjectDetail.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<style>
    .sys_masklock{
        position: fixed;
    }
</style>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	
	<div class="container theme-hipage ng-scope" role="main">
		<div class="crumbs">
			<ul style="margin-right:150px;">
				<li><a href="#"  class="a_hover">联动管理</a></li>
				<li><a href="#"  class="a_hover">>其他收入</a></li>
				<li><a href="#"  class="a_hover">>项目</a></li>
			</ul>
		</div>
        <div class="row">
        	<%-- <div style="height:34px; line-height:34px;" class="mgb10"><span class="blue"><strong>项目编号：${estate.projectNo}    &nbsp;&nbsp;&nbsp;楼盘名称：${estate.estateNm}</strong></span><a href="${ctx}/sceneEstate?searchParam=1" class="float-right btn btn-primary">返回</a></div> --%>
        	<div style="height:34px; line-height:34px;" class="mgb10">
        	<h4 class="border-bottom pdb10" ><strong style="font-size:18px;">项目编号：${estate.projectNo}    &nbsp;&nbsp;&nbsp;楼盘名称：${estate.estateNm} </strong>
					<input id="projectNo" name="projectNo" type="hidden" value="${estate.projectNo}"/>
					<input id="estateName" name="estateName" type="hidden" value="${estate.estateNm}"/>
					<input id="estateId" name="estateId" type="hidden" value="${estateId}">
				<a href="${ctx}/qtProject?searchParam=1" class="float-right btn btn-primary" style="float: right;margin-top: -10px;">返回</a>
			</h4>
			</div>

        		<div class="page-content">
                <form  id="qtProjectDetailListForm">
                	<input type="hidden" name="orderName" value="dateCreate">
                	<input type="hidden" name="qtType" value="${qtType}">
					<input type="hidden" name="orderType" value="DESC">
					<input type="hidden" name="cityNo" value="${userInfo.cityNo}">
					<input type="hidden" name="estateId" value="${estateId}">
					<input type="hidden" name="searchParmCache" value="1">
					<input type="hidden" id="curPageTemp" value="1">
					<input type="hidden" id="sysPageChaneTemp" value="1">
					<input type="hidden" name="progress" id="progress" value="">
                    <input type="hidden" name="brokerageStatus" id="brokerageStatus" value="">
					<input type="hidden" name="yearMonth" id="yearMonth" value="${yearMonth}"/>
					<input type="hidden" name="isShowCurrDate" id="isShowCurrDate" value="${isShowCurrDate}"/>
                    <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
								<label class="control-label">状态：</label>
								<div class="form-group">
									<input type="checkbox" value="27001" id="checkbox1" name="status">
									<label for="checkbox1" class="fon-normal small">报备</label>
									<input type="checkbox" value="27002" id="checkbox2" name="status">
									<label for="checkbox3" class="fon-normal small">成销</label> 
									<input type="checkbox" value="22003" id="checkbox3" name="status">
									<label for="checkbox3" class="fon-normal small">结佣</label>
								</div>
								<div class="form-group">
									<label style="margin-left: 25px;">报备编号:</label> <input
										type="text" class="form-control" id="reportNo" name="reportNo"
										style="width: 170px; margin-left: 10px;" placeholder="报备编号"
										value=""/>
								</div>
								<div class="form-group"  style="margin-left: 50px;">
									<!-- 日期类型 -->
<%-- 									<t:dictSelect field="dateTypeKbn" id="dateTypeKbn" --%>
<%-- 										xmlkey="LOOKUP_Dic" dbparam="P1:137" nullOption="请选择日期类型" --%>
<%-- 										classvalue="10"></t:dictSelect> --%>
										
									<select class="multi-select-text" id="dateTypeKbn" name="dateTypeKbn" style="width: 130px;height: 34px;">
	                                    <option value="">请选择日期类型</option>
	                                    <option value="13701">报备日期</option>
	                                    <option value="13704">成销日期</option>
	                                    <option value="13705">结佣日期</option>
	                                </select>
								</div>
								<div class="form-group">
									<div class="input-group">
										<input type="text" class="calendar-icon form-control w100"
											name="dateStart" id="dateStart"
											onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
											readonly="readonly" class="ipttext Wdate" />
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<input type="text" class="calendar-icon form-control w100"
											name="dateEnd" id="dateEnd"
											onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
											readonly="readonly" class="ipttext Wdate" />
									</div>
								</div>
							</div>
                        
                        <div class="form-inline">
	                        <div class="form-group" style="float: right;">
								<button style="margin-left:60px;" type="button" class="btn btn-primary" id="J_search" onclick="javascript:qtProjectDetail.search();">查询</button>
		                        <button style="margin-left:10px;" type="button" id="btn-reset" class="btn btn-default" onclick="javascript:qtProjectDetail.reset();">重置</button>
		                    </div>
                        </div>
                    </div>
                </div>
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
    	qtProjectDetail.setSearchParams(searchParamMap);
    }
    
</script>
</html>
