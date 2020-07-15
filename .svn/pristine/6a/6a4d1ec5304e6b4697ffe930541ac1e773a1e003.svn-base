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
	src="${ctx}/meta/js/store/storeList.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<form id="storeListForm">
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
						<strong>门店管理</strong>
					</h4>
					<div class="panel panel-default" style="margin-bottom:10px;">
						<div class="panel-body">
							<div class="form-inline">
								<div class="col-md-20">
								   <div class="form-group">
								      	<label>所在城市</label>
		                					<select class="form-control" title="" id="storeCityNo" name="storeCityNo" style="width:100px;">
					                            <option value="" selected="selected">请选择</option>
		                					    <c:forEach items="${citylist}" var="city">
					                            <option value="${city.cityNo}">${city.cityName}</option>
					                            </c:forEach>
				                            </select>
								   </div>
									<div class="form-group">
									   <label>区域</label>
									   <%--
										<select class="form-control" title="" id="districtNo"
											name="districtNo" style="width:118px;">
											<option value="" selected="selected">请选择</option>
											<c:if test="${!empty districtList}">
												<c:forEach items="${districtList}" var="district">
													<option value="${district.districtNo}">${district.districtName}</option>
												</c:forEach>
											</c:if>
										</select> --%>
										<select class="form-control" title="" id="districtNo" name="districtNo">
				                            	<option selected="selected" value="">请选择</option>       
				                        </select>
									</div>
									<div class="form-group">
										<label class="sr-only">门店编号</label> <input type="text"
											class="form-control" id="searchKey" name="searchKey"
											style="width:223px;margin-left:15px;"
											placeholder="门店编号、名称、地址、维护人" value="">
									</div>
									<!-- 添加录入时间 -->
									<div class="form-group">
										<label>创建时间</label> <input type="text" size="11"
											class="calendar-icon form-control" name="dateCreateStart"
											id="dateCreateStart"
											onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
											readonly="readonly" class="ipttext Wdate"
											onchange="checkDate(this)" /> <input type="text" size="11"
											class="calendar-icon form-control" name="dateCreateEnd"
											id="dateCreateEnd"
											onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'dateCreateStart\')}',maxDate:'%y-%M-%d'})"
											readonly="readonly" class="ipttext Wdate"
											onchange="checkDate(this)" />
									</div>
									<div class="form-group">
										<label>营业状态</label>
										<t:dictSelect field="businessStatus" id="businessStatus" xmlkey="LOOKUP_Dic" dbparam="P1:209" nullOption="请选择" classvalue="30"></t:dictSelect>
									</div>
								</div>
							</div>

							<div class="form-inline">
								<div class="col-md-20">
									<!-- 2017/06/30 Add cning Start -->
									<div class="form-group">
										<label>所属中心</label>
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
									<!-- 2017/06/30 Add cning End -->
									<!-- 签约类型 -->
									<div class="form-group">
										<label>合作模式</label>
										<t:dictSelect field="contractType" id="contractType" xmlkey="LOOKUP_Dic"
													  dbparam="P1:103" nullOption="请选择" classvalue="30"
													  jsonValue='{"1":"未签"}'></t:dictSelect>
									</div>
									<!-- 合同状态 -->
									<div class="form-group">
										<label>合同状态</label>
										<t:dictSelect field="contractStatus" id="contractStatus" xmlkey="LOOKUP_Dic"
													  dbparam="P1:104" nullOption="请选择" classvalue="30"></t:dictSelect>
									</div>
									<!-- 装修进度 -->
									<div class="form-group">
										<label>装修进度</label>
										<t:dictSelect field="decorationStatus" id="decorationStatus" xmlkey="LOOKUP_Dic"
													  dbparam="P1:163" nullOption="请选择" classvalue="30"></t:dictSelect>
									</div>
									<div class="form-group">
										<!--  <label style="width:120px;text-align:right;margin-left:107px;">门店资质等级</label>  -->
										<label>资质等级</label>
										<select class="form-control" id="storetype" name="storetype"
												style="width:100px;">
											<option value="">请选择</option>
											<option value="19901">甲类</option>
											<option value="19902">乙类</option>
										</select>
									</div>
									<div class="form-group" style="text-align:right;margin-left:10px;">
										<a class="btn btn-primary" href="javascript:Store.setStoreDateType('');Store.search();">搜索</a>&nbsp;
										<a type="button" class="btn btn-default" onclick="javascript:Store.reset('${list_search_page}');">重置</a>
										<%--<a class="btn btn-primary" href="${ctx}/store/c">新增门店</a>--%>
									</div>
								</div>
							</div>
							<div class="form-inline">
							
							</div>
						</div>
						
					</div>
					<div class="form-inline" style="margin-bottom:25px;">
						<div class="col-md-12">
							<div class="form-group label-radio-group" id="divStoreType">
								<label style="width:56px;margin-left:17px;">筛选</label>
								<a class="btn active" href="${ctx}/store">全部</a>
								<a class="btn" href="javascript:Store.setStoreDateType('1');Store.search();">今日新增</a>
								<a class="btn" href="javascript:Store.setStoreDateType('2');Store.search();">本周新增</a>
								<a class="btn" href="javascript:Store.setStoreDateType('3');Store.search();">本月新增</a>
								<a class="btn" href="javascript:Store.setStoreDateType('4');Store.search();">待续签门店</a>
								<a class="btn" href="javascript:Store.setStoreDateType('5');Store.search();">待乙转甲门店</a>
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
