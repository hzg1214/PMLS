<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
    <script type="text/javascript" src="${ctx}/meta/js/store/storeUpload.js?_v=${vs}"></script>
<style>
    ul{margin-bottom: 0px;}
</style>
<form id="storeDetailForm">
	<div class="container theme-hipage ng-scope" role="main">
                   <span class="" style="float:right"><a href="javascript:StoreLog.close();" class="btn btn-default">&times;</a></span>
                <div class="row">
			       <div class="page-content">
				   <input type="hidden" id="storeId" name="storeId" value="${storeId}"/>
				   <input type="hidden" name="longitude" id="longitude" value="${storeLog.longitude}">
				    <input type="hidden" name="latitude" id="latitude" value="${storeLog.latitude}">
				    <input type="hidden" name="pictureRefId" id="pictureRefId" value="${storeLog.pictureRefId}">
				    <input type="hidden" name="districtName" id="districtName" value="">
				     <input type="hidden" name="cityName" value="${userInfo.cityName}"/>
				<h4 class="border-bottom pdb10"><strong>修改门店信息</strong></h4>
               	<ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" style="margin-left: 45px;">
                            <label class="fw-normal w200 text-right"><i>*</i>修改项目名称:</label>
                            <c:if test="${storeLog.businessStatus ne 20903}">
                            <span style="padding-left:10px;">
					              		<input type="hidden" value="" id="name" />
					              		<input type="checkbox" name="storeName"  id="storeName" class="check" 
					              		                    value="1" onclick="nameChange(this)"  />门店名称
					         </span>

								<c:if test="${contractStatus ne 10402 && contractStatus ne 10403 && gpContractStatus ne 10402 && gpContractStatus ne 10403}">
								 <span style="margin-left: 35px;">
											<input type="hidden" value="" id="address" />
											<input type="checkbox" name="storeAddress" id="storeAddress" class="check"
															   value="1" onclick="addressChange(this)"  />门店地址
								  </span>
								</c:if>
						      </c:if>
						     
						     <c:if test="${storeLog.businessStatus eq 20903 && contractStatus ne 10403 && contractStatus ne 10406 && gpContractStatus ne 10406 && gpContractStatus ne 10403}">
						         <span style="margin-left: 35px;">
							        		<input type="checkbox" id="businessStatusCheck" class="check"   
							        		                   value="1" onclick="businessStatusChange(this)"  />营业状态
							      </span>
						      </c:if>
                            <span class="fc-warning" id="checkchange"></span>
                        </div>
                    </li>
                </ul>
                
                <div id="changeName" style="display: none;">
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" style="margin-left: 65px;">
                            <label class="fw-normal w200 text-right">原门店名称:</label>
                            <span style="padding-left:10px;"></span>
                            <input type="hidden"  id="oldName" name="oldName" value="${storeLog.name}">
                            ${storeLog.name}
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline" style="margin-top: 0px;">
                    <li>
                        <div class="form-group" style="margin-left: 30px;">
                            <label class="fw-normal w200 text-right"><i>*</i>修改后门店名称:</label>
                            <span style="padding-left:10px;"></span>
					        <input type="text" class="form-control" id="newName" name="newName" style="width:460px;" notnull="true" value="">
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
                          <label class="fw-normal w200 text-right">原门店地址:</label>
                          <span style="padding-left:10px;"></span>
                           <input type="hidden" id="oldStoreCity" name="oldStoreCity" value="${storeLog.cityNo} ">
                           <input type="hidden" id="oldStoreDistrict" name="oldStoreDistrict" value="${storeLog.districtNo }">
                           <input type="hidden" id="oldStoreAddress" name="oldStoreAddress" value="${storeLog.address }">
                           <input type="hidden" id="oldAddressDetail" name="oldAddressDetail" value="${storeLog.addressDetail}">
                           ${storeLog.addressDetail}
                       </div>
                   </li>
                </ul>
             
                <ul class="list-inline form-inline" style="margin-top: 0px;">
                    <li>
                              <div class="form-group" style="margin-left: 28px;">
					             		<label><i>* </i>修改后门店地址:</label>
		                				<span class="control-select" style="padding-left:10px;">
		                					<select class="form-control" title="" id="newStoreCity" name="newStoreCity">
		                					  <option value="${storeLog.cityNo}"  selected="selected" readonly="true" >${storeLog.cityName}</option>
		                					   <%--  <c:forEach items="${citylist}" var="city">
					                            <option value="${city.cityNo}" <c:if test="${city.cityNo eq userInfo.cityNo}">selected</c:if>>${city.cityName}</option>
					                            </c:forEach> --%>
				                            </select>
		                				</span>
		                				<span class="control-select">
		                					<select class="form-control" title="" id="newStoreDistrict" name="newStoreDistrict">
				                                <c:if test="${!empty districtList}"> 
					                                 <c:forEach items="${districtList}" var="district">
					                                    <option value="${district.districtNo}">${district.districtName}</option>
					                                </c:forEach>
				                            	 </c:if> 
				                            </select>
		                				</span>
		                				<input type="text" class="form-control w300" id="newStoreAddress" name="newStoreAddress" placeholder="具体地址信息" value="" notnull="true" maxlength="100">
                					     <span class="fc-warning"></span>
                					     <span id="checkAddress"></span>
                					</div>
                    </li>
                </ul>
                </div>
                
                
                <div id="changeBusinessStatus" style="display: none;">
                    <ul class="list-inline form-inline">
                        <li>
                            <div class="form-group" style="margin-left: 65px;">
                                <label class="fw-normal w200 text-right">原营业状态:</label> <span
                                    style="padding-left: 10px;"></span> 已停业
                            </div>
                        </li>
                    </ul>
                    <ul class="list-inline form-inline" style="margin-top: 0px;">
                        <li>
                            <div class="form-group" style="margin-left: 30px;">
                                <label class="fw-normal w200 text-right"><i>*</i>修改后营业状态:</label>
                                <span style="padding-left: 10px;"></span> 
                                <input type="hidden" id="oldStoreCity" name="oldStoreCity" value="${storeLog.cityNo} ">
	                            <input type="hidden" id="oldStoreDistrict" name="oldStoreDistrict" value="${storeLog.districtNo }">
	                            <input type="hidden" id="oldStoreAddress" name="oldStoreAddress" value="${storeLog.address }">
	                            <input type="hidden" id="oldAddressDetail" name="oldAddressDetail" value="${storeLog.addressDetail}">
                                <input type="hidden"
                                    class="form-control" id="businessStatus" name="businessStatus"
                                    notnull="true" value="">正常营业
                            </div>
                        </li>
                    </ul>
                </div>
                
               
                <input type="hidden" name="fileRecordMainId" value="${storeLog.fileRecordMainId}"
                       id="fileRecordMainId"/>
                <input type="hidden" name="fileTypeId" value="4"/>
                <input type="hidden" name="fileSourceId" value="2"/>
                
                          <!-- 门店图片列表 -->
                <table class="table-sammary">
                    <col style="width:140px;">
                    <col style="width:auto;">
                    <tr>
                        <td colspan="2">
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						附件(营业执照等)
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${storeLog.fileUrl!=null && storeLog.fileUrl!=''}">
						<div class="thumb-xs-list item-photo-list" style="display: none;">
							   <a href="${storeLog.fileUrl}" class="thumbnail swipebox"  target="_blank" >
								   	<span class="thumb-img">
								   		<span class="thumb-img-container">
								   			<span class="thumb-img-content">
								   				<img alt="门店图片列表" src="${storeLog.fileAbbrUrl}" class="empPhoto" />
								   				<label class="fileName" style="display: none;"></label>
								   			</span>
							   			</span>
						   			</span>
					   		   </a>
						<input type="hidden" name="limitSize" value="10">
					    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
					  </div>
					</c:if>
					
					<c:if test="${storeLog.storePicList == null || ((storeLog.fileUrl!=null && storeLog.storePicList.size()<9) || (storeLog.fileUrl==null && storeLog.storePicList.size()<10))  }">
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<span class="thumb-img" style="display: none;">
							<span class="thumb-img-container">
							<span class="thumb-img-content">
							<img src="" class="img empPhoto"/>
							<label class="fileName" style="display: none;"></label>
							</span>
							</span>
							</span>
							<span class="thumb-bottom-roup" style="display: none;">
				   		   		<i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
				   		   	</span>
								<input type="file" name="fileName0" class="btn-flie file-control"  onchange="upload(this, 'storeDetailForm');">
							</a>
						</div>
					</c:if>
					</div>
				</div>
     </div>
 </div> 	
                        </td>
                    </tr>
                </table>
			</div>
		</div>
	</div>
</form>

