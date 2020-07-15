<!-- Add By NingChao 2017/04/07  续签画面添加 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/report/expand/multi.select.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/contract/contractAdd.js?_v=${vs}"></script> 

<script type="text/javascript" src="${ctx}/meta/js/contract/contractCom.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/relate/relateUtil.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/relate/relateStores.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/relate/relateUser.js?_v=${vs}"></script>
</head>

<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
<form id = "contractAddForm" >
<input type ="hidden"  id = "centerId"   name = "centerId" value="${centerId}" />
<input type="hidden" id="storeId" value="${storeId}">
<input type="hidden" name="_method" value="put"/>
 <input type = "hidden"  id = "fileTypeIds"   name = "fileTypeIds" value = "${contractInfo.fileTypeIds}"/>
 <input type = "hidden"  id = "oldContractNo"   name = "oldContractNo" value = "${contractInfo.contract.contractNo}"/>
 <input type = "hidden"  id = "id"   name = "id" value = "${contractInfo.contract.id}"/>
 <input type = "hidden"  id = "companyId"   name = "companyId" value = "${contractInfo.contract.companyId}"/>
 <input type = "hidden"  id = "storeIdArray"   name = "storeIdArray"  />
 <input type = "hidden"  id = "oldStoreIdArray"   name = "oldStoreIdArray"  value = "${contractInfo.oldStoreIdArray}" />
 
    <!-- 存放fangyou附件id集  old-->
    <input type="hidden" name="oldfileRecordMainIds" id="oldfileRecordMainIds"  value = "${contractInfo.fileRecordMainIds}" />
    
    <!-- 存放fangyou附件id集 -->
	<input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"  value = "${contractInfo.fileRecordMainIds}" />
	
	<%--<!-- 中介营业执照 -->
	<input type="hidden" name="oaFileIdBusiness" id="oaFileIdBusiness" value="${contractInfo.contract.oaFileIdBusiness}"/><!-- oa -->
	<!-- 中介法定代表人/负责人身份证或名片 -->
	<input type="hidden" name="oaFileIdCard" id="oaFileIdCard"  value="${contractInfo.contract.oaFileIdCard}"/><!-- oa -->
	<!-- 合作协议书 -->
	<input type="hidden" name="oaFileIdPhoto" id="oaFileIdPhoto"  value="${contractInfo.contract.oaFileIdPhoto}"/><!-- oa -->
	<!-- 中介门店照片（门店外景和室内） -->
	<input type="hidden" name="oaFileIdStore" id="oaFileIdStore"  value="${contractInfo.contract.oaFileIdStore}"/><!-- oa -->
	 <!-- 房友系统申请安装单/确认单 -->
	<input type="hidden" name="oaFileIdInstall" id="oaFileIdInstall"  value="${contractInfo.contract.oaFileIdInstall}"/><!-- oa -->
	<!-- 其他-->
	<input type="hidden" name="oaFileIdOther" id="oaFileIdOther"  value="${contractInfo.contract.oaFileIdOther}"/><!-- oa -->--%>
	<!-- OA审批流程类别 -->
	<!--<input type="hidden" name="oaApproveType" id="oaApproveType"  value="${contractInfo.contract.oaApproveType}"/>-->
	<input type="hidden" name="ContractTypeB2A" id="ContractTypeB2A"  value="20202"/><!-- 是否乙类转甲类-->
