/** ************************公共部分***************************** */
var progress = [];
$(function () {
    var msg;
    // 初始化查询
    initList();
    initDateRangeBind('#dateKbn', '#dateStart', '#dateEnd');

    $("input[name='status']:checkbox").each(function () {
        $(this).click(function () {
            if ($(this).prop('checked') == true) {
                progress.push($(this).attr('value'));
            } else {
                progress.splice(progress.indexOf($(this).attr('value')), 1);
            }
            $('#progress').val(progress.join());
            console.log(progress.join());
        });
    });

    var divHeight = $("#detailList").height();
    if (divHeight > 450) {
        $("#detailList").addClass("s-scoll-y");
    }

    //选择文件后，进行导入
    $("#historyDataFile").change(function(){
        if($(this).val() == "")
        {
            return;
        }
        dataImport();
    })
});

/** **************************demo js*************************** */
SceneRecognition = function () {
};
/**
 * 初始化查询
 */
initList = function () {

    httpGet('SceneRecognitionListForm', 'LoadCxt', BASE_PATH
        + '/sceneEstate/qSceneRecognition/q', function () {

        // 获得列表数据后 初始化CheckBox
        $('#J_checkBoxAll').click(
            function () {
                var currentStatus = $('#J_checkBoxAll').prop('checked');
                $('.J_estateItem input[type="checkbox"]').prop('checked',
                    currentStatus);
            });

        pageInfo("SceneRecognitionListForm", function () {
            initList();
        });
        var width = $("#divSceneRecognition").width();
        var height = $("#divSceneRecognition").height();
        FixTable("tbSceneRecognition", 3, 1150, height);
    });
};
/**
 * 查询
 *
 */
SceneRecognition.search = function (page) {
    if (page) {
        $('#curPage').val(page);
    } else {
        $('#curPage').val('1');
    }
    initList();
};

/**
 * 认定
 *
 */
SceneRecognition.recognition = function () {
    var itemList = $('.J_estateItem input[type="checkbox"]:checked');
    if (itemList.length <= 0) {
        Dialog.alertError('请选择需要认定的项目');
    } else {
        var reportIds = [];
        itemList.each(function () {
            $(this).parent().siblings().each(function (i) {
                if (i == 0) {
                    reportIds.push($(this).attr('value'));
                }
            });
        });
        // todo  弹出确认框
        var originUrl = location.href;
        var url = BASE_PATH + "/sceneEstate/qSceneRecognition/confirm";
        var params = {
            reportIds: reportIds.join(),
            estateId: $('input[name="estateId"]').val()
        };

        ajaxGet(url, params, function (data) {
            if (data.returnValue == 0) {
                Dialog.alertError(data.returnMsg);
            } else {
                location.href = originUrl;
            }
        }, function () {
        });
    }
};

SceneRecognition.recognitionStatus = function (reportId, status) {
    var url = BASE_PATH + "/sceneEstate/qSceneRecognition/confirm";
    var params = {
        reportId: reportId,
        estateId: $('input[name="estateId"]').val(),
        status: status
    };
    var msg = "";
    if (status == 13501) {
        msg = "确认要报备吗？";
    } else if (status == 13502) {
        msg = "确认要带看吗？";
    } else if (status == 13503) {
        msg = "确认要认筹吗？";
    } else if (status == 13504) {
        msg = "确认要大定吗？";
    } else if (status == 13505) {
        msg = "确认要成销吗？";
    } else if (status == 13506) {
        msg = "确认要退筹吗？";
    } else if (status == 13507) {
        msg = "确认要结佣吗？";
    } else if (status == 13602) {
        msg = "确认要无效吗？";
    }
    Dialog.confirm(msg, function () {
        ajaxGet(url, params, function (data) {
            if (data.returnValue == 0) {
                Dialog.alertError(data.returnMsg);
            } else {
                var page = 1;
                if ($("#pageChane").length > 0) {
                    page = $("#pageChane").val();
                }
                SceneRecognition.search(page);
            }
        }, function () {
        });
    });
};

//重置输入内容
SceneRecognition.reset = function () {
    $('select').each(function (key, value) {
        $(value).find('option').prop('selected', false);
        $(value).find('option:first').prop('selected', true);
    });
    $('form input[type="text"]').val('');
    $('form input[type="checkbox"]').attr("checked", false);
    $('#progress').val('');
    progress = [];
    SceneRecognition.search();
};

//返回项目
back = function () {
    location.href = BASE_PATH + '/sceneEstate';
};

SceneRecognition.toSceneHouseInfo = function (reportId, status) {
    var estateId = $('input[name="estateId"]').val();
    var url = null;
    var params = null;
    if (status == 13502 || status == 13503) {
        params = {
            reportId: reportId
        }
        url = BASE_PATH + "/sceneEstate/toSceneHouseInfoAddOne/" + status;
    }
    // 点击大定
    if (status == 13504) {
        url = BASE_PATH + "/sceneEstate/toSceneHouseInfoAdd";
        params = {
            reportId: reportId,
            estateId: estateId
        };
    }
    if (status == 13505) {
        url = BASE_PATH + "/sceneEstate/toSceneHouseInfoEdit";
        params = {
            reportId: reportId,
            estateId: estateId
        };
    }
    var dialogOptions = {

        width: 500,
        height: 100,
        ok: function () {
            // 确定
            var reback = SceneRecognition.updateStatus(reportId, status, estateId);
            return reback;
        },
        okVal: '保存',
        cancel: true,
        cancelVal: '取消'
    };
    Dialog.ajaxOpenDialog(url, params, "流程节点", function (dialog, resData) {
        SceneRecognition.dialog = dialog;
    }, dialogOptions);
};

