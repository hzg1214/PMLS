<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<body>

	<div class="container">
		<form id = "contractViewForm" >
		  <!-- Add by WangLei 2017/04/07 End  -->
	            <div class="content">
	                <div class="page-content">
						<h4>
		                	<strong>合同详情</strong>
		                	<a href="javascript:Dialog.dialog.close();" class="btn btn-default">&times;</a>
		                	<c:choose>
				                <c:when test="${contractInfo.contract.contractType eq 10306}">
				                	
				                </c:when>
				                <c:otherwise>
									<!-- 非C版 且 经办人才能对 (处于审核中、审核不同过的) 的合同 有 提交审核和获取审核状态权限 -->
									<c:if test="${not empty oaOperator.operCode && contractInfo.contract.contractType ne 10303}">
									  <!-- 处于审核中、审核不同过的 -->
										<c:if test="${(contractInfo.contract.contractStatus eq 10401&&(empty contractInfo.contract.submitOAStatus || contractInfo.contract.submitOAStatus eq 21201 || contractInfo.contract.submitOAStatus eq 21204)) || (contractInfo.contract.contractStatus eq 10404 && contractInfo.contract.submitOAStatus ne 21202)}">
											<a href="javascript:Contract.toAudit('${contractInfo.contract.id}','${oaOperator.isCombine}');"
											   class="btn btn-primary" style="margin-right:10px;">提交审核</a>
										</c:if>
									</c:if>
				                  </c:otherwise>
			                 </c:choose>
		                </h4>
					</div>
					<table class="table-sammary">
						<col style="width:130px;">
						<col style="width:atuo;">
						<col style="width:130px;">
						<col style="width:atuo;">
						<tr>
							<td class="talabel">合同编号：</td>
							<td>${contractInfo.contract.contractNo}</td>
							<td class="talabel">甲方公司：</td>
							<td>${contractInfo.contract.partyB}</td>
						</tr>
						<tr>
							<td class="talabel">统一社会信用代码：</td>
							<td>${contractInfo.contract.registrId}</td>
							<td class="talabel">法定代表人：</td>
	                   		<td>${contractInfo.contract.lealPerson}</td>
						</tr>
						 <!-- Mod 2017/04/05 cning start -->
						  <%--  <ul class="list-inline half form-inline">
	                    <li><div class="form-group"><label class="fw-normal w140 text-right">法定代表人：</label>${contractInfo.contract.lealPerson}</div></li>
	                    <li><div class="form-group"><label class="fw-normal w140 text-right">合同状态：</label>${contractInfo.contract.contractStatusName}</div></li>
	                </ul>
	               
	                <ul class="list-inline half form-inline">
	                    <li><div class="form-group"><label class="fw-normal w140 text-right">公司注册地址：</label>${contractInfo.contract.partyAddressDetail}</div></li>  
	                    <li><div class="form-group"><label class="fw-normal w140 text-right">协议书编号：</label>${contractInfo.contract.agreementNo}</div></li>
	                </ul> --%>
	                
	                <tr>	                    
	                    <td class="talabel">公司注册地址：</td>
	                    <td colspan="3">${contractInfo.contract.partyAddressDetail}</td>  
	                </tr>
	                <tr>
	                	<td class="talabel">合同类别：
	                	<!-- Add By NingChao 2017/04/07 Start -->
	                	<input type="hidden" id="originalContractDistinction" value="${contractInfo.contract.originalContractdistinction}"></td>
	                	<!-- Add By NingChao 2017/04/07 End -->
	                	<c:if test="${contractInfo.contract.originalContractdistinction eq 18601}">
	                		<td>新签</td>
	                	</c:if>
	                	<c:if test="${contractInfo.contract.originalContractdistinction eq 18602}">
	                		<td>续签</td>
	                	</c:if>
	                	
	                	<c:if test="${contractInfo.contract.originalContractdistinction eq 18602}">
	                		 <td class="talabel">原合同编号：</td>
	                		 <td><a href="${ctx}/contract/${fn:trim(contractInfo.contract.originalId)}/${fn:trim(contractStatus)}">${contractInfo.contract.originalContractNo}</a></td>
	                	</c:if>	                   
	                </tr> 
	                <tr>
	                	<td class="talabel">协议书编号：</td>
	                	<td>${contractInfo.contract.agreementNo}</td> 
	                	<td class="talabel">合作模式：</td>
						<td>${contractInfo.contract.contractTypeName}</td>
	                </tr>
	               <tr>
	               		<td class="talabel">甲方授权代表：</td>
						<td>${contractInfo.contract.authRepresentative}</td>
						<td class="talabel">联系方式：</td>
						<td>${contractInfo.contract.agentContact}</td>
	               </tr>
	                <tr>
						<td class="talabel">合同生效日期：</td>
						<td>${sdk:ymd2(contractInfo.contract.dateLifeStart)}</td>
						<td class="talabel">合同到期日期：</td>
						<td>${sdk:ymd2(contractInfo.contract.dateLifeEnd)}</td>
					</tr>
					<tr>
						<td class="talabel">每店保证金额：</td>
						<td>${contractInfo.contract.depositFee}元</td>
						<td class="talabel">合作门店数：</td>
						<td>${contractInfo.contract.storeNum}</td>
					</tr>
					<tr>
						<td class="talabel">总保证金：</td>
						<td>${contractInfo.contract.totleDepositFee}元</td>
						<td class="talabel">违约金金额：</td>
						<td>${contractInfo.contract.penaltyFee}元</td>
						
					</tr>
					<tr>
						<td class="talabel">客户公司账号：</td>
						<td>${contractInfo.contract.companyBankNo}</td>
					</tr>
					<tr>
						<td class="talabel">开户银行：</td>
						<td>${contractInfo.contract.bankAccount}</td>
						<td class="talabel">开户名：</td>
						<td>${contractInfo.contract.accountName}</td>
					</tr>	
					<tr>
						<td class="talabel">甲方收件人：</td>
						<td>${contractInfo.contract.recipients}</td>
						<td class="talabel">甲方收件地址：</td>
						<td>${contractInfo.contract.recipientsAddressDetail}</td>
					</tr>
					<tr>							
						<td class="talabel">OA审批流程类别：</td>
						<td>${contractInfo.contract.oaApproveTypeName}</td>
						<td class="talabel">是否乙转甲新签：</td>
						<td>
							<c:choose>
								<c:when test="${empty contractInfo.contract.contractB2A}">-</c:when>
								<c:otherwise>
									${contractInfo.contract.contractB2AName}
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="talabel">到账保证金总额：</td>
						<td>
							<c:choose>
								<c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
									-
								</c:when>
								<c:otherwise>
									${contractInfo.contract.arrivalDepositFee}
								</c:otherwise>
							</c:choose>
						</td>
						<td class="talabel">保证金到账状态：</td>
		                <td>
		                	<c:choose>
								<c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
									-
								</c:when>
								<c:otherwise>
									${contractInfo.contract.depositFeeStateName}
								</c:otherwise>
							</c:choose>
		                </td>
					</tr>
					<tr>	
						<td class="talabel">保证金退款总额：</td>
						<td>
							<c:choose>
								<c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
									-
								</c:when>
								<c:when test="${empty contractInfo.contract.totalRefundAmount}">-</c:when>
								<c:otherwise>
									${contractInfo.contract.totalRefundAmount}
								</c:otherwise>
							</c:choose>
						</td>						
						<td class="talabel">保证金退款状态：</td>
						<td>
							<c:choose>
								<c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
									-
								</c:when>
								<c:when test="${empty contractInfo.contract.refundStateName}">-</c:when>
								<c:otherwise>
									${contractInfo.contract.refundStateName}
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="talabel">业绩归属拓展人：</td>
						<td>${contractInfo.contract.expandingPersonnel}</td>
						<td class="talabel">业绩归属中心：</td>
						<td>${contractInfo.contract.centerName}</td>
					</tr>
					
					<tr>
						<%-- <c:if test="${not empty contractInfo.contract.pfmcAtbtDepmtName}">
		                    <td class="talabel">业绩归属部门：</td>
		                    <td>${contractInfo.contract.pfmcAtbtDepmtName}</td>
		                </c:if> --%>		                
						<td class="talabel">合同审核状态：</td>
	                    <td>${contractInfo.contract.contractStatusName}</td> 
		                <c:if test="${not empty contractInfo.contract.performDate}">
							<td class="talabel">审核通过时间：</td>
		                	<td>${sdk:ymd2(contractInfo.contract.performDate)}</td>
		                </c:if>
					</tr>
						<c:if test="${!(contractInfo.contract.contractStatus eq 10403 && contractInfo.contract.submitOAStatus eq 21201)}">
							<tr>
								<td class="talabel">提交OA状态：</td>
								<c:if test="${contractInfo.contract.submitOAStatus eq 21202||contractInfo.contract.submitOAStatus eq 21204}">
									<td style="color:red;">${contractInfo.contract.submitOAStatusName}</td>
								</c:if>
								<c:if test="${contractInfo.contract.submitOAStatus eq 21201||contractInfo.contract.submitOAStatus eq 21203}">
									<td>${contractInfo.contract.submitOAStatusName}</td>
								</c:if>
							</tr>
						</c:if>
					<%-- <tr>
						<td class="talabel">创建时间：</td>
						<td>${contractInfo.contract.dateCreate}</td>
						
					</tr>
					<tr>
						<td class="talabel">是否变更：</td>
		                <td>${contractInfo.contract.isChangedStr}</td>
		                
					</tr> --%>
					</table>
	                <div class="page-content">
	                	<h4>
	                		<strong>门店信息</strong>
	                	</h4>
	                </div>
					<table id="relateStoreTableId" class="table table-striped table-hover table-bordered">
	                	<tr>
	                		<th>门店编号</th>
			                <th>门店名称</th>
			                <!-- <th>所在区域</th> -->
			                <th>门店地址</th>
			                <!-- <th>创建日期</th> -->
			                <th>门店资质等级</th>
			                <th>签约状态</th>
			                <th>是否到账</th>
			                <th>到账日期</th>
			                <th>退款金额</th>
			                <th>最后退款日期</th>
			                <th>退款状态</th>
			            </tr>
			            <c:forEach items="${contractInfo.storeDetails}" var="storelist">
		                   	<tr>
		                   	<!--Add By WangLei 2017/04/07 Start-->
		                   	<input type="hidden" name = "storeId" id="storeId" value = "${storelist.storeId }">
		                   	<!--Add By WangLei 2017/04/07 Start!-->
		                   		<td>${storelist.storeNo}</td>
		                   		<td>${storelist.name}</td>
		                   		<%-- <td>${storelist.districtName}</td> --%>
		                   		<td>${storelist.addressDetailContract}</td>
		                   		<%-- <td>${storelist.dateCreate}</td> --%>
		                   		<td>
		                   			<c:choose>
		                   			<c:when test="${empty storelist.abtypeStore}">-</c:when>
		                   			<c:when test="${storelist.abtypeStore eq 19901}">
										甲类
									</c:when>
									<c:when test="${storelist.abtypeStore eq 19902}">
									          乙类(${storelist.btypeStoreName})
									</c:when>
		                   			<c:otherwise>
		                   				-
		                   			</c:otherwise>
		                   			</c:choose>
		                   			
		                   			
		                   		
		                   		</td>
		                   		<td>${storelist.storeStateName}</td>
		                   		<td><c:choose>
		                   			<c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
										-
									</c:when>
		                   			<c:when test="${storelist.isArrivalAct == 1}">已到账</c:when>
		                   			<c:otherwise>
		                   				未到账
		                   			</c:otherwise>
		                   		</c:choose></td>
			                	<td><c:choose>
		                   			<c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
										-
									</c:when>
		                   			<c:when test="${empty storelist.dateArrivalAct}">-</c:when>
		                   			<c:otherwise>${sdk:ymd2(storelist.dateArrivalAct)}</c:otherwise>
		                   		</c:choose></td>
		                   		<td><c:choose>
		                   			<c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
										-
									</c:when>
									<c:when test="${empty storelist.refundAmount}">-</c:when>
		                   			<c:otherwise>${storelist.refundAmount}</c:otherwise>
		                   		</c:choose></td>
		                   		<td><c:choose>
		                   			<c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
										-
									</c:when>
									<c:when test="${empty storelist.refundDate}">-</c:when>
		                   			<c:otherwise>${sdk:ymd2(storelist.refundDate)}</c:otherwise>
		                   		</c:choose></td>
		                   		<td><c:choose>
		                   			<c:when test="${contractInfo.contract.contractType eq 10301 || contractInfo.contract.contractType eq 10303 || contractInfo.contract.contractType eq 10306}">
										-
									</c:when>
									<c:when test="${empty storelist.refundStateName}">-</c:when>
		                   			<c:otherwise>${storelist.refundStateName}</c:otherwise>
		                   		</c:choose></td>
		                   		<td style='display:none'><input name='storeIds'  id='storeIds${storelist.storeId}'   type='hidden' value='${storelist.storeId}'></td>
		                   	</tr>
						</c:forEach> 
	                </table>
					<table class="table-sammary">
						<col style="width:75px;">
						<col style="width:auto">
						<tr>
							<td class="talabel">合同备注：</td>
							<td>
								<span class="fw-normal" name="remark" id="remark" readOnly="readOnly" style="word-break:break-all;word-wrap: break-word;resize: none;">${contractInfo.contract.remark}</span>
							</td>
						</tr>
					</table>
					<div class="page-content">
	                	<h4>
	                		<strong>附件</strong>
	                	</h4>
	                </div>
	                <table class="table-sammary">
						<col style="width:145px;">
						<col style="width:auto">
						<tr>
							<!-- <td class="talabel">营业执照：</td> -->
							<td colspan="2">
						
