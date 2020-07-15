var active;
var layer;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        formSelects = layui.formSelects,
        $ = layui.$;
    layer = layui.layer;

    init();

    function init() {

        var switchDate = $("#countDateEndStr").val();
        if (isEmpty(switchDate)) {
            switchDate = "1900-1-1";
        }


        laydate.render({
            elem: '#countDateStart',
            type: 'date',
            format: 'yyyy-MM-dd',
            max: switchDate,
            trigger: 'click',
            done: function (value, date, endDate) {
                var startDate = new Date(value).getTime();
                if ($('#countDateEnd').val() != '') {
                    var endTime = new Date($('#countDateEnd').val()).getTime();
                    if (endTime < startDate) {
                        layer.msg('开始时间不能大于结束时间');
                        $('#countDateStart').val('');
                    }
                }
            }
        });

        laydate.render({
            elem: '#countDateEnd',
            type: 'date',
            format: 'yyyy-MM-dd',
            max: switchDate,
            trigger: 'click',
            done: function (value, date, endDate) {
                if ($('#countDateStart').val() != "") {
                    var startDate = new Date($('#countDateStart').val()).getTime();
                    var endTime = new Date(value).getTime();
                    if (endTime < startDate) {
                        layer.msg('结束时间不能小于开始时间');
                        $('#countDateEnd').val('');
                    }
                }
            }
        });


        form.render('select'); //刷新select选择框渲染

        tableRender();

    }

    function tableRender() {
        table.render({
            elem: '#mainTable'
            , cols: setCols()
            , id: 'contentReload'
            , data: []
            , page: true
            , height: "full"
            , limits: [10, 20, 30]
            , limit: 10 //默认采用60
            , method: 'post'
            , loading: false
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
    }

    function setCols() {
        var row = [];
        row.push(
            {type: 'checkbox', fixed: true},
            {field: 'ReportId', fixed: true, title: '订单编号', width: 150, align: 'center'},
            {
                field: 'EstateNm', fixed: true, title: '楼盘名称', width: 150, align: 'center'
                , templet: function (d) {
                    return "<div style='text-align: left'>" + d.EstateNm + "</div>";
                }
            },
            {
                field: 'CompanyNm', title: '经纪公司', width: 250, align: 'center'
                , templet: function (d) {
                    return "<div style='text-align: left'>" + d.CompanyNm + "</div>";
                }
            },
            {
                field: 'AddressDetail', title: '门店地址', width: 250, align: 'center', templet: function (d) {
                    var addressDetail = d.AddressDetail
                    if (isEmpty(addressDetail)) {
                        return "-"
                    }
                    return "<div style='text-align: left'>" + addressDetail + "</div>";
                }
            },
            {field: 'CustomerNm', title: '客户', width: 150, align: 'center'},
            {field: 'CustomerTel', title: '客户手机号', width: 150, align: 'center'},
            {field: 'dicValue', title: '最新进度', width: 100, align: 'center'},
            {
                field: 'CompanyName', title: '返佣对象', width: 250, align: 'center'
                , templet: function (d) {
                    return "<div style='text-align: left'>" + d.CompanyName + "</div>";
                }
            },
            {
                title: '操作', fixed: 'right', align: 'center', width: 110, templet: function (d) {
                    var ret = "<a class='layui-btn layui-btn-xs' onclick='javascript:lnkYjReportListPmls.showDetail(\"" + d.ReportId + "\")'>查看</a>";
                    if (d.brokerageStatus == 0) {
                        ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:lnkYjReportListPmls.edit(\"" + d.ReportId + "\")'>编辑</a>";
                    }
                    return ret;
                }
            }
        );

        var cols = [];
        cols.push(row);
        return cols;
    }

    reloadData();//初始化加载表格

    active = {
        reload: function () {
            if (valid()) {
                var optionsData = {};
                optionsData.projectDepartmentId = $("#projectDepartmentId").val();
                optionsData.estateNmKey = trimStr($("#estateNmKey").val());
                optionsData.businessType = $("#businessType").val();
                optionsData.countDateStart = $("#countDateStart").val();
                optionsData.countDateEnd = $("#countDateEnd").val();

                optionsData.reportId = trimStr($("#reportId").val());
                optionsData.searchKey = trimStr($("#searchKey").val());

                optionsData.objNum = 0;
                if ($("[name = objNum]:checkbox").is(":checked")) {
                    optionsData.objNum = 1;
                }
                sessionStorage.removeItem('PMLS_LNK_YJ_REPORT_SEARCH');
                reloadData(optionsData);
            }
        },
        reset: function () {
            $("#projectDepartmentId").val("");
            $("#estateNmKey").val("");
            $("#businessType").val("");
            $("#countDateStart").val("");
            $("#countDateEnd").val("");
            $("#reportId").val("");
            $("#searchKey").val("");
            $("#objNum").prop("checked", false);
            form.render('select');
            form.render('checkbox')
            active.reload();
        },
        achieveMentChange: function () {
            var checkStatus = table.checkStatus("contentReload"); //获取选中行状态

            var dataList = checkStatus.data; //获取选中行的数据
            var dataLen = checkStatus.data.length; //获取选中行数量
            if (dataLen <= 0) {
                parent.msgAlert("请至少选择一个需要返佣维护的记录！")
                return;
            }

            var flag = false;
            var projectNo = "";
            var ids = "";
            var reprotinfoStr = "";

            for (i = 0; i < dataLen; i++) {
                var data = dataList[i];
                if (i != 0 && projectNo != data.projectNo) {
                    parent.msgAlert("选择的房源数据不为同一项目，请重新选择！")
                    flag = true;
                    return;
                }
                projectNo = data.projectNo;
                ids += data.id + ',';
                reprotinfoStr += data.id + "#" + data.ReportId + ",";
            }
            if (flag == true) {
                return;
            }

            if (reprotinfoStr != "") {
                reprotinfoStr = reprotinfoStr.substr(0, reprotinfoStr.length - 1);
            }

            maintenanceToDialog(projectNo, reprotinfoStr);
        }

    };

    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
        var index = parent.layer.load(2);

        var sessionData = sessionStorage.getItem('PMLS_LNK_YJ_REPORT_SEARCH');
        var pageIndex = 1;
        if (sessionData != null && sessionData != '') {
            optionsData = JSON.parse(sessionData);
            pageIndex = optionsData.curr;

            $("#projectDepartmentId").val(optionsData.projectDepartmentId);
            $("#estateNmKey").val(optionsData.estateNmKey);
            $("#businessType").val(optionsData.businessType);
            $("#countDateStart").val(optionsData.countDateStart);
            $("#countDateEnd").val(optionsData.countDateEnd);
            $("#reportId").val(optionsData.reportId);
            $("#searchKey").val(optionsData.searchKey)
            if (optionsData.objNum == 1) {
                $("#objNum").prop("checked", true);
            }
            form.render('select');
            form.render('checkbox')
        }


        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsLnkYjReport/getLnkYjReprotList',
            cols: setCols(),
            where: optionsData,
            height: window.innerHeight - $(".layui-table-view").offset().top - 20,
            page: {
                curr: pageIndex
            },
            done: function (res, curr, count) {
                parent.layer.close(index);
                if (!optionsData) {
                    optionsData = {};
                }
                optionsData.curr = curr;
                sessionStorage.setItem("PMLS_LNK_YJ_REPORT_SEARCH", JSON.stringify(optionsData));

            }
        });
    }

    function valid() {
        return true;
    }

    function maintenanceToDialog(projectNo, reprotinfoStr) {
        var url = BASE_PATH + '/pmlsLnkYjReport/maintenance?projectNo=' + escape(projectNo) + "&reprotinfoStr=" + escape(reprotinfoStr);
        parent.layer.open({
            type: 2
            , title: '维护返佣对象'
            , area: ['750px', '321px']
            , content: url
            , scrollbar: false
            , resize: false
            , shadeClose: false
            , btn: ['确定', '取消']
            , yes: function (index, layero) {
                //确认的回调
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                var formData = iframeWin.getFormData();
                if (formData != null) {

                    if (vaidSaveMaintenance(formData) == false) {
                        return;
                    }
                    saveMaintenance(formData, index);

                }
            }
        });
    }

    function vaidSaveMaintenance(data) {

        var inputCompanyNo = data.inputCompanyNo;
        var inputCompanyName = data.inputCompanyName;
        if (isEmpty(inputCompanyName) || isEmpty(inputCompanyNo)) {
            parent.layer.alert("返佣对象二必填!");
            return false;
        }
        if (isEmpty(inputCompanyNo) && !isEmpty(inputCompanyName)) {
            parent.msgAlert("经纪公司（返佣对象二）录入有误，请选择对应的经纪公司！");
            return false;
        }

        var inputCompanyNoTwo = data.inputCompanyNoTwo;
        var inputCompanyNameTwo = data.inputCompanyNameTwo;

        if (isEmpty(inputCompanyNoTwo) && !isEmpty(inputCompanyNameTwo)) {
            parent.msgAlert("经纪公司（返佣对象三）录入有误，请选择对应的经纪公司！");
            return false;
        }
        if (!isEmpty(inputCompanyNoTwo) && inputCompanyNoTwo == inputCompanyNo) {
            parent.msgAlert("返佣对象二与返佣对象三不能一样！");
            return false;
        }

    }

    function saveMaintenance(data, index) {

        var url = BASE_PATH + '/pmlsLnkYjReport/saveMaintenance';
        var parms = {
            ids: data.ids,
            reportIds: data.reportIds,
            yjReportFirst: data.inputCompanyName,
            yjReportSecond: data.inputCompanyNameTwo,
            companyNo: data.inputCompanyNo,
            companyNoTwo: data.inputCompanyNoTwo
        }
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        restPost(url, parms, function (data) {
            parent.layer.close(loadIndex);
            parent.msgProp(data.returnMsg);
            parent.layer.close(index);
            active.reload();

        }, function (data) {
            parent.layer.close(loadIndex);
            parent.msgAlert(data.returnMsg);
        })

    }

});


