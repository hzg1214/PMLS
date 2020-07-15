<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<h4 class="border-bottom" style="padding-top: 0;padding-bottom: 0"><strong>编辑项目</strong></h4>
<p>
	<strong>基本信息</strong>
</p>
<input type="hidden" id="opEstateId" name="opEstateId" value="${estate.opEstateId}">
<input type="hidden" id="opEstateNm" name="opEstateNm" value="${estate.opEstateNm}">
<input type="hidden" id="countryNo" name="countryNo" value="${estate.countryNo}">
<input type="hidden" id="bigCustomerFlagStr"  value="${estate.bigCustomerFlag}">
<%-- <input type="hidden" id="mattressNailTextHidden"  value="${estate.mattressNailName}"> --%>
<ul class="list-inline form-inline">
		<li style="width: 600px;">
			<div class="form-group">
				<label class="fw-normal w140 text-right">楼盘位置<i>*</i>：</label>
				<c:if test="${estate.estatePosition eq null or estate.estatePosition eq 0}">
					<input type="radio" value="0" name="estatePosition" disabled="disabled" checked/><label class="fon-normal small">国内</label>
					<input type="radio" value="1" name="estatePosition" disabled="disabled"/><label class="fon-normal small">海外</label>
				</c:if>
				<c:if test="${estate.estatePosition eq 1}">
					<input type="radio" value="0" name="estatePosition" disabled="disabled"/><label class="fon-normal small">国内</label>
					<input type="radio" value="1" name="estatePosition" disabled="disabled" checked/><label class="fon-normal small">海外</label>
				</c:if>
			</div>
		</li>
</ul>
<ul class="list-inline form-inline">
	<li style="width: 600px;">
		<div class="form-group">
			<label class="fw-normal w140 text-right">楼盘名<i>*</i>：</label>
			<input type="text" class="form-control w300" name="estateNm" id="estateNm" placeholder="" notnull="true" maxlength="25"
				dataType="normal" value="${estate.estateNm}">
<c:if test="${addEstateManual eq 'false'}"><button type="button" class="btn btn-primary" id="estateNmBtn" onclick="javascript:EstateAdd.selectFromOp();" style="vertical-align: top;">选择</button></c:if>
			<span class="fc-warning"></span>
		</div>
	</li>
	<li>
		<div class="form-group">
			<label class="fw-normal w140 text-right">备案名：</label>
			<input type="text" class="form-control w300" name="recordName" id="recordName" placeholder="" maxlength="25"
				dataType="normal" value="${estate.recordName}"> <span class="fc-warning"></span>
		</div>
	</li>

</ul>
<ul class="list-inline form-inline">
	<li style="width: 600px;">
		<div class="form-group">
			<label class="fw-normal w140 text-right">推广名：</label>
			<input type="text" class="form-control w300" name="promotionName" id="promotionName" placeholder="" maxlength="25"
				dataType="normal" value="${estate.promotionName}"> <span class="fc-warning"></span>
		</div>
	</li>
	<li>
		<div class="form-group">
			<label class="fw-normal w140 text-right">签约名：</label>
			<input type="text" class="form-control w300" name="signName" id="signName" placeholder="" maxlength="25"
				dataType="normal" value="${estate.signName}"> <span class="fc-warning"></span>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li style="width: 600px;">
		<div class="form-group">
			<label class="fw-normal w140 text-right">项目归属城市<i>*</i>：</label>
			<select class="form-control" title="" id="cityNo" name="cityNo" readonly>
	            <option value="${userInfo.cityNo}" selected>${userInfo.cityName}</option>
            </select>
		</div>
	</li>
	<li>
		<div class="form-group">
            <label class="fw-normal w140 text-right">业绩归属项目部<i>*</i>：</label>
            <select class="form-control" title="" id="projectDepartmentId" name="projectDepartmentId" readonly>
                  <c:forEach items="${rebacklist}" var="list">   
                    <c:if test="${list.projectDepartmentId eq estate.projectDepartmentId}"><option value="${list.projectDepartmentId}" selected>${list.projectDepartment}</option></c:if>
                    <c:if test="${list.projectDepartmentId ne estate.projectDepartmentId}"><option value="${list.projectDepartmentId}">${list.projectDepartment}</option></c:if>
                  </c:forEach>
            </select>
        </div>
	</li>
