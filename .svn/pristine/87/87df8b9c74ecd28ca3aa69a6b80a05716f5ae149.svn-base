/**
 * Created by haidan on 2020/7/2.
 */



layui.use(['form', 'table', 'layer', 'upload'], function () {
    var submitObj = [];
    var form = layui.form,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;

    form.on('radio(allocateType)', function (data) {
        if (data.value == 0) {
            //按成销日期优先
            $(".marketAllocate").show();
            $(".customizeAllocate").hide();
        } else {
            //按房源自定义
            $(".marketAllocate").hide();
            $(".customizeAllocate").show();
        }
        clearTbl();
    });

    var active = {
        //预览
        previewAllocate: function () {
            var index = parent.layer.load(2);
            var srId = $("#srId").val();

            table.render({
                elem: '#allocateRecordTable'
                , cols: setCols()
                , id: 'contentReload'
                , url: BASE_PATH + '/skAllocate/previewAllocate'
                //, totalRow: true
                , height: "full"
                , even: false //开启隔行背景
                , page: false
                , limits: [10, 20, 30]
                , limit: 500 //默认采用60
                , method: 'post'
                , loading: false
                , where: {srId: srId}
                , request: {
                    pageName: 'curPage' //页码的参数名称，默认：page
                    , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
                }
                , response: {
                    statusName: 'returnCode' //数据状态的字段名称，默认：code
                    , statusCode: 200 //成功的状态码，默认：0
                    , msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                    , countName: 'totalCount' //数据总数的字段名称，默认：count
                    , dataName: 'returnData' //数据列表的字段名称，默认：data
                }
                , done: function (res, curr, count) {
                    submitObj = res.returnData;
                    console.log(submitObj);
                    if(submitObj.length > 0){
                        skStatementAllocate.addHejiRow();
                    }
                    parent.layer.close(index);
                }
            });
        },
        //下载模板
        downloadTemplate: function () {
            var index = parent.layer.load(2, {shade: 0.1});
            window.location.href = BASE_PATH + '/skAllocate/downloadTemplate?srId=' + $("#srId").val();
            parent.layer.closeAll();
        },
        //上传拆分订单附件
        uploadTemplate: function () {
            parent.layer.tblObj = [];
            clearTbl();
            parent.layer.open({
                type: 2,
                title: '拆分订单附件上传',
                area: ['500px', '400px'],
                content: BASE_PATH + '/skAllocate/toView',
                end: function () {
                    submitObj = parent.layer.tblObj;
                    console.log(submitObj);
                    table.reload('contentReload', {
                            data: submitObj,
                            cols: setCols(),
                            //totalRow: true,
                            height: "full",
                            even: false,
                            page: false,
                            done: function (res, curr, count) {
                                if(submitObj.length > 0){
                                    skStatementAllocate.addHejiRow();
                                }
                                parent.layer.closeAll();
                            }
                        }
                    );
                }
            });
        },
        //提交
        submitAllocate: function () {
            parent.layer.load(2);
            var allocateType = $("input[name='allocateType']:checked").val();
            if (!allocateType) {
                parent.layer.closeAll();
                parent.layer.alert('请选择拆分方式');
                return;
            }
            if (!submitObj || submitObj.length == undefined || submitObj.length < 1) {
                if (allocateType == 1) {
                    parent.layer.closeAll();
                    parent.layer.alert('请确定是否已上传附件');
                } else {
                    parent.layer.closeAll();
                    parent.layer.alert('请先预览可拆分订单，确认无误后再提交');
                }
                return;
            }

            parent.layer.confirm("确认提交吗？", {icon: 3, title: '提示'}, function () {
                var reqUrl = ctx + '/skAllocate/submitAllocate';
                console.log(submitObj);
                var srId = $("#srId").val();
                var allocatAmountBef = $("#allocatAmountBef").val();
                var reportNoList = "";
                var reportObj = new Object();
                for (var i = 0; i < submitObj.length; i++) {
                    var obj = submitObj[i];
                    reportNoList = reportNoList + obj.reportId + ",";
                    var num = new Number(obj.allocatAmount_bef);
                    reportObj[obj.reportId] = num.toFixed(2);
                }
                if (reportNoList.length > 1) reportNoList = reportNoList.substring(0, reportNoList.length - 1);

                $.ajax({
                    url: reqUrl,
                    type: 'post',
                    dataType: 'json',
                    data: {
                        reportObj: JSON.stringify(reportObj),
                        reportNoList: reportNoList,
                        id: srId,
                        allocateType: allocateType,
                        allocatAmountBef: allocatAmountBef
                    },
                    success: function (data) {
                        parent.layer.closeAll();
                        console.log(data);
                        if (data.returnCode == '200') {
                            parent.layer.alert(data.returnMsg, {icon: 1, title: '提示'});
                            parent.redirectTo('replace', ctx + '/skStatement', '收入明细');
                        } else {
                            parent.layer.alert(data.returnMsg);
                        }
                    }, fail: function () {
                        parent.layer.closeAll();
                        parent.layer.alert('请求异常');
                    }
                });
            }, function () {
                parent.layer.closeAll();
            });
        },
        //取消
        backList: function () {
            window.parent.redirectTo('delete');
        }
    };

    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function setCols() {
        var row = [];
        row.push(
            {
                field: 'reportId', title: '订单编号', width: 160, align: 'center', fixed: true
            },
            {
                field: 'customerName', title: '客户姓名', width: 180, align: 'left', fixed: true
            },
            {
                field: 'buildingNo', title: '楼室号', width: 180, align: 'left', fixed: true
            },
            {
                field: 'roughtArea', title: '大定面积', width: 120, align: 'right'
            },
            {
                field: 'roughtAmount', title: '大定总价', width: 150, align: 'right',
                templet: function (d) {
                    var saleAmount = isEmpty(d.roughtAmount) ? "0.00" : formatMoney(d.roughtAmount);
                    return saleAmount;
                }
            },
            {
                field: 'roughtDate', title: '大定日期', width: 130, align: 'center',
                templet: function (d) {
                    if (d.roughtDate == null || d.roughtDate == "") {
                        return "";
                    } else {
                        return formatDate(d.roughtDate, "yyyy-MM-dd");
                    }
                }
            },
            {
                field: 'roughAuditTime', title: '大定过审日期', width: 130, align: 'center',
                templet: function (d) {
                    if (d.roughAuditTime == null || d.roughAuditTime == "") {
                        return "";
                    } else {
                        return formatDate(d.roughAuditTime, "yyyy-MM-dd");
                    }
                }
            },
            {
                field: 'cxArea', title: '成销面积', width: 120, align: 'right'
            },
            {
                field: 'cxAmount', title: '成销总价', width: 150, align: 'right',
                templet: function (d) {
                    var saleAmount = isEmpty(d.cxAmount) ? "0.00" : formatMoney(d.cxAmount);
                    return saleAmount;
                }
            },
            {
                field: 'dealDate', title: '成销日期', width: 130, align: 'center',
                templet: function (d) {
                    if (d.dealDate == null || d.dealDate == "") {
                        return "";
                    } else {
                        return formatDate(d.dealDate, "yyyy-MM-dd");
                    }
                }
            },
            {
                field: 'yjAmount_bef', title: '成销收入(税前)', width: 150, align: 'right',
                templet: function (d) {
                    var saleAmount = isEmpty(d.yjAmount_bef) ? "0.00" : formatMoney(d.yjAmount_bef);
                    return "<input name='yjAmount_bef' type='hidden' value='"+ d.yjAmount_bef + "'><span>"+ saleAmount +"</span>";
                }
            },
            {
                field: 'sjAmount_bef', title: '成销实收金额(税前)', width: 160, align: 'right',
                templet: function (d) {
                    var saleAmount = isEmpty(d.sjAmount_bef) ? "0.00" : formatMoney(d.sjAmount_bef);
                    return "<input name='sjAmount_bef' type='hidden' value='"+ d.sjAmount_bef + "'><span>"+ saleAmount +"</span>";
                }
            },
            {
                field: 'unBackAmount_bef', title: '未回款金额(税前)', width: 150, align: 'right',
                templet: function (d) {
                    var unBackAmount_bef = d.unBackAmount_bef;
                    var saleAmount = isEmpty(unBackAmount_bef) ? "0.00" : formatMoney(unBackAmount_bef);
                    return "<input name='unBackAmount_bef' type='hidden' value='"+ d.unBackAmount_bef + "'><span>"+ saleAmount +"</span>";
                }
            },
            {
                field: 'allocatAmount_bef', title: '本次拆分金额(元)', width: 150, align: 'right',
                templet: function (d) {
                    var saleAmount = isEmpty(d.allocatAmount_bef) ? "0.00" : formatMoney(d.allocatAmount_bef);
                    return "<input name='allocatAmount_bef' type='hidden' value='"+ d.allocatAmount_bef + "'><span>"+ saleAmount +"</span>";
                }
            },
            {
                field: 'stayAmount_bef', title: '剩余拆分金额(元)', width: 150, align: 'right',
                templet: function (d) {
                    var stayAmount_bef = d.stayAmount_bef;
                    var saleAmount = isEmpty(stayAmount_bef) ? "0.00" : formatMoney(stayAmount_bef);
                    return "<input name='stayAmount_bef' type='hidden' value='"+ d.stayAmount_bef + "'><span>"+ saleAmount +"</span>";
                }
            }
        );
        var cols = [];
        cols.push(row);
        return cols;
    }
    
    function clearTbl() {
        table.render({
            elem: '#allocateRecordTable'
            , cols: []
            , id: 'contentReload'
        });

        submitObj = [];
    }

});

