<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/contract/contractChangeCom.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/contract/contractB2AChangeEdit.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/relate/relateUtil.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/relate/relateCompany.js?_v=${vs}"></script> 
</head>
<body>
    <!-- 头部 -->
    <jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
        <jsp:param value="${headMenuIdSelect}" name="navSelectId" />
    </jsp:include>
    <div class="container">
        <div class="row article">
            <!-- 左侧菜单 -->
            <jsp:include page="/WEB-INF/page/contract/contractLeftMenu.jsp"
                flush="true">
                <jsp:param value="110402" name="leftMenuSelectId" />
            </jsp:include>
            <div class="col-md-10 content">
                <div class="page-content">
                <br>
                    <h4>
                        <strong>修改变更</strong>
                    </h4>
                    
                    <div class="" style="height: auto;">
                        <form id="stopContractForm">
                            <input type="hidden" name="contractId" id="contractId" value="${contractId}">
                            <input type="hidden" name="contractChangeId" id="contractChangeId" value="${contractChange.id}">
                            <input type="hidden" name="oldUpdateCompanyName" value="${oldCompanyName}">
                            <!-- 存放图片Id -->
                            <%-- <input type="hidden" name="contractChangePicIds" id="contractChangePicIds" value="${contractChange.contractChangePicIds}"> --%>
                            <!-- 存放fangyou附件id集 -->
                            <input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"  value="${contractChange.fileRecordMainIds}"/>
                            <input type="hidden" name="oldCompanyId" id="oldCompanyId" value="${contractChange.oldCompanyId}"/>
                            <ul class="list-inline form-inline">
                                <li>
                                    <div class="form-group" style="padding-left:10px;">
                                        <label class="fw-normal w125 text-right"><i>* </i>变更类型</label>
                                        <span style="padding-left:5px;"><t:dictSelect field="changeType" id="changeType" xmlkey="LOOKUP_Dic" dbparam="P1:170" defaultVal="${contractChange.changeType}" disable="true"></t:dictSelect></span>
                                        <input type="hidden" name="changeType" value="${contractChange.changeType}"/>
                                        <span class="fc-warning"></span>
                                    </div>
                                </li>
                            </ul>
                        
                            <ul class="list-inline form-inline" >
                                <li style="padding-left:15px;">
                                    <div class="form-group" id="checkbox" >
                                        <label class="fw-normal w125 text-right"><i>* </i>乙转甲类型</label>
                                        <span style="padding-left:5px;">
                                        <span style="padding-left:15px;">
                                        <input type="hidden" value="${contractChange.changeCompany}" id="sign" />
                                        <input type="checkbox" name="mainchange" id="mainchange" 
                                                          <c:if test="${contractChange.changeCompany  == 1}">
                                                                  checked="checked"
                                                          </c:if> 
                                                          value="1"  onclick="signChange(this)"  />签约主体变更
                                        </span>
                                        <input type="hidden" value="${contractChange.changeCompanyName}" id="companyManagement" />
                                        <input type="checkbox" name="companyrange"  id="companyrange" class="check" 
                                                           <c:if test="${contractChange.changeCompanyName  == 1}">
                                                                  checked="checked"
                                                            </c:if> 
                                                            value="1"  onclick="manageChange(this)"  />公司经营范围变更
                                        </span>
                                        <span style="padding-left:15px;">
                                        <input type="hidden" value="${contractChange.changeCompanyAdress}" id="companyRegister" />
                                        <input type="checkbox" name="companychange" id="companychange" class="check"  
                                                           <c:if test="${contractChange.changeCompanyAdress  == 1}">
                                                                  checked="checked"
                                                           </c:if> 
                                                           value="1"  onclick="companyChange(this)"  />公司注册地址变更
                                        </span>
                                        <span style="padding-left:15px;">
                                        <input type="hidden" value="${contractChange.changeStoreAdress}" id="storeRegister" />
                                        <input type="checkbox" name="storechange" id="storechange" class="check" 
                                                          <c:if test="${contractChange.changeStoreAdress  == 1}">
                                                                  checked="checked"
                                                          </c:if> 
                                                          value="1"  onclick="storeChange(this)"  />门店地址变更
                                        </span>
                                    </div> 
                                </li>
                            </ul>
                            <c:if test="${contractChange.changeCompanyName  == 1 || contractChange.changeCompanyAdress  == 1 || (contractChange.changeStoreAdress  == 1 && contractChange.changeCompany  != 1)}">
                                 <div  id="companyNameChangeRadio" style="display:block;">
                                    <ul class="list-inline form-inline">
                                        <li style="padding-left:50px;">
                                            <div class="form-group" >
                                                <label><i>* </i>是否工商更名</label>
                                                <c:if test="${contractChange.updateCompanyName eq 1}">
                                                    <span style="padding-left:15px;"><input type="radio" name="updateCompanyName"  id="companyNameChangeYes" value="1" checked="checked" onclick="ContractB2A.companyNameChange(this)" />是</span>
                                                    <span style="padding-left:15px;"><input type="radio" name="updateCompanyName"  id="companyNameChangeNo" value="0" onclick="ContractB2A.companyNameChange(this)" />否</span>
                                                </c:if>
                                                <c:if test="${empty contractChange.updateCompanyName || contractChange.updateCompanyName eq 0}">
                                                    <span style="padding-left:15px;"><input type="radio" name="updateCompanyName"  id="companyNameChangeYes" value="1" onclick="ContractB2A.companyNameChange(this)" />是</span>
                                                    <span style="padding-left:15px;"><input type="radio" name="updateCompanyName"  id="companyNameChangeNo" value="0" checked="checked" onclick="ContractB2A.companyNameChange(this)" />否</span>
                                                </c:if>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                <div id="companyNameChange" <c:if test="${contractChange.updateCompanyName eq 1}">style="display: block;"</c:if><c:if test="${contractChange.updateCompanyName ne 1}">style="display: none;"</c:if>>
                                    <ul class="list-inline form-inline">
                                        <li style="padding-left: 15px;">
                                            <div class="form-group">
                                                <label class="fw-normal w125 text-right" style="vertical-align: top;">更名前公司名称</label>
                                                <span style="padding-left:5px;">${oldCompanyName}</span>
                                                <span class="fc-warning"></span>
                                            </div>
                                        </li>
                                    </ul>
                                    <ul class="list-inline form-inline">
                                        <li style="padding-left: 15px;">
                                            <div class="form-group">
                                                <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>*</i>更名后公司名称</label> 
                                                <span style="padding-left: 5px;"> 
                                                     <input type="text" class="form-control" id="newUpdateCompanyName" name="newUpdateCompanyName" value="${contractChange.newUpdateCompanyName}" placeholder="" style="width: 488px;" notnull="true" maxlength="50">
                                                </span>
                                                <span class="fc-warning"></span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </c:if>
                            <c:if test="${contractChange.changeCompany  == 1}">
                                 <div  id="companyNameChangeRadio" style="display:none;">
                                    <ul class="list-inline form-inline">
                                        <li style="padding-left:50px;">
                                            <div class="form-group" >
                                                <label><i>* </i>是否工商更名</label>
                                                <c:if test="${contractChange.updateCompanyName eq 1}">
                                                    <span style="padding-left:15px;"><input type="radio" name="updateCompanyName"  id="companyNameChangeYes" value="1" checked="checked" onclick="ContractB2A.companyNameChange(this)" />是</span>
                                                    <span style="padding-left:15px;"><input type="radio" name="updateCompanyName"  id="companyNameChangeNo" value="0" onclick="ContractB2A.companyNameChange(this)" />否</span>
                                                </c:if>
                                                <c:if test="${empty contractChange.updateCompanyName || contractChange.updateCompanyName eq 0}">
                                                    <span style="padding-left:15px;"><input type="radio" name="updateCompanyName"  id="companyNameChangeYes" value="1" onclick="ContractB2A.companyNameChange(this)" />是</span>
                                                    <span style="padding-left:15px;"><input type="radio" name="updateCompanyName"  id="companyNameChangeNo" value="0" checked="checked" onclick="ContractB2A.companyNameChange(this)" />否</span>
                                                </c:if>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                <div id="companyNameChange" style="display: none;">
                                    <ul class="list-inline form-inline">
                                        <li style="padding-left: 15px;">
                                            <div class="form-group">
                                                <label class="fw-normal w125 text-right" style="vertical-align: top;">更名前公司名称</label> 
                                                <span style="padding-left:5px;">${oldCompanyName}</span>
                                                <span class="fc-warning"></span>
                                            </div>
                                        </li>
                                    </ul>
                                    <ul class="list-inline form-inline">
                                        <li style="padding-left: 15px;">
                                            <div class="form-group">
                                                <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>*</i>更名后公司名称</label> 
                                                <span style="padding-left: 5px;"> 
                                                     <input type="text" class="form-control" id="newUpdateCompanyName" name="newUpdateCompanyName" value="${contractChange.newUpdateCompanyName}" placeholder="" style="width: 488px;" notnull="true" maxlength="50">
                                                </span>
                                                <span class="fc-warning"></span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </c:if>
                            <div  id="companyChangeAddress" style="display:none;">
                            <ul class="list-inline form-inline">
                                <li>
                                    <div class="form-group" >
                                        <label><i>* </i>变更后公司注册地址</label>
                                        <span class="control-select" style="padding-left:5px;">
                                            <select class="form-control" title="" id="companyCity" name="companyCity">  
                                            <option selected="selected" value="" >请选择城市</option>    
                                                <c:forEach items="${citylist}" var="city">
                                                <c:if test="${fn:trim(contractChange.companyCity) eq city.cityNo}">
                                                   <option value="${city.cityNo}" selected="selected">${city.cityName}</option>
                                                </c:if>
                                                <c:if test="${fn:trim(contractChange.companyCity) ne city.cityNo}">
                                                   <option value="${city.cityNo}">${city.cityName}</option>
                                                </c:if>
                                                </c:forEach>
                                            </select>
                                        </span>
                                        <span class="control-select">
                                            <select class="form-control" title="" id="companyDistrict" name="companyDistrict">  
                                            <option selected="selected" value="" >请选区域</option>
                                                 <c:if test="${!empty companyDistrictList}">
                                                    <c:forEach items="${companyDistrictList}" var="district">
                                                       <c:if test="${fn:trim(contractChange.companyDistrict) eq district.districtNo}">
                                                        <option value="${district.districtNo}" selected="selected">${district.districtName}</option>
                                                       </c:if>
                                                       <c:if test="${fn:trim(contractChange.companyDistrict) ne district.districtNo}">
                                                        <option value="${district.districtNo}">${district.districtName}</option>
                                                       </c:if>
                                                    </c:forEach>
                                                </c:if> 
                                            </select>
                                        </span>
                                        <input type="text" class="form-control w300" id="companyAddress" name="companyAddress" placeholder="具体地址信息" value="${contractChange.companyAdresss}"  maxlength="100">
                                    </div>
                                </li>
                            </ul>
                            </div>
                            
                            <div  id="storeChangeAddress" style="display:none;">
                            <ul class="list-inline form-inline">
                                <li style="padding-left:16px;">
                                    <div class="form-group" >
                                        <label  class="fw-normal w125 text-right"  >修改前门店地址</label>
                                        <input type="hidden" name="oldStoreAddressDetail" value="${storeList[0].addressDetail}">
                                        <span style="padding-left:5px;">${storeList[0].addressDetail}</span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:16px;">
                                    <div class="form-group" >
                                        <label  class="fw-normal w125 text-right"  ><i>* </i>修改后门店地址</label>
                                        <span class="control-select" style="padding-left:5px;">
                                            <select class="form-control" title="" id="storeCity" name="storeCity">  
                                            <option selected="selected" value="">请选择城市</option>
                                                <c:forEach items="${citylist}" var="city">
                                                     <c:if test="${fn:trim(contractChange.storeCity) eq city.cityNo}">
                                                       <option value="${city.cityNo}" selected="selected">${city.cityName}</option>
                                                     </c:if>
                                                     <c:if test="${fn:trim(contractChange.storeCity) ne city.cityNo}">
                                                       <option value="${city.cityNo}">${city.cityName}</option>
                                                     </c:if>
                                                </c:forEach>
                                            </select>
                                        </span>
                                        <span class="control-select">
                                            <select class="form-control" title="" id="storeDistrict" name="storeDistrict">
                                            <option selected="selected" value="" >请选区域</option>
                                                <c:if test="${!empty storeDistrictList}">
                                                    <c:forEach items="${storeDistrictList}" var="district">
                                                        <c:if test="${fn:trim(contractChange.storeDistrict) eq district.districtNo}">
                                                            <option value="${district.districtNo}" selected="selected">${district.districtName}</option>
                                                        </c:if>
                                                        <c:if test="${fn:trim(contractChange.storeDistrict) ne district.districtNo}">
                                                            <option value="${district.districtNo}">${district.districtName}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </span>
                                        <input type="text" class="form-control w300" id="storeAddress" name="storeAddress" onchange="ContractB2A.checkStoreAddress(this)"  placeholder="具体地址信息" value="${contractChange.storeAdresss}"  maxlength="100">
                                    </div>
                                </li>
                            </ul>
                            </div>
                            
                                <!-- 主体变更时才显示   -->
                        <div id="changeMain" style="display: none;">
                                 <ul  class="list-inline form-inline">
                                      <li style="padding-left:15px;">
                                            <div class="form-group">
                                            <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>新公司名称</label>
                                             <span style="padding-left:5px;">
                                             <input type="hidden" id="newCompanyId" name="newCompanyId" value="${contractChange.newCompanyId}">
                                             <input type="text" class="form-control" readonly id="newCompanyName" name="newCompanyName" value="${contractChange.newCompanyName}" style="width: 488px;background-color: #F9F9F9;" notnull="true">
                                             <button type="button" class="btn btn-primary"  onclick="javascript:ContractB2A.popCompany({},ContractB2A.companyPopCallBack);" style="vertical-align: top;">选择公司</button>
                                             </span>
                                             <span class="fc-warning"></span>
                                             </div>
                                      </li>
                                 </ul>
                        
                        
                        
                                  <ul class="list-inline form-inline">
                                       <li style="padding-left:15px;">
                                            <div class="form-group">
                                            <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>新公司法定代表人</label>
                                             <span style="padding-left:5px;">
                                             <input type="text" style="background-color: #F9F9F9;" class="form-control w300" readonly id="newLegalPerson" name="newLegalPerson" placeholder="" value="${contractChange.newLegalPerson}" notnull="true"  maxlength="20">
                                             </span>
                                             <span class="fc-warning"></span>
                                             </div>
                                       </li>
                                  </ul>
                                  
                         <ul class="list-inline form-inline">
                                <li style="padding-left:15px;">
                                    <div class="form-group" >
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>新营业执照地址</label>
                                        <span style="padding-left:5px;">
                                            <input type="hidden" id="newCompanyAddressCityNo" name="newCompanyAddressCityNo" value="${contractChange.newCompanyAddressCityNo}">
                                            <input type="hidden" id="newCompanyAddressDistrictNo" name="newCompanyAddressDistrictNo" value="${contractChange.newCompanyAddressDistrictNo}">
                                            <input type="hidden" id="newCompanyAddress" name="newCompanyAddress" value="${contractChange.newCompanyAddress}">
                                            <c:forEach items="${citylist}" var="city">
                                                <c:if test="${city.cityNo eq contractChange.newCompanyAddressCityNo}">
                                                    <input type="text" value="${city.cityName}" style="background-color: #F9F9F9;width: 120px;" class="form-control w120" id="address1" readonly>
                                                </c:if>
                                            </c:forEach>
                                            <c:forEach items="${mainDistrictList}" var="mainDistrict">
                                                <c:if test="${mainDistrict.districtNo eq contractChange.newCompanyAddressDistrictNo}">
                                                    <input type="text" value="${mainDistrict.districtName}" style="background-color: #F9F9F9;width: 120px;" class="form-control w120" id="address1" readonly>
                                                </c:if>
                                            </c:forEach>
                                            <input type="text" value="${contractChange.newCompanyAddress}" style="background-color: #F9F9F9;" class="form-control w300" id="address3" readonly>
                                        </span>
                                    </div>
                                </li>
                            </ul>
                            
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>合作协议书类型</label>
                                        <c:forEach items="${agreementTypeList}" var="agreementTypeList">
                                            <c:if test="${agreementTypeList.dicCode != 11003 && agreementTypeList.dicCode != 11001}">
                                                <c:if test="${agreementTypeList.dicCode eq contractChange.agreementType}">
                                                    <input checked="checked" type="radio" value="${agreementTypeList.dicCode}" name="agreementType">
                                                </c:if>
                                                <c:if test="${agreementTypeList.dicCode ne contractChange.agreementType}">
                                                    <input type="radio" value="${agreementTypeList.dicCode}" id="${agreementTypeList.dicCode}" name="agreementType">
                                                </c:if>
                                                <label for="${agreementTypeList.dicCode}" class="fon-normal small">${agreementTypeList.dicValue}</label>
                                            </c:if>
                                        </c:forEach>
                                        <span class="fc-warning"></span>
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>新合作协议书编号</label>
                                        <span>
                                        <input type="text" onblur="ContractB2A.checkAgreementNo(this)" class="form-control w300" id="newAgreementNo" name="newAgreementNo" value="${contractChange.newAgreementNo}" placeholder=""  notnull="true"  maxlength="20">
                                        </span>
                                        <span class="fc-warning"></span>
                                   </div>
                                   <div class="form-group" style="padding-left: 50px;">
                                               <label><i>* </i>新合同合作期限</label>
                                                     <div class="input-group date">
                                                         <input type="hidden" id="oldContractStartDate" value="${contractInfo.contract.dateLifeStart}">
                                                     <input type="text" class="calendar-icon form-control w100" name="newDateLifeStart"
                                                           id="newDateLifeStart"
                                                           value="${sdk:ymd2(contractChange.newDateLifeStart)}"
                                                           onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'newDateLifeEnd\');}',minDate:'#F{$dp.$D(\'oldContractStartDate\',{d:1});}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                                           readonly="readonly" class="ipttext Wdate" style="width: 120px;"/>
                                                     </div>
                                                     <span>-</span>
                                                     <div class="input-group date">
                                                      <input type="text" class="calendar-icon form-control w100" name="newDateLifeEnd"
                                                           id="newDateLifeEnd"
                                                           value="${sdk:ymd2(contractChange.newDateLifeEnd)}"
                                                           onFocus="WdatePicker({minDate:'#F{$dp.$D(\'newDateLifeStart\');}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                                           readonly="readonly" class="ipttext Wdate" style="width: 120px;"/>
                                                     </div>
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>甲方授权代表</label>
                                        <span>
                                        <input type="text" class="form-control w300" id="authRepresentative" name="authRepresentative" value="${contractChange.authRepresentative}" placeholder=""  notnull="true" maxlength="20">
                                        </span>
                                        <span class="fc-warning"></span>
                                   </div>
                                   <div class="form-group" style="padding-left: 92px;">
                                        <label><i>* </i>联系方式</label>
                                        <input type="text" class="form-control w300" id="agentContact" name="agentContact" value="${contractChange.agentContact}" placeholder="" maxlength="11" >
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>保证金金额</label>
                                        <input type="text" class="form-control w300" id="totleDepositFee" name="totleDepositFee" value="${contractChange.totleDepositFee}" placeholder=""  notnull="true"  datatype="moneyWithFlot"  maxlength="9">
                                        <span class="fc-warning"></span>
                                   </div>
                                   <div class="form-group" style="padding-left: 92px;">
                                        <label><i>* </i>违约金额</label>
                                        <input type="text" class="form-control w300" id="penaltyFee" name="penaltyFee" value="${contractChange.penaltyFee}" placeholder="" notnull="true"  datatype="moneyWithFlot"  maxlength="9" >
                                        <span class="fc-warning"></span>
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">客户公司账号</label>
                                        <input type="text" class="form-control w300" id="companyBankNo" name="companyBankNo" value="${contractChange.companyBankNo}" placeholder="" value=""  maxlength="50">
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">开户银行</label>
                                        <input type="text" class="form-control w300" id="bankAccount" name="bankAccount" value="${contractChange.bankAccount}" placeholder="" value=""  maxlength="30">
                                   </div>
                                   <div class="form-group" style="padding-left: 118px;">
                                        <label>开户名</label>
                                        <input type="text" class="form-control w300" id="accountName" name="accountName" value="${contractChange.accountName}" placeholder="" value=""  maxlength="20">
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">甲方收件人</label>
                                        <input type="text" class="form-control w300" id="recipients" name="recipients" value="${contractChange.recipients}" placeholder="" value=""  maxlength="20">
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">甲方收件地址</label>
                                       <span class="control-select">
                                    <select class="form-control w120" title="" id="cityNo" name="cityNo">
                                          <option value="" selected="selected">请选择城市</option>
                                          <c:forEach items="${citylist}" var="city">
                                            <c:if test="${contractChange.cityNo eq city.cityNo}">
                                                <option value="${city.cityNo}" selected="selected">${city.cityName}</option>
                                            </c:if>
                                            <c:if test="${contractChange.cityNo ne city.cityNo}">
                                                <option value="${city.cityNo}">${city.cityName}</option>
                                            </c:if>
                                          </c:forEach>
                                    </select>
                                </span>
                                <span class="control-select">
                                    <select class="form-control w200"  id="districtNo" name="districtNo">
                                            <option selected="selected" value="">请选择行政区</option>  
                                        <c:forEach items="${districtList}" var="district">
                                            <c:if test="${contractChange.districtNo eq district.districtNo}">
                                               <option value="${district.districtNo}" selected="selected">${district.districtName}</option>
                                            </c:if>
                                            <c:if test="${contractChange.districtNo ne district.districtNo}">
                                               <option value="${district.districtNo}">${district.districtName}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </span>
                                <input type="text" class="form-control w300" id="recipientsAddress" name="recipientsAddress" value="${contractChange.recipientsAddress}" placeholder="具体地址信息" value=""  maxlength="100">
                                   </div>
                                </li>
                            </ul>
                    </div>
                        
                            
                            
                            <ul class="list-inline form-inline">
                                <li style="padding-left:15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">备注</label>
                                       <span style="padding-left:5px;"> <textarea maxlength="150" name="remarks" id="remarks" cols="30" rows="10"  style="resize: none;" >${contractChange.remarks}</textarea></span>
                                        <span class="fc-warning"></span>
                                    </div>
                                </li>
                            </ul>
                            
                            <p><strong>门店信息</strong></p>
                            <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                                <tr>                                   
                                    <th>门店编号</th>
                                    <th>门店名称</th>
                                    <th>门店地址</th>
                                    <th>门店资质等级</th>
                                    <th>创建日期</th>
                                </tr>
                                <c:set var="index" value="0"></c:set>
                                <c:forEach items="${storeList}" var="item" varStatus="listIndex">
                                    <tr>
                                        <td>${item.storeNo}</td>
                                        <td>${item.name}</td>
                                        <td>${item.addressDetail}</td>
                                        <td>
                                            <c:if test="${empty item.abtypeStore || item.abtypeStore eq 0}"> -- </c:if>
                                            <c:if test="${item.abtypeStore eq 19901}">甲类</c:if>
                                            <c:if test="${item.abtypeStore eq 19902 }">乙类 (${item.btypeStoreName})</c:if>
                                        </td>
                                        <td>${item.dateCreate}</td>
                                        <td style="display:none">
                                            <input type="hidden" id="storeIdArray" name="storeIdArray" value="${item.storeId}"/>
                                        </td>
                                        <td style="display:none">
                                            <input type="hidden" id="storeNoArray" name="storeNoArray" value="${item.storeNo}"/>
                                        </td>
                                    </tr>
                                    <c:set var="index" value="${index+1}"></c:set>
                                </c:forEach>
                            </table>
                            
                            </form>
    
