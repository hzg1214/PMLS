<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<p>
	<strong>联动规则</strong>
</p>
<c:if test="${estateRule eq null}">
    <input type="hidden" name="ruId" value="-9999">
</c:if>
<c:if test="${estateRule ne null}">
    <input type="hidden" name="ruId" value="${estateRule.id}">
</c:if>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">认证类型<i>*</i>：</label>
			<c:forEach items="${authenticationKbnList}" var="list">
				<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="authenticationKbn" <c:if test="${list.dicCode eq estateRule.authenticationKbn}">checked</c:if>>
				<label class="fon-normal small">${list.dicValue}</label>
			</c:forEach>
			<span class="fc-warning"></span>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">提前报备期：</label> <select class="form-control" title=""
				name="advanceReportHH">
				<c:if test="${!empty advanceReportHHList}">
					<c:forEach items="${advanceReportHHList}" var="list">
						<option value="${list}" <c:if test="${list eq estateRule.advanceReportHH}">selected="selected"</c:if>>${list}</option>
					</c:forEach>
				</c:if>
			</select>时 <select class="form-control" title="" name="advanceReportMM">
				<c:if test="${!empty advanceReportMMList}">
					<c:forEach items="${advanceReportMMList}" var="list">
						<option value="${list}" <c:if test="${list eq estateRule.advanceReportMM}">selected="selected"</c:if>>${list}</option>
					</c:forEach>
				</c:if>
			</select>分
		</div>
	</li>
	<li>
		<div class="form-group" style="width:auto;margin-left: 5px;">
			<label class="fw-normal w100 text-right">带看保护期<i>*</i>：</label> <input type="text"
				class="form-control w80" name="relationProtectPeriod" id="relationProtectPeriod" placeholder=""
				notnull="true" maxlength="3" dataType="normal" value="${estateRule.relationProtectPeriod}">天 <span class="fc-warning"></span>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">带看奖励：</label> <input type="text" class="form-control w125"
				name="relationReward" id="relationReward" placeholder=""  maxlength="7"
				dataType="needMoney" value="${estateRule.relationReward}">元
		</div>
	</li>
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">起始日期</label> <input type="text" class="calendar-icon"
				name="relationDtStart" id="relationDtStart"
				onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'relationDtEnd\')}'})"
				readonly="readonly" class="ipttext Wdate" value="${sdk:ymd2(estateRule.relationDtStart)}" />
		</div>
	</li>
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">截止日期</label> <input type="text" class="calendar-icon"
				name="relationDtEnd" id="relationDtEnd"
				onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'relationDtStart\')}'})"
				readonly="readonly" class="ipttext Wdate" value="${sdk:ymd2(estateRule.relationDtEnd)}" />
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">认筹奖励：</label> <input type="text" class="form-control w125"
				name="pledgedReward" id="pledgedReward" placeholder=""  maxlength="7" dataType="needMoney" value="${estateRule.pledgedReward}">元
		</div>
	</li>
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">起始日期</label> <input type="text" class="calendar-icon"
				name="pledgedDtStart" id="pledgedDtStart"
				onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'pledgedDtEnd\')}'})"
				readonly="readonly" class="ipttext Wdate" value="${sdk:ymd2(estateRule.pledgedDtStart)}" />
		</div>
	</li>
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">截止日期</label> <input type="text" class="calendar-icon"
				name="pledgedDtEnd" id="pledgedDtEnd"
				onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'pledgedDtStart\')}'})"
				readonly="readonly" class="ipttext Wdate" value="${sdk:ymd2(estateRule.pledgedDtEnd)}" />
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">大定奖励：</label> <input type="text" class="form-control w125"
				name="subscribedReward" id="subscribedReward" placeholder=""  maxlength="7"
				dataType="needMoney" value="${estateRule.subscribedReward}">元
		</div>
	</li>
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">起始日期</label> <input type="text" class="calendar-icon"
				name="subscribedDtStart" id="subscribedDtStart"
				onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'subscribedDtEnd\')}'})"
				readonly="readonly" class="ipttext Wdate" value="${sdk:ymd2(estateRule.subscribedDtStart)}" />
		</div>
	</li>
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">截止日期</label> <input type="text" class="calendar-icon"
				name="subscribedDtEnd" id="subscribedDtEnd"
				onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'subscribedDtStart\')}'})"
				readonly="readonly" class="ipttext Wdate" value="${sdk:ymd2(estateRule.subscribedDtEnd)}" />
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">成销奖励：</label> <input type="text"
				class="form-control w125" name="bargainReward" id="bargainReward" placeholder=""
				maxlength="7" dataType="needMoney" value="${estateRule.bargainReward}">元 <span class="fc-warning"></span>
		</div>
	</li>
