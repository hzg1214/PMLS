<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/keFuOrder/keFuOrderList.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
</head>
<style>
    input::-webkit-input-placeholder {
       font-size: 9px;
    }
</style>

<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>

<div class="container theme-hipage ng-scope" role="main">
<h4 class="border-bottom pdb10" style="margin-right: -15px;margin-left:-15px;"><strong>工单管理</strong></h4>
    <div class="row">
        <ul class="nav nav-tabs nav-tabs-header">
			<li role="presentation" class="active" id="presentation0">
				<a href="javascript:void(0);" id="taskType0">全部工单</a>
			</li>
			<li role="presentation"  id="presentation1">
				<a href="javascript:void(0);" id="taskType1">未处理</a>
			</li>
			<li role="presentation"  id="presentation2">
				<a href="javascript:void(0);" id="taskType2">未核验</a>
			</li>
			<li role="presentation"  id="presentation3">
				<a href="javascript:void(0);" id="taskType3">核验通过</a>
			</li>
        </ul>
        <div class="page-content">
            <form id="keFuOrderForm">
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
                                    <label>归属城市</label>
                                    <select class="form-control" title="" id="cityNo" name="cityNo" style="width:160px;">
			                            <option value="" selected="selected">请选择</option>
		                            </select>
                                    <input type="hidden" id="authCityIds" name="authCityIds" value="${authCityIds}" />
                                </div>
                                <div class="form-group">
                                    <label>门店城市</label>
                                    <input type="text" class="form-control" id="storeCity" name="storeCity" style="width:210px;" placeholder="门店城市" value="">
                                </div>
                                <div class="form-group">
                                    <label>创建日期</label>
                                    <input type="text" size="11" class="calendar-icon form-control" name="dateCreate" id="dateCreate" readonly="readonly" style="width:162px;"  class="ipttext Wdate"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
                                    至
                                    <input type="text" size="11" class="calendar-icon form-control" name="dateCreateEnd" id="dateCreateEnd" readonly="readonly" style="width:162px;"  class="ipttext Wdate"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-inline">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>经纪公司</label>
                                    <input type="text" class="form-control" id="companyValue" name="companyValue" style="width:162px;" placeholder="公司编号、公司名称" value="">
                                </div>
                                <div class="form-group"><label>一级分类</label>
	                            	<t:dictSelect field="categoryOne" id="categoryOne" xmlkey="LOOKUP_Dic" dbparam="P1:240" nullOption="请选择" classvalue="10" txWidth="210px"></t:dictSelect>
	                        	</div>
	                        	<div class="form-group">
	                        		<label>二级分类</label>
		                            <select class="form-control" title="" id="categoryTwo" name="categoryTwo" notnull="true" style="width:162px;">
		                            	<option value="" selected="selected">请选择</option>
		                            </select>
	                        	</div>
                                <div class="form-group">
                                    <label>经办人</label>
                                    <input type="text" class="form-control" id="userName" name="userName" style="width:210px;" placeholder="经办人" value="">
                                </div>
                            </div>
                         </div>
                        <div class="form-inline">
                            <div class="col-md-7">
                                <div class="form-group"><label >问题状态</label>
	                            	<t:dictSelect field="dealStatus" id="dealStatus" xmlkey="LOOKUP_Dic" dbparam="P1:242" nullOption="请选择" classvalue="10" txWidth="160px"></t:dictSelect>
	                        	</div>
                                <div class="form-group" ><label >核验状态</label>
	                            	<t:dictSelect field="checkStatus" id="checkStatus" xmlkey="LOOKUP_Dic" dbparam="P1:243" nullOption="请选择" classvalue="10" txWidth="210px"></t:dictSelect>
	                        	</div>
                                    
                            </div>
                            <div class="col-md-5">
                                <button type="button" class="btn btn-primary" id="J_search" onclick="javascript:KeFuOrder.search();" style="margin-left:12px;">
                                    查询
                                </button>
                                &nbsp;
                                <a type="button" class="btn btn-default" onclick="javascript:KeFuOrder.reset('${list_search_page}');">重置</a>
                                &nbsp;
                                <a class="btn btn-primary" style="margin-left:35px;" href="javascript:KeFuOrder.export();" id="btn-reset">导出</a>&nbsp;
                                <a type="button" class="btn btn-primary" href="${ctx}/keFuOrder/toAddKeFuOrder" style="margin-left:40px;" >新增工单</a>
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
    	KeFuOrder.setSearchParams(searchParamMap);
    }
    KeFuOrder.tabClick();
</script>
</html>