function handlerFileInfo1(status) {
    if (status == 13502 || status == 13504) {
        //验证带单附件
        if ($('#fileIdPhotoToSee> .item-photo-list').length && $('#fileIdPhotoToSee> .item-photo-list').length > 0) {
        } else {
            $("#errorMsg").find(".fc-warning").empty().html("请上传带看单");
            return false;
        }
        if (status == 13504) {
            //验证大定单附件
            if ($('#fileIdPhotoBox> .item-photo-list').length && $('#fileIdPhotoBox> .item-photo-list').length > 0) {
            } else {
                $("#errorMsg").find(".fc-warning").empty().html("请上传大定单");
                return false;
            }
            //验证甲方项目负责人名片附件
            if ($('#fileIdPhotoCard> .item-photo-list').length && $('#fileIdPhotoCard> .item-photo-list').length > 0) {
            } else {
                $("#errorMsg").find(".fc-warning").empty().html("请上传甲方项目负责人名片");
                return false;
            }
        }
    }

    if (status == 13505) {
        if ($('#fileIdPhotoToDeal> .item-photo-list').length && $('#fileIdPhotoToDeal> .item-photo-list').length > 0) {
        } else {
            $("#errorMsg").find(".fc-warning").empty().html("请上传成销确认书/佣金结算资料");
            return false;
        }
    }

    var bol = true;
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

SceneRecognition.updateStatus = function (reportId, status, estateId) {
    $("#errorMsg").find(".fc-warning").empty();
    systemLoading("", true);
    var customerNm = $("#customerNm").val();
    var customerTel = $("#customerTel").val();
    var buildingNo = $("#buildingNo").val();
    var area = $("#area").val();
    var roughAmount = $("#roughAmount").val();
    var dealAmount = $("#dealAmount").val();
    var operateDate = $("#operateDate").val();
    var roughArea = $("#roughArea").val();
    var settleConfirmDate = $("#settleConfirmDate").val();

    var customerNmTwo = $("#customerNmTwo").val();
    var customerTelTwo = $("#customerTelTwo").val();

    var befYJSRAmount = $("#befYJSRAmount").val();
    befYJSRAmount = $.trim(befYJSRAmount).replaceAll(",", "");
    var aftYJSRAmount = $("#aftYJSRAmount").val();
    aftYJSRAmount = $.trim(aftYJSRAmount).replaceAll(",", "");

    var accountProjectNo = $("#accountProjectNo").val();

    var fySize = $("#fySize").val();
    var fyList = [];
    var falg = true;
    var falgIndex = 0;
    if (fySize != null && fySize != undefined) {
        for (var i = 0; i < fySize; i++) {
            var companyNo = $("#companyNo" + i).val();
            var befYJFYAmount = $("#befYJFYAmount" + i).val();
            befYJFYAmount = $.trim(befYJFYAmount).replaceAll(",", "");
            var aftYJFYAmount = $("#aftYJFYAmount" + i).val();
            aftYJFYAmount = $.trim(aftYJFYAmount).replaceAll(",", "");
            var befYJDYAmount = $("#befYJDYAmount" + i).val();
            befYJDYAmount = $.trim(befYJDYAmount).replaceAll(",", "");
            var aftYJDYAmount = $("#aftYJDYAmount" + i).val();
            aftYJDYAmount = $.trim(aftYJDYAmount).replaceAll(",", "");
            if(befYJDYAmount!=""&& befYJDYAmount!= undefined && befYJDYAmount != null && aftYJDYAmount!=""&& aftYJDYAmount!= undefined && aftYJDYAmount != null ){
                falg = true;
            }else if((befYJDYAmount==""|| befYJDYAmount== undefined || befYJDYAmount == null) && (aftYJDYAmount=="" || aftYJDYAmount== undefined || aftYJDYAmount == null)){
                falg = true;
            }else{
                falg = false;
                falgIndex = i+1;
                break;
            }
            var obj = {
                companyNo: companyNo,
                befYJFYAmount: befYJFYAmount,
                aftYJFYAmount: aftYJFYAmount,
                befYJDYAmount: befYJDYAmount,
                aftYJDYAmount: aftYJDYAmount
            };
            fyList.push(obj);
        }
    }


    if (customerNm != '' && customerNm != undefined && customerNm != null)customerNm = $.trim(customerNm);
    if (buildingNo != '' && buildingNo != undefined && buildingNo != null)buildingNo = $.trim(buildingNo);
    if (customerNmTwo != '' && customerNmTwo != undefined && customerNmTwo != null)customerNmTwo = $.trim(customerNmTwo);


    if (status == 13502 || status == 13504 || status == 13505) {
        if ($("#customerNmTwo").val() != "") {
            if ($("#customerTelTwo").val() == undefined || $("#customerTelTwo").val() == null || $("#customerTelTwo").val() == '') {
                $("#errorMsg").find(".fc-warning").empty().html("客户已填写，客户手机必须填写！");
                systemLoaded();
                $("#customerTelTwo").focus();
                return false;
            }
        }
        if ($("#customerTelTwo").val() != "") {
            if ($("#customerNmTwo").val() == undefined || $("#customerNmTwo").val() == null || $("#customerNmTwo").val() == '') {
                $("#errorMsg").find(".fc-warning").empty().html("客户手机已填写，客户必须填写！");
                systemLoaded();
                $("#customerNmTwo").focus();
                return false;
            }
        }
    }
    if (status == 13505) {
        if (buildingNo == undefined || buildingNo == null || buildingNo == '') {
            $("#errorMsg").find(".fc-warning").empty().html("楼室号不能为空！");
            systemLoaded();
            $("#buildingNo").focus();
            return false;
        }
    }

    if(!falg){
        $("#errorMsg").find(".fc-warning").empty().html("返佣对象"+falgIndex+"垫佣必须成对填写！");
        systemLoaded();
        $("#aftYJDYAmount" + falgIndex-1).focus();
        return false;
    }

    if(status == 13505){
        if(accountProjectNo == undefined || accountProjectNo == null || accountProjectNo == ''){
            $("#errorMsg").find(".fc-warning").empty().html("核算主体不能为空！");
            systemLoaded();
            $("#accountProjectNo").focus();
            return false;
        }
    }

    if (!handlerFileInfo1(status)) {
        systemLoaded();
        return false;
    }
    if (status == 13504) {
        var flag = buildingNoRepeatCount(buildingNo, reportId, 2);
        if (!flag) {
            systemLoaded();
//        	$("#errorMsg").find(".fc-warning").empty().html("该楼室号已报备，请勿重复录入！");
            $("#buildingNo").focus();
            return false;
        }
    }

    if (Validator.validForm("sceneHouseInfoForm")) {

        var params = {
            reportId: reportId,
            estateId: estateId,
            status: status,
            buildingNo: buildingNo,
            area: area,
            roughAmount: roughAmount,
            dealAmount: dealAmount,
            operateDate: operateDate,
            roughArea: roughArea,
            customerNm: customerNm,
            customerTel: customerTel,
            customerNmTwo: customerNmTwo,
            customerTelTwo: customerTelTwo,
            fileRecordMainIds: $("#fileRecordMainIds").val(),
            id: $("#id").val(),
            settleConfirmDate: settleConfirmDate,
            befYJSRAmount: befYJSRAmount,
            aftYJSRAmount: aftYJSRAmount,
            accountProjectNo:accountProjectNo,
            fyList: JSON.stringify(fyList)
        };

        $($(".ui_state_highlight").get(0)).attr("disabled", "disabled");

        var url = BASE_PATH + "/sceneEstate/qSceneRecognition/confirm/post";
        $.ajax({
            type: 'POST',
            url: url,
            data: params,
            dataType: "json",
            success: function (data) {
                systemLoaded();
                if (data.returnValue == 0) {
                    $("#errorMsg").find(".fc-warning").empty().html("操作失败");
                    return false;
                } else if (data.returnValue == 201) {
                    $($(".ui_state_highlight").get(0)).attr("disabled", false);
                    $("#errorMsg").find(".fc-warning").empty().html(data.returnMsg);
                    return false;
                } else if (data.returnValue == 202) {
                    $($(".ui_state_highlight").get(0)).attr("disabled", "disabled");
                    $("#errorMsg").find(".fc-warning").empty().html(data.returnMsg);
                    return false;
                } else {
                    SceneRecognition.close();
                    var page = 1;
                    if ($("#pageChane").length > 0) {
                        page = $("#pageChane").val();
                    }
                    SceneRecognition.search(page);
                }
            },
            error: function (data) {
                $("#errorMsg").find(".fc-warning").empty().html("操作失败");
                systemLoaded();
                return false;
            }
        });
    }
    setTimeout(function () {
        systemLoaded();
    }, 3000);
    return false;
};


SceneRecognition.rebackView = function (reportId, status) {
    var estateId = $('input[name="estateId"]').val();
    var url = BASE_PATH + "/sceneEstate/rebackView/" + status;
    var params = {"reportId": reportId};
    var dialogOptions = {
        width: 500,
        height: 100,
        ok: function () {
            // 确定
            var reback = SceneRecognition.rebackToSee(reportId, status, estateId);
            return reback;
        },
        okVal: '确定',
        cancel: true,
        cancelVal: '返回'
    };
    Dialog.ajaxOpenDialog(url, params, "新房联动回退", function (dialog, resData) {
        SceneRecognition.dialog = dialog;
    }, dialogOptions);
};

var rebackRepeatFlg = false;
SceneRecognition.rebackToSee = function (reportId, status, estateId) {
    systemLoading("", true);
    var url = BASE_PATH + "/report/reback";
    var originUrl = BASE_PATH + "/sceneEstate/qSceneRecognition/" + estateId;
    var operateDate = $("#operateDate").val();
    if (Validator.validForm("rebackForm")) {
//		var flag = checkRepeatSubmit();
//		if(flag){
//			return false;
//		}
        var params = {
            reportId: reportId,
            estateId: estateId,
            status: status,
            operateDate: operateDate
        };
        httpPut(url, params, function (data) {
            systemLoaded();
            if (data.returnValue == 201) {
                //rebackRepeatFlg = false;
                $("#errorMsg").find(".fc-warning").empty().html(data.returnMsg);
                return false;
            } else if (data.returnValue == 500) {
                //rebackRepeatFlg = false;
                $("#errorMsg").find(".fc-warning").empty().html("操作异常");
                return false;
            } else {
                SceneRecognition.close();
                var page = 1;
                if ($("#pageChane").length > 0) {
                    page = $("#pageChane").val();
                }
                SceneRecognition.search(page);
                //location.href = originUrl;
            }
        }, function (data) {
            //rebackRepeatFlg = false;
            $("#errorMsg").find(".fc-warning").empty().html("操作失败");
            systemLoaded();
            return false;
        });
    }
    setTimeout(function () {
        systemLoaded();
    }, 3000);
    return false;
};

function checkRepeatSubmit() {
    if (!rebackRepeatFlg) {
        rebackRepeatFlg = true;
        return false;//第一次提交
    } else {
        return true;//重复提交
    }
}


/**
 * 关闭弹窗
 */
SceneRecognition.close = function (clearNum) {
    SceneRecognition.dialog.close();
};

SceneRecognition.setSearchParams = function (searchParamMap) {
    var jsonMap = JSON.parse(searchParamMap);

    if (jsonMap["progress"]) {
        var statusArr = jsonMap["progress"].split(",");
        if (statusArr.length > 0) {
            var selectedArr = [];
            for (var i = 0; i < statusArr.length / 2; i++) {
                selectedArr[i] = statusArr[i * 2] + "," + statusArr[i * 2 + 1]
            }
            progress = selectedArr;
            for (var j = 0; j < selectedArr.length; j++) {
                $("input[value='" + selectedArr[j] + "']").attr("checked", "checked");
            }
        }
    }

    $("#SceneRecognitionListForm").find("select").each(function () {
        var searchKey = $(this).attr("id");
        var searchValue = jsonMap[searchKey];
        if (searchValue != undefined && searchValue != null && searchValue != '') {
            $("#" + searchKey).val(searchValue);
        }
    });

    $("#SceneRecognitionListForm").find("input").each(function () {
        var searchKey = $(this).attr("id");
        var searchValue = jsonMap[searchKey];
        if (searchValue != undefined && searchValue != null && searchValue != '') {
            $("#" + searchKey).val(searchValue);
        }
    });
    var curPage = jsonMap["curPage"];
    if (curPage > 1) {
        $('#curPageTemp').val(curPage);
        $('#sysPageChaneTemp').val(jsonMap["sysPageChane"]);
    }
};

/** **************************demo js*************************** */
BatchSale = function () {
    dialog: null;
    busNo: null;
};
BatchSale.close = function () {
    $("body").css("overflow", "initial");
    BatchSale.dialog.close();
    refleshbatchSaleNum();
}


//打开批量成销列表
function openBatchSale(projectNo, estateNm,estateId) {
    var batchSaleCount = $("#batchSaleCount").val();
    if (batchSaleCount > 0) {
        //对应查询方法
        var url = BASE_PATH + '/sceneEstate/openBatchSaleList';
        /*var params = {
         projectNo: projectNo,
         estateNm: estateNm
         };
         var dialogOptions = {
         width: 1000,
         height: "auto"
         };*/
        /*Dialog.ajaxOpenDialog(url, params, "批量成销", function (dialog, resData) {
         BatchSale.dialog = dialog;
         }, dialogOptions);
         $("body").css("overflow", "hidden");*/
        window.location.href=url+"?projectNo="+projectNo+"&estateNm="+estateNm+"&estateId="+estateId;
    } else {
        Dialog.alertError("请先添加批量成销的报备房源再操作！");
    }
}

function openCashBillPop(estateId ,obj) {
    var cashCount = $(obj).attr("data-num");
    if (cashCount > 0) {
        var url = BASE_PATH + '/cashBill/toCashBillView/' +estateId;;
        window.location.href = url
    } else {
        Dialog.alertError("请先添加批量请款的报备房源再操作！");
    }
}


/**
 * 格式化文本框
 * @param o
 */
function formatter(o) {
    var arr = o.value.split('.'), nl = arr[0].length;
    var tmp = arr[0];
    if(tmp==""){
        tmp=0;
    }
    var ss = o.selectionStart + tmp.length - arr[0].length;
    o.value = tmp + '.' + (arr[1] || '00').substring(0, 2);
}

BatchSale.addBatchSale = function (reportId) {
    var projectNo = $("#projectNo").val();
    var estateName = $("#estateName").val();
    var estateId = $("#estateId").val();

    var params = {
        projectNo: projectNo,
        estateId: estateId,
        estateName: estateName,
        reportId: reportId
    };
    var url = BASE_PATH + "/sceneEstate/addBatchSale";
    $.ajax({
        type: 'POST',
        url: url,
        data: params,
        dataType: "json",
        success: function (data) {
            if (data.returnCode == "0000") {
                if (data.returnValue.code == 1) {
                    Dialog.alertSuccess("批量成销添加成功！");
                }else  if(data.returnValue.code==3){
                    Dialog.alertInfo("批量成销操作不适用多返佣对象的房源!");
                }else {
                    Dialog.alertSuccess("已添加批量成销，请勿重复添加！");
                }
                refleshbatchSaleNum();
            } else {
                Dialog.alertInfo(data.returnMsg);
            }
        },
        error: function (data) {
            Dialog.alertInfo("添加失败！");
        }
    });
}

/**
 * 删除批量成销子表信息
 * @param batchSaleDetailId
 */
BatchSale.deleteBatchSaleDetail = function (r, batchSaleDetailId, batchId) {
    var url = BASE_PATH + "/sceneEstate/deleteBatchSaleDetailById"

    // console.log("batchSaleDetailId:"+batchSaleDetailId);
    // console.log("batchId:"+batchId);

    $.ajax({
        type: 'POST',
        url: url,
        data: {batchDetailId: batchSaleDetailId, batchId: batchId},
        dataType: "json",
        success: function (data) {
            if (data.returnCode == '0000') {
                refleshbatchSaleNum();
                var i = r.parentNode.parentNode.rowIndex;
                document.getElementById('myTable').deleteRow(i);
                document.getElementById('myTable_tableColumnClone').deleteRow(i);

                sumAcreageAndAmountNew();
                //获取table对象
                var tab = document.getElementById("myTable");
                //得到表格行数
                var rows = tab.rows;

                if (rows.length == 1) {
                    BatchSale.close();
                }
                if (rows.length == 2) {
                    var estateId = $("#estateId").val();
                    location.href = BASE_PATH + "/sceneEstate/qSceneRecognition/" + estateId + "?searchParam=1";
                }
            } else {
                $("#errorMsg").html(data.returnMsg);
            }
        },
        error: function (data) {
            Dialog.alertError("删除失败！")
        }
    });
}

/**
 * 保存
 */
BatchSale.updateBatchSaleDetailAll = function () {
    uploadContract();
    var saleDate = $("#saleDate").val();
    if (saleDate == "" || saleDate == null) {
        saleDate = null;
    }
    var fileRecordMainIds = $("#fileRecordMainIds").val();
    if (fileRecordMainIds == "" || fileRecordMainIds == null) {
        fileRecordMainIds = "";
    }
    var param = [];
    var settlementDate = $("#settlementDate").val();
    if (settlementDate == "" || settlementDate == null) {
        settlementDate = null;
    }//获取table对象
    var tab = document.getElementById("myTable");
    //得到表格行数
    var rows=tab.rows;
    var accountProjectList = [];
    for(var i=1;i<rows.length-1;i++) { //遍历表格的行,表头不计算在内，故从1开始
        var accountProject={};
        accountProject.accountProject =  tab.rows[i].cells[9].getElementsByTagName("INPUT")[0].value.replaceAll(',','');
        accountProjectList.push(accountProject);
    }
    var url = BASE_PATH + "/sceneEstate/checkAccountProject";
    $.post(url,{accountProjectList:JSON.stringify(accountProjectList)},function(data){
        data = JSON.parse(data);
        if(data.returnCode != '200'){
            Dialog.alertInfo(data.returnMsg);
            return false;
        }else{
            systemLoading("", true);
            var param = tableInfo();
            var url = BASE_PATH + "/sceneEstate/updateBatchSaleDetailAll";
            $.ajax({
                type: 'POST',
                url: url,
                data: {param: JSON.stringify(param)},
                dataType: "json",
                success: function (data) {
                    if (data.returnCode == '0000') {
                        Dialog.alertSuccess("保存成功！");
                        systemLoaded();
                        var estateId = $("#estateId").val();
                        window.location.href = BASE_PATH + '/sceneEstate/qSceneRecognition/'+estateId;
                    } else {
                        $("#errorMsg").html(data.returnMsg);
                        systemLoaded();
                    }
                },
                error: function (data) {
                    $("#errorMsg").html("保存失败！");
                    systemLoaded();
                }
            });
        }
    });

}


BatchCashBill = function () {
};

BatchCashBill.close = function (clearNum) {
    BatchCashBill.dialog.close();
    $("body").css("overflow", "initial");
    if ($("#toCashBillPop").length > 0 || (clearNum != undefined && clearNum == 1)) {
        refleshCashBillNum();
    }
};

function refleshCashBillNum() {
    var estateId = $("input[name='estateId']").val();
    ajaxGet(BASE_PATH + "/cashBill/getBatchCashNumber", {"estateId": estateId}, function (data) {
        if (parseInt(data.returnData) > 0) {
            $("#toCashBillPop").html("批量请款(" + data.returnData + "套)");
            $("#toCashBillPop").attr("data-num", data.returnData);
        } else {
            $("#toCashBillPop").html("批量请款(0套)");
            $("#toCashBillPop").attr("data-num", 0);
        }
    }, function (data) {
        Dialog.alertInfo(data.returnMsg);
    });
}

BatchCashBill.addToBatchCash = function (reportId) {
    ajaxGet(BASE_PATH + "/cashBill/addToBatchCash", {"reportId": reportId}, function (data) {
        Dialog.alertSuccess(data.returnMsg);
        refleshCashBillNum();
    }, function (data) {
        Dialog.alertInfo(data.returnMsg);
    })
};

BatchCashBill.fnToCashBillPop = function (estateId) {
    if (parseInt($("#toCashBillPop").attr("data-num")) > 0) {
        var url = BASE_PATH + "/cashBill/toCashBillPop";
        var params = {"estateId": estateId};
        var dialogOptions = {
            width: 1000,
        };
        Dialog.ajaxOpenDialog(url, params, "批量请款", function (dialog) {
            BatchCashBill.dialog = dialog;
        }, dialogOptions);

        $("body").css("overflow", "hidden");
    } else {
        Dialog.alertInfo("请先添加批量请款的报备房源再操作！")
    }
};

/**
 * 批量成销总套数
 */
function refleshbatchSaleNum() {
    var estateId = $("input[name='estateId']").val();
    var url = BASE_PATH + "/sceneEstate/countBatchSaleDetailAll";
    $.ajax({
        type: 'POST',
        url: url,
        data: {estateId: estateId},
        dataType: "json",
        success: function (data) {
            $("#toBatchSalePop").html("批量成销(" + data.returnValue + "套)");
            $("#batchSaleCount").val(data.returnValue);
        },
        error: function (data) {
        }
    });
};

/***
 * 提交批量成销
 */
BatchSale.submitBatchSale = function () {
    $("#saleDateMsg").html("");
    $("#fileRecordMsg").html("");
    $("#errorMsg").html("");

    //禁用按钮
    $("#submitBut").attr("disabled", "disabled");
    $("#saveBut").attr("disabled", "disabled");

    var saleDate = $("#saleDate").val();
    if (phoneCheckNew()) {
        $("#submitBut").removeAttr("disabled");
        $("#saveBut").removeAttr("disabled");
        $("#errorMsg").html("成销房源信息填写不全/手机号填写不正确");
        systemLoaded();
    }
    else if (saleDate == "" || saleDate == null) {
        $("#submitBut").removeAttr("disabled");
        $("#saveBut").removeAttr("disabled");
        $("#saleDateMsg").html("请先选择成销日期，再进行提交");
        systemLoaded();
    } else if (uploadContract()) {
        //获取table对象
        var tab = document.getElementById("myTable");
        //得到表格行数
        var rows=tab.rows;
        var accountProjectList = [];
        for(var i=1;i<rows.length-1;i++) { //遍历表格的行,表头不计算在内，故从1开始
            var accountProject={};
            accountProject.accountProject = tab.rows[i].cells[9].getElementsByTagName("INPUT")[0].value.replaceAll(',','');
            accountProjectList.push(accountProject);
        }
        var url = BASE_PATH + "/sceneEstate/checkAccountProject";
        $.post(url,{accountProjectList:JSON.stringify(accountProjectList)},function(data){
            data = JSON.parse(data);
            if(data.returnCode != '200'){
                Dialog.alertInfo(data.returnMsg);
                $("#submitBut").removeAttr("disabled");
                $("#saveBut").removeAttr("disabled");
                return false;
            }else{
                systemLoading("", true);
                var param = tableInfo();
                var url2 = BASE_PATH + "/sceneEstate/updateBatchSaleDetailAll";
                $.ajax({
                    type: 'POST',
                    url: url2,
                    data: {param: JSON.stringify(param)},
                    dataType: "json",
                    success: function (data) {
                        //保存成功之后成销
                        if (data.returnCode == '0000') {
                            var url = BASE_PATH + "/sceneEstate/submitBatchSaleAll";
                            $.ajax({
                                type: 'POST',
                                url: url,
                                data: {param: JSON.stringify(param)},
                                dataType: "json",
                                success: function (data) {
                                    if (data.returnCode == '0002') {
                                        $("#submitBut").removeAttr("disabled");
                                        $("#saveBut").removeAttr("disabled");
                                        $("#errorMsg").html("报备编号：" + data.returnValue + "不符合成销条件，请删除后再提交!");
                                        systemLoaded();
                                    } else if (data.returnCode == '0000') {
                                        Dialog.alertSuccess("提交成功！");
                                        refleshbatchSaleNum();
                                        var estateId = $("#estateId").val();
                                        window.location.href = BASE_PATH + '/sceneEstate/qSceneRecognition/'+estateId;
                                    } else {
                                        $("#submitBut").removeAttr("disabled");
                                        $("#saveBut").removeAttr("disabled");
                                        $("#errorMsg").html(data.returnMsg);
                                        systemLoaded();
                                    }

                                },
                                error: function (data) {
                                    $("#submitBut").removeAttr("disabled");
                                    $("#saveBut").removeAttr("disabled");
                                    $("#errorMsg").html("提交异常");
                                    systemLoaded();
                                }
                            });
                        } else {
                            //保存失败
                            $("#submitBut").removeAttr("disabled");
                            $("#saveBut").removeAttr("disabled");
                            $("#errorMsg").html("保存失败！");
                            systemLoaded();
                        }
                    },
                    error: function (data) {
                        $("#submitBut").removeAttr("disabled");
                        $("#saveBut").removeAttr("disabled");
                        $("#errorMsg").html("保存异常");
                        systemLoaded();
                    }
                });
            }
        });
    } else {
        $("#submitBut").removeAttr("disabled");
        $("#saveBut").removeAttr("disabled");
        $("#fileRecordMsg").html("请先上传成销确认书/佣金结算资料，再进行提交");
        systemLoaded();
    }
}

function sumAcreageAndAmount() {
    var listSize = $("#listSize").val();
    var countAcreage = 0.00;
    var countAmount = 0.00;
    var countBefYjsr = 0.00;
    var countAftYjsr = 0.00;
    var countBefYjfy = 0.00;
    var countAftYjfy = 0.00;
    var countBefYjdy = 0.00;
    var countAftYjdy = 0.00;
    for (var i = 0; i < listSize; i++) {
        countAcreage = Number(countAcreage) + Number($("#saleAcreage_" + i).val());
        countAmount = Number(countAmount) + Number($("#saleAmount_" + i).val());
        countBefYjsr = Number(countBefYjsr) + Number($("#myTable #befYjsrTaxAmount_" + i).val());
        countAftYjsr = Number(countAftYjsr) + Number($("#myTable #aftYjsrTaxAmount_" + i).val());
        countBefYjfy = Number(countBefYjfy) + Number($("#myTable #befYjfyTaxAmount_" + i).val());
        countAftYjfy = Number(countAftYjfy) + Number($("#myTable #aftYjfyTaxAmount_" + i).val());
        countBefYjdy = Number(countBefYjdy) + Number($("#myTable #befYjfyTaxAmount_" + i).val());
        countAftYjdy = Number(countAftYjdy) + Number($("#myTable #aftYjfyTaxAmount_" + i).val());
    }
    $("#countAcreage").html(toDecimal2(countAcreage));
    $("#countAmount").html(number_format(toDecimal2(countAmount), 2, ".", ","));
    $("#myTable #countBefYjsr").html(number_format(toDecimal2(countBefYjsr), 2, ".", ","));
    $("#myTable #countAftYjsr").html(number_format(toDecimal2(countAftYjsr), 2, ".", ","));
    $("#myTable #countBefYjfy").html(number_format(toDecimal2(countBefYjfy), 2, ".", ","));
    $("#myTable #countAftYjfy").html(number_format(toDecimal2(countAftYjfy), 2, ".", ","));
    $("#myTable #countBefYjdy").html(number_format(toDecimal2(countBefYjfy), 2, ".", ","));
    $("#myTable #countAftYjdy").html(number_format(toDecimal2(countAftYjfy), 2, ".", ","));
}


function toDecimal2(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return false;
    }
    var f = Math.round(x * 100) / 100;
    var s = f.toString();
    var rs = s.indexOf('.');
    if (rs < 0) {
        rs = s.length;
        s += '.';
    }
    while (s.length <= rs + 2) {
        s += '0';
    }
    return s;
}
function uploadContract() {
    var upload = true;

    if ($('#fileIdPhotoToDeal> .item-photo-list').length && $('#fileIdPhotoToDeal> .item-photo-list').length > 0) {
    } else {
        $("#errorMsg").find(".fc-warning").empty().html("请上传成销确认书/佣金结算资料");
        upload = false;
    }
    var fileRecordMainIds = "";
    $("input[name=fileRecordMainIdHidden]").each(function () {
        if ($(this).val() == "") {
            Dialog.alertError("图片上传出现异常，请删除重新上传。");
            bol = false;
            upload = false;
        }
        fileRecordMainIds += "," + $(this).val();
    })
    if (fileRecordMainIds != "") {
        fileRecordMainIds = fileRecordMainIds.substring(1);
    }
    $("#fileRecordMainIds").val(fileRecordMainIds);

    return upload;
}

