<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css?_v=${vs}">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<div class="container" role="main" style="width: 800px;">
	<div class="row">
		<div class="page-content">
			<span class="" style="float:right"><a href="javascript:Contract.close();" class="btn btn-default">&times;</a></span>
			<h4 class="border-bottom pdb10"><strong>附件上传</strong></h4>
		</div>
		<form id="contractAdditionalForm">
            <input type="hidden" id="fileRecordMainIds" name="fileRecordMainIds">
            <input type="hidden" id="contractId" name="contractId" value="${contractId}">
        </form>
		<table class="table-sammary">
			<col style="width:145px;">
			<col style="width:auto">
				<tr>
					<td colspan="2">
						<div class="" role="main">
							<div>
								<div class="pd10" >
									<h4 class="thumb-title" style="width: 95%;">
										补充协议
									</h4>
									<div class="thumb-xs-box">
										<c:if test="${not empty fileList}">
											<c:set  var="fileSize" value="0"/>
											<c:forEach items="${fileList}" var="list" varStatus="status">
													<c:set var="fileSize" value="${fileSize + 1}"/>
													<div class="thumb-xs-list item-photo-list">
														<a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank" title="${list.fileName}">
															<span class="thumb-img">
																<span class="thumb-img-container">
																	<span class="thumb-img-content">
																		<img alt="补充协议" src="${list.fileAbbrUrl}" class="empPhoto" />
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
														<input type="hidden" name="fileTypeId" value="1026" />
														<input type="hidden" name="fileSourceId" value="1" />
													</div>
											</c:forEach>
										</c:if>
										<div class="thumb-xs-list item-photo-add" <c:if test="${fileSize>=10}">style="display: none;"</c:if>>
											<input type="hidden" name="limitSize" value="10">
											<a href="javascript:void(0);" class="thumbnail" title="添加附件">
												<input type="file" class="btn-flie file-control" data-limit="10" data-name="photo-shinei" multiple="multiple">
												<input type="hidden" name="fileTypeId" value="1026" />
												<input type="hidden" name="fileSourceId" value="1" />
												<input type="hidden" name ="companyId" value="">
											</a>
										</div>
									</div>
									<i class="fontset">注：需双方均已盖章。</i>
								</div>
							</div>
						</div>
					</td>
				</tr>
		</table>
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
        
        $(document).off("click",".btn-remove-photo").on('click', '.btn-remove-photo', function(e){
        	$(this).parent().parent().parent().remove();
        	return false;
        });
	});
</script>