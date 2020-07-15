/**
 * Created by yinkun on 2018/8/14.
 */

$(function () {
    $("#content").css("height", "500px");
    $("#content").css("overflow", "auto");

    // 核算主体
    getOALnkAccountProjectList();

    var options = {
        "url": BASE_PATH + '/file/uploadCommonFile',
        "isDeleteImage": false,//删除时校验checkEditImage
        "isAddImage": true,   //添加时校验checkEditImage
        "isCommonFile": true  //公共上传文件
    };
    photoUploader(options, null, null, null);

    //计算合计
    evalTotal();

});


function getOALnkAccountProjectList() {
    var listSize = $("#listSize").val();

    for (i = 0; i < listSize; i++) {
        $("#lnkAccountProjectCode" + (i + 1)).html('');
    }
    var url = BASE_PATH + "/cashBill/getOALnkAccountProjectList";
    var projectNo = $("#projectNo").val();
    if (isNullOrEmpty(projectNo)) {
        var params = {projectNo: projectNo};
        ajaxGet(url, params, function (data) {
            var dataLength = data.returnValue.length;
            var result = "<option data-lnkaccountProject='' data-oaProjectNo= '' data-oaProjectName='' value=''>请选择核算主体</option>";
            $.each(data.returnValue, function (n, value) {
                if (dataLength > 1) {
                    result += "<option value='" + value.lnkaccountProjectCode
                        + "'data-lnkaccountProject='" + value.lnkAccountProject
                        + "'data-oaProjectNo='" + value.oaProjectNo
                        + "'data-oaProjectName='" + value.oaProjectName
                        + "'>"
                        + value.lnkaccountProjectCode + "_" + value.lnkAccountProject + "</option>";
                }
                if (dataLength > 0 && dataLength < 2) {
                    result += "<option value='" + value.lnkaccountProjectCode
                        + "'data-lnkaccountProject='" + value.lnkAccountProject
                        + "'data-oaProjectNo='" + value.oaProjectNo
                        + "'data-oaProjectName='" + value.oaProjectName
                        + "' selected>"
                        + value.lnkaccountProjectCode + "_" + value.lnkAccountProject + "</option>";
                }
            });
            for (i = 0; i < listSize; i++) {
                $("#accountProjectNo" + (i)).append(result);
                $("#accountProjectNo" + (i)).val($("#oldAccountProject" + (i)).val());
            }
        }, function () {
        });
    }
}


function isNullOrEmpty(obj) {
    if (obj == null || obj == "" || obj == undefined) {
        return false;
    } else {
        return true;
    }
}

function checkEditImage(files) {
    var bol = true;
    var fileSize = 0;

    for (var i = 0; i < files.length; i++) {
        fileSize = files[i].size;
        var photoExt = files[i].name.substr(files[i].name.lastIndexOf(".")).toLowerCase();// 获得文件后缀名
        if (photoExt != '.jpg' && photoExt != '.png') {
            Dialog.alertInfo("请上传后缀名为jpg、png的文件!");
            systemLoaded();
            bol = false;
            return bol;
        }

        if (fileSize > 5000 * 1024) {
            Dialog.alertInfo("所选文件不能大于5M！");
            self.value = null;
            systemLoaded();
            bol = false;
            return bol;
        }
    }

    return bol;
}


function removeFromCashBill(repRowId, comRowId, proRowId, index, obj) {
    ajaxGet(BASE_PATH + "/cashBill/removeFromCashBill", {
        "repRowId": repRowId,
        "comRowId": comRowId,
        "proRowId": proRowId
    }, function (data) {
        Dialog.alertSuccess(data.returnMsg);
        var eleTr = $(obj).parent().parent();
        $(eleTr).empty().remove();
        $("#companyTb2_fix_"+index).find("tr[detail='"+repRowId+"']").remove();
        evalTotal_ObjIndex(index, $("#companyTb2_" + index));
        if ($("#companyTb2_" + index).find("tr[name='detail']").length == 0) {

            $("#companyTb2_" + index).empty().remove();
            $("#companyTb2_fix_"+index).empty().remove();
            $("#divcompany_"+index).empty().remove();
            $("#companyTb1_" + index).empty().remove();
            $("#companyHr_" + index).empty().remove();
        }

        if ($("table[tag='companyTbl']").length == 0) {
            var estateId = $("#estateId").val();
            location.href = BASE_PATH + "/sceneEstate/qSceneRecognition/" + estateId + "?searchParam=1";
        }
    }, function (data) {
        Dialog.alertError(data.returnMsg);
    })
}

