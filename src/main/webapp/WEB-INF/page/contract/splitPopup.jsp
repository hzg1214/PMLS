<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<body>
	<input type="hidden" id='arrivalDepositFeeDb' value="${contractInfo.contract.arrivalDepositFee}">
	<input type="hidden" id='depositFeeDb' value="${contractInfo.contract.depositFee}">
	<input type="hidden" id='contractNo' value="${contractInfo.contract.contractNo}">
	<input type="hidden" id='restSplitDepositFeeSplit' value="${contractInfo.contract.restSplitDepositFee}">
	<h4>
		<label >保证金分账</label>
		<span> <a href="javascript:void(0)"
			style="float:right;" onclick="Contract.closeSplit();">关闭</a>
		</span>
	</h4>
	<p id="warningSpan" style="color: red; font-weight: bold; "></p>
	<HR>
	<c:set var="unArrivalDepositFee" scope="session" value="${contractInfo.contract.totleDepositFee-contractInfo.contract.arrivalDepositFee}"/>
	<ul class="list-inline half form-inline">
		<li><label>合同编号：</label>${contractInfo.contract.contractNo}</li>
		<li><label>合同类型：</label>${contractInfo.contract.contractTypeName}</li>
		<li><label>甲方名称：</label>${contractInfo.contract.partyB}</li>
	</ul>
	<ul class="list-inline half form-inline">
		<li><label>应收保证金总额(元)：</label>${contractInfo.contract.totleDepositFee}</li>
		<li><label>已到账保证金(元)：</label><span id="arrivalDepositFee">${contractInfo.contract.arrivalDepositFee}</span></li>
		<li><label>未分账保证金(元)：</label>${contractInfo.contract.restSplitDepositFee}</li>
	</ul>
	<table id="splitPopupTable"
		class="table table-striped table-hover table-bordered">
		<tr>
			<th>门店编号</th>
			<th>门店名称</th>
			<th>门店地址</th>
			<th>应收保证金(元)</th>
			<th>保证金到账日期</th>
			<th>是否到账</th>
		</tr>
		<c:forEach items="${contractInfo.storeDetails}" var="storelist">
			<tr>
				<td>${storelist.storeNo}</td>
				<td>${storelist.name}</td>
				<td>${storelist.address}</td>
				<td>${contractInfo.contract.depositFee}</td>
				<td>${sdk:ymd2(storelist.dateArrivalAct)}</td>
				<td><input type="checkbox" id="isArrivalAct"
					name="isArrivalAct" value="${storelist.storeId}"
					<c:if test="${storelist.isArrivalAct == 1}">checked="true" disabled="true"</c:if>>已到账</td>
			</tr>
		</c:forEach>
	</table>
</body>
