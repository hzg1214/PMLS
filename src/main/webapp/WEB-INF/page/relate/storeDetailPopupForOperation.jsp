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
				<h4 class="border-bottom pdb10"><strong>运营维护门店信息</strong></h4>
               	<ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" style="margin-left: 45px;">
                            <label class="fw-normal w200 text-right"><i>*</i>修改项目名称:</label>
                            <span style="padding-left:10px;">
					              		<input type="hidden" value="" id="name" />
					              		<input type="checkbox" name="storeName"  id="storeName" class="check" 
					              		                    value="1" onclick="nameChange(this)"  />门店名称
					         </span>
					         
					         <span style="margin-left: 35px;">
						        		<input type="hidden" value="" id="address" />
						        		<input type="checkbox" name="storeAddress" id="storeAddress" class="check"   
						        		                   value="1" onclick="addressChange(this)"  />门店地址
						      </span>
							<span style="padding-left:35px;">
					              		<input type="hidden" value="" id="businessPlace" />
					              		<input type="checkbox" name="businessPlaceType"  id="businessPlaceType" class="check"
											   value="1" onclick="businessPlaceChange(this)"  />经营场所类型
					         </span>

							<span style="margin-left: 35px;">
						        		<input type="hidden" value="" id="storeSize" />
						        		<input type="checkbox" name="storeSizeScale" id="storeSizeScale" class="check"
											   value="1" onclick="storeSizeChange(this)"  />门店规模
						      </span>
							<span style="margin-left: 35px;">
						        		<input type="hidden" value="" id="storeTp" />
						        		<input type="checkbox" name="storeType" id="storeType" class="check"
											   value="1" onclick="storeStoreType(this)"  />门店类型
						      </span>

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
		                					  <%--   <option value="${userInfo.cityNo}" selected>${userInfo.cityName}</option> --%>
		                					    <c:forEach items="${citylist}" var="city">
					                            <option value="${city.cityNo}" <c:if test="${city.cityNo eq userInfo.cityNo}">selected</c:if>>${city.cityName}</option>
					                            </c:forEach>
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

				<div id="changeBusinessPlace" style="display: none;">
						   <ul class="list-inline form-inline">
							   <li>
								   <div class="form-group" style="margin-left: 38px;">
									   <label class="fw-normal w200 text-right">原经营场所类型:</label>

										    <input type="hidden"  id="oldBusinessPlace" name="oldBusinessPlace" value="${storeLog.businessPlaceType}">
									   		<c:if test="${storeLog.businessPlaceType eq 22401}"><span style="padding-left:10px;">商铺</span></c:if>
									   		<c:if test="${storeLog.businessPlaceType eq 22402}"><span style="padding-left:10px;">办公楼内</span></c:if>

								   </div>
							   </li>
						   </ul>
						   <ul class="list-inline form-inline" style="margin-top: 0px;">
							   <li>
								   <div class="form-group" style="margin-left: 5px;">
									   <label class="fw-normal w200 text-right"><i>*</i>修改后经营场所类型:</label>
									   <span style="padding-left:10px;">
					              		<input type="radio" name="newBusinessPlace" value="22401" checked/>商铺
										</span>
										<span style="margin-left: 35px;">
												<input type="radio" name="newBusinessPlace" value="22402"  />办公楼内
									    </span>
								   </div>
							   </li>
						   </ul>
						   <span id="checkChangeBusinessPlace"></span>
					   </div>

				<div id="changeStoreSize" style="display: none;">
						   <ul class="list-inline form-inline">
							   <li>
								   <div class="form-group" style="margin-left: 65px;">
									   <label class="fw-normal w200 text-right">原门店规模:</label>

										   <input type="hidden"  id="oldStoreSize" name="oldStoreSize" value="${storeLog.storeSizeScale}">
										   <c:if test="${storeLog.storeSizeScale eq 22901}"><span style="padding-left:10px;">大型</span></c:if>
										   <c:if test="${storeLog.storeSizeScale eq 22902}"><span style="padding-left:10px;">小型</span></c:if>
										   <c:if test="${storeLog.storeSizeScale eq 22903}"><span style="padding-left:10px;">微型</span></c:if>

								   </div>
							   </li>
						   </ul>
						   <ul class="list-inline form-inline" style="margin-top: 0px;">
							   <li>
								   <div class="form-group" style="margin-left: 30px;">
									   <label class="fw-normal w200 text-right"><i>*</i>修改后门店规模:</label>
									   <span style="padding-left:10px;">
					              		<input type="radio" name="newStoreSize" value="22901" checked/>大型
										</span>
									   <span style="margin-left: 35px;">
												<input type="radio" name="newStoreSize" value="22902"  />小型
									    </span>
									   <span style="margin-left: 35px;">
												<input type="radio" name="newStoreSize" value="22903"  />微型
									    </span>
								   </div>
							   </li>
						   </ul>
						<span id="checkChangeStoreSize"></span>
					   </div>

				<div id="changeStoreType" style="display: none;">
						   <ul class="list-inline form-inline">
							   <li>
								   <div class="form-group" style="margin-left: 65px;">
									   <label class="fw-normal w200 text-right">原门店类型:</label>

										   <input type="hidden"  id="oldStoreType" name="oldStoreType" value="${storeLog.storeType}">
										   <c:if test="${storeLog.storeType eq 23401}"><span style="padding-left:10px;">门店</span></c:if>
										   <c:if test="${storeLog.storeType eq 23402}"><span style="padding-left:10px;">社区</span></c:if>
										   <c:if test="${storeLog.storeType eq 23403}"><span style="padding-left:10px;">渠道</span></c:if>

								   </div>
							   </li>
						   </ul>
						   <ul class="list-inline form-inline" style="margin-top: 0px;">
							   <li>
								   <div class="form-group" style="margin-left: 70px;">
									   <label class="fw-normal w200 text-right"><i>*</i>门店类型:</label>
									   <span style="padding-left:10px;">
					              		<input type="radio" name="newStoreType" value="23401" checked/>门店
										</span>
									   <span style="margin-left: 35px;">
												<input type="radio" name="newStoreType" value="23402"  />社区
									    </span>
									   <span style="margin-left: 35px;">
												<input type="radio" name="newStoreType" value="23403"  />渠道
									    </span>
									   <span style="margin-left: 35px;">
												<input type="radio" name="newStoreType" value="23404"  />代理
									    </span>
								   </div>
							   </li>
						   </ul>
						<span id="changeStoreTp"></span>
					   </div>

                <div id="changeContractDiv" style="display: none;">
                    <ul class="list-inline form-inline">
                        <li>
							<div class="form-group" style="margin-left: 22px;">
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
					<c:if test="${contract ne null}">
						<ul id="changeContractUl" class="list-inline form-inline" style="display: block;margin-top: 0px;">
							<li>
								<div class="form-group" style="margin-left: 50px;">
									<label class="fw-normal w200 text-right">合同编号:</label>
									<span style="padding-left:10px;">
										<input type="hidden" name="contractNo" value="${contract.contractNo}">
										<input type="checkbox" name="contractId" class="check" value="${contract.id}"/>&nbsp;&nbsp;${contract.contractNo}
									</span>
								</div>
							</li>
						</ul>
					</c:if>
					<c:if test="${gpContract ne null}">
						<ul id="changeContractUl2" class="list-inline form-inline" style="display: block;margin-top: 0px;">
							<li>
								<div class="form-group" style="margin-left: 50px;">
									<label class="fw-normal w200 text-right">公盘合同编号:</label>
									<span style="padding-left:10px;">
										<input type="hidden" name="gpContractNo" value="${gpContract.gpContractNo}">
										<input type="checkbox" name="gpContractId" class="check" value="${gpContract.id}"/>&nbsp;&nbsp;${gpContract.gpContractNo}
									</span>
								</div>
							</li>
						</ul>
					</c:if>
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