<div class="" role="main">
    <p><strong>附件</strong></p>
</div>                              
                            <!--  变更补充条款 -->
                            <div  id="signEnclosure" >

<%-- <div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>变更补充条款
                    </h4>
                    <div class="thumb-xs-box" id="stopContractBox">
                    <c:if test="${contractChange.changeSupplementFileList != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.changeSupplementFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}">
                                <input type="hidden" name="fileTypeId" value="1010" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" />
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                    
                        <div class="thumb-xs-list item-photo-add" 
                        <c:if test="${contractChange.changeSupplementFileList != null && contractChange.changeSupplementFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="1010" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                        
                    </div>
                </div>
     </div>
 </div> 
         --%>                     
                             <!-- 新营业执照 -->

<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>新营业执照
                    </h4>
                    <div class="thumb-xs-box" id="surrenderBox">
                    <c:if test="${contractChange.newBusinessLicenseFileList != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.newBusinessLicenseFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
                                <input type="hidden" name="fileTypeId" value="1011" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <%-- <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" /> --%>
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                        <div class="thumb-xs-list item-photo-add" 
                        <c:if test="${contractChange.newBusinessLicenseFileList != null && contractChange.newBusinessLicenseFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="1011" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                    <i class="fontset">注：须字迹清晰且符合甲类标准。</i>
                </div>
     </div>
 </div>                          
                            
                             
                              <!-- 国家企业信用信息公示系统网站截图-->

