<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;">
    <table class="table table-striped table-hover table-bordered">
            <tr>
		        <th>文件名</th>
			    <th>来源</th>
			    <th>状态</th>
			    <th>下载</th>
			    <th>创建时间</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${reportlist}" var="list">
				<tr class="J_eatateItem" data-hidenumberflg="0">
					<td style="width:350px;">${list.fileName}</td>					
					<td>${list.exportTypeZh}</td>
					<td>${list.statusZh}</td>
					<c:if test="${list.status == 2}">
					   <td style="width:70px;">
							<a class="butt_blue" href="javascript:void(0);" onclick="doUpload('${list.fileName}','${list.downloadurl}')">下载</a>
					     </td> 
					</c:if>
					<c:if test="${list.status != 2}">
					    <td></td>
					</c:if>						
					<td>${list.createtime}</td>
					<c:if test="${list.status == 2}">
					   <td style="width:70px;">
					       <a class="butt_blue" href="javascript:void(0);" onclick="doDelete('${list.id}','${list.downloadurl}')">删除</a>
					     </td> 
					</c:if>
					<c:if test="${list.status != 2}">
					    <td></td>
					</c:if>		
				</tr>
			</c:forEach> 
    </table>
</div>

${pageInfo.html}

<c:if test="${fn:length(reportlist) le 0}">
	<div class="nodata">
		<i class="icon-hdd"></i>
		<p>暂无数据...</p>
	</div>
</c:if>
