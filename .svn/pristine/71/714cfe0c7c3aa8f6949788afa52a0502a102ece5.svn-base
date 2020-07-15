/**
 * 初始化
 */
$(function () {
    var options = {
        "url": BASE_PATH + '/file/uploadCommonFile',
        "isDeleteImage": false,//删除时校验checkEditImage
        "isAddImage": false,   //添加时校验checkEditImage
        "isCommonFile": true  //公共上传文件
    };
    photoUploader(options, null, null, null);

    initCityCenter();
    var oldAccCityNo = $("#oldAccCityNo").val();
    var oldCenterId = $("#oldCenterId").val();
    initCenter(oldAccCityNo, oldCenterId);

    //初始化accountProject
    var accountProjectTxt = $("#accountProjectNo").find("option:selected").text();
    var txt = accountProjectTxt.split("_");
    if (txt && txt.length > 1) {
        var accountProject = txt[1];
        $("#accountProject").val(accountProject);
    }

    $("#accountProjectNo").change(
        function () {
            var aPTxt = $("#accountProjectNo").find("option:selected").text();
            var txtArr = aPTxt.split("_");
            if (txtArr && txtArr.length > 1) {
                var accountProject = txtArr[1];
                $("#accountProject").val(accountProject);
            }
        });
});


/**
 * 二级联动
 */
function initCityCenter(callbackCity, callbackDist) {
    var url = BASE_PATH + "/qtReport/getBasCitySettingList";
    var oldAccCityNo = $("#oldAccCityNo").val();
    var params = {};
    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择城市</option>";
        $.each(data.returnValue, function (n, value) {
            result += "<option value='" + value.cityNo + "'>"
                + value.cityName + "</option>";
        });
        $("#accCitySelect").html('');
        $("#accCitySelect").append(result);
        // 默认值赋值
        $('#divAccCitySelect .selectpicker').selectpicker('val', oldAccCityNo);//默认选中
        $('#divAccCitySelect .selectpicker').selectpicker('refresh');
        $('#divAccCitySelect .selectpicker').selectpicker('render');

        callbackCity ? callbackCity() : $.noop();
    }, function () {
    });
    $("#accCitySelect").change(
        function () {
            var accCity = $("#accCitySelect").val();

            // 清除默认值
            $("#oldAccCityNo").val(accCity);
            $("#oldCenterId").val("");

            var realityCityNm = $("#accCitySelect").find("option:selected").text();
            if ("请选择城市" == realityCityNm) {
                $("#centerSelect").html('');
                return false;
            }

            //设新值
            $("#accCityNo").val(accCity);

            initCenter(accCity, "", callbackDist);
        });
}


function initCenter(accCity, defultVal, callbackDist) {
    var url = BASE_PATH + "/qtReport/getAchivAchievementLevelSettingList/" + accCity;
    var params = {};

    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择业绩归属中心</option>";
        $.each(data.returnValue, function (n, value) {
            result += "<option value='" + value.centerGroupId + "'>"
                + value.centerGroupName + "</option>";
        });
        $("#centerSelect").html('');
        $("#centerSelect").append(result);
        // 默认值赋值
        $("#centerSelect").val(defultVal);

        callbackDist ? callbackDist() : $.noop();
    }, function () {
    });

    $("#centerSelect").change(
        function () {
            var centerId = $("#centerSelect").val();
            var centerName = $("#centerSelect").find("option:selected").text();

            //设新值
            $("#centerId").val(centerId);
            $("#centerName").val(centerName);
        });
}

/**
 * 添加返佣对象
 */