function saveCashBill(submitStatus) {
	//判断请款备注长度
	var flag = true;
    $(".remarksClass").each(function(i, n){
    		var eachRemarks = $(n).val().replace(/(^\s*)|(\s*$)/g, "");
    		$(n).val(eachRemarks);
    		var remarksLen = eachRemarks.length;
    		if(remarksLen != undefined && remarksLen != null && remarksLen >=200){
    			Dialog.alertInfo("第"+(i+1)+"条经纪公司的请款备注已超200字，请缩减后再提交！");
    			flag=false;
    		}
    	}
    );
    if(!flag){
    	return flag;
    }
    systemLoading("", true);
    $("input[type='button']").attr("disabled", "disabled");
    //1是提交
    if (submitStatus == 1) {
        $("#submitStatus").val(1);

        if(chkSubmit()==false){
            $("input[type='button']").removeAttr("disabled");
            systemLoaded();
            return false;
        }

    } else {
        $("#submitStatus").val(0);
    }
    handlerFileInfo();

    var url = BASE_PATH + "/cashBill/saveOACashBill";
    var param = $("#cashBillForm").serialize();

    var estateId = $("#estateId").val();
    $.ajax({
        type: 'POST',
        url: url,
        data: param,
        dataType: "json",
        success: function (data) {
            $("input[type='button']").removeAttr("disabled");
            if ("200" == data.returnCode) {
                Dialog.alertSuccess(data.returnMsg);
//                BatchCashBill.close(1);
                location.href = BASE_PATH + "/sceneEstate/qSceneRecognition/" + estateId + "?searchParam=1";
                systemLoaded();
            }else{
                Dialog.alertError(data.returnMsg);
                systemLoaded();
            }
        },
        error: function (data) {
            $("#errMsg").html(data.returnMsg);
            $("input[type='button']").removeAttr("disabled");
            systemLoaded();
        }
    });
}

function chkSubmit(){

    var returnFlag = true;
    // 必填项
    if (!Validator.validForm("cashBillForm")) {
        return false;
    }

    // 上传文件处理
    if ($('#fileIdPhotoToCashBill> .item-photo-list').length && $('#fileIdPhotoToCashBill> .item-photo-list').length > 0) {
    }
    else {
        $("#errMsg").html("请上传成销确认书/佣金结算资料！");
    }

    $("input[tag='requestAmount']").each(function(i){
        var requestAmount =$(this).val();
        var tr =  $(this).parent().parent();
        var reportNo = $(tr).find("input[tag='reportNo']").val();
        var taxAmount = $(tr).find("input[tag='taxAmount']").val();
        // 提交时校验每个报备请款金额应大于0，否则提示： 报备编号BB2018080600002请款金额应大于0！
        if(Number(requestAmount)<=0){
            returnFlag = false
            Dialog.alertError("报备编号"+reportNo+"请款金额应大于0！")
            return false;
        }
        //提示 报备编号BB2018080600002不符合请款条件，请从请款列表中删除。
        var requestType=  $(tr).find("input[tag='requestType']").val();
        if( requestType== 0){
            returnFlag = false
            Dialog.alertError("报备编号"+reportNo+"不符合请款条件，请从请款列表中删除。")
            return false;
        }

        // 提交时校验每个报备请款金额应小于等于应返金额，否则提示： 报备编号BB2018080600002请款金额不能大于应返金额！
        var sqYjfyAmount  =  $(tr).find("input[tag='sqYjfyAmount']").val();
        if(requestType==1 && Number(requestAmount)>Number(sqYjfyAmount)){
            returnFlag = false
            Dialog.alertError("报备编号"+reportNo+"请款金额不能大于应返金额！")
            return false;
        }




        var taxrate = taxAmount / requestAmount;
        var taxFlag = false;
        if (
            (taxrate >= 0.166 && taxrate <= 0.175) ||
            (taxrate >= 0.156 && taxrate <= 0.165) ||
            (taxrate >= 0.126 && taxrate <= 0.135) ||
            (taxrate >= 0.106 && taxrate <= 0.115) ||
            (taxrate >= 0.096 && taxrate <= 0.105) ||
            (taxrate >= 0.056 && taxrate <= 0.065) ||
            (taxrate >= 0.046 && taxrate <= 0.055) ||
            (taxrate >= 0.026 && taxrate <= 0.035) ||
            (taxrate >= 0.012 && taxrate <= 0.017) || taxrate == 0){
            taxFlag = true;

        }
        if(!taxFlag){
            returnFlag = false;
            Dialog.alertError("报备编号"+reportNo+"税率没有落在税率区间内 请修改");
            return false;
        }



    });

    return returnFlag;
}

