<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>添加下级</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .layui-input-block2{
/*             margin-left: 110px; */
            min-height: 36px;
            margin-right: 20px;
        }
        .labelWidth{
             width:105px !important;
        }
        
        .layui-anim-upbit {
            max-height: 150px !important; /* 适当调 300以下 合适就好 */
        }
    </style>
</head>
<script type="application/javascript">
    var parentId = '${parentId}';
    var parentName='${parentName}';
    var id='${id}';
    var developerBrandDto='${developerBrandDto}';
</script>
<body>
	<div  class="addDeveloperBrandPage">
		<div class="layui-card" style="height: 419px;">
			<div class="layui-card-body">
				<input type="hidden" name="partner" id="partner" value="${partner}">
				<input type="hidden" name="parentFlag" id="parentFlag" value="${parentFlag}">
				<input type="hidden" name="orgId" id="orgId" value="${developerBrandDto.orgId}">
				<input type="hidden" name="parentId" id="parentId" value="${developerBrandDto.parentId}">
				<fieldset class="layui-elem-field layui-field-title"
					style="margin-top: 20px;">
					<legend>上级合作方品牌：${parentName}</legend>
				</fieldset>
				<div class="layui-form" style="margin-top: 20px;">
					<div class="layui-form-item">
						<label class="layui-form-label labelWidth"><span class="redSpan">*</span>合作方品牌名称</label>
						<div class="layui-input-inline layui-input-block2">
							<input type="text" id="developerBrandName" style="width: 270px;"
								name="developerBrandName" lay-verify="required"
								autocomplete="off" placeholder="请输入"
								class="layui-input">
						</div>
					</div>
					<div class="layui-form-item " id="partnerDiv">
	                    <div class="layui-inline">
	                        <label class="layui-form-label labelWidth"><span class="redSpan">*</span>合作方类型</label>
	                        <div class="layui-input-inline" style="width: auto;">
	                            <c:forEach items="${partnerList}" var="list">
	                                <input type="radio" value="${list.dicCode}" title="${list.dicValue}" name="partner" lay-filter="partner"
                                       <c:if test="${list.dicCode eq developerBrandDto.partner}">checked</c:if>>
	                            </c:forEach>
	                        </div>
	                    </div>
	                </div>

					<div class="layui-form-item" id="bigCustomerFlagDiv">
						<div class="layui-inline">
							<label class="layui-form-label labelWidth"><span class="redSpan">*</span>是否大客户</label>
							<div class="layui-input-inline" style="width: auto;">
								<input type="radio" id="bigCustomerFlag1" name="bigCustomerFlag" value="22601" title="是" lay-filter="bigCustomerFlag"
									   <c:if test="${'22601' eq developerBrandDto.bigCustomerFlag}">checked</c:if>>
								<input type="radio" id="bigCustomerFlag2" name="bigCustomerFlag" value="22602" title="否" lay-filter="bigCustomerFlag"
									   <c:if test="${'22602' eq developerBrandDto.bigCustomerFlag}">checked</c:if>>
							</div>
						</div>
					</div>

					<div class="layui-form-item" id="bigCustomerDiv" style="display:none">
						<label class="layui-form-label labelWidth"><span class="redSpan">*</span>大客户简称</label>
						<div class="layui-input-inline layui-input-block2" style="width: 271px;!important;">
							<select id="bigCustomer" name="bigCustomer" lay-search=""  lay-verify="required" lay-filter="bigCustomer">
								<option value="" >请选择或搜索</option>
								<c:forEach items="${bigList}" var="list">
									<option value="${list.bigCustomerId}"
									<c:if test="${list.bigCustomerId eq developerBrandDto.bigCustomerId}">selected</c:if>>${list.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>


					<div class="layui-form-item" id="isYjDyDiv">
						<div class="layui-inline">
							<label class="layui-form-label labelWidth"><span class="redSpan">*</span>是否垫佣甲方</label>
							<div class="layui-input-inline" style="width: auto;">
								<input type="radio" id="isYjDy1" name="isYjDy" value="1" title="是" lay-filter="isYjDy" 
									   <c:if test="${'1' eq developerBrandDto.isYjDy}">checked</c:if>>
								<input type="radio" id="isYjDy2" name="isYjDy" value="0" title="否" lay-filter="isYjDy"
									   <c:if test="${'0' eq developerBrandDto.isYjDy}">checked</c:if>>
							</div>
						</div>
					</div>

					<div class="layui-form-item" id="mattressNailDiv" style="display:none">
						<label class="layui-form-label labelWidth"><span class="redSpan">*</span>垫佣甲方简称</label>
						<div class="layui-input-inline layui-input-block2"  style="width: 271px;!important;">
							<select id="mattressNail" name="mattressNail" lay-search=""  lay-verify="required" lay-filter="mattressNail">
								<option value="" >请选择或搜索</option>
								<c:forEach items="${nList}" var="list">
									<option value="${list.mattressNailId}"
									<c:if test="${list.mattressNailId eq developerBrandDto.mattressNailId}">selected</c:if>>${list.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>






<!-- 					<div class="layui-form-item"> -->
<!-- 						<label class="layui-form-label labelWidth">备注</label> -->
<!-- 						<div class="layui-input-inline layui-input-block2"> -->
<!-- 							<input type="text" id="remark" name="remark" style="width: 270px;" -->
<!-- 								lay-verify="required" placeholder="请输入备注" autocomplete="off" -->
<!-- 								class="layui-input"> -->
<!-- 						</div> -->
<!-- 					</div> -->
				</div>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/meta/pmls/js/developer/addDeveloperBrandPage.js?v=${vs}"></script>
</body>
</html>
