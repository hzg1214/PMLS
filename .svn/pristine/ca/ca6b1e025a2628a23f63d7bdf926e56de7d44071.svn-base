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
        .layui-btn-mini {
            height: 22px;
            line-height: 22px;
            padding: 0 5px;
            font-size: 12px;
        }
    </style>
</head>
<script type="application/javascript">

    var citylist = '${citylist}';
    var partnerList = '${partnerList}';

</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <div class="layui-form-item"  style="margin-bottom:0px;">
					<div class="layui-inline">
                        <label class="layui-form-label">城市</label>
                        <div class="layui-input-inline">
                            <select id="cityNo" name="cityNo" xm-select="cityNo"
                                    xm-select-height="30px" xm-select-search="" xm-select-skin="normal"
                                    xm-select-show-count='1'>
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">合作方编号</label>
                        <div class="layui-input-inline">
                            <input type="text" autocomplete="off" class="layui-input" id="developerCode"  placeholder="请输入" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">合作方名称</label>
                        <div class="layui-input-inline">
                            <input type="text" autocomplete="off" class="layui-input" id="developerName" placeholder="请输入" lay-verify="">
                        </div>
                    </div>

					<div class="layui-inline">
                        <label class="layui-form-label">合作方类型</label>
                        <div class="layui-input-inline">
                            <select id="partner" name="partner" lay-verify="partner"
                                     lay-filter="partner">
                                <option value="">请选择</option>
                                <c:forEach items="${partnerList}" var="list">
                                    <option value="${list.dicCode}">${list.dicValue}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
					<div class="layui-inline">
                        <label class="layui-form-label">合作方品牌</label>
                        <div class="layui-input-inline">
                        	<input type="text" autocomplete="off" class="layui-input" id="developerBrandCode"  placeholder="请输入" lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline toolbar">
                        <button class="layui-btn" data-type="reload">查询</button>
                        <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<div>
    <div class="layui-card">
        <shiro:hasPermission name="/developer:ADD_DEVELOPER">
	        <div class="layui-card-header">
	            <div class="layui-btn-group toolbar">
	                	<button class="layui-btn" data-type="addDeveloper">新建合作方</button>
	            </div>
	        </div>
        </shiro:hasPermission>
        <div class="layui-card-body">
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>

</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/developer/developerList.js?v=${vs}"></script>
</body>
</html>