function handlerFileInfo() {
    var bol = true;
    //附件其它
    var fileRecordMainIds = "";
    $("input[name=fileRecordMainIdHidden]").each(function () {
        if ($(this).val() == "") {
            Dialog.alertError("图片上传出现异常，请删除重新上传。");
            bol = false;
            return false;
        }
        fileRecordMainIds += "," + $(this).val();
    })
    if (fileRecordMainIds != "") {
        fileRecordMainIds = fileRecordMainIds.substring(1);
    }
    $("#fileRecordMainIds").val(fileRecordMainIds);
    return bol;
}


/**
 * format金额
 * @param num
 * @returns
 */
function formatCurrency(num) {
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
        num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
        cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
        num = num.substring(0, num.length - (4 * i + 3)) + ',' +
            num.substring(num.length - (4 * i + 3));
    return (((sign) ? '' : '-') + num + '.' + cents);
}

/**
 * 总计-含税请款金额
 * @param obj
 * @returns
 */
function requestAmountEval(obj) {
    var item = $(obj).parents('tbody')
    var lblRequestAmountTotal = $(item).find("tr[name='total']").find("span[tag='lblRequestAmountTotal']");
    var hidRequestAmountTotal = $(item).find("tr[name='total']").find("input[tag='requestAmountTotal']");
    var total = 0;
    $(item).find("tr[name='detail']").find("input[tag='requestAmount']").each(function () {
        total = Number(total) + Number($(this).val());
    });
    $(lblRequestAmountTotal).html(formatCurrency(total));
    $(hidRequestAmountTotal).val(total);

    // 请款类别：
    evalRequestType($(obj).parents("tr[name='detail']"));

}

