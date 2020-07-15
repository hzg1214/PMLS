<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<style type="text/css">
	.text-overflow {
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		width: 100%;
	}
</style>
<div>
	 <table  id="relateStoreTableId"  class="table table-striped table-bordered table-hover">
		<tr>
			 <th style="width:100px">序号</th>
			 <th>修改项目</th>
             <th style="width:200px">修改时间</th>
             <th style="width:200px">修改人(工号)</th>
             <th style="width:100px">操作</th>
		</tr>

		<c:forEach items="${logList}" var="log" varStatus="status">
                 	<tr>
                 		<td style="text-align:center;">
                 			${status.index + 1}
                 		</td>
                 		<td style="text-align:left;" title="${log.updateItem}" class="text-overflow">
                 			${log.updateItem}
                 		</td>
                 		<td style="text-align:center;">
                 			${log.updateTime}
                 		</td>                 		
                 		<td style="text-align:center;" >
                 			${log.updateUserName}
                 		</td>
                 		<td>
                 			<a href="javascript:void(0)" onclick="toLogDetail('${log.id}')">查看</a>
                 		</td>
                 	</tr>
		</c:forEach> 

     </table>
	${pageInfo.html}
</div>
<c:if test="${fn:length(logList) le 0}" >
	<div class="nodata"><i class="icon-hdd"></i><p>暂无数据...</p></div>
</c:if>