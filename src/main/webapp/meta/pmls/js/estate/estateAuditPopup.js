var $,table;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        layer = layui.layer;
        table = layui.table;
        $ = layui.$;

    form.on('radio(auditStatus)', function(data){
        if(data.value=='12903'){
            //通过
            $(".auditStatusSpan").addClass("hidden");
        }else{
            //不通过
            $(".auditStatusSpan").removeClass("hidden");
        }
    });

});

function getFormData(){
    var formData={};
    var id = $("#id").val();
    var auditStatus = $("input[name='auditStatus']:checked").val();
    var auditMemo = $("#auditMemo").val();
    if(!auditStatus){
        layer.alert('请选择审核结果！',{icon: 2, title:'提示'});
        return;
    }
    if(auditStatus == '12902'){
        if(!auditMemo){
            layer.alert('请输入原因！',{icon: 2, title:'提示'});
            return;
        }
    }
    formData.id = id;
    formData.auditStatus=auditStatus;
    formData.auditMemo=auditMemo;
    return formData;
}