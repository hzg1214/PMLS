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

    laydate.render({
        elem: '#countDateStart',
        type: 'date',
        format: 'yyyy-MM-dd'
        ,max:$("#countDateEndStr").val(),
        trigger: 'click',
        done: function (value, date, endDate) {
            var startDate = new Date(value).getTime();
            if($('#countDateEnd').val()!=''){
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
        max:$("#countDateEndStr").val(),
        trigger: 'click',
        done: function (value, date, endDate) {
            if($('#countDateStart').val()!=""){
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

    function tableRender() {
        table.render({
            elem: '#contentTable'
            , cols: setCols()
            , id: 'contentReload'
            , data: []
            // , height: 'full-200'
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
        var row1 = [], row2 = [];
        row1.push(
            {type:'numbers',fixed: true,title: '序号',width:60, align: 'center',rowspan: 2},
            {field: 'projectNo',fixed: true,title: '项目编号',width:100, align: 'center',rowspan: 2},
            {field: 'estateNm',fixed: true,title: '项目名称',width:150, align: 'center',rowspan: 2,style:"text-align:left"},
            {field: 'reportId',fixed: true,title: '订单编号',width:150, align: 'center',rowspan: 2},
            {field: 'companyNo',title: '渠道公司编号',width:120, align: 'center',rowspan: 2},
            {field: 'companyName',title: '渠道公司',width:150, align: 'center',rowspan: 2,style:"text-align:left"},

            {field: 'buildingNo',title: '楼室号',width:100, align: 'center',rowspan: 2},
            {field: 'num',title: '套数',width:60, align: 'center',rowspan: 2
                ,templet:function (d) {
                    if (isBlank(d.defaultFlag) || d.defaultFlag==0){
                        return d.num;
                    }else{
                        return ""
                    }
                }
            },
            {field: 'roughAmount',title: '大定总价',width:150, align: 'center',rowspan: 2
                ,templet:function (d) {
                    if (isBlank(d.defaultFlag) || d.defaultFlag==0){
                        return d.roughAmount;
                    }else{
                        return ""
                    }
                }
            },
            {field: 'roughDate',title: '大定日期',width:105, align: 'center',rowspan: 2},
            {field: 'dealAmount',title: '成销总价',width:150, align: 'center',rowspan: 2,style:"text-align:right"
                ,templet:function (d) {
                    if (isBlank(d.defaultFlag) || d.defaultFlag==0){
                        return d.dealAmount;
                    }else{
                        return ""
                    }
                }
            },
            {field: 'dealDate',title: '成销日期',width:150, align: 'center',rowspan: 2},
            {field: 'incomeStatusStr',title: '收入类型',width:115, align: 'center',rowspan: 2}
        );
        var estateType = $("#estateType").val();
        if(estateType=='yj'){
            row1.push(
                {field: '',title: '应计垫佣小计',width:150, align: 'center',colspan: 2},
                {field: '',title: '应计垫佣',width:150, align: 'center',colspan: 3},
                {field: '',title: '应计垫佣调整1',width:150, align: 'center',colspan: 3},
                {field: '',title: '应计垫佣调整2',width:150, align: 'center',colspan: 3},
                {field: '',title: '应计垫佣调整3',width:150, align: 'center',colspan: 3},
                {field: '',title: '应计垫佣调整4',width:150, align: 'center',colspan: 3},
                {field: '',title: '应计垫佣调整5',width:150, align: 'center',colspan: 3},
                {field: '',title: '应计垫佣调整6',width:150, align: 'center',colspan: 3}
            );
        }

        if(estateType=='sj'){
            row1.push(
                {field: '',title: '实际垫佣小计',width:150, align: 'center',colspan: 2},
                {field: '',title: '实际垫佣',width:150, align: 'center',colspan: 3},
                {field: '',title: '实际垫佣调整1',width:150, align: 'center',colspan: 3},
                {field: '',title: '实际垫佣调整2',width:150, align: 'center',colspan: 3},
                {field: '',title: '实际垫佣调整3',width:150, align: 'center',colspan: 3},
                {field: '',title: '实际垫佣调整4',width:150, align: 'center',colspan: 3},
                {field: '',title: '实际垫佣调整5',width:150, align: 'center',colspan: 3},
                {field: '',title: '实际垫佣调整6',width:150, align: 'center',colspan: 3}
            );
        }

        row2.push(
            {field: 'befTaxAmountTotal',title: '税前',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.befTaxAmountTotal);
                }
            },
            {field: 'aftTaxAmountTotal',title: '税后',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.aftTaxAmountTotal);
                }
            },
            {field: 'befTaxAmount',title: '税前',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.befTaxAmount);
                }
            },
            {field: 'aftTaxAmount',title: '税后',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.aftTaxAmount);
                }
            },
            {field: 'recordDate',title: '日期',width:105, align: 'center'},

            {field: 'befTaxAmount1',title: '税前',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.befTaxAmount1);
                }
            },
            {field: 'aftTaxAmount1',title: '税后',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.aftTaxAmount1);
                }
            },
            {field: 'recordDate1',title: '日期',width:105, align: 'center'},
            {field: 'befTaxAmount2',title: '税前',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.befTaxAmount2);
                }
            },
            {field: 'aftTaxAmount2',title: '税后',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.aftTaxAmount2);
                }
            },
            {field: 'recordDate2',title: '日期',width:105, align: 'center'},
            {field: 'befTaxAmount3',title: '税前',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.befTaxAmount3);
                }
            },
            {field: 'aftTaxAmount3',title: '税后',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.aftTaxAmount3);
                }
            },
            {field: 'recordDate3',title: '日期',width:105, align: 'center'},

            {field: 'befTaxAmount4',title: '税前',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.befTaxAmount4);
                }
            },
            {field: 'aftTaxAmount4',title: '税后',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.aftTaxAmount4);
                }
            },
            {field: 'recordDate4',title: '日期',width:105, align: 'center'},
            {field: 'befTaxAmount5',title: '税前',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.befTaxAmount5);
                }
            },
            {field: 'aftTaxAmount5',title: '税后',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.aftTaxAmount5);
                }
            },
            {field: 'recordDate5',title: '日期',width:105, align: 'center'},
            {field: 'befTaxAmount6',title: '税前',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.befTaxAmount6);
                }
            },
            {field: 'aftTaxAmount6',title: '税后',width:130, align: 'center',style:"text-align:right"
                ,templet: function (d) {
                    return formatMoney2(d.aftTaxAmount6);
                }
            },
            {field: 'recordDate6',title: '日期',width:105, align: 'center'}
        );
        var cols = [];
        cols.push(row1);
        cols.push(row2);
        return cols;
    }

    //reloadData();//初始化加载表格

    active = {
        reload: function () {
            if (valid()) {
                var optionsData = {};
                // optionsData.cityNo = $("#cityNo").val();
                optionsData.projectDepartmentId = $("#projectDepartmentId").val();
                optionsData.searchKey = trimStr($("#searchKey").val());
                optionsData.reportId = trimStr($("#reportId").val());
                optionsData.countDateStart = $("#countDateStart").val();
                optionsData.countDateEnd = $("#countDateEnd").val();
                optionsData.estateType = $("#estateType").val();
                optionsData.estateNmKey = getXMSelectIds(formSelects.value('estateNmKey'));
                optionsData.businessType = $("#businessType").val();
                // optionsData.years = $("#years").val();
                // optionsData.flagHref = $("#flagHref").val();
                reloadData(optionsData);
            }
        },

        reset: function () {
            console.info("all.reset");

            $("#projectDepartmentId").val("");
            formSelects.value('estateNmKey', []);
            $("#estateType").val("");
            $("#countDateStart").val("");
            $("#countDateEnd").val("");
            $("#businessType").val("");
            $("#reportId").val("");
            $("#searchKey").val("");

            form.render('select');

            table.render({
                elem: '#contentTable'
                , cols: []
                , id: 'contentReload'
            });

        },

        export: function () {
            if (valid()) {
                var index = parent.layer.load(2,{shade: 0.1});
                var url = BASE_PATH + '/pmlsLnkYjDy/exportCheck?projectDepartmentId=' + $("#projectDepartmentId").val()
                    + "&countDateStart=" + $("#countDateStart").val() + "&countDateEnd=" + $("#countDateEnd").val()
                    + "&businessType=" + $("#businessType").val()
                    + "&searchKey=" + encodeURIComponent(trimStr($("#searchKey").val())) + "&estateType=" + $("#estateType").val()
                    + "&estateNmKey=" + getXMSelectIds(formSelects.value('estateNmKey')) + "&reportId=" + trimStr($("#reportId").val());
                $.ajax({
                    type: "GET",
                    url: url,
                    dataType:"json",
                    contentType: 'application/json',
                    success: function (data) {
                        if (data.returnCode == 200 || data.returnCode == '200') {
                            window.location.href = BASE_PATH + '/pmlsLnkYjDy/export?projectDepartmentId=' + $("#projectDepartmentId").val()
                                + "&countDateStart=" + $("#countDateStart").val() + "&countDateEnd=" + $("#countDateEnd").val()
                                + "&businessType=" + $("#businessType").val()
                                + "&searchKey=" + encodeURIComponent(trimStr($("#searchKey").val())) + "&estateType=" + $("#estateType").val()
                                + "&estateNmKey=" + getXMSelectIds(formSelects.value('estateNmKey')) + "&reportId=" + trimStr($("#reportId").val());
                                parent.layer.closeAll();
                        } else {
                            parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'},function () {
                                parent.layer.closeAll();
                            });
                            return false;
                        }
                    },
                    error: function () {
                        parent.layer.alert("查询数据失败", {icon: 2, title: '提示'},function () {
                            parent.layer.closeAll();
                        });
                        return false;
                    }
                });
            }
        },
        import: function () {
            var estateType = $('#estateType').val();
            if(estateType == ''){
                parent.layer.alert("请选择模板类型!", {icon: 2, title: '提示'});
                return false;
            }

            layer.open({
                type: 2,
                title: 'EXCEL导入',
                area: ['500px', '400px'],
                content: BASE_PATH + '/pmlsLnkYjDy/toView'
            });

        }
    };

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
        tableRender();
        var index =  parent.layer.load(2);
        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsLnkYjDy/getLnkYjDyList',
            cols: setCols(),
            where: optionsData,
            page:{
                curr: 1 //重新从第 1 页开始
            },
            done: function (res, curr, count) {
                parent.layer.close(index);
                signRed(res);
            }
        });
    }


    function valid() {
        var statrDate = $('#countDateStart').val();
        var endStart = $('#countDateEnd').val();
        var start = statrDate.substring(0,4);
        var end   = endStart.substring(0,4);
        var estateType = $('#estateType').val();
        var businessType = $('#businessType').val();

        if(estateType == ''){
            parent.layer.alert("请选择模板类型!", {icon: 2, title: '提示'});
            return false;
        }

        if(statrDate == '') {
            parent.layer.alert("请选择开始日期！", {icon: 2, title: '提示'});
            return false;
        }
        if(endStart == '') {
            parent.layer.alert("请选择结束日期！", {icon: 2, title: '提示'});
            return false;
        }
        if(statrDate > endStart) {
            parent.layer.alert("开始日期不能大于结束日期!", {icon: 2, title: '提示'});
            return false;
        }

        if(businessType == ''){
            parent.layer.alert("请选择业务阶段!", {icon: 2, title: '提示'});
            return false;
        }


        $('#years').val(start);
        return true;
    }

});


var params = {
    data: function () {
        var params = {
            // cityNo : $("#cityNo").val(),无
            projectDepartmentId : $("#projectDepartmentId").val(),
            searchKey : trimStr($("#searchKey").val()),
            reportId : trimStr($("#reportId").val()),
            countDateStart : $("#countDateStart").val(),
            countDateEnd : $("#countDateEnd").val(),
            estateType : $("#estateType").val(),
            //estateNmKey : getXMSelectIds(formSelects.value('estateNmKey')),
            businessType : $("#businessType").val()
            // years:$("#years").val(),无
            // flagHref:$("#flagHref").val() 无
        };
        return params;
    }
};