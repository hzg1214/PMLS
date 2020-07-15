<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
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
				<div class="page-content ul-line-block" name="Viewerbox">
				<br>
					<h4>
						<strong>合同变更详情</strong>
						<a href="${ctx}/contractChangeNew/list/${contractId}/${contractStatus}" class="btn btn-primary">返回</a>
						<shiro:hasPermission name="/ct:OPERATE_AUDIT">
							<c:if test="${contractChange.approveState eq 1}">
								<a href="javascript:void(0)" onclick="operateAuditCt('${contractChange.id}','${contractId}','${contractStatus}');" id="operateAuditCt" class="btn btn-primary" style="margin-right:10px;">运营维护审核状态</a>
							</c:if>
						</shiro:hasPermission>
					</h4>
					<ul class="list-inline half form-inline">
						<li>
							<div class="form-group">
								<label class="fw-normal w140  text-right" style="vertical-align: top;float:left">合同变更申请编号：</label>
								${contractChange.contractStopNo}
							</div>
						</li>
					</ul>
					<ul class="list-inline half form-inline">
						<li>
							<div class="form-group">
								<label class="fw-normal w140  text-right" style="vertical-align: top;float:left">变更合同编号：</label>
								${contractChange.contractNo}
							</div>
						</li>
					</ul>
					<ul class="list-inline half form-inline">
						<li>
			             	<div class="form-group">
			             		<label class="fw-normal w140  text-right" style="vertical-align: top;float:left">变更类型：</label>
			             		${contractChange.changeTypeStr}
			             	</div>
						</li>
					</ul>
					<ul class="list-inline half form-inline" >
						<li>
			             	<div class="form-group" style="width:500px;">
			              		<label class="fw-normal w140 text-right"style="vertical-align: top;float:left">乙转甲类型：</label>
			                  	<c:if test="${contractChange.changeCompany == 1}">
                                                                                 签约主体变更
                                </c:if>
                                <c:if test="${contractChange.changeCompany == 1 && contractChange.changeCompanyName == 1}">
                                                                                    、
                                 </c:if>
			                  	<c:if test="${contractChange.changeCompanyName == 1}">
			                  	公司经营范围变更
			                  	</c:if>
			                  	
			                  	 <c:if test="${(contractChange.changeCompany == 1 || contractChange.changeCompanyName == 1) &&  contractChange.changeCompanyAdress == 1 }">
			                  	    、
			                  	 </c:if> 
			                  	          	
			                  	<c:if test="${contractChange.changeCompanyAdress == 1}">
			                  	公司注册地址变更
			                  	</c:if>
			                  	
			                  	<c:if test="${(contractChange.changeCompany == 1 || contractChange.changeCompanyName == 1 || contractChange.changeCompanyAdress == 1) && contractChange.changeStoreAdress == 1}">
			                  	、
			                  	</c:if>
			                  	
			                  	<c:if test="${contractChange.changeStoreAdress == 1}">
			                  	门店地址变更
			                  	</c:if>
			             	</div>
						</li>
					</ul>	
						
				  <c:if test="${contractChange.changeCompanyAdress == 1}">
					<ul class="list-inline half form-inline">
                               <li>
								  <div class="form-group">
								        <label class="fw-normal w140 text-right" style="vertical-align: top;float:left"><i>* </i>变更后公司地址：</label>
									     ${contractChange.changeCompanyCityName} 
									     ${contractChange.changeCompanyDistrictName} 
									     ${contractChange.companyAdresss} 
									      
								</div>
                			</li>
                      </ul>
                     </c:if>
				  <c:if test="${contractChange.updateCompanyName == 1}">
					<ul class="list-inline form-inline">
                               <li>
								  <div class="form-group" style="width:50%;">
								        <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">变更前公司名称：</label>
									     ${contractChange.oldUpdateCompanyName} 
								</div>
								<div class="form-group" style="padding-left:15px;">
                                        <label class="fw-normal w240 text-right" style="vertical-align: top;float:left"><i>* </i>变更后公司名称：</label>
                                         ${contractChange.newUpdateCompanyName} 
                                </div>
                			</li>
                      </ul>
                     </c:if>
                     <c:if test="${contractChange.changeStoreAdress == 1}">
                       <ul class="list-inline form-inline">
                               <li>
								  <div class="form-group" style="width:50%;">
								        <label class="fw-normal w140 text-right" style="vertical-align: top;">变更前门店地址：</label>
									     ${contractChange.oldStoreAddressDetail} 
								</div>
								  <div class="form-group" style="padding-left:15px;">
								        <label class="fw-normal w240 text-right" style="vertical-align: top;"><i>* </i>变更后门店地址：</label>
									     ${contractChange.changeStoreCityName} 
									     ${contractChange.changeStoreDistrictName} 
									     ${contractChange.storeAdresss}  								 
								</div>
                			</li>
                         </ul>
                         </c:if>                                                                                                   
                         
                      <c:if test="${contractChange.changeCompany == 1 }">
                      
                      <ul class="list-inline form-inline">
						<li>
			                <div class="form-group" style="width:50%;">
			                    <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">新公司名称：</label>
			                    ${contractChange.newCompanyName}
			                </div>
			                <div class="form-group">
                                <label class="fw-normal w240 text-right" style="vertical-align: top;float:left"><i>* </i>新公司法定代表人：</label> 
                                   ${contractChange.newLegalPerson}                                  
                            </div>
			            </li>
					  </ul>
					      
                           <ul class="list-inline form-inline">
                               <li>
                                   <div class="form-group" style="width: 50%;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left"><i>* </i>新营业执照地址：</label>   
                                          ${contractChange.newCompanyAddressCityName} 
                                          ${contractChange.newCompanyAddressDistrictName} 
                                          ${contractChange.newCompanyAddress}                                
                                   </div>
                                   <div class="form-group" style="padding-left:15px;">
                                       <label class="fw-normal w240 text-right" style="vertical-align: top;float:left"><i>* </i>合作协议书类型：</label>
                                        <c:forEach items="${agreementTypeList}" var="agreementTypeList">
                                            <c:if test="${agreementTypeList.dicCode eq contractChange.agreementType}">
                                                ${agreementTypeList.dicValue}
                                            </c:if>
                                        </c:forEach>                                                   
                                   </div>
                               </li>
                          </ul>
                          
                          <ul class="list-inline form-inline">
                                <li>
                                   <div class="form-group" style="width:50%;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;"><i>* </i>新合作协议书编号：</label>
                                       ${contractChange.newAgreementNo} 
                                       <span class="fc-warning"></span>
                                   </div>
                                   <div class="form-group" style="padding-left:15px;">
                                        <label><i>* </i>新合同合作期限：</label>
                                        ${sdk:ymd2(contractChange.newDateLifeStart)} <c:if test="${sdk:ymd2(contractChange.newDateLifeStart) != null && sdk:ymd2(contractChange.newDateLifeEnd) != null}">至  </c:if>${sdk:ymd2(contractChange.newDateLifeEnd)}
                                   </div>
                                </li>
                            </ul>
                          
                          <ul class="list-inline form-inline">
                               <li>
                                   <div class="form-group" style="width:50%;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left"><i>* </i>甲方授权代表：</label>
                                        ${contractChange.authRepresentative}                                                       
                                   </div>
                                   <div class="form-group" style="padding-left:15px;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left"><i>* </i>联系方式：</label>
                                        ${contractChange.agentContact}                                                       
                                   </div>
                               </li>
                          </ul>
                          <ul class="list-inline form-inline">
                               <li>
                                   <div class="form-group" style="width:50%;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left"><i>* </i>保证金金额：</label>
                                        ${contractChange.totleDepositFee}                                                       
                                   </div>
                                   <div class="form-group" style="padding-left:15px;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left"><i>* </i>违约金额：</label>
                                        ${contractChange.penaltyFee}                                                       
                                   </div>
                               </li>
                          </ul>
                          <ul class="list-inline form-inline">
                               <li>
                                   <div class="form-group">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">客户公司账号：</label>
                                        ${contractChange.companyBankNo}                                                       
                                   </div>
                               </li>
                          </ul>
                          <ul class="list-inline form-inline">
                               <li>
                                   <div class="form-group" style="width:50%;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">开户银行：</label>
                                        ${contractChange.bankAccount}                                                       
                                   </div>
                                   <div class="form-group" style="padding-left:15px;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">开户名：</label>
                                        ${contractChange.accountName}                                                       
                                   </div>
                               </li>
                          </ul>
                          <ul class="list-inline form-inline">
                               <li>
                                   <div class="form-group" style="width:50%;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">甲方收件人：</label>
                                        ${contractChange.recipients}                                                       
                                   </div>
                                   <div class="form-group" style="padding-left:15px;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">甲方收件地址：</label>
                                        <c:forEach items="${citylist}" var="city">
                                            <c:if test="${contractChange.cityNo eq city.cityNo}">
                                                ${city.cityName}
                                            </c:if>
                                          </c:forEach>
                                          <c:forEach items="${districtList}" var="district">
                                            <c:if test="${contractChange.districtNo eq district.districtNo}">
                                               ${district.districtName}
                                            </c:if>
                                        </c:forEach>
                                        ${contractChange.recipientsAddress}                                                     
                                   </div>
                               </li>
                          </ul>
                      </c:if>    
                      
                                 
					<ul class="list-inline form-inline">
						<li>
			                <div class="form-group" style="overflow:hidden;">
			                    <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">备注：</label>
			                    <span style="display:block;margin-left:140px;">${contractChange.remarks}</span>
			                </div>
			            </li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="form-group" style="overflow:hidden;">
								<label class="fw-normal w140 text-right" style="vertical-align: top;float:left">提交OA状态：</label>
								<c:if test="${contractChange.submitOAStatus eq 21202 || contractChange.submitOAStatus eq 21204}">
									<span style="display:block;margin-left:140px;color: red;">${contractChange.submitOAStatusName}</span>
								</c:if>
								<c:if test="${contractChange.submitOAStatus eq 21201 || contractChange.submitOAStatus eq 21203}">
									<span style="display:block;margin-left:140px;">${contractChange.submitOAStatusName}</span>
								</c:if>
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
				           <c:forEach items="${storeList}" var="item"  varStatus="listIndex">
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
			                 	</tr>
							</c:forEach> 
					</table>
				
				<!-- 主体不变附件  -->
				<c:if test="${contractChange.changeCompany == 0}">
                <p><strong>文件</strong></p>
                <ul class="list-inline form-inline">
                  <li>
                      
