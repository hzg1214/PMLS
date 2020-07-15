<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/store/storeLogList.js?_v=${vs}"></script> 
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

 <div class="container" role="main">
 		<div class="crumbs">
			<ul>
				<li><a href="#"  class="a_hover">门店管理</a></li>
				<li><a href="#"  class="a_hover">>门店详情</a></li>
				<li><a href="#"  class="a_hover">>修改日志</a></li>
			</ul>
		</div>
        <div class="row article">
        
        <!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/store/storeLeftMenu.jsp" flush="true">
				<jsp:param value="110408" name="leftMenuSelectId" />
			</jsp:include>
			
			<div class="col-md-10 content">
        
            <div class="page-content">

				<h4>
					<strong>修改日志</strong>
					 <a type="button" class="btn btn-primary" href="${ctx}/store?searchParam=1">返回</a>
				</h4>
                <!-- 搜索条件区域 -->
                <form id="storeLogListForm">
                
                    <input type="hidden" name="storeId" id="storeId" value="${storeId}">

                <!-- 异步加载列表内容 -->
                <div id="load_content">
		           <div id="LoadCxt"></div>
		        </div>
		        
               </form> 
                
            </div>
       
            </div>      
       
        </div>
    </div>
    
</body>

</html>
