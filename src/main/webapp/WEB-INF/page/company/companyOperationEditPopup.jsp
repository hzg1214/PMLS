<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/company/companyOperationEdit.js?_v=${vs}"></script>

<style>
.half li {
	width:85%
}
.w200{
	width:200px!important;
	margin-right:20px
}

#divs{
	margin:0 auto;
}
</style>

<form id = "companyEditForm" >
    <!-- 存放fangyou附件id集 -->
	<input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"  value = "${info.fileRecordMainIds}" />
                
 <input type = "hidden"  id = "companyId"   name = "companyId" value = "${info.id}"/>
 <input type = "hidden"  id = "companyNo"   name = "companyNo" value = "${info.companyNo}"/>
 <input type = "hidden"  id = "companyName"   name = "companyName" value = "${info.companyName}"/>
 <input type = "hidden"  id = "address"   name = "address" value = "${info.address}"/>
 <input type = "hidden"  id = "businessLicenseNo"   name = "businessLicenseNo" value = "${info.businessLicenseNo}"/>
 <input type = "hidden"  id = "legalPerson"   name = "legalPerson" value = "${info.legalPerson}"/>
 <input type = "hidden"  id = "oldCityNo"   name = "oldCityNo" value = "${info.cityNo}"/>
 <input type = "hidden"  id = "oldDistrictNo"   name = "oldDistrictNo" value = "${info.districtNo}"/>
 <input type="hidden" id="oldContactNumber" name="oldContactNumber" value="${info.contactNumber}">
 <input type="hidden" id="flag" value="${flag}">
