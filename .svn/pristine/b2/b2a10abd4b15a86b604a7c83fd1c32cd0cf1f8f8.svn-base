<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  <head>
    <%@ include file="/WEB-INF/page/common/meta.jsp"%>
	<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
	<link href="${ctx}/meta/css/cssreport/multi.select.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
	<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
	<script type="text/javascript" src="${ctx}/meta/js/relate/relateUtil.js?_v=${vs}"></script> 
	<script type="text/javascript" src="${ctx}/meta/js/relate/relateCompany.js?_v=${vs}"></script>
	<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/estateAdd.js?_v=${vs}"></script>
	<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/estateCommon.js?_v=${vs}"></script>
	<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
	<script type="text/javascript">
		$(function(){
			$('#tmpContactId').on("blur",function(){
			    var url = BASE_PATH + "/report/getLinkUserCenter";
				var tmpContactId = $("#tmpContactId").val();
			    var params = {linkUserCode:tmpContactId};
				ajaxGet(url, params, function(data2) {
					var dataLength =  data2.returnValue.length;
					var result = "<option value=''>请选择业绩归属中心</option>";
					$.each(data2.returnValue, function(n, value) {
						if(dataLength > 1) {
							result += "<option value='" + value.exchangeCenterId + "'>"
							+ value.exchangeCenterName + "</option>";
						}
						if(dataLength > 0 && dataLength < 2) {
							result += "<option value='" + value.exchangeCenterId + "' selected>"
							+ value.exchangeCenterName + "</option>";
						}
					});
					$("#achieveCenter").empty();
					$("#achieveCenter").append(result);
				}, function() {
				});
			});
		});
	</script>
  </head>
  <body>
    <!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	
	<form id="reportAddForm">
		<input type="hidden" name="estateId" id="estateId" value="${estateInfo.estate.estateId}">
		<input type="hidden" name="estateNm" id="estateNm" value="${estateInfo.estate.estateNm}">
		<input type="hidden" name="reportCompanyId" id="reportCompanyId" value="">
		<input type="hidden" name="cityNo" id="cityNo" value="${userInfo.cityNo}">
		
	<div class="container theme-hipage ng-scope" role="main">
		<div class="crumbs">
			<ul style="margin-right:150px;">
				<li><a href="#"  class="a_hover">联动管理</a></li>
				<li><a href="#"  class="a_hover">>案场管理</a></li>
				<li><a href="#"  class="a_hover">>报备</a></li>
			</ul>
		</div>
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>报备</strong></h4>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <strong>
                                <label class="fw-normal w100 text-right"><strong>项目编号：</strong></label>${estateInfo.estate.projectNo}
                                <label class="fw-normal w100 text-right"><strong>楼盘名称：</strong></label>${estateInfo.estate.estateNm}
                            </strong>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li style="width:50%">
                        <div class="form-group">
                            <label class="fw-normal w120 text-right"><i>* </i>公司名称</label>
                            <input type="text" class="form-control w300" id="reportCompanyNm" name="reportCompanyNm" placeholder=""   readonly="readonly">
                            <button style="width:124px;" type="button" class="btn btn-primary"  onclick="javascript:relateCompany('fromLinkage','${releaseCityNo}','${releaseCityNoflag}');">选择公司</button>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                        	<label class="fw-normal w80 text-right"  for="storeNm"><i>* </i>门店</label>
                            <select style="width:300px;" class="form-control" name="storeNm" id="storeNm" readonly onchange="javascript:storeSelect(this);">
                                <option value="">请选择...</option>
							</select>
                            <span class="fc-warning"></span>
                        </div>
                    </li>  
                </ul>
                <ul class="list-inline half form-inline">
                    <li style="width:49%">
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">业绩归属人</label>
                            <input type="hidden" class="form-control w300" id="tmpContactId" name="tmpContactId">
                            <input type="text" class="form-control w300" id="tmpContactNm" name="tmpContactNm" placeholder=""   readonly="readonly">
                            <button type="button" class="btn btn-primary"  onclick="javascript:achieveMentChange2();">选择业绩归属人</button>
                        </div>
                    </li>  
                    <li>
                        <div class="form-group" >
                        	<label   for="achieveCenter" ><i>* </i>业绩归属中心</label>
                        	<input type="hidden"  id="centerGroupId" name="centerGroupId">
                        	<input type="hidden"  id="centerGroupName" name="centerGroupName">
                            <select style="width:300px;" class="form-control" name="achieveCenter" id="achieveCenter" >
                                <!-- <option value="">请选择...</option> -->
							</select>
                            <span class="fc-warning"></span>
                        </div>
                    </li>  
                </ul>
                
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right"  for="reportAgent"><i>* </i>报备经纪人</label>
                            <input type="text" class="form-control w300" id="reportAgent" name="reportAgent" placeholder="请填写报备经纪人"   dataType="normal"   notnull="true"  maxlength="20">
                            <span class="fc-warning" style="font-size: 10px;"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                        	<label class="fw-normal w120 text-right"  for="reportAgentTel"><i>* </i>经纪人手机</label>
                            <input type="text" class="form-control w300" id="reportAgentTel" name="reportAgentTel" placeholder="请填写经纪人手机"   notnull="true" dataType="phone" maxlength="25">
                            <span class="fc-warning" style="font-size: 10px;"></span>
                        </div>
                    </li>  
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right"  for="customerName"><i>* </i>客户姓名</label>
                            <input type="text" class="form-control w300" id="customerName" name="customerName" placeholder="请填写客户姓名"   dataType="normal"   notnull="true"  maxlength="60">
                            <span class="fc-warning" style="font-size: 10px;"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                        	<label class="fw-normal w120 text-right"  for="customerPhone"><i>* </i>客户手机</label>
                            <input type="text" class="form-control w300" id="customerPhone" name="customerPhone" placeholder="请填写客户手机"   notnull="true" dataType="phone" maxlength="11">
                            <span class="fc-warning" style="font-size: 10px;"></span>
                        </div>
                    </li>  
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right"  for="customerNmTwo"><i></i>客户姓名</label>
                            <input type="text" class="form-control w300" id="customerNmTwo" name="customerNmTwo" placeholder="请填写客户姓名"   dataType="normal" maxlength="60">
                            <span class="fc-warning" style="font-size: 10px;"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                        	<label class="fw-normal w120 text-right"  for="customerTelTwo"><i></i>客户手机</label>
                            <input type="text" class="form-control w300" id="customerTelTwo" name="customerTelTwo" placeholder="请填写客户手机"  dataType="phone" maxlength="11">
                            <span class="fc-warning" style="font-size: 10px;"></span>
                        </div>
                    </li>  
                </ul>
                
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right" for="reportDate"><i>* </i>报备日期</label>
                            <input  type="text" class="calendar-icon w300" name="reportDate" id="reportDate" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"class="ipttext Wdate"  notnull="true"/>
                            <span class="fc-warning" style="font-size: 10px;"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">客户人数</label>
                            <t:dictSelect field="customerNum" id="customerNum" xmlkey="LOOKUP_Dic" dbparam="P1:161" nullOption="请选择..." ></t:dictSelect>
                        </div>
                    </li>  
             
                </ul>
                <ul class="list-inline half form-inline">
             
				</ul>
				
				<!-- <ul class="list-inline half form-inline">
                	<li>
                        <div class="form-group">
                            <p style="color:red;position:absolute;left:200px;"> 注：选择日期时，日期灰色不可选代表该月份日期业绩已关账。</p> 
                        </div>
                    </li>  
				</ul> -->
			</div>
		</div>
	</div>

    <input type="hidden" id="fileRecordMainIds" name="fileRecordMainIds">

	<div class="text-center">
		<a href="javascript:reportAdd();" class="btn btn-primary" id="reportPcAddBtn">报备</a>
		<a href="${ctx}/sceneEstate?searchParam=1" class="btn btn-primary mgl20">返回</a>
	</div>
	</form>
  </body>
  <script type="text/javascript">
  $(function(){
	    //上传开始
	    var options = {
	        "url":BASE_PATH + '/file/uploadCommonFile',
	        "isDeleteImage":false,//删除时校验checkEditImage
	        "isAddImage":false,   //添加时校验checkEditImage
	        "isCommonFile":true  //公共上传文件
	    };
	    photoUploader(options,null,null,null);
	});
  </script>
</html>
