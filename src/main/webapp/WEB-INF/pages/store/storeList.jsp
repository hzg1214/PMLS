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
//     var progressStatusList='${progressStatusList}';//借佣进度
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
            	<div class="layui-tab layui-tab-card" lay-filter="storeTab">
                    <ul class="layui-tab-title">
                        <li lay-id="all" act-type="all" class="layui-this">全部</li>
                        <li lay-id="brand" act-type="brand">渠道(门店)</li>
                        <input type="hidden" id="backTab" value="${backTab}">
                    </ul>
                    <div class="layui-tab-content" style="padding-top: 10px">
                        <div id="allTabItem" class="layui-tab-item layui-show">
			                <div class="layui-form-item">
			                    <div class="layui-inline" >
			                        <label class="layui-form-label">所在城市</label>
			                        <div class="layui-input-inline" style="width:120px;">
			                            <select id="all-storeCityNo" name="all-storeCityNo" lay-filter="all-storeCityNo">
				                            <option value="" selected="selected">请选择城市</option>
			              					<c:forEach items="${citylist}" var="city">
				                           		<option value="${city.cityNo}">${city.cityName}</option>
				                            </c:forEach>
			                            </select>
			                        </div>
			                        <div class="layui-input-inline" style="width:120px;">
			                            <select id="all-districtNo" name="all-districtNo" lay-verify="all-districtNo" lay-filter="all-districtNo">
			                                <option value="">请选择区域</option>
			                            </select>
			                        </div>
			                    </div>
			                    
			                    <div class="layui-inline">
			                        <label class="layui-form-label">业务类型</label>
			                        <div class="layui-input-inline">
			                            <select id="all-brandType" name="all-brandType" lay-verify="all-brandType"
			                                    lay-search="" lay-filter="all-brandType">
			                                <option value="">请选择</option>
			                                <c:forEach items="${brandTypeList}" var="list">
			                                    <option value="${list.dicCode}">${list.dicValue}</option>
			                                </c:forEach>
			                            </select>
			                        </div>
			                    </div>
			                    
			                    
			                    
			<!--                     <div class="layui-inline"> -->
			<!--                         <label class="layui-form-label">创建时间</label> -->
			<!--                         <div class="layui-input-inline"> -->
			<!--                             <input type="text" name="dateCreateStart" id="dateCreateStart" lay-verify="dateCreateStart" -->
			<!--                                    placeholder="请选择" autocomplete="off" lay-verify="required" class="layui-input" -->
			<!--                                    readonly> -->
			<!--                         </div> -->
			<!--                         <div class="layui-input-inline"> -->
			<!--                             <input type="text" name="dateCreateEnd" autocomplete="off" id="dateCreateEnd" -->
			<!--                                    lay-verify="dateCreateEnd" -->
			<!--                                    placeholder="请选择" lay-verify="required" class="layui-input" readonly> -->
			<!--                         </div> -->
			<!--                     </div> -->
			                    
			<!--                     <div class="layui-inline"> -->
			<!--                         <label class="layui-form-label">营业状态</label> -->
			<!--                         <div class="layui-input-inline" style="width:100px;" > -->
			<%--                         	<t:dictSelect field="businessStatus" id="businessStatus" xmlkey="LOOKUP_Dic" dbparam="P1:209" nullOption="请选择" classvalue="30"></t:dictSelect> --%>
			<!--                         </div> -->
			<!--                     </div> -->
			                    
			                    <div class="layui-inline" >
			                        <label class="layui-form-label" style="width:85px;">联动维护中心</label>
			                        <div class="layui-input-inline" >
			                            <select class="form-control" title="" id="all-groupId"
															name="all-groupId" style="width:250px;">
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
			                        <div class="layui-input-inline" style="width: 250px;">
			                        	<input type="text" class="layui-input" id="all-searchKey" name="all-searchKey" autocomplete="off" placeholder="门店编号、名称、地址、维护人" lay-verify="">
			                        </div>
			                    </div>
			                    <div class="layui-inline" >
			                        <label class="layui-form-label">房友门店</label>
			                        <div class="layui-input-inline" >
			                            <select class="form-control" title="" id="all-isFyStore"
															name="all-isFyStore" >
											<option value="" >请选择</option>
											<option value="1">是</option>
											<option value="0">否</option>
										</select>
			                        </div>
			                    </div>
			                    
			<!--                     <div class="layui-inline"> -->
			<!--                         <label class="layui-form-label">合作模式</label> -->
			<!--                         <div class="layui-input-inline" style="width:100px;" > -->
			<%--                         	<t:dictSelect field="contractType" id="contractType" xmlkey="LOOKUP_Dic" --%>
			<%-- 													  dbparam="P1:103" nullOption="请选择" classvalue="30" --%>
			<%-- 													  jsonValue='{"1":"未签"}'></t:dictSelect> --%>
			<!--                         </div> -->
			<!--                     </div> -->
			                    
			<!--                     <div class="layui-inline"> -->
			<!--                         <label class="layui-form-label">装修进度</label> -->
			<!--                         <div class="layui-input-inline" style="width:100px;" > -->
			<%-- 	                        <t:dictSelect field="decorationStatus" id="decorationStatus" xmlkey="LOOKUP_Dic" --%>
			<%-- 														  dbparam="P1:163" nullOption="请选择" classvalue="30"></t:dictSelect> --%>
			<!--                         </div> -->
			<!--                     </div> -->
			                    
			<!--                     <div class="layui-inline"> -->
			<!--                         <label class="layui-form-label">合同状态</label> -->
			<!--                         <div class="layui-input-inline" style="width:100px;" > -->
			<%--                         	<t:dictSelect field="contractStatus" id="contractStatus" xmlkey="LOOKUP_Dic" --%>
			<%-- 													  dbparam="P1:104" nullOption="请选择" classvalue="30"></t:dictSelect> --%>
			<!--                         </div> -->
			<!--                     </div> -->
			
			                    <div class="layui-inline toolbar">
			                        <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
			                        <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
			                    </div>
			                    
			                </div>
		                    <div class="layui-form-item">
                                <table id="all-contentTable" lay-size="sm" lay-filter="all-content"></table>
                            </div>
			            </div>
			            
			            <div id="brandTabItem" class="layui-tab-item">
			                <div class="layui-form-item">
			                    <div class="layui-inline" >
			                        <label class="layui-form-label">所在城市</label>
			                        <div class="layui-input-inline" style="width:120px;">
			                            <select id="brand-storeCityNo" name="brand-storeCityNo" lay-filter="brand-storeCityNo">
				                            <option value="" selected="selected">请选择城市</option>
			              					<c:forEach items="${citylist}" var="city">
				                           		<option value="${city.cityNo}">${city.cityName}</option>
				                            </c:forEach>
			                            </select>
			                        </div>
			                        <div class="layui-input-inline" style="width:120px;">
			                            <select id="brand-districtNo" name="brand-districtNo" lay-verify="brand-districtNo" lay-filter="brand-districtNo">
			                                <option value="">请选择区域</option>
			                            </select>
			                        </div>
			                    </div>
			                    
			                    <div class="layui-inline" >
			                        <label class="layui-form-label">房友门店</label>
			                        <div class="layui-input-inline" >
			                            <select class="form-control" title="" id="brand-isFyStore"
															name="brand-isFyStore" >
											<option value="" >请选择</option>
											<option value="1">是</option>
											<option value="0">否</option>
										</select>
			                        </div>
			                    </div>
			                    
			                    
			                    <div class="layui-inline" >
			                        <label class="layui-form-label" style="width:85px;">联动维护中心</label>
			                        <div class="layui-input-inline" >
			                            <select class="form-control" title="" id="brand-groupId"
															name="brand-groupId" style="width:250px;">
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
			                        <div class="layui-input-inline" style="width: 250px;">
			                        	<input type="text" class="layui-input" id="brand-searchKey" name="brand-searchKey" autocomplete="off" placeholder="门店编号、名称、地址、维护人" lay-verify="">
			                        </div>
			                    </div>
			
			                    <div class="layui-inline toolbar">
			                        <button class="layui-btn layui-btn-normal" data-type="reload">查询</button>
			                        <button class="layui-btn layui-btn-primary" data-type="reset">重置</button>
			                    </div>
			                </div>
		                    <div class="layui-form-item">
                                <table id="brand-contentTable" lay-size="sm" lay-filter="brand-content"></table>
                            </div>
			            </div>
			       </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/store/storeList.js?v=${vs}"></script>
</body>
</html>

