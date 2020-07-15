var layer;
layui.use(['element', 'laydate', 'form', 'table', 'layer', 'upload'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        formSelects = layui.formSelects,
        upload = layui.upload,
        $ = layui.$;
    layer = layui.layer;
    init();

    function init() {
        var roughDate = $("#roughDate").val();
        if (isEmpty(roughDate)) {
            roughDate = "1900-1-1";
        }
        var nowDate = new Date().format('yyyy-MM-dd');
        var switchDate = $("#switchDate").val();
        if (isEmpty(switchDate)) {
            switchDate = "1900-1-1";
        }

        if (dateCompare(roughDate, switchDate)) {
            switchDate = roughDate;
        }
        laydate.render({
            elem: '#dealDate',
            type: 'date',
            format: 'yyyy-MM-dd',
            max: nowDate,
            min: switchDate,
            trigger: 'click'
        });

        laydate.render({
            elem: '#settleConfirmDate',
            type: 'date',
            format: 'yyyy-MM-dd',
            trigger: 'click'
        });

        var cityNo = $("#cityNo").val()
        var accountProjectNos = [];
        accountProjectNos.push("accountProjectNo");
        // 核算主体
//        accountProjectLinkageIsService(cityNo, function () {
//            form.render('select')
//        }, accountProjectNos);
        
        //20200701  根据项目找到收入类合同(LNK_Estate_Srlht)对应得核算 -begin
        var projectNo = $("#projectNo").val();
        initAccountProject(projectNo, function () {
            form.render('select')
        }, accountProjectNos);
        //end
        
        
        form.render("radio");
        form.render('select'); // 刷新单选框渲染
        //var firstCompanyNo = $("#firstCompanyNo").val();
        $("#fyObject").find("input[name='companyNo']").each(function () {
            var companyNo = $(this).val();
            form.on('radio(rate' + companyNo + ')', function (data) {
                succSaleAdd.changeRateAmount(this);
                // if (firstCompanyNo == companyNo) {
                //     succSaleAdd.setFYRateAfterAmount('befFangyouYJFYAmount', 'aftFangyouYJFYAmount');
                // }
            });
        });

        form.render();


    }

    var active = {
        save: function () {
            if (valid() == true) {
                var optionsData = {};
                optionsData.id = $("#id").val();
                optionsData.reportId = $("#reportId").val();
                optionsData.estateId = $("#estateId").val();
                optionsData.projectNo = $("#projectNo").val();


                optionsData.buildingNo = $("#buildingNo").val();
                optionsData.roughArea = $("#roughArea").val();
                optionsData.roughAmount = $("#roughAmount").val();
                optionsData.roughDate = $("#roughInputDate").val();

                optionsData.customerNm = trimStr($("#customerNm").val());
                optionsData.customerTel = $("#customerTel").val();
                optionsData.customerNmTwo = trimStr($("#customerNmTwo").val());
                optionsData.customerTelTwo = $("#customerTelTwo").val();

                optionsData.area = $("#dealArea").val();
                optionsData.dealAmount = $("#dealAmount").val();
                optionsData.operateDate = $("#dealDate").val();
                optionsData.settleConfirmDate = $("#settleConfirmDate").val();

                optionsData.accountProjectNo = $("#accountProjectNo").val();

                optionsData.planId = $("#planId").attr("data-id");
                optionsData.befYJSRAmount = $("#befYJSRAmount").val();
                optionsData.aftYJSRAmount = $("#aftYJSRAmount").val();

                // optionsData.befFangyouYJFYAmount = $("#befFangyouYJFYAmount").val();
                // optionsData.aftFangyouYJFYAmount = $("#aftFangyouYJFYAmount").val();

                var fyList = [];
                $("#fyObject div[class='fyObjectItem']").each(function () {
                    var fy = {};
                    fy.companyNo = $(this).find("input[name='companyNo']").val();
                    fy.rate = $(this).find("input[tag='rate']:checked").val();
                    fy.befYJFYAmount = $(this).find("input[name='befYJFYAmount']").val();
                    fy.aftYJFYAmount = $(this).find("input[name='aftYJFYAmount']").val();
                    fy.befYJDYAmount = $(this).find("input[name='befYJDYAmount']").val();
                    fy.aftYJDYAmount = $(this).find("input[name='aftYJDYAmount']").val();
                    fy.programmeId = $(this).find("input[name='fyPlanId']").attr("data-id");

                    fyList.push(fy);

                });
                optionsData.fyList = JSON.stringify(fyList);

                //获取上传图片的id
                var fileIds = '';
                $(".layui-upload .item_img").each(function () {
                    fileIds += $(this).data("id") + ',';
                });
                if (fileIds != '') {
                    fileIds = fileIds.substring(0, fileIds.length - 1);
                }
                optionsData.fileRecordMainIds = fileIds;

                submitToService(optionsData)
            }
        }
        , cancel: function () {
            parent.redirectTo('delete', null, null);
        }
        , goback: function () {
            parent.redirectTo('delete', null, null);
        }
        , openIncomePlan: function () {

            // 选择按钮点击前校验
            if (succSaleAdd.chkPlanNecessary() == false) {
                return;
            }

            var projectNo = $("#projectNo").val();
            var reportId = $("#reportId").val();
            var dealDate = $("#dealDate").val();  // 成销日期
            parent.layer.open({
                type: 2,
                title: '选择收入方案',
                area: ['800px', '660px'],
                content: BASE_PATH + '/pmlsEstate/selectIncomeplanListPopup/' + projectNo + '/' + reportId + '/' + dealDate
                , scrollbar: false
                , resize: false
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    if (formData != null) {
                        $("#planId").val(formData.planName);
                        $("#planId").attr("data-id", formData.id);
                        succSaleAdd.changePlanAmount();

                        //关闭弹出层
                        parent.layer.close(index);
                    }
                }
            });
        }

    }

    $('.layui-btn').on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });

    function valid() {
        // 客户姓名
        var customerNm = $("#customerNm").val();
        if (isEmpty(customerNm)) {
            parent.msgAlert("请输入客户姓名！")
            return false;
        }
        // 客户手机号
        var customerTel = $("#customerTel").val();
        if (isEmpty(customerTel)) {
            parent.msgAlert("请输入客户手机号！")
            return false;
        }
        if (!checkPhoneNumber(customerTel)) {
            parent.msgAlert("客户手机号不正确！")
            return false;
        }

        // 客户手机号2
        var customerNmTwo = $("#customerNmTwo").val();
        var customerTelTwo = $("#customerTelTwo").val();
        if (!isEmpty(customerNmTwo) && isEmpty(customerTelTwo)) {
            parent.msgAlert("客户已填写，客户手机必须填写！")
            return false;
        }
        if (isEmpty(customerNmTwo) && !isEmpty(customerTelTwo)) {
            parent.msgAlert("客户手机已填写，客户必须填写！")
            return false;
        }
        if (!isEmpty(customerTelTwo) && !checkPhoneNumber(customerTelTwo)) {
            parent.msgAlert("客户手机号不正确！")
            return false;
        }

        // 成销面积
        var dealArea = $("#dealArea").val();
        if (isEmpty(dealArea)) {
            parent.msgAlert("请输入成销面积！")
            return false;
        }
        if (!checkIsNumber(dealArea)) {
            parent.msgAlert("成销面积不正确！")
            return false;
        }

        // 成销总价
        var dealAmount = $("#dealAmount").val();
        if (isEmpty(dealAmount)) {
            parent.msgAlert("请输入成销总价！")
            return false;
        }
        if (!checkIsNumber(dealAmount)) {
            parent.msgAlert("成销总价不正确！")
            return false;
        }

        // 成销日期
        var dealDate = $("#dealDate").val();
        if (isEmpty(dealDate)) {
            parent.msgAlert("请选择成销日期！");
            return false;
        }

        // 关账日期 <= 成销日期 <= 当前日期
        var roughDate = $("#roughDate").val();
        if (isEmpty(roughDate)) {
            roughDate = "1900-1-1";
        }
        var nowDate = new Date().format('yyyy-MM-dd');
        var switchDate = $("#switchDate").val();
        if (isEmpty(switchDate)) {
            switchDate = "1900-1-1";
        }
        if (!dateCompare(dealDate, roughDate)) {
            parent.msgAlert("成销日期不能在大定日期之前！");
            return false;
        }
        if (!dateCompare(dealDate, switchDate)) {
            parent.msgAlert("成销日期不能在关账日期之前！");
            return false;
        }
        if (!dateCompare(nowDate, dealDate)) {
            parent.msgAlert("成销日期不能在当前日期之后！");
            return false;
        }

        // 核算主体
        var accountProjectNo = $("#accountProjectNo").val();
        if (isEmpty(accountProjectNo)) {
            parent.msgAlert("请选择核算主体！");
            return false;
        }


        // 应计收入税前
        var befYJSRAmount = $("#befYJSRAmount").val();
        if (isEmpty(befYJSRAmount)) {
            parent.msgAlert("请输入应计收入税前！");
            return false;
        }
        if (!checkIsNumber(befYJSRAmount)) {
            parent.msgAlert("应计收入税前不正确！");
            return false;
        }

        //应计收入税后
        var aftYJSRAmount = $("#aftYJSRAmount").val();
        if (isEmpty(aftYJSRAmount)) {
            parent.msgAlert("请输入应计收入税后！");
            return false;
        }
        if (!checkIsNumber(aftYJSRAmount)) {
            parent.msgAlert("应计收入税后不正确！");
            return false;
        }

        var htedition = $("#htedition").val()
        htedition = isEmpty(htedition) ? "28302" : htedition;
        if (htedition == "28302") {
            // // 房友返佣税前
            // var befFangyouYJFYAmount = $("#befFangyouYJFYAmount").val();
            // if (isEmpty(befFangyouYJFYAmount)) {
            //     parent.msgAlert("请输入房友返佣税前！");
            //     return false;
            // }
            // if (!checkIsNumber(befFangyouYJFYAmount)) {
            //     parent.msgAlert("房友返佣税前不正确！");
            //     return false;
            // }

            // // 房友返佣税后
            // var aftFangyouYJFYAmount = $("#aftFangyouYJFYAmount").val();
            // if (isEmpty(aftFangyouYJFYAmount)) {
            //     parent.msgAlert("请输入房友返佣税后！");
            //     return false;
            // }
            // if (!checkIsNumber(aftFangyouYJFYAmount)) {
            //     parent.msgAlert("房友返佣税后不正确！");
            //     return false;
            // }

            var befYjdyTaxAmount = $("#befYjdyTaxAmount").val();
            var aftYjdyTaxAmount = $("#aftYjdyTaxAmount").val();

            if (isEmpty(aftYjdyTaxAmount) && !isEmpty(befYjdyTaxAmount)) {
                parent.msgAlert("应计垫佣税前已填写,请输入应计垫佣税后!");
                return false;
            }
            if (!isEmpty(aftYjdyTaxAmount) && isEmpty(befYjdyTaxAmount)) {
                parent.msgAlert("应计垫佣税后已填写,请输入应计垫佣税前!");
                return false;
            }
            if (!isEmpty(aftYjdyTaxAmount) && !isEmpty(befYjdyTaxAmount)) {

                if (!checkIsNumber(befYjdyTaxAmount)) {
                    parent.msgAlert("应计垫佣税前不正确！");
                    return false;
                }

                if (!checkIsNumber(aftYjdyTaxAmount)) {
                    parent.msgAlert("应计垫佣税后不正确！");
                    return false;
                }
            }
        }

        var fyObjectReturn = true;
        // 返佣对象
        $("#fyObject div[class='fyObjectItem']").each(function () {

            // 增值税税率
            var rate = $(this).find("input[tag='rate']:checked").val();
            if (isEmpty(rate)) {
                parent.msgAlert("请选择增值税税率！");
                fyObjectReturn = false;
                return false;
            }
            // 应计返佣税前
            var befYJFYAmount = $(this).find("input[name='befYJFYAmount']").val();
            if (isEmpty(befYJFYAmount)) {
                parent.msgAlert("请输入应计返佣税前！");
                fyObjectReturn = false;
                return false;
            }
            if (!checkIsNumber(befYJFYAmount)) {
                parent.msgAlert("应计返佣税前不正确！");
                fyObjectReturn = false;
                return false;
            }

            // 应计返佣税后
            var aftYJFYAmount = $(this).find("input[name='aftYJFYAmount']").val();
            if (isEmpty(aftYJFYAmount)) {
                parent.msgAlert("请输入应计返佣税后！");
                fyObjectReturn = false;
                return false;
            }

            if (!checkIsNumber(aftYJFYAmount)) {
                parent.msgAlert("应计返佣税后不正确！");
                fyObjectReturn = false;
                return false;
            }

            if (htedition == "28302") {
                // 应计垫佣税前(元)
                var befYJDYAmount = $(this).find("input[name='befYJDYAmount']").val();
                if (!isEmpty(befYJDYAmount) && !checkIsNumber(befYJDYAmount)) {
                    parent.msgAlert("应计垫佣税前不正确！");
                    fyObjectReturn = false;
                    return false;
                }
                // 应计垫佣税后(元)
                var aftYJDYAmount = $(this).find("input[name='aftYJDYAmount']").val();
                if (!isEmpty(aftYJDYAmount) && !checkIsNumber(aftYJDYAmount)) {
                    parent.msgAlert("应计垫佣税后不正确！");
                    fyObjectReturn = false;
                    return false;
                }

                var dyRatio = parseFloat(NullToZero($("#dyRatio").val()));
                var dyAmount = Math.round(parseFloat(befYJFYAmount * dyRatio) * 100) / 100;

                if (befYJDYAmount > dyAmount) {
                    parent.msgAlert("应计垫佣税前不能大于应返佣税前*总控垫佣比例！");
                    fyObjectReturn = false;
                    return false;
                }

            }


        });

        if (fyObjectReturn == false) {
            return false;
        }
        // 成销确认书/佣金结算资料
        var fileSize = $("#uploadSuccSaleImg .upload_img_list .item_img").size()
        if (fileSize == null || fileSize <= 0) {
            parent.msgAlert("请上传成销确认书/佣金结算资料！")
            return false;
        }

        return true;
    }

    function submitToService(optionsData) {
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        var url = BASE_PATH + "/sceneTrade/saveSuccSale";
        restPost(url, optionsData,
            function (data) {
                parent.layer.close(loadIndex);
                if (data.returnData == 0 || data.returnData == 201 || data.returnData == 202) {
                    parent.msgAlert(data.returnMsg);
                } else {

                    parent.msgProp("操作成功！");
                    parent.redirectTo('delete', null, null);
                }
            },
            function (data) {
                parent.layer.close(loadIndex);
                parent.msgAlert(data.returnMsg);
            })
    }

    // 成销确认书/佣金结算资料
    uploadRender('uploadSuccSaleImg', {fileTypeId: '1025', fileSourceId: '5', exts: 'jpg|png|jpeg|pdf'});

});
succSaleAdd = {}