<div class="" role="main">
        <div>
                <div class="pd10" >
					<h4 class="thumb-title">
						新营业执照
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractChange.newBusinessLicenseFileList }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractChange.newBusinessLicenseFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="新营业执照" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div> 	
                  </li>
              </ul>
              
              <ul class="list-inline form-inline">
                  <li>
                    
<div class="" role="main">
        <div >
                <div class="pd10" >
					<h4 class="thumb-title">
						国家企业信用信息公示系统网站截图
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractChange.informationPublicityFileList }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractChange.informationPublicityFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="国家企业信用信息公示系统网站截图" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div> 	
                  </li>
              </ul>
              <c:if test="${contractChange.changeStoreAdress == 1}">
               <ul class="list-inline form-inline">
                  <li>
                   
<div class="" role="main">
        <div >
                <div class="pd10" >
					<h4 class="thumb-title">
						房友确认单
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractChange.confirmationSheetFileList }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractChange.confirmationSheetFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="房友确认单" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div> 
                  </li>
              </ul>
             
               <ul class="list-inline form-inline">
                  <li>
                      
<div class="" role="main">
        <div >
                <div class="pd10" >
					<h4 class="thumb-title">
						门店照片
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractChange.storePhotosFileList }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractChange.storePhotosFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="门店照片" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div> 
                  </li>
              </ul>
               </c:if>
               <ul class="list-inline form-inline">
                  <li>
                       
