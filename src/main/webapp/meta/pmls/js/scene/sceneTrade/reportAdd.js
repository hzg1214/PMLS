layui.use(['element', 'laydate', 'form', 'table', 'layer', 'upload'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        $ = layui.$;

    init()

    function init() {

        var nowDate = new Date().format('yyyy-MM-dd');
        // var switchDate = $("#switchDate").val();
        // if (isEmpty(switchDate)) {
        //     switchDate = "1900-1-1";
        // }

        laydate.render({
            elem: '#reportDate',
            type: 'date',
            format: 'yyyy-MM-dd',
            max: nowDate,
            // min: switchDate,
            trigger: 'click'
        });

    }

    form.render('select'); // 刷新单选框渲染

    var active = {
        save: function () {
            if (valid() == true) {
                var optionsData = {};

                optionsData.estateId = $("#estateId").val();
                optionsData.estateNm = $("#estateNm").val();
                optionsData.reportCompanyId = $("#companyId").attr("data-companyNo");
                optionsData.reportCompanyNm = $("#companyId").val();

                var branchId = $("#companyId").attr("data-branchId");
                optionsData.branchId = isEmpty(branchId) ? null : branchId;

                if (isEmpty(branchId)) {
                    optionsData.storeId = $("#ddlStoreId").val();
                    optionsData.storeName = $("#ddlStoreId").find("option:selected").text();
                    optionsData.storeCityNo = $("#ddlStoreId").find("option:selected").attr("storeCityNo")
                } else {
                    optionsData.storeName = trimStr($("#inputStoreId").val());
                }
                optionsData.contactId = $("#contactId").val();
                optionsData.contactNm = $("#contactNm").val();
                optionsData.centerGroupId = $("#centerGroupId").val();
                optionsData.centerGroupName = $("#centerGroupName").val();
                optionsData.reportAgent = trimStr($("#reportAgent").val());
                optionsData.reportAgentTel = $("#reportAgentTel").val();
                optionsData.customerName = trimStr($("#customerName").val());
                optionsData.customerPhone = $("#customerPhone").val();
                optionsData.customerNmTwo = trimStr($("#customerNmTwo").val());
                optionsData.customerTelTwo = $("#customerTelTwo").val();
                optionsData.reportDate = $("#reportDate").val();
                optionsData.estateCenterId = $("#estateCenterId").val();
                // optionsData.customerNum = $("#customerNum").val();

                submitToService(optionsData)
            }
        },

        cancel: function () {
            parent.redirectTo('delete', null, null);
        },

        goback: function () {
            parent.redirectTo('delete', null, null);
        },

        openDialogCompany: function () {
            var estateId = $('#estateId').val();
            parent.layer.open({
                type: 2,
                title: '选择经纪公司',
                area: ['800px', '660px'],
                content: '/pmlsFrameContract/selectBusinessPage/' + estateId
                , scrollbar: false
                , resize: false
                , btn: ['确定', '取消']
                , yes: function (index, layero) {

                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();

                    //确认的回调
                    if (formData != null) {
                        var w_companyId = $("#companyId").attr("data-companyId");

                        $("#companyId").attr("data-branchId", formData.topbranchId);
                        $("#companyId").attr("data-companyId", formData.id);
                        $("#companyId").attr("data-companyNo", formData.companyNo);
                        $("#companyId").val(formData.companyName);


                        $("#inputStoreId").val("");
                        $("#ddlStoreId").html(' <option value="" data-storeNo="" data-storeName="" data-storeCityNo="">请选择门店</option>');
                        restYeJi();
                        form.render("select");

                        // 非大渠道(来源于CRM)
                        if ('29502' == formData.arteryType) {
                            $("#divInputStore").hide();
                            $("#divddlStore").show();

                            var url = BASE_PATH + "/sceneTrade/store/qr";
                            var params = {companyId: formData.id};
                            var loadIndex = parent.layer.load(2, {shade: [0.1]});
                            ajaxGet(url, params, function (data) {
                                parent.layer.close(loadIndex);
                                var result = ' <option value="" data-storeNo="" data-storeName="">请选择门店</option>';
                                var selected = "";
                                if (!isEmpty(data) && !isEmpty(data.returnData) && data.returnData.length == 1) {
                                    selected = " selected "
                                }
                                $.each(data.returnData, function (n, value) {
                                    result += '<option ' + selected + ' value="' + value.storeId + '" ';
                                    result += ' data-storeNo="' + value.storeNo + '" ';
                                    result += ' data-storeName="' + value.name + '" ';
                                    result += ' data-contactId="' + value.pmlsMaintainCode + '" ';
                                    result += ' data-contactNm="' + value.pmlsMaintainName + '" ';
                                    result += ' data-centerGroupId="' + value.pmlsCenterId + '" ';
                                    result += ' data-centerGroupName="' + value.pmlsGroupName + '" ';
                                    result += ' data-storeCityNo="' + value.cityNo + '">' + value.name + '</option>';
                                });
                                $("#ddlStoreId").html('');
                                $("#ddlStoreId").append(result);
                                form.render("select");
                                if (!isEmpty(data) && !isEmpty(data.returnData) && data.returnData.length == 1) {
                                    changeStoreSetYeJi();
                                }
                            }, function (data) {
                                parent.layer.close(loadIndex);
                            });
                        }
                        // 其他渠道
                        else {
                            $("#divInputStore").show();
                            $("#divddlStore").hide();

                            var contactId = formData.maintainer;
                            var contactNm = formData.maintainerName;
                            var centerGroupId = formData.pmlsCenterId;
                            var centerGroupName = formData.centerName;

                            $("#contactId").val(contactId);
                            $("#contactNm").val(contactNm);
                            //$("#contactIdLabel").html(contactNm);
                            $("#centerGroupId").val(centerGroupId);
                            $("#centerGroupName").val(centerGroupName);
                            //$("#centerGroupIdLabel").html(centerGroupName);

                        }

                        parent.layer.close(index);
                    }
                }
            });
        },

        /*openDialogContact: function () {
            parent.layer.open({
                type: 2,
                title: '选择业绩归属人',
                area: ['800px', '660px'],
                content: '/sceneTrade/selUser'
                , scrollbar: false
                , resize: false
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    if (formData != null) {
                        $("#contactId").attr('data-id', formData.userCode);
                        $("#contactId").val(formData.userName);

                        var centerIds = [];
                        centerIds.push("centerGroupId");
                        // 业绩归属中心
                        centerIdLinkageIsService(formData.userCode, function () {
                            form.render('select')
                        }, centerIds);
                        //关闭弹出层
                        parent.layer.close(index);
                    }
                }
            });
        }*/

    }
    $('.layui-btn'
    ).on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });

    function valid() {
        // 经纪公司
        var companyId = $("#companyId").attr("data-companyNo");
        var companyNm = $("#companyId").val();
        if (isEmpty(companyNm) || isEmpty(companyId)) {
            parent.msgAlert("请选择经纪公司！")
            return false;
        }

        // 门店
        var branchId = $("#companyId").attr("data-branchId");
        if (isEmpty(branchId)) {
            var storeId = $("#ddlStoreId").val();
            if (isEmpty(storeId)) {
                parent.msgAlert("请选择门店！")
                return false;
            }
        } else {
            var storeName = $("#inputStoreId").val();
            if (!isEmpty(storeName) && isIncludeBlank(storeName)) {
                parent.msgAlert("门店名称有误！")
                return false;
            }
        }


        // 业绩归属人
        var contactId = $("#contactId").val();
        if (isEmpty(contactId)) {
            parent.msgAlert("该门店没有业绩归属人，请去分配门店维护人！")
            return false;
        }
        // 业绩归属中心
        var centerGroupId = $("#centerGroupId").val();
        var centerGroupName = $("#centerGroupName").val();
        if (isEmpty(centerGroupId) || isEmpty(centerGroupName)) {
            parent.msgAlert("该项目没有中心，请提前进行维护！")
            return false;
        }
        // 报备经纪人
        var reportAgent = $("#reportAgent").val();
        var reportAgentTel = $("#reportAgentTel").val();
        if (!isEmpty(reportAgent) && isEmpty(reportAgentTel)) {
            parent.msgAlert("经纪人已填写，经纪人手机必须填写！")
            return false;
        }
        if (isEmpty(reportAgent) && !isEmpty(reportAgentTel)) {
            parent.msgAlert("经纪人手机已填写，经纪人必须填写！")
            return false;
        }
        if (!isEmpty(reportAgentTel) && !checkPhoneNumber(reportAgentTel)) {
            parent.msgAlert("经纪人手机号不正确！")
            return false;
        }

        // 客户姓名
        var customerName = $("#customerName").val();
        if (isEmpty(customerName)) {
            parent.msgAlert("请输入客户姓名！")
            return false;
        }
        // 客户手机号
        var customerPhone = $("#customerPhone").val();
        if (isEmpty(customerPhone)) {
            parent.msgAlert("请输入客户手机号！")
            return false;
        }
        if (!checkPhoneNumber(customerPhone)) {
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


        // 报备日期
        var reportDate = $("#reportDate").val();
        if (isEmpty(reportDate)) {
            parent.msgAlert("请选择报备日期！")
            return false;
        }

        // // 关账日期 <= 报备日期 <= 当前日期
        var nowDate = new Date().format('yyyy-MM-dd');
        // var switchDate = $("#switchDate").val();
        // if (isEmpty(switchDate)) {
        //     switchDate = "1900-1-1";
        // }
        // if (!dateCompare(reportDate, switchDate)) {
        //     parent.msgAlert("报备日期不能在关账日期之前！");
        //     return false;
        // }
        if (!dateCompare(nowDate, reportDate)) {
            parent.msgAlert("报备日期不能在当前日期之后！");
            return false;
        }

        return true;
    }

    function submitToService(optionsData) {
        var loadIndex = parent.layer.load(2, {shade: [0.1]});

        var url = BASE_PATH + "/sceneTrade/saveReport";
        restPost(url, optionsData,
            function (data) {
                parent.layer.close(loadIndex);
                parent.msgProp("新增成功!");
                rest()
            },
            function (data) {
                parent.layer.close(loadIndex);
                parent.msgAlert(data.returnMsg);
            }
        );
    }

    function rest() {

        $("#companyId").attr("data-companyId", '');
        $("#companyId").attr("data-companyNo", '');
        $("#companyId").attr("data-branchId", '');
        $("#companyId").val('');

        $("#ddlStoreId").html('<option value="" data-storeNo="" data-storeName="">请选择门店</option>')
        $("#ddlStoreId").val('');
        $("#inputStoreId").val('');

        // $("#contactId").val("");
        // $("#contactId").attr("data-id", '')
        //
        // $("#centerGroupId").html('<option value="">请选择业绩归属中心</option>')
        // $("#centerGroupId").val("");


        $("#reportAgent").val("");
        $("#reportAgentTel").val("");
        $("#customerName").val("");
        $("#customerPhone").val("");
        $("#customerNmTwo").val("");
        $("#customerTelTwo").val("");
        $("#reportDate").val("");
        // $("#customerNum").val("");
        restYeJi();
        form.render('select')
    }

    form.on('select(ddlStoreId)', function (data) {
        if (data.value != null && data.value != '') {
            changeStoreSetYeJi();
        } else {
            restYeJi();
        }
    });

    function restYeJi() {
        $("#contactId").val("");
        $("#contactNm").val("");
        //$("#contactIdLabel").html("");
        $("#centerGroupId").val("");
        $("#centerGroupName").val("");
        //$("#centerGroupIdLabel").html("");
        form.render('select');
    }

    function changeStoreSetYeJi() {
        var contactId = $("#ddlStoreId").find("option:selected").attr("data-contactId");
        var contactNm = $("#ddlStoreId").find("option:selected").attr("data-contactNm");
        var centerGroupId = $("#ddlStoreId").find("option:selected").attr("data-centerGroupId");
        var centerGroupName = $("#ddlStoreId").find("option:selected").attr("data-centerGroupName");
        $("#contactId").val(contactId);
        $("#contactNm").val(contactNm);
        //$("#contactIdLabel").html(contactNm);
        $("#centerGroupId").val(centerGroupId);
        $("#centerGroupName").val(centerGroupName);
        //$("#centerGroupIdLabel").html(centerGroupName);

    }
});