function evalRequestType (tr){

    var progress = $(tr).find("input[tag='progress']").val(); // 当前报备流程
    var confirmStatus = $(tr).find("input[tag='confirmStatus']").val(); // 当前报备状态

    // 如果当前流程状态不是大定或者不是成销的时候
    if(progress!=13505 && (confirmStatus!=13601 || confirmStatus!=13603 )){
        setRequestType(0);
        return;
    }

    // 请款类别：
    var requestAmount =$(tr).find("input[tag='requestAmount']").val(); // 请款金额

    // 没有请款的场合
    if(requestAmount=="" || Number(requestAmount) == 0 ){
        setRequestType(tr,0);
        return;
    }

    // 如果是特殊项目且当前状态是大定的时候，默认为垫佣
    var isSpecialProject = $("#isSpecialProject").val();
    if(isSpecialProject==1 && progress==13505 &&  confirmStatus==13603 ){
        setRequestType(tr,2);
        return;
    }

    var sqYjsrAmount = $(tr).find("input[tag='sqYjsrAmount']").val();  // 应收收入
    var sqYjfyAmount = $(tr).find("input[tag='sqYjfyAmount']").val();  // 应收收入
    var sqYjdyAmount = $(tr).find("input[tag='sqYjdyAmount']").val();  // 应计垫佣

    var sqSjsrAmount = $(tr).find("input[tag='sqSjsrAmount']").val();  // 实收收入
    var sqSjfyAmount = $(tr).find("input[tag='sqSjfyAmount']").val();  // 实际返佣
    var sqSjdyAmount = $(tr).find("input[tag='sqSjdyAmount']").val();  // 实际垫佣

    //    应收收入   应收收入   应计垫佣     结果
    // 1.  ╳         ╳         ╳           -
    // 2.  ╳         ╳         ╳           -
    // 3.  ╳         √         ╳           -
    // 4.  ╳         √         √           -
    // 5.  √         ╳         ╳           -
    // 6.  √         ╳         √           -
    // 7.  √         √         ╳           返佣
    // 8.  √         √         √           正常


    // 返佣：请款金额≤实收收入-实际返佣
    if(Number(requestAmount) <= (Number(sqSjsrAmount) - Number(sqSjfyAmount)).toFixed(2)){
        // 场景7 和场景8 的情况
        if(Number(sqYjsrAmount)>0 && Number(sqYjfyAmount)>0){
            setRequestType(tr,1);
        }else{
            setRequestType(tr,0);
        }
    }
    // 垫佣：请款金额≤应计垫佣-实际垫佣
    else if(Number(requestAmount) <= (Number(sqYjdyAmount) - Number(sqSjdyAmount)).toFixed(2)){
        if(Number(sqYjdyAmount)>0 &&
            // (Number(sqYjsrAmount)<=0 && Number(sqYjfyAmount)<=0)   ||        // 场景2
            (Number(sqYjsrAmount)>0 &&  Number(sqYjfyAmount)>0 )      // 场景8
        ) {
            setRequestType(tr,2);
        }else {
            setRequestType(tr,0);
        }
    }else{
        setRequestType(tr,0);
    }

}
/*
    设置情况类型
 */
function setRequestType(tr,val){
    if(val == 1){
        $(tr).find("span[tag='requestType']").text("返佣");
        $(tr).find("input[tag='requestType']").val("1");
    }else  if(val == 2){
        $(tr).find("span[tag='requestType']").text("垫佣");
        $(tr).find("input[tag='requestType']").val("2");
    }else{
        $(tr).find("span[tag='requestType']").text("-");
        $(tr).find("input[tag='requestType']").val("0");
    }
}


/**
 * 总计-税额
 * @param obj
 * @returns
 */
function taxAmountEval(obj) {
    var item = $(obj).parents('tbody')
    var lblTaxAmountTotal =   $(item).find("tr[name='total']").find("span[tag='lbltaxAmountTotal']");
    var hidTaxAmountTotal =   $(item).find("tr[name='total']").find("input[tag='taxAmountTotal']");
    var total = 0;
    $(item).find("tr[name='detail']").find("input[tag='taxAmount']").each(function () {
        total = Number(total) + Number($(this).val());
    });
    $(lblTaxAmountTotal).html(formatCurrency(total));
    $(hidTaxAmountTotal).val(total);

}

/**
 * 总计-销售面积    大定总价    成销总价    累计税前
 应计收入    应计返佣    应计垫佣    实际收入    实际返佣    实际垫佣
 * @param
 * @returns
 */

function evalTotal() {

    $(".companyTb2").each(function (i) {
        evalTotal_ObjIndex(i, this);
        evalRequestType($(this).find("tr[name=detail]"));
    });


}

