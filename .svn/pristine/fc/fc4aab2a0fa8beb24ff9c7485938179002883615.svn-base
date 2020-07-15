<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/company/company.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/relate/relateUtil.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/relate/relateStores.js?_v=${vs}"></script>

</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	<form id="companyAddForm">
	
	<!-- 关联的门店id 集合 -->
	<input type ="hidden"  id = "storeIdArray"   name = "storeIdArray"  />
	
	 <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4>
                	<strong>新建公司</strong>
                	<a href="javascript:Company.back();" class="btn btn-primary">返回</a>
                </h4>
                <p><strong>主要信息</strong></p>
                <div>
                	<table class="table-sammary">
                		<col style="width:95px;">
                		<col style="width:auto;">
                		<col style="width:80px;">
                		<col style="width:auto;">
                		<tr>
                			<td class="talabel required">公司名称</td>
                			<td><input type="text" class="form-control w300" id="companyName" name="companyName"  placeholder="营业执照的名称"  notnull="true" maxlength="50">
                            <span class="fc-warning"></span></td>
                            <td class="talabel">公司来源</td>
                			<td><t:dictSelect field="sourceId" id="sourceId" xmlkey="LOOKUP_Dic" dbparam="P1:105" nullOption="请选择..." ></t:dictSelect></td>
                		</tr>
                		<tr>
                			<td class="talabel required">营业执照号</td>
                			<td><input type="text" class="form-control w300" id="businessLicenseNo" name="businessLicenseNo"  maxlength="30"><span class="fc-warning"></span></td>
                			<td class="talabel">公司规模</td>
                			<td><t:dictSelect field="companyScale" id="companyScale" xmlkey="LOOKUP_Dic" dbparam="P1:106" nullOption="请选择..." ></t:dictSelect></td>
                		</tr>
                		<tr>
                			<td class="talabel">经纪人数</td>
                			<td><input type="text" class="form-control w300" id="predictAccountCount" name="predictAccountCount"  datatype="posNumWithZero" maxlength="6"><span class="fc-warning"></span></td>
                			<td class="talabel">原系统</td>
                			<td><t:dictSelect field="originalVersions" id="originalVersions" xmlkey="LOOKUP_Dic" dbparam="P1:108" nullOption="请选择..." ></t:dictSelect></td>
                		</tr>
                		<tr>
                			<td class="talabel required">法定代表人</td>
                			<td><input type="text" class="form-control w300" id="legalPerson" name="legalPerson"  datatype="onlyCnEnNum" maxlength="15">
                            <span class="fc-warning"></span></td>
                			<td class="talabel required">联系电话</td>
                			<td><input type="text" class="form-control w300" id="contactNumber" name="contactNumber" placeholder=""  notnull="true"  maxlength="11"><span class="fc-warning"></span></td>
                		</tr>
                		<tr>
                			<td class="talabel required">公司注册地址</td>
                			<td colspan="3">
                				<div class="control-group">
                					<span class="control-select">
                						<select class="form-control" title="" id="cityNo" name="cityNo" readonly>
			                            	<option value="${userInfo.cityNo}" selected>${userInfo.cityName}</option>
		                            	</select>
                					</span>
                					<span class="control-select">
                						<select class="form-control" title="" id="districtNo" name="districtNo" >
			                            	<option value="">请选择行政区</option>
			                            	<c:if test="${!empty districtList}">
				                                <c:forEach items="${districtList}" var="district">
				                                    <option value="${district.districtNo}">${district.districtName}</option>
				                                </c:forEach>
			                            	</c:if>
			                            </select>
                					</span>
                					 <input type="text" class="form-control w300" id="address" name="address" placeholder="具体地址信息" dataType="normal2" notnull="true" maxlength="50" style="vertical-align:middle;">
                            		<span class="fc-warning"></span>
                				</div>
                			</td>
                		</tr>
                	</table>
                </div>
                <p><strong>门店信息</strong></p>
                <ul class="list-inline half form-inline">
                    <li>
                        <button type="button" class="btn btn-primary" onclick="javascript:relateStores('company');" >关联门店信息</button>
                    </li>
                </ul>

            <table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
                	<tr>
		                <th>门店名称</th>
		                <th>所在区域</th>
		                <th>门店地址</th>
		                <th>创建日期</th>
		                <th>操作</th>
		            </tr>
		            ${htmls}
             </table>

            </div>
            <div class="text-center">
              <button type="button" id="companyAddButton" class="btn btn-primary" onclick="javascript:Company.add();" >保存</button>  
              
              <button type="button" class="btn btn-default" onclick="javascript:Company.back();">取消</button>
            </div>
        </div>
    </div>
	
	
	
	
	</form>

</body>

</html>