//获取表格数据并拼装JSON
function tableInfo() {
    var param = [];
    //成销时间
    var saleDate = $("#saleDate").val();
    if (saleDate == "" || saleDate == null) {
        saleDate = null;
    }
    //结算确认日期
    var settlementDate = $("#settlementDate").val();
    if (settlementDate == "" || settlementDate == null) {
        settlementDate = null;
    }
    //协议
    var fileRecordMainIds = $("#fileRecordMainIds").val();
    if (fileRecordMainIds == "" || fileRecordMainIds == null) {
        fileRecordMainIds = "";
    }
    //获取table对象
    var tab = document.getElementById("myTable");
    //得到表格行数
    var rows=tab.rows;
    var accountProjectList = [];
    for(var i=1;i<rows.length-1;i++) { //遍历表格的行,表头不计算在内，故从1开始
        var detail={};
        var accountProject={};
        detail.batchDetailId = tab.rows[i].cells[3].getElementsByTagName("INPUT")[0].value;//第四列 第一个INPUT的值 批量成销子表ID
        detail.reportId=tab.rows[i].cells[3].getElementsByTagName("INPUT")[1].value;//第四列 第二个INPUT的值 批量成销报备编号
        detail.batchId=tab.rows[i].cells[3].getElementsByTagName("INPUT")[2].value;//第四列 第三个INPUT的值 批量成销主表ID
        detail.companyNo=tab.rows[i].cells[3].getElementsByTagName("INPUT")[3].value;//第四列 第四个INPUT的值 批量公司名称
        detail.floor=tab.rows[i].cells[3].getElementsByTagName("INPUT")[4].value;//第四列 第五个INPUT的值 批量成销楼室号
        detail.customerName1 = tab.rows[i].cells[3].getElementsByTagName("INPUT")[5].value;//第四列 第7个INPUT的值  批量成销客户姓名
        detail.customerPhone1 = tab.rows[i].cells[4].getElementsByTagName("INPUT")[0].value;//第五列 第1个INPUT的值 批量成销客户手机号
        detail.customerName2 = tab.rows[i].cells[5].getElementsByTagName("INPUT")[0].value;//第六列 第1个INPUT的值 批量成销客户姓名2
        detail.customerPhone2 =tab.rows[i].cells[6].getElementsByTagName("INPUT")[0].value;//第七列 第1个INPUT的值 批量成销客户手机号2
        detail.saleAcreage = tab.rows[i].cells[7].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第八列 第1个INPUT的值 批量成销面积
        detail.saleAmount = tab.rows[i].cells[8].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第九列 第1个INPUT的值 批量成销金额
        detail.accountProject = tab.rows[i].cells[9].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第九列 第1个INPUT的值 批量成销金额
        detail.befYjsrTaxAmount = tab.rows[i].cells[10].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第十列 第1个INPUT的值 应计收入税前
        detail.aftYjsrTaxAmount = tab.rows[i].cells[11].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第十一列 第1个INPUT的值 应计收入税后
        detail.befYjfyTaxAmount = tab.rows[i].cells[12].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第十二列 第1个INPUT的值 应计返佣税前
        detail.aftYjfyTaxAmount = tab.rows[i].cells[13].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第十三列 第1个INPUT的值 应计返佣税后
        detail.befYjdyTaxAmount = tab.rows[i].cells[14].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第十四列 第1个INPUT的值 应计垫佣税前
        detail.aftYjdyTaxAmount = tab.rows[i].cells[15].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第十五列 第1个INPUT的值 应计垫佣税后

        if(detail.befYjsrTaxAmount == ""){detail.befYjsrTaxAmount = null;}
        if(detail.aftYjsrTaxAmount == ""){detail.aftYjsrTaxAmount = null;}
        if(detail.befYjfyTaxAmount == ""){detail.befYjfyTaxAmount = null;}
        if(detail.aftYjfyTaxAmount == ""){detail.aftYjfyTaxAmount = null;}
        if(detail.befYjdyTaxAmount == ""){detail.befYjdyTaxAmount = null;}
        if(detail.aftYjdyTaxAmount == ""){detail.aftYjdyTaxAmount = null;}

        detail.saleDate=saleDate;//成销时间
        detail.settlementDate=settlementDate;//结算确认日期
        detail.fileRecordMainIds=fileRecordMainIds;//协议
        accountProject.accountProject =  detail.accountProject;
        param.push(detail);
        accountProjectList.push(accountProject);
    }
    console.log(param);
    return param;
}

