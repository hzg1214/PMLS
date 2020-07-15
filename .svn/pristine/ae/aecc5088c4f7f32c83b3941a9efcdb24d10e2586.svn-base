<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/accountproject/accountProject.js?_v=${vs}"></script>
	<link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/meta/js/report/expand/multi.select.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/bootstrap-multiselect.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/bootstrap-multiselect.js?_v=${vs}"></script>
    <link href="${ctx}/meta/css/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctx}/meta/js/common/jquery.searchableSelect.js"></script>
    <style type="text/css">
    table th{ 
	text-align: center; 
}
        .form-inline {
            overflow: inherit;
        }
    </style>
</head>

<body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>

<div class="container theme-hipage ng-scope" role="main">
            <h4 class="border-bottom pdb10"><strong>核算主体维护</strong></h4>
    <div class="row">
<!--     	<ul class="nav nav-tabs nav-tabs-header"> -->
<!-- 			<li role="presentation"  > -->
<%--                 <a href="${ctx}/yfCenterUser">业务人员维护</a> --%>
<!--             </li> -->
<!--             <li role="presentation" class="active"> -->
<%--                 <a href="${ctx}/mCenterUser">管理人员维护</a> --%>
<!--             </li> -->
<!--         </ul> -->
        <div class="page-content">
            <form id="accountProjectListForm">
<!--                 <input type="hidden" name="searchParmCache" value="1"> -->
<!--                 <input type="hidden" id="curPageTemp" value="1"> -->
<!--                 <input type="hidden" id="sysPageChaneTemp" value="1"> -->

                <div class="panel panel-default" style="margin-bottom:10px;" id="contentAll">
                    <div class="panel-body">
                        <div class="form-inline">
                                <div class="form-group">
                                    <label style="margin-left:15px;">归属城市</label>
                                    <select class="form-control" title="" id="cityNo" name="cityNo"
                                            style="width:100px;" >
                                        <option value="" selected="selected">请选择</option>
                                        <c:forEach items="${citylist}" var="city">
                                            <option value="${city.cityNo}">${city.cityName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>核算主体</label>
                                    <input type="text" class="form-control" id="searchKey" name="searchKey"
                                           placeholder="核算主体名称、编码" value="">
                                </div>

                                <button type="button" class="btn btn-primary" id="J_search"
                                        onclick="javascript:AccountProject.search();">查询
                                </button>
                                <button type="button" id="btn-reset" class="btn btn-default"
                                        onclick="javascript:AccountProject.reset()">重置
                                </button>

                                <button type="button" class="btn btn-primary" id="J_export"
                                        style="margin-left:40px;"
                                        onclick="javascript:AccountProject.create();">新建核算主体
                                </button>
                        </div>
                    </div>
                </div>
                <!-- 异步加载列表内容 -->
                <div id="load_content">
                    <div class="" style="height: auto;" id="LoadCxt"></div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var searchParamMap = '${searchParamMap}';
    console.log(searchParamMap);
    if (searchParamMap != undefined && searchParamMap != null && searchParamMap != '') {
        mCenterUser.setSearchParams(searchParamMap);
    }
</script>
</html>


