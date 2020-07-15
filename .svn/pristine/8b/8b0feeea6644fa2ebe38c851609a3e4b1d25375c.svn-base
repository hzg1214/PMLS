<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<p>
	<strong>楼盘相册</strong>
</p>
 <div style="margin-left: 40px;">
	<p style="color: red;"><i>* </i>提示：鼠标悬浮在上传图片上后,可设置楼盘封面图片。</p>
</div>
<%--<input type="hidden" name="uploadPhotoId1" id="uploadPhotoId1" value="${uploadPhotoId1}">--%>
<%--<input type="hidden" name="uploadPhotoId2" id="uploadPhotoId2" value="${uploadPhotoId2}">--%>
<%--<input type="hidden" name="uploadPhotoId3" id="uploadPhotoId3" value="${uploadPhotoId3}">--%>
<%--<input type="hidden" name="uploadPhotoId4" id="uploadPhotoId4" value="${uploadPhotoId4}">--%>
<%--<input type="hidden" name="uploadPhotoId5" id="uploadPhotoId5" value="${uploadPhotoId5}">--%>
<input type="hidden" name="estateCoverImg" id="estateCoverImg" value="${coverImg}">
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right" style="vertical-align: top;">效果图：</label>
			<ul class="list_photo_uploader photo1" style="display:inline-block">
				<c:forEach items="${estatePhoto}" var="list" varStatus="i">
					<c:if test="${list.photoKbn eq 15901}">
						<li class="item_photo_uploader <c:if test='${list.coverFlg eq "Y"}'>item_cover</c:if>">
							<span class="btn_remove_photo" onclick="removePhoto(this);"></span>
							<a href="${list.fileUrl}" target="_blank">
								<img src="${list.fileAbbrUrl}" class="img-thumbnail" />
							</a>
							<span onclick="setCover(this);" data="<c:if test='${list.coverFlg eq "Y"}'>1</c:if><c:if test='${list.coverFlg eq "N"}'>0</c:if>" class="set-cover">设为封面</span>
							<i class="cover_bg"></i>
							<input type="hidden" name="photo1[${i.index}].fileSuffix" value="${list.fileSuffix}" class="fileSuffix">
							<input type="hidden" name="photo1[${i.index}].fileAbbrUrl" value="${list.fileAbbrUrl}" class="fileAbbrUrl">
							<input type="hidden" name="photo1[${i.index}].url50" value="${list.url50}" class="url50">
							<input type="hidden" name="photo1[${i.index}].fileUrl" value="${list.fileUrl}" class="fileUrl">
							<input type="hidden" name="photo1[${i.index}].sellFileNo" value="${list.sellFileNo}" class="sellFileNo">
							<input type="hidden" name="photo1[${i.index}].sfImage" value="${list.sfImage}" class="sfImage">
							<input type="hidden" name="photo1[${i.index}].fileFullName" value="${list.fileFullName}" class="fileFullName">
						</li>
					</c:if>
				</c:forEach>
				<c:choose>
				   <c:when test="${imgList.designImgsCount >= 10}">  
					    <li class="item_photo_uploader item_photo_uploader_add" style="display:none;">
							<s class="icon s_add_btn"></s>
							<input type="file" onchange="addPhoto(this);" class="file_control btn-add-photo" data-limit="10" data-name="photo1"  capture="camera">
						</li>
				   </c:when>
				   <c:otherwise> 
					   <li class="item_photo_uploader item_photo_uploader_add">
						   <s class="icon s_add_btn"></s>
						   <input type="file" onchange="addPhoto(this);" class="file_control btn-add-photo" data-limit="10" data-name="photo1"
								capture="camera">
					   </li>
				   </c:otherwise>
				</c:choose> 
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
				<c:forEach items="${estatePhoto}" var="list" varStatus="i">
					<c:if test="${list.photoKbn eq 15902}">
						<li class="item_photo_uploader <c:if test='${list.coverFlg eq "Y"}'>item_cover</c:if>">
							<span class="btn_remove_photo" onclick="removePhoto(this);"></span>
							<a href="${list.fileUrl}" target="_blank">
								<img src="${list.fileAbbrUrl}" class="img-thumbnail" id="${list.photoId}"/>
							</a>
							<span onclick="setCover(this);"
								data="<c:if test='${list.coverFlg eq "Y"}'>1</c:if><c:if test='${list.coverFlg eq "N"}'>0</c:if>"
								class="set-cover">设为封面</span>
							<i class="cover_bg"></i>
							<input type="hidden" name="photo2[${i.index}].fileSuffix" value="${list.fileSuffix}" class="fileSuffix">
							<input type="hidden" name="photo2[${i.index}].fileAbbrUrl" value="${list.fileAbbrUrl}" class="fileAbbrUrl">
							<input type="hidden" name="photo2[${i.index}].url50" value="${list.url50}" class="url50">
							<input type="hidden" name="photo2[${i.index}].fileUrl" value="${list.fileUrl}" class="fileUrl">
							<input type="hidden" name="photo2[${i.index}].sellFileNo" value="${list.sellFileNo}" class="sellFileNo">
							<input type="hidden" name="photo2[${i.index}].sfImage" value="${list.sfImage}" class="sfImage">
							<input type="hidden" name="photo2[${i.index}].fileFullName" value="${list.fileFullName}" class="fileFullName">
						</li>
					</c:if>
				</c:forEach>
				<c:choose>
				   <c:when test="${imgList.templateImgsCount >= 10}">  
					    <li class="item_photo_uploader item_photo_uploader_add" style="display:none;"><s class="icon s_add_btn"></s> <input
						type="file" onchange="addPhoto(this);" class="file_control btn-add-photo" data-limit="10" data-name="photo2"
						capture="camera"></li>  
				   </c:when>
				   <c:otherwise> 
					   <li class="item_photo_uploader item_photo_uploader_add"><s class="icon s_add_btn"></s> <input
						type="file" onchange="addPhoto(this);" class="file_control btn-add-photo" data-limit="10" data-name="photo2"
						capture="camera"></li>
				   </c:otherwise>
				</c:choose> 
				
			</ul>
			<div class="cor_9" style="display:inline-block">最多上传10张</div>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right" style="vertical-align: top;">地理位置：</label>
			<ul class="list_photo_uploader" style="display:inline-block">
				<c:forEach items="${estatePhoto}" var="list" varStatus="i">
					<c:if test="${list.photoKbn eq 15903}">
						<li class="item_photo_uploader <c:if test='${list.coverFlg eq "Y"}'>item_cover</c:if>">
							<span class="btn_remove_photo" onclick="removePhoto(this);"></span>
							<a href="${list.fileUrl}" target="_blank">
								<img src="${list.fileAbbrUrl}" class="img-thumbnail" id="${list.photoId}"/>
							</a>
							<span onclick="setCover(this);"
								  data="<c:if test='${list.coverFlg eq "Y"}'>1</c:if><c:if test='${list.coverFlg eq "N"}'>0</c:if>"
								  class="set-cover">设为封面</span>
							<i class="cover_bg"></i>
							<input type="hidden" name="photo3[${i.index}].fileSuffix" value="${list.fileSuffix}" class="fileSuffix">
							<input type="hidden" name="photo3[${i.index}].fileAbbrUrl" value="${list.fileAbbrUrl}" class="fileAbbrUrl">
							<input type="hidden" name="photo3[${i.index}].url50" value="${list.url50}" class="url50">
							<input type="hidden" name="photo3[${i.index}].fileUrl" value="${list.fileUrl}" class="fileUrl">
							<input type="hidden" name="photo3[${i.index}].sellFileNo" value="${list.sellFileNo}" class="sellFileNo">
							<input type="hidden" name="photo3[${i.index}].sfImage" value="${list.sfImage}" class="sfImage">
							<input type="hidden" name="photo3[${i.index}].fileFullName" value="${list.fileFullName}" class="fileFullName">
						</li>
					</c:if>
				</c:forEach>
				<c:choose>
				   <c:when test="${imgList.mapImgsCount >= 10}">  
					    <li class="item_photo_uploader item_photo_uploader_add" style="display:none;"><s class="icon s_add_btn"></s> <input
						type="file" onchange="addPhoto(this);" class="file_control btn-add-photo" data-limit="10" data-name="photo3"
						capture="camera"></li> 
				   </c:when>
				   <c:otherwise> 
					   <li class="item_photo_uploader item_photo_uploader_add"><s class="icon s_add_btn"></s> <input
						type="file" onchange="addPhoto(this);" class="file_control btn-add-photo" data-limit="10" data-name="photo3"
						capture="camera"></li>
				   </c:otherwise>
				</c:choose> 
			</ul>
			<div class="cor_9" style="display:inline-block">最多上传10张</div>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right" style="vertical-align: top;">区域规划：</label>
			<ul class="list_photo_uploader" style="display:inline-block">
				<c:forEach items="${estatePhoto}" var="list" varStatus="i">
					<c:if test="${list.photoKbn eq 15904}">
						<li class="item_photo_uploader <c:if test='${list.coverFlg eq "Y"}'>item_cover</c:if>">
							<span class="btn_remove_photo" onclick="removePhoto(this);"></span>
							<a href="${list.fileUrl}" target="_blank">
								<img src="${list.fileAbbrUrl}" class="img-thumbnail" id="${list.photoId}"/>
							</a>
							<span onclick="setCover(this);"
								  data="<c:if test='${list.coverFlg eq "Y"}'>1</c:if><c:if test='${list.coverFlg eq "N"}'>0</c:if>"
								  class="set-cover">设为封面</span>
							<i class="cover_bg"></i>
							<input type="hidden" name="photo4[${i.index}].fileSuffix" value="${list.fileSuffix}" class="fileSuffix">
							<input type="hidden" name="photo4[${i.index}].fileAbbrUrl" value="${list.fileAbbrUrl}" class="fileAbbrUrl">
							<input type="hidden" name="photo4[${i.index}].url50" value="${list.url50}" class="url50">
							<input type="hidden" name="photo4[${i.index}].fileUrl" value="${list.fileUrl}" class="fileUrl">
							<input type="hidden" name="photo4[${i.index}].sellFileNo" value="${list.sellFileNo}" class="sellFileNo">
							<input type="hidden" name="photo4[${i.index}].sfImage" value="${list.sfImage}" class="sfImage">
							<input type="hidden" name="photo4[${i.index}].fileFullName" value="${list.fileFullName}" class="fileFullName">
						</li>
					</c:if>
				</c:forEach>
				<c:choose>
				   <c:when test="${imgList.districtImgsCount >= 10}">  
					    <li class="item_photo_uploader item_photo_uploader_add" style="display:none;"><s class="icon s_add_btn"></s> <input
						type="file" onchange="addPhoto(this);" class="file_control btn-add-photo" data-limit="10" data-name="photo4"
						capture="camera"></li> 
				   </c:when>
				   <c:otherwise> 
					   <li class="item_photo_uploader item_photo_uploader_add"><s class="icon s_add_btn"></s> <input
						type="file" onchange="addPhoto(this);" class="file_control btn-add-photo" data-limit="10" data-name="photo4"
						capture="camera"></li>
				   </c:otherwise>
				</c:choose> 
			</ul>
			<div class="cor_9" style="display:inline-block">最多上传10张</div>
		</div>
	</li>
