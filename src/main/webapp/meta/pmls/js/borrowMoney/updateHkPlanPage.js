/*
 * 渠道品牌js
 * */
var layer;
var parentId=window.parentId;
var id=window.id;
var brandJsonDto;

layui.use(['tree', 'layer', 'form','laydate'], function() {
    layer = layui.layer,
        $ = layui.jquery;
    var form = layui.form;
    laydate=layui.laydate;
    var nowDate=formatDate(new Date().getTime(),'yyyy-MM-dd');
    laydate.render({
        elem: '#sjhkDate',
        format: 'yyyy-MM-dd',
        trigger: 'click'
         ,min: window.jkDate
         ,max: nowDate
    });

    //初始化还款类型
    if(window.hkTypeList!=null && window.hkTypeList!=''){
        //var listData=eval('(' + window.hkTypeList + ')');
        var listData=eval('(' + window.hkTypeList.replace(/[\r\n]/g,"\\n") + ')');
        var result = "<option value=''>请选择</option>";
        $.each(listData, function (n, value) {
            if(value.dicCode=='28502'){
                result += "<option value='" + value.dicCode + "' selected>" + value.dicValue + "</option>";
            }else{
                result += "<option value='" + value.dicCode + "'>" + value.dicValue + "</option>";
            }
        });
        $("#hkType").html('');
        $("#hkType").append(result);
        $("#hkType").attr('disabled',"");
    }
    form.render();
});

function getFormData(){
    var hkType=$("#hkType").val();
    var sjhkDate=$("#sjhkDate").val();
    var remark=$("#remark").val().trim();
    if(isBlank(hkType)){
        parent.layer.alert('请选择还款类型！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(sjhkDate)){
        parent.layer.alert('请选择还款日！',{icon: 2, title:'提示'});
        return;
    }
    var obj={};
    obj.hkType=hkType;
    obj.remark=remark;
    obj.sjhkDate=sjhkDate;
    obj.id=window.id;
    return obj;
}

