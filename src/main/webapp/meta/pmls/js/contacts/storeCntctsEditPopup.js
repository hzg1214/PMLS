var layer;
var id=window.id;
var jsonDto;

layui.use(['tree', 'layer', 'form'], function() {
    layer = layui.layer,
        $ = layui.jquery;
    var form = layui.form;

    if(id!=null && id !=''){
        if(info!=''){
            //jsonDto=eval('(' + window.info + ')');
            jsonDto=eval('(' + window.info.replace(/[\r\n]/g,"\\n") + ')');
        }
        $(".layui-field-title").hide();
        $("#name").val(jsonDto.name);
        $("#mobilePhone").val(jsonDto.mobilePhone);
    }
});

function getFormData(){
    var name=$("#name").val();
    var mobilePhone=$("#mobilePhone").val();
    if(isBlank(name)){
        parent.layer.alert('请输入姓名！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(mobilePhone)){
        parent.layer.alert('请输入手机号码！',{icon: 2, title:'提示'});
        return;
    }else if(!checkPhoneNumber(mobilePhone)){
        parent.layer.alert('请输入正确的手机号码！',{icon: 2, title:'提示'});
        return;
    }
    var obj={};
    obj.name=name;
    obj.mobilePhone=mobilePhone;
    obj.id=id;
    return obj;
}

