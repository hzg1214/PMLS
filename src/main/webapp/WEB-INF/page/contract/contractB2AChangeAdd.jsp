<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/contract/contractChangeCom.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/contract/contractB2AChangeAdd.js?_v=${vs}"></script>
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
                <jsp:param value="110404" name="leftMenuSelectId" />
            </jsp:include>
            <div class="col-md-10 content">
                <div class="page-content">
                <br>
                    <h4>
                        <strong>新增变更</strong>
                    </h4>
                          
                           
                    <div class="" style="height: auto;">
                        <form id="stopContractForm">
                            <input type="hidden" name="contractId" id="contractId" value="${contractId}">
                            <!-- 存放图片Id -->
                            <!-- <input type="hidden" name="contractChangePicIds" id="contractChangePicIds"/> -->
                            <!-- 存放fangyou附件id集 -->
                            <input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"  />
                            <input type="hidden" name="oldCompanyId" id="oldCompanyId" value="${storeList[0].companyId}"/>
                            <ul class="list-inline form-inline">
                                <li>
                                    <div class="form-group" style="padding-left:10px;">
                                        <label class="fw-normal w125 text-right"><i>* </i>变更类型</label>
                                        <span style="padding-left:5px;"><t:dictSelect field="changeType" id="changeType" xmlkey="LOOKUP_Dic" dbparam="P1:170" defaultVal="17002" disable="true"></t:dictSelect></span>
                                        <input type="hidden" name="changeType" value="17002"/>
                                        <span class="fc-warning"></span>
                                    </div>
                                </li>
                            </ul>
                        
                            <ul class="list-inline form-inline" >
                                <li style="padding-left:15px;">
                                    <div class="form-group" id="checkbox" >
                                        <label class="fw-normal w125 text-right" ><i>* </i>乙转甲类型</label>
                                        <span style="padding-left:15px;"><input type="checkbox" name="mainchange"  id="mainchange" value="1"  onclick="signChange(this)" />签约主体变更</span>
                                        <span style="padding-left:5px;"><input type="checkbox" name="companyrange"  class="check" value="1" id="companyrange" onclick="manageChange(this)"/>公司经营范围变更</span>
                                        <span style="padding-left:15px;"><input type="checkbox" name="companychange" class="check" value="1" id="companychange" onclick="companyChange(this)"/>公司注册地址变更</span>
                                        <span style="padding-left:15px;"><input type="checkbox" name="storechange" class="check" value="1" id="storechange" onclick="storeChange(this)"/>门店地址变更</span>
                                    </div> 
                                </li>
                            </ul>
                            <div  id="companyNameChangeRadio" style="display:block;">
                                <ul class="list-inline form-inline">
                                    <li style="padding-left:50px;">
                                        <div class="form-group" >
                                            <label><i>* </i>是否工商更名</label>
                                            <span style="padding-left:15px;"><input type="radio" name="updateCompanyName"  id="companyNameChangeYes" value="1"  onclick="ContractB2A.companyNameChange(this)" />是</span>
                                            <span style="padding-left:15px;"><input type="radio" name="updateCompanyName"  id="companyNameChangeNo" value="0" checked="checked" onclick="ContractB2A.companyNameChange(this)" />否</span>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div id="companyNameChange" style="display: none;">
                                <ul class="list-inline form-inline">
                                    <li style="padding-left: 15px;">
                                        <div class="form-group">
                                            <label class="fw-normal w125 text-right" style="vertical-align: top;">更名前公司名称</label> 
                                            <input type="hidden" name="oldUpdateCompanyName" value="${storeList[0].companyName}">
                                            <span style="padding-left:5px;">${storeList[0].companyName}</span>
                                            <span class="fc-warning"></span>
                                        </div>
                                    </li>
                                </ul>
                                <ul class="list-inline form-inline">
                                    <li style="padding-left: 15px;">
                                        <div class="form-group">
                                            <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>*</i>更名后公司名称</label> 
                                            <span style="padding-left: 5px;"> 
                                                 <input type="text" class="form-control" id="newUpdateCompanyName" name="newUpdateCompanyName" placeholder="" style="width: 488px;" notnull="true" maxlength="50">
                                            </span>
                                            <span class="fc-warning"></span>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div  id="companyChangeAddress" style="display:none;">
                            <ul class="list-inline form-inline">
                                <li>
                                    <div class="form-group" >
                                        <label  ><i>* </i>变更后公司注册地址</label>
                                        <span class="control-select" style="padding-left:5px;">
                                            <select class="form-control" title="" id="companyCity" name="companyCity">
                                                <option value="" selected="selected">请选择城市</option>
                                                <c:forEach items="${citylist}" var="city">
                                                <option value="${city.cityNo}">${city.cityName}</option>
                                                </c:forEach>
                                            </select>
                                        </span>
                                        <span class="control-select">
                                            <select class="form-control" title="" id="companyDistrict" name="companyDistrict">
                                                <option selected="selected" value="">请选择区域</option>
                                                <%-- <c:if test="${!empty districtList}">
                                                    <c:forEach items="${districtList}" var="district">
                                                        <option value="${district.districtNo}">${district.districtName}</option>
                                                    </c:forEach>
                                                </c:if> --%>
                                            </select>
                                        </span>
                                        <input type="text" class="form-control w300" id="companyAddress" name="companyAddress" placeholder="具体地址信息" value=""  maxlength="100">
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
                                                <option value="" selected="selected">请选择城市</option>
                                                <c:forEach items="${citylist}" var="city">
                                                <option value="${city.cityNo}">${city.cityName}</option>
                                                </c:forEach>
                                            </select>
                                        </span>
                                        <span class="control-select">
                                            <select class="form-control" title="" id="storeDistrict" name="storeDistrict">
                                                <option selected="selected" value="">请选择区域</option>
                                                <%-- <c:if test="${!empty districtList}">
                                                    <c:forEach items="${districtList}" var="district">
                                                        <option value="${district.districtNo}">${district.districtName}</option>
                                                    </c:forEach>
                                                </c:if> --%>
                                            </select>
                                        </span>
                                        <input type="text" class="form-control w300" id="storeAddress" onchange="ContractB2A.checkStoreAddress(this)" name="storeAddress" placeholder="具体地址信息" value=""  maxlength="100">
                                    </div>
                                </li>
                            </ul>
                            </div>
                            
                        <div id="changeMain" style="display: none;">
                                 <ul  class="list-inline form-inline">
                                      <li style="padding-left:15px;">
                                            <div class="form-group">
                                            <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>新公司名称</label>
                                             <span style="padding-left:5px;">
                                             <input type="hidden" id="newCompanyId" name="newCompanyId">
                                             <input type="text" class="form-control" readonly id="newCompanyName" name="newCompanyName" placeholder="" style="width: 488px;background-color: #F9F9F9;" notnull="true">
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
                                             <input type="text" style="background-color: #F9F9F9;" class="form-control w300" readonly id="newLegalPerson" name="newLegalPerson" placeholder="" notnull="true"  maxlength="20">
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
                                            <input type="hidden" id="newCompanyAddressCityNo" name="newCompanyAddressCityNo">
                                            <input type="hidden" id="newCompanyAddressDistrictNo" name="newCompanyAddressDistrictNo">
                                            <input type="hidden" id="newCompanyAddress" name="newCompanyAddress">
                                            <input type="text" style="background-color: #F9F9F9;width: 120px;" class="form-control w120" id="address1" readonly>
                                            <input type="text" style="background-color: #F9F9F9;" class="form-control w200" id="address2" readonly>
                                            <input type="text" style="background-color: #F9F9F9;" class="form-control w300" id="address3" readonly>
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
                                                <input type="radio" value="${agreementTypeList.dicCode}" name="agreementType"  >
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
                                        <input type="text" onblur="ContractB2A.checkAgreementNo(this)" class="form-control w300" id="newAgreementNo" name="newAgreementNo" placeholder=""  notnull="true"  maxlength="20">
                                        </span>
                                        <span class="fc-warning"></span>
                                   </div>
                                   <div class="form-group" style="padding-left: 50px;">
                                               <label><i>* </i>新合同合作期限</label>
                                                     <div class="input-group date">
                                                         <input type="hidden" id="oldContractStartDate" value="${contractInfo.contract.dateLifeStart}">
                                                     <input type="text" class="calendar-icon form-control w100" name="newDateLifeStart"
                                                           id="newDateLifeStart"
                                                           onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'newDateLifeEnd\');}',minDate:'#F{$dp.$D(\'oldContractStartDate\',{d:1});}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                                           readonly="readonly" class="ipttext Wdate" style="width: 120px;"/>
                                                     </div>
                                                     <span>-</span>
                                                     <div class="input-group date">
                                                      <input type="text" class="calendar-icon form-control w100" name="newDateLifeEnd"
                                                           id="newDateLifeEnd"
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
                                        <input type="text" class="form-control w300" id="authRepresentative" value="${contractInfo.contract.authRepresentative}" name="authRepresentative" placeholder=""  notnull="true" maxlength="20">
                                        </span>
                                        <span class="fc-warning"></span>
                                   </div>
                                   <div class="form-group" style="padding-left: 92px;">
                                        <label><i>* </i>联系方式</label>
                                        <input type="text" class="form-control w300" id="agentContact" value="${contractInfo.contract.agentContact}" name="agentContact" placeholder="" maxlength="11" >
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>保证金金额</label>
                                        <input type="text" class="form-control w300" id="totleDepositFee" name="totleDepositFee" placeholder=""  notnull="true"  datatype="moneyWithFlot"  maxlength="9">
                                        <span class="fc-warning"></span>
                                   </div>
                                   <div class="form-group" style="padding-left: 92px;">
                                        <label><i>* </i>违约金额</label>
                                        <input type="text" class="form-control w300" id="penaltyFee" name="penaltyFee" placeholder="" notnull="true"  datatype="moneyWithFlot"  maxlength="9" >
                                        <span class="fc-warning"></span>
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">客户公司账号</label>
                                        <input type="text" class="form-control w300" id="companyBankNo" value="${contractInfo.contract.companyBankNo}" name="companyBankNo" placeholder="" value=""  maxlength="50">
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">开户银行</label>
                                        <input type="text" class="form-control w300" id="bankAccount" value="${contractInfo.contract.bankAccount}" name="bankAccount" placeholder="" value=""  maxlength="30">
                                   </div>
                                   <div class="form-group" style="padding-left: 118px;">
                                        <label>开户名</label>
                                        <input type="text" class="form-control w300" id="accountName" value="${contractInfo.contract.accountName}" name="accountName" placeholder="" value=""  maxlength="20">
                                   </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:20px;">
                                   <div class="form-group" >
                                       <label class="fw-normal w125 text-right" style="vertical-align: top;">甲方收件人</label>
                                        <input type="text" class="form-control w300" id="recipients" value="${contractInfo.contract.recipients}" name="recipients" placeholder="" value=""  maxlength="20">
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
                                            <c:if test="${contractInfo.contract.cityNo eq city.cityNo}">
                                                <option value="${city.cityNo}" selected="selected">${city.cityName}</option>
                                            </c:if>
                                            <c:if test="${contractInfo.contract.cityNo ne city.cityNo}">
                                                <option value="${city.cityNo}">${city.cityName}</option>
                                            </c:if>
                                          </c:forEach>
                                    </select>
                                </span>
                                <span class="control-select">
                                    <select class="form-control w200"  id="districtNo" name="districtNo">
                                        <option selected="selected" value="" >请选区域</option>
                                        <c:if test="${!empty districtList}">
                                           <c:forEach items="${districtList}" var="district">
                                              <c:if test="${fn:trim(contractInfo.contract.districtNo) eq district.districtNo}">
                                               <option value="${district.districtNo}" selected="selected">${district.districtName}</option>
                                              </c:if>
                                              <c:if test="${fn:trim(contractInfo.contract.districtNo) ne district.districtNo}">
                                               <option value="${district.districtNo}">${district.districtName}</option>
                                              </c:if>
                                           </c:forEach>
                                       </c:if> 
                                    </select>
                                </span>
                                <input type="text" class="form-control w300" id="recipientsAddress" value="${contractInfo.contract.recipientsAddress}" name="recipientsAddress" placeholder="具体地址信息" value=""  maxlength="100">
                                   </div>
                                </li>
                            </ul>
                    </div>
                        
                            
                            <ul class="list-inline form-inline">
                                <li style="padding-left:15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">备注</label>
                                       <span style="padding-left:5px;"> <textarea maxlength="150" name="remarks" id="remarks" cols="30" rows="10"  style="resize: none;" ></textarea></span>
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
                                        <td style="display:none">
                                            <input type="hidden" value="${item.btypeStore}" id="sign"/>
                                        </td>


                                    </tr>
                                    <c:set var="index" value="${index+1}"></c:set>
                                </c:forEach>
                            </table>
                            
                            </form>
                            
                            
    
