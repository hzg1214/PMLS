<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>发布项目</title>
	<%@include file="../common/common.jsp" %>
	<style type="text/css">
		.releaseLay {
			height: 469px;
			background-color: #ffffff;
		}
		.releaseLay .layui-form{
			padding-top: 15px;
		}
		.releaseLay .layui-form .hidden{
			display: none;
		}
		.releaseLay .layui-form-item .layui-inline{
			width: 455px;
		}
		.releaseLay .layui-form-item .layui-inline .layui-form-label{
			width: 100px;
		}
		.releaseLay .layui-form-item .layui-input-inline{
			width: 230px;
		}
	</style>
</head>
<script type="application/javascript">

</script>
<body>

<div class="releaseLay">
	<div class="layui-form">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">发布方式</label>
				<div class="layui-input-inline">
					<input type="radio" name="releaseFlg" value="1" title="立即发布" checked="" lay-filter="releaseFlg">
					<input type="radio" name="releaseFlg" value="0" title="预约发布" lay-filter="releaseFlg">
				</div>
			</div>
		</div>
		<div class="layui-form-item hidden predictDate">
			<div class="layui-inline">
				<label class="layui-form-label">预约发布时间</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id="releaseDt" readonly="" placeholder="yyyy-MM-dd">
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="${ctx}/meta/pmls/js/estate/estateRelease.js?_v=${vs}"></script>
</body>
</html>
