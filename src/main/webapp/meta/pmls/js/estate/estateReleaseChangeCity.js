
var $,table;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        layer = layui.layer;
    $ = layui.$;
    table = layui.table;

    form.render('select');
    form.render();
});



function getFormData(){
    var formData={};
    var id = $("#id").val();
    var estateId = $("#estateId").val();
    var flag = $("#flag").val();
    var cityNo = $("#cityNo").val();
    var cityNm = $("#cityNm").val();
    var oldReleaseCity = $("#oldReleaseCity").val();
    var citylistLenth = $("#citylistLenth").val();
    var select_newRelseaseCityNo = layui.formSelects.value('relseaseCity', 'valStr');//取值val字符串
    if(!select_newRelseaseCityNo){
        layer.alert('请选择项目发布城市！',{icon: 2, title:'提示'});
        return;
    }
    formData.id = id;
    formData.estateId = estateId;
    formData.flag=flag;
    formData.cityNo=cityNo;
    formData.cityNm=cityNm;
    formData.oldReleaseCity=oldReleaseCity;
    formData.citylistLenth=citylistLenth;
    formData.select_newRelseaseCityNo=select_newRelseaseCityNo;
    return formData;
}
