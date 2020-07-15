<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<script type="text/javascript">
$(function() {
	$("#errorTip").html("&nbsp;");
	$("#newFyAccount").focus();
});
</script>
<form id="fangyouAccountChangeForm" style="height: auto;width: auto" action="${ctx}/fangyouAccount/changeFyAcount">
	<input type="hidden" name="storeId" value="${storeId}">
	<input type="hidden" name="oldFyAcount" value="${oldFyAcount}" id="oldFyAcount">
	<input type="hidden" name="userCode" value="${userCode}" >
	<input type="hidden" name="userName" value="${userName}" >
	<input type="hidden" name="userIdCreate" value="${userIdCreate}" >
	<input type="hidden" name="storeNo" value="${storeNo}" >
	<div role="main">
		<div class="row">
			<div class="page-content">
				<a href="javascript:FangyouAccountView.closePopup();" class="btn btn-default" style="float: right;margin-top: -10px;">&times;</a>
				<label class="fw-normal w100 text-left" style="padding-left:20px">编辑房友账号</label>
				<div class="border-bottom"></div>
				<span class="fc-warning" id="errormsg" style="padding-left:20px"></span>
				<table class="table-sammary">
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w110 text-left" style="vertical-align: top;margin-left: 35px"  for="realOpenTime"><i>* </i>房友账号：</label>
		                    <input  type="text"  style="width:160px;" name="newFyAccount" id="newFyAccount" notnull="true" maxlength="6" />
		                    <span class="fc-warning"></span>
		                    <span style="color:#FF0000;" id="errorTip"> &nbsp;</span>
						</td>
					</tr>
				</table>
				<div class="text-center">
                <a href="javascript:FangyouAccountView.changeFyAcount(${storeId})" class="btn btn-primary mgt20">　保存　</a>
				<a href="javascript:FangyouAccountView.closePopup()" class="btn btn-default mgt20 mgl50">　取消　</a>
            </div>
			</div>
		</div>
	</div>
</form>