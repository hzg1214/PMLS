<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <style>
        .w90 {
            width: 90px !important;
        }

        /*.layui-form-label {*/
        /*    width: 100px;*/
        /*}*/

        body{padding: 0px; /*overflow-y: scroll;*/}
        .layui-btn-mini {
            height: 22px;
            line-height: 22px;
            padding: 0 5px;
            font-size: 12px;
        }
    </style>
</head>

<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <div class="layui-form-item">
					<input type="hidden" name="weekNo" id="weekNo" >
					<input type="hidden" name="sortNo" id="sortNo" >
					<input type="hidden" name="cityNo" id="cityNo" value="${cityNo}">
					<input type="hidden" name="performYear" id="performYear" value="${performSwitch.year}">
					<input type="hidden" name="performMonth" id="performMonth" value="${performSwitch.month}">
					<input type="hidden" name="performWeekNo" id="performWeekNo" value="${performSwitch.weekNo}">
                    <div class="layui-inline">
							<label class="layui-form-label">归属项目部</label>
							<div class="layui-input-inline">
								<select id="projectDepartmentId" name="projectDepartmentId"
									lay-verify="projectDepartmentId"
									lay-filter="projectDepartmentId">
									<option value="">请选择</option>
									<c:forEach items="${rebacklist}" var="list">
										<option value="${list.projectDepartmentId}">${list.projectDepartment}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">项目</label>
							<div class="layui-input-inline">
								<input type="text" id="projectNo" name="projectNo"
									lay-verify="projectNo" lay-filter="projectNo"
									autocomplete="off" class="layui-input"
									placeholder="请输入项目编号、楼盘名称">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">模板类型</label>
							<div class="layui-input-inline">
								<select id="trackType" name="trackType" lay-verify="trackType"
									lay-filter="trackType">
									<option value="">请选择</option>
									<option value="0">回款预计</option>
									<option value="1">回款实际</option>
								</select>
							</div>
						</div>
						
						<div class="layui-inline">
							<label class="layui-form-label">年月</label>
							<div class="layui-input-inline">
								<input type="text" name="yearMonth" id="yearMonth" 
									lay-verify="required" autocomplete="off"
									class="layui-input" placeholder="请选择">
							</div>
						</div>
						
						<div class="layui-inline">
							<label class="layui-form-label">关账版本</label>
							<div class="layui-input-inline">
								<input type="text" name="yearMonthVersion" id="yearMonthVersion" 
									lay-verify="required" autocomplete="off"
									class="layui-input" placeholder="请选择">
							</div>
							<div class="layui-input-inline" style="width:100px;">
								<select id="weekVersion" name="weekVersion" lay-verify="weekVersion"
									lay-filter="weekVersion">
									<option value="">请选择</option>
								</select>
							</div>
						</div>

                </div>
            </div>
        </div>
    </div>
</div>


<div>
    <div class="layui-card">
        <div class="layui-card-header">
            <div class="layui-inline toolbar">
	             <button class="layui-btn"  data-type="export">导出</button>
	             <button class="layui-btn" id="btnImport" data-type="import">导入</button>
	             <button class="layui-btn" data-type="reload">查询</button>
	             <button class="layui-btn" data-type="reset">重置</button>
            </div>
        </div>
        <div class="layui-card-body">
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>


<script type="text/javascript" src="${ctx}/meta/pmls/js/remittanceTrack/remittanceTrackList.js?_v=${vs}"></script>

</body>
</html>
