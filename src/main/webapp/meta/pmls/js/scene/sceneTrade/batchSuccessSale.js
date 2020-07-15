var evalTotal;
layui.use(['element', 'laydate', 'form', 'table', 'layer', 'upload'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        upload = layui.upload,
        uploadExcel = layui.upload,
        formSelects = layui.formSelects,
        $ = layui.$;

    var uploadSaleImg = [];
    if (reportInfo != null && reportInfo.fileList != null && reportInfo.fileList.length > 0) {
        for (var i = 0; i < reportInfo.fileList.length; i++) {
            if (reportInfo.fileList[i].fileTypeId == '1025') {
                uploadSaleImg.push(reportInfo.fileList[i]);
            }
        }
    }

    init();

    function init() {

        var nowDate = new Date().format('yyyy-MM-dd');

        var switchDate = $("#switchDate").val();
        if (isEmpty(switchDate)) {
            switchDate = "1900-1-1";
        }

        laydate.render({
            elem: '#saleDate',
            type: 'date',
            format: 'yyyy-MM-dd',
            max: nowDate,
            min: switchDate,
            trigger: 'click'
        });

        laydate.render({
            elem: '#settlementDate',
            type: 'date',
            format: 'yyyy-MM-dd',
            trigger: 'click'
        });

        form.render('select'); // 刷新单选框渲染

        tableRender();

        loadImageList("uploadSaleImg", uploadSaleImg, true);

    }

    function tableRender() {
        table.render({
            elem: '#reportTable'
            , url: BASE_PATH + '/sceneTrade/getBatchSaleDetailList/' + $("#batchId").val()
            , cols: setCols()
            , id: 'contentReload'
            , page: false
            , method: 'post'
            , limit: 1000
            , request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
            , response: {
                statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
            }
            , parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
                return {
                    "code": res.returnCode, //解析接口状态
                    "msg": res.returnMsg, //解析提示文本
                    "count": res.totalCount, //解析数据长度
                    "data": res.returnData //解析数据列表
                };
            }
            , done: function () {

                $("div[lay-id='contentReload'] select[name='accountProject']").each(function () {
                    var accountProjectNo = $(this).attr("defVal")
                    $(this).val(accountProjectNo);
                });

                form.render('select'); // 刷新单选框渲染

                var fixTrStr = '';
                fixTrStr += '<tr tag="sum">'
                fixTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-0 "></div></td>';
                fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-1">合计：</div></td>';
                fixTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-2"></div></td>';
                fixTrStr += '</tr>';

                var contentTrStr = '';
                contentTrStr += '<tr tag="sum">'
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-0"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-1"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-2"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-3"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-4"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-5"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-6"></div></td>';
                contentTrStr += '<td data-field="saleAmount" align="right" data-content="">';
                contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-7"><span name="saleAcreage"></span></div></td>';
                contentTrStr += '<td data-field="saleAmount" align="right" data-content="">';
                contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-8"><span name="saleAmount"></span></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-9"></div></td>';
                contentTrStr += '<td data-field="befYjsrTaxAmount"  align="right" data-content="">';
                contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-10"><span name="befYjsrTaxAmount"></span></div></td>';
                contentTrStr += '<td data-field="aftYjsrTaxAmount"  align="right" data-content="">';
                contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-11"><span name="aftYjsrTaxAmount"></span></div></td>';

                contentTrStr += '<td data-field="befYjfyTaxAmount"  align="right" data-content="">';
                contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-12"><span name="befYjfyTaxAmount"></span></div></td>';
                contentTrStr += '<td data-field="aftYjfyTaxAmount"  align="right" data-content="">';
                contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-13"><span name="aftYjfyTaxAmount"></span></div></td>';
                contentTrStr += '<td data-field="befYjdyTaxAmount"  align="right" data-content="">';
                contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-14"><span name="befYjdyTaxAmount"></span></div></td>';
                contentTrStr += '<td data-field="aftYjdyTaxAmount"  align="right" data-content="">';
                contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-15"><span name="aftYjdyTaxAmount"></span></div></td>';
                // contentTrStr += '<td data-field="befFangyouYJFYAmount"  align="right" data-content="">';
                // contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-16"><span name="befFangyouYJFYAmount"></span></div></td>';
                // contentTrStr += '<td data-field="aftFangyouYJFYAmount"  align="right" data-content="">';
                // contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-17"><span name="aftFangyouYJFYAmount"></span></div></td>';

                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-16"></div></td>';
                contentTrStr += '</tr>';

                var rightTrStr = '';
                rightTrStr += '<tr tag="sum">'
                rightTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-16"><span></span></div></td>';
                rightTrStr += '</tr>';

                $("div[lay-id='contentReload'] .layui-table-fixed-l .layui-table-body tbody").append(fixTrStr);
                $("div[lay-id='contentReload'] .layui-table-main tbody").append(contentTrStr);
                $("div[lay-id='contentReload'] .layui-table-fixed-r .layui-table-body tbody").append(rightTrStr);

                evalTotal();
            }
        });
    }

    function setCols() {
        var row = [
            {type: 'numbers', fixed: true, title: '序号', width: 60},
            {
                field: 'reportId', fixed: true, title: '报备编号', width: 150, align: 'center'
            },
            {field: 'floor', fixed: true, title: '楼室号', width: 150, align: 'center'},
            {
                field: 'customerName1', title: '客户姓名<i>*</i>', width: 150, align: 'center'
                , templet: function (d) {

                    var reportId = NullToEmpty(d.reportId);
                    var batchDetailId = NullToEmpty(d.batchDetailId);
                    var batchId = NullToEmpty(d.batchId);
                    var companyNo = NullToEmpty(d.companyNo);
                    var floor = NullToEmpty(d.floor);
                    var customerName1 = NullToEmpty(d.customerName1);
                    var htedition = NullToEmpty(d.htedition);
                    var commissionRatio = NullToEmpty(d.commissionRatio);
                    var commissionAmount = NullToEmpty(d.commissionAmount);
                    var isGlobalControl = NullToEmpty(d.isGlobalControl);
                    var dyRatio = NullToEmpty(d.dyRatio);

                    var ret = ""
                    ret += '<input type="hidden" name="reportId" value="' + reportId + '">'
                    ret += '<input type="hidden" name="batchDetailId" value="' + batchDetailId + '">'
                    ret += '<input type="hidden" name="batchId" value="' + batchId + '">'
                    ret += '<input type="hidden" name="companyNo" value="' + companyNo + '">'
                    ret += '<input type="hidden" name="floor" value="' + floor + '">'
                    ret += '<input type="hidden" name="htedition" value="' + htedition + '">'
                    ret += '<input type="hidden" name="commissionRatio" value="' + commissionRatio + '">'
                    ret += '<input type="hidden" name="commissionAmount" value="' + commissionAmount + '">'
                    ret += '<input type="hidden" name="isGlobalControl" value="' + isGlobalControl + '">'
                    ret += '<input type="hidden" name="dyRatio" value="' + dyRatio + '">'
                    ret += '<input type="text" name="customerName1" maxlength="20" autocomplete="off"  class="renderInput ml" value="' + customerName1 + '">'

                    return ret;
                }
            },
            {
                field: 'customerPhone1', title: '客户手机号<i>*</i>', width: 150, align: 'center', templet: function (d) {
                    var customerPhone1 = isEmpty(d.customerPhone1) ? "" : d.customerPhone1;
                    return '<input type="text" name="customerPhone1" maxlength="11" autocomplete="off"  class="renderInput mc" value="' + customerPhone1 + '">'
                }
            },
            {
                field: 'customerName2', title: '客户姓名', width: 150, align: 'center', templet: function (d) {
                    var customerName2 = isEmpty(d.customerName2) ? "" : d.customerName2;
                    return '<input type="text" name="customerName2" maxlength="20" autocomplete="off" class="renderInput ml" value="' + customerName2 + '">'
                }
            },
            {
                field: 'customerPhone2', title: '客户手机号', width: 150, align: 'center', templet: function (d) {
                    var customerPhone2 = isEmpty(d.customerPhone2) ? "" : d.customerPhone2;
                    return '<input type="text" name="customerPhone2" maxlength="11" autocomplete="off"  class="renderInput mc" value="' + customerPhone2 + '">'
                }
            },
            {
                field: 'saleAcreage', title: '成销面积(㎡)<i>*</i>', width: 150, align: 'center',
                templet: function (d) {
                    var saleAcreage = isEmpty(d.saleAcreage) ? "" : formatAccount2(d.saleAcreage);
                    return '<input type="text" name="saleAcreage" maxlength="20" autocomplete="off" ' +
                        ' onkeyup="javascript:batchSuccessSale.evalItem(\'saleAcreage\')" ' +
                        ' oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" ' +
                        ' class="renderInput mr" value="' + saleAcreage + '">'
                }
            },
            {
                field: 'saleAmount', title: '成销金额(元)<i>*</i>', width: 150, align: 'center',
                templet: function (d) {
                    var saleAmount = isEmpty(d.saleAmount) ? "" : formatAccount2(d.saleAmount);
                    return '<input type="text" name="saleAmount" maxlength="20" autocomplete="off" ' +
                        ' onkeyup="javascript:batchSuccessSale.evalItem(\'saleAmount\');batchSuccessSale.setHTRateAmonut(this)" ' +
                        ' oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" ' +
                        ' class="renderInput mr" value="' + saleAmount + '">'
                }
            },
            {
                field: 'accountProjectNo', title: '核算主体<i>*</i>', width: 200, align: 'center', templet: function (d) {
                    var accountProjectNo = isEmpty(d.accountProjectNo) ? "" : d.accountProjectNo;
                    var valStr = "";
                    //return '<input type="text" name="accountProject" maxlength="50" autocomplete="off" class="renderInput ml" value="' + accountProject + '">'
                    valStr += '<select lay-ignore name="accountProject" class="renderInput" defVal="' + accountProjectNo + '" >';
                    valStr += $("#cityAccountProject").html();
                    valStr += '</select>';

                    return valStr;
                }
            },
            {
                field: 'befYjsrTaxAmount', title: '应计收入税前(元)<i>*</i>', width: 150, align: 'center',
                templet: function (d) {
                    var befYjsrTaxAmount = isEmpty(d.befYjsrTaxAmount) ? "" : formatAccount2(d.befYjsrTaxAmount);
                    return '<input type="text" name="befYjsrTaxAmount" maxlength="20" autocomplete="off"  ' +
                        ' onkeyup="javascript:batchSuccessSale.evalItem(\'befYjsrTaxAmount\')" ' +
                        ' onchange="javascript:batchSuccessSale.setFixRateAfterAmount(this,\'befYjsrTaxAmount\',\'aftYjsrTaxAmount\');" ' +
                        ' oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')"  class="renderInput mr" value="' + befYjsrTaxAmount + '">'
                }
            },
            {
                field: 'aftYjsrTaxAmount', title: '应计收入税后(元)<i>*</i>', width: 150, align: 'center',
                templet: function (d) {
                    var aftYjsrTaxAmount = isEmpty(d.aftYjsrTaxAmount) ? "" : formatAccount2(d.aftYjsrTaxAmount);
                    return '<input type="text" name="aftYjsrTaxAmount" maxlength="20" autocomplete="off" onkeyup="javascript:batchSuccessSale.evalItem(\'aftYjsrTaxAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" class="renderInput mr" value="' + aftYjsrTaxAmount + '">'
                }
            },

            {
                field: 'befYjfyTaxAmount', title: '应计返佣税前(元)<i>*</i>', width: 150, align: 'center',
                templet: function (d) {
                    var befYjfyTaxAmount = isEmpty(d.befYjfyTaxAmount) ? "" : formatAccount2(d.befYjfyTaxAmount);
                    return '<input type="text" name="befYjfyTaxAmount" maxlength="20" autocomplete="off" ' +
                        'onkeyup="javascript:batchSuccessSale.evalItem(\'befYjfyTaxAmount\')" ' +
                        ' onchange="javascript:batchSuccessSale.setDyRateAfterAmount(this,\'befYjfyTaxAmount\',\'befYjdyTaxAmount\');" ' +
                        'oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" class="renderInput mr" value="' + befYjfyTaxAmount + '">'
                }
            },
            {
                field: 'aftYjfyTaxAmount', title: '应计返佣税后(元)<i>*</i>', width: 150, align: 'center',
                templet: function (d) {
                    var aftYjfyTaxAmount = isEmpty(d.aftYjfyTaxAmount) ? "" : formatAccount2(d.aftYjfyTaxAmount);
                    return '<input type="text" name="aftYjfyTaxAmount" maxlength="20" autocomplete="off" ' +
                        ' onkeyup="javascript:batchSuccessSale.evalItem(\'aftYjfyTaxAmount\')" ' +
                        ' oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" ' +
                        ' class="renderInput mr" value="' + aftYjfyTaxAmount + '">'
                }
            },
            {
                field: 'befYjdyTaxAmount', title: '应计垫佣税前(元)', width: 150, align: 'center',
                templet: function (d) {
                    if (d.htedition == '28301') {
                        return "-"
                    }
                    var befYjdyTaxAmount = isEmpty(d.befYjdyTaxAmount) ? "" : formatAccount2(d.befYjdyTaxAmount);
                    return '<input type="text" name="befYjdyTaxAmount" maxlength="20" autocomplete="off" ' +
                        ' onkeyup="javascript:batchSuccessSale.evalItem(\'befYjdyTaxAmount\')" ' +
                        ' oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" ' +
                        ' class="renderInput mr" value="' + befYjdyTaxAmount + '">'
                }
            },
            {
                field: 'aftYjdyTaxAmount', title: '应计垫佣税后(元)', width: 150, align: 'center',
                templet: function (d) {
                    if (d.htedition == '28301') {
                        return "-"
                    }
                    var aftYjdyTaxAmount = isEmpty(d.aftYjdyTaxAmount) ? "" : formatAccount2(d.aftYjdyTaxAmount);
                    return '<input type="text" name="aftYjdyTaxAmount" maxlength="20" autocomplete="off" onkeyup="javascript:batchSuccessSale.evalItem(\'aftYjdyTaxAmount\')" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" class="renderInput mr" value="' + aftYjdyTaxAmount + '">'
                }
            },
            // {
            //     field: 'befFangyouYJFYAmount', title: '房友返佣税前(元)<i>*</i>', width: 150, align: 'center',
            //     templet: function (d) {
            //         if (d.htedition == '28301') {
            //             return "-"
            //         }
            //         var befFangyouYJFYAmount = isEmpty(d.befFangyouYJFYAmount) ? "" : formatAccount2(d.befFangyouYJFYAmount);
            //         return '<input type="text" name="befFangyouYJFYAmount" maxlength="20" autocomplete="off" ' +
            //             ' onkeyup="javascript:batchSuccessSale.evalItem(\'befFangyouYJFYAmount\')" ' +
            //             ' oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')"  class="renderInput mr" value="' + befFangyouYJFYAmount + '">'
            //     }
            // },
            // {
            //     field: 'aftFangyouYJFYAmount', title: '房友返佣税后(元)<i>*</i>', width: 150, align: 'center',
            //     templet: function (d) {
            //         if (d.htedition == '28301') {
            //             return "-"
            //         }
            //         var aftFangyouYJFYAmount = isEmpty(d.aftFangyouYJFYAmount) ? "" : formatAccount2(d.aftFangyouYJFYAmount);
            //         return '<input type="text" name="aftFangyouYJFYAmount" maxlength="20" autocomplete="off" ' +
            //             ' onkeyup="javascript:batchSuccessSale.evalItem(\'aftFangyouYJFYAmount\')" ' +
            //             ' oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" class="renderInput mr" value="' + aftFangyouYJFYAmount + '">'
            //     }
            // },
            {

                title: '操作', fixed: 'right', align: 'center', width: 80,
                templet: function (d) {
                    return "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:batchSuccessSale.delete(this,\"" + d.batchDetailId + "\",\"" + d.batchId + "\")'>删除</a>";
                }
            }
        ];
        var cols = [];
        cols.push(row);
        return cols;
    }

    evalTotal = function () {
        batchSuccessSale.evalItem("saleAcreage");
        batchSuccessSale.evalItem("saleAmount");
        batchSuccessSale.evalItem("befYjsrTaxAmount");
        batchSuccessSale.evalItem("aftYjsrTaxAmount");
        // batchSuccessSale.evalItem("befFangyouYJFYAmount");
        // batchSuccessSale.evalItem("aftFangyouYJFYAmount");
        batchSuccessSale.evalItem("befYjfyTaxAmount");
        batchSuccessSale.evalItem("aftYjfyTaxAmount");
        batchSuccessSale.evalItem("befYjdyTaxAmount");
        batchSuccessSale.evalItem("aftYjdyTaxAmount");
    }

    // 成销确认书/佣金结算资料
    uploadRender('uploadSaleImg', {fileTypeId: '1025', fileSourceId: '25', exts: 'jpg|png|jpeg|pdf'});

    uploadExcel.render({
        elem: '#historyDataFile',
        url: BASE_PATH + '/sceneTrade/imput',
        id: 'excelfile',
        multiple: false
        , title: '请上传Excel文件'
        , exts: 'xls|xlsx'
        , accept: 'file'
        , data: {},
        before: function (obj) {
            layer.msg('导入中...', {
                icon: 16,
                shade: 0.01,
                time: 0
            })
        },
        done: function (res) {
            console.log(res);
            layer.close(layer.msg());//关闭上传提示窗口
            if (res.returnCode == 200) { //上传成功

                var tbody = $("div[lay-id='contentReload'] .layui-table-main tbody");
                var datas = res.returnValue;
                for (var i = 0; i < datas.length; i++) {
                    var record = datas[i];
                    $(tbody).find("tr[tag!='sum']").each(function () {
                        var reportId = $(this).find("input[name='reportId']").val();
                        if (reportId == record.reportId) {

                            // $(this).find("input[name='customerName1']").val();
                            // $(this).find("input[name='customerPhone1']").val();
                            // $(this).find("input[name='customerName2']").val();
                            // $(this).find("input[name='customerPhone2']").val();
                            $(this).find("input[name='saleAcreage']").val(record.saleAcreage);
                            $(this).find("input[name='saleAmount']").val(record.saleAmount);
                            //$(this).find("input[name='accountProject']").val(record.accountProject.replace(/\s*/g, ""));
                            var xls_accountProject = record.accountProject.replace(/\s*/g, "");
                            var accountProjectNo = "";
                            $(this).find("select[name='accountProject']").find("option").each(function (i) {
                                var accountProject = $(this).attr("data-name");
                                if (accountProject == xls_accountProject) {
                                    accountProjectNo = $(this).attr("data-id");
                                }
                            });
                            $(this).find("select[name='accountProject']").val(accountProjectNo);

                            $(this).find("input[name='befYjsrTaxAmount']").val(record.befYjsrTaxAmount);
                            $(this).find("input[name='aftYjsrTaxAmount']").val(record.aftYjsrTaxAmount);

                            // $(this).find("input[name='befFangyouYJFYAmount']").val(record.befFangyouYJFYAmount);
                            // $(this).find("input[name='aftFangyouYJFYAmount']").val(record.aftFangyouYJFYAmount);

                            $(this).find("input[name='befYjfyTaxAmount']").val(record.befYjfyTaxAmount);
                            $(this).find("input[name='aftYjfyTaxAmount']").val(record.aftYjfyTaxAmount);

                            $(this).find("input[name='befYjdyTaxAmount']").val(record.befYjdyTaxAmount);
                            $(this).find("input[name='aftYjdyTaxAmount']").val(record.aftYjdyTaxAmount);
                        }
                    });

                    evalTotal();
                }


            } else {
                parent.msgAlert(res.returnMsg);
            }
        }
    })

    var active = {
        submit: function () {
            toSave(1)
        }
        , save: function () {
            toSave(0)
        }
        , cancel: function () {
            parent.redirectTo('delete', null, null);
        }
        , goback: function () {
            parent.redirectTo('delete', null, null);
        }
        , export: function () {

            var url = BASE_PATH + '/sceneTrade/exportCheck/' + $("#batchId").val();

            var cookieName = guid();
            var param = {}
            restPost(url, "", function (data) {
                window.location.href = BASE_PATH + '/sceneTrade/export/' + $("#batchId").val() + "?cookieName=" + cookieName;

            }, function (data) {
                parent.msgAlert(data.returnMsg);
            });
        }
    }

    $('.layui-btn').on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });

    function toSave(type) {

        // 判断必填内容
        if (vaild(type) == false) {
            return false;
        }

        saveToService(type)
    }

    function vaild(type) {

        if (type == 0) {
            var ret = true;
            var tbody = $("div[lay-id='contentReload'] .layui-table-main tbody");

            $(tbody).find("tr[tag!='sum']").each(function () {

                // ->报备编号
                var reportId = $(this).find("input[name='reportId']").val();
                // ->核算主体
                var accountProject = $(this).find("select[name='accountProject']").val();
                if (isEmpty(accountProject)) {
                    parent.msgAlert("请选择报备编号[" + reportId + "]的核算主体!")
                    ret = false;
                    return false;
                }
            });

            if (ret == false) {
                return false;
            }
        } else {
            var ret = true;

            // 成销日期
            var saleDate = $("#saleDate").val();
            if (isEmpty(saleDate)) {
                parent.msgAlert("请选择成销日期！")
                ret = false;
                return false;
            }

            // 关账日期 <= 成销日期 <= 当前日期
            var nowDate = new Date().format('yyyy-MM-dd');
            var switchDate = $("#switchDate").val();
            if (isEmpty(switchDate)) {
                switchDate = "1900-1-1";
            }
            if (!dateCompare(saleDate, switchDate)) {
                parent.msgAlert("成销日期不能在关账日期之前！");
                return false;
            }
            if (!dateCompare(nowDate, saleDate)) {
                parent.msgAlert("成销日期不能在当前日期之后！");
                return false;
            }

            // 成销确认书/佣金结算资料
            var fileSize = $("#uploadSaleImg .item_img").size()
            if (fileSize == null || fileSize <= 0) {
                msgAlert("请上传[成销确认书/佣金结算资料]！")
                ret = false;
                return false;
            }

            // 成销房源列表

            var tbody = $("div[lay-id='contentReload'] .layui-table-main tbody");

            $(tbody).find("tr[tag!='sum']").each(function () {

                // ->报备编号
                var reportId = $(this).find("input[name='reportId']").val();
                var htedition = $(this).find("input[name='htedition']").val();
                // ->客户姓名
                var customerName1 = $(this).find("input[name='customerName1']").val();
                if (isEmpty(customerName1)) {
                    msgAlert("请输入报备编号[" + reportId + "]的客户姓名!")
                    ret = false;
                    return false;
                }
                // ->客户手机号
                var customerPhone1 = $(this).find("input[name='customerPhone1']").val();
                if (isEmpty(customerPhone1)) {
                    msgAlert("请输入报备编号[" + reportId + "]的客户手机号!")
                    ret = false;
                    return false;
                }
                if (!isEmpty(customerPhone1) && !checkPhoneNumber(customerPhone1)) {
                    msgAlert("报备编号[" + reportId + "]的客户手机号不正确！!")
                    ret = false;
                    return false;
                }

                // 客户手机号2
                var customerName2 = $(this).find("input[name='customerName2']").val();
                var customerPhone2 = $(this).find("input[name='customerPhone2']").val();
                if (!isEmpty(customerName2) && isEmpty(customerPhone2)) {
                    parent.msgAlert("报备编号[" + reportId + "]的客户已填写，客户手机必须填写！")
                    return false;
                }
                if (isEmpty(customerName2) && !isEmpty(customerPhone2)) {
                    parent.msgAlert("报备编号[" + reportId + "]的客户手机已填写，客户必须填写！")
                    return false;
                }
                if (!isEmpty(customerPhone2) && !checkPhoneNumber(customerPhone2)) {
                    msgAlert("报备编号[" + reportId + "]的客户手机号不正确！!")
                    ret = false;
                    return false;
                }


                // ->成销面积
                var saleAcreage = $(this).find("input[name='saleAcreage']").val();
                if (isEmpty(saleAcreage)) {
                    msgAlert("请输入报备编号[" + reportId + "]的成销面积!")
                    ret = false;
                    return false;
                }
                if (!checkIsNumber(saleAcreage)) {
                    parent.msgAlert("报备编号[" + reportId + "]的成销面积不正确！")
                    return false;
                }

                // ->成销金额(元)
                var saleAmount = $(this).find("input[name='saleAmount']").val();
                if (isEmpty(saleAmount)) {
                    msgAlert("请输入报备编号[" + reportId + "]的成销金额!")
                    ret = false;
                    return false;
                }
                if (!checkIsNumber(saleAmount)) {
                    parent.msgAlert("报备编号[" + reportId + "]的成销金额不正确！")
                    return false;
                }
                // ->核算主体
                var accountProject = $(this).find("select[name='accountProject']").val();
                if (isEmpty(accountProject)) {
                    msgAlert("请选择报备编号[" + reportId + "]的核算主体!")
                    ret = false;
                    return false;
                }
                // ->应计收入税前(元)
                var befYjsrTaxAmount = $(this).find("input[name='befYjsrTaxAmount']").val();
                if (isEmpty(befYjsrTaxAmount)) {
                    msgAlert("请输入报备编号[" + reportId + "]的应计收入税前!")
                    ret = false;
                    return false;
                }
                if (!checkIsNumber(befYjsrTaxAmount)) {
                    parent.msgAlert("报备编号[" + reportId + "]的应计收入税前不正确！")
                    return false;
                }
                // ->应计收入税后(元)
                var aftYjsrTaxAmount = $(this).find("input[name='aftYjsrTaxAmount']").val();
                if (isEmpty(aftYjsrTaxAmount)) {
                    msgAlert("请输入报备编号[" + reportId + "]的应计收入税后!")
                    ret = false;
                    return false;
                }
                if (!checkIsNumber(aftYjsrTaxAmount)) {
                    parent.msgAlert("报备编号[" + reportId + "]的应计收入税后不正确！")
                    return false;
                }

                // ->应计返佣税前(元)
                var befYjfyTaxAmount = $(this).find("input[name='befYjfyTaxAmount']").val();
                if (isEmpty(befYjfyTaxAmount)) {
                    msgAlert("请输入报备编号[" + reportId + "]的应计返佣税前!")
                    ret = false;
                    return false;
                }
                if (!checkIsNumber(befYjfyTaxAmount)) {
                    parent.msgAlert("报备编号[" + reportId + "]的应计返佣税前不正确！")
                    return false;
                }
                // ->应计返佣税后(元)
                var aftYjfyTaxAmount = $(this).find("input[name='aftYjfyTaxAmount']").val();
                if (isEmpty(aftYjfyTaxAmount)) {
                    msgAlert("请输入报备编号[" + reportId + "]的应计返佣税后!")
                    ret = false;
                    return false;
                }
                if (!checkIsNumber(aftYjfyTaxAmount)) {
                    parent.msgAlert("报备编号[" + reportId + "]的应计返佣税后不正确！")
                    return false;
                }

                // 非渠道的场合
                if (NullToEmpty(htedition) != '28301') {
                    // // ->房友返佣税前(元)
                    // var befFangyouYJFYAmount = $(this).find("input[name='befFangyouYJFYAmount']").val();
                    // if (isEmpty(befFangyouYJFYAmount)) {
                    //     msgAlert("请输入报备编号[" + reportId + "]的房友返佣税前!")
                    //     ret = false;
                    //     return false;
                    // }
                    // // ->房友返佣税后(元)
                    // var aftFangyouYJFYAmount = $(this).find("input[name='aftFangyouYJFYAmount']").val();
                    // if (isEmpty(aftFangyouYJFYAmount)) {
                    //     msgAlert("请输入报备编号[" + reportId + "]的房友返佣税后!")
                    //     ret = false;
                    //     return false;
                    // }

                    var befYjdyTaxAmount = $(this).find("input[name='befYjdyTaxAmount']").val();
                    var aftYjdyTaxAmount = $(this).find("input[name='aftYjdyTaxAmount']").val();

                    if (isEmpty(aftYjdyTaxAmount) && !isEmpty(befYjdyTaxAmount)) {
                        msgAlert("应计垫佣税前已填写,请输入报备编号[" + reportId + "]的应计垫佣税后!")
                        ret = false;
                        return false;
                    }
                    if (!isEmpty(aftYjdyTaxAmount) && isEmpty(befYjdyTaxAmount)) {
                        msgAlert("应计垫佣税后已填写,请输入报备编号[" + reportId + "]的应计垫佣税前!")
                        ret = false;
                        return false;
                    }
                    if (!isEmpty(aftYjdyTaxAmount) && !isEmpty(befYjdyTaxAmount)) {

                        if (!checkIsNumber(befYjdyTaxAmount)) {
                            parent.msgAlert("报备编号[" + reportId + "]的应计垫佣税前不正确！");
                            ret = false;
                            return false;
                        }

                        if (!checkIsNumber(aftYjdyTaxAmount)) {
                            parent.msgAlert("报备编号[" + reportId + "]的应计垫佣税后不正确！");
                            ret = false;
                            return false;
                        }
                    }


                    var dyRatio = parseFloat(NullToZero($(this).find("input[name='dyRatio']").val()));
                    var dyAmount = Math.round(parseFloat(befYjfyTaxAmount * dyRatio) * 100) / 100;
                    if (befYjdyTaxAmount > dyAmount) {
                        parent.msgAlert("报备编号[" + reportId + "]应计垫佣税前不能大于应返佣税前*总控垫佣比例！");
                        ret = false;
                        return false;


                    }
                }
            });

            if (ret == false) {
                return false;
            }
        }
        return true;
    }

    function saveToService(type) {

        //  校验核算主体
        var accountProjectList = [];
        var tbody = $("div[lay-id='contentReload'] .layui-table-main tbody");
        $(tbody).find("select[name='accountProject']").each(function () {
            var accountProject = {};
            accountProject.accountProject = $(this).find("option:selected").attr("data-name");
            accountProjectList.push(accountProject);
        });

        // 如果填写了核算主体，需要校验是否正确
        if (accountProjectList.length > 0) {
            doCheckAccuontProject(accountProjectList, function (data) {
                if (data.returnCode != '200') {
                    msgAlert(data.returnMsg)
                    return false;
                } else {
                    doSubimtToService(type)
                }
            }, function (data) {
                msgAlert(data.returnMsg)
                return false;
            })
        } else {
            doSubimtToService(type)
        }
    }

    function doCheckAccuontProject(accountProjectList, successback, failback) {

        var url = BASE_PATH + "/sceneTrade/checkAccountProject";
        var projectNo = $('#projectNo').val();
        var param = {
            accountProjectList: JSON.stringify(accountProjectList),
            projectNo: projectNo
        }
        $.ajax({
            url: url,
            data: param,
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data && data.returnCode == '200') {
                    if (successback) {
                        successback(data);
                    }
                    return;
                }
                if (failback) {
                    failback(data);
                }
            }
        });

    }

    function doSubimtToService(type) {
        var param = getData();
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        var url = BASE_PATH + "/sceneTrade/updateBatchSaleDetailAll";

        $.ajax({
            type: 'POST',
            url: url,
            data: {param: JSON.stringify(param)},
            dataType: "json",
            success: function (data) {
                parent.layer.close(loadIndex);
                if (data.returnCode == '0000') {

                    if (type == 0) {
                        parent.msgProp("保存成功")
                        parent.redirectTo('delete', null, null);
                    } else {
                        toSuccSale(param)
                    }

                } else {
                    msgAlert(data.returnMsg);
                    return false;
                }
            },
            error: function (data) {
                parent.layer.close(loadIndex);
                msgAlert("保存失败");
                return false;
            }
        });
    }

    function getData() {
        var param = [];
        // 成销时间
        var saleDate = isEmpty($("#saleDate").val()) ? null : $("#saleDate").val();
        // 结算确认日期
        var settlementDate = isEmpty($("#settlementDate").val()) ? null : $("#settlementDate").val();

        // 协议文件
        var fileIds = '';
        $("#uploadSaleImg .item_img").each(function () {
            fileIds += $(this).data("id") + ',';
        });
        if (fileIds != '') {
            fileIds = fileIds.substring(0, fileIds.length - 1);
        }
        var fileRecordMainIds = fileIds;

        var tbody = $("div[lay-id='contentReload'] .layui-table-main tbody");
        $(tbody).find("tr[tag!='sum']").each(function () {
            var detail = {};

            detail.reportId = $(this).find("input[name='reportId']").val();
            detail.batchDetailId = $(this).find("input[name='batchDetailId']").val();
            detail.batchId = $(this).find("input[name='batchId']").val();
            detail.companyNo = $(this).find("input[name='companyNo']").val();
            detail.floor = $(this).find("input[name='floor']").val();

            detail.customerName1 = trimStr($(this).find("input[name='customerName1']").val());
            detail.customerPhone1 = $(this).find("input[name='customerPhone1']").val();
            detail.customerName2 = trimStr($(this).find("input[name='customerName2']").val());
            detail.customerPhone2 = $(this).find("input[name='customerPhone2']").val();
            detail.saleAcreage = $(this).find("input[name='saleAcreage']").val();
            detail.saleAmount = $(this).find("input[name='saleAmount']").val();
            detail.accountProject = $(this).find("select[name='accountProject']").find("option:selected").text();

            detail.befYjsrTaxAmount = $(this).find("input[name='befYjsrTaxAmount']").val();
            detail.aftYjsrTaxAmount = $(this).find("input[name='aftYjsrTaxAmount']").val();

            // detail.befFangyouYJFYAmount = $(this).find("input[name='befFangyouYJFYAmount']").val();
            // detail.aftFangyouYJFYAmount = $(this).find("input[name='aftFangyouYJFYAmount']").val();

            detail.befYjfyTaxAmount = $(this).find("input[name='befYjfyTaxAmount']").val();
            detail.aftYjfyTaxAmount = $(this).find("input[name='aftYjfyTaxAmount']").val();
            detail.befYjdyTaxAmount = $(this).find("input[name='befYjdyTaxAmount']").val();
            detail.aftYjdyTaxAmount = $(this).find("input[name='aftYjdyTaxAmount']").val();

            if (!checkIsNumber(detail.saleAcreage)) {
                detail.saleAcreage = null;
            }
            if (!checkIsNumber(detail.saleAmount)) {
                detail.saleAmount = null;
            }
            if (!checkIsNumber(detail.befYjsrTaxAmount)) {
                detail.befYjsrTaxAmount = null;
            }
            if (!checkIsNumber(detail.aftYjsrTaxAmount)) {
                detail.aftYjsrTaxAmount = null;
            }

            // if (!checkIsNumber(detail.befFangyouYJFYAmount)) {
            //     detail.befFangyouYJFYAmount = null;
            // }
            // if (!checkIsNumber(detail.aftFangyouYJFYAmount)) {
            //     detail.aftFangyouYJFYAmount = null;
            // }


            if (!checkIsNumber(detail.befYjfyTaxAmount)) {
                detail.befYjfyTaxAmount = null;
            }
            if (!checkIsNumber(detail.aftYjfyTaxAmount)) {
                detail.aftYjfyTaxAmount = null;
            }
            if (!checkIsNumber(detail.befYjdyTaxAmount)) {
                detail.befYjdyTaxAmount = null;
            }
            if (!checkIsNumber(detail.aftYjdyTaxAmount)) {
                detail.aftYjdyTaxAmount = null;
            }

            detail.saleDate = saleDate;//成销时间
            detail.settlementDate = settlementDate;//结算确认日期
            detail.fileRecordMainIds = fileRecordMainIds;//协议

            param.push(detail);
        });

        return param;
    }

    function toSuccSale(param) {
        var url = BASE_PATH + "/sceneTrade/submitBatchSaleAll";
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        $.ajax({
            type: 'POST',
            url: url,
            data: {param: JSON.stringify(param)},
            dataType: "json",
            success: function (data) {
                parent.layer.close(loadIndex);
                if (data.returnCode == '0002') {
                    parent.msgAlert("报备编号：" + data.returnValue + "不符合成销条件，请删除后再提交!");
                    return false;
                } else if (data.returnCode == '0000') {
                    parent.msgProp("提交成功")
                    parent.redirectTo('delete', null, null);
                } else {
                    parent.msgError(data.returnValue);
                    return false;
                }
            },
            error: function (data) {
                parent.layer.close(loadIndex);
                parent.msgError("提交异常！");
                return false;
            }
        });
    }
});
batchSuccessSale = {}
batchSuccessSale.delete = function (object, batchSaleDetailId, batchId) {
    parent.msgConfirm("确认要删除吗？", function () {
        var url = BASE_PATH + "/sceneTrade/deleteBatchSaleDetailById"
        var param = {
            batchDetailId: batchSaleDetailId, batchId: batchId
        }
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        $.ajax({
            type: 'POST',
            url: url,
            data: param,
            dataType: "json",
            success: function (data) {
                parent.layer.close(loadIndex);
                if (data.returnCode == '0000') {

                    var index = $(object).parents("tr").attr("data-index");

                    var leftTBody = $("div[lay-id='contentReload'] .layui-table-fixed-l .layui-table-body tbody");
                    var mainTBody = $("div[lay-id='contentReload'] .layui-table-main tbody");
                    var rightTBody = $("div[lay-id='contentReload'] .layui-table-fixed-r .layui-table-body tbody");

                    $(leftTBody).find("tr[data-index='" + index + "']").remove();
                    $(mainTBody).find("tr[data-index='" + index + "']").remove();
                    $(rightTBody).find("tr[data-index='" + index + "']").remove();

                    if ($(mainTBody).find("tr[tag!='sum']").length == 0) {
                        parent.redirectTo('delete', null, null);
                    }

                    evalTotal();

                } else {
                    msgAlert(data.returnMsg)
                }
            },
            error: function (data) {
                parent.layer.close(loadIndex);
                msgAlert("删除失败！")

            }
        });
    });

}
batchSuccessSale.evalItem = function (itemName) {
    var itemTotal = 0.00;

    $("div[lay-id='contentReload'] .layui-table-main tbody").find("input[name='" + itemName + "']").each(function () {
        var value = $(this).val();
        itemTotal = itemTotal + formatAccount(value);
    });

    $("div[lay-id='contentReload'] .layui-table-main tbody").find("span[name='" + itemName + "']").html(formatCurrency(itemTotal));
}
batchSuccessSale.setFixRateAfterAmount = function (obj, bef, aft) {
    var taxRate = $("#taxRate").val();
    var befAmount = $(obj).parents("tr").find("input[name='" + bef + "']").val();
    if (!isEmpty(befAmount) && !isEmpty(taxRate)) {
        var aftAmount = parseFloat(befAmount * 100 / (100 + parseFloat(taxRate))).toFixed(2);
        $(obj).parents("tr").find("input[name='" + aft + "']").val(aftAmount);
    }
    batchSuccessSale.evalItem(aft)
}
batchSuccessSale.setHTRateAmonut = function (obj) {
    var commissionRatio = $(obj).parents("tr").find("input[name='commissionRatio']").val();
    var commissionAmount = $(obj).parents("tr").find("input[name='commissionAmount']").val();
    var saleAmount = $(obj).parents("tr").find("input[name='saleAmount']").val();

    if (!isEmpty(saleAmount) && (!isEmpty(commissionRatio) || !isEmpty(commissionAmount))) {
        var saleAmount = parseFloat(NullToZero(saleAmount));
        var commissionRatio = parseFloat(NullToZero(commissionRatio));
        var commissionAmount = parseFloat(NullToZero(commissionAmount));

        var befAmount = parseFloat(commissionAmount + parseFloat(saleAmount * commissionRatio / 100)).toFixed(2)
        var befObj = $(obj).parents("tr").find("input[name='befYjfyTaxAmount']")
        $(befObj).val(befAmount);
        batchSuccessSale.setFixRateAfterAmount(befObj, "befYjfyTaxAmount", "aftYjfyTaxAmount");
    }
    batchSuccessSale.evalItem("befYjfyTaxAmount")
}
batchSuccessSale.setDyRateAfterAmount = function (obj, fy, dy) {
    var fyAmount = $(obj).parents("tr").find("input[name='" + fy + "']").val();

    if (!isEmpty(fyAmount)) {

        var dyRatio = parseFloat(NullToZero($(obj).parents("tr").find("input[name='dyRatio']").val()));
        var dyAmount = parseFloat(fyAmount * dyRatio).toFixed(2);
        $(obj).parents("tr").find("input[name='" + dy + "']").val(dyAmount);

    }
}