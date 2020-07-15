<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>


<script>

$(function() {

	// 初始化查询
	ReceiveBank.initList();
});

/** ***************************************************** */

</script>



   <div class="" role="main">
   
  		<span style="position: absolute;right:10px;display:block"><a href="javascript:cashBillView.receiveBankdialog.close();" class="btn btn-default">&times;</a></span>
   
        <div class="row">
            <div class="page-content">
            	<h4><strong>选择银行</strong></h4>
                
                <!-- 搜索条件区域 -->
                <form id="receiveBankForm">
                    <!-- 默认排序字段、排序类型 -->
                    <input type="hidden" id="orderName" name="orderName" value="ROW_ID" >
    				<input type="hidden" id="orderType" name="orderType" value="DESC" >
    				<input type="hidden" id="oldCompanyId" name="vendorId"  value="${vendorId}">
	                <ul class="list-inline form-inline pdb20">
	                    <li style="padding-left: 40px;padding-top: 10px;">
	                    	<div class="form-group">
                                <label class="">银行名称</label>
                                <input type="text" class="form-control" id="bankName" name="bankName">
                            </div>
<!--                             <div class="form-group"> -->
<!--                                 <label class="">框架协议名称</label> -->
<!--                                 <input type="text" class="form-control" id="companyName" name="companyName"> -->
<!--                             </div> -->
	                        <button type="button" class="btn btn-primary" onclick="javascript:ReceiveBank.search();">搜索</button>
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