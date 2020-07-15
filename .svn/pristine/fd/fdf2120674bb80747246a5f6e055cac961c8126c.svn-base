<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!-- <style>
	 input::-webkit-input-placeholder {
       /* placeholder字体大小  */
       font-size: 9px;
    }
    input {
    	width: 120px;
    }
</style> -->
<form id="customerAdjustForm" style="height: auto;width: 780px;" onkeydown="if(event.keyCode==13){return false;}">
	<input type="hidden" name="reportId" value="${reportId}">
	<input type="hidden" name="relateId" value="${relateId}">
	<input type="hidden" id="oldCustomerNm" name="oldCustomerNm" value="${customerNm}">
	<input type="hidden" id="oldCustomerTel" name="oldCustomerTel" value="${customerTel}" >
	<input type="hidden" id="oldCustomerNmTwo" name="oldCustomerNmTwo" value="${customerNmTwo}" >
	<input type="hidden" id="oldCustomerTelTwo" name="oldCustomerTelTwo" value="${customerTelTwo}" >
	
	<input type="hidden" name="userCode" value="${userCode}" >
	<input type="hidden" name="userName" value="${userName}" >
	<input type="hidden" name="userIdCreate" value="${userIdCreate}" >
	<div role="main">
		<div class="row">
			<div class="page-content">
				<a href="javascript:AchievementView.closePopup();" class="btn btn-default" style="float: right;margin-top: -10px;">&times;</a>
				<h4 class="border-bottom pdb10" style="padding-left:20px"><strong>客户信息调整</strong></h4>
				<span class="fc-warning" id="errormsg" style="padding-left:20px"></span>
				<table class="table-sammary">
					<col style="width:150px;">
                    <col style="width:240px;">
                    <col style="width:100px;">
                    <col style="width:auto;">
                    <tr>
						<td colspan="3">
							<label style="margin-left: 40px;font-size: 14px;">原客户信息：</label>
						</td>
					</tr>
                    <tr>
                        <td class="talabel">客户姓名：</td>
                        <td>${customerNm}</td>
                        <td class="talabel">客户手机：</td>
                        <td>${customerTel}</td>
                    </tr>
                    <tr>
                        <td class="talabel">客户姓名：</td>
                        <td>${customerNmTwo}</td>
                        <td class="talabel">客户手机：</td>
                        <td>${customerTelTwo}</td>
                    </tr>
					
					<tr>
						<td colspan="3">
							<label style="margin-left: 40px;font-size: 14px;">修改后客户信息：</label>
						</td>
					</tr>
					
					<tr>
                        <td class="talabel required">客户姓名：</td>
                        <td>
                        	<input type="text" class="form-control w160" name="customerNm" id="customerNm" notnull="true" placeholder="请输入客户名称">
                        	<span class="fc-warning"></span>
                        </td>
                        <td class="talabel required">客户手机：</td>
                        <td>
                        	<input type="text" class="form-control w160"  name="customerTel" id="customerTel" notnull="true" datatype="phone"  placeholder="请输入客户电话" maxlength="11">
                        	<span class="fc-warning"></span>
                        </td>
                    </tr>
					<tr>
                        <td class="talabel">客户姓名：</td>
                        <td>
                        	<input type="text" class="form-control w160" name="customerNmTwo" id="customerNmTwo" placeholder="请输入客户名称" >
							<span id="errorMsg1" class="fc-warning"></span>
                        </td>
                        <td class="talabel">客户手机：</td>
                        <td>
                        	<input type="text" class="form-control w160" name="customerTelTwo" id="customerTelTwo" datatype="phone"  placeholder="请输入客户电话" maxlength="11">
							<span id="errorMsg2" class="fc-warning"></span>
                        </td>
                    </tr>
                    <tr></tr>
                    <tr></tr>
				</table>
				<div class="text-center">
                <a href="javascript:AchievementView.saveCustomerInfoAdjut('${estateId}','${companyId}','${customerId}','${relateId}','${fromType}')" class="btn btn-primary mgt20">　保存　</a>
				<a href="javascript:AchievementView.closePopup()" class="btn btn-default mgt20 mgl50">　取消　</a>
            </div>
			</div>
		</div>
	</div>
</form>
