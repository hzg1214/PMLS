var evalTotal = function () {
    batchReback.evalItem("saleArea");
    batchReback.evalItem("saleAmount");
    batchReback.evalItem("befYjsrTaxAmount");
    batchReback.evalItem("aftYjsrTaxAmount");
    batchReback.evalItem("befYjfyTaxAmount");
    batchReback.evalItem("aftYjfyTaxAmount");
}
var batchRebackDto = {};
var batchRebackDetails = [];

layui.use(['element', 'laydate', 'form', 'table', 'layer', 'upload'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        layer = layui.layer,
        upload = layui.upload,
        uploadExcel = layui.upload,
        formSelects = layui.formSelects,
        $ = layui.$,
        table = layui.table;


    init();

    function init() {

        if (window.batchRebackInfo != '') {
            // batchRebackDto = eval('(' + window.batchRebackInfo + ')');
            batchRebackDto = eval('(' + window.batchRebackInfo.replace(/[\r\n]/g,"\\n") + ')');
            batchRebackDetails = batchRebackDto.batchRebackDetails;
        }

        tableRender();

        var uploadSaleImg = [];
        if (batchRebackDto != null && batchRebackDto.fileList != null && batchRebackDto.fileList.length > 0) {
            for (var i = 0; i < batchRebackDto.fileList.length; i++) {
                if (batchRebackDto.fileList[i].fileTypeId == '1065') {
                    uploadSaleImg.push(batchRebackDto.fileList[i]);
                }
            }
        }

        loadImageList("uploadSaleImg", uploadSaleImg, true);

    }

    function tableRender() {
        table.render({
            elem: '#reportTable'
            , cols: setCols()
            , data: batchRebackDto.batchRebackDetails
            , id: 'contentReload'
            , page: false
            , limit: 1000
            , method: 'post'
            , request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
            , done: function (res, curr, count) {

                var nowDate = new Date().format('yyyy-MM-dd');

                var switchDate = $("#switchDate").val();
                if (isEmpty(switchDate)) {
                    switchDate = "1900-1-1";
                }

                //日期控件
                $(".layui-input-date").each(function (i) {
                    laydate.render({
                        elem: this,
                        format: "yyyy-MM-dd",
                        max: nowDate,
                        min: switchDate,
                        trigger: 'click',
                        done: function (value, date) {
                            if (res && res.data[i]) {
                                $.extend(res.data[i], {'rebackDate': value})
                            }
                        }
                    });
                });


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
                contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-7"><span name="saleArea"></span></div></td>';
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
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-14"></div></td>';
                contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-15"></div></td>';
                contentTrStr += '</tr>';


                var rightTrStr = '';
                rightTrStr += '<tr tag="sum">'
                rightTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-15"><span></span></div></td>';
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
            {field: 'reportId', fixed: true, title: '报备编号', width: 150, align: 'center'},
            {field: 'floor', fixed: true, title: '楼室号', width: 150, align: 'center'},
            {
                field: 'customerName1', title: '客户姓名', width: 150, align: 'center'
                , templet: function (d) {

                    var reportId = isEmpty(d.reportId) ? "" : d.reportId;
                    var batchDetailId = isEmpty(d.batchDetailId) ? "" : d.batchDetailId;
                    var batchId = isEmpty(d.batchId) ? "" : d.batchId;
                    var floor = isEmpty(d.floor) ? "" : d.floor;

                    var saleArea = isEmpty(d.saleArea) ? "" : d.saleArea;
                    var saleAmount = isEmpty(d.saleAmount) ? "" : d.saleAmount;
                    var befYjsrTaxAmount = isEmpty(d.befYjsrTaxAmount) ? "" : d.befYjsrTaxAmount;
                    var aftYjsrTaxAmount = isEmpty(d.aftYjsrTaxAmount) ? "" : d.aftYjsrTaxAmount;
                    var befYssrTaxAmount = isEmpty(d.befYssrTaxAmount) ? "" : d.befYssrTaxAmount;
                    var aftYssrTaxAmount = isEmpty(d.aftYssrTaxAmount) ? "" : d.aftYssrTaxAmount;
                    var customerName1 = isEmpty(d.customerName1) ? "" : d.customerName1;

                    var ret = ""
                    ret += '<input type="hidden" name="reportId" value="' + reportId + '">'
                    ret += '<input type="hidden" name="batchDetailId" value="' + batchDetailId + '">'
                    ret += '<input type="hidden" name="batchId" value="' + batchId + '">'
                    ret += '<input type="hidden" name="floor" value="' + floor + '">'


                    ret += '<input type="hidden" name="saleArea" value="' + saleArea + '">'
                    ret += '<input type="hidden" name="saleAmount" value="' + saleAmount + '">'

                    ret += '<input type="hidden" name="befYjsrTaxAmount" value="' + befYjsrTaxAmount + '">'
                    ret += '<input type="hidden" name="aftYjsrTaxAmount" value="' + aftYjsrTaxAmount + '">'
                    ret += '<input type="hidden" name="befYssrTaxAmount" value="' + befYssrTaxAmount + '">'
                    ret += '<input type="hidden" name="aftYssrTaxAmount" value="' + aftYssrTaxAmount + '">'
                    ret += '<input type="hidden" name="customerName1" value="' + customerName1 + '">'
                    ret += '<span>' + customerName1 + '</span>'
                    return ret;
                }
            },
            {
                field: 'customerPhone1', title: '客户手机号', width: 150, align: 'center', templet: function (d) {
                    var customerPhone1 = isEmpty(d.customerPhone1) ? "" : d.customerPhone1;
                    return customerPhone1;
                }
            },
            {
                field: 'customerName2', title: '客户姓名', width: 150, align: 'center', templet: function (d) {
                    var customerName2 = isEmpty(d.customerName2) ? "" : d.customerName2;
                    return customerName2;
                }
            },
            {
                field: 'customerPhone2', title: '客户手机号', width: 150, align: 'center', templet: function (d) {
                    var customerPhone2 = isEmpty(d.customerPhone2) ? "" : d.customerPhone2;
                    return customerPhone2;
                }
            },
            {
                field: 'saleArea', title: '成销面积(㎡)', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var saleArea = isEmpty(d.saleArea) ? "" : formatMoney(d.saleArea);
                    return saleArea;
                }
            },
            {
                field: 'saleAmount', title: '成销金额(元)', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var saleAmount = isEmpty(d.saleAmount) ? "0.00" : formatMoney(d.saleAmount);
                    return saleAmount;
                }
            },
            {
                field: 'accountProject', title: '核算主体', width: 200, align: 'center',
                templet: function (d) {
                    var accountProject = isEmpty(d.accountProject) ? "0.00" : d.accountProject;
                    return accountProject;
                }
            },
            {
                field: 'befYjsrTaxAmount', title: '应计收入税前(元)', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var befYjsrTaxAmount = isEmpty(d.befYjsrTaxAmount) ? "0.00" : formatMoney(d.befYjsrTaxAmount);
                    return befYjsrTaxAmount;
                }
            },
            {
                field: 'aftYjsrTaxAmount', title: '应计收入税后(元)', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var aftYjsrTaxAmount = isEmpty(d.aftYjsrTaxAmount) ? "0.00" : formatMoney(d.aftYjsrTaxAmount);
                    return aftYjsrTaxAmount;
                }
            },
            {
                field: 'befYssrTaxAmount', title: '应收收入税前(元)', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var befYssrTaxAmount = isEmpty(d.befYssrTaxAmount) ? "0.00" : formatMoney(d.befYssrTaxAmount);
                    return befYssrTaxAmount;
                }
            },
            {
                field: 'aftYssrTaxAmount', title: '应收收入税后(元)', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var aftYssrTaxAmount = isEmpty(d.aftYssrTaxAmount) ? "0.00" : formatMoney(d.aftYssrTaxAmount);
                    return aftYssrTaxAmount;
                }
            },
            {
                field: 'rebackDate', title: '退房日期', width: 200, align: 'center',
                templet: function (d) {
                    var rebackDate = isEmpty(d.rebackDate) ? "" : formatDate(d.rebackDate, "yyyy-MM-dd");
                    return '<input type="text" name="rebackDate" autocomplete="off"  lay-verify="verify" value="' + rebackDate + '" placeholder="请选择" class="layui-input layui-input-date renderInput ml" />';
                }
            },

            {

                title: '操作', fixed: 'right', align: 'center', width: 80,
                templet: function (d) {
                    return "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:batchReback.delete(this,\"" + d.batchDetailId + "\",\"" + d.batchId + "\")'>删除</a>";
                }
            }
        ];
        var cols = [];
        cols.push(row);
        return cols;
    }


    // 退房资料
    uploadRender('uploadSaleImg', {fileTypeId: '1065', fileSourceId: '5', exts: 'jpg|png|jpeg|pdf'});

    uploadExcel.render({
        elem: '#historyDataFile',
        url: BASE_PATH + '/sceneTrade/imputReback',
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

                            $(this).find("input[name='rebackDate']").val(record.rebackDate);
                        }
                    });

                }
            } else {
                parent.msgAlert(res.returnMsg);
            }
        }
    });


    var active = {
        submit: function () {
            toSave(1);
        }
        , save: function () {
            toSave(0);
        }
        , cancel: function () {
            parent.redirectTo('delete', null, null);
        }
        , goback: function () {
            parent.redirectTo('delete', null, null);
        }
        , export: function () {

            var projectNo = $("#projectNo").val();
            var estateId = $("#estateId").val();
            var estateNm = $("#estateName").val();
            var batchId = $("#batchId").val();
            var url = BASE_PATH + '/sceneTrade/exportRebackCheck';

            var cookieName = guid();
            var param = {
                batchId: batchId,
                estateId: estateId,
                estateNm: estateNm,
                projectNo: projectNo
            }
            restPost(url, param, function (data) {
                window.location.href = BASE_PATH + '/sceneTrade/exportReback?batchId=' + batchId
                    + "&projectNo=" + projectNo
                    + "&estateId=" + estateId
                    + "&estateNm=" + estateNm
                    + "&cookieName=" + cookieName;

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

        doSubimtToService(type);
    }

    //校验
    function vaild(type) {

        var nowDate = new Date().format('yyyy-MM-dd');
        var switchDate = $("#switchDate").val();
        if (isEmpty(switchDate)) {
            switchDate = "1900-1-1";
        }

        var ret = true;
        var tbody = $("div[lay-id='contentReload'] .layui-table-main tbody");
        $(tbody).find("tr[tag!='sum']").each(function () {
            var rebackDate = $(this).find("input[name='rebackDate']").val();
            if (isEmpty(rebackDate)) {
                parent.msgAlert("请选择退房日期！");
                ret = false;
                return false;
            }
            if (!dateCompare(rebackDate, switchDate)) {
                parent.msgAlert("退房日期不能在关账日期之前！")
                ret = false;
                return false;
            }
            if (!dateCompare(nowDate, rebackDate)) {
                parent.msgAlert("退房日期不能在当前日期之后！");
                ret = false;
                return false;
            }

        });

        if (ret == false) {
            return false;
        }

        // 退房资料
        var fileSize = $("#uploadSaleImg .item_img").size()
        if (fileSize == null || fileSize <= 0) {
            parent.msgAlert("请上传[退房资料]！")
            ret = false;
            return false;
        }
        return ret;
    }


    //保存/提交  1 ：提交  0 ：保存
    function doSubimtToService(type) {
        var param = getData();
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        var url = BASE_PATH + "/sceneTrade/updateBatchRebackDetailAll";

        $.ajax({
            type: 'POST',
            url: url,
            data: {param: JSON.stringify(param)},
            dataType: "json",
            success: function (data) {
                parent.layer.close(loadIndex);
                if (data.returnCode == '0000') {

                    if (type == 0) {//保存
                        parent.msgProp("保存成功");
                        parent.redirectTo('delete', null, null);
                    } else {//提交
                        toSuccSale(param)
                    }

                } else {
                    parent.msgAlert(data.returnMsg);
                    return false;
                }
            },
            error: function (data) {
                parent.layer.close(loadIndex);
                parent.msgAlert("保存失败");
                return false;
            }
        });
    }

    //获取参数
    function getData() {
        var param = [];

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

            detail.befYjsrAmount = $(this).find("input[name='befYjsrTaxAmount']").val();
            detail.aftYjsrAmount = $(this).find("input[name='aftYjsrTaxAmount']").val();
            detail.befYssrAmount = $(this).find("input[name='befYjfyTaxAmount']").val();
            detail.aftYssrAmount = $(this).find("input[name='aftYjfyTaxAmount']").val();

            if (!checkIsNumber(detail.befYjsrAmount)) {
                detail.befYjsrAmount = null;
            }
            if (!checkIsNumber(detail.aftYjsrAmount)) {
                detail.aftYjsrAmount = null;
            }
            if (!checkIsNumber(detail.befYssrAmount)) {
                detail.befYssrAmount = null;
            }
            if (!checkIsNumber(detail.aftYssrAmount)) {
                detail.aftYssrAmount = null;
            }

            detail.rebackDate = $(this).find("input[name='rebackDate']").val();
            detail.fileRecordMainIds = fileRecordMainIds;

            param.push(detail);
        });


        return param;
    }

    function toSuccSale(param) {
        var url = BASE_PATH + "/sceneTrade/submitBatchReback";
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
                    return false
                } else if (data.returnCode == '0000') {
                    parent.msgProp("提交成功")
                    parent.redirectTo('delete', null, null);
                }
                else {
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

batchReback = {}
batchReback.delete = function (object, batchSaleDetailId, batchId) {
    parent.msgConfirm("确认要删除吗？", function () {
        var url = BASE_PATH + "/sceneTrade/deleteBatchRebackDetailById"
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
                if (data.returnCode == '200') {

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
                    parent.layer.close(loadIndex);
                    parent.msgAlert(data.returnparent.msg)
                }
            },
            error: function (data) {
                parent.msgAlert("删除失败！")

            }
        });
    });

}
batchReback.evalItem = function (itemName) {
    var itemTotal = 0.00;

    $("div[lay-id='contentReload'] .layui-table-main tbody").find("input[name='" + itemName + "']").each(function () {
        var value = $(this).val();
        itemTotal = itemTotal + formatAccount(value);
    });

    $("div[lay-id='contentReload'] .layui-table-main tbody").find("span[name='" + itemName + "']").html(formatCurrency(itemTotal));
}