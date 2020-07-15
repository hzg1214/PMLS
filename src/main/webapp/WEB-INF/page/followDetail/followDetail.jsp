<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <script src="http://api.map.baidu.com/api?v=2.0&ak=${sysConfig.baiduApiKey}" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/followDetail/followDetail.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <style type="text/css">
        .form-inline {
            overflow: inherit;
        }

        .too-long {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
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
            <h4 class="border-bottom pdb10"><strong>跟进明细</strong></h4>
            <form id="followDetailForm">

                <div class="panel panel-default" style="margin-bottom:10px;">
                    <div class="panel-body">

                        <div class="form-inline">
                            <div class="form-group">
                                <label class="lab">架构年份：</label>
                                <select class="multi-select-text" id="organization" name="organization"
                                        style="width: 120px; ">
                                    <option value="2019">2019</option>
                                    <option value="2018">2018</option>
                                    <option value="2017">2017</option>
                                </select>
                            </div>
                            <div class="form-group" name="group1">
                                <label class="lab">归属区域：</label>
                                <div class="multi-select" id="region" name="region" style="width: 165px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="regionCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 165px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group2">
                                <label class="lab">归属城市：</label>
                                <div class="multi-select" id="areaCity" name="areaCity" style="width: 120px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="areaCityCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 120px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group3">
                                <label class="lab">所在城市：</label>
                                <div class="multi-select" id="city" name="city" style="width: 120px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="cityIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 120px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group4">
                                <label class="lab">归属中心：</label>
                                <div class="multi-select" id="centerGroup" name="centerGroup" style="width: 131px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="centerIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 131px;">
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
                                           style="width: 120px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>门店信息：</label>
                                <input type="text" class="form-control" id="store" name="store" style="width: 165px"
                                       placeholder="请输入编号/名称/地址">
                            </div>
                            <div class="form-group">
                                <label>　跟进人：</label>
                                <input type="text" class="form-control" id="follower" name="follower"
                                       placeholder="请输入跟进人" style="width: 120px">
                            </div>
                            <div class="form-group" style="margin-top: 5px;">
                                <label>跟进日期：</label>
                                <input type="text" class="calendar-icon form-control w100" name="dateStart"
                                       style="width: 135px;"
                                       id="dateStart"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                                <span>-</span>
                                <input type="text" class="calendar-icon form-control w100" name="dateEnd"
                                       style="width: 135px;"
                                       id="dateEnd"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                            </div>
                        </div>

                        <div class="form-inline">
                            <div class="form-group">
                                <label class="lab">合同状态：</label>
                                <select class="multi-select-text" id="contractStatus" name="contractStatus"
                                        style="width: 120px; ">
                                    <option value="">请选择</option>
                                    <option value="10401">草签</option>
                                    <option value="10402">审核中</option>
                                    <option value="10403">审核通过</option>
                                    <option value="10404">审核未通过</option>
                                    <option value="10405">作废</option>
                                    <option value="10406">终止</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label class="lab">翻牌进度：</label>
                                <select class="multi-select-text" id="decorateStatus" name="decorateStatus"
                                        style="width: 165px; ">
                                    <option value="">请选择</option>
                                    <option value="16301">未装修</option>
                                    <option value="16302">装修中</option>
                                    <option value="16303">翻牌完成</option>
                                    <option value="16304">翻牌验收完成</option>
                                </select>
                            </div>

                            <div class="form-group" name="group6">
                                <label class="lab">跟进目的：</label>
                                <div class="multi-select" id="followAim" name="followAim" style="width: 120px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="followAimCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 120px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <span style="float: right;">
                                <button type="button" class="btn btn-primary" id="J_search"
                                        onclick="javascript:FollowDetail.search();">搜索
                                </button>
                                &nbsp;
                                <button type="button" class="btn btn-primary" id="J_export"
                                        onclick="javascript:FollowDetail.export();">导出
                                </button>
                            </span>
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
