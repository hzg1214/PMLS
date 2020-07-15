<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/scene/statistic/sceneStatisticCompany.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	
	<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
        	<div style="height:34px; line-height:34px;" class="mgb10"><span class="blue">项目编号：${estateId}    &nbsp;&nbsp;&nbsp;楼盘名：${estateNm}</span><a href="${ctx}/sceneStatisticEstate?searchParam=1"  class="float-right btn btn-primary" style="margin-bottom:10px">返回</a></div>
            <div class="page-content">
                <form  id="SceneStatisticCompanyListForm">
                	<input type="hidden" name="orderName" value="dateCreate">
					<input type="hidden" name="orderType" value="DESC">
					<input type="hidden" name="cityNo" value="${userInfo.cityNo}">
					<input type="hidden"  id="estateId"  name="estateId" value="${estateId}">
					<input type="hidden" name="estateId" value="${estateNm}">
					<input type="hidden"  id="prevCountDateStart"  name="prevCountDateStart" value="${prevCountDateStart}">
					<input type="hidden"  id="prevCountDateEnd"  name="prevCountDateEnd" value="${prevCountDateEnd}">
					<input type="hidden"  id="prevCountDateTypeKbn"  name="prevCountDateTypeKbn" value="${prevCountDateTypeKbn}">
					<input type="hidden" name="searchParmCache" value="1">
                    <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
			                 <div class="form-group">
				              	<!-- 统计时间 -->
			                  	统计时间：
				              </div>
			              	<div class="form-group">
				                <div class="input-group">
				                  <input  type="text" class="calendar-icon form-control w100" name="countDateStart" id="countDateStart"   onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate"/>
				                </div>
				              </div>
				              <div class="form-group">
				                <div class="input-group">
				                	<input  type="text" class="calendar-icon form-control w100" name="countDateEnd" id="countDateEnd"  onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate"/>
				                </div>
				              </div>
				              <div class="form-group">
				                <input type="text" class="form-control " id="companyNm" name="companyNm"  placeholder="请输入公司">
				              </div>
	
				              	<button type="button" class="btn btn-primary" id="J_search" onclick="javascript:SceneStatisticCompany.search();">查询</button>
	                           <button type="button" id="btn-reset" class="btn btn-default"  onclick="javascript:reset()">重置</button>
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
		SceneStatisticCompany.setSearchParams(searchParamMap);
	}
</script>

</html>
