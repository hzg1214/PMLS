<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<form id="accountProjectForm" style="height: auto;width: auto">
	<input type="hidden" id="estateCityNo" name="estateCityNo" value="${estateCityNo}">
	<div role="main">
		<div class="row">
			<div class="page-content">
				<a href="javascript:EstateType.closePopup();" class="btn btn-default" style="float: right;margin-top: -10px;">&times;</a>
				<label class="fw-normal text-left" style="padding-left:20px;width:150px"><strong>选择核算主体</strong></label>
				<div class="border-bottom"></div>
				<span class="fc-warning" id="errormsg" style="padding-left:20px"></span>
				<table class="table-sammary">
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal text-left" style="margin-left:100px;width:100px"">核算主体：</label>
							<select class="form-control" id="lnkAccountProjectCode" name="lnkAccountProjectCode" notnull="true" style="width:200px;">
							</select>
				            <span id="warning-noCity" style="color:#f00;font-size:12px;margin-left:35px"></span>
						</td>
					</tr>
					
				</table>
				<div class="text-center">
                <a href="javascript:EstateType.chooseAccountProject()" class="btn btn-primary mgt20">　确定　</a>
				<a href="javascript:EstateType.closePopup()" class="btn btn-default mgt20 mgl50">　取消　</a>
            </div>
			</div>
		</div>
	</div>
</form>
<script>
$("#lnkAccountProjectCode").html('');
var url = BASE_PATH + "/cashBill/getLnkAccountProjectList";
var estateCityNo =  $("#estateCityNo").val();
if(isNullOrEmpty(estateCityNo)) {
	var params = {estateCityNo:estateCityNo};
	ajaxGet(url, params, function(data) {
		var dataLength =  data.returnValue.length;
		var result = "<option value=''>请选择核算主体</option>";
		$.each(data.returnValue, function(n, value) {
			if(dataLength > 1) {
				result += "<option value='" + value.lnkaccountProjectCode + "'data-lnkaccountProject='" + value.lnkAccountProject+ "'>"
				+ value.lnkaccountProjectCode +"_"+ value.lnkAccountProject + "</option>";
			}
		});
		$("#lnkAccountProjectCode").append(result);
	}, function() {
	});
}
function isNullOrEmpty(obj){
	if(obj == null || obj == "" || obj == undefined){
		return false;
	}else{
		return true;
	}
}
</script>