<%--  	<input type="hidden" id="contractTypeStr" name="contractTypeStr" value="${contractInfo.contract.contractType}" > --%>
 
 	<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>新建续签合同</strong></h4>
                <p><strong>基本信息</strong></p>
                 <ul class="list-inline half form-inline">
                	<li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">原合同编号 :</label>
                            <input type="hidden" class="form-control w300" id="originalContractNo" name="originalContractNo" placeholder="" value="${contractInfo.contract.contractNo}"  readonly="readonly">
                        	<label >${contractInfo.contract.contractNo}</label>                        	
                        </div>
                    </li>  
                 </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">甲方名称</label>
                            <input type="text" class="form-control w300" id="partyB" name="partyB" placeholder="甲方名称"  value="${company.companyName}"  readonly="readonly" style="background-color: #F9F9F9">
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                        	<label class="fw-normal w120 text-right"  for="lealPerson"><i>* </i>法定代表/负责人</label>
                            <input type="text" class="form-control w300" id="lealPerson" name="lealPerson" placeholder="请输入"  notnull="true"   value="${company.legalPerson}"  readonly="readonly" style="background-color: #F9F9F9">
                            <span class="fc-warning"></span>
                        </div>
                    </li>  
                </ul>
                
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                        	<label class="fw-normal w120 text-right"  for="partyBAddress"><i>* </i>公司注册地址</label>
                        	<input type="hidden" class="form-control w120" id="partyBcityNo" name="partyBcityNo" value="${company.cityNo}">
                            <input type="hidden" class="form-control w120" id="partyBdistrictNo" name="partyBdistrictNo" value="${company.districtNo}" >
                            <input type="text" class="form-control" id="partyBCityName" name="partyBCityName"  value="${company.cityName}" notnull="true"  readonly="readonly" style="width:94px;background-color: #F9F9F9">
                            <input type="text" class="form-control" id="partyBDistrictName" name="partyBDistrictName"  value="${company.districtName}" notnull="true"  readonly="readonly" style="width:136px;background-color: #F9F9F9">   
                            <input type="text" class="form-control w300" id="partyBAddress" name="partyBAddress" placeholder="公司地址"  notnull="true"  value="${company.address}"  readonly="readonly" style="background-color: #F9F9F9">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                
                <ul class="list-inline half form-inline ">
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w125 text-right" for="registrId"><i>* </i>统一社会信用代码</label>
                            <input type="text" class="form-control" id="registrId" name="registrId" style="width:296px" placeholder=""  notnull="true"  maxlength="30" value="${company.businessLicenseNo}" readonly="readonly" style="background-color: #F9F9F9">
                            <span class="fc-warning"></span>
                        </div>
                    </li>  
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="agreementNo"><i>* </i>协议书编号</label>
                            <input type="text" class="form-control w300" id="agreementNo" name="agreementNo" placeholder=""  notnull="true" maxlength="20">
                            <span class="fc-warning"></span>
                        </div>
                    </li>  
                </ul>
                
                <ul class="list-inline half form-inline">
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right"><i>* </i>合作模式</label>
                            	<c:choose>
                              		<c:when test="${contractInfo.contract.contractType eq 10304}">
                              			 <input type="hidden" id="contractType" name="contractType" value="10302" >
                              		</c:when>
                              		 <c:otherwise>
	                           			 <input type="hidden" id="contractType" name="contractType" value="${contractInfo.contract.contractType}" >
                              		  </c:otherwise>
                              	 </c:choose>
	                            <input type="radio" value="10307" id="10307" name="contractType"  <c:if test="${contractInfo.contract.contractType eq  10307}">checked</c:if> disabled style='vertical-align:-3px' >
	                            <label for="10307" class="fon-normal small">授牌</label>
	                            <input type="radio" value="10302" id="10302" name="contractType"  <c:if test="${contractInfo.contract.contractType eq  10302 || contractInfo.contract.contractType eq  10304}">checked</c:if> disabled style='vertical-align:-3px' >
	                            <label for="10302" class="fon-normal small">B</label>
                           		<%-- <c:forEach items="${contractTypeList}" var="contractTypeList">
                           		<c:if test="${contractTypeList.dicCode == 10302 || contractTypeList.dicCode == 10307 }">
	                           		<c:choose>
	                                    <c:when test="${contractInfo.contract.contractType eq 10304}">
	                                         <input type="hidden" id="contractType" name="contractType" value="10302" >
	                                        <input type="radio" value="${contractTypeList.dicCode}" id="${contractTypeList.dicCode}" name="contractType"  <c:if test="${contractTypeList.dicCode eq  10302}">checked</c:if> disabled style='vertical-align:-3px' >
	                                    </c:when>
	                                    <c:otherwise>
		                        		    <input type="radio" value="${contractTypeList.dicCode}" id="${contractTypeList.dicCode}" name="contractType"  <c:if test="${contractInfo.contract.contractType eq  contractTypeList.dicCode}">checked</c:if> disabled style='vertical-align:-3px' >
		                                </c:otherwise>
		                             </c:choose>
		                        		<label for="${contractTypeList.dicCode}" class="fon-normal small">${contractTypeList.dicValue}</label>
	                        	</c:if>
 								</c:forEach> --%>
                        </div>
                    </li>
                </ul>
                
				<!--授牌类型 -->
                <c:if test="${contractInfo.contract.contractType ==10307}">
                	<ul class="list-inline half form-inline" id="shoupaiTypeLi" >
	                	<li>
	                		<div class="form-group">
		                		<label class="fw-normal w120 text-right"><i>* </i>授牌类型</label>
	                			<c:forEach items="${shoupaiTypeList}" var="shoupaiTypeList">
                        		   <c:choose>
                                      <c:when test="${contractInfo.contract.shoupaiType eq shoupaiTypeList.dicCode}">
                                           <input type="radio" value="${shoupaiTypeList.dicCode}" id="${shoupaiTypeList.dicCode}" name="shoupaiType" onchange="Contract.changeShoupaiType(this);" checked="checked" style="vertical-align: -2px; margin-right:3px;">
                                           <label for="${shoupaiTypeList.dicCode}" class="fon-normal small" style="margin-right:5px;">${shoupaiTypeList.dicValue}</label>
                                      </c:when>
                                      <c:otherwise>
                                           <input type="radio" value="${shoupaiTypeList.dicCode}" id="${shoupaiTypeList.dicCode}" name="shoupaiType" onchange="Contract.changeShoupaiType(this);" style="vertical-align: -2px; margin-right:3px;">
                                           <label for="${shoupaiTypeList.dicCode}" class="fon-normal small" style="margin-right:5px;">${shoupaiTypeList.dicValue}</label>
                                      </c:otherwise>
                                   </c:choose>
                        		</c:forEach>
                        	</div>
	                	</li>
                	</ul>
	            </c:if>
                
				<c:if test="${contractInfo.contract.contractType ==10302 || contractInfo.contract.contractType ==10304}">
	                <ul class="list-inline half form-inline">
	                	<li>
	                		<div class="form-group">
	                		<input type="hidden" id="agreementType" name="agreementType" value="${contractInfo.contract.agreementType}" >
	                		<label class="fw-normal w120 text-right"><i>* </i>合作协议书类型</label>
	                		<c:forEach items="${agreementTypeList}" var="agreementTypeList">
	                			<c:if test="${agreementTypeList.dicCode != 11001 && agreementTypeList.dicCode != 11006}">
	                        		<input type="radio" value="${agreementTypeList.dicCode}" id="${agreementTypeList.dicCode}" name="agreementType"/>
									<label for="${agreementTypeList.dicCode}" class="fon-normal small">${agreementTypeList.dicValue}</label>
								</c:if>
							</c:forEach>  
	                		</div>
	                	</li>
	                </ul>
				</c:if>
				
				<c:if test="${contractInfo.contract.contractType ==10307}">
                    <input type="hidden" name="agreementType" id="agreementType"  value="11006"/>
                </c:if>
                
                <c:if test="${contractInfo.contract.contractType ==10306 || contractInfo.contract.contractType ==10301 || contractInfo.contract.contractType ==10307}">
	                <ul class="list-inline half form-inline" id="OAapproval" style="display:none;">
	                    <li>
	                        <div class="form-group" >
	                            <label class="fw-normal w120 text-right"><i>* </i>OA审批流程类别</label>
	                        	 <c:forEach items="${oaApproveTypeList}" var="oaApproveTypeList">
	                        	 	<c:choose>
	                                      <c:when test="${contractInfo.contract.oaApproveType eq oaApproveTypeList.dicCode}">
	                                           <input type="radio" value="${oaApproveTypeList.dicCode}" id="${oaApproveTypeList.dicCode}" name="oaApproveType" checked="checked" style="vertical-align: -2px; margin-right:3px;">
	                                           <label for="${oaApproveTypeList.dicCode}" class="fon-normal small" style="margin-right:5px;">${oaApproveTypeList.dicValue}</label>
	                                      </c:when>
	                                      <c:otherwise>
	                                           <input type="radio" value="${oaApproveTypeList.dicCode}" id="${oaApproveTypeList.dicCode}" name="oaApproveType"  style="vertical-align: -2px; margin-right:3px;">
	                                           <label for="${oaApproveTypeList.dicCode}" class="fon-normal small" style="margin-right:5px;">${oaApproveTypeList.dicValue}</label>
	                                      </c:otherwise>
	                                   </c:choose>
	                        	</c:forEach>
	                        </div>
	                    </li>
	                </ul>
                </c:if>
                
                <c:if test="${contractInfo.contract.contractType ==10302 || contractInfo.contract.contractType ==10304}">
                    <!-- OA审批流程类别 -->
                    <input type="hidden" name="oaApproveType" id="oaApproveType"  value="${contractInfo.contract.oaApproveType}"/>
                </c:if>
                   <c:if test="${contractInfo.contract.contractType ==10302 || contractInfo.contract.contractType ==10304}">
				   <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                        	<label class="fw-normal w120 text-right"><i>* </i>合同生效日期</label>
                            <input  type="text" class="calendar-icon  w300"  name="dateLifeStart" id="dateLifeStart" value="${sdk:ymd2(contractDateLifeEnd.dateLifeEnd)}"  class="ipttext Wdate"  readonly/>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                        	<label class="fw-normal w120 text-right"><i>* </i>合同到期日期</label>
                            <input  type="text" class="calendar-icon form-control w300" name="dateLifeEnd" id="dateLifeEnd" 
                				onFocus="WdatePicker({minDate:'#F{$dp.$D(\'dateLifeStart\',{d:364});}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                			class="ipttext Wdate"  notnull="true"/>
                            <span class="fc-warning"></span>   
                        </div>
                    </li>  
                </ul>
                </c:if>
                <c:if test="${contractInfo.contract.contractType ==10307}">
                	<ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                        	<label class="fw-normal w120 text-right"><i>* </i>合同生效日期</label>
                            <input  type="text" class="calendar-icon  w300"  name="dateLifeStart" id="dateLifeStart" value="${sdk:ymd2(contractInfo.contract.dateLifeEnd)}"  class="ipttext Wdate"  readonly/>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                        	<label class="fw-normal w120 text-right"><i>* </i>合同到期日期</label>
                            <input  type="text" class="calendar-icon form-control w300" name="dateLifeEnd" id="dateLifeEnd" 
                				onFocus="WdatePicker({minDate:'#F{$dp.$D(\'dateLifeStart\',{d:1});}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                			class="ipttext Wdate"  notnull="true"/>
                            <span class="fc-warning"></span>   
                        </div>
                    </li>  
                </ul>
                </c:if>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">甲方授权代表</label>
                            <input type="text" class="form-control w300" id="authRepresentative" name="authRepresentative" placeholder=""  value="${contractInfo.contract.authRepresentative}" maxlength="20">
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="agentContact"><i>* </i>联系方式</label>
                            <input type="text" class="form-control w300" id="agentContact" name="agentContact"  value="${contractInfo.contract.agentContact}" maxlength="11">
                        	<span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="depositFee"><i>* </i>保证金金额</label>
                            <input type="text" class="form-control w300" id="depositFee" name="depositFee" placeholder=""  notnull="true"  onblur="calculateTotleDepositFee(4)" value=""  datatype="moneyWithFlot"  maxlength="9" >
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <input type="hidden" class="form-control w300" id="storeNum" name="storeNum" placeholder=""  value="1" >
                            <input type="hidden" class="form-control w300" id="totleDepositFee" name="totleDepositFee" placeholder=""  value="${contractInfo.contract.totleDepositFee}"  datatype="moneyWithFlot"  maxlength="9"  >
                              
                            <label class="fw-normal w120 text-right"  for="penaltyFee"><i>* </i>违约金金额</label>
                            <input type="text" class="form-control w300" id="penaltyFee" name="penaltyFee" placeholder=""  notnull="true"  datatype="moneyWithFlot"  maxlength="9" >
                            <span class="fc-warning"></span>
                        </div>
                    </li>  
                </ul>   
                
                <ul class="list-inline half form-inline">
                   <li>
                        <div class="form-group">
                        <!-- 业绩归属拓展人工号 -->
                        	<input type="hidden" id="expandingPersonnelId" name="expandingPersonnelId" value="${contractInfo.contract.expandingPersonnelId}" >
                            <label class="fw-normal w120 text-right" for="expandingPersonnel"><i>* </i>业绩归属拓展人</label>
                            <input type="text" class="form-control w300" id="expandingPersonnel" name="expandingPersonnel" placeholder=""  notnull="true" readonly="readonly"  value="${contractInfo.contract.expandingPersonnel}" style="background-color: #F9F9F9">
                             <!-- <button type="button" class="btn btn-primary"  onclick="javascript:relateUser(1,null);">选择</button> -->
                             <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="centerName"><i>* </i>业绩归属中心</label>
                            <input type="text" class="form-control w300" id="centerName" name="centerName" placeholder=""  notnull="true" readonly="readonly"  value="${centerName}" style="background-color: #F9F9F9">
                             <span class="fc-warning"></span>
                        </div>
                    </li>
                 </ul>
                            
                <ul class="list-inline half form-inline">
                  
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">客户公司账号</label>
                            <input type="text" class="form-control w300" id="companyBankNo" name="companyBankNo" placeholder=""  value="${contractInfo.contract.companyBankNo}"   maxlength="50">
                        </div>
                    </li>  
                    
                </ul>
                <ul class="list-inline half form-inline">
                 	<li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">开户名</label>
                            <input type="text" class="form-control w300" id="accountName" name="accountName" placeholder="" value="${contractInfo.contract.accountName}"  maxlength="20">
                        </div>
                    </li> 
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">开户银行</label>
                            <input type="text" class="form-control w300" id="bankAccount" name="bankAccount" placeholder="" value="${contractInfo.contract.bankAccount}"  maxlength="30">
                        </div>
                    </li>
                    
                </ul>
                <ul class="list-inline half form-inline">

                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">甲方收件人</label>
                            <input type="text" class="form-control w300" id="recipients" name="recipients" placeholder=""  value="${contractInfo.contract.recipients}"  maxlength="20">
                        </div>
                    </li>
                           
                </ul>

                <ul class="list-inline form-inline pdb20">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">甲方收件地址</label>
                            <select class="form-control" title="" id="cityNo" name="cityNo">
	                            	<option value="" selected="selected">请选择</option>
	                				<c:forEach items="${citylist}" var="city">
				                            <option value="${city.cityNo}">${city.cityName}</option>
				                    </c:forEach>
                           	</select>
                            <select class="form-control" title="" id="districtNo" name="districtNo">
                            	<option value="" selected="selected">请选择行政区</option>
                				<c:forEach items="${districtList}" var="district">
			                            <option value="${district.districtNo}" >${district.districtName}</option>
			                    </c:forEach>
                            </select>
                            <input type="text" class="form-control w300" id="recipientsAddress" name="recipientsAddress" placeholder="具体地址信息"  value="${contractInfo.contract.recipientsAddress}"  maxlength="100">
                        </div>
                    </li>
                </ul>

                
                
                <p><strong>门店信息</strong></p>
                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                	<tr class="isShowClass">
		                <th style="width:80px">门店名称</th>
		                <!--<th style="width:60px">所在区域</th>-->
		                <th style="width:80px">门店地址</th>
		                <!--<th style="width:80px">创建日期</th>-->
		                <th style="width:60px">门店维护人</th>
		                <th style="width:60px">门店联系人</th>
		                <th style="width:80px">联系电话</th>
		                <th style="width:60px">门店资质等级</th>
		            </tr>
                </table>

                <ul class="list-inline form-inline pdb20">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" style="vertical-align: top;">合同备注:</label>
                            <textarea maxlength="300"  name="remark" id="remark" cols="30" rows="10"  style="word-break:break-all;word-wrap: break-word;resize: none;">${contractInfo.contract.remark}</textarea>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</form>

