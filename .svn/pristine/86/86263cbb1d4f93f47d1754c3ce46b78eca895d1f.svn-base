<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/company/company.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<style>
/*  面包屑  */
/* .crumbs {
	font-size:12px;
	line-height: 10px;
	color: #888888;
	margin-left:-50px
}
.crumbs a {
	color: #888888;
}
.crumbs ul li {
	display:inline
}
.a_hover{
	text-decoration: underline;
}
.a_hover:hover{
	color: #ba242a;
	text-decoration: underline!important;
} */
</style>
</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<!-- 关联的门店id 集合 -->
	<input type="hidden" id="storeIdArray" name="storeIdArray" />
	
	<input type="hidden" id="companyId" name="companyId" value="${companyId}"/>
	<div class="container">
		<div class="crumbs">
			<ul>
				<li><a href="#"  class="a_hover">公司管理</a></li>
				<li><a href="#"  class="a_hover">>公司详情</a></li>
				<li><a href="#"  class="a_hover">>基本信息</a></li>
			</ul>
		</div>
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/company/companyLeftMenu.jsp"
				flush="true">
				<jsp:param value="110401" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content">
				<div class="page-content">
					<h4>
						<strong>基本信息</strong>
						<a type="button" class="btn btn-primary" href="${ctx}/companys?searchParam=1">返回</a>
						<shiro:hasPermission name="/companys:CRM_COMPANYS_UPD">
							<a type="button" id ="edit" class="btn btn-primary" style="margin-right:10px">修改信息</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="/companys:CRM_COMPANYS_OPR">
							<a type="button" id ="operationEdit" class="btn btn-primary" style="margin-right:10px">运营维护</a>
						</shiro:hasPermission>
						<a href="javascript:void(0)" onclick="javascript:showPicture()" style="margin-left:12px;font-size:2px;position: relative;top: 10px;">查看信息修改说明</a>
					</h4>
					<table class="table-sammary" name="Viewerbox">
						<col style="width:130px"/>
						<col style="width:auto"/>
						<col style="width:120px"/>
						<col style="width:auto"/>	
						<tr>
						    <td class="talabel">公司编号：</td>
							<td style="width:350px">${info.companyNo}</td>
							<td class="talabel">公司名称：</td>
							<td>${info.companyName}</td>
						</tr>
						<tr>
						    <td class="talabel">统一社会信用代码：</td>
							<td>${info.businessLicenseNo}</td>
							<td class="talabel">营业执照性质：</td>
							<td>${info.businessLicenseNatureNm}</td>	
						</tr>
						<tr>
							<td class="talabel">法定代表/负责人：</td>
							<td>${info.legalPerson}</td>
							<td class="talabel">联系电话：</td>
							<td>${info.contactNumber}</td>
						</tr>
						<tr>
							<td class="talabel">公司类型：</td>
							<td>${info.companyTypeNm}</td>
							<td class="talabel">业务类型：</td>
							<td>${info.bizTypeNm}</td>
						</tr>
						<tr>
							<td class="talabel">公司经营地址：</td>
							<td>${info.companyAddress}</td>
						</tr>
						<tr>
							<td class="talabel">公司注册地址：</td>
							<td colspan="3">${info.cityName}${info.districtName}${info.address}</td>
						</tr>
						<tr>
							<td class="talabel">成立年限：</td>
							<td colspan="3">${info.establishYear}</td>
						</tr>
						<tr>
							<td class="talabel">对接人：</td>
							<td>${info.dockingPeo}</td>
							<td class="talabel">对接人电话：</td>
							<td>${info.dockingPeoTel}</td>
						</tr>
						<tr>
							<td class="talabel">门店数量：</td>
							<td>${info.storeNumber}</td>
							<td class="talabel">公司员工数量：</td>
							<td>${info.comStaffNum}</td>
						</tr>
						<tr>
							<td class="talabel">渠道分类：</td>
							<td>${info.channelTypeName}</td>
							<td class="talabel">可承接项目类型：</td>
							<td>${info.undertakeTypeName}</td>
						</tr>
						<tr>
							<td class="talabel">资源覆盖范围：</td>
							<td>${info.resourcesRange}</td>
							<td class="talabel">特有资源：</td>
							<td>${info.specificResources}</td>
						</tr>
						<tr>
							<td class="talabel">一二手联动规模：</td>
							<td>${info.lnkScaleName}</td>
							<td class="talabel">合作密切开发商：</td>
							<td>${info.closeDeveloper}</td>
						</tr>

						<tr>
							<td class="talabel">归属城市：</td>
							<td>${info.acCityName}</td>
							<td class="talabel">发布城市：</td>
							<td>${info.realseCityName}</td>
						</tr>


						<tr>
							<td colspan="4">
								<div class="" role="main">
									<div>
										<div class="pd10" >
											<h4 class="thumb-title"  style="border-bottom: 1px dashed #ccc;">
												<strong style="font-size: 14px;">营业执照附件</strong>
											</h4>
											<div class="thumb-xs-box" id="fileRecordMainBusiness">
												<c:if test="${not empty info.fileRecordMainBusiness}">
													<c:set var="fileSize" value="0"/>
													<c:forEach items="${info.fileRecordMainBusiness}" var="list" varStatus="status">
														<c:set var="fileSize" value="${fileSize + 1}"/>
														<div class="thumb-xs-list item-photo-list">
															<a href="javascript:void(0);" class="thumbnail swipebox" target="_blank">
																<span class="thumb-img">
																	<span class="thumb-img-container">
																		<span class="thumb-img-content">
																			<img alt="营业执照附件" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
																		</span>
																	</span>
																</span>
															</a>
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
		<div class="modal fade" id="companyInfoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
		  <div class="modal-dialog" role="document" style="width:700px">
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button class="close" type="button" data-dismiss="modal">×</button>
		        <h4 class="modal-title" id="weChatTemplateModalLabel02">信息修改说明</h4>
		      </div>
		      <div class="modal-body">
		        <form id="imageForm">
			        	<img src="${ctx}/meta/images/companyInfoEditRule.png" >
		        </form>
		      </div>
		      <!-- <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		      </div> -->
		   </div>
		  </div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">

	function showPicture(){
		$("#companyInfoModal span").remove();
		$('#companyInfoModal').modal('show');
	}

</script>
