
var $,table;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        layer = layui.layer;
    $ = layui.$;
    table = layui.table;

    form.on('radio(releaseFlg)', function(data){
        if(data.value==0){
            //预约发布
            $(".predictDate").removeClass("hidden");
        }else{
            //立即发布
            $(".predictDate").addClass("hidden");
        }
    });

    laydate.render({
        elem: '#releaseDt',
        trigger: 'click'
    });

});

function getFormData(){
    var formData={};
    var releaseDt = $("#releaseDt").val();
    var releaseFlg = $("input[name='releaseFlg']:checked").val();

    if(!releaseFlg){
        layer.alert('请选择发布方式！',{icon: 2, title:'提示'});
        return;
    }
    if(releaseFlg=='0' && !releaseDt){
        layer.alert('请选择预约发布时间！',{icon: 2, title:'提示'});
        return;
    }
    formData.releaseFlg = releaseFlg;
    formData.releaseDt = releaseDt;
    formData.releaseStatus="13002";
    return formData;
}
