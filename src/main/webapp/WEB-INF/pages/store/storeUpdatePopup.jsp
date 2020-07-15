<%@ page contentType="text/html;charset=UTF-8"
	trimDirectiveWhitespaces="true" language="java"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>修改门店信息</title>
<%@include file="/WEB-INF/pages/common/common.jsp" %>
<style type="text/css">
	.labelWidth{
		width:120px!important;
	}
	.selWidth{
		width:80px!important;
	}
	#selDistrict .layui-anim-upbit{
		height:190px!important;
	}
	
	.inputClass {
		background: rgb(221, 221, 221);
		min-height: 36px;
		     width: 250px;
	}
</style>
</head>
<script type="application/javascript">

</script>
<body>
	<div>
		<div class="layui-card">
		<div class="storeUpdatePopupPage">
			<div class="layui-card-body">
				<div class="layui-form" style="margin-top: 5px;margin-left: 50px;">
					<div class="layui-form-item" >
						<div class="layui-inline">
							<input type="hidden" value="${storeId}" id="storeId" />
							<input type="hidden" value="" id="pmlsMaintainCode" />
							<input type="hidden" value="" id="pmlsMaintainName" />
							<input type="hidden" value="" id="pmlsCenterId" />
							<input type="hidden" value="" id="pmlsCenterName" />
							<input type="hidden" value="${reqMap.acCityNo}" id="acCityNo" />
<%-- 							<input type="hidden" value="${pmlsCenterId}" id="pmlsCenterId" /> --%>
							<label class="layui-form-label labelWidth">
								<span class="redSpan">*</span>修改项目名称：
							</label>
							<label class="layui-form-label">
								业务类型
							</label>
<%-- 							<c:if test="${storeLog.businessStatus ne 20903}"> --%>
<!-- 								<div class="layui-input-inline  labelWidth" > -->
<!-- 				              		<input type="hidden" value="" id="name" /> -->
<!-- 									<input type="checkbox" name="storeName" value="1" onclick="nameChange(this)" lay-filter="storeName" lay-skin="primary" title="门店名称" > -->
<!-- 								</div> -->
<%-- 								<c:if test="${contractStatus ne 10402 && contractStatus ne 10403 && gpContractStatus ne 10402 && gpContractStatus ne 10403}"> --%>
<!-- 									<div class="layui-input-inline  labelWidth" > -->
<!-- 										<input type="hidden" value="" id="address" /> -->
<!-- 									    <input type="checkbox" name="storeAddress" value="1" onclick="addressChange(this)" lay-filter="storeAddress" lay-skin="primary" title="门店地址" > -->
<!-- 								    </div> -->
<%-- 								</c:if> --%>
<%-- 						    </c:if> --%>
<!-- 								<div class="layui-input-inline  labelWidth" > -->
<!-- 									<input type="checkbox" name="brandType" checked value="1" onclick="changeBrandType(this)" lay-filter="brandType" lay-skin="primary" title="业务类型" > -->
<!-- 								</div> -->
						</div>
					</div>
					<div id="changeBrandType" >  
						<div class="layui-form-item">
							<div class="layui-inline"> 
								<label class="layui-form-label labelWidth"><span class="redSpan">*</span>是否渠道(门店)：</label>
								<div class="layui-input-inline" style="width: 320px;">
		                            <select  id="isFyBrand" name="isFyBrand" lay-filter="industry">
										<option value="1">是</option>
										<option value="0">否</option>
									</select>
		                        </div>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label labelWidth"><span class="redSpan">*</span>维护人：</label>
							<div class="layui-input-inline" >
								<input type="text" id="pmlsMaintain" name="pmlsMaintain" style="width: 320px;"
									 lay-verify="required" readOnly="true" disabled="true"
									placeholder="请选择" autocomplete="off" class="layui-input" />
