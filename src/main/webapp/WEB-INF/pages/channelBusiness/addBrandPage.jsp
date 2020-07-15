<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>添加下级</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .layui-form-label2{
            width:100px;
            float: left;
            display: block;
            padding: 9px 15px;
            font-weight: 400;
            line-height: 20px;
            text-align: right;
        }
        .layui-input-block2{
            margin-left: 130px;
            min-height: 36px;
            margin-right: 20px;
        }
    </style>
</head>
<script type="application/javascript">
    var parentId = '${parentId}';
    var parentName='${parentName}';
    var id='${id}';
    var brandDto='${brandDto}';
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body" style="height: 280px;">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>上级渠道品牌：${parentName}</legend>
            </fieldset>
            <div class="layui-form" style="margin-top:20px;">
                <div class="layui-form-item">
                    <label class="layui-form-label2"><span class="redSpan">*</span>渠道品牌名称</label>
                    <div class="layui-input-block2">
                        <input type="text" id="brandName" name="brandName" lay-verify="required" autocomplete="off" placeholder="请输入" class="layui-input">
                    </div>
                </div>
                <%--<div class="layui-form-item">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block2">
                        <input type="text" id="remark" name="remark" lay-verify="required" placeholder="请输入备注" autocomplete="off" class="layui-input">
                    </div>
                </div>--%>
            </div>
        </div>
    </div>
</div>


</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/channelBusiness/addBrandPage.js?v=${vs}"></script>
</body>
</html>
