<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>


<script>
$(function() {
	// 初始化查询
	CompanyStore.initList();
});
</script>
	<div class="" role="main" style="width:750px;">
		<span class="" style="float:right"><a href="javascript:storeList.close();" class="btn btn-default">&times;</a></span>
		
		<div class="row">
				<div class="page-content">
					<h4>
						<strong>选择关联门店</strong>
					</h4>
					<!-- 搜索条件区域 -->
					<form id="companyStoreForm">
					 	<input type="hidden" name="companyId" id="companyId" value="${companyId}">
						<input type="hidden" name="userCreate" id="userCreate" value="${userCreate}">
 						<ul class="list-inline form-inline pdb20">
		                    <li>
		                    	<div class="form-group">
	                                <input type="text" class="form-control w300" id="name" name="name" placeholder="请输入 门店编号/门店名称/门店地址">
	                            </div>
	                            <!-- 默认排序字段、排序类型 -->
								<input type="hidden" name="orderName" value="dateCreate">
								<input type="hidden" name="orderType" value="DESC">
		                        <button type="button" class="btn btn-primary" onclick="javascript:CompanyStore.search();">搜索</button>
		                    </li>
	                	</ul>
						<!-- 异步加载列表内容 -->
						<div id="load_content">
							<div id="LoadCxtCompanyStore"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
