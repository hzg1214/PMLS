<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/WEB-INF/page/common/meta.jsp"%>
	<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
	<script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
	<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
	<script type="text/javascript" src="${ctx}/meta/js/companyInfoDetail/companyInfoDetail.js?_v=${vs}"></script>
	<style type="text/css">
		.form-inline {
			overflow: inherit;
		}
		.text-overflow{
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
			width:100%;
		}
	</style>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
		<div class="container theme-hipage ng-scope" role="main" id="contentAll">
			<div class="row">
				<div class="page-content">
					<h4 class="border-bottom pdb10">
						<strong>公司信息明细表</strong>
					</h4>
					<form id="companyInfoDetailForm">
						<input type="hidden" name="searchParmCache" value="1">
						<input type="hidden" id="curPageTemp" value="1">
						<input type="hidden" id="sysPageChaneTemp" value="1">
					<div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
							<div class="form-group" id="city" style="margin-left:40px;">
								<label >归属城市&nbsp;</label>
								<div class="multi-select" id="cityId" name="cityId">
									<input type="hidden" id="cityCode" class="multi-select-value" readonly="readonly">
									<input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
										   style="width: 180px;">
									<ul class="multi-select-list">
										<li class="multi-select-item">
											<label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
										</li>
									</ul>
								</div>
							</div>
							<div class="form-group"  style="margin-left: 55px;">
                               <label>公司编号</label>
                               <input type="text" class="form-control" id="companyNo" name="companyNo" style="width:250px;" placeholder="公司编号" value="">
                            </div>
							<div class="form-group">
                               <label>公司名称</label>
                               <input type="text" class="form-control" id="companyName" name="companyName" style="width:250px;" placeholder="公司名称" value="">
                            </div>
						</div>
                        <div class="form-inline">
						   <div class="form-group" style="margin-left:15px;">
								<label>业绩查询日期</label>
                                <input type="text" size="11" class="calendar-icon form-control" name="dateCreateStart" id="dateCreateStart" readonly="readonly" class="ipttext Wdate"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" onchange="checkDate(this)"/>
                                <input type="text" size="11" class="calendar-icon form-control" name="dateCreateEnd" id="dateCreateEnd" readonly="readonly" class="ipttext Wdate"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" onchange="checkDate(this)"/>
							</div>
							<div class="form-group">
                               <label>注册地址</label>
                               <input type="text" class="form-control" id="address" name="address" style="width:250px;" placeholder="注册地址" value="">
                            </div>
                            <div class="form-group" style="margin-left: 30px;">
                           		<label>类型</label>
                                   <select class="form-control" title="" id="switchType" name="switchType" style="width:116px;">
                                       <option value="0" selected>请选择</option>
                                       <option value="1">翻牌</option>
                                       <option value="2">渠道</option>
                                   </select>
                            </div>
						</div>
                        <div class="form-inline">
							<div class="pull-right">
								<a class="btn btn-primary" href="javascript:CompanyInfoDetail.search();" style="margin-left:20px;">查询</a>&nbsp;
								<a type="button" class="btn btn-default" style="margin-left:10px;" onclick="javascript:CompanyInfoDetail.reset();">重置</a>
								<a class="btn btn-primary" style="margin-left:35px;" href="javascript:CompanyInfoDetail.export();" id="btn-reset">导出</a>&nbsp;
							</div>
                         </div>
                           
                    </div>
                </div>
					<div id="load_content">
						<div class="" style="height: auto;" id="LoadCxt"></div>
					</div>
					</form>
				</div>
			</div>
		</div>
</body>
</html>