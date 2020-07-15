<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="" style="height: auto;">
<table class="table table-striped table-hover table-bordered">
    <tr>
        <th style="width: 50px;"><input type="checkbox" onclick="swapCheck()" /></th>
        <th style="width: 110px;">门店编号</th>
        <th style="width: 120px;">门店名称</th>
        <th style="width:190px;">门店地址</th>
        <th style="width:90px;">合作模式</th>
        <th style="width:90px;">合同状态</th>
        <th style="width: 105px;">门店所属中心</th>
        <th style="width:85px;">维护人</th>
        <th style="width: 115px;">维护人所属中心</th>
        <th >备注</th>
    </tr>

    <c:forEach items="${contentlist}" var="list">
        <tr>
            <td><input type="checkbox" value="${list.id}" class="selectReport" />
            	 <input type="hidden" id="storeCenterId" value="${list.storeCenterId}">
            </td>
            <td>
            	<c:choose>
					<c:when test="${list.inStatus eq 1075 || (list.centername).indexOf(list.groupName) eq '-1'  || list.sortindex eq 4}">        		
							 <font color="red">${list.storeNo}</font>		
					</c:when>
				
					<c:otherwise>
							${list.storeNo}		 
					</c:otherwise>
				</c:choose>	
            </td>
            <td style="text-align:left;" title="${list.name}">
	            <c:choose>
					<c:when test="${list.inStatus eq 1075 || (list.centername).indexOf(list.groupName) eq '-1'  || list.sortindex eq 4}">        		
							 <font color="red">
							 	 ${fn:substring(list.name, 0, 6)}
										<c:if test="${fn:length(list.name) > '6'}">
											...
										</c:if>
							 </font>		
					</c:when>
				
					<c:otherwise>
							 ${fn:substring(list.name, 0, 6)}
										<c:if test="${fn:length(list.name) > '6'}">
											...
										</c:if>		 
					</c:otherwise>
				</c:choose>	
			</td>
            <td style="text-align:left;" title="${list.addressDetail}">
			    <c:choose>
					<c:when test="${list.inStatus eq 1075 || (list.centername).indexOf(list.groupName) eq '-1'  || list.sortindex eq 4}">        		
							 <font color="red">
							 ${fn:substring(list.addressDetail, 0, 10)}
										<c:if test="${fn:length(list.addressDetail) > '10'}">
											...
										</c:if>
							 </font>		
					</c:when>
				
					<c:otherwise>
							 ${fn:substring(list.addressDetail, 0, 10)}
										<c:if test="${fn:length(list.addressDetail) > '10'}">
											...
										</c:if>	 
					</c:otherwise>
				</c:choose>	
			</td>
            <td>
            	<c:choose>
					<c:when test="${list.inStatus eq 1075 || (list.centername).indexOf(list.groupName) eq '-1'  || list.sortindex eq 4}">        		
							 <font color="red">${list.contractTypeName}</font>		
					</c:when>
				
					<c:otherwise>
							${list.contractTypeName}		 
					</c:otherwise>
				</c:choose>	
			</td>
            <td>
	            <c:choose>
					<c:when test="${list.inStatus eq 1075 || (list.centername).indexOf(list.groupName) eq '-1'  || list.sortindex eq 4}">        		
							 <font color="red">${list.contractStatusName}</font>		
					</c:when>
					<c:otherwise>
							${list.contractStatusName}		 
					</c:otherwise>
				</c:choose>	
			</td>
            <td>
            	<c:choose>
				<c:when test="${list.inStatus eq 1075 || (list.centername).indexOf(list.groupName) eq '-1'  || list.sortindex eq 4}">        		
						 <font color="red">${list.groupName}</font>		
				</c:when>
			
				<c:otherwise>
						${list.groupName}	
				</c:otherwise>
				</c:choose>	
			</td>
            <td>
	            <c:choose>
					<c:when test="${list.inStatus eq 1075 || (list.centername).indexOf(list.groupName) eq '-1'  || list.sortindex eq 4}">        		
							 <font color="red">${list.maintainerName}</font>		
					</c:when>
				
					<c:otherwise>
								${list.maintainerName}	 
					</c:otherwise>
				</c:choose>	
			</td>
           <td  title="${list.centername}">
           				<c:choose>
							<c:when test="${list.inStatus eq 1075 || (list.centername).indexOf(list.groupName) eq '-1'  || list.sortindex eq 4}">        		
									 <font color="red">
									 	${fn:substring(list.centername, 0, 5)}
										<c:if test="${fn:length(list.centername) > '5'}">
											...
										</c:if>
									 </font>		
							</c:when>
							<c:otherwise>
								${fn:substring(list.centername, 0, 10)}
										<c:if test="${fn:length(list.centername) > '10'}">
											...
										</c:if>			 
							</c:otherwise>
						</c:choose>	
			 </td>
           <td  title="${list.titleLabel}">
           				<c:choose>
							<c:when test="${list.inStatus eq 1075 || (list.centername).indexOf(list.groupName) eq '-1' || list.sortindex eq 4}">        		
									 <font color="red">
									 	${fn:substring(list.titleLabel, 0, 13)}
										<c:if test="${fn:length(list.titleLabel) > '13'}">
											...
										</c:if>
									 </font>		
							</c:when>
							<c:otherwise>
								${fn:substring(list.titleLabel, 0, 13)}
										<c:if test="${fn:length(list.titleLabel) > '13'}">
											...
										</c:if>			 
							</c:otherwise>
						</c:choose>	
			 </td>
        </tr>
    </c:forEach>
</table>
</div>
${pageInfo.html}

<c:if test="${fn:length(contentlist) le 0}">
    <div class="nodata"><i class="icon-hdd"></i>
        <p>暂无数据...</p></div>
</c:if>

<!-- 遮罩层(辅助) -->
<div class="shade"></div>