<div class="" role="main">
    <p><strong>附件</strong></p>
</div>                              
                            <div  id="signEnclosure">
                            <!-- 变更补充条款 -->
<!-- <div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>变更补充条款
                    </h4>
                    <div class="thumb-xs-box" id="stopContractBox">
                        <div class="thumb-xs-list item-photo-add" >
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
 </div>                     -->         
                             
                            <!-- 新营业执照 -->
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>新营业执照
                    </h4>
                    <div class="thumb-xs-box" id="surrenderBox">
                        <div class="thumb-xs-list item-photo-add" >
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
                            
                            <!-- 国家企业信用信息公示系统网站截图 -->

<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>国家企业信用信息公示系统网站截图
                    </h4>
                    <div class="thumb-xs-box" id="receiptBox">
                        <div class="thumb-xs-list item-photo-add" >
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
                             <!-- 房友确认单-->
                             <div style="display:none;" id="storeChangeEnclosure">
                        
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>房友确认单
                    </h4>
                    <div class="thumb-xs-box" id="returnProveBox">
                        <div class="thumb-xs-list item-photo-add" >
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
                             
                            <!--门店照片-->
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>门店照片
                    </h4>
                    <div class="thumb-xs-box" id="cancellateBox">
                        <div class="thumb-xs-list item-photo-add" >
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
                        <div class="thumb-xs-list item-photo-add" >
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
                        <div class="thumb-xs-list item-photo-add" >
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
                             
                            <!-- 新签合作协议书-->