<!-- 								<input type="hidden" name="linkUserName" id="linkUserName" value=""> -->
							</div>
							<button type="button" class="layui-btn"  style="margin-left: 130px;"
								data-type="selPmlsMaintain">选择</button>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label labelWidth"><span
								class="redSpan">*</span>维护中心：</label>
							<div class="layui-input-inline">
								<input class="inputClass" placeholder=""  type="text" name="pmlsCenter" id="pmlsCenter"
								 style="border: 1px solid #DDD;background-color: #F5F5F5;color: #ACA899;width: 317px;" readonly="true" disabled="true" value="">
							</div>
						</div>
					</div>
					<!--  style="display: none;"-->
<!-- 					<div id="changeName" >   -->
<!-- 						<div class="layui-form-item"> -->
<!-- 							<div class="layui-inline">  -->
<!-- 								<label class="layui-form-label labelWidth">原门店名称:</label> -->
<%-- 								<label class="layui-form-label " style="text-align: left;">${storeLog.name}</label> --%>
<%-- 								<input type="hidden"  id="oldName"  name="oldName" value="${storeLog.name}"/> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="layui-form-item"> -->
<!-- 							<div class="layui-inline"> -->
<!-- 								<label class="layui-form-label labelWidth"><span class="redSpan">*</span>修改后门店名称:</label> -->
<!-- 								<div class="layui-input-inline" > -->
<!-- 									<input type="text" style="width:300px;"  id="newName" name="newName" lay-verify="required" placeholder="修改后门店名称" autocomplete="off" class="layui-input" /> -->
<!-- 		                        </div> -->
<!-- 				         		<span id="checkName"></span> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div id="changeAddress" >   -->
<!-- 						<div class="layui-form-item"> -->
<!-- 							<div class="layui-inline">  -->
<!-- 								<label class="layui-form-label labelWidth">原门店地址:</label> -->
<%-- 								<label class="layui-form-label " style="text-align: left;width:500px;">${storeLog.addressDetail}</label> --%>
<%-- 								<input type="hidden" id="oldStoreCity" name="oldStoreCity" value="${storeLog.cityNo} "> --%>
<%-- 	                            <input type="hidden" id="oldStoreDistrict" name="oldStoreDistrict" value="${storeLog.districtNo }"> --%>
<%-- 	                            <input type="hidden" id="oldStoreAddress" name="oldStoreAddress" value="${storeLog.address }"> --%>
<%-- 	                            <input type="hidden" id="oldAddressDetail" name="oldAddressDetail" value="${storeLog.addressDetail}"> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="layui-form-item"> -->
<!-- 							<div class="layui-inline"> -->
<!-- 								<label class="layui-form-label labelWidth"><span class="redSpan">*</span>修改后门店地址:</label> -->
<!-- 								<div class="layui-input-inline selWidth"> -->
<!-- 									<select id="newStoreCity" name="newStoreCity" lay-filter="storeCityNo"> -->
<%-- 		                				<option value="${storeLog.cityNo}"  selected="selected" readonly="true" >${storeLog.cityName}</option> --%>
<!-- 				                    </select> -->
<!-- 		                        </div> -->
<!-- 								<div class="layui-input-inline selWidth" id="selDistrict"> -->
<!-- 									<select id="newStoreDistrict" name="newStoreDistrict" lay-filter="newStoreDistrict"> -->
<%-- 		                				<c:if test="${!empty districtList}">  --%>
<%-- 			                                 <c:forEach items="${districtList}" var="district"> --%>
<%-- 			                                    <option value="${district.districtNo}">${district.districtName}</option> --%>
<%-- 			                                </c:forEach> --%>
<%-- 		                            	 </c:if>  --%>
<!-- 				                    </select> -->
<!-- 		                        </div> -->
<!-- 								<div class="layui-input-inline "> -->
<!-- 									<input type="text"  id="newStoreAddress" name="newStoreAddress" placeholder="具体地址信息" lay-verify="required"  autocomplete="off" class="layui-input"  maxlength="100"> -->
<!--                					    <span class="fc-warning"></span> -->
<!--                					    <span id="checkAddress"></span> -->
<!-- 		                        </div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
				</div>
			</div>
		</div>
	</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/meta/pmls/js/store/storeUpdatePopup.js?v=${vs}"></script>
</body>
</html>
