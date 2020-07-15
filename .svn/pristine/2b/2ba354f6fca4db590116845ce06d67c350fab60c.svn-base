<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>还款</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .layui-input-block2{
            margin-left: 110px;
            min-height: 36px;
            margin-right: 20px;
        }
    </style>
</head>
<script type="application/javascript">
    var id='${id}';
    var jkDate='${jkDate}';
    var hkTypeList='${hkTypeList}';
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body" style="height: 480px;">

            <div class="layui-form" style="margin-top:20px;">
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>还款类型</label>
                    <div class="layui-input-block2" >
                        <select id="hkType" lay-filter="hkType" placeholder="请选择">
                            <option value="">请选择</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><span class="redSpan">*</span>还款日</label>
                    <div class="layui-input-block2">
                        <input type="text" id="sjhkDate" readonly class="layui-input test-item" autocomplete="off" placeholder="请选择">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block2">
                        <input type="text" id="remark" name="remark" lay-verify="required" placeholder="请输入备注" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/borrowMoney/updateHkPlanPage.js?v=${vs}"></script>
</body>
</html>
