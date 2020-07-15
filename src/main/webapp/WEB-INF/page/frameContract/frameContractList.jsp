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
	src="${ctx}/meta/js/frameContract/frameContractList.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<form id="frameContractListForm">
		<input type="hidden"  id="storeDateType" name="storeDateType">
		<input type="hidden" name="searchParmCache" value="1">
		<input type="hidden" id="curPageTemp" value="1">
        <input type="hidden" id="sysPageChaneTemp" value="1">
		<div class="container theme-hipage ng-scope">
			<div class="row">
				<div class="page-content">
					<h4 class="border-bottom pdb10">
						<strong>联动框架协议</strong>
					</h4>
					<div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="col-md-12">
                               <div class="form-group">
									<label style="margin-left:15px;">合同编号</label> 
									<input type="text" class="form-control" id="searchValues" name="searchValues"
										style="width:250px;margin-left:10px;"
										placeholder="合同编号" value="">
								</div>
								<div class="form-group">
                                    <label>创建日期</label>
                                    <input type="text" class="calendar-icon form-control w100" name="dateStart"
                                           style="width: 135px;margin-left:10px;"
                                           id="dateStart"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                           readonly="readonly" class="ipttext Wdate"/>
                                    <span  style="margin-left:10px;">-</span>
                                    <input type="text" class="calendar-icon form-control w100" name="dateEnd"
                                           style="width: 135px;margin-left:10px;"
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
									<label style="margin-left:28px;">创建人</label> 
									<input type="text" class="form-control" id="userName" name="userName"
										style="width:250px;margin-left:10px;" placeholder="创建人姓名" value="">
								</div>
                            	<div class="form-group">
                            		<label>合同状态</label> 
									<select class="form-control" title=""
										id="approveState" name="approveState" style="width:135px;margin-left:10px;">
										<option value="">请选择</option>
										<option value="10401">草签</option>
										<option value="10402">审核中</option>
										<option value="10403">审核通过</option>
										<option value="10404">审核未通过</option>
										<option value="10405">作废</option>
										<option value="10406">终止</option>
									</select>
								</div>
                            </div>
                            <div class="col-md-5">&nbsp;&nbsp;&nbsp;&nbsp;
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                <button type="button" style="margin-left:40px;" class="btn btn-primary" id="J_search" 
	                                        onclick="javascript:FrameContract.search();">查询
	                                </button>&nbsp;&nbsp;&nbsp;
	                                <a type="button" class="btn btn-default" onclick="javascript:FrameContract.reset('${list_search_page}');">重置</a>
	                                &nbsp;
	                                <%-- <shiro:hasPermission name="/frameContract:ADDFRAMECONTRACT">
	                            		<button type="button" class="btn btn-primary" id="J_export"  
	                                        style="margin-left:40px;"  onclick="javascript:toaddFrameContract();">新增合同
	                                    </button>
                                    </shiro:hasPermission> --%>
	                            		<button type="button" class="btn btn-primary" id="J_export"  
	                                        style="margin-left:40px;" 
	                                        onclick="javascript:toaddFrameContract();">新增合同
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
		FrameContract.setSearchParams(searchParamMap);
	}
</script>
</html>
