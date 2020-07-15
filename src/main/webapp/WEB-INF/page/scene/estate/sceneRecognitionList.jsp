<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/scene/estate/sceneRecognition.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<style>
    .sys_masklock{
        position: fixed;
    }
</style>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	
	<div class="container theme-hipage ng-scope" role="main">
		<div class="crumbs">
			<ul style="margin-right:150px;">
				<li><a href="#"  class="a_hover">联动管理</a></li>
				<li><a href="#"  class="a_hover">>案场管理</a></li>
				<li><a href="#"  class="a_hover">>项目</a></li>
			</ul>
		</div>
        <div class="row">
        	<%-- <div style="height:34px; line-height:34px;" class="mgb10"><span class="blue"><strong>项目编号：${estate.projectNo}    &nbsp;&nbsp;&nbsp;楼盘名称：${estate.estateNm}</strong></span><a href="${ctx}/sceneEstate?searchParam=1" class="float-right btn btn-primary">返回</a></div> --%>
        	<div style="height:34px; line-height:34px;" class="mgb10">
        	<h4 class="border-bottom pdb10" ><strong style="font-size:18px;">项目编号：${estate.projectNo}    &nbsp;&nbsp;&nbsp;楼盘名称：${estate.estateNm} </strong>
					<input id="projectNo" name="projectNo" type="hidden" value="${estate.projectNo}"/>
					<input id="estateName" name="estateName" type="hidden" value="${estate.estateNm}"/>
					<input id="estateId" name="estateId" type="hidden" value="${estateId}">
				<a href="${ctx}/sceneEstate?searchParam=1" class="float-right btn btn-primary" style="float: right;margin-top: -10px;">返回</a>

				<c:if test="${oaOperatorStr eq '1' and estate.signDate ne null}">
