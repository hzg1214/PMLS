<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <title>核算主体维护</title>
    <style type="text/css">
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
        <div class="layui-form" style="margin-top: 20px;">
            <label class="layui-form-label">归属城市</label>
            <div class="layui-inline" style="width: 250px!important;">
                <select id="newCityNo" name="newCityNo" lay-verify="newCityNo" lay-filter="newCityNo">
                    <option value="">请选择</option>
                    <c:forEach items="${cityList}" var="city">
                        <option value="${city.cityNo}">${city.cityName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form" style="margin-top: 20px;">
            <label class="layui-form-label">核算主体 </label>
            <div class="layui-inline" style="width: 250px!important;">
                <select id="newAccountProjectNo" name="newAccountProjectNo" xm-select="newAccountProjectNo"
                        xm-select-skin="normal" xm-select-direction="down" xm-select-height="30px" xm-select-search=""
                        xm-select-show-count='1'>
                    <option value="">请选择</option>
                    <c:forEach items="${accountProjectList}" var="info">
                        <option value="${info.accountProjectNo}"
                                data-name="${info.accountProject}">${info.accountProjectNo}_${info.accountProject}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
</div>

<script>

    var formSelects;
    layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
        var element = layui.element,
            laydate = layui.laydate,
            table = layui.table,
            form = layui.form,
            layer = layui.layer,
            $ = layui.$;

        formSelects = layui.formSelects;
        form.render('select'); // 刷新单选框渲染
    });

    function getFormData() {
        var cityNo = $("#newCityNo").val()
        if (isEmpty(cityNo)) {
            parent.msgAlert("请选择归属城市！")
            return;
        }

        var accountProjectNos = formSelects.value('newAccountProjectNo', 'valStr')
        if (isEmpty(accountProjectNos)) {
            parent.msgAlert("请选择核算主体！")
            return;
        }

        var result = {
            cityNo: cityNo
            , accountProjectNos: accountProjectNos
        };

        return result;
    }
</script>
</body>
</html>
