var all;
var register;
var watch;
var rought;
var sale;
var commission;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        $ = layui.$;

    var currentUrl = BASE_PATH + "/sceneTrade/report";
    var defTab = "all";
    var backTab = $("#backTab").val();
    var permission = NullToZero($("#permission").val());

    // 全部
    all = {
        init: function () {
            laydate.render({
                elem: '#all-dateStart',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    var startDate = new Date(value).getTime();
                    if ($('#all-dateEnd').val() != '') {
                        var endTime = new Date($('#all-dateEnd').val()).getTime();
                        if (endTime < startDate) {
                            layer.msg('开始时间不能大于结束时间');
                            $('#dateStart').val('');
                        }
                    }
                }
            });

            laydate.render({
                elem: '#all-dateEnd',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    if ($('#all-dateStart').val() != "") {
                        var startDate = new Date($('#all-dateStart').val()).getTime();
                        var endTime = new Date(value).getTime();
                        if (endTime < startDate) {
                            layer.msg('结束时间不能小于开始时间');
                            $('#dateEnd').val('');
                        }
                    }
                }
            });

            // 表格数据
            this.tableRender();
        },

        tableRender: function () {
            table.render({
                elem: '#all-contentTable'
                , cols: all.setCols()
                // , url: BASE_PATH + '/sceneTrade/queryReportList'
                , date: []
                , id: 'all-contentReload'
                , height: "full"
                , loading: true
                , page: true
                , limits: [10, 20, 30]
                , limit: 10 //默认采用60
                , method: 'post'
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
            });
        },

        setCols: function () {
            var row = [
                {
                    field: 'reportId', fixed: true, title: '订单编号', width: 150, align: 'center', templet: function (d) {
                        return "<a class='layui-table-link' onclick='javascript:reportList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>" + d.reportId + "</a>";
                    }
                },
                {field: 'customerNm', fixed: true, title: '客户', width: 100, align: 'center'},
                {field: 'customerTel', title: '客户手机号', width: 120, align: 'center'},
                {
                    field: 'estateNm', title: '楼盘名称', width: 180, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.estateNm + "</div>";
                    }
                },
                {field: 'buildingNo', title: '楼室号', width: 100, align: 'center'},
                {
                    field: 'latestProgressNm', title: '订单状态', width: 100, align: 'center',
                    templet: function (d) {

                        if (d.brokerageStatus == '22002' || d.brokerageStatus == '22003') {
                            return "结佣";
                        }  else {
                            return d.latestProgressNm;
                        }
                    }
                },
                {field: 'confirmStatusNm', title: '确认状态', width: 100, align: 'center'},
                {
                    field: 'companyNm', title: '经纪公司', width: 220, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.companyNm + "</div>";
                    }
                },
                {field: 'contactNm', title: '业绩归属人', width: 100, align: 'center'},
                {field: 'centerName', title: '业绩归属人中心', width: 135, align: 'center'},
                {field: 'projectDepartmentName', title: '归属项目部', width: 120, align: 'center'},
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
                                || d.latestProgressNm == '大定' || d.latestProgressNm == '成销') {
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
                    title: '操作', fixed: 'right', align: 'center', width: 180,
                    templet: function (d) {
                        var ret = "<div style='text-align: left'>"
                        ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>查看</a>";

                        if (d.confirmStatus == 13603 && d.latestProgress == 13502 && d.customerFrom != 17403 && d.customerFrom != 17405) {
                            ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.watchAdd(\"" + d.reportId + "\")'>带看</a>";
                            ret += "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:reportList.toInvalid(\"" + d.reportId + "\",\"" + d.estateId + "\")'>无效</a>";
                        }
                        if (d.confirmStatus == 13603 && d.latestProgress == 13503 && d.customerFrom != 17403 && d.customerFrom != 17405) {
                            ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.roughtAdd(\"" + d.reportId + "\")'>大定</a>";
                            ret += "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:reportList.toInvalid(\"" + d.reportId + "\",\"" + d.estateId + "\")'>无效</a>";
                        }
                        if (d.confirmStatus == 13603 && d.latestProgress == 13504 && d.customerFrom != 17403 && d.customerFrom != 17405) {
                            ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.roughtAdd(\"" + d.reportId + "\")'>大定</a>";
                        }
                        if (d.confirmStatus == 13603 && d.latestProgress == 13505) {
                            if (d.roughAuditStatus == '0') {
                                if (permission == "1") {
                                    ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.roughtAudit(\"" + d.reportId + "\")'>大定审核</a>";
                                }
                            }
                            if (d.roughAuditStatus == '1') {
                                if (permission == "1") {
                                    ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.succSaleAdd(\"" + d.reportId + "\")'>成销</a>";
                                }
                            }
                            if (d.roughAuditStatus == '2' && (d.customerFrom == '17401' || d.customerFrom == '17402')) {
                                ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.roughtUpt(\"" + d.reportId + "\")'>大定修改</a>";
                                ret += "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:reportList.toInvalid(\"" + d.reportId + "\",\"" + d.estateId + "\")'>无效</a>";
                            }
                            if (d.roughAuditStatus == '1') {
                                if (permission == "1") {
                                    ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.backRought(\"" + d.reportId + "\")'>退定</a>";
                                }
                            }
                        }
                        if (d.confirmStatus == 13601 && d.latestProgress == 13505) {
                            if (permission == "1") {
                                ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.backSale(\"" + d.reportId + "\",\"" + d.brokerageStatus + "\",\"" + d.rebackFlag + "\",\"" + d.estateId + "\")'>退房</a>";
                            }
                        }

                        ret += "</div>"
                        return ret;
                    }
                }
            ];
            var cols = [];
            cols.push(row);
            return cols;
        },

        active: {
            reload: function () {
                if (all.valid()) {
                    var optionsData = {};
                    optionsData.projectDepartmentId = $("#all-projectDepartmentId").val();
                    optionsData.reportId = trimStr($("#all-reportId").val());
                    optionsData.customerFrom = $("#all-customerFrom").val();
                    // optionsData.companyNm = trimStr($("#all-companyNm").val());
                    // optionsData.buildingNo = trimStr($("#all-buildingNo").val());
                    // optionsData.customerNm = trimStr($("#all-customerNm").val());
                    optionsData.dateTypeKbn = $("#all-dateTypeKbn").val();
                    optionsData.dateStart = $("#all-dateStart").val();
                    optionsData.dateEnd = $("#all-dateEnd").val();
                    optionsData.keyTypeKbn = $("#all-keyTypeKbn").val();
                    optionsData.keyword = trimStr($("#all-keyword").val());

                    sessionStorage.removeItem('PMLS_SCENE_TRADE_REPORT_LIST_ALL_SEARCH');
                    all.reloadData(optionsData);
                }
            }
            , reset: function () {
                $("#all-projectDepartmentId").val("");
                $("#all-reportId").val("");
                $("#all-customerFrom").val("");
                // $("#all-companyNm").val("");
                // $("#all-buildingNo").val("");
                // $("#all-customerNm").val("");
                $("#all-dateTypeKbn").val("");
                $("#all-dateStart").val("");
                $("#all-dateEnd").val("");
                $("#all-keyTypeKbn").val("");
                $("#all-keyword").val("");
                form.render('select');
                all.active.reload();
            }
        },

        reloadData: function (optionsData) {
            //var index = layer.load(2);

            if (!optionsData) {
                optionsData = {};
            }

            var sessionData = sessionStorage.getItem('PMLS_SCENE_TRADE_REPORT_LIST_ALL_SEARCH');
            var pageIndex = 1;
            if (sessionData != null && sessionData != '') {
                optionsData = JSON.parse(sessionData);
                pageIndex = optionsData.curr;

                $("#all-projectDepartmentId").val(optionsData.projectDepartmentId);
                $("#all-reportId").val(optionsData.reportId);
                $("#all-customerFrom").val(optionsData.customerFrom );
                // $("#all-companyNm").val(optionsData.companyNm);
                // $("#all-buildingNo").val(optionsData.buildingNo);
                // $("#all-customerNm").val(optionsData.customerNm);
                $("#all-dateTypeKbn").val(optionsData.dateTypeKbn);
                $("#all-dateStart").val(optionsData.dateStart);
                $("#all-dateEnd").val(optionsData.dateEnd);
                $("#all-keyTypeKbn").val(optionsData.keyTypeKbn);
                $("#all-keyword").val(optionsData.keyword);
            }

            table.reload('all-contentReload', {
                url: BASE_PATH + '/sceneTrade/queryReportList',
                cols: all.setCols(),
                height: window.innerHeight - $("#allTabItem .layui-table-view").offset().top - 45,
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
                    sessionStorage.setItem("PMLS_SCENE_TRADE_REPORT_LIST_ALL_SEARCH", JSON.stringify(optionsData));
                }
            });
        },
        valid: function () {

            return true;
        }
    }

    // 报备
    register = {
        init: function () {

            laydate.render({
                elem: '#register-dateStart',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    var startDate = new Date(value).getTime();
                    if ($('#register-dateEnd').val() != '') {
                        var endTime = new Date($('#register-dateEnd').val()).getTime();
                        if (endTime < startDate) {
                            layer.msg('开始时间不能大于结束时间');
                            $('#register-dateStart').val('');
                        }
                    }
                }
            });

            laydate.render({
                elem: '#register-dateEnd',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    if ($('#register-dateStart').val() != "") {
                        var startDate = new Date($('#register-dateStart').val()).getTime();
                        var endTime = new Date(value).getTime();
                        if (endTime < startDate) {
                            layer.msg('结束时间不能小于开始时间');
                            $('#register-dateEnd').val('');
                        }
                    }
                }
            });

            // 表格数据
            this.tableRender();
        },

        tableRender: function () {
            table.render({
                elem: '#register-contentTable'
                , cols: register.setCols()
                //, url: BASE_PATH + '/sceneTrade/queryReportList'
                , id: 'register-contentReload'
                , data: []
                , height: "full"
                , loading: true
                , page: true
                , limits: [10, 20, 30]
                , limit: 10 //默认采用60
                , method: 'post'
                , request: {
                    pageName: 'curPage' //页码的参数名称，默认：page
                    , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
                }
                , where: {
                    latestProgress: '13501'
                }
                , response: {
                    statusName: 'returnCode' //数据状态的字段名称，默认：code
                    , statusCode: 200 //成功的状态码，默认：0
                    , msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                    , countName: 'totalCount' //数据总数的字段名称，默认：count
                    , dataName: 'returnData' //数据列表的字段名称，默认：data
                }
            });
        },

        setCols: function () {
            var row = [
                {
                    field: 'reportId', fixed: true, title: '订单编号', width: 150, align: 'center', templet: function (d) {
                        return "<a class='layui-table-link' onclick='javascript:reportList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>" + d.reportId + "</a>";
                    }
                },
                {field: 'customerNm', fixed: true, title: '客户', width: 100, align: 'center'},
                {field: 'customerTel', title: '客户手机号', width: 120, align: 'center'},
                {
                    field: 'estateNm', title: '楼盘名称', width: 180, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.estateNm + "</div>";
                    }
                },
                {
                    field: 'companyNm', title: '经纪公司', width: 220, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.companyNm + "</div>";
                    }
                },
                {field: 'confirmStatusNm', title: '确认状态', width: 100, align: 'center'},
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

                {field: 'contactNm', title: '业绩归属人', width: 100, align: 'center'},
                {field: 'centerName', title: '业绩归属人中心', width: 135, align: 'center'},
                {field: 'projectDepartmentName', title: '归属项目部', width: 120, align: 'center'},
                {field: 'customerFromStr', title: '报备来源', width: 100, align: 'center'},
                {
                    title: '操作', fixed: 'right', align: 'center', width: 160,
                    templet: function (d) {
                        var ret = "<div style='text-align: left'>"
                        ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>查看</a>";

                        if (d.confirmStatus == 13603 && d.latestProgress == 13502 && d.customerFrom != 17403 && d.customerFrom != 17405) {
                            ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.watchAdd(\"" + d.reportId + "\")'>带看</a>";
                            ret += "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:reportList.toInvalid(\"" + d.reportId + "\",\"" + d.estateId + "\")'>无效</a>";
                        }
                        ret += "</div>"
                        return ret;
                    }

                }
            ];
            var cols = [];
            cols.push(row);
            return cols;
        },

        active: {
            reload: function () {
                if (register.valid()) {
                    var optionsData = {};
                    optionsData.projectDepartmentId = $("#register-projectDepartmentId").val();
                    optionsData.reportId = trimStr($("#register-reportId").val());
                    optionsData.customerFrom = $("#register-customerFrom").val();
                    // optionsData.companyNm = trimStr($("#register-companyNm").val());
                    // optionsData.customerNm = trimStr($("#register-customerNm").val());
                    // optionsData.centerId = trimStr($("#register-centerId").val());
                    optionsData.dateTypeKbn = '13701';
                    optionsData.latestProgress = '13501';
                    optionsData.dateStart = $("#register-dateStart").val();
                    optionsData.dateEnd = $("#register-dateEnd").val();
                    optionsData.keyTypeKbn = $("#register-keyTypeKbn").val();
                    optionsData.keyword = trimStr($("#register-keyword").val());

                    sessionStorage.removeItem('PMLS_SCENE_TRADE_REPORT_LIST_REGISTER_SEARCH');
                    register.reloadData(optionsData);
                }
            },

            reset: function () {
                $("#register-projectDepartmentId").val("");
                $("#register-reportId").val("");
                $("#register-customerFrom").val("");
                // $("#register-companyNm").val("");
                // $("#register-centerId").val("")
                // $("#register-customerNm").val("");
                $("#register-dateStart").val("");
                $("#register-dateEnd").val("");
                $("#register-keyTypeKbn").val("");
                $("#register-keyword").val("");
                form.render('select');
                register.active.reload();
            }
        },

        reloadData: function (optionsData) {
            //var index = layer.load(2);

            if (!optionsData) {
                optionsData = {};
                optionsData.dateTypeKbn = '13701';
                optionsData.latestProgress = '13501';
            }

            var sessionData = sessionStorage.getItem('PMLS_SCENE_TRADE_REPORT_LIST_REGISTER_SEARCH');
            var pageIndex = 1;
            if (sessionData != null && sessionData != '') {
                optionsData = JSON.parse(sessionData);
                pageIndex = optionsData.curr;
                $("#register-projectDepartmentId").val(optionsData.projectDepartmentId);
                $("#register-reportId").val(optionsData.reportId);
                $("#register-customerFrom").val(optionsData.customerFrom );
                // $("#register-companyNm").val(optionsData.companyNm);
                // $("#register-centerId").val(optionsData.customerNm)
                // $("#register-customerNm").val(optionsData.centerId);
                $("#register-dateStart").val(optionsData.dateStart);
                $("#register-dateEnd").val(optionsData.dateEnd);
                $("#register-keyTypeKbn").val(optionsData.keyTypeKbn);
                $("#register-keyword").val(optionsData.keyword);

                form.render('select');

            }
            table.reload('register-contentReload', {
                url: BASE_PATH + '/sceneTrade/queryReportList',
                cols: register.setCols(),
                height: window.innerHeight - $("#registerTabItem .layui-table-view").offset().top - 45,
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
                    sessionStorage.setItem("PMLS_SCENE_TRADE_REPORT_LIST_REGISTER_SEARCH", JSON.stringify(optionsData));
                }
            });
        },

        valid: function () {
            return true;
        }
    }

    // 带看
    watch = {
        init: function () {

            laydate.render({
                elem: '#watch-dateStart',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    var startDate = new Date(value).getTime();
                    if ($('#watch-dateEnd').val() != '') {
                        var endTime = new Date($('#watch-dateEnd').val()).getTime();
                        if (endTime < startDate) {
                            layer.msg('开始时间不能大于结束时间');
                            $('#watch-dateStart').val('');
                        }
                    }
                }
            });

            laydate.render({
                elem: '#watch-dateEnd',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    if ($('#watch-dateStart').val() != "") {
                        var startDate = new Date($('#watch-dateStart').val()).getTime();
                        var endTime = new Date(value).getTime();
                        if (endTime < startDate) {
                            layer.msg('结束时间不能小于开始时间');
                            $('#watch-dateEnd').val('');
                        }
                    }
                }
            });

            // 表格数据
            this.tableRender();
        },

        tableRender: function () {
            table.render({
                elem: '#watch-contentTable'
                , cols: watch.setCols()
                //, url: BASE_PATH + '/sceneTrade/queryReportList'
                , id: 'watch-contentReload'
                , data: []
                , height: "full"
                , loading: true
                , page: true
                , limits: [10, 20, 30]
                , limit: 10 //默认采用60
                , method: 'post'
                , request: {
                    pageName: 'curPage' //页码的参数名称，默认：page
                    , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
                }
                , where: {
                    latestProgress: '13502'
                }
                , response: {
                    statusName: 'returnCode' //数据状态的字段名称，默认：code
                    , statusCode: 200 //成功的状态码，默认：0
                    , msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                    , countName: 'totalCount' //数据总数的字段名称，默认：count
                    , dataName: 'returnData' //数据列表的字段名称，默认：data
                }
            });
        },

        setCols: function () {
            var row = [
                {
                    field: 'reportId', fixed: true, title: '订单编号', width: 150, align: 'center', templet: function (d) {
                        return "<a class='layui-table-link' onclick='javascript:reportList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>" + d.reportId + "</a>";
                    }
                },
                {field: 'customerNm', fixed: true, title: '客户', width: 100, align: 'center'},
                {field: 'customerTel', title: '客户手机号', width: 120, align: 'center'},
                {
                    field: 'estateNm', title: '楼盘名称', width: 180, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.estateNm + "</div>";
                    }
                },
                {
                    field: 'companyNm', title: '经纪公司', width: 220, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.companyNm + "</div>";
                    }
                },
                {field: 'confirmStatusNm', title: '确认状态', width: 100, align: 'center'},
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

                {field: 'contactNm', title: '业绩归属人', width: 100, align: 'center'},
                {field: 'centerName', title: '业绩归属人中心', width: 135, align: 'center'},
                {field: 'projectDepartmentName', title: '归属项目部', width: 120, align: 'center'},
                {field: 'customerFromStr', title: '报备来源', width: 100, align: 'center'},
                {
                    title: '操作', fixed: 'right', align: 'center', width: 160,
                    templet: function (d) {
                        var ret = "<div style='text-align: left'>"
                        ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>查看</a>";

                        if (d.confirmStatus == 13603 && d.latestProgress == 13503 && d.customerFrom != 17403 && d.customerFrom != 17405) {
                            ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.roughtAdd(\"" + d.reportId + "\")'>大定</a>";
                            ret += "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:reportList.toInvalid(\"" + d.reportId + "\",\"" + d.estateId + "\")'>无效</a>";
                        }
                        if (d.confirmStatus == 13603 && d.latestProgress == 13504 && d.customerFrom != 17403 && d.customerFrom != 17405) {
                            ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.roughtAdd(\"" + d.reportId + "\")'>大定</a>";
                        }
                        ret += "</div>"
                        return ret;
                    }
                }
            ];
            var cols = [];
            cols.push(row);
            return cols;
        },

        active: {
            reload: function () {
                if (watch.valid()) {
                    var optionsData = {};
                    optionsData.projectDepartmentId = $("#watch-projectDepartmentId").val();
                    optionsData.reportId = trimStr($("#watch-reportId").val());
                    optionsData.customerFrom = $("#watch-customerFrom").val();
                    // optionsData.companyNm = trimStr($("#watch-companyNm").val());
                    // optionsData.customerNm = trimStr($("#watch-customerNm").val());
                    // optionsData.centerId = trimStr($("#watch-centerId").val());
                    optionsData.dateTypeKbn = '13702';
                    optionsData.latestProgress = '13502';
                    optionsData.dateStart = $("#watch-dateStart").val();
                    optionsData.dateEnd = $("#watch-dateEnd").val();
                    optionsData.keyTypeKbn = $("#watch-keyTypeKbn").val();
                    optionsData.keyword = trimStr($("#watch-keyword").val());

                    sessionStorage.removeItem('PMLS_SCENE_TRADE_REPORT_LIST_WATCH_SEARCH');
                    watch.reloadData(optionsData);
                }
            },

            reset: function () {
                $("#watch-projectDepartmentId").val("");
                $("#watch-reportId").val("");
                $("#watch-customerFrom").val("");
                // $("#watch-companyNm").val("");
                // $("#watch-centerId").val("")
                // $("#watch-customerNm").val("");
                $("#watch-dateStart").val("");
                $("#watch-dateEnd").val("");
                $("#watch-keyTypeKbn").val("");
                $("#watch-keyword").val("");

                form.render('select');
                watch.active.reload()
            }
        },

        reloadData: function (optionsData) {
            //var index = layer.load(2);

            if (!optionsData) {
                optionsData = {};
                optionsData.dateTypeKbn = '13702';
                optionsData.latestProgress = '13502';
            }
            var sessionData = sessionStorage.getItem('PMLS_SCENE_TRADE_REPORT_LIST_WATCH_SEARCH');
            var pageIndex = 1;
            if (sessionData != null && sessionData != '') {
                optionsData = JSON.parse(sessionData);
                pageIndex = optionsData.curr;
                $("#watch-projectDepartmentId").val(optionsData.projectDepartmentId);
                $("#watch-reportId").val(optionsData.reportId);
                $("#watch-customerFrom").val(optionsData.customerFrom );
                // $("#watch-companyNm").val(optionsData.companyNm);
                // $("#watch-centerId").val(optionsData.centerId)
                // $("#watch-customerNm").val(optionsData.customerNm);
                $("#watch-dateStart").val(optionsData.dateStart);
                $("#watch-dateEnd").val(optionsData.dateEnd);
                $("#watch-keyTypeKbn").val(optionsData.keyTypeKbn);
                $("#watch-keyword").val(optionsData.keyword);
                form.render('select');
            }

            table.reload('watch-contentReload', {
                url: BASE_PATH + '/sceneTrade/queryReportList',
                cols: watch.setCols(),
                height: window.innerHeight - $("#watchTabItem .layui-table-view").offset().top - 45,
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
                    sessionStorage.setItem("PMLS_SCENE_TRADE_REPORT_LIST_WATCH_SEARCH", JSON.stringify(optionsData));
                }
            });
        },
        valid: function () {
            return true;
        }
    }

    // 大定
    rought = {
        init: function () {

            laydate.render({
                elem: '#rought-dateStart',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    var startDate = new Date(value).getTime();
                    if ($('#rought-dateEnd').val() != '') {
                        var endTime = new Date($('#rought-dateEnd').val()).getTime();
                        if (endTime < startDate) {
                            layer.msg('开始时间不能大于结束时间');
                            $('#rought-dateStart').val('');
                        }
                    }
                }
            });

            laydate.render({
                elem: '#rought-dateEnd',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    if ($('#rought-dateStart').val() != "") {
                        var startDate = new Date($('#rought-dateStart').val()).getTime();
                        var endTime = new Date(value).getTime();
                        if (endTime < startDate) {
                            layer.msg('结束时间不能小于开始时间');
                            $('#rought-dateEnd').val('');
                        }
                    }
                }
            });

            // 表格数据
            this.tableRender();
        },

        tableRender: function () {
            table.render({
                elem: '#rought-contentTable'
                , cols: rought.setCols()
                //, url: BASE_PATH + '/sceneTrade/queryReportList'
                , id: 'rought-contentReload'
                , data: []
                , height: "full"
                , loading: true
                , page: true
                , limits: [10, 20, 30]
                , limit: 10 //默认采用60
                , method: 'post'
                , request: {
                    pageName: 'curPage' //页码的参数名称，默认：page
                    , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
                }
                , where: {
                    latestProgress: '13504'
                }
                , response: {
                    statusName: 'returnCode' //数据状态的字段名称，默认：code
                    , statusCode: 200 //成功的状态码，默认：0
                    , msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                    , countName: 'totalCount' //数据总数的字段名称，默认：count
                    , dataName: 'returnData' //数据列表的字段名称，默认：data
                }
            });
        },

        setCols: function () {
            var row = [
                {
                    field: 'reportId', fixed: true, title: '订单编号', width: 150, align: 'center', templet: function (d) {
                        return "<a class='layui-table-link' onclick='javascript:reportList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>" + d.reportId + "</a>";
                    }
                },
                {field: 'customerNm', fixed: true, title: '客户', width: 100, align: 'center'},
                {field: 'customerTel', title: '客户手机号', width: 120, align: 'center'},
                {
                    field: 'estateNm', title: '楼盘名称', width: 180, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.estateNm + "</div>";
                    }
                },
                {field: 'buildingNo', title: '楼室号', width: 100, align: 'center'},
                {
                    field: 'companyNm', title: '经纪公司', width: 220, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.companyNm + "</div>";
                    }
                },
                {
                    field: 'roughAuditStatus', title: '大定审核状态', width: 120, align: 'center',
                    templet: function (d) {
                        if (d.roughAuditStatus == null) {
                            return "-";
                        } else {
                            if (d.brokerageStatus == '22002' || d.brokerageStatus == '22003'
                                || d.latestProgressNm == '大定' || d.latestProgressNm == '成销') {
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
                    field: 'roughInputDate', title: '大定日期', width: 120, align: 'center',
                    templet: function (d) {
                        if (d.roughInputDate == null) {
                            return "-";
                        } else {
                            return formatDate(d.roughInputDate, "yyyy-MM-dd");
                        }
                    }
                },
                {field: 'contactNm', title: '业绩归属人', width: 100, align: 'center'},
                {field: 'centerName', title: '业绩归属人中心', width: 135, align: 'center'},
                {field: 'projectDepartmentName', title: '归属项目部', width: 120, align: 'center'},
                {field: 'customerFromStr', title: '报备来源', width: 100, align: 'center'},
                {
                    title: '操作', fixed: 'right', align: 'center', width: 180,
                    templet: function (d) {
                        var ret = "<div style='text-align: left'>"
                        ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>查看</a>";

                        if (d.confirmStatus == 13603 && d.latestProgress == 13505) {
                            if (d.roughAuditStatus == '0') {
                                if (permission == "1") {
                                    ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.roughtAudit(\"" + d.reportId + "\")'>大定审核</a>";
                                }
                            }
                            if (d.roughAuditStatus == '1') {
                                if (permission == "1") {
                                    ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.succSaleAdd(\"" + d.reportId + "\")'>成销</a>";
                                }
                            }
                            if (d.roughAuditStatus == '2' && (d.customerFrom == '17401' || d.customerFrom == '17402')) {
                                ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.roughtUpt(\"" + d.reportId + "\")'>大定修改</a>";
                                ret += "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:reportList.toInvalid(\"" + d.reportId + "\",\"" + d.estateId + "\")'>无效</a>";
                            }
                            if (d.roughAuditStatus == '1') {
                                if (permission == "1") {
                                    ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.backRought(\"" + d.reportId + "\")'>退定</a>";
                                }
                            }
                        }
                        ret += "</div>"
                        return ret;
                    }
                }
            ];
            var cols = [];
            cols.push(row);
            return cols;
        },

        active: {
            reload: function () {
                if (rought.valid()) {
                    var optionsData = {};
                    optionsData.projectDepartmentId = $("#rought-projectDepartmentId").val();
                    optionsData.reportId = trimStr($("#rought-reportId").val());
                    optionsData.customerFrom = $("#rought-customerFrom").val();
                    // optionsData.companyNm = trimStr($("#rought-companyNm").val());
                    // optionsData.customerNm = trimStr($("#rought-customerNm").val());
                    // optionsData.buildingNo = trimStr($("#rought-buildingNo").val());
                    optionsData.dateTypeKbn = '13703';
                    optionsData.latestProgress = '13504';
                    optionsData.dateStart = $("#rought-dateStart").val();
                    optionsData.dateEnd = $("#rought-dateEnd").val();
                    optionsData.keyTypeKbn = $("#rought-keyTypeKbn").val();
                    optionsData.keyword = trimStr($("#rought-keyword").val());

                    sessionStorage.removeItem('PMLS_SCENE_TRADE_REPORT_LIST_ROUGHT_SEARCH');
                    rought.reloadData(optionsData);
                }
            },

            reset: function () {
                $("#rought-projectDepartmentId").val("");
                $("#rought-reportId").val("");
                $("#rought-customerFrom").val("");
                // $("#rought-companyNm").val("");
                // $("#rought-buildingNo").val("")
                // $("#rought-customerNm").val("");
                $("#rought-dateStart").val("");
                $("#rought-dateEnd").val("");
                $("#rought-keyTypeKbn").val("");
                $("#rought-keyword").val("");
                form.render('select');
                rought.active.reload();
            }
        },

        reloadData: function (optionsData) {
            //var index = layer.load(2);

            if (!optionsData) {
                optionsData = {};
                optionsData.dateTypeKbn = '13703';
                optionsData.latestProgress = '13504';
            }
            var sessionData = sessionStorage.getItem('PMLS_SCENE_TRADE_REPORT_LIST_ROUGHT_SEARCH');
            var pageIndex = 1;
            if (sessionData != null && sessionData != '') {
                optionsData = JSON.parse(sessionData);
                pageIndex = optionsData.curr;
                $("#rought-projectDepartmentId").val(optionsData.projectDepartmentId);
                $("#rought-reportId").val(optionsData.reportId);
                $("#rought-customerFrom").val(optionsData.customerFrom );
                // $("#rought-companyNm").val(optionsData.companyNm);
                // $("#rought-buildingNo").val(optionsData.buildingNo)
                // $("#rought-customerNm").val(optionsData.customerNm);
                $("#rought-dateStart").val(optionsData.dateStart);
                $("#rought-dateEnd").val(optionsData.dateEnd);
                $("#rought-keyTypeKbn").val(optionsData.keyTypeKbn);
                $("#rought-keyword").val(optionsData.keyword);
                form.render('select');
            }

            table.reload('rought-contentReload', {
                url: BASE_PATH + '/sceneTrade/queryReportList',
                cols: rought.setCols(),
                height: window.innerHeight - $("#roughtTabItem .layui-table-view").offset().top - 45,
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
                    sessionStorage.setItem("PMLS_SCENE_TRADE_REPORT_LIST_ROUGHT_SEARCH", JSON.stringify(optionsData));
                }
            });
        },
        valid: function () {
            return true;
        }
    }

    // 成销
    sale = {
        init: function () {

            laydate.render({
                elem: '#sale-dateStart',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    var startDate = new Date(value).getTime();
                    if ($('#sale-dateEnd').val() != '') {
                        var endTime = new Date($('#sale-dateEnd').val()).getTime();
                        if (endTime < startDate) {
                            layer.msg('开始时间不能大于结束时间');
                            $('#sale-dateStart').val('');
                        }
                    }
                }
            });

            laydate.render({
                elem: '#sale-dateEnd',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    if ($('#sale-dateStart').val() != "") {
                        var startDate = new Date($('#sale-dateStart').val()).getTime();
                        var endTime = new Date(value).getTime();
                        if (endTime < startDate) {
                            layer.msg('结束时间不能小于开始时间');
                            $('#sale-dateEnd').val('');
                        }
                    }
                }
            });

            // 表格数据
            this.tableRender();
        },

        tableRender: function () {
            table.render({
                elem: '#sale-contentTable'
                , cols: sale.setCols()
                //, url: BASE_PATH + '/sceneTrade/queryReportList'
                , id: 'sale-contentReload'
                , data: []
                , height: "full"
                , loading: true
                , page: true
                , limits: [10, 20, 30]
                , limit: 10 //默认采用60
                , method: 'post'
                , request: {
                    pageName: 'curPage' //页码的参数名称，默认：page
                    , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
                }
                , where: {
                    latestProgress: '13505'
                }
                , response: {
                    statusName: 'returnCode' //数据状态的字段名称，默认：code
                    , statusCode: 200 //成功的状态码，默认：0
                    , msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                    , countName: 'totalCount' //数据总数的字段名称，默认：count
                    , dataName: 'returnData' //数据列表的字段名称，默认：data
                }
            });
        },

        setCols: function () {
            var row = [
                {
                    field: 'reportId', fixed: true, title: '订单编号', width: 150, align: 'center', templet: function (d) {
                        return "<a class='layui-table-link' onclick='javascript:reportList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>" + d.reportId + "</a>";
                    }
                },
                {field: 'customerNm', fixed: true, title: '客户', width: 100, align: 'center'},
                {field: 'customerTel', title: '客户手机号', width: 120, align: 'center'},
                {
                    field: 'estateNm', title: '楼盘名称', width: 180, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.estateNm + "</div>";
                    }
                },
                {
                    field: 'companyNm', title: '经纪公司', width: 220, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.companyNm + "</div>";
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
                {field: 'contactNm', title: '业绩归属人', width: 100, align: 'center'},
                {field: 'centerName', title: '业绩归属人中心', width: 135, align: 'center'},
                {field: 'projectDepartmentName', title: '归属项目部', width: 120, align: 'center'},
                {field: 'customerFromStr', title: '报备来源', width: 100, align: 'center'},

                {
                    title: '操作', fixed: 'right', align: 'center', width: 160,
                    templet: function (d) {
                        var ret = "<div style='text-align: left'>"
                        ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>查看</a>";

                        if (d.confirmStatus == 13601 && d.latestProgress == 13505) {
                            if (permission == "1") {
                                ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.backSale(\"" + d.reportId + "\",\"" + d.brokerageStatus + "\",\"" + d.rebackFlag + "\",\"" + d.estateId + "\")'>退房</a>";
                            }
                        }
                        ret += "</div>"
                        return ret;
                    }
                }
            ];
            var cols = [];
            cols.push(row);
            return cols;
        },

        active: {
            reload: function () {
                if (sale.valid()) {
                    var optionsData = {};
                    optionsData.projectDepartmentId = $("#sale-projectDepartmentId").val();
                    optionsData.reportId = trimStr($("#sale-reportId").val());
                    optionsData.customerFrom = $("#sale-customerFrom").val();
                    // optionsData.companyNm = trimStr($("#sale-companyNm").val());
                    // optionsData.customerNm = trimStr($("#sale-customerNm").val());
                    // optionsData.buildingNo = trimStr($("#sale-buildingNo").val());
                    optionsData.dateTypeKbn = '13704';
                    optionsData.latestProgress = '13505';
                    optionsData.dateStart = $("#sale-dateStart").val();
                    optionsData.dateEnd = $("#sale-dateEnd").val();
                    optionsData.keyTypeKbn = $("#sale-keyTypeKbn").val();
                    optionsData.keyword = trimStr($("#sale-keyword").val());

                    sessionStorage.removeItem('PMLS_SCENE_TRADE_REPORT_LIST_SALE_SEARCH');
                    sale.reloadData(optionsData);
                }
            },

            reset: function () {
                $("#sale-projectDepartmentId").val("");
                $("#sale-reportId").val("");
                $("#sale-customerFrom").val("");
                // $("#sale-companyNm").val("");
                // $("#sale-buildingNo").val("")
                // $("#sale-customerNm").val("");
                $("#sale-dateStart").val("");
                $("#sale-dateEnd").val("");
                $("#sale-keyTypeKbn").val("");
                $("#sale-keyword").val("");

                form.render('select');
                sale.active.reload();
            }
        },

        reloadData: function (optionsData) {
            //var index = layer.load(2);
            if (!optionsData) {
                optionsData = {};
                optionsData.dateTypeKbn = '13704';
                optionsData.latestProgress = '13505';
            }

            var sessionData = sessionStorage.getItem('PMLS_SCENE_TRADE_REPORT_LIST_SALE_SEARCH');
            var pageIndex = 1;
            if (sessionData != null && sessionData != '') {
                optionsData = JSON.parse(sessionData);
                pageIndex = optionsData.curr;
                $("#sale-projectDepartmentId").val(optionsData.projectDepartmentId);
                $("#sale-reportId").val(optionsData.reportId);
                $("#sale-customerFrom").val(optionsData.customerFrom );
                // $("#sale-companyNm").val(optionsData.companyNm);
                // $("#sale-buildingNo").val(optionsData.buildingNo)
                // $("#sale-customerNm").val(optionsData.customerNm);
                $("#sale-dateStart").val(optionsData.dateStart);
                $("#sale-dateEnd").val(optionsData.dateEnd);
                $("#sale-keyTypeKbn").val(optionsData.keyTypeKbn);
                $("#sale-keyword").val(optionsData.keyword);
                form.render('select');
            }

            table.reload('sale-contentReload', {
                url: BASE_PATH + '/sceneTrade/queryReportList',
                cols: sale.setCols(),
                height: window.innerHeight - $("#saleTabItem .layui-table-view").offset().top - 45,
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
                    sessionStorage.setItem("PMLS_SCENE_TRADE_REPORT_LIST_SALE_SEARCH", JSON.stringify(optionsData));
                }
            });
        },
        valid: function () {
            return true;
        }
    }

    // 结佣
    commission = {
        init: function () {

            laydate.render({
                elem: '#commission-dateStart',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    var startDate = new Date(value).getTime();
                    if ($('#commission-dateEnd').val() != '') {
                        var endTime = new Date($('#commission-dateEnd').val()).getTime();
                        if (endTime < startDate) {
                            layer.msg('开始时间不能大于结束时间');
                            $('#commission-dateStart').val('');
                        }
                    }
                }
            });

            laydate.render({
                elem: '#commission-dateEnd',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    if ($('#commission-dateStart').val() != "") {
                        var startDate = new Date($('#commission-dateStart').val()).getTime();
                        var endTime = new Date(value).getTime();
                        if (endTime < startDate) {
                            layer.msg('结束时间不能小于开始时间');
                            $('#commission-dateEnd').val('');
                        }
                    }
                }
            });

            // 表格数据
            this.tableRender();
        },

        tableRender: function () {
            table.render({
                elem: '#commission-contentTable'
                , cols: commission.setCols()
                //, url: BASE_PATH + '/sceneTrade/queryReportList'
                , id: 'commission-contentReload'
                , data: []
                , height: "full"
                , loading: true
                , page: true
                , limits: [10, 20, 30]
                , limit: 10 //默认采用60
                , method: 'post'
                , request: {
                    pageName: 'curPage' //页码的参数名称，默认：page
                    , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
                }
                , where: {
                    latestProgress: '13507'
                }
                , response: {
                    statusName: 'returnCode' //数据状态的字段名称，默认：code
                    , statusCode: 200 //成功的状态码，默认：0
                    , msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                    , countName: 'totalCount' //数据总数的字段名称，默认：count
                    , dataName: 'returnData' //数据列表的字段名称，默认：data
                }
            });
        },

        setCols: function () {
            var row = [
                {
                    field: 'reportId', fixed: true, title: '订单编号', width: 150, align: 'center', templet: function (d) {
                        return "<a class='layui-table-link' onclick='javascript:reportList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>" + d.reportId + "</a>";
                    }
                },
                {field: 'customerNm', fixed: true, title: '客户', width: 100, align: 'center'},
                {field: 'customerTel', title: '客户手机号', width: 120, align: 'center'},
                {
                    field: 'estateNm', title: '楼盘名称', width: 180, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.estateNm + "</div>";
                    }
                },

                {
                    field: 'companyNm', title: '经纪公司', width: 220, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.companyNm + "</div>";
                    }
                },
                {field: 'brokerageStatusNm', title: '结佣状态', width: 100, align: 'center'},
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
                {field: 'contactNm', title: '业绩归属人', width: 100, align: 'center'},
                {field: 'centerName', title: '业绩归属人中心', width: 135, align: 'center'},
                {field: 'projectDepartmentName', title: '归属项目部', width: 120, align: 'center'},
                {field: 'customerFromStr', title: '报备来源', width: 100, align: 'center'},
                {
                    title: '操作', fixed: 'right', align: 'center', width: 160,
                    templet: function (d) {
                        var ret = "<div style='text-align: left'>"
                        ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>查看</a>";

                        if (d.confirmStatus == 13601 && d.latestProgress == 13505) {
                            if (permission == "1") {
                                ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:reportList.backSale(\"" + d.reportId + "\",\"" + d.brokerageStatus + "\",\"" + d.rebackFlag + "\",\"" + d.estateId + "\")'>退房</a>";
                            }
                        }
                        ret += "</div>"
                        return ret;
                    }
                }
            ];
            var cols = [];
            cols.push(row);
            return cols;
        },

        active: {
            reload: function () {
                if (commission.valid()) {
                    var optionsData = {};
                    optionsData.projectDepartmentId = $("#commission-projectDepartmentId").val();
                    optionsData.reportId = trimStr($("#commission-reportId").val());
                    // optionsData.companyNm = trimStr($("#commission-companyNm").val());
                    optionsData.customerFrom = $("#commission-customerFrom").val();
                    optionsData.dateTypeKbn = '13707';
                    optionsData.latestProgress = '13507';
                    optionsData.dateStart = $("#commission-dateStart").val();
                    optionsData.dateEnd = $("#commission-dateEnd").val();
                    optionsData.keyTypeKbn = $("#commission-keyTypeKbn").val();
                    optionsData.keyword = trimStr($("#commission-keyword").val());

                    sessionStorage.removeItem('PMLS_SCENE_TRADE_REPORT_LIST_COMMISSION_SEARCH');
                    commission.reloadData(optionsData);
                }
            },

            reset: function () {
                $("#commission-projectDepartmentId").val("");
                $("#commission-reportId").val("");
                $("#commission-customerFrom").val("");
                // $("#commission-companyNm").val("");
                $("#commission-dateStart").val("");
                $("#commission-dateEnd").val("");
                $("#commission-keyTypeKbn").val("");
                $("#commission-keyword").val("");
                form.render('select');
                commission.active.reload();
            }
        },

        reloadData: function (optionsData) {
            //var index = layer.load(2);

            if (!optionsData) {
                optionsData = {};
                optionsData.dateTypeKbn = '13707';
                optionsData.latestProgress = '13507';
            }
            var sessionData = sessionStorage.getItem('PMLS_SCENE_TRADE_REPORT_LIST_COMMISSION_SEARCH');
            var pageIndex = 1;
            if (sessionData != null && sessionData != '') {
                optionsData = JSON.parse(sessionData);
                pageIndex = optionsData.curr;
                $("#commission-projectDepartmentId").val(optionsData.projectDepartmentId);
                $("#commission-reportId").val(optionsData.reportId);
                $("#commission-customerFrom").val(optionsData.customerFrom );
                // $("#commission-companyNm").val(optionsData.companyNm);
                $("#commission-dateStart").val(optionsData.dateStart);
                $("#commission-dateEnd").val(optionsData.dateEnd);
                $("#commission-keyTypeKbn").val(optionsData.keyTypeKbn);
                $("#commission-keyword").val(optionsData.keyword);
                form.render('select');
            }

            table.reload('commission-contentReload', {
                url: BASE_PATH + '/sceneTrade/queryReportList',
                cols: commission.setCols(),
                height: window.innerHeight - $("#commissionTabItem .layui-table-view").offset().top - 45,
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
                    sessionStorage.setItem("PMLS_SCENE_TRADE_REPORT_LIST_COMMISSION_SEARCH", JSON.stringify(optionsData));
                }
            });
        },
        valid: function () {
            return true;
        }
    }


    // 画面初始化
    initialization();

    function initialization() {

        var sessionData_all = sessionStorage.getItem('PMLS_SCENE_TRADE_REPORT_LIST_ALL_SEARCH');
        var sessionData_register = sessionStorage.getItem('PMLS_SCENE_TRADE_REPORT_LIST_REGISTER_SEARCH');
        var sessionData_watch = sessionStorage.getItem('PMLS_SCENE_TRADE_REPORT_LIST_WATCH_SEARCH');
        var sessionData_rought = sessionStorage.getItem('PMLS_SCENE_TRADE_REPORT_LIST_ROUGHT_SEARCH');
        var sessionData_sale = sessionStorage.getItem('PMLS_SCENE_TRADE_REPORT_LIST_SALE_SEARCH');
        var sessionData_commission = sessionStorage.getItem('PMLS_SCENE_TRADE_REPORT_LIST_COMMISSION_SEARCH');


        var projectDepartmentIds = [];
        projectDepartmentIds.push('all-projectDepartmentId');
        projectDepartmentIds.push('register-projectDepartmentId');
        projectDepartmentIds.push('watch-projectDepartmentId');
        projectDepartmentIds.push('rought-projectDepartmentId');
        projectDepartmentIds.push('sale-projectDepartmentId');
        projectDepartmentIds.push('commission-projectDepartmentId');

        // 楼盘归属项目部
        projectDepartmentLinkageIsService(function () {
            if (sessionData_all != null && sessionData_all != '') {
                var allOptionsData = JSON.parse(sessionData_all);
                $("#all-projectDepartmentId").val(allOptionsData.projectDepartmentId);
            }
            if (sessionData_register != null && sessionData_register != '') {
                var registerOptionsData = JSON.parse(sessionData_register);
                $("#register-projectDepartmentId").val(registerOptionsData.projectDepartmentId);
            }
            if (sessionData_watch != null && sessionData_watch != '') {
                var watchOptionsData = JSON.parse(sessionData_watch);
                $("#watch-projectDepartmentId").val(watchOptionsData.projectDepartmentId);
            }
            if (sessionData_rought != null && sessionData_rought != '') {
                var roughtOptionsData = JSON.parse(sessionData_rought);
                $("#rought-projectDepartmentId").val(roughtOptionsData.projectDepartmentId);
            }
            if (sessionData_sale != null && sessionData_sale != '') {
                var saleOptionsData = JSON.parse(sessionData_sale);
                $("#sale-projectDepartmentId").val(saleOptionsData.projectDepartmentId);
            }
            if (sessionData_commission != null && sessionData_commission != '') {
                var commissionOptionsData = JSON.parse(sessionData_commission);
                $("#commission-projectDepartmentId").val(commissionOptionsData.projectDepartmentId);
            }
            form.render('select')
        }, projectDepartmentIds);

        // var centerIds = [];
        // centerIds.push("register-centerId");
        // centerIds.push("watch-centerId");
        // // 业绩归属中心
        // centerGrpIdLinkageIsService(function () {
        //
        //     if (sessionData_register != null && sessionData_register != '') {
        //         var registerOptionsData = JSON.parse(sessionData_register);
        //         $("#register-centerId").val(registerOptionsData.centerId);
        //     }
        //     if (sessionData_watch != null && sessionData_watch != '') {
        //         var watchOptionsData = JSON.parse(sessionData_watch);
        //         $("#watch-centerId").val(watchOptionsData.centerId);
        //     }
        //     form.render('select')
        // }, centerIds);


        all.init();               // 全部
        register.init();          // 报备
        watch.init();             // 带看
        rought.init();            // 大定
        sale.init();              // 成销
        commission.init()         // 结佣

        form.render('select');   // 刷新单选框渲染

        act = {
            all: function () {
                //all.active.reset();
                //all.active.reload();
                all.reloadData();
            },
            register: function () {
                //register.active.reset();
                //register.active.reload();
                register.reloadData();
            },
            watch: function () {
                //watch.active.reset();
                //watch.active.reload()
                watch.reloadData();
            },
            rought: function () {
                //rought.active.reset();
                //rought.active.reload();
                rought.reloadData();
            },
            sale: function () {
                //sale.active.reset();
                // sale.active.reload();
                sale.reloadData();
            },
            commission: function () {
                //commission.active.reset();
                //commission.active.reload();
                commission.reloadData();
            },
        }


        // tab切换
        element.on("tab(reportTab)", function (data) {
            var type = "all";
            if (data.index == 0) {
                type = "all";
            }
            else if (data.index == 1) {
                type = "register";
            }
            else if (data.index == 2) {
                type = "watch";
            }
            else if (data.index == 3) {
                type = "rought";
            }
            else if (data.index == 4) {
                type = "sale";
            }
            else if (data.index == 5) {
                type = "commission";
            }

            act[type] ? act[type].call(this) : '';
            var url = currentUrl + "?backTab=" + type;
            parent.breadcrumbArray[parent.breadcrumbArray.length - 1].url = url;

        });

        // 全部
        $('#allTabItem .toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            all.active[type] ? all.active[type].call(this) : '';
        });

        // 报备
        $('#registerTabItem .toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            register.active[type] ? register.active[type].call(this) : '';
        });

        // 带看
        $('#watchTabItem .toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            watch.active[type] ? watch.active[type].call(this) : '';
        });

        // 大定
        $('#roughtTabItem .toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            rought.active[type] ? rought.active[type].call(this) : '';
        });

        // 成销
        $('#saleTabItem .toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            sale.active[type] ? sale.active[type].call(this) : '';
        });

        // 结佣
        $('#commissionTabItem .toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            commission.active[type] ? commission.active[type].call(this) : '';
        });


        var tab = isEmpty(backTab) ? defTab : backTab;
        //$(".layui-tab-title li[act-type='" + tab + "']").click();
        element.tabChange('reportTab', tab);


    }

});

