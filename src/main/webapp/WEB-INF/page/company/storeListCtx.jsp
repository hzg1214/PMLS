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
             <th style="width:100px">合作模式</th>
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
                 		<td style="text-align:center;">${storelist.contractTypeName}</td>
                 		<td>
                 			<%-- <a href='${ctx}/store/${storelist.storeId}'>查看</a> --%>
                 			<a href="javascript:void(0)" onclick="contractGotoStore('${ctx}/store/${storelist.storeId}','${ctx}/store')">查看</a>
                 			<%-- <c:if test="${userCreate eq userInfo.userId}">
                 				<a href='javascript:void(0)' onclick='deleteCompanyStore(${storelist.storeId},${companyId})'>删除</a>
                 			</c:if> --%>
                 			<!-- 合同過期, 未签，作废，终止情况可以取消关联 -->
                 			<shiro:hasPermission name="/companys:COMPANY_CL_STORE">  
	                 			<c:if test="${storelist.dateLifeEnd < nowDate || storelist.contractStatus eq null || storelist.contractStatus eq 10405 || storelist.contractStatus eq 10406}">
	                 				<!-- 已取消关联的不再显示 -->
	                 				<c:if test="${storelist.deleteFlag eq false}">
	                 					<a href="javascript:void(0)" onclick="cancelCompanyStore(${storelist.storeId},${companyId},${empty storelist.fangyouId},this);">取消关联</a>
	                 				</c:if>
	                 			</c:if>
                 			</shiro:hasPermission> 
                 		</td>
                 	</tr>
		</c:forEach> 

     </table>
	${pageInfo.html}
</div>
<c:if test="${fn:length(info) le 0}" >
	<div class="nodata"><i class="icon-hdd"></i><p>暂无数据...</p></div>
</c:if>