</ul>
<c:if test="${estate.estatePosition eq 0}">
	<div class="form-group">
		<label class="fw-normal w140 text-right">楼盘地址<i>*</i>：</label>
		<div style="display:inline-block;width:127px;">
			 <select class="form-control selectpicker" title="" id="realityCityNo" name="realityCityNo" notnull="true" style="width:150px;" data-live-search="true"></select>
		 </div>
		 <div style="display:inline-block; vertical-align: middle;">
			 <select class="form-control" title="" id="districtNo" name="districtId" notnull="true" style="width:150px;"></select>
		 </div>
		 <input type="text" class="form-control" name="address" id="address" placeholder="具体地址信息"
			notnull="true" value="${estate.address}" maxlength="50" style="display:inline-block; vertical-align: middle;width: 450px;"> <span class="fc-warning"></span>
	</div>
</c:if>
<c:if test="${estate.estatePosition eq 1}">
	<div class="form-group">
		<label class="fw-normal w140 text-right">楼盘地址<i>*</i>：</label>
		<div style="display:inline-block;width:160px; vertical-align: middle;">
			<select class="form-control" title="" id="countryNo" name="countryNo" notnull="true" style="width:150px;" data-live-search="true">
				<option value="">请选择国家</option>
				<c:forEach var="country" items="${countryList}">
					<c:if test="${estate.countryNo eq country.countryNo}">
						<option value="${country.countryNo}" countryNm="${country.countryName}" selected>${country.countryName}</option>
					</c:if>
					<c:if test="${estate.countryNo ne country.countryNo && country.countryName ne '中国'}">
						<option value="${country.countryNo}" countryNm="${country.countryName}">${country.countryName}</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		<input type="text" class="form-control" name="address" id="address" placeholder="具体地址信息"
			   notnull="true" value="${estate.address}" maxlength="50" style="display:inline-block; vertical-align: middle;width: 450px;"> <span class="fc-warning"></span>
	</div>
</c:if>
<ul class="list-inline form-inline">
	<li style="width: 600px;">
	    <div class="form-group">
			<%--<label class="fw-normal w100 text-right">合作模式<i>*</i>：</label>--%>
			<%--<c:forEach items="${cooperationTypeList}" var="list">--%>
				<%--<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="cooperationMode" <c:if test="${list.dicCode eq estate.cooperationMode}">checked</c:if>>--%>
				<%--<label class="fon-normal small">${list.dicValue}</label>--%>
			<%--</c:forEach>--%>

				<input type="hidden" name="cooperationMode" value="-1"/>
				<label class="fw-normal w140 text-right">业务模式<i>*</i>：</label>
				<select class="form-control" title="" id="businessModel" name="businessModel" notnull="true" style="width:280px;" data-live-search="true">
					<option value="">请选择</option>
					<c:forEach items="${businessModelTypeList}" var="list">
						<option value=${list.dicCode}
							<c:if test="${list.dicCode eq estate.businessModel}">selected</c:if>
						>${list.dicValue}</option>
					</c:forEach>
				</select>
			<span class="fc-warning"></span>
		</div>
	</li>
	<li>
		<div class="form-group" style="width:auto;">
			<label class="fw-normal w140 text-right">销售状态<i>*</i>：</label>
			<c:forEach items="${salesStatusList}" var="list">
				<c:if test="${list.dicCode ne 1443}">
				<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="salesStatus" <c:if test="${list.dicCode eq estate.salesStatus}">checked</c:if>>
				<label class="fon-normal small">${list.dicValue}</label>
				</c:if>
			</c:forEach>
			<span class="fc-warning"></span>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li style="width: 600px;">
	    <div class="form-group">
			<label class="fw-normal w140 text-right">是否独家<i>*</i>：</label>
			<c:forEach items="${particularList}" var="list">
				<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="particularFlag" <c:if test="${list.dicCode eq estate.particularFlag}">checked</c:if>>
				<label class="fon-normal small">${list.dicValue}</label>
			</c:forEach>
			<span class="fc-warning"></span>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w140 text-right">总价段<i>*</i>：</label> <input type="text"
				class="form-control w125" name="salePriceUnitMin" id="salePriceUnitMin" placeholder="" notnull="true"
				maxlength="15" dataType="needMoney" value="${estate.salePriceUnitMin}">-
				<input type="text"
				class="form-control w125" name="salePriceUnitMax" id="salePriceUnitMax" placeholder="" notnull="true"
				maxlength="15" dataType="needMoney" value="${estate.salePriceUnitMax}">万元/套 <span class="fc-warning"></span>
		</div>
	</li>
