<%--
  Created by IntelliJ IDEA.
  User: haidan
  Date: 2019/10/15
  Time: 16:59
  退成销页面
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp" %>

    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/otherReport/qtDealBack.js?_v=${vs}"></script>
</head>
<body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>

<form id="dealBackForm">
    <input type="hidden" id="id" name="id" value="${qtReport.id}">
    <input type="hidden" id="dealDate" value="${sdk:ymd2(qtReport.dealDate)}">
    <input type="hidden" id="befYJSSAmount" value="${qtReport.befYJSSAmount}">
    <input type="hidden" id="aftYJSSAmount" value="${qtReport.aftYJSSAmount}">
    <input type="hidden" id="befSJFYAmount" value="${qtReport.befSJFYAmount}">
    <input type="hidden" id="aftSJFYAmount" value="${qtReport.aftSJFYAmount}">
    <div class="container theme-hipage ng-scope" role="main">
        <div class="crumbs">
            <ul style="margin-right:150px;">
                <li><a href="#" class="a_hover">联动管理</a></li>
                <li><a href="${ctx}/qtProject?searchParam=1" class="a_hover">>其他收入</a></li>
                <li><a href="#" class="a_hover">>退成销</a></li>
            </ul>
        </div>
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>退成销</strong></h4>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <strong>
                                <label class="fw-normal w100 text-right"><strong>项目编号：</strong></label>${qtReport.estate.projectNo}
                                <label class="fw-normal w100 text-right"><strong>楼盘名称：</strong></label>${qtReport.estateNm}
                            </strong>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">合作方：</label>${qtReport.estate.partnerNm}
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">成销金额：</label>${sdk:mf2(qtReport.dealAmount)}元
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">成销日期：</label>${sdk:ymd2(qtReport.dealDate)}
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">应计收入税前：</label>${sdk:mf2(qtReport.srAmount)}元
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">应计收入税后：</label>${sdk:mf2(qtReport.aftYJSRAmount)}元
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">应计返佣税前：</label>${sdk:mf2(qtReport.befYJFYAmount)}元
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">应计返佣税后：</label>${sdk:mf2(qtReport.aftYJFYAmount)}元
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="backDealDate"><i>*</i>退成销日期：</label>
                            <input type="text" class="calendar-icon w200" style="width: 200px;" name="backDealDate"
                                   id="backDealDate"
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
                            <label class="fw-normal w120 text-right" for="memo"><i>*</i>原因：</label>
                            <textarea name="memo" class="form-control " style="width: 725px; height: 100px" id="memo" rows="10" cols="30" notnull="true"></textarea>
                            <span class="fc-warning" style="font-size: 10px;"></span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="text-center">
        <a href="javascript:QTDealBack.save();" class="btn btn-primary" id="dealBackBtn">保存</a>
        <a href="javascript:QTDealBack.backPre();" class="btn btn-primary mgl20">返回</a>
    </div>
</form>
</body>
</html>
