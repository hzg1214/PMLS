<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container">
		<div class="crumbs">
			<ul>
				<li><a href="#"  class="a_hover">门店管理</a></li>
				<li><a href="#"  class="a_hover">>门店详情</a></li>
				<li><a href="#"  class="a_hover">>客服督导</a></li>
			</ul>
		</div>
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/store/storeLeftMenu.jsp"
				flush="true">
				<jsp:param value="110410" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content" style="margin-top: 3px;">
<!-- 			<h4 class="border-bottom pdb10" style="margin-right: -15px;margin-left:-15px;"><strong>案场管理</strong></h4> -->
<!--         <div class="row"> -->
        	<ul class="nav nav-tabs nav-tabs-header">
	            <li role="presentation" class="active">
	                <a href="${ctx}/keFuOrder/getKeFuOrderListByStoreId/{storeId}">工单</a>
	            </li>
				<li role="presentation">
					<a href="${ctx}/keFuWj/getInvestedList/${storeId}">满意度调查</a>
				</li>
				<li role="presentation">
					<a href="${ctx}/keFuWj/getEvaluationList/${storeId}">门店测评</a>
				</li>
				<a type="button" class="btn btn-primary" style="float: right;margin-top: 3px;" href="${ctx}/store?searchParam=1">返回</a>
	        </ul>
				<div class="page-content">
<!-- 					<h4> -->
<!-- 						<strong>客服督导</strong> -->
<%-- <a type="button" class="btn btn-primary" href="${ctx}/store?searchParam=1">返回</a> --%>
<!-- 					</h4> -->
					<form id="storeKeFuOrderForm">	
					<!-- 门店Id -->
					<input type="hidden" id="storeId" name="storeId" value="${storeId} ">
					<input type="hidden" id="userId" name="userId" value="${userInfo.userId} ">			
					<div class="" style="height: auto;">
					<table class="table table-striped table-hover table-bordered">
			            <tr>
			                <th style="width:135px;">工单编号</th>
			                <th style="width:100px;">一级分类</th>
			                <th style="width:100px;">二级分类</th>
			                <th style="width:100px;">经办人</th>
			                <th style="width:100px;">创建人</th>
			                <th style="width:120px;">创建日期</th>
			                <th style="width:100px;">问题状态</th>
			                <th style="width:100px;">核验状态</th>
			                <th>操作</th>
			            </tr>
			            
				            <c:forEach items="${content}" var="list">
								<tr class="J_eatateItem" data-hidenumberflg="0">
									<td title="${list.orderNo}" class="text-overflow"><a href="${ctx}/keFuOrder/getKeFuOrderById/${list.id}">${list.orderNo}</a></td>
									<td  title="${list.categoryOneNm}" class="text-overflow">${list.categoryOneNm}</td>
                					<td  title="${list.categoryTwoNm}" class="text-overflow">${list.categoryTwoNm}</td>
                					<td>${list.userName}</td>
					                <td>${list.createName}</td>
					                <td>${sdk:ymd2(list.dateCreate)}</td>
					                <td>${list.dealStatusNm}</td>
					                <td>${list.checkStatusNm}</td>
					                <td><a href="${ctx}/keFuOrder/getKeFuOrderById/${list.id}">查看</a></td>
								</tr>	
							</c:forEach>
				    	</table>
				    	</form>
				    </div>
<c:if test="${fn:length(content) le 0}">
	<div class="nodata">
		<i class="icon-hdd"></i>
		<p>暂无数据...</p>
	</div>
</c:if>
${pageInfo.html}
				</div>
			</div>
		</div>	
</div>
</body>
</html>


