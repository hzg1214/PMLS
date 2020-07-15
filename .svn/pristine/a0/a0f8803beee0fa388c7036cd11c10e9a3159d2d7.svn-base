<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/company/company.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/contacts/contacts.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/relate/relateUtil.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/relate/relateStores.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	 <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
            
            	<form id="companyEditForm">
					<input type="hidden" name="fangyouCompanyId" value="${info.fangyouCompanyId}" />
					<input type="hidden" name="_method" value="put" /> 
					
					<!-- 公司联系人Id -->
					<input type="hidden" id="companyId" name="id" value="${info.id}" />
					
					<!-- 联系人Id -->
					<input type="hidden" name="contactId" value="${info.contactList[0].id}" />
				
					<!-- 关联的门店id 集合 -->
					<input type ="hidden"  id = "storeIdArray"   name = "storeIdArray"  />
            
            
                <h4>
                	<strong>编辑公司</strong>
                	<a href="javascript:Company.back();" class="btn btn-primary">返回</a>
                </h4>
                <p><strong>主要信息</strong></p>
                <table class="table-sammary">
               		<col style="width:95px;">
               		<col style="width:auto;">
               		<col style="width:95px;">
               		<col style="width:auto;">
                    <tr>
                        <td class="talabel required">公司名称</td>
                        <td>
                            <c:choose>
                                <c:when test="${num > 0}">
                                    <input type="text" class="form-control w300" id="companyName" name="companyName"  placeholder="营业执照的名称" value="${info.companyName}" notnull="true" maxlength="50"  readonly="readonly">
                                </c:when>
                                <c:otherwise>
                                    <input type="text" class="form-control w300" id="companyName" name="companyName"  placeholder="营业执照的名称" value="${info.companyName}" notnull="true" maxlength="50">
                                </c:otherwise>
                            </c:choose>
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">公司编号</td>
                        <td><input type="text" class="form-control w300" id="companyNo" name="companyNo" value="${info.companyNo}" readonly="readonly"><span class="fc-warning"></span></td>
                    </tr>
                    <tr>
                        <td class="talabel">公司来源</td>
                        <td><t:dictSelect field="sourceId" id="sourceId" xmlkey="LOOKUP_Dic" dbparam="P1:105" nullOption="请选择..." defaultVal="${info.sourceId}" ></t:dictSelect></td>
                        <td class="talabel required">营业执照号</td>
                        <td><input type="text" class="form-control w300" id="businessLicenseNo" name="businessLicenseNo" maxlength="30" value="${info.businessLicenseNo}"><span class="fc-warning"></span></td>
                    </tr>
                    <tr>
                        <td class="talabel">公司规模</td>
                        <td><t:dictSelect field="companyScale" id="companyScale" xmlkey="LOOKUP_Dic" dbparam="P1:106" nullOption="请选择..." defaultVal="${info.companyScale}" ></t:dictSelect></td>
                        <td class="talabel">经纪人数</td>
                        <td><input type="text" class="form-control w300" id="predictAccountCount" name="predictAccountCount" value="${info.predictAccountCount}" datatype="posNumWithZero" ><span class="fc-warning"></span></td>
                    </tr>
                    <tr>
                        <td class="talabel">原系统</td>
                        <td><t:dictSelect field="originalVersions" id="originalVersions" xmlkey="LOOKUP_Dic" dbparam="P1:108" nullOption="请选择..." defaultVal="${info.originalVersions}" ></t:dictSelect></td>
                        <td class="talabel required">法定代表人</td>
                        <td><input type="text" class="form-control w300" id="legalPerson" name="legalPerson"  datatype="onlyCnEnNum" maxlength="15" value="${info.legalPerson}"><span class="fc-warning"></span></td>
                    </tr>
                    <tr>
                        <td class="talabel required">联系电话</td>
                        <td><input type="text" class="form-control w300" id="contactNumber" name="contactNumber" placeholder=""  notnull="true"  maxlength="11" value="${info.contactNumber}"><span class="fc-warning"></span></td>
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
                                    <select class="form-control" title="" id="districtNo" name="districtNo">
                                        <option <c:if test="${empty storeDetail.districtNo}">selected="selected"</c:if> value="">请选区域</option>
                                        <c:if test="${!empty districtList}">
                                            <c:forEach items="${districtList}" var="district">
                                                <option value="${district.districtNo}" <c:if test="${info.districtNo eq district.districtNo}">selected="selected"</c:if>>${district.districtName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </span>
                                 <input type="text" class="form-control w300" id="address" name="address" value="${info.address}" placeholder="具体地址信息"  dataType="normal2"  notnull="true"  maxlength="50" style="vertical-align: middle;"><span class="fc-warning"></span>
                            </div>
                        </td>
                    </tr>
                </table>
				</form>
                <p><strong>门店信息</strong></p>
                <ul class="list-inline half form-inline">
                    <li>
                        <button type="button" class="btn btn-primary" onclick="javascript:relateStores('company');" >关联门店信息</button>
                    </li>
                </ul>
                <div>
	                <table  id="relateStoreTableId"  class="table table-striped table-bordered table-hover">
						<tr>
							<!-- <th class="text-center">
								<input value="checkbox" type="checkbox">
							</th> -->
							<!-- <th>门店名称</th>
							<th>所在地址</th>
							<th>联系人</th> -->
							 <th>门店名称</th>
			                <th>所在区域</th>
			                <th>门店地址</th>
			                <th>创建日期</th>
			                <th>操作</th>
						</tr>
				
						<c:forEach items="${info.storeList}" var="storelist">
		                   	<tr>
		                   		<td style='display:none'><input name='storeIds'  id='storeIds${storelist.storeId}'   type='hidden' value='${storelist.storeId}'></td>
		                   		<td style="text-align:center;" title="${storelist.name}">
		                 			${fn:substring(storelist.name, 0, 50)}
		                 			<c:if test="${fn:length(storelist.name) >= '50'}">
									...
									</c:if>
		                 		</td>
		                 		<td style="text-align:center;" title="${storelist.districtName}">
		                 			${fn:substring(storelist.districtName, 0, 20)}
		                 			<c:if test="${fn:length(storelist.districtName) >= '20'}">
									...
									</c:if>
		                 		</td>
		                   		<td style="text-align:center;" title="${storelist.addressDetail}">
		                 			${fn:substring(storelist.addressDetail, 0, 50)}
		                 			<c:if test="${fn:length(storelist.addressDetail) >= '50'}">
									...
									</c:if>
		                 		</td>
		                   		<td style="text-align:center;">${storelist.dateCreate}</td>
		                   		<td><a href='javascript:void(0)' onclick='removeTr(this,"company")'>移除</a></td>
		                   	</tr>
						</c:forEach> 
			
				     </table>
               </div>
            </div>
            <div class="text-center">
              <button type="button" class="btn btn-primary" onclick="javascript:Company.update('');" >保存</button>  
              
              <button type="button" class="btn btn-default" onclick="javascript:Company.back();">取消</button>
            </div>
        </div>
    </div>
</body>
</html>
