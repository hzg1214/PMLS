<%@ page contentType="text/html;charset=UTF-8"
         trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>退房解锁</title>
    <%@include file="../../common/common.jsp" %>
    <style type="text/css">
        .lable-left {
            margin-left: 100px;
        }
    </style>
</head>
<script type="application/javascript">
</script>
<body>
<div class="layui-card">
    <div class="layui-card-body">
        <div class="layui-form myForm" style="margin-top: 20px;">
            <input type="hidden" id="reportId" value="${reportId}"/>
            <input type="hidden" name="relateId" id="relateId" value="${relateId}">
            <%--<fieldset class="layui-elem-field layui-field-title"--%>
            <%--style="margin-top: 20px;">--%>
            <%--<legend>退房解锁</legend>--%>
            <%--</fieldset>--%>
            <div class="layui-inline" style="margin-left:-40px;">
                <label class="layui-form-label" style="width: 120px !important;"><span
                        class="redSpan">*</span>解锁原因</label>
                <div class="layui-input-inline">
					<textarea name="unlockReason" id="unlockReason" notnull="true"
                              style="width: 318px; height: 150px"></textarea>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/scene/sceneTrade/openDialogUnlockBack.js?v=${vs}"></script>
</body>
</html>
