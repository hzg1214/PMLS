<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/houseLinkage/estate/estateAudit.js?_v=${vs}"></script>
</head>
<body style="padding-bottom:34px;">
	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>
	<div class="container theme-hipage ng-scope">
		<div class="row">
			<div class="page-content">
				<!-- 楼盘基本信息和详情 -->
				<h4 class="border-bottom pdb10">
					<strong>项目详情</strong>
					<form id="estateAuditYesForm" style="float:right;">
						<input type="hidden" name="auditStatus" value="12903"> <input type="hidden" name="id"
							value="${estateInfo.estate.id}"> <span onclick="auditYes('${estateInfo.estate.id}')"
							class="btn btn-primary">通过</span> <span onclick="auditNo('${estateInfo.estate.id}')"
							class="btn btn-primary">驳回</span> <a href="${ctx}/estate" class="btn btn-primary" style="float:none;">返回</a>
					</form>
				</h4>
				<div style="margin-bottom:20px;">
					<p>
						<strong>基本信息</strong>
					</p>
					<table class="table-sammary">
						<col style="width:105px;">
						<col style="width:auto">
						<col style="width:117px;">
						<col style="width:auto">
						<col style="width:105px;">
						<col style="width:auto">
						<tr>
							<td class="talabel">楼盘名：</td>
							<td>${estateInfo.estate.estateNm}</td>
							<td class="talabel">备案名：</td>
							<td>${estateInfo.estate.recordName}</td>
							<td class="talabel">推广名：</td>
							<td>${estateInfo.estate.promotionName}</td>
						</tr>
						<tr>
							<td class="talabel">签约名：</td>
							<td>${estateInfo.estate.signName}</td>
							<td class="talabel">审核状态：</td>
							<td>${estateInfo.estate.auditStatusStr}</td>
							<td class="talabel">发布状态：</td>
							<td>${estateInfo.estate.releaseStatusStr}</td>
						</tr>
						<tr>
							<td class="talabel">业绩归属城市：</td>
							<td>${estateInfo.estate.cityNm}</td>
							<td class="talabel">业绩归属项目部：</td>
							<td>--</td>
							<td class="talabel">地址：</td>
							<td>${estateInfo.estate.realityCityNm}${estateInfo.estate.districtNm}${estateInfo.estate.areaNm}${estateInfo.estate.address}</td>
							
							
							
						</tr>
						<tr>
							<td class="talabel">销售状态：</td>
							<td>${estateInfo.estate.salesStatusStr}</td>
							<td class="talabel">总价段：</td>
							<td>${estateInfo.estate.salePriceUnitMin}-${estateInfo.estate.salePriceUnitMax}万元/套</td>
							<td class="talabel">标签：</td>
							<td>${estateInfo.estate.mark}</td>
						</tr>
						<tr>
							<td class="talabel">预计开盘日期：</td>
							<td>${sdk:ymd3(estateInfo.estate.openTime)}</td>
							<td class="talabel">预计交房日期：</td>
							<td>${estateInfo.estate.houseTransferTime}</td>
							<td class="talabel">合作方类型：</td>
							<td>${estateInfo.estate.partnerStr}</td>
						</tr>
						<tr>
							<td class="talabel">合作方名称：</td>
							<td>${estateInfo.estate.partnerNm}</td>
							<td class="talabel">我方负责人：</td>
							<td>${estateInfo.estate.sceneDeptNm}${estateInfo.estate.sceneEmpName}</td>
							<td class="talabel">合作期：</td>
							<td>${sdk:ymd2(estateInfo.estate.cooperationDtStart)}至${sdk:ymd2(estateInfo.estate.cooperationDtEnd)}</td>
						</tr>
						<tr>
							<td class="talabel">项目简介：</td>
							<td colspan="3">${estateInfo.estate.projectDescription}</td>
						</tr>
					</table>
				</div>
				<div class="btn-group btn-table-group">
					<!-- <li class="active"><a href="#BasicInformation" data-toggle="tab">基本信息</a></li> -->

					<a href="#Housingdetails" data-toggle="tab" class="btn btn-default active">楼盘详情</a>
					<a href="#Propertyinformation" data-toggle="tab" class="btn btn-default">物业信息</a>
					<a href="#Linkagerule" data-toggle="tab" class="btn btn-default">联动规则</a>
					<a href="#Soldinfamily" data-toggle="tab" class="btn btn-default">在售户型</a>
					<a href="#Buildinganalbum" data-toggle="tab" class="btn btn-default">楼盘相册</a>
					<a href="#Peripheral" data-toggle="tab" class="btn btn-default">周边配套</a>
					<a href="#ChangeHistory" data-toggle="tab" class="btn btn-default">项目日志</a>
				</div>
				<div class="tab-content" style="border:1px solid #ddd; border-top:none; min-height:100px; _height:100px; padding:10px; margin-bottom:24px;">
					<div class="tab-pane active" id="Housingdetails">
						<table class="table-sammary">
							<col style="width:80px;">
							<col style="width:auto">
							<col style="width:80px;">
							<col style="width:auto">
							<col style="width:80px;">
							<col style="width:auto">
							<tr>
								<td class="talabel">开发商：</td>
								<td>${estateInfo.estate.devCompany}</td>
								<td class="talabel">案场地址：</td>
								<td colspan="3">${estateInfo.estate.fieldAddress}</td>
							</tr>
							<tr>
								<td class="talabel">预售许可：</td>
								<td>
									<c:if test="${1 eq estateInfo.estate.preSalePermitKbn}">有</c:if>
									<c:if test="${0 eq estateInfo.estate.preSalePermitKbn}">无</c:if>
								</td>
								<td class="talabel">物业类型：</td>
								<td>${estateInfo.estate.mgtKbnStr}</td>
								<td class="talabel">产权年限：</td>
								<td>${estateInfo.estate.ownYearKbnStr}</td>
							</tr>
							<tr>
								<td class="talabel">装修情况：</td>
								<td>${estateInfo.estate.decorationKbnStr}</td>
								<td class="talabel">建筑类型：</td>
								<td>${estateInfo.estate.typeKbnStr}</td>
								<td class="talabel">规划户数：</td>
								<td>${estateInfo.estate.houseCnt}户</td>
							</tr>
							<tr>
								<td class="talabel">车位情况：</td>
								<td>${estateInfo.estate.parkCnt}位</td>
								<td class="talabel">停车费：</td>
								<td>${estateInfo.estate.parkFee}元/月</td>
								<td class="talabel">梯户：</td>
								<td>
									<c:forEach items="${estateInfo.estate.tiHus}" var="list"
										varStatus="status">
									${list.Ti}梯${list.Hu}户<c:if test="${status.last eq false}">,</c:if>
									</c:forEach>
								</td>
							</tr>
						</table>
					</div>
					<div class="tab-pane" id="Propertyinformation">
						<table class="table-sammary">
							<col style="width:80px;">
							<col style="width:auto">
							<col style="width:80px;">
							<col style="width:auto">
							<col style="width:80px;">
							<col style="width:auto">
							<tr>
								<td class="talabel">物业公司：</td>
								<td>${estateInfo.estate.mgtCompany}</td>
								<td class="talabel">容积率：</td>
								<td>${estateInfo.estate.rateFAR}</td>
								<td class="talabel">绿化率：</td>
								<td>${estateInfo.estate.rateGreen}%</td>
							</tr>
							<tr>
								<td class="talabel">物业费用：</td>
								<td>${estateInfo.estate.mgtPrice}元/m²/月</td>
								<td class="talabel">供暖方式：</td>
								<td>${estateInfo.estate.heatKbnStr}</td>
								<td class="talabel">水电燃气：</td>
								<td>${estateInfo.estate.hydropowerGasKbnStr}</td>
							</tr>
						</table>
					</div>
					<div class="tab-pane" id="Linkagerule">
						<table class="table-sammary">
							<col style="width:90px;">
							<col style="width:auto">
							<col style="width:90px;">
							<col style="width:auto">
							<col style="width:90px;">
							<col style="width:auto">
							<c:forEach items="${estateInfo.estateRuleDetails}" var="list">
							<tr>
								<td class="talabel">认证类型：</td>
								<td>${list.authenticationKbnStr}</td>
								<td class="talabel">提前报备期：</td>
								<td>${list.advanceReportHH}时${list.advanceReportMM}分</td>
								<td class="talabel">带看保护期：</td>
								<td colspan="3">${list.relationProtectPeriod}天</td>
							</tr>
							<tr>
								<td class="talabel">带看奖励：</td>
								<td>${list.relationReward}元</td>
								<td class="talabel">起始日期：</td>
								<td>${sdk:ymd2(list.relationDtStart)}</td>
								<td class="talabel">截止日期：</td>
								<td>${sdk:ymd2(list.relationDtEnd)}</td>
							</tr>
							<tr>
								<td class="talabel">认筹奖励：</td>
								<td>${list.pledgedReward}元</td>
								<td class="talabel">起始日期：</td>
								<td>${sdk:ymd2(list.pledgedDtStart)}</td>
								<td class="talabel">截止日期：</td>
								<td>${sdk:ymd2(list.pledgedDtEnd)}</td>
							</tr>
							<tr>
								<td class="talabel">大定奖励：</td>
								<td>${list.subscribedReward}元</td>
								<td class="talabel">起始日期：</td>
								<td>${sdk:ymd2(list.subscribedDtStart)}</td>
								<td class="talabel">截止日期：</td>
								<td>${sdk:ymd2(list.subscribedDtEnd)}</td>
							</tr>
							<tr>
								<td class="talabel">成销奖励：</td>
								<td>${list.bargainReward}元</td>
								<td class="talabel">结佣期限：</td>
								<td>${list.commissionPeriod}天</td>
								<td class="talabel">佣金方式：</td>
								<td>${list.commissionKbnStr}</td>
							</tr>
							<tr>
								<td class="talabel">佣金：</td>
								<td>${list.commission}<c:if
									test="${'1481' eq list.commissionKbn}">元</c:if>
								<c:if test="${'1482' eq list.commissionKbn}">%</c:if></td>
								<td class="talabel">佣金说明：</td>
								<td>${list.commissionMemo}</td>
								<td class="talabel">结佣方式：</td>
								<td>${list.payKbnStr}</td>
							</tr>
							<tr>
								<td class="talabel">销售方式：</td>
								<td>${list.saleKbnStr}</td>
								<td class="talabel">报备开始日：</td>
								<td>${sdk:ymd2(list.reportDtStart)}</td>
								<td class="talabel">报备截止日：</td>
								<td>${sdk:ymd2(list.reportDtEnd)}</td>
							</tr>
							<tr>
								<td class="talabel">报备模式：</td>
								<td>${list.reportKbnStr}</td>
								<td class="talabel">隐号报备：</td>
								<td><c:if test="${'1' eq list.hideNumberFlg}">支持</c:if>
								<c:if test="${'0' eq list.hideNumberFlg}">不支持</c:if></td>
								<td class="talabel">报备规则：</td>
								<td>${list.reportRule}</td>
							</tr>
							<tr>
								<td class="talabel">结佣规则：</td>
								<td colspan="5">${list.commissionRule}</td>
							</tr>
							</c:forEach>
						</table>
					</div>
					<div class="tab-pane" id="Soldinfamily">
						<table class="table-sammary">
							<col style="width:80px;">
							<col style="width:auto">
							<col style="width:80px;">
							<col style="width:auto">
							<col style="width:80px;">
							<col style="width:auto">
							<c:forEach items="${estateInfo.estateTypeDetails}" var="list">
							<tr>
								<td class="talabel">户型：</td>
								<td>${list.countF}室${list.countT}厅${list.countW}卫<c:if test="${1 eq list.countFlg}">(主力)</c:if></td>
								<td class="talabel">面积：</td>
								<td>${list.buildSquare}㎡</td>
								<td class="talabel">朝向：</td>
								<td>${list.directionKbnStr}</td>
							</tr>
							<tr>
								<td class="talabel">户型图：</td>
								<td colspan="5">
									<ul class="list_photo_uploader" style="display:inline-block">
										<c:forEach items="${list.houseImgs}" var="imgList">
											<c:if test="${imgList.photoKbn eq 15906}">
												<li class="item_photo_uploader <c:if test='${imgList.coverFlg eq "Y"}'>item_cover</c:if>">
													<a href="${imgList.fileUrl}" target="_blank">
														<img src="${imgList.fileAbbrUrl}" class="img-thumbnail" />
													</a>
													<c:if test='${list.coverFlg eq "Y"}'>
														<i class="cover_bg"></i>
													</c:if>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</td>
							</tr>
							<tr>
								<td class="talabel">样板间：</td>
								<td colspan="5">
									<ul class="list_photo_uploader" style="display:inline-block">
										<c:forEach items="${list.houseImgs}" var="imgList">
											<c:if test="${imgList.photoKbn eq 15907}">
												<li class="item_photo_uploader <c:if test='${imgList.coverFlg eq "Y"}'>item_cover</c:if>">
													<a href="${imgList.fileUrl}" target="_blank">
														<img src="${imgList.fileAbbrUrl}" class="img-thumbnail" />
													</a>
													<c:if test='${list.coverFlg eq "Y"}'>
														<i class="cover_bg"></i>
													</c:if>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</td>
							</tr>
						</table>
						</c:forEach>
					</div>
					<div class="tab-pane" id="Buildinganalbum">
						<table class="table-sammary">
							<col style="width:80px;">
							<col style="width:auto">
							<tr>
								<td class="talabel">效果图：</td>
								<td>
									<ul class="list_photo_uploader" style="display:inline-block">
										<c:forEach items="${estateInfo.filePhoto}" var="list">
											<c:if test="${list.photoKbn eq 15901}">
												<li class="item_photo_uploader <c:if test='${list.coverFlg eq "Y"}'>item_cover</c:if>">
													<a href="${list.fileUrl}" target="_blank">
														<img src="${list.fileAbbrUrl}" class="img-thumbnail" />
													</a>
													<c:if test='${list.coverFlg eq "Y"}'>
														<i class="cover_bg"></i>
													</c:if>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</td>
							</tr>
							<tr>
								<td class="talabel">样板间：</td>
								<td>
									<ul class="list_photo_uploader" style="display:inline-block">
										<c:forEach items="${estateInfo.filePhoto}" var="list">
											<c:if test="${list.photoKbn eq 15902}">
												<li class="item_photo_uploader <c:if test='${list.coverFlg eq "Y"}'>item_cover</c:if>">
													<a href="${list.fileUrl}" target="_blank">
														<img src="${list.fileAbbrUrl}" class="img-thumbnail" />
													</a>
													<c:if test='${list.coverFlg eq "Y"}'>
														<i class="cover_bg"></i>
													</c:if>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</td>
							</tr>
							<tr>
								<td class="talabel">地理位置：</td>
								<td>
									<ul class="list_photo_uploader" style="display:inline-block">
										<c:forEach items="${estateInfo.filePhoto}" var="list">
											<c:if test="${list.photoKbn eq 15903}">
												<li class="item_photo_uploader <c:if test='${list.coverFlg eq "Y"}'>item_cover</c:if>">
													<a href="${list.fileUrl}" target="_blank">
														<img src="${list.fileAbbrUrl}" class="img-thumbnail" />
													</a>
													<c:if test='${list.coverFlg eq "Y"}'>
														<i class="cover_bg"></i>
													</c:if>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</td>
							</tr>
							<tr>
								<td class="talabel">区域规划：</td>
								<td>
									<ul class="list_photo_uploader" style="display:inline-block">
										<c:forEach items="${estateInfo.filePhoto}" var="list">
											<c:if test="${list.photoKbn eq 15904}">
												<li class="item_photo_uploader <c:if test='${list.coverFlg eq "Y"}'>item_cover</c:if>">
													<a href="${list.fileUrl}" target="_blank">
														<img src="${list.fileAbbrUrl}" class="img-thumbnail" />
													</a>
													<c:if test='${list.coverFlg eq "Y"}'>
														<i class="cover_bg"></i>
													</c:if>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</td>
							</tr>
							<tr>
								<td class="talabel">实景图：</td>
								<td>
									<ul class="list_photo_uploader" style="display:inline-block">
										<c:forEach items="${estateInfo.filePhoto}" var="list">
											<c:if test="${list.photoKbn eq 15905}">
												<li class="item_photo_uploader <c:if test='${list.coverFlg eq "Y"}'>item_cover</c:if>">
													<a href="${list.fileUrl}" target="_blank">
														<img src="${list.fileAbbrUrl}" class="img-thumbnail" />
													</a>
													<c:if test='${list.coverFlg eq "Y"}'>
														<i class="cover_bg"></i>
													</c:if>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</td>
							</tr>
						</table>
					</div>
					<div class="tab-pane" id="Peripheral">
						<table class="table-sammary">
							<c:forEach items="${estateInfo.estateSupportDetails}" var="list">
								<tr>
									<td class="talabel" width="80px">类型：</td>
									<td width="20%">${list.title}</td>
									<td class="talabel" width="80px">描述：</td>
									<td>${list.description}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<div class="tab-pane" id="ChangeHistory">
						<table style="width:100%;border-left:#adadad 1px solid;border-top:#adadad 1px solid">
							<col style="width:10%;border-right:#adadad 1px solid">
							<col style="width:15%;border-right:#adadad 1px solid">
							<col style="width:40%;border-right:#adadad 1px solid">
							<col style="width:15%;border-right:#adadad 1px solid">
							<col style="width:30%;border-right:#adadad 1px solid">
							<tr style="height:28px;border-bottom:#adadad 1px solid">
								<td align="center">序号</td>
								<td align="center">类型</td>
								<td align="center">详细描述</td>
								<td align="center">操作人（工号）</td>
								<td align="center">操作时间</td>
							</tr>
							<c:forEach items="${estateInfo.estateChangeDetails}" var="estateChange">
								<tr style="height:28px;border-bottom:#adadad 1px solid">
									<td align="center">${estateChange.orderId}</td>
									<td align="center">${estateChange.changeName}</td>
									<td align="center">${estateChange.changeDetail}</td>
									<td align="center">${estateChange.operatorName}<c:if test="${estateChange.operatorCode ne 0}">（${estateChange.operatorCode}）</c:if></td>
									<td align="center">${estateChange.changeDate}</td>
								</tr>
							</c:forEach>
						</table>
						<c:if test="${empty estateInfo.estateChangeDetails}"><div class="nodata"><i class="icon-hdd"></i><p>暂无数据...</p></div></c:if>
					</div>
				</div>
				<!-- <ul class="list-inline form-inline" style="margin-top: 40px">
					<li>
						<strong>下架说明</strong>
						<div style="display: inline-block;word-wrap: break-word; word-break: break-all;">${estateInfo.estate.releaseOffMemo}</div>
						</li>
				</ul> -->
			</div>
			
			<!-- <div class="" style="position:fixed; bottom:0;width:100%;background:#fff;"></div> -->
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$(document).on("click",".btn-table-group .btn",function(){
				$(this).addClass("active").siblings().removeClass("active");
			});
		});
	</script>
</body>
</html>