var reportList = {};

// 查看
reportList.showDetail = function (estateId, companyId, customerId, id) {
    layer.load(2);
    var url = ctx + "/sceneTrade/reportDetail/" + estateId + "/" + companyId + "/" + customerId + "/" + id;
    parent.redirectTo('append', url, '订单详情');
}
// 带看
reportList.watchAdd = function (reportId) {
    var url = BASE_PATH + "/sceneTrade/watchAdd/" + reportId;
    parent.redirectTo('append', url, '新增带看');
}
// 无效
reportList.toInvalid = function (reportId, estateId) {

    parent.msgConfirm("确认要无效吗？", function () {

        var url = BASE_PATH + "/sceneTrade/toInvalid";

        var params = {
            estateId: estateId,
            reportId: reportId
        };
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        ajaxGet(url, params, function (data) {
            if (data.returnData == 0) {
                parent.layer.close(loadIndex);
                parent.msgError(data.returnMsg);
            } else {
                parent.layer.close(loadIndex);
                var action = {
                    all: function () {
                        all.active.reload();
                    }
                    , register: function () {
                        register.active.reload();
                    }
                    , watch: function () {
                        watch.active.reload();
                    }
                    , rought: function () {
                        rought.active.reload();
                    }
                    , sale: function () {
                        sale.active.reload();
                    }
                    , commission: function () {
                        commission.active.reload();
                    }
                }
                var type = $(".layui-tab-title li[class='layui-this']").attr("act-type");
                action[type] ? action[type].call(this) : '';
                parent.msgProp("操作成功！");

            }
        });
    });


}
// 大定
reportList.roughtAdd = function (reportId) {
    console.log("按订单 大定"+reportId);
    var flag = checkPreBack(reportId);
    if(!flag){
        parent.msgError("该报备订单已预退，不能继续大定、成销!");
        return false;
    }
    var url = BASE_PATH + "/sceneTrade/roughtAdd/" + reportId;
    parent.redirectTo('append', url, '新增大定');
}
// 大定审核
reportList.roughtAudit = function (reportId) {
    var url = BASE_PATH + "/sceneTrade/roughtAudit/" + reportId;
    parent.redirectTo('append', url, '大定审核');
}
// 成销
reportList.succSaleAdd = function (reportId) {
    console.log("按订单 成销"+reportId);
    var flag = checkPreBack(reportId);
    if(!flag){
        parent.msgError("该报备订单已预退，不能继续大定、成销!");
        return false;
    }
    var url = BASE_PATH + "/sceneTrade/succSaleAdd/" + reportId;
    parent.redirectTo('append', url, '成销');
}
// 大定修改
reportList.roughtUpt = function (reportId) {
    var url = BASE_PATH + "/sceneTrade/roughtUpt/" + reportId;
    parent.redirectTo('append', url, '大定修改');
}
// 退定
reportList.backRought = function (reportId) {
    var url = BASE_PATH + "/sceneTrade/backRought/" + reportId;
    parent.redirectTo('append', url, '退定');
}
// 退房
reportList.backSale = function (reportId, brokerageStatus, rebackFlag, estateId) {

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
