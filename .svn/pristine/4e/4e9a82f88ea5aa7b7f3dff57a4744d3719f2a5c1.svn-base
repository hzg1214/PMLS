<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/fangyou/fangyou.js?_v=${vs}"></script>
</head>

<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId" />
</jsp:include>

<div class="container " role="main">
	<div class="crumbs">
		<ul>
			<li><a href="#"  class="a_hover">公司管理</a></li>
			<li><a href="#"  class="a_hover">>公司详情</a></li>
			<li><a href="#"  class="a_hover">>房友账号</a></li>
		</ul>
	</div>
    <div class="row article">

        <!-- 左侧菜单 -->
        <jsp:include page="/WEB-INF/page/company/companyLeftMenu.jsp" flush="true">
            <jsp:param value="110406" name="leftMenuSelectId" />
        </jsp:include>

        <div class="col-md-10 content">
            <div class="page-content">
            
            	<!-- 异步加载公司基本信息
                <div id="load_content">
		           <div id="LoadBaseInfo"></div>
		        </div> -->
		        
                <h4 class="border-bottom pdb10">
                    <strong>账号</strong>
                    <a type="button" class="btn btn-primary" href="${ctx}/companys?searchParam=1">返回</a>
                </h4>
                
                <span class="small">账号数：<em id="J_showAccountCount"></em>
                    <c:if test="${!empty companyInfo.predictAccountCount}">
                        /${companyInfo.predictAccountCount}
                    </c:if>
                </span>
                <c:if test="${! empty companyInfo.openTime}">
                	<span class="small" style="margin-left:20px;">
                		账号创建时间：${companyInfo.openTime}
                	</span>
                </c:if>
                <shiro:hasPermission name="/fangyou/account:COMPANY_FY_CREAT">
                   <%--  <c:if test="${accountInfo == null}">没有房友账号才显示此按钮
	                    <div class="float-right pdb10">
	                    	<a type="button" class="btn btn-primary" style="float:right; padding:5px 10px; line-height:1" onclick="createFangyou(${companyId},${userCreate});">创建账号</a>
	                    </div>
                   	</c:if> --%>
                </shiro:hasPermission>
               <!--  <div class="float-right pdb10">
                    <button type="button" class="btn btn-primary" id="J_changePassword" style="display: none;">重置管理员密码</button>
                </div> -->
                <div class="" style="height: auto;">
                    <form id="fangyouAccountForm">
                        <input type="hidden" name="companyId" id="companyId" value="${companyId}">
                        <input type="hidden" name="userCreate" id="userCreate" value="${userCreate}">
                        <!-- 异步加载列表内容 -->
                        <div id="load_content">
                            <div id="loadAccountList"></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>

</body>

</html>