</ul>
<%--<ul class="list-inline form-inline">--%>
	<%--<li>--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w100 text-right">标签：</label>--%>
			<%--<c:forEach items="${estate.markLst}" var="list" varStatus="status">--%>
				<%--<input type="text" class="form-control" style="width: 140px;" name="mark[${status.index}]" placeholder="" maxlength="4" value="${list}">--%>
				<%--<c:if test="${status.first eq true}"><span class="btn btn-link btn-add-input-tag" onclick="addMark(this);">新增标签</span></c:if>--%>
				<%--<c:if test="${status.first eq false}"><span class="btn btn-link btn-add-input-tag" onclick="delMark(this);">删除</span></c:if>--%>
			<%--</c:forEach>--%>
			<%--<c:if test="${fn:length(estate.markLst) le 0}">--%>
				<%--<input type="text" class="form-control" style="width: 140px;" name="mark[0]" placeholder="" maxlength="4" value="">--%>
				<%--<span class="btn btn-link btn-add-input-tag" onclick="addMark(this);">新增标签</span>--%>
			<%--</c:if>--%>
		<%--</div>--%>
	<%--</li>--%>
<%--</ul>--%>
<%--<ul class="list-inline form-inline">--%>
	<%--<li style="width: 600px;">--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w130 text-right">预计开盘日期：</label>--%>
			<%--<input type="text" class="calendar-icon" name="openTime" id="openTime" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'houseTransferTime\')}'})" readonly="readonly" class="ipttext Wdate" value="${sdk:ymd3(estate.openTime)}"/>--%>
			<%--<span class="fc-warning"></span>--%>
		<%--</div>--%>
	<%--</li>--%>
	<%--<li>--%>
		<%--<div class="form-group" style="width:auto;">--%>
			<%--<label class="fw-normal w100 text-right">预计交房日期：</label>--%>
			<%--<input type="text" class="calendar-icon" name="houseTransferTime" id="houseTransferTime" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'openTime\')}'})" readonly="readonly" class="ipttext Wdate" value="${estate.houseTransferTime}"/>--%>
			<%--<span class="fc-warning"></span>--%>
		<%--</div>--%>
	<%--</li>--%>
<%--</ul>--%>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w140 text-right">合作方类型<i>*</i>：</label>
			<c:forEach items="${partnerList}" var="list">
				<input onchange="autoSetDevMer()" type="radio" value="${list.dicCode}" id="${list.dicCode}" id="partner" name="partner" <c:if test="${list.dicCode eq estate.partner}">checked</c:if>>
				<label class="fon-normal small">${list.dicValue}</label>
			</c:forEach>
			<span class="fc-warning"></span>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li style="width:600px">
		<div class="form-group">
			<label class="fw-normal w140 text-right">合作方名称<i>*</i>：</label> <input type="text"
				class="form-control w300" onchange="autoSetDevMer()" name="partnerNm" id="partnerNm" placeholder="" notnull="true" maxlength="25"
				 value="${estate.partnerNm}"> <span class="fc-warning"></span>
		</div>
	</li>
	<li>
		<div class="form-group">
			<label class="fw-normal w140 text-right" >是否可垫佣甲方<i>*</i>：</label> <input type="radio" value="1" name="custodyFlg" onchange="custodyChange('1');" <c:if test="${ 1 eq estate.custodyFlg}">checked</c:if>><label
				 class="fon-normal small">是</label> <input type="radio" value="0"
				name="custodyFlg" onchange="custodyChange('1');" <c:if test="${ 0 eq estate.custodyFlg}">checked</c:if>><label class="fon-normal small">否</label>
				<span class="fc-warning"></span>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline" id="mattressNail" style="display:none;overflow:initial;">
   	<li>
       <div class="form-group">
           <label class="fw-normal w140 text-right" >垫佣甲方简称<i>*</i>：</label>
           <div style="width:127px;" id="selectMattressNail2">
            <select class="form-control selectpicker3 " title="" id="selectMattressNail" name="mattressNailId" notnull="true" style="width:150px;" data-live-search="true" ></select>
             <input type="hidden"  name="mattressNailText" id="mattressNailText" >
         </div>
            <span class="fc-warning"></span>
        </div>
    </li>
</ul>

<div id="baseInfo">
<ul class="list-inline form-inline">
    <li style="width: 600px;">
        <div class="form-group">
            <label class="fw-normal w140 text-right">合作方对接人<i>*</i>：</label>
            <input type="text" onchange="autoSetDevMer()" class="form-control w300" name="partnerContactNm" id="partnerContactNm"
                   placeholder="" notnull="true" maxlength="50" dataType="normal" value="${estate.partnerContactNm}">
            <span class="fc-warning"></span>
        </div>
    </li>
     <li>
        <div class="form-group" style="width:auto;">
            <label class="fw-normal w140 text-right">对接人电话<i>*</i>：</label>
            <input type="text" onchange="autoSetDevMer()" class="form-control w200" name="partnerContactTel" id="partnerContactTel"
                   placeholder="请输入11位电话号码" notnull="true" maxlength="11" dataType="phone" value="${estate.partnerContactTel}">
            <span class="fc-warning"></span>
        </div>
    </li>