<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>国家企业信用信息公示系统网站截图
                    </h4>
                    <div class="thumb-xs-box" id="receiptBox">
                    <c:if test="${contractChange.informationPublicityFileList != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.informationPublicityFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
                                <input type="hidden" name="fileTypeId" value="1012" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <%-- <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" /> --%>
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                        <div class="thumb-xs-list item-photo-add" 
                            <c:if test="${contractChange.informationPublicityFileList != null && contractChange.informationPublicityFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="1012" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                    <i class="fontset">注：须信息完整且字迹清晰。</i>
                </div>
     </div>
 </div>                           
                             
                             <div id="storeChangeEnclosure" style="display:none;" >
                            <!-- 房友确认单-->   
                            
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>房友确认单
                    </h4>
                    <div class="thumb-xs-box" id="returnProveBox">
                    <c:if test="${contractChange.confirmationSheetFileList != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.confirmationSheetFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
                                <input type="hidden" name="fileTypeId" value="1013" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <%-- <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" /> --%>
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                        <div class="thumb-xs-list item-photo-add" 
                        <c:if test="${contractChange.confirmationSheetFileList != null && contractChange.confirmationSheetFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="1013" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                    <i class="fontset">注：须中介盖章，且与门店实际经营地址一致。</i>
                </div>
     </div>
 </div> 
                             
                            <!-- 门店照片-->
                                
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>门店照片
                    </h4>
                    <div class="thumb-xs-box" id="cancellateBox">
                    <c:if test="${contractChange.storePhotosFileList != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.storePhotosFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
                                <input type="hidden" name="fileTypeId" value="1014" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <%-- <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" /> --%>
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                        <div class="thumb-xs-list item-photo-add" 
                            <c:if test="${contractChange.storePhotosFileList != null && contractChange.storePhotosFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="1014" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                    <i class="fontset">注：含店招、门牌号（须字迹清晰可辨识）的内外景照片。</i>
                </div>
     </div>
 </div> 
                            
                             </div>
                             <!-- 其他-->   
                                        
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        其他
                    </h4>
                    <div class="thumb-xs-box" id="thumbXsBox">
                    <c:if test="${contractChange.changeOthersFileList != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.changeOthersFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
                                <input type="hidden" name="fileTypeId" value="1015" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <%-- <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" /> --%>
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                        <div class="thumb-xs-list item-photo-add" 
                            <c:if test="${contractChange.changeOthersFileList != null && contractChange.changeOthersFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="1015" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                    <i class="fontset">注：若注册地址变更，另须合作协议书的合同要素页（修改处须中介盖章）；若门店经营地址变更，另须确认单。</i>
                </div>
     </div>
 </div> 
                            
                             </div>
                             
                             
                     <!--  签约主体变更 附件 -->
                        <!-- 权利义务转让三方协议书-->
                        <div style="display:none;" id="signChangeEnclosure">
                        
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>权利义务转让三方协议书
                    </h4>
                    <div class="thumb-xs-box" id="transferRightsBox">
                    <c:if test="${contractChange.transferRightsFileList != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.transferRightsFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
                                <input type="hidden" name="fileTypeId" value="1016" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <%-- <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" /> --%>
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                        <div class="thumb-xs-list item-photo-add" 
                            <c:if test="${contractChange.transferRightsFileList != null && contractChange.transferRightsFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="1016" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                    <i class="fontset">注：合同空格处完整填写，原营业执照和新营业执照公司盖章且法定代表人签字。</i>
                </div>
     </div>
 </div> 
                             
                             <!-- 新签合作协议书 -->
                                                
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>新签合作协议书
                    </h4>
                    <div class="thumb-xs-box" id="newSigningBox">
                    <c:if test="${contractChange.newSigningFileList != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.newSigningFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="新签合作协议书" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
                                <input type="hidden" name="fileTypeId" value="1017" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <%-- <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" /> --%>
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                        <div class="thumb-xs-list item-photo-add" 
                            <c:if test="${contractChange.newSigningFileList != null && contractChange.newSigningFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="1017" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                    <i class="fontset">注：请清晰上传合同封皮的正反面页、合同要素表页、全部附件页（附件合作确认函正文无需填写，仅在骑缝处盖章；空白附件划掉后上传）。</i>
                </div>
     </div>
 </div>

