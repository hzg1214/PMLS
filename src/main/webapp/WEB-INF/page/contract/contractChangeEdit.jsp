<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/contract/contractChangeEdit.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/contract/contractChangeCom.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
</head><body>
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>
<div class="container">
    <div class="row article">
        <!-- 左侧菜单 -->
        <jsp:include page="/WEB-INF/page/contract/contractLeftMenu.jsp"
                     flush="true">
            <jsp:param value="110402" name="leftMenuSelectId"/>
        </jsp:include>
        <div class="col-md-10 content">
            <div class="page-content">
                <br>
                <h4>
                    <strong>修改合同终止申请</strong>
                </h4>

                <div class="" style="height: auto;">
                    <form id="stopContractForm">
                   		 <input type="hidden" name="contractId" id="contractId" value="${contractId}">
                        <input type="hidden" name="contractChangeId" id="contractChangeId" value="${contractChange.id}">
                        <input type="hidden" id="storeIdArray" name="storeIdArray"/>
                        <!-- 存放图片Id -->
                        <input type="hidden" name="contractChangePicIds" id="contractChangePicIds"
                               value="${contractChange.contractChangePicIds}">
                        <!-- 存放fangyou附件id集 -->
                        <input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"
                               value="${contractChange.fileRecordMainIds}"/>
                        <input type ="hidden"  id ="isReleaseFlag"   name="isReleaseFlag"  />
                        <%--<ul class="list-inline form-inline">
                          <li>
					             	<div class="form-group">
					              		<label class="fw-normal w125 text-right" for="changeType"><i>* </i>变更类型</label>
					                  	<t:dictSelect field="changeType" id="changeType" xmlkey="LOOKUP_Dic" dbparam="P1:170" defaultVal="17001" disable="true" nullOption="请选择..." ></t:dictSelect>
					                  	<input type="hidden" name="changeType" value="17001"/>
					                  	<span class="fc-warning"></span>
					             	</div>
								</li> 
                        </ul>--%>
                        <input type="hidden" name="changeType" value="17001"/>
							<ul class="list-inline form-inline">
								<li>
					             	<div class="form-group">
					              		<label class="fw-normal w125 text-right" for="stopType"><i>* </i>终止类型</label>
					                  	<%-- <t:dictSelect field="stopType" id="stopType" xmlkey="LOOKUP_Dic" dbparam="P1:164" nullOption="请选择..." ></t:dictSelect> --%>
					                  	<select class="form-control" title="" id="stopType" name="stopType" style="width:300px;" onclick="endChange()" value="${contractChange.stopType}">
				                            <option value="" >请选择...</option>
				                            <option value="16406" <c:if test="${contractChange.stopType eq '16406'}">selected="selected"</c:if>>提前解约</option>
				                            <option value="16401" <c:if test="${contractChange.stopType eq '16401'}">selected="selected"</c:if>>关店/注销</option>
				                   		</select>
					                  	<span class="fc-warning"></span>
					             	</div>
								</li>
							</ul>
							<p><strong>选择变更门店</strong></p>
							<table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
								<tr>
					           		<th style="width:40px"></th>
					           		<th style="width:95px">门店编号</th>
					              	<th style="width:80px">门店名称</th>
					              	<th style="width:100px">门店地址</th>
					              	<th style="width:75px">装修情况</th>
					              	<th style="width:75px">装修类型</th>
					              	<th style="width:75px">装修公司</th>
					              	<th style="width:75px">装修费用(元)</th>
					              	<th style="width:95px;text-align:center;">已收保证金(元)</th>
					              	<th style="width:125px;text-align:center;">保证金余额处理方式</th>
					              	<th style="width:95px;text-align:center;">保证金退还金额(元)</th>
					              	<!-- <th>所在区域</th> -->
					              	<!-- <th>营业状态</th>
					              	<th>创建日期</th> -->
								</tr>
								<c:set var="index" value="0"></c:set>
						           <c:forEach items="${storeList}" var="item"  varStatus="listIndex">
						           		<c:if test="${item.isCancel eq '17203' }">
						                 	<tr>
						                 	    <%-- <td>
						                 	    <input type="checkbox"  id="storeState${index}" name="storeChk"
													<c:if test="${item.storeState eq '2'}">
														disabled="true" 
													</c:if>
													<c:if test="${item.changeCompany eq '0' && item.approveState eq '1'}">
														disabled="true" 
													</c:if>
													<c:if test="${item.changeCompany eq '1' && item.approveState eq '1'}">
														disabled="true" 
													</c:if>
													<c:if test="${item.changeCompany eq '1' && item.approveState eq '2'}">
														disabled="true" 
													</c:if>
						                 	    	value="${item.storeId}"
						                 	    >
						                 	    </td> --%>
						                 	     <td>
						                 	     <input type="checkbox" id="storeState${index}" name="storeChk"
				                                        <c:choose>
				                                        <c:when test="${(item.storeState eq '2' || item.storeState eq '1' || item.disabledFlag=='2') && item.contractStopId ne contractChange.id}">
				                                                   disabled="true"
				                                        </c:when>
				                                        <c:otherwise>
				                                        <c:if test="${item.contractStopId eq contractChange.id }">
				                                                   checked="checked"
				                                        </c:if>
				                                        </c:otherwise>
				                                        </c:choose>
				                                                   value="${item.storeId}"
				                                        >
				                                            <c:choose>
				                                                <c:when test="${item.storeState != null && item.storeState eq '2' }">
				                                                    <input type="hidden" id="hidStoreState${index}" value="1">
				                                                </c:when>
				                                                <c:otherwise>
				                                                    <c:if test="${item.contractChangeState eq '正常' }">
				                                                        <input type="hidden" id="hidStoreState${index}" value="0">
				                                                    </c:if>
				                                                    <c:if test="${item.contractChangeState eq '变更中' }">
				                                                        <input type="hidden" id="hidStoreState${index}" value="2">
				                                                    </c:if>
				                                                </c:otherwise>
				                                            </c:choose>
				                                        </td>
						                 		<td>${item.storeNo}</td>
						                 		<td>${item.name}</td>
						                 		<td>${item.address}</td>
						                 		<td>
						                 			<c:if test="${item.decorateStatus eq 16304}">
														已装修
													</c:if>
													<c:if test="${item.decorateStatus eq 16301 or item.decorateStatus eq 16302 or item.decorateStatus eq 16303  }">
														未装修
													</c:if>
													<input type="hidden" name="decorateSituate${item.storeId}" value="${item.decorateStatus}">
												</td>
						                 		<td>
						                 			<c:if test="${item.decorateStatus eq 16304}">
														<c:if test="${item.decorationType eq 17001}">
															自费装修
														</c:if>
														<c:if test="${item.decorationType eq 17002}">
															我司装修
														</c:if>
													</c:if>
							                 		<c:if test="${item.decorateStatus eq 16301 or item.decorateStatus eq 16302 or item.decorateStatus eq 16303  }">
														-
													</c:if>
													<c:if test="${item.decorationType eq null}">
                                                       <input type="hidden" name="decorationType${item.storeId}" value="17003">
                                                    </c:if>
                                                    <c:if test="${item.decorationType ne null}">
                                                       <input type="hidden" name="decorationType${item.storeId}" value="${item.decorationType}">
                                                    </c:if>
												</td>
						                 		<td>
						                 			<c:if test="${item.decorateStatus eq 16301 or item.decorateStatus eq 16302 or item.decorateStatus eq 16303  }">
														-
													</c:if>
													<c:if test="${item.decorateStatus eq 16304}">
														<c:if test="${item.decorationType eq 17001}">
															-
														</c:if>
														<c:if test="${item.decorationType eq 17002}">
															${item.decorateCompany}
														</c:if>
													</c:if>
						                 			
						                 			<input type="hidden" name="decorateCompany${item.storeId}" value="${item.decorateCompany}">
						                 		</td>
						                 		<td>
						                 			<c:if test="${item.decorateStatus eq 16301 or item.decorateStatus eq 16302 or item.decorateStatus eq 16303  }">
														0.00
													</c:if>
						                 			<c:if test="${item.decorateStatus eq 16304}">
							                 			<c:if test="${item.decorationType eq 17001}">
															0.00
														</c:if>
														<c:if test="${item.decorationType eq 17002}">
															
															<fmt:formatNumber type="number" value="${item.oaMdysSumJsj}" pattern="0.00" maxFractionDigits="2"/>
														</c:if>
													</c:if>
													<input type="hidden" name="decorateAmount${item.storeId}" value="${item.oaMdysSumJsj}">
						                 		</td>
						                 		<td>
					                  				 <input type="text" class="form-control" id="receivedAmount${item.storeId}" 
					                  				 	name="receivedAmount${item.storeId}" value='<fmt:formatNumber type="number" value="${item.receivedAmount}" pattern="0.00" maxFractionDigits="2"/>'  maxlength="9" 
					                  				 	onchange="myChange('depositBackMoney${item.storeId}','depositBalance${item.storeId}','receivedAmount${item.storeId}')">
						                 		</td>
						                 		<td>
						                 			<select class="form-control" title="" id="depositBalance${item.storeId}" name="depositBalance${item.storeId}" style="width:110px;padding:0px 0px;" 
						                 			onclick="divClick('depositBackMoney${item.storeId}','depositBalance${item.storeId}','receivedAmount${item.storeId}')">
				                           				<option value="" >请选择...</option>
				                           				<option value="21301" <c:if test="${item.depositBalance eq '21301'}">selected="selected"</c:if>>保证金全部退还</option>
				                            			<option value="21302" <c:if test="${item.depositBalance eq '21302'}">selected="selected"</c:if>>保证金全部没收</option>
				                            			<option value="21303" <c:if test="${item.depositBalance eq '21303'}">selected="selected"</c:if>>其他</option>
				                   					</select>
						                 		</td>
						                 		<td>
						                 				<input type="text" name="depositBackMoney${item.storeId}" id="depositBackMoney${item.storeId}"  class="form-control depositBackMoney" style="width:80px;" 
						                 				disabled="disabled"  value='<fmt:formatNumber type="number" value="${item.depositBackMoney}" pattern="0.00" maxFractionDigits="2"/>'/>
						                 		</td>
						                 	</tr>
						                 	<c:set var="index" value="${index+1}"></c:set>
					                 	</c:if>
									</c:forEach> 
							</table>
							<div>
								<p style="color: red;font-size:12px;"><i>* </i>注：灰色不可选代表该门店的合同已经终止或做过合同变更（乙转甲、三方变更）操作</p>
							</div>
							
							<p><strong>合作终止情况</strong></p>
							<ul class="list-inline form-inline">
								<li>
					                <div class="form-group">
                                    <label class="fw-normal w125 text-right" for="stopDate"><i>* </i>合作终止时间</label>
                                     <input type="text" class="calendar-icon " name="stopDate" id="stopDate" style="width:200px;"
                                           value="${sdk:ymd2(contractChange.stopDate)}"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                           class="ipttext Wdate" notnull="true"/>
                                    <span class="fc-warning"></span>
                                </div>
					                
					            </li>
							</ul>
							<ul class="list-inline form-inline">
								<li>
					                <div class="form-group">
                                    <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>终止具体原因</label>
                                    <textarea maxlength="150" name="stopReason" id="stopReason" cols="30" rows="10"
                                              style="resize: none;" notnull="true">${contractChange.stopReason}</textarea>
                                    <span class="fc-warning"></span>
                                </div>
					            </li>
							</ul>
							<ul class="list-inline form-inline">
								<li>
					                 <div class="form-group">
                                    <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>终止方案阐述</label>
                                    <textarea maxlength="150" name="stopDescribe" id="stopDescribe" cols="30" rows="10"
                                              style="resize: none;" notnull="true">${contractChange.stopDescribe}</textarea>
                                    <span class="fc-warning"></span>
                                </div>
					            </li>
							</ul>
							<ul class="list-inline form-inline">
								<li>
					                <div class="form-group">
                                    <label class="fw-normal w125 text-right" style="vertical-align: top;">备注</label>
                                    <textarea maxlength="150" name="remarks" id="remarks" cols="30" rows="10"
                                              style="resize: none;">${contractChange.remarks}</textarea>
                                    <span class="fc-warning"></span>
                                </div>
					            </li>
							</ul>
							</form>
							<!-- 终止合作协议书 -->
				
  <div class="" role="main">
                        <p><strong>附件</strong></p>
                    </div>
                    <div  id="stopContractBoxPc">
                    <!-- 终止合作协议书 -->
                    <div class="" role="main">
                        <div class="row">
                            <div class="pd10">
                                <h4 class="thumb-title">
                                    <i>*</i>终止合作协议书/单方解除函
                                </h4>
                                <div class="thumb-xs-box" id="stopContractBox">
                                    <c:if test="${contractChange.stopContractFileList != null }">
                                        <c:set var="fileSize" value="0"/>
                                        <c:forEach items="${contractChange.stopContractFileList}" var="list"
                                                   varStatus="status">
                                            <c:set var="fileSize" value="${fileSize + 1}"/>
                                            <div class="thumb-xs-list item-photo-list">
                                                <a href="${list.fileUrl}" class="thumbnail swipebox" target="_blank"
                                                   title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto"/>
										   			</span>
									   			</span>
								   			</span>
                                                    <span class="thumb-bottom-roup">
								   		   		<i class="icon down-icon"></i><i
                                                            class="icon remove-icon btn-remove-photo"></i>
								   		   	</span>
                                                </a>

                                                <input type="hidden" name="limitSize" value="10">
                                                <input type="file" id="file${fileSize}"
                                                       class="btn-flie file-control hide" data-limit="10"
                                                       multiple="multiple"/>
                                                <input type="hidden" name="fileRecordMainIdHidden"
                                                       value="${list.fileRecordMainId}"/>
                                                    <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}">
                                                <input type="hidden" name="fileTypeId" value="1001"/>
                                                <input type="hidden" name="fileSourceId" value="4"/>
                                                <input type="hidden" name="contractChangePicId"
                                                       value="${list.contractChangePicId}"/>
                                            </div>
                                        </c:forEach>

                                    </c:if>
                                    <div class="thumb-xs-list item-photo-add"
                                         <c:if test="${contractChange.stopContractFileList != null && contractChange.stopContractFileList.size()>=10  }">style="display: none;"</c:if>>
                                        <input type="hidden" name="limitSize" value="10">
                                        <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                            <input type="file" class="btn-flie file-control" data-limit="10"
                                                   multiple="multiple" data-name="photo-shinei">
                                            <input type="hidden" name="fileTypeId" value="1001"/>
                                            <input type="hidden" name="fileSourceId" value="4"/>
                                            <input type="hidden" name="companyId" value="">
                                        </a>
                                    </div>
                                </div>
                                    <i class="fontset">注：版本须经法务出具，终止合作协议需中介盖章签字。</i>
                            </div>
                        </div>
                    </div>
