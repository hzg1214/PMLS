<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/frameContract/frameContractAdd.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/frameContract/relateCompany.js?_v=${vs}"></script>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

<form id = "frameContractAddForm" >
	<input type ="hidden"  id ="frameCompanyNo"   name="frameCompanyNo" value ="${contractInfo.companyNo}"/>
	<input type ="hidden"  id ="frameContractId"   name="frameContractId" value ="${contractInfo.id}"/>
	<input type ="hidden"  id ="id"   name="id" value ="${contractInfo.id}"/>
	<input type ="hidden"  id ="oldCompanyNo"   name="oldCompanyNo" value ="${contractInfo.companyNo}"/>
	<input type ="hidden"  id ="companyNo"   name="companyNo" value ="${contractInfo.companyNo}"/>
	<input type ="hidden"  id ="companyId"   name="companyId" value ="${companyInfo.id}"/>
	<input type ="hidden"  name="userIdCreate"  value="${userIdCreate}"/>
	<input type ="hidden"  name="userCityNo" id="userCityNo" value="${userCityNo}"/>
	<input type="hidden" name="oldfileRecordMainIds" id="oldfileRecordMainIds"  value = "${contractInfo.fileRecordMainIds}" />
	<input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds" value ="${contractInfo.fileRecordMainIds}"  />
	<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4><strong>编辑联动框架协议</strong></h4>
                <p><strong>基本信息</strong></p>
                <table class="table-sammary">
                	<col style="width:145px;">
                	<col style="width:auto;">
                	<col style="width:125px;">
                	<col style="width:auto;">
                	<tr>
                		<td class="talabel required">协议类型</td>
                		<td>
                			<c:forEach items="${agreementTypeList}" var="list">
          						<input type="radio" value="${list.dicCode}"  id="agreementType" disabled name="agreementType" <c:if test="${list.dicCode eq contractInfo.agreementType}">checked</c:if>>
								<label class="fon-normal small">${list.dicValue}</label>
							</c:forEach>
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">乙方名称</td>
                		<td>
                			<input type="text" class="form-control w300" id="comapanyName" name="comapanyName" value="${contractInfo.companyName}" placeholder="请选择公司"   readonly="readonly" style="background-color: #F9F9F9">
                            <!-- <button type="button" class="btn btn-primary"  onclick="javascript:relateCompanyList();" style="vertical-align: top;">选择公司</button> -->
                        </td>
                        <td class="talabel required">法定代表/负责人</td>
                		<td>
                			<input type="text" class="form-control w300" id="lealPerson" name="lealPerson" value="${contractInfo.legalPerson}" placeholder=""  notnull="true"  maxlength="20" readonly="readonly" style="background-color: #F9F9F9">
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">公司注册地址</td>
                		<td colspan="3"> 
                			<input type="hidden" class="form-control w120" id="cityNo" name="cityNo" value="${contractInfo.cityNo}">
                			<input type="hidden" class="form-control w120" id="districtNo" name="districtNo" value="${contractInfo.districtNo}">
                            <input type="text" class="form-control w120" id="cityNm" name="cityNm" value="${contractInfo.cityNm}" notnull="true"  readonly="readonly" style="background-color: #F9F9F9">
                            <input type="text" class="form-control w200" id="districtNm" name="districtNm" value="${contractInfo.districtNm}" notnull="true"  readonly="readonly" style="width:132px;background-color: #F9F9F9">
                            <input type="text" class="form-control w300" id="address" name="address" value="${contractInfo.address}" notnull="true"  maxlength="100"  readonly="readonly" style="background-color: #F9F9F9">
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">统一社会信用代码</td>
                		<td>
                			<input type="text" class="form-control w300" id="businessLicenseNo" name="businessLicenseNo" value="${contractInfo.businessLicenseNo}" placeholder=""  notnull="true"  maxlength="30" readonly="readonly"  style="background-color: #F9F9F9">
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">合同开始日期</td>
                		<td>
                			<input  type="text" class="calendar-icon form-control w300" name="dateLifeStart" id="dateLifeStart"  value="${sdk:ymd2(contractInfo.dateLifeStart)}"  
                			
                			onFocus="WdatePicker({isShowClear:true, readOnly:true,maxDate:'#F{$dp.$D(\'dateLifeEnd\',{d:-1})}', dateFmt:'yyyy-MM-dd'})" class="ipttext Wdate"  notnull="true"/>
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">合同到期日期</td>
                		<td>
                			<input  type="text" class="calendar-icon form-control w300" name="dateLifeEnd" id="dateLifeEnd" value="${sdk:ymd2(contractInfo.dateLifeEnd)}" 
                			
                			onFocus="WdatePicker({isShowClear:true, readOnly:true, minDate:'#F{$dp.$D(\'dateLifeStart\',{d:1})}', dateFmt:'yyyy-MM-dd'})" class="ipttext Wdate"  notnull="true"/>
                            <span class="fc-warning"></span>
                        </td>
                	</tr>
                	
                	<tr>
						<td class="talabel required">合同签订日期</td>
						<td>
							<input  type="text" class="calendar-icon form-control w300" name="signDate" id="signDate" value="${sdk:ymd2(contractInfo.signDate)}" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" class="ipttext Wdate"  notnull="true"/>
							<span class="fc-warning"></span>
						</td>
						<td class="talabel required">是否自动续约</td>
                		<td>
                			<c:forEach items="${reAgreeFlagList}" var="list">
          						<input type="radio" value="${list.dicCode}"  id="reAgreeFlag"  name="reAgreeFlag" <c:if test="${list.dicCode eq contractInfo.reAgreeFlag}">checked</c:if>>
								<label class="fon-normal small">${list.dicValue}</label>
							</c:forEach>
						</td>
                	</tr>
                	
                	<tr>
                        <td class="talabel required">开户名</td>
                		<td>
                			<input type="text" class="form-control w300" id="accountNm" name="accountNm" value="${contractInfo.accountNm}" placeholder="请输入开户名"  notnull="true" maxlength="50">
                            <span class="fc-warning"></span>
                        </td>
						<td class="talabel required">开户省市</td>
						<td>
							<span class="control-select">
								<input type="hidden" id="accountProvinceNm" name="accountProvinceNm" value="${contractInfo.accountProvinceNm}">
								<select class="form-control" title="" id="accountProvinceNo" name="accountProvinceNo" required readonly style="width: 150px;">
									<option value="">请选择省份</option>
									<c:forEach items="${provinceList}" var="province">
									<option value="${province.provinceNo}" <c:if test="${province.provinceNo eq contractInfo.accountProvinceNo}"> selected</c:if>>${province.provinceName}</option>
									</c:forEach>
								</select>
							</span>
							<span class="control-select">
								<input type="hidden" id="accountCityNm" name="accountCityNm" value="${contractInfo.accountCityNm}">
								<select class="form-control" title="" id="accountCityNo" name="accountCityNo" required readonly style="width: 150px;">
									<c:forEach items="${cityList}" var="city">
										<option value="${city.cityNo}" <c:if test="${city.cityNo eq contractInfo.accountCityNo}"> selected</c:if> >${city.cityName}</option>
									</c:forEach>
								</select>
							</span>
						</td>
                	</tr>
                	<tr>
                		<td class="talabel required">开户行</td>
                		<td>
                			<input type="text" class="form-control w300" id="bankAccountNm" name="bankAccountNm" value="${contractInfo.bankAccountNm}" placeholder="请输入开户行"  notnull="true" maxlength="50">
                            <span class="fc-warning"></span>
                        </td>
                       <td class="talabel required">银行帐号</td>
                		<td>
                			<input type="text" class="form-control w300" id="bankAccount"  oninput="this.value=this.value.replace(/[^0-9a-zA-Z/-]+/g,'')" value="${contractInfo.bankAccount}"  name="bankAccount" placeholder="请输入银行帐号" maxlength="50" notnull="true" dataType="bankAccount">
                        	<span class="fc-warning"></span>
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">乙方授权代表</td>
                		<td>
                			<input type="text" class="form-control w300" id="partyBNm" name="partyBNm" value="${contractInfo.partyBNm}" placeholder="请输入乙方授权代表"  notnull="true" maxlength="20">
                            <span class="fc-warning"></span>
                        </td>
                       <td class="talabel required">乙方联系方式</td>
                		<td>
                			<input type="text" class="form-control w300" id="partyBTel" name="partyBTel" value="${contractInfo.partyBTel}" placeholder="请输入乙方联系方式" maxlength="11" >
                        	<span class="fc-warning"></span>
                        </td>
                	</tr>
                	<!-- <tr>
                		<td class="talabel required">房友帐号信息接收人</td>
                		<td>
                			<input type="text" class="form-control w300" id="fyAccountNm" name="fyAccountNm" value="${contractInfo.fyAccountNm}" placeholder="请输入房友帐号信息接受人"  notnull="true" maxlength="20">
                            <span class="fc-warning"></span>
                        </td>
                       <td class="talabel required">接收人联系方式</td>
                		<td>
                			<input type="text" class="form-control w300" id="fyAccountNmTel" name="fyAccountNmTel" value="${contractInfo.fyAccountNmTel}" placeholder="请输入接受人联系方式" maxlength="11"  >
                        	<span class="fc-warning"></span>
                        </td>
                	</tr> -->
                </table>
				<table class="table-sammary">
					<col style="width:145px;">
					<col style="width:auto;">
					<tr>
						<td class="talabel">合同说明</td>
						<td>
							<textarea maxlength="300" style="width:875px;" name="contractNote" id="contractNote" cols="30" rows="10"  style="word-break:break-all;word-wrap: break-word;resize: none;">${contractInfo.contractNote}</textarea>
						</td>
					</tr>
					<tr>
						<td class="talabel">备注</td>
						<td>
							<textarea maxlength="300" style="width:875px;" name="remark" id="remark" cols="30" rows="10"  style="word-break:break-all;word-wrap: break-word;resize: none;">${contractInfo.remark}</textarea>
						</td>
					</tr>
				</table>
             </div>
        </div>
    </div>
