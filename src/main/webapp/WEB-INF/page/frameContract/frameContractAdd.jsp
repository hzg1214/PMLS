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
	<input type ="hidden"  id ="frameCompanyNo"   name="frameCompanyNo" />
	<input type ="hidden"  id ="companyId"   name="companyId" />
	<input type ="hidden"  name="userIdCreate"  value="${userIdCreate}"/>
	<input type ="hidden"  name="contractNo" value="${frameContractNo}"/>
	<input type ="hidden"  name="userCityNo" id="userCityNo" value="${userCityNo}"/>
	<input type ="hidden"  name="companyAcCityNo" id="companyAcCityNo"/>
	<input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"  />
	<input type ="hidden"  name="companyContactTel" id="companyContactTel"/>
	<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
            <div class="page-content">
                <h4><strong>新建联动框架协议</strong></h4>
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
          						<input onchange="changeFrameType(this)" type="radio" value="${list.dicCode}" id="${list.dicCode}" id="agreementType" name="agreementType" <c:if test="${list.dicCode eq changeFrameType}">checked</c:if>>
								<label class="fon-normal small">${list.dicValue}</label>
							</c:forEach>
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">乙方名称</td>
                		<td>
                			<input type="text" class="form-control w300" id="comapanyName" name="comapanyName" placeholder="请选择公司"   readonly="readonly" style="background-color: #F9F9F9">
                            <button type="button" class="btn btn-primary"  onclick="javascript:relateCompanyList();" style="vertical-align: top;">选择公司</button>
                        </td>
                        <td class="talabel required">法定代表/负责人</td>
                		<td>
                			<input type="text" class="form-control w300" id="lealPerson" name="lealPerson" placeholder=""  notnull="true"  maxlength="20" readonly="readonly" style="background-color: #F9F9F9">
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">公司注册地址</td>
                		<td colspan="3"> 
                			<input type="hidden" class="form-control w120" id="cityNo" name="cityNo" >
                			<input type="hidden" class="form-control w120" id="districtNo" name="districtNo" >
                			<input type ="hidden"  name="companyCityNo" id="companyCityNo"/>
                			<input type ="hidden"  name="companyDistrictNo" id="companyDistrictNo"/>
                            <input type="text" class="form-control w120" id="cityNm" name="cityNm"  notnull="true"  readonly="readonly" style="background-color: #F9F9F9">
                            <input type="text" class="form-control w200" id="districtNm" name="districtNm"  notnull="true"  readonly="readonly" style="width:132px;background-color: #F9F9F9">
                            <input type="text" class="form-control w300" id="address" name="address"  notnull="true"  maxlength="100"  readonly="readonly" style="background-color: #F9F9F9">
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">统一社会信用代码</td>
                		<td>
                			<input type="text" class="form-control w300" id="businessLicenseNo" name="businessLicenseNo" placeholder=""  notnull="true"  maxlength="30" readonly="readonly"  style="background-color: #F9F9F9">
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">合同开始日期</td>
                		<td>
                			<input  type="text" class="calendar-icon form-control w300" name="dateLifeStart" id="dateLifeStart" onFocus="WdatePicker({isShowClear:true, readOnly:true, maxDate:'#F{$dp.$D(\'dateLifeEnd\',{d:-1})}',dateFmt:'yyyy-MM-dd'})" class="ipttext Wdate"  notnull="true"/>
                            <span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">合同到期日期</td>
                		<td>
                			<input  type="text" class="calendar-icon form-control w300" name="dateLifeEnd" id="dateLifeEnd" onFocus="WdatePicker({isShowClear:true, readOnly:true, minDate:'#F{$dp.$D(\'dateLifeStart\',{d:1})}', dateFmt:'yyyy-MM-dd'})" class="ipttext Wdate"  notnull="true"/>
                            <span class="fc-warning"></span>
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">合同签订日期</td>
						<td>
							<input  type="text" class="calendar-icon form-control w300" name="signDate" id="signDate" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" class="ipttext Wdate"  notnull="true"/>
							<span class="fc-warning"></span>
						</td>
                		<td class="talabel required">是否自动续约</td>
                		<td>
                		
                			<c:forEach items="${reAgreeFlagList}" var="list">
          						<input  type="radio" value="${list.dicCode}" id="reAgreeFlag" name="reAgreeFlag">
								<label class="fon-normal small">${list.dicValue}</label>
							</c:forEach>
          					<!-- <input type="radio" value="22201"  id="reAgreeFlag" name="reAgreeFlag" class="radioCheck">是</input>
          					<input type="radio" value="22202"  id="reAgreeFlag" name="reAgreeFlag" class="radioCheck">否</input> -->
                        </td>
                	</tr>
                	<tr>
                        <td class="talabel required">开户名</td>
                		<td>
                			<input type="text" class="form-control w300" id="accountNm" name="accountNm" placeholder="请输入开户名"  notnull="true" maxlength="50">
                            <span class="fc-warning"></span>
                        </td>
						<td class="talabel required">开户省市</td>
						<td>
							<span class="control-select">
								<input type="hidden" id="accountProvinceNm" name="accountProvinceNm">
								<select class="form-control" title="" id="accountProvinceNo" name="accountProvinceNo" required readonly style="width: 150px;">
									<option value="">请选择省份</option>
									<c:forEach items="${provinceList}" var="province">
										<option value="${province.provinceNo}">${province.provinceName}</option>
									</c:forEach>
								</select>
							</span>
							<span class="control-select">
								<input type="hidden" id="accountCityNm" name="accountCityNm">
								<select class="form-control" title="" id="accountCityNo" name="accountCityNo" required readonly style="width: 150px;">
									<c:forEach items="${cityList}" var="city">
										<option value="${city.cityNo}">${city.cityName}</option>
									</c:forEach>
								</select>
							</span>
						</td>
                	</tr>
                	<tr>
                		<td class="talabel required">开户行</td>
                		<td>
                			<input type="text" class="form-control w300" id="bankAccountNm" name="bankAccountNm" placeholder="请输入开户行"  notnull="true" maxlength="50">
                            <span class="fc-warning"></span>
                        </td>
                       <td class="talabel required">银行帐号</td>
                		<td>
                			<input type="text" class="form-control w300" id="bankAccount" name="bankAccount" oninput="this.value=this.value.replace(/[^0-9a-zA-Z/-]+/g,'')" placeholder="请输入银行帐号" maxlength="50" notnull="true" dataType="bankAccount">
                        	<span class="fc-warning"></span>
                        </td>
                	</tr>
                	<tr>
                		<td class="talabel required">乙方授权代表</td>
                		<td>
                			<input type="text" class="form-control w300" id="partyBNm" name="partyBNm" placeholder="请输入乙方授权代表"  notnull="true" maxlength="20">
                            <span class="fc-warning"></span>
                        </td>
                       <td class="talabel required">乙方联系方式</td>
                		<td>
                			<input type="text" class="form-control w300" id="partyBTel" name="partyBTel" placeholder="请输入乙方联系方式" maxlength="11" >
                        	<span class="fc-warning"></span>
                        </td>
                	</tr>
                	<!-- <tr>
                		<td class="talabel required">房友帐号信息接收人</td>
                		<td>
                			<input type="text" class="form-control w300" id="fyAccountNm" name="fyAccountNm" placeholder="请输入房友帐号信息接受人"  notnull="true" maxlength="20">
                            <span class="fc-warning"></span>
                        </td>
                       <td class="talabel required">接收人联系方式</td>
                		<td>
                			<input type="text" class="form-control w300" id="fyAccountNmTel" name="fyAccountNmTel" placeholder="请输入接受人联系方式" maxlength="11" >
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
							<textarea maxlength="300" style="width:875px;" name="contractNote" id="contractNote" cols="30" rows="10"  style="word-break:break-all;word-wrap: break-word;resize: none;"></textarea>
						</td>
					</tr>
					<tr>
						<td class="talabel">备注</td>
						<td>
							<textarea maxlength="300" style="width:875px;" name="remark" id="remark" cols="30" rows="10"  style="word-break:break-all;word-wrap: break-word;resize: none;"></textarea>
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
<div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						<i>*</i>营业执照
					</h4>
					<div class="thumb-xs-box" id="fileBusiness">
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="1028" />
				                <input type="hidden" name="fileSourceId" value="8" />
				                <input type="hidden" name ="companyId" value="">
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
						<i>*</i>合同
					</h4>
					<div class="thumb-xs-box" id="fileContract">
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="1029" />
				                <input type="hidden" name="fileSourceId" value="8" />
				                <input type="hidden" name ="companyId" value="">
							</a>
						</div>
					</div>
				</div>
     </div>
 </div>
 <!-- 其他 -->
 <div class="container theme-hipage ng-scope" role="main">
        <div class="row">
                <div class="pd10">
					<h4 class="thumb-title">
						其他
					</h4>
					<div class="thumb-xs-box" id="attachmentFile">
						<div class="thumb-xs-list item-photo-add" >
							<input type="hidden" name="limitSize" value="10">
							<a href="javascript:void(0);" class="thumbnail" title="添加附件">
								<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
				                <input type="hidden" name="fileTypeId" value="1030" />
				                <input type="hidden" name="fileSourceId" value="8" />
				                <input type="hidden" name ="companyId" value="">
							</a>
						</div>
					</div>
				</div>
     </div>
 </div>
 <div class="lockHandle2" style="position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; 
overflow: hidden; z-index: 1981; background: rgb(0, 0, 0); opacity: 0.4;"></div>
        <div class="text-center">
          	 <input type="button" id="btn-submit" onclick="addSubmit();" class="btn btn-primary" style="width: 100px;margin-left: 100px!important;" value="草签">
          	 <a href="${ctx}/frameContract?searchParam=1" class="btn btn-default" style="width: 100px;margin-left: 100px!important;">取消</a>
        </div>
</body>
</html>
