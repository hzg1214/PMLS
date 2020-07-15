<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/keFuWj/storeKeFuInvestedList.js?_v=${vs}"></script>
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
			<div class="col-md-10 content"  style="margin-top: 3px;">
        	<ul class="nav nav-tabs nav-tabs-header">
	            <li role="presentation" >
	                <a href="${ctx}/keFuOrder/getKeFuOrderListByStoreId/${storeId}">工单</a>
	            </li>
				<li role="presentation" class="active">
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
<!-- 					</h4> -->
					<form id="storeKeFuInvestedForm">	
					<!-- 门店Id -->
					<input type="hidden" id="storeId" name="storeId" value="${storeId} ">
					<input type="hidden" id="userId" name="userId" value="${userInfo.userId} ">			
					<div class="" style="height: auto;">
					<table class="table table-striped table-hover table-bordered">
			            <tr>
			                <th style="width:100px;">序号</th>
			                <th style="width:200px;">调查时间</th>
			                <th style="width:120px;">得分</th>
			                <th style="width:160px;">创建人</th>
			                <th style="width:200px;">创建时间</th>
			                <th>操作</th>
			            </tr>
			            
				            <c:forEach items="${contentlist}" var="list">
								<tr class="J_eatateItem" data-hidenumberflg="0">
                					<td>${list.rn}</td>
									<td  title="${list.wjdcjd}" class="text-overflow">${list.wjdcjd}</td>
                					<td  title="${list.wjdcTotalscore}" class="text-overflow">${list.wjdcTotalscore}</td>
                					<td>${list.userName}</td>
					                <td>${sdk:ymd2(list.dateCreate)}</td>
					                <td><a href="javascript:KeFuInvested.toInvestedView(${list.id},${storeId},'${list.StoreNo}')">查看</a></td>
								</tr>	
							</c:forEach>
				    	</table>
				    	</form>
				    </div>
<c:if test="${fn:length(contentlist) le 0}">
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


