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
	<input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"  />
	<input type="hidden" name="companyId" id="companyId" >
	<input type="hidden" name="companyNo" id="companyNo1" >
	<input type="hidden" name="storeId" id="storeId" >
	<input type="hidden" name="cityNo" id="cityNo" >
	<input type="hidden" name="dealStatus" id="dealStatus" value="24201">
	<input type="hidden" id="consultProduct" name="consultProduct" />
    <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4><strong>新建工单</strong>
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
                				<select class="form-control w300 selectpicker" title="" id="selectCity" name="selectCity" notnull="true" data-live-search="true" ></select>
	                            <input type="hidden"  name="selectCityNm" id="selectCityNm" >
                            </div>
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel">经纪公司</td>
                        <td>
                            <input type="text" class="form-control w300" id="companyInfo" name="companyInfo" placeholder=""   readonly="readonly" style="background-color: #F9F9F9">
                            <button type="button" class="btn btn-primary"  onclick="javascript:relateCompanyInfo('keFuOrder');" style="vertical-align: top;">选择公司</button>
                        </td>
                        <td class="talabel">门店</td>
                        <td>
                        	<div class="control-group">
                				<span class="control-select">
                					<select class="form-control w300" title="" id="storeInfo" readonly  name="storeInfo" >
			                              <option value="" >请选择</option>
		                            </select>
                				</span>
                            </div>
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">客户姓名</td>
                        <td>
                            <input type="text" class="form-control w300" id="customerName" name="customerName" placeholder="请输入客户姓名"  notnull="true" maxlength="20">
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">客户电话</td>
                        <td>
                            <input type="text" class="form-control w300" id="customerTel" name="customerTel" placeholder="请输入客户电话" notnull="true" maxlength="11" >
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">咨询产品名称</td>
                        <td>
                            <div class="multi-select w300">
                                <input type="hidden" id="selectConsultProduct" name="selectConsultProduct" class="multi-select-value" readonly="readonly" >
                                <input type="text" name="selectConsultProductNm" id="selectConsultProductNm"  class="form-control multi-select-text w300" readonly>
                                <ul class="multi-select-list">
                                    <c:if test="${!empty orderProductList}">
                                        <c:forEach items="${orderProductList}" var="list">
                                            <li class="multi-select-item">
                                                <label>
                                                	<input type="checkbox" value="${list.dicCode}" data-text="${list.dicValue}">
                                                	<span>${list.dicValue}</span>
                                                </label>
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
                                                    <option value="${list.dicCode}">${list.dicValue}</option>
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
                            <input type="text" class="form-control" id="questionTopic"  name="questionTopic"  notnull="true" style="width: 600px;">
                        	 <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                        <td class="talabel required">问题描述</td>
                        <td>
                            <textarea maxlength="300" name="questionDesc" id="questionDesc" cols="30" rows="10" notnull="true" style="word-break:break-all;word-wrap: break-word;resize: none;width:895px;"></textarea>
                            <span class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr>
                		<td class="talabel required">工单优先级</td>
                		<td>
                			<c:forEach items="${orderDescList}" var="list">
          						<input type="radio" value="${list.dicCode}" id="${list.dicCode}"
                                <c:if test="${list.dicCode==24402}">
                                        checked="checked"
                                </c:if>
                                       id="questionLevel" name="questionLevel">
								<label class="fon-normal small">${list.dicValue}</label>
							</c:forEach>
                        </td>
                	</tr>
                	<tr>
                        <td class="talabel required">经办人</td>
                        <td>
                        	<input type="hidden" name="operatorValue" id ="operatorValue" value="${userCode}"/>
                            <input type="text" class="form-control w300" value="${userName}" id="operatorName" name="operatorName" placeholder=""   readonly="readonly" style="background-color: #F9F9F9">
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

<!-- 营业执照 -->
<div class="container theme-hipage ng-scope" role="main" id="csMemberFileBox1">
    <div class="row">
        <div class="pd10">
            <h4 class="thumb-title">
                <!-- <i>*</i> -->
            </h4>
            <div class="thumb-xs-box" id="csMemberFileBox">
                <div class="thumb-xs-list item-photo-add">
                    <input type="hidden" name="limitSize" value="10">
                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                        <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"
                               data-name="photo-shinei">
                        <input type="hidden" name="fileTypeId" value="1058"/>
                        <input type="hidden" name="fileSourceId" value="17"/>
                        <input type="hidden" name="companyId" value="">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="lockHandle2"></div>
<div class="text-center">
    <!-- <a href="javascript:void(0)" onclick="doSave()" class="btn btn-primary">草签</a> -->
   <!--  <button class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="caret"></span>
    <ul class="dropdown-menu">
            <li><a href="javascript:void(0)" onclick="doSave()">首页</a></li>
            <li><a href="#">科技</a></li>
        </ul> -->
</div>
<div class="text-center">
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
    <a href="${ctx}/keFuOrder" class="btn btn-default mgl20">取消</a>
  </div>
</div>
</body>

</html>