// 只有1个返佣对象【应计返佣税前】值取得，并同步计算税后
succSaleAdd.changeHTAmonut = function () {
    var dealAmount = $("#dealAmount").val();
    var commissionRatio = $("#commissionRatio").val();
    var commissionAmount = $("#commissionAmount").val();

    if (!isEmpty(dealAmount) && (!isEmpty(commissionRatio) || !isEmpty(commissionAmount))) {

        var dealAmount = parseFloat(NullToZero(dealAmount));
        var commissionRatio = parseFloat(NullToZero(commissionRatio));
        var commissionAmount = parseFloat(NullToZero(commissionAmount));

        var fyObjectItem = $("#fyObject").find("div[class='fyObjectItem']")
        var length = $(fyObjectItem).length;
        if (length == 1) {
            var befAmount = parseFloat(commissionAmount + parseFloat(dealAmount * commissionRatio / 100)).toFixed(2)
            var befobj = $(fyObjectItem).find("input[name='befYJFYAmount']")
            $(befobj).val(befAmount);
            succSaleAdd.setRipRateAfterAmount(befobj, "befYJFYAmount", "aftYJFYAmount");
        }
    }
}

// 根据收入方案，计算【应计收入税前】、同步计算【应计收入税后(元)】
succSaleAdd.changePlanAmount = function () {
    var planId = $("#planId").attr("data-id");

    var projectNo = $("#projectNo").val();
    var dealAmount = $("#dealAmount").val();
    var dealArea = $("#dealArea").val();

    if (!isEmpty(planId) && planId != 0 && !isEmpty(projectNo)) {

        var url = BASE_PATH + "/pmlsEstate/queryQueryYJAmount";
        var parmas = {
            projectNo: projectNo,
            dealAmount: NullToZero(dealAmount),
            dealArea: NullToZero(dealArea),
            planId: planId
        }
        $.ajax({
                url: url,
                data: parmas,
                type: 'GET',
                async: false,
                dataType: 'json',
                success: function (data) {
                    if (data && data.returnCode == '200') {
                        var da = data.returnData;
                        var befYJSRAmount = da.sqAmount;

                        $("#befYJSRAmount").val(befYJSRAmount);
                        succSaleAdd.setFixRateAfterAmount("befYJSRAmount", "aftYJSRAmount");
                    } else {
                        parent.msgError(data.returnMsg);
                    }
                }
            }
        );
    }
}

