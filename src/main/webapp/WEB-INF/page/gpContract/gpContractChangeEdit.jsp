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
<script type="text/javascript" src="${ctx}/meta/js/gpContract/gpContractChange.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<style type="text/css">
    .text-overflow {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        width: 100%;
    }
    textarea {
    	width: 1035px;
    }
</style>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
<form id = "stopGpContractForm" >

	<input type="hidden" name="storeIdArray" id="storeIdArray">
	<!-- <input type="hidden" name="oldStoreIdArray" id="oldStoreIdArray"> -->
	<input type="hidden" name="gpContractId" id="gpContractId" value="${gpContractId}">
	<%-- <input type="hidden" name="gpContractNo" id="gpContractNo" value="${gpContractNo}">
	<input type="hidden" name="companyId" id="companyId" value="${companyId}">
	<input type="hidden" name="companyName" id="companyName" value="${companyName}">
	<input type="hidden" name="gpAgreementNo" id="gpAgreementNo" value="${gpAgreementNo}"> --%>
	<!-- 存放fangyou附件id集 -->
	<input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"  />
	<input type="hidden" name="changeType" value="17001"/>
	<input type="hidden" name="oldfileRecordMainIds" id="oldfileRecordMainIds"  value = "${gpContractChangeInfo.fileRecordMainIds}" />
	<input type="hidden" name="id" id="id"  value = "${gpContractChangeInfo.id}" />
 	<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>编辑公盘合同终止申请</strong></h4>
                <table class="table-sammary">
                    <col style="width:135px;">
                    <col style="width:600px;">
                    <col style="width:135px;">
                    <col style="width:auto;">
                     <tr>
                     	<td class="talabel" style="vertical-align: text-top;">公盘合同编号：</td>
                     	<td colspan='3' style="vertical-align: text-top;">${gpContractChangeInfo.gpContractNo}</td>
                 	</tr>
                    <tr>
                    	<td class="talabel" style="vertical-align: text-top;">公盘经纪公司：</td>
                     	<td colspan='3' style="vertical-align: text-top;">${gpContractChangeInfo.companyName}</td>
                    </tr>
                    <tr>
                    	<td class="talabel"><label class="fw-normal  text-right" for="stopType"><i>* </i>终止类型：</label></td>
                        <td colspan="3">
                            <div class="input-grounp">
                                <select class="form-control" title="" id="stopType" name="stopType" style="width:200px;" notnull="true">
									<option value="" selected="selected">请选择...</option>
									<c:if test="${!empty stopTypeList}">
										<c:forEach items="${stopTypeList}" var="list">
											<option value="${list.dicCode}" <c:if test="${list.dicCode eq gpContractChangeInfo.stopType}">selected="selected"</c:if>>${list.dicValue}</option>
										</c:forEach>
									</c:if>
								</select>
								<span class="fc-warning"></span>
                            </div>
                        </td>
                    </tr>
                </table>
                <p><strong>选择终止门店</strong></p>
                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                	<tr>
						<th style="width:25px"></th>
                		<th style="width:70px">门店编号</th>
						<th style="width:100px">门店名称</th>
						<th style="width:130px">门店地址</th>
						<th style="width:65px">门店负责人</th>
						<th style="width:70px">门店负责人电话</th>
						<th style="width:95px">门店维护人</th>
		            </tr>
		            <c:set var="index" value="0"></c:set>
					   <c:forEach items="${storeList}" var="item"  varStatus="listIndex">
							<tr>
								<td>
								<input type="checkbox"  id="hidStoreState${index}" name="storeChk"
									<c:if test="${(item.storeState eq '2' || item.storeState eq '1' || item.disabledFlag=='2') &&  item.contractStopId ne gpContractChangeInfo.id}">
										disabled="true" 
									</c:if>
									<c:if test="${item.contractStopId eq gpContractChangeInfo.id }">
                                               checked="checked"
                                    </c:if>
									value="${item.storeId}">
								</td>
								<td>${item.storeNo}
									<input type="hidden" name="changeStoreId${item.storeId}" value="${item.gpContractStoreStopId}">
									<input type="hidden" name="storeNo${item.storeId}" value="${item.storeNo}">
								</td>
								<td title="${item.name}" class="text-overflow">${item.name}
									<input type="hidden" name="storeName${item.storeId}" value="${item.name}">
								</td>
								<td title="${item.address}" class="text-overflow">${item.address}
									<input type="hidden" name="storeAddress${item.storeId}" value="${item.address}">
								</td>
								<td>${item.storeManager}
									<input type="hidden" name="storeManager${item.storeId}" value="${item.storeManager}">
								</td>
								<td>${item.storeManagerPhone}
									<input type="hidden" name="storeManagerPhone${item.storeId}" value="${item.storeManagerPhone}">
								</td>
								<td>${item.maintainerName}（${item.maintainer}）
									<input type="hidden" name="maintainer${item.storeId}" value="${item.maintainer}">
									<input type="hidden" name="maintainerName${item.storeId}" value="${item.maintainerName}">
								</td>
							</tr>
							<c:set var="index" value="${index+1}"></c:set>
						</c:forEach> 
                </table>
				<div>
					<p style="color: red;font-size:12px;"><i>* </i>注：
						灰色不可选代表该门店已做公盘合同终止操作</p>
				</div>
				<p><strong>合作终止情况</strong></p>
                <table class="table-sammary">
                	 <tr>
                        <td colspan="3">
                            <div class="form-group">
								<label class="fw-normal w125 text-right" for="stopDate"><i>* </i>终止时间：</label>
								<input  type="text" class="calendar-icon " style="width:200px;"  name="stopDate" id="stopDate"
									value="${sdk:ymd2(gpContractChangeInfo.stopDate)}"
								 onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" class="ipttext Wdate"  notnull="true"/>
								<span class="fc-warning"></span>
							</div>
                        </td>
                    </tr>
					<tr>
                        <td colspan="3">
							<div class="form-group">
								<label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>预计退款金额：</label>
								<!-- <input  name="ptBackAmount" id="ptBackAmount" notnull="true" /> -->
								<input type="number" class="form-control" onchange="setTwoNumberDecimal(this)" id="ptBackAmount" value="${gpContractChangeInfo.ptBackAmount}"
								style="width:200px;"  name="ptBackAmount"  notnull="true">
								<span class="fc-warning"></span>
					            <!-- <span style="color:#FF0000;margin-left:25px;" id="errorTip2" class="errorTip2"> &nbsp;本次退款金额请填写两位小数以内的数字格式!</span> -->
							</div>
                        </td>
                    </tr>
					<tr>
                        <td colspan="3">
							<div class="form-group">
								<label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>终止具体原因：</label>
								<textarea maxlength="150" name="stopReason" id="stopReason" cols="30" rows="10"  style="resize: none;"  notnull="true" >${gpContractChangeInfo.stopReason}</textarea>
								<span class="fc-warning"></span>
							</div>
                        </td>
                    </tr>
					<tr>
                        <td colspan="3">
							<div class="form-group">
								<label class="fw-normal w125 text-right" style="vertical-align: top;"><i>* </i>终止方案阐述：</label>
								<textarea maxlength="150" name="stopDescribe" id="stopDescribe"  cols="30" rows="10"  style="resize: none;"  notnull="true" >${gpContractChangeInfo.stopDescribe}</textarea>
								<span class="fc-warning"></span>
							</div>
                        </td>
                    </tr>
					<tr>
                        <td colspan="3">
							<div class="form-group">
								<label class="fw-normal w125 text-right" style="vertical-align: top;">备注：</label>
								<textarea maxlength="150" name="remarks" id="remarks" cols="30" rows="10"  style="resize: none;" >${gpContractChangeInfo.remarks}</textarea>
								<span class="fc-warning"></span>
							</div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</form>


