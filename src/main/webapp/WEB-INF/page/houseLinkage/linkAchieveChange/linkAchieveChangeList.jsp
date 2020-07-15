<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/houseLinkage/linkAchieveChange/linkAchieveChange.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
</head>

<body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>

<div class="container theme-hipage ng-scope" role="main">
    <h4 class="border-bottom pdb10" style="margin-right: -15px;margin-left:-15px;"><strong>佣金管理</strong></h4>
    <div class="row">
        <ul class="nav nav-tabs nav-tabs-header">
            <li role="presentation">
                <a href="${ctx}/sceneCommission">外佣/返佣</a>
            </li>
            <li role="presentation">
                <a href="${ctx}/sceneInCommission">内佣</a>
            </li>
            <li role="presentation">
                <a href="${ctx}/scenePadCommission/index">垫佣</a>
            </li>
            <li role="presentation">
                <a href="${ctx}/settleConfirm">结算确认</a>
            </li>
            <%-- <li role="presentation" class="active">
                <a href="${ctx}/linkAchieveChange">业绩调整</a>
            </li> --%>
            <li role="presentation">
                <a href="${ctx}/cashBill">请款单</a>
            </li>

        </ul>
        <div class="page-content">
            <%--<h4 class="border-bottom pdb10"><strong>联动业绩调整</strong></h4>--%>
            <form id="linkAchieveChangeForm">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="col-md-12">

                                <div class="form-group">
                                    <label>业务阶段</label>
                                    <t:dictSelect field="latestProgress" id="latestProgress" xmlkey="LOOKUP_Dic" dbparam="P1:135" nullOption="请选择" classvalue="30"></t:dictSelect>
                                </div>

                                <div class="form-group">
                                    <label>查询日期</label>
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

                                <div class="form-group">
                                    <input type="text" class="form-control" id="searchValues" name="searchValues" style="width: 400px"
                                           placeholder="请输入报备编号/楼盘名称/经纪公司/门店维护人/客户姓名">
                                </div>

                            </div>
                        </div>

                        <div class="form-inline">
                            <div class="col-md-9">

                            </div>
                            <div class="col-md-3">&nbsp; &nbsp;
                                <button type="button" class="btn btn-primary" id="J_search"
                                        onclick="javascript:LinkAchieveChange.search();">查询
                                </button>&nbsp;&nbsp;
                                 <a type="button" class="btn btn-default" onclick="javascript:LinkAchieveChange.reset('${list_search_page}');">重置</a>
                                <shiro:hasPermission name="/linkAchieveChange:ACHIEVE_CHANGE">
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <button type="button" class="btn btn-primary" id="J_export"
                                            onclick="javascript:LinkAchieveChange.achieveMentChange();">业绩调整
                                    </button>
                                </shiro:hasPermission>
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
