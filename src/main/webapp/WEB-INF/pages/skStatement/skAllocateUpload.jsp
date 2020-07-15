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
<script>
    var active;
    layui.use(['layer', 'upload'], function () {
        var layer = layui.layer,
                upload = layui.upload,
                $ = layui.$;
        upload.render({
            elem: '#selectFile'
            , url: BASE_PATH + '/skAllocate/uploadTemplate/'
            , data: {}
            , auto: false
            , accept: 'file'
            , exts: 'xls|xlsx'
            , bindAction: '#uploadImport'
            , before: function (obj) {
                parent.layer.load(2);
            }
            , done: function (data) {
                //上传完毕
                if (data.returnCode == -1) {
                    parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'}, function (index) {
                        parent.layer.closeAll();
                    });
                } else {
                    parent.layer.alert(data.returnMsg, {icon: 1, title: '提示'}, function (index) {
                        var jsonDto = data.returnValue;
                        console.log(jsonDto);
                        parent.layer.tblObj = jsonDto;
                        parent.layer.closeAll();
                    });
                }
            }
        });
    });
</script>
<script src="${ctx}/meta/pmls/js/skStatement/skStatementAllocate.js?v=${vs}"></script>
</body>
</html>