<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>新营业执照
                    </h4>
                    <div class="thumb-xs-box" id="surrenderBox">
                    <c:if test="${contractChange.newBusinessLicenseFileList != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.newBusinessLicenseFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="新营业执照" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <input type="hidden" name="fileTypeId" value="1011" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <%-- <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" /> --%>
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                        <div class="thumb-xs-list item-photo-add" 
                        <c:if test="${contractChange.newBusinessLicenseFileList != null && contractChange.newBusinessLicenseFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="1011" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                    <i class="fontset">注：须字迹清晰且符合甲类标准。</i>
                </div>
     </div>
 </div>  
 
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>法人身份证
                    </h4>
                    <div class="thumb-xs-box" id="legalPersonBox">
                    <c:if test="${contractChange.legalCardFileList != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.legalCardFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="法人身份证" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <input type="hidden" name="fileTypeId" value="2" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <%-- <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" /> --%>
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                        <div class="thumb-xs-list item-photo-add" 
                        <c:if test="${contractChange.legalCardFileList != null && contractChange.legalCardFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="2" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                    <i class="fontset">注：须字迹清晰；身份证须上传正反面且在有效期。</i>
                </div>
     </div>
 </div>                          
                              <!-- 国家企业信用信息公示系统网站截图-->
                                                                    
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>国家企业信用信息公示系统网站截图
                    </h4>
                    <div class="thumb-xs-box" id="informationPublicityBox">
                    <c:if test="${contractChange.mainInformationPublicityFileList != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.mainInformationPublicityFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
                                <input type="hidden" name="fileTypeId" value="1018" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <%-- <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" /> --%>
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                        <div class="thumb-xs-list item-photo-add" 
                            <c:if test="${contractChange.mainInformationPublicityFileList != null && contractChange.mainInformationPublicityFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="1018" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                    <i class="fontset">注：须信息完整且字迹清晰。</i>
                </div>
     </div>
 </div> 
 <!-- 房友确认单-->   
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>房友确认单
                    </h4>
                    <div class="thumb-xs-box" id="returnProveBox">
                    <c:if test="${contractChange.confirmationSheetFileList != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.confirmationSheetFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="房友确认单" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
                                <input type="hidden" name="fileTypeId" value="1013" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <%-- <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" /> --%>
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                        <div class="thumb-xs-list item-photo-add" 
                        <c:if test="${contractChange.confirmationSheetFileList != null && contractChange.confirmationSheetFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="1013" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                    <i class="fontset">注：须中介盖章，且与门店实际经营地址一致。</i>
                </div>
     </div>
 </div> 