var num = 0;
function addObj() {
    var befYJFYAmount = $("#befYJFYAmount").val();
    if (befYJFYAmount == null || befYJFYAmount == "" || befYJFYAmount == undefined || befYJFYAmount == 0) {
        Dialog.alertError("应计返佣为0，不允许维护返佣对象!");
        return false;
    } else {
        var objCount = $("#objCount").val();
        if (objCount < 20) {
            $("#objCount").val(parseInt(objCount) + 1);
            num = num + 1;
        } else {
            Dialog.alertError("最多允许添加20个返佣对象!");
            return false;
        }
    }

    var trHtml = '<tr class="retCommissionTr">'
        + '<td align="center">'
        + '<input type="text" class="objTxt" style="text-align: left" placeholder="请输入返佣对象" value="" notnull="true"/>'
        + '<input type="hidden" name="companyNo"/>'
        + '<input type="hidden" name="companyName"/>'
        + '</td>'
        + '<td align="center"><input type="text" placeholder="" name="yjfybefTaxAmount" maxlength="12" dataType="needMoney" notnull="true" oninput="this.value=this.value.replace(/\s+/g,\'\')"></td>'
        + '<td align="center"><input type="text" placeholder="" name="yjfyaftTaxAmount" maxlength="12" dataType="needMoney" notnull="true" oninput="this.value=this.value.replace(/\s+/g,\'\')"></td>'
        + '</td>'
        + '<td align="center">'
        + '<a href="#" onclick="javascript:removeObj(this)"> 删除</a>'
        + '</td>'
        + '</tr>';

    $(".retCommission").append(trHtml);

    //加载事件
    $("#errorMsg").empty().html("");
    var dataArray = [];
    var dataArray2 = [];
    var jsonStr = "";

    var url = BASE_PATH + "/lnkYjReport/getCompanyByCondition";
    var projectNo = $("#projectNo").val();
    var params = {projectNo: projectNo};
    ajaxGet(url, params, function (data) {
        $.each(data.returnValue, function (n, value) {
            dataArray.push({
                id: value.companyNo,
                label: value.companyName + "(" + value.companyNo + ")",
                value: value.companyName
            });
            dataArray2.push(value.companyName + "(" + value.companyNo + ")");
            jsonStr += value.companyName + "(" + value.companyNo + ")";
        });
    }, function () {
    });

    var option = {
        max: 15,    //列表里的条目数
        minChars: 0,    //自动完成激活之前填入的最小字符
        width: 500,     //提示的宽度，溢出隐藏
        scrollHeight: 200,   //提示的高度，溢出显示滚动条
        matchContains: false,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
        autoFill: true,    //自动填充
        minLength: 1,
        select: function (event, ui) {
            $("#errorMsg").empty().html("");
            $(this).val(ui.item.label);
            $(this).parent().find("input[name='companyName']").val(ui.item.value);
            $(this).parent().find("input[name='companyNo']").val(ui.item.id);
            return false;
        }, change: function (event, ui) {
            var containFlag = $.inArray(this.value, dataArray2);
            if (containFlag < 0) {
                $("#errorMsg").empty().html("");
                $(this).parent().find("input[name='companyName']").val("");
                $(this).parent().find("input[name='companyNo']").val("");
                if (jsonStr.indexOf(this.value) < 0) {
                    $("#errorMsg").empty().html("暂无匹配到当前输入返佣对象!");
                    $("#errorMsg").css("visibility", "initial");
                    return false;
                }
            }
        }
    };

    $(".objTxt").autocomplete({source: dataArray}, option).on('focus', function () {
        $(this).keydown();
    });
}

function removeObj(obj) {
    $(obj).parent().parent().remove();
    var objCount = $("#objCount").val();
    $("#objCount").val(objCount - 1);
}

function isNullEmpty(obj) {
    if (obj == null || obj == "" || obj == undefined) {
        return true;
    } else {
        return false;
    }
}

function successSale() {
    $(".fc-warning").empty();
    $("#errorMsg").empty();
    systemLoading("", true);

    var srType = $("#srType").val();
    if (isNullEmpty(srType)) {
        Dialog.alertError("请选择收入类型！");
        systemLoaded();
        return false;
    }

    var befYJFYAmount = $("#befYJFYAmount").val();
    var aftYJFYAmount = $("#aftYJFYAmount").val();

    if (befYJFYAmount > 0 || aftYJFYAmount > 0) {
        //检查应计返佣不为0 返佣对象必须维护
        if ($("input[name='yjfybefTaxAmount']").length <= 0 || $("input[name='yjfyaftTaxAmount']").length <= 0) {
            Dialog.alertError("应计返佣不为0，请维护返佣对象！");
            systemLoaded();
            return false;
        }

        //检查应计返佣税前/应计返佣税后与维护返佣对象的应计返佣税前总和/应计返佣税后总和一致
        var yjfybefTaxAmount = 0;
        $("input[name='yjfybefTaxAmount']").each(function () {
            var bef = $(this).val();
            yjfybefTaxAmount = yjfybefTaxAmount + parseFloat(bef);
        });
        if (parseFloat(befYJFYAmount).toFixed(2) != parseFloat(yjfybefTaxAmount).toFixed(2)) {
            Dialog.alertError("应计返佣税前金额与维护返佣对象的应计返佣税前总和不一致！");
            systemLoaded();
            return false;
        }

        var yjfyaftTaxAmount = 0;
        $("input[name='yjfyaftTaxAmount']").each(function () {
            var aft = $(this).val();
            yjfyaftTaxAmount = yjfyaftTaxAmount + parseFloat(aft);
        });
        if (parseFloat(aftYJFYAmount).toFixed(2) != parseFloat(yjfyaftTaxAmount).toFixed(2)) {
            Dialog.alertError("应计返佣税后金额与维护返佣对象的应计返佣税后总和不一致！");
            systemLoaded();
            return false;
        }

        //当应计返佣税前金额大于0时，返佣确认函附件必须上传
        if ($('#fileIdPhotoToFY> .item-photo-list').length && $('#fileIdPhotoToFY> .item-photo-list').length > 0) {
        }
        else {
            Dialog.alertError("请上传返佣确认函！");
            systemLoaded();
            return false;
        }
    }

    if ($('#fileIdPhotoToDeal> .item-photo-list').length && $('#fileIdPhotoToDeal> .item-photo-list').length > 0) {
    }
    else {
        Dialog.alertError("请上传成销确认书/佣金结算资料！");
        systemLoaded();
        return false;
    }

    handlerFileInfo();

    var url = BASE_PATH + '/qtSuccessSale/successSale';
    if (Validator.validForm("qtSuccessSaleHandle")) {
        var fyList = [];
        $(".retCommissionTr").each(function () {
            var companyNo = $(this).find("input[name='companyNo']").val();
            var companyName = $(this).find("input[name='companyName']").val();
            var yjfyaftTaxAmount = $(this).find("input[name='yjfyaftTaxAmount']").val();
            var yjfybefTaxAmount = $(this).find("input[name='yjfybefTaxAmount']").val();

            var obj = {
                companyNo: companyNo,
                companyName: companyName,
                yjfybefTaxAmount: yjfybefTaxAmount,
                yjfyaftTaxAmount: yjfyaftTaxAmount
            }
            fyList.push(obj);
        });
        $("#fyList").val(JSON.stringify(fyList));

        httpPost('qtSuccessSaleHandle', url, function (data) {
            window.history.back(-1);
        }, function (data) {
            Dialog.alertError(data.returnMsg);
            systemLoaded();
        });
    } else {
        systemLoaded();
    }
}

//处理文件信息
function handlerFileInfo(){
    var bol = true;
    //附件其它
    var fileRecordMainIds = "";
    $("input[name=fileRecordMainIdHidden]").each(function(){
        if($(this).val()==""){
            Dialog.alertError("图片上传出现异常，请删除重新上传。");
            bol = false;
            return false;
        }
        fileRecordMainIds += ","+$(this).val();
    })
    if(fileRecordMainIds!=""){
        fileRecordMainIds = fileRecordMainIds.substring(1);
    }
    $("#fileRecordMainIds").val(fileRecordMainIds);
    return bol;
}

function backPre() {
    window.history.back(-1);
}