</div>
                    <!-- 三方解约协议 -->
<div style="display:none;" id="surrenderBoxPc">	
                    <div class="" role="main">
                        <div class="row">
                            <div class="pd10">
                                <h4 class="thumb-title">
                                    <i>*</i>合同权利义务转让三方协议书
                                </h4>
                                <div class="thumb-xs-box" id="surrenderBox">
                                    <c:if test="${contractChange.surrenderFileList != null }">
                                        <c:set var="fileSize" value="0"/>
                                        <c:forEach items="${contractChange.surrenderFileList}" var="list"
                                                   varStatus="status">
                                            <c:set var="fileSize" value="${fileSize + 1}"/>
                                            <div class="thumb-xs-list item-photo-list">
                                                <a href="${list.fileUrl}" class="thumbnail swipebox" target="_blank"
                                                   title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto"/>
										   			</span>
									   			</span>
								   			</span>
                                                    <span class="thumb-bottom-roup">
								   		   		<i class="icon down-icon"></i><i
                                                            class="icon remove-icon btn-remove-photo"></i>
								   		   	</span>
                                                </a>

                                                <input type="hidden" name="limitSize" value="10">
                                                <input type="file" id="file${fileSize}"
                                                       class="btn-flie file-control hide" data-limit="10"
                                                       multiple="multiple"/>
                                                <input type="hidden" name="fileRecordMainIdHidden"
                                                       value="${list.fileRecordMainId}"/>
                                                <input type="hidden" name="fileTypeId" value="1002"/>
                                                <input type="hidden" name="fileSourceId" value="4"/>
                                                <input type="hidden" name="contractChangePicId"
                                                       value="${list.contractChangePicId}"/>
                                            </div>
                                        </c:forEach>

                                    </c:if>
                                    <div class="thumb-xs-list item-photo-add"
                                         <c:if test="${contractChange.surrenderFileList != null && contractChange.surrenderFileList.size()>=10  }">style="display: none;"</c:if>>
                                        <input type="hidden" name="limitSize" value="10">
                                        <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                            <input type="file" class="btn-flie file-control" data-limit="10"
                                                   multiple="multiple" data-name="photo-shinei">
                                            <input type="hidden" name="fileTypeId" value="1002"/>
                                            <input type="hidden" name="fileSourceId" value="4"/>
                                            <input type="hidden" name="companyId" value="">
                                        </a>
                                    </div>
                                    
                                </div>
                                <i class="fontset">注：版本须经法务出具，三方协议需中介盖章签字。</i>
                            </div>
                        </div>
                    </div>
