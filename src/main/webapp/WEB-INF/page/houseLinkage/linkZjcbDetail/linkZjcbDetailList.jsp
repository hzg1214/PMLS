<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/houseLinkage/linkZjcbDetail/linkZjcbDetail.js?_v=${vs}"></script>
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
            <li role="presentation">
                <a href="${ctx}/linkMarginDetail">保证金&诚意金</a>
<!--                 <a>保证金&诚意金</a> -->
            </li>
			<li role="presentation" class="active" >
                <a href="${ctx}/linkZjcbDetail">垫佣</a>
            </li>
        </ul>
        <div class="page-content">
            <form id="LinkZjcbDetailListForm">

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">

                            <div class="form-group">
                                <label class="lab">架构年份：</label>
                                <select class="multi-select-text" id="organization" name="organization"
                                        style="width: 180px; ">
                                    <option value="2019">2019</option>
                                    <option value="2018">2018</option>
                                    <option value="2017">2017</option>
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
                            
                            <div class="form-group" name="group3">
                                <label class="lab">所在城市：</label>
                                <div class="multi-select" id="city" name="city" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="cityIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 180px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            
                        </div>
                        <div class="form-inline">
                        
                        	<div class="form-group" name="group4">
                                <label class="lab">归属中心：</label>
                                <div class="multi-select" id="centerGroup" name="centerGroup" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="centerIds">
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
                                <label style="width:90px;text-align:right;">项目：</label>
                                <%--<script type="text/javascript">--%>
                                    <%--$(document).ready(function () {--%>
                                        <%--$("#estateNm").multiselect({--%>
                                            <%--enableFiltering: true,--%>
                                            <%--filterPlaceholder: '请输入楼盘名称',--%>
                                            <%--buttonWidth: '180px',--%>
                                            <%--nonSelectedText: '请选择',--%>
                                            <%--nSelectedText: '个楼盘被选中',--%>
                                            <%--includeSelectAllOption: false,--%>
                                            <%--selectAllText: '全选/取消',--%>
                                            <%--allSelectedText: '已选中所有楼盘',--%>
                                            <%--selectedClass: 'active1',--%>
                                            <%--numberDisplayed: 10,--%>
                                            <%--dropRight: true,--%>
                                            <%--maxHeight: 275,--%>
                                            <%--dropUp: true,--%>
                                            <%--onChange: function (element, checked) {--%>
                                                <%--var brands = $('#estateNm option:selected');--%>
                                                <%--var selected = [];--%>
                                                <%--$(brands).each(function (index, brand) {--%>
                                                    <%--selected.push(['\'' + $(this).val() + '\'']);--%>
                                                <%--});--%>
                                                <%--$("#estateNmKey").val(selected);--%>
                                                <%--console.log(selected);--%>
                                            <%--}--%>
                                        <%--});--%>
                                        <%--//控制select显示--%>
                                        <%--$("span.multiselect-selected-text").css({--%>
                                            <%--"text-align": 'left',--%>
                                            <%--display: 'inline-block'--%>
                                        <%--});--%>
                                        <%--$("b.caret").css("margin-left", "106px");--%>
                                        <%--//图标颜色--%>
                                        <%--$(".input-group-btn i").css(--%>
                                        	<%--"color","#4169E1"--%>
                                        <%--);--%>
                                        <%--//图标颜色--%>
                                        <%--$(".input-group-addon i").css(--%>
                                        	<%--"color","#4169E1"--%>
                                        <%--);--%>
                                    <%--});--%>
                                <%--</script>--%>
                                <%--<select class="form-control" style="width:150px;" id="estateNm" name="estateNm"--%>
                                        <%--multiple="multiple">--%>
                                    <%--<c:forEach items="${estateList}" var="list">--%>
                                        <%--<option value="${list}">${list}</option>--%>
                                    <%--</c:forEach>--%>
                                <%--</select>--%>
                                <input type="text"  class="form-control w200" id="estateNm" name="estateNm"
                                       placeholder="请输入项目名称、编号" style="width:180px;">

                                <span class="fc-warning"></span>
                            </div>
<!--                             <div class="form-group"> -->
<!--                                 <label>项目编号：</label> -->
<!--                                 <input type="text" class="form-control w200" id="projectNo" name="projectNo" -->
<!--                                        placeholder="请输入项目编号" style="width:180px;"> -->
<!--                             </div> -->
                            <div class="form-group">
                                <label>报备编号：</label>
                                <input type="text" class="form-control w200" id="reportId" name="reportId"
                                       placeholder="请输入报备编号" style="width:180px;">
                            </div>
<!--                             <div class="form-group"> -->
<!--                                 <label>查询日期：</label> -->
<!--                                 <input type="text" class="calendar-icon form-control w100" name="dateStart" -->
<!--                                        id="dateStart" style="width: 120px" -->
<!--                                        onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" -->
<!--                                        readonly="readonly" class="ipttext Wdate"/> -->
<!--                                 <span>-</span> -->
<!--                                 <input type="text" class="calendar-icon form-control w100" name="dateEnd" -->
<!--                                        id="dateEnd" style="width: 120px" -->
<!--                                        onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" -->
<!--                                        readonly="readonly" class="ipttext Wdate"/> -->
<!--                             </div> -->

                            <div class="form-group">
                                <label style="width:90px;text-align:right;">期初：</label>
                                <input type="text" class="calendar-icon form-control w100" name="dateStage"
                                       id="dateStage" style="width: 180px"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                            </div>
                        </div>
                        <div class="form-inline">
                            <div class="pull-right">
                                <button type="button" class="btn btn-primary" id="J_search"
                                        onclick="javascript:LinkZjcbDetail.search();">查询
                                </button>
                                <button type="button" class="btn btn-primary" id="btn-reset"
                                        onclick="javascript:LinkZjcbDetail.export()">导出
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