//输入参数校验
function phoneCheckNew() {
    var isValid = false;
    var myreg = /^[1][0-9]{10}$/;
    //获取table对象
    var tab = document.getElementById("myTable");
    //得到表格行数
    var rows = tab.rows;
    for (var i = 1; i < rows.length-1; i++) { //遍历表格的行,表头不计算在内，故从1开始
        var customerName1 = tab.rows[i].cells[3].getElementsByTagName("INPUT")[5].value;//第四列 第7个INPUT的值  批量成销客户姓名
        var customerPhone1 = tab.rows[i].cells[4].getElementsByTagName("INPUT")[0].value;//第五列 第1个INPUT的值 批量成销客户手机号
        var customerName2 = tab.rows[i].cells[5].getElementsByTagName("INPUT")[0].value;//第六列 第1个INPUT的值 批量成销客户姓名2
        var customerPhone2 = tab.rows[i].cells[6].getElementsByTagName("INPUT")[0].value;//第七列 第1个INPUT的值 批量成销客户手机号2
        var saleAcreage = tab.rows[i].cells[7].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第八列 第1个INPUT的值 批量成销面积
        var saleAmount = tab.rows[i].cells[8].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第九列 第1个INPUT的值 批量成销金额
        var accountProject = tab.rows[i].cells[9].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第九列 第1个INPUT的值 核算主体
        var befYjsrTaxAmount = tab.rows[i].cells[10].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第十列 第1个INPUT的值 应计收入税前
        var aftYjsrTaxAmount = tab.rows[i].cells[11].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第十一列 第1个INPUT的值 应计收入税后
        var befYjfyTaxAmount = tab.rows[i].cells[12].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第十二列 第1个INPUT的值 应计返佣税前
        var aftYjfyTaxAmount = tab.rows[i].cells[13].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第十三列 第1个INPUT的值 应计返佣税后
        var befYjdyTaxAmount = tab.rows[i].cells[14].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第十四列 第1个INPUT的值 应计垫佣税前
        var aftYjdyTaxAmount = tab.rows[i].cells[15].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第十五列 第1个INPUT的值 应计垫佣税后

        if (customerName1 == "" || customerName1 == null) {
            tab.rows[i].cells[3].getElementsByTagName("INPUT")[5].setAttribute("style", "border:1px solid red;width: 100%;");
            isValid = true;
        } else {
            tab.rows[i].cells[3].getElementsByTagName("INPUT")[5].setAttribute("style", "border:1px solid grey;width: 100%;");
        }
        if (customerPhone1 == "" || customerPhone1 == null) {
            tab.rows[i].cells[4].getElementsByTagName("INPUT")[0].setAttribute("style", "border:1px solid red;width: 100%;");
            isValid = true;
        } else {
            if (!myreg.test(customerPhone1)) {
                tab.rows[i].cells[4].getElementsByTagName("INPUT")[0].setAttribute("style", "border:1px solid red;width: 100%;");
                isValid = true;
            } else {
                tab.rows[i].cells[4].getElementsByTagName("INPUT")[0].setAttribute("style", "border:1px solid grey;width: 100%;");
            }
        }
        if (saleAcreage == "" || saleAcreage == null || saleAcreage == '0') {
            tab.rows[i].cells[7].getElementsByTagName("INPUT")[0].setAttribute("style", "border:1px solid red;width: 100%;");
            isValid = true;
        } else {
            tab.rows[i].cells[7].getElementsByTagName("INPUT")[0].setAttribute("style", "border:1px solid grey;width: 100%;");
        }
        if (saleAmount == "" || saleAmount == null || saleAmount == '0') {
            tab.rows[i].cells[8].getElementsByTagName("INPUT")[0].setAttribute("style", "border:1px solid red;width: 100%;");
            isValid = true;
        } else {
            tab.rows[i].cells[8].getElementsByTagName("INPUT")[0].setAttribute("style", "border:1px solid grey;width: 100%;");
        }

        if (accountProject == "" || accountProject == null || accountProject == '0') {
            tab.rows[i].cells[9].getElementsByTagName("INPUT")[0].setAttribute("style", "border:1px solid red;width: 100%;");
            isValid = true;
        } else {
            tab.rows[i].cells[9].getElementsByTagName("INPUT")[0].setAttribute("style", "border:1px solid grey;width: 100%;");
        }

        if (befYjsrTaxAmount=="" || befYjsrTaxAmount==null || befYjsrTaxAmount=='0'){
            tab.rows[i].cells[10].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid red;width: 100%;");
            isValid=true;
        }else {
            tab.rows[i].cells[10].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid grey;width: 100%;");
        }

        if (aftYjsrTaxAmount=="" || aftYjsrTaxAmount==null || aftYjsrTaxAmount=='0'){
            tab.rows[i].cells[11].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid red;width: 100%;");
            isValid=true;
        }else {
            tab.rows[i].cells[11].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid grey;width: 100%;");
        }

        if (befYjfyTaxAmount=="" || befYjfyTaxAmount==null || befYjfyTaxAmount=='0'){
            tab.rows[i].cells[12].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid red;width: 100%;");
            isValid=true;
        }else {
            tab.rows[i].cells[12].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid grey;width: 100%;");
        }

        if (aftYjfyTaxAmount=="" || aftYjfyTaxAmount==null || aftYjfyTaxAmount=='0'){
            tab.rows[i].cells[13].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid red;width: 100%;");
            isValid=true;
        }else {
            tab.rows[i].cells[13].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid grey;width: 100%;");
        }

        if(befYjdyTaxAmount!=""&&befYjdyTaxAmount!=null || befYjdyTaxAmount!='0'&&aftYjdyTaxAmount!="" && aftYjdyTaxAmount!=null && aftYjdyTaxAmount=='0'){
            tab.rows[i].cells[14].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid grey;width: 100%;");
            tab.rows[i].cells[15].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid grey;width: 100%;");
        }else if((befYjdyTaxAmount=="" || befYjdyTaxAmount==null || befYjdyTaxAmount=='0')&&(aftYjdyTaxAmount=="" || aftYjdyTaxAmount==null || aftYjdyTaxAmount=='0')){
            tab.rows[i].cells[14].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid grey;width: 100%;");
            tab.rows[i].cells[15].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid grey;width: 100%;");
        }else{
            tab.rows[i].cells[15].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid red;width: 100%;");
            tab.rows[i].cells[14].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid red;width: 100%;");
            isValid=true;
        }

        if ((customerName2=="" || customerName2==null)&&customerPhone2!="") {
            tab.rows[i].cells[5].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid red;width: 100%;");
            isValid=true;
        }else {
            tab.rows[i].cells[5].getElementsByTagName("INPUT")[0].setAttribute("style","border:1px solid grey;width: 100%;");
        }
        if ((customerPhone2 == "" || customerPhone2 == null) && customerName2 != '') {
            tab.rows[i].cells[6].getElementsByTagName("INPUT")[0].setAttribute("style", "border:1px solid red;width: 100%;");
            isValid = true;
        } else {
            if (customerPhone2 != "" && customerPhone2 != null) {
                if (!myreg.test(customerPhone2)) {
                    tab.rows[i].cells[6].getElementsByTagName("INPUT")[0].setAttribute("style", "border:1px solid red;width: 100%;");
                    isValid = true;
                }
            }

        }
    }
    return isValid;
}

