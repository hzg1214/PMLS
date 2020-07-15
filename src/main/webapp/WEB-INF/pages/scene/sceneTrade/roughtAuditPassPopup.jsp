<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <title>收入类型</title>
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
    <div class="layui-card-body" style="height: 89px">
        <div class="layui-form" style="margin: 20px;">
            <label class="layui-form-label"><i>*</i>收入类型</label>
            <div class="layui-inline">
                <input type="radio" value="25301" name="inComeStatus" lay-filter="inComeStatus" title="新房联动">
                <input type="radio" value="25302" name="inComeStatus" lay-filter="inComeStatus" title="房友联动">
                <input type="radio" value="25303" name="inComeStatus" lay-filter="inComeStatus" title="运维服务">
                <input type="radio" value="25304" name="inComeStatus" lay-filter="inComeStatus" title="其他">
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

        form.on('radio(inComeStatus)', function (data) {

        });
    });

    function getFormData() {

        var inComeStatus = $("input:radio[name='inComeStatus']:checked").val();
        if (isEmpty(inComeStatus)) {
            parent.msgAlert("请选择收入类型");
            return;
        } else {
            var result = {
                inComeStatus: inComeStatus
            };
            return result;
        }
    }
</script>
</body>
</html>
