<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		$(".houseType").show();
		console.log(hideFlag);
		if(hideFlag){
			$(".houseType").hide();
		}
	});
</script>
<form id="estateTypeEditForm">
	<div class="container theme-hipage ng-scope" role="main">
		<div class="row">
			<div class="page-content">
			    <a href="javascript:EstateType.closePopup();" class="btn btn-default" style="float: right;">&times;</a>
				<input type="hidden" id="id" name="id" value="${estateType.id}">
				<input type="hidden" name="estateId" value="${estateType.estateId}">
				<input type="hidden" name="typeId" value="${estateType.typeId}">
				<%--<input type="hidden" name="uploadPhotoId6" id="uploadPhotoId6" value="${estateType.houseTypeImgIds}">--%>
				<%--<input type="hidden" name="uploadPhotoId7" id="uploadPhotoId7" value="${estateType.houseTemplateImgIds}">--%>
				<input type="hidden" name="houseCoverImg" id="houseCoverImg" value="${estateType.coverImgId}">
				<h4 class="border-bottom pdb10">
					<strong>编辑在售户型</strong>
				</h4>
				<span class="fc-warning" id="errormsg" style="padding-left:20px"></span>
				<ul class="list-inline form-inline">
					<li>
						<div class="form-group">
							<label class="fw-normal w100 text-right">主力户型：</label> <input type="radio" value="1" name="countFlg"
								<c:if test="${1 eq estateType.countFlg}">checked</c:if>> <label class="fon-normal small">是</label>
							<input type="radio" value="0" name="countFlg" <c:if test="${0 eq estateType.countFlg}">checked</c:if>>
							<label class="fon-normal small">否</label> <span class="fc-warning"></span>
						</div>
					</li>
				</ul>
				<ul class="list-inline form-inline">
					<li class="houseType">
						<div class="form-group">
							<label class="fw-normal w100 text-right">户型：</label> <input
								type="text" class="form-control w50" name="countF" id="countF" placeholder=""
								maxlength="2" dataType="posNumWithZero" value="${estateType.countF}">室 <input type="text"
								class="form-control w50" name="countT" id="countT" placeholder="" maxlength="2"
								dataType="posNumWithZero" value="${estateType.countT}">厅 <input type="text"
								class="form-control w50" name="countW" id="countW" placeholder="" maxlength="2"
								dataType="posNumWithZero" value="${estateType.countW}">卫 <span class="fc-warning"></span>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="fw-normal w100 text-right">面积：</label> <input type="text"
								class="form-control w80" name="buildSquare" id="buildSquare" placeholder=""
								maxlength="5" dataType="normal" value="${estateType.buildSquare}">㎡ <span class="fc-warning"></span>
						</div>
					</li>
				</ul>
				<ul class="list-inline form-inline">
					<li>
						<div class="form-group">
							<label class="fw-normal w100 text-right">朝向：</label>
							<c:forEach items="${directionKbnList}" var="list">
								<input type="radio" value="${list.dicCode}" id="${list.dicCode}" name="directionKbn"
									<c:if test="${list.dicCode eq estateType.directionKbn}">checked</c:if>>
								<label class="fon-normal small">${list.dicValue}</label>
							</c:forEach>
							<span class="fc-warning"></span>
						</div>
					</li>
				</ul>
				<ul class="list-inline form-inline" style="display:none">
					<li>
						<div class="form-group">
							<label class="fw-normal w100 text-right">标签：</label>
							<c:forEach items="${estateType.labelLst}" var="list" varStatus="status">
								<input type="text" class="form-control w125" name="label[${status.index}]" placeholder=""
									maxlength="30" value="${list}">
								<c:if test="${status.first eq true}">
									<span class="btn btn-link btn-add-input-tag" onclick="addLabel(this);">新增标签</span>
								</c:if>
								<c:if test="${status.first eq false}">
									<span class="btn btn-link btn-add-input-tag" onclick="delLabel(this);">删除</span>
								</c:if>
							</c:forEach>
							<c:if test="${fn:length(estate.labelLst) le 0}">
								<input type="text" class="form-control w125" name="label[0]" placeholder="" maxlength="30" value="">
								<span class="btn btn-link btn-add-input-tag" onclick="addLabel(this);">新增标签</span>
							</c:if>
						</div>
					</li>
				</ul>
				<ul class="list-inline form-inline">
					<li>
						<div class="form-group">
							<label class="fw-normal w100 text-right" style="vertical-align: top;">户型图：</label>
							<ul class="list_photo_uploader houseTypePhotos" style="display:inline-block">
								<c:forEach items="${estateType.houseImgs}" var="list" varStatus="i">
									<c:if test="${list.photoKbn eq 15906}">
										<li class="item_photo_uploader ">
											<span class="btn_remove_photo" onclick="removePhoto(this);"></span>
											<a href="${list.fileUrl}" target="_blank">
												<img src="${list.fileAbbrUrl}" class="img-thumbnail" />
											</a>
											<input type="hidden" name="houseTypePhotos[${i.index}].fileSuffix" value="${list.fileSuffix}" class="fileSuffix">
											<input type="hidden" name="houseTypePhotos[${i.index}].fileAbbrUrl" value="${list.fileAbbrUrl}" class="fileAbbrUrl">
											<input type="hidden" name="houseTypePhotos[${i.index}].url50" value="${list.url50}" class="url50">
											<input type="hidden" name="houseTypePhotos[${i.index}].fileUrl" value="${list.fileUrl}" class="fileUrl">
											<input type="hidden" name="houseTypePhotos[${i.index}].sellFileNo" value="${list.sellFileNo}" class="sellFileNo">
											<input type="hidden" name="houseTypePhotos[${i.index}].sfImage" value="${list.sfImage}" class="sfImage">
											<input type="hidden" name="houseTypePhotos[${i.index}].fileFullName" value="${list.fileFullName}" class="fileFullName">
										</li>
									</c:if>
								</c:forEach>
								<li class="item_photo_uploader item_photo_uploader_add"><s class="icon s_add_btn"></s> <input
									type="file" onchange="addPhoto(this);" class="file_control btn-add-photo" data-limit="10" data-name="houseTypePhotos"
									 capture="camera"></li>
							</ul>
							<div class="cor_9" style="display:inline-block">最多上传10张</div>
						</div>
					</li>
				</ul>
				<ul class="list-inline form-inline">
					<li>
						<div class="form-group">
							<label class="fw-normal w100 text-right" style="vertical-align: top;">样板间：</label>
							<ul class="list_photo_uploader" style="display:inline-block">
								<c:forEach items="${estateType.houseImgs}" var="list" varStatus="i">
									<c:if test="${list.photoKbn eq 15907}">
										<li class="item_photo_uploader ">
											<span class="btn_remove_photo" onclick="removePhoto(this);"></span>
											<a href="${list.fileUrl}" target="_blank">
												<img src="${list.fileAbbrUrl}" class="img-thumbnail" />
											</a>
											<input type="hidden" name="templetPhotos[${i.index}].fileSuffix" value="${list.fileSuffix}" class="fileSuffix">
											<input type="hidden" name="templetPhotos[${i.index}].fileAbbrUrl" value="${list.fileAbbrUrl}" class="fileAbbrUrl">
											<input type="hidden" name="templetPhotos[${i.index}].url50" value="${list.url50}" class="url50">
											<input type="hidden" name="templetPhotos[${i.index}].fileUrl" value="${list.fileUrl}" class="fileUrl">
											<input type="hidden" name="templetPhotos[${i.index}].sellFileNo" value="${list.sellFileNo}" class="sellFileNo">
											<input type="hidden" name="templetPhotos[${i.index}].sfImage" value="${list.sfImage}" class="sfImage">
											<input type="hidden" name="templetPhotos[${i.index}].fileFullName" value="${list.fileFullName}" class="fileFullName">
										</li>
									</c:if>
								</c:forEach>
								<li class="item_photo_uploader item_photo_uploader_add"><s class="icon s_add_btn"></s> <input
									type="file" onchange="addPhoto(this);" class="file_control btn-add-photo" data-limit="10" data-name="templetPhotos"
									 capture="camera"></li>
							</ul>
							<div class="cor_9" style="display:inline-block">最多上传10张</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</form>