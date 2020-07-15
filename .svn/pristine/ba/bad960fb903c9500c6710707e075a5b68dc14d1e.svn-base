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
    <script type="text/javascript" src="${ctx}/meta/js/report/membership/membershipDetailReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>

</head>
<style type="text/css">
    table th {
        text-align: center;
    }

    .form-inline {
        overflow: inherit;
    }

    /* .table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th { */
    /*     padding: 8px 6px; */
    /*     line-height: 1.42857143; */
    /*     vertical-align: top; */
    /*     border-top: 1px solid #ddd; */
    /* } */
</style>

<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>

<div class="container theme-hipage ng-scope" role="main" id="contentAll">
    <div class="row">
        <div class="page-content">
            <form id="membershipDetail">
                <h4 class="border-bottom pdb10"><strong>公盘会员明细表</strong></h4>
                <div class="panel panel-default">
                    <div class="panel-body" style="padding-bottom:10px; width: 1200px;">
                        <div class="form-inline">
                            <div class="form-group" id="stage" style="width: 360px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">业务阶段：</label>
                                <div class="multi-select" style="width: 230px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="stages">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 230px; height: 24px; margin-left: 0px;"/>
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                        <%--<li class="multi-select-item">--%>
                                            <%--<label><input id="initial" type="checkbox" value="initialDate" data-text="合同草签日期"><span>合同草签日期</span></label>--%>
                                        <%--</li>--%>
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" value="performDate" data-text="审核通过日期"><span>审核通过日期</span></label>
                                        </li>
                                        <%--<li class="multi-select-item">--%>
                                            <%--<label><input type="checkbox" value="flipCompleDate"--%>
                                                          <%--data-text="翻牌完成日期"><span>翻牌完成日期</span></label>--%>
                                        <%--</li>--%>
                                        <%--<li class="multi-select-item">--%>
                                            <%--<label><input type="checkbox" value="flipPassDate" data-text="验收通过日期"><span>验收通过日期</span></label>--%>
                                        <%--</li>--%>
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" value="dateArrivalAct"
                                                          data-text="保证金到账日期"><span>保证金到账日期</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" style="width: 360px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">查询日期：</label>
                                <input type="text" size="11" class="calendar-icon form-control" name="startDate" id="startDate"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                                       readonly="readonly" class="ipttext Wdate"
                                       onchange="checkDate(this)" style="width: 110px; height: 24px; margin-left: 0px; padding-left: 5px; font-size: 12px;"/> -
                                <input type="text" size="11" class="calendar-icon form-control" name="endDate" id="endDate"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                                       readonly="readonly" class="ipttext Wdate"
                                       onchange="checkDate(this)" style="width: 110px; height: 24px; margin-left: 0px; padding-left: 5px; font-size: 12px;"/>
                            </div>
                            <div class="form-group" style="width: 360px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">经纪公司：</label>
                                <input type="text" class="multi-select-text"
                                       style="width: 230px; height: 24px; margin-left: 0px;" id="company" name="company"
                                       placeholder="经纪公司编号、名称">
                            </div>
                        </div>

                        <div class="form-inline">
                            <div class="form-group" style="width: 360px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">合同编号：</label>
                                <input type="text" class="multi-select-text"
                                       style="width: 230px; height: 24px; margin-left: 0px;" id="contractNo"
                                       name="contractNo" placeholder="合同编号">
                            </div>
                            <div class="form-group" style="width: 360px; margin-left: 0px;">
                                <label class="lab" style="width:75px;padding-left: 5px;">门店：</label>
                                <input type="text" class="multi-select-text" style="width: 238px; height: 24px; "
                                       id="storeNo" name="storeNo" placeholder="门店编号、名称">
                            </div>
                            <div class="form-group" style="width: 360px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">架构年份：</label>
                                <select class="multi-select-text" id="organization" name="organization"
                                        style="width: 230px; height: 24px; margin-left: 0px; font-size: 12px;">
                                    <option value="2019">2019</option>
                                    <option value="2018">2018</option>
                                    <option value="2017">2017</option>
                                </select>
                            </div>

                        </div>


                        <div class="form-inline">
                            <div class="form-group" name="group1" style="width: 360px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">归属区域：</label>
                                <div class="multi-select" id="region" name="region" style="width: 230px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="regionCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 230px; height: 24px; margin-left: 0px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group2" style="width: 360px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">归属城市：</label>
                                <div class="multi-select" id="areaCity" name="areaCity" style="width: 235px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="areaCityCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 238px; height: 24px; margin-left: 0px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group3" style="width: 360px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">所在城市：</label>
                                <div class="multi-select" id="city" name="city" style="width: 230px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="cityIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 230px; height: 24px; margin-left: 0px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                        </div>
                        <div class="form-inline">

                            <div class="form-group" name="group4" style="width: 360px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">归属中心：</label>
                                <div class="multi-select" id="centerGroup" name="centerGroup" style="width: 230px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="centerIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 230px; height: 24px; margin-left: 0px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="form-group" id="person" style="width: 360px; margin-left: 0px;">
                                <label class="lab" style="padding-left: 5px;">归属人员：</label>
                                <input class="w70 validatebox-text" type="text"
                                       style="width: 238px; height: 24px; margin-left: 0px; font-size: 12px;"
                                       id="maintainer" name="maintainer">
                            </div>
                            <div class="form-group"  style="width: 360px; margin-left: 0px;" id="searchDiv">
                                    <button type="button" class="btn btn-primary btn-flat" id="btn-search"
                                            style="height: 25px; margin-top: 0px; line-height: 5px; margin-left: 14px;"
                                            onclick="javascript:membershipDetail.search();">查询
                                    </button>
                                    <%--<button type="button" class="btn btn-primary btn-flat" id="btn-reset"--%>
                                            <%--style="height: 25px; margin-top: 0px; line-height: 5px; margin-left: 14px;"--%>
                                            <%--onclick="javascript:membershipDetail.reset();">重置--%>
                                    <%--</button>--%>
                                    <button type="button" class="btn btn-primary btn-flat" id="btn-export"
                                            style="height: 25px; margin-top: 0px; line-height: 5px;margin-left: 14px;"
                                            onclick="javascript:membershipDetail.export();">导出
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
