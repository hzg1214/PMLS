<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
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
	#ui-id-2 {
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
<form id="yfCenterUserCreateForm" style="height: auto;width: 700px">
	<div role="main">
		<div class="row">
			<div class="page-content">
				<span class="" style="float:right"><a href="javascript:LnkYjReport.close();" class="btn btn-default">&times;</a></span>
                <h4 class="border-bottom pdb10"><strong>维护返佣对象</strong></h4>
				<strong><span>您已经选择<i style="font-size:15px;">${size}</i>条报备房源信息，请指定返佣对象，默认报备经纪公司无需指定。</span></strong>
				<input type="hidden" id="ids" name="ids" value="${ids}">
				<input type="hidden" id="reportIds" name="reportIds" value="${reportIds}">
				<input type="hidden" id="companyNo" name="companyNo" value="${companyNo}">
				<input type="hidden" id="projectNo" name="projectNo" value="${projectNo}">
				<table class="table-sammary">
					<tr class="list-inline form-inline">
						<td>
						<label class="fw-normal w125x text-right" style="vertical-align: top;margin-left: 35px"  for="inputCompanyName"><i>* </i>返佣对象二：</label>
							<input type="text"  name="inputCompanyName" id="inputCompanyName" placeholder="请输入返佣对象" style="width:330px;">
							<!-- <input id="autocomplete" > -->
							<input type="hidden"  name="companyName" id="companyName" >
							<input type="hidden"  name="companyCode" id="companyCode" >
							<input type="hidden"  name="companyNo" id="companyNo" >
							<input type="hidden"  name="changeFlag" id="changeFlag" value="1">
							<input type="hidden"  name="changeValue" id="changeValue" value="">
                  			<span class="fc-warning" id="errorMsg2" ></span>
						</td>
						
					</tr>
					<tr class="list-inline form-inline">
						<td>
						<label class="fw-normal w125x text-right" style="vertical-align: top;margin-left: 35px"  for="inputCompanyNameTwo">返佣对象三：</label>
							<input type="text"  name="inputCompanyNameTwo" id="inputCompanyNameTwo" placeholder="请输入返佣对象" style="width:330px;">
							<input type="hidden"  name="companyNameTwo" id="companyNameTwo" >
							<input type="hidden"  name="companyCodeTwo" id="companyCodeTwo" >
							<input type="hidden"  name="companyNoTwo" id="companyNoTwo" >
							<input type="hidden"  name="changeFlagTwo" id="changeFlagTwo" value="1">
							<input type="hidden"  name="changeValueTwo" id="changeValueTwo" value="">
                  			<span class="fc-warning" id="errorMsg3" ></span>
						</td>
						
					</tr>
				</table>
				<div id="errorMsg" style="color: red;margin-bottom: 5px;visibility: hidden;height: 20px;"></div>
<!--                         <div class="form-group"> -->
<!--                             <label class="fw-normal w200 text-right"></label> -->
<!--                             <span class="fc-warning" id="newWarningMsg"></span> -->
<!--                         </div> -->
<!--                     </li> -->
<!--                 </ul> -->
<!-- 				<div class="text-center"> -->
<%--                 <a href="javascript:AchievementView.saveAchievementAdjut('${estateId}','${companyId}','${customerId}','${relateId}','${fromType}')" class="btn btn-primary mgt20">　保存　</a> --%>
<!-- 				<a href="javascript:AchievementView.closePopup()" class="btn btn-default mgt20 mgl50">　取消　</a> -->
<!--             </div> -->
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
	        $("#inputCompanyName").val(ui.item.label);
	        $("#companyName").val(ui.item.value);
	        $("#companyCode").val(ui.item.id);
	        $("#changeFlag").val("2");
	        return false ;
	    },change: function( event, ui ) {
			var containFlag = $.inArray(this.value, dataArray2);
			if(containFlag < 0 ){
				$("#errorMsg2").empty().html("");
				$("#companyName").val("");
				$("#companyCode").val("");
				if(jsonStr.indexOf(this.value) <0) {
					$("#errorMsg2").empty().html("暂无匹配到当前输入返佣对象!");
					return false;
				}
			}
		}
	    
};
var url = BASE_PATH + "/lnkYjReport/getCompanyByCondition";
var projectNo = $("#projectNo").val();
var params = {projectNo:projectNo};
ajaxGet(url, params, function(data) {
	var dataLength =  data.returnValue.length;
	$.each(data.returnValue, function(n, value) {
		dataArray.push({id :value.companyNo, label: value.companyName+" ("+value.companyNo+")",value:value.companyName});
		dataArray2.push(value.companyName+" ("+value.companyNo+")");
		jsonStr += value.companyName+" ("+value.companyNo+")";
	});
}, function() {
});
$("#inputCompanyName").autocomplete({source: dataArray}, option).on('focus', function() { $(this).keydown(); });
$("#errorMsg3").empty().html("");
var dataArray3=[];
var dataArray4=[];
var jsonStr2="";
var option1 = {
		max: 15,    //列表里的条目数
		minChars: 0,    //自动完成激活之前填入的最小字符
		width: 180,     //提示的宽度，溢出隐藏
		scrollHeight: 200,   //提示的高度，溢出显示滚动条
		matchContains: false,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
		autoFill: true,    //自动填充
		minLength: 1,
		select: function (event, ui) {
			$("#errorMsg3").empty().html("");
	        $("#inputCompanyNameTwo").val(ui.item.label);
	        $("#companyNameTwo").val(ui.item.value);
	        $("#companyCodeTwo").val(ui.item.id);
	        $("#changeFlagTwo").val("2");
	        return false ;
	    },change: function( event, ui ) {
			var containFlag2 = $.inArray(this.value, dataArray4);
			if(containFlag2 < 0 ){
				$("#errorMsg3").empty().html("");
				$("#companyNameTwo").val("");
				$("#companyCodeTwo").val("");
				if(jsonStr2.indexOf(this.value) <0) {
					$("#errorMsg3").empty().html("暂无匹配到当前输入返佣对象!");
					return false;
				}
			}
		}
	    
};
ajaxGet(url, params, function(data) {
	var dataLength =  data.returnValue.length;
	$.each(data.returnValue, function(n, value) {
		dataArray3.push({id :value.companyNo, label: value.companyName+" ("+value.companyNo+")",value:value.companyName});
		dataArray4.push(value.companyName+" ("+value.companyNo+")");
		jsonStr2 += value.companyName+" ("+value.companyNo+")";
	});
}, function() {
});
$("#inputCompanyNameTwo").autocomplete({source: dataArray3}, option1).on('focus', function() { $(this).keydown(); });
// $(".ui-autocomplete ui-front ui-menu ui-widget ui-widget-content").css("z-index:99999");
// $("#ui-id-2").addClass("z-index:99999");

function isNullOrEmpty(obj){
	if(obj == null || obj == "" || obj == undefined){
		return false;
	}else{
		return true;
	}
}
</script>
