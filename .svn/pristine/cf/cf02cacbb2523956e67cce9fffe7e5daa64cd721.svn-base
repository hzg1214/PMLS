<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/estateAdd.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/relateProjectLeader.js?_v=${vs}"></script>
    <%--<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/estateCommon.js?_v=${vs}"></script>--%>

    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/report/expand/bootstrap-select.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/cssreport/bootstrap-select.css?_v=${vs}">
</head>
<body style="padding-bottom:34px;">
<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>
<form id="estateAddForm">
    <!-- 记录文件数量，创建的时候为0 -->
    <input type="hidden" id="picNum" value="0">
    <input type="hidden" name="auditStatus" id="auditStatus" value="12904">
    <input type="hidden" name="projectStatus" id="projectStatus" value="20305">
    <input type="hidden" name="houseCoverImg" id="houseCoverImg">
    <input type="hidden" name="estateCoverImg" id="estateCoverImg">
    <input type="hidden" name="mgtKbn" id="mgtKbn"><!-- 物业类型 -->
    <input type="hidden" name="ownYearKbn" id="ownYearKbn"><!-- 产权年限 -->
    <input type="hidden" name="decorationKbn" id="decorationKbn"><!-- 装修情况 -->
    <input type="hidden" name="typeKbn" id="typeKbn"><!-- 建筑类型 -->
    <input type="hidden" name="opEstateId" id="opEstateId">
    <input type="hidden" name="opEstateNm" id="opEstateNm">
    <input type="hidden" name="achievementCityNo" id="achievementCityNo">
    <input type="hidden" name="houseTypeEdit" id="houseTypeEdit">
    <input type="hidden"  name="lnkAccountProject" id="lnkAccountProject" >
        <input type="hidden"  name="lnkAccountProjectCode" id="lnkAccountProjectCode" >
    <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom" style="padding-top: 0;padding-bottom: 0"><strong>新建项目</strong></h4>
                <p><strong>基本信息</strong></p>
                <ul class="list-inline form-inline">
                    <li style="width:600px;">
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">楼盘位置<i>*</i>：</label>
                                <c:if test="${estatePosition eq null or estatePosition eq 0}">
                                    <input type="radio" value="0" onchange="EstateAdd.fnChangePosition(this)" name="estatePosition" checked/><label class="fon-normal small">国内</label>
                                    <input type="radio" value="1" onchange="EstateAdd.fnChangePosition(this)" name="estatePosition"/><label class="fon-normal small">海外</label>
                                </c:if>
                                <c:if test="${estatePosition eq 1}">
                                    <input type="radio" value="0" onchange="EstateAdd.fnChangePosition(this)" name="estatePosition"/><label class="fon-normal small">国内</label>
                                    <input type="radio" value="1" onchange="EstateAdd.fnChangePosition(this)" name="estatePosition" checked/><label class="fon-normal small">海外</label>
                                </c:if>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li style="width:600px;">
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">楼盘名<i>*</i>：</label>
                            <input type="text" class="form-control w300" name="estateNm" id="estateNm" placeholder="" notnull="true" maxlength="25" <c:if test="${addEstateManual eq 'false'}">readonly="readonly"</c:if>/>
