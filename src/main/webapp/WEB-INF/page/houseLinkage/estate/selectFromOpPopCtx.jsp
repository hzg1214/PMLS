<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;width:750px">
	<table class="table table-striped table-bordered table-hover">
		<col style="width:40px;">
		<col style="width:160px;">
		<col style="width:120px;">
		<col style="width:auto;">
		<tr>
			<th></th>
			<th>楼盘名称</th>
			<th>楼盘城市</th>
			<th>楼盘地址</th>
		</tr>

		<c:forEach items="${estateList}" var="list">
			<tr class="J_eatateItem" data-hidenumberflg="0">
				<td><input type="radio" name="estateId" value="${list.estateId}" 
				        data-estateNm="${list.estateNm}" 
				        data-cityNo="${list.cityNo}"
				        data-address="${list.address}"
				        data-openTime="${list.openTime}"
				        data-salePriceUnitMin="${list.salePriceUnitMin}"
				        data-salePriceUnitMax="${list.salePriceUnitMax}"
				        data-mark="${list.mark}"
				        data-preSalePermitKbn="${list.preSalePermitKbn}"
				        data-preSalePermit="${list.preSalePermit}"
				        data-projectDescription="${list.projectDescription}"
				        data-ownYearKbnStr="${list.ownYearKbnStr}"
				        data-decorationKbnStr="${list.decorationKbnStr}"
				        data-typeKbnStr="${list.typeKbnStr}"
				        data-houseCnt="${list.houseCnt}"
				        data-parkCnt="${list.parkCnt}"
				        data-mgtCompany="${list.mgtCompany}"
				        data-rateFAR="${list.rateFAR }"
				        data-rateGreen="${list.rateGreen }"
				        data-mgtPrice="${list.mgtPrice }"
				        data-salesStatus="${list.salesStatus }"
				        data-salesStatusValue="${list.salesStatusValue }"
						data-cooperationDtStart="${list.cooperationDtStart}"
						data-cooperationDtEnd="${list.cooperationDtEnd}"
						data-houseTransferTime="${list.houseTransferTime}"
						data-partner="${list.partner}"
						data-partnerNm="${list.partnerNm}"
						data-partnerContractNm="${list.partnerContractNm}"
						data-partnerContractTel="${list.partnerContractTel}"
						data-devCompany="${list.devCompany}"
						data-districtId="${list.districtId}"
						data-countryNm="${list.countryNm}"
						data-roomLen="${fn:length(list.rooms)}"
					<c:forEach items="${list.rooms}" var="room" varStatus="status">
						   data-${status.index}-countF="${room.countF}"
						   data-${status.index}-countT="${room.countT}"
						   data-${status.index}-countW="${room.countW}"
						   data-${status.index}-buildSquare="${room.buildSquare}"
						   data-${status.index}-directionKbn="${room.directionKbn}"
					</c:forEach>
						>
				</td>
				<td>${list.estateNm}</td>
				<td>${list.cityNm}</td>
				<td>${list.address}</td>
			</tr>
		</c:forEach>
	</table>
</div>
${pageInfo.html}

<c:if test="${fn:length(estateList) le 0}">
	<div class="nodata">
		<i class="icon-hdd"></i>
		<p>暂无数据...</p>
	</div>
</c:if>