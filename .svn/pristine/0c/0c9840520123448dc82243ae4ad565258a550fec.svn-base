<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/contract/contractList.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/contract/exchangeCenter.js?_v=${vs}"></script>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>合同管理</strong></h4>
                <form  id="contractForm">
                	<input type="hidden"  id="orderName" name="orderName" value="dateCreate" >
					<input type="hidden"  id="orderType" name="orderType" value="DESC" >
					<input type="hidden"  id="signDateType" name="signDateType" >
					<input type="hidden" name="searchParmCache" value="1">
					<input type="hidden" id="curPageTemp" value="1">
                    <input type="hidden" id="sysPageChaneTemp" value="1">
					<div class="panel panel-default" style="margin-bottom:10px;">
						<div class="panel-body">
							<div class="form-inline">
								<div class="col-md-12">
									<div class="form-group">
										<!-- <label>合同编号</label> <input size="12" type="text"
											class="form-control" id="contractNo" name="contractNo"
											placeholder="合同编号" value="">-->
											<label style="width:84px; text-align:right;">合同类别</label>
											<select class="form-control" title="originalContractdistinction" id="originalContractdistinction"
													name="originalContractdistinction" style="width:103px;">
													<option value="0" selected="selected">请选择</option>
													<option value="1">新签</option>
													<option value="2">续签</option>
											</select>
											<div class="form-group">
												<label class="sr-only">合同编号</label> <input type="text"
													class="form-control" id="searchKey" name="searchKey"
													style="width:292px;margin-left: 21px;"
													placeholder="合同编号、协议书编号、经纪公司或创建人" value="">
											</div>
									</div> 
									<div class="form-group">
										<label>创建时间</label> <input type="text" size="11"
											class="calendar-icon form-control" name="dateCreateStart"
											id="dateCreateStart"
											onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
											readonly="readonly" class="ipttext Wdate"
											onchange="checkDate(this)" /> <input type="text" size="11"
											class="calendar-icon form-control" name="dateCreateEnd"
											id="dateCreateEnd"
											onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
											readonly="readonly" class="ipttext Wdate"
											onchange="checkDate(this)" />
									</div>
									<div class="form-group"><label>合作模式</label> 
										<select class="form-control" title=""
											id="contracttype" name="contracttype">
											<option value="">请选择</option>
											<option value="10301">A</option>
											<option value="10302">B</option>
											<option value="10303">C</option>
											<option value="10304">A转B</option>
											<option value="10306">D</option>
											<option value="10307">授牌</option>
										</select>
									</div>
									
									<!-- <div class="form-group">
										<label>协议书编号</label> <input size="20" type="text"
											class="form-control" id="agreementNo" name="agreementNo"
											placeholder="协议书编号" value="">
									</div> -->
								</div>
							</div>
							<div class="form-inline">								
								<div class="col-md-9">
									<div class="form-group"><label>合同审核状态</label> 
										<select class="form-control" title=""
											id="contractStatus" name="contractStatus" style="width:103px;">
											<option value="">请选择</option>
											<option value="10401">草签</option>
											<option value="10402">审核中</option>
											<option value="10403">审核通过</option>
											<option value="10404">审核未通过</option>
											<option value="10405">作废</option>
											<option value="10406">终止</option>
										</select>
									</div>
								<!-- 	<div class="form-group">
										<label>公司名称</label> <input size="12" type="text"
											class="form-control" id="companyName" name="companyName"
											placeholder="公司名称" value="">
									</div>&nbsp; -->
									<div class="form-group">
										<label>保证金到账状态</label> <select class="form-control" title=""
											id="depositFeeStatus" name="depositFeeStatus" style="width:103px;">
											<option value="0">请选择</option>
											<!-- <option value="1">保证金为0</option> -->
											<option value="2">未到账</option>
											<option value="3">部分到账</option>
											<option value="4">已到账</option>
										</select> 
									</div>
									<div class="form-group">
										<label>退款状态</label>
											<select class="form-control" title=""
											id="refundState" name="refundState" style="width:103px;">
											<option value="0">请选择</option>
											<option value="1">未退款</option>
											<option value="2">部分退款</option>
											<option value="3">已退款</option>
										</select> 
											<%-- <t:dictSelect field="refundState" id="refundState"
												 xmlkey="LOOKUP_Dic" dbparam="P1:178" nullOption="请选择" 
											 	classvalue="30">
											 </t:dictSelect> --%>
										</select> 
									</div>
									<!-- <div class="form-group">
										<label>合同生效状态</label>
										<select class="form-control" title="" id="Valid" name="Valid">
											<option value="0">请选择</option>
											<option value="1">待生效</option>
											<option value="2">生效</option>
											<option value="3">过期</option>
										</select>
									</div> -->
									<div class="form-group">
										<label>是否为乙转甲</label>
										<select class="form-control" title="" id="isaTob" name="isaTob">
											<option value="0">请选择</option>
											<option value="1">是</option>
											<option value="2">否</option>
										</select>
									</div>
									<!-- <div class="form-group"><label>合同状态</label> <select class="form-control" title=""
											id="contractStatus" name="contractStatus">
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
										<label>创建人</label> <input size="12" type="text"
											class="form-control" id="userCreate" name="userCreate"
											placeholder="创建人" value="">
									</div>-->
								</div>
								<div class="col-md-3">
									<button type="button" class="btn btn-primary" id="J_search"
										onclick="javascript:Contract.setSignDateType('');Contract.search();" style="margin-left:33px;">搜索</button>&nbsp;
									<a type="button" class="btn btn-default" onclick="javascript:Contract.reset('${list_search_page}');">重置</a>
									&nbsp; 
									<shiro:hasPermission name="/contract:CONTRACT_ADD">
										<a type="button" class="btn btn-primary" href="javascript:Contract.create();" style="float:right">新增合同</a>
									</shiro:hasPermission>
								</div>
							</div>
						</div>
					</div>
					<div class="form-inline" style="margin-bottom:25px;">
						<div class="col-md-12">
							<div class="form-group label-radio-group" id="divSignType">
								<label style="margin-left:17px;width:84px;">筛选</label>
								<a class="btn active" href="${ctx}/contract">全部</a>
								<a class="btn" href="javascript:Contract.setSignDateType('1');Contract.search();">今日签约</a>
								<a class="btn" href="javascript:Contract.setSignDateType('2');Contract.search();">本周签约</a>
								<a class="btn" href="javascript:Contract.setSignDateType('3');Contract.search();">本月签约</a>
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
		Contract.setSearchParams(searchParamMap);
	}
</script>
</html>
