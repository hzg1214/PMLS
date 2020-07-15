var jsStatementJsonDto = {};
var jsStatementDtlJsonDto = {};
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        upload = layui.upload,
        $ = layui.$;

    var uploadSuccSaleImg = [], uploadProjectContractImg = [], uploadBranchContractImg = [], uploadOtherImg = [];
    if (fileListInfo != null && fileListInfo.length > 0) {
        for (var i = 0; i < fileListInfo.length; i++) {
            var fileInfo = fileListInfo[i];
            if (fileInfo.fileTypeId == '1101') {
                uploadSuccSaleImg.push(fileInfo);
            } else if (fileInfo.fileTypeId == '1102') {
                uploadProjectContractImg.push(fileInfo);
            } else if (fileInfo.fileTypeId == '1103') {
                uploadBranchContractImg.push(fileInfo);
            } else if (fileInfo.fileTypeId == '1104') {
                uploadOtherImg.push(fileInfo);
            }
        }
    }

    init();

    function init() {
        if (window.jsStatementDto != '') {
            jsStatementJsonDto = eval('(' + window.jsStatementDto.replace(/[\r\n]/g, "\\n") + ')');
            jsStatementDtlJsonDto = jsStatementJsonDto.jsStatementDtlList;
        }

        loadImageList("uploadSuccSaleImg", uploadSuccSaleImg, false);
        loadImageList("uploadProjectContractImg", uploadProjectContractImg, false)
        loadImageList("uploadBranchContractImg", uploadBranchContractImg, false)
        loadImageList("uploadOtherImg", uploadOtherImg, false)

        // 结算订单
        jsOrderTableRender(jsStatementDtlJsonDto);

    }

    //结算订单
    function jsOrderTableRender(jsStatementDtlJsonDto) {
        table.render({
            elem: '#jsOrderTable'
            , cols: setJsOrderCols()
            , data: jsStatementDtlJsonDto
            , id: 'logReload'
            , even: false //开启隔行背景
            , page: false
            , limits: [10, 20, 30]
            , limit: 1000 //默认采用60
            , method: 'post'
//            , totalRow: true
            , request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
            , done: function (res, curr, count) {
                $('th').css({'font-size': '12px'})
            }
        });
    }


    function setJsOrderCols() {
        var row = [
            {type: 'numbers', title: '序号', size: 12, fixed: true, width: 60, align: 'center'},
            {
                field: 'reportId', title: '订单编号', width: 135, fixed: true, align: 'center'
                , templet: function (d) {
                    if (d.id != null) {
                        return d.reportId;
                    } else {
                        return "";
                    }
                }
            },
            {
                field: 'customerName', title: '客户姓名', width: 100, fixed: true, align: 'center'
                , templet: function (d) {
                    if (d.id != null) {
                        return d.customerName;
                    } else {
                        return "";
                    }
                }
            },
            {
                field: 'buildingNo', title: '楼室号', width: 120, fixed: true, align: 'center'
                , templet: function (d) {
                    if (d.id != null) {
                        return d.buildingNo;
                    } else {
                        return "合计";
                    }
                }
            },
            {
                field: 'projectName', title: '项目', width: 200, align: 'center'
                , templet: function (d) {
                    if (d.id != null) {
                        return "<div style='text-align: left'>" + d.projectName + "</div>";
                    } else {
                        return "";
                    }

                }
            },
            {
                field: 'isGlobalControl', title: '垫佣控制规则', width: 105, align: 'center'
                , templet: function (d) {
                    if (d.id != null) {
                        if (d.isGlobalControl == '1') {
                            return '项目总控';
                        } else if (d.isGlobalControl == '0' || d.isGlobalControl == null || d.isGlobalControl == "") {
                            return '房源单控';
                        }
                    } else {
                        return "";
                    }
                }
            },
            {
                field: 'cxArea', title: '面积(㎡)', width: 100, align: 'center', totalRow: true
                , templet: function (d) {
                    return "<div style='text-align: right'>" + formatMoney(d.cxArea) + "</div>";
                }
            },
            {
                field: 'cxAmount', title: '签约总价(元)', width: 130, align: 'center', totalRow: true
                , templet: function (d) {
                    return "<div style='text-align: right'>" + formatMoney(d.cxAmount) + "</div>";
                }
            },
            {
                field: 'dealDate', title: '成销日期', width: 100, align: 'center',
                templet: function (d) {
                    if (d.dealDate == null) {
                        return "-";
                    } else {
                        return formatDate(d.dealDate, "yyyy-MM-dd");
                    }
                }
            },
            {
                field: 'serviceFeeDes', title: '服务费比例', width: 200, align: 'center'
                , templet: function (d) {
                    if (d.id != null) {
                        return "<div style='text-align: right'>" + d.serviceFeeDes + "</div>";
                    } else {
                        return "";
                    }
                }
            },
            {
                field: 'contractYdAmount', title: '合同约定金额(元)', width: 130, align: 'center', totalRow: true
                , templet: function (d) {
                    return "<div style='text-align: right'>" + formatMoney(d.contractYdAmount) + "</div>";
                }
            },
            {
                field: 'jsAmount', title: '本次结算金额(元)', width: 130, align: 'center', totalRow: true
                , templet: function (d) {
                    return "<div style='text-align: right'>" + formatMoney(d.jsAmount) + "</div>";
                }
            },
            {
                field: 'kpAmount', title: '实际开票金额(元)', width: 130, align: 'center', totalRow: true
                , templet: function (d) {
                    return "<div style='text-align: right'>" + formatMoney(d.kpAmount) + "</div>";
                }
            },
            {
                field: 'kpTaxAmount', title: '税额(元)', width: 130, align: 'center'
                , templet: function (d) {
                    return "<div style='text-align: right'>" + formatMoney(d.kpTaxAmount) + "</div>";
                }
            },
            {
                field: 'jsStatementType', title: '结算类型', width: 100, align: 'center'
                , templet: function (d) {
                    if (d.id != null) {
                        if (d.jsStatementType == -1) {
                            return '未知';
                        } else if (d.jsStatementType == 0) {
                            return '借佣';
                        } else if (d.jsStatementType == 1) {
                            return '返佣';
                        } else if (d.jsStatementType == 2) {
                            return '垫佣';
                        }
                    } else {
                        return "";
                    }
                }
            },
            {
                field: 'hsCode', title: '核算主体', width: 200, align: 'center'
                , templet: function (d) {
                    if (d.id != null) {
                        if (d.hsCode != null && d.hsCode != "" && d.hsCode != undefined) {
                            return "<div style='text-align: left'>" + d.hsCode + "_" + d.hsName + "</div>";
                        } else {
                            return "";
                        }
                    } else {
                        return "";
                    }
                }
            }
        ]
        var cols = [];
        cols.push(row);
        return cols;
    }
});

/**
 * 跳转到请款单详情
 * @param cashBillNo
 * @returns
 */
function toCashBillDetail(cashBillNo) {
    $.ajax({
        url: BASE_PATH + "/jsStatement/getCashBillDeatilByCashBillNo",
        type: "get",
        data: {
            cashBillNo: cashBillNo
        },
        success: function (data) {
            var endData = eval('(' + data.replace(/[\r\n]/g, "\\n") + ')');
            var result = endData.returnData;
            if (result != null && result != "") {
                var url = BASE_PATH + "/pmlsCashBill/getCashBillDeatilById/" + result.comParentId + "/" + result.proParentId
                parent.redirectTo('append', url, '请款详情');
            }
        }
    });
}
