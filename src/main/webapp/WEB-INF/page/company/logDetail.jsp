<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<style>
.half li {
	width:80%
}
.w200{
	width:200px!important;
	margin-right:20px
}
</style>

<form id = "companyEditForm" >

<div class="container theme-hipage ng-scope" >
	<input type="hidden" id="companyId" value="${info.companyId}">
	<%-- <input type="hidden" id="companyId" value="${info.oaFileldBusiness}"> --%>
	<span class="" style="float:right"><a href="javascript:CompanyLog.close();" class="btn btn-default">&times;</a></span>

        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>查看</strong></h4>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                             <label class="fw-normal w200 text-right" for="name"><i>*</i>修改项目  ：</label>
                           		${info.updateItem}
                        </div>
                    </li> 
                </ul>
                <c:if  test="${info.chbCompanyName eq 1 }">
	                <ul class="list-inline half form-inline">
	                    <li>
	                        <div class="form-group">
	                            <label class="fw-normal w200 text-right" for="mobilePhone">原公司名称  ：</label>
	                             	${info.oldCompanyName}
	                        </div>
	                    </li>
	                </ul>
	              
	                <ul class="list-inline half form-inline" id="ulName">
	                    <li>
	                        <div class="form-group">
	                            <label class="fw-normal w200 text-right"><i>*</i>修改后公司名称  ：</label>
	                            	${info.newCompanyName}
	                        </div>
	                    </li>
	                </ul>
                </c:if>
                
                <c:if  test="${info.chbAddress eq 1 }">
	                <ul class="list-inline half form-inline">
	                     <li>
	                        <div class="form-group">
	                            <label class="fw-normal w200 text-right">原公司注册地址  ：</label>
	                            ${info.oldAddressDetail}
	                        </div>
	                    </li> 
	                   
	                </ul>
	                <ul class="list-inline half form-inline" id="ulAddress">
	                     <li>
	                        <div class="form-group">
	                            <label class="fw-normal w200 text-right"><i>*</i>修改后公司注册地址  ：</label>
	                            ${info.newAddressDetail}
	                        </div>
	                    </li> 
	                   
	                </ul>
                </c:if>
                
                <c:if  test="${info.chbBusinessLicenseNo eq 1 }">
                	<ul class="list-inline half form-inline">
	                     <li>
	                        <div class="form-group">
	                            <label class="fw-normal w200 text-right">原营业执照  ：</label>
	                            ${info.oldBusinessLicenseNo}
	                        </div>
	                    </li>                    
	                </ul>
	                <ul class="list-inline half form-inline" id="ulBusinessLicenseNo">
	                     <li>
	                        <div class="form-group">
	                            <label class="fw-normal w200 text-right"><i>*</i>修改后营业执照  ：</label>
	                            ${info.newBusinessLicenseNo}
	                        </div>
	                    </li> 
	                </ul>
                </c:if>
                
                 <c:if  test="${info.chbLegalPerson eq 1 }">
                 	 <ul class="list-inline half form-inline">
	                     <li>
	                        <div class="form-group">
	                            <label class="fw-normal w200 text-right">原法定代表人  ：</label>
	                            ${info.oldLegalPerson}
	                        </div>
	                    </li> 	                   
	                </ul>
	                <ul class="list-inline half form-inline" id="ulLegalPerson">
	                     <li>
	                        <div class="form-group">
	                            <label class="fw-normal w200 text-right"><i>*</i>修改后法定代表人  ：</label>
	                             ${info.newLegalPerson}
	                        </div>
	                    </li>                    
	                </ul>
                 </c:if>
				<c:if  test="${info.chbContactNumber eq 1 }">
					<ul class="list-inline half form-inline">
						<li>
							<div class="form-group">
								<label class="fw-normal w200 text-right">原法定代表人电话  ：</label>
									${info.oldContactNumber}
							</div>
						</li>
					</ul>
					<ul class="list-inline half form-inline" id="ulLegalPerson">
						<li>
							<div class="form-group">
								<label class="fw-normal w200 text-right"><i>*</i>修改后法定代表人电话  ：</label>
									${info.newContactNumber}
							</div>
						</li>
					</ul>
				</c:if>
                 <ul class="list-inline half form-inline" id="ulLegalPerson">
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right">修改人  ：</label>
                             ${info.updateUserName}
                        </div>
                         <div class="form-group">
                            <label class="fw-normal w200 text-right">修改时间  ：</label>
                             ${info.updateTime}
                        </div>
                    </li>                    
                </ul>
                
                <ul class="list-inline half form-inline">
                     <li>
               			<div class="container theme-hipage ng-scope" role="main">
				        	<div class="row">
				                <div class="pd10">
									<h4 class="thumb-title">
										<i>*</i>附件(营业执照)：
									</h4>
									<div class="thumb-xs-box" id="thumbXsBox">
									<c:if test="${info.fileRecordMainBusiness != null }">
										<c:set  var="fileSize" value="0"/>
						                <c:forEach items="${info.fileRecordMainBusiness}" var="list" varStatus="status">
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
                
            </div>
        </div>
    </div>
    
</form>