
var $,table;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        layer = layui.layer;
    $ = layui.$;
    table = layui.table;

    //初始化核算主体
    initLnkAccountProjectList();

    /**
     * 初始化核算主体
     */
    function initLnkAccountProjectList(){
        var url = BASE_PATH + "/cashBill/getLnkAccountProjectList";
        var params = {estateCityNo:estateCityNoProject};
        ajaxGet(url, params, function(data) {
            console.log(data);
            var dataLength =  data.returnValue.length;
            var result = "<option value=''>请选择核算主体</option>";
            $.each(data.returnValue, function(n, value) {
                result += "<option value='" + value.lnkaccountProjectCode + "'data-lnkaccountProject='" + value.lnkAccountProject+ "'>"
                    + value.lnkaccountProjectCode +"_"+ value.lnkAccountProject + "</option>";
            });
            console.log(result);
            $("#lnkAccountProjectCode").html('');
            $("#lnkAccountProjectCode").append(result);
            form.render('select'); //刷新select选择框渲染
        }, function() {
        });
    }

});

function getFormData(){
    var formData={};
    var accountProjectCode = $("#lnkAccountProjectCode").val();
    var lnkAccountProject = $("#lnkAccountProjectCode option:selected").attr('data-lnkAccountProject');

    if(!accountProjectCode){
        layer.alert('请选择核算主体！',{icon: 2, title:'提示'});
        return;
    }
    formData.accountProjectCode = accountProjectCode;
    formData.lnkAccountProject = lnkAccountProject;
    return formData;
}