<%-- 					<strong style="font-size:16px;"><a href="javascript:void(0);" id="toCashBillPop" data-num="${cashCount}" onclick="BatchCashBill.fnToCashBillPop('${estateId}')" class="float-right" style="float: right;margin-right: 10px;">批量请款(${cashCount}套)</a></strong> --%>
					<strong style="font-size:16px;"><a href="javascript:void(0)"  onclick="openCashBillPop('${estateId}',this)" id="toCashBillPop" data-num="${cashCount}"
													   class="float-right" style="float: right;margin-right: 10px;">批量请款(${cashCount}套)</a></strong>
				</c:if>
                <shiro:hasPermission name="/lnk:BL_PERMISSION">
					<strong style="font-size:16px;"><a class="float-right" style="margin-right: 10px;" href="javascript:void(0)"  id="toBatchSalePop" onclick="openBatchSale('${estate.projectNo}','${estate.estateNm}','${estateId}');">批量成销(${batchSaleCount}套)</a></strong>
                </shiro:hasPermission>
				<input type="hidden" id="batchSaleCount" value="${batchSaleCount}"/>
			</h4>
			</div>

        		<div class="page-content">
                <form  id="SceneRecognitionListForm">
                	<input type="hidden" name="orderName" value="dateCreate">
					<input type="hidden" name="orderType" value="DESC">
					<input type="hidden" name="cityNo" value="${userInfo.cityNo}">
					<input type="hidden" name="estateId" value="${estateId}">
					<input type="hidden" name="searchParmCache" value="1">
					<input type="hidden" id="curPageTemp" value="1">
					<input type="hidden" id="sysPageChaneTemp" value="1">
					<input type="hidden" name="progress" id="progress" value="">
					<input type="hidden" name="yearMonth" id="yearMonth" value="${yearMonth}"/>
					<input type="hidden" name="isShowCurrDate" id="isShowCurrDate" value="${isShowCurrDate}"/>
                    <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="form-inline">
                        	<label class="control-label">状态：</label>
                        	<div class="form-group">
                        		<input type="checkbox" value="13501,13502" id="checkbox1" name="status" ><label for="checkbox1" class="fon-normal small">报备</label>
								<input type="checkbox" value="13502,13503" id="checkbox2" name="status" ><label for="checkbox2" class="fon-normal small">带看</label>
								<input type="checkbox" value="13504,13505" id="checkbox4" name="status" ><label for="checkbox4" class="fon-normal small">大定</label>
								<input type="checkbox" value="13507,13507" id="checkbox5" name="status" ><label for="checkbox5" class="fon-normal small">成销</label>
								<input type="checkbox" value="13508,13508" id="checkbox6" name="status" ><label for="checkbox6" class="fon-normal small">结佣</label>
	                        </div>
	                        <div class="form-group">
	                        	<!-- 归属项目组 -->
	                            <select class="form-control" title="" id="projectDepartmentId" name="projectDepartmentId" notnull="true" readonly>
	                                <option value="" selected>请选择归属项目部</option>
	                                <c:forEach items="${rebacklist}" var="list">
	                                     <option value="${list.projectDepartmentId}">${list.projectDepartment}</option>
	                                </c:forEach>
                            	</select>
	                        </div>
							<div class="form-group">
								<select class="form-control" title="" id="customerFrom" name="customerFrom">
									<option value="" selected="selected">请选择报备来源</option>
									<option value="17401">CRM</option>
									<option value="17403">APP</option>
									<option value="17405">友房通</option>
								</select>
							</div>
				              <div class="form-group">
				                <input type="text" class="form-control " id="estateNm" name="estateNm"  placeholder="请输入报备编号/客户姓名/手机号/经纪人" style="width:280px">
				              </div>
				              <div class="form-group">
				                <input type="text" class="form-control " id="buildingNo" name="buildingNo"  placeholder="请输入楼室号" style="width:120px">
				              </div>
				              <div class="form-group">
				                <input type="text" class="form-control " id="companyNm" name="companyNm"  placeholder="请输入经纪公司" style="width:125px">
				              </div>	
                        </div>
                        
                        <div class="form-inline">
                        	<div class="form-group">
		                  	<!-- 日期类型 -->
		                  	<t:dictSelect field="dateTypeKbn" id="dateTypeKbn" xmlkey="LOOKUP_Dic" dbparam="P1:137" nullOption="请选择日期类型" classvalue="10" ></t:dictSelect>
		                  </div>
		                  <%-- <div class="form-group">
		                  	<!-- 日期 -->
		                  	<t:dictSelect field="dateKbn" id="dateKbn" xmlkey="LOOKUP_Dic" dbparam="P1:139" nullOption="请选择日期" classvalue="10" ></t:dictSelect>
		                  </div> --%>
		                  <div class="form-group">
		                    <div class="input-group">
		                    	<input  type="text" class="calendar-icon form-control w100" name="dateStart" id="dateStart" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate"/>
		                    </div>
		                  </div>
		                  <div class="form-group">
		                    <div class="input-group">
		                      	<input  type="text" class="calendar-icon form-control w100" name="dateEnd" id="dateEnd" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate"/>
		                    </div>
		                  </div>
		                   <div class="form-group">
				                <input style="margin-left:10px;" type="text" class="form-control " id="stroreAddressDetail" name="stroreAddressDetail"  placeholder="请输入门店地址" style="width:140px">
				            </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button style="margin-left:60px;" type="button" class="btn btn-primary" id="J_search" onclick="javascript:SceneRecognition.search();">查询</button>
	                           <button style="margin-left:10px;" type="button" id="btn-reset" class="btn btn-default" onclick="javascript:SceneRecognition.reset();">重置</button>
                        </div>
                    </div>
                </div>
                    <!-- 异步加载列表内容 -->
	                
                
                <!-- <caption>
		            <div class="btn-group float-right">
		                <a class="btn btn-primary" id="J_statusAffirm" href="javascript:SceneRecognition.recognition()" style="margin-bottom:10px">批量状态认定</a>
		            </div>
		        </caption> -->
                <div id="load_content">
			           <div id="LoadCxt"></div>
			    	</div>
                </form>
                
            </div>
        </div>
    </div>

