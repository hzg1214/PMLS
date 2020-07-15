<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/houseLinkage/linkDetail/linkDetail.js?_v=${vs}"></script>
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
            <h4 class="border-bottom pdb10"><strong>联动明细</strong></h4>
            <form id="LinkDetailListForm">

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">

                            <div class="form-group">
                                <label class="lab">架构年份：</label>
                                <select class="multi-select-text" id="organization" name="organization"
                                        style="width: 180px; ">
                                    <option value="2019">2019</option>
                                    <option value="2018">2018</option>
                                    <option value="2017">2017</option>
                                </select>
                            </div>
<!--                             <div class="form-group" id="serachKeyGroup"> -->
<!--                                 <label class="lab">查询维度：</label> -->
<!--                                 <select class="multi-select-text" id="serachKey" name="serachKey" onchange="changeValue(this)" -->
<!--                                         style="width: 180px; " > -->
<!--                                     <option value="YJ">业绩</option> -->
<!--                                     <option value="SR">收入</option> -->
<!--                                 </select> -->
<!--                             </div> -->
                            <div class="form-group" id="serachKeyGroup">
                                <label class="lab">查询维度：</label>
                                <div class="multi-select" id="serachKey" onchange="changeValue(obj)" name="serachKey" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="serachKeys">
                                    <input type="text" class="multi-select-text"  readonly="readonly" placeholder="请选择" style="width: 180px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" id="accountProjectGroup">
                                <label class="lab">核算主体：</label>
                                <div class="multi-select" id="accountProject" name="accountProject" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="accountProjectNos">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 180px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group1">
                                <label class="lab">归属区域：</label>
                                <div class="multi-select" id="region" name="region" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="regionCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 180px;">
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
                                <div class="multi-select" id="areaCity" name="areaCity" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="areaCityCodes">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 180px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group3">
                                <label class="lab">所在城市：</label>
                                <div class="multi-select" id="city" name="city" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly" name="cityIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 180px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="form-group" name="group4">
                                <label class="lab">归属中心：</label>
                                <div class="multi-select" id="centerGroup" name="centerGroup" style="width: 180px;">
                                    <input type="hidden" class="multi-select-value" readonly="readonly"
                                           name="centerIds">
                                    <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
                                           style="width: 180px;">
                                    <ul class="multi-select-list">
                                        <li class="multi-select-item">
                                            <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="form-group" name="group2">
                                <label class="lab">报备来源：</label>
                                <select class="multi-select-text" id="customerFromId" name="customerFromId" style="width: 180px; ">
                                    <option value="">请选择</option>
                                    <c:forEach items="${customerFromList}" var="list">
                                        <option value="${list.dicCode}">${list.dicValue}</option>
                                    </c:forEach>
                                </select>
                            </div>

                        </div>

                        <div class="form-inline">
                            <div class="form-group">
                                <label>项目编号：</label>
                                <input type="text" class="form-control w200" id="estateId" name="estateId"
                                       placeholder="请输入项目编号" style="width:180px;">
                            </div>
                            <div class="form-group">
                                <label>楼盘名称：</label>
                                <input type="text" class="form-control w200" id="estateNm" name="estateNm"
                                       placeholder="请输入楼盘名称" style="width:180px;">
                            </div>
                            <div class="form-group">
                                <label>报备编号：</label>
                                <input type="text" class="form-control w200" id="reportId" name="reportId"
                                       placeholder="请输入报备编号" style="width:180px;">
                            </div>
                            <div class="form-group">
                                <label>门店编号：</label>
                                <input type="text" class="form-control w200" id="storeNo" name="storeNo"
                                       placeholder="请输入门店编号" style="width:180px;">
                            </div>

                        </div>

                        <div class="form-inline">
                        	<div class="form-group">
                                <label>报备日期：</label>
                                <input type="text" class="calendar-icon form-control w100" name="reportDateStart"
                                       id="reportDateStart" style="width: 120px"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                                <span>-</span>
                                <input type="text" class="calendar-icon form-control w100" name="reportDateEnd"
                                       id="reportDateEnd" style="width: 120px"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                            </div>
                            <div class="form-group">
                                <label>大定日期：</label>
                                <input type="text" class="calendar-icon form-control w100" name="roughDateStart"
                                       id="roughDateStart" style="width: 120px"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                                <span>-</span>
                                <input type="text" class="calendar-icon form-control w100" name="roughDateEnd"
                                       id="roughDateEnd" style="width: 120px"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                            </div>
                            <div class="form-group">
                                <label>成销日期：</label>
                                <input type="text" class="calendar-icon form-control w100" name="dealDateStart"
                                       id="dealDateStart" style="width: 120px"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                                <span>-</span>
                                <input type="text" class="calendar-icon form-control w100" name="dealDateEnd"
                                       id="dealDateEnd" style="width: 120px"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly" class="ipttext Wdate"/>
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

                            <div class="form-inline">
                                <li class="three_li" style="width: 650px">
                                    <label class="lab" style="width: 200px;font-size: 12px;font-weight: bold;color: red">您当前有<span id="reportSize">${userReportSize}</span>个文件正在下载中……</label>
                                </li>
                                <li class="three_li" style="width: 150px">

                                </li>
                                <li class="three_li" style="width: 100px">

                                </li>
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
