<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div class="" style="height: auto;">
    <table class="table table-striped table-hover table-bordered">
            <col style="width:140px;">
            <col style="width:110px;"><!-- style="text-align: left; display: inline-block;" -->
            <col style="width:200px;">
            <col style="width:80px;">
            <col style="width:80px;">
            <col style="width:100px;">
            <col style="width:100px;">
            <col style="width:80px;">
            <col style="width:80px;">
            <col style="width:80px;">
            <!-- <col style="width:100px;"> -->
            <tr>
                <th style="display:none"><input value="checkbox" type="checkbox"></th>
                <!--
                <th>合同编号</th>
                <th>合作模式</th>
                <th>协议书编号</th>
                <th>公司名称</th>
                <th>创建人</th>
                <th>创建日期</th>
                <th>合同审核状态</th>
				<th>合同生效状态</th>
                <th>是否变更</th>
                <th>保证金到账状态</th>
                <th>保证金退款状态</th>
                <th>未分账金额</th>
                <th>操作</th>
                -->
                <th>合同编号</th>
                <th>协议书编号</th>
                <th>公司名称</th>                
                <th>合作模式</th>
                <th>创建人</th>
                <th>创建日期</th>
                <th>审核状态</th>
				<th>生效状态</th>
               <!--  <th>是否变更</th> -->
                <th>保证金到账状态</th>
                <th>保证金退款状态</th>
               <!--  <th>未分账金额</th> -->
                <th>操作</th>
            </tr>
            <c:forEach items="${contentlist}" var="list">
				<tr class="J_eatateItem" data-hidenumberflg="0">
					<td style="display:none"><input type="checkbox" name="blackCheck" value="${list.id}"></td>
					<td><a href="${ctx}/contract/${list.id}/${list.contractStatus}">${list.contractNo}</a></td>
					<td style="text-align:center;" title="${list.agreementNo}" class="text-overflow">
						${list.agreementNo}
					</td>
					<td style="text-align:left;" title="${list.companyName}" class="text-overflow">
						${list.companyName}
					</td>
					<td>${list.contractTypeName}</td>					
					<td>${list.userNameCreate}</td>
					<td>${sdk:ymd2(list.dateCreate)}</td>
					<td>${list.contractStatusName}</td>
					<!--Add By tong 2017/04/07 Start  -->
					<td>
					  <c:choose>
						<c:when test= "${list.contractStatus eq '10403'}">
						  ${list.validName}
						</c:when>
						<c:otherwise>
						 -
						</c:otherwise>
					  </c:choose>
					</td>
					<!--Add By tong 2017/04/07 End -->
					<%-- <td>${list.isChangedStr}</td> --%>
					 <td>
						<c:choose>
							<c:when test="${list.contractType eq 10301 || list.contractType eq 10303 || list.contractType eq 10306}">
								-
							</c:when>
							<c:when test="${list.contractStatus eq 10403 || list.contractStatus eq 10406}">
							 ${list.depositFeeStatus}
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${list.contractStatus eq 10403 || list.contractStatus eq 10406}">
							 	<c:choose>
								 	<c:when test="${empty list.refundState}">
										未退款
									</c:when>
									<c:otherwise>
										${list.refundStateName}
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
						<%-- <c:choose>
							<c:when test="${empty list.refundState}">
								-
							</c:when>
							
							<c:otherwise>
								${list.refundStateName}
							</c:otherwise>
						</c:choose> --%>
					</td>
					<%-- <td>
						<c:choose>
							<c:when test="${list.contractType eq 10301 || list.contractType eq 10303 ||list.contractType eq 10306}">
								--
							</c:when>
							<c:otherwise>
							<fmt:formatNumber value="${ list.restSplitDepositFee}" pattern="#.##"/>
							</c:otherwise>
						</c:choose>
					</td> --%>
					<td>
						<a href="${ctx}/contract/${list.id}/${list.contractStatus}">查看</a> 
						
					<!-- 谁的合同谁才有编辑权限 -->
					<%-- <c:if test="${list.userCreate eq userInfo.userId}">
						<c:if test="${list.contractStatus eq 10401 || list.contractStatus eq 10404}">
							<a href="${ctx}/contract/u/${list.id}"> 编辑</a>
						</c:if>
					</c:if> --%>
					<c:if test="${list.contractStatus eq 10401 || list.contractStatus eq 10404}">
					
					      <!-- 经办人有编辑权限 -->
						  <c:if test="${not empty oaOperator.operCode}">
						      <!-- 流程编辑 -->
							  <a href="${ctx}/contract/u/${list.id}"> 编辑</a>
						  </c:if>
					  
					      <!-- 非经办人则自己有编辑权限和添加权限者 -->
						  <c:if test="${empty oaOperator.operCode}">
