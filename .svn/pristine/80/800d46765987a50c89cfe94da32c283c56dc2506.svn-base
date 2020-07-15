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


<script type="text/javascript" src="${ctx}/meta/js/contract/contractEdit.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/contract/contractCom.js?_v=${vs}"></script> 

<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/relate/relateUtil.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/relate/relateStores.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/relate/relateUser.js?_v=${vs}"></script>


	<script>

		$(function(){

			//console.log('${contractInfo}');
			var contractType = '${contractInfo.contract.contractType}';
			if(10301 == contractType){
				$("#supplementAgreement").show();
			}else {
				$("#supplementAgreement").hide();
			}
		});

	</script>
</head>

<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
<script>
var contractType = '${contractInfo.contract.contractType}';
</script>
<form id = "contractEditForm" >

<input type="hidden" name="_method" value="put"/>


 <input type = "hidden"  id = "id"   name = "id" value = "${contractInfo.contract.id}"/>
 <input type = "hidden"  id = "companyId"   name = "companyId" value = "${contractInfo.contract.companyId}"/>
 <input type = "hidden"  id = "contractVersion"   name = "contractVersion" value = "${contractInfo.contract.contractVersion}"/>
 <input type = "hidden"  id = "storeIdArray"   name = "storeIdArray"  />
 <input type = "hidden"  id = "oldStoreIdArray"   name = "oldStoreIdArray"  value = "${contractInfo.oldStoreIdArray}" />
 <!-- 存放经纪公司城市编码 -->
 <input type ="hidden"  id = "companyCityName"   name = "companyCityName" value = "${contractInfo.contract.companyCityName}" />
 
    <!-- 存放fangyou附件id集  old-->
    <input type="hidden" name="oldfileRecordMainIds" id="oldfileRecordMainIds"  value = "${contractInfo.fileRecordMainIds}" />

    <!-- 存放fangyou附件id集 -->
	<input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"  value = "${contractInfo.fileRecordMainIds}" />

	<%--<!-- 中介营业执照 -->--%>
	<%--<input type="hidden" name="oaFileIdBusiness" id="oaFileIdBusiness" value="${contractInfo.contract.oaFileIdBusiness}"/><!-- oa -->--%>
	<%--<!-- 中介法定代表人/负责人身份证或名片 -->--%>
	<%--<input type="hidden" name="oaFileIdCard" id="oaFileIdCard"  value="${contractInfo.contract.oaFileIdCard}"/><!-- oa -->--%>
	<%--<!-- 合作协议书 -->--%>
	<%--<input type="hidden" name="oaFileIdPhoto" id="oaFileIdPhoto"  value="${contractInfo.contract.oaFileIdPhoto}"/><!-- oa -->--%>
	<%--<!-- 中介门店照片（门店外景和室内） -->--%>
	<%--<input type="hidden" name="oaFileIdStore" id="oaFileIdStore"  value="${contractInfo.contract.oaFileIdStore}"/><!-- oa -->--%>
	 <%--<!-- 房友系统申请安装单/确认单 -->--%>
	<%--<input type="hidden" name="oaFileIdInstall" id="oaFileIdInstall"  value="${contractInfo.contract.oaFileIdInstall}"/><!-- oa -->--%>
	<%--<!-- 其他-->--%>
	<%--<input type="hidden" name="oaFileIdOther" id="oaFileIdOther"  value="${contractInfo.contract.oaFileIdOther}"/><!-- oa -->--%>
	<!-- OA审批流程类别 -->
	
 	<!--<input type="hidden" name="oaApproveType" id="oaApproveType"  value="${contractInfo.contract.oaApproveType}"/>-->
 	
 	<!-- 是否乙类转甲类-->
	<!-- <input type="hidden" name="ContractTypeB2A" id="ContractTypeB2A"  value="${contractInfo.contract.contractB2A}"/>  -->
 
 	<!-- 	开户省市 -->
	<input type="hidden" name="accountProvinceName" id="accountProvinceName"  />
	<input type="hidden" name="accountCityName" id="accountCityName"  />
 	<div class="container theme-hipage ng-scope" role="main">
           	<div class="crumbs">
				<ul>
					<li><a href="#"  class="a_hover">合同管理</a></li>
					<li><a href="#"  class="a_hover">>合同列表</a></li>
					<li><a href="#"  class="a_hover">>合同修改</a></li>
				</ul>
			</div>
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10">
                	<strong>合同修改</strong>
                	<a href="${ctx}/contract?searchParam=1" class="btn btn-primary">返回</a>
                </h4>
                <p><strong>基本信息</strong></p>
                
                <table class="table-sammary">
                    <col style="width:125px;">
                    <col style="width:auto;">
                    <col style="width:125px;">
                    <col style="width:auto;">
                     <tr>
                     	<c:if test="${contractInfo.contract.originalContractdistinction eq 18602}">
                        <td class="talabel">原合同编号 :</td>
                        <td colspan='3'>
                            <input type="hidden" class="form-control w300" id="originalContractNo" name="originalContractNo" placeholder="" value="${contractInfo.contract.originalContractNo}"  readonly="readonly">
                        	<label  >${contractInfo.contract.originalContractNo}</label>
                   		</td>
                   		</c:if>	 
                 	</tr>
                    <tr>
                        <td class="talabel">甲方名称</td>
                        <td><input type="text" class="form-control w300" id="partyB" name="partyB" placeholder="甲方名称" value="${contractInfo.contract.partyB}"  readonly="readonly" style="background-color: #F2F2F2"></td>
                        <td class="talabel required">法定代表/负责人</td>
                        <td><input type="text" class="form-control w300" id="lealPerson" name="lealPerson" placeholder="请输入"  notnull="true"   value="${contractInfo.contract.lealPerson}"  maxlength="20" readonly="readonly" style="background-color: #F2F2F2"><span class="fc-warning"></span></td>
                    </tr>
                    <tr>
                        <td class="talabel required">公司注册地址</td>
                        <td colspan="3">
                            <div class="input-grounp">
                                <input type="hidden" class="form-control w120" id="partyBcityNo" name="partyBcityNo" value="${contractInfo.contract.partyBcityNo}">
                                <input type="hidden" class="form-control w120" id="partyBdistrictNo" name="partyBdistrictNo" value="${contractInfo.contract.partyBdistrictNo}">
                                <input type="text" class="form-control w120" id="partyBCityName" name="partyBCityName"  value="${contractInfo.contract.partyBCityName}" notnull="true"  readonly="readonly" style="background-color: #F2F2F2">
                                <input type="text" class="form-control w120" id="partyBDistrictName" name="partyBDistrictName"  value="${contractInfo.contract.partyBDistrictName}" notnull="true"  readonly="readonly" style="background-color: #F2F2F2">
                                <input type="text" class="form-control w300" id="partyBAddress" name="partyBAddress" placeholder="公司地址"  notnull="true"  value="${contractInfo.contract.partyBAddress}"  maxlength="100" readonly="readonly" style="background-color: #F2F2F2">
                                <span class="fc-warning"></span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">统一社会信用代码</td>
                        <td><input type="text" class="form-control w300" id="registrId" name="registrId" placeholder=""  notnull="true"  maxlength="30" value="${contractInfo.contract.registrId}" readonly="readonly" style="background-color: #F2F2F2"><span class="fc-warning"></span></td>
                        <td class="talabel required">协议书编号</td>
                        <td><input type="text" class="form-control w300" id="agreementNo" name="agreementNo" placeholder=""  notnull="true"  value="${contractInfo.contract.agreementNo}"  maxlength="20"><span class="fc-warning"></span>
                        	<input type="hidden" id="contractNo" name="contractNo" value="${contractInfo.contract.contractNo}">
                        </td>
                        
                        <%-- <td class="talabel">合同编号</td>
                        <td><input type="text" class="form-control w300" id="contractNo" name="contractNo" placeholder="" value="${contractInfo.contract.contractNo}"  readonly="readonly" style="background-color: #F2F2F2"></td>
 --%>               </tr>
 					
 					<tr>
                        <td class="talabel required">合作模式</td>
                        <td>
                            <input type="hidden" id="contractType" name="contractType" value="${contractInfo.contract.contractType}" >