skStatementAllocate = {};

skStatementAllocate.addHejiRow = function () {
    var fixTrStr = '';
    fixTrStr += '<tr tag="sum">'
    fixTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-0 "></div></td>';
    fixTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-1"></div></td>';
    fixTrStr += '<td align="center"><div class="layui-table-cell laytable-cell-1-0-2">合计</div></td>';
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
    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-7"></div></td>';
    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-8"></div></td>';
    contentTrStr += '<td><div class="layui-table-cell laytable-cell-1-0-9"></div></td>';
    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-10"><span name="yjAmount_sum"></span></div></td>';
    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-11"><span name="sjAmount_sum"></span></div></td>';
    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-12"><span name="unBackAmount_sum"></span></div></td>';
    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-13"><span name="allocatAmount_sum"></span></div></td>';
    contentTrStr += '<td align="right"><div class="layui-table-cell laytable-cell-1-0-14"><span name="stayAmount_sum"></span></div></td>';

    var leftTable = $("div .layui-table-fixed-l ");
    var mainTable = $("div .layui-table-main ");

    $(leftTable).find(".layui-table-body tbody").append(fixTrStr);
    $(mainTable).find("tbody").append(contentTrStr);

    $(leftTable).removeClass('layui-hide');
    $(mainTable).find('div[class="layui-none"]').empty().remove();

    skStatementAllocate.evalNormalTotal();
};

skStatementAllocate.evalNormalTotal = function () {
    skStatementAllocate.evalNormalItem("yjAmount");
    skStatementAllocate.evalNormalItem("sjAmount");
    skStatementAllocate.evalNormalItem("unBackAmount");
    skStatementAllocate.evalNormalItem("allocatAmount");
    skStatementAllocate.evalNormalItem("stayAmount");
};

skStatementAllocate.evalNormalItem = function (itemName) {
    var itemTotal = 0.00;

    $("div .layui-table-main tbody").find("input[name='" + itemName + "_bef']").each(function () {
        var value = $(this).val();
        itemTotal = itemTotal + formatAccount(value);
    });
    itemTotal = formatAccount2(itemTotal);
    $("div .layui-table-main tbody").find("span[name='" + itemName + "_sum']").html(formatMoney(itemTotal));
    if(itemName == "allocatAmount"){
        $("#allocatAmountBef").val(itemTotal);
    }
};






