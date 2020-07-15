<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css?_v=${vs}">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<style>
    .sys_masklock{
        position: fixed;
    }
</style>
<div class="container" role="main">
	<div class="row">
		<div class="page-content">
			<span class="" style="float:right"><a href="javascript:SceneRecognition.close();" class="btn btn-default">&times;</a></span>
			<h4 class="border-bottom pdb10"><strong>${houseInfo.progressNm}</strong></h4>
			<ul class="list-inline half form-inline">
				<li>
					<div class="form-group">
						<strong>
							<label class="fw-normal w100 text-right"><strong>项目编号：</strong></label>${houseInfo.projectNo}
							<label class="fw-normal w100 text-right"><strong>楼盘名称：</strong></label>${houseInfo.estateNm}
						</strong>
					</div>
				</li>
			</ul>
			<ul class="list-inline half form-inline">
				<li style="width: 50%;">
					<div class="form-group">
						<label class="fw-normal w120 text-right">报备编号：</label>${reportNo}
					</div>
				</li>
				<li>
					<div class="form-group">
						<label class="fw-normal w120 text-right">报备来源：</label>
						${houseInfo.customerFromNm}
					</div>
				</li>
			</ul>
			<ul class="list-inline half form-inline">
				<li style="width: 50%;">
					<div class="form-group">
						<label class="fw-normal w120 text-right">经纪公司：</label>${houseInfo.companyNm}
					</div>
				</li>
				<li>
					<div class="form-group">
						<label class="fw-normal w120 text-right">门店：</label>${houseInfo.storeNm}
					</div>
				</li>
			</ul>
			
			<ul class="list-inline half form-inline">
				<li style="width: 50%;">
					<div class="form-group">
						<label class="fw-normal w120 text-right">报备经纪人：</label>${houseInfo.reportAgent}
					</div>
				</li>
				<li>
					<div class="form-group">
						<label class="fw-normal w120 text-right">经纪人手机：</label>${houseInfo.reportAgentTel}

					</div>
				</li>
			</ul>
			
			<form id="sceneHouseInfoForm">
				<input type="hidden" id="fileRecordMainIds" name="fileRecordMainIds">
				<input type="hidden" id="id" value="${houseInfo.id}">
				<input type="hidden" name="customerId" value="${houseInfo.customerId}">
				<ul class="list-inline half form-inline">
					<li style="width: 50%;">
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="customerNm"><i>*</i>客户姓名：</label>
							<input type="text" class="form-control w160" name="customerNm"	id="customerNm" placeholder="请输入客户姓名" notnull="true" maxlength="60"
								   value="${houseInfo.customerNm}"> <span class="fc-warning"></span>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="customerTel"><i>*</i>手机号码：</label>
							<input type="text" class="form-control w160" name="customerTel"	id="customerTel" placeholder="请输入客户电话" notnull="true" maxlength="11"
								   value="${houseInfo.customerTel}"> <span class="fc-warning"></span>
						</div>
					</li>
				</ul>
				<ul class="list-inline half form-inline">
					<li style="width: 50%;">
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="customerNmTwo"><i></i>客户姓名：</label>
							<input type="text" class="form-control w160" name="customerNmTwo"	id="customerNmTwo" placeholder="请输入客户姓名" maxlength="60"
								   value="${houseInfo.customerNmTwo}"> <span class="fc-warning"></span>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="customerTelTwo"><i></i>手机号码：</label>
							<input type="text" class="form-control w160" name="customerTelTwo"	id="customerTelTwo" placeholder="请输入客户电话" maxlength="11"
								   value="${houseInfo.customerTelTwo}"> <span class="fc-warning"></span>
						</div>
					</li>
				</ul>
				<ul class="list-inline half form-inline">
					<li style="width: 50%;">
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="buildingNo"><i>*</i>楼室号：</label>
							${houseInfo.buildingNo}
							<input type="hidden" class="form-control w160" name="buildingNo"
								   id="buildingNo" notnull="true" maxlength="25" value="${houseInfo.buildingNo}"> <span class="fc-warning"></span>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="roughArea"><i>*</i>大定面积：</label>
							${houseInfo.roughArea}
							<input type="hidden" class="form-control w160" name="roughArea" id="roughArea"
								   notnull="true" maxlength="7" dataType="twodecimal" readonly="readonly"
								   value="<fmt:formatNumber value="${houseInfo.roughArea}" pattern="#.00"/>">㎡ <span class="fc-warning"></span>
						</div>
					</li>
				</ul>
				<ul class="list-inline half form-inline">
					<li style="width: 50%;">
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="roughAmount"><i>*</i>大定总价：</label>
							<fmt:formatNumber value="${houseInfo.roughAmount}" pattern="#,##0.00#"/>
							<input type="hidden" class="form-control w160" name="roughAmount"
								   id="roughAmount" notnull="true" maxlength="12" dataType="needMoney" readonly="readonly" value="<fmt:formatNumber value="${houseInfo.roughAmount}" pattern="#.00"/>"
							>元 <span
								class="fc-warning"></span><span class="yzroughAmount"></span>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="fw-normal w120 text-right"><i></i>大定日期：</label>
								${sdk:ymd2(houseInfo.roughDate)}
						</div>
					</li>
				
				</ul>
				<ul class="list-inline half form-inline">
					<li style="width: 50%;">
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="area"><i>*</i>成销面积：</label>
							<input type="text" class="form-control w160" name="area" id="area"
								   notnull="true" maxlength="7" oninput="this.value=this.value.replace(/\s+/g,'')" dataType="twodecimal"
								   value="<fmt:formatNumber value="${houseInfo.roughArea}" pattern="#.00"/>">㎡ <span class="fc-warning"></span>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="dealAmount"><i>*</i>成销总价：</label>
							<input type="text" class="form-control w160" name="dealAmount"
								   id="dealAmount" notnull="true" oninput="this.value=this.value.replace(/\s+/g,'')" maxlength="12" dataType="needMoney" value="<fmt:formatNumber value="${houseInfo.roughAmount}" pattern="#.00"/>"
							>元 <span
								class="fc-warning"></span><span class="yzdealAmount"></span>
						</div>
					</li>
				</ul>
				<ul class="list-inline half form-inline">
					<li style="width: 50%;">
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="operateDate"><i>*</i>成销日期：</label>
							<input type="text" class="calendar-icon w160" name="operateDate" id="operateDate" value="${currDate}" onFocus="WdatePicker({isShowClear:true,onpicked:fnChangeCloseDate(this), readOnly:true, dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
								   readonly="readonly" notnull="true" class="ipttext Wdate" />
							<span class="fc-warning"></span>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="settleConfirmDate">结算确认日期：</label>
