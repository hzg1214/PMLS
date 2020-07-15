var active;
var layer;
var $;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        formSelects = layui.formSelects;
        $ = layui.$
    layer = layui.layer;
    form.render();
    location.href = BASE_PATH + "/app/index";


});





