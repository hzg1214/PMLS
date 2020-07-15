
var $,table;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        layer = layui.layer;
    $ = layui.$;
    table = layui.table;

});

var oldnum = '';

function checkPercentage(num){
    var reg=new RegExp("^(((\\d{1,2})[.]((\\d{1,2})?))|100|(?:0|[1-9][0-9]?)|100.00|100.0)$");
    if(num==''||num=='-'){
        oldnum = '';
        return oldnum;
    }else{
        if(!reg.test(num)){
            return oldnum;
        }else{
            oldnum = num;
            return num;
        }
    }
}

//获取参数
function getFormData(){
    var obj={};
    var projectNo=$("#projectNo").val();
    var planType = $("#planType").val();
    var fixAmount = $("#fixAmount").val();
    var totalPercentage = $("#totalPercentage").val();

    if(isBlank(fixAmount)&&isBlank(totalPercentage)){
        layer.alert('固定与总价必须输入一个！',{icon: 2, title:'提示'});
        return;
    }

    obj.projectNo=projectNo;
    obj.planType=planType;
    obj.fixAmount=fixAmount;
    obj.totalPercentage=totalPercentage;

    return obj;
}