</ul>

</div>
<ul class="list-inline form-inline">
	<li style="width: 600px;">
		<div class="form-group">
			<label class="fw-normal w140 text-right">合作期自：</label>
			<input type="text" class="form-control calendar-icon" id="cooperationDtStart" name="cooperationDtStart" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'cooperationDtEnd\')}'})" readonly="readonly" class="ipttext Wdate"  value="${sdk:ymd2(estate.cooperationDtStart)}"/>
		</div>
	</li>
	<li>
		<div class="form-group" style="width:auto;">
			<label class="fw-normal w140 text-right">合作期至：</label>
			<input type="text" class="form-control calendar-icon" id="cooperationDtEnd" name="cooperationDtEnd" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'cooperationDtStart\')}'})" readonly="readonly" class="ipttext Wdate"  value="${sdk:ymd2(estate.cooperationDtEnd)}"/>
		</div>
	</li>
</ul>
<%--<ul class="list-inline form-inline">--%>
	<%--<li>--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w100 text-right" style="vertical-align: top;">项目简介：</label>--%>
			<%--<textarea id="projectDescription" name="projectDescription" cols="30" placeholder=""  maxlength="500" rows="10"--%>
				<%--style="resize: none;width:725px;">${estate.projectDescription}</textarea>--%>
			<%--<span class="fc-warning"></span>--%>
		<%--</div>--%>
	<%--</li>--%>
<%--</ul>--%>
<%--<p>--%>
	<%--<strong>楼盘详情</strong>--%>
<%--</p>--%>
<ul class="list-inline form-inline">
    <li style="width:600px;">
       <div class="form-group">
            <label class="fw-normal w140 text-right">开发商是否大客户<i>*</i>：</label>
            <c:forEach items="${bigCustomerStatus}" var="list">
                <input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="bigCustomerFlag" onchange="bigCustomerChange('1');" <c:if test="${list.dicCode eq estate.bigCustomerFlag}">checked</c:if>>
                <label class="fon-normal small">${list.dicValue}</label>
            </c:forEach>
            <span class="fc-warning"></span>
        </div>
    </li>
</ul>
<ul class="list-inline form-inline" id="developerNameYes"  style="overflow:initial;">
	<li style="width:600px;">
       <div class="form-group">
            <label class="fw-normal w140 text-right" >开发商简称<i>*</i>：</label>
            <input type="text" class="form-control w300" name="devCompany" id="devCompany" value="${estate.devCompany}"
                   placeholder="" notnull="true" maxlength="64" >
            <div style="display:none;width:127px;" id="selectDevCompany2">
             <select class="form-control selectpicker2 " title="" id="selectDevCompany" name="bigCustomerId" notnull="true" style="width:150px;" data-live-search="true" ></select>
             <input type="hidden"  name="devCompanyText" id="devCompanyText" >
         </div>
            <span class="fc-warning"></span>
        </div>
    </li>
    <li >
        <div class="form-group" id="developerNameShow" style="display:none;">
            <label class="fw-normal w140 text-right">开发商全称<i>*</i>：</label>
            <input type="text" class="form-control w300" name="developerNameBigYes" id="developerNameBigYes"
                   placeholder="" notnull="true" maxlength="25" dataType="normal" value="${estate.developerName}">
            <span class="fc-warning"></span>
        </div>
    </li>
</ul>
<ul class="list-inline form-inline" id="developerNameNo" style="overflow:initial;display:none">
    <li >
        <div class="form-group"  >
            <label class="fw-normal w140 text-right">开发商全称<i>*</i>：</label>
            <input type="text" class="form-control w300" name="developerNameBigNo" id="developerNameBigNo"
                   placeholder="" notnull="true" maxlength="25" dataType="normal" value="${estate.developerName}">
            <span class="fc-warning"></span>
        </div>
    </li>
</ul>
<ul class="list-inline form-inline">
	<li style="width: 600px;">
		<div class="form-group">
		    <label class="fw-normal w140 text-right">开发商对接人：</label>
		    <input type="text" class="form-control w300" name="devCompanyBroker" id="devCompanyBroker"
		           placeholder=""  maxlength="64" dataType="normal" value="${estate.devCompanyBroker}">
		               <span class="fc-warning"></span>
	    </div>
	</li>
	<li>
	    <div class="form-group">
	        <label class="fw-normal w140 text-right">开发商对接人电话：</label>
	        <input type="text" class="form-control w200" name="devCompanyBrokerTel" id="devCompanyBrokerTel"
	               placeholder=""  maxlength="64" dataType="phone" value="${estate.devCompanyBrokerTel}">
	            <span class="fc-warning"></span>
        </div>
	</li>
