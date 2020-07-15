<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <title>维护返佣对象</title>
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
    <div class="layui-card-body"  style="height: 149px">
        <div class="layui-form" style="margin: 20px">
            <label class="layui-form-label"><i>*</i>意见</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入" class="layui-textarea" name="txtOpinion" lay-verify="txtOpinion"
                          lay-filter="txtOpinion"></textarea>
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

        var txtOpinion = $("textarea[name='txtOpinion']").val();
        if (isEmpty(txtOpinion)) {
            parent.msgAlert("请输入内容!");
            return;
        } else {
            var result = {
                txtOpinion: txtOpinion
            };
            return result;
        }
    }
</script>
</body>
</html>
