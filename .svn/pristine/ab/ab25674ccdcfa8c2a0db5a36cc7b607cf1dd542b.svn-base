var $,table;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        layer = layui.layer;
        table = layui.table;
        $ = layui.$;

    form.on('select(realityCityNo)', function(data){
        initDistrictNoList(data.value);
    });

    form.on('checkbox(estateType)', function(data){
        var name = $(data.elem).prop("name");
        if(data.elem.checked){
            $("."+ name).removeClass("hidden");
        }else{
            $("."+ name).addClass("hidden");
        }
    });

    laydate.render({
        elem: '#openTime',
        trigger: 'click',
        showBottom: false
    });

    //初始化楼盘城市—区域
    function initDistrictNoList(realityCityNo) {
        var url = BASE_PATH + "/linkages/city/" + realityCityNo;
        var params = {};
        var result = "<option value=''>请选择区域</option>";
        if(realityCityNo){
            ajaxGet(url, params, function (data) {
                $.each(data.returnValue, function(n, value) {
                    result += "<option value='" + value.districtNo + "'>"
                        + value.districtName + "</option>";
                });
                $("#districtNo").html('');
                $("#districtNo").append(result);
                form.render('select'); //刷新select选择框渲染
            }, function () {
            });
        }else{
            $("#districtNo").html('');
            $("#districtNo").append(result);
            form.render('select'); //刷新select选择框渲染
        }
    }

});

function getFormData(){
    var formData={};
    var id = $("#id").val();
    var estateAddressChecked = $('input[name="estateAddress"]').prop("checked");
    var salesStatusChecked = $('input[name="salesStatus"]').prop("checked");
    var businessModeChecked = $('input[name="businessMode"]').prop("checked");

    var oldEstatePosition = $("#oldEstatePosition").val();
    var oldCountryNo = $("#oldCountryNo").val();
    var oldCityNo = $("#oldCityNo").val();
    var oldDistrictNo = $("#oldDistrictNo").val();
    var oldAddress = $("#oldAddress").val();

    var newCountryNo = $("#newCountryNo").val();
    var newCountryNm = $("#newCountryNo option:selected").text();
    var realityCityNo = $("#realityCityNo").val();
    var newCityNm = $("#realityCityNo option:selected").text();
    var districtNo = $("#districtNo").val();
    var newDistrictNm = $("#districtNo option:selected").text();
    var newAddress = $("#newAddress").val();

    var oldSalesStatus = $("#oldSalesStatus").val();
    var oldSalesStatusStr = $("#oldSalesStatusStr").val();
    var newSalesStatus = $("input[name='newSalesStatus']:checked").val();
    var openTime = $("#openTime").val();

    var oldBusinessMode = $("#oldBusinessMode").val();
    var oldBusinessModeStr = $("#oldBusinessModeStr").val();

    var newBusinessMode = $("#newBusinessMode").val();
    var newBusinessModeStr = $("#newBusinessMode option:selected").text();
    var modifyReason = $("#modifyReason").val();

    if(!estateAddressChecked && !salesStatusChecked && !businessModeChecked){
        layer.alert('请选择修改项目类型！',{icon: 2, title:'提示'});
        return;
    }
    //项目地址
    if(estateAddressChecked){
        if(oldEstatePosition=='0'){
            //国内
            if(!realityCityNo){
                layer.alert('请选择城市！',{icon: 2, title:'提示'});
                return;
            }
            if(!districtNo){
                layer.alert('请选择区域！',{icon: 2, title:'提示'});
                return;
            }
            if(!newAddress){
                layer.alert('请输入具体地址！',{icon: 2, title:'提示'});
                return;
            }
            if(oldCityNo==realityCityNo && oldDistrictNo==districtNo && oldAddress==newAddress){
                layer.alert('修改后项目地址和原项目地址一样！',{icon: 2, title:'提示'});
                return;
            }
            formData.realityCityNo=realityCityNo;
            formData.newCityNm=newCityNm;
            formData.districtNo=districtNo;
            formData.newDistrictNm=newDistrictNm;
        }else{
            //海外
            if(!newCountryNo){
                layer.alert('请选择国家！',{icon: 2, title:'提示'});
                return;
            }
            if(oldCountryNo==newCountryNo && oldAddress==newAddress){
                layer.alert('修改后项目地址和原项目地址一样！',{icon: 2, title:'提示'});
                return;
            }
            formData.newCountryNo=newCountryNo;
            formData.newCountryNm=newCountryNm;
        }
        formData.estateAddress = "1";
        formData.oldEstatePosition=oldEstatePosition;
        formData.oldCountryNo = oldCountryNo;
        formData.oldCityNo=oldCityNo;
        formData.oldDistrictNo=oldDistrictNo;
        formData.oldAddress=oldAddress;
        formData.newAddress=newAddress;
    }
    //销售状态
    if(salesStatusChecked){
        if(!newSalesStatus){
            layer.alert('请选择销售状态！',{icon: 2, title:'提示'});
            return;
        }
        if(!openTime){
            layer.alert('请选择实际开盘日期！',{icon: 2, title:'提示'});
            return;
        }
        if(oldSalesStatus==newSalesStatus){
            layer.alert('修改后销售状态和原销售状态一样！',{icon: 2, title:'提示'});
            return;
        }
        formData.salesStatus = "1";
        formData.oldSalesStatus=oldSalesStatus;
        formData.oldSalesStatusStr=oldSalesStatusStr;
        formData.newSalesStatus=newSalesStatus;
        formData.openTime=openTime;
    }
    //业务模式
    if(businessModeChecked){
        if(!newBusinessMode){
            layer.alert('请选择业务模式！',{icon: 2, title:'提示'});
            return;
        }
        if(oldBusinessMode==newBusinessMode){
            layer.alert('修改后业务模式和原业务模式一样！',{icon: 2, title:'提示'});
            return;
        }
        formData.businessMode = "1";
        formData.oldBusinessMode=oldBusinessMode;
        formData.oldBusinessModeStr=oldBusinessModeStr;
        formData.newBusinessMode=newBusinessMode;
        formData.newBusinessModeStr=newBusinessModeStr;
    }
    if(!modifyReason){
        layer.alert('请输入修改原因！',{icon: 2, title:'提示'});
        return;
    }
    formData.id = id;
    //修改原因
    formData.modifyReason=modifyReason;
    return formData;
}