<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp" %>
    <link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/cssreport/bootstrap-select.css?_v=${vs}">
<%--    <link href="${ctx}/meta/css/jquery.searchableSelect.css" rel="stylesheet" type="text/css">--%>

    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>

    <script type="text/javascript" src="${ctx}/meta/js/report/expand/bootstrap-select.js?_v=${vs}"></script>
<%--    <script type="text/javascript" src="${ctx}/meta/js/common/jquery.searchableSelect.js"></script>--%>

    <script type="text/javascript" src="${ctx}/meta/js/otherReport/qtReportAdd.js?_v=${vs}"></script>

    <script type="text/javascript">

    </script>
</head>
<body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>

<form id="reportAddForm">
    <input type="hidden" name="estateId" id="estateId" value="${estateInfo.estate.estateId}">
    <input type="hidden" name="estateNm" id="estateNm" value="${estateInfo.estate.estateNm}">
    <input type="hidden" name="cityNo" id="cityNo" value="${userInfo.cityNo}">
    <input type="hidden" name="partnerNm" id="partnerNm" value="${estateInfo.estate.partnerNm}">

    <div class="container theme-hipage ng-scope" role="main">
        <div class="crumbs">
            <ul style="margin-right:150px;">
                <li><a href="#" class="a_hover">联动管理</a></li>
                <li><a href="#" class="a_hover">>其他收入</a></li>
                <li><a href="#" class="a_hover">>报备</a></li>
            </ul>
        </div>
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>报备</strong></h4>

                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <strong>
                                <label class="fw-normal w100 text-right"><strong>项目编号：</strong></label>${estateInfo.estate.projectNo}
                                <label class="fw-normal w100 text-right"><strong>楼盘名称：</strong></label>${estateInfo.estate.estateNm}
                            </strong>
                        </div>
                    </li>
                </ul>

                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">合作方：</label>${estateInfo.estate.partnerNm}
                        </div>
                    </li>
                </ul>


                <div class="form-group"  style="display:inline-block;width: 47%;">
                    <label class="fw-normal w120 text-right"><i>*</i>业绩归属城市：</label>
                    <div style="display:inline-block;width:200px;">
                        <select class="form-control selectpicker" title="" id="accCity" name="accCity" notnull="true" style="width:200px;" data-live-search="true"></select>
                    </div>
                    <span class="fc-warning"></span>
                </div>
                <div class="form-group" style="display:inline-block;width: 47%;">
                    <label class="fw-normal w150 text-right"><i>*</i>业绩归属中心：</label>
                    <div style="display:inline-block; vertical-align: middle;">
                        <select class="form-control selectpicker1" title="" id="centerId" name="centerId" notnull="true" style="width:175px;"></select>
                    </div>
                    <span class="fc-warning"></span>
                </div>

               <%-- <div class="form-group">
                    <label class="fw-normal w120 text-right"><i>*</i>业绩归属城市：</label>
                    <div style="display:inline-block;width:127px;">
                        <select class="form-control selectpicker" title="" id="accCity" name="accCity" notnull="true" style="width:150px;" data-live-search="true">
                            <option value="" selected="selected">请选择</option>
                            <c:forEach items="${cityList}" var="accCity">
                                <option value="${accCity.cityNo}">${accCity.cityName}</option>
                            </c:forEach>
                        </select>
                    </div>


                    <label class="fw-normal w120 text-right" style="margin-left: 250px;"><i>*</i>业绩归属中心：</label>
                    <div style="display:inline-block; vertical-align: middle;">
                        <select class="form-control  w200" title="" id="centerId" name="centerId" notnull="true"
                                style="width:180px;"></select>
                    </div>
                </div>--%>


                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="srType"><i>* </i>收入类型：</label>
                            <select style="width:200px;" class="form-control" name="srType" id="srType">
                                <option value="">请选择</option>
                                <option value="27201">带看奖</option>
                                <option value="27202">成交奖</option>
                                <option value="27203">广告咨询费</option>
                                <option value="27204">拓客服务费</option>
                                <option value="27205">其他</option>
                            </select>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>

                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="srAmount"><i>* </i>收入金额：</label>
                            <input type="text" class="form-control w200" name="srAmount"
                                   id="srAmount" placeholder="请输入收入金额" notnull="true"
                                   maxlength="12" dataType="needMoney"
                                   oninput="this.value=this.value.replace(/\s+/g,'')">元
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>


                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="reportDate"><i>* </i>报备日期：</label>
                            <input type="text" class="calendar-icon w200" style="width: 200px;" name="reportDate"
                                   id="reportDate"
                                   onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
                                   class="ipttext Wdate" notnull="true"/>
                            <span class="fc-warning" style="font-size: 10px;"></span>
                        </div>
                    </li>
                    <li>

                    </li>
                </ul>
                <ul class="list-inline  form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="reportDate">备注：</label>
                            <textarea name="memo" class="form-control " style="width: 725px; height: 100px" id="memo" rows="10" cols="30"></textarea>
                            <span class="fc-warning" style="font-size: 10px;"></span>
                        </div>
                    </li>
                </ul>

            </div>
        </div>
    </div>

    <input type="hidden" id="fileRecordMainIds" name="fileRecordMainIds">

    <div class="text-center">
        <a href="javascript:reportAdd();" class="btn btn-primary" id="reportPcAddBtn">报备</a>
        <a href="${ctx}/qtProject?searchParam=1" class="btn btn-primary mgl20">返回</a>
    </div>
</form>
</body>

</html>
