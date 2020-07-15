layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        $ = layui.$;

    var currentUrl = BASE_PATH + '/sceneTrade/rought';
    var defTab = "checking";
    var backTab = $("#backTab").val();

    // 大定待审核
    var checking = {
        init: function () {
            laydate.render({
                elem: '#checking-dateStart',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    var startDate = new Date(value).getTime();
                    if ($('#checking-dateEnd').val() != '') {
                        var endTime = new Date($('#checking-dateEnd').val()).getTime();
                        if (endTime < startDate) {
                            layer.msg('开始时间不能大于结束时间');
                            $('#checking-dateStart').val('');
                        }
                    }
                }
            });

            laydate.render({
                elem: '#checking-dateEnd',
                type: 'date',
                format: 'yyyy-MM-dd',
                trigger: 'click',
                done: function (value, date, endDate) {
                    if ($('#checking-dateStart').val() != "") {
                        var startDate = new Date($('#checking-dateStart').val()).getTime();
                        var endTime = new Date(value).getTime();
                        if (endTime < startDate) {
                            layer.msg('结束时间不能小于开始时间');
                            $('#checking-dateEnd').val('');
                        }
                    }
                }
            });

            // 表格数据
            this.tableRender();
        },
        tableRender: function () {
            table.render({
                elem: '#checking-contentTable'
                , cols: checking.setCols()
                //, url: BASE_PATH + '/sceneTrade/queryReportList'
                , data: []
                , id: 'checking-contentReload'
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
                    latestProgress: '13504',
                    roughAuditStatus: '0'
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
                        return "<a class='layui-table-link' onclick='javascript:roughtList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>" + d.reportId + "</a>";
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
                    field: 'roughInputDate', title: '大定日期', width: 120, align: 'center',
                    templet: function (d) {
                        if (d.roughInputDate == null) {
                            return "-";
                        } else {
                            return formatDate(d.roughInputDate, "yyyy-MM-dd");
                        }
                    }
                },
                {field: 'projectDepartmentName', title: '归属项目部', width: 120, align: 'center'},
                {
                    title: '操作', fixed: 'right', align: 'center', width: 100,
                    templet: function (d) {
                        return "<a class='layui-btn layui-btn-xs' onclick='javascript:roughtList.roughtAudit(\"" + d.reportId + "\")'>大定审核</a>";
                    }
                }
            ];
            var cols = [];
            cols.push(row);
            return cols;
        },
        active: {
            reload: function () {
                if (checking.valid()) {
                    var optionsData = {};
                    optionsData.projectDepartmentId = $("#checking-projectDepartmentId").val();
                    optionsData.reportId = trimStr($("#checking-reportId").val());
                    optionsData.customerFrom = $("#checking-customerFrom").val();
                    // optionsData.companyNm = trimStr($("#checking-companyNm").val());
                    // optionsData.buildingNo = trimStr($("#checking-buildingNo").val());
                    // optionsData.customerNm = trimStr($("#checking-customerNm").val());
                    optionsData.dateTypeKbn = '13703';
                    optionsData.latestProgress = '13504';
                    optionsData.roughAuditStatus = '0'
                    optionsData.dateStart = $("#checking-dateStart").val();
                    optionsData.dateEnd = $("#checking-dateEnd").val();
                    optionsData.keyTypeKbn = $("#checking-keyTypeKbn").val();
                    optionsData.keyword = $("#checking-keyword").val();

                    sessionStorage.removeItem('PMLS_SCENE_TRADE_ROUGHT_LIST_CHECKING_SEARCH');
                    checking.reloadData(optionsData);
                }
            }
            , reset: function () {
                $("#checking-projectDepartmentId").val("");
                $("#checking-reportId").val("");
                $("#checking-customerFrom").val("");
                // $("#checking-companyNm").val("");
                // $("#checking-buildingNo").val("");
                // $("#checking-customerNm").val("");
                $("#checking-dateStart").val("");
                $("#checking-dateEnd").val("");
                $("#checking-keyTypeKbn").val("");
                $("#checking-keyword").val("");
                form.render('select');
                checking.active.reload();
            }
        },
        reloadData: function (optionsData) {
            //var index = layer.load(2);

            if (!optionsData) {
                var optionsData = {};
                optionsData.dateTypeKbn = '13703';
                optionsData.latestProgress = '13504';
                optionsData.roughAuditStatus = '0'
            }

            var sessionData = sessionStorage.getItem('PMLS_SCENE_TRADE_ROUGHT_LIST_CHECKING_SEARCH');
            var pageIndex = 1;
            if (sessionData != null && sessionData != '') {
                optionsData = JSON.parse(sessionData);
                pageIndex = optionsData.curr;
                $("#checking-projectDepartmentId").val(optionsData.projectDepartmentId);
                $("#checking-reportId").val(optionsData.reportId);
                $("#checking-customerFrom").val(optionsData.customerFrom);
                // $("#checking-companyNm").val(optionsData.companyNm);
                // $("#checking-buildingNo").val(optionsData.buildingNo);
                // $("#checking-customerNm").val(optionsData.customerNm);
                $("#checking-dateStart").val(optionsData.dateStart);
                $("#checking-dateEnd").val(optionsData.dateEnd);
                $("#checking-keyTypeKbn").val(optionsData.keyTypeKbn);
                $("#checking-keyword").val(optionsData.keyword);

                form.render('select');
            }


            table.reload('checking-contentReload', {
                url: BASE_PATH + '/sceneTrade/queryReportList',
                cols: checking.setCols(),
                height: window.innerHeight - $("#checkingTabItem .layui-table-view").offset().top - 45,
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
                    sessionStorage.setItem("PMLS_SCENE_TRADE_ROUGHT_LIST_CHECKING_SEARCH", JSON.stringify(optionsData));
                }
            });
        },
        valid: function () {

            return true;
        }
    }

    // 大定已审核
    var audited = {
        init: function () {

            // 表格数据
            this.tableRender();
        },
        tableRender: function () {
            table.render({
                elem: '#audited-contentTable'
                , cols: audited.setCols()
                //, url: BASE_PATH + '/sceneTrade/queryReportList'
                , id: 'audited-contentReload'
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
                    latestProgress: '13504',
                    roughAuditStatus: '1,2'
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
                        return "<a class='layui-table-link' onclick='javascript:roughtList.showDetail(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.customerId + "\",\"" + d.id + "\")'>" + d.reportId + "</a>";
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
                    field: 'roughAuditStatus', title: '审核状态', width: 120, align: 'center',
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
                    field: 'companyNm', title: '经纪公司', width: 220, align: 'center'
                    , templet: function (d) {
                        return "<div style='text-align: left'>" + d.companyNm + "</div>";
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
                {field: 'projectDepartmentName', title: '归属项目部', width: 120, align: 'center'},
                {
                    title: '操作', fixed: 'right', align: 'center', width: 100,
                    templet: function (d) {
                        var ret = "<a class='layui-btn layui-btn-xs' onclick='javascript:roughtList.roughtAudit(\"" + d.reportId + "\")'>查看</a>";
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
                if (audited.valid()) {
                    var optionsData = {};
                    optionsData.projectDepartmentId = $("#audited-projectDepartmentId").val();
                    optionsData.reportId = trimStr($("#audited-reportId").val());
                    optionsData.customerFrom = $("#audited-customerFrom").val();
                    // optionsData.companyNm = trimStr($("#audited-companyNm").val());
                    // optionsData.buildingNo = trimStr($("#audited-buildingNo").val());
                    // optionsData.customerNm = trimStr($("#audited-customerNm").val());
                    optionsData.dateTypeKbn = '13703';
                    optionsData.latestProgress = '13504';
                    var roughAuditStatus = $("#audited-roughAuditStatus").val()
                    if (isEmpty(roughAuditStatus)) {
                        optionsData.roughAuditStatus = '1,2'
                    } else {
                        optionsData.roughAuditStatus = roughAuditStatus;
                    }
                    optionsData.keyTypeKbn = $("#audited-keyTypeKbn").val();
                    optionsData.keyword = trimStr($("#audited-keyword").val());

                    sessionStorage.removeItem('PMLS_SCENE_TRADE_ROUGHT_LIST_AUDITED_SEARCH');
                    audited.reloadData(optionsData);
                }
            }
            , reset: function () {

                $("#audited-projectDepartmentId").val("");
                $("#audited-reportId").val("");
                $("#audited-customerFrom").val("");
                // $("#audited-companyNm").val("");
                // $("#audited-buildingNo").val("");
                // $("#audited-customerNm").val("");
                $("#audited-roughAuditStatus").val("");
                $("#audited-keyTypeKbn").val("");
                $("#audited-keyword").val("");

                form.render('select');
                audited.active.reload();
            }
        },
        reloadData: function (optionsData) {
            //var index = layer.load(2);
            if (!optionsData) {
                optionsData = {};
                optionsData.dateTypeKbn = '13703';
                optionsData.latestProgress = '13504';
            }

            var sessionData = sessionStorage.getItem('PMLS_SCENE_TRADE_ROUGHT_LIST_AUDITED_SEARCH');
            var pageIndex = 1;
            if (sessionData != null && sessionData != '') {
                optionsData = JSON.parse(sessionData);
                pageIndex = optionsData.curr;
                $("#audited-projectDepartmentId").val(optionsData.projectDepartmentId);
                $("#audited-reportId").val(optionsData.reportId);
                $("#audited-customerFrom").val(optionsData.customerFrom);
                // $("#audited-companyNm").val(optionsData.companyNm);
                // $("#audited-buildingNo").val(optionsData.buildingNo);
                // $("#audited-customerNm").val(optionsData.customerNm);
                if (optionsData.roughAuditStatus == '1,2') {
                    $("#audited-roughAuditStatus").val("");
                } else {
                    $("#audited-roughAuditStatus").val(optionsData.roughAuditStatus);
                }
                $("#audited-keyTypeKbn").val(optionsData.keyTypeKbn);
                $("#audited-keyword").val(optionsData.keyword);
                form.render('select');
            }

            table.reload('audited-contentReload', {
                url: BASE_PATH + '/sceneTrade/queryReportList',
                cols: audited.setCols(),
                height: window.innerHeight - $("#auditedTabItem .layui-table-view").offset().top - 45,
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
                    sessionStorage.setItem("PMLS_SCENE_TRADE_ROUGHT_LIST_AUDITED_SEARCH", JSON.stringify(optionsData));
                }
            });
        }

        ,
        valid: function () {
            return true;
        }
    }

    // 画面初始化
    initialization();

    function initialization() {

        var sessionData_checking = sessionStorage.getItem('PMLS_SCENE_TRADE_ROUGHT_LIST_CHECKING_SEARCH');
        var sessionData_aduited = sessionStorage.getItem('PMLS_SCENE_TRADE_ROUGHT_LIST_AUDITED_SEARCH');

        var projectDepartmentIds = [];
        projectDepartmentIds.push('checking-projectDepartmentId');
        projectDepartmentIds.push('audited-projectDepartmentId');

        // 楼盘归属项目部
        projectDepartmentLinkageIsService(function () {

            if (sessionData_checking != null && sessionData_checking != '') {
                var checkingOptionsData = JSON.parse(sessionData_checking);
                $("#checking-projectDepartmentId").val(checkingOptionsData.projectDepartmentId);
            }
            if (sessionData_aduited != null && sessionData_aduited != '') {
                var auditedOptionsData = JSON.parse(sessionData_aduited);
                $("#audited-projectDepartmentId").val(auditedOptionsData.projectDepartmentId);
            }
            form.render('select')
        }, projectDepartmentIds);


        checking.init();         // 大定待审核
        audited.init();          // 大定已审核

        form.render('select');   // 刷新单选框渲染

        act = {
            checking: function () {
                //checking.active.reset();
                //checking.active.reload();
                checking.reloadData()
            },
            audited: function () {
                //audited.active.reset();
                //audited.active.reload();
                audited.reloadData()
            }
        }
        // tab切换
        // $('.layui-tab-title li').on('click', function () {
        //     var type = $(this).attr('act-type');
        //     act[type] ? act[type].call(this) : '';
        //     var url = currentUrl + "?backTab=" + type;
        //     parent.breadcrumbArray[parent.breadcrumbArray.length - 1].url = url;
        // });

        element.on("tab(roughtTab)", function (data) {
            var type = "checking";
            if (data.index == 0) {
                type = "checking";
            }
            else if (data.index == 1) {
                type = "audited";
            }

            act[type] ? act[type].call(this) : '';
            var url = currentUrl + "?backTab=" + type;
            parent.breadcrumbArray[parent.breadcrumbArray.length - 1].url = url;

        });

        // 大定待审核
        $('#checkingTabItem .toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            checking.active[type] ? checking.active[type].call(this) : '';
        });

        // 大定已审核
        $('#auditedTabItem .toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            audited.active[type] ? audited.active[type].call(this) : '';
        });


        var tab = isEmpty(backTab) ? defTab : backTab;
        //$(".layui-tab-title li[act-type='" + tab + "']").click();
        element.tabChange('roughtTab', tab);
    }
});

var roughtList = {};

// 订单详情
roughtList.showDetail = function (estateId, companyId, customerId, id) {
    var url = ctx + "/sceneTrade/reportDetail/" + estateId + "/" + companyId + "/" + customerId + "/" + id;
    parent.redirectTo('append', url, '订单详情');
}

// 大定审核
roughtList.roughtAudit = function (reportId) {
    var url = BASE_PATH + "/sceneTrade/roughtAudit/" + reportId;
    parent.redirectTo('append', url, '大定审核详情');
}
