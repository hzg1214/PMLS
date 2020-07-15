<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<%-- <script type="text/javascript" src="${ctx}/meta/js/common/select2.min.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/select2.min.css?_v=${vs}">
<script type="text/javascript" src="${ctx}/meta/js/common/jquery.autocomplete.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/jquery.autocomplete.css?_v=${vs}">--%> 
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/jquery-ui.css?_v=${vs}">
<style>
	body {
		font-family: Arial, Helvetica, sans-serif;
	}
	
	table {
		font-size: 1em;
	}
	
	.ui-draggable, .ui-droppable {
		background-position: top;
	}
	 .ui-autocomplete-loading {
		background: white url("http://jqueryui.com/resources/demos/autocomplete/images/ui-anim_basic_16x16.gif") right center no-repeat;
	}
	#ui-id-1 {
		z-index:10000;
		font-size:14px;
	}
	.ui-autocomplete {
	    max-height: 258px;
	    overflow-y: auto;
	    overflow-x: hidden;
	  }
	.w125x{
		width:160px!important;
		margin-right:5px
	}
	.select2-container--open{
		z-index:10000;
		font-size:14px;
	}
	.select2-results__options{
		max-height:322px!important;
	}
	.autocomplete-suggestions {font-size: 15px; -webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box; border: 1px solid #999; background: #FFF; cursor: default; overflow: auto; -webkit-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); -moz-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); }
	.autocomplete-suggestion { padding: 2px 5px; white-space: nowrap; overflow: hidden; }
	.autocomplete-no-suggestion { padding: 2px 5px;}
	.autocomplete-selected { background: #F0F0F0; }
	.autocomplete-suggestions strong { font-weight: bold; color: #000; }
	.autocomplete-group { padding: 2px 5px; }
	.autocomplete-group strong { font-weight: bold; font-size: 16px; color: #000; display: block; border-bottom: 1px solid #000; }
</style>
    <%-- <script type="text/javascript" src="${ctx}/meta/js/report/expand/bootstrap-select.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/cssreport/bootstrap-select.css?_v=${vs}">--%>
<%-- <script type="text/javascript" src="${ctx}/meta/js/houseLinkage/report/achievementAdjustModePopup.js?_v=${vs}"></script> --%>
<form id="achievementAdjustForm" style="height: auto;width: 650px;" onkeydown="if(event.keyCode==13){return false;}">
	<input type="hidden" name="reportId" value="${reportId}">
	<input type="hidden" name="relateId" value="${relateId}">
	<input type="hidden" name="customerNm" value="${customerNm}">
	<input type="hidden" name="customerTel" value="${customerTel}" >
	<input type="hidden" name="oldContactId" value="${oldContactId}" >
	<input type="hidden" name="oldContactNm" value="${oldContactNm}" >
	<input type="hidden" name="oldCenterGroupId" value="${oldCenterGroupId}" >
	<input type="hidden" name="oldCenterGroupName" value="${oldCenterGroupName}" >
	
	<input type="hidden" name="userCode" value="${userCode}" >
	<input type="hidden" name="userName" value="${userName}" >
	<input type="hidden" name="userIdCreate" value="${userIdCreate}" >
	<div role="main">
		<div class="row">
			<div class="page-content">
				<a href="javascript:AchievementView.closePopup();" class="btn btn-default" style="float: right;margin-top: -10px;">&times;</a>
				<h4 class="border-bottom pdb10" style="padding-left:20px"><strong>业绩调整</strong></h4>
				<span class="fc-warning" id="errormsg" style="padding-left:20px"></span>
				<table class="table-sammary">
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w125x text-right" style="margin-left:35px">报备编号：</label>
							<label class="fon-normal">${reportId}</label>
							<span class="fc-warning"></span>
						</td>
					</tr>
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w125x text-right" style="margin-left:35px">客户：</label>
							<label class="fon-normal">${customerNm}&nbsp;&nbsp;${customerTel}</label>
							<span class="fc-warning"></span>
						</td>
					</tr>
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w125x text-right" style="margin-left:35px">原业绩归属人：</label>
							<label class="fon-normal">${oldContactNm}</label>
							<span class="fc-warning"></span>
						</td>
					</tr>
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w125x text-right" style="margin-left:35px">原业绩归属中心：</label>
							<label class="fon-normal">${oldCenterGroupName}</label>
							<span class="fc-warning"></span>
						</td>
					</tr>
					<%-- <tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w125x text-right" style="vertical-align: top;margin-left: 35px"  for="realOpenTime"><i>* </i>变更后业绩归属人：</label>
							<select class="singleSelect" title="" id="linkUser" name="linkUser" notnull="true" style="width:180px;height:80px;">
								<option value="" selected>请选择</option>
		                    	<c:if test="${!empty linkUserList}">
	                                <c:forEach items="${linkUserList}" var="list">
	                                     <option value="${list.userCode}" data-userName="${list.userName}">${list.userName}(${list.userCode})</option>
	                                </c:forEach>
                                </c:if>
							</select>
							<input type="hidden"  name="linkUserName" id="linkUserName" >
							<input type="hidden"  name="linkUserCode" id="linkUserCode" >
                  
		                    <span class="fc-warning"></span>
						</td>
					</tr> --%>
					<tr class="list-inline form-inline">
						<td>
						<label class="fw-normal w125x text-right" style="vertical-align: top;margin-left: 35px"  for="inputLinkUserName"><i>* </i>变更后业绩归属人：</label>
							<input type="text"  name="inputLinkUserName" id="inputLinkUserName" placeholder="搜索后选择业绩归属人" style="width:180px;">
							<!-- <input id="autocomplete" > -->
							<input type="hidden"  name="linkUserName" id="linkUserName" >
							<input type="hidden"  name="linkUserCode" id="linkUserCode" >
							<input type="hidden"  name="changeFlag" id="changeFlag" value="1">
							<input type="hidden"  name="changeValue" id="changeValue" value="">
                  			<span class="fc-warning" id="errorMsg2" ></span>
						</td>
						
					</tr>
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w125x text-right" style="vertical-align: top;margin-left: 35px"  for="newContactId"><i>* </i>变更后业绩归属中心：</label>
		                    <select class="form-control selectpicker4 " title="" id="newCenterId" name="newCenterId" notnull="true" style="width:180px;" data-live-search="true" ></select>
							<input type="hidden"  name="newCenterName" id="newCenterName" >
							<input type="hidden"  name="newCenterCode" id="newCenterCode" >
		                    <span class="fc-warning"></span>
						</td>
					</tr>
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w125x text-right" style="vertical-align: top;margin-left: 35px"  for="changeReason"><i>* </i>变更原因：</label>
							<textarea name="changeReason" id="changeReason" notnull="true" style="width:345px; height:86px"></textarea>
		                    <span class="fc-warning"></span>
						</td>
					</tr>
				</table>
				<div class="text-center">
                <a href="javascript:AchievementView.saveAchievementAdjut('${estateId}','${companyId}','${customerId}','${relateId}','${fromType}')" class="btn btn-primary mgt20">　保存　</a>
				<a href="javascript:AchievementView.closePopup()" class="btn btn-default mgt20 mgl50">　取消　</a>
            </div>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript" src="${ctx}/meta/js/common/jquery-ui.js?_v=${vs}"></script>
<script>
$("#errorMsg2").empty().html("");
var dataArray=[];
var dataArray2=[];
var jsonStr="";
var option = {
		max: 15,    //列表里的条目数
		minChars: 0,    //自动完成激活之前填入的最小字符
		width: 180,     //提示的宽度，溢出隐藏
		scrollHeight: 200,   //提示的高度，溢出显示滚动条
		matchContains: false,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
		autoFill: true,    //自动填充
		minLength: 1,
		select: function (event, ui) {
			$("#errorMsg2").empty().html("");
	        $("#inputLinkUserName").val(ui.item.label);
	        $("#linkUserName").val(ui.item.value);
	        $("#linkUserCode").val(ui.item.id);
	        $("#newCenterId").html('');
	        $("#changeFlag").val("2");
	        var linkUserCode = $("#linkUserCode").val();
			if(isNullOrEmpty(linkUserCode)) {
				var url = BASE_PATH + "/report/getLinkUserCenter";
				var params = {linkUserCode:linkUserCode};
				ajaxGet(url, params, function(data) {
					var dataLength =  data.returnValue.length;
					var result = "<option value=''>请选择中心</option>";
					$.each(data.returnValue, function(n, value) {
						if(dataLength > 1) {
							result += "<option value='" + value.exchangeCenterId + "'>"
							+ value.exchangeCenterName + "</option>";
						}
						if(dataLength > 0 && dataLength < 2) {
							result += "<option value='" + value.exchangeCenterId + "' selected>"
							+ value.exchangeCenterName + "</option>";
						}
					});
						$("#newCenterId").append(result);
				}, function() {
				});
			}
	        return false ;
	    },change: function( event, ui ) {
			//$("#changeValue").val(this.value).change();
			var containFlag = $.inArray(this.value, dataArray2);
			if(containFlag < 0 ){
				$("#errorMsg2").empty().html("");
				$("#newCenterId").html('');
				$("#linkUserName").val("");
				$("#linkUserCode").val("");
				if(jsonStr.indexOf(this.value) <0) {
					$("#errorMsg2").empty().html("暂无找到当前输入所匹配的名称!");
				}
			}
		}
	    
};
var url = BASE_PATH + "/report/getLinkUserByCondition";
var params = {};
ajaxGet(url, params, function(data) {
	var dataLength =  data.returnValue.length;
	$.each(data.returnValue, function(n, value) {
		dataArray.push({id :value.userCode, label: value.userName+"("+value.userCode+")",value:value.userName});
		dataArray2.push(value.userName+"("+value.userCode+")");
		jsonStr += value.userName+"("+value.userCode+")";
	});
}, function() {
});
$("#inputLinkUserName").autocomplete({source: dataArray}, option).on('focus', function() { $(this).keydown(); });

function isNullOrEmpty(obj){
	if(obj == null || obj == "" || obj == undefined){
		return false;
	}else{
		return true;
	}
}
/* $("#changeValue").change(function () {
    var changeTextValue = $(this).val();
    var containFlag = $.inArray(changeTextValue, dataArray2);
   	$("#errorMsg2").empty().html("");
	$("#newCenterId").html('');
	//输入值不为完整的
    if(containFlag < 0) {
		$("#linkUserName").val("");
		$("#linkUserCode").val("");
		if(""!=changeTextValue){
			if(jsonStr.indexOf(changeTextValue)<0){//不存在的
				$("#errorMsg2").empty().html("暂无找到当前输入所匹配的名称");	
			}
		}
    }else {//输入值为完整的  李晓东（14394）
    	var linkUserCode = $("#linkUserCode").val();
		if(isNullOrEmpty(linkUserCode)) {
			var url = BASE_PATH + "/report/getLinkUserCenter";
			var params = {linkUserCode:linkUserCode};
			ajaxGet(url, params, function(data) {
				var dataLength =  data.returnValue.length;
				var result = "<option value=''>请选择中心</option>";
				$.each(data.returnValue, function(n, value) {
					if(dataLength > 1) {
						result += "<option value='" + value.exchangeCenterId + "'>"
						+ value.exchangeCenterName + "</option>";
					}
					if(dataLength > 0 && dataLength < 2) {
						result += "<option value='" + value.exchangeCenterId + "' selected>"
						+ value.exchangeCenterName + "</option>";
					}
				});
					$("#newCenterId").append(result);
			}, function() {
			});
		}
    	
    }
}); */

</script>
