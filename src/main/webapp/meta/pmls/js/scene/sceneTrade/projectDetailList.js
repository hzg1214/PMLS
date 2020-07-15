var active;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
        var element = layui.element,
            laydate = layui.laydate,
            form = layui.form,
            table = layui.table,
            layer = layui.layer,
            formSelects = layui.formSelects,
            $ = layui.$;

        var oaOperatorStr = $("#oaOperatorStr").val();
        var permission = NullToZero($("#permission").val());
        var expentPermit = NullToZero($("#expentPermit").val());

        init();

        function init() {
            var sessionData = sessionStorage.getItem('PMLS_SCENE_TRADE_PROJECT_DETAIL_SEARCH');
            // // 楼盘归属项目部
            // projectDepartmentLinkageIsService(function () {
            //     if (sessionData != null && sessionData != '') {
            //         var optionsData = JSON.parse(sessionData);
            //         $("#projectDepartmentId").val(optionsData.projectDepartmentId);
            //     }
            //     form.render('select')
            // });

            // 业绩归属中心
            centerGrpIdLinkageIsService(function () {
                if (sessionData != null && sessionData != '') {
                    var optionsData = JSON.parse(sessionData);
                    $("#centerId").val(optionsData.centerId);
                }
                form.render('select')
            });


            laydate.render({
                elem: '#dateStart',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    var startDate = new Date(value).getTime();
                    if ($('#dateEnd').val() != '') {
                        var endTime = new Date($('#dateEnd').val()).getTime();
                        if (endTime < startDate) {
                            layer.msg('开始时间不能大于结束时间');
                            $('#dateStart').val('');
                        }
                    }
                }
            });

            laydate.render({
                elem: '#dateEnd',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    if ($('#dateStart').val() != "") {
                        var startDate = new Date($('#dateStart').val()).getTime();
                        var endTime = new Date(value).getTime();
                        if (endTime < startDate) {
                            layer.msg('结束时间不能小于开始时间');
                            $('#dateEnd').val('');
                        }
                    }
                }
            });

            form.render('select'); // 刷新单选框渲染
            formSelects.render();   // 刷新多选框渲染

            tableRender();

            var optionsData = {};
            reloadData(optionsData);

        }

        function tableRender() {
            table.render({
                elem: '#contentTable'
                , cols: setCols()
                //, url: BASE_PATH + '/sceneTrade/qSceneRecognition'
                , id: 'contentReload'
                , page: true
                , height: "full"
                , limits: [10, 20, 30, 50, 100]
                , limit: 10 //默认采用60
                , method: 'post'
                , loading: true
                , request: {
                    pageName: 'curPage' //页码的参数名称，默认：page
                    , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
                }
                , where: {
                    estateId: $("#estateId").val()
                }
                , response: {
                    statusName: 'returnCode' //数据状态的字段名称，默认：code
                    , statusCode: 200 //成功的状态码，默认：0
                    , msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                    , countName: 'totalCount' //数据总数的字段名称，默认：count
                    , dataName: 'returnData' //数据列表的字段名称，默认：data
                }
            });
        }

        function setCols() {
            var row = [
                {
                    field: 'reportId', fixed: true, title: '订单编号', width: 150, align: 'center', templet: function (d) {
                        return "<a class='layui-table-link' onclick='javascript:projectDetailList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>" + d.reportId + "</a>";
                    }
                },
                {field: 'customerNm', fixed: true, title: '客户', width: 100, align: 'center'},
                {field: 'customerTel', title: '客户手机号', width: 150, align: 'center'},
                {field: 'buildingNo', title: '楼室号', width: 100, align: 'center'},
                {
                    field: 'companyNm', title: '经纪公司', width: 220, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.companyNm + "</div>";
                    }
                },
                {
                    field: 'latestProgressNmHandle', title: '订单状态', width: 100, align: 'center',
                    templet: function (d) {
                        if (d.brokerageStatus == '22002' || d.brokerageStatus == '22003') {
                            return "结佣";
                        } else {
                            return d.latestProgressNmHandle;
                        }
                    }
                },
                {field: 'confirmStatusNmHandle', title: '确认状态', width: 100, align: 'center'},
                {field: 'maintainerName', title: '业绩归属人', width: 100, align: 'center'},
                {field: 'centerName', title: '业绩归属人中心', width: 135, align: 'center'},
                {
                    field: 'reportDate', title: '报备日期', width: 120, align: 'center',
                    templet: function (d) {
                        if (d.reportDate == null) {
                            return "-";
                        } else {
                            return formatDate(d.reportDate, "yyyy-MM-dd");
                        }
                    }
                },
                {
                    field: 'seeDate', title: '带看日期', width: 120, align: 'center',
                    templet: function (d) {
                        if (d.seeDate == null) {
                            return "-";
                        } else {
                            return formatDate(d.seeDate, "yyyy-MM-dd");
                        }
                    }
                },
                {
                    field: 'roughInputDate', title: '大定日期', width: 120, align: 'center',
                    templet: function (d) {
                        if (d.roughInputDate == null) {
                            return "-";
                        } else {
                            return formatDate(d.roughInputDate, "yyyy-MM-dd");
                        }
                    }
                },
                {
                    field: 'roughAuditStatus', title: '大定审核状态', width: 120, align: 'center',
                    templet: function (d) {
                        if (d.roughAuditStatus == null) {
                            return "-";
                        } else {
                            if (d.brokerageStatus == '22002' || d.brokerageStatus == '22003'
                                || d.latestProgressNmHandle == '大定' || d.latestProgressNmHandle == '成销') {
                                if (d.roughAuditStatus == 0) {
                                    return "待审核";
                                } else if (d.roughAuditStatus == 1) {
                                    return "审核通过";
                                } else if (d.roughAuditStatus == 2) {
                                    return "审核驳回";
                                } else {
                                    return "-";
                                }
                            } else {
                                return "-";
                            }
                        }
                    }
                },
                {
                    field: 'dealDate', title: '成销日期', width: 120, align: 'center',
                    templet: function (d) {
                        if (d.dealDate == null) {
                            return "-";
                        } else {
                            return formatDate(d.dealDate, "yyyy-MM-dd");
                        }
                    }
                },
                {
                    field: 'brokerageUptDt', title: '结佣日期', width: 120, align: 'center',
                    templet: function (d) {
                        if (d.brokerageUptDt == null) {
                            return "-";
                        } else {
                            return formatDate(d.brokerageUptDt, "yyyy-MM-dd");
                        }
                    }
                },
                {
                    field: 'roughBackDate', title: '退定日期', width: 120, align: 'center',
                    templet: function (d) {
                        if (d.roughBackDate == null) {
                            return "-";
                        } else {
                            return formatDate(d.roughBackDate, "yyyy-MM-dd");
                        }
                    }
                },
                {
                    field: 'dealBackDate', title: '退房日期', width: 120, align: 'center',
                    templet: function (d) {
                        if (d.dealBackDate == null) {
                            return "-";
                        } else {
                            return formatDate(d.dealBackDate, "yyyy-MM-dd");
                        }
                    }
                },
                {field: 'customerFromStr', title: '报备来源', width: 100, align: 'center'},
                {
                    title: '操作', fixed: 'right', align: 'center', width: 350,
                    templet: function (d) {
                        var ret = "<div style='text-align: left'>"
                        ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectDetailList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>查看</a>";

                        if (d.confirmStatus == 13603 && d.latestProgress == 13502 && d.customerFrom != 17403 && d.customerFrom != 17405) {
                            ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectDetailList.watchAdd(\"" + d.reportId + "\")'>带看</a>";
                            ret += "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:projectDetailList.toInvalid(\"" + d.reportId + "\")'>无效</a>";
                        }
                        if (d.confirmStatus == 13603 && d.latestProgress == 13503 && d.customerFrom != 17403 && d.customerFrom != 17405) {
                            ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectDetailList.roughtAdd(\"" + d.reportId + "\")'>大定</a>";
                            ret += "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:projectDetailList.toInvalid(\"" + d.reportId + "\")'>无效</a>";
                        }
                        if (d.confirmStatus == 13603 && d.latestProgress == 13504 && d.customerFrom != 17403 && d.customerFrom != 17405) {
                            ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectDetailList.roughtAdd(\"" + d.reportId + "\")'>大定</a>";
                        }
                        if (d.confirmStatus == 13603 && d.latestProgress == 13505) {
                            if (d.roughAuditStatus == '0') {
                                if (permission == "1") {
                                    ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectDetailList.roughtAudit(\"" + d.reportId + "\")'>大定审核</a>";
                                }
                            }
                            if (d.roughAuditStatus == '1') {
                                if (permission == "1") {
                                    ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectDetailList.succSaleAdd(\"" + d.reportId + "\")'>成销</a>";
                                }
                            }
                            if (d.roughAuditStatus == '2' && (d.customerFrom == '17401' || d.customerFrom == '17402')) {
                                ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectDetailList.roughtUpt(\"" + d.reportId + "\")'>大定修改</a>";
                                ret += "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:projectDetailList.toInvalid(\"" + d.reportId + "\")'>无效</a>";
                            }
                            if (d.roughAuditStatus == '1') {
                                if (permission == "1") {
                                    ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectDetailList.backRought(\"" + d.reportId + "\")'>退定</a>";
                                }
                            }
                            if (d.roughAuditStatus == '1') {
                                if (permission == "1") {
                                    ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectDetailList.addBatchSale(\"" + d.reportId + "\")'>加入批量成销</a>";
                                }
                            }
                        }
                        if (d.confirmStatus == 13601 && d.latestProgress == 13505) {
                            if (permission == "1") {
                                //ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectDetailList.backSale(\"" + d.reportId + "\")'>退房</a>";
                                ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectDetailList.backSale(\"" + d.reportId + "\",\"" + d.brokerageStatus + "\",\"" + d.rebackFlag + "\",\"" + d.estateId + "\")'>退房</a>";
                                ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectDetailList.addBatchBack(\"" + d.reportId + "\",\"" + d.brokerageStatus + "\",\"" + d.rebackFlag + "\",\"" + d.estateId + "\")'>加入批量退房</a>";
                            }
                        }
//                        if (expentPermit == "1") {
//                            if (oaOperatorStr == '1' && (d.signDate != null || d.isSpecialProject == 2)
//                                && (d.confirmStatus == 13601 || (d.confirmStatus == 13603 && (d.isSpecialProject == 1 || d.isSpecialProject == 2)))
//                                && d.latestProgress == 13505 && d.roughAuditStatus == '1' && d.brokerageStatus != '22003') {
//                                ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:projectDetailList.addBatchExpent(\"" + d.reportId + "\")'>加入批量请款</a>";
//                            }
//                        }
                        ret += "</div>"
                        return ret;
                    }
                }
            ];
            var cols = [];
            cols.push(row);
            return cols;
        }

        active = {
            reload: function () {
                if (valid()) {
                    var optionsData = {};
                    // optionsData.projectDepartmentId = $("#projectDepartmentId").val();
                    optionsData.centerId = $("#centerId").val();
                    //optionsData.reportId = trimStr($("#reportId").val());
                    optionsData.progress = formSelects.value('status', 'valStr');
                    optionsData.progressArr = formSelects.value('status', 'val');

                    optionsData.customerFrom = $("#customerFrom").val();
                    optionsData.keyTypeKbn = $("#keyTypeKbn").val();
                    optionsData.keyword = trimStr($("#keyword").val());

                    // optionsData.buildingNo = trimStr($("#buildingNo").val());
                    // optionsData.companyNm = trimStr($("#companyNm").val());
                    // optionsData.customerNm = trimStr($("#customerNm").val());
                    optionsData.dateTypeKbn = $("#dateTypeKbn").val();
                    optionsData.dateStart = $("#dateStart").val();
                    optionsData.dateEnd = $("#dateEnd").val();

                    sessionStorage.removeItem('PMLS_SCENE_TRADE_PROJECT_DETAIL_SEARCH');
                    reloadData(optionsData);
                }
            },
            reset: function () {

                // $("#projectDepartmentId").val("");
                $("#centerId").val("");
                //$("#reportId").val("");
                $("#status").val("");
                // $("#buildingNo").val("");
                // $("#companyNm").val("");
                // $("#customerNm").val("");
                $("#customerFrom").val("");
                $("#dateTypeKbn").val("");
                $("#dateStart").val("");
                $("#dateEnd").val("");
                $("#keyTypeKbn").val("");
                $("#keyword").val("");
                formSelects.value('status', []);
                form.render('select');
                active.reload()

            },
            batchBackSale: function () {
                var num = $("#toBatchBackSale").attr("data-num");
                if (NullToZero(num) <= 0) {
                    parent.msgAlert("请先加入退房报备订单！");
                    return;
                }
                var projectNo = $("#projectNo").val();
                var estateId = $("#estateId").val();
                var estateNm = $("#estateName").val();
                var url = BASE_PATH + "/sceneTrade/toBatchRebackView?projectNo="
                    + projectNo + "&estateNm=" + estateNm + "&estateId=" + estateId;
                parent.redirectTo('append', url, '批量退房');

            },
            batchSuccessSale: function () {
                var num = $("#toBatchSuccessSale").attr("data-num");
                if (NullToZero(num) <= 0) {
                    parent.msgAlert("请先添加批量成销的报备房源再操作！");
                    return;
                }
                var projectNo = $("#projectNo").val();
                var url = BASE_PATH + "/sceneTrade/batchSuccessSale/" + projectNo;
                parent.redirectTo('append', url, '批量成销');
            },
            batchExpent: function () {
                var num = $("#toBatchExpent").attr("data-num");
                if (NullToZero(num) <= 0) {
                    parent.msgAlert("请先添加批量请款的报备房源再操作！");
                    return;
                }
                var estateId = $("#estateId").val();
                var url = BASE_PATH + "/sceneExpent/batchExpent/" + estateId;
                parent.redirectTo('append', url, '批量请款');
            },
            goback: function () {
                parent.redirectTo('delete', null, null);
            }
        };

        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        function reloadData(optionsData) {
            //var index = layer.load(2);

            var sessionData = sessionStorage.getItem('PMLS_SCENE_TRADE_PROJECT_DETAIL_SEARCH');
            var pageIndex = 1;
            if (sessionData != null && sessionData != '') {
                optionsData = JSON.parse(sessionData);
                pageIndex = optionsData.curr;

                $("#centerId").val(optionsData.centerId);
                //$("#projectDepartmentId").val(optionsData.projectDepartmentId);
                //$("#reportId").val(optionsData.reportId)
                formSelects.value('status', optionsData.progressArr);
                $("#customerFrom").val(optionsData.customerFrom);
                // $("#buildingNo").val(optionsData.buildingNo);
                // $("#companyNm").val(optionsData.companyNm);
                // $("#customerNm").val(optionsData.customerNm);
                $("#dateTypeKbn").val(optionsData.dateTypeKbn);
                $("#dateStart").val(optionsData.dateStart);
                $("#dateEnd").val(optionsData.dateEnd);
                $("#keyTypeKbn").val(optionsData.keyTypeKbn);
                $("#keyword").val(optionsData.keyword);

                form.render('select'); // 刷新单选框渲染
                //formSelects.render();   // 刷新多选框渲染
            }
            optionsData.estateId = $("#estateId").val();

            table.reload('contentReload', {
                url: BASE_PATH + '/sceneTrade/qSceneRecognition',
                cols: setCols(),
                height: window.innerHeight - $(".layui-table-view").offset().top - 10,
                where: optionsData,
                page: {
                    curr: pageIndex //重新从第 1 页开始
                },
                done: function (res, curr, count) {
                    //layer.close(index);
                    if (!optionsData) {
                        optionsData = {};
                    }
                    optionsData.curr = curr;
                    sessionStorage.setItem("PMLS_SCENE_TRADE_PROJECT_DETAIL_SEARCH", JSON.stringify(optionsData));
                }
            });
        }

        function valid() {
            return true;
        }

    }
);

var projectDetailList = {};

// 查看
projectDetailList.showDetail = function (estateId, companyId, customerId, id) {
    var url = BASE_PATH + "/sceneTrade/reportDetail/" + estateId + "/" + companyId + "/" + customerId + "/" + id;
    parent.redirectTo('append', url, '订单详情');
}
// 带看
projectDetailList.watchAdd = function (reportId) {
    var url = BASE_PATH + "/sceneTrade/watchAdd/" + reportId;
    parent.redirectTo('append', url, '新增带看');
}
// 无效
projectDetailList.toInvalid = function (reportId) {

    parent.msgConfirm("确认要无效吗？", function () {

        var url = BASE_PATH + "/sceneTrade/toInvalid";

        var estateId = $("#estateId").val();
        var params = {
            estateId: estateId,
            reportId: reportId
        };
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        ajaxGet(url, params, function (data) {
            parent.layer.close(loadIndex);
            if (data.returnData == 0) {
                parent.msgError(data.returnMsg);
            } else {
                active.reload();
                parent.msgProp("操作成功！");
            }
        }, function (data) {
            parent.layer.close(loadIndex);
        });
    }, function () {

    })


}
// 大定
projectDetailList.roughtAdd = function (reportId) {
    console.log("按项目 大定"+reportId);
    var flag = checkPreBack(reportId);
    if(!flag){
        parent.msgError("该报备订单已预退，不能继续大定、成销!");
        return false;
    }

    var url = BASE_PATH + "/sceneTrade/roughtAdd/" + reportId;
    parent.redirectTo('append', url, '新增大定');
}
// 大定审核
projectDetailList.roughtAudit = function (reportId) {
    var url = BASE_PATH + "/sceneTrade/roughtAudit/" + reportId;
    parent.redirectTo('append', url, '大定审核');
}
// 成销
projectDetailList.succSaleAdd = function (reportId) {
    console.log("按项目 成销"+reportId);
    var flag = checkPreBack(reportId);
    if(!flag){
        parent.msgError("该报备订单已预退，不能继续大定、成销!");
        return false;
    }

    var url = BASE_PATH + "/sceneTrade/succSaleAdd/" + reportId;
    parent.redirectTo('append', url, '成销');
}
// 大定修改
projectDetailList.roughtUpt = function (reportId) {
    var url = BASE_PATH + "/sceneTrade/roughtUpt/" + reportId;
    parent.redirectTo('append', url, '大定修改');
}
// 退定
projectDetailList.backRought = function (reportId) {
    var url = BASE_PATH + "/sceneTrade/backRought/" + reportId;
    parent.redirectTo('append', url, '退定');
}
// 退房
projectDetailList.backSale = function (reportId, brokerageStatus, rebackFlag, estateId) {
    // 如果进行退房解锁了，进入退房画面
    if (!isEmpty(rebackFlag) && "true" == rebackFlag) {
        var url = BASE_PATH + "/sceneTrade/backSale/" + reportId;
        parent.redirectTo('append', url, '退房');
    } else {
        // 如果没有解锁，那么判断当前状态是否已结佣或者部分结佣
        if ("22002" == brokerageStatus || "22003" == brokerageStatus) {
            parent.msgError("该报备订单状态是已结佣或者部分结佣，请通知对口结算解锁退房!");
            return;
        } else {
            // 状态为未结佣的场合，判断是否存在实际返佣
            var url = BASE_PATH + "/sceneTrade/checkSjfy";
            var params = {
                reportId: reportId
            };
            $.ajax({
                type: 'POST',
                url: url,
                data: params,
                dataType: "json",
                success: function (data) {
                    if (data.returnCode == "200") {
                        var url = BASE_PATH + "/sceneTrade/backSale/" + reportId;
                        parent.redirectTo('append', url, '退房');
                    } else {
                        parent.msgError(data.returnMsg);
                    }
                },
                error: function (data) {
                    parent.msgError("退房异常！");
                }
            });
        }
    }

}

// 加入批量退房
projectDetailList.addBatchBack = function (reportId, brokerageStatus, rebackFlag, estateId) {
    var loadIndex = parent.layer.load(2, {shade: [0.1]});

    var projectNo = $("#projectNo").val();
    var estateNm = $("#estateName").val();

    var url = BASE_PATH + "/sceneTrade/addBatchReback";
    var params = {
        reportId: reportId,
        brokerageStatus: brokerageStatus,
        rebackFlag: rebackFlag,
        projectNo: projectNo,
        estateId: estateId,
        estateNm: estateNm
    };

    ajaxGet(url, params, function (data) {
        parent.layer.close(loadIndex);
        if (data.returnData.code == 1) {
            parent.msgProp("批量退房添加成功！");
        } else if (data.returnData.code == 3) {
            parent.msgErrorProp("该报备订单已有实际返佣，请通知对口结算解锁退房!");
        } else if (data.returnData.code == 4) {
            parent.msgErrorProp("该报备订单状态是已结佣或者部分结佣，请通知对口结算解锁退房!");
        }
        else if (data.returnData.code == 2) {
            //msgAlertProp("已添加批量退房，请勿重复添加！");
            msgAlertProp(data.returnData.msg);
        }
        projectDetailList.refleshBatchBackNum();
    }, function (data) {
        parent.layer.close(loadIndex);
        parent.msgError("添加失败！");
    });
}
// 加入批量成销
projectDetailList.addBatchSale = function (reportId) {
    console.log("按项目 批量成销"+reportId);
    var flag = checkPreBack(reportId);
    if(!flag){
        parent.msgError("该报备订单已预退，不能加入批量成销!");
        return false;
    }

    var loadIndex = parent.layer.load(2, {shade: [0.1]});

    var url = BASE_PATH + "/sceneTrade/addBatchSale";

    var projectNo = $("#projectNo").val();
    var estateName = $("#estateName").val();
    var estateId = $("#estateId").val();
    var params = {
        projectNo: projectNo,
        estateId: estateId,
        estateName: estateName,
        reportId: reportId
    };
    ajaxGet(url, params, function (data) {
        parent.layer.close(loadIndex);
        if (data.returnData.code == 1) {
            parent.msgProp("批量成销添加成功！");
        } else if (data.returnData.code == 3) {
            parent.msgErrorProp("批量成销操作不适用多返佣对象的房源!");
        } else {
            //msgAlertProp("已添加批量成销，请勿重复添加！");
            msgAlertProp(data.returnData.msg);
        }
        projectDetailList.refleshBatchSaleNum();

    }, function (data) {
        parent.layer.close(loadIndex);
        parent.msgError("添加失败！");
    });
}
// 加入批量请款   废弃
projectDetailList.addBatchExpent = function (reportId) {
    var loadIndex = parent.layer.load(2, {shade: [0.1]});

    var url = BASE_PATH + "/sceneExpent/addBatchExpent";
    var params = {
        reportId: reportId
    };

    ajaxGet(url, params, function (data) {
        parent.layer.close(loadIndex);
        parent.msgProp(data.returnMsg)
        projectDetailList.refleshCashBillNum();
    }, function (data) {
        parent.layer.close(loadIndex);
        parent.msgError("添加失败！");
    });
}

// 刷新退房数量
projectDetailList.refleshBatchBackNum = function () {
    var url = BASE_PATH + "/sceneTrade/countBatchRebackDetail";
    var params = {
        estateId: $("input[name='estateId']").val()
    };

    ajaxGet(url, params, function (data) {
        if (parseInt(data.returnData) > 0) {
            $("#toBatchBackSale").html("批量退房(" + data.returnData + "套)");
            $("#toBatchBackSale").attr("data-num", data.returnData);
        } else {
            $("#toBatchBackSale").html("批量退房(0套)");
            $("#toBatchBackSale").attr("data-num", 0);
        }
    }, function (data) {
        msgAlert(data.returnMsg);
    });
}
// 刷新成销数量
projectDetailList.refleshBatchSaleNum = function () {
    var url = BASE_PATH + "/sceneTrade/countBatchSaleDetailAll";
    var params = {
        estateId: $("input[name='estateId']").val()
    };

    ajaxGet(url, params, function (data) {
        if (parseInt(data.returnData) > 0) {
            $("#toBatchSuccessSale").html("批量成销(" + data.returnData + "套)");
            $("#toBatchSuccessSale").attr("data-num", data.returnData);
        } else {
            $("#toBatchSuccessSale").html("批量成销(0套)");
            $("#toBatchSuccessSale").attr("data-num", 0);
        }
    }, function (data) {
        msgAlert(data.returnMsg);
    });
}
// 刷新批量请款  废弃
projectDetailList.refleshCashBillNum = function () {
    var url = BASE_PATH + "/sceneExpent/getBatchCashNumber";
    var params = {
        estateId: $("input[name='estateId']").val()
    };

    ajaxGet(url, params, function (data) {
        if (parseInt(data.returnData) > 0) {
            $("#toBatchExpent").html("批量请款(" + data.returnData + "套)");
            $("#toBatchExpent").attr("data-num", data.returnData);
        } else {
            $("#toBatchExpent").html("批量请款(0套)");
            $("#toBatchExpent").attr("data-num", 0);
        }
    }, function (data) {
        msgAlert(data.returnMsg);
    });

}