<c:if test="${addEstateManual eq 'false'}"><button type="button" class="btn btn-primary" id="estateNmBtn" onclick="javascript:EstateAdd.selectFromOp();" style="vertical-align: top;">选择</button></c:if>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">备案名：</label>
                            <input type="text" class="form-control w300" name="recordName" id="recordName"
                                   placeholder="" maxlength="25" dataType="normal">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li style="width:600px;">
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">推广名：</label>
                            <input type="text" class="form-control w300" name="promotionName" id="promotionName"
                                   placeholder="" maxlength="25" dataType="normal">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">签约名：</label>
                            <input type="text" class="form-control w300" name="signName" id="signName" placeholder=""
                                   maxlength="25" dataType="normal" style="width: 163px;">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li style="width:600px;">
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">项目归属城市<i>*</i>：</label>
                            <select class="form-control" title="" id="cityNo" name="cityNo" style="width:125px;"  readonly>
                                <option value="${userInfo.cityNo}" selected>${userInfo.cityName}</option>
                            </select>
                            <input type="hidden" name="cityName" value="${userInfo.cityName}">
                            <span class="fc-warning"></span>
                        </div>
                      </li>
                      <li>  
                         <div class="form-group">
                            <label class="fw-normal w140 text-right">业绩归属项目部<i>*</i>：</label>
                            <select class="form-control" title="" id="projectDepartmentId" name="projectDepartmentId" notnull="true" readonly>
                                <option value="" selected>请选择</option>
                                <c:forEach items="${rebacklist}" var="list">
                                     <option value="${list.projectDepartmentId}">${list.projectDepartment}</option>
                                </c:forEach>
                            </select>
                            <span class="fc-warning"></span>
                        </div>
                       </li>
                </ul>
                <c:if test="${estatePosition eq null or estatePosition eq 0}">
                    <input type="hidden" name="countryNo" value="CTY_000001">
                    <div class="form-group">
                        <label class="fw-normal w140 text-right">楼盘地址<i>*</i>：</label>
                        <div style="display:inline-block;width:127px;">
                            <select class="form-control selectpicker" title="" id="realityCityNo" name="realityCityNo" notnull="true" style="width:150px;" data-live-search="true"></select>
                        </div>
                        <div style="display:inline-block; vertical-align: middle;">
                            <select class="form-control selectpicker1" title="" id="districtNo" name="districtId" notnull="true" style="width:150px;"></select>
                        </div>
                        <input type="text" class="form-control" name="address" id="address"
                               placeholder="具体地址信息" notnull="true" value="" maxlength="50" style="display:inline-block; vertical-align: middle;width: 450px;">
                        <span class="fc-warning"></span>
                    </div>
                </c:if>
                <c:if test="${estatePosition eq 1}">
                    <div class="form-group">
                        <label class="fw-normal w140 text-right">楼盘地址<i>*</i>：</label>
                        <div style="display:inline-block;width:160px;vertical-align: middle;">
                            <select class="form-control" title="" id="countryNo" name="countryNo" notnull="true" style="width:150px;" data-live-search="true">
                                <option value="">请选择国家</option>
                                <c:forEach var="country" items="${countryList}">
                                    <c:if test="${country.countryName ne '中国'}">
                                        <option value="${country.countryNo}" countryNm="${country.countryName}">${country.countryName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <input type="text" class="form-control" name="address" id="address"
                               placeholder="具体地址信息" notnull="true" value="" maxlength="50" style="display:inline-block; vertical-align: middle;width: 450px;">
                        <span class="fc-warning"></span>
                    </div>
                </c:if>

                <ul class="list-inline form-inline">
                    <li style="width:600px;">
                       <div class="form-group">
                            <%--<label class="fw-normal w140 text-right">合作模式<i>*</i>：</label>--%>
                            <%--<c:forEach items="${cooperationTypeList}" var="list">--%>
                                <%--<input type="radio" value="${list.dicCode}" id="${list.dicCode}"--%>
                                       <%--name="cooperationMode"><label--%>
                                                                  <%--class="fon-normal small">${list.dicValue}</label>--%>
                            <%--</c:forEach>--%>
                           <input type="hidden" name="cooperationMode" value="-1"/>
                           <label class="fw-normal w140 text-right">业务模式<i>*</i>：</label>
                           <select class="form-control" title="" id="businessModel" name="businessModel" notnull="true" style="width:280px;" data-live-search="true">
                               <option value="" selected>请选择</option>
                               <c:forEach items="${businessModelTypeList}" var="list">
                               <option value=${list.dicCode} >${list.dicValue}</option>
                               </c:forEach>
                           </select>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">销售状态<i>*</i>：</label>
                            <c:forEach items="${salesStatusList}" var="list">
                                <c:if test="${list.dicCode ne 1443}">
                                <input type="radio" value="${list.dicCode}" id="${list.dicCode}" onchange="openTimeChange(this);"
                                       name="salesStatus" <c:if test="${list.dicCode eq 1441}">checked="checked"</c:if> >
                                       <label class="fon-normal small">${list.dicValue}</label>
                                </c:if>
                            </c:forEach>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                
                <ul class="list-inline form-inline">
                    <li style="width:600px;">
                       <div class="form-group">
                            <label class="fw-normal w140 text-right">是否独家<i>*</i>：</label>
                            <c:forEach items="${particularList}" var="list">
                                <input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="particularFlag">
                                <label class="fon-normal small">${list.dicValue}</label>
                            </c:forEach>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">总价段<i>*</i>：</label>
                            <input type="text" class="form-control w125" name="salePriceUnitMin" id="salePriceUnitMin"
                                   placeholder="" notnull="true" maxlength="15" dataType="needMoney">-
                            <input type="text" class="form-control w125" name="salePriceUnitMax" id="salePriceUnitMax"
                                   placeholder="" notnull="true" maxlength="15" dataType="needMoney">万元/套
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">标签：</label>--%>
                            <%--<input type="text" class="form-control" name="mark[0]" id="mark" placeholder=""--%>
                                   <%--maxlength="4" style="width: 140px;">--%>
                            <%--<span class="btn btn-link btn-add-input-tag" onclick="addMark(this)">新增标签</span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li style="width:600px;">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" id="opentimeShow">--%>
                                <%--开盘日期：--%>
                            <%--</label>--%>
                            <%--<input type="text" class="form-control calendar-icon" name="openTime" id="openTime"--%>
                                   <%--onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'houseTransferTime\')}'})"--%>
                                   <%--readonly="readonly" class="ipttext Wdate" value=""/>--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">预计交房日期：</label>--%>
                            <%--<input type="text" class="form-control calendar-icon" name="houseTransferTime" id="houseTransferTime"--%>
                                   <%--onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'openTime\')}'})"--%>
                                   <%--readonly="readonly" class="ipttext Wdate" value=""/>--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">合作方类型<i>*</i>：</label>
                            <c:forEach items="${partnerList}" var="list">
                                <input onchange="autoSetDevMer()" type="radio" value="${list.dicCode}" id="${list.dicCode}" id="partner"
                                       name="partner"><label
                                                             class="fon-normal small">${list.dicValue}</label>
                            </c:forEach>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li style="width:600px">
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">合作方名称<i>*</i>：</label>
                            <input onchange="autoSetDevMer()" type="text" class="form-control w300" name="partnerNm" id="partnerNm" placeholder=""
                                   notnull="true" maxlength="25">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">是否可垫佣甲方<i>*</i>：</label>
                            <input type="radio" value="1" name="custodyFlg" onchange="custodyChange('1');"><label class="fon-normal small">是</label>
                            <input type="radio" value="0" name="custodyFlg" onchange="custodyChange('1');"><label class="fon-normal small">否</label>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline" id="mattressNail"  style="display:none;overflow:initial;">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right" >垫佣甲方简称<i>*</i>：</label>
                            <div style="display:inline-block;width:127px;" id="selectMattressNail2">
	                            <select class="form-control selectpicker3 " title="" id="selectMattressNail" name="mattressNailId" notnull="true" 
	                            	style="width:150px;" data-live-search="true" ></select>
	                            <input type="hidden"  name="mattressNailText" id="mattressNailText" />
	                        </div>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <div id="baseInfo">
                <ul class="list-inline form-inline">
                    <li style="width:600px;">
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">合作方对接人<i>*</i>：</label>
                            <input onchange="autoSetDevMer()" type="text" class="form-control w300" name="partnerContactNm" id="partnerContactNm"
                                   placeholder="" notnull="true" maxlength="50" dataType="normal">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">对接人电话<i>*</i>：</label>
                            <input onchange="autoSetDevMer()" type="text" class="form-control w200" name="partnerContactTel" id="partnerContactTel"
                                   placeholder="请输入11位电话号码" notnull="true" maxlength="11" dataType="phone">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>

                </div>
                <ul class="list-inline form-inline">
                    <li style="width:600px;">
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">合作期自：</label>
                            <input type="text" class="form-control calendar-icon" id="cooperationDtStart" name="cooperationDtStart"
                                   onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd', maxDate:'#F{$dp.$D(\'cooperationDtEnd\')}'})"
                                   readonly="readonly" class="ipttext Wdate" value=""/>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">合作期至：</label>
                            <input type="text" class="form-control calendar-icon" id="cooperationDtEnd" name="cooperationDtEnd"
                                   onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd', minDate:'#F{$dp.$D(\'cooperationDtStart\')}'})"
                                   readonly="readonly" class="ipttext Wdate" value="" />
                        </div>
                    </li>
                </ul>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" style="vertical-align: top;">项目简介：</label>--%>
                            <%--<textarea id="projectDescription" name=projectDescription cols="30" placeholder="" maxlength="500"--%>
                                      <%--rows="10" style="resize: none;width:728px;"></textarea>--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<p style="margin-top:10px;"><strong>楼盘详情</strong></p>--%>
                <!-- <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">楼盘信息</label>
                        </div>
                    </li>
                </ul> -->
                <ul class="list-inline form-inline">
                    <li style="width:600px;">
                       <div class="form-group">
                            <label class="fw-normal w140 text-right">开发商是否大客户<i>*</i>：</label>
                            <c:forEach items="${bigCustomerStatus}" var="list">
                                <input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="bigCustomerFlag" onchange="bigCustomerChange('1');">
                                <label class="fon-normal small">${list.dicValue}</label>
                            </c:forEach>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline" id="developerNameYes" style="overflow:initial;">
                    <li style="width:600px;">
                        <div class="form-group">
                            <label class="fw-normal w140 text-right" >开发商简称<i>*</i>：</label>
                            <input type="text" class="form-control w300" name="devCompany" id="devCompany"
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
                                   placeholder="" notnull="true" maxlength="25" dataType="normal">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline" id="developerNameNo" style="overflow:initial;display:none">
                    <li >
                        <div class="form-group"  >
                            <label class="fw-normal w140 text-right">开发商全称<i>*</i>：</label>
                            <input type="text" class="form-control w300" name="developerNameBigNo" id="developerNameBigNo"
                                   placeholder="" notnull="true" maxlength="25" dataType="normal">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li style="width:600px">
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">开发商对接人：</label>
                            <input type="text" class="form-control w300" name="devCompanyBroker" id="devCompanyBroker"
                                   placeholder="" maxlength="64" dataType="normal">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">开发商对接人电话：</label>
                            <input type="text" class="form-control w200" name="devCompanyBrokerTel" id="devCompanyBrokerTel"
                                   placeholder="请输入11位电话号码"  maxlength="64" dataType="phone">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">案场地址<i>*</i>：</label>--%>
                            <%--<input type="text" class="form-control w300" name="fieldAddress" id="fieldAddress"--%>
                                   <%--placeholder="" notnull="true" maxlength="25" dataType="normal">--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <ul class="list-inline form-inline" id="directSignListShow" style="display:none">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">是否直签<i>*</i>：</label>
                            <c:forEach items="${directSignList}" var="list">
                               <input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="directSignFlag">
                               <label class="fon-normal small">${list.dicValue}</label>
                            </c:forEach>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">预售许可：</label>--%>
                            <%--<input type="radio" value="1" id="preSalePermitKbn1" name="preSalePermitKbn"><label--%>
                                <%--class="fon-normal small">有</label>--%>
                            <%--<input type="radio" value="0" id="preSalePermitKbn0" name="preSalePermitKbn"><label--%>
                                <%--class="fon-normal small">无</label>--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">物业类型：</label>
                            <c:forEach items="${mgtKbnList}" var="list">
                                <input type="checkbox" value="${list.dicCode}" id="${list.dicCode}" name="mgtKbnCB"
                                       onchange="mgtKbnChange()">
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
                            <c:forEach items="${ownYearKbnList}" var="list">
                                <input type="checkbox" value="${list.dicCode}" id="${list.dicCode}" name="ownYearKbnCB"
                                       onchange="ownYearKbnChange()">
                                <label class="fon-normal small">${list.dicValue}</label>
                            </c:forEach>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>

                <ul class="list-inline form-inline">
                    <li style="width:600px;">
                        <div class="form-group" >
                            <label class="fw-normal w140 text-right" >我方负责人<i>*</i>：</label>
                            <%--  <select class="form-control" title="" id=empId name="sceneEmpId">
                                 <option selected="selected" value="">请选择</option>
                                 <c:if test="${!empty sceneUserList}">
                                     <c:forEach items="${sceneUserList}" var="list">
                                         <option value="${list.userId}">${list.userName}</option>
                                     </c:forEach>
                                 </c:if>
                             </select> --%>
                            <input type="hidden" id="empId" name="sceneEmpId"/>
                            <input type="text" class="form-control" id="empId1" name="sceneEmpId1" placeholder=""   readonly="readonly" style="background-color: #F9F9F9">
                            <button type="button" class="btn btn-primary" id="J_export1"
                                    onclick="javascript:relateProjectLeader();"  style="margin-left:15px;">选择
                            </button>

                            <!-- <input type="text" class="form-control w300" id="partyB" name="partyB" placeholder=""
                             readonly="readonly" style="background-color: #F9F9F9">
                        <button type="button" class="btn btn-primary"  onclick="javascript:relateCompany('fromContract');"
                        style="vertical-align: top;">选择公司</button> -->
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">我方负责人电话<i>*</i>：</label>
                            <input type="text" class="form-control w200" name="empTel" id="empTel"
                                   placeholder="请输入11位电话号码" notnull="true" maxlength="11" dataType="phone">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li style="width:600px;">
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">预计当年大定金额<i>*</i>：</label>
                            <input type="text" class="form-control w125" name="subscribedThisYear" id="subscribedThisYear"
                                   placeholder="" notnull="true" maxlength="20" dataType="needMoney" onkeyup=""> 万元
                            <span class="fc-warning"></span>
                        </div>
                    </li>

                    <li>
                        <div class="form-group">
                            <label class="fw-normal w140 text-right">预计明年大定金额<i>*</i>：</label>
                            <input type="text" class="form-control w125" name="subscribedNextYear" id="subscribedNextYear"
                                   placeholder="" notnull="true" maxlength="20" dataType="needMoney" onkeyup="">万元
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">装修情况：</label>--%>
                            <%--<c:forEach items="${decorationKbnList}" var="list">--%>
                                <%--<input type="checkbox" value="${list.dicCode}" id="${list.dicCode}" name="decorationKbnCB"--%>
                                       <%--onchange="decorationKbnChange()">--%>
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
                            <%--<c:forEach items="${typeKbnList}" var="list">--%>
                                <%--<input type="checkbox" value="${list.dicCode}" id="${list.dicCode}" name="typeKbnCB"--%>
                                       <%--onchange="typeKbnChange()"><label class="fon-normal small">${list.dicValue}</label>--%>
                            <%--</c:forEach>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">规划户数：</label>--%>
                            <%--<input type="text" class="form-control w80" name="houseCnt" id="houseCnt" maxlength="9" placeholder=""--%>
                                   <%--dataType="posNumWithZero">户--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">车位情况：</label>--%>
                            <%--<input type="text" class="form-control w80" name="parkCnt" id="parkCnt" placeholder="" maxlength="6"--%>
                                   <%--dataType="posNumWithZero">位--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li style="margin: 0px 8px;">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">停车费：</label>--%>
                            <%--<input type="text" class="form-control w125" name="parkFee" placeholder="" maxlength="5"--%>
                                   <%--dataType="needMoney">元/月--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="stair list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">梯户：</label><input type="text"--%>
                                                                                       <%--class="form-control w50"--%>
                                                                                       <%--name="staircase[0]"--%>
                                                                                       <%--placeholder=""--%>
                                                                                       <%--dataType="posNumWithZero"--%>
                                                                                       <%--maxlength="1">梯<input type="text"--%>
                                                                                                             <%--class="form-control w50"--%>
                                                                                                             <%--name="household[0]"--%>
                                                                                                             <%--placeholder=""--%>
                                                                                                             <%--dataType="posNumWithZero"--%>
                                                                                                             <%--maxlength="3">户<span--%>
                                <%--class="btn btn-link btn-add-houst-type" onclick="addStair(this)">新增梯户数</span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<p><strong>物业信息</strong></p>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">物业公司：</label>--%>
                            <%--<input type="text" class="form-control w300" name="mgtCompany" id="mgtCompany"--%>
                                   <%--placeholder="" maxlength="25" dataType="normal">--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">容积率：</label>--%>
                            <%--<input type="text" class="form-control " name="rateFAR" id="rateFAR" placeholder=""--%>
                                   <%--maxlength="5" dataType="onedecimal" style="width:70px;">--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<div class="form-group" style="width:auto;margin-left: 45px;">--%>
                            <%--<label class="fw-normal w100 text-right">绿化率：</label>--%>
                            <%--<input type="text" class="form-control w80 " name="rateGreen" id="rateGreen" placeholder=""--%>
                                   <%--maxlength="5" dataType="flothree">%--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<div class="form-group" style="width:auto;margin-left: 7px;">--%>
                            <%--<label class="fw-normal w100 text-right">物业费用：</label>--%>
                            <%--<input type="text" class="form-control w125" name="mgtPrice" id="mgtPrice" placeholder="请输入数字"--%>
                                   <%--maxlength=10" dataType="needMoney">元/m²/月--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">供暖方式：</label>--%>
                            <%--<c:forEach items="${heatKbnList}" var="list">--%>
                                <%--<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="heatKbn"><label--%>
                                     <%--class="fon-normal small">${list.dicValue}</label>--%>
                            <%--</c:forEach>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">水电燃气：</label>--%>
                            <%--<c:forEach items="${hydropowerGasKbnList}" var="list">--%>
                                <%--<input type="radio" value="${list.dicCode}" id="${list.dicCode}"--%>
                                       <%--name="hydropowerGasKbn"><label class="fon-normal small">${list.dicValue}</label>--%>
                            <%--</c:forEach>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<p><strong>联动规则</strong></p>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" >认证类型<i>*</i>：</label>--%>
                            <%--<c:forEach items="${authenticationKbnList}" var="list">--%>
                                <%--<input type="radio" value="${list.dicCode}" id="${list.dicCode}"--%>
                                       <%--name="authenticationKbn"><label class="fon-normal small">${list.dicValue}</label>--%>
                            <%--</c:forEach>--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">提前报备期：</label>--%>
                            <%--<select class="form-control" title="" name="advanceReportHH">--%>
                                <%--<c:if test="${!empty advanceReportHHList}">--%>
                                    <%--<c:forEach items="${advanceReportHHList}" var="list">--%>
                                        <%--<c:if test="${list eq '00'}"><option value="${list}" selected="selected">${list}</option></c:if>--%>
                                        <%--<c:if test="${list ne '00'}"><option value="${list}">${list}</option></c:if>--%>
                                    <%--</c:forEach>--%>
                                <%--</c:if>--%>
                            <%--</select>时--%>
                            <%--<select class="form-control" title="" name="advanceReportMM">--%>
                                <%--<c:if test="${!empty advanceReportMMList}">--%>
                                    <%--<c:forEach items="${advanceReportMMList}" var="list">--%>
                                        <%--<c:if test="${list eq '00'}"><option value="${list}" selected="selected">${list}</option></c:if>--%>
                                        <%--<c:if test="${list ne '00'}"><option value="${list}">${list}</option></c:if>--%>
                                    <%--</c:forEach>--%>
                                <%--</c:if>--%>
                            <%--</select>分--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<div class="form-group" style="width:auto;margin-left: 20px;">--%>
                            <%--<label class="fw-normal w110 text-right">带看保护期<i>*</i>：</label>--%>
                            <%--<input type="text" class="form-control w80" name="relationProtectPeriod"--%>
                                   <%--id="relationProtectPeriod" placeholder="" notnull="true" maxlength="3"--%>
                                   <%--dataType="normal">天--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">带看奖励：</label>--%>
                            <%--<input type="text" class="form-control w125" name="relationReward" id="relationReward"--%>
                                   <%--placeholder="" maxlength="7" dataType="needMoney">元--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">起始日期</label>--%>
                            <%--<input type="text" class="form-control calendar-icon" name="relationDtStart" id="relationDtStart"--%>
                                   <%--onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'relationDtEnd\')}'})"--%>
                                   <%--readonly="readonly" class="ipttext Wdate" value=""/>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">截止日期</label>--%>
                            <%--<input type="text" class="form-control calendar-icon" name="relationDtEnd" id="relationDtEnd"--%>
                                   <%--onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'relationDtStart\')}'})"--%>
                                   <%--readonly="readonly" class="ipttext Wdate" value=""/>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">认筹奖励：</label>--%>
                            <%--<input type="text" class="form-control w125" name="pledgedReward" id="pledgedReward"--%>
                                   <%--placeholder="" maxlength="7" dataType="needMoney">元--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">起始日期</label>--%>
                            <%--<input type="text" class="form-control calendar-icon" name="pledgedDtStart" id="pledgedDtStart"--%>
                                   <%--onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'pledgedDtEnd\')}'})"--%>
                                   <%--readonly="readonly" class="ipttext Wdate" value=""/>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">截止日期</label>--%>
                            <%--<input type="text" class="form-control calendar-icon" name="pledgedDtEnd" id="pledgedDtEnd"--%>
                                   <%--onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'pledgedDtStart\')}'})"--%>
                                   <%--readonly="readonly" class="ipttext Wdate" value=""/>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">大定奖励：</label>--%>
                            <%--<input type="text" class="form-control w125" name="subscribedReward" id="subscribedReward"--%>
                                   <%--placeholder="" maxlength="7" dataType="needMoney">元--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">起始日期</label>--%>
                            <%--<input type="text" class="form-control calendar-icon" name="subscribedDtStart" id="subscribedDtStart"--%>
                                   <%--onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'subscribedDtEnd\')}'})"--%>
                                   <%--readonly="readonly" class="ipttext Wdate" value=""/>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">截止日期</label>--%>
                            <%--<input type="text" class="form-control calendar-icon" name="subscribedDtEnd" id="subscribedDtEnd"--%>
                                   <%--onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'subscribedDtStart\')}'})"--%>
                                   <%--readonly="readonly" class="ipttext Wdate" value=""/>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">成销奖励：</label>--%>
                            <%--<input type="text" class="form-control w125" name="bargainReward" id="bargainReward"--%>
                                   <%--placeholder="" maxlength="7" dataType="needMoney">元--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%-- <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">佣金方式<i>*</i>：</label>
                            <c:forEach items="${commissionKbnList}" var="list">
                                <input type="radio" value="${list.dicCode}" id="${list.dicCode}"
                                       name="commissionKbn"><label class="fon-normal small">${list.dicValue}</label>
                            </c:forEach>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul> --%>
                <!-- <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">佣金<i>*</i>：</label>
                            <input type="text" class="form-control w125" name="commission" id="commission"
                                   placeholder="" notnull="true" maxlength="7" dataType="needMoney"><span
                                id="commissionStatus">元</span>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" >结佣期限<i>*</i>：</label>
                            <input type="text" class="form-control w80" name="commissionPeriod" id="commissionPeriod"
                                   placeholder="" notnull="true" maxlength="3" dataType="posNumWithZero">天
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul> -->
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" style="vertical-align: top;">收入标的<i>*</i>：</label>--%>
                            <%--<textarea id="incomeSubject" name="incomeSubject" cols="45" placeholder="" maxlength="250"--%>
                                      <%--rows="5" notnull="true" dataType="normal3" style="resize: none;width: 750px;height: 100px;"></textarea>--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                        <%----%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" style="vertical-align: top;">收入结算条件<i>*</i>：</label>--%>
                            <%--<select class="form-control"  id="commissionCondition" name="commissionCondition" style="width:125px;" notnull="true">--%>
                                <%--<option value="" selected>请选择</option>--%>
                                <%--<option value="21902">成销</option>--%>
                                <%--<option value="21903">结算确认</option>--%>
                            <%--</select>--%>
                        <%--</div>  --%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" style="vertical-align: top;">收入结算描述<i>*</i>：</label>--%>
                            <%--<textarea id="commissionMemo" name="commissionMemo" cols="45" placeholder="" maxlength="250"--%>
                                      <%--rows="5" notnull="true" dataType="normal3" style="resize: none;width: 750px;height: 100px;"></textarea>--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>  --%>
                    <%--</li>--%>
                <%--</ul>--%>
                 <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" style="vertical-align: top;">返佣标准<i>*</i>：</label>--%>
                            <%--<textarea id="rtnCommission" name="rtnCommission" cols="45" placeholder="" maxlength="250"--%>
                                      <%--rows="5" notnull="true" dataType="normal3" style="resize: none;width: 750px;height: 100px;"></textarea>--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div> --%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" style="vertical-align: top;">返佣结算条件<i>*</i>：</label>--%>
                            <%--<textarea id="rtnCommissionMemo" name="rtnCommissionMemo" cols="45" placeholder="" maxlength="250"--%>
                                      <%--rows="5" notnull="true" dataType="normal3" style="resize: none;width: 750px;height: 100px;"></textarea>--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">结佣方式：</label>--%>
                            <%--<c:forEach items="${payKbnList}" var="list">--%>
                                <%--<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="payKbn"><label--%>
                                    <%--class="fon-normal small">${list.dicValue}</label>--%>
                            <%--</c:forEach>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">销售方式：</label>--%>
                            <%--<c:forEach items="${saleKbnList}" var="list">--%>
                                <%--<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="saleKbn"><label--%>
                                    <%--class="fon-normal small">${list.dicValue}</label>--%>
                            <%--</c:forEach>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">报备开始日<i>*</i>：</label>--%>
                            <%--<input type="text" class="form-control calendar-icon" name="reportDtStart" id="reportDtStart"--%>
                                   <%--id="reportDtStart"--%>
                                   <%--onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'reportDtEnd\')}'})"--%>
                                   <%--readonly="readonly" class="ipttext Wdate" value="" notnull="true"/>--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w90 text-right">报备截止日<i>*</i>：</label>--%>
                            <%--<input type="text" class="form-control calendar-icon" name="reportDtEnd" id="reportDtEnd"--%>
                                   <%--id="reportDtEnd"--%>
                                   <%--onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'reportDtStart\')}'})"--%>
                                   <%--readonly="readonly" class="ipttext Wdate" value="" notnull="true"/>--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline" style="display:none">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">报备模式：</label>--%>
                            <%--<c:forEach items="${reportKbnList}" var="list">--%>
                                <%--<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="reportKbn"><label--%>
                                     <%--class="fon-normal small">${list.dicValue}</label>--%>
                            <%--</c:forEach>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">隐号报备：</label>--%>
                            <%--<input type="radio" value="1" name="hideNumberFlg" checked><label class="fon-normal small">支持</label>--%>
                            <%--<input type="radio" value="0" name="hideNumberFlg"><label class="fon-normal small">不支持</label>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" style="vertical-align: top;">报备规则：</label>--%>
                            <%--<textarea name="reportRule" cols="30" placeholder="" maxlength="500"--%>
                                      <%--rows="10" style="resize: none;width:750px"></textarea>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
               <!--  <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal text-right" style="vertical-align: top;width:90px;margin-left: 15px;">中介佣金说明<i>*</i>：</label>
                            <textarea name="commissionRule" id="commissionRule" cols="30" placeholder="" maxlength="500"
                                      rows="10" style="resize: none;width:750px" notnull="true"></textarea>
                        </div>
                    </li>
                </ul> -->
                <%--<p><strong>在售户型</strong></p>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">主力户型：</label>--%>
                            <%--<input type="radio" value="1" name="countFlg"><label class="fon-normal small">是</label>--%>
                            <%--<input type="radio" value="0" name="countFlg"><label class="fon-normal small">否</label>--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li class="houseType">--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">户型：</label>--%>
                            <%--<input type="text" class="form-control w50" name="countF" id="countF" placeholder=""--%>
                                   <%--maxlength="2" dataType="posNumWithZero">室--%>
                            <%--<input type="text" class="form-control w50" name="countT" id="countT" placeholder=""--%>
                                   <%--maxlength="2" dataType="posNumWithZero">厅--%>
                            <%--<input type="text" class="form-control w50" name="countW" id="countW" placeholder=""--%>
                                   <%--maxlength="2" dataType="posNumWithZero">卫--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">面积：</label>--%>
                            <%--<input type="text" class="form-control w80" name="buildSquare" id="buildSquare"--%>
                                   <%--placeholder="" maxlength="4" dataType="normal">㎡--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">朝向：</label>--%>
                            <%--<c:forEach items="${directionKbnList}" var="list">--%>
                                <%--<input type="radio" value="${list.dicCode}" id="${list.dicCode}"--%>
                                       <%--name="directionKbn"><label class="fon-normal small">${list.dicValue}</label>--%>
                            <%--</c:forEach>--%>
                            <%--<span class="fc-warning"></span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline" style="display:none">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">标签：</label>--%>
                            <%--<input type="text" class="form-control w125" name="label[0]" placeholder="" maxlength="30">--%>
                            <%--<span class="btn btn-link btn-add-input-tag" onclick="addLabel(this)">新增标签</span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right"--%>
                                   <%--style="vertical-align: top;">户型图：</label>--%>
                            <%--<ul class="list_photo_uploader houseTypePhotos" style="display:inline-block">--%>
                                <%--<li class="item_photo_uploader item_photo_uploader_add">--%>
                                    <%--<s class="icon s_add_btn"></s>--%>
                                    <%--<input type="file" onchange="addPhoto(this);" class="file_control btn-add-photo"--%>
                                           <%--data-limit="10" data-name="houseTypePhotos"  capture="camera">--%>
                                <%--</li>--%>
                            <%--</ul>--%>
                            <%--<div class="cor_9" style="display:inline-block">最多上传10张</div>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" style="vertical-align: top;">样板间：</label>--%>
                            <%--<ul class="list_photo_uploader" style="display:inline-block">--%>
                                <%--<li class="item_photo_uploader item_photo_uploader_add"><s class="icon s_add_btn"></s>--%>
                                    <%--<input--%>
                                            <%--type="file" onchange="addPhoto(this);" class="file_control btn-add-photo"--%>
                                            <%--data-limit="10" data-name="templetPhotos"--%>
                                            <%--capture="camera"></li>--%>
                            <%--</ul>--%>
                            <%--<div class="cor_9" style="display:inline-block">最多上传10张</div>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<a href="javascript:void(0)" onclick="addSellingHouseType(this);" id="addSellingHouseType"--%>
                   <%--style="padding-left: 35px ">+新增在售户型</a>--%>

                <%--<p><strong>楼盘相册</strong></p>--%>
                 <%--<div style="margin-left: 40px;">--%>
					<%--<p style="color: red;"><i>* </i>提示：鼠标悬浮在上传图片上后,可设置楼盘封面图片。</p>--%>
				<%--</div>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" style="vertical-align: top;">效果图：</label>--%>
                            <%--<ul class="list_photo_uploader photo1" style="display:inline-block">--%>
                                <%--<li class="item_photo_uploader item_photo_uploader_add">--%>
                                    <%--<s class="icon s_add_btn"></s>--%>
                                    <%--<input type="file" onchange="addPhoto(this);" class="file_control btn-add-photo"--%>
                                            <%--data-limit="10" data-name="photo1"  capture="camera">--%>
                                <%--</li>--%>
                            <%--</ul>--%>
                            <%--<div class="cor_9" style="display:inline-block">最多上传10张</div>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" style="vertical-align: top;">样板间：</label>--%>
                            <%--<ul class="list_photo_uploader" style="display:inline-block">--%>
                                <%--<li class="item_photo_uploader item_photo_uploader_add"><s class="icon s_add_btn"></s>--%>
                                    <%--<input--%>
                                            <%--type="file" onchange="addPhoto(this);" class="file_control btn-add-photo"--%>
                                            <%--data-limit="10" data-name="photo2"--%>
                                            <%--capture="camera"></li>--%>
                            <%--</ul>--%>
                            <%--<div class="cor_9" style="display:inline-block">最多上传10张</div>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" style="vertical-align: top;">地理位置：</label>--%>
                            <%--<ul class="list_photo_uploader" style="display:inline-block">--%>
                                <%--<li class="item_photo_uploader item_photo_uploader_add"><s class="icon s_add_btn"></s>--%>
                                    <%--<input--%>
                                            <%--type="file" onchange="addPhoto(this);" class="file_control btn-add-photo"--%>
                                            <%--data-limit="10" data-name="photo3"--%>
                                            <%--capture="camera"></li>--%>
                            <%--</ul>--%>
                            <%--<div class="cor_9" style="display:inline-block">最多上传10张</div>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" style="vertical-align: top;">区域规划：</label>--%>
                            <%--<ul class="list_photo_uploader" style="display:inline-block">--%>
                                <%--<li class="item_photo_uploader item_photo_uploader_add"><s class="icon s_add_btn"></s>--%>
                                    <%--<input--%>
                                            <%--type="file" onchange="addPhoto(this);" class="file_control btn-add-photo"--%>
                                            <%--data-limit="10" data-name="photo4"--%>
                                            <%--capture="camera"></li>--%>
                            <%--</ul>--%>
                            <%--<div class="cor_9" style="display:inline-block">最多上传10张</div>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<ul class="list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right" style="vertical-align: top;">实景图：</label>--%>
                            <%--<ul class="list_photo_uploader" style="display:inline-block">--%>
                                <%--<li class="item_photo_uploader item_photo_uploader_add"><s class="icon s_add_btn"></s>--%>
                                    <%--<input--%>
                                            <%--type="file" onchange="addPhoto(this);" class="file_control btn-add-photo"--%>
                                            <%--data-limit="10" data-name="photo5"--%>
                                            <%--capture="camera"></li>--%>
                            <%--</ul>--%>
                            <%--<div class="cor_9" style="display:inline-block">最多上传10张</div>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<p><strong>周边配套</strong></p>--%>
                <%--<ul class="match list-inline form-inline">--%>
                    <%--<li>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="fw-normal w100 text-right">类型：</label><input type="text"--%>
                                                                                       <%--class="form-control w100"--%>
                                                                                       <%--name="title[0]" placeholder=""--%>
                                                                                       <%--maxlength="25"><label--%>
                                <%--class="fw-normal w100 text-right">描述：</label><input type="text"--%>
                                                                                    <%--class="form-control w300"--%>
                                                                                    <%--name="description[0]" placeholder=""--%>
                                                                                    <%--maxlength="100"><span--%>
                                <%--onclick="addMatch(this)" class="btn btn-link btn-add-descriptions">新增</span>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</div>--%>
            <div class="text-center" style="margin-top: 20px">
                <%--<input type="button" id="btn-save" onclick="add();" class="btn btn-primary" style="width: 100px;background-color: #71b0e6;" value="保存">--%>
                <input type="button" id="btn-submit" onclick="addSubmit();" class="btn btn-primary" style="width: 100px;margin-left: 100px!important;" value="提交"> 
                <a href="${ctx}/estate?searchParam=1" class="btn btn-default" style="width: 100px;margin-left: 100px!important;">取消</a>
            </div>
        </div>
    </div>
</form>
</body>
<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/estateCommonNew.js?_v=${vs}"></script>
</html>
