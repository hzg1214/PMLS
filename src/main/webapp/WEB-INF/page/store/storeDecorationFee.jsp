<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<body>
	<form id="decorationFeeForm">
		<ul class="list-inline form-inline">
			<li><div class="form-group">
					<label for="decoractionFeeOne">装修翻牌费1:</label> <input
						name="decoractionFeeOne" id="decoractionFeeOne"
						value="${store.decoractionFeeOne}" dataType="needMoney"
						notnull="true">元 
						<br><span class="fc-warning"></span>
				</div></li>
			<li><div class="form-group">
					<label for="datePayDcrtFeeOne">支付日期1:</label> <input type="text"
						class="calendar-icon  w150" name="datePayDcrtFeeOne"
						id="datePayDcrtFeeOne"
						onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
						class="ipttext Wdate" notnull="true" value="${sdk:ymd2(store.datePayDcrtFeeOne)}"/> 
						<br><span class="fc-warning"></span>
				</div></li>
			<li><div class="form-group">
					<label for="pleasePayNoOne">请款单号1:</label> <input name="pleasePayNoOne"
						id="pleasePayNoOne" value="${store.pleasePayNoOne}"
						dataType="normal" notnull="true" maxlength="20"> 
						<br><span class="fc-warning"></span>
				</div></li>
		</ul>

		<ul class="list-inline form-inline">
			<li><div class="form-group">
					<label for="decoractionFeeTwo">装修翻牌费2:</label> <input
						name="decoractionFeeTwo" id="decoractionFeeTwo"
						value="${store.decoractionFeeTwo}" dataType="needMoney">元
					<br>
					<span class="fc-warning"></span>
				</div></li>
			<li><div class="form-group">
					<label for="datePayDcrtFeeTwo">支付日期2:</label> <input type="text"
						class="calendar-icon  w150" name="datePayDcrtFeeTwo"
						id="datePayDcrtFeeTwo"
						onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
						class="ipttext Wdate" value="${sdk:ymd2(store.datePayDcrtFeeTwo)}"/> 
						<br><span class="fc-warning"></span>
				</div></li>
			<li><div class="form-group">
					<label for="pleasePayNoTwo">请款单号2:</label> <input name="pleasePayNoTwo"
						id="pleasePayNoTwo" value="${store.pleasePayNoTwo}"
						dataType="normal" maxlength="20"> 
						<br><span class="fc-warning"></span>
				</div></li>
		</ul>
	</form>
</body>