function evalTotal_ObjIndex(i, obj) {
    var areaTotal = 0.00;
    var roughAmountTotal = 0.00;
    var dealAmountTotal = 0.00;
    var sqYjsrAmountTotal = 0.00;
    var sqYjfyAmountTotal = 0.00;
    var sqYjdyAmountTotal = 0.00;
    var sqSjsrAmountTotal = 0.00;
    var sqSjfyAmountTotal = 0.00;
    var sqSjdyAmountTotal = 0.00;
    var requestAmountTotal = 0.00;
    var taxAmountTotal = 0.00;

    $(obj).find("tr[name=detail]").each(function (j) {

        var area = $(this).find("input[tag='area']").val();
        var roughAmount = $(this).find("input[tag='roughAmount']").val();
        var dealAmount = $(this).find("input[tag='dealAmount']").val();
        var sqYjsrAmount = $(this).find("input[tag='sqYjsrAmount']").val();
        var sqYjfyAmount = $(this).find("input[tag='sqYjfyAmount']").val();
        var sqYjdyAmount = $(this).find("input[tag='sqYjdyAmount']").val();
        var sqSjsrAmount = $(this).find("input[tag='sqSjsrAmount']").val();
        var sqSjfyAmount = $(this).find("input[tag='sqSjfyAmount']").val();
        var sqSjdyAmount = $(this).find("input[tag='sqSjdyAmount']").val();

        var requestAmount = $(this).find("input[tag='requestAmount']").val();
        var taxAmount = $(this).find("input[tag='taxAmount']").val();

        areaTotal = areaTotal + formatAccount(area);
        roughAmountTotal = roughAmountTotal + formatAccount(roughAmount);
        dealAmountTotal = dealAmountTotal + formatAccount(dealAmount);
        sqYjsrAmountTotal = sqYjsrAmountTotal + formatAccount(sqYjsrAmount);
        sqYjfyAmountTotal = sqYjfyAmountTotal + formatAccount(sqYjfyAmount);
        sqYjdyAmountTotal = sqYjdyAmountTotal + formatAccount(sqYjdyAmount);
        sqSjsrAmountTotal = sqSjsrAmountTotal + formatAccount(sqSjsrAmount);
        sqSjfyAmountTotal = sqSjfyAmountTotal + formatAccount(sqSjfyAmount);
        sqSjdyAmountTotal = sqSjdyAmountTotal + formatAccount(sqSjdyAmount);

        requestAmountTotal = requestAmountTotal + formatAccount(requestAmount);
        taxAmountTotal = taxAmountTotal + formatAccount(taxAmount);

    });
    $(obj).find("tr[name='total']").find("span[tag='lblAreaTotal']").text(formatCurrency(areaTotal));
    $(obj).find("tr[name='total']").find("span[tag='lblAoughAmountTotal']").text(formatCurrency(roughAmountTotal));
    $(obj).find("tr[name='total']").find("span[tag='lblDealAmountTotal']").text(formatCurrency(dealAmountTotal));
    $(obj).find("tr[name='total']").find("span[tag='lblSqYjsrAmountTotal']").text(formatCurrency(sqYjsrAmountTotal));
    $(obj).find("tr[name='total']").find("span[tag='lblSqYjfyAmountTotal']").text(formatCurrency(sqYjfyAmountTotal));
    $(obj).find("tr[name='total']").find("span[tag='lblSqYjdyAmountTotal']").text(formatCurrency(sqYjdyAmountTotal));
    $(obj).find("tr[name='total']").find("span[tag='lblSqSjsrAmountTotal']").text(formatCurrency(sqSjsrAmountTotal));
    $(obj).find("tr[name='total']").find("span[tag='lblSqSjfyAmountTotal']").text(formatCurrency(sqSjfyAmountTotal));
    $(obj).find("tr[name='total']").find("span[tag='lblSqSjdyAmountTotal']").text(formatCurrency(sqSjdyAmountTotal));
    $(obj).find("tr[name='total']").find("span[tag='lblRequestAmountTotal']").text(formatCurrency(requestAmountTotal));
    $(obj).find("tr[name='total']").find("span[tag='lbltaxAmountTotal']").text(formatCurrency(taxAmountTotal));


    $(obj).find("tr[name='total']").find(" input[tag='areaTotal']").val(areaTotal);
    $(obj).find("tr[name='total']").find(" input[tag='roughAmountTotal']").val(roughAmountTotal);
    $(obj).find("tr[name='total']").find(" input[tag='dealAmountTotal']").val(dealAmountTotal);
    $(obj).find("tr[name='total']").find(" input[tag='sqYjsrAmountTotal']").val(sqYjsrAmountTotal);
    $(obj).find("tr[name='total']").find(" input[tag='sqYjfyAmountTotal']").val(sqYjfyAmountTotal);
    $(obj).find("tr[name='total']").find(" input[tag='sqYjdyAmountTotal']").val(sqYjdyAmountTotal);
    $(obj).find("tr[name='total']").find(" input[tag='sqSjsrAmountTotal']").val(sqSjsrAmountTotal);
    $(obj).find("tr[name='total']").find(" input[tag='sqSjfyAmountTotal']").val(sqSjfyAmountTotal);
    $(obj).find("tr[name='total']").find(" input[tag='sqSjdyAmountTotal']").val(sqSjdyAmountTotal);
    $(obj).find("tr[name='total']").find(" input[tag='requestAmountTotal']").val(requestAmountTotal);
    $(obj).find("tr[name='total']").find(" input[tag='taxAmountTotal']").val(taxAmountTotal);

}