</ul>
<%-- <ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">佣金方式<i>*</i>：</label>
			<c:forEach items="${commissionKbnList}" var="list">
				<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="commissionKbn"  onclick="getcommissionStatus();" <c:if test="${list.dicCode eq estateRule.commissionKbn}">checked</c:if>>
				<label class="fon-normal small">${list.dicValue}</label>
			</c:forEach>
			<span class="fc-warning"></span>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">佣金<i>*</i>：</label> <input type="text"
				class="form-control w125" name="commission" id="commission" placeholder="" notnull="true" maxlength="7"
				dataType="needMoney" value="${estateRule.commission}"><span id="commissionStatus">${estateRule.commissionKbn=='1481'?'元':'%'}</span> <span class="fc-warning"></span>
		</div>
	</li>
	<li>
		<div class="form-group" style="width:auto;margin-left: 18px;">
			<label class="fw-normal w100 text-right">结佣期限<i>*</i>：</label> <input type="text"
				class="form-control w80" name="commissionPeriod" id="commissionPeriod" placeholder="" notnull="true"
				maxlength="3" dataType="posNumWithZero" value="${estateRule.commissionPeriod}">天 <span class="fc-warning"></span>
		</div>
	</li>
</ul> --%>
<ul class="list-inline form-inline">
     <li>
         <div class="form-group">
         <label class="fw-normal w100 text-right" style="vertical-align: top;">收入标的<i>*</i>：</label>
			 <textarea id="incomeSubject" name="incomeSubject" cols="45" placeholder="" maxlength="250"
					   rows="5" notnull="true" dataType="normal3" style="resize: none;width: 750px;height: 100px;">${estateRule.incomeSubject}</textarea>
			 <span class="fc-warning"></span>
          </div>
     </li>
</ul>
<ul class="list-inline form-inline">
	<li>
	    <div class="form-group">
	        <label class="fw-normal w100 text-right" style="vertical-align: top;">收入结算条件<i>*</i>：</label>
	        
	        <select class="form-control" title="" name="commissionCondition" id="commissionCondition" notnull="true">
				<option
                   <c:if test="${empty estateRule.commissionCondition or estateRule.commissionCondition eq ''}">selected="selected"</c:if>
                   value="">请选择
                </option>
                <c:if test="${!empty commissionConditionList}">
                    <c:forEach items="${commissionConditionList}" var="list">
                        <option value="${list.dicCode}"
                                <c:if test="${estateRule.commissionCondition eq list.dicCode}">selected="selected"</c:if>>${list.dicValue}</option>
                    </c:forEach>
                </c:if>
			</select>
	    </div>  
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right" style="vertical-align: top;">收入结算描述<i>*</i>：</label>
			<textarea id="commissionMemo" name="commissionMemo" cols="45" placeholder="" maxlength="250"
					  rows="5" notnull="true" dataType="normal3" style="resize: none;width: 750px;height: 100px;">${estateRule.commissionMemo}</textarea>
			<span class="fc-warning"></span>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	 <li>
	     <div class="form-group">
	        <label class="fw-normal w100 text-right" style="vertical-align: top;">返佣标准<i>*</i>：</label>
			 <textarea id="rtnCommission" name="rtnCommission" cols="45" placeholder="" maxlength="250"
					   rows="5" notnull="true" dataType="normal3" style="resize: none;width: 750px;height: 100px;">${estateRule.rtnCommission}</textarea>
			 <span class="fc-warning"></span>
	     </div>
	  </li>                   
