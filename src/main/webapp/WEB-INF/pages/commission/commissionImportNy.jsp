<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link rel="stylesheet" href="${ctx}/meta/layui/css/layui.css?_v=${vs}">
    <script src="${ctx}/meta/layui/layui.js?_v=${vs}"></script>
    <title></title>
</head>
<script type="application/javascript">
    var BASE_PATH = '${ctx}';
</script>
<body>
<div style="margin: 30px;">
    <div class="layui-upload">
        <button type="button" class="layui-btn layui-btn-normal" id="selectFile">选择文件</button>
        <div style="color: red;margin-top: 10px;">注：请选择后缀名为.xlsx文件。</div>
    </div>

    <div class="layui-upload" style="margin-top: 30px;">
        <button type="button" class="layui-btn" id="uploadImport">开始上传</button>
    </div>
</div>

<script src="${ctx}/meta/pmls/js/commission/importExcelCommissionNy.js?v=${vs}"></script>
</body>
</html>