<div  class="container theme-hipage ng-scope" role="main">
    <h4>
        <strong>附件</strong>
    </h4>
</div>				
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						<i>*</i>终止合作协议书/单方解除函
					</h4>
					<div class="thumb-xs-box" id="stopContractBox">
					<c:if test="${gpContractChangeInfo.stopContractFileList != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${gpContractChangeInfo.stopContractFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="终止合作协议书/单方解除函" src="${list.fileAbbrUrl}" class="empPhoto" />
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
								<input type="hidden" name="fileTypeId" value="1039" />
								<input type="hidden" name="fileSourceId" value="11" />
							  </div>
		                </c:forEach> 
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${gpContractChangeInfo.stopContractFileList != null && gpContractChangeInfo.stopContractFileList.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
				                <input type="hidden" name="fileTypeId" value="1039" />
				                <input type="hidden" name="fileSourceId" value="11" />
				                <input type="hidden" name ="companyId" value="${companyInfo.id}">
							</a>
						</div>
					</div>
					<i class="fontset">注：版本须经法务出具，终止合作协议需中介盖章签字。</i>
				</div>
     </div>
 </div>
<!-- 保证金收据 --> 
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						保证金收据
					</h4>
					<div class="thumb-xs-box" id="receiptBox">
					<c:if test="${gpContractChangeInfo.receiptFileList != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${gpContractChangeInfo.receiptFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="保证金收据" src="${list.fileAbbrUrl}" class="empPhoto" />
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
								<input type="hidden" name="fileTypeId" value="1040" />
								<input type="hidden" name="fileSourceId" value="11" />
							  </div>
		                </c:forEach> 
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${gpContractChangeInfo.receiptFileList != null && gpContractChangeInfo.receiptFileList.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
				                <input type="hidden" name="fileTypeId" value="1040" />
				                <input type="hidden" name="fileSourceId" value="11" />
				                <input type="hidden" name ="companyId" value="${companyInfo.id}">
							</a>
						</div>
					</div>
						<i class="fontset">注：请清晰上传我司开具给中介的保证金收据。</i>
				</div>
     </div>
 </div> 
<!-- 其它-->	
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						其他
					</h4>
					<div class="thumb-xs-box" id="othersFileList">
					<c:if test="${gpContractChangeInfo.othersFileList != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${gpContractChangeInfo.othersFileList}" var="list" varStatus="status">
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
								<input type="hidden" name="fileTypeId" value="1041" />
								<input type="hidden" name="fileSourceId" value="11" />
							  </div>
		                </c:forEach> 
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${gpContractChangeInfo.othersFileList != null && gpContractChangeInfo.othersFileList.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
				                <input type="hidden" name="fileTypeId" value="1041" />
				                <input type="hidden" name="fileSourceId" value="11" />
				                <input type="hidden" name ="companyId" value="${companyInfo.id}">
							</a>
						</div>
					</div>
				</div>
     </div>
 </div> 								 		
<div class="text-center">
     <a href="javascript:void(0)" onclick="doSave()" class="btn btn-primary">保存</a>
	<a href="${ctx}/gpContractChange" class="btn btn-default mgl20">取消</a>
</div>
		
</body>
</html>