</ul>
<ul class="list-inline form-inline">
	<li>
		<div class="form-group">
			<label class="fw-normal w100 text-right" style="vertical-align: top;">实景图：</label>
			<ul class="list_photo_uploader" style="display:inline-block">
				<c:forEach items="${estatePhoto}" var="list" varStatus="i">
					<c:if test="${list.photoKbn eq 15905}">
						<li class="item_photo_uploader <c:if test='${list.coverFlg eq "Y"}'>item_cover</c:if>">
							<span class="btn_remove_photo" onclick="removePhoto(this);"></span>
							<a href="${list.fileUrl}" target="_blank">
								<img src="${list.fileAbbrUrl}" class="img-thumbnail" id="${list.photoId}"/>
							</a>
							<span onclick="setCover(this);"
								  data="<c:if test='${list.coverFlg eq "Y"}'>1</c:if><c:if test='${list.coverFlg eq "N"}'>0</c:if>"
								  class="set-cover">设为封面</span>
							<i class="cover_bg"></i>
							<input type="hidden" name="photo5[${i.index}].fileSuffix" value="${list.fileSuffix}" class="fileSuffix">
							<input type="hidden" name="photo5[${i.index}].fileAbbrUrl" value="${list.fileAbbrUrl}" class="fileAbbrUrl">
							<input type="hidden" name="photo5[${i.index}].url50" value="${list.url50}" class="url50">
							<input type="hidden" name="photo5[${i.index}].fileUrl" value="${list.fileUrl}" class="fileUrl">
							<input type="hidden" name="photo5[${i.index}].sellFileNo" value="${list.sellFileNo}" class="sellFileNo">
							<input type="hidden" name="photo5[${i.index}].sfImage" value="${list.sfImage}" class="sfImage">
							<input type="hidden" name="photo5[${i.index}].fileFullName" value="${list.fileFullName}" class="fileFullName">
						</li>
					</c:if>
				</c:forEach>
				<c:choose>
				   <c:when test="${imgList.realImgsCount >= 10}">  
					    <li class="item_photo_uploader item_photo_uploader_add" style="display:none;"><s class="icon s_add_btn"></s> <input
						type="file" onchange="addPhoto(this);" class="file_control btn-add-photo" data-limit="10" data-name="photo5"
						capture="camera"></li>
				   </c:when>
				   <c:otherwise> 
					   <li class="item_photo_uploader item_photo_uploader_add"><s class="icon s_add_btn"></s> <input
						type="file" onchange="addPhoto(this);" class="file_control btn-add-photo" data-limit="10" data-name="photo5"
						capture="camera"></li>
				   </c:otherwise>
				</c:choose>
				
			</ul>
			<div class="cor_9" style="display:inline-block">最多上传10张</div>
		</div>
	</li>
</ul>
