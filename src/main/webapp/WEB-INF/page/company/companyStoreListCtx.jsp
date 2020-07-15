<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div style="height: auto;width:750px;">
	 <table  id="relateStoreTableId"  class="table table-striped table-bordered table-hover">
		<col style="width:100px;">
		<col style="width:200px;">
		<col style="width:auto;">
		<col style="width:auto;">
		<tr>
			 <th></th>
			 <th>门店编号</th>
			 <th>门店名称</th>
             <th>门店地址</th>             
		</tr>

		<c:forEach items="${info}" var="storelist">
                 	<tr>
                 		<td style="width:100px"><input type="checkbox" id="chbStore" name="chbStore"/>
                 			<input name='storeId'  id='storeId'   type='hidden' value='${storelist.storeId}'>
                 		</td>
                 		<td style="text-align:center;">
                 			${storelist.storeNo}	
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
                 	</tr>
		</c:forEach> 

     </table>
	${pageInfo.html}
</div>
<c:if test="${fn:length(info) le 0}" >
	<div class="nodata"><i class="icon-hdd"></i><p>暂无数据...</p></div>
</c:if>