<div class="" role="main">
        <div >
                <div class="pd10" >
					<h4 class="thumb-title">
						其他
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractChange.changeOthersFileList }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractChange.changeOthersFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="其他" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div> 
                  </li>
              </ul>
              </c:if>
            
            
            
            <!-- 主体变更附件 -->
            <c:if test="${contractChange.changeCompany == 1 }">
                  <p><strong>文件</strong></p>
                <ul class="list-inline form-inline">
                    <li>
                       
<div class="" role="main">
        <div >
                <div class="pd10" >
					<h4 class="thumb-title">
						权利义务转让三方协议书
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractChange.transferRightsFileList }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractChange.transferRightsFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="权利义务转让三方协议书" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div>
                    </li>
                </ul>
             
                <ul class="list-inline form-inline">
                  <li>
                       
<div class="" role="main">
        <div >
                <div class="pd10" >
					<h4 class="thumb-title">
						新签合作协议书
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractChange.newSigningFileList }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractChange.newSigningFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="新签合作协议书" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div>
                  </li>
              </ul>
              
              <ul class="list-inline form-inline">
                  <li>
                      
<div class="" role="main">
        <div >
                <div class="pd10" >
                    <h4 class="thumb-title">
                        新营业执照
                    </h4>
                    <div class="thumb-xs-box" id="thumbXsBox">
                    <c:if test="${not empty contractChange.newBusinessLicenseFileList }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.newBusinessLicenseFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank"  >
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="新营业执照" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                       </a>
                              </div>
                        </c:forEach> 
                        
                    </c:if> 
                    </div>
                   
                </div>
     </div>
 </div>     
                  </li>
              </ul>
              
              <ul class="list-inline form-inline">
                  <li>
                      
