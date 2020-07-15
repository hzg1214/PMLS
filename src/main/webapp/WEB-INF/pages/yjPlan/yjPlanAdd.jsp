<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>佣金方案维护</title>
	<%@include file="../common/common.jsp" %>
	<style type="text/css">
		.yjPlanForm .layui-form-label{
			width:120px;
		}
		.yjPlanForm .layui-input-block{
			margin-left: 150px;
			min-height: 36px;
			margin-right: 40px;
		}
	</style>
</head>
<script type="application/javascript">

</script>
<body>
<div>
	<div class="layui-card">
		<div class="layui-card-body" style="min-height:500px;height: auto;">
			<div class="layui-form yjPlanForm">
                <input type="hidden" id="projectNo" name="projectNo" value="${projectNo}"/>
				<div class="layui-form-item">
					<label class="layui-form-label"><span class="redSpan">*</span>方案类型</label>
					<div class="layui-input-block">
						<input type="radio" id="planType0" name="planType" value="28701" title="收入" checked="" lay-filter="planType">
						<input type="radio" id="planType1" name="planType" value="28702" title="返佣" lay-filter="planType">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label"><span class="redSpan">*</span>方案名称</label>

					<div class="layui-input-block">
						<input type="text" id="planName" name="planName" style="width: 337px;" lay-verify="required" maxlength="10" placeholder="请输入不超过10个汉字" autocomplete="off"  class="layui-input">
					</div>
				</div>

				<div class="layui-form-item" id="fyPlanTypeDiv" style="display: none">
					<label class="layui-form-label"><span class="redSpan">*</span>返佣方案</label>
					<div class="layui-input-block">
						<input type="radio" name="fyPlanType" value="28801" title="标准" checked="" lay-filter="fyPlanType">
						<input type="radio" name="fyPlanType" value="28802" title="特殊" lay-filter="fyPlanType">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label"><span class="redSpan">*</span>物业类型</label>
					<div class="layui-input-block" style="width: 337px;">
							<select id="yjWyList" name="yjWyList" lay-verify="required" lay-search="" lay-filter="yjWyList"
									xm-select-max="100" xm-select="yjWyList" xm-select-search="" xm-select-show-count = '1' xm-select-height="30px" style="width: 120px;" xm-select-skin="normal">
							</select>
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label"><span class="redSpan">*</span>期间</label>
					<div class="layui-input-block">
						<div class="layui-input-inline" style="width: 100px;">
							<select id="periodType" name="periodType" lay-verify="required" lay-search="" lay-filter="periodType">
								<option value="">请选择阶段</option>
								<option value="28901">报备</option>
								<option value="28902">带看</option>
								<option value="28903">大定</option>
								<option value="28904">成销</option>
							</select>
						</div>

						<div class="layui-input-inline" style="width: 100px;">
							<input type="text" class="layui-input" id="periodBeginDate" readonly="" placeholder="开始日期">
						</div>
						<div class="layui-form-mid">-</div>
						<div class="layui-input-inline" style="width: 100px;">
							<input type="text" class="layui-input" id="periodEndDate" readonly="" placeholder="结束日期">
						</div>
					</div>
				</div>


				<div class="layui-form-item" style="margin-bottom: 5px;">
					<label class="layui-form-label"><span class="redSpan">*</span>公式</label>
					<div class="layui-input-block">
						<input type="radio" name="equationType" value="28101" title="每套" checked="" lay-filter="equationType">
						<input type="radio" name="equationType" value="28102" title="每平米" lay-filter="equationType">
						<input type="radio" name="equationType" value="28103" title="每套总价" lay-filter="equationType">
						<input type="radio" name="equationType" value="28104" title="每套总面积（㎡）" lay-filter="equationType">
					</div>
				</div>

				<div class="layui-form-item" id="equationTypeDiv01"  style="margin-top: 1px;margin-bottom: 20px;margin-left: 100px;">
					<table id="content01Table" lay-size="sm" lay-filter="content01Table"></table>
				</div>

				<div class="layui-form-item" id="equationTypeDiv02"  style="margin-top: 1px;margin-bottom: 20px;margin-left: 100px;display: none;">
					<table id="content02Table" lay-size="sm" lay-filter="content02Table"></table>
				</div>


				<div id="equationTypeDiv03" style="display: none;">
					<div class="layui-form-item" style="margin-top: 1px;margin-bottom: 1px;margin-left: 22px;" >
						<label class="layui-form-label">
							<strong>新增条件</strong><img style="width: 20px;height: 20px;margin-left: 1px" onclick="javascript:addColPage03()" src="${ctx}/meta/pmls/images/plus.png">
						</label>
					</div>
					<div class="layui-form-item" id="equationType03"  style="margin-top: 1px;margin-bottom: 20px;margin-left: 100px;">
						<table id="content03Table" lay-size="sm" lay-filter="content03Table"></table>
					</div>
				</div>



				<div id="equationTypeDiv04" style="display: none;">
					<div class="layui-form-item" style="margin-top: 1px;margin-bottom: 1px;margin-left: 22px;" >
						<label class="layui-form-label">
							<strong>新增条件</strong><img style="width: 20px;height: 20px;margin-left: 1px" onclick="javascript:addColPage04()" src="${ctx}/meta/pmls/images/plus.png">
						</label>
					</div>
					<div class="layui-form-item" id="equationType04"  style="margin-top: 1px;margin-bottom: 20px;margin-left: 100px;">
						<table id="content04Table" lay-size="sm" lay-filter="content04Table"></table>
					</div>
				</div>
			<%--	<div class="layui-form-item" id="equationType04"  style="margin-top: 1px;margin-bottom: 20px;margin-left: 100px;">
					<table id="content04Table" lay-size="sm" lay-filter="content04Table"></table>
				</div>--%>


				<div id="companyDiv" style="display: none;">
					<div class="layui-form-item" style="margin-bottom: 1px;margin-left: 22px;" >
						<label class="layui-form-label">
							<strong>渠道公司</strong><img style="width: 20px;height: 20px;margin-left: 1px" onclick="javascript:addCol()" src="${ctx}/meta/pmls/images/plus.png">
						</label>
					</div>
					<div class="layui-form-item"  style="margin-top: 1px;margin-bottom: 20px;margin-left: 100px;">
						<table id="contentCompanyTable" lay-size="sm" lay-filter="contentCompanyTable"></table>
					</div>
				</div>



			</div>
		</div>
	</div>
</div>


</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/yjPlan/yjPlanAdd.js?v=${vs}"></script>
</body>
</html>
