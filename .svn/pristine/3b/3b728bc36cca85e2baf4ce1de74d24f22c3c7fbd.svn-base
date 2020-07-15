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
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/storeRelocation/storeRelocation.js?_v=${vs}"></script>
<style type="text/css">
    .text-overflow {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        width: 100%;
    }
</style>
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
                        <strong>编辑合同变更</strong>
                    </h4>
                    <div class="" style="height: auto;">
                        <form id="storeRelocationForm">
                        	<input type="hidden" name="id" value="${id}">
                            <input type="hidden" name="contractId" id="contractId" value="${contractChange.contractId}">
                            <input type="hidden" name="contractStatus" id="contractStatus" value="${contractStatus}">
                            <input type="hidden" name="storeId" id="storeId" value="${contractChange.storeList[0].storeId}">
                            <input type="hidden" name="contractAcCityNo" id="contractAcCityNo" value="${contractChange.cityNo}">
                            <input type="hidden" name="oldfileRecordMainIds" id="oldfileRecordMainIds" value="${contractChange.fileRecordMainIds}"/>
    						<input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds" value="${contractChange.fileRecordMainIds}"/>
                            <ul class="list-inline form-inline">
                                <li>
                                    <div class="form-group" style="padding-left:10px;">
                                        <label class="fw-normal w125 text-right">变更合同编号</label>
                                        <span style="padding-left:5px;">${contractChange.contractNo}</span>
                                    </div>
                                </li>
                            </ul>

                            <ul class="list-inline form-inline">
                                <li>
                                    <div class="form-group" style="padding-left:10px;">
                                        <label class="fw-normal w125 text-right">变更类型：</label>
                                        <span style="padding-left:5px;">门店迁址</span>
                                        <input type="hidden" name="changeType" value="17004"/>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left: 15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">经纪公司：</label>
                                        <input type="hidden" name="oldUpdateCompanyName" value="${contractChange.oldUpdateCompanyName}">
                                        <input type="hidden" name="oldCompanyId" id="oldCompanyId" value="${contractChange.oldCompanyId}"/>
                                        <input type="hidden" name="companyNo" id="companyNo" value="${contractChange.companyBankNo}"/>
                                        <span style="padding-left:5px;">${contractChange.oldUpdateCompanyName} &nbsp;&nbsp; ${contractChange.companyBankNo}</span>
                                        <span class="fc-warning"></span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left: 15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">门店：</label>
                                        <span style="padding-left:5px;">${contractChange.storeList[0].name} &nbsp;&nbsp; ${contractChange.storeList[0].storeNo}</span>
                                        <span class="fc-warning"></span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left: 15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">门店原址：</label>
                                        <input type="hidden" name="oldStoreAddressDetail" id="oldStoreAddressDetail" value="${contractChange.storeList[0].addressDetail}"/>
                                        <span style="padding-left:5px;">${contractChange.oldStoreAddressDetail} </span>
                                        <span class="fc-warning"></span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:15px;">
                                    <div class="form-group" >
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>门店变更后地址：</label>
                                        <span style="padding-left:5px;">
                                           <input type="hidden" id="storeCity" name="storeCity" value="${contractChange.storeCity}">
                                            <c:forEach items="${citylist}" var="city">
                                                <c:if test="${city.cityNo eq contractChange.storeCity}">
                                                    <input type="text" value="${city.cityName}" style="background-color: #F9F9F9;width: 120px;" class="form-control w120" id="address1" readonly notnull="true">
                                                </c:if>
                                            </c:forEach>
                                            <select style="width: 120px;" class="form-control w120"   id="storeDistrict" name="storeDistrict" notnull="true" onchange="checkAddress()">
				                                <option
				                                        <c:if test="${empty contractChange.districtList or contractChange.districtList eq ''}">selected="selected"</c:if>
				                                        value="">请选择区域
				                                </option>
				                                <c:if test="${!empty contractChange.districtList}">
				                                    <c:forEach items="${contractChange.districtList}" var="district">
				                                        <option value="${district.districtNo}"
				                                                <c:if test="${district.districtNo eq contractChange.storeDistrict}">selected="selected"</c:if>>${district.districtName}</option>
				                                    </c:forEach>
				                                </c:if>
				                            </select>
				                            <input type="text" name="storeAddress" style="width: 350px;" class="form-control" id="storeAddress" notnull="true" value="${contractChange.storeAdresss2}" onchange="checkAddress()">
				                            <span class="fc-warning"></span><span style="font-size: 12px;color: #FF0000;" id="warningMsg"></span>
                                        </span>
                                    </div>
                                </li>
                            </ul>
                            <ul class="list-inline form-inline">
                                <li style="padding-left:15px;">
                                    <div class="form-group">
                                        <label class="fw-normal w125 text-right" style="vertical-align: top;">备注：</label>
                                       <span style="padding-left:5px;"> <textarea maxlength="150" name="remarks" id="remarks" cols="30" rows="10"  style="resize: none;" >${contractChange.remarks}</textarea></span>
                                        <span class="fc-warning"></span>
                                    </div>
                                </li>
                            </ul>
                            
                            <p><strong>门店信息</strong></p>
                            <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                                <tr>
                                    <th style="width: 100px;">门店编号</th>
                                    <th style="width: 150px;">门店名称</th>
                                    <th style="width: 120px;"><i>* </i>门店负责人</th>
                                    <th style="width: 120px;"><i>* </i>门店负责人电话</th>
                                    <th style="width: 120px;"><i>* </i>门店资质等级</th>
                                </tr>
                                    <tr>
                                        <td>${contractChange.storeList[0].storeNo}</td>
                                        <td style="text-align:center;" title="${contractChange.storeList[0].name}" class="text-overflow">${contractChange.storeList[0].name}</td>
                                        <td><input type="text" name="storeManager" style="width: 120px" value="${contractChange.storeList[0].storeManager}" notnull="true"> <span class="fc-warning"></span></td>
                                        <td><input type="text" name="storeManagerPhone" style="width: 120px" value="${contractChange.storeList[0].storeManagerPhone}" notnull="true" datatype="phone" maxlength="11"> <span class="fc-warning"></span></td>
                                        <td>
                                            <select id="storeABTypeStore" name="storeABTypeStore" style="width: 60px;" notnull="true">
                                                <option value="19902">乙类</option>
                                            </select>
                                            <input type="text" id="bTypeStoreName" readonly style="width: 60px;" notnull="true" value="乙3">
                                            <input type="hidden" id="storeBTypeStore" name="storeBTypeStore"  value="20003">
                                        </td>
                                        <input type="hidden" id="abTypeStore" value="19002">
                                        <input type="hidden" id="bTypeStore" value="20003">
                                    </tr>
                            </table>
                            
                    </form>
                    </div>
    				
    				
    				<div  class="" role="main">
					    <h4>
					        <strong>附件</strong>
					    </h4>
					</div>	
                    <!-- 补充协议 --> 
				<div class="" role="main">
				        <div class="row">
				                <div class="pd10">
									<h4 class="thumb-title">
										补充协议
									</h4>
									<div class="thumb-xs-box" id="fileComplement">
									<c:if test="${contractChange.fileComplement != null }">
										<c:set  var="fileSize" value="0"/>
						                <c:forEach items="${contractChange.fileComplement}" var="list" varStatus="status">
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
												<input type="hidden" name="fileTypeId" value="1045" />
												<input type="hidden" name="fileSourceId" value="13" />
											  </div>
						                </c:forEach> 
									</c:if>
										<div class="thumb-xs-list item-photo-add" 
											<c:if test="${contractChange.fileComplement != null && contractChange.fileComplement.size()>=10  }">style="display: none;"</c:if>>
											<input type="hidden" name="limitSize" value="10">
											<a href="javascript:void(0);" class="thumbnail" title="添加附件">
												<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
								                <input type="hidden" name="fileTypeId" value="1045" />
								                <input type="hidden" name="fileSourceId" value="13" />
								                <input type="hidden" name ="companyId" value="${companyInfo.id}">
											</a>
										</div>
									</div>
										<i class="fontset">注：须使用法务提供版本，内容完整，盖章清晰。</i>
								</div>
				     </div>
				 </div> 
				 <!-- 房友确认单 --> 
				<div class="" role="main">
				        <div class="row">
				                <div class="pd10">
									<h4 class="thumb-title">
										房友确认单
									</h4>
									<div class="thumb-xs-box" id="fileFyConfirmation">
									<c:if test="${contractChange.fileFyConfirmation != null }">
										<c:set  var="fileSize" value="0"/>
						                <c:forEach items="${contractChange.fileFyConfirmation}" var="list" varStatus="status">
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
												<input type="hidden" name="fileTypeId" value="1046" />
												<input type="hidden" name="fileSourceId" value="13" />
											  </div>
						                </c:forEach> 
									</c:if>
										<div class="thumb-xs-list item-photo-add" 
											<c:if test="${contractChange.fileFyConfirmation != null && contractChange.fileFyConfirmation.size()>=10  }">style="display: none;"</c:if>>
											<input type="hidden" name="limitSize" value="10">
											<a href="javascript:void(0);" class="thumbnail" title="添加附件">
												<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
								                <input type="hidden" name="fileTypeId" value="1046" />
								                <input type="hidden" name="fileSourceId" value="13" />
								                <input type="hidden" name ="companyId" value="${companyInfo.id}">
											</a>
										</div>
									</div>
										<i class="fontset">注：房友确认单须中介盖章且与门店实际经营地址一致。</i>
								</div>
				     </div>
				 </div> 
				 <!-- 门店照片 --> 
				<div class="" role="main">
				        <div class="row">
				                <div class="pd10">
									<h4 class="thumb-title">
										门店照片
									</h4>
									<div class="thumb-xs-box" id="fileStorePhoto">
									<c:if test="${contractChange.fileStorePhoto != null }">
										<c:set  var="fileSize" value="0"/>
						                <c:forEach items="${contractChange.fileStorePhoto}" var="list" varStatus="status">
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
												<input type="hidden" name="fileTypeId" value="1047" />
												<input type="hidden" name="fileSourceId" value="13" />
											  </div>
						                </c:forEach> 
									</c:if>
										<div class="thumb-xs-list item-photo-add" 
											<c:if test="${contractChange.fileStorePhoto != null && contractChange.fileStorePhoto.size()>=10  }">style="display: none;"</c:if>>
											<input type="hidden" name="limitSize" value="10">
											<a href="javascript:void(0);" class="thumbnail" title="添加附件">
												<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
								                <input type="hidden" name="fileTypeId" value="1047" />
								                <input type="hidden" name="fileSourceId" value="13" />
								                <input type="hidden" name ="companyId" value="${companyInfo.id}">
											</a>
										</div>
									</div>
										<i class="fontset">注：含店招、门牌号（须字迹清晰可辨识）的内外景照片，乙类须有授权经理或以上入镜。</i>
								</div>
				     </div>
				 </div> 
				  <!-- 其他 --> 
				<div class="" role="main">
				        <div class="row">
				                <div class="pd10">
									<h4 class="thumb-title">
										其他
									</h4>
									<div class="thumb-xs-box" id="fileOtherList">
									<c:if test="${contractChange.fileOtherList != null }">
										<c:set  var="fileSize" value="0"/>
						                <c:forEach items="${contractChange.fileOtherList}" var="list" varStatus="status">
						                	<c:set var="fileSize" value="${fileSize + 1}"/>
											<div class="thumb-xs-list item-photo-list">
													   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
														   	<span class="thumb-img">
														   		<span class="thumb-img-container">
														   			<span class="thumb-img-content">
														   				<img alt="其他" src="${list.fileAbbrUrl}" class="empPhoto" />
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
												<input type="hidden" name="fileTypeId" value="1047" />
												<input type="hidden" name="fileSourceId" value="13" />
											  </div>
						                </c:forEach> 
									</c:if>
										<div class="thumb-xs-list item-photo-add" 
											<c:if test="${contractChange.fileOtherList != null && contractChange.fileOtherList.size()>=10  }">style="display: none;"</c:if>>
											<input type="hidden" name="limitSize" value="10">
											<a href="javascript:void(0);" class="thumbnail" title="添加附件">
												<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
								                <input type="hidden" name="fileTypeId" value="1048" />
								                <input type="hidden" name="fileSourceId" value="13" />
								                <input type="hidden" name ="companyId" value="${companyInfo.id}">
											</a>
										</div>
									</div>
								</div>
				     </div>
				 </div> 




                </div>
                <span id="msgId" style="color:red"></span>
                <div class="text-center">
                    <a href="javascript:void(0)" onclick="fnSave(1)" class="btn btn-primary">保存</a>
                    <a href="${ctx}/contractChangeNew/list/${contractId}/${contractStatus}" class="btn btn-default mgl20">取消</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>


