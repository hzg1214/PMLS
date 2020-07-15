<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>


<script>

$(function() {

	// 初始化查询
	FrameRelateCompany.initList();
});

/** ***************************************************** */

</script>



  <!--  <div class="fs14 tanchuang-pannel " role="main"> -->
   <div class="" role="main">
   
  		<span style="position: absolute;right:10px;display:block"><a href="javascript:FrameRelateCompany.close();" class="btn btn-default">&times;</a></span>
   
        <div class="row">
            <div class="page-content">
            	<h4><strong>公司信息</strong></h4>
                
                <!-- 搜索条件区域 -->
                <form id="relateCompanyForm">
                    <!-- 默认排序字段、排序类型 -->
                    <input type="hidden" id="orderName" name="orderName" value="id" >
    				<input type="hidden" id="orderType" name="orderType" value="DESC" >
    				<input type="hidden" id="type" name="cityNm"  value="${cityNm}">
    				<input type="hidden" id="type" name="districtNm"  value="${districtNm}">
    				<input type="hidden" id="type" name="fid"  value="${fid}">
    				<input type="hidden" id="agreementType" name="agreementType"  value="${agreementType}">
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
	                        <button type="button" class="btn btn-primary" onclick="javascript:FrameRelateCompany.search();">搜索</button>
	                    </li>
	                </ul>
                
               <div id="errorMsg" style="color: red;margin-bottom: 5px;visibility: hidden;height: 20px;"></div>
               <!-- 异步加载列表内容 -->
                <div id="load_content">
		           <div id="LoadCxt"></div>
		        </div>
               </form>
            </div>
            
        </div>
    </div>