</div>
                    <!-- 保证金收据 -->

                    <div class="" role="main">
                        <div class="row">
                            <div class="pd10">
                                <h4 class="thumb-title">
                                    <i>*</i>保证金收据
                                </h4>
                                <div class="thumb-xs-box" id="receiptBox">
                                    <c:if test="${contractChange.receiptFileList != null }">
                                        <c:set var="fileSize" value="0"/>
                                        <c:forEach items="${contractChange.receiptFileList}" var="list"
                                                   varStatus="status">
                                            <c:set var="fileSize" value="${fileSize + 1}"/>
                                            <div class="thumb-xs-list item-photo-list">
                                                <a href="${list.fileUrl}" class="thumbnail swipebox" target="_blank"
                                                   title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto"/>
										   			</span>
									   			</span>
								   			</span>
                                                    <span class="thumb-bottom-roup">
								   		   		<i class="icon down-icon"></i><i
                                                            class="icon remove-icon btn-remove-photo"></i>
								   		   	</span>
                                                </a>

                                                <input type="hidden" name="limitSize" value="10">
                                                <input type="file" id="file${fileSize}"
                                                       class="btn-flie file-control hide" data-limit="10"
                                                       multiple="multiple"/>
                                                <input type="hidden" name="fileRecordMainIdHidden"
                                                       value="${list.fileRecordMainId}"/>
                                                    <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}">
                                                <input type="hidden" name="fileTypeId" value="1003"/>
                                                <input type="hidden" name="fileSourceId" value="4"/>
                                                <input type="hidden" name="oaFileId" value=""/>
                                                <input type="hidden" name="contractChangePicId"
                                                       value="${list.contractChangePicId}"/>
                                            </div>
                                        </c:forEach>

                                    </c:if>
                                    <div class="thumb-xs-list item-photo-add"
                                         <c:if test="${contractChange.receiptFileList != null && contractChange.receiptFileList.size()>=10  }">style="display: none;"</c:if>>
                                        <input type="hidden" name="limitSize" value="10">
                                        <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                            <input type="file" class="btn-flie file-control" data-limit="10"
                                                   multiple="multiple" data-name="photo-shinei">
                                            <input type="hidden" name="fileTypeId" value="1003"/>
                                            <input type="hidden" name="fileSourceId" value="4"/>
                                            <input type="hidden" name="companyId" value="">
                                        </a>
                                    </div>
                                </div>
                                    <i class="fontset">注：请清晰上传我司开具给中介的保证金收据。</i>
                            </div>
                        </div>
                    </div>