// 计算【应计收入税后(元)】
succSaleAdd.setFixRateAfterAmount = function (bef, aft) {
    var taxRate = $("#taxRate").val();
    var befAmount = $("#" + bef).val();
    if (!isEmpty(befAmount) && !isEmpty(taxRate)) {
        var aftAmount = parseFloat(befAmount * 100 / (100 + parseFloat(taxRate))).toFixed(2);
        $("#" + aft).val(aftAmount);
    }
}

// 增值税税率变更，同步修改【应计返佣税后】、【应计垫佣税后】
succSaleAdd.changeRateAmount = function (obj) {
    var fyObjectItem = $(obj).parents("div[class='fyObjectItem']");

    var rate = $(fyObjectItem).find("input[tag='rate']:checked").val();
    if (isEmpty(rate)) {
        return;
    }

    succSaleAdd.setRipRateAfterAmount(obj, "befYJFYAmount", "aftYJFYAmount");
    succSaleAdd.setRipRateAfterAmount(obj, "befYJDYAmount", "aftYJDYAmount");

}

// 计算【应计返佣税后】、【应计垫佣税后】
succSaleAdd.setRipRateAfterAmount = function (obj, bef, aft) {
    var fyObjectItem = $(obj).parents("div[class='fyObjectItem']");
    var rate = $(fyObjectItem).find("input[tag='rate']:checked").val();
    if (isEmpty(rate)) {
        return;
    }
    var befAmount = $(fyObjectItem).find("input[name='" + bef + "']").val();
    if (!isEmpty(befAmount)) {
        var aftAmount = parseFloat(befAmount / (1 + parseFloat(rate))).toFixed(2);
        $(fyObjectItem).find("input[name='" + aft + "']").val(aftAmount);


        // 应计垫佣 = 应计返佣 * 总控垫佣比例
        if (bef == "befYJFYAmount") {
            var dyRatio = parseFloat(NullToZero($("#dyRatio").val()));
            var dyAmount = parseFloat(befAmount * dyRatio).toFixed(2);
            $(fyObjectItem).find("input[name='befYJDYAmount']").val(dyAmount);
            succSaleAdd.setRipRateAfterAmount(obj, 'befYJDYAmount', 'aftYJDYAmount');
        }
    }
}