<div class="" role="main">
        <div >
                <div class="pd10" >
                    <h4 class="thumb-title">
                        法人身份证
                    </h4>
                    <div class="thumb-xs-box" id="thumbXsBox">
                    <c:if test="${not empty contractChange.legalCardFileList }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.legalCardFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank"  >
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="法人身份证" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                       </a>
                              </div>
                        </c:forEach> 
                        
                    </c:if> 
                    </div>
                </div>
     </div>
 </div>     
                  </li>
              </ul>
              
              <ul class="list-inline form-inline">
                  <li>
                         
<div class="" role="main">
        <div >
                <div class="pd10" >
					<h4 class="thumb-title">
						国家企业信用信息公示系统的网站截图
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractChange.mainInformationPublicityFileList }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractChange.mainInformationPublicityFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="国家企业信用信息公示系统的网站截图" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div>
                      
                  </li>
              </ul>
            <ul class="list-inline form-inline">
                  <li>
                   
<div class="" role="main">
        <div >
                <div class="pd10" >
                    <h4 class="thumb-title">
                        房友确认单
                    </h4>
                    <div class="thumb-xs-box" id="thumbXsBox">
                    <c:if test="${not empty contractChange.confirmationSheetFileList }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.confirmationSheetFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank"  >
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="房友确认单" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                       </a>
                              </div>
                        </c:forEach> 
                        
                    </c:if> 
                    </div>
                </div>
     </div>
 </div> 
                  </li>
              </ul>
             
               <ul class="list-inline form-inline">
                  <li>
                      
<div class="" role="main">
        <div >
                <div class="pd10" >
                    <h4 class="thumb-title">
                        门店照片
                    </h4>
                    <div class="thumb-xs-box" id="thumbXsBox">
                    <c:if test="${not empty contractChange.storePhotosFileList }">
                        <c:set  var="fileSize" value="0"/>
                        <c:forEach items="${contractChange.storePhotosFileList}" var="list" varStatus="status">
                            <c:set var="fileSize" value="${fileSize + 1}"/>
                            <div class="thumb-xs-list item-photo-list">
                                       <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank"  >
                                            <span class="thumb-img">
                                                <span class="thumb-img-container">
                                                    <span class="thumb-img-content">
                                                        <img alt="门店照片" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
                                                    </span>
                                                </span>
                                            </span>
                                       </a>
                              </div>
                        </c:forEach> 
                        
                    </c:if> 
                    </div>
                </div>
     </div>
 </div> 
                  </li>
              </ul>
               <ul class="list-inline form-inline">
                  <li>
                              
<div class="" role="main">
        <div >
                <div class="pd10" >
					<h4 class="thumb-title">
						重要提示函
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractChange.fileRecordMainNotice }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractChange.fileRecordMainNotice}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="重要提示函" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div>
 </li>
              </ul>
               <ul class="list-inline form-inline">
                  <li>
<div class="" role="main">
        <div>
                <div class="pd10" >
					<h4 class="thumb-title">
						其他
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractChange.mainChangeOthersFileList }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractChange.mainChangeOthersFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="其他" src="${list.fileAbbrUrl}" data-original="${list.url50}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div>
                  </li>
              </ul>
            </c:if>
            </div>
            </div>
			</div>
		</div>
</body>
</html>
<script type="text/javascript">
	function operateAuditCt(id, contractId, contractStatus) {
		if(!isBlank(id)) {
			systemLoading("", true);
			$.ajax({
				url:BASE_PATH+"/stopcontract/operateAuditCt",
				data:$.param({
					id:id
				}),
				type:"post",
				success:function(data){
					data = JSON.parse(data);
					if(data && data.returnCode == '200'){
						Dialog.alertSuccess("状态变更成功!");
						$("#operateAuditCt").hide();
						systemLoaded();
						location.href = BASE_PATH + '/contractChangeNew/list/'+contractId+"/"+contractStatus;
					}
				},
				error:function(){
					Dialog.alertError("状态变更失败");
					$("#operateChangeCt").hide();
					systemLoaded();
				}
			});
		}

	};
</script>