<%-- 	                        <input type="radio" value="10301" id="10301" name="contractType"  <c:if test="${contractInfo.contract.contractType eq  10301}">checked</c:if> disabled style='vertical-align:-3px' > --%>
<!-- 	                        <label for="10301" class="fon-normal small">A</label> -->
	                        <input type="radio" value="10307" id="10307" name="contractType"  <c:if test="${contractInfo.contract.contractType eq  10307}">checked</c:if> disabled style='vertical-align:-3px' >
	                        <label for="10307" class="fon-normal small">授牌</label>
	                        <input type="radio" value="10302" id="10302" name="contractType"  <c:if test="${contractInfo.contract.contractType eq  10302}">checked</c:if> disabled style='vertical-align:-3px' >
	                        <label for="10302" class="fon-normal small">B</label>
                            <%-- <c:forEach items="${contractTypeList}" var="contractTypeList">
                            	<c:if test="${contractTypeList.dicCode == 10301 || contractTypeList.dicCode == 10302 || contractTypeList.dicCode == 10307}">
	                        	<label for="${contractTypeList.dicCode}" class="fon-normal small">${contractTypeList.dicValue}</label>
	                        	</c:if>
 							</c:forEach> --%>
                        </td>
                    </tr>
                     <!--Add 2017/4/6 cning start  -->
	                <tr id="agreementTypeId">
	                	<td class="talabel required">合作协议书类型</td> 
	                	<td colspan="3">
	                	<c:if test="${contractInfo.contract.originalContractdistinction eq 18602 }">
                			<%-- <input type="hidden" id="agreementType" name="agreementType" value="${contractInfo.contract.agreementType}" > --%>
                			<c:forEach items="${agreementTypeList}" var="agreementTypeList">
	                        	<c:if test="${agreementTypeList.dicCode != 11001 && agreementTypeList.dicCode != 11006}">
		                			<input type="radio" value="${agreementTypeList.dicCode}" id="${agreementTypeList.dicCode}" name="agreementType"  <c:if test="${contractInfo.contract.agreementType eq  agreementTypeList.dicCode}">checked</c:if>>
									<label for="${agreementTypeList.dicCode}" class="fon-normal small">${agreementTypeList.dicValue}</label>
								</c:if>
							</c:forEach>  
						</c:if>
						<c:if test="${contractInfo.contract.originalContractdistinction eq 18601 }">
                			<%-- <input type="hidden" id="agreementType" name="agreementType" value="${contractInfo.contract.agreementType}" > --%>
                			<c:forEach items="${agreementTypeList}" var="agreementTypeList">
	       						 <c:if test="${agreementTypeList.dicCode != 11003 && agreementTypeList.dicCode != 11001 && agreementTypeList.dicCode != 11006}">
		                	        <input type="radio" value="${agreementTypeList.dicCode}" id="${agreementTypeList.dicCode}" name="agreementType"  <c:if test="${contractInfo.contract.agreementType eq  agreementTypeList.dicCode}">checked</c:if>>
									<label for="${agreementTypeList.dicCode}" class="fon-normal small">${agreementTypeList.dicValue}</label>
								 </c:if>
							</c:forEach>  
						</c:if>
	                	</td>
	                </tr>
                <!--Add 2017/4/6 cning end  -->
                
                   	<tr id="OAapproval">
                   		<td class="talabel required">OA审批流程类别</td>
                           <td>
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
                           </td>
                   	</tr>  
                   	<tr id="shoupaiTypeLi" > 
                		<td class="talabel required">授牌类型</td>
                		<td>
                			<c:forEach items="${shoupaiTypeList}" var="shoupaiTypeList">