</body>
<script type="text/javascript">
    var searchParamMap = '${searchParamMap}';
    if (searchParamMap != undefined && searchParamMap != null && searchParamMap != '') {
        SceneRecognition.setSearchParams(searchParamMap);
    }
    
    var ReportDetail=function(){}
    ReportDetail.close = function() {
    	ReportDetail.dialog.close();
    };
    function toOperDetailUpdate(reportId,reportDetailId) {
    	var url = BASE_PATH + "/report/toOperDetailUpdate";
        var params = {
                "reportId":reportId,
                "reportDetailId":reportDetailId
                };
        
        var dialogOptions = {
                
                width : 500, 
                height : 100,
            ok : function() {
                // 确定
                var reback = ReportDetail.updateOperDetail(reportId,reportDetailId);
                return reback;
            },
            okVal : '确定',
            cancel : true,
            cancelVal : '返回'
        };
        Dialog.ajaxOpenDialog(url, params, "修改", function(dialog, resData) {
        	ReportDetail.dialog = dialog;
        }, dialogOptions);
    };

    function handlerFileInfo(){
    	//验证带单附件
    	if($('#fileIdPhotoToSee> .item-photo-list').length && $('#fileIdPhotoToSee> .item-photo-list').length>0){
    	}else{
    		/* $("#errorMsg").find(".fc-warning").empty().html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请上传带看单!"); */
    		alert("请上传带看单!");
    		return false;
    	}
    	//验证大定单附件
    	if($('#fileIdPhotoBox> .item-photo-list').length && $('#fileIdPhotoBox> .item-photo-list').length>0){
    	}else{
    		/* $("#errorMsg").find(".fc-warning").empty().html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请上传大定单!"); */
    		alert("请上传大定单!");
    		return false;
    	}
    	//验证甲方项目负责人名片附件
    	if($('#fileIdPhotoCard> .item-photo-list').length && $('#fileIdPhotoCard> .item-photo-list').length>0){
    	}else{
    		/* $("#errorMsg").find(".fc-warning").empty().html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请上传甲方项目负责人名片!"); */
    		alert("请上传甲方项目负责人名片!");
    		return false;
    	}
        var bol = true;
        var fileRecordMainIds = "";
        $("input[name=fileRecordMainIdHidden]").each(function(){
            if($(this).val()==""){
                Dialog.alertError("图片上传出现异常，请删除重新上传。");
                bol = false;
                return false;
            }
            fileRecordMainIds += ","+$(this).val();
        })
        if(fileRecordMainIds!=""){
            fileRecordMainIds = fileRecordMainIds.substring(1);
        }
        $("#fileRecordMainIds").val(fileRecordMainIds);

        return bol;
    }

    ReportDetail.updateOperDetail = function(reportId,reportDetailId) {
        systemLoading("", true);
        if(!handlerFileInfo()){
        	 systemLoaded();
            return false;
    	}
        var buildingNo = $("#buildingNo").val();
        var reportIdHi = $("#reportIdHi").val();
        var flag = buildingNoRepeatCount(buildingNo,reportIdHi,5);
        if(!flag){
        	systemLoaded();
//         	$("#errorMsg").find(".fc-warning").empty().html("该楼室号已报备，请勿重复录入！");
        	$("#buildingNo").focus(); 
    		return false;
        }
       
        if (Validator.validForm("operDetailForm")) {
        	
        	var oldCustomerNm = $("#oldCustomerNm").val();
        	var customerNm = $("#customerNm").val();
        	var oldCustomerTel = $("#oldCustomerTel").val();
        	var customerTel = $("#customerTel").val();
        	var oldBuildingNo = $("#oldBuildingNo").val();
        	var buildingNo = $("#buildingNo").val();
        	var oldRoughArea = $("#oldRoughArea").val();
        	var roughArea = $("#roughArea").val();
        	var oldRoughAmount = $("#oldRoughAmount").val();
        	var roughAmount = $("#roughAmount").val();
        	var oldArea = $("#oldArea").val();
        	var area = $("#area").val();
        	var oldDealAmount = $("#oldDealAmount").val();
        	var dealAmount = $("#dealAmount").val();
        	var oldBizOperDate = $("#oldBizOperDate").val();
        	var bizOperDate = $("#bizOperDate").val();
        	var fileRecordMainIds = $("#fileRecordMainIds").val();
            var oldFileRecordMainIds = $("#oldFileRecordMainIds").val();
            
          	var oldCustomerNmTwo = $("#oldCustomerNmTwo").val();
        	var customerNmTwo = $("#customerNmTwo").val();
        	var oldCustomerTelTwo = $("#oldCustomerTelTwo").val();
        	var customerTelTwo = $("#customerTelTwo").val();
        	var yearMonthDate = $("#yearMonthDate").val();
        	if(oldCustomerNm == customerNm && oldCustomerTel == customerTel 
        			&& oldBuildingNo == buildingNo && oldRoughArea == roughArea
        			&& oldRoughAmount == roughAmount && oldArea == area
        			&& oldDealAmount == dealAmount && oldBizOperDate == bizOperDate
    				&& fileRecordMainIds == oldFileRecordMainIds
    				&& oldCustomerNmTwo == customerNmTwo && oldCustomerTelTwo == customerTelTwo){
        		/* $("#errorMsg").find(".fc-warning").empty().html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未做改动"); */
        		systemLoaded();
        		alert("未做改动!");
        		return false;
        	}
        	if($("#customerNmTwo").val() !="") {
       			if ($("#customerTelTwo").val()==undefined || $("#customerTelTwo").val()==null || $("#customerTelTwo").val()=='') {
       				$("#errorMsg").find(".fc-warning").empty().html("客户已填写，客户手机必须填写！");
       				systemLoaded();
       				$("#customerTelTwo").focus();
       				return false;
       			}
       		}
       		if($("#customerTelTwo").val() !="") {
       			if ($("#customerNmTwo").val()==undefined || $("#customerNmTwo").val()==null || $("#customerNmTwo").val()=='') {
       				$("#errorMsg").find(".fc-warning").empty().html("客户手机已填写，客户必须填写！");
       				systemLoaded();
       				$("#customerNmTwo").focus(); 
       				return false;
       			}
       		}
        	
        	if(yearMonthDate != ""  && yearMonthDate != null && yearMonthDate !=undefined){
        		if(!tab(yearMonthDate,bizOperDate)){
        			$("#errorMsg").find(".fc-warning").empty().html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大定日期小于关帐日期!");
        			systemLoaded();
        			alert("选择的大定日期处在关账月份，请重新选择!");
        			return false;
        		}
        	}
            var url = BASE_PATH + "/report/operDetailUpdate/"+reportId+"/"+reportDetailId;
            var params = $("#operDetailForm").serialize();
            ajaxGet(url, params, function(data) {
                systemLoaded();
                if (data.returnCode != 200) {
                    /* $("#errorMsg").find(".fc-warning").empty().html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+data.returnMsg); */
                    alert(data.returnMsg);
                    return false;
                }else {
                	ReportDetail.close();
                	location.reload(true);
                }
            }, function(data) {
               /*  $("#errorMsg").find(".fc-warning").empty().html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+data.returnMsg); */
                systemLoaded();
                alert(data.returnMsg);
                return false;
            });
        }
        setTimeout(function() {
            systemLoaded();
        }, 3000);
        return false;
    };
 function tab(date1,date2){
    	var flag = true;
        var oDate1 = new Date(date1);
        var oDate2 = new Date(date2);
        if(oDate1.getTime() > oDate2.getTime()){
        	//清空
        	flag = false;
            return flag;
        }
        return flag;
    }
</script>
</html>
