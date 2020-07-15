<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css?_v=${vs}">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/cashbill/cashbill.js?_v=${vs}"></script>
<div class="" role="main" style="width:1000px;">
	<h4 class="border-bottom pdb10" style="overflow:hidden;">
		<strong style="position:relative;top:10px;">批量请款</strong>
		<span id="errMsg" style="color: red;padding-left: 20px;position:relative;top:10px;"></span>
		<span class="" style="float:right"><a href="javascript:BatchCashBill.close();" class="btn btn-default" style="float:right">&times;</a></span>
	</h4>


	<div class="row">
		<div class="page-content">

			<div id="content">
				<form id="cashBillForm">
					<input type="hidden" id="submitStatus" name="submitStatus" value="0">
					<input type="hidden" id="fileRecordMainIds" name="fileRecordMainIds">
					<input type="hidden" name="id" value="${project.id}">
					<input type="hidden" id="estateCityNo" name="estateCityNo" value="${estateCityNo}">
					<input type="hidden" id="estateId" name="estateId" value="${project.estateId}">
					<input type="hidden" id="estateNm" name="estateNm" value="${project.estateNm}">
					<input type="hidden" id="projectNo" name="projectNo" value="${project.projectNo}">
					<input type="hidden" id="amountNoTax" name="amountNoTax" value="${project.amountNoTax}">
					<input type="hidden" id="amountTax" name="amountTax" value="${project.amountTax}">
					<input type="hidden" id="amountTotal" name="amountTotal" value="${project.amountTotal}">
					<span style="padding-left: 20px;"><strong>项目编号：${project.projectNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;楼盘名称：${project.estateNm}</strong></span>
					<c:forEach items="${project.companyList}" var="company" varStatus="comIndex">
						<table id="companyTb1_${comIndex.index}" class="table-sammary">
							<input type="hidden" name="companyList[${comIndex.index}].companyId" value="${company.companyId}">
							<input type="hidden" name="companyList[${comIndex.index}].companyName" value="${company.companyName}">
							<input type="hidden" name="companyList[${comIndex.index}].id" value="${company.id}">
							<col style="width:123px;">
							<col style="width:auto;">
							<col style="width:123px;">
							<col style="width:auto;">
							<tr>
								<td class="talabel" style="padding-left: 50px;">经纪公司：</td>
								<td>${company.companyName}&nbsp;${company.companyNo}</td>
								<td class="talabel"><i>*</i>付款金额：</td>
								<td><input money1 type="text" class="form-control w140"
										   onchange="formatter(this)"
										   style="height: 27px;" name="companyList[${comIndex.index}].amountNoTax" value="${company.amountTotal}" notnull="true" datatype="needMoney">元
									<span class="fc-warning"></span></td>
							</tr>
							<tr>
								<td class="talabel" style="padding-left: 50px;"><i>*</i>税额：</td>
								<td><input money2 type="text" class="form-control w140"
										   onchange="formatter(this)"
										   style="height: 27px;" name="companyList[${comIndex.index}].amountTax" value="${company.amountTax}" notnull="true" datatype="needMoney">元
									<span class="fc-warning"></span></td>
								<td class="talabel"><i>*</i>说明：</td>
								<td><input type="text" class="form-control w300" style="height: 27px;" name="companyList[${comIndex.index}].remark" value="${company.remark}" style="width: 257px;" notnull="true">
									<span class="fc-warning"></span></td>
							</tr>
						</table>
						<table class="reportClass" id="companyTb2_${comIndex.index}"  width="90%" style="border:solid 1px #adadad;" align="center">
							<col style="width:5%;border-right:#adadad 1px solid">
							<col style="width:14%;border-right:#adadad 1px solid">
							<col style="width:10%;border-right:#adadad 1px solid">
							<col style="width:15%;border-right:#adadad 1px solid">
							<col style="width:8%;border-right:#adadad 1px solid">
							<col style="width:16%;border-right:#adadad 1px solid">
							<col style="width:16%;border-right:#adadad 1px solid">
							<col style="width:6%;border-right:#adadad 1px solid">
							<tr style="height:28px;border-bottom:#adadad 1px solid">
								<td align="center">序号</td>
								<td align="center">报备编号</td>
								<td align="center">楼室号</td>
								<td align="center">客户姓名</td>
								<td align="center">销售面积</td>
								<td align="center">大定总价</td>
								<td align="center">成销总价</td>
								<td align="center">操作</td>
							</tr>
							<c:forEach items="${company.reportList}" var="report" varStatus="index">
								<tr style="height:28px;border-bottom:#adadad 1px solid">
									<input type="hidden" name="companyList[${comIndex.index}].reportList[${index.index}].reportId" value="${report.reportId}">
									<input type="hidden" name="companyList[${comIndex.index}].reportList[${index.index}].reportNo" value="${report.reportNo}">
									<input type="hidden" name="companyList[${comIndex.index}].reportList[${index.index}].id" value="${report.id}">
									<input type="hidden" name="companyList[${comIndex.index}].reportList[${index.index}].buildingNo" value="${report.buildingNo}">
									<input type="hidden" name="companyList[${comIndex.index}].reportList[${index.index}].customerNm" value="${report.customerNm}">
									<input type="hidden" name="companyList[${comIndex.index}].reportList[${index.index}].area" value="${report.area}">
									<input type="hidden" name="companyList[${comIndex.index}].reportList[${index.index}].roughAmount" value="${report.roughAmount}">
									<input type="hidden" name="companyList[${comIndex.index}].reportList[${index.index}].dealAmount" value="${report.dealAmount}">
									<td align="center">${index.index+1}</td>
									<td align="center">${report.reportNo}</td>
									<td align="center"><c:if test="${report.buildingNo eq null}">-</c:if><c:if test="${report.buildingNo ne null}">${report.buildingNo}</c:if></td>
									<td align="center">${report.customerNm}</td>
									<td align="center"><c:if test="${report.area eq null}">-</c:if><c:if test="${report.area ne null}"><fmt:formatNumber value="${report.area}" pattern="#,##0.00" maxFractionDigits="2"/></c:if></td>
									<td align="center"><c:if test="${report.roughAmount eq null}">-</c:if><c:if test="${report.roughAmount ne null}"><fmt:formatNumber value="${report.roughAmount}" pattern="#,##0.00" maxFractionDigits="2"/></c:if></td>
									<td align="center"><c:if test="${report.dealAmount eq null}">-</c:if><c:if test="${report.dealAmount ne null}"><fmt:formatNumber value="${report.dealAmount}" pattern="#,##0.00" maxFractionDigits="2"/></c:if></td>
									<td align="center"><a href="javascript:void(0);" onclick="removeFromCashBill('${report.id}','${company.id}','${project.id}',${comIndex.index},this)">删除</a></td>
								</tr>
							</c:forEach>
						</table>
						<c:if test="${(comIndex.index + 1) eq fn:length(project.companyList)}">
							<hr id="companyHr_${comIndex.index}" align="center" style="margin-top: 20px;margin-bottom: 0px;" width="95%" color="#987cb9" size="1">
						</c:if>
						<c:if test="${(comIndex.index + 1) ne fn:length(project.companyList)}">
							<hr id="companyHr_${comIndex.index}" align="center" width="95%" color="#987cb9" size="1">
						</c:if>
					</c:forEach>
					<table class="table-sammary">
						<tr>
							<td width="27%"></td>
							<td width="12%">不含税请款总计：</td>
							<td width="12%" id="amountNoTaxTd">￥<fmt:formatNumber value="${project.amountNoTax}" pattern="#,##0.00" maxFractionDigits="2"/></td>
							<td width="10%">税额总计：</td>
							<td width="10%" id="amountTaxTd">￥<fmt:formatNumber value="${project.amountTax}" pattern="#,##0.00" maxFractionDigits="2"/></td>
							<td width="12%">本次请款总计：</td>
							<td width="12%" id="amountTotalTd">￥<fmt:formatNumber value="${project.amountTotal}" pattern="#,##0.00" maxFractionDigits="2"/></td>
							<td width="5%"></td>
						</tr>
					</table>
					<table class="table-sammary">
						<col style="width:123px;">
						<col style="width:auto;">
						<col style="width:123px;">
						<col style="width:auto;">
						<tr>
							<td class="talabel" style="padding-left: 30px;"><i>*</i>入账日期：</td>
							<td><input type="text"  name="recordTime" value="${sdk:ymd2(project.recordTime)}" notnull="true" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'${yearMonth}'})" readonly="readonly" class="ipttext Wdate form-control w160 calendar-icon">
								<span class="fc-warning"></span></td>
							<td class="talabel"><i>*</i>预计付款日期：</td>
							<td><input type="text" name="predictPayTime" value="${sdk:ymd2(project.predictPayTime)}" notnull="true" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate form-control calendar-icon" style="width:200px;">
								<span class="fc-warning"></span></td>
						</tr>
						<tr>
							<td class="talabel" style="padding-left: 30px;"><i>*</i>付款方式：</td>
							<td style="width: 157px;">
								<c:if test="${project.payType == null}">
									<t:dictSelect field="payType" id="payType" xmlkey="LOOKUP_Dic"
												  dbparam="P1:223" classvalue="30" defaultVal="22303" txWidth="157px"></t:dictSelect>
								</c:if>
								<c:if test="${project.payType != null}">
									<t:dictSelect field="payType" id="payType" xmlkey="LOOKUP_Dic"
												  dbparam="P1:223" classvalue="30" defaultVal="${project.payType}" txWidth="157px"></t:dictSelect>
								</c:if>
							</td>
							<td class="talabel" style="padding-left: 30px;"><i>*</i>核算主体：</td>
							<td style="width: 200px;">
								<select class="form-control" id="lnkAccountProjectCode" name="lnkAccountProjectCode" notnull="true" style="width:200px;"></select>
								<input type="hidden"  name="lnkAccountProject" id="lnkAccountProject" >
								<span class="fc-warning"></span>
							</td>
						</tr>
					</table>
					<div class="page-content" style="padding-left: 10px;">
						<h4 style="font-size:16px;line-height: 0px;">
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
										<div class="pd10" >
											<i>*</i>成销确认书/佣金结算资料
											<div class="thumb-xs-box" id="fileIdPhotoToCashBill">
												<c:if test="${not empty project.fileList}">
													<c:set  var="fileSize" value="0"/>
													<c:forEach items="${project.fileList}" var="list" varStatus="status">
														<c:set var="fileSize" value="${fileSize + 1}"/>
														<div class="thumb-xs-list item-photo-list">
															<a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
															<span class="thumb-img">
																<span class="thumb-img-container">
																	<span class="thumb-img-content">
																		<img alt="成销确认书/佣金结算资料" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
																	</span>
																</span>
															</span>
																<span class="thumb-bottom-roup">
																<i class="icon down-icon"></i><i class="icon remove-icon btn-remove-photo"></i>
															</span>
															</a>
															<input type="hidden" name="limitSize" value="10">
															<input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
															<input type="hidden" name="fileRecordMainIdHidden" value="${list.fileRecordMainId}" />
															<input type="hidden" name="fileTypeId" value="1032" />
															<input type="hidden" name="fileSourceId" value="9" />
														</div>
													</c:forEach>
												</c:if>
												<div class="thumb-xs-list item-photo-add"
													 <c:if test="${fileSize>=10  }">style="display: none;"</c:if>>
													<input type="hidden" name="limitSize" value="10">
													<a href="javascript:void(0);" class="thumbnail" title="添加附件">
														<input type="file" class="btn-flie file-control" data-limit="10"  multiple="multiple">
														<input type="hidden" name="fileTypeId" value="1032" />
														<input type="hidden" name="fileSourceId" value="9" />
														<input type="hidden" name ="companyId" value="">
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div class="text-center">
				<input type="button" id="btn-submit" onclick="saveCashBill(1);" class="btn btn-primary"  style="width: 100px;background-color: #286090;" value="提交">
				<input type="button" id="btn-save" onclick="saveCashBill(0);" class="btn btn-primary" style="width: 100px;margin-left: 100px!important;background-color: #5bc0de" value="保存">
				<a href="javascript:BatchCashBill.close();" class="btn btn-default" style="width: 100px;margin-left: 100px!important;">取消</a>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/meta/js/common/jquery-ui.js?_v=${vs}"></script>
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
                if(dataLength > 0 && dataLength < 2) {
                    result += "<option value='" + value.lnkaccountProjectCode +"'data-lnkaccountProject='" + value.lnkAccountProject+ "' selected>"
                        + value.lnkaccountProjectCode +"_"+ value.lnkAccountProject+ "</option>";
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