<%--                         		<input type="radio" value="${shoupaiTypeList.dicCode}" id="${shoupaiTypeList.dicCode}" onchange="Contract.changeShoupaiType(this);" name="shoupaiType" style="vertical-align: -2px; margin-right: 3px;"><label for="${shoupaiTypeList.dicCode}" class="fon-normal small" style="margin-right:5px;">${shoupaiTypeList.dicValue}</label> --%>
                        		<c:choose>
                                      <c:when test="${contractInfo.contract.shoupaiType eq shoupaiTypeList.dicCode}">
                                           <input type="radio" value="${shoupaiTypeList.dicCode}" id="${shoupaiTypeList.dicCode}" name="shoupaiType" onchange="Contract.changeShoupaiType(this);" checked="checked" style="vertical-align: -2px; margin-right:3px;"><label for="${shoupaiTypeList.dicCode}" class="fon-normal small" style="margin-right:5px;">${shoupaiTypeList.dicValue}</label>
                                      </c:when>
                                      <c:otherwise>
                                           <input type="radio" value="${shoupaiTypeList.dicCode}" id="${shoupaiTypeList.dicCode}" name="shoupaiType" onchange="Contract.changeShoupaiType(this);" style="vertical-align: -2px; margin-right:3px;"><label for="${shoupaiTypeList.dicCode}" class="fon-normal small" style="margin-right:5px;">${shoupaiTypeList.dicValue}</label>
                                      </c:otherwise>
                                   </c:choose>
                        	</c:forEach>
                		</td>
                	</tr>
                    <tr>
                    	<c:if test="${contractInfo.contract.contractType eq  10302}">
	                        <td class="talabel required">合同生效日期</td>
	                        <!-- onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"  -->
	                        <td><input  type="text" class="calendar-icon  form-control w300" name="dateLifeStart" id="dateLifeStart"  value="${sdk:ymd2(contractInfo.contract.dateLifeStart)}" 
	                        onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'dateLifeEnd\',{d:-364});}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
	                        class="ipttext Wdate" notnull="true"/><span class="fc-warning"></span></td>
	                        <td class="talabel required">合同到期日期</td>
	                        <td>
		                        <!-- onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"  -->
		                        <input  type="text" class="calendar-icon form-control w300" name="dateLifeEnd" id="dateLifeEnd" value="${sdk:ymd2(contractInfo.contract.dateLifeEnd)}"  
		                        onFocus="WdatePicker({minDate:'#F{$dp.$D(\'dateLifeStart\',{d:364});}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" 	
		                        class="ipttext Wdate" notnull="true"/>
		                        <span class="fc-warning"></span>
	                        </td>
                    	</c:if>
                    	<c:if test="${contractInfo.contract.contractType eq  10307}">
                    		<td class="talabel required">合同生效日期</td>
	                        <!-- onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"  -->
	                        <td><input  type="text" class="calendar-icon  form-control w300" name="dateLifeStart" id="dateLifeStart"  value="${sdk:ymd2(contractInfo.contract.dateLifeStart)}" 
	                        onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'dateLifeEnd\',{d:-1});}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
	                        class="ipttext Wdate" notnull="true"/><span class="fc-warning"></span></td>
	                        <td class="talabel required">合同到期日期</td>
	                        <td>
		                        <!-- onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"  -->
		                        <input  type="text" class="calendar-icon form-control w300" name="dateLifeEnd" id="dateLifeEnd" value="${sdk:ymd2(contractInfo.contract.dateLifeEnd)}"  
		                        onFocus="WdatePicker({minDate:'#F{$dp.$D(\'dateLifeStart\',{d:1});}',isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" 	
		                        class="ipttext Wdate" notnull="true"/>
		                        <span class="fc-warning"></span>
	                        </td>
                    	</c:if>
                    </tr>
                    <tr>
                        <td class="talabel">甲方授权代表</td>
                        <td><input type="text" class="form-control w300" id="authRepresentative" name="authRepresentative" placeholder=""  value="${contractInfo.contract.authRepresentative}" maxlength="20"></td>
                          <td class="talabel required">联系方式</td>
                        <td><input type="text" class="form-control w300" id="agentContact" name="agentContact" notnull="true" value="${contractInfo.contract.agentContact}" maxlength="11"><span class="fc-warning"></span></td>
                    </tr>
                    <tr>
                        <td class="talabel required">每店保证金额</td>
                        <td><input type="text" class="form-control w300" id="depositFee" name="depositFee" placeholder=""  notnull="true"  onblur="calculateTotleDepositFee(3)" value="${contractInfo.contract.depositFee}"  datatype="needInteger"  maxlength="9" >
                        <!-- <span class="fc-warning"></span> --><span style="color:#FF0000;" id="errorTip"> &nbsp;请输入整数!</span></td>
                        <td class="talabel required">合作门店数</td>
                        <td><input type="text" class="form-control w300" id="storeNum" name="storeNum" placeholder=""  notnull="true"  value="${contractInfo.contract.storeNum}"  datatype="posNumWithOutZero"  readonly="readonly" maxlength="9" style="background-color: #F2F2F2">
                        <span class="fc-warning"></span></td>
                    </tr>
                    <tr>
                        <td class="talabel required">总保证金</td>
                        <td><input type="text" class="form-control w300" id="totleDepositFee" name="totleDepositFee" placeholder=""  notnull="true"  value="${contractInfo.contract.totleDepositFee}"
                        datatype="needInteger"  maxlength="9"  readonly="readonly" style="background-color: #F2F2F2">
                        <span class="fc-warning"></span></td>
                        <td class="talabel required">违约金金额</td>
                        <td><input type="text" class="form-control w300" id="penaltyFee" name="penaltyFee" placeholder=""  notnull="true"  value="${contractInfo.contract.penaltyFee}"  datatype="needInteger"  maxlength="9" onblur="listeningpenaltyFee()">
                        <!-- <span class="fc-warning"></span> -->
                        <span style="color:#FF0000;" id="errorTip2">  &nbsp;请输入整数!</span>
                        </td>
                    </tr>
                    
                    <tr>
                     	<td class="talabel" id="accountNameLabel">开户名</td>
                        <td><input type="text" class="form-control w300" id="accountName" name="accountName" placeholder="" value="${contractInfo.contract.accountName}"  maxlength="30"></td>
                        <td class="talabel "  id="accountProvinceNoLabel">开户省市</td>
                		<td >
                			<div class="control-group">
                				<span class="control-select">
                					<select class="form-control w120" title="" id="accountProvinceNo" name="accountProvinceNo" >
			                              <option value="" selected="selected">请选择省</option>
		                				  <c:forEach items="${provinceList}" var="city">
					                            <option value="${city.provinceNo}" <c:if test="${city.provinceNo eq contractInfo.contract.accountProvinceNo}">selected="selected"</c:if>>${city.provinceName}</option>
					                      </c:forEach>
		                            </select>
                				</span>
                				<span class="control-select">
                					<select class="form-control w180" Style="width:177px;" id="accountCityNo" name="accountCityNo">
		                            	<option value="" selected="selected">请选择城市</option>
		                            	<c:if test="${contractInfo.accountCityList != null }">
			                				<c:forEach items="${contractInfo.accountCityList}" var="city">
						                        <option value="${city.cityNo}" <c:if test="${contractInfo.contract.accountCityNo eq city.cityNo}">selected="selected"</c:if>>${city.cityName}</option>
						                    </c:forEach>
					                    </c:if>	
		                            </select>
                				</span>
                			</div>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel" id="bankAccountLabel">开户行</td>
                        <td><input type="text" class="form-control w300" id="bankAccount" name="bankAccount" placeholder="" value="${contractInfo.contract.bankAccount}"  maxlength="30"></td>
                        <td class="talabel" id="companyBankNoLabel">银行账号</td>
                        <td><input type="text" class="form-control w300" id="companyBankNo" name="companyBankNo" oninput="this.value=this.value.replace(/[^0-9a-zA-Z/-]+/g,'')" placeholder=""  value="${contractInfo.contract.companyBankNo}"   maxlength="50"></td>
                    </tr>
                   <tr>
                        <td class="talabel required">业绩归属拓展人</td>
                        <td>
                            <input type="hidden" id="expandingPersonnelId" name="expandingPersonnelId" value="${contractInfo.contract.expandingPersonnelId}" >
                            <input type="text" class="form-control w300" id="expandingPersonnel" name="expandingPersonnel" placeholder=""  notnull="true" readonly="readonly"  value="${contractInfo.contract.expandingPersonnel}" style="background-color: #F2F2F2">
                             <span class="fc-warning"></span>
                        </td>                       
                        <td class="talabel required">业绩归属中心</td>
                        <td>
                            <input type="text" class="form-control w300" id="centerName" name="centerName" placeholder=""  notnull="true" readonly="readonly"  value="${contractInfo.contract.centerName}" style="background-color: #F2F2F2">
                            <span class="fc-warning"></span>
                        </td>  
                    </tr>
                    <tr>
                    	<td class="talabel">甲方收件人</td>
                        <td><input type="text" class="form-control w300" id="recipients" name="recipients" placeholder=""  value="${contractInfo.contract.recipients}"  maxlength="20"></td>
                    </tr>
                    <tr>
                        <td class="talabel">甲方收件地址</td>
                        <td colspan="3">
                            <div class="control-group">
                                <span class="control-select">
                                    <select class="form-control w120" title="" id="cityNo" name="cityNo">
                                        <option value="" selected="selected">请选择</option>
		                				<c:forEach items="${citylist}" var="city">
					                            <option value="${city.cityNo}" <c:if test="${city.cityNo eq contractInfo.contract.cityNo}">selected="selected"</c:if>>${city.cityName}</option>
					                    </c:forEach>
                                    </select>
                                </span>
                                <span class="control-select">
                                    <select class="form-control w120" title="" id="districtNo" name="districtNo">
                                        <option value="" selected="selected">请选择行政区</option>
		                				<c:forEach items="${districtList}" var="district">
					                            <option value="${district.districtNo}" <c:if test="${contractInfo.contract.districtNo eq district.districtNo}">selected="selected"</c:if>>${district.districtName}</option>
					                    </c:forEach>
                                    </select>
                                </span>
                                <input type="text" class="form-control w300" id="recipientsAddress" name="recipientsAddress" placeholder="具体地址信息"  value="${contractInfo.contract.recipientsAddress}"  maxlength="100">
                            </div>
                        </td>
                    </tr>
                    
                  <!--Add 2017/6/12   -->
                   <%--  <tr id="B2Achange">
                		<td class="talabel required" >是否乙类转甲类</td>
                		<td colspan="2">
                             <c:forEach items="${ContractTypeB2AList}" var="ContractTypeB2AList">
	                        		<input type="radio" value="${ContractTypeB2AList.dicCode}" id="${ContractTypeB2AList.dicCode}" name="ContractTypeB2A"  
	                        		<c:if test="${contractInfo.contract.contractB2A eq  ContractTypeB2AList.dicCode}">checked</c:if>  style='vertical-align:-3px' >
	                        		<label for="${ContractTypeB2AList.dicCode}" class="fon-normal small">${ContractTypeB2AList.dicValue}</label>
 							  </c:forEach>
                		</td>
                	</tr> --%>
                	<input type="hidden" name="ContractTypeB2A" id="20202" value="20202"/>
                </table>
                <p><strong>门店信息</strong></p>
                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                	<tr>
                		<th>门店编号</th>
		                <th>门店名称</th>
		                <th>所在区域</th>
		                <th>门店地址</th>
		                <!--<th>创建日期</th>-->
		                <th id="storeGrade">门店资质等级</th>
		                <!-- <th>操作</th> -->
		            </tr>
		            <c:forEach items="${contractInfo.storeDetails}" var="storelist">
	                   	<tr>
	                   		<td>${storelist.storeNo}</td>
	                   		<td>${storelist.name}</td>
	                   		<td>${storelist.districtName}</td>
	                   		<td>${storelist.addressDetailContract}</td>
	                   		<td style='display:none'>
								<c:if test="${empty storelist.abtypeStore || storelist.abtypeStore eq 0}"> -- </c:if>
								<c:if test="${storelist.abtypeStore eq 19901}">甲类</c:if>
								<c:if test="${storelist.abtypeStore eq 19902 }">乙类 (${storelist.btypeStoreName})</c:if>
	                   		</td>
	                   		<td class="storeGradedata">
								 <select  id="storetype${storelist.storeId}" name="storetype${storelist.storeId}" style='width: 70px;'  onchange='storetypeChange(this)'>
									<c:choose>
										<c:when test="${contractInfo.contract.originalContractdistinction eq 18602 && storelist.abtypeStore eq 19901}">
											<option value="19901" <c:if test="${storelist.abtypeStore eq 19901}">selected</c:if>>甲类</option>
										</c:when>
										<c:when test="${contractInfo.contract.originalContractdistinction eq 18602 && storelist.abtypeStore eq 19902}">
											<option value="19902" <c:if test="${storelist.abtypeStore eq 19902}">selected</c:if>>乙类</option>
										</c:when>
										<c:otherwise>
											<option value="">请选择</option>
											<option value="19901" <c:if test="${storelist.abtypeStore eq 19901}">selected</c:if>>甲类</option>
											<option value="19902" <c:if test="${storelist.abtypeStore eq 19902}">selected</c:if>>乙类</option>
										</c:otherwise>
									</c:choose>
								</select>
								<input type="text" id="bTypenamelst${storelist.storeId}" readonly style='width: 70px;<c:if test="${storelist.abtypeStore ne 19902}">display:none;</c:if>' name="bTypenamelst${storelist.storeId}" value="${fn:replace(storelist.btypeStoreName,',',';')}">
	                   		</td>
	                   		
	                   		<td style='display:none'>
								<input name='storetype${storelist.storeId}'  id='storetype${storelist.storeId}'   type='hidden' value='${storelist.abtypeStore}'>
								<input name='storeIds'  id='storeIds${storelist.storeId}'   type='hidden' value='${storelist.storeId}'>
								<input type="hidden" id="storetypeBlst${storelist.storeId}" name="storetypeBlst${storelist.storeId}" value="${fn:replace(storelist.btypeStore,',',';')}">
							</td>
	                   	</tr>
					</c:forEach> 
                </table>
                <table class="table-sammary">
                    <col style="width:125px;">
                    <col style="width:auto;">
                    <tr>
                        <td class="talabel">合同备注:</td>
                        <td>
                            <textarea class="form-control" maxlength="300"  name="remark" id="remark" cols="30" rows="10"  style="word-break:break-all;word-wrap: break-word;resize: none;">${contractInfo.contract.remark}</textarea>
                        </td>
                    </tr>
                </table>
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
										   				<img alt="营业执照" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
								   		   	<span class="thumb-bottom-roup">
								   		   		<i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
								   		   	</span>
							   		   </a>
							    
								<input type="hidden" name="limitSize" value="10">
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
							   	<%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
								<input type="hidden" name="fileTypeId" value="1" />
								<input type="hidden" name="fileSourceId" value="1" />
			                    <%--<input type="hidden" name="oaFileId" value="${oaFileId}" id="oaFileIdBusiness${status.index}"  />--%>
							  </div>
		                </c:forEach> 
		                
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${contractInfo.fileRecordMainBusiness != null && contractInfo.fileRecordMainBusiness.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
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
							    
								<input type="hidden" name="limitSize" value="10">
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
							   	<%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
								<input type="hidden" name="fileTypeId" value="2" />
								<input type="hidden" name="fileSourceId" value="1" />
			                    <%--<input type="hidden" name="oaFileId" value="${oaFileId}" id="oaFileIdCard${status.index}"  />--%>
							  </div>
		                </c:forEach> 
		                
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${contractInfo.fileRecordMainCard != null && contractInfo.fileRecordMainCard.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
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
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}" >
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
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
							   	<%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
								<input type="hidden" name="fileTypeId" value="3" />
								<input type="hidden" name="fileSourceId" value="1" />
			                    <%--<input type="hidden" name="oaFileId" value="${oaFileId}" id="oaFileIdPhoto${status.index}"  />--%>
							  </div>
		                </c:forEach> 
		                
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${contractInfo.fileRecordMainPhoto != null && contractInfo.fileRecordMainPhoto.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
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
										   				<img alt="门店照片" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
								   		   	<span class="thumb-bottom-roup">
								   		   		<i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
								   		   	</span>
							   		   </a>
							    
								<input type="hidden" name="limitSize" value="10">
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
							   	<%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
								<input type="hidden" name="fileTypeId" value="4" />
								<input type="hidden" name="fileSourceId" value="1" />
			                    <%--<input type="hidden" name="oaFileId" value="${oaFileId}" id="oaFileIdStore${status.index}"  />--%>
							  </div>
		                </c:forEach> 
		                
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${contractInfo.fileRecordMainStore != null && contractInfo.fileRecordMainStore.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
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
							    
								<input type="hidden" name="limitSize" value="10">
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
							   	<%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
								<input type="hidden" name="fileTypeId" value="5" />
								<input type="hidden" name="fileSourceId" value="1" />
			                    <%--<input type="hidden" name="oaFileId" value="${oaFileId}" id="oaFileIdInstall${status.index}"  />--%>
							  </div>
		                </c:forEach> 
		                
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${contractInfo.fileRecordMainInstall != null && contractInfo.fileRecordMainInstall.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
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
							    
								<input type="hidden" name="limitSize" value="10">
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
							   	<%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
								<input type="hidden" name="fileTypeId" value="1020" />
								<input type="hidden" name="fileSourceId" value="1" />
			                    <%--<input type="hidden" name="oaFileId" value="${oaFileId}" id="oaFileIdInstall${status.index}"  />--%>
							  </div>
		                </c:forEach> 
		                
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${contractInfo.fileRecordMainNotice != null && contractInfo.fileRecordMainNotice.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
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
 
<div class="container theme-hipage ng-scope" role="main" id="supplementAgreement">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						补充协议
					</h4>
					<div class="thumb-xs-box" id="noticeBox">
					<c:if test="${contractInfo.fileRecordMainComplement != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileRecordMainComplement}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="补充协议" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
								   		   	<span class="thumb-bottom-roup">
								   		   		<i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
								   		   	</span>
							   		   </a>
							    
								<input type="hidden" name="limitSize" value="10">
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
								<input type="hidden" name="fileTypeId" value="1026" />
								<input type="hidden" name="fileSourceId" value="1" />
							  </div>
		                </c:forEach> 
		                
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${contractInfo.fileRecordMainComplement != null && contractInfo.fileRecordMainComplement.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
				                <input type="hidden" name="fileTypeId" value="1026" />
				                <input type="hidden" name="fileSourceId" value="1" />
				                <input type="hidden" name ="companyId" value="${contractInfo.contract.companyId}">
							</a>
						</div>
					</div>
					<i class="fontset">注：需双方均已盖章。</i>
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
							    
								<input type="hidden" name="limitSize" value="10">
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
							   	<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
							   	<%-- <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}"> --%>
								<input type="hidden" name="fileTypeId" value="6" />
								<input type="hidden" name="fileSourceId" value="1" />
			                    <%--<input type="hidden" name="oaFileId" value="${oaFileId}" id="oaFileIdOther${status.index}"  />--%>
							  </div>
		                </c:forEach> 
		                
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${contractInfo.fileRecordMainOther != null && contractInfo.fileRecordMainOther.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
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
	         		<a href="javascript:Contract.update();" class="btn btn-primary">保存</a>
	         		<a href="${ctx}/contract?searchParam=1" class="btn btn-primary mgl20">返回</a>
                  </div>


</body>

</html>
