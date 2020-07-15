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
<script type="text/javascript" src="${ctx}/meta/js/keFuOrder/keFuOrder.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/relate/relateCompany.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/report/expand/bootstrap-select.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/cssreport/bootstrap-select.css?_v=${vs}">
<link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">
<script type="text/javascript" src="${ctx}/meta/js/report/expand/multi.select.js?_v=${vs}"></script>
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
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>

<form id="keFuOrderAddForm">
	<input type ="hidden"  id ="id"   name="id" value ="${keFuOrder.id}"/>
	<input type="hidden" name="companyId" id="companyId" value="${keFuOrder.companyId}">
	<input type="hidden" name="storeId" id="storeId" value="${keFuOrder.storeId}">
	<input type="hidden" name="cityNo" id="cityNo" value="${keFuOrder.cityNo}">
	<input type="hidden" id="consultProduct" name="consultProduct" value="${keFuOrder.consultProduct}"/>
	<input type="hidden" id="categoryTwoValue" name="categoryTwoValue" value="${keFuOrder.categoryTwo}"/>
	<input type="hidden" id="categoryTwoText" name="categoryTwoText" value="${keFuOrder.categoryTwoNm}"/>
	<input type="hidden" id="oldDealStatus" name="oldDealStatus" value="${keFuOrder.dealStatus}"/>
	<input type="hidden" id="dealStatus" name="dealStatus"/>
	<input type="hidden" name="oldfileRecordMainIds" id="oldfileRecordMainIds"  value = "${keFuOrder.fileRecordMainIds}" />
	<input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds" value ="${keFuOrder.fileRecordMainIds}"  />
    <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4>
                	<strong>修改工单</strong>
                	<a type="button" class="btn btn-primary" href="${ctx}/keFuOrder?searchParam=1">返回</a>
                </h4>
                <p><strong>基本信息</strong></p>
                <table class="table-sammary">
                    <col style="width:125px;">
                    <col style="width:auto;">
                    <col style="width:146px;">
                    <col style="width:auto;">
                    <tr>
                        <td class="talabel required">归属城市</td>
                        <td>
                            <div class="control-group">
	                            <select class="form-control w300" title="" id="cityNo" readonly  name="cityNo" notnull="true">
                                          <option value="${keFuOrder.cityNo}" >${keFuOrder.cityName}</option>
		                        </select>
                            </div>
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">经纪公司</td>
                        <td>
                            <input type="text" class="form-control w300" id="companyInfo" name="companyInfo" placeholder=""  value="${keFuOrder.companyName}" readonly="readonly" style="background-color: #F9F9F9">
                        </td>
                        <td class="talabel">门店</td>
                        <td>
                        	<div class="control-group">
                				<span class="control-select">
                					<select class="form-control w300" title="" id="storeInfo" readonly  name="storeInfo" >
			                              <option value="${keFuOrder.storeId}" >
			                              		<c:if test="${!empty keFuOrder.storeId}">
			                              			${keFuOrder.storeName}(${keFuOrder.storeNo} &nbsp; ${keFuOrder.storeAddress})
			                              		</c:if>
			                              </option>
		                            </select>
                				</span>
                            </div>
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <c:choose>
						<c:when test="${operatorFlag eq 0}">
							<tr>
		                        <td class="talabel required">客户姓名</td>
		                        <td>
		                            <input type="text" class="form-control w300" id="customerName" value="${keFuOrder.customerName}" name="customerName" placeholder="请输入客户姓名"  notnull="true" maxlength="20">
		                            <span class="fc-warning"></span>
		                        </td>
		                        <td class="talabel required">客户电话</td>
		                        <td>
		                            <input type="text" class="form-control w300" id="customerTel" value="${keFuOrder.customerTel}" name="customerTel" placeholder="请输入客户电话" notnull="true" maxlength="11" dataType="phone" >
		                            <span class="fc-warning"></span>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="talabel required">咨询产品名称</td>
		                        <td>
		                            <div class="multi-select w300">
		                                <input type="hidden" id="selectConsultProduct" name="selectConsultProduct" value="${fn:replace(keFuOrder.consultProduct,',',',')}" class="multi-select-value" readonly="readonly" >
		                                <input type="text" name="selectConsultProductNm" id="selectConsultProductNm" value="${fn:replace(keFuOrder.consultProductNm,',',';')}"  class="form-control multi-select-text w300" readonly>
		                                <ul class="multi-select-list">
		                                    <c:if test="${!empty orderProductList}">
		                                        <c:forEach items="${orderProductList}" var="list">
		                                            <li class="multi-select-item">
		                                                <label><input type="checkbox" value="${list.dicCode}"
		                                                              data-text="${list.dicValue}"
		                                                              <c:if test="${fn:contains(keFuOrder.consultProduct,list.dicCode)}">checked</c:if>><span>${list.dicValue}</span></label>
		                                            </li>
		                                        </c:forEach>
		                                    </c:if>
		                                </ul>
		                            </div>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="talabel required">问题一级分类</td>
		                        <td>
		                            <div class="control-group">
		                				<span class="control-select">
		                					<select class="form-control w300" title="" id="categoryOne"  name="categoryOne" notnull="true">
					                              <option value="" >请选择</option>
				                					    <c:forEach items="${categoryOneList}" var="list">
		                                                    <option value="${list.dicCode}" <c:if test="${list.dicCode eq keFuOrder.categoryOne}"> selected</c:if>>${list.dicValue}</option>
		                                                </c:forEach>
				                            </select>
		                				</span>
		                            </div>
		                            <span class="fc-warning"></span>
		                        </td>
		                        <td class="talabel required">问题二级分类</td>
		                        <td>
		                            <div class="control-group">
		                				<span class="control-select">
		                					<select class="form-control w300" title="" id="categoryTwo"   name="categoryTwo" notnull="true">
					                              <option value="" >请选择</option>
				                            </select>
		                				</span>
		                            </div>
		                            <span class="fc-warning"></span>
		                        </td>
		                    </tr>
		                    
		                    <tr>
		                        <td class="talabel required">问题摘要</td>
		                        <td>
		                            <input type="text" class="form-control" value="${keFuOrder.questionTopic}" id="questionTopic"  name="questionTopic"  notnull="true" style="width: 600px;">
		                        	 <span class="fc-warning"></span>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="talabel required">问题描述</td>
		                        <td>
		                            <textarea maxlength="300" name="questionDesc" id="questionDesc" cols="30" rows="10" notnull="true" style="word-break:break-all;word-wrap: break-word;resize: none;width:895px;">${keFuOrder.questionDesc}</textarea>
		                            <span class="fc-warning"></span>
		                        </td>
		                    </tr>
		                    <tr>
		                		<td class="talabel required">工单优先级</td>
		                		<td>
		                			<c:forEach items="${orderDescList}" var="list">
		          						<input type="radio" value="${list.dicCode}" id="${list.dicCode}" id="questionLevel" name="questionLevel" <c:if test="${list.dicCode eq keFuOrder.questionLevel}">checked</c:if>>
										<label class="fon-normal small">${list.dicValue}</label>
									</c:forEach>
		                        </td>
		                	</tr>
						</c:when>
						<c:otherwise>
								<tr>
		                        <td class="talabel required">客户姓名</td>
		                        <td>
		                            <input type="text" class="form-control w300" id="customerName" readonly value="${keFuOrder.customerName}" name="customerName" placeholder="请输入客户姓名"  notnull="true" maxlength="20">
		                            <span class="fc-warning"></span>
		                        </td>
		                        <td class="talabel required">客户电话</td>
		                        <td>
		                            <input type="text" class="form-control w300" id="customerTel" readonly value="${keFuOrder.customerTel}" name="customerTel" placeholder="请输入客户电话" notnull="true" maxlength="11">
		                            <span class="fc-warning"></span>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="talabel required">咨询产品名称</td>
		                        <td>
		                            <div class="multi-select w300">
		                                <input type="hidden" id="selectConsultProduct" name="selectConsultProduct" value="${fn:replace(keFuOrder.consultProduct,',',',')}" class="multi-select-value" readonly="readonly" >
		                                <input type="text" name="selectConsultProductNm" id="selectConsultProductNm" value="${fn:replace(keFuOrder.consultProductNm,',',';')}"  class="form-control multi-select-text w300" readonly>
		                                <ul class="multi-select-list">
		                                    <c:if test="${!empty orderProductList}">
		                                        <c:forEach items="${orderProductList}" var="list">
		                                            <li class="multi-select-item">
		                                                <label><input type="checkbox" value="${list.dicCode}"
		                                                              data-text="${list.dicValue}"
		                                                              <c:if test="${fn:contains(keFuOrder.consultProduct,list.dicCode)}">checked</c:if> disabled><span>${list.dicValue}</span></label>
		                                            </li>
		                                        </c:forEach>
		                                    </c:if>
		                                </ul>
		                            </div>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="talabel required">问题一级分类</td>
		                        <td>
		                            <div class="control-group">
		                				<span class="control-select">
		                					<select class="form-control w300" title="" id="categoryOne"  name="categoryOne" notnull="true">
                                               <option value="${keFuOrder.categoryOne}">${keFuOrder.categoryOneNm}</option>
				                            </select>
		                				</span>
		                            </div>
		                            <span class="fc-warning"></span>
		                        </td>
		                        <td class="talabel required">问题二级分类</td>
		                        <td>
		                            <div class="control-group">
		                				<span class="control-select">
		                					<select class="form-control w300" title="" id="categoryTwo"   name="categoryTwo" notnull="true">
					                              <option value="${keFuOrder.categoryTwo}">${keFuOrder.categoryTwoNm}</option>
				                            </select>
		                				</span>
		                            </div>
		                            <span class="fc-warning"></span>
		                        </td>
		                    </tr>
		                    
		                    <tr>
		                        <td class="talabel required">问题摘要</td>
		                        <td>
		                            <input type="text" class="form-control" value="${keFuOrder.questionTopic}" readonly id="questionTopic"  name="questionTopic"  notnull="true" style="width: 600px;">
		                        	 <span class="fc-warning"></span>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td class="talabel required">问题描述</td>
		                        <td>
		                            <textarea maxlength="300" name="questionDesc" id="questionDesc" readonly cols="30" rows="10" notnull="true" style="word-break:break-all;word-wrap: break-word;resize: none;width:895px;">${keFuOrder.questionDesc}</textarea>
		                            <span class="fc-warning"></span>
		                        </td>
		                    </tr>
		                    <tr>
		                		<td class="talabel required">工单优先级</td>
		                		<td>
		                			<c:forEach items="${orderDescList}" var="list">
		          						<input type="radio" value="${list.dicCode}" id="${list.dicCode}" id="questionLevel" name="questionLevel" disabled <c:if test="${list.dicCode eq keFuOrder.questionLevel}">checked</c:if>>
										<label class="fon-normal small">${list.dicValue}</label>
									</c:forEach>
		                        </td>
		                	</tr>
						</c:otherwise>
					</c:choose>
                	<tr>
                        <td class="talabel required">经办人</td>
                        <td>
                        	<input type="hidden" name="operatorValue" id ="operatorValue" value="${keFuOrder.userCode}"/>
                            <input type="text" class="form-control w300" id="operatorName" name="operatorName" placeholder="" value="${keFuOrder.userName}"  readonly="readonly" style="background-color: #F9F9F9">
                            <button type="button" class="btn btn-primary"  onclick="javascript:toChooseOperator();" style="vertical-align: top;">选择</button>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</form>