/**
 * 格式化文本框
 * @param o
 */
function formatter(o) {
    var arr = o.value.split('.'), nl = arr[0].length;
    var tmp = arr[0];
    if (tmp == "") {
        tmp = 0;
    }
    var ss = o.selectionStart + tmp.length - arr[0].length;
    o.value = tmp + '.' + (arr[1] || '00').substring(0, 2);
}

/**
 * 格式化金额
 * @param obj
 * @returns
 */
function formatAccount(obj) {
    if (obj == '-' || obj =="" || obj == null || obj == undefined) {
        obj = 0.00;
    } else {
        obj = parseFloat(obj.replace(/,/g, ""));
    }
    return obj;
}

/**
 * 切换核算主体 清空对应数据
 * @returns
 */
function changeAccountProject(obj) {

    var accountProjectNo = $(obj).val();
    var accountProject = $(obj).find("option:selected").attr("data-lnkaccountProject");
    var oaProjectNo = $(obj).find("option:selected").attr("data-oaProjectNo");
    var oaProjectName = $(obj).find("option:selected").attr("data-oaProjectName");
    $(obj).parents('tbody').find('input[tag=accountProject]').val(accountProject);
    $(obj).parents('tbody').find('input[tag=oaProjectNo]').val(oaProjectNo);
    $(obj).parents('tbody').find('input[tag=oaProjectName]').val(oaProjectName);

    $(obj).parents('tbody').find('input[tag="frameOaNo"]').val('');

    $(obj).parents('tbody').find('input[tag="vendorId"]').val('');
    $(obj).parents('tbody').find('input[tag="vendorCode"]').val('');
    $(obj).parents('tbody').find('input[tag="vendorName"]').val('');

    $(obj).parents('tbody').find('input[tag="receiveBankName"]').val('');
    $(obj).parents('tbody').find('input[tag="receiveBankAccountCardCode"]').val('');
    $(obj).parents('tbody').find('input[tag="receiveBankAccountName"]').val('');
    $(obj).parents('tbody').find('input[tag="receiveBankProvinceName"]').val('');
    $(obj).parents('tbody').find('input[tag="receiveBankCityName"]').val('');
    $(obj).parents('tbody').find('input[tag="receiveBankSerialNo"]').val('');

    $(obj).parents('tbody').find('input[tag="receiveBankProvinceCityName"]').val('');

    // 考核主体
    $(obj).parents('div[tag="companyDiv"]').find('select[tag="selCheckBodyId"]').html("");
    $(obj).parents('div[tag="companyDiv"]').find("input[tag='checkBodyId']").val("");
    $(obj).parents('div[tag="companyDiv"]').find("input[tag='checkBodyName']").val("");

    var url = BASE_PATH + "/cashBill/getOACheckBodyList";

    if (isNullOrEmpty(accountProjectNo)) {
        var params = {accountProjectCode: accountProjectNo};
        ajaxGet(url, params, function (data) {
            var dataLength = data.returnValue.length;
            var result = "<option data-accountProjectCode='' data-accountProjectName= '' data-checkBodyId='' data-checkBodyName='' value=''>请选择考核主体</option>";
            $.each(data.returnValue, function (n, value) {
                if (dataLength > 1) {
                    result += "<option value='" + value.checkBodyId
                        + "'data-accountProjectCode='" + value.accountProjectCode
                        + "'data-accountProjectName='" + value.accountProjectName
                        + "'data-checkBodyId='" + value.checkBodyId
                        + "'data-checkBodyName='" + value.checkBodyName
                        + "'>"+ value.checkBodyName + "</option>";
                }
                if (dataLength > 0 && dataLength < 2) {
                    result += "<option value='" + value.checkBodyId
                        + "'data-accountProjectCode='" + value.accountProjectCode
                        + "'data-accountProjectName='" + value.accountProjectName
                        + "'data-checkBodyId='" + value.checkBodyId
                        + "'data-checkBodyName='" + value.checkBodyName
                        + "' selected'>"+ value.checkBodyName + "</option>";
                }
            });

            $(obj).parents('div[tag="companyDiv"]').find('select[tag="selCheckBodyId"]').append(result);
            $(obj).parents('div[tag="companyDiv"]').find('select[tag="selCheckBodyId"]').change();

        }, function () {
        });
    }

}

/**
 * 切换考核主体  数据处理
 */
function changeCheckBody (obj) {
    var checkBodyId = $(obj).find("option:selected").attr("data-checkBodyId");
    var checkBodyName = $(obj).find("option:selected").attr("data-checkBodyName");
    $(obj).parent().find("input[tag='checkBodyId']").val(checkBodyId);
    $(obj).parent().find("input[tag='checkBodyName']").val(checkBodyName);
}


/**
 * 获取框架协议、供应商
 * @param type
 * @returns
 */
function getFrmAgreement(count, obj) {
    var url = BASE_PATH + '/cashBill/getOAFrmAgreement';
    var title = '选择框架协议';

    var accountProjectNo = $(obj).parents('tbody').find('select[tag="accountProjectNo"]').val();
    if (accountProjectNo == null || accountProjectNo == undefined || accountProjectNo == '') {
        Dialog.alertError("请选择核算主体");
        return false;
    }

    var params = {
        accountProjectCode: accountProjectNo
    };

    var dialogOptions = {

        width: 800,
        /* height : 760, */

        ok: function () {
            // 确定
            cashBillView.putFrmAgreement(count);
            cashBillView.frmAgreementdialog.close();
            return true;

        },
        okVal: '确定',
        cancel: true,
        cancelVal: '取消'
    };

    Dialog.ajaxOpenDialog(url, params, title, function (dialog, resData) {
        dialog.position('50%', 10);
        cashBillView.frmAgreementdialog = dialog;

    }, dialogOptions);

}

/**
 * 获取框架协议、供应商、收款银行
 * @param type
 * @returns
 */
function getReceiveBank(count, obj) {
    var url = BASE_PATH + '/cashBill/getOAReceiveBank';
    var title = '选择银行';

    var frameOaNo = $(obj).parents('tbody').find('input[tag="frameOaNo"]').val();
    var vendorId = $(obj).parents('tbody').find('input[tag="vendorId"]').val();
    var accountProjectNo = $(obj).parents('tbody').find('select[tag="accountProjectNo"]').val();
    if (accountProjectNo == null || accountProjectNo == undefined || accountProjectNo == '') {
        Dialog.alertError("请选择核算主体");
        return false;
    }
    if (frameOaNo == null || frameOaNo == undefined || frameOaNo == ''
        || vendorId == null || vendorId == undefined || vendorId == '') {
        Dialog.alertError("请选择框架协议");
        return false;
    }

    var params = {
        vendorId: vendorId
    };

    var dialogOptions = {
        width: 800,
        /* height : 760, */
        // 确定
        ok: function () {
            cashBillView.putReceiveBank(count);
            cashBillView.receiveBankdialog.close();
            return
        },
        okVal: '确定',
        cancel: true,
        cancelVal: '取消'
    };

    Dialog.ajaxOpenDialog(url, params, title, function (dialog, resData) {
        dialog.position('50%', 10);
        cashBillView.receiveBankdialog = dialog;

    }, dialogOptions);

}