<!-- 照片-->

                    <div class="" role="main">
                        <div class="row">
                            <div class="pd10">
                                <h4 class="thumb-title">
                                    <i>*</i>门店照片
                                </h4>
                                <div class="thumb-xs-box" id="storePhoto">
                                    <c:if test="${contractChange.storePhotosFileList != null }">
                                        <c:set var="fileSize" value="0"/>
                                        <c:forEach items="${contractChange.storePhotosFileList}" var="list"
                                                   varStatus="status">
                                            <c:set var="fileSize" value="${fileSize + 1}"/>
                                            <div class="thumb-xs-list item-photo-list">
                                                <a href="${list.fileUrl}" class="thumbnail swipebox" target="_blank"
                                                   title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto"/>
										   			</span>
									   			</span>
								   			</span>
                                                    <span class="thumb-bottom-roup">
								   		   		<i class="icon down-icon"></i><i
                                                            class="icon remove-icon btn-remove-photo"></i>
								   		   	</span>
                                                </a>

                                                <input type="hidden" name="limitSize" value="10">
                                                <input type="file" id="file${fileSize}"
                                                       class="btn-flie file-control hide" data-limit="10"
                                                       multiple="multiple"/>
                                                <input type="hidden" name="fileRecordMainIdHidden"
                                                       value="${list.fileRecordMainId}"/>
                                                    <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}">
                                                <input type="hidden" name="fileTypeId" value="1014"/>
                                                <input type="hidden" name="fileSourceId" value="4"/>
                                                <input type="hidden" name="contractChangePicId"
                                                       value="${list.contractChangePicId}"/>
                                            </div>
                                        </c:forEach>

                                    </c:if>
                                    <div class="thumb-xs-list item-photo-add"
                                         <c:if test="${contractChange.photosFileList != null && contractChange.photosFileList.size()>=10  }">style="display: none;"</c:if>>
                                        <input type="hidden" name="limitSize" value="10">
                                        <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                            <input type="file" class="btn-flie file-control" data-limit="10"
                                                   multiple="multiple" data-name="photo-shinei">
                                            <input type="hidden" name="fileTypeId" value="1014"/>
                                            <input type="hidden" name="fileSourceId" value="4"/>
                                            <input type="hidden" name="companyId" value="">
                                        </a>
                                    </div>
                                </div>
                                    <i class="fontset">注：请提供门店现状照片，须已拆除房友相关的店招及标志。</i>
                            </div>
                        </div>
                    </div>
                    <!-- 已付装修款退还证明-->

                    <div class="" role="main">
                        <div class="row">
                            <div class="pd10">
                                <h4 class="thumb-title">
                                    已付装修款退还证明
                                </h4>
                                <div class="thumb-xs-box" id="returnProveBox">
                                    <c:if test="${contractChange.returnProveFileList != null }">
                                        <c:set var="fileSize" value="0"/>
                                        <c:forEach items="${contractChange.returnProveFileList}" var="list"
                                                   varStatus="status">
                                            <c:set var="fileSize" value="${fileSize + 1}"/>
                                            <div class="thumb-xs-list item-photo-list">
                                                <a href="${list.fileUrl}" class="thumbnail swipebox" target="_blank"
                                                   title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto"/>
										   			</span>
									   			</span>
								   			</span>
                                                    <span class="thumb-bottom-roup">
								   		   		<i class="icon down-icon"></i><i
                                                            class="icon remove-icon btn-remove-photo"></i>
								   		   	</span>
                                                </a>

                                                <input type="hidden" name="limitSize" value="10">
                                                <input type="file" id="file${fileSize}"
                                                       class="btn-flie file-control hide" data-limit="10"
                                                       multiple="multiple"/>
                                                <input type="hidden" name="fileRecordMainIdHidden"
                                                       value="${list.fileRecordMainId}"/>
                                                    <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}">
                                                <input type="hidden" name="fileTypeId" value="1004"/>
                                                <input type="hidden" name="fileSourceId" value="4"/>
                                                <input type="hidden" name="contractChangePicId"
                                                       value="${list.contractChangePicId}"/>
                                            </div>
                                        </c:forEach>

                                    </c:if>
                                    <div class="thumb-xs-list item-photo-add"
                                         <c:if test="${contractChange.returnProveFileList != null && contractChange.returnProveFileList.size()>=10  }">style="display: none;"</c:if>>
                                        <input type="hidden" name="limitSize" value="10">
                                        <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                            <input type="file" class="btn-flie file-control" data-limit="10"
                                                   multiple="multiple" data-name="photo-shinei">
                                            <input type="hidden" name="fileTypeId" value="1004"/>
                                            <input type="hidden" name="fileSourceId" value="4"/>
                                            <input type="hidden" name="companyId" value="">
                                        </a>
                                    </div>
                                </div>
                                    <i class="fontset">注：请清晰上传中介转账凭证或我司收款凭证。</i>
                            </div>
                        </div>
                    </div>

                    <!-- 注销证明-->
