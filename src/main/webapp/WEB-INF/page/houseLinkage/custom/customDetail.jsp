<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<%-- <script type="text/javascript" src="${ctx}/meta/js/contract/contract.js?_v=${vs}"></script>  --%>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>


<form id = "CustomViewForm" >
 	<input type = "hidden"  id = "id"   name = "id" value = "${customInfo.report.id}"/>
	<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4>
			    	<strong>基本信息</strong>
			    	<a href="${ctx}/custom?searchParam=1" class="btn btn-primary">返回</a>
			    </h4>
			    <div class="panel panel-default">
			    	<div class="panel-body">
			    		<div class="form-inline">
					      <div class="form-group">
								<label class="">${customInfo.report.customerNm}：</label>${customInfo.report.customerTel}
							</div>
					    </div>
			    	</div>
			    </div>
			    <h4>
			   		 <strong>报备记录</strong>
			    </h4>
			    <table class="table table-striped table-hover table-bordered">
			      <tr>
			        <th>报备楼盘</th>
			        <th>最新进度</th>
			        <th>状态</th>
			        <th>公司</th>
			        <th>跟进日</th>
			      </tr>
			      <c:forEach var="c" items="${customInfo.reportDetails}">
			        <tr>
			          <td>${c.estateNm}</td>
			          <td>${c.progressNm}</td>
			          <td>${c.confirmStatusNm}</td>
			          <td>${c.companyNm}</td>
			          <td>${c.followDate}</td>
			        </tr>
			      </c:forEach>
			    </table>
  			</div>
    	</div>
  </div>

</form>





</body>

</html>
