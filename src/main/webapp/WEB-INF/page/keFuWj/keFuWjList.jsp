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
    <script type="text/javascript" src="${ctx}/meta/js/keFuWj/keFuWjList.js?_v=${vs}"></script>
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
<h4 class="border-bottom pdb10" style="margin-right: -15px;margin-left:-15px;"><strong>问卷模板维护</strong></h4>
    <div class="row">
        <ul class="nav nav-tabs nav-tabs-header">
			<li role="presentation" class="active" id="presentation0">
				<a href="javascript:void(0);" id="taskType0">全部</a>
			</li>
			<li role="presentation"  id="presentation1">
				<a href="javascript:void(0);" id="taskType1">已启用</a>
			</li>
			<li role="presentation"  id="presentation2">
				<a href="javascript:void(0);" id="taskType2">草稿</a>
			</li>
			<li role="presentation"  id="presentation3">
				<a href="javascript:void(0);" id="taskType3">未启用</a>
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
                                <div class="form-group" style="margin-left: 40px;">
                                    <label>模板编号</label>
                                    <input type="text" class="form-control" id="wjCode" name="wjCode" style="width:210px;" placeholder="模板编号" value="">
                                </div>
                                <div class="form-group" style="margin-left: 60px;">
                                    <label>模板名称</label>
                                    <input type="text" class="form-control" id="wjName" name="wjName" style="width:210px;" placeholder="模板名称" value="">
                                </div>
                                <div class="form-group" name="city" style="margin-left: 60px;">
                                    <label>适用城市</label>
                                    <div class="multi-select" id="wjCity" name="wjCity" style="width: 235px;">
	                                    <input type="hidden" class="multi-select-value" readonly="readonly"
	                                           name="wjCitys">
	                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
	                                           style="width: 238px; height: 35px; margin-left: 0px;">
	                                           
	                                    <ul class="multi-select-list">
	                                        <li class="multi-select-item">
	                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
	                                        </li>
	                                    </ul>
	                                </div>
                                </div>
                            
                        </div>
                        <div class="form-inline">
                                <div class="form-group" style="margin-left: 40px;display:none">
                                	<label >问卷状态</label>
	                            	<t:dictSelect field="wjStatus" id="wjStatus" xmlkey="LOOKUP_Dic" dbparam="P1:247" nullOption="请选择" classvalue="10" txWidth="210px"></t:dictSelect>
	                        	</div>
                                <div class="form-group" style="margin-left: 20px;">
                                	<label >创建人</label>
	                            	<input type="text" class="form-control" id="createName" name="createName" style="width:210px;margin-left: 14px;" placeholder="姓名" value="">
	                        	</div>
                                <div class="form-group" style="margin-left: 60px;">
                                    <label>创建日期</label>
                                    <input type="text" size="11" class="calendar-icon form-control" name="dateCreate" id="dateCreate" readonly="readonly" style="width:140px;"  class="ipttext Wdate"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
                                    至
                                    <input type="text" size="11" class="calendar-icon form-control" name="dateCreateEnd" id="dateCreateEnd" readonly="readonly" style="width:140px;"  class="ipttext Wdate"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
                                </div>  
                       </div>
                       <div class="form-inline">
                            <div class="col-md-3" style="float: right;">
                                <a class="btn btn-primary" style="margin-left:35px;" href="javascript:KeFuWj.import();" id="btn-reset">导入</a>&nbsp;
                                <button type="button" class="btn btn-primary" id="J_search" onclick="javascript:KeFuWj.search();" style="margin-left:12px;">
                                    查询
                                </button>
                                &nbsp;
                                <a type="button" class="btn btn-default" onclick="javascript:KeFuWj.reset('${list_search_page}');">重置</a>
                            </div>
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
<form name="imputForm" id="imputForm" method="post" action="${pageContext.request.contextPath}/keFuWj/imput"
      target="hiddenFrame" enctype="multipart/form-data">
    <input type="file" id="historyDataFile" name="historyDataFile" accept=".xls,.xlsx" style="display:none">
<!--     <input type="hidden" id="estateTypeImput" name="estateTypeImput"> -->
</form>
<iframe name="hiddenFrame" id="hiddenFrame" style="display:none">
</iframe>
</body>
<script type="text/javascript">
    var searchParamMap = '${searchParamMap}';
    console.log(searchParamMap);
    if (searchParamMap != undefined && searchParamMap != null && searchParamMap != '') {
    	KeFuWj.setSearchParams(searchParamMap);
    }
    KeFuWj.tabClick();
</script>
</html>
