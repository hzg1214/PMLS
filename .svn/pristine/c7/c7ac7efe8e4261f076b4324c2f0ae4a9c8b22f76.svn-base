<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>


<script>

$(function() {

	// 初始化查询
	RelateCompany.initList();
});

/** ***************************************************** */

</script>



  <!--  <div class="fs14 tanchuang-pannel " role="main"> -->
   <div class="" role="main">
   
  		<span style="position: absolute;right:10px;display:block"><a href="javascript:RelateUtil.close();" class="btn btn-default">&times;</a></span>
   
        <div class="row">
            <div class="page-content">
            	<h4><strong>公司信息</strong></h4>
                
                <!-- 搜索条件区域 -->
                <form id="relateCompanyForm">
                	<input type="hidden" name="releaseCityNo" id="releaseCityNo" value="${releaseCityNo}">
					<input type="hidden" name="releaseCityNoflag" id="releaseCityNoflag" value="${releaseCityNoflag}">
					<input type="hidden" name="oldCityNo" id="oldCityNo" value="${oldCityNo}">
					<input type="hidden" name="keFuOrderCity" id="keFuOrderCity" value="${keFuOrderCity}">
		
                    <!-- 默认排序字段、排序类型 -->
                    <input type="hidden" id="orderName" name="orderName" value="id" >
    				<input type="hidden" id="orderType" name="orderType" value="DESC" >
    				<input type="hidden" id="type" name="type"  value="${type}">
    				<input type="hidden" id="oldCompanyId" name="oldCompanyId"  value="${oldCompanyId}">
	                <ul class="list-inline form-inline pdb20">
	                    <li>
	                    	<div class="form-group">
                                <label class="">公司编号</label>
                                <input type="text" class="form-control" id="companyNo" name="companyNo">
                            </div>
                            <div class="form-group">
                                <label class="">公司名称</label>
                                <input type="text" class="form-control" id="companyName" name="companyName">
                            </div>
	                        <button type="button" class="btn btn-primary" onclick="javascript:RelateCompany.search();">搜索</button>
	                    </li>
	                </ul>
                
               
               <!-- 动态加载区域块  begin -->
                              <!-- 异步加载列表内容 -->
                <div id="load_content">
		           <div id="LoadCxt"></div>
		        </div>
               </form>
            </div>
            
        </div>
    </div>