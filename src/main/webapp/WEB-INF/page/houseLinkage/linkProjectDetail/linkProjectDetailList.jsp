<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript"
            src="${ctx}/meta/js/houseLinkage/linkProjectDetail/linkProjectDetail.js?_v=${vs}"></script>
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
            <h4 class="border-bottom pdb10"><strong>联动项目明细</strong></h4>
            <form id="LinkProjectDetailListForm">

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


                            <div class="form-group" id="searchTypeForm">
                                <label>查询维度：</label>
                                <select class="form-control" title="" id="searchType" name="searchType" style="width:140px;">
                                    <option value="1">项目</option>
                                    <option value="2">考核</option>
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
                                <label>项目编号：</label>
                                <input type="text" class="form-control w200" id="projectNo" name="projectNo"
                                       placeholder="请输入项目编号" style="width:180px; margin-left: 0px;">
                            </div>
                        </div>

                        <div class="form-inline">
                            <div class="form-group" id="projectStatusMulti">
                                <label class="lab">项目状态：</label>
                                <div class="multi-select" id="projectStatus" style="width: 140px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="projectStatus">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 140px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label>
                                                <input type="checkbox" class="multi-select-checkall" value="20301,20302,20303,20304">
                                                <span>全部</span>
                                            </label>
                                        </li>
                                        <li class="multi-select-item">
                                            <label>
                                                <input type="checkbox" value="20301" data-text="跟单">
                                                <span>跟单</span>
                                            </label>
                                        </li>
                                        <li class="multi-select-item">
                                            <label>
                                                <input type="checkbox" value="20302" data-text="签约">
                                                <span>签约</span>
                                            </label>
                                        </li>
                                        <li class="multi-select-item">
                                            <label>
                                                <input type="checkbox" value="20303" data-text="结案">
                                                <span>结案</span>
                                            </label>
                                        </li>
                                        <li class="multi-select-item">
                                            <label>
                                                <input type="checkbox" value="20304" data-text="取消跟单">
                                                <span>取消跟单</span>
                                            </label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group noProject">
                                <label>起始月份：</label>
                                <input type="text" class="calendar-icon form-control w100" name="startDate"
                                       id="startDate" style="width: 140px"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                            </div>
                            <div class="form-group noProject">
                                <label>截止月份：</label>
                                <input type="text" class="calendar-icon form-control w100" name="endDate"
                                       id="endDate" style="width: 140px"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                            </div>

                            <div class="form-group project">
                                <label>起始日期：</label>
                                <input type="text" class="calendar-icon form-control w100" name="startDateProject"
                                       id="startDateProject" style="width: 140px"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                            </div>
                            <div class="form-group project">
                                <label>截止日期：</label>
                                <input type="text" class="calendar-icon form-control w100" name="endDateProject"
                                       id="endDateProject" style="width: 140px"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                            </div>

                            <div class="form-group">
                                <label>楼盘名称：</label>
                                <input type="text" class="form-control w200" id="estateNm" name="estateNm"
                                       placeholder="请输入楼盘名称" style="width:180px;">
                            </div>
                        </div>

                        <div class="form-inline">
                            <div class="form-group" id="realityCity">
                                <label>楼盘城市：</label>
                                <select class="form-control" title="" id="realityCityNo" name="realityCityNo"
                                        style="width:140px;">
                                </select>
                            </div>
                            <div class="form-group" id="district">
                                <label id="districtLabel">楼盘区域：</label>
                                <select class="form-control" title="" id="districtNo" name="districtNo"
                                        style="width:140px;">

                                </select>
                            </div>

                            <div class="form-group">
                                <label>楼盘地址：</label>
                                <input type="text" class="form-control w200" id="address" name="address"
                                       placeholder="请输入楼盘地址" style="width:415px;">
                            </div>

                        </div>
                        <div class="form-inline">
                            <div class="pull-right">
                                <button type="button" class="btn btn-primary" id="J_search"
                                        onclick="javascript:LinkDetail.search();">查询
                                </button>
                                <button type="button" class="btn btn-primary" id="btn-reset"
                                        onclick="javascript:LinkDetail.export()">导出
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
