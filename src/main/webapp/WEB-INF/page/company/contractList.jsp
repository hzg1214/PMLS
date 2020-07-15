<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/company/contractList.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

 <div class="container" role="main">
 		<div class="crumbs">
			<ul>
				<li><a href="#"  class="a_hover">公司管理</a></li>
				<li><a href="#"  class="a_hover">>公司详情</a></li>
				<li><a href="#"  class="a_hover">>合同</a></li>
			</ul>
		</div>
        <div class="row article">
        
        <!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/company/companyLeftMenu.jsp" flush="true">
				 <jsp:param value="110405" name="leftMenuSelectId" />
			</jsp:include>
			
			<div class="col-md-10 content">
        
            <div class="page-content">
            
        <!--     异步公司基本信息内容
                <div id="load_content">
		           <div id="LoadBaseInfo"></div>
		        </div> -->
            
                <h4>
                   <strong>合同</strong>
                   <a type="button" class="btn btn-primary" href="${ctx}/companys?searchParam=1">返回</a>
                </h4>
                
                <!-- 搜索条件区域 -->
                <form id="companyContractListForm">
                
                    <input type="hidden" name="companyId" id="companyId" value="${companyId}">
                
                
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="form-inline">
                              <div class="col-md-12">
                              
                                <div class="form-group">
                                    <select class="form-control" title="" id="conditionId" name="conditionId" value="">
                                        <option value="1">公司名称</option>
                                        <option value="2">协议书编号</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="sr-only">Name</label>
                                    <input type="text" class="form-control" id="name" name="name" placeholder="请输入" value="">
                                </div>
                                
                                <div class="form-group">
                                    <label>创建日期</label>
                            		<input  type="text" class="calendar-icon form-control w100" name="dateCreateStart" id="dateCreateStart" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate"/>
                                </div>
                                <div class="form-group">
                                	<input  type="text" class="calendar-icon form-control w100" name="dateCreateEnd" id="dateCreateEnd" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate"/>
                                </div>
                                <!-- 默认排序字段、排序类型 -->
                                <input type="hidden" name="orderName" value="dateCreate" >
       						    <input type="hidden" name="orderType" value="DESC" >
                                <input type="hidden"  id="signDateType" name="signDateType"  value="">
                                <button type="button" class="btn btn-primary" id="J_search"  onclick="javascript:search();">搜索</button>
                              </div>
                              
                              <%--  <div class="col-md-3 text-right">
                                <a type="button" class="btn btn-primary" href="javascript:void(0);" >新建</a>
                              </div> --%>
                                
                            </div>
                        </div>
                    </div>
                
                
                <!-- 异步加载列表内容 -->
                <div id="load_content">
		           <div id="LoadCxt"></div>
		        </div>
		        
               </form> 
                
            </div>
       
            </div>      
       
        </div>
    </div>
    
</body>

</html>