</form>


<div class="container theme-hipage ng-scope" role="main">
	<p><strong style="font-size:18px;">附件</strong></p>
</div>
<!-- 营业执照 -->
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						营业执照
					</h4>
					<div class="thumb-xs-box" id="fileBusiness">
					<c:if test="${contractInfo.fileBusinessList != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileBusinessList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="营业执照" src="${list.fileAbbrUrl}" class="empPhoto" />
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
								<input type="hidden" name="fileTypeId" value="1028" />
								<input type="hidden" name="fileSourceId" value="8" />
							  </div>
		                </c:forEach> 
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${contractInfo.fileBusinessList != null && contractInfo.fileBusinessList.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
				                <input type="hidden" name="fileTypeId" value="1028" />
				                <input type="hidden" name="fileSourceId" value="8" />
				                <input type="hidden" name ="companyId" value="${companyInfo.id}">
							</a>
						</div>
					</div>
				</div>
     </div>
 </div> 
<!-- 合同 -->
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						合同
					</h4>
					<div class="thumb-xs-box" id="fileContract">
					<c:if test="${contractInfo.fileContractList != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileContractList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="合同" src="${list.fileAbbrUrl}" class="empPhoto" />
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
								<input type="hidden" name="fileTypeId" value="1029" />
								<input type="hidden" name="fileSourceId" value="8" />
							  </div>
		                </c:forEach> 
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${contractInfo.fileContractList != null && contractInfo.fileContractList.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
				                <input type="hidden" name="fileTypeId" value="1029" />
				                <input type="hidden" name="fileSourceId" value="8" />
				                <input type="hidden" name ="companyId" value="${companyInfo.id}">
							</a>
						</div>
					</div>
				</div>
     </div>
 </div> 
<!-- 其他附件 -->
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						其他
					</h4>
					<div class="thumb-xs-box" id="attachmentFile">
					<c:if test="${contractInfo.attachmentFileList != null }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.attachmentFileList}" var="list" varStatus="status">
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
								<input type="hidden" name="fileTypeId" value="1030" />
								<input type="hidden" name="fileSourceId" value="8" />
							  </div>
		                </c:forEach> 
					</c:if>
						<div class="thumb-xs-list item-photo-add" 
							<c:if test="${contractInfo.attachmentFileList != null && contractInfo.attachmentFileList.size()>=10  }">style="display: none;"</c:if>>
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei"  multiple="multiple">
				                <input type="hidden" name="fileTypeId" value="1030" />
				                <input type="hidden" name="fileSourceId" value="8" />
				                <input type="hidden" name ="companyId" value="${companyInfo.id}">
							</a>
						</div>
					</div>
				</div>
     </div>
 </div> 
	<div class="text-center">
		<input type="button" id="btn-submit" onclick="updateSubmit(${contractInfo.id});" class="btn btn-primary" style="width: 100px;margin-left: 100px!important;" value="保存">
		<a href="${ctx}/frameContract?searchParam=1" class="btn btn-primary mgl20">返回</a>
	</div>
</body>
</html>
