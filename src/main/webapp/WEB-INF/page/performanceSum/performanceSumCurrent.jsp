<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>

    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/performanceSum/performanceSumCurrent.js?_v=${vs}"></script>
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
        <h4 class="border-bottom pdb10"><strong>中心汇总表</strong></h4>
        <ul class="nav nav-tabs nav-tabs-header">
            <li role="presentation">
                <a href="${ctx}/performanceSum/weekSum">周汇总</a>
            </li>
            <li role="presentation">
                <a href="${ctx}/performanceSum/monthSum">月汇总</a>
            </li>
            <li role="presentation">
                <a href="${ctx}/performanceSum/yearSum">年汇总</a>
            </li>
            <li role="presentation" class="active">
                <a href="${ctx}/performanceSum/currentSum">当期汇总</a>
            </li>
        </ul>

        <div class="page-content">
            <form id="PerformanceSumForm">

                <input type="hidden" id="cityNo" name="cityNo" value="${userInfo.cityNo}">
                <input type="hidden" name="groupType" id="groupType" value="all">
                <input type="hidden" name="type" id="type" value="daily">
                <input type="hidden" name="startDay" id="startDay" value="0">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="form-group" style="width: 250px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">架构年份：</label>
                                <select class="multi-select-text" id="organization" name="organization"
                                        style="width: 140px;  margin-left: 0px;">
                                    <option value="2019">2019</option>
                                    <option value="2018">2018</option>
                                    <option value="2017">2017</option>
                                </select>
                            </div>

                            <div class="form-group" name="group1" style="width: 250px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">归属区域：</label>
                                <div class="multi-select" id="region" name="region" style="width: 140px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="regionCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 140px;  margin-left: 0px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="form-group" name="group2" style="width: 250px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">归属城市：</label>
                                <div class="multi-select" id="areaCity" name="areaCity" style="width: 140px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="areaCityCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 140px;  margin-left: 0px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="form-group" name="group3" style="width: 250px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">所在城市：</label>
                                <div class="multi-select" id="city" name="city" style="width: 140px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="cityIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 140px; margin-left: 0px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="form-inline">
                            <div class="form-group" name="group4" style="width: 250px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">归属中心：</label>
                                <div class="multi-select" id="centerGroup" name="centerGroup" style="width: 140px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="centerIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 140px; margin-left: 0px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="form-group" id="date" style="width: 500px; margin-left: 0px;">
                                <label class="lab" style="width: 75px;text-align: right;">日期：</label>
                                <input type="text" class="calendar-icon form-control w100" name="dateStart"
                                       style="width: 140px;  margin-left: 0px;"
                                       id="dateStart"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                                <span>-</span>
                                <input type="text" class="calendar-icon form-control w100" name="dateEnd"
                                       style="width: 140px;  margin-left: 0px;"
                                       id="dateEnd"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                            </div>
                        </div>

                        <div class="pull-right">
                            <button type="button" class="btn btn-primary" id="J_search"
                                    onclick="javascript:PerformanceSumCurrent.search();">查询
                            </button>
                            <button type="button" class="btn btn-primary" id="btn-reset"
                                    onclick="javascript:PerformanceSumCurrent.export()">导出
                            </button>
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
<!-- 底部栏 -->
<%-- <jsp:include page="/WEB-INF/page/common/footer.jsp" flush="true"></jsp:include> --%>
<div class="control-sidebar-bg"></div>
</body>

</html>