function sumAcreageAndAmountNew() {
    var acreage = 0.00; //成销总面积
    var amount = 0.00;  //成销总金额
    var befYjsr = 0.00;
    var aftYjsr = 0.00;
    var befYjfy = 0.00;
    var aftYjfy = 0.00;
    var befYjdy = 0.00;
    var aftYjdy = 0.00;
    //获取table对象
    var tab = document.getElementById("myTable");
    //得到表格行数
    var rows = tab.rows;
    for (var i = 1; i < rows.length-1; i++) { //遍历表格的行,表头不计算在内，故从1开始
        var saleAcreage = tab.rows[i].cells[7].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第八列 第1个INPUT的值 批量成销面积
        var saleAmount = tab.rows[i].cells[8].getElementsByTagName("INPUT")[0].value.replaceAll(',','');//第九列 第1个INPUT的值 批量成销金额
        var befYjsrAmount = tab.rows[i].cells[10].getElementsByTagName("INPUT")[0].value.replaceAll(',','');
        var aftYjsrAmount = tab.rows[i].cells[11].getElementsByTagName("INPUT")[0].value.replaceAll(',','');
        var befYjfyAmount = tab.rows[i].cells[12].getElementsByTagName("INPUT")[0].value.replaceAll(',','');
        var aftYjfyAmount = tab.rows[i].cells[13].getElementsByTagName("INPUT")[0].value.replaceAll(',','');
        var befYjdyAmount = tab.rows[i].cells[14].getElementsByTagName("INPUT")[0].value.replaceAll(',','');
        var aftYjdyAmount = tab.rows[i].cells[15].getElementsByTagName("INPUT")[0].value.replaceAll(',','');
        acreage = Number(acreage) + Number(saleAcreage);
        amount = Number(amount) + Number(saleAmount);
        befYjsr = Number(befYjsr) + Number(befYjsrAmount);
        aftYjsr = Number(aftYjsr) + Number(aftYjsrAmount);
        befYjfy = Number(befYjfy) + Number(befYjfyAmount);
        aftYjfy = Number(aftYjfy) + Number(aftYjfyAmount);
        befYjdy = Number(befYjdy) + Number(befYjdyAmount);
        aftYjdy = Number(aftYjdy) + Number(aftYjdyAmount);
    }
    $("#myTable #countAcreage").html(toDecimal2(acreage));
    $("#myTable #countAmount").html(number_format(toDecimal2(amount), 2, ".", ","));
    $("#myTable #countBefYjsr").html(number_format(toDecimal2(befYjsr), 2, ".", ","));
    $("#myTable #countAftYjsr").html(number_format(toDecimal2(aftYjsr), 2, ".", ","));
    $("#myTable #countBefYjfy").html(number_format(toDecimal2(befYjfy), 2, ".", ","));
    $("#myTable #countAftYjfy").html(number_format(toDecimal2(aftYjfy), 2, ".", ","));
    $("#myTable #countBefYjdy").html(number_format(toDecimal2(befYjdy), 2, ".", ","));
    $("#myTable #countAftYjdy").html(number_format(toDecimal2(aftYjdy), 2, ".", ","));

}

