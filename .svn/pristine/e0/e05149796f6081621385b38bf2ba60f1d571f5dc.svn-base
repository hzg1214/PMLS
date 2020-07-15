<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/store/storeDecorationState.js?_v=${vs}"></script> 
<body>
	<form id="decorationStateForm">
		<div class="form-group">
		<label for="decorationState">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;装修状态：</label>
			<select style="width:100px" size="1" id="decorationState" name="decorationState" onchange="changeState()">
				<c:forEach items="${dcrtStateList}" var="state">
					<c:choose>
						<c:when test="${state.dicCode == store.decorationState}">
							<option value="${state.dicCode}" selected>${state.dicValue}</option>
						</c:when>
						<c:otherwise>
							<option value="${state.dicCode}">${state.dicValue}</option>
						</c:otherwise>
					</c:choose>
	            </c:forEach>
            </select>
		</div>
		<ul class="list-inline form-inline">
			<li><div class="form-group">
					<label for="decorationCompNm">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;装修公司名称：</label> <input
						name="decorationCompNm" id="decorationCompNm"
						value="${store.decorationCompNm}" dataType="normal" maxlength="50">
						<br><span class="fc-warning"></span>
				</div></li>
			<li><div class="form-group">
					<label for="decorationContractNo">装修合同会签单号：</label> <input
						name="decorationContractNo" id="decorationContractNo"
						value="${store.decorationContractNo}" dataType="normal" maxlength="50">
						<br><span class="fc-warning"></span>
				</div></li>
			<li><div class="form-group">
					<label for="dateDecorationBill">装修发票开具日期：</label> <input type="text"
					class="calendar-icon  w150" name="dateDecorationBill"
					id="dateDecorationBill"
					onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
					class="ipttext Wdate" value="${sdk:ymd2(store.dateDecorationBill)}"/> 
					<br><span class="fc-warning"></span>
				</div></li>
		</ul>
		
		<ul class="list-inline form-inline">
			<li><div class="form-group">
					<label for="oaFlopNo">OA翻牌验收单号：</label> <input
						name="oaFlopNo" id="oaFlopNo"
						value="${store.oaFlopNo}" dataType="normal" maxlength="50">
						<br><span class="fc-warning"></span>
				</div></li>
			<li><div class="form-group">
					<label for="dateFlopCkAccept">翻牌验收通过日期：</label> 
					<input type="text"
						class="calendar-icon  w150" name="dateFlopCkAccept"
						id="dateFlopCkAccept"
						onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
						class="ipttext Wdate" value="${sdk:ymd2(store.dateFlopCkAccept)}"/> 
						<br><span class="fc-warning"></span>
				</div></li>
		</ul>
	</form>
</body>
