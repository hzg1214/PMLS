<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/statistic/statisticByEstateIdDetail.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	
	<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
        	<ul class="nav nav-tabs nav-tabs-header">
	            <li role="presentation" >
	                <a href="${ctx}/estate">项目</a>
	            </li>
	            <li role="presentation" class="active">
	                <a href="${ctx}/statistic">统计</a>
	            </li>
	        </ul>
            <div class="page-content">
                <form  id="StatisticDetailListForm">
                	<input type="hidden" name="orderName" value="dateCreate">
					<input type="hidden" name="orderType" value="DESC">
					<input type="hidden" name="cityNo" value="${userInfo.cityNo}">
					<input type="hidden" name="estateId" value="${estateId}">
                    <div class="panel panel-default">
                    <div class="panel-body">
			            <div class="form-inline">
			            	<div class="form-group">
			              	<select class="form-control" title="" id=districtId name="districtId">
                            	<option selected="selected" value="">请选区域</option>
                            	<c:if test="${!empty districtList}">
	                                <c:forEach items="${districtList}" var="district">
	                                    <option value="${district.districtNo}">${district.districtName}</option>
	                                </c:forEach>
                            	</c:if>
                            </select>
			              </div>
			              <div class="form-group">
			                <!-- 类型 -->
		                  	<t:dictSelect field="estateType" id="estateType" xmlkey="LOOKUP_Dic" dbparam="P1:134" nullOption="请选择类型" classvalue="10" ></t:dictSelect>
			              </div>
			              <div class="form-group">
			              	<!-- 奖励类型 -->
		                  	<t:dictSelect field="rewardType" id="rewardType" xmlkey="LOOKUP_Dic" dbparam="P1:141" nullOption="请选择奖励类型" classvalue="10" ></t:dictSelect>			                
			              </div>
			              <div class="form-group">
			                <!-- 可否结算 -->
		                  	<t:dictSelect field="accountType" id="accountType" xmlkey="LOOKUP_Dic" dbparam="P1:158" nullOption="请选择可否结算" classvalue="10" ></t:dictSelect>
			              </div>
			              <div class="form-group">
			                <input type="text" class="form-control " id="estateNm" name="estateNm"  placeholder="请输入公司/楼盘名">
			              </div>

			              	<button type="button" class="btn btn-primary" id="J_search" onclick="javascript:StatisticDetail.search();">查询</button>
                            <button type="button" id="btn-reset" class="btn btn-default" onclick="javascript:reset()">重置</button>
			            </div>
			            <div class="form-inline">
			            	<div class="form-group">
				                <!-- 结算状态 -->
			                  	<t:dictSelect field="accountStatus" id="accountStatus" xmlkey="LOOKUP_Dic" dbparam="P1:142" nullOption="请选择结算状态" classvalue="10" ></t:dictSelect>
			              </div>
			              <div class="form-group">
				                <!-- 认定日 -->
			                  	认定日：
			              </div>
			              <div class="form-group">
		                    <div class="input-group">
		                    	<input  type="text" class="calendar-icon form-control w100" name="dateStart" id="dateStart" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'dateEnd\')}'})" readonly="readonly" class="ipttext Wdate"/>
		                    </div>
		                  </div>
		                  <div class="form-group">
		                    <div class="input-group">
		                      	<input  type="text" class="calendar-icon form-control w100" name="dateEnd" id="dateEnd" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'dateStart\')}'})" readonly="readonly" class="ipttext Wdate"/>
		                    </div>
		                  </div>
			            </div>
                    </div>
                </div>
                <caption>
			        <div class="float-right" >
			          <a href="${ctx}/statistic?searchParam=1" class="btn btn-primary" style="margin-bottom:10px">返回</a>
			        </div>
			    </caption>
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
