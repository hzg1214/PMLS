<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/store/storeStopCancelList.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>

	<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
	<script type="text/javascript" src="${ctx}/meta/js/crm/storeDetail.js?_v=${vs}"></script>
	<script src="http://api.map.baidu.com/api?v=2.0&ak=${sysConfig.baiduApiKey}" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
	<style type="text/css">
		.form-inline {
			overflow: inherit;
		}
	</style>

</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container">
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/crm/crmLeftMenu.jsp"
				flush="true">
				<jsp:param value="110403" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content">
				<div class="page-content">
					<h4>
						<strong>停业撤销审核</strong>
						<a type="button" class="btn btn-primary" href="${ctx}/store?searchParam=1">返回</a>
					</h4>
					<!-- 搜索条件区域 -->
					<form id="storeStopCancelForm">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="form-inline">
									<div>
										<div class="form-group" id="city">
											<label style="padding-right: 15px;">城市</label>
											<div class="multi-select" id="cityId" name="cityId" style="width: 160px;">
												<input type="hidden" class="multi-select-value" readonly="readonly">
												<input type="text" class="multi-select-text" style="width: 160px;" readonly="readonly" placeholder="请选择"
													   style="width: 60px;">
												<ul class="multi-select-list">
													<li class="multi-select-item">
														<label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
													</li>
												</ul>
											</div>
										</div>
										<div class="form-group" id="center">
											<label id="centerLabel" style="padding-left:47px;">中心</label>
											<div class="multi-select" id="centerId" name="centerId" style="width: 250px;">
												<input type="hidden" class="multi-select-value" readonly="readonly">
												<input type="text" class="multi-select-text" readonly="readonly" style="width: 250px;" placeholder="请选择"
													   style="width: 110px;">
												<ul class="multi-select-list">
													<li class="multi-select-item">
														<label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
													</li>
												</ul>
											</div>
										</div>

										<div class="form-group">
											<label style="padding-left:50px;">门店</label> <input type="text" style="width:160px"
												class="form-control" id="storeNo" name="storeNo" placeholder="门店编号、门店名称">
										</div>
									</div>
								</div>
                                <div class="form-inline">
                                    <div>
										<div class="form-group">
											<label>创建人</label> <input type="text" style="width:160px"
																								 class="form-control" id="userCode" name="userCode" placeholder="工号、姓名">
										</div>
                                        <div class="form-group">
                                            <label>创建时间</label>
                                            <input type="text" class="calendar-icon form-control w100" name="dateCreateBegin" style="width: 120px"
                                                   id="dateCreateBegin"
                                                   onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'dateCreateEnd\');}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                                   readonly="readonly" class="ipttext Wdate"/>
                                            <span>-</span>
                                            <input type="text" class="calendar-icon form-control w100" name="dateCreateEnd" style="width: 120px"
                                                   id="dateCreateEnd"
                                                   onFocus="WdatePicker({minDate:'#F{$dp.$D(\'dateCreateBegin\');}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                                   readonly="readonly" class="ipttext Wdate"/>
                                        </div>
										<div class="form-group">
											<label>审核状态</label>
											<t:dictSelect field="approveStatus" id="approveStatus" xmlkey="LOOKUP_Dic" dbparam="P1:210" nullOption="请选择" classvalue="30" txWidth="160px;"></t:dictSelect>
										</div>

                                        <div class="pull-right">
                                            <button type="button" class="btn btn-primary" id="J_search"
                                                onclick="javascript:BizStop.search();">搜索</button>
                                            <button type="button" class="btn btn-default" onclick="javascript:BizStop.reset('${list_search_page}');">重置</button>
                                        </div>
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
	</div>
</body>
</html>
