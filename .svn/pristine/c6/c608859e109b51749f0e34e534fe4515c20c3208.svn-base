<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/cashbill/cashBillList.js?_v=${vs}"></script>
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
                <a href="${ctx}/lnkYjNy">内佣</a>
            </li>
            <li role="presentation">
                <a href="${ctx}/scenePadCommission/index">垫佣</a>
            </li>
            <li role="presentation">
                <a href="${ctx}/settleConfirm">结算确认</a>
            </li>
            <%-- <li role="presentation">
                <a href="${ctx}/linkAchieveChange">业绩调整</a>
            </li> --%>
            <li role="presentation" class="active">
                <a href="${ctx}/cashBill">请款单</a>
            </li>
			<li role="presentation"  >
               	<a href="${ctx}/lnkYjReport">返佣对象维护</a>
           	</li>
        </ul>
        <div class="page-content">
            <form id="cashBillListForm">
            	<input type="hidden" name="searchParmCache" value="1">
            	<input type="hidden" id="curPageTemp" value="1">
        		<input type="hidden" id="sysPageChaneTemp" value="1">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="col-md-12">
                                <div class="form-group">
									<label style="margin-left:15px;">编号</label> 
									<input type="text" class="form-control" id="cashValues" name="cashValues"
										style="width:250px;margin-left:10px;"
										placeholder="请款编号" value="">
								</div>
                                <div class="form-group">
									<label style="margin-left:49px;">项目</label> 
									<input type="text" class="form-control" id="projectValues" name="projectValues"
										style="width:250px;margin-left:10px;"
										placeholder="项目编号、项目名称" value="">
								</div>
                                <div class="form-group">
									<label style="margin-left:15px;">经纪公司编号</label> 
									<input type="text" class="form-control" id="companyValues" name="companyValues"
										style="width:250px;margin-left:10px;"
										placeholder="经纪公司编号、经纪公司" value="">
								</div>
                            </div>
                        </div>
						<div class="form-inline">
                            <div class="col-md-8">
                            	<div class="form-group">
									<label style="margin-left:2px;">申请人</label> 
									<input type="text" class="form-control" id="userName" name="userName"
										style="width:250px;margin-left:10px;" placeholder="申请人姓名" value="">
								</div>
<!--                             	<div class="form-group"> -->
<!--                             		<label>OA提交状态</label>  -->
<!-- 									<select class="form-control" title="" -->
<!-- 										id="oAState" name="oAState" style="width:135px;margin-left:10px;"> -->
<!-- 										<option value="">请选择</option> -->
<!-- 										<option value="21203">提交成功</option> -->
<!-- 										<option value="21204">提交失败</option> -->
<!-- 									</select> -->
<!-- 								</div> -->
								<div class="form-group" style="margin-left: 30px;">
								<label >审核状态</label>
	                            	<t:dictSelect field="approveStatus" id="approveStatus" xmlkey="LOOKUP_Dic" dbparam="P1:254" nullOption="请选择" classvalue="10" txWidth="135px"></t:dictSelect>
	                        	</div>
                            </div>
                            <div class="col-md-4">
                            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                            <button type="button" class="btn btn-primary" id="J_search"
                                        onclick="javascript:CashBillList.search();">查询
                                </button>&nbsp;&nbsp;
                                 <a type="button" class="btn btn-default" onclick="javascript:CashBillList.reset('${list_search_page}');">重置</a>    
	                        </div>
                         </div>
                    </div>
                </div>
                <div id="load_content">
                    <div id="LoadCxt"></div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>

</html>
<script type="text/javascript">
	var searchParamMap = '${searchParamMap}';
	console.log(searchParamMap);
	if (searchParamMap != undefined && searchParamMap != null && searchParamMap != '') {
		CashBillList.setSearchParams(searchParamMap);
	}
</script>
