<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/store/storeEdit.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/store/storeMap.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/store/storeUpload.js?_v=${vs}"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=${sysConfig.baiduApiKey}" type="text/javascript"></script>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<form id="storeAddForm">
		<div class="container theme-hipage ng-scope" role="main">
			<div class="row">
				<div class="page-content">
					<input type="hidden" name="storeStatus" value="0">
					<input type="hidden" id="cityName" value="${userInfo.cityName}">
					<input type="hidden" name="longitude" id="longitude" value="">
	                <input type="hidden" name="latitude" id="latitude" value="">
					<h4><strong>新建门店</strong></h4>
                	<p><strong>基本信息</strong></p>
                	<table class="table-sammary">
                		<col style="width:85px;">
                		<col style="width:auto;">
                		<tr>
                			<td class="talabel required">门店名称：</td>
                			<td>
	                			<input type="text" class="form-control w300" name="name" id="name" placeholder="例：上海某某地产闸北店" notnull="true">
	                			<span class="fc-warning"></span>
                			</td>
                		</tr>
                		<tr>
                			<td class="talabel">门店规模：</td>
                			<td><t:dictSelect field="storeScale" id="storeScale" xmlkey="LOOKUP_Dic" dbparam="P1:106" nullOption="请选择..." ></t:dictSelect><span class="fc-warning"></span></td>
                		</tr>
                		<tr>
                			<td class="talabel required">联系地址：</td>
                			<td>
                				<div class="control-group">
                					<span class="control-select">
                						<select class="form-control" title="" id="cityNo" name="cityNo" readonly>
			                            	<option value="${userInfo.cityNo}" selected>${userInfo.cityName}</option>
		                            	</select>
                					</span>
                					<span class="control-select">
                						<select class="form-control" title="" id="districtNo" name="districtNo">
			                            	<option selected="selected" value="">请选区域</option>
			                            	<c:if test="${!empty districtList}">
				                                <c:forEach items="${districtList}" var="district">
				                                    <option value="${district.districtNo}">${district.districtName}</option>
				                                </c:forEach>
			                            	</c:if>
			                            </select>
                					</span>
		                            <input type="text" class="form-control w300" name="address" id="address" placeholder="具体地址信息" notnull="true"  value="" maxlength="100" dataType="address">
		                            <button type="button" class="btn btn-primary" onclick="btnMark();">标记地图</button>
		                            <span class="fc-warning"></span>
                				</div>
                			</td>
                		</tr>
                		<tr>
                			<td class="talabel"></td>
                			<td>
                				<div style="width:600px;height:300px;border:#ccc solid 1px;border-radius: 4px;font-size:12px;display:inline-block" id="mapDiv"></div>
                			</td>
                		</tr>
                	</table>
	                <p><strong>附属信息</strong></p>
	                
	                <input type="hidden" name="fileRecordMainId" id="fileRecordMainId" />
	                
	                <input type="hidden" name="fileTypeId" value="4" />
	                <input type="hidden" name="fileSourceId" value="2" />
	                
	                <!-- 渠道系统关系fileNo -->
	                <input type="hidden" name="fileNo"  id="fileNo"/>
	                
	                
	                <table class="table-sammary">
	                	<col style="width:85px;">
	                	<col style="width:auto;">
	                	<tr>
	                		<td class="talabel required">门店图片：</td>
	                		<td>
	                			<span id="showImg">
	                            	<input type="hidden" id="fileUrl" name="fileUrl" value="${fileUrl}" />
		                            <a href="javascript:void(0)" target=" _blank" style="  display: inline-block;  height: auto; vertical-align: -webkit-baseline-middle;">
			                            <img src="${ctx}/meta/images/store.png" alt="" class="img">
			                            <br /><label class="fileName">${list.fileName}</label>
		                            </a>
	                            </span>
	                            <span class="fc-warning">(总大小不超过5M)</span>
	                		</td>
	                	</tr>
	                	<tr>
	                		<td class="talabel"></td>
	                		<td>
	                			<div class="form-group">
	                            	<span class="abso_file btn btn-default">浏览...</span>
	                                <input class="inputstyle" type="file" id="btnfileUpload" name="fileName" onchange="upload(this,'storeAddForm');">
	                            </div>
	                		</td>
	                	</tr>
	                </table>
				</div>
				<div class="text-center">
		           	<button type="button" onclick="Add();" class="btn btn-primary">保存</button>
					<a href="${ctx}/store" class="btn btn-default mgl10">取消</a>
	            </div>
			</div>
		</div>
	</form>
</body>
</html>
