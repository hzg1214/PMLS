/*
 * 渠道品牌js
 * */
var layer;
var parentId=window.parentId;
var id=window.id;
var brandJsonDto;

layui.use(['tree', 'layer', 'form'], function() {
    layer = layui.layer,
        $ = layui.jquery;
    var form = layui.form;

    if(id!=null && id !=''){
        if(brandDto!=''){
            //brandJsonDto=eval('(' + window.brandDto + ')');
            brandJsonDto=eval('(' + window.brandDto.replace(/[\r\n]/g,"\\n") + ')');
        }
        $(".layui-field-title").hide();
        $("#brandName").val(brandJsonDto.brandName);
        // $("#remark").val(brandJsonDto.remark);
    }
});

function getFormData(){
    var brandName=$("#brandName").val().trim();
    //var remark=$("#remark").val();
    var reg =/\s/;//校验是否存在空格
    if(isBlank(brandName)){
        parent.layer.alert('请填写渠道品牌名称！',{icon: 2, title:'提示'});
        return;
    }else if(reg.test(brandName)){
        parent.layer.alert('渠道品牌名称不能含有空格！',{icon: 2, title:'提示'});
        return;
    }
    var obj={};
    obj.brandName=brandName;
    //obj.remark=remark;
    obj.parentId=parentId;
    if(id!=null && id!=''){
        obj.id=id;
    }
    return obj;
}