BatchSale.output = function(){
    var url = BASE_PATH + '/sceneEstate/exportCheck';
    systemLoading("", true);
    $("#btn-output").attr("disabled",true);
    var projectNo = $("#projectNo").val();
    var estateNm = $("#estateNm").val();
    var estateId = $("#estateId").val();
    var cookieName = guid();
    var param = {
    }
    httpPost("batchSaleFrom", url, function(data) {
        window.location.href = BASE_PATH + '/sceneEstate/export?projectNo='+projectNo+'&estateNm='+estateNm+'&estateId='+estateId+ "&cookieName=" + cookieName;
        systemLoaded();
        $('#btn-output').removeAttr("disabled");
    }, function(data) {
        Dialog.alertError(data.returnMsg);
        systemLoaded();
        $('#btn-output').removeAttr("disabled");
    });
}

BatchSale.imput = function(){
    $("#historyDataFile").click();
}

//导入
function dataImport()
{
    var url= BASE_PATH + "/sceneEstate/imput/";
    systemLoading("", true);
    httpPost("imputForm",url, function(data) {
        $("#historyDataFile").val('');
        var batchSaleDetails = data.returnValue;
        var tab = document.getElementById("myTable");
        var rows = tab.rows;
        for (var i = 1; i < rows.length-1; i++) { //遍历表格的行,表头不计算在内，故从1开始
            var reportId = $(tab.rows[i].cells[1]).text();
            var floor = $(tab.rows[i].cells[2]).text().replace(/\s*/g,"");
            for (var j = 0;j<batchSaleDetails.length;j++){
                if(batchSaleDetails[j].reportId == reportId){
                    $("#myTable #saleAcreage_"+(i-1)).val(formatCurrency(batchSaleDetails[j].saleAcreage));
                    $("#myTable #saleAmount_"+(i-1)).val(formatCurrency(batchSaleDetails[j].saleAmount));
                    $("#myTable #accountProject_"+(i-1)).val(batchSaleDetails[j].accountProject.replace(/\s*/g,""));
                    $("#myTable #befYjsrTaxAmount_"+(i-1)).val(formatCurrency(batchSaleDetails[j].befYjsrTaxAmount));
                    $("#myTable #aftYjsrTaxAmount_"+(i-1)).val(formatCurrency(batchSaleDetails[j].aftYjsrTaxAmount));
                    $("#myTable #befYjfyTaxAmount_"+(i-1)).val(formatCurrency(batchSaleDetails[j].befYjfyTaxAmount));
                    $("#myTable #aftYjfyTaxAmount_"+(i-1)).val(formatCurrency(batchSaleDetails[j].aftYjfyTaxAmount));
                    if(batchSaleDetails[j].befYjdyTaxAmount==null){
                        $("#myTable #befYjdyTaxAmount_"+(i-1)).val("");
                    }else{
                        $("#myTable #befYjdyTaxAmount_"+(i-1)).val(formatCurrency(batchSaleDetails[j].befYjdyTaxAmount));
                    }
                    if(batchSaleDetails[j].aftYjdyTaxAmount==null){
                        $("#myTable #aftYjdyTaxAmount_"+(i-1)).val("");
                    }else{
                        $("#myTable #aftYjdyTaxAmount_"+(i-1)).val(formatCurrency(batchSaleDetails[j].aftYjdyTaxAmount));
                    }
                }
            }
        }
        sumAcreageAndAmountNew();
        systemLoaded();
        Dialog.alertError(data.returnMsg);
    }, function(data) {
        $("#historyDataFile").val('');
        Dialog.alertError(data.returnMsg);
        systemLoaded();
        return false;
    });

    /**
     * 将数值四舍五入(保留2位小数)后格式化成金额形式
     *
     * @param num 数值(Number或者String)
     * @return 金额格式的字符串,如'1,234,567.45'
     * @type String
     */
    function formatCurrency(num) {
        num = num.toString().replace(/\$|\,/g,'');
        if(isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num*100+0.50000000001);
        cents = num%100;
        num = Math.floor(num/100).toString();
        if(cents<10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
            num = num.substring(0,num.length-(4*i+3))+','+
                num.substring(num.length-(4*i+3));
        return (((sign)?'':'-') + num + '.' + cents);
    }

};