</ul>
<%--<ul class="list-inline form-inline">--%>
	<%--<li>--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w100 text-right">案场地址<i>*</i>：</label> <input type="text"--%>
				<%--class="form-control w300" name="fieldAddress" id="fieldAddress" placeholder="" notnull="true"--%>
				<%--maxlength="25" dataType="normal" value="${estate.fieldAddress}"> <span class="fc-warning"></span>--%>
		<%--</div>--%>
	<%--</li>--%>
<%--</ul>--%>
<ul class="list-inline form-inline" id="directSignListShow" style="display:none">
    <li>
        <div class="form-group">
            <label class="fw-normal w140 text-right">是否直签<i>*</i>：</label>
            <c:forEach items="${directSignList}" var="list">
               <input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="directSignFlag" <c:if test="${list.dicCode eq estate.directSignFlag}">checked</c:if>>
               <label class="fon-normal small">${list.dicValue}</label>
            </c:forEach>
            <span class="fc-warning"></span>
        </div>
    </li>
</ul>
<%--<ul class="list-inline form-inline">--%>
	<%--<li>--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w100 text-right">预售许可：</label> <input type="radio" value="1"--%>
				<%--id="preSalePermitKbn1" name="preSalePermitKbn" <c:if test="${1 eq estate.preSalePermitKbn}">checked</c:if>><label--%>
				<%--class="fon-normal small">有</label> <input type="radio" value="0" id="preSalePermitKbn0" --%>
				<%--name="preSalePermitKbn" <c:if test="${0 eq estate.preSalePermitKbn}">checked</c:if>><label class="fon-normal small">无</label> <span--%>
				<%--class="fc-warning"></span>--%>
		<%--</div>--%>
	<%--</li>--%>
<%--</ul>--%>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w140 text-right">物业类型：</label>
			<c:set var="theStr" value="${estate.mgtKbn}" />
			<c:forEach items="${mgtKbnList}" var="list">
				<input type="checkbox" value="${list.dicCode}" id="${list.dicCode}" name="mgtKbnCB"
					   onchange="mgtKbnChange()" <c:if test="${fn:contains(theStr, list.dicCode)}">checked="checked"</c:if>>
				<label class="fon-normal small">${list.dicValue}</label>
			</c:forEach>
			<span class="fc-warning"></span>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w140 text-right">产权年限：</label>
			<c:set var="theStr" value="${estate.ownYearKbn}" />
			<c:forEach items="${ownYearKbnList}" var="list">
				<input type="checkbox" value="${list.dicCode}" id="${list.dicCode}" name="ownYearKbnCB"
					   onchange="ownYearKbnChange()" <c:if test="${fn:contains(theStr, list.dicCode)}">checked="checked"</c:if>>
				<label class="fon-normal small">${list.dicValue}</label>
			</c:forEach>
			<span class="fc-warning"></span>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li style="width: 600px;">
		<div class="form-group">
			<label class="fw-normal w140 text-right">我方负责人<i>*</i>：</label>
			<%-- <select class="form-control" title="" id="empId" name="sceneEmpId" onchange="depChange()">
				<c:if test="${!empty sceneUserList}">
					<c:forEach items="${sceneUserList}" var="list">
						<c:if test="${list.userId eq estate.sceneEmpId}"><option value="${list.userId}" selected="selected">${list.userName}</option></c:if>
						<c:if test="${list.userId ne estate.sceneEmpId}"><option value="${list.userId}">${list.userName}</option></c:if>
					</c:forEach>
				</c:if>
			</select> --%>
			<input type="hidden" id="empId" name="sceneEmpId" value="${estate.sceneEmpId}"/>
			<input type="text" class="form-control" id="empId1" name="sceneEmpId1" placeholder=""  value="${estate.userName}"
				   readonly="readonly" style="background-color: #F9F9F9">
			<button type="button" class="btn btn-primary" id="J_export1"
					onclick="javascript:relateProjectLeader();"  style="margin-left:15px;">选择
			</button>
			<span class="fc-warning"></span>
		</div>
	</li>
	<li style="width:400px;">
		<div class="form-group" style="width:auto;">
			<label class="fw-normal w140 text-right">我方负责人电话<i>*</i>：</label>
			<input type="text" class="form-control w200" name="empTel" id="empTel"  notnull="true" maxlength="11" dataType="phone" value="${estate.empTel}">
			<span class="fc-warning"></span>
		</div>
	</li>
</ul>

