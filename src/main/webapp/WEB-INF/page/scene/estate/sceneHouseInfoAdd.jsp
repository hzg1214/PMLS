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
			<h4 class="border-bottom pdb10"><strong>大定</strong></h4>
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
						<label class="fw-normal w120 text-right">报备编号：</label>${houseInfo.reportId}
					</div>
				</li>
				<li>
					<div class="form-group">
						<label class="fw-normal w120 text-right">报备来源：</label>${houseInfo.customerFromNm}
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
							<input type="text" class="form-control w160" name="customerTel"	id="customerTel" dataType="phone" placeholder="请输入客户电话" notnull="true" maxlength="11"
								   value="${houseInfo.customerTel}"> <span class="fc-warning"></span>
						</div>
					</li>
				</ul>
				<ul class="list-inline half form-inline">
					<li style="width: 50%;">
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="customerNmTwo"><i></i>客户姓名：</label>
							<input type="text" class="form-control w160" name="customerNmTwo"	id="customerNmTwo" placeholder="请输入客户姓名"  maxlength="60"
								   value="${houseInfo.customerNmTwo}"> <span class="fc-warning"></span>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="customerTelTwo"><i></i>手机号码：</label>
							<input type="text" class="form-control w160" name="customerTelTwo"	id="customerTelTwo" dataType="phone" placeholder="请输入客户电话"  maxlength="11"
								   value="${houseInfo.customerTelTwo}"> <span class="fc-warning"></span>
						</div>
					</li>
				</ul>
				<ul class="list-inline half form-inline">
					<li style="width: 50%;">
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="buildingNo"><i>*</i>楼室号：</label>
							<input type="text" class="form-control w160" name="buildingNo" oninput="this.value=this.value.replace(/\s+/g,'')"	id="buildingNo" placeholder="请输入楼室号" notnull="true" maxlength="25"
							> <span class="fc-warning"></span>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="roughArea"><i>*</i>大定面积：</label>
							<input type="text" class="form-control w160" name="roughArea" id="roughArea"
								   placeholder="请输入大定面积" notnull="true" maxlength="7" oninput="this.value=this.value.replace(/\s+/g,'')"
								   dataType="twodecimal">㎡ <span class="fc-warning"></span>
						</div>
					</li>
				</ul>
				<ul class="list-inline half form-inline">
					<li style="width: 50%;">
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="roughAmount"><i>*</i>大定总价：</label>
							<input type="text" class="form-control w160" name="roughAmount"
								   id="roughAmount" placeholder="请输入大定总价" notnull="true"
								   maxlength="12" dataType="needMoney" oninput="this.value=this.value.replace(/\s+/g,'')">元 <span
								class="fc-warning"></span><span class="yzroughAmount"></span>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="fw-normal w120 text-right" for="operateDate"><i>*</i>大定日期：</label>
							<input type="text" class="calendar-icon w160" name="operateDate" id="operateDate" value="${currDate}" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd',minDate:'${yearMonth}',maxDate:'%y-%M-%d'})"
								   readonly="readonly" notnull="true" class="ipttext Wdate" />
							<span class="fc-warning"></span>
						</div>
					</li>
				</ul>
				<ul class="list-inline form-inline">
					<li>
						<div  id="errorMsg">
							<span class="fc-warning"></span>
						</div>
					</li>
				</ul>
			</form>
		</div>
		<c:if test="${progress eq 13504}">
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
										<i>*</i>带看单
									</h4>
									<div class="thumb-xs-box" id="fileIdPhotoToSee">
										<c:if test="${not empty houseInfo.fileList}">
											<c:set  var="fileSize" value="0"/>
											<c:forEach items="${houseInfo.fileList}" var="list" varStatus="status">
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
										<c:if test="${not empty houseInfo.fileList}">
											<c:set  var="fileSize" value="0"/>
											<c:forEach items="${houseInfo.fileList}" var="list" varStatus="status">
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
										<c:if test="${not empty houseInfo.fileList}">
											<c:set  var="fileSize" value="0"/>
											<c:forEach items="${houseInfo.fileList}" var="list" varStatus="status">
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
</script>