<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>房友新房分销系统</title>
	<%@include file="../common/common.jsp" %>

	<style>
		body{padding: 0px; /*overflow-y: scroll;*/}
		.layDiv{
			background-color: #FFF;
			height: 299px;
		}
		.layDiv .layui-form{
			padding-top: 30px;
		}
	</style>
</head>
<script type="application/javascript">
	var estateCityNoProject = '${estateCityNo}';
</script>
<body>
<div class="layDiv">
	<div class="layui-form">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">核算主体</label>
				<div class="layui-input-inline">
					<select id="lnkAccountProjectCode" name="lnkAccountProjectCode" lay-verify="required" lay-search="">
						<option value="">请选择核算主体</option>
					</select>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
<script type="text/javascript" src="${ctx}/meta/pmls/js/estate/estateAccountProjectMapping.js?_v=${vs}"></script>
