<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>选择核算主体</title>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>

    <style>
        body{padding: 0px; /*overflow-y: scroll;*/}
        .searchForm{
            padding-top: 5px;
        }
    </style>
</head>
<script type="application/javascript">

</script>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body" style="height: 179px;">
            <form class="layui-form" style="margin-top: 10px;">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">核算主体</label>
                        <div class="layui-input-inline" style="width: 220px;">
                            <select id="userAccountProject" name="userAccountProject" lay-verify="userAccountProject"
                                     lay-filter="userAccountProject">
								 <c:if test="${!empty userMappingAccountProject}">
	                                    <c:forEach items="${userMappingAccountProject}" var="user">
	                                        <option value="${user.accountProjectCode}"
	                                                <c:if test="${user.accountProjectCode eq cityAccountProjectCode}">selected</c:if>
	                                                >${user.accountProject}</option>
	                                    </c:forEach>
	                            </c:if>
                            </select>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
<script type="application/javascript">
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer;
        $ = layui.$;
    formSelects = layui.formSelects;
});
function getFormData() {
	var obj={};
	var userAccountProjectCode = $('#userAccountProject').val();
    if (userAccountProjectCode ==null || userAccountProjectCode == '' || userAccountProjectCode == undefined) {
        layer.alert('请选核算主体！', {icon: 2, title: '提示'});
        return;
    }
    var userAccountProject = $('select[name="userAccountProject"] option:selected').text();
    obj.accountProjectCode=userAccountProjectCode;
    obj.accountProject=userAccountProject;
    
    return obj;
}
</script>
</body>
</html>

