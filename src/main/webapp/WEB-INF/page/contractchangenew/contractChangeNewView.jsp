<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
	<style type="text/css">
		.text-overflow {
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
			width: 100%;
		}
	</style>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container">
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/contract/contractLeftMenu.jsp"
				flush="true">
				<jsp:param value="110404" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content">
				<div class="page-content ul-line-block" name="Viewerbox">
					<br>
					<h4>
						<strong>合同变更详情</strong>
						<a href="${ctx}/contractChangeNew/list/${contractId}/${contractStatus}" class="btn btn-primary">返回</a>
						<shiro:hasPermission name="/ct:OPERATE_AUDIT">
							<c:if test="${contractChange.approveState eq 1}">
								<a href="javascript:void(0)" onclick="operateAuditCt('${contractChange.id}','${contractId}','${contractStatus}');" id="operateAuditCt" class="btn btn-primary" style="margin-right:10px;">运营维护审核状态</a>
							</c:if>
						</shiro:hasPermission>
					</h4>

					<ul class="list-inline half form-inline">
						<li>
							<div class="form-group">
								<label class="fw-normal w140  text-right" style="vertical-align: top;float:left">合同变更申请编号：</label>
								${contractChange.contractStopNo}
							</div>
						</li>
					</ul>
					<ul class="list-inline half form-inline">
						<li>
							<div class="form-group">
								<label class="fw-normal w140  text-right" style="vertical-align: top;float:left">变更合同编号：</label>
								${contractChange.contractNo}
							</div>
						</li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
			             	<div class="form-group" style="width: 50%;">
			             		<label class="fw-normal w140  text-right" style="vertical-align: top;">变更类型：</label>
			             		${contractChange.changeTypeStr}
			             	</div>
							<div class="form-group" style="padding-left: 15px;">
								<label class="fw-normal w140  text-right" style="vertical-align: top;">三方变更类型：</label>
								${contractChange.threePartChangeTypeNm}
							</div>
						</li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
			                <div class="form-group" style="width:50%;">
			                    <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">转让方：</label>
			                    ${contractChange.oldUpdateCompanyName}
			                </div>
			                <div class="form-group" style="padding-left: 15px;">
                                <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">受让方：</label>
                                   ${contractChange.newCompanyName}
                            </div>
			            </li>
					  </ul>
					<ul class="list-inline form-inline">
                               <li>
                                   <div class="form-group" style="width:50%;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">受让方法定代表人：</label>
                                        ${contractChange.newLegalPerson}
                                   </div>
                                   <div class="form-group" style="padding-left:15px;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">受让方注册地址：</label>
                                        ${contractChange.newCompanyAddressDetail}
                                   </div>
                               </li>
                          </ul>
                    <ul class="list-inline form-inline">
                               <li>
                                   <div class="form-group" style="width:50%;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">合作模式：</label>
                                        ${contractChange.contractTypeNm}
                                   </div>
                               </li>
                          </ul>
                    <ul class="list-inline form-inline">
                               <li>
                                   <div class="form-group" style="width:50%;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">合作协议书编号：</label>
                                        ${contractChange.newAgreementNo}
                                   </div>
                                   <div class="form-group" style="padding-left:15px;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">合作周期：</label>
									   <fmt:parseDate value="${contractChange.newDateLifeStart}" var="dateLifeStart" pattern="yyyy-MM-dd HH:mm:ss"/>
									   <fmt:parseDate value="${contractChange.newDateLifeEnd}" var="dateLifeEnd" pattern="yyyy-MM-dd HH:mm:ss"/>
									   <fmt:formatDate value="${dateLifeStart}" pattern="yyyy-MM-dd"/> 至 <fmt:formatDate value="${dateLifeEnd}" pattern="yyyy-MM-dd"/>
                                   </div>
                               </li>
                          </ul>
                    <ul class="list-inline form-inline">
                               <li>
                                   <div class="form-group" style="width:50%;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">保证金金额：</label>
                                        ￥<fmt:formatNumber value="${contractChange.totleDepositFee}" pattern="#,##0.00#"/>
                                   </div>
                                   <div class="form-group" style="padding-left:15px;">
                                       <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">违约金额：</label>
                                        ￥<fmt:formatNumber value="${contractChange.penaltyFee}" pattern="#,##0.00#"/>
                                   </div>
                               </li>
                          </ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="form-group" style="width:50%;">
								<label class="fw-normal w140 text-right" style="vertical-align: top;float:left">受让方授权代表：</label>
								${contractChange.authRepresentative}
							</div>
							<div class="form-group" style="padding-left:15px;">
								<label class="fw-normal w140 text-right" style="vertical-align: top;float:left">联系方式：</label>
								${contractChange.agentContact}
							</div>
						</li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="form-group" style="width:50%;">
								<label class="fw-normal w140 text-right" style="vertical-align: top;float:left">受让方公司账号：</label>
								${contractChange.companyBankNo}
							</div>
						</li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="form-group" style="width:50%;">
								<label class="fw-normal w140 text-right" style="vertical-align: top;float:left">开户银行：</label>
								${contractChange.bankAccount}
							</div>
							<div class="form-group" style="padding-left:15px;">
								<label class="fw-normal w140 text-right" style="vertical-align: top;float:left">开户名：</label>
								${contractChange.accountName}
							</div>
						</li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="form-group" style="width:50%;">
								<label class="fw-normal w140 text-right" style="vertical-align: top;float:left">受让方收件人：</label>
								${contractChange.recipients}
							</div>
							<div class="form-group" style="padding-left:15px;">
								<label class="fw-normal w140 text-right" style="vertical-align: top;float:left">受让方收件地址：</label>
								${contractChange.recipientsAddressDetail}
							</div>
						</li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
			                <div class="form-group" style="overflow:hidden;">
			                    <label class="fw-normal w140 text-right" style="vertical-align: top;float:left">备注：</label>
			                    <span style="display:block;margin-left:140px;">${contractChange.remarks}</span>
			                </div>
			            </li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="form-group" style="width:50%;">
								<label class="fw-normal w140 text-right" style="vertical-align: top;float:left">创建人：</label>
								${contractChange.userCreateName}
							</div>
							<div class="form-group" style="padding-left:15px;">
								<label class="fw-normal w140 text-right" style="vertical-align: top;float:left">创建时间：</label>
								${contractChange.dateCreate}
							</div>
						</li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="form-group" style="overflow:hidden;width: 50%;">
								<label class="fw-normal w140 text-right" style="vertical-align: top;float:left">提交OA状态：</label>
								<c:if test="${contractChange.submitOAStatus eq 21202 || contractChange.submitOAStatus eq 21204}">
									<span style="display:block;margin-left:140px;color: red;">${contractChange.submitOAStatusName}</span>
								</c:if>
								<c:if test="${contractChange.submitOAStatus eq 21201 || contractChange.submitOAStatus eq 21203}">
									<span style="display:block;margin-left:140px;">${contractChange.submitOAStatusName}</span>
								</c:if>
							</div>
							<div class="form-group" style="padding-left:15px;">
								<label class="fw-normal w140 text-right" style="vertical-align: top;float:left">审核状态：</label>
								${contractChange.approveStateName}
							</div>
						</li>
					</ul>
					<p><strong>门店信息</strong></p>
					<table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
						<tr>
			           		<th style="width: 100px;">门店编号</th>
			              	<th style="width: 150px;">门店名称</th>
			              	<th style="width: 180px;">门店地址</th>
							<th style="width: 120px;">门店负责人</th>
							<th style="width: 120px;">门店负责人电话</th>
			              	<th style="width: 120px;">门店资质等级</th>
						</tr>
				           <c:forEach items="${contractChange.storeList}" var="item"  varStatus="listIndex">
			                 	<tr>
			                 		<td >${item.storeNo}</td>
			                 		<td style="text-align:left;" title="${item.name}" class="text-overflow">${item.name}</td>
			                 		<td style="text-align:left;" title="${item.addressDetail}" class="text-overflow">${item.addressDetail}</td>
									<td >${item.storeManager}</td>
									<td >${item.storeManagerPhone}</td>
			                 		<td >
										<c:if test="${item.abtypeStore eq 19901}">${item.abtypeStoreName}</c:if>
										<c:if test="${item.abtypeStore eq 19902}">${item.abtypeStoreName}(${item.btypeStoreName})</c:if>
			                 		</td>
			                 	</tr>
							</c:forEach> 
					</table>
					<p><strong>附件</strong></p>
					<ul class="list-inline form-inline">
						<li>
							<div class="" role="main">
								<div>
									<div class="pd10">
										<h4 class="thumb-title">
											受让方营业执照
										</h4>
										<div class="thumb-xs-box" id="thumbXsBox">
											<c:if test="${not empty contractChange.photosFileList }">
												<c:set var="fileSize" value="0"/>
												<c:forEach items="${contractChange.photosFileList}"
														   var="list" varStatus="status">
													<c:if test="${list.fileTypeId eq 1033}">
														<c:set var="fileSize" value="${fileSize + 1}"/>
														<div class="thumb-xs-list item-photo-list">
															<a href="javascript:void(0);" class="thumbnail swipebox"
															   target="_blank">
															<span class="thumb-img">
																<span class="thumb-img-container">
																	<span class="thumb-img-content">
																		<img alt="受让方营业执照" data-original="${list.url50}"
																			 src="${list.fileAbbrUrl}" class="empPhoto"/>
																	</span>
																</span>
															</span>
															</a>
														</div>
													</c:if>
												</c:forEach>

											</c:if>
										</div>
									</div>
								</div>
							</div>
						</li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="" role="main">
								<div>
									<div class="pd10">
										<h4 class="thumb-title">
											国家企业信用信息公示系统网站截图
										</h4>
										<div class="thumb-xs-box" id="thumbXsBox">
											<c:if test="${not empty contractChange.photosFileList }">
												<c:set var="fileSize" value="0"/>
												<c:forEach items="${contractChange.photosFileList}"
														   var="list" varStatus="status">
													<c:if test="${list.fileTypeId eq 1034}">
														<c:set var="fileSize" value="${fileSize + 1}"/>
														<div class="thumb-xs-list item-photo-list">
															<a href="javascript:void(0);" class="thumbnail swipebox"
															   target="_blank">
															<span class="thumb-img">
																<span class="thumb-img-container">
																	<span class="thumb-img-content">
																		<img alt="国家企业信用信息公示系统网站截图"
																			 data-original="${list.url50}"
																			 src="${list.fileAbbrUrl}" class="empPhoto"/>
																	</span>
																</span>
															</span>
															</a>
														</div>
													</c:if>
												</c:forEach>
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="" role="main">
								<div>
									<div class="pd10">
										<h4 class="thumb-title">
											受让方法人身份证
										</h4>
										<div class="thumb-xs-box" id="thumbXsBox">
											<c:if test="${not empty contractChange.photosFileList }">
												<c:set var="fileSize" value="0"/>
												<c:forEach items="${contractChange.photosFileList}"
														   var="list" varStatus="status">
													<c:if test="${list.fileTypeId eq 1035}">
														<c:set var="fileSize" value="${fileSize + 1}"/>
														<div class="thumb-xs-list item-photo-list">
															<a href="javascript:void(0);" class="thumbnail swipebox"
															   target="_blank">
															<span class="thumb-img">
																<span class="thumb-img-container">
																	<span class="thumb-img-content">
																		<img alt="受让方法人身份证"
																			 data-original="${list.url50}"
																			 src="${list.fileAbbrUrl}" class="empPhoto"/>
																	</span>
																</span>
															</span>
															</a>
														</div>
													</c:if>
												</c:forEach>
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="" role="main">
								<div>
									<div class="pd10">
										<h4 class="thumb-title">
											三方变更协议
										</h4>
										<div class="thumb-xs-box" id="thumbXsBox">
											<c:if test="${not empty contractChange.photosFileList }">
												<c:set var="fileSize" value="0"/>
												<c:forEach items="${contractChange.photosFileList}"
														   var="list" varStatus="status">
													<c:if test="${list.fileTypeId eq 1036}">
														<c:set var="fileSize" value="${fileSize + 1}"/>
														<div class="thumb-xs-list item-photo-list">
															<a href="javascript:void(0);" class="thumbnail swipebox"
															   target="_blank">
															<span class="thumb-img">
																<span class="thumb-img-container">
																	<span class="thumb-img-content">
																		<img alt="三方变更协议"
																			 data-original="${list.url50}"
																			 src="${list.fileAbbrUrl}" class="empPhoto"/>
																	</span>
																</span>
															</span>
															</a>
														</div>
													</c:if>
												</c:forEach>
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="" role="main">
								<div>
									<div class="pd10">
										<h4 class="thumb-title">
											转让方保证金收据
										</h4>
										<div class="thumb-xs-box" id="thumbXsBox">
											<c:if test="${not empty contractChange.photosFileList }">
												<c:set var="fileSize" value="0"/>
												<c:forEach items="${contractChange.photosFileList}"
														   var="list" varStatus="status">
													<c:if test="${list.fileTypeId eq 1037}">
														<c:set var="fileSize" value="${fileSize + 1}"/>
														<div class="thumb-xs-list item-photo-list">
															<a href="javascript:void(0);" class="thumbnail swipebox"
															   target="_blank">
															<span class="thumb-img">
																<span class="thumb-img-container">
																	<span class="thumb-img-content">
																		<img alt="转让方保证金收据"
																			 data-original="${list.url50}"
																			 src="${list.fileAbbrUrl}" class="empPhoto"/>
																	</span>
																</span>
															</span>
															</a>
														</div>
													</c:if>
												</c:forEach>
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</li>
					</ul>
					<ul class="list-inline form-inline">
						<li>
							<div class="" role="main">
								<div>
									<div class="pd10">
										<h4 class="thumb-title">
											其他
										</h4>
										<div class="thumb-xs-box" id="thumbXsBox">
											<c:if test="${not empty contractChange.photosFileList }">
												<c:set var="fileSize" value="0"/>
												<c:forEach items="${contractChange.photosFileList}"
														   var="list" varStatus="status">
													<c:if test="${list.fileTypeId eq 1038}">
														<c:set var="fileSize" value="${fileSize + 1}"/>
														<div class="thumb-xs-list item-photo-list">
															<a href="javascript:void(0);" class="thumbnail swipebox"
															   target="_blank">
															<span class="thumb-img">
																<span class="thumb-img-container">
																	<span class="thumb-img-content">
																		<img alt="其他"
																			 data-original="${list.url50}"
																			 src="${list.fileAbbrUrl}" class="empPhoto"/>
																	</span>
																</span>
															</span>
															</a>
														</div>
													</c:if>
												</c:forEach>
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</li>
					</ul>
            	</div>
            </div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function operateAuditCt(id, contractId, contractStatus) {
		if(!isBlank(id)) {
			systemLoading("", true);
			$.ajax({
				url:BASE_PATH+"/stopcontract/operateAuditCt",
				data:$.param({
					id:id
				}),
				type:"post",
				success:function(data){
					data = JSON.parse(data);
					if(data && data.returnCode == '200'){
						Dialog.alertSuccess("状态变更成功!");
						$("#operateAuditCt").hide();
						systemLoaded();
						location.href = BASE_PATH + '/contractChangeNew/list/'+contractId+"/"+contractStatus;
					}
				},
				error:function(){
					Dialog.alertError("状态变更失败");
					$("#operateChangeCt").hide();
					systemLoaded();
				}
			});
		}

	};
</script>


