<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"
	trimDirectiveWhitespaces="true" language="java"%>

<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<%@include file="/WEB-INF/pages/common/common.jsp"%>
<title>房友新房分销系统</title>
<style>
.businessCss {
	margin-top: 5px;
	margin-left: 5px;
}

.w90 {
	width: 90px !important;
}

.layui-tab-content .layui-form-label {
	width: 100px;
}
</style>

</head>
<body>

	<div class="layui-card">
		<div class="layui-card-body">
			<div class="layui-form">
				<div class="layui-form-item" style="  border-bottom: 1px solid #E6E6E6;">
					<input type="hidden" id="qtType" name="qtType" value="${qtType}">
					<c:if test="${qtType eq 'qtProject'}">
						<div class="layui-row blockBody">
							<div class="layui-col-xs6" style="margin-top: -5px;font-weight: 400;font-size: 16px;">
								<label><b>项目编号：${estate.projectNo}</b> </label>&nbsp;&nbsp;&nbsp;
								<label><b>楼盘名称：${estate.estateNm}</b> </label> <input
									type="hidden" id="projectNo" name="projectNo"
									value="${estate.projectNo}"> <input type="hidden"
									id="estateId" name="estateId" value="${estate.estateId}">
								<input type="hidden" id="estateName" name="estateName"
									value="${estate.estateNm}">
							</div>
							<div class="layui-col-xs6 blockBtn" style="margin-top: -5px;">
								<button type="button" class="layui-btn layui-btn-primary"
									onclick="goback()">返回</button>
							</div>
						</div>
					</c:if>
				</div>
				<div class="layui-form-item">
					<c:if test="${qtType ne 'qtProject'}">
						<label class="layui-form-label" style="margin-left: 5px;">归属项目部</label>
						<div class="layui-input-inline">
							<select id="projectDepartmentId" name="projectDepartmentId"
								lay-verify="projectDepartmentId"
								lay-filter="projectDepartmentId">
								<option value="">请选择</option>
							</select>
						</div>
					</c:if>
					<label class="layui-form-label">订单编号</label>
					<div class="layui-input-inline">
						<input type="text" id="reportNo" autocomplete="off"
							style="margin-top: 2px;" name="reportNo" lay-verify="reportNo"
							lay-filter="reportNo" class="layui-input" placeholder="请输入">
					</div>
					<label class="layui-form-label">订单状态</label>
					<div class="layui-input-inline" style="margin-top: 2px;">
						<select id="orderStatus" name="orderStatus"
							lay-filter="orderStatus">
							<option value="">请选择</option>
							<option value="27001">报备</option>
							<option value="27002">成销</option>
							<option value="22003">结佣</option>
						</select>
					</div>
					<div class="layui-inline businessCss">
						<label class="layui-form-label">业务阶段</label>
						<div class="layui-input-inline w90">
							<select id="dateTypeKbn" name="dateTypeKbn"
								lay-filter="dateTypeKbn">
								<option value="">请选择</option>
								<option value="13701">报备</option>
								<option value="13704">成销</option>
								<option value="13705">结佣</option>
							</select>
						</div>
						<div class="layui-input-inline w90">
							<input type="text" name="dateStart" id="dateStart"
								lay-verify="dateStart" autocomplete="off"
								lay-filter="dateStart" class="layui-input" placeholder="开始日期">
						</div>
						<div class="layui-input-inline w90">
							<input type="text" name="dateEnd" id="dateEnd"
								lay-verify="dateEnd" autocomplete="off" lay-filter="dateEnd"
								class="layui-input" placeholder="结束日期">
						</div>
					</div>
					<div class="layui-inline">
						<div class="layui-inline toolbar" style="margin-top: 10px;">
							<button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
							<button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <table id="contentTable" lay-size="sm" lay-filter="content"></table>
        </div>
    </div>
</div>


	<script src="${ctx}/meta/pmls/js/otherReport/qtReportList.js?v=${vs}"></script>

	<script>
		//取消返回上一个页面
		function goback() {
			parent.redirectTo('delete', null, null);
		}
	</script>
</body>