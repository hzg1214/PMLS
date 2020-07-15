<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <title>结算书终止</title>
    <style>
        body {
            padding: 0px;
            overflow: hidden;
        }

        i {
            color: #FF0000;
        }

    </style>
</head>
<body>

<div class="layui-card">
    <div class="layui-card-body" style="height: 149px">
        <div class="layui-form" style="margin: 20px">
            <label class="layui-form-label"><i>*</i>终止原因</label>
            <div class="layui-input-block">
                <textarea id="txtTerminationDesc" placeholder="不超200字" class="layui-textarea"
                          name="txtTerminationDesc" lay-verify="txtTerminationDesc" maxlength="200"
                          lay-filter="txtTerminationDesc"></textarea>
            </div>
        </div>
    </div>
</div>


<script>

    layui.use(['element', 'form', 'layer'], function () {
        var element = layui.element,
            form = layui.form,
            layer = layui.layer,
            $ = layui.$;

    });

    function getFormData() {

        var txtTerminationDesc = $("textarea[name='txtTerminationDesc']").val();
        if (isEmpty(txtTerminationDesc)) {
            parent.msgAlert("请输入终止原因!");
            return;
        } else {
            var result = {
                terminationDesc: txtTerminationDesc
            };
            return result;
        }
    }
</script>
</body>
</html>
