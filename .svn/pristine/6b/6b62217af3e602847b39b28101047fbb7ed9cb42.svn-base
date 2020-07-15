<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<!-- 引入js -->
<script type="text/javascript"
	src="${ctx}/meta/js/common/head.js?_v=${vs}"></script>

<nav class="navbar navbar-default navbar-static-top bg-nav-blue">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand fon-white" href="#">房友CRM</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<!-- 岗位list循环 -->
				<c:forEach items="${userInfo.postList}" var="postList">
					<!-- 用户选中岗位Id匹配 -->
					<c:if test="${userInfo.selectpostId eq postList.postId}">
						<!-- 权限信息- 菜单 -->
						<c:forEach items="${postList.authList}" var="list">
							<!-- 选中一级 -->
							<c:if test="${list.parentId eq 0}">
								<li id="nav${list.authId}" class="dropdown">
									<a href="#" class="fon-white">${list.authName}<span class="caret"></span></a>
										<ul class="dropdown-menu">
											<c:forEach items="${postList.authList}" var="secendlist">
											  <c:if test="${secendlist.parentId eq list.authId}">
													<li  onclick="menuClick(${secendlist.parentId}, '${secendlist.authName}','${secendlist.url}');">
														<a href="javascript:void(0)">${secendlist.authName}</a>
													</li>
												</c:if>
											</c:forEach>

                                            <%--<li  onclick="menuClick(119, '维护门店汇总','/storePreserve/storePreserveSummary');">--%>
                                                <%--<a href="${ctx}/storePreserve/storePreserveSummary">维护门店汇总</a>--%>
                                            <%--</li>--%>
										</ul>
									</li>
							</c:if>
						</c:forEach>
						<!-- 权限信息-菜单 -->
					</c:if>
					<!-- 用户选中岗位Id匹配 -->
				</c:forEach>
				<!-- 岗位list循环 -->
			</ul>
			
				<!-- Add By tong 2017/04/07 Start -->
			<%-- <ul class="nav navbar-nav">
			    <li class="dropdown"><a href="" class="dropdown-toggle fon-white" data-toggle="dropdown"
					role="button" aria-haspopup="true" aria-expanded="false">统计分析<span class="caret"></span></a>
						<ul class="dropdown-menu">
						<!-- 后期工作 
							<li><a href="${ctx}/expendReport/expandReportDate">拓展汇总</a></li>
						-->	
							<li><a href="${ctx}/expendReport/expandDetailList">拓展明细</a></li>
							<li><a href="${ctx}/expendReport">导出文件列表</a></li>
						</ul>
				</li>
			</ul> --%>
			<!-- Add By tong 2017/04/07 End -->
			
			<ul class="nav navbar-nav navbar-right">
				<li><a class="fon-white" role="button" aria-haspopup="true" aria-expanded="false">
						${userInfo.userName}
				    </a>
				</li>
				<!-- 岗位选择 -->
				<li id="nav202"><a class="fon-white"
					role="button" aria-haspopup="true" aria-expanded="false">
						${userInfo.selectPost.postName}(${userInfo.selectPost.cityName})<span class="caret"></span>
					</a>
					<c:if test="${userInfo.postList != null && fn:length(userInfo.postList) > 1}">
					<input type="hidden" name="menuPermissionLength" id="menuPermissionLength" value="${fn:length(userInfo.postList)}">
					<script type="text/javascript">
						$(document).ready(function(){
							var dataLenth = parseInt($("#menuPermissionLength").val(), 10);
								 if(dataLenth >  10){
									 $("ul.permissionShow").css("height","265px"); 
									 $("ul.permissionShow").css("overflow","auto"); 
							}
						});
					</script>
					<ul class="dropdown-menu permissionShow">
						<c:forEach items="${userInfo.postList}" var="u">
							<c:if test="${u.postId ne userInfo.selectPost.postId}">
								<li><a href="javascript:postChange(${u.postId})">${u.postName}(${u.cityName})</a></li>
							</c:if>
						</c:forEach>
					</ul>
					</c:if>
				</li>
				<li class="open-hover"><a onclick="toFeeback()" href="javascript:void(0);" class="fon-white">意见反馈</a></li>
				<li class="open-hover"><a href="${ctx}/logout" class="fon-white">退出</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>

<script>
	var navSelectId = '${param.navSelectId}';
</script>
