<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp"%>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
    <link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/meta/js/report/expand/multi.select.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/report/preserve/storePreserveSummaryListCtx.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>

</head>
<style type="text/css">
    table th{
        text-align: center;
    }
    .form-inline{
        overflow:inherit;
    }
</style>

<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId" />
</jsp:include>

<div class="container theme-hipage ng-scope" role="main" id="contentAll">
    <div class="row">
        <div class="page-content">
            <form  id="preservedetail">
                <h4 class="border-bottom pdb10"><strong>维护门店汇总</strong></h4>
                <div class="panel panel-default" style="margin-bottom:10px;">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="form-group">
                                <label class="lab">架构年份：</label>
                                <select class="multi-select-text" id="organization" name="organization"
                                        style="width: 140px; ">
                                    <option value="2019">2019</option>
                                    <option value="2018">2018</option>
                                </select>
                            </div>
                            <div class="form-group" name="group1">
                                <label class="lab">归属区域：</label>
                                <div class="multi-select" id="region" name="region" style="width: 140px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="regionCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 140px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group2">
                                <label class="lab">归属城市：</label>
                                <div class="multi-select" id="areaCity" name="areaCity" style="width: 140px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="areaCityCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 140px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group3">
                                <label class="lab">所在城市：</label>
                                <div class="multi-select" id="city" name="city" style="width: 140px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="cityIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 140px;">
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
                                <div class="multi-select" id="centerGroup" name="centerGroup">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="centerIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 140px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="lab">归属个人：</label>
                                <input type="text" class="multi-select-text"  style="width: 140px;height: 34px" id="personal" name="personal"/>
                            </div>
                            <div class="form-group">
                                <label class="lab">查询年份：</label>
                                <select class="multi-select-text" id="year" name="year"
                                        style="width: 140px; " onchange="monitorYear(this)" >
                                </select>
                            </div>
                            <div class="form-group" id="selectQuarter">
                                <label class="lab">查询季度：</label>
                                <div class="multi-select" id="quarter" name="quarter" style="width: 140px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="quarterIds" id="quarterIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 140px;" id="quarterText">
                                    <ul class="multi-select-list" id="quarterSelect">
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="form-inline">
                            <div class="form-group">
                                <label class="lab">门店筛选：</label>
                                <select class="multi-select-text" id="shopState" name="shopState"
                                        style="width: 140px; ">
                                    <option value="1">过审</option>
                                    <option value="2">验收</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="lab">导出是否到个人：</label>
                                <select class="multi-select-text" id="isPerson" name="isPerson"
                                        style="width: 140px; ">
                                    <option value="1">否</option>
                                    <option value="2">是</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-inline">
                            <div class="col-md-10">
                            </div>
                            <div class="col-md-2">
                                <button type="button" class="btn btn-primary" id="search"
                                        onclick="searchStorePreserve()">搜索
                                </button>
                                &nbsp;
                                <button type="button" class="btn btn-primary" id="btn-reset"
                                        onclick="exportPreserveDetail()">导出
                                </button>
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
</body>

</html>