<div style="display:none;" id="cancellateBoxPc">
                    <div class="" role="main">
                        <div class="row">
                            <div class="pd10">
                                <h4 class="thumb-title">
                                    <i>*</i>注销单
                                </h4>
                                <div class="thumb-xs-box" id="cancellateBox">
                                    <c:if test="${contractChange.cancellateFileList != null }">
                                        <c:set var="fileSize" value="0"/>
                                        <c:forEach items="${contractChange.cancellateFileList}" var="list"
                                                   varStatus="status">
                                            <c:set var="fileSize" value="${fileSize + 1}"/>
                                            <div class="thumb-xs-list item-photo-list">
                                                <a href="${list.fileUrl}" class="thumbnail swipebox" target="_blank"
                                                   title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto"/>
										   			</span>
									   			</span>
								   			</span>
                                                    <span class="thumb-bottom-roup">
								   		   		<i class="icon down-icon"></i><i
                                                            class="icon remove-icon btn-remove-photo"></i>
								   		   	</span>
                                                </a>

                                                <input type="hidden" name="limitSize" value="10">
                                                <input type="file" id="file${fileSize}"
                                                       class="btn-flie file-control hide" data-limit="10"
                                                       multiple="multiple"/>
                                                <input type="hidden" name="fileRecordMainIdHidden"
                                                       value="${list.fileRecordMainId}"/>
                                                    <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}">
                                                <input type="hidden" name="fileTypeId" value="1005"/>
                                                <input type="hidden" name="fileSourceId" value="4"/>
                                                <input type="hidden" name="oaFileId" value=""/>
                                                <input type="hidden" name="contractChangePicId"
                                                       value="${list.contractChangePicId}"/>
                                            </div>
                                        </c:forEach>

                                    </c:if>
                                    <div class="thumb-xs-list item-photo-add"
                                         <c:if test="${contractChange.cancellateFileList != null && contractChange.cancellateFileList.size()>=10  }">style="display: none;"</c:if>>
                                        <input type="hidden" name="limitSize" value="10">
                                        <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                            <input type="file" class="btn-flie file-control" data-limit="10"
                                                   multiple="multiple" data-name="photo-shinei">
                                            <input type="hidden" name="fileTypeId" value="1005"/>
                                            <input type="hidden" name="fileSourceId" value="4"/>
                                            <input type="hidden" name="companyId" value="">
                                        </a>
                                    </div>
                                </div>
                            </div>
                                    <i class="fontset">注：请清晰上传工商出具的注销单。</i>
                        </div>
                    </div>