<ul class="list-inline form-inline">
	<li style="width:600px;">
		<div class="form-group">
			<label class="fw-normal w140 text-right">预计当年大定金额<i>*</i>：</label>
			<input type="text" class="form-control w125" name="subscribedThisYear" id="subscribedThisYear"
				   placeholder="" notnull="true" maxlength="20" dataType="needMoney" onkeyup="" value="${estate.subscribedThisYear}"> 万元
			<span class="fc-warning"></span>
		</div>
	</li>

	<li>
		<div class="form-group">
			<label class="fw-normal w140 text-right">预计明年大定金额<i>*</i>：</label>
			<input type="text" class="form-control w125" name="subscribedNextYear" id="subscribedNextYear"
				   placeholder="" notnull="true" maxlength="20" dataType="needMoney" onkeyup="" value="${estate.subscribedNextYear}">万元
			<span class="fc-warning"></span>
		</div>
	</li>
</ul>
<%--<ul class="list-inline form-inline">--%>
	<%--<li>--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w100 text-right">装修情况：</label>--%>
			<%--<c:set var="theStr" value="${estate.decorationKbn}" />--%>
			<%--<c:forEach items="${decorationKbnList}" var="list">--%>
				<%--<input type="checkbox" value="${list.dicCode}" id="${list.dicCode}" name="decorationKbnCB"--%>
					   <%--onchange="decorationKbnChange()" <c:if test="${fn:contains(theStr, list.dicCode)}">checked="checked"</c:if>>--%>
				<%--<label class="fon-normal small">${list.dicValue}</label>--%>
			<%--</c:forEach>--%>
			<%--<span class="fc-warning"></span>--%>
		<%--</div>--%>
	<%--</li>--%>
<%--</ul>--%>
<%--<ul class="list-inline form-inline">--%>
	<%--<li>--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w100 text-right">建筑类型：</label>--%>
			<%--<c:set var="theStr" value="${estate.typeKbn}" />--%>
			<%--<c:forEach items="${typeKbnList}" var="list">--%>
				<%--<input type="checkbox" value="${list.dicCode}" id="${list.dicCode}" name="typeKbnCB"--%>
					<%--onchange="typeKbnChange()" <c:if test="${fn:contains(theStr, list.dicCode)}">checked="checked"</c:if>>--%>
				<%--<label class="fon-normal small">${list.dicValue}</label>--%>
			<%--</c:forEach>--%>
		<%--</div>--%>
	<%--</li>--%>
<%--</ul>--%>
<%--<ul class="list-inline form-inline">--%>
	<%--<li>--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w100 text-right">规划户数：</label> <input type="text" class="form-control w80"  maxlength="9"--%>
				<%--name="houseCnt" placeholder="" dataType="posNumWithZero" value="${estate.houseCnt}">户--%>
		<%--</div>--%>
	<%--</li>--%>
	<%--<li>--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w100 text-right">车位情况：</label> <input type="text" class="form-control w80"--%>
				<%--name="parkCnt" placeholder="" dataType="posNumWithZero"  maxlength="6" value="${estate.parkCnt}">位 <span class="fc-warning"></span>--%>
		<%--</div>--%>
	<%--</li>--%>
	<%--<li>--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w100 text-right">停车费：</label> <input type="text" class="form-control w125"--%>
				<%--name="parkFee" placeholder="" maxlength="6" dataType="needMoney" value="${estate.parkFee}">元/月 <span class="fc-warning"></span>--%>
		<%--</div>--%>
	<%--</li>--%>
<%--</ul>--%>
<%--<c:forEach items="${estate.tiHus}" var="list" varStatus="status">--%>
	<%--<ul class="stair list-inline form-inline">--%>
		<%--<li>--%>
			<%--<div class="form-group">--%>
				<%--<label class="fw-normal w100 text-right"><c:if test="${status.first eq true}">梯户：</c:if></label><input type="text" class="form-control w50" maxlength="1" name="staircase[${status.index}]" placeholder="" dataType="posNumWithZero" value="${list.Ti}">梯<input type="text"--%>
					<%--class="form-control w50" name="household[${status.index}]" placeholder="" dataType="posNumWithZero"  maxlength="3" value="${list.Hu}">户<c:if test="${status.first eq true}"><span class="btn btn-link btn-add-houst-type" onclick="addStair(this);">新增梯户数</span></c:if><c:if test="${status.first eq false}"><span class="btn btn-link btn-add-houst-type" onclick="delStair(this);">删除</span></c:if>--%>
			<%--</div>--%>
		<%--</li>--%>
	<%--</ul>--%>
