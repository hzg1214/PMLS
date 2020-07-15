<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
<script type="text/javascript"
	src="${ctx}/meta/js/follow/followList.js?_v=${vs}"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=1.0&ak=${sysConfig.baiduApiKey}"></script>
<script type="text/javascript"
	src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript"
	src="${ctx}/meta/js/store/storeRelateUtil.js?_v=${vs}"></script>
<script type="text/javascript"
	src="${ctx}/meta/js/relate/relateStoreUser.js?_v=${vs}"></script>
<script type="text/javascript"
	src="${ctx}/meta/js/relate/relateUtil.js?_v=${vs}"></script>
<script type="text/javascript"
    src="${ctx}/meta/js/relate/storeDetailEdit.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/contract/exchangeCenter.js?_v=${vs}"></script>   
<script src="http://api.map.baidu.com/api?v=2.0&ak=${sysConfig.baiduApiKey}" type="text/javascript"></script> 
<script type="text/javascript" src="${ctx}/meta/js/crm/storeMap.js?_v=${vs}"></script>
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<style>
	.icon-explain{
    width: 16px;
    height: 16px;
    display: inline-block;
    vertical-align: middle;
    position: relative;
    text-align: center;
    color: #fff;
    font-style: normal;
    font-size: 10px;
    line-height: 17px;
}
.icon-explain::after, .icon-explain::before{
    display: block;
    width: 100%;
    height: 100%;
}
.icon-explain::before {
    content: "\20";
    border-radius: 100%;
    background-color: #4A9AFB;
}
.icon-explain::after {
    content: "?";
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -8px;
    margin-left: -8px;
}
</style>
</head>
<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

	<%-- <input type="hidden" id="centerCount" name="centerCount" value="${centerCount}" />
	<input type="hidden" id="centerId" name="centerId" value="${centerId}" /> --%>
	<!-- 关联的门店id 集合 -->
	<input type="hidden" id="storeIdArray" name="storeIdArray" />
	<input type="hidden" id="storeId" name="storeId" value="${storeId}" />
	<div class="container">
		<div class="crumbs">
			<ul>
				<li><a href="#"  class="a_hover">门店管理</a></li>
				<li><a href="#"  class="a_hover">>门店详情</a></li>
				<li><a href="#"  class="a_hover">>基本信息</a></li>
			</ul>
		</div>
		<div class="row article">
			<!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/store/storeLeftMenu.jsp"
				flush="true">
				<jsp:param value="110401" name="leftMenuSelectId" />
			</jsp:include>
			<div class="col-md-10 content">
				<div class="page-content">
					<h4>
						<strong>基本信息</strong>					
						<a type="button" class="btn btn-primary" href="${ctx}/store?searchParam=1">返回</a>
						
						 <!-- 2017-10-10 新增门店状态的判断 如果门店处在停业申请或者已停业状态 不允许乙转甲 -->
						<c:if test="${storeDetail.businessStatus ne '20902' && storeDetail.businessStatus ne '20903' && storeDetail.contractType ne '0' && storeDetail.contractType ne '10307'}">
							<c:if test="${not empty storecontract.contractNo}">
									<a type="button" class="btn btn-primary"
									   data-contractchange = "${contractchange}"
									   data-iscancel="${storeDetail.isCancel}"
									   data-storeid="${storeDetail.storeId}"
									   data-contractid="${storecontract.id}"
									   data-relocationstatus="${storeDetail.storeRelocationStatus}"
									   data-partychangestatus="${storeDetail.storePartyChangeStatus}"
									   data-b2achangestatus="${storeDetail.storeB2AChangeStatus}"
									   data-contractstatus="${storecontract.contractStatus}"
									   data-abtypestore="${storecontract.aBTypeStore}"
									   data-contractPastStatus="${storecontract.contractPastStatus}"
									   style="float:right; margin-right:10px;" href="javascript:void(0);"
									   onclick="StoreLog.fnToContractChange(this)"
									>合同变更</a>
							</c:if>
						</c:if>
						   <!-- 2017-10-10 新增门店状态的判断 如果门店处在停业申请或者已停业状态 不允许续签 -->
						   <c:if test="${storeDetail.businessStatus ne '20902' && storeDetail.businessStatus ne '20903'}">
								<c:if test="${storeDetail.renewFlag eq RENEWFLAG_TYPE_DX and storeDetail.neededRenew eq 18501 and (storeDetail.contractType eq 10302 ||storeDetail.contractType eq 10304 || storeDetail.contractType eq 10307)}">
									<c:if test="${storeDetail.isCancel eq 17201 || storeDetail.isCancel eq 17202}">
									  <a type="button" class="btn btn-primary" style="float:right; margin-right:10px;" href="javascript:void(0)" onclick="renewAdd('${ctx}/contract/r/${storeDetail.contractId}/${storeDetail.storeId}','${ctx}/contract','${storeDetail.storeId}')">续签</a>
									</c:if>
								</c:if>
							</c:if>
							<!-- Add 2017/04/06 lei end -->
						<shiro:hasPermission name="/store:STORE_MTC_MAN">
							<%-- <c:if test="${not empty storeDetail.contractType && storeDetail.contractType != 0}"> --%>
								<a class="btn btn-primary" onclick="javascript:relateStoreUser(${storeDetail.storeId},${storeDetail.centerId});" style="margin-right:10px;">分配维护人</a>
							<%-- </c:if> --%>
						</shiro:hasPermission>
						<%-- <shiro:hasPermission name="/store:STORE_DCRT_STATE">
							<c:if test="${not empty storeDetail.contractType && storeDetail.contractType != 0}">
								<a type="button" class="btn btn-primary" href="javascript:void(0)" onclick="setDecorationState();" style="margin-right:10px;">装修进度</a>
							</c:if>
						</shiro:hasPermission>
						<shiro:hasPermission name="/store:STORE_DEPOSIT">
							<c:if test="${not empty storeDetail.contractType && storeDetail.contractType != 0}">
								<a type="button" class="btn btn-primary" href="javascript:void(0)" onclick="setDeposit();" style="margin-right:10px;">保证金到账</a>
							</c:if>
						</shiro:hasPermission>
						<shiro:hasPermission name="/store:STORE_FlOP_FEE">
							<c:if test="${not empty storeDetail.contractType && storeDetail.contractType != 0}">
								<a type="button" class="btn btn-primary" href="javascript:void(0)" onclick="setDecoractionFee();" style="margin-right:10px;">装修翻牌费支付</a>
							</c:if>
						</shiro:hasPermission> --%>
						
						<!-- Add 2017/07/11 QJP start -->
						<shiro:hasPermission name="/store:CRM_STORE_UPD">
							<c:if test="${storeDetail.businessStatus eq 20903 || storeDetail.businessStatus eq 20901}">
							    <a class="btn btn-primary"  onclick="javascript:relateStore('${storeDetail.storeId}','${storeDetail.storeNo}','${storeDetail.contractStatus}','${storeDetail.gpContractStatus}');" style="margin-right:10px;">修改信息</a>
							</c:if>
						</shiro:hasPermission>
					    <!-- Add 2017/07/11 QJP end -->
						<shiro:hasPermission name="/store:CRM_STORE_OPR">
							<a class="btn btn-primary"  onclick="javascript:operationUpdate('${storeDetail.storeId}','${storeDetail.storeNo}');" style="margin-right:10px;">运营维护</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="/store:CRM_STORE_CANCEL">
							<c:if test="${storeDetail.businessStatus eq 20903 and  storeDetail.stopCancelStatus ne 23201 and (storeDetail.contractStatus eq 10403 || storeDetail.contractStatus eq 10405)}">
								<a class="btn btn-primary" onclick="javascript:storeStopCancel(this,'${storeDetail.storeId}');" style="margin-right:10px;">停业撤销</a>
							</c:if>
						</shiro:hasPermission>
						<a href="javascript:void(0)" onclick="javascript:showPicture()" style="margin-left:12px;font-size:2px;position: relative;top: 10px;">查看信息修改说明</a>
					</h4>
					<table class="table-sammary">
					    <input type="hidden" id="latitude" value="${storeDetail.latitude}"/>
					    <input type="hidden" id="longitude" value="${storeDetail.longitude}"/>
					    <input type="hidden" id="storeName" value="${storeDetail.name}"/>
					    <input type="hidden" id="addressDetail" value="${storeDetail.addressDetail}"/>
					    <input type="hidden" id="createLongitude" value="${storeDetail.createLongitude}"/>
					    <input type="hidden" id="createLatitude" value="${storeDetail.createLatitude}"/>
						<col style="width:150px;">
						<col style="width:auto;">
						<col style="width:150px;">
						<col style="width:auto;">
						<tr>
						    <td class="talabel">编号：</td>
							<td>${storeDetail.storeNo}</td>
							<td class="talabel">店招编号：</td>
							<td>${storeDetail.signageNo}</td>
						</tr>
						<tr>
							<td class="talabel">门店名称：</td>
							<td>${storeDetail.name}</td>
							<td class="talabel">经纪人数：</td>
							<td>${storeDetail.storePersonNumName}</td>
							<%-- <td class="talabel">门店规模：</td>
							<td>${storeDetail.storeScaleName}</td> --%>
						</tr>
						<tr>
							<td class="talabel" style="vertical-align: text-top;">门店地址：</td>
							<td colspan="3" style="vertical-align: text-top;">${storeDetail.addressDetail}</td>
						</tr>
						<tr>
							<td class="talabel">门店负责人：</td>
							<td>${storeDetail.storeManager}</td>
							<td class="talabel">负责人电话：</td>
							<td>${storeDetail.storeManagerPhone}</td>	
						</tr>
						<tr>
						    <td class="talabel">门店所属中心：</td>
							<td>${storeDetail.centerName}</td>
							<td class="talabel">门店资质等级：</td>
							<td>
								<c:if test="${empty storeDetail.abtypeStore || storeDetail.abtypeStore eq 0}"> - </c:if>
								<c:if test="${storeDetail.abtypeStore eq 19901}">甲类</c:if>
								<c:if test="${storeDetail.abtypeStore eq 19902 }">乙类 (${storeDetail.btypeStoreName})</c:if>
							</td>		
						</tr>
						<tr>
						    <td class="talabel">合作模式：</td>
							<td>${storeDetail.contractTypeName}</td>
							<td class="talabel">装修进度：</td>
								<td>
									<c:choose>
		                            	<c:when test="${empty storeDetail.decorationStatusName or storeDetail.contractType eq 10306}">
		                            		-
		                            	</c:when>
		                            	<c:otherwise>
		                            		${storeDetail.decorationStatusName}
		                            	</c:otherwise>
		                            </c:choose>
		                            <input type="hidden" id="decorationState" value="${storeDetail.decorationState}"/>
							</td>
							<%-- <td class="talabel">门店状态：</td>
							<td>${storeDetail.auditStatusName}</td> --%>
						</tr>
						<c:if test="${storeDetail.depositFlag eq '1' && storeDetail.contractType ne 0}">
							<tr>
								<td class="talabel">应收保证金：</td>

								<td>
									<c:choose>
										<c:when test="${empty storeDetail.totalAmount}">
											-
										</c:when>
										<c:otherwise>
											￥<fmt:formatNumber type="number" value="${storeDetail.totalAmount}" pattern="0.00" maxFractionDigits="2"/>
										</c:otherwise>
									</c:choose>
								</td>

								<td class="talabel"><i class="icon-explain" title="已收保证金以OA过审为准"></i>&nbsp;已收保证金：</td>
								<td>
									<c:choose>
										<c:when test="${empty storeDetail.receiveAmount}">
											-
										</c:when>
										<c:otherwise>
											￥<fmt:formatNumber type="number" value="${storeDetail.receiveAmount}" pattern="0.00" maxFractionDigits="2"/>
										</c:otherwise>
									</c:choose>
								</td>

							</tr>
							<tr>
								<td class="talabel">已退保证金：</td>

								<td>
									<c:choose>
										<c:when test="${empty storeDetail.paymentAmount}">
											-
										</c:when>
										<c:otherwise>
											￥<fmt:formatNumber type="number" value="${storeDetail.paymentAmount}" pattern="0.00" maxFractionDigits="2"/>
										</c:otherwise>
									</c:choose>
								</td>
								<td class="talabel">保证金到账状态：</td>
								<td>${storeDetail.depositState}</td>
							</tr>
						</c:if>
						<tr>
							<td class="talabel">连锁情况：</td>
							<td>${storeDetail.linkageSituation}</td>
							<td class="talabel">连锁品牌：</td>
							<td>${storeDetail.chainBrandName}</td>
						</tr>
						<tr>
							<td class="talabel">所属公司：</td>
							<td>${storeDetail.companyName}</td>
							<td class="talabel">公盘所属公司：</td>
							<td>${storeDetail.gpCompanyName}</td>
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
							<td class="talabel">交易方式：</td>
							<td>${storeDetail.transactionModeName}</td>
						</tr>
						<tr>
							<td class="talabel">主营业务：</td>
							<td>${storeDetail.mainBusinessName}</td>
							<td class="talabel">经营场所类型：</td>
							<td>${storeDetail.businessPlaceTypeVal}</td>
						</tr>
						<tr>
							<td class="talabel">门店规模：</td>
							<td>${storeDetail.storeSizeScaleName}</td>
							<td class="talabel">业务类型：</td>
							<td>${storeDetail.bizTypeVal}</td>
						</tr>
						<tr>
							<td class="talabel">门店类型：</td>
							<td>${storeDetail.storeTypeNm}</td>
						    <td class="talabel">门店录入人：</td>
							<td>${storeDetail.userNameCreate}</td>
						</tr>
						<%-- <c:if test="${not empty storeDetail.contractType && storeDetail.contractType != 0}"> --%>
						<tr>
							<td class="talabel">门店录入时间：</td>
							<td>${storeDetail.dateCreate}</td>
							<td class="talabel">门店维护人：</td>
							<td><input type="hidden" id="maintainerId" name="maintainerId" value="${storeDetail.maintainer}" readonly="readonly">${storeDetail.maintainerName}</td>
						</tr>
						<tr>
							<td class="talabel">门店业绩是否撤销：</td>
							<td>
								<c:if test="${empty storeDetail.isCancel}"> - </c:if>
								<c:if test="${storeDetail.isCancel eq 17201 || storeDetail.isCancel eq 17202}">否</c:if>
								<c:if test="${storeDetail.isCancel eq 17203 }"> 是 </c:if>
							</td>
                            <td class="talabel">营业状态：</td>
                            <td>
                                <c:if test="${storeDetail.businessStatus eq 20901}">正常营业</c:if>
                                <c:if test="${storeDetail.businessStatus eq 20902}">停业审核中</c:if>
                                <c:if test="${storeDetail.businessStatus eq 20903}">已停业</c:if>
                            </td>
                        </tr>
						<tr>
							<c:if test="${storeDetail.contractType eq 10307}">
								<td class="talabel">授牌验收状态：</td>
								<td>${storeDetail.authCheckStatusNm}</td>
							</c:if>
							 <c:if test="${storeDetail.neededRenew eq 18502}">
                                    <td class="talabel">门店是否续签：</td>
                                    <td>否</td>
                            </c:if>
						</tr>
					</table>
				</div>
				<hr style="margin-bottom:0px;border-top:0px;">
				<div class="btn-group btn-table-group tab-store-links">
                    <a href="#storePic" data-toggle="tab" class="btn btn-default active">拓店照片</a>
                    <a id="storeDecorationPicTab" href="#storeDecorationPic" data-toggle="tab" class="btn btn-default">翻牌后门店照片</a>
                    <a id="storeMapTab" href="#storeMap" data-toggle="tab" class="btn btn-default">门店地图</a>
                </div>
                <div class="tab-content" style="border:1px solid #ddd; border-top:none; min-height:100px; _height:100px; padding:10px;">
                    <div class="tab-pane active" id="storePic">
                        <div class="thumb-xs-box" name="Viewerbox">
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
                                                    class="thumb-img-content"> <img alt="拓店照片列表"
                                                        src="${list.smallPictureUrl}" data-original="${list.middlePictureUrl}" class="empPhoto" />
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
                    <div class="tab-pane" id="storeDecorationPic">
                        <div class="thumb-xs-box" name="Viewerbox">
                            <c:if test="${storeDetail.decorationState eq '16304'}">
	                            <c:if test="${not empty storeDetail.storeDecorationPicList }">
	                                <c:set var="fileSize" value="0" />
	                                <c:forEach items="${storeDetail.storeDecorationPicList}" var="list"
	                                    varStatus="status">
	                                    <c:set var="fileSize" value="${fileSize + 1}" />
	                                    <div class="thumb-xs-list item-photo-list">
	                                        <a href="javascript:void(0);"
	                                            class="thumbnail swipebox" target="_blank"> <span
	                                            class="thumb-img"> <span
	                                                class="thumb-img-container"> <span
	                                                    class="thumb-img-content"> <img alt="翻牌后门店照片列表"
	                                                        src="${list.smallPictureUrl}" data-original="${list.middlePictureUrl}" class="empPhoto" />
	                                                </span>
	                                            </span>
	                                        </span>
	                                        </a> <input type="file" id="file${fileSize}"
	                                            class="btn-flie file-control hide" data-limit="10"
	                                            multiple="multiple" />
	                                    </div>
	                                </c:forEach>
	                            </c:if>
                            </c:if>
                        </div>
                    </div>
                    <div class="tab-pane" id="storeMap">
                        <div class="thumb-xs-box" >
                            <div style="width: 100%; height: 300px; border: #ccc solid 1px; border-radius: 4px; font-size: 12px; display: inline-block" id="mapDiv"></div>
                        </div>
                    </div>
                </div>
			</div>
		</div>
		<div class="modal fade" id="storeInfoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
		  <div class="modal-dialog" role="document" style="width:800px">
		    <div class="modal-content">
		      <div class="modal-header">
		      	<button class="close" type="button" data-dismiss="modal">×</button>
		        <h4 class="modal-title" id="weChatTemplateModalLabel02">信息修改说明</h4>
		      </div>
		      <div class="modal-body">
		        <form id="imageForm">
			        	<img src="${ctx}/meta/images/storeInfoEditRule.png" >
		        </form>
		      </div>
		   </div>
		  </div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function showPicture(){
		$("#storeInfoModal span").remove();
		$('#storeInfoModal').modal('show');
	}
</script>
