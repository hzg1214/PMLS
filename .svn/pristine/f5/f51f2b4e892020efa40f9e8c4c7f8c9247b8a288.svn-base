<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/performanceQuery/performanceQuery.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
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
        <div class="row">
            <div class="page-content">
                <form id="performanceQueryForm">
                    <h4 class="border-bottom pdb10"><strong>业绩查询</strong></h4>
                    <div class="panel panel-default">
                        <div class="panel-body" style="padding-bottom:10px;">
                            <div class="form-inline">
                                <div class="form-group">
                                    <label class="lab">架构年份：</label>
                                    <select class="multi-select-text" id="organization" name="organization"
                                            style="width: 140px; ">
                                        <option value="2019">2019</option>
                                        <option value="2018">2018</option>
                                        <option value="2017">2017</option>
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
                            </div>

                            <div class="form-inline">
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
                                <div class="form-group" style="margin-top: 5px">
                                    <label>时间范围：</label>
                                    <input type="text" size="11" class="calendar-icon form-control" name="startDate"
                                           id="startDate"
                                           readonly="readonly"
                                           onchange="checkDate(this)"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                           class="ipttext Wdate"/> 至
                                    <input type="text" size="11" class="calendar-icon form-control" name="endDate"
                                           id="endDate"
                                           readonly="readonly"
                                           onchange="checkDate(this)"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                           class="ipttext Wdate"/>
                                </div>
                            </div>

                            <div class="form-inline">
                                <div class="form-group">
                                    <label>汇总条件：</label>
                                    <select class="form-control" title="" id="sumBy" name="sumBy"
                                            notnull="true" style="width: 140px">
                                        <option value="center" selected>中心</option>
                                        <option value="store">门店</option>
                                        <option value="person">个人</option>
                                        <option value="city">城市</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>排名条件：</label>
                                    <select class="form-control" title="" id="sortBy" name="sortBy"
                                            notnull="true" style="width: 140px">
                                        <option value="flopPassDate" selected>翻牌验收</option>
                                        <option value="netSignEndDate">网签完成</option>
                                        <option value="roughAmout">大定金额</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>门店编号：</label>
                                    <input type="text" class="form-control w200" id="storeNo" name="storeNo"
                                           placeholder="请输入门店编号" style="width:180px;">
                                </div>

                                <div class="form-group">
                                    <label>门店地址：</label>
                                    <input type="text" class="form-control w200" id="storeAddress" name="storeAddress"
                                           placeholder="请输入门店地址" style="width:245px;">
                                </div>
                            </div>
                            <div class="form-inline">
                                <div class="pull-right">
                                    <button type="button" class="btn btn-primary" id="J_search"
                                            onclick="javascript:PerformanceQuery.search();">查询
                                    </button>
                                    <button type="button" class="btn btn-primary" id="btn-reset"
                                            onclick="javascript:PerformanceQuery.export()">导出
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
