<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%--<%@ include file="/WEB-INF/page/common/meta.jsp"%>--%>
<%--<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>--%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
	<script type="text/javascript" src="${ctx}/meta/js/crm/storeDetail.js?_v=${vs}"></script>
	<script src="http://api.map.baidu.com/api?v=2.0&ak=${sysConfig.baiduApiKey}" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/meta/js/crm/storeMap.js?_v=${vs}"></script>
</head>
<body>

	<%--<!-- 头部 -->--%>
	<%--<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">--%>
		<%--<jsp:param value="${headMenuIdSelect}" name="navSelectId" />--%>
	<%--</jsp:include>--%>
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
				onclick="javascript:StoreAudit.close();">
			<span>&times;</span>
		</button>
		<h4 class="modal-title">门店审批详情</h4>
	</div>
	<!-- 关联的门店id 集合 -->
	<div class="container">
		<div class="row article">
			<!-- 左侧菜单 -->
			<%--<jsp:include page="/WEB-INF/page/crm/crmLeftMenu.jsp"--%>
				<%--flush="true">--%>
				<%--<jsp:param value="110401" name="leftMenuSelectId" />--%>
			<%--</jsp:include>--%>
			<%--<div class="col-md-10 content">--%>
			<div class="col-md-12 content">
				<div class="page-content">
					<h4>
						<strong>门店信息</strong>
						<%--<a type="button" class="btn btn-primary" href="${ctx}/crm/index">返回</a>--%>
						<c:if test="${storeDetail.auditStatus==10}">
							<a type="button" class="btn btn-primary" href="javascript:void(0)" onclick="auditReturn(${storeDetail.storeId},'${storeDetail.storeName}','${storeDetail.storeNo}','${storeDetail.userCode}');" style="margin-right:10px;">驳回</a>
							<a type="button" class="btn btn-primary" href="javascript:void(0)" onclick="auditPass(${storeDetail.storeId},'${storeDetail.storeName}','${storeDetail.storeNo}','${storeDetail.userCode}');" style="margin-right:10px;">审核通过</a>
						</c:if>
						</h4>
					<table class="table-sammary">
						<col style="width:150px;">
						<col style="width:auto;">
						<col style="width:150px;">
						<col style="width:auto;">
						<input id="storeName" type="hidden" value="${storeDetail.storeName}"/>
						<input id="longitude" type="hidden" value="${storeDetail.longitude}"/>
						<input id="latitude" type="hidden" value="${storeDetail.latitude}"/>
						<input id="createLongitude" type="hidden" value="${storeDetail.createLongitude}"/>
						<input id="createLatitude" type="hidden" value="${storeDetail.createLatitude}"/>
						<input id="addressDetail" type="hidden" value="${storeDetail.addressDetail}"/>
						<tr>
							<td class="talabel">门店编号：</td>
							<td>${storeDetail.storeNo}</td>
							<td class="talabel">门店名称：</td>
							<td>${storeDetail.storeName}</td>
						</tr>
						<tr>
							<td class="talabel">门店地址：</td>
							<td>${storeDetail.addressDetail}</td>
							<td class="talabel">门店负责人：</td>
							<td>${storeDetail.storeManager}</td>
						</tr>

						<tr>
							<td class="talabel">联系电话：</td>
							<td>${storeDetail.storeManagerPhone}</td>
							<td class="talabel">连锁情况：</td>
							<td>${storeDetail.linkageSituation}</td>
						</tr>
						<tr>
							<td class="talabel">连锁品牌：</td>
							<td>${storeDetail.chainBrandName}</td>
							<td class="talabel">经纪人数：</td>
							<td>${storeDetail.storePersonNumName}</td>
						</tr>
						<tr>
							<td class="talabel">当前使用系统：</td>
							<td>${storeDetail.nowUserSystem}</td>
							<td class="talabel">加盟到期时间：</td>
							<td>${storeDetail.storeDueTime}</td>
						</tr>
						<tr>
							<td class="talabel">门店租赁到期时间：</td>
							<td>${storeDetail.storeLeaseDueTime}</td>
							<td class="talabel">主营业务：</td>
							<td>${storeDetail.mainBusinessName}</td>
						</tr>
						<tr>
							<td class="talabel">交易方式：</td>
							<td>${storeDetail.transactionModeName}</td>
							<td class="talabel">经营场所类型：</td>
							<td>${storeDetail.businessPlaceTypeName}</td>
							
						</tr>
						<tr>
							<td class="talabel">门店规模：</td>
							<td>${storeDetail.storeSizeScaleName}</td>
							<td class="talabel">门店类型：</td>
							<td>${storeDetail.storeTypeName}</td>
						</tr>
						<tr>
							<td class="talabel">创建人：</td>
							<td>${storeDetail.userNameCreate}</td>
							<td class="talabel">创建时间：</td>
							<td>${storeDetail.dateCreate}</td>
						</tr>
						<tr>
							<td colspan="4">
								<div class="" role="main">
									<div>
										<div class="pd10" name="Viewerbox">
											<h4 class="thumb-title">门店照片</h4>
											<div class="thumb-xs-box" id="thumbXsBox">
												<c:if test="${not empty storeDetail.storePicList }">
													<c:set var="fileSize" value="0" />
													<c:forEach items="${storeDetail.storePicList}" var="list"
														varStatus="status">
														<c:set var="fileSize" value="${fileSize + 1}" />
														<div class="thumb-xs-list item-photo-list">
															<a href="javascript:void(0);"
																class="thumbnail swipebox" target="_blank"> <span
																class="thumb-img"> <span
																	class="thumb-img-container"> <span
																		class="thumb-img-content"> <img alt="门店图片列表" data-original="${list.bigPictureUrl}"
																			src="${list.smallPictureUrl}" class="empPhoto" />
																	</span>
																</span>
															</span>
															</a> <input type="file" id="file${fileSize}"
																class="btn-flie file-control hide" data-limit="10"
																multiple="multiple" />
														</div>
													</c:forEach>

												</c:if>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<!-- <td class="talabel"></td> -->
							<td colspan="2">
								<div class="" role="main">
									<div>
										<div class="pd10">
											<h4 class="thumb-title">街景</h4>
											<div class="thumb-xs-box" id="thumbXsBox">
												<div
													style="width: 544px; height: 300px; border: #ccc solid 1px; border-radius: 4px; font-size: 12px; display: inline-block"
													id="panorama"></div>
											</div>
										</div>
									</div>
								</div>
							</td>
							<td colspan="2">
                                <div class="" role="main">
                                    <div>
                                        <div class="pd10">
                                            <h4 class="thumb-title">地图</h4>
                                            <div class="thumb-xs-box" id="thumbXsBox">
                                                <div
                                                    style="width: 544px; height: 300px; border: #ccc solid 1px; border-radius: 4px; font-size: 12px; display: inline-block"
                                                    id="mapDiv"></div>
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