</div>
                    


                    
 
                    <!-- 其它-->

                    <div class="" role="main">
                        <div class="row">
                            <div class="pd10">
                                <h4 class="thumb-title">
                                    其它
                                </h4>
                                <div class="thumb-xs-box" id="thumbXsBox">
                                    <c:if test="${contractChange.othersFileList != null }">
                                        <c:set var="fileSize" value="0"/>
                                        <c:forEach items="${contractChange.othersFileList}" var="list"
                                                   varStatus="status">
                                            <c:set var="fileSize" value="${fileSize + 1}"/>
                                            <div class="thumb-xs-list item-photo-list">
                                                <a href="${list.fileUrl}" class="thumbnail swipebox" target="_blank"
                                                   title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="服务费" src="${list.fileAbbrUrl}" class="empPhoto"/>
										   			</span>
									   			</span>
								   			</span>
                                                    <span class="thumb-bottom-roup">
								   		   		<i class="icon down-icon"></i><i
                                                            class="icon remove-icon btn-remove-photo"></i>
								   		   	</span>
                                                </a>

                                                <input type="hidden" name="limitSize" value="10">
                                                <input type="file" id="file${fileSize}"
                                                       class="btn-flie file-control hide" data-limit="10"
                                                       multiple="multiple"/>
                                                <input type="hidden" name="fileRecordMainIdHidden"
                                                       value="${list.fileRecordMainId}"/>
                                                    <input type="hidden" name="fileNoHidden" value="${attachment.fileNo}">
                                                <input type="hidden" name="fileTypeId" value="1009"/>
                                                <input type="hidden" name="fileSourceId" value="4"/>
                                                <input type="hidden" name="contractChangePicId"
                                                       value="${list.contractChangePicId}"/>
                                            </div>
                                        </c:forEach>

                                    </c:if>
                                    <div class="thumb-xs-list item-photo-add"
                                         <c:if test="${contractChange.othersFileList != null && contractChange.othersFileList.size()>=10  }">style="display: none;"</c:if>>
                                        <input type="hidden" name="limitSize" value="10">
                                        <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                            <input type="file" class="btn-flie file-control" data-limit="10"
                                                   multiple="multiple" data-name="photo-shinei">
                                            <input type="hidden" name="fileTypeId" value="1009"/>
                                            <input type="hidden" name="fileSourceId" value="4"/>
                                            <input type="hidden" name="companyId" value="">
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <span id="msgId" style="color:red"></span>
                </div>
            </div>
            <div class="text-center">
                <a href="javascript:void(0)" onclick="doEdit(${contractChange.id},${contractStatus})"
                   class="btn btn-primary">保存</a>
                <a href="${ctx}/contract/changeRecord/${contractId}/${contractStatus}"
                   class="btn btn-default mgl20">取消</a>
            </div>
        </div>
    </div>
