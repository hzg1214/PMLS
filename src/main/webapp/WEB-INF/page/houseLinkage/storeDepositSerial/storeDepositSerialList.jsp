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
				src="${ctx}/meta/js/houseLinkage/storeDepositSerial/storeDepositSerial.js?_v=${vs}"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/bootstrap-multiselect.css">
	<script type="text/javascript" src="${ctx}/meta/js/common/bootstrap-multiselect.js?_v=${vs}"></script>
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
	<form id="StoreDepositSerialForm" id="contentAll">
		<input type="hidden"  id="storeDateType" name="storeDateType">
		<input type="hidden" name="searchParmCache" value="1">
		<input type="hidden" id="curPageTemp" value="1">
        <input type="hidden" id="sysPageChaneTemp" value="1">
		<div class="container theme-hipage ng-scope">
			<div class="row">
				<div class="page-content">
					<h4 class="border-bottom pdb10">
						<strong>保证金流水表</strong>
					</h4>
					<div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline" style="overflow: inherit;">
								   <div class="form-group" style="margin-left:25px;">
										<label style="margin-right:15px;">核算主体</label>
									   <script type="text/javascript">
                                           var data = $(document).ready(function() {
                                               $('#accountProjectCode').multiselect("destroy").multiselect({
                                                   enableFiltering: true,
                                                   filterPlaceholder:'请输入核算主体',
                                                   buttonWidth: '220px',
                                                   nonSelectedText:'请选择',
                                                   nSelectedText:'个被选中',
                                                   includeSelectAllOption:false,
                                                   selectAllText:'全选/取消',
                                                   allSelectedText:'已选中所有',
                                                   selectedClass: 'active1',
                                                   numberDisplayed : 10,
                                                   dropRight: true,
                                                   maxHeight: 275,
                                                   dropUp: true,
                                                   onChange: function(element, checked) {
                                                       var brands = $('#accountProjectCode option:selected');
                                                       var selected = [];
                                                       $(brands).each(function(index, brand){
                                                           selected.push([$(this).val()]);
                                                       });
                                                       $("#accountProjectCodeList").val(selected);
                                                       console.log(selected);
                                                   }
                                               });
                                               $("span.multiselect-selected-text").css({"text-align":'left',display:'inline-block'});
                                               $("b.caret").css("margin-left","100px");
                                           });
									   </script>
										<select class="form-control" id="accountProjectCode" name="accountProjectCode" multiple="multiple">
											<c:forEach items="${accountList}" var="account">
												<option value="${account.accountProjectCode}">${account.accountProject}</option>
											</c:forEach>
										</select>
									</div>
								<input type="hidden" id="accountProjectCodeList" name="accountProjectCodeList" />
									<div class="form-group" style="margin-left:53px;">
										<label>入账日期</label> <input type="text" size="11"
											class="calendar-icon form-control" name="dateCreateStart"
											id="dateCreateStart"
											style="margin-left:15px;"
											onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
											readonly="readonly" class="ipttext Wdate"
											onchange="checkDate(this)" /> <input type="text" size="11"
											class="calendar-icon form-control" name="dateCreateEnd"
											id="dateCreateEnd"
											onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'dateCreateStart\')}',maxDate:'%y-%M-%d'})"
											readonly="readonly" class="ipttext Wdate"
											onchange="checkDate(this)" />
									</div>
							<div class="form-group" style="margin-left:53px;">
								<label>经纪公司</label> <input type="text"
														   class="form-control" id="companyCondition" name="companyCondition"
														   style="width:240px;margin-left:15px;"
														   placeholder="公司编号、公司名称" value="">
							</div>
                        </div>
                        <div class="form-inline" >
								<div class="form-group" style="margin-left:25px;">
									<label>中介门店</label> <input type="text"
										class="form-control" id="storeCondition" name="storeCondition"
										style="width:220px;margin-left:15px;"
										placeholder="门店编号、名称" value="">
								</div>
								
								<div class="form-group" style="margin-left:19px;">
									<label>协议书编号</label> <input type="text"
										class="form-control" id="agreementNo" name="agreementNo"
										style="width:235px;margin-left:15px;"
										placeholder="协议书编号" value="">
								</div>
							<div class="form-group" style="margin-left:61px;">
								<label>OA单号</label> <input type="text"
														   class="form-control" id="oaNo" name="oaNo"
														   style="width:240px;margin-left:15px;"
														   placeholder="OA单号" value="">
							</div>
                         </div>
                         <div class="form-inline">
								<div class="form-group" style="margin-left:25px;">
									<label>表单类型</label> 
									<select class="form-control" title=""
										id="orderType" name="orderType" style="width:220px;margin-left:15px;">
										<option value="">请选择</option>
										<option value="21401">收款单</option>
										<option value="21402">调整单</option>
										<option value="21405">退款单</option>
									</select>
                            </div>
							 <div class="pull-right">
								 <a class="btn btn-primary" href="javascript:StoreDepositSerial.search();" style="margin-left:20px;">查询</a>&nbsp;
								 <a type="button" class="btn btn-default" style="margin-left:10px;" onclick="javascript:StoreDepositSerial.reset();">重置</a>
								 <a class="btn btn-primary" style="margin-left:30px;" href="javascript:StoreDepositSerial.export();">导出</a>&nbsp;
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

</html>