cashBillView = function () {
    frmAgreementdialog = null;
    receiveBankdialog = null;
};

/** 点击选择公司按钮后 弹出dialog 选择完公司后 点击确认按钮 触发以下事件 */
cashBillView.putFrmAgreement = function (count) {
    var selectTravel = $(':radio[name=selectFrmAgreement]:checked');

    var frmAgreementCount = "frmAgreement" + count;
    var receiveBankcount = "receiveBank" + count;
    var receiveBankData = "receiveBankData" + count;

    var w_frameOaNo = $("#" + frmAgreementCount).find("input[tag='frameOaNo']").val();
    var t_frameOaNo = selectTravel.attr("data-frameOaNo");
    var w_vendorId = $("#" + frmAgreementCount).find("input[tag='vendorId']").val()
    var t_vendorId = selectTravel.attr("data-vendorId")
    if (w_frameOaNo != t_frameOaNo) {
        $("#" + frmAgreementCount).find("input[tag='frameOaNo']").val(selectTravel.attr("data-frameOaNo"));
        $("#" + frmAgreementCount).find("input[tag='frameOaName']").val(selectTravel.attr("data-frameOaName"));
        $("#" + frmAgreementCount).find("input[tag='vendorName']").val(selectTravel.attr("data-vendorName"));
        $("#" + frmAgreementCount).find("input[tag='vendorCode']").val(selectTravel.attr("data-vendorCode"));
        $("#" + frmAgreementCount).find("input[tag='vendorId']").val(selectTravel.attr("data-vendorId"));
    }
    if (w_vendorId != t_vendorId) {
        $("#" + frmAgreementCount).find("input[tag='vendorId']")
        $("#" + receiveBankcount).find("input[tag='receiveBankName']").val('');
        $("#" + receiveBankcount).find("input[tag='receiveBankAccountCardCode']").val('');
        $("#" + receiveBankData).find("input[tag='receiveBankAccountName']").val('');

        $("#" + receiveBankData).find("input[tag='receiveBankProvinceName']").val('');
        $("#" + receiveBankData).find("input[tag='receiveBankCityName']").val('');
        $("#" + receiveBankData).find("input[tag='receiveBankSerialNo']").val('');

        $("#" + receiveBankData).find("input[tag='receiveBankProvinceCityName']").val('');
    }

};

cashBillView.putReceiveBank = function (count, type) {
    var selectTravel = $(':radio[name=selectCompanyId]:checked');

    var receiveBankcount = "receiveBank" + count;
    var receiveBankData = "receiveBankData" + count;

    $("#" + receiveBankcount).find("input[tag='receiveBankName']").val(selectTravel.attr("data-bankName"));
    $("#" + receiveBankcount).find("input[tag='receiveBankAccountCardCode']").val(selectTravel.attr("data-bankAccountCardCode"));
    $("#" + receiveBankData).find("input[tag='receiveBankAccountName']").val(selectTravel.attr("data-bankAccountName"));

    $("#" + receiveBankData).find("input[tag='receiveBankProvinceName']").val(selectTravel.attr("data-provinceName"));
    $("#" + receiveBankData).find("input[tag='receiveBankCityName']").val(selectTravel.attr("data-cityName"));
    $("#" + receiveBankData).find("input[tag='receiveBankSerialNo']").val(selectTravel.attr("data-serialNo"));

    $("#" + receiveBankData).find("input[tag='receiveBankProvinceCityName']").val(selectTravel.attr("data-provinceName") + " " + selectTravel.attr("data-cityName"));
};


