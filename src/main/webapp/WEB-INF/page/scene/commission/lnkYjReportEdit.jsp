<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/jquery-ui.css?_v=${vs}">
<script type="text/javascript" src="${ctx}/meta/js/scene/commission/lnkYjReportEdit.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/bootstrap-multiselect.css">
<script type="text/javascript" src="${ctx}/meta/js/common/bootstrap-multiselect.js?_v=${vs}"></script>
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
</head>

<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<form id = "lnkYjReportEditForm" >
	<div class="container theme-hipage ng-scope">
		<div class="row">
			<div class="page-content">
				<input type="hidden" id="reportId" name="reportId" value="${yjReportInfo.reportId}"/>
				<input type="hidden" id="inputCompanyName" name="inputCompanyName" value=""/>
				<input type="hidden" id="inputCompanyNameTwo" name="inputCompanyNameTwo" value=""/>
				<input type="hidden" id="companyNo"  value=""/>
				<input type="hidden" id="companyNoTwo"  value=""/>
				<input type="hidden" id="projectNo" name="projectNo" value="${yjReportInfo.projectNo}">
				<h4 class="border-bottom pdb10">
					<strong>维护返佣对象</strong>
				</h4>
					<table class="table-sammary">
						<col style="width:135px;">
						<col style="width:320px">
						<col style="width:130px;">
						<col style="width:auto">
						<tr>
							<td class="talabel">报备编号：</td>
							<td>${yjReportInfo.reportId}</td>
						</tr>
						<tr>
							<td class="talabel">客户：</td>
							<td>${yjReportInfo.customerNm}</td>
							<td class="talabel">客户电话：</td>
							<td>${yjReportInfo.customerTel}</td>
						</tr>
						
						<c:forEach items="${yjReportInfo.companys}" var="list" varStatus="index">
							<tr>
								<td><label class="fw-normal w125x text-right" style="vertical-align: top;"  for="inputCompanyNameTwo">
									<c:choose>
										<c:when test="${index.count == 1}">
											返佣对象一：
										</c:when>
										<c:when test="${index.count == 2}">
											返佣对象二：
										</c:when>
										<c:otherwise>
											返佣对象三：
										</c:otherwise>
									</c:choose>
								
								
								
								</label></td>
								<c:choose>
									<c:when test="${list.isDb eq 1 && index.count == 1}">
										<td><input type="text"  name="companyName"  value = "${list.companyName}(${list.companyNo})" placeholder="请选择公司"  id="companyName${index.count}" readonly="readonly" style="background-color: #F9F9F9;width:330px;"></td>
									</c:when>
									<c:when test="${list.isDb eq 1}">
<%-- 										<td><input type="text"  name="companyName"  value = "${list.companyName}" style="width:300px;"></td> --%>
											<td>
<!-- 											<label class="fw-normal w125x text-right" style="vertical-align: top;margin-left: 35px"  for="inputCompanyName"><i>* </i>返佣对象一：</label> -->
												<input type="text"  name="companyName" id="fyCompanyName${index.count}" value = "${list.companyName}(${list.companyNo})" placeholder="请输入返佣对象" style="width:330px;">
												<!-- <input id="autocomplete" > -->
												<input type="hidden"  name="companyName${index.count}" id="companyName${index.count}" >
												<input type="hidden"  name="companyCode${index.count}" id="companyCode${index.count}" value="${list.companyNo}" >
												<input type="hidden"  name="changeValue" id="changeValue" value="">
					                  			<span class="fc-warning" id="errorMsg2" ></span>
					                  		</td>
									</c:when>
									<c:otherwise>
										<td><input type="text"  name="companyName" id="fyCompanyName${index.count}" value = "" style="width:330px;"></td>
												<input type="hidden"  name="companyName${index.count}" id="companyName${index.count}" >
												<input type="hidden"  name="companyCode${index.count}" id="companyCode${index.count}" value="${list.companyNo}" >
												<input type="hidden"  name="changeValue" id="changeValue" value="">
					                  			<span class="fc-warning" id="errorMsg3" ></span>
									</c:otherwise>
								</c:choose>
						</c:forEach>
					</table>
					<div id="errorMsg" style="color: red;margin-bottom: 5px;visibility: hidden;height: 20px;"></div>
					<div id="errorMsg4" style="color: red;margin-bottom: 5px;visibility: hidden;height: 20px;"></div>
			</div>
		</div>
	</div> 
	</form>
				<div class="text-center"> 
	                <a href="javascript:LnkYjReport.save();" class="btn btn-primary mgt20">　保存　</a>
					<a href="${ctx}/lnkYjReport?searchParam=1" class="btn btn-default mgt20 mgl50">　取消　</a>
            	</div>
</body>
</html>
<script type="text/javascript" src="${ctx}/meta/js/common/jquery-ui.js?_v=${vs}"></script>
<script>
$("#errorMsg4").empty().html("");
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
			$("#errorMsg4").empty().html("");
	        $("#fyCompanyName2").val(ui.item.label);
	        $("#companyName2").val(ui.item.value);
	        $("#companyCode2").val(ui.item.id);
	        $("#changeFlag2").val("2");
	        return false ;
	    },change: function( event, ui ) {
			var containFlag = $.inArray(this.value, dataArray2);
			if(containFlag < 0 ){
				$("#errorMsg4").empty().html("");
				$("#companyName2").val("");
				$("#companyCode2").val("");
				if(jsonStr.indexOf(this.value) <0) {
					$("#errorMsg4").empty().html("暂无匹配到当前输入返佣对象!");
					$("#errorMsg4").css("visibility","initial");
// 					$("#fyCompanyName2").val("");
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
		dataArray.push({id :value.companyNo, label: value.companyName+"("+value.companyNo+")",value:value.companyName});
		dataArray2.push(value.companyName+"("+value.companyNo+")");
		jsonStr += value.companyName+"("+value.companyNo+")";
	});
}, function() {
});
$("#fyCompanyName2").autocomplete({source: dataArray}, option).on('focus', function() { $(this).keydown(); });
// $("#errorMsg3").empty().html("");
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
			$("#errorMsg4").empty().html("");
	        $("#fyCompanyName3").val(ui.item.label);
	        $("#companyName3").val(ui.item.value);
	        $("#companyCode3").val(ui.item.id);
	        $("#changeFlag3").val("2");
	        return false ;
	    },change: function( event, ui ) {
			var containFlag2 = $.inArray(this.value, dataArray4);
			if(containFlag2 < 0 ){
				$("#errorMsg4").empty().html("");
				$("#companyName3").val("");
				$("#companyCode3").val("");
				if(jsonStr2.indexOf(this.value) <0) {
					$("#errorMsg4").empty().html("暂无匹配到当前输入返佣对象!");
					$("#errorMsg4").css("visibility","initial");
// 					$("#fyCompanyName3").val("");
					return false;
				}
			}
		}
	    
};
ajaxGet(url, params, function(data) {
	var dataLength =  data.returnValue.length;
	$.each(data.returnValue, function(n, value) {
		dataArray3.push({id :value.companyNo, label: value.companyName+"("+value.companyNo+")",value:value.companyName});
		dataArray4.push(value.companyName+"("+value.companyNo+")");
		jsonStr2 += value.companyName+"("+value.companyNo+")";
	});
}, function() {
});
$("#fyCompanyName3").autocomplete({source: dataArray3}, option1).on('focus', function() { $(this).keydown(); });
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

