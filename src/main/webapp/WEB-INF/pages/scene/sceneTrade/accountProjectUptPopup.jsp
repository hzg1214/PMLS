<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <title>核算主体维护</title>
    <style>
        body {
            padding: 0px;
            overflow: hidden;
        }

        dl {
            max-height: 180px!important;
        }
    </style>
</head>
<body>

<div class="layui-card">
    <div class="layui-card-body" style="height: 299px">
        <input type="hidden" id='cityNo' value="${cityNo}">
        <input type="hidden" id='accountProjectNo' value="${accountProjectNo}">

        <div class="layui-form" style="margin-top: 20px;">
            <label class="layui-form-label">归属城市</label>
            <div class="layui-inline" style="width: 250px!important;">
                <select id="editCityNo" name="editCityNo" disabled lay-verify="editCityNo" lay-filter="editCityNo">
                    <option value="">请选择</option>
                    <c:forEach items="${cityList}" var="city">
                        <option value="${city.cityNo}"
                                <c:if test="${cityNo eq city.cityNo}">selected</c:if>
                        >${city.cityName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form" style="margin-top: 20px;">
            <label class="layui-form-label">核算主体 </label>
            <div class="layui-inline" style="width: 250px!important;">
                <select id="editAccountProjectNo" name="editAccountProjectNo" lay-verify="editAccountProjectNo"
                        lay-filter="editAccountProjectNo"> >
                    <option value="">请选择</option>
                    <c:forEach items="${accountProjectList}" var="info">
                        <option value="${info.accountProjectNo}" data-name="${info.accountProject}"
                                <c:if test="${accountProjectNo eq info.accountProjectNo}">selected</c:if>
                        >${info.accountProjectNo}_${info.accountProject}</option>
                    </c:forEach>
                </select>
            </div>
        </div>


    </div>
</div>

<script>

    layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
        var element = layui.element,
            laydate = layui.laydate,
            table = layui.table,
            form = layui.form,
            formSelects = layui.formSelects,
            layer = layui.layer,
            $ = layui.$;

        form.render('select'); // 刷新单选框渲染
    });

    function getFormData() {

        var accountProjectNos = $("#editAccountProjectNo").val()
        if (isEmpty(accountProjectNos)) {
            parent.msgAlert("请选择核算主体！");
            return;
        }

        var result = {
            accountProjectNos: accountProjectNos
        }
        return result;
    }
</script>
</body>
</html>
