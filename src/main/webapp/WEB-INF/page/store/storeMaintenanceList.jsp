<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript"
	src="${ctx}/meta/js/store/storeMaintenanceList.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<form id="storeMaintenanceListForm">
	    <input type="hidden" name="cityNo" value="${userInfo.cityNo}">
		<%--<input type="hidden" name="districtsNo" value="${districtsNo}"> --%>
		<input type="hidden"  id="storeDateType" name="storeDateType">
		<input type="hidden" name="searchParmCache" value="1">
		<input type="hidden" id="curPageTemp" value="1">
        <input type="hidden" id="sysPageChaneTemp" value="1">
		<div class="container theme-hipage ng-scope">
			<div class="row">
				<div class="page-content">
					<h4 class="border-bottom pdb10">
						<strong>门店维护</strong>
					</h4>
					<div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="col-md-12">

                                <div class="form-group">
								      	<label>所在城市</label>
		                					<select class="form-control" title="" id="storeCityNo" name="storeCityNo" >
					                            <option value="" selected="selected">请选择</option>
		                					    <c:forEach items="${citylist}" var="city">
					                            <option value="${city.cityNo}">${city.cityName}</option>
					                            </c:forEach>
				                            </select>
								 </div>
								 <div class="form-group">
									   <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;区域</label>
										<select class="form-control" title="" id="districtNo" name="districtNo" style="width:120px;">
			                            	<option selected="selected" value="">请选择</option>       
				                        </select>
	                            </select>
								</div>

                                <div class="form-group">
										<label class="sr-only" >门店编号</label> <input type="text"
											class="form-control" id="searchValues" name="searchValues"
											style="width:300px;margin-left:60px;"
											placeholder="请输入门店编号、名称、地址、维护人" value="">
									</div>
								<div class="form-group">
										<label style="margin-left:10px;">门店所属中心</label>
										<select class="form-control" title="" id="groupId"
												name="groupId" style="width:100px;">
											<option value="" selected="selected">请选择</option>
											<c:if test="${!empty groupList}">
												<c:forEach items="${groupList}" var="group">
													<option value="${group.groupId}">${group.groupName}</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
                            </div>
                        </div>
							<div class="form-inline"></div>
                        <div class="form-inline">
                            <div class="col-md-8">
									<div class="form-group">
										<label>合作模式</label>
										<t:dictSelect field="contractType" id="contractType" xmlkey="LOOKUP_Dic" 
													  dbparam="P1:103" nullOption="请选择" classvalue="30"
													  jsonValue='{"1":"未签"}' ></t:dictSelect>
									</div>
									<!-- 合同状态 -->
									<div class="form-group">
										<label>合同状态</label>
										<t:dictSelect field="contractStatus" id="contractStatus" xmlkey="LOOKUP_Dic"
													  dbparam="P1:104" nullOption="请选择" classvalue="30"></t:dictSelect>
									</div>
                            </div>
                            <div class="col-md-4">
                            		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                <button type="button" class="btn btn-primary" id="J_search" 
	                                        onclick="javascript:StoreMaintenance.search();">查询
	                                </button>&nbsp;&nbsp;&nbsp;
	                                <a type="button" class="btn btn-default" onclick="javascript:StoreMaintenance.reset('${list_search_page}');">重置</a>
	                                &nbsp;
	                                <button type="button" class="btn btn-primary" id="J_export"
	                                        onclick="javascript:StoreMaintenance.achieveMentChange();" style="width:120px;margin-left:15px;">批量变更维护人
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
		Store.setSearchParams(searchParamMap);
	}
</script>
</html>
