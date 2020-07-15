<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;">
    <table class="table table-striped table-hover table-bordered">   
            <col style="width:100px">
             <col style="width:150px">
            <col style="width:90px">
            <col style="width:260px">
           
            <tr>
                <th>项目编号</th>
                <th>楼盘名称</th>
                <th>楼盘城市</th>
                <th>楼盘地址</th>
                <th>报备</th>
                <th>成销</th>
                <th>结佣</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${contentlist}" var="list">
				<tr class="J_eatateItem" data-hidenumberflg="0">
				        <td><a href="${ctx}/estate/otherReport/${list.id}">${list.projectNo}</a></td>
						<td style="text-align:left;" title="${list.estateNm}" class="text-overflow">
                            ${list.estateNm}
					    </td>
						<td>
                            <c:if test="${list.estatePosition eq 0}">${list.realityCityNm}</c:if>
                            <c:if test="${list.estatePosition eq 1}">${list.countryNm}</c:if>
                        </td>
						<td style="text-align:left;" title="${list.realityCityNm}${list.districtNm}${list.address}" class="text-overflow">
                    	${list.realityCityNm}${list.districtNm}${list.address}
                    </td>
                        <td>${list.bbCount}</td>
                        <td>${list.cxCount}</td>
                        <td>${list.jyCount}</td>
					<td>
						<a href="${ctx}/qtProject/qSceneRecognition/${list.estateId}">查看</a>
                        <!-- 只有审核状态为通过,并且发布过[发布时间不为Null]的才能报备-->
                        <c:if test="${list.auditStatus eq 12903 && list.releaseDt ne null}">
                            <a href="${ctx}/qtReport/toQtReport/${list.id}">报备</a>
                        </c:if>
					</td>
				</tr>
			</c:forEach> 
    </table>
</div>

${pageInfo.html}

<c:if test="${fn:length(contentlist) le 0}">
	<div class="nodata">
		<i class="icon-hdd"></i>
		<p>暂无数据...</p>
	</div>
</c:if>