<%--</c:forEach>--%>
<%--<c:if test="${fn:length(estate.tiHus) le 0}">--%>
	<%--<ul class="stair list-inline form-inline">--%>
		<%--<li>--%>
			<%--<div class="form-group">--%>
				<%--<label class="fw-normal w100 text-right">梯户：</label><input type="text" class="form-control w50" name="staircase[0]" placeholder="" dataType="posNumWithZero" maxlength="1" value="">梯<input type="text"--%>
					<%--class="form-control w50" name="household[0]" placeholder="" dataType="posNumWithZero" value="">户<span class="btn btn-link btn-add-houst-type" maxlength="1" onclick="addStair(this);">新增梯户数</span>--%>
			<%--</div>--%>
		<%--</li>--%>
	<%--</ul>--%>
<%--</c:if>--%>
<%--<p>--%>
	<%--<strong>物业信息</strong>--%>
<%--</p>--%>
<%--<ul class="list-inline form-inline">--%>
	<%--<li>--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w100 text-right">物业公司：</label> <input type="text"--%>
				<%--class="form-control w300" name="mgtCompany" id="mgtCompany" placeholder=""  maxlength="25"--%>
				<%--dataType="normal" value="${estate.mgtCompany}"> <span class="fc-warning"></span>--%>
		<%--</div>--%>
	<%--</li>--%>
<%--</ul>--%>
<%--<ul class="list-inline form-inline">--%>
	<%--<li>--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w100 text-right">容积率：</label> <input type="text"--%>
				<%--class="form-control " name="rateFAR" id="rateFAR" placeholder=""  maxlength="5"--%>
				<%--dataType="onedecimal" value="${estate.rateFAR}" style="width:70px;"> <span class="fc-warning"></span>--%>
		<%--</div>--%>
	<%--</li>--%>
	<%--<li>--%>
		<%--<div class="form-group" style="width:auto;margin-left: 45px;">--%>
			<%--<label class="fw-normal w100 text-right">绿化率：</label> <input type="text"--%>
				<%--class="form-control w80 " name="rateGreen" id="rateGreen" placeholder=""  maxlength="5"--%>
				<%--dataType="flothree" value="${estate.rateGreen}">% <span class="fc-warning"></span>--%>
		<%--</div>--%>
	<%--</li>--%>
	<%--<li>--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w100 text-right">物业费用：</label> <input type="text"--%>
				<%--class="form-control w125" name="mgtPrice" id="mgtPrice" placeholder="请输入数字"  maxlength="10"--%>
				<%--dataType="needMoney" value="${estate.mgtPrice}">元/m²/月 <span class="fc-warning"></span>--%>
		<%--</div>--%>
	<%--</li>--%>
<%--</ul>--%>
<%--<ul class="list-inline form-inline">--%>
	<%--<li>--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w100 text-right">供暖方式：</label>--%>
			<%--<c:forEach items="${heatKbnList}" var="list">--%>
				<%--<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="heatKbn" <c:if test="${list.dicCode eq estate.heatKbn}">checked</c:if>>--%>
				<%--<label class="fon-normal small">${list.dicValue}</label>--%>
			<%--</c:forEach>--%>
		<%--</div>--%>
	<%--</li>--%>
<%--</ul>--%>
<%--<ul class="list-inline form-inline">--%>
	<%--<li>--%>
		<%--<div class="form-group">--%>
			<%--<label class="fw-normal w100 text-right">水电燃气：</label>--%>
			<%--<c:forEach items="${hydropowerGasKbnList}" var="list">--%>
				<%--<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="hydropowerGasKbn" <c:if test="${list.dicCode eq estate.hydropowerGasKbn}">checked</c:if>>--%>
				<%--<label class="fon-normal small">${list.dicValue}</label>--%>
			<%--</c:forEach>--%>
		<%--</div>--%>
	<%--</li>--%>
<%--</ul>--%>
<div class="text-center" style="margin-top: 20px">
	<span onclick="editSubmit()" class="btn btn-primary" style="width: 100px;margin-left: 100px!important;">提交</span>
	<a href="${ctx}/estate?searchParam=1" class="btn btn-default" style="width: 100px;margin-left: 100px!important;">取消</a>
</div>

