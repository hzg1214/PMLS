/*
 * 渠道品牌js
 * */
var layer;
var id=window.id;
var jsonDto;

layui.use(['tree', 'layer', 'form'], function() {
    layer = layui.layer,
        $ = layui.jquery;
    var form = layui.form;

    if(id!=null && id !=''){
        if(contactDto!=''){
            //jsonDto=eval('(' + window.contactDto + ')');
            jsonDto=eval('(' + window.contactDto.replace(/[\r\n]/g,"\\n") + ')');
        }
        $(".layui-field-title").hide();
        $("#dockingPeo").val(jsonDto.dockingPeo);
        $("#dockingPeoTel").val(jsonDto.dockingPeoTel);
    }
});

function getFormData(){
    var dockingPeo=$("#dockingPeo").val();
    var dockingPeoTel=$("#dockingPeoTel").val();
    if(isBlank(dockingPeo)){
        parent.layer.alert('请填写管理员姓名！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(dockingPeoTel)){
        parent.layer.alert('请填写管理员手机号码！',{icon: 2, title:'提示'});
        return;
    }else if(!checkPhoneNumber(dockingPeoTel)){
        parent.layer.alert('请输入正确的手机号码！',{icon: 2, title:'提示'});
        return;
    }
    var obj={};
    obj.dockingPeo=dockingPeo;
    obj.dockingPeoTel=dockingPeoTel;
    obj.companyNo=window.companyNo;
    if(id!=null && id!=''){
        obj.id=id;
    }
    return obj;
}

