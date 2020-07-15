<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<form id="salesStatusChangeForm" style="height: auto;width: auto" action="${ctx}/estate/changeStatusMode">
	<input type="hidden" name="id" value="${id}">
	<div role="main">
		<div class="row">
			<div class="page-content">
				<a href="javascript:EstateType.closePopup();" class="btn btn-default" style="float: right;margin-top: -10px;">&times;</a>
				<label class="fw-normal w100 text-left" style="padding-left:20px">销售状态变更</label>
				<div class="border-bottom"></div>
				<span class="fc-warning" id="errormsg" style="padding-left:20px"></span>
				<table class="table-sammary">
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w100 text-left" style="margin-left:35px">楼盘名称：</label>
							<label class="fon-normal">${name}</label>
							<span class="fc-warning"></span>
						</td>
					</tr>
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w100 text-left" style="margin-left:35px">销售状态：</label>
								<input type="radio" value="1442" name="salesStatus" disabled> <label class="fon-normal small  text-left" style="margin-right:12px">待售</label>
								<input type="radio" value="1441" name="salesStatus" checked> <label class="fon-normal small text-left" style="margin-right:12px">在售</label>
								<input type="radio" value="1443" name="salesStatus" disabled> <label class="fon-normal small  text-left" style="margin-right:12px">售完</label>
							<%-- <c:if test="${mode eq 20402}">
								<input type="radio" value="20402" name="cooperationMode" checked> <label class="fon-normal small w80 text-left">整合</label>
								<input type="radio" value="20401" name="cooperationMode"> <label class="fon-normal small">分销</label>
							</c:if>
							<c:if test="${mode eq 20401}">
								<input type="radio" value="20402" name="cooperationMode"> <label class="fon-normal small w80 text-left">整合</label>
								<input type="radio" value="20401" name="cooperationMode" checked> <label class="fon-normal small">分销</label>
							</c:if> --%>
							<span class="fc-warning"></span>
						</td>
					</tr>
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w110 text-left" style="vertical-align: top;margin-left: 35px"  for="realOpenTime"><i>* </i>实际开盘日期：</label>
		                    <input  type="text" class="calendar-icon " style="width:160px;"  name="realOpenTime" id="realOpenTime" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" class="ipttext Wdate"  notnull="true"/>
		                    <span class="fc-warning"></span>
						</td>
					</tr>
				</table>
				<div class="text-center">
                <a href="javascript:EstateType.changeSalesStatusMode(${id})" class="btn btn-primary mgt20">　确定　</a>
				<a href="javascript:EstateType.closePopup()" class="btn btn-default mgt20 mgl50">　取消　</a>
            </div>
			</div>
		</div>
	</div>
</form>