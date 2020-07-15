<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>项目发布状态变更</title>
	<%@include file="../common/common.jsp" %>
	<style type="text/css">
		.releaseLay {
			height: 349px;
			background-color: #ffffff;
		}
		.releaseLay .layui-form{
			padding-top: 15px;
		}
		.releaseLay .layui-form-item .layui-inline{
			/*width: 455px;*/
		}
		.releaseLay .layui-form-item .layui-inline .layui-form-label{
			width: 160px;
		}
		.releaseLay .layui-form-item .layui-input-inline{
			width: 320px;
			height: 38px;
			line-height: 38px;
		}
		.releaseLay .xm-select-parent .xm-form-selected dl{
			max-height: 225px;
		}
	</style>
</head>
<script type="application/javascript">

</script>
<body>

<div class="releaseLay">
	<input type="hidden" id="id" name="id" value="${id}">
	<input type="hidden" id="estateId" name="estateId" value="${estateId}">
	<input type="hidden" id="flag" name="flag" value="${flag}">
	<input type="hidden" id="cityNo" name="cityNo" value="${cityNo}">
	<input type="hidden" id="cityNm" name="cityNm" value="${cityNm}">
	<input type="hidden" id="oldReleaseCity" name="oldReleaseCity" value="${oldReleaseCity}">
	<input type="hidden" id="citylistLenth" name ="citylistLenth" value="${citylistLenth}" />

	<div class="layui-form">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">原项目发布城市：</label>
				<div class="layui-input-inline">
					<c:if test="${flag eq '0'}">${cityNm}</c:if>
					<c:if test="${flag eq '1'}">
						${oldReleaseCity}
					</c:if>
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">变更后项目发布城市：</label>
				<div class="layui-input-inline">
					<select name="relseaseCity" xm-select="relseaseCity" xm-select-height="38px">
						<c:forEach items="${citylist}" var="city">
							<option value="${city.cityNo}">${city.cityName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${ctx}/meta/pmls/js/estate/estateReleaseChangeCity.js?_v=${vs}"></script>
</body>
</html>
