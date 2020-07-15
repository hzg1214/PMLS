<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css?_v=${vs}">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<form id="bankAdjustForm" style="height: auto;width: 780px;" onkeydown="if(event.keyCode==13){return false;}">
	<input type="hidden" id="fileRecordMainIds" name="fileRecordMainIds">
	<input type="hidden" name="gpContractId" value="${gpContractId}">
	
	<input type="hidden" id="companyId" name="companyId" value="${companyId}">
	<input type="hidden" id="companyName" name="companyName" value="${companyName}" >
	<input type="hidden" id="partyB" name="partyB" value="${partyB}" >
	<input type="hidden" id="oldAccountNm" name="oldAccountNm" value="${accountNm}" >
	
	<input type="hidden" id="oldBankAccountNm" name="oldBankAccountNm" value="${bankAccountNm}" >
	<input type="hidden" id="oldBankAccount" name="oldBankAccount" value="${bankAccount}" >
	<input type="hidden" id="oldAccountProvinceNo" name="oldAccountProvinceNo" value="${accountProvinceNo}" >
	<input type="hidden" id="oldAccountProvinceNm" name="oldAccountProvinceNm" value="${accountProvinceNm}" >
	<input type="hidden" id="oldAccountCityNo" name="oldAccountCityNo" value="${accountCityNo}" >
	<input type="hidden" id="oldAccountCityNm" name="oldAccountCityNm" value="${accountCityNm}" >
	
	<input type="hidden" name="userCode" value="${userCode}" >
	<input type="hidden" name="userName" value="${userName}" >
	<input type="hidden" name="userIdCreate" value="${userIdCreate}" >
	<div role="main">
		<div class="row">
			<div class="page-content">
				<a href="javascript:GpContract.closePopup();" class="btn btn-default" style="float: right;margin-top: -10px;">&times;</a>
				<h4 class="border-bottom pdb10" style="padding-left:20px"><strong>银行信息变更</strong></h4>
				<table class="table-sammary">
					<col style="width:150px;">
                    <col style="width:240px;">
                    <col style="width:100px;">
                    <col style="width:auto;">
                    <tr>
						<td colspan="3">
							<label style="margin-left: 40px;font-size: 14px;">修改前银行信息：</label>
						</td>
					</tr>
                    <tr>
                        <td class="talabel">开户名：</td>
                        <td>${accountNm}</td>
                        <td class="talabel">开户省市：</td>
                        <td>${accountProvinceNm} ${accountCityNm}</td>
                    </tr>
                    <tr>
                        <td class="talabel">开户行：</td>
                        <td>${bankAccountNm}</td>
                        <td class="talabel">开户账号：</td>
                        <td>${bankAccount}</td>
                    </tr>
					
					<tr>
						<td colspan="3">
							<label style="margin-left: 40px;font-size: 14px;">修改后银行信息：</label>
						</td>
					</tr>
					
					<tr>
                        <td class="talabel required">开户名：</td>
                        <td>
                        	<input type="text" class="form-control w160" name="accountNm" id="accountNm" notnull="true" placeholder="请输入开户名">
                        	<span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">开户省市：</td>
                        <td>
                        	<span class="control-select">
								<input type="hidden" id="accountProvinceNm" name="accountProvinceNm">
								<select class="form-control" title="" notnull="true" id="accountProvinceNo" name="accountProvinceNo" required readonly style="width: 148px;">
									<option value="">请选择省份</option>
									<c:forEach items="${provinceList}" var="province">
                                        <option value="${province.provinceNo}">${province.provinceName}</option>
                                    </c:forEach>
								</select>
							</span>
                            <span class="control-select">
								<input type="hidden" id="accountCityNm" name="accountCityNm">
								<select class="form-control" title="" id="accountCityNo" notnull="true" name="accountCityNo" required readonly style="width: 148px;">
									<c:forEach items="${cityList}" var="city">
                                        <option value="${city.cityNo}">${city.cityName}</option>
                                    </c:forEach>
								</select>
							</span>
							<span class="fc-warning"></span>
                        </td>
                    </tr>
					<tr>
                        <td class="talabel required">开户行：</td>
                        <td>
                        	<input type="text" class="form-control w160" name="bankAccountNm" notnull="true"  id="bankAccountNm" placeholder="请输入开户行" >
							<span id="errorMsg1" class="fc-warning"></span>
                        </td>
                        <td class="talabel required">银行账号：</td>
                        <td>
                        	<input type="text" class="form-control w160" name="bankAccount" notnull="true"  id="bankAccount"   placeholder="请输入银行账号">
							<span id="errorMsg2" class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr></tr>
                    <tr></tr>
				</table>
				<div class="page-content" style="padding-left: 41px;">
					<h4 style="font-size:14px;line-height: 0px;">
						<strong>附件</strong>
					</h4>
				</div>
				<table class="table-sammary" name="Viewerbox">
						<col style="width:145px;">
						<col style="width:auto">
						<tr>
							<td colspan="2">
								<div class="" role="main">
									<div>
										<div class="pd10" style="padding-left: 41px;">
											账户变更申请书
											<div class="thumb-xs-box" id="fileAccountChange">
												<div class="thumb-xs-list item-photo-add">
													<input type="hidden" name="limitSize" value="10">
													<a href="javascript:void(0);" class="thumbnail" title="添加附件">
														<input type="file" class="btn-flie file-control" data-limit="10"  multiple="multiple">
														<input type="hidden" name="fileTypeId" value="1043" />
														<input type="hidden" name="fileSourceId" value="12" />
														<input type="hidden" name ="companyId" value="">
													</a>
												</div>
											</div>
											<i class="fontset" style="color:#808080">注：账号信息请完整填写，委托方加盖公章确认。</i>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</table>
				<div  id="errorMsg" >
		                    <span class="fc-warning"  style="margin-left: 40px;"></span>
		         </div>
				<div class="text-center">
                <a href="javascript:GpContract.saveBankInfoAdjut('${gpContractId}')" class="btn btn-primary mgt20">　保存　</a>
				<a href="javascript:GpContract.closePopup()" class="btn btn-default mgt20 mgl50">　取消　</a>
            </div>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	$("#accountProvinceNo").change(function () {
		$("#accountProvinceNm").val($("#accountProvinceNo option:selected").html());
		var url = BASE_PATH +  "/cityCascade/city";
		var params = {provinceNo:$("#accountProvinceNo").val()};
		ajaxGet(url, params, function(data) {
			var result = "<option value=''>请选择城市</option>";
			$.each(data.returnValue, function(n, value) {
				result += "<option value='" + value.cityNo + "'>"
					+ value.cityName + "</option>";
			});
			$("#accountCityNo").html('');
			$("#accountCityNo").append(result);
		}, function() {
		});
	});
	$("#accountCityNo").change(function () {
		$("#accountCityNm").val($("#accountCityNo option:selected").html());
	});
	//上传开始
    var options = {
        "url":BASE_PATH + '/file/uploadCommonFile',
        "isDeleteImage":false,//删除时校验checkEditImage
        "isAddImage":true,   //添加时校验checkEditImage
        "isCommonFile":true  //公共上传文件
    };
    photoUploader(options,null,null,null);
    //上传结束
});
</script>
