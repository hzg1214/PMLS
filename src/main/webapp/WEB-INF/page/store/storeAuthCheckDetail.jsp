<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
	<script type="text/javascript" src="${ctx}/meta/js/store/storeAuthCheckDetail.js?_v=${vs}"></script>
	<script src="http://api.map.baidu.com/api?v=2.0&ak=${sysConfig.baiduApiKey}" type="text/javascript"></script>
	<%-- <script type="text/javascript" src="${ctx}/meta/js/crm/storeMap.js?_v=${vs}"></script> --%>
</head>
<body>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" onclick="javascript:StoreAuthCheck.close();">
			<span>&times;</span>
		</button>
		<h4 class="modal-title">授牌验收申请</h4>
	</div>
	<div class="container">
		<div class="row article">
			<div class="col-md-12 content">
				<div class="page-content">
					<h4>
						<strong>基本信息</strong>
						<c:if test="${storeAuthCheckInfo.checkStatus==23301}">
							<a  href="javascript:void(0)" class="btn btn-primary" style="margin-right:16px"
							  onclick="auditReturn(${storeAuthCheckInfo.id},${storeAuthCheckInfo.storeId});">审核驳回</a>
							<a  href="javascript:void(0)" class="btn btn-primary" style="margin-right:16px"
							  onclick="auditPass(${storeAuthCheckInfo.id},${storeAuthCheckInfo.storeId});">审核通过</a>
						</c:if>
						</h4>
					<table class="table-sammary">
						<col style="width:150px;">
						<col style="width:auto;">
						<col style="width:150px;">
						<col style="width:auto;">
						<tr>
							<td class="talabel">门店编号：</td>
							<td>${storeAuthCheckInfo.storeNo}</td>
							<td class="talabel">门店名称：</td>
							<td>${storeAuthCheckInfo.storeName}</td>
						</tr>
						<tr>
							<td class="talabel">门店地址：</td>
							<td>${storeAuthCheckInfo.addressDetail}</td>
							<td class="talabel">门店维护人：</td>
							<td>${storeAuthCheckInfo.maintainerName}</td>
						</tr>

						<tr>
							<td class="talabel">门店所属中心：</td>
							<td>${storeAuthCheckInfo.centerName}</td>
						</tr>
						<tr>
							<td class="talabel">验收日期：</td>
							<td>${sdk:ymd2(storeAuthCheckInfo.checkDate)}</td>
						</tr>
						<tr>
							<td class="talabel">备注：</td>
							<td>${storeAuthCheckInfo.remark}</td>
						</tr>
						<tr>
							<td class="talabel">验收人：</td>
							<td>${storeAuthCheckInfo.userCreateNm}</td>
							<td class="talabel">申请时间：</td>
							<td>${storeAuthCheckInfo.dateCreate}</td>
						</tr>
						<tr>
							<td class="talabel">验收申请状态：</td>
							<td>${storeAuthCheckInfo.checkStatusNm}</td>
							<c:if test="${storeAuthCheckInfo.checkStatus==23302}">
								<td class="talabel">验收过审时间：</td>
								<td>${storeAuthCheckInfo.authTime}</td>
							</c:if>
							<c:if test="${storeAuthCheckInfo.checkStatus==23303}">
								<td class="talabel">审核时间：</td>
								<td>${storeAuthCheckInfo.authTime}</td>
							</c:if>
						</tr>
						<c:if test="${storeAuthCheckInfo.checkStatus==23303}">
							<tr>
							<td class="talabel">驳回原因：</td>
							<td style="color:red;">${storeAuthCheckInfo.auditDesc}</td>
							</tr>
						</c:if>
						<tr>
							<td colspan="4">
								<strong>附件：</strong>
								<div class="" role="main">
									<div>
										<div class="pd10" name="Viewerbox">
											<h4 class="thumb-title">挂牌照片</h4>
											<div class="thumb-xs-box" id="thumbXsBox">
												<c:if test="${not empty storeAuthCheckInfo.plateFileList}">
													<c:set var="fileSize" value="0" />
													<c:forEach items="${storeAuthCheckInfo.plateFileList}" var="list" varStatus="status">
														<c:set var="fileSize" value="${fileSize + 1}" />
														<div class="thumb-xs-list item-photo-list">
															<a href="javascript:void(0);"class="thumbnail swipebox" target="_blank">
																 <span class="thumb-img"> 
																 	<span class="thumb-img-container"> 
																 	<span class="thumb-img-content"> 
																			<img alt="挂牌照片" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
																	</span>
																</span>
															</span>
															</a> 
															<input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
														</div>
													</c:forEach>

												</c:if>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
    $(document.getElementsByName("Viewerbox")).each(function(index){
        var viewer = new Viewer(this, {
            url: 'data-original',
            show: function (){
                viewer.update();
            }
        });
    })
});

$(document).ready(function(){
    downLoadFile();
});
function downLoadFile(){
    $(document).on("click", ".viewer-flip-download", function(){
        var src = $(".viewer-canvas").find("img").attr("src");
        var fileName = $(".viewer-canvas").find("img").attr("alt");
        //参数格式化
        fileName = formatOptions(fileName);
        if (src) {
            var link = document.createElement('a');
            link.href = BASE_PATH + "/files/downloadFile"+"?fileName=" + fileName + "&fileUrl=" + src;
            link.style.cssText = 'display:none;position:absolute;left:-9999px;top:-9999px;';
            document.body.appendChild(link);
            link.click();
            setTimeout(function () {
                document.body.removeChild(link);
            }, 5000);
        }
        return false;
    });
}
</script>
</html>