<div class="" role="main">
        <div >
                <div class="pd10">
					<h4 class="thumb-title">
						营业执照
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractInfo.fileRecordMainBusiness }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileRecordMainBusiness}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="营业执照" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div> 		
							</td>
						</tr>
						<tr>
							<!-- <td class="talabel">法人身份证：</td> -->
							<td colspan="2">
										
<div class="" role="main">
        <div >
                <div class="pd10">
					<h4 class="thumb-title">
						法人身份证
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractInfo.fileRecordMainCard }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileRecordMainCard}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="法人身份证" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div> 
							</td>
						</tr>
						<tr>
							<td colspan="2">
													
<div class="" role="main">
        <div >
                <div class="pd10">
					<h4 class="thumb-title">
						合同照片
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractInfo.fileRecordMainPhoto }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileRecordMainPhoto}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="合同照片" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div> 
							</td>
						</tr>
						<tr>
							<!-- <td class="talabel">门店照片：</td> -->
							<td colspan="2">
													
<div class="" role="main">
        <div >
                <div class="pd10">
					<h4 class="thumb-title">
						门店照片
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractInfo.fileRecordMainStore }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileRecordMainStore}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="门店照片" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div> 
							</td>
						</tr>
						<tr>
							<td colspan="2">
														
<div class="" role="main">
        <div >
                <div class="pd10">
					<h4 class="thumb-title">
						房友确认单
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractInfo.fileRecordMainInstall }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileRecordMainInstall}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="房友确认单" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div> 
							</td>
						</tr>
						<tr>
							<td colspan="2">
														
<div class="" role="main">
        <div >
                <div class="pd10">
					<h4 class="thumb-title">
						其他
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty contractInfo.fileRecordMainOther }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${contractInfo.fileRecordMainOther}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.fileUrl}" class="thumbnail swipebox"  target="_blank"  >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="其他" src="${list.fileAbbrUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div> 
							</td>
						</tr>
					</table>
	            </div>
	     </form>
    </div>
</body>

</html>
