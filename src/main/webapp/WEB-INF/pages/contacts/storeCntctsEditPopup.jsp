<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>编辑联系人</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .contactsForm .layui-form-label{
            width:120px;
        }
        .contactsForm .layui-input-block{
            margin-left: 150px;
            min-height: 36px;
            margin-right: 40px;
        }
    </style>
</head>
<script type="application/javascript">
    var info='${info}';
    var id='${id}';
    <%--var sexList='${sexList}';--%>
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body" style="height: 280px;">
            <div class="layui-form contactsForm">
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>姓名</label>
                    <div class="layui-input-block">
                        <input type="text" id="name" name="name" lay-verify="required" autocomplete="off" placeholder="请输入" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>手机号码</label>
                    <div class="layui-input-block">
                        <input type="text" id="mobilePhone" name="mobilePhone" lay-verify="required" maxlength="11" placeholder="请输入" autocomplete="off"  class="layui-input">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/contacts/storeCntctsEditPopup.js?v=${vs}"></script>
</body>
</html>