// 计算【房友返佣税后】
succSaleAdd.setFYRateAfterAmount = function (bef, aft) {
    var fyObjectItem = $("#fyObject").find("div[class='fyObjectItem']").eq(0);
    var rate = $(fyObjectItem).find("input[tag='rate']:checked").val();
    if (isEmpty(rate)) {
        return;
    }
    var befAmount = $("#" + bef).val();
    if (!isEmpty(befAmount)) {
        var aftAmount = parseFloat(befAmount / (1 + parseFloat(rate))).toFixed(2);
        $("#" + aft).val(aftAmount);
    }
}

// 返佣方案选择
succSaleAdd.openFyPlan = function (obj) {

    // 选择按钮点击前校验
    if (succSaleAdd.chkPlanNecessary() == false) {
        return;
    }

    var projectNo = $("#projectNo").val();
    var reportId = $("#reportId").val();
    var companyNo = $(obj).attr("data-companyNo");
    var dealDate = $("#dealDate").val();  // 成销日期
    parent.layer.open({
        type: 2,
        title: '选择返佣方案',
        area: ['900px', '660px'],
        content: BASE_PATH + '/pmlsEstate/selectFyplanListPopup/' + projectNo + '/' + reportId + '/' + companyNo + '/' + dealDate
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();
            if (formData != null) {
                var fyObjectItem = $(obj).parents("div[class='fyObjectItem']");
                var fyPlanId = $(fyObjectItem).find("input[name='fyPlanId']");

                $(fyPlanId).val(formData.planName);
                $(fyPlanId).attr("data-id", formData.id)

                succSaleAdd.changeFYPlanAmount(fyObjectItem);

                //关闭弹出层
                parent.layer.close(index);
            }
        }
    });
}

