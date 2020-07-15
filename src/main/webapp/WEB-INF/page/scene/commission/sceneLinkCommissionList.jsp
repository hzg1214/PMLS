<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/scene/commission/sceneLinkCommission.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/bootstrap-multiselect.css">
	<script type="text/javascript" src="${ctx}/meta/js/common/bootstrap-multiselect.js?_v=${vs}"></script>
	<style type="text/css">
		.form-inline {
			overflow: inherit;
		}
	</style>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	
	<div class="container theme-hipage ng-scope" role="main">
		<h4 class="border-bottom pdb10" style="margin-right: -15px;margin-left:-15px;"><strong>佣金管理</strong></h4>
        <div class="row">
        	<ul class="nav nav-tabs nav-tabs-header">
	             <li role="presentation">
	                <a href="${ctx}/sceneCommission">外佣/返佣</a>
	            </li>
	            <li role="presentation" >
	                <a href="${ctx}/sceneInCommission">内佣</a>
	            </li>
				<li role="presentation">
					<a href="${ctx}/scenePadCommission/index">垫佣</a>
				</li>
				<li role="presentation">
					<a href="${ctx}/settleConfirm">结算确认</a>
				</li>
				<%-- <li role="presentation">
					<a href="${ctx}/linkAchieveChange">业绩调整</a>
				</li> --%>
				<li role="presentation">
                	<a href="${ctx}/cashBill">请款单</a>
            	</li>

	        </ul>
            <div class="page-content">
                <form  id="SceneYSSRCommissionListForm">
					<input type="hidden" name="estateType" id="estateType" value="20606">
                    <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">

                        	<%--<div class="form-group" style="margin-right: 1%;">
                        		业绩归属城市：
	                           <label>${userInfo.cityName}</label>
                        		<!--项目所在城市区域 -->
                        	</div>--%>
                        	<div class="form-group">
				              	<!-- 城市下的中心 -->
								<div class="form-group" style="width:auto;">
									<label style="text-align:right;">业绩归属项目部：</label>
		                            <select class="form-control" style="width:150px;" id="projectDepartmentId" name="projectDepartmentId" >
		                                <option value="" selected>请选择</option>
		                                <c:forEach items="${rebacklist}" var="list">
		                                     <option value="${list.projectDepartmentId}">${list.projectDepartment}</option>
		                                </c:forEach>
		                            </select>
		                            <span class="fc-warning"></span>
		                        </div>
	                        </div>
							<div class="form-group">
								<label style="width:90px;text-align:right;">楼盘名称：</label>
								<script type="text/javascript">
									$(document).ready(function() {
										$("#estateNm").multiselect({
											enableFiltering: true,
											filterPlaceholder: '请输入楼盘名称',
											buttonWidth: '150px',
											nonSelectedText: '请选择',
											nSelectedText: '个楼盘被选中',
											includeSelectAllOption: false,
											selectAllText: '全选/取消',
											allSelectedText: '已选中所有楼盘',
											selectedClass: 'active1',
											numberDisplayed: 10,
											dropRight: true,
											maxHeight: 275,
											dropUp: true,
											onChange: function(element, checked) {
												var brands = $('#estateNm option:selected');
												var selected = [];
												$(brands).each(function (index, brand) {
													selected.push(['\''+$(this).val()+'\'']);
												});
												$("#estateNmKey").val(selected);
												console.log(selected);
											}
										});
										//控制select显示
										$("span.multiselect-selected-text").css({"text-align":'left',display:'inline-block'});
										$("b.caret").css("margin-left","75px");
									});
								</script>
								<select class="form-control" style="width:150px;" id="estateNm" name="estateNm" multiple="multiple">
									<c:forEach items="${estateList}" var="list">
										<option value="${list}">${list}</option>
									</c:forEach>
								</select>
								<input type="hidden" id="estateNmKey" name="estateNmKey" />
								<span class="fc-warning"></span>
							</div>
							<div class="form-group">
								<!-- 报备编号 -->
								<label style="width:90px;text-align:right;">报备编号：</label>
							</div>
							<div class="form-group">
								<input type="text" class="form-control " id="reportId" name="reportId" style="width:150px;">
							</div>
							<div class="form-group">
								<input type="text" class="form-control " style="width:355px;" id="searchKey"
									   name="searchKey" placeholder="请输入项目编号、开发商、楼室号、客户姓名、电话">
							</div>
                        </div>
                        <div class="form-inline">
							<div class="form-group" style="margin-left: 9px;">
								<!-- 开始日期 -->
								<i>*</i> 开始日期：
							</div>
							<div class="form-group">
								<div class="input-group">
									<input  type="text" class="calendar-icon form-control w100" name="countDateStart" id="countDateStart" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" style="width:150px;" readonly="readonly" class="ipttext Wdate"/>
								</div>
							</div>
							<div class="form-group" style="margin-left: 9px;">
								<!-- 结束日期 -->
								<i>*</i> 结束日期：
							</div>
							<div class="form-group">
								<div class="input-group">
									<input  type="text" class="calendar-icon form-control w100" name="countDateEnd" id="countDateEnd" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'countDateStart\')}',maxDate:'%y-%M-%d'})" style="width:150px;" readonly="readonly" class="ipttext Wdate"/>
									<span class="fc-warning"></span>
								</div>
							</div>
	                       <div class="form-group" style="float:right;width:300px;">
	                       	<div style="float:left">
							  <button type="button" class="btn btn-primary" id="J_search" onclick="javascript:SceneYSSRCommission.search();" >查询</button>&nbsp;
							 <button type="button" id="btn-reset" class="btn btn-default"  onclick="javascript:SceneYSSRCommission.reset()">重置</button>
							</div>
							 <div style="float:right" >
							 <button type="button" id="btn-output" class="btn btn-primary" onclick="javascript:SceneYSSRCommission.output();">导出</button>&nbsp;
							 <button type="button" id="btn-imput" class="btn btn-primary" onclick="javascript:SceneYSSRCommission.imput();">导入</button>
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
            </div>
        </div>
    </div>
	<form name="imputForm" id="imputForm" method="post" action="${pageContext.request.contextPath}/scenePadCommission/imput" target="hiddenFrame" enctype="multipart/form-data">
	 	<input type="file" id="historyDataFile" name="historyDataFile" accept=".xls,.xlsx"  style="display:none">
	 	<input type="hidden" id="estateTypeImput" name="estateTypeImput"> 
    </form>
    <iframe name="hiddenFrame" id="hiddenFrame" style="display:none">
	</iframe>
</body>

</html>