<!-- 营业执照 begin -->
<div class="container theme-hipage ng-scope" role="main">
	<p><strong>附件</strong></p>
</div>

<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						营业执照
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${contractInfo.fileRecordMainBusiness != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileRecordMainBusiness}" var="list" varStatus="status">
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
							    
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"   />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
							   	<%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
								<input type="hidden" name="fileTypeId" value="1" />
								<input type="hidden" name="fileSourceId" value="1" />
			                    <%--<input type="hidden" name="oaFileId" value="${oaFileId}" id="oaFileIdBusiness${status.index}"  />--%>
							  </div>
		                </c:forEach> 
		                
					</c:if>
			
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="1" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="${contractInfo.contract.companyId}">
							</a>
						</div>
					</div>
					<i class="fontset">注：营业执照须字迹清晰。</i>
				</div>
     </div>
 </div> 

<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						身份证
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${contractInfo.fileRecordMainCard != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileRecordMainCard}" var="list" varStatus="status">
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
							    
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"   />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
							   	<%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
								<input type="hidden" name="fileTypeId" value="2" />
								<input type="hidden" name="fileSourceId" value="1" />
			                    <%--<input type="hidden" name="oaFileId" value="${oaFileId}" id="oaFileIdCard${status.index}"  />--%>
							  </div>
		                </c:forEach> 
		                
					</c:if>
			
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="2" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="${contractInfo.contract.companyId}">
							</a>
						</div>
					</div>
					<i class="fontset">注：身份证正反面，照片清晰。</i>
				</div>
     </div>
 </div>
 
 
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						<i>*</i>合同照片
					</h4>
					<div class="thumb-xs-box" id="fileIdPhotoBox">
					<c:if test="${contractInfo.fileRecordMainPhoto != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileRecordMainPhoto}" var="list" varStatus="status">
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
							    
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"   />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
							   	<%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
								<input type="hidden" name="fileTypeId" value="3" />
								<input type="hidden" name="fileSourceId" value="1" />
			                    <%--<input type="hidden" name="oaFileId" value="${oaFileId}" id="oaFileIdPhoto${status.index}"  />--%>
							  </div>
		                </c:forEach> 
		                
					</c:if>
			
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="3" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="${contractInfo.contract.companyId}">
							</a>
						</div>
					</div>
					<i class="fontset">注：含合同封皮的正反面页、合同要素表页、全部附件页（附件合作确认函正文无需填写，仅在骑缝处盖章；空白附件划掉后上传）。</i>
				</div>
     </div>
 </div>
 
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						门店照片
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${contractInfo.fileRecordMainStore != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileRecordMainStore}" var="list" varStatus="status">
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
							    
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"   />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
							   	<%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
								<input type="hidden" name="fileTypeId" value="4" />
								<input type="hidden" name="fileSourceId" value="1" />
			                    <%--<input type="hidden" name="oaFileId" value="${oaFileId}" id="oaFileIdStore${status.index}"  />--%>
							  </div>
		                </c:forEach> 
		                
					</c:if>
			
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="4" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="${contractInfo.contract.companyId}">
							</a>
						</div>
					</div>
					<i class="fontset">注：含店招、门牌号（须字迹清晰可辨识）的内外景照片，乙类须有授权经理或以上入镜。</i>
				</div>
     </div>
 </div>
 
 
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						房友确认单
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${contractInfo.fileRecordMainInstall != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileRecordMainInstall}" var="list" varStatus="status">
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
							    
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"   />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
							   	<%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
								<input type="hidden" name="fileTypeId" value="5" />
								<input type="hidden" name="fileSourceId" value="1" />
			                    <%--<input type="hidden" name="oaFileId" value="${oaFileId}" id="oaFileIdInstall${status.index}"  />--%>
							  </div>
		                </c:forEach> 
		                
					</c:if>
			
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="5" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="${contractInfo.contract.companyId}">
							</a>
						</div>
					</div>
					<i class="fontset">注：房友确认单须中介盖章且与门店实际经营地址一致。</i>
				</div>
     </div>
 </div>
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						重要提示函
					</h4>
					<div class="thumb-xs-box" id="noticeBox">
					<c:if test="${contractInfo.fileRecordMainNotice != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileRecordMainNotice}" var="list" varStatus="status">
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
							    
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"   />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
							   	<%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
								<input type="hidden" name="fileTypeId" value="1020" />
								<input type="hidden" name="fileSourceId" value="1" />
			                    <%--<input type="hidden" name="oaFileId" value="${oaFileId}" id="oaFileIdInstall${status.index}"  />--%>
							  </div>
		                </c:forEach> 
		                
					</c:if>
			
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="1020" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="${contractInfo.contract.companyId}">
							</a>
						</div>
					</div>
					<i class="fontset">注：须中介签字并盖章确认。</i>
				</div>
     </div>
 </div>
 
 
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						其他
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${contractInfo.fileRecordMainOther != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileRecordMainOther}" var="list" varStatus="status">
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
							    
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple"   />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
							   	<%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
								<input type="hidden" name="fileTypeId" value="6" />
								<input type="hidden" name="fileSourceId" value="1" />
			                    <%--<input type="hidden" name="oaFileId" value="${oaFileId}" id="oaFileIdOther${status.index}"  />--%>
							  </div>
		                </c:forEach> 
		                
					</c:if>
			
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="6" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="${contractInfo.contract.companyId}">
							</a>
						</div>
					</div>
				<i class="fontset">注：乙类须提供《承诺书》及店东手持或签字时照片，乙一类另须提供核名单。</i>
				</div>
     </div>
 </div>
 
                <div class="text-center">
	         		<a href="javascript:Contract.add();" class="btn btn-primary">草签</a>
	         		<a href="${ctx}/store" class="btn btn-default mgl20">返回</a>
                  </div>


</body>

</html>
<script>
	var contractType = '${contractInfo.contract.contractType}';
	if(contractType == 10307){
		 $('#relateStoreTableId tr').find('th:eq(5)').hide();
	}
</script>