// 根据返佣方案，计算【应计返佣税前】、同步计算【应计收入税后(元)】
succSaleAdd.changeFYPlanAmount = function (fyObjectItem) {
    var fyPlanId = $(fyObjectItem).find("input[name='fyPlanId']");

    var projectNo = $("#projectNo").val();
    var planId = $(fyPlanId).attr("data-id");
    var dealAmount = $("#dealAmount").val();
    var dealArea = $("#dealArea").val();

    if (!isEmpty(planId) && planId != 0 && !isEmpty(projectNo)) {

        var url = BASE_PATH + "/pmlsEstate/queryQueryYJAmount";
        var parmas = {
            projectNo: projectNo,
            dealAmount: NullToZero(dealAmount),
            dealArea: NullToZero(dealArea),
            planId: planId
        }
        $.ajax({
                url: url,
                data: parmas,
                type: 'GET',
                async: false,
                dataType: 'json',
                success: function (data) {
                    if (data && data.returnCode == '200') {
                        var da = data.returnData;

                        var befYJFYAmount = $(fyObjectItem).find("input[name='befYJFYAmount']");
                        $(befYJFYAmount).val(da.sqAmount);
                        succSaleAdd.setRipRateAfterAmount(fyPlanId, "befYJFYAmount", "aftYJFYAmount");

                    } else {
                        parent.msgError(data.returnMsg);
                    }
                }
            }
        );
    }
}

