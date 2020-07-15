<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<body>
	<form id="depositForm">
		<div class="form-group">
			<label for="deposit">&nbsp;&nbsp;&nbsp;保证金：</label>
			<input name="deposit" id="deposit" value="${store.deposit}" dataType="needMoney" notnull="true">元
			<br><span class="fc-warning"></span>
		</div>
		<div class="form-group">
			<label for="dateAccountDeposit">到账日期：</label>
			<input type="text"
						class="calendar-icon  w150" name=dateAccountDeposit
						id="dateAccountDeposit"
						onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
						class="ipttext Wdate" notnull="true" value="${sdk:ymd2(store.dateAccountDeposit)}"/> 
			<br><span class="fc-warning"></span>
		</div>
	</form>
</body>