<!-- 门店照片-->
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>门店照片
                    </h4>
                    <div class="thumb-xs-box" id="cancellateBox">
                    <c:if test="${contractChange.storePhotosFileList != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.storePhotosFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="门店照片" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
                                <input type="hidden" name="fileTypeId" value="1014" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <%-- <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" /> --%>
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                        <div class="thumb-xs-list item-photo-add" 
                            <c:if test="${contractChange.storePhotosFileList != null && contractChange.storePhotosFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="1014" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                    <i class="fontset">注：含店招、门牌号（须字迹清晰可辨识）的内外景照片。</i>
                </div>
     </div>
 </div> 
<!-- 重要提示函-->
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>重要提示函
                    </h4>
                    <div class="thumb-xs-box" id="noticeBox">
                    <c:if test="${contractChange.fileRecordMainNotice != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.fileRecordMainNotice}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="重要提示函" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
                                <input type="hidden" name="fileTypeId" value="1020" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <%-- <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" /> --%>
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                        <div class="thumb-xs-list item-photo-add" 
                            <c:if test="${contractChange.mainChangeOthersFileList != null && contractChange.mainChangeOthersFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="1020" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                    <i class="fontset">注：须中介签字并盖章确认。</i>
                </div>
     </div>
 </div>  
<!-- 其他-->
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        其他
                    </h4>
                    <div class="thumb-xs-box" id="thumbXsBox">
                    <c:if test="${contractChange.mainChangeOthersFileList != null }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.mainChangeOthersFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                            <span class="thumb-bottom-roup">
                                                <i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
                                            </span>
                                       </a>
                                
                                <input type="hidden" name="limitSize" value="10">
                                <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"  />
                                <input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
                                <%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
                                <input type="hidden" name="fileTypeId" value="1019" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name="oaFileId" value="" />
                                <%-- <input type="hidden" name="contractChangePicId" value="${list.contractChangePicId}" /> --%>
                              </div>
                        </c:forEach> 
                        
                    </c:if>
                        <div class="thumb-xs-list item-photo-add" 
                            <c:if test="${contractChange.mainChangeOthersFileList != null && contractChange.mainChangeOthersFileList.size()>=10  }">style="display: none;"</c:if>>
                            <input type="hidden" name="limitSize" value="10">
                            <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                <input type="hidden" name="fileTypeId" value="1019" />
                                <input type="hidden" name="fileSourceId" value="4" />
                                <input type="hidden" name ="companyId" value="">
                            </a>
                        </div>
                    </div>
                    <i class="fontset">注：回执（须店东签字且盖章）。</i>
                </div>
     </div>
 </div>  
                             </div>
                        <span id="msgId" style="color:red"></span>
                    </div>
                </div>
                <div class="text-center">
                    <a href="javascript:void(0)" onclick="doEdit(${contractChange.id},${contractStatus})" class="btn btn-primary">保存</a>
                    <a href="${ctx}/contractChangeNew/list/${contractId}/${contractStatus}" class="btn btn-default mgl20">取消</a>
                </div>
            </div>
        </div>
</body>
</html>