succSaleAdd.clearPlanAmount = function () {

    // 清除收入方案及应计收入金额
    var planId = $("#planId").attr("data-id")
    if (!isEmpty(planId) && planId != 0) {
        $("#planId").val("");
        $("#planId").attr("data-id", "");
        $("#befYJSRAmount").val("");
        $("#aftYJSRAmount").val("");
    }

    // 清除返佣方案及应计返佣金额
    $("#fyObject").find("div[class='fyObjectItem']").each(function () {
        var fyPlanId = $(this).find("input[name='fyPlanId']");
        var id = $(fyPlanId).attr("data-id")
        if (!isEmpty(id) && id != 0) {
            $(fyPlanId).val("");
            $(fyPlanId).attr("data-id", "");
            var befYJFYAmount = $(this).find("input[name='befYJFYAmount']");
            var aftYJFYAmount = $(this).find("input[name='aftYJFYAmount']");
            $(befYJFYAmount).val("");
            $(aftYJFYAmount).val("");
        }
    });

}

succSaleAdd.chkPlanNecessary = function () {

    // 成销面积
    var dealArea = $("#dealArea").val();
    if (isEmpty(dealArea)) {
        parent.msgAlert("请输入成销面积！")
        return false;
    }
    if (!checkIsNumber(dealArea)) {
        parent.msgAlert("成销面积不正确！")
        return false;
    }

    // 成销总价
    var dealAmount = $("#dealAmount").val();
    if (isEmpty(dealAmount)) {
        parent.msgAlert("请输入成销总价！")
        return false;
    }
    if (!checkIsNumber(dealAmount)) {
        parent.msgAlert("成销总价不正确！")
        return false;
    }

    // 成销日期
    var dealDate = $("#dealDate").val();
    if (isEmpty(dealDate)) {
        parent.msgAlert("请选择成销日期！");
        return false;
    }

    return true;
}

/**
 * desc：获取项目对应收入类合同得核算主体
 * @param projectNo
 * @param callback
 * @param ids
 * @returns
 */
function initAccountProject(projectNo,callback, ids){

    var url = BASE_PATH + "/sceneTrade/getAccountProjectByProjectNo/" + projectNo;

    var params = {};

    if (ids == null) {
        ids = [];
        ids.push("accountProject")
    }

    ajaxGet(url, params, function (data) {
        var result = "<option value=''>请选择</option>";

        $.each(data.returnData, function (n, value) {
            result += "<option value='" + value.accountProjectNo
                + "' data-name='" + value.accountProject + "'>"
                + value.accountProjectNo + "_" + value.accountProject + "</option>";
        });


        for (var i = 0; i < ids.length; i++) {
            var id = "#" + ids[i];
            $(id).html('');
            $(id).append(result);
        }
        callback ? callback() : $.noop();
    }, function () {

    });
}