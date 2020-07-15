var active;
var layer;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        $ = layui.$;
        layer = layui.layer;
    if ($("#importType").val() == 'yj'){
        $("#btnImport").hide();
    }else{
        $("#btnImport").show();
    }
    initOrgList();
    laydate.render({
        elem: '#date',
        type: 'month',
        value: new Date(),
        format: 'yyyy-MM',
        trigger: 'click'
    });

    if (sessionStorage.getItem("kfdr_date")) {
        $("#date").val(sessionStorage.getItem("kfdr_date"));
    }

    form.render('select'); //刷新select选择框渲染
    function tableRender() {
        table.render({
            elem: '#contentTable'
            , cols: setCols()
            , id: 'contentReload'
            // , height: 'full-200'
            , data: []
            , even: false //开启隔行背景
            , page: true
            , limits: [10, 20, 30]
            , limit: 10 //默认采用60
            , loading: false
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
    }
    function setCols() {
        var row = [
            {type: 'numbers', title: '序号', width: 60},
            {field: 'accountProjectName', title: '核算主体', width: 150, align: 'center'},
            {field: 'accountProjectCode', title: '核算主体编码', width: 150, align: 'center'},
            {field: 'checkBodyName', title: '考核主体', width: 150, align: 'center'},
            {field: 'checkBodyCode', title: '考核主体编码', width: 150, align: 'center'},
            {field: 'costName', title: '成本中心', width: 150, align: 'center'},
            {field: 'costCode', title: '成本中心编码', width: 150, align: 'center'},
            {field: 'projectName', title: '项目', width: 130, align: 'center'},
            {field: 'projectNo', title: '项目编码', width: 100, align: 'center'}
        ];
        if ($("#importType").val() == 'yj') {
            row.push(
                {field: 'serviceType', title: '业务类型', width: 100, align: 'center'},
                {
                    field: 'jobBonus', title: '岗位佣金', width: 100, align: 'center',style:"text-align:right", templet: function (d) {
                        if (!d.jobBonus) {
                            return '-';
                        }
                        return d.jobBonus.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }
                },
                {
                    field: 'teamBonus', title: '团奖', width: 80, align: 'center',style:"text-align:right", templet: function (d) {
                        if (!d.teamBonus) {
                            return '-';
                        }
                        return d.teamBonus.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }
                },
                {field: 'countDate', title: '计提时间', width: 120, align: 'center'}
            );
        } else {
            row.push(
                {field: 'userName', title: '姓名', width: 80, align: 'center'},
                {field: 'userCode', title: '工号', width: 80, align: 'center'},
                {field: 'serviceType', title: '业务类型', width: 100, align: 'center'},
                {
                    field: 'jobBonus', title: '岗位佣金', width: 115, align: 'center',style:"text-align:right", templet: function (d) {
                        if (!d.jobBonus) {
                            return '-';
                        }
                        return d.jobBonus.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }
                },
                {
                    field: 'teamBonus', title: '团奖', width: 115, align: 'center',style:"text-align:right", templet: function (d) {
                        if (!d.teamBonus) {
                            return '-';
                        }
                        return d.teamBonus.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
                    }
                },
                {field: 'canSendDate', title: '可发日期', width: 120, align: 'center'}
            );
        }

        var cols = [];
        cols.push(row);
        return cols;
    }

    active = {
        reload: function () {
            if (valid()) {
                var optionsData = {};
                optionsData.importType = $("#importType").val();
                optionsData.cityNo = $("#cityNo").val();
                optionsData.dateMonth = $("#date").val();
                optionsData.businessTypeCode = $("#businessType").val();

                //将搜索条件缓存下来
                sessionStorage.setItem("kfdr_cityNo", $("#cityNo").val());
                sessionStorage.setItem("kfdr_date", $("#date").val());
                sessionStorage.setItem("kfdr_businessType", $("#businessType").val());

                reloadData(optionsData);
            }
        },
        reset: function () {
            console.info("all.reset");

            $("#org").val("");
            $("#cityNo").val("");
            $("#date").val("");

            form.render('select');

            table.render({
                elem: '#contentTable'
                , cols: []
                , id: 'contentReload'
            });

        },

        export: function () {
            var cookieName = guid();
            showExprotLoading(cookieName);
            window.location.href = BASE_PATH + '/pmlsKfCommission/exportTemplate?cookieName=' + cookieName + '&importType=' + $('#importType').val();
        },
        import: function () {
            if (valid()) {
                layer.open({
                    type: 2,
                    title: 'EXCEL导入',
                    area: ['500px', '400px'],
                    content: BASE_PATH + '/pmlsKfCommission/toView'
                });
            }
        }
    };

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
        tableRender();
        var index = parent.layer.load(2);
        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsKfCommission/queryList',
            cols: setCols(),
            where: optionsData,
            page:{
                curr: 1 //重新从第 1 页开始
            },
            done: function () {
                parent.layer.close(index);
            }
        });
    }

    function valid() {
        var org = $("#org").val();
        if (isBlank(org)) {
            parent.layer.alert("请选择架构年份！", {icon: 2, title: '提示'});
            return false;
        }

        var cityId = $("#cityNo").val();
        if (isBlank(cityId)) {
            parent.layer.alert("请选择城市！", {icon: 2, title: '提示'});
            return false;
        }

        var dateMonth = $("#date").val();
        if (isBlank(dateMonth)) {
            parent.layer.alert("请选择可发月份！", {icon: 2, title: '提示'});
            return false;
        }

        var businessType = $("#businessType").val();
        if (isBlank(businessType)) {
            parent.layer.alert("请选择业务类型！", {icon: 2, title: '提示'});
            return false;
        }

        return true;
    }

    function initOrgList() {
        var year = new Date().getFullYear();
        var result = '<option value="">请选择</option>';
        for (var i = 2018; i <= year; i++) {
            var value = 'FY' + i.toString().substr(2, 4);
            if (i == year) {
                result += "<option value='" + value + "' selected>" + i + "</option>";
                initCityList(value);
            } else {
                result += "<option value='" + value + "'>" + i + "</option>";
            }
        }
        $("#org").html('');
        $("#org").append(result);
        form.render('select');

        form.on('select(year)', function (data) {
            if ($("#importType").val() == 'yj'){
                $("#btnImport").hide();
            }else{
                $("#btnImport").show();
            }
            var year = data.value;
            if (isBlank(year)) {
                resetValue('#cityNo');
                return;
            }
            initCityList(year);
        });
    }

    function initCityList(year) {
        var url = BASE_PATH + "/pmlsKfCommission/queryCityList";
        var params = {year: year};
        ajaxGet(url, params, function (data) {
            var list = data.returnValue;
            var result = '<option value="">请选择</option>';
            if (list != null && list.length > 0) {
                for (var i = 0; i < list.length; i++) {
                    var cacheCityNo = sessionStorage.getItem("kfdr_cityNo");
                    if (cacheCityNo == list[i].cityNo) {
                        result += "<option value='" + list[i].cityNo + "' selected>" + list[i].cityName + "</option>";
                        //initBusinessTypeList(cacheCityNo, year);
                    } else {
                        result += "<option value='" + list[i].cityNo + "'>" + list[i].cityName + "</option>";
                    }
                }
            }
            $("#cityNo").html('');
            $("#cityNo").append(result);
            form.render('select');

            form.on('select(cityNo)', function (data) {
                var cityNo = data.value;
                if ($("#importType").val() == 'yj'){
                    if(cityNo!="1D74EECD-DA7D-4C9A-B9DD-76B485F666D7"){
                        $("#btnImport").hide();
                    }else{
                        $("#btnImport").show();
                    }
                }else{
                    $("#btnImport").show();
                }

                if (isBlank(cityNo)) {
                    resetValue('#businessType');
                    return;
                }
                //initBusinessTypeList(cityNo,year);
            });
        }, function () {
        });
    }

    function initBusinessTypeList(cityNo,year) {
        var url = BASE_PATH + "/pmlsKfCommission/queryBusinessTypeList";
        var params = {cityNo: cityNo,year:year};
        ajaxGet(url, params, function (data) {
            var list = data.returnValue;
            var result = '<option value="">请选择或搜索选择</option>';
            if (list != null && list.length > 0) {
                for (var i = 0; i < list.length; i++) {
                    var businessTypeCode = sessionStorage.getItem("kfdr_businessType");
                    if (businessTypeCode == list[i].businessTypeCode) {
                        result += "<option value='" + list[i].businessTypeCode + "' selected>" + list[i].businessTypeName + "</option>";
                    } else {
                        result += "<option value='" + list[i].businessTypeCode + "'>" + list[i].businessTypeName + "</option>";
                    }
                }
            }
            $("#businessType").html('');
            $("#businessType").append(result);
            form.render('select');

            form.on('select(businessType)', function (data) {
                var businessType = data.value;
                if ($("#importType").val() == 'yj'){
                    if(cityNo!="1D74EECD-DA7D-4C9A-B9DD-76B485F666D7"){
                        if(businessType=="19201" && cityNo!="1D74EECD-DA7D-4C9A-B9DD-76B485F666D7"){
                            $("#btnImport").hide();
                        }else{
                            $("#btnImport").show();
                        }
                    }else{
                        $("#btnImport").show();
                    }
                }else{
                    $("#btnImport").show();
                }

            });

        }, function () {
        });
    }

    function resetValue(id) {
        $(id).html('');
        $(id).append('<option value="">请选择或搜索选择</option>');
        form.render('select');
        if(businessType=="19201" && cityNo!="1D74EECD-DA7D-4C9A-B9DD-76B485F666D7"){
            $("#btnImport").hide();
        }else{
            $("#btnImport").show();
        }
    }
});


var params = {
    data: function () {
        var params = {
            cityNo: $("#cityNo").val(),
            dateMonth: $("#date").val(),
            importType: $("#importType").val(),
            businessTypeCode: $("#businessType").val()
        };

        return params;
    }
};