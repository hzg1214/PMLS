<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/gpContract/gpContractList.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/contract/exchangeCenter.js?_v=${vs}"></script>
</head>

<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>

<div class="container theme-hipage ng-scope" role="main">
<h4 class="border-bottom pdb10" style="margin-right: -15px;margin-left:-15px;"><strong>公盘合同</strong></h4>
    <div class="row">
    	<ul class="nav nav-tabs nav-tabs-header">
	            <li role="presentation" class="active">
	                <a href="${ctx}/gpContract" >公盘合同</a>
	            </li>
	            <li role="presentation">
	                <a href="${ctx}/gpCsMemberContract" >初始会员合同</a>
	            </li>
	            <li role="presentation">
	                <a href="${ctx}/gpContractChange">公盘合同终止</a>
	            </li>
	        </ul>
        <div class="page-content">
            <form id="gpContractForm">
                <input type="hidden" id="orderName" name="orderName" value="dateCreate">
                <input type="hidden" id="orderType" name="orderType" value="DESC">
                <input type="hidden" name="searchParmCache" value="1">
                <input type="hidden" id="curPageTemp" value="1">
                <input type="hidden" id="sysPageChaneTemp" value="1">
                <div class="panel panel-default" style="margin-bottom:10px;">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>公盘编号</label>
                                    <input type="text" class="form-control" id="searchKey" name="searchKey" style="width:250px;" placeholder="公盘合同编号/公盘协议书编号" value="">
                                </div>
                                <div class="form-group">
                                    <label>创建时间</label>
                                    <input type="text" size="11" class="calendar-icon form-control" name="dateCreateStart" id="dateCreateStart" readonly="readonly" class="ipttext Wdate"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" onchange="checkDate(this)"/>
                                    <input type="text" size="11" class="calendar-icon form-control" name="dateCreateEnd" id="dateCreateEnd" readonly="readonly" class="ipttext Wdate"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" onchange="checkDate(this)"/>
                                </div>
                                <div class="form-group">
                                    <label>经纪公司</label>
                                    <input type="text" class="form-control" id="companyName" name="companyName" style="width:250px;" placeholder="经纪公司编号/经纪公司名称" value="">
                                </div>
                            </div>
                        </div>
                        <div class="form-inline">
                            <div class="col-md-9">
                                <div class="form-group">
                                    <label style="margin-left: 14px;">创建人</label>
                                    <input type="text" class="form-control" id="userName" name="userName" style="width:250px;" placeholder="创建人姓名" value="">
                                </div>
                                <div class="form-group"><label>审核状态</label>
                                    <select class="form-control" title="" id="contractStatus" name="contractStatus" style="width:116px;">
                                        <option value="">请选择</option>
                                        <option value="10401">草签</option>
                                        <option value="10402">审核中</option>
                                        <option value="10403">审核通过</option>
                                        <option value="10404">审核未通过</option>
                                        <option value="10405">作废</option>
                                        <option value="10406">终止</option>
                                    </select>
                                </div>
                                <div class="form-group"><label>保证金状态</label>
                                    <select class="form-control" title="" id="depositStatus" name="depositStatus" style="width:116px;">
                                        <option value="">请选择</option>
                                        <option value="1">已到账</option>
                                        <option value="0">未到账</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <button type="button" class="btn btn-primary" id="J_search" onclick="javascript:GpContract.search();" style="margin-left:33px;">
                                    搜索
                                </button>
                                &nbsp;
                                <a type="button" class="btn btn-default" onclick="javascript:GpContract.reset('${list_search_page}');">重置</a>
                                &nbsp;
                                <shiro:hasPermission name="/gpContract:GP_CREATE">
	                                <c:if test="${gpAddFlag eq '1'}">
	                                    <a type="button" class="btn btn-primary" href="javascript:GpContract.create();" style="float:right">新增合同</a>
	                                </c:if>
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
<script type="text/javascript">
    var searchParamMap = '${searchParamMap}';
    console.log(searchParamMap);
    if (searchParamMap != undefined && searchParamMap != null && searchParamMap != '') {
        GpContract.setSearchParams(searchParamMap);
    }
</script>
</html>
