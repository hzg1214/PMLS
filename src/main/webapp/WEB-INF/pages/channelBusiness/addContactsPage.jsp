<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>添加下级</title>
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
    var companyNo='${companyNo}';
    var id='${id}';
    var contactDto='${contactDto}';
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body" style="height: 280px;">
            <div class="layui-form contactsForm">
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>管理员姓名</label>
                    <div class="layui-input-block">
                        <input type="text" id="dockingPeo" name="dockingPeo" lay-verify="required" autocomplete="off" placeholder="请输入管理员姓名" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>管理员手机号码</label>
                    <div class="layui-input-block">
                        <input type="text" id="dockingPeoTel" name="dockingPeoTel" lay-verify="required" maxlength="11" placeholder="请输入管理员手机号码" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/channelBusiness/addContactsPage.js?v=${vs}"></script>
</body>
</html>
