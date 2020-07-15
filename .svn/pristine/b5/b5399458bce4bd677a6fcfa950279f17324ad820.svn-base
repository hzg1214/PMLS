<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/report/excelTask/excelTask.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>导出文件列表</strong></h4>
                <form  id="excelTasktForm" action="${ctx}/excelTask/export" method="post">
                    <input  type="hidden" id="fileNameId" name="fileName" value=""></input>
                    <input  type="hidden" id="filePathId" name="filePath" value=""></input>
                
					<div class="panel panel-default">
						<div class="panel-body" style="padding-bottom:5px;">
							<div class="form-inline">
                                <div class="form-group">
                                    <label class="sr-only">Name</label>
                                    <input type="text" class="form-control" id="SearchfileName" name="SearchfileName" placeholder="请输入文件名" value="">
                                </div>
                               
                                <button type="button" class="btn btn-primary" id="J_search" onclick="Search()">查询</button>
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

</html>
