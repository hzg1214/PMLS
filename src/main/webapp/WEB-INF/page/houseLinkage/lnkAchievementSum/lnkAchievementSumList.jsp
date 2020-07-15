<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
   <%--  <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%> --%>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript"
            src="${ctx}/meta/js/houseLinkage/lnkAchievementSum/lnkAchievementSum.js?_v=${vs}"></script>
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
            <h4 class="border-bottom pdb10"><strong>联动业绩汇总</strong></h4>
            <form id="LnkAchievementSumListForm">

                <div class="panel panel-default">
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
                                <label style="margin-left: 28px;">归属区域：</label>
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
                            <div class="form-group" name="group5">
                                <label class="lab">项目归属部门：</label>
                                <div class="multi-select" id="dept" name="dept">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="deptIds">
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
                                <label>查询日期：</label>
                                <input type="text" size="11"
											class="calendar-icon form-control w100" name="startDate"
											id="startDate"
											style="width: 140px"
											onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
											readonly="readonly" class="ipttext Wdate"
											onchange="checkDate(this)" /> 至
								<input type="text" style="width: 140px"
											class="calendar-icon form-control w100" name="endDate"
											id="endDate"
											onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'startDate\')}'})"
											readonly="readonly" class="ipttext Wdate"
											onchange="checkDate(this)"/>
                            </div>
                        </div>

                        <div class="form-inline">
                            <div class="form-group">
                                <label style="margin-left:27px;">项目：</label>
                                <input type="text" class="form-control w200" id="projectCondition" name="projectCondition"
                                       placeholder="项目编号、项目名称" style="width:140px;font-size: 12px;">
                            </div>
                            <div class="form-group">
                                <label style="margin-left: 29px;">经纪公司：</label>
                                <input type="text" class="form-control w200" id="companyCondition" name="companyCondition"
                                       placeholder="公司编号、公司名称" style="width:140px;font-size: 12px;">
                            </div>
                            <div class="form-group">
                                <label style="margin-left:28px;">门店：</label>
                                <input type="text" class="form-control w200" id="storeCondition" name="storeCondition"
                                       placeholder="门店编号、门店名称" style="width:140px;font-size: 12px;">
                            </div>
                            <div class="form-group">
                                <label>经服/渠道：</label>
                                <input type="text" class="form-control w200" id="channelCondition" name="channelCondition"
                                       placeholder="" style="width:140px;font-size: 12px;">
                            </div>
                        </div>
                        <div class="form-inline">
                            <div class="pull-right" style="margin-right: 32px;">
                                <a class="btn btn-primary" href="javascript:LnkAchievementSum.search();" style="margin-left:20px;">查询</a>&nbsp;
                                <a type="button" class="btn btn-default" style="margin-left:10px;" onclick="javascript:LnkAchievementSum.reset();">重置</a>
                                <a class="btn btn-primary" id="btn-reset" style="margin-left:30px;" href="javascript:LnkAchievementSum.export();">导出</a>&nbsp;
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