</body>
</html>
<%-- <body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>
<div class="container">
    <div class="row article">
        <!-- 左侧菜单 -->
        <jsp:include page="/WEB-INF/page/contract/contractLeftMenu.jsp"
                     flush="true">
            <jsp:param value="110402" name="leftMenuSelectId"/>
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
                        <input type="hidden" id="storeIdArray" name="storeIdArray"/>
                        <!-- 存放图片Id -->
                        <input type="hidden" name="contractChangePicIds" id="contractChangePicIds"
                               value="${contractChange.contractChangePicIds}">
                        <!-- 存放fangyou附件id集 -->
                        <input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"
                               value="${contractChange.fileRecordMainIds}"/>
                        <ul class="list-inline form-inline">
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right" for="changeType"><i>* </i>变更类型</label>
                                    <t:dictSelect field="changeType" id="changeType" xmlkey="LOOKUP_Dic"
                                                  dbparam="P1:170"
                                                  defaultVal="${contractChange.changeType}"></t:dictSelect>
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                        </ul>
                        <ul class="list-inline form-inline">
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right" for="stopType"><i>* </i>终止类型</label>
                                    <t:dictSelect field="stopType" id="stopType" xmlkey="LOOKUP_Dic" dbparam="P1:164"
                                                  defaultVal="${contractChange.stopType}"></t:dictSelect>
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                        </ul>
                        <ul class="list-inline form-inline">
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right" for="isReleaseFlag"><i>* </i>是否一并解除</label>
                                    <t:dictSelect field="isReleaseFlag" id="isReleaseFlag" xmlkey="LOOKUP_Dic"
                                                  dbparam="P1:168"
                                                  defaultVal="${contractChange.isReleaseFlag}"></t:dictSelect>
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                        </ul>
                        <p><strong>合作基本情况</strong></p>
                        <ul class="list-inline form-inline">
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right"><i>* </i>已收保证金额数</label>
                                    <input type="text" class="form-control w300" id="receivedAmount"
                                           name="receivedAmount" value="${contractChange.receivedAmount}" maxlength="9"
                                           dataType="moneyWithFlot" notnull="true">
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right" for="decorateCNTSituate"><i>* </i>三方装修合同情况</label>
                                    <t:dictSelect field="decorateCNTSituate" id="decorateCNTSituate" xmlkey="LOOKUP_Dic"
                                                  dbparam="P1:165"
                                                  defaultVal="${contractChange.decorateCNTSituate}"></t:dictSelect>
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                        </ul>
                        <ul class="list-inline form-inline">
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right" for="decorateSituate"><i>* </i>装修情况</label>
                                    <t:dictSelect field="decorateSituate" id="decorateSituate" xmlkey="LOOKUP_Dic"
                                                  dbparam="P1:166"
                                                  defaultVal="${contractChange.decorateSituate}"></t:dictSelect>
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right"><i>* </i>装修公司</label>
                                    <input type="text" class="form-control w300" id="decorateCompany"
                                           name="decorateCompany" value="${contractChange.decorateCompany}"
                                           maxlength="200" dataType="normal" notnull="true">
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                        </ul>
                        <ul class="list-inline form-inline">
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right" for="flopMode"><i>* </i>翻牌模式</label>
                                    <t:dictSelect field="flopMode" id="flopMode" xmlkey="LOOKUP_Dic" dbparam="P1:167"
                                                  defaultVal="${contractChange.flopMode}"></t:dictSelect>
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right"><i>* </i>装修费用总金额</label>
                                    <input type="text" class="form-control w300" id="decorateAmount"
                                           name="decorateAmount" value="${contractChange.decorateAmount}" maxlength="9"
                                           dataType="moneyWithFlot" notnull="true">
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                        </ul>
                        <ul class="list-inline form-inline">
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right"><i>* </i>已支付金额</label>
                                    <input type="text" class="form-control w300" id="payAmount" name="payAmount"
                                           value="${contractChange.payAmount}" maxlength="9" dataType="moneyWithFlot"
                                           notnull="true">
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                        </ul>
                        <p><strong>合作终止情况</strong></p>
                        <ul class="list-inline form-inline">
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right" for="stopDate"><i>* </i>合作终止时间</label>
                                    <input type="text" class="calendar-icon w300" name="stopDate" id="stopDate"
                                           value="${sdk:ymd2(contractChange.stopDate)}"
                                           onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                           class="ipttext Wdate" notnull="true"/>
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right" for="aConvertBFlag"><i>* </i>是否B转A</label>
                                    <t:dictSelect field="aConvertBFlag" id="aConvertBFlag" xmlkey="LOOKUP_Dic"
                                                  dbparam="P1:169" defaultVal="${contractChange.isaTob}"></t:dictSelect>
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                        </ul>
                        <ul class="list-inline form-inline">
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right" style="vertical-align: top;">终止具体原因</label>
                                    <textarea maxlength="150" name="stopReason" id="stopReason" cols="30" rows="10"
                                              style="resize: none;">${contractChange.stopReason}</textarea>
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                        </ul>
                        <ul class="list-inline form-inline">
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right" style="vertical-align: top;">终止方案阐述</label>
                                    <textarea maxlength="150" name="stopDescribe" id="stopDescribe" cols="30" rows="10"
                                              style="resize: none;">${contractChange.stopDescribe}</textarea>
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                        </ul>
                        <ul class="list-inline form-inline">
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w125 text-right" style="vertical-align: top;">备注</label>
                                    <textarea maxlength="150" name="remarks" id="remarks" cols="30" rows="10"
                                              style="resize: none;">${contractChange.remarks}</textarea>
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                        </ul>
                        <p><strong>门店信息</strong></p>
                        <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                            <tr>
                                <th></th>
                                <th>门店编号</th>
                                <th>门店名称</th>
                                <th>所在区域</th>
                                <th>门店地址</th>
                                <th>营业状态</th>
                                <th>创建日期</th>
                            </tr>
                            <c:set var="index" value="0"></c:set>
                            <c:forEach items="${storeList}" var="item" varStatus="listIndex">
                                <c:if test="${item.isCancel eq '17203'}">
                                    <tr>
                                        <td><input type="checkbox" id="storeState${index}" name="storeChk"
                                        <c:choose>
                                        <c:when test="${item.storeState != null && item.storeState eq '2' }">
                                                   disabled="true"
                                        </c:when>
                                        <c:otherwise>
                                        <c:if test="${item.contractChangeState eq '变更中' }">
                                                   checked="checked"
                                        </c:if>
                                        </c:otherwise>
                                        </c:choose>
                                        <c:if test="${item.changeCompany eq '0' && item.approveState eq '1'}">
                                                   disabled="true"
                                        </c:if>
                                        <c:if test="${item.changeCompany eq '1' && item.approveState eq '1'}">
                                                   disabled="true"
                                        </c:if>
                                        <c:if test="${item.changeCompany eq '1' && item.approveState eq '2'}">
                                                   disabled="true"
                                        </c:if>
                                                   value="${item.storeId}"
                                        >
                                            <c:choose>
                                                <c:when test="${item.storeState != null && item.storeState eq '2' }">
                                                    <input type="hidden" id="hidStoreState${index}" value="1">
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${item.contractChangeState eq '正常' }">
                                                        <input type="hidden" id="hidStoreState${index}" value="0">
                                                    </c:if>
                                                    <c:if test="${item.contractChangeState eq '变更中' }">
                                                        <input type="hidden" id="hidStoreState${index}" value="2">
                                                    </c:if>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${item.storeNo}</td>
                                        <td>${item.name}</td>
                                        <td>${item.districtName}</td>
                                        <td>${item.address}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.businessStatus eq '20901'}">正常营业</c:when>
                                                <c:when test="${item.businessStatus eq '20902'}">停业上报审核中</c:when>
                                                <c:when test="${item.businessStatus eq '20903'}">已停业</c:when>
                                            </c:choose>
                                        </td>
                                        <td>${item.dateCreate}</td>
                                    </tr>
                                    <c:set var="index" value="${index+1}"></c:set>
                                </c:if>
                            </c:forEach>
                        </table>
                    </form>

                    <div>
                        <p style="color: red;"><i>* </i>灰色不可选代表该门店的合同已经终止或做过乙类转甲类(签约主体变更)的操作</p>
                    </div>

