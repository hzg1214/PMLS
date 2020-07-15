<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/company/trailList.js?_v=${vs}"></script> 
<script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.0&ak=${sysConfig.baiduApiKey}"></script>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

 <div class="container" role="main">
        <div class="row article">
        
        <!-- 左侧菜单 -->
			<jsp:include page="/WEB-INF/page/company/companyLeftMenu.jsp" flush="true">
				<jsp:param value="110404" name="leftMenuSelectId" />
			</jsp:include>
			
			<div class="col-md-10 content">
        
            <div class="page-content">
            <!-- 异步公司基本信息内容
                <div id="load_content">
		           <div id="LoadBaseInfo"></div>
		        </div> -->
            
                <h4><strong>跟进</strong></h4>
                
                <!-- 搜索条件区域 -->
                <form id="trailListForm">
                
                    <input type="hidden" name="companyId" id="companyId" value="${companyId}">
                
                
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="form-inline">
                              <div class="col-md-12">
                              
                                 <div class="form-group">
                                    <label class="">跟进主题</label>
                                    <input type="text" class="form-control" id="title" name="title">
                                </div>
                                <div class="form-group">
                                    <label>跟进日期</label>
                            		<input  type="text" class="calendar-icon form-control w100" name="dateCreateFrom" id="dateCreateFrom" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate"/>
                                </div>
                                <div class="form-group">
                                	<input  type="text" class="calendar-icon form-control w100" name="dateCreateTo" id="dateCreateTo" onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="ipttext Wdate"/>
                                </div>
                                <!-- 默认排序字段、排序类型 -->
                                <input type="hidden" name="orderName" value="dateCreate" >
       						    <input type="hidden" name="orderType" value="DESC" >
                                
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
