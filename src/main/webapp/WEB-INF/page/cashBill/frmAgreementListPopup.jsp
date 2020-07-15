<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>


<script>

$(function() {

	// 初始化查询
	FrmAgreementList.initList();
});

/** ***************************************************** */

</script>



   <div class="" role="main">
   
  		<span style="position: absolute;right:10px;display:block"><a href="javascript:cashBillView.frmAgreementdialog.close();" class="btn btn-default">&times;</a></span>
   
        <div class="row">
            <div class="page-content">
            	<h4><strong>选择框架协议</strong></h4>
                
                <!-- 搜索条件区域 -->
                <form id="frmAgreementListForm">
                    <!-- 默认排序字段、排序类型 -->
                    <input type="hidden" id="orderName" name="orderName" value="frameOaNo" >
    				<input type="hidden" id="orderType" name="orderType" value="DESC" >
    				<input type="hidden" id="accountProjectCode" name="accountProjectCode"  value="${accountProjectCode}">
	                <ul class="list-inline form-inline pdb20">
	                    <li style="padding-left: 40px;padding-top: 10px;">
	                    	<div class="form-group">
                                <label class="">框架协议编号</label>
                                <input type="text" class="form-control" id="frameOaNo" name="frameOaNo">
                            </div>
                            <div class="form-group">
                                <label class="">供应商名称</label>
                                <input type="text" class="form-control" id="vendorName" name="vendorName">
                            </div>
	                        <button type="button" class="btn btn-primary" onclick="javascript:FrmAgreementList.search();">搜索</button>
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