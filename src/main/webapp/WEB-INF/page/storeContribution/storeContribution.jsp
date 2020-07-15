<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/meta/js/report/expand/multi.select.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/storeContribution/storeContribution.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <style type="text/css">
        table th {
            text-align: center;
            background-color:#EEE;
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

<div class="container theme-hipage ng-scope" role="main" id="contentAll">
    <div class="row">
        <div class="page-content">
            <form id="storeContributionForm">
                <h4 class="border-bottom pdb10"><strong>门店贡献分析表</strong></h4>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="form-group" style="width: 275px; margin-left: 0px;">
                                <label class="lab" >架构年份：</label>
                                <select class="multi-select-text" id="organization" name="organization"
                                        style="width: 180px; height: 24px; margin-left: 0px; font-size: 12px;">
                                    <option value="2019">2019</option>
                                    <option value="2018">2018</option>
                                    <option value="2017">2017</option>
                                </select>
                            </div>

                            <div class="form-group" name="group1" style="width: 275px; margin-left: 0px;">
                                <label class="lab" >归属区域：</label>
                                <div class="multi-select" id="region" name="region" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="regionCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 180px; height: 24px; margin-left: 0px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group2" style="width: 275px; margin-left: 0px;">
                                <label class="lab" >归属城市：</label>
                                <div class="multi-select" id="areaCity" name="areaCity" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="areaCityCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 180px; height: 24px; margin-left: 0px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group3" style="width: 275px; margin-left: 0px;">
                                <label class="lab" >所在城市：</label>
                                <div class="multi-select" id="city" name="city" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="cityIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 180px; height: 24px; margin-left: 0px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="form-inline">
                            <div class="form-group" name="group4" style="width: 275px; margin-left: 0px;">
                                <label class="lab" >归属中心：</label>
                                <div class="multi-select" id="centerGroup" name="centerGroup" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="centerIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 180px; height: 24px; margin-left: 0px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="form-group" id="stage" style="width: 275px; margin-left: 0px;">
                                <label class="lab" 275>业务类型：</label>
                                <div class="multi-select" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="stages">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 180px; height: 24px; margin-left: 0px;"/>
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" value="ldItem" data-text="联动"><span>联动</span></label>
                                        </li>
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" value="gpItem" data-text="公盘"><span>公盘</span></label>
                                        </li>
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" value="jyItem" data-text="交易"><span>交易</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" style="width: 300px; margin-left: 0px;">
                                <label class="lab" >查询日期：</label>
                                <input type="text" size="11" class="calendar-icon form-control" name="startDate"
                                       id="startDate"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                                       readonly="readonly" class="ipttext Wdate"
                                       onchange="checkDate(this)"
                                       style="width: 95px; height: 24px; margin-left: 0px; padding-left: 5px; font-size: 12px;"/>
                                -
                                <input type="text" size="11" class="calendar-icon form-control" name="endDate"
                                       id="endDate"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                                       readonly="readonly" class="ipttext Wdate"
                                       onchange="checkDate(this)"
                                       style="width: 95px; height: 24px; margin-left: 0px; padding-left: 5px; font-size: 12px;"/>
                            </div>

                            <div class="form-group" style="width: 250px; margin-left: 0px;  float: right" id="searchDiv">
                                <button type="button" class="btn btn-primary btn-flat" id="btn-search"
                                        style="height: 25px; margin-top: 0px; line-height: 5px; margin-left: 14px;"
                                        onclick="javascript:storeContribution.search();">查询
                                </button>
                                <button type="button" class="btn btn-primary btn-flat" id="btn-export"
                                        style="height: 25px; margin-top: 0px; line-height: 5px;margin-left: 14px;"
                                        onclick="javascript:storeContribution.export();">导出
                                </button>
                            </div>
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
</html>