<!-- 							  <c:if test="${list.userCreate eq userInfo.userId}"> 
 							     流程编辑 
 								  <a href="${ctx}/contract/u/${list.id}"> 编辑</a> 
 							  </c:if>	 -->
							  <c:choose> 
								  <c:when test="${list.userCreate eq userInfo.userId}"> 
								  	<a href="${ctx}/contract/u/${list.id}"> 编辑</a>  
								  </c:when>  
								  <c:otherwise> 
								  	<shiro:hasPermission name="/contract:CT_EDIT">
								  		<a href="${ctx}/contract/u/${list.id}"> 编辑</a>
								  	</shiro:hasPermission>   
								  </c:otherwise> 
							  </c:choose> 
						  </c:if>	
					</c:if>
					
					
					
				<!-- Del By NingChao 2017/04/07 Start -->
					<!-- （审核中的合同）经办人才有提交审核和获取审核状态权限 -->
					<%-- <c:if test="${list.contractStatus eq 10402 && not empty oaOperator.operCode}">
					
					    <!-- 经办人自己的合同，及其下级 -->
						<c:forEach items="${userIdList}" var="idList">
							<c:if test="${list.userCreate eq idList}">
									<!-- 获取审核状态 -->
									<a href="javascript:Contract.getOAAuthStatus('${list.flowId}','${list.id}', '${list.companyId}');">获取审核状态</a>
									<!-- 流程撤销 -->
									<!-- <a href="javascript:Contract.undo();">撤销</a> -->
							</c:if>		
						</c:forEach>	
							
					</c:if> --%>
					<!-- （草签状态的合同、审核失败的合同）自己有作废权限，有作废权限的可以作废 -->
					<c:if test="${(list.contractStatus eq 10404) or (list.contractStatus eq 10401)}">
						<c:if test="${list.contractStatus eq 10404}">
							<a href="javascript:Contract.getOAOpinions('${list.flowId}');">原因</a>
						</c:if>
						<c:choose>
							<c:when test="${list.contractType eq 10301 || list.contractType eq 10303 || list.contractType eq 10306}">
								<c:choose>
									<c:when test="${list.userCreate eq userInfo.userId}">
										<a href="javascript:Contract.cancel('${list.id}');"> 作废</a>
									</c:when>
									<c:otherwise>
										<shiro:hasPermission name="/contract:CT_CANCEL">
											<a href="javascript:Contract.cancel('${list.id}');"> 作废</a>
										</shiro:hasPermission>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:if test="${list.isDeposit eq 1}">
									<c:choose>
										<c:when test="${list.userCreate eq userInfo.userId}">
											<a href="javascript:Contract.cancel('${list.id}');"> 作废</a>
										</c:when>
										<c:otherwise>
											<shiro:hasPermission name="/contract:CT_CANCEL">
												<a href="javascript:Contract.cancel('${list.id}');"> 作废</a>
											</shiro:hasPermission>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:if>
					<!-- 分账 显示条件 0.当合同作废后，分账按钮不显示; 1.保证金部分到账,已到账保证金之和<应收保证金总额; 2.合同含2家及以上门店; 3.未分账金额≥单店应收保证金金额;-->
					<%-- <c:if test="${list.contractStatus!=10405}"> --%>
					 <c:if test="${list.newDepositOpenFlag eq '0'}">
						 <shiro:hasPermission name="/contract:CONTRACT_FENZ"> 
							<c:if test="${list.depositFeeStateCode eq 17502}">
								<c:if test="${list.arrivalDepositFee < list.totleDepositFee}">
									<c:if test="${list.storeNum >= 2}">
										<c:if test="${list.restSplitDepositFee >= list.depositFee}">
											<a href="javascript:void(0)" onClick="Contract.goSplit('${list.id}')">分账</a>
										</c:if>
									</c:if>
								</c:if>
							</c:if>
						</shiro:hasPermission>
					</c:if>
					<%-- </c:if> --%>
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