<!-- 							关账 -->
                            <input type="hidden" id="closeDate" value="${yearMonth}">
							<input type="text" class="calendar-icon w160" name="settleConfirmDate" id="settleConfirmDate" value="${currDate}" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'closeDate\')}',maxDate:'%y-%M-%d'})"
								   readonly="readonly" class="ipttext Wdate" />
							<span class="fc-warning"></span>
						</div>
					</li>
                    <%-- <c:if test="${houseInfo.customerFrom eq 17403}">
                        <li>
                            <div class="form-group">
                                <label class="fw-normal w120 text-right">APP报备编号：</label>${houseInfo.fyReportId}
                            </div>
                        </li>
                    </c:if> --%>
				</ul>
				<ul class="list-inline half form-inline">
					<li style="width: 50%;">
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="befYJSRAmount"><i>*</i>应计收入税前：</label>
							<input type="text" class="form-control w160" name="befYJSRAmount" oninput="this.value=this.value.replace(/\s+/g,'')" id="befYJSRAmount" notnull="true" maxlength="12" dataType="needMoney"
								   value="<fmt:formatNumber value="${houseInfo.befYJSRAmount}" pattern="#.00"/>">元 <span class="fc-warning"></span>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="aftYJSRAmount"><i>*</i>应计收入税后：</label>
							<input type="text" class="form-control w160" name="aftYJSRAmount" oninput="this.value=this.value.replace(/\s+/g,'')" id="aftYJSRAmount" notnull="true" maxlength="12" dataType="needMoney"
								   value="<fmt:formatNumber value="${houseInfo.aftYJSRAmount}" pattern="#.00"/>">元 <span class="fc-warning"></span>
						</div>
					</li>
				</ul>
				<input type="hidden" id="fySize" value="<c:if test="${not empty houseInfo.fyObjectList}">${houseInfo.fyObjectList.size()}</c:if>">
				<c:forEach items="${houseInfo.fyObjectList}" var="list" varStatus="index">
					<ul class="list-inline half form-inline">
						<li style="width: 50%;">
							<div class="form-group">
								<input type="hidden" id="companyNo${index.index}" value="${list.companyNo}">
								<label class="fw-normal w120 text-right">返佣对象<c:if test="${index.index == 0}">一</c:if><c:if test="${index.index == 1}">二</c:if><c:if test="${index.index == 2}">三</c:if>：</label>${list.companyNm}
							</div>
						</li>
					</ul>
					<ul class="list-inline half form-inline">
						<li style="width: 50%;">
							<div class="form-group">
								<label class="fw-normal w120 text-right" for="befYJFYAmount${index.index}"><i>*</i>应计返佣税前：</label>
								<input type="text" class="form-control w160" oninput="this.value=this.value.replace(/\s+/g,'')" name="befYJFYAmount${index.index}" id="befYJFYAmount${index.index}" notnull="true" maxlength="12" dataType="needMoney"
									   value="<fmt:formatNumber value="${list.befYJFYAmount}" pattern="#.00"/>">元 <span class="fc-warning"></span>
							</div>
						</li>
						<li>
							<div class="form-group">
								<label class="fw-normal w120 text-right" for="aftYJFYAmount${index.index}"><i>*</i>应计返佣税后：</label>
								<input type="text" class="form-control w160" oninput="this.value=this.value.replace(/\s+/g,'')" name="aftYJFYAmount${index.index}" id="aftYJFYAmount${index.index}" notnull="true" maxlength="12" dataType="needMoney"
									   value="<fmt:formatNumber value="${list.aftYJFYAmount}" pattern="#.00"/>">元 <span class="fc-warning"></span>
							</div>
						</li>
					</ul>
					<ul class="list-inline half form-inline">
						<li style="width: 50%;">
							<div class="form-group">
								<label class="fw-normal w120 text-right" for="befYJDYAmount${index.index}">应计垫佣税前：</label>
								<input type="text" class="form-control w160" oninput="this.value=this.value.replace(/\s+/g,'')" name="befYJDYAmount${index.index}" id="befYJDYAmount${index.index}" maxlength="12" dataType="needMoney"
									   value="<fmt:formatNumber value="${list.befYJDYAmount}" pattern="#.00"/>">元 <span class="fc-warning"></span>
							</div>
						</li>
						<li>
							<div class="form-group">
								<label class="fw-normal w120 text-right" for="aftYJDYAmount${index.index}">应计垫佣税后：</label>
								<input type="text" class="form-control w160" oninput="this.value=this.value.replace(/\s+/g,'')" name="aftYJDYAmount${index.index}" id="aftYJDYAmount${index.index}" maxlength="12" dataType="needMoney"
									   value="<fmt:formatNumber value="${list.aftYJDYAmount}" pattern="#.00"/>">元 <span class="fc-warning"></span>
							</div>
						</li>
					</ul>
				</c:forEach>
				<ul class="list-inline half form-inline">
					<li>
						<div class="form-group">
							<label class="fw-normal w120 text-right" ><i>*</i>核算主体：</label>
							<select class="form-control" title="" id="accountProjectNo" name="accountProjectNo" style="width:160px;">
								<option value="" selected="selected">请选择</option>
								<c:forEach items="${accountProjectList}" var="acc">
									<option value="${acc.accountProjectNo}" <c:if test="${houseInfo.accountProjectNo == acc.accountProjectNo}">selected</c:if>>${acc.accountProjectNo}_${acc.accountProject}</option>
								</c:forEach>
							</select>
						</div>
					</li>
				</ul>
                <c:if test="${not empty houseInfo.fyObjectList}">
                    <ul class="list-inline half form-inline">
                        <li style="width: 50%;">
                            <div class="form-group">
                                <i class="fontBold">*返佣对象需在佣金管理中提前进行维护。</i>
                            </div>
                        </li>
                    </ul>
                </c:if>
				<ul class="list-inline form-inline">
					<li>
						<div  id="errorMsg">
							<span class="fc-warning"></span>
						</div>
					</li>
				</ul>
			</form>
		</div>
		<c:if test="${progress eq 13505}">
			<div class="page-content" style="padding-left: 10px;">
				<h4 style="font-size:16px">
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
									<h4 class="thumb-title">
										<i>*</i>成销确认书/佣金结算资料
									</h4>
									<div class="thumb-xs-box" id="fileIdPhotoToDeal">
										<c:if test="${not empty houseInfo.fileList}">
											<c:set  var="fileSize" value="0"/>
											<c:forEach items="${houseInfo.fileList}" var="list" varStatus="status">
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
			</table>
		</c:if>
	</div>
</div>
<script>
    $(function(){
        var options = {
            "url":BASE_PATH + '/file/uploadCommonFile',
            "isDeleteImage":false,//删除时校验checkEditImage
            "isAddImage":false,   //添加时校验checkEditImage
            "isCommonFile":true  //公共上传文件
        };
        photoUploader(options,null,null,null);
    });

    var defaultCloseDate;
    function fnChangeCloseDate(obj){
        $("#settleConfirmDate").val("");
        if(!defaultCloseDate){
            defaultCloseDate = $("#closeDate").val();
        }

        if(new Date(obj.value) > new Date(defaultCloseDate)){
            $("#closeDate").val(obj.value);
        }else{
            $("#closeDate").val(defaultCloseDate);
        }
    }
</script>