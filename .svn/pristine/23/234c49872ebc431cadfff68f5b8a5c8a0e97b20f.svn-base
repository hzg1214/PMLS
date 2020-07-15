<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>新建合作方</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
		.addressInputBlock  .layui-form-select{
            width: 100px;
        }
        .addressInputBlock .divInline{
            float:left;
            margin-right:10px;
        }
        .layui-treeSelect .ztree li span.button.switch{
            top:-7px;
        }
        .layui-form-item .layui-inline.toolbar{
            margin-left: 330px;
        }
    </style>
</head>
<script type="application/javascript">
    var id='${id}';
    var developerDto='${developerDto}';//合作方信息
    var partnerList='${partnerList}';//合作方类型
    
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-row blockBody">
                <div class="layui-col-xs6">
                    <div class="blockTitle">新建合作方</div>
                </div>
                <div class="layui-col-xs6 blockBtn">
                    <button type="button" class="layui-btn layui-btn-primary" onclick="back()">返回</button>
                </div>
            </div>
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                <legend>基本信息</legend>
            </fieldset>
            <div class="layui-form myForm" style="margin-top:20px;">
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>合作方名称</label>
                    <div class="layui-input-block">
                        <input type="text" id="developerName" name="developerName" onchange="checkDeveloperName()" lay-verify="required" autocomplete="off" placeholder="请输入" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>统一社会信用代码</label>
                    <div class="layui-input-inline">
	                    <input type="text" style="width: 420px;" id="businessLicenseNo" maxlength="18" name="businessLicenseNo" onchange="checkDeveloper()" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
	                <a href="https://www.qixin.com/" style="color: #fff;" target="_blank" rel="noreferrer"><button type="button" class="layui-btn" style="margin-left: 230px;">快捷查询</button></a>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>注册地址</label>
                    <div class="layui-input-block addressInputBlock">
                        <div class="divInline">
                            <select id="realityCityNo" lay-filter="realityCityNo" lay-search="" placeholder="请选择">
                                <option value="">请选择城市</option>
                            </select>
                        </div>
                        <div class="divInline">
                            <select id="districtNo" lay-filter="districtNo" lay-search="" placeholder="请选择">
                                <option value="">请选择区域</option>
                            </select>
                        </div>
                        <div class="divInline" style="margin-right: 0px;width: 200px;">
                            <input type="text" id="address" name="address" lay-verify="required" placeholder="请输入详细地址" autocomplete="off" class="layui-input">
                        </div>

                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>法定代表人</label>
                    <div class="layui-input-block">
                        <input type="text" id="legalPerson" name="legalPerson" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">法人手机号码</label>
                    <div class="layui-input-block">
                        <input type="text" id="contactNumber" maxlength="11" name="contactNumber" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>联系人姓名</label>
                    <div class="layui-input-block">
                        <input type="text" id="dockingPeo" name="dockingPeo" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>联系人手机号码</label>
                    <div class="layui-input-block">
                        <input type="text" id="dockingPeoTel" maxlength="11" name="dockingPeoTel" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                	<input type="hidden" id="editDeveloperBrandId" name="editDeveloperBrandId" value="">
                	<input type="hidden" id="chooseDeveloperBrandId" name="chooseDeveloperBrandId" value="">
                    <label class="layui-form-label"><span class="redSpan">*</span>合作方品牌</label>
<!--                     <div class="layui-input-block brandIdInputBlock"> -->
<!--                         <input id="developerBrandId" type="text" class="layui-input" lay-filter="tree"></input> -->
<!--                     </div> -->
                    <div class="layui-input-inline" style="width: 420px;">
                        <input type="text" class="layui-input" id="developerBrandId" disabled name="developerBrandId" lay-verify="required"  autocomplete="off">
                    </div>
                    <div id="chooseBtn">
                    	<button type="button" class="layui-btn" onclick="selectBrand()">选择</button>
                    </div>
                </div>
                <div class="layui-form-item ">
                    <div class="layui-inline">
                    	<input type="hidden" id="partnerAbbreviationNm" name="partnerAbbreviationNm" value="${developerDto.partnerAbbreviationNm}">
                        <label class="layui-form-label"><span class="redSpan">*</span>合作方类型</label>
                        <div class="layui-input-inline" style="width: auto;" id="partnerType">
<%--                             <c:forEach items="${partnerList}" var="list"> --%>
<%--                                 <input type="radio" value="${list.dicCode}" title="${list.dicValue}" name="partner" lay-filter="partner" --%>
<%--                                       <c:if test="${list.dicCode eq developerDto.partner}">checked</c:if>> --%>
<%--                             </c:forEach> --%>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
        			<input type="hidden" id="bigCustomerId" name="bigCustomerId" value="${developerDto.bigCustomerId}">
				    <label class="layui-form-label"><span class="redSpan">*</span>是否大客户</label>
                	<input type="hidden" id="bigCustomerName" name="bigCustomerName">
				    <div class="layui-input-block" id="bigCustomerFlag">
				      <input type="radio" name="bigCustomer" disabled value="22601" title="是">
				      <input type="radio" name="bigCustomer" disabled value="22602" title="否">
				    </div>
				</div>
                <div class="layui-form-item">
                	<input type="hidden" id="mattressNailId" name="mattressNailId" value="${developerDto.mattressNailId}">
                	<input type="hidden" id="mattressNailName" name="mattressNailName">
				    <label class="layui-form-label"><span class="redSpan">*</span>是否垫佣甲方</label>
				    <div class="layui-input-block" id="isYjDy">
				      <input type="radio" name="mattress" disabled value="1" title="是">
				      <input type="radio" name="mattress" disabled value="0" title="否">
				    </div>
				</div>
                <div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <textarea placeholder="200字以内"  maxlength="200" id="remark" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
			        <div class="layui-inline toolbar">
			            <button type="button" class="layui-btn" onclick="submitForm()">提交</button>
			            <button type="button" class="layui-btn layui-btn-primary" onclick="back()">取消</button>
			        </div>
	       		</div>
            </div>
            </div>
        </div>
    </div>
</div>


</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/developer/addDeveloperPage.js?v=${vs}"></script>
</body>
</html>