--%>
                  
<script type="text/javascript">    
	$(function() {
		 var selectValue = ${contractChange.stopType};  
		 if(selectValue == 16401 ){  
		        $("#stopContractBoxPc").show();
		        $("#cancellateBoxPc").show();
		        $("#surrenderBoxPc").hide();
		    }  
		    if(selectValue == 16406){
		    	$("#stopContractBoxPc").show();
		        $("#surrenderBoxPc").hide();  
		        $("#cancellateBoxPc").hide();  
		    }  
		    if(selectValue == 16407){  
		    	$("#surrenderBoxPc").show(); 
		    	$("#stopContractBoxPc").hide();
		    	 $("#cancellateBoxPc").hide();
		    } 
	    $("input[name = storeChk]").each(function(){
			if ($(this).prop('checked')){
				var storeId =$(this).val();
				var receivedAmount = $('input[name="receivedAmount'+storeId+'"]').val();
				var depositBalance = $("select[name='depositBalance"+storeId+"']").val();
				if(depositBalance == 21301){
					$("#"+"depositBackMoney"+storeId).css("background-color", "#F9F9F9");
					$("#"+"depositBackMoney"+storeId).attr("disabled",true);
					
					var depositBackMoney = $('input[name="depositBackMoney'+storeId+'"]').val(receivedAmount);
				}
				if(depositBalance == 21302){
					$("#"+"depositBackMoney"+storeId).css("background-color", "#F9F9F9");
					$("#"+"depositBackMoney"+storeId).attr("disabled",true);
					
				}
				if(depositBalance == 21303){
					
					$("#"+"depositBackMoney"+storeId).css("background-color", "");
					$("#"+"depositBackMoney"+storeId).attr("disabled",false);
				}
			}
		});  
		   
	});
</script>