<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>新签合作协议书
                    </h4>
                    <div class="thumb-xs-box" id="newSigningBox">
                        <div class="thumb-xs-list item-photo-add" >
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
                        <div class="thumb-xs-list item-photo-add" >
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
                        <div class="thumb-xs-list item-photo-add" >
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
                             
                            <!-- 国家企业信用信息公示系统网站截图 -->

<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>国家企业信用信息公示系统网站截图
                    </h4>
                    <div class="thumb-xs-box" id="informationPublicityBox">
                        <div class="thumb-xs-list item-photo-add" >
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
                        <div class="thumb-xs-list item-photo-add" >
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
                             
                            <!--门店照片-->
<div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>门店照片
                    </h4>
                    <div class="thumb-xs-box" id="cancellateBox">
                        <div class="thumb-xs-list item-photo-add" >
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
 
 <div class="" role="main">
        <div class="row">
                <div class="pd10">
                    <h4 class="thumb-title">
                        <i>*</i>重要提示函
                    </h4>
                    <div class="thumb-xs-box" id="noticeBox">
                        <div class="thumb-xs-list item-photo-add" >
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
                        <div class="thumb-xs-list item-photo-add" >
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
                    </div>
                </div>
                
                
                <span id="msgId" style="color:red"></span>
                <div class="text-center">
                    <a href="javascript:void(0)" onclick="Save(${contractId},${contractStatus} )" class="btn btn-primary">保存</a>
                    <a href="${ctx}/contractChangeNew/list/${contractId}/${contractStatus}" class="btn btn-default mgl20">取消</a>
                </div>
            </div>
        </div>
        
    </div>
</body>
</html>


