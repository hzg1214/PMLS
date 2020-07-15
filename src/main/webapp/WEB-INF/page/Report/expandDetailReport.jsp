<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/WEB-INF/page/common/meta.jsp"%>
	<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
	<link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/meta/js/report/expand/multi.select.js?_v=${vs}"></script>
	<script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
	<script type="text/javascript" src="${ctx}/meta/js/report/expand/expandDetailReport.js?_v=${vs}"></script>
	<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>

</head>
<style type="text/css"> 
table th{ 
	text-align: center; 
}
.form-inline{
	overflow:inherit;
} 
/* .table>tbody>tr>td, .table>tbody>tr>th, .table>tfoot>tr>td, .table>tfoot>tr>th, .table>thead>tr>td, .table>thead>tr>th { */
/*     padding: 8px 6px; */
/*     line-height: 1.42857143; */
/*     vertical-align: top; */
/*     border-top: 1px solid #ddd; */
/* } */
</style>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
            	<form  id="expanddetail">
                <h4 class="border-bottom pdb10"><strong>拓展明细表</strong></h4>
				<div class="panel panel-default" id="contentAll">
					 <div class="panel-body" style="padding-bottom:10px; width: 1200px;">
						  <div class="form-inline">
							   <div class="form-group" id="stage"  style="width: 250px; margin-left: 0px;">
									<label class="lab" style="padding-left: 5px;">业务阶段：</label>
									<div class="multi-select"style="width: 140px;">
									   <input type="hidden" class="multi-select-value" readonly="readonly" name="stages">
									   <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 140px; height: 24px; margin-left: 0px;" />
									   <ul class="multi-select-list">
										   <li class="multi-select-item">
											   <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
										   </li>
										   <li class="multi-select-item">
											   <label><input type="checkbox" value="initialDate" data-text="合同草签日期"><span>合同草签日期</span></label>
										   </li>
										   <li class="multi-select-item">
											   <label><input type="checkbox" value="performDate" data-text="审核通过日期"><span>审核通过日期</span></label>
										   </li>
										   <li class="multi-select-item">
											   <label><input type="checkbox" value="flipCompleDate" data-text="翻牌完成日期"><span>翻牌完成日期</span></label>
										   </li>
										   <li class="multi-select-item">
											   <label><input type="checkbox" value="flipPassDate" data-text="验收通过日期"><span>验收通过日期</span></label>
										   </li>
										   <li class="multi-select-item">
											   <label><input type="checkbox" value="dateArrivalAct" data-text="保证金到账日期"><span>保证金到账日期</span></label>
										   </li>
									   </ul>
									</div>
							   </div>
							   <div class="form-group" style="width: 340px; margin-left: 0px;">
									<label class="lab" style="padding-left: 5px;">查询日期：</label>
									<input type="text" size="11" class="calendar-icon form-control" name="startDate" id="startDate"
										onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
										readonly="readonly" class="ipttext Wdate"
										onchange="checkDate(this)" style="width: 110px; height: 24px; margin-left: 0px; padding-left: 5px; font-size: 12px;"/> -
									<input type="text" size="11" class="calendar-icon form-control" name="endDate" id="endDate"
										onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
										readonly="readonly" class="ipttext Wdate"
										onchange="checkDate(this)" style="width: 110px; height: 24px; margin-left: 0px; padding-left: 5px; font-size: 12px;"/>
							   </div>
							  <div class="form-group" style="width: 250px; margin-left: 0px;">
								  <label class="lab" style="padding-left: 5px;">合同编号：</label>
								  <input type="text" class="multi-select-text" style="width: 157px; height: 24px; margin-left: 0px;" id="contractNo" name="contractNo">
							  </div>
							   <div class="form-group" style="width: 250px; margin-left: 0px;" id="contractType">
									<label class="lab" style="padding-left: 5px;">合作模式：</label>
									<div class="multi-select">
										<input type="hidden" class="multi-select-value" readonly="readonly" id="contractTypes" name="contractTypes">
										<input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 140px; height: 24px; margin-left: 0px;">
										<ul class="multi-select-list">
											<li class="multi-select-item">
												<label><input type="checkbox" class="multi-select-checkall" value="10301,10302,10304"><span>全部</span></label>
											</li>
											<li class="multi-select-item">
												<label><input type="checkbox" value="10301" data-text="A版"><span>A版</span></label>
											</li>
											<li class="multi-select-item">
												<label><input type="checkbox" value="10302" data-text="B版"><span>B版</span></label>
											</li>
											<%--<li class="multi-select-item">--%>
												<%--<label><input type="checkbox" value="10303" data-text="C版"><span>C版</span></label>--%>
											<%--</li>--%>
											<li class="multi-select-item">
												<label><input type="checkbox" value="10304" data-text="A转B版"><span>A转B版</span></label>
											</li>
											<li class="multi-select-item">
												<label><input type="checkbox" value="10307" data-text="授牌"><span>授牌</span></label>
											</li>
										</ul>
									</div>
							   </div>
						  </div>

						  <div class="form-inline">
							  <div class="form-group" style="width: 250px; margin-left: 0px;">
								  <label class="lab" style="padding-left: 5px;">门店编号：</label>
								  <input type="text" class="multi-select-text" style="width: 140px; height: 24px; " id="storeNo" name="storeNo">
							  </div>
							  <div class="form-group" style="width: 340px; margin-left: 0px;">
								  <label class="lab" style="padding-left: 5px;">门店地址：</label>
								  <input type="text" class="multi-select-text" style="width: 238px; height: 24px; margin-left: 0px;" id="storeAddress" name="storeAddress">
							  </div>
							  <div class="form-group" style="width: 250px; margin-left: 0px;">
								  <label class="lab" style="padding-left: 5px;">经纪公司：</label>
								  <input type="text" class="multi-select-text" style="width: 157px; height: 24px; margin-left: 0px;" id="company" name="company">
							  </div>

							  <div class="form-group" style="width: 250px; margin-left: 0px;">
								  <label class="lab" style="padding-left: 5px;">架构年份：</label>
								  <select class="multi-select-text" id="organization" name="organization" style="width: 140px; height: 24px; margin-left: 0px; font-size: 12px;">
									  <option value="2019">2019</option>
									  <option value="2018">2018</option>
									  <option value="2017">2017</option>
								  </select>
							  </div>
						  </div>
						  <div class="form-inline">
							  <div class="form-group" name="group1" style="width: 250px; margin-left: 0px;">
								  <label class="lab" style="padding-left: 5px;">归属区域：</label>
								  <div class="multi-select" id="region" name="region" style="width: 140px;">
									  <input type="hidden" class="multi-select-value" readonly="readonly" name="regionCodes">
									  <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 140px; height: 24px; margin-left: 0px;">
									  <ul class="multi-select-list">
										  <li class="multi-select-item">
											  <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
										  </li>
									  </ul>
								  </div>
							  </div>
							  <div class="form-group" name="group2" style="width: 340px; margin-left: 0px;">
								  <label class="lab" style="padding-left: 5px;">归属城市：</label>
								  <div class="multi-select" id="areaCity" name="areaCity" style="width: 235px;">
									  <input type="hidden" class="multi-select-value" readonly="readonly" name="areaCityCodes">
									  <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 238px; height: 24px; margin-left: 0px;">
									  <ul class="multi-select-list">
										  <li class="multi-select-item">
											  <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
										  </li>
									  </ul>
								  </div>
							  </div>
							  <div class="form-group" name="group3" style="width: 250px; margin-left: 0px;">
								  <label class="lab" style="padding-left: 5px;">所在城市：</label>
								  <div class="multi-select" id="city" name="city" style="width: 157px;">
									  <input type="hidden" class="multi-select-value" readonly="readonly" name="cityIds">
									  <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 157px; height: 24px; margin-left: 0px;">
									  <ul class="multi-select-list">
										  <li class="multi-select-item">
											  <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
										  </li>
									  </ul>
								  </div>
							  </div>
							  <div class="form-group" name="group4" style="width: 250px; margin-left: 0px;">
								  <label class="lab" style="padding-left: 5px;">归属中心：</label>
								  <div class="multi-select" id="centerGroup" name="centerGroup" style="width: 140px;">
									  <input type="hidden" class="multi-select-value" readonly="readonly" name="centerIds">
									  <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 140px; height: 24px; margin-left: 0px;">
									  <ul class="multi-select-list">
										  <li class="multi-select-item">
											  <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
										  </li>
									  </ul>
								  </div>
							  </div>
						  </div>
						  <div class="form-inline">
							  <div class="form-group" id="person" style="width: 250px; margin-left: 0px;">
								  <label class="lab" style="padding-left: 5px;">归属人员：</label>
								  <input class="w70 validatebox-text" type="text" style="width: 140px; height: 24px; margin-left: 0px; font-size: 12px;" id="maintainer" name="maintainer">
							  </div>
							  <div class="form-group" style="width: 340px; margin-left: 0px;">
								  <label class="lab" style="padding-left: 5px;">门店城市：</label>
								  <input type="text" class="multi-select-text" style="width: 238px; height: 24px; margin-left: 0px;" id="storeCityName" name="storeCityName">
							  </div>
							  <div class="form-group" style="width: 250px; margin-left: 0px;" id="businessPlaceType">
								  <label class="lab" style="padding-left: 5px;">经营场所类型：</label>
								  <div class="multi-select">
									  <input type="hidden" class="multi-select-value" readonly="readonly" id="businessPlaceTypes" name="businessPlaceTypes">
									  <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 130px; height: 24px; margin-left: 0px;">
									  <ul class="multi-select-list">
										  <li class="multi-select-item">
											  <label><input type="checkbox" class="multi-select-checkall" value="22401,22402"><span>全部</span></label>
										  </li>
										  <li class="multi-select-item">
											  <label><input type="checkbox" value="22401" data-text="商铺"><span>商铺</span></label>
										  </li>
										  <li class="multi-select-item">
											  <label><input type="checkbox" value="22402" data-text="办公楼内"><span>办公楼内</span></label>
										  </li>
									  </ul>
								  </div>
							  </div>

                              <div class="form-group" style="width: 250px; margin-left: 0px;" id="storeSignStatus">
                                  <label class="lab" style="padding-left: 5px;">签约状态：</label>
                                  <div class="multi-select">
                                      <input type="hidden" class="multi-select-value" readonly="readonly" id="storeSignStatuses" name="storeSignStatuses">
                                      <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 140px; height: 24px; margin-left: 0px;">
                                      <ul class="multi-select-list">
                                          <li class="multi-select-item">
                                              <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                          </li>
                                          <li class="multi-select-item">
                                              <label><input type="checkbox" value="合作中" data-text="合作中"><span>合作中</span></label>
                                          </li>
                                          <li class="multi-select-item">
                                              <label><input type="checkbox" value="解约终止" data-text="解约终止"><span>解约终止</span></label>
                                          </li>
                                          <li class="multi-select-item">
                                              <label><input type="checkbox" value="停业终止" data-text="停业终止"><span>停业终止</span></label>
                                          </li>
                                          <li class="multi-select-item">
                                              <label><input type="checkbox" value="作废" data-text="作废"><span>作废</span></label>
                                          </li>
                                          <li class="multi-select-item">
                                              <label><input type="checkbox" value="草签完成" data-text="草签完成"><span>草签完成</span></label>
                                          </li>
                                          <li class="multi-select-item">
                                              <label><input type="checkbox" value="草签过期" data-text="草签过期"><span>草签过期</span></label>
                                          </li>
                                          <li class="multi-select-item">
                                              <label><input type="checkbox" value="草签作废" data-text="草签作废"><span>草签作废</span></label>
                                          </li>
                                      </ul>
                                  </div>
                              </div>
						  </div>

                         <div class="form-inline">
                             <div class="form-group" style="width: 250px; margin-left: 0px;" id="storeSizeScale">
                                 <label class="lab" style="padding-left: 5px;">门店规模：</label>
                                 <div class="multi-select">
                                     <input type="hidden" class="multi-select-value" readonly="readonly" id="storeSizeScales" name="storeSizeScales">
                                     <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 140px; height: 24px; margin-left: 0px;">
                                     <ul class="multi-select-list">
                                         <li class="multi-select-item">
                                             <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
                                         </li>
                                         <li class="multi-select-item">
                                             <label><input type="checkbox" value="22901" data-text="大型"><span>大型</span></label>
                                         </li>
                                         <li class="multi-select-item">
                                             <label><input type="checkbox" value="22902" data-text="小型"><span>小型</span></label>
                                         </li>
                                         <li class="multi-select-item">
                                             <label><input type="checkbox" value="22903" data-text="微型"><span>微型</span></label>
                                         </li>
                                     </ul>
                                 </div>
                             </div>

							 <div class="form-group" style="width: 250px; margin-left: 0px;" id="storeType">
								 <label class="lab" style="padding-left: 5px;">门店类型：</label>
								 <div class="multi-select">
									 <input type="hidden" class="multi-select-value" readonly="readonly" id="storeTypes" name="storeTypes">
									 <input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择" style="width: 140px; height: 24px; margin-left: 0px;">
									 <ul class="multi-select-list">
										 <li class="multi-select-item">
											 <label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
										 </li>
										 <li class="multi-select-item">
											 <label><input type="checkbox" value="23401" data-text="门店"><span>门店</span></label>
										 </li>
										 <li class="multi-select-item">
											 <label><input type="checkbox" value="23402" data-text="社区"><span>社区</span></label>
										 </li>
										 <li class="multi-select-item">
											 <label><input type="checkbox" value="23403" data-text="渠道"><span>渠道</span></label>
										 </li>
										 <li class="multi-select-item">
											 <label><input type="checkbox" value="23404" data-text="代理"><span>代理</span></label>
										 </li>
									 </ul>
								 </div>
							 </div>

                             <div style="float:right; " id="searchDiv">
                                 <div class="form-group" style="width: 250px; margin-left: 20px;" >
                                     <button type="button" class="btn btn-primary btn-flat" id="search" style="height: 25px; margin-top: 0px; line-height: 5px; margin-left: 14px;" onclick="searchExpandDetail()">查询</button>&nbsp;&nbsp;
                                     <button type="button" class="btn btn-primary btn-flat" id="btn-reset" style="height: 25px; margin-top: 0px; line-height: 5px; " onclick="exportExpandDetail()">导出</button>
                                 </div>
                             </div>

							 <div class="form-inline">
								 <li class="three_li" style="width: 650px">
									 <label class="lab" style="width: 200px;font-size: 12px;font-weight: bold;color: red">您当前有<span id="reportSize">${userReportSize}</span>个文件正在下载中……</label>
								 </li>
								 <li class="three_li" style="width: 150px">

								 </li>
								 <li class="three_li" style="width: 100px">

								 </li>
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
