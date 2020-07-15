<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript"
	src="${ctx}/meta/js/store/storePaymentList.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<form id="storePaymentListForm">
	    <input type="hidden" name="cityNo" value="${userInfo.cityNo}">
		<input type="hidden"  id="storeDateType" name="storeDateType">
		<input type="hidden" name="searchParmCache" value="1">
		<input type="hidden" id="curPageTemp" value="1">
        <input type="hidden" id="sysPageChaneTemp" value="1">
		<div class="container theme-hipage ng-scope">
			<div class="row">
				<div class="page-content">
					<h4 class="border-bottom pdb10">
						<strong>保证金退款</strong>
					</h4>
					<div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="col-md-12">
                               <div class="form-group">
									<label style="margin-left:15px;">编号</label> 
									<input type="text" class="form-control" id="searchValues" name="searchValues"
										style="width:250px;margin-left:10px;"
										placeholder="退款、合同、协议书、OA收款单" value="">
								</div>
								<div class="form-group">
                                    <label>退款日期</label>
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
									<label >经纪公司</label> 
									<input type="text" class="form-control" id="companyName" name="companyName"
										style="width:250px;margin-left:10px;"
										placeholder="经纪公司" value="">
								</div>
                            </div>
                        </div>
						<div class="form-inline"></div>
                        <div class="form-inline">
                            <div class="col-md-7">
								<div class="form-group">
									<label>申请人</label> 
									<input type="text" class="form-control" id="userName" name="userName"
										style="width:250px;margin-left:10px;" placeholder="申请人" value="">
								</div>
                            </div>
                            <div class="col-md-5">&nbsp;&nbsp;&nbsp;&nbsp;
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           			<shiro:hasPermission name="/storePayment:ADDPAYMENT">
	                            		<button type="button" class="btn btn-primary" id="J_export"
	                                        style="margin-left:40px;"  onclick="javascript:addPayment();">新增退款
	                                    </button>
                                    </shiro:hasPermission>
	                                <button type="button" style="margin-left:40px;" class="btn btn-primary" id="J_search" 
	                                        onclick="javascript:StorePayment.search();">查询
	                                </button>&nbsp;&nbsp;&nbsp;
	                                <a type="button" class="btn btn-default" onclick="javascript:StorePayment.reset('${list_search_page}');">重置</a>
	                                &nbsp;
	                            </div>
                         </div>
                           
                    </div>
                </div>
					
					<div id="load_content">
						<div class="" style="height: auto;" id="LoadCxt"></div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	var searchParamMap = '${searchParamMap}';
	console.log(searchParamMap);
	if (searchParamMap != undefined && searchParamMap != null && searchParamMap != '') {
		StorePayment.setSearchParams(searchParamMap);
	}
</script>
</html>
