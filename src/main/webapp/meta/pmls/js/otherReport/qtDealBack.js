var layer;
var form;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        table = layui.table,
        $ = layui.jquery;

        layer = layui.layer;
        form = layui.form;

    laydate.render({
        elem: '#backDealDate',
        type: 'date',
        format: 'yyyy-MM-dd'
        , max: $("#countDateEndStr").val(),
        trigger: 'click'
    });
    form.render();



    var active = {

    }
    $('.layui-btn').on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });

});



function qtReportBackSale() {
    parent.layer.load(2);

    var dealDate = $("#dealDate").val();
    var backDealDate = $("#backDealDate").val();
    var memo = $("#memo").val();

    var befYJSSAmount = $("#befYJSSAmount").val();
    var aftYJSSAmount = $("#aftYJSSAmount").val();
    var befSJFYAmount = $("#befSJFYAmount").val();
    var aftSJFYAmount = $("#aftSJFYAmount").val();

    if (isNullEmpty(backDealDate)) {
        parent.layer.closeAll();
        parent.layer.alert('请选择退成销日期！',{icon: 2, title:'提示'});
        return false;

    } else if (backDealDate < dealDate) {
        parent.layer.closeAll();
        parent.layer.alert('退成销日期应大于或等于成销日期！',{icon: 2, title:'提示'});
        return false;
    }

    if (isNullEmpty(memo)) {
        parent.layer.closeAll();
        parent.layer.alert('请填写退成销原因！',{icon: 2, title:'提示'});
        return false;
    } else if (memo.length > 200) {
        parent.layer.closeAll();
        parent.layer.alert('退成销原因不能超过200字！',{icon: 2, title:'提示'});
        return false;
    }

    if (isNotZero(befYJSSAmount) || isNotZero(aftYJSSAmount)) {
        parent.layer.closeAll();
        parent.layer.alert('该订单已有应计实收数据，不允许退成销！',{icon: 2, title:'提示'});
        return false;
    }

    if (isNotZero(befSJFYAmount) || isNotZero(aftSJFYAmount)) {
        parent.layer.closeAll();
        parent.layer.alert('该订单已有实际返佣数据，不允许退成销！',{icon: 2, title:'提示'});
        return false;
    }

    var optionsData = {};

    optionsData.id = $("#id").val();
    optionsData.backDealDate = backDealDate;
    optionsData.memo= memo;

    var url = BASE_PATH + '/pmlsQtSuccessSale/dealBack';
    $.ajax({
        type: "POST",
        url: url,
        data: optionsData,
        dataType: "json",
        success: function (data) {
            parent.layer.closeAll();
            if (data.returnCode == 200 || data.returnCode == '200') {
                back();
            } else {
                parent.layer.alert(data.returnMsg,{icon: 2, title:'提示'});
            }
        },
        error: function () {
            parent.layer.closeAll();
            parent.layer.alert('退成销失败');
        }
    });
};

function isNullEmpty(obj) {
    if (obj == null || obj == "" || obj == undefined) {
        return true;
    } else {
        return false;
    }
}

function isNotZero(obj) {
    if (obj != null && obj != undefined && obj != "" && obj > 0) {
        return true;
    } else {
        return false;
    }
}






//取消返回上一个页面
function back(){
    parent.redirectTo('delete',null,null);
}


function isNullEmpty(obj){
    if(obj == null || obj == "" || obj == undefined){
        return true;
    }else{
        return false;
    }
}

