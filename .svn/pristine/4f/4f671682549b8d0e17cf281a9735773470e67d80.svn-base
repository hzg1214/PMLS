
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/houseLinkage/linkConversionRate/linkConversionRate.js?_v=${vs}"></script>
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
    <div class="row">
        <div class="page-content">
            <h4 class="border-bottom pdb10"><strong>联动转化率分析</strong></h4>
            <form id="searchForm">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="form-group">
                                <label class="lab">架构年份：</label>
                                <select class="multi-select-text" id="organization" name="organization" style="width: 150px; ">
                                    <option value="2019">2019</option>
                                    <option value="2018">2018</option>
                                    <option value="2017">2017</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="lab">查询维度：</label>
                                <select class="form-control" id="serachKey" name="serachKey" style="width:150px;">
                                    <option value="y">年度</option>
                                    <option value="q">季度</option>
                                </select>
                            </div>
                            <div class="form-group" id="quarterGroup">
                                <label class="lab">查询周期</label>：
                                <select class="form-control" id="yearly" name="yearly" style="width:150px;">
                                    <option value="2019">2019</option>
                                    <option value="2018">2018</option>
                                    <option value="2017">2017</option>
                                </select>
                                <div class="multi-select" id="quarter" style="width:66px;display: none">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="quarter">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 66px;min-width: 66px">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label></li>
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" value="1" data-text="Q1"><span>Q1</span></label></li>
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" value="2" data-text="Q2"><span>Q2</span></label></li>
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" value="3" data-text="Q3"><span>Q3</span></label></li>
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" value="4" data-text="Q4"><span>Q4</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group1">
                                <label class="lab">归属区域：</label>
                                <div class="multi-select" id="region" name="region" style="width: 150px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="regionCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 150px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="form-inline">
                            <div class="form-group" name="group2">
                                <label class="lab">归属城市：</label>
                                <div class="multi-select" id="areaCity" onchange="changeValue(obj)" name="areaCity" style="width: 150px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="areaCityCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 150px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group3">
                                <label class="lab">所在城市：</label>
                                <div class="multi-select" id="city" onchange="changeCity(obj)" name="city" style="width: 150px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="cityIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 150px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="lab">项目名称：</label>
                                <input type="text" class="form-control w150" id="estateNmKey" name="estateNmKey" placeholder="项目编号或名称" style="width:150px; margin-left: 0px;">
                                <%--<script type="text/javascript">
                                    $(document).ready(function () {
                                        $("#estateNm").multiselect({
                                            enableFiltering: true,
                                            filterPlaceholder: '请输入项目名称',
                                            buttonWidth: '150px',
                                            nonSelectedText: '请选择',
                                            nSelectedText: '个项目被选中',
                                            includeSelectAllOption: false,
                                            selectAllText: '全选/取消',
                                            allSelectedText: '已选中所有项目',
                                            selectedClass: 'active1',
                                            numberDisplayed: 10,
                                            dropRight: true,
                                            maxHeight: 275,
                                            dropUp: true,
                                            onChange: function (element, checked) {
                                                var brands = $('#estateNm option:selected');
                                                var selected = [];
                                                $(brands).each(function (index, brand) {
                                                    selected.push(['\'' + $(this).val() + '\'']);
                                                });
                                                $("#estateNmKey").val(selected);
                                                console.log(selected);
                                            }
                                        });
                                        //控制select显示
                                        $("span.multiselect-selected-text").css({
                                            "text-align": 'left',
                                            display: 'inline-block'
                                        });
                                        $("b.caret").css("margin-left", "75px");
                                        //图标颜色
                                        $(".input-group-btn i").css(
                                                "color","#4169E1"
                                        );
                                        //图标颜色
                                        $(".input-group-addon i").css(
                                                "color","#4169E1"
                                        );
                                    });
                                </script>
                                <select class="form-control" style="width:150px;" id="estateNm" name="estateNm"
                                        multiple="multiple">
                                    <c:forEach items="${estateList}" var="list">
                                        <option value="${list}">${list}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" id="estateNmKey" name="estateNmKey"/>--%>
                                <span class="fc-warning"></span>
                            </div>
                        </div>
                        <div class="form-inline">
                            <div class="pull-right">
                                <button type="button" class="btn btn-primary" id="J_search"
                                        onclick="javascript:LinkConversionRate.search();">查询
                                </button>
                                <button type="button" class="btn btn-primary" id="btn-output"
                                        onclick="javascript:LinkConversionRate.export()">导出
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
