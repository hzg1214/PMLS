<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/store/storePaymentAdd.js?_v=${vs}"></script>
</head>

<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
<form id="storePaymentForm">
<input type ="hidden"  id ="storeIdArray"   name="storeIdArray"  />
<input type ="hidden"  name="totalAmountSum"  />
<input type ="hidden"  name="contractNo"  value="${contractInfo.contract.contractNo}"/>
<input type ="hidden"  name="paymentNo"  value="${paymentNo}"/>
<input type ="hidden"  name="contractId"  value="${contractInfo.contract.id}"/>
<input type ="hidden"  name="refundStateFlag" id="refundStateFlag"/>
<input type ="hidden"  name="contractCityNo" id="contractCityNo" value="${contractInfo.contract.acCityNo}"/>
<input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"  />
	<div class="container theme-hipage ng-scope">
		<div class="row">
			<div class="page-content">
				<input type="hidden" id="receiveId" name="receiveId" value="${receiveId}"/>
				<h4 class="border-bottom pdb10">
					<strong>保证金退款</strong>
					<%-- <a href="${ctx}/storePayment?searchParam=1" class="btn btn-primary">返回</a> --%>
				</h4>
				<h4
					style="margin-right: 15px;margin-left:15px;border-bottom :1px dashed #e1e1e1;font-size:14px;height: 45px;"">
					<tr>&nbsp;&nbsp;&nbsp;
						<td class="talabel">编号：</td>
							<td id="paymentNo">${paymentNo}</td>
					</tr>
				</h4>
				<table class="table-sammary">
						<col style="width:135px;">
						<col style="width:auto">
						<col style="width:130px;">
						<col style="width:auto">
						<col style="width:145px;">
						<col style="width:auto">
						<tr>
							<td class="talabel">合同编号：</td>
							<td>${contractInfo.contract.contractNo}</td>
							<td class="talabel">协议书编号：</td>
							<td id="agreementNo">${contractInfo.contract.agreementNo}</td>
							<td class="talabel">合同状态：</td>
							<td id="contractTypeName">${contractInfo.contract.contractStatusName}</td>
						</tr>
						<tr>
							<td class="talabel">经纪公司：</td>
							<td id="companyName">${contractInfo.contract.companyName}</td>
							
							<td class="talabel">合作模式：</td>
							<td id="contractTypeName">${contractInfo.contract.contractTypeName}</td>
							<!-- <td class="talabel">本次退款金额：</td>
							<td id="totalAmountSum">
							
							</td> -->
							<td class="talabel">申请人：</td>
							<td id="userName">${userName }</td>
						</tr>
					</table>
				<div class="page-content" style="height: 60px;" >
                    <h4  style="margin-right: 15px;margin-left:15px;font-size:16px;height: 45px;">
                        <strong>填写门店退款信息</strong>
                    </h4>
                </div>
                
               <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right"><i>*</i>退款方式：</label>
                            <select class="form-control" title="" id="paymentType" name="paymentType">
                       			<option value="" selected="selected">请选择...</option>
                       			<option value="21701" >银行转账</option>
                        		<option value="21702" >现金</option>                        		
                        		<option value="21703" >中转</option>
               				</select>
                            <span class="fc-warning"></span>
                        </div>
                      </li>
                      <li style="margin: 0px 4px;">  
                         <div class="form-group" style="width:auto;margin-left: 210px;">
                             <label class="fw-normal w125 text-right" for="backDate"><i>* </i>退款日期</label>
		                    <input  type="text" class="calendar-icon " style="width:200px;"  name="backDate" id="backDate" 
		                     onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'${yearMonth}',maxDate:'%y-%M-%d'})"class="ipttext Wdate"  
		                     notnull="true"/>
		                    <span class="fc-warning"></span>
                        </div>
                       </li>
                </ul>
                <table id="relateStoreTableId" class="table table-striped table-hover table-bordered" style="margin-right: 15px;margin-left:15px;width:1140px">
                    <tr>
                    	<th style="width: 50px;"><input type="checkbox" onclick="swapCheck()" /></th>
                        <th style="width:100px;">门店编号</th>
                        <th style="width:100px;">门店名称</th>
                        <th style="width:200px;">门店地址</th>
                        <th style="width:150px;">应退金额（元）</th>
                        <th style="width:150px;">已退金额（元）</th>
                        <th style="width:150px;">本次退款金额（元）</th>
                        <th >备注</th>
                    </tr>
                    <c:set var="index" value="0"></c:set>
                    <c:forEach items="${contractInfo.storeDetails}" var="storelist"  varStatus="listIndex">
                        <tr class="dataTr">
                             <td><input type="checkbox" value="${storelist.storeId}" id="storeId${index}" name="storeChk" onchange="appendAmountSum(1)"  class="selectReport" 
                             <c:if test="${storelist.disabledFlag=='2'}">
								disabled="true" </c:if>/>
            	 				<input type="hidden" id="storeId" value="${storelist.id}">
           					 </td>
                            <td><a href="javascript:void(0)"
                                   onclick="contractGotoStore('${ctx}/store/${storelist.storeId}','/store')">${storelist.storeNo}</a>
                                   <input type ="hidden"  name="storeNo${storelist.storeId}"  id="storeNo${storelist.storeId}" value="${storelist.storeNo}"/>
                            </td>
                            <td>${storelist.name}
                            	 <input type ="hidden"  name="storeName${storelist.storeId}"  id="storeName${storelist.storeId}" value="${storelist.name}"/>
                            </td>
                            <td>${storelist.addressDetail}</td>
                            <td>
                            <%-- <fmt:formatNumber type="number" value="${storelist.totalAmount}" pattern="0.00" maxFractionDigits="2"/> --%>
                            	 <input type="text" class="form-control" id="totalAmount${storelist.storeId}" 
                            	 value="<fmt:formatNumber type="number" value='${storelist.totalAmount}' pattern="0.00" maxFractionDigits="2"/>"
					               name="totalAmount${storelist.storeId}"  readonly="readonly"  maxlength="9" dataType="moneyWithFlot" >
					               <input type="hidden" name="totalAmount" id="totalAmount" value="${storelist.totalAmount}"/>
                            </td>
                            <td><input type="text" class="form-control" id="paymentAmount${storelist.storeId}" 
                            		 value="<fmt:formatNumber type="number" value='${storelist.paymentAmount}' pattern="0.00" maxFractionDigits="2"/>"
					               name="paymentAmount${storelist.storeId}" readonly="readonly"  maxlength="9" dataType="moneyWithFlot" ></td>
					               <input type="hidden" name="paymentAmount" id="paymentAmount" value="${storelist.paymentAmount}"/>
                            <td><input type="number" class="form-control" id="amount${storelist.storeId}" 
					               name="amount${storelist.storeId}" onchange="appendAmountSum(0)"  maxlength="9"  onkeypress="return myNumberic(event)"></td>
					              <span style="color:#FF0000;margin-left:25px;" id="errorTip2" class="errorTip2"> &nbsp;本次退款金额请填写两位小数以内的数字格式!</span>
					               <input type="hidden" name="amount" id="amount" value="${storelist.amount}" />
                            <td>
                            	<textarea name="remark${storelist.storeId}" id="remark${storelist.storeId}" style="width:200px;height:34px"  notnull="true" ></textarea>
                            </td>
                        </tr>
                        <c:set var="index" value="${index+1}"></c:set>
                    </c:forEach>
                     
                </table>
                <div class="page-content" style="height: 60px;margin-left:15px;" id="totalAmountSum2">
                   <div id="totalAmountSum2"></div>
                </div>
                <table class="table-sammary" name="Viewerbox">
                    <col style="width:145px;">
                    <col style="width:auto">
                    <tr>
                        <td colspan="2">
							<div class="" role="main">
							       <div class="row">
							          <div class="pd10">
										<h4 class="thumb-title">
											<i>*</i>附件
										</h4>
										<div class="thumb-xs-box" id="fileRecordMainAttachment">
											<div class="thumb-xs-list item-photo-add" >
												<input type="hidden" name="limitSize" value="10">
												<a href="javascript:void(0);" class="thumbnail" title="添加附件">
													<input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
									                <input type="hidden" name="fileTypeId" value="1027" />
									                <input type="hidden" name="fileSourceId" value="7" />
									                <input type="hidden" name ="companyId" value="">
												</a>
											</div>
										</div>
											<i class="fontset">注：选择日期时，日期灰色不可选代表该月份日期业绩已关账。</i>
										</div>
							    	</div>
							 </div> 
                        </td>
                    </tr>
                   </table>
                   
			</div>
		</div>
		<div class="text-center" style="margin-top:40px">
                <input type="button" id="btn-submit" onclick="addSubmit();" class="btn btn-primary" style="width: 100px;margin-left: 100px!important;" value="保存"> 
                <a href="${ctx}/storePayment?searchParam=1" class="btn btn-default" style="width: 100px;margin-left: 100px!important;">取消</a>
            </div>
	</div> 
</form>
</body>
</html>
<script type="text/javascript">
	
	//计算总的金额
	/* var totalCount = 0;
	var paymentAmountCount = 0;
   	$("#relateStoreTableId").find(".dataTr").each(function(){
   		var trCount = $(this).find("#totalAmount").val();
   		if(isBlank(trCount)){
   			trCount = 0;
   		}
   		trCount = parseInt(trCount,10);
   		totalCount += trCount;
   		var paymentAmount = $(this).find("#paymentAmount").val();
   		if(isBlank(paymentAmount)){
   			paymentAmount = 0;
   		}
   		paymentAmount = parseInt(paymentAmount,10);
   		paymentAmountCount += paymentAmount;
   	});
   	
   	$("input[name=totalAmountSum]").val(totalCount.toFixed(2));  
   	$("#totalAmountSum2").append('本次合计退款金额 &nbsp;&nbsp;￥'+ '<strong>'+totalCount.toFixed(2)+'</strong>');
   	//本次退款金额
   	paymentAmountMoney = totalCount - paymentAmountCount;
   	$("#totalAmountSum").text('￥'+paymentAmountMoney.toFixed(2)); */
  /*  	$("#totalAmountSum2").append(); */
   	
</script>
