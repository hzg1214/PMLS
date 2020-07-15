<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css?_v=${vs}">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
 <div class="container" role="main">
 
    <span class="" style="float:right"><a href="javascript:ReportDetail.close();" class="btn btn-default">&times;</a></span>
        <form id="operDetailForm">
        <input type="hidden" name="progress" value="${info.progress}">
        <input type="hidden" name="cityNo" value="${info.cityNo}">
        <input type="hidden" id="fileRecordMainIds" name="fileRecordMainIds">
        <input type="hidden" id="oldFileRecordMainIds" name="oldFileRecordMainIds">
        <input type="hidden" id="id" name="id" value="${info.id}">
        <input type="hidden" id="reportIdHi" name="reportIdHi" value="${info.reportId}">
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>${info.progressNm}修改</strong></h4>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <strong>
                                <label class="fw-normal w120 text-right"><strong>楼盘名称：</strong></label>${info.estateNm}
                                <label class="fw-normal w120 text-right"><strong>项目编号：</strong></label>${info.projectNo}
                            </strong>
                        </div>
                    </li>
                </ul>
               <ul class="list-inline half form-inline">
				<li>
					<div class="form-group">
						<label class="fw-normal w120 text-right">报备编号：</label>${info.reportId}
					</div>
				</li>
				<li>
					<div class="form-group">
						<label class="fw-normal w120 text-right">报备来源：</label>${info.customerFromNm}
					</div>
				</li>
				</ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">经纪公司：</label>${info.companyNm}
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w120 text-right">门店：</label>${info.storeNm}
                        </div>
                    </li>
                </ul>
                
               <ul class="list-inline half form-inline">
				<li>
					<div class="form-group">
						<label class="fw-normal w120 text-right">报备经纪人：</label>${info.reportAgent}
					</div>
				</li>
				<li>
					<div class="form-group">
						<label class="fw-normal w120 text-right">经纪人手机：</label>${info.reportAgentTel}

					</div>
				</li>
			 </ul>
                
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <input type="hidden" id = "oldCustomerNm" name="oldCustomerNm" value="${info.customerNm}">
                            <label class="fw-normal w120 text-right" for="customerNm"><i>*</i>客户姓名：</label>
		                    <input type="text" class="form-control w160" name="customerNm"  id="customerNm" placeholder="请输入客户姓名" notnull="true" maxlength="60"
		                        value="${info.customerNm}"> <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <input type="hidden" id = "oldCustomerTel" name="oldCustomerTel" value="${info.customerTel}">
                            <label class="fw-normal w120 text-right" for="customerTel"><i>*</i>客户电话：</label>
                            <input type="text" class="form-control w160" name="customerTel" id="customerTel" dataType="phone" placeholder="请输入客户电话" notnull="true" maxlength="11"
                                   value="${info.customerTel}"> <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <input type="hidden" id = "oldCustomerNmTwo" name="oldCustomerNmTwo" value="${info.customerNmTwo}">
                            <input type="hidden" id = "customerIdTwo" name="customerIdTwo" value="${info.customerIdTwo}">
                            <label class="fw-normal w120 text-right" for="customerNmTwo"><i></i>客户姓名：</label>
		                    <input type="text" class="form-control w160" name="customerNmTwo"  id="customerNmTwo" placeholder="请输入客户姓名" maxlength="60"
		                        value="${info.customerNmTwo}"> <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <input type="hidden" id = "oldCustomerTelTwo" name="oldCustomerTelTwo" value="${info.customerTelTwo}">
                            <label class="fw-normal w120 text-right" for="customerTelTwo"><i></i>客户电话：</label>
                            <input type="text" class="form-control w160" name="customerTelTwo" id="customerTelTwo" dataType="phone" placeholder="请输入客户电话"  maxlength="11"
                                   value="${info.customerTelTwo}"> <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <c:if test="${info.progress eq 13505}">
                    <c:set var="readOnly" value="readonly"></c:set>
                </c:if>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <input type="hidden" id = "oldBuildingNo" name="oldBuildingNo" value="${info.buildingNo}">
                            <label class="fw-normal w120 text-right" for="buildingNo"><i>*</i>楼室号：</label>
		                    <input type="text" class="form-control w160" style="background-color: #ddd;" name="buildingNo" oninput="this.value=this.value.replace(/\s+/g,'')"
		                       ${readOnly} id="buildingNo" notnull="true" maxlength="25" value="${info.buildingNo}"> <span class="fc-warning"></span>
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <input type="hidden" id = "oldRoughArea" name="oldRoughArea" value='<fmt:formatNumber value="${info.roughArea}" pattern="#.00"/>'>
                            <label class="fw-normal w120 text-right" for="roughArea"><i>*</i>大定面积：</label>
                            <input type="text" class="form-control w160" style="background-color: #ddd;" name="roughArea" id="roughArea"
                                   notnull="true" maxlength="7" dataType="twodecimal" oninput="this.value=this.value.replace(/\s+/g,'')"
                            ${readOnly} value="<fmt:formatNumber value="${info.roughArea}" pattern="#.00"/>">㎡ <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                <c:choose>
                    <c:when test="${info.progress eq 13505}">
                        <ul class="list-inline half form-inline">
                            <li>
                                <div class="form-group">
                                    <input type="hidden" id = "oldRoughAmount" name="oldRoughAmount" value='<fmt:formatNumber value="${info.roughAmount}" pattern="#.00"/>'>
                                    <label class="fw-normal w120 text-right" for="roughAmount"><i>*</i>大定总价：</label>
                                    <input type="text" class="form-control w160" style="background-color: #ddd;" name="roughAmount" oninput="this.value=this.value.replace(/\s+/g,'')"
                                        ${readOnly} id="roughAmount" notnull="true" maxlength="12" dataType="needMoney" value="<fmt:formatNumber value="${info.roughAmount}" pattern="#.00"/>"
                                    >元 <span
                                        class="fc-warning"></span><span class="yzroughAmount"></span>
                                </div>
                            </li>
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w120 text-right" ><i>*</i>大定日期：</label>
                                    <input type="text" class="form-control w160" style="background-color: #ddd;" readonly="readonly" id="roughDateRead" name="roughDateRead" value="${sdk:ymd2(info.roughDate)}"/>
                                </div>
                            </li>
                        </ul>
                        <ul class="list-inline half form-inline">
                            <li>
                                <input type="hidden" id = "oldArea" name="oldArea" value='<fmt:formatNumber value="${info.area}" pattern="#.00"/>'>
                                <div class="form-group">
                                    <label class="fw-normal w120 text-right" for="area"><i>*</i>成销面积：</label>
                                    <input type="text" class="form-control w160" name="area" id="area"
                                           notnull="true" maxlength="7" dataType="twodecimal"
                                           value="<fmt:formatNumber value="${info.area}" pattern="#.00"/>">㎡ <span class="fc-warning"></span>
                                </div>
                            </li>
                            <li>
                                <input type="hidden" id = "oldDealAmount" name="oldDealAmount" value='<fmt:formatNumber value="${info.dealAmount}" pattern="#.00"/>'>
                                <div class="form-group">
                                    <label class="fw-normal w120 text-right" for="dealAmount"><i>*</i>成销总价：</label>
                                    <input type="text" class="form-control w160" name="dealAmount"
                                           id="dealAmount" notnull="true" maxlength="12" dataType="needMoney" value="<fmt:formatNumber value="${info.dealAmount}" pattern="#.00"/>"
                                    >元 <span
                                        class="fc-warning"></span><span class="yzdealAmount"></span>
                                </div>
                            </li>
                        </ul>
                        <ul class="list-inline half form-inline">
                            <li>
                                <div class="form-group">
                                    <fmt:parseDate value="${info.bizOperDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    <input type="hidden" id = "oldBizOperDate" name="oldBizOperDate" value='<fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd"/>'>
                                    <label class="fw-normal w120 text-right" for="bizOperDate"><i>*</i>${info.progressNm}日期：</label>
                                    <c:choose>
                                        <c:when test="${not empty yearMonth}">
                                            <input type="hidden" id ="yearMonthDate" name="yearMonthDate" value="${yearMonth}">
                                            <c:if test="${info.progress eq 13504}">
                                                <c:if test="${(info.pledgedDate ne null) and (info.pledgedDate ge yearMonth)}">
                                                    <input type="hidden" id="minDate" name="minDate" value='${info.pledgedDate}'>
                                                </c:if>
                                                <c:if test="${(info.pledgedDate ne null) and (info.pledgedDate lt yearMonth)}">
                                                    <input type="hidden" id="minDate" name="minDate" value='${yearMonth}'>
                                                </c:if>

                                                <c:if test="${(info.pledgedDate eq null) and (info.seeDate ge yearMonth)}">
                                                    <input type="hidden" id="minDate" name="minDate" value='${info.seeDate}'>
                                                </c:if>
                                                <c:if test="${(info.pledgedDate eq null) and (info.seeDate lt yearMonth)}">
                                                    <input type="hidden" id="minDate" name="minDate" value='${yearMonth}'>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${(info.progress eq 13505) and (info.roughDate ge yearMonth)}">
                                                <input type="hidden" id="minDate" name="minDate" value='${info.roughDate}'>
                                            </c:if>
                                            <c:if test="${(info.progress eq 13505) and (info.roughDate lt yearMonth)}">
                                                <input type="hidden" id="minDate" name="minDate" value='${yearMonth}'>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${info.progress eq 13504}">
                                                <c:if test="${info.pledgedDate ne null}">
                                                    <input type="hidden" id="minDate" name="minDate" value='${info.pledgedDate}'>
                                                </c:if>
                                                <c:if test="${info.pledgedDate eq null}">
                                                    <input type="hidden" id="minDate" name="minDate" value='${info.seeDate}'>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${info.progress eq 13505}">
                                                <input type="hidden" id="minDate" name="minDate" value='${info.roughDate}'>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" class="form-control w160" class="calendar-icon" name="bizOperDate" id="bizOperDate" value='<fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd"/>' onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'minDate\')}'})"
                                           notnull="true" class="ipttext Wdate" />
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                            <li>
                                <div class="form-group">
                                    <label class="fw-normal w120 text-right" for="settleConfirmDate">结算确认日期：</label>
                                    <input type="hidden" id="closeDate" value="${yearMonth}">
                                    <input type="hidden" class="form-control w160" name="settleConfirmDateOld" id="settleConfirmDateOld" value="${sdk:ymd2(info.settleConfirmDate)}" />

                                    <input type="text" class="form-control w160" name="settleConfirmDate" id="settleConfirmDate" value="${sdk:ymd2(info.settleConfirmDate)}" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'closeDate\')}',maxDate:'%y-%M-%d'})"
                                           readonly="readonly" class="ipttext Wdate" />
                                </div>
                            </li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="list-inline half form-inline">
                            <li>
                                <div class="form-group">
                                    <input type="hidden" id = "oldRoughAmount" name="oldRoughAmount" value='<fmt:formatNumber value="${info.roughAmount}" pattern="#.00"/>'>
                                    <label class="fw-normal w120 text-right" for="roughAmount"><i>*</i>大定总价：</label>
                                    <input type="text" class="form-control w160" style="background-color: #ddd;" name="roughAmount" oninput="this.value=this.value.replace(/\s+/g,'')"
                                        ${readOnly} id="roughAmount" notnull="true" maxlength="12" dataType="needMoney" value="<fmt:formatNumber value="${info.roughAmount}" pattern="#.00"/>"
                                    >元 <span
                                        class="fc-warning"></span><span class="yzroughAmount"></span>
                                </div>
                            </li>
                            <li>
                                <div class="form-group">
                                    <fmt:parseDate value="${info.bizOperDate}" var="bizOperDate" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    <input type="hidden" id = "oldBizOperDate" name="oldBizOperDate" value='<fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd"/>'>
                                    <label class="fw-normal w120 text-right" for="bizOperDate"><i>*</i>${info.progressNm}日期：</label>
                                    <c:choose>
                                        <c:when test="${not empty yearMonth}">
                                            <input type="hidden" id ="yearMonthDate" name="yearMonthDate" value="${yearMonth}">
                                            <c:if test="${info.progress eq 13504}">
                                                <c:if test="${(info.pledgedDate ne null) and (info.pledgedDate ge yearMonth)}">
                                                    <input type="hidden" id="minDate" name="minDate" value='${info.pledgedDate}'>
                                                </c:if>
                                                <c:if test="${(info.pledgedDate ne null) and (info.pledgedDate lt yearMonth)}">
                                                    <input type="hidden" id="minDate" name="minDate" value='${yearMonth}'>
                                                </c:if>

                                                <c:if test="${(info.pledgedDate eq null) and (info.seeDate ge yearMonth)}">
                                                    <input type="hidden" id="minDate" name="minDate" value='${info.seeDate}'>
                                                </c:if>
                                                <c:if test="${(info.pledgedDate eq null) and (info.seeDate lt yearMonth)}">
                                                    <input type="hidden" id="minDate" name="minDate" value='${yearMonth}'>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${(info.progress eq 13505) and (info.roughDate ge yearMonth)}">
                                                <input type="hidden" id="minDate" name="minDate" value='${info.roughDate}'>
                                            </c:if>
                                            <c:if test="${(info.progress eq 13505) and (info.roughDate lt yearMonth)}">
                                                <input type="hidden" id="minDate" name="minDate" value='${yearMonth}'>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${info.progress eq 13504}">
                                                <c:if test="${info.pledgedDate ne null}">
                                                    <input type="hidden" id="minDate" name="minDate" value='${info.pledgedDate}'>
                                                </c:if>
                                                <c:if test="${info.pledgedDate eq null}">
                                                    <input type="hidden" id="minDate" name="minDate" value='${info.seeDate}'>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${info.progress eq 13505}">
                                                <input type="hidden" id="minDate" name="minDate" value='${info.roughDate}'>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                    <input type="text" class="form-control w160" class="calendar-icon" name="bizOperDate" id="bizOperDate" value='<fmt:formatDate value="${bizOperDate}" pattern="yyyy-MM-dd"/>' onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d',minDate:'#F{$dp.$D(\'minDate\')}'})"
                                           notnull="true" class="ipttext Wdate" />
                                    <span class="fc-warning"></span>
                                </div>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
                <c:if test="${info.progress eq 13505}">
                    <ul class="list-inline half form-inline">
                        <li>
                            <div class="form-group">
                                <input type="hidden" id = "oldAccountProjectNo" name="oldAccountProjectNo" value="${info.accountProjectNo}">
                                <label class="fw-normal w120 text-right" ><i>*</i>核算主体：</label>
                                <select class="form-control" title="" id="accountProjectNo" name="accountProjectNo" style="width:160px;">
                                    <option value="" selected="selected">请选择</option>
                                    <c:forEach items="${accountProjectList}" var="acc">
                                        <option value="${acc.accountProjectNo}" <c:if test="${info.accountProjectNo == acc.accountProjectNo}">selected</c:if>>${acc.accountProjectNo}_${acc.accountProject}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </li>
                    </ul>
                </c:if>
                <ul class="list-inline half form-inline">
		            <li>
		                <div  id="errorMsg">
		                    <span class="fc-warning"></span>
		                </div>
		            </li>
		        </ul>
            </div>
        </div>
        </form>
    </div>
    <div class="page-content">
        <h4>
            <strong>附件</strong>
        </h4>
    </div>
    <table class="table-sammary" name="Viewerbox">
        <col style="width:145px;">
        <col style="width:auto">
        <c:if test="${info.progress eq 13505}">
       	<input type="hidden" name="newProgressHidden" id="newProgressHidden" value="${13505}"/>
        <tr>
            <td colspan="2">
                <div class="" role="main">
                    <div>
                        <div class="pd10" >
                            <h4 class="thumb-title">
                                <i>*</i>成销确认书/佣金结算资料
                            </h4>
                            <div class="thumb-xs-box" id="fileIdPhotoToDeal">
                                <c:if test="${not empty info.fileList}">
                                    <c:set  var="fileSize" value="0"/>
                                    <c:forEach items="${info.fileList}" var="list" varStatus="status">
                                        <c:if test="${list.fileTypeId eq 1025}">
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
                                                <input type="hidden" name="fileTypeId" value="1025" />
                                                <input type="hidden" name="fileSourceId" value="5" />
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <div class="thumb-xs-list item-photo-add"
                                     <c:if test="${fileSize>=10  }">style="display: none;"</c:if>>
                                    <input type="hidden" name="limitSize" value="10">
                                    <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                        <input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
                                        <input type="hidden" name="fileTypeId" value="1025" />
                                        <input type="hidden" name="fileSourceId" value="5" />
                                        <input type="hidden" name ="companyId" value="">
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        </c:if>
        <c:if test="${info.progress eq 13504}">
        	<input type="hidden" name="newProgressHidden" id="newProgressHidden" value="13504"/>
            <tr>
                <td colspan="2">
                    <div class="" role="main">
                        <div>
                            <div class="pd10" >
                                <h4 class="thumb-title">
                                    <i>*</i>带看单
                                </h4>
                                <div class="thumb-xs-box" id="fileIdPhotoToSee">
                                    <c:if test="${not empty info.fileList}">
                                        <c:set  var="fileSize" value="0"/>
                                        <c:forEach items="${info.fileList}" var="list" varStatus="status">
                                            <c:if test="${list.fileTypeId eq 1022}">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
																<span class="thumb-img">
																	<span class="thumb-img-container">
																		<span class="thumb-img-content">
																			<img alt="带看单" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
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
                                                    <input type="hidden" name="fileTypeId" value="1022" />
                                                    <input type="hidden" name="fileSourceId" value="5" />
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <div class="thumb-xs-list item-photo-add"
                                         <c:if test="${fileSize>=10  }">style="display: none;"</c:if>>
                                        <input type="hidden" name="limitSize" value="10">
                                        <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                            <input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
                                            <input type="hidden" name="fileTypeId" value="1022" />
                                            <input type="hidden" name="fileSourceId" value="5" />
                                            <input type="hidden" name ="companyId" value="">
                                        </a>
                                    </div>
                                </div>
                                <i class="fontset">注：带看单必须包含中介名称、客户名称、客户电话、驻场签字和客户签字信息。</i>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="" role="main">
                        <div>
                            <div class="pd10" >
                                <h4 class="thumb-title">
                                    <i>*</i>大定单
                                </h4>
                                <div class="thumb-xs-box" id="fileIdPhotoBox">
                                    <c:if test="${not empty info.fileList}">
                                        <c:set  var="fileSize" value="0"/>
                                        <c:forEach items="${info.fileList}" var="list" varStatus="status">
                                            <c:if test="${list.fileTypeId eq 1023}">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
																<span class="thumb-img">
																	<span class="thumb-img-container">
																		<span class="thumb-img-content">
																			<img alt="大定单" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
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
                                                    <input type="hidden" name="fileTypeId" value="1023" />
                                                    <input type="hidden" name="fileSourceId" value="5" />
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <div class="thumb-xs-list item-photo-add"
                                         <c:if test="${fileSize>=10  }">style="display: none;"</c:if>>
                                        <input type="hidden" name="limitSize" value="10">
                                        <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                            <input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
                                            <input type="hidden" name="fileTypeId" value="1023" />
                                            <input type="hidden" name="fileSourceId" value="5" />
                                            <input type="hidden" name ="companyId" value="">
                                        </a>
                                    </div>
                                </div>
                                <i class="fontset">注：大定单必须包含大定面积、金额、楼室号、客户名、客户电话、客户签名、日期和甲方盖章信息。</i>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="" role="main">
                        <div>
                            <div class="pd10" >
                                <h4 class="thumb-title">
                                    <i>*</i>甲方项目负责人名片
                                </h4>
                                <div class="thumb-xs-box" id="fileIdPhotoCard">
                                    <c:if test="${not empty info.fileList}">
                                        <c:set  var="fileSize" value="0"/>
                                        <c:forEach items="${info.fileList}" var="list" varStatus="status">
                                            <c:if test="${list.fileTypeId eq 1024}">
                                                <c:set var="fileSize" value="${fileSize + 1}"/>
                                                <div class="thumb-xs-list item-photo-list">
                                                    <a href="javascript:void(0);" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
																<span class="thumb-img">
																	<span class="thumb-img-container">
																		<span class="thumb-img-content">
																			<img alt="甲方项目签字人名片" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto" />
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
                                                    <input type="hidden" name="fileTypeId" value="1024" />
                                                    <input type="hidden" name="fileSourceId" value="5" />
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                    <div class="thumb-xs-list item-photo-add"
                                         <c:if test="${fileSize>=10  }">style="display: none;"</c:if>>
                                        <input type="hidden" name="limitSize" value="10">
                                        <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                            <input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
                                            <input type="hidden" name="fileTypeId" value="1024" />
                                            <input type="hidden" name="fileSourceId" value="5" />
                                            <input type="hidden" name ="companyId" value="">
                                        </a>
                                    </div>
                                </div>
                                <i class="fontset">注：带看单上签字的甲方相关负责人名片。</i>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </c:if>
    </table>
<script type="text/javascript">
    $(function(){
        var options = {
            "url":BASE_PATH + '/file/uploadCommonFile',
            "isDeleteImage":false,//删除时校验checkEditImage
            "isAddImage":false,   //添加时校验checkEditImage
            "isCommonFile":true  //公共上传文件
        };
        photoUploader(options,null,null,null);

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
        $("#oldFileRecordMainIds").val(fileRecordMainIds);
    });
</script>