lnkYjReportListPmls = {}
lnkYjReportListPmls.showDetail = function (reportId) {
    var url = BASE_PATH + "/pmlsLnkYjReport/getYjReportDetaileById/" + reportId;
    parent.redirectTo('append', url, '返佣对象记录查看');
}
lnkYjReportListPmls.edit = function (reportId) {
    var url = BASE_PATH + "/pmlsLnkYjReport/u/" + reportId;

    parent.layer.open({
        type: 2
        , title: '维护返佣对象'
        , area: ['750px', '440px']
        , content: url
        , scrollbar: false
        , resize: false
        , shadeClose: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();
            if (formData != null) {

                if (lnkYjReportListPmls.vaidUpdateMaintenance(formData) == false) {
                    return;
                }
                lnkYjReportListPmls.updateMaintenance(formData, index);

            }
        }
    });

}

lnkYjReportListPmls.vaidUpdateMaintenance = function (data) {

    var inputCompanyNo1 = data.inputCompanyNo1;
    var inputCompanyName1 = data.inputCompanyName1;
    var inputCompanyNo2 = data.inputCompanyNo2;
    var inputCompanyName2 = data.inputCompanyName2;
    var inputCompanyNo3 = data.inputCompanyNo3;
    var inputCompanyName3 = data.inputCompanyName3;

    if (isEmpty(inputCompanyNo2) && !isEmpty(inputCompanyName2)) {
        parent.msgAlert("经纪公司(返佣对象二)录入有误，请选择对应的经纪公司！");
        return false;
    }
    if (isEmpty(inputCompanyNo3) && !isEmpty(inputCompanyName3)) {
        parent.msgAlert("经纪公司(返佣对象三)录入有误，请选择对应的经纪公司！");
        return false;
    }
    if (!isEmpty(inputCompanyNo2) && inputCompanyNo2 == inputCompanyNo1) {
        parent.msgAlert("返佣对象一与返佣对象二不能一样！");
        return false;
    }
    if (!isEmpty(inputCompanyNo3)) {
        if (isEmpty(inputCompanyNo2)) {
            parent.msgAlert("请选择返佣对象二");
            return false;
        }

        if (inputCompanyNo3 == inputCompanyNo1) {
            parent.msgAlert("返佣对象一与返佣对象三不能一样！");
            return false;
        }
        if (inputCompanyNo3 == inputCompanyNo2) {
            parent.msgAlert("返佣对象二与返佣对象三不能一样！");
            return false;
        }
    }

}
lnkYjReportListPmls.updateMaintenance = function (data, index) {
    var url = BASE_PATH + '/pmlsLnkYjReport/updateMaintenance';

    var parms = {
        projectNo: data.projectNo
        , reportId: data.reportId
        , companyCode1: data.inputCompanyNo1
        , companyName1: data.inputCompanyName1
        , companyCode2: data.inputCompanyNo2
        , inputCompanyName: data.inputCompanyName2
        , companyCode3: data.inputCompanyNo3
        , inputCompanyNameTwo: data.inputCompanyName3
    }
    var loadIndex = parent.layer.load(2, {shade: [0.1]});
    restPost(url, parms, function (data) {
        parent.layer.close(loadIndex);
        parent.msgProp(data.returnMsg);
        parent.layer.close(index);
        active.reload();

    }, function (data) {
        parent.layer.close(loadIndex);
        parent.msgAlert(data.returnMsg);
    })


}