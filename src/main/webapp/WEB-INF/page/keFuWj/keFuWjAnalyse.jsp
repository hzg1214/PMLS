<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp" %>
<%--     <%@ include file="/WEB-INF/page/common/jsCss.jsp"%> --%>
	<link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/meta/js/report/expand/multi.select.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/bootstrap-multiselect.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/bootstrap-multiselect.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/keFuWj/keFuWjAnalyse.js?_v=${vs}"></script>
    <link href="${ctx}/meta/css/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctx}/meta/js/common/jquery.searchableSelect.js"></script>
</head>
<style>
table th {
        text-align: center;
    }
    input::-webkit-input-placeholder {
       font-size: 9px;
    }
     .form-inline{  
      	overflow:inherit;  
      } 
</style>

<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>

<div class="container theme-hipage ng-scope" role="main">
<h4 class="border-bottom pdb10" style="margin-right: -15px;margin-left:-15px;"><strong>问卷分析</strong></h4>
    <div class="row">
        <ul class="nav nav-tabs nav-tabs-header">
            <li role="presentation" class="active">
                <a href="${ctx}/keFuWjAnalyse">满意度分析</a>
            </li>
            <li role="presentation">
                <a href="${ctx}/keFuWjEvaluation">门店测评</a>
            </li>
        </ul>
        <div class="page-content">
        <form id="keFuWjForm">
            <input type="hidden" name="searchParmCache" value="1">
            <input type="hidden" id="curPageTemp" value="1">
            <input type="hidden" id="sysPageChaneTemp" value="1">
            <div class="panel panel-default" style="margin-bottom:10px;">
                <div class="panel-body">
                    <div class="form-inline">
                        <div class="form-group" id="businessTypeId">
                            <label class="">查询周期</label>
                            <select class="form-control" id="year" name="year" style="width:120px;">
                                <option value="">请选择年份</option>
                                <c:forEach items="${yearList}" var="year">
                                    <option value="${year}">${year}</option>
                                </c:forEach>
                            </select>
                            <select class="form-control" id="quarter" name="quarter" style="width:120px;">
                                <option value="">请选择季度</option>
                                <option value="1">第一季度</option>
                                <option value="2">第二季度</option>
                                <option value="3">第三季度</option>
                                <option value="4">第四季度</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>归属城市</label>
                            <select class="form-control" title="" id="affiliationCityNo" name="affiliationCityNo" style="width:177px;">
                                <option value="" selected="selected">请选择</option>
                                <c:forEach items="${affiliationCitylist}" var="affiliationCity">
                                    <option value="${affiliationCity.cityNo}">${affiliationCity.cityName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>所在城市</label>
                            <select class="form-control" title="" id="placeCityNo" name="placeCityNo" style="width:177px;">
                                <option value="" selected="selected">请选择</option>
                                <c:forEach items="${placeCitylist}" var="placeCity">
                                    <option value="${placeCity.cityNo}">${placeCity.cityName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>门店</label>
                            <input type="text" class="form-control" id="store" name="store" style="width:210px;" placeholder="门店编号、门店名称" value="">
                        </div>
                    </div>
                    <div class="form-inline">
                        <div class="form-group">
                            <label >经纪公司</label>
                            <input type="text" class="form-control" id="company" name="company" style="width:210px;" placeholder="公司编号、公司名称" value="">
                        </div>
                        <div class="form-group"  name="wjTypeCenter">
                            <label class="lab">问卷状态</label>
                            <div class="multi-select" id="wjType" name="wjType" style="width: 157px;">
                                <input type="hidden" class="multi-select-value" readonly="readonly"
                                       name="wjTypes">
                                <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                       style="width: 157px;">
                                <ul class="multi-select-list" style="height: 130px;">
                                    <li class="multi-select-item">
                                        <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                    </li>
                                </ul>
                            </div>
                        </div>
                   </div>
                   <div class="form-inline">
                        <div class="col-md-3" style="float: right;padding-left: 105px;">
                            <a class="btn btn-primary" style="margin-left:35px;" href="javascript:keFuWjAnalyse.exportWj();" id="btn-output">导出</a>
                            &nbsp;
                            <a type="button" class="btn btn-default" onclick="javascript:keFuWjAnalyse.reset();">重置</a>
                        </div>
                   </div>
                </div>
            </div>
        </form>
    </div>
    </div>
</div>
<iframe name="hiddenFrame" id="hiddenFrame" style="display:none">
</iframe>
<script>
    $(function(){
        $('#affiliationCityNo').searchableSelect();
        $('#placeCityNo').searchableSelect();
        $(".searchable-select").css("z-index",100);
    });
</script>
</body>
</html>