<div class="container theme-hipage ng-scope" role="main">
    <p><strong>附件</strong></p>
</div>
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<!-- <h4 class="thumb-title">
						其他
					</h4> -->
					<div class="thumb-xs-box" id="attachmentFile">
					<c:if test="${keFuOrder.orderFileList != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${keFuOrder.orderFileList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="" src="${list.fileAbbrUrl}" class="empPhoto" />
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
								<input type="hidden" name="fileTypeId" value="1058" />
								<input type="hidden" name="fileSourceId" value="17" />
							  </div>
		                </c:forEach> 
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${keFuOrder.orderFileList != null && keFuOrder.orderFileList.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
				                <input type="hidden" name="fileTypeId" value="1058" />
				                <input type="hidden" name="fileSourceId" value="17" />
				                <input type="hidden" name ="companyId" value="${keFuOrder.companyId}">
							</a>
						</div>
					</div>
				</div>
     </div>
 </div> 
<div class="lockHandle2"></div>
<div class="text-center">
	<c:if test="${keFuOrder.dealStatus eq 24201 }">
	      <div class="btn-group dropup">
	        <button type="button" class="btn btn-primary " onclick="doSave('1')" >提交为未处理</button>
	        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <span class="caret"></span>
	        </button>
	        <ul class="dropdown-menu dropdown-menu-right">
	          <li><a href="javascript:void(0)" onclick="doSave('1')" >提交为未处理</a></li>
	          <li><a href="javascript:void(0)" onclick="doSave('2')">提交为处理中</a></li>
	          <li><a href="javascript:void(0)" onclick="doSave('3')">提交为处理完成</a></li>
	        </ul>
	     </div>
	</c:if>
	<c:if test="${keFuOrder.dealStatus eq 24202 }">
	      <div class="btn-group dropup">
	        <button type="button" class="btn btn-primary " onclick="doSave('2')" >提交为处理中</button>
	        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <span class="caret"></span>
	        </button>
	        <ul class="dropdown-menu dropdown-menu-right">
	          <li><a href="javascript:void(0)" onclick="doSave('2')">提交为处理中</a></li>
	          <li><a href="javascript:void(0)" onclick="doSave('3')">提交为处理完成</a></li>
	        </ul>
	     </div>
	</c:if>
	<c:if test="${keFuOrder.dealStatus eq 24203 }">
	      <div class="btn-group dropup">
	        <button type="button" class="btn btn-primary " onclick="doSave('3')" >提交为处理完成</button>
	        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	          <span class="caret"></span>
	        </button>
	        <ul class="dropdown-menu dropdown-menu-right">
	          <li><a href="javascript:void(0)" onclick="doSave('3')">提交为处理完成</a></li>
	        </ul>
	     </div>
	</c:if>
    <a href="${ctx}/keFuOrder" class="btn btn-default mgl20">取消</a>
  </div>
</div>
</body>

</html>
<script type="text/javascript">
$(function(){
	var categoryOne= $("#categoryOne").val();
	var categoryTwoValue= $("#categoryTwoValue").val();
	var categoryTwoText= $("#categoryTwoText").val();
	var oldDealStatus= $("#oldDealStatus").val();
	if("" !=categoryOne){
		if(oldDealStatus != 24201){
			var result = "<option value='" + categoryTwoValue + "' selected>"
			+ categoryTwoText + "</option>";
			$("#categoryTwo").html('');
			$("#categoryTwo").append(result);
		}else {
			var url = BASE_PATH +  "/keFuOrder/getCategoryTwo";
			var params = {categoryOne:categoryOne};
			ajaxGet(url, params, function(data) {
				var result = "<option value=''>请选择</option>";
				$.each(data.returnValue, function(n, value) {
					if(value.dicCode == categoryTwoValue){
							result += "<option value='" + value.dicCode + "' selected>"
						+ value.dicValue + "</option>";
					}else{
						result += "<option value='" + value.dicCode + "'>"
						+ value.dicValue + "</option>";
					}
				});
				$("#categoryTwo").html('');
				$("#categoryTwo").append(result);
			}, function() {
			});
		}
	}else {
		var result = "<option value=''>请选择</option>";
		$("#categoryTwo").html('');
		$("#categoryTwo").append(result);
	}
	
});
</script>