</ul>
<ul class="list-inline form-inline">
      <li>
          <div class="form-group">
             <label class="fw-normal w100 text-right" style="vertical-align: top;">返佣结算条件<i>*</i>：</label>
			  <textarea id="rtnCommissionMemo" name="rtnCommissionMemo" cols="45" placeholder="" maxlength="250"
						rows="5" notnull="true" dataType="normal3" style="resize: none;width: 750px;height: 100px;">${estateRule.rtnCommissionMemo}</textarea>
			  <span class="fc-warning"></span>
             </div>
       </li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">结佣方式：</label>
			<c:forEach items="${payKbnList}" var="list">
				<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="payKbn" <c:if test="${list.dicCode eq estateRule.payKbn}">checked</c:if>>
				<label class="fon-normal small">${list.dicValue}</label>
			</c:forEach>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">销售方式：</label>
			<c:forEach items="${saleKbnList}" var="list">
				<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="saleKbn" <c:if test="${list.dicCode eq estateRule.saleKbn}">checked</c:if>>
				<label class="fon-normal small">${list.dicValue}</label>
			</c:forEach>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">报备开始日<i>*</i>：</label> <input type="text"
				class="calendar-icon" name="reportDtStart" id="reportDtStart"
				onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'reportDtEnd\')}'})"
				readonly="readonly" class="ipttext Wdate" value="${sdk:ymd2(estateRule.reportDtStart)}" notnull="true" />
		</div>
	</li>
	<li>
		<div class="form-group" style="width:auto;margin-left: 18px;">
			<label class="fw-normal w100 text-right">报备截止日<i>*</i>：</label> <input type="text"
				class="calendar-icon" name="reportDtEnd" id="reportDtEnd"
				onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'reportDtStart\')}'})"
				readonly="readonly" class="ipttext Wdate" value="${sdk:ymd2(estateRule.reportDtEnd)}" notnull="true" />
		</div>
	</li>
</ul>
<ul class="list-inline form-inline" style="display:none">
	<li>
		<div class="form-group" >
			<label class="fw-normal w100 text-right">报备模式：</label>
			<c:forEach items="${reportKbnList}" var="list">
				<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="reportKbn" <c:if test="${list.dicCode eq estateRule.reportKbn}">checked</c:if>>
				<label class="fon-normal small">${list.dicValue}</label>
			</c:forEach>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right">隐号报备：</label> <input type="radio" value="1" name="hideNumberFlg" <c:if test="${'1' eq estateRule.hideNumberFlg}">checked</c:if>><label
				 class="fon-normal small">支持</label> <input type="radio" value="0"
				name="hideNumberFlg" <c:if test="${'0' eq estateRule.hideNumberFlg}">checked</c:if>><label class="fon-normal small">不支持</label>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right" style="vertical-align: top;">报备规则：</label>
			<textarea name="reportRule" cols="30" placeholder="请输入" maxlength="500" rows="10"
				style="resize: none;width: 750px;">${estateRule.reportRule}</textarea>
		</div>
	</li>
</ul>
<%-- <ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w140 text-right" style="vertical-align: top;width:90px;margin-left: 15px;">中介佣金说明<i>*</i>：</label>
			<textarea name="commissionRule" id="commissionRule" cols="30" placeholder="请输入"  maxlength="500" rows="10"
				style="resize: none;width:750px" notnull="true">${estateRule.commissionRule}</textarea>
		</div>
	</li>
</ul> --%>