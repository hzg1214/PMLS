<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/scene/commission/sceneCommissionDetail.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	
	<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
        	<div style="height:34px; line-height:34px;" class="mgb10"><span class="blue">项目编号：${estateId}    &nbsp;&nbsp;&nbsp;楼盘名：${estateNm}</span><a href="${ctx}/sceneCommission" class="float-right btn btn-primary">返回</a></div>
            <div class="page-content">
                <form  id="SceneCommissionDetailListForm">
                	<input type="hidden" name="orderName" value="dateCreate">
					<input type="hidden" name="orderType" value="DESC">
					<input type="hidden" name="cityNo" value="${userInfo.cityNo}">
					<input type="hidden" name="estateId" value="${estateId}">
					<input type="hidden" name="estateId" value="${estateNm}">
					<input type="hidden" name="progress" id="progress" value="">
                    <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
                        	<label class="control-label">奖励类型：</label>
                        	<div class="form-group">
                        		<input type="checkbox" value="13502" id="checkbox1" name="status" ><label for="checkbox1" class="fon-normal small">带看奖励</label>
								<input type="checkbox" value="13503" id="checkbox2" name="status" ><label for="checkbox2" class="fon-normal small">认筹奖励</label>
								<input type="checkbox" value="13504" id="checkbox3" name="status" ><label for="checkbox3" class="fon-normal small">大定奖励</label>
								<input type="checkbox" value="13505" id="checkbox4" name="status" ><label for="checkbox4" class="fon-normal small">成销奖励</label>
								<input type="checkbox" value="13507" id="checkbox5" name="status" ><label for="checkbox5" class="fon-normal small">佣金</label>
	                        </div>
				              <div class="form-group">
				                <input type="text" class="form-control " id="companyNm" name="companyNm"  placeholder="请输入公司名">
				                <input type="text" class="form-control " id="empNm" name="empNm"  placeholder="请输入经纪人姓名">
				                <input type="text" class="form-control " id="customerNm" name="customerNm"  placeholder="请输入客户姓名">
				              </div>
	
				              	<button type="button" class="btn btn-primary" id="J_search" onclick="javascript:SceneCommissionDetail.search();">查询</button>
	                           <button type="button" id="btn-reset" class="btn btn-default" onclick="javascript:reset()">重置</button>     
                        </div>
                        
                        <div class="form-inline">
                        	<div class="form-group">
			                  	<!-- 结算状态 -->
			                  	<t:dictSelect field="accountStatus" id="accountStatus" xmlkey="LOOKUP_Dic" dbparam="P1:142" nullOption="请选择结算状态" classvalue="10" ></t:dictSelect>
			                  </div>
                        	<div class="form-group">
		                  	<!-- 日期类型 -->
		                  	<t:dictSelect field="dateTypeKbn" id="dateTypeKbn" xmlkey="LOOKUP_Dic" dbparam="P1:143" nullOption="请选择日期类型" classvalue="10" ></t:dictSelect>
		                  </div>
		                  <%-- <div class="form-group">
		                  	<!-- 日期 -->
		                  	<t:dictSelect field="dateKbn" id="dateKbn" xmlkey="LOOKUP_Dic" dbparam="P1:139" nullOption="请选择日期" classvalue="10" ></t:dictSelect>
		                  </div> --%>
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
                    </div>
                </div>
                <!-- 异步加载列表内容 -->
                <div id="load_content">
		           <div id="LoadCxt"></div>
		        </div>
                </form>
                <!-- <caption>
		            <div class="btn-group float-right" >
		                <a class="btn btn-primary" href="javascript:SceneCommissionDetail.modify2();" id="J_UpdateCommission" style="margin-bottom:10px">修改佣金</a>
		                <a class="btn btn-primary" href="javascript:SceneCommissionDetail.confirm2();" id="J_UpdateBalance" style="margin-bottom:10px">确认结算</a>
		            </div>
		        </caption> -->
               
                
            </div>
        </div>
    </div>

</body>

</html>
