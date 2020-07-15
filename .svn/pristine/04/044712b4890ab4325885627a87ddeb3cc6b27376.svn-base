<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/houseLinkage/linkMarginDetail/linkMarginDetail.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/bootstrap-multiselect.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/bootstrap-multiselect.js?_v=${vs}"></script>
    <style type="text/css">
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


<div class="container theme-hipage ng-scope" role="main" id="contentAll">
   <h4 class="border-bottom pdb10" style="width: 1170px;margin-left: -15px;"><strong>联动资金成本</strong></h4>
    <div class="row">
    	<ul class="nav nav-tabs nav-tabs-header" style="margin-bottom: 20px;">
            <li role="presentation" class="active">
                <a href="${ctx}/linkMarginDetail">保证金&诚意金</a>
<!--                 <a>保证金&诚意金</a> -->
            </li>
			<li role="presentation" >
                <a href="${ctx}/linkZjcbDetail">垫佣</a>
            </li>
        </ul>
        <div class="page-content">
            <form id="LinkMarginDetailListForm">

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">

                            <div class="form-group" onchange="changeOrganization(this)" >
                                <label class="lab">架构年份：</label>
                                <select class="multi-select-text" id="organization" name="organization"
                                        style="width: 157px; ">
                                    <option value="2019">2019</option>
                                    <option value="2018">2018</option>
<!--                                     <option value="2017">2017</option> -->
                                </select>
                            </div>
                            
                            <div class="form-group" name="group1">
                                <label class="lab">归属区域：</label>
                                <div class="multi-select" id="region" name="region" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="regionCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 180px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            
                            <div class="form-group" name="group2">
                                <label class="lab">归属城市：</label>
                                <div class="multi-select" id="areaCity" name="areaCity" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="areaCityCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 180px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group">
                                <label style="width:90px;text-align:right;">项目名称：</label>
                                <input type="text"  class="form-control w200" id="estateNm" name="estateNm"
                                       placeholder="请输入项目名称、编号" style="width:180px;">

                                <span class="fc-warning"></span>
                            </div>
                        </div>
                        <div class="form-inline">
                            <div class="form-group"  name="costCenter">
                                <label class="lab">成本中心：</label>
	                            <div class="multi-select" id="cost" name="cost" style="width: 157px;">
	                                <input type="hidden" class="multi-select-value" readonly="readonly"
	                                       name="costCodes">
	                                <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
	                                       style="width: 157px;">
	                                <ul class="multi-select-list" style="height: 250px;">
	                                    <li class="multi-select-item">
	                                        <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
	                                    </li>
	                                </ul>
	                            </div>
                            </div>
                            <div class="form-group" style="margin-left: 27px;"><label>类型：</label>
                            	<t:dictSelect field="marginTypeCode" id="marginTypeCode" xmlkey="LOOKUP_Dic" dbparam="P1:267" nullOption="请选择" classvalue="10" txWidth="180px"></t:dictSelect>
                        	</div>
                        	<div class="pull-right">
                                <button type="button" class="btn btn-primary" id="J_search"
                                        onclick="javascript:LinkMarginDetail.search();">查询
                                </button>
                                <button type="button" class="btn btn-primary" id="btn-reset"
                                        onclick="javascript:LinkMarginDetail.export()">导出
                                </button>
                            </div>
                        </div>

                        <div class="form-inline">
                            <li class="three_li" style="width: 650px">
                                <label class="lab" style="width: 200px;font-size: 12px;font-weight: bold;color: red">您当前有<span id="reportSize">${userReportSize}</span>个文件正在下载中……</label>
                            </li>
                            <li class="three_li" style="width: 150px">

                            </li>
                            <li class="three_li" style="width: 100px">

                            </li>
                            
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