<script>
$(function(){
	bigCustomerChange('2');
	$(".selectpicker").selectpicker({  
        noneSelectedText : '请选择城市'  
    });  

    $(window).on('load', function() {  
        $('.selectpicker').selectpicker('val', '');  
        $('.selectpicker').selectpicker('refresh');  
    });
	
	var url = BASE_PATH + "/cityCascade/queryCityListByIsService";
	var params = {};
	ajaxGet(url, params, function(data) {
		var result = "<option value=''>请选择城市</option>";
		$.each(data.returnValue, function(n, value) {
			result += "<option value='" + value.cityNo + "'>"
					+ value.cityName + "</option>";
		});
		$("#realityCityNo").html('');
		$("#realityCityNo").append(result);
		$("#realityCityNo").val('${estate.realityCityNo}');//选中
		$('.selectpicker').selectpicker({
			selectedText:'${estate.realityCityNo}'
		});  
        $('.selectpicker').selectpicker('refresh');  
	}, function() {
	});
	
	var url = BASE_PATH + "/linkages/city/" + '${estate.realityCityNo}';
	var params = {};

	ajaxGet(url, params, function(data) {
		var result = "<option value=''>请选择区域</option>";
		$.each(data.returnValue, function(n, value) {
			result += "<option value='" + value.districtNo + "'>"
					+ value.districtName + "</option>";
		});
		$("#districtNo").html('');
		$("#districtNo").append(result);
		$("#districtNo").val('${estate.districtId}');//选中
	}, function() {
	});
	
	$("#realityCityNo").change(
			function() {
				var realityCityNo = $("#realityCityNo").val();
				var realityCityNm = $("#realityCityNo").find("option:selected").text();
				if("请选择城市"==realityCityNm){
					$("#districtNo").html('');
					return false;
				}
				
				var url = BASE_PATH + "/linkages/city/" + realityCityNo;
				var params = {};

				ajaxGet(url, params, function(data) {
					var result = "<option value=''>请选择区域</option>";
					$.each(data.returnValue, function(n, value) {
						result += "<option value='" + value.districtNo + "'>"
								+ value.districtName + "</option>";
					});
					$("#districtNo").html('');
					$("#districtNo").append(result);
				}, function() {
				});
	});
	var bigCustomerFlag = $("#bigCustomerFlagStr").val();

	$(".selectpicker2").selectpicker({  
        noneSelectedText : '请选择'  
    });  
    $(window).on('load', function() {  
        $('.selectpicker2').selectpicker('val', '');  
        $('.selectpicker2').selectpicker('refresh');  
    });
    var url = BASE_PATH + "/estate/getBigCustomerList";
	var params = {};
	ajaxGet(url, params, function(data) {
		var result = "<option value=''>请选择</option>";
		$.each(data.returnValue, function(n, value) {
			result += "<option value='" + value.bigCustomerId + "'>"
					+ value.name + "</option>";
		});
		$("#selectDevCompany").html('');
		$("#selectDevCompany").append(result);
		$("#selectDevCompany").val('${estate.bigCustomerId}');//选中
		  
        $('.selectpicker2').selectpicker('refresh'); 
        var selectDevCompanyNm = $("#selectDevCompany").find("option:selected").text();
		$("#devCompanyText").val(selectDevCompanyNm);
	}, function() {
	});
	$("#selectDevCompany").change(function() {
		var selectDevCompanyNm = $("#selectDevCompany").find("option:selected").text();
		$("#devCompanyText").val(selectDevCompanyNm);
	});
	var custodyFlg = $("input[name='custodyFlg']:checked").val();
// 	$("#mattressNailText").val($("#mattressNailTextHidden").val());
	if(custodyFlg==1){
		$("#mattressNail").show();
		$("#mattressNail").css("display","inline-block");
		$("#selectMattressNail2").css("display","inline-block");
	}
	if(custodyFlg==0){
		$("#mattressNail").hide();
		$("#mattressNail").css("display","none");
// 		$("#selectMattressNail2").css("display","inline-block");
	}
	$(".selectpicker3").selectpicker({  
        noneSelectedText : '请选择'  
    });  
    $(window).on('load', function() {  
        $('.selectpicker3').selectpicker('val', '');  
        $('.selectpicker3').selectpicker('refresh');  
    });
    var url = BASE_PATH + "/estate/getMattressNail";
	var params = {};
	ajaxGet(url, params, function(data) {
		var result = "<option value=''>请选择</option>";
		$.each(data.returnValue, function(n, value) {
			result += "<option value='" + value.mattressNailId + "'>"
					+ value.name + "</option>";
		});
		$("#selectMattressNail").html('');
		$("#selectMattressNail").append(result);
		$("#selectMattressNail").val('${estate.mattressNailId}');//选中
		  
        $('.selectpicker3').selectpicker('refresh'); 
        var selectDevCompanyNm = $("#selectMattressNail").find("option:selected").text();
		$("#mattressNailText").val(selectDevCompanyNm);
	}, function() {
	});
	$("#selectMattressNail").change(function() {
		var selectDevCompanyNm = $("#selectMattressNail").find("option:selected").text();
		$("#mattressNailText").val(selectDevCompanyNm);
	});
});
</script>