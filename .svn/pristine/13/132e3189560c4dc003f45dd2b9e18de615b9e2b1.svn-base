var layer;
var form,upload;

layui.use(['element', 'laydate', 'form', 'table', 'layer','upload','tree'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        table = layui.table,
        formSelects = layui.formSelects,
        $ = layui.jquery;

        layer = layui.layer;
        form = layui.form;
        upload = layui.upload;

    laydate.render({
        elem: '#reportDate',
        type: 'date',
        format: 'yyyy-MM-dd'
        , max: $("#countDateEndStr").val(),
        trigger: 'click'
    });
    form.render();

    //初始化业绩城市
    achievementCity("accCity",null,function () {
        form.render();
        //初始化业绩中心
      /*  achievementCenter("#centerId",data.value,null,function () {
            form.render();
        });*/
    });

    //城市选择事件
    form.on('select(accCity)', function (data) {
        if(data.value!=null && data.value!=''){
            achievementCenter("centerId",data.value,null,function () {
                form.render();
            });
        }

        if(data.value!=null && data.value!=''){
            achievementCenter("srCenterId",data.value,null,function () {
                form.render();
            });
        }
    });
});





function reportAdd() {
    parent.layer.load(2);

    var estateId = $("#estateId").val();
    var estateNm = $("#estateNm").val();
    var partnerNm = $("#partnerNm").val();

    var accCityNo = $("#accCity").find("option:selected").val();
    var centerGroupId = $("#centerId").find("option:selected").val();
    var centerGroupName = $("#centerId").find("option:selected").text();

    var srCenterId = $("#srCenterId").find("option:selected").val();
    var srCenterName = $("#srCenterId").find("option:selected").text();


    var srType = $("#srType").val();
    var srAmount = $("#srAmount").val();
    var reportDate = $("#reportDate").val();
    var memo = $("#memo").val();



    if (isNullEmpty(accCityNo)) {
        parent.layer.closeAll();
        parent.layer.alert('请选择业绩归属城市！',{icon: 2, title:'提示'});
        return false;
    }

    if (isNullEmpty(centerGroupId)) {
        parent.layer.closeAll();
        parent.layer.alert('请选择业绩归属中心！',{icon: 2, title:'提示'});
        return false;
    }

    if (isNullEmpty(srCenterId)) {
        parent.layer.closeAll();
        parent.layer.alert('请选择收入中心！',{icon: 2, title:'提示'});
        return false;
    }

    if (isNullEmpty(srType)) {
        parent.layer.closeAll();
        parent.layer.alert('请选择收入类型！',{icon: 2, title:'提示'});
        return false;
    }

    if (isNullEmpty(srAmount)) {
        parent.layer.closeAll();
        parent.layer.alert('请输入收入金额！',{icon: 2, title:'提示'});
        return false;
    }

    if (isNullEmpty(reportDate)) {
        parent.layer.closeAll();
        parent.layer.alert('请选择报备日期！',{icon: 2, title:'提示'});
        return false;
    }

    var lnkQtReport = {};
    lnkQtReport.estateId = estateId;
    lnkQtReport.estateNm = estateNm;
    lnkQtReport.partnerNm = partnerNm;
    lnkQtReport.accCityNo = accCityNo;
    lnkQtReport.centerId = centerGroupId;
    lnkQtReport.centerName = centerGroupName;
    lnkQtReport.srCenterId = srCenterId;
    lnkQtReport.srCenterName = srCenterName;
    lnkQtReport.srType = srType;
    lnkQtReport.srAmount = srAmount;
    lnkQtReport.reportDate = reportDate;
    lnkQtReport.memo = memo;

    var url = BASE_PATH + '/pmlsQtReport/createReport';

    $("#reportPcAddBtn").hide();

    $.ajax({
        type: "POST",
        url: url,
        contentType: 'application/json;charset=utf-8', //设置请求头信息
        data: JSON.stringify(lnkQtReport),
        dataType: "json",
        success: function (data) {
            parent.layer.closeAll();
            if (data.returnCode == 200 || data.returnCode == '200') {
                back();
            } else {

                parent.layer.alert(data.returnMsg,{icon: 2, title:'提示'});
            }
            $("#reportPcAddBtn").show();
        },
        error: function () {
            parent.layer.closeAll();
            parent.layer.alert('报备失败');
            $("#reportPcAddBtn").show();
        }
    });
}


function isNullEmpty(obj){
    if(obj == null || obj == "" || obj == undefined){
        return true;
    }else{
        return false;
    }
}



//取消返回上一个页面
function back(){
    parent.redirectTo('delete',null,null);
}



