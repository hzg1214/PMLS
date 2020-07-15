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
	src="${ctx}/meta/js/store/storeReceiveList.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<form id="storeReceiveListForm">
	    <input type="hidden" name="orderTypeFlag" id="orderTypeFlag" value="${orderTypeFlag}">
	    <input type="hidden" name="cityNo" value="${userInfo.cityNo}">
		<input type="hidden"  id="storeDateType" name="storeDateType">
		<input type="hidden" name="searchParmCache" value="1">
		<input type="hidden" id="curPageTemp" value="1">
        <input type="hidden" id="sysPageChaneTemp" value="1">
		<div class="container theme-hipage ng-scope">
			<div class="row">
		        	<ul class="nav nav-tabs nav-tabs-header">
			            <li role="presentation" class="active">
			                <a href="${ctx}/storeReceive?orderType=21401">收款</a>
			            </li>
						<li role="presentation">
							<a href="${ctx}/storeReceive?orderType=21402">收款调整</a>
						</li>
			        </ul>
			        <div class="page-content">
			        <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="col-md-12">
                               <div class="form-group">
									<label>编号</label>
									<input type="text" class="form-control" id="searchValues" name="searchValues"
										style="width:250px;margin-left:10px;"
										placeholder="收款、合同、协议书、OA收款单" value="">
								</div>
                               <div class="form-group">
									<label style="margin-left:40px;">经纪公司</label>
									<input type="text" class="form-control" id="companyName" name="companyName"
										style="width:250px;margin-left:10px;"
										placeholder="经纪公司" value="">
								</div>
								<div class="form-group">
									<label style="margin-left:70px;margin-right: 10px;">收款类型</label>
									<t:dictSelect field="feeType" id="feeType" xmlkey="LOOKUP_Dic" dbparam="P1:180" nullOption="请选择" classvalue="10" txWidth="200px"></t:dictSelect>
								</div>
                            </div>
                        </div>
						<div class="form-inline">
                            <div class="col-md-12">
								<div class="form-group">
									<label >收款状态</label>
									<select class="form-control" id="status" name="status" style="width:220px;">
										<option value="">请选择</option>
										<option value="0">未支付</option>
										<option value="10">已支付</option>
										<option value="20">已确认</option>
										<option value="30">账单确认</option>
										<option value="40">账单异常</option>
									</select>
								</div>
								<div class="form-group">
									<label style="margin-left:65px;">申请人</label>
									<input type="text" class="form-control" id="userName" name="userName"
										style="width:250px;margin-left:10px;" value="">
								</div>
								<div class="form-group">
									<label style="margin-left:5px;">收款单OA审核状态</label>
									<select class="form-control" id="approveStatus" name="approveStatus" style="width:200px;margin-left:11px;">
										<option value="">请选择</option>
										<option value="21601">待提交OA</option>
										<option value="21602">审批中</option>
										<option value="21603">审批通过</option>
										<option value="21604">审批不通过</option>
									</select>
								</div>
                            </div>
                         </div>
						<div class="form-inline">
							<div class="col-md-12">
								<a type="button" style="    width: 80px;float:right;"  class="btn btn-default" onclick="javascript:StoreReceive.reset('${list_search_page}');">重置</a>
								<button type="button" style="margin-right:15px;float:right;    width: 80px;" class="btn btn-primary" id="J_search"
										onclick="javascript:StoreReceive.search();">查询
								</button>
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
		StoreReceive.setSearchParams(searchParamMap);
	}
</script>
</html>
