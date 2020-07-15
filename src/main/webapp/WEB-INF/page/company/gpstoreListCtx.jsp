<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div>
	 <table  id="relateStoreTableId"  class="table table-striped table-bordered table-hover">
		<tr>
			 <th style="width:120px">门店编号</th>
			 <th style="width:150px">门店名称</th>
             <th>门店地址</th>
             <th style="width:180px">录入日期</th>
             <th style="width:130px">操作</th>
		</tr>

		<c:forEach items="${info}" var="storelist">
                 	<tr>
                 		<td style='display:none'><input name='storeIds'  id='storeIds${storelist.storeId}'   type='hidden' value='${storelist.storeId}'></td>
                 		<td style="text-align:center;">
                 			<a href="javascript:void(0)" onclick="contractGotoStore('${ctx}/store/${storelist.storeId}','${ctx}/store')">
                 				${storelist.storeNo}
                 			</a>		
                 		</td>
                 		<td style="text-align:center;" title="${storelist.name}">
                 			${fn:substring(storelist.name, 0, 20)}
                 			<c:if test="${fn:length(storelist.name) >= '20'}">
							...
							</c:if>
                 		</td>
                 		<td style="text-align:center;" title="${storelist.addressDetail}">
                 			${fn:substring(storelist.addressDetail, 0, 30)}
                 			<c:if test="${fn:length(storelist.addressDetail) >= '30'}">
							...
							</c:if>
                 		</td>
                 		<td style="text-align:center;">${storelist.dateCreate}</td>
						<td>
							<c:if test="${ storelist.contractStatus eq null || storelist.contractStatus eq 10405 || storelist.contractStatus eq 10406 || storelist.storeState eq 2}">
							<!-- 已取消关联的不再显示 -->
                 			<!-- 合同過期, 未签，作废，终止情况可以取消关联 -->
                 			<shiro:hasPermission name="/companys:CY_GP_STORE">
                 			  <a href="javascript:void(0)" onclick="cancelgpCompanyStore(${storelist.storeId},${companyId},this);">取消关联</a>
	                 		</shiro:hasPermission>
							</c:if>
                 		</td>
                 	</tr>
		</c:forEach> 

     </table>
	${pageInfo.html}
</div>
<c:if test="${fn:length(info) le 0}" >
	<div class="nodata"><i class="icon-hdd"></i><p>暂无数据...</p></div>
</c:if>