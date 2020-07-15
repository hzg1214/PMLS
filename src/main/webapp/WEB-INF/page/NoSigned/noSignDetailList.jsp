<%@ page language="java" contentType="text/html; charset=UTF-8"
         import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/meta/js/report/expand/multi.select.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/noSigned/noSignDetailList.js?_v=${vs}"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.0&ak=${sysConfig.baiduApiKey}"></script>
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


<form id="storeListForm">
    <%--<input type="hidden" id="storeCityList" name="storeCityList" value="">--%>
    <%--<input type="hidden" id="dueCause" name="dueCause" value=""></input>--%>
    <div class="container theme-hipage ng-scope" id="contentAll">
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10">
                    <strong>未签门店明细表</strong>
                </h4>
                <div class="panel panel-default" style="margin-bottom:10px;">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="form-group" >
                                <label class="lab" >架构年份：</label>
                                <select class="multi-select-text" id="organization" name="organization" style="width: 100px; ">
                                    <option value="2019">2019</option>
                                    <option value="2018">2018</option>
                                    <option value="2017">2017</option>
                                </select>
                            </div>

                            <div class="form-group" name="group1" >
                                <label class="lab">归属区域：</label>
                                <div class="multi-select" id="region" name="region" style="width: 170px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="regionCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 170px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group2" >
                                <label class="lab">归属城市：</label>
                                <div class="multi-select" id="areaCity" name="areaCity" style="width: 131px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="areaCityCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 131px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group3">
                                <label class="lab">所在城市：</label>
                                <div class="multi-select" id="city" name="city" style="width: 131px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="cityIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 131px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group4">
                                <label class="lab" >归属中心：</label>
                                <div class="multi-select" id="centerGroup" name="centerGroup" style="width: 131px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="centerIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 131px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="form-inline">
                            <div class="form-group" name="group5">
                                <label>门店城市：</label>
                                <div class="multi-select" id="cityNo" name="cityNo">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="cityNos">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 100px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="lab">门店信息：</label>
                                <input type="text" class="form-control" id="searchKey"
                                       name="searchKey"
                                       style="width:170px;padding-left: 5px;"
                                       placeholder="编号/名称/地址/维护人" value="">
                            </div>
                            <div class="form-group" id="contractStop">
                                <label class="lab">结束原因：</label>
                                <div class="multi-select"  style="width: 131px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="dueCause">
                                    <input type="text" class="multi-select-text" readonly="readonly"
                                           placeholder="请选择" style="width: 131px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item"><label><input
                                                type="checkbox" class="multi-select-checkall"
                                                value="作废,终止,合同到期,撤销,未签"><span>全部</span></label>
                                        </li>
                                        <li class="multi-select-item"><label><input
                                                type="checkbox" value="作废" data-text="作废"><span>作废</span></label>
                                        </li>
                                        <li class="multi-select-item"><label><input
                                                type="checkbox" value="终止" data-text="终止"><span>终止</span></label>
                                        </li>
                                        <li class="multi-select-item"><label><input
                                                type="checkbox" value="合同到期" data-text="合同到期"><span>合同到期</span></label>
                                        </li>
                                        <li class="multi-select-item"><label><input
                                                type="checkbox" value="撤销" data-text="撤销"><span>撤销</span></label>
                                        </li>
                                        <li class="multi-select-item"><label><input
                                                type="checkbox" value="未签" data-text="未签"><span>未签</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!-- 添加录入时间 -->
                            <div class="form-group">
                                <label class="lab">创建日期：</label>
                                <div class="input-group date">
                                    <input type="text" size="11"
                                           class="calendar-icon form-control" id="dateCreateStart"
                                           name="dateCreateStart"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                                           readonly="readonly" class="ipttext Wdate"
                                           onchange="" style="width: 131px;"/>
                                </div>
                                <span>-</span>
                                <div class="input-group date">
                                    <input type="text" size="11"
                                           class="calendar-icon form-control" id="dateCreateEnd" name="dateCreateEnd"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                                           readonly="readonly" class="ipttext Wdate"
                                           onchange="" style="width: 131px;"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-inline">
                            <div class="form-group" style="padding-left: 1005px; margin-top: 10px;">
                                <button type="button" class="btn btn-primary btn-flat" id="search"
                                        onclick="searchList()">搜索
                                </button>
                                <button type="button" class="btn btn-primary" id="btn-reset" onclick="noSignExport()">
                                    导出
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="load_content">
                    <div class="" style="height: auto;" id="LoadCxt"></div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
