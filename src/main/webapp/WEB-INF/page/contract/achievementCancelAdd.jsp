<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
<script type="text/javascript">
 
   $(function(){
	   
	   var checkAll = true;
		$("input[name='isCancel']").each(function(index,val){
			
			if(!val.checked){
				checkAll = false;
			}
			
		});
		
		$("input[name='canceled']").each(function(index,val){
			
			if(!val.checked){
				checkAll = false;
			}
			
		});
	
		if(checkAll){
			$("input[name='isCancelAll']").prop("checked",true);
		}else{
			$("input[name='isCancelAll']").prop("checked",false);
		}
   });

</script>
<body>
	<h4>
		<label>业绩撤销</label> <span> <a href="javascript:void(0)"
			style="float:right;" onclick="AchievementCancel.closeCancel();">关闭</a>
		</span>
	</h4>
	<HR>
<form id = "AchievementCancelForm" >	
	<div class="list-inline half form-inline" >
		<li>
			<label>所属公司名称 ：${companyName}</label>
			<input type="hidden" id = "companyName" value="${companyName}">
			<input type="hidden" id = "isCombine" value="${oaOperator.isCombine}">
			<input type="hidden" id="contractTypeName" value="${contractTypeStr}">
			<input type="hidden" id="contractId" value="${contractId}">
			<input type="hidden" id="contractStatus" value="${contractStatus}">
		</li>
		<%-- <li>
			<c:if test="${oaOperator.isCombine eq true}">
				<label><i>* </i>事业部区域 :</label>
		         <select class="form-control" title="" id="busNo" name="busNo" >
		         	<option value="">请选择事业部区域</option>
		         	<c:if test="${not empty busMap}">
		              <c:forEach items="${busMap}" var="bus">
		                  <option value="${bus.key}">${bus.value}</option>
		              </c:forEach>
		         	</c:if>
		        </select>
	        </c:if>
		</li> --%>
	</div>
	<P><i>* </i>选择撤销门店</P>
	<table id="splitPopupTable"
		class="table table-striped table-hover table-bordered">
		<tr>
			<th><input type="checkbox" id="isCancelAll" name="isCancelAll" onclick="AchievementCancel.selectAll(this);">全选</th>
			<th>门店编号</th>
			<th>门店名称</th>
			<th>所在区域</th>
			<th>门店地址</th>
			<th>合作模式</th>
			<th>装修进度</th>
			<th>装修日期</th>
		</tr>
		<c:if test="${storeList != null}">
			<c:forEach items="${storeList}" var="store">
				<tr>
					<td><input type="checkbox" id="isCancel" 
						value="${store.storeId}" businessStatus="${store.businessStatus}" storeNo="${store.storeNo}" approveState = "${store.approveState}"
						<c:if test="${store.isCancel eq '17202' || store.isCancel eq '17203'}">name="canceled" disabled="true"</c:if>
						<c:if test="${store.changeCompany eq '0' && store.approveState eq '1'}">name="canceled" disabled="true"</c:if>
						<c:if test="${store.changeCompany eq '1' && store.approveState eq '1'}">name="canceled" disabled="true"</c:if>
					   	<c:if test="${store.changeCompany eq '1' && store.approveState eq '2'}">name="canceled" disabled="true"</c:if>
						<c:if test="${store.changeType eq '17004' && store.cancelFlag eq '0' && store.approveState ne '2'}">name="canceled" disabled="true"</c:if>
						<c:if test="${store.changeType eq '17003' && store.cancelFlag eq '0' && store.approveState ne '2'}">name="canceled" disabled="true"</c:if>
						<c:if test="${store.isCancel eq '17201' || empty store.isCancel}"> name="isCancel" onclick="AchievementCancel.selectThis(this);"</c:if>>
					</td>
					<td>${store.storeNo}</td>
					<td>${store.storeName}</td>
					<td>${store.districtName}</td>
					<td>${store.address}</td>
					<td>${store.contractTypeStr}</td>
					<td><c:choose>
						<c:when test="${store.decorationStatusStr eq '-'}">--</c:when>
						<c:otherwise>${store.decorationStatusStr}</c:otherwise>
					</c:choose></td>
					<td><c:choose>
						<c:when test="${store.updateDateStr eq '-'}">--</c:when>
						<c:otherwise>${store.updateDateStr}</c:otherwise>
					</c:choose></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
		<div>
			<p style="color: red;"><i>* </i> 注：灰色不可选代表门店已经撤销或者正在撤销中或做过乙类转甲类(签约主体变更)的操作</p>
		</div>
		<div>
			<p><i>* </i>撤销原因</p>
			<textarea id="cancelReason" name="cancelReason" maxlength="100" notnull ="true"></textarea>
		</div>
		<p id="warningSpan" style="color: red; font-weight: bold; "></p>
</form>
</body>
