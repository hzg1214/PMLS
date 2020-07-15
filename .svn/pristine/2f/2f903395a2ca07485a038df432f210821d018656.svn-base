<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<script>
$(function() {
	// 初始化查询
	RelateStores.initList();
});
</script>
<div class="">
	<span class="" style="float:right"><a
		href="javascript:RelateUtil.close();" class="btn btn-default">&times;</a></span>
	<div class="row">
		<div class="page-content" style="">
			<h4>
				<strong>门店信息</strong>
			</h4>
			<!-- 搜索条件区域 -->
			<form id="relateStoresForm">
				<!-- 默认排序字段、排序类型 -->
				<input type="hidden" id="orderName" name="orderName" value="id">
				<input type="hidden" id="orderType" name="orderType" value="DESC">
				<input type="hidden" id="type" name="type" value="${type}">
				<input type="hidden" id="storeStatus" name="storeStatus"
					value="${storeStatus}"> <input type="hidden" id="cityNo"
					name="cityNo" value="${userInfo.cityNo}"> <input
					type="hidden" name="districtsNo" value="${districtsNo}">
				<ul class="list-inline form-inline pdb20">
					<li>
						<div class="form-group">
							<input type="text" class="form-control w300" id="searchKey"
								name="searchKey" placeholder="门店编号、门店名称或门店地址" value="">
							<button type="button" class="btn btn-primary"
								onclick="javascript:RelateStores.search();">搜索</button>
						</div>
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