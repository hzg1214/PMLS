<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<p>
	<strong>在售户型</strong>
	<button type="button" onclick="EstateType.toAddEstateType();" class="btn btn-primary">新增</button>
</p>
<script type="text/javascript">
	$(document).ready(function(){
		$(".houseType").show();
		console.log(hideFlag);
		if(hideFlag){
			$(".houseType").hide();
		}
	});
</script>
<table class="table table-striped table-hover table-bordered">
	<tr>
		<th>主力户型 ${houseTypeEdit}</th>
		<th class="houseType">户型</th>
		<th>面积</th>
		<th>朝向</th>
		<th style="display:none">标签</th>
		<th>户型图</th>
		<th>样板间</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${estateType}" var="list">
	<tr class="estateTypeEdit">
		<td><c:if test="${1 eq list.countFlg}">是</c:if><c:if test="${0 eq list.countFlg}">否</c:if></td>
		<td class="houseType">${list.countF}室${list.countT}厅${list.countW}卫</td>
		<td>${list.buildSquare}㎡</td>
		<td>${list.directionKbnStr}</td>
		<td style="display:none">${list.label}</td>
		<td><c:forEach items="${list.houseTypeImgs}" var="typeImgs">
				<img src="${typeImgs}" class="img-thumbnail" height="115" width="85"/>
			</c:forEach></td>
		<td><c:forEach items="${list.houseTemplateImgs}" var="templateImgs">
				<img src="${templateImgs}" class="img-thumbnail" height="115" width="85"/>
			</c:forEach></td>
		<td><a href="javascript:EstateType.toEditEstateType(${list.id});">编辑</a> <a
			href="javascript:EstateType.delEstateType(${list.id});">删除</a></td>
	</tr>
	</c:forEach>
</table>




