<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=1.0&ak=${sysConfig.baiduApiKey}"></script>
<script type="text/javascript"
	src="${ctx}/meta/js/fangyou/fangyouAccountView.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container">
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/store/storeLeftMenu.jsp"
				flush="true">
				<jsp:param value="110409" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content">
				<div class="page-content">
					<h4>
						<strong>房友账号</strong>
					    <a type="button" class="btn btn-primary" href="${ctx}/store">返回</a>
					</h4>
					<form id="fangYouDetailForm">
						<div style="height:50px;">
								<div style="width:400px;float:left ;padding-top: 10px">
									门店关联房友1.0账号：<c:if test="${!empty store}">${store.fyAccount}</c:if> 
									<shiro:hasPermission name="/fy:FYACCOUNT_EDIT">
										<a href="javascript:FangyouAccountView.editFyAcountMode('${storeId}','${store.fyAccount}','${store.storeNo}')"
										class="btn btn-primary" style="margin-left:12px">修改</a>
									</shiro:hasPermission>
								</div>
						</div>	
					<div class="page-content">
                    	<h4 class="border-bottom pdb10">
                        	<strong>日志</strong>
                   		 </h4>
                	</div>
	                <table id="logTableId" class="table table-striped table-hover table-bordered">
	                    <tr>
	                        <th style="width: 80px;">序号</th>
	                        <th>详细描述</th>
	                        <th style="width: 200px;">操作人</th>
	                        <th style="width: 250px;">操作时间</th>
	                    </tr>
	                    <c:forEach items="${Logcontent}" var="log" varStatus="index">
	                        <tr>
	                            <td>${index.count}</td>
	                            <td style="text-align:left">${log.description}</td>
	                            <td>${log.userName}<c:if test="${!empty log.userCode}">(${log.userCode})</c:if></td>
	                            <td>${log.createDate}</td>
	                        </tr>
	                    </c:forEach>
	                </table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