<div class="container theme-hipage ng-scope" role="main" id="divs">

	<span class="" style="float:right"><a href="javascript:Company.close();" class="btn btn-default">&times;</a></span>

        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>运营维护公司信息</strong></h4>
                <ul class="list-inline half form-inline">
                    <li style="margin-left: 50px;color: red;font-size:12px;">
                        <c:if test="${flag eq 300}">
                            注：该公司有审批中或审批通过的合同，公司名称、注册地址和统一社会信用代码不允许修改。
                        </c:if>
                    </li>
                    <li>
                        <div class="form-group">
                             <label class="fw-normal w200 text-right"><i>*</i>修改项目  ：</label>
                            <span class="fc-warning" id="itemWarning" style="float: right;"></span>
                            <div style="float: right">
                                <input type="checkbox" id="chb5" name="chb5" value="1">法定代表人电话
                            </div>
                            <div id="chbMore" style="float: right">
                                <input type="checkbox" id="chb1" name="chb1" value="1">公司名称 &nbsp;&nbsp;
                                <input type="checkbox" id="chb2" name="chb2" value="1">公司注册地址&nbsp;&nbsp;
                                <input type="checkbox" id="chb3" name="chb3" value="1">统一社会信用代码&nbsp;&nbsp;
                                <input type="checkbox" id="chb4" name="chb4" value="1">法定代表人&nbsp;&nbsp;
                            </div>
                        </div>
                    </li> 
                </ul>
                <ul class="list-inline half form-inline" id="oldUlName">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right">原公司名称  ：</label>
                             	${info.companyName}
                        </div>
                    </li>
                </ul>
              
                <ul class="list-inline half form-inline" id="ulName">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right"><i>*</i>修改后公司名称  ：</label>
                            <input type="text" class="form-control w300" id="newCompanyName" name="newCompanyName" placeholder="" notnull="true" autocomplete="off"  maxlength="200">
                            <span class="fc-warning" id="companyNameWarning"></span>
                        </div>
                    </li>
                </ul>
                
                <ul class="list-inline half form-inline" id="oldUlAddress">
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right">原公司注册地址  ：</label>
                             <input type="hidden" id="oldCityName" name="oldCityName" value="${info.cityName} ">
                             <input type="hidden" id="oldDistrictName" name="oldDistrictName" value="${info.districtName }">
                              ${info.cityName}${info.districtName }${info.address}
                        </div>
                    </li> 
                   
                </ul>
                <ul class="list-inline half form-inline" id="ulAddress">
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right"><i>*</i>修改后公司注册地址  ：</label>

                                   <span class="control-select">
                						<select class="form-control" title="" id="newCityNo" name="newCityNo" readonly>
		                					    <c:forEach items="${citylist}" var="city">
					                            <option value="${city.cityNo}" <c:if test="${city.cityNo eq userInfo.cityNo}">selected</c:if>>${city.cityName}</option>
					                            </c:forEach>
		                            	</select>
                					</span>
                					<span class="control-select">
                						<select class="form-control" title="" id="newDistrictNo" name="newDistrictNo">
			                            	 <c:if test="${!empty companyDistrictList}">
				                                <c:forEach items="${companyDistrictList}" var="district">
				                                    <option value="${district.districtNo}">${district.districtName}</option>
				                                </c:forEach>
			                            	</c:if>
			                            </select>
                					</span>
		                            <input type="text" class="form-control w300" name="newAddress" id="newAddress" placeholder="具体地址信息" notnull="true"  value="" maxlength="100">
                            <span class="fc-warning" id="addressWarning"></span>
                        </div>
                    </li> 
                   
                </ul>
                <ul class="list-inline half form-inline" id="oldUlBusinessLicenseNo">
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right">原统一社会信用代码  ：</label>
                            ${info.businessLicenseNo}
                        </div>
                    </li> 
                   
                </ul>
                <ul class="list-inline half form-inline" id="ulBusinessLicenseNo">
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right"><i>*</i>修改后统一社会信用代码  ：</label>
                            <input type="text" class="form-control w300" id="newBusinessLicenseNo" name="newBusinessLicenseNo" placeholder="" notnull="true" autocomplete="off"  maxlength="18">
                            <span class="fc-warning" id="busWarning"></span>
                        </div>
                    </li> 
                </ul>
                 <ul class="list-inline half form-inline" id="oldUlLegalPerson">
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right">原法定代表人  ：</label>
                            ${info.legalPerson}
                        </div>
                    </li> 
                   
                </ul>
                <ul class="list-inline half form-inline" id="ulLegalPerson">
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right"><i>*</i>修改后法定代表人  ：</label>
                            <input type="text" class="form-control w300" id="newLegalPerson" name="newLegalPerson" placeholder="" notnull="true" autocomplete="off"  maxlength="40">
                            <span class="fc-warning" id="personWarning"></span>
                        </div>
                    </li>                    
                </ul>
                <ul class="list-inline half form-inline" style="display: none;" id="oldUlContactNumber">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right">原法定代表人电话  ：</label>
                            ${info.contactNumber}
                        </div>
                    </li>

                </ul>
                <ul class="list-inline half form-inline" style="display: none;" id="ulContactNumber">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right"><i>*</i>修改后法定代表人电话  ：</label>
                            <input type="text" class="form-control w300" id="contactNumber" name="contactNumber" placeholder="" notnull="true" autocomplete="off"  maxlength="40">
                            <span class="fc-warning" id="sContactNumber"></span>
                        </div>
                    </li>
                </ul>

                <div id="changeContractDiv" style="display: none;">
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group" style="margin-left: -10px;">
                                <label class="fw-normal w200 text-right">是否修改合同信息:</label>
                                <span style="padding-left:10px;">
					              		<input type="radio" name="modContract"  class="check"
                                               value="1" onclick="changeContractUlChange(this)"  checked/>是
					         	</span>
                                <span style="margin-left: 35px;">
						        		<input type="radio" name="modContract" class="check"
                                               value="0" onclick="changeContractUlChange(this)"  />否
						      </span>
                            </div>
                        </li>
                    </ul>
                    <c:if test="${contractList ne null}">
                        <ul id="changeContractUl" class="list-inline form-inline" style="display: block;">
                            <li>
                                <div class="form-group" style="margin-left: -10px;">
                                    <label class="fw-normal w200 text-right">拓店合同编号:</label>
                                    <c:forEach items="${contractList}" var="contract">
                                        <span style="padding-left:10px;">
                                            <input type="hidden" name="contractNo" value="${contract.contractNo}">
                                            <input type="checkbox" name="contractId" class="check" value="${contract.id}"/>&nbsp;&nbsp;${contract.contractNo}
                                        </span>
                                    </c:forEach>
                                </div>
                            </li>
                        </ul>
                    </c:if>
                    <c:if test="${frameContractList ne null}">
                        <ul id="changeContractUl2" class="list-inline form-inline" style="display: block;">
                            <li>
                                <div class="form-group" style="margin-left: -10px;">
                                    <label class="fw-normal w200 text-right">联动框架协议编号:</label>
                                    <c:forEach items="${frameContractList}" var="frameContract">
                                        <span style="padding-left:10px;">
                                            <input type="hidden" name="frameContractNo" value="${frameContract.contractNo}">
                                            <input type="checkbox" name="frameContractId" class="check" value="${frameContract.id}"/>&nbsp;&nbsp;${frameContract.contractNo}
                                        </span>
                                    </c:forEach>
                                </div>
                            </li>
                        </ul>
                    </c:if>
                    <c:if test="${gpContractList ne null}">
                        <ul id="changeContractUl3" class="list-inline form-inline" style="display: block;">
                            <li>
                                <div class="form-group" style="margin-left: -10px;">
                                    <label class="fw-normal w200 text-right">公盘合同编号:</label>
                                    <c:forEach items="${gpContractList}" var="gpContract">
                                        <span style="padding-left:10px;">
                                            <input type="hidden" name="gpContract" value="${gpContract.gpContractNo}">
                                            <input type="checkbox" name="gpContractId" class="check" value="${gpContract.id}"/>&nbsp;&nbsp;${gpContract.gpContractNo}
                                        </span>
                                    </c:forEach>
                                </div>
                            </li>
                        </ul>
                    </c:if>
                </div>

<c:if test="${flag ne 300}">
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						<i id="fileMustRemain" style="display: none;">*</i>附件(营业执照) ：<span class="fc-warning" id="businessWarning" ></span>
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
						<div class="thumb-xs-list item-photo-add" >
							<%-- <c:if test="${info.fileRecordMainBusiness != null && info.fileRecordMainBusiness.size()>=10  }" style="display: none;"> </c:if>> --%>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
				                <input type="hidden" name="fileTypeId" value="1" />
				                <input type="hidden" name="fileSourceId" value="3" />
				                <input type="hidden" name ="companyId" value="${info.companyId}"> 
							</a>
						</div>
					</div>
				</div>
     </div>
 </div>
</c:if>

            </div>
        </div>
    </div>
    
</form>