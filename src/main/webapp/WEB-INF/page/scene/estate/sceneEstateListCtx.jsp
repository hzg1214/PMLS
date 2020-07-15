<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;">
    <table class="table table-striped table-hover table-bordered">   
            <col style="width:100px">
             <col style="width:150px">
            <col style="width:90px">
            <col style="width:260px">
            <!-- <col style="width:60px">
            <col style="width:60px"> -->
           
            <tr>
                <%--<th>编号</th>--%>
                <th>项目编号</th>
                <!-- <th>业绩归属城市</th> -->
                <th>楼盘名称</th>
                <th>楼盘城市</th>
                <th>楼盘地址</th>
                <!-- <th>区域</th>
                <th>类型</th> -->
                <th>报备</th>
                <th>带看</th>
                <th>大定</th>
                <th>成销</th>
                <th>结佣</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${contentlist}" var="list">
				<tr class="J_eatateItem" data-hidenumberflg="0">
				        <%--<td><a href="${ctx}/estate/${list.id}">${list.estateId}</a></td>--%>
				        <td><a href="${ctx}/estate/${list.id}">${list.projectNo}</a></td>
						<%-- <td>${list.cityNoNm}</td> --%>
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
                        <%-- <td>${list.districtNm}</td>
                        <td>${list.estateTypeNm}</td> --%>
                        
                        <%--<td>${list.reportOk}</td> --%>
                        <td>${list.relationUnconfirm}</td>
                        <td>${list.pledgedUnconfirm}</td>
                        <td>${list.signUnconfirm}</td>
                        <td>${list.signConfirm}</td>
                        <td>${list.brokerageConfirm}</td>
					<td>
						<a href="${ctx}/sceneEstate/qSceneRecognition/${list.estateId}">查看</a>
                        <!-- 只有审核状态为通过,并且发布过[发布时间不为Null]的才能报备-->
                        <c:if test="${list.auditStatus eq 12903 && list.releaseDt ne null}">
                            <a href="${ctx}/estate/toReport1/${list.id}">报备</a>
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
