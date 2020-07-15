<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房友新房分销系统</title>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>

    <style>
    
    </style>

</head>
<script type="application/javascript">
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
            <input type="hidden" name="acCityNo" id="acCityNo" value="${acCityNo}">
                <div class="layui-form-item">
                    <div class="layui-inline" >
                        <label class="layui-form-label">所在城市</label>
                        <div class="layui-input-inline" >
                            <select id="storeCityNo" name="storeCityNo" lay-filter="storeCityNo">
	                            <option value="" selected="selected">请选择城市</option>
              					<c:forEach items="${citylist}" var="city">
	                           		<option value="${city.cityNo}">${city.cityName}</option>
	                            </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="layui-inline" >
                        <label class="layui-form-label">房友门店</label>
                        <div class="layui-input-inline" >
                            <select class="form-control" title="" id="isFyStore"
												name="isFyStore" >
								<option value="" >请选择</option>
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
                        </div>
                    </div>
                    
                    <div class="layui-inline" >
                        <label class="layui-form-label" style="width:85px;">联动维护中心</label>
                        <div class="layui-input-inline" >
                            <select class="form-control" title="" id="groupId"
												name="groupId" >
								<option value="" selected="selected">请选择</option>
								<c:if test="${!empty groupList}">
									<c:forEach items="${groupList}" var="group">
										<option value="${group.groupId}">${group.groupName}</option>
									</c:forEach>
								</c:if>
							</select>
                        </div>
                    </div>
                    
                    <div class="layui-inline">
                        <label class="layui-form-label">门店</label>
                        <div class="layui-input-inline" >
                        	<input type="text" class="layui-input" id="storeNo" name="storeNo" autocomplete="off" placeholder="门店编号、名称" lay-verify="">
                        </div>
                    </div>
                    
                    <div class="layui-inline">
                        <label class="layui-form-label">维护人</label>
                        <div class="layui-input-inline">
                        	<input type="text" class="layui-input" id="maintenanceCode" name="maintenanceCode" autocomplete="off" placeholder="维护人工号、姓名" lay-verify="">
                        </div>
                    </div>

                    <div class="layui-inline toolbar">
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
        <shiro:hasPermission name="/pmlsStoreMaintenance:BATCH_CHANGE">
	        <div class="layui-card-header">
	            <div class="layui-btn-group toolbar">
	                   <button class="layui-btn" data-type="batchChangeMaintenance">批量变更维护人</button>
	            </div>
	        </div>
        </shiro:hasPermission>
        <div class="layui-card-body">
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/storeMaintenance/storeMaintenanceList.js?v=${vs}"></script>
</body>
</html>

