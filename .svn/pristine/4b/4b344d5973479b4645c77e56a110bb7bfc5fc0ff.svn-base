<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/storeStructure/storeStructure.js?_v=${vs}"></script>
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
            <h4 class="border-bottom pdb10"><strong>门店结构</strong></h4>
            <form id="storeStructureForm">

                <div class="panel panel-default" style="margin-bottom:10px;">
                    <div class="panel-body">
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
                            <div class="form-group">
                                <label>当期</label>
                                <input type="text" size="11" class="calendar-icon form-control" name="dateStart"
                                       id="dateStart"
                                       readonly="readonly"
                                       onchange="checkDate(this)"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       class="ipttext Wdate"/> 至
                                <input type="text" size="11" class="calendar-icon form-control" name="dateEnd"
                                       id="dateEnd"
                                       readonly="readonly"
                                       onchange="checkDate(this)"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       class="ipttext Wdate"/>
                            </div>
                        </div>
                        <div class="form-inline">
                            <div class="col-md-10">
                            </div>
                            <div class="col-md-2">
                                <button type="button" class="btn btn-primary" id="J_search"
                                        onclick="javascript:StoreStructure.search();">搜索
                                </button>
                                &nbsp;
                                <button type="button" class="btn btn-primary" id="J_export"
                                        onclick="javascript:StoreStructure.export();">导出
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
