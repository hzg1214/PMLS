<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/estateEdit.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/report/expand/bootstrap-select.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/cssreport/bootstrap-select.css?_v=${vs}">
<form id="estatePopupForm">
    <div class="container theme-hipage ng-scope" role="main" style="width: 800px">
        <span class="" style="float:right"><a href="javascript:EstateType.closePopup();" class="btn btn-default">&times;</a></span>
        <div class="row">
            <div class="page-content">
                <input type="hidden" id="id" name="id" value="${id}"/>
                <h4 class="border-bottom pdb10"><strong>项目信息变更</strong></h4>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" style="margin-left: 45px;">
                            <label class="fw-normal w200 text-right"><i>*</i>修改项目类型:</label>
                            <%--<span style="padding-left:10px;">
                                <input type="hidden" value="" id="name"/>
                                <input type="checkbox" name="estateNm" id="estateNm" class="check" value="1" onclick="changeEstateNm(this)"/>楼盘名称
					         </span>--%>
                            <span style="margin-left: 15px;">
                                <input type="hidden" value="" id="address"/>
                                <input type="checkbox" name="estateAddress" id="estateAddress" class="check" value="1" onclick="changeAddress(this)"/>项目地址
                            </span>
                            <span style="margin-left: 15px;">
                                <input type="hidden" value="" id="sales"/>
                                <input type="checkbox" name="salesStatus" id="salesStatus" class="check" value="1" onclick="changeSalesStatus(this)"/>销售状态
                            </span>
                            <span style="margin-left: 15px;">
                                <input type="hidden" value="" id="mode"/>
                                <input type="checkbox" name="businessMode" id="businessMode" class="check" value="1" onclick="changeBusinessMode(this)"/>业务模式
                            </span>
                            <span class="fc-warning" id="checkChange"></span>
                        </div>
                    </li>
                </ul>
                <div id="changeName" style="display: none;">
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group" style="margin-left: 65px;">
                                <label class="fw-normal w200 text-right">原楼盘名称:</label>
                                <span style="padding-left:10px;"></span>
                                <input type="hidden" id="oldName" name="oldName" value="${estate.estateNm}">
                                ${estate.estateNm}
                            </div>
                        </li>
                    </ul>
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group" style="margin-left: 30px;">
                                <label class="fw-normal w200 text-right"><i>*</i>修改后楼盘名称:</label>
                                <span style="padding-left:10px;"></span>
                                <input type="text" class="form-control" id="newName" name="newName" style="width:545px;" notnull="true" value="">
                                <span class="fc-warning"></span>
                                <span id="checkName"></span>
                            </div>
                        </li>
                    </ul>
                </div>
                <div id="changeAddress" style="display: none;">
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group" style="margin-left: 65px;">
                                <label class="fw-normal w200 text-right">原项目地址:</label>
                                <span style="padding-left:10px;"></span>
                                <input type="hidden" id="oldCityNo" name="oldCityNo" value="${estate.realityCityNo}">
                                <input type="hidden" id="oldDistrictNo" name="oldDistrictNo" value="${estate.districtId}">
                                <input type="hidden" id="oldAddress" name="oldAddress" value="${estate.realityCityNm}${estate.districtNm}${estate.areaNm}${estate.address}">
                                ${estate.realityCityNm}${estate.districtNm}${estate.areaNm}${estate.address}
                            </div>
                        </li>
                    </ul>
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group" style="margin-left: 28px;">
                                <label><i>* </i>修改后项目地址:</label>
                                <span class="control-select" style="padding-left:10px;">
                                    <select class="form-control" title="" id="realityCityNo" name="realityCityNo" style="width: 118px;">
                                         <c:forEach items="${citylist}" var="city">
                                             <option value="${city.cityNo}" <c:if test="${city.cityNo eq estate.realityCityNo}">selected</c:if>>${city.cityName}</option>
                                         </c:forEach>
                                    </select>
                                </span>
                                <span class="control-select">
                                    <select class="form-control" title="" id="districtNo" name="districtNo" style="width: 118px;">
                                        <c:if test="${!empty districtList}">
                                            <c:forEach items="${districtList}" var="district">
                                                <option value="${district.districtNo}"<c:if test="${district.districtNo eq estate.districtId}">selected</c:if>>${district.districtName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </span>
                                <input type="hidden" id="newCityNm" name="newCityNm" value="${estate.realityCityNm}">
                                <input type="hidden" id="newDistrictNm" name="newDistrictNm" value="${estate.districtNm}">
                                <input type="text" class="form-control w300" id="newAddress" name="newAddress" placeholder="具体地址信息" value="" notnull="true" maxlength="100">
                                <span id="checkAddress"></span>
                            </div>
                        </li>
                    </ul>
                </div>
                <div id="changeSalesStatus" style="display: none;">
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group" style="margin-left: 65px;">
                                <label class="fw-normal w200 text-right">原销售状态:</label>
                                <span style="padding-left: 10px;"></span>
                                <input type="hidden" id="oldSalesStatus" name="oldSalesStatus" value="${estate.salesStatus}">
                                <input type="hidden" id="oldSalesStatusStr" name="oldSalesStatusStr" value="${estate.salesStatusStr}">
                                ${estate.salesStatusStr}
                            </div>
                        </li>
                    </ul>
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group" style="margin-left: 30px;">
                                <label class="fw-normal w200 text-right"><i>*</i>修改后销售状态:</label>
                                <span style="padding-left: 10px;"></span>
                                <input type="radio" value="1442" id="1442" name="newSalesStatus" onchange="openTimeChange(this);">
                                <label class="fon-normal small">待售</label>
                                <input type="radio" value="1441" id="1441" name="newSalesStatus" checked="checked" onchange="openTimeChange(this);">
                                <label class="fon-normal small">在售</label>
                                <input type="radio" value="1443" id="1443" name="newSalesStatus" onchange="openTimeChange(this);">
                                <label class="fon-normal small">售完</label>
                                <span id="checkSalesStatus"></span>
                            </div>
                        </li>
                    </ul>
                    <ul class="list-inline form-inline" id="ulOpenTime">
                        <li>
                            <div class="form-group" style="margin-left: 30px;">
                                <label class="fw-normal w110 text-left" style="vertical-align: top;margin-left: 13px" id="opentimeShow"><i>*</i>实际开盘日期:</label>
                                <span style="padding-left: 10px;"></span>
                                <input  type="text" class="calendar-icon " style="width:160px;"  name="openTime" id="openTime" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" class="ipttext Wdate"  notnull="true"/>
                            </div>
                        </li>
                    </ul>
                </div>
                <div id="changeBusinessMode" style="display: none;">
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group" style="margin-left: 65px;">
                                <label class="fw-normal w200 text-right">原业务模式:</label>
                                <c:if test="${!empty estate.businessModel and -1 ne estate.businessModel}"><span style="padding-left: 10px;"></span></c:if>
                                <input type="hidden" id="oldBusinessMode" name="oldBusinessMode" value="${estate.businessModel}">
                                <input type="hidden" id="oldBusinessModeStr" name="oldBusinessModeStr" value="<c:if test="${25501 eq estate.businessModel}">标准</c:if>
								<c:if test="${25502 eq estate.businessModel}">非标准（保证金）</c:if>
								<c:if test="${25503 eq estate.businessModel}">非标准（诚意金）</c:if>
								<c:if test="${25504 eq estate.businessModel}">非标准（包销）</c:if>
								<c:if test="${25505 eq estate.businessModel}">非标准（前佣）</c:if>
								<c:if test="${25506 eq estate.businessModel}">非标准（全民经纪）</c:if>
								<c:if test="${25507 eq estate.businessModel}">非标准（其他）</c:if>
								<c:if test="${25508 eq estate.businessModel}">纯垫佣</c:if>">
                                <c:if test="${25501 eq estate.businessModel}">标准</c:if>
                                <c:if test="${25502 eq estate.businessModel}">非标准（保证金）</c:if>
                                <c:if test="${25503 eq estate.businessModel}">非标准（诚意金）</c:if>
                                <c:if test="${25504 eq estate.businessModel}">非标准（包销）</c:if>
                                <c:if test="${25505 eq estate.businessModel}">非标准（前佣）</c:if>
                                <c:if test="${25506 eq estate.businessModel}">非标准（全民经纪）</c:if>
                                <c:if test="${25507 eq estate.businessModel}">非标准（其他）</c:if>
                                <c:if test="${25508 eq estate.businessModel}">纯垫佣</c:if>
                            </div>
                        </li>
                    </ul>
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group" style="margin-left: 30px;">
                                <label class="fw-normal w200 text-right"><i>*</i>修改后业务模式:</label>
                                <span style="padding-left: 10px;"></span>
                                <t:dictSelect field="newBusinessMode" id="newBusinessMode" xmlkey="LOOKUP_Dic" dbparam="P1:255" nullOption="请选择业务模式" classvalue="10"></t:dictSelect>
                                <input type="hidden" id="newBusinessModeStr" name="newBusinessModeStr">
                                <span id="checkBusinessMode"></span>
                            </div>
                        </li>
                    </ul>
                </div>
                <div id="changePartner" style="display: none;">
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group" style="margin-left: 51px;">
                                <label class="fw-normal w200 text-right">原合作方名称:</label>
                                <span style="padding-left:10px;"></span>
                                <input type="hidden" id="oldPartnerNm" name="oldPartnerNm" value="${estate.partnerNm}">
                                ${estate.partnerNm}
                            </div>
                        </li>
                    </ul>
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group" style="margin-left: 17px;">
                                <label class="fw-normal w200 text-right"><i>*</i>修改后合作方名称:</label>
                                <span style="padding-left:10px;"></span>
                                <input type="text" class="form-control" id="newPartnerNm" name="newPartnerNm" style="width:545px;" notnull="true" value="">
                                <span class="fc-warning"></span>
                                <span id="checkPartnerNm"></span>
                            </div>
                        </li>
                    </ul>
                </div>
                <div id="changeDeveloper" style="display: none;">
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group" style="margin-left: 51px;">
                                <label class="fw-normal w200 text-right">原开发商名称:</label>
                                <span style="padding-left:10px;"></span>
                                <input type="hidden" id="oldDevCompany" name="oldDevCompany" value="${estate.devCompany}">
                                ${estate.devCompany}
                            </div>
                        </li>
                    </ul>
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group" style="margin-left: 17px;">
                                <label class="fw-normal w200 text-right"><i>*</i>修改后开发商名称:</label>
                                <span style="padding-left:10px;"></span>
                                <input type="text" class="form-control" id="newDevCompany" name="newDevCompany" style="width:545px;" notnull="true" value="">
                                <span class="fc-warning"></span>
                                <span id="checkDevCompany"></span>
                            </div>
                        </li>
                    </ul>
                </div>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" style="margin-left: 73px;">
                            <label class="fw-normal w200 text-right" style="vertical-align: top;"><i>*</i>修改原因:</label>
                            <span style="padding-left:10px;"></span>
                            <textarea name="modifyReason" id="modifyReason" notnull="true" style="width:545px; height:110px"></textarea>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</form>

