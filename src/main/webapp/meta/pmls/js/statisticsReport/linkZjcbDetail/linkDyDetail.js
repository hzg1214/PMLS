var form;
var formSelects;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        table = layui.table,
        layer = layui.layer,

        $ = layui.$;
    form = layui.form;
    formSelects = layui.formSelects;

    laydate.render({
        elem: '#dateStage'
        , trigger: 'click'
        , type: 'month'
    });

    var defaultYear = new Date().getFullYear();

    // 组织架构
    organizationLinkAge()


    fileInterval();


    form.render('select'); //刷新select选择框渲染

    function tableRender() {
        table.render({
            elem: '#contentTable'
            , cols: setCols()
            , id: 'contentReload'
            , data: []
            , loading: false
            , height: 'full'
            , even: false //开启隔行背景
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
    }

    //封装参数
    function getParam() {
        var param = {};
        var estateNm = trimStr($("#estateNm").val());//项目
        var reportId = trimStr($("#reportId").val());//报备编号
        if (isIncludeBlank(estateNm)) {
            parent.layer.alert('项目中间有空格，请重新输入！', {
                icon: 2,
                title: '提示'
            });
            return;
        }
        if (isIncludeBlank(reportId)) {
            parent.layer.alert('报备编号中间有空格，请重新输入！', {
                icon: 2,
                title: '提示'
            });
            return;
        }
        var organization = $("#orgYear").val();//结构年份
        // var regionCodes = getXMSelectOutIds(formSelects.value('region'));//归属区域
        // var areaCityCodes = getXMSelectOutIds(formSelects.value('areaCity'));//归属城市
        // var cityIds = getXMSelectOutIds(formSelects.value('city'));//所在城市
        // var centerIds = getXMSelectOutIds(formSelects.value('centerGroup'));//归属中心

        var regionCodes = formSelects.value('region', 'valStr');
        var areaCityCodes = formSelects.value('areaCity', 'valStr');
        var cityIds = formSelects.value('city', 'valStr');
        var centerIds = formSelects.value('centerGroup', 'valStr');

        //var dateStage = $("#dateStage").val();//期初

        param.organization = organization;
        param.regionCodes = regionCodes;
        param.areaCityCodes = areaCityCodes;
        param.cityIds = cityIds;
        param.centerIds = centerIds;
        param.estateNm = estateNm;
        param.reportId = reportId;
        //param.dateStage = dateStage;
        return param;
    }

    var active = {
        reload: function () {
            if (valid()) {
                var param = getParam();
                if (param != null) {
                    //获取参数
                    var optionsData = {
                        organization: param.organization,
                        estateNm: param.estateNm,
                        reportId: param.reportId,
                        //dateStage: param.dateStage,

                        regionCodes: param.regionCodes,
                        areaCityCodes: param.areaCityCodes,
                        cityIds: param.cityIds,
                        centerIds: param.centerIds
                    }
                    reloadData(optionsData);
                }
            }
        },
        export: function () {
            if (valid()) {
                var param = getParam();
                if (param != null) {
                    var cookieName = guid();

                    url = LOC_RES_BASE_PATH + '/pmlsLinkDyDetail/exportLinkZjcbDetailAjax?'
                        + "organization=" + param.organization
                        + "&estateNm=" + param.estateNm
                        + "&reportId=" + param.reportId
                        //+ "&dateStage=" + param.dateStage
                        + "&regionCodes=" + param.regionCodes
                        + "&areaCityCodes=" + param.areaCityCodes
                        + "&cityIds=" + param.cityIds
                        + "&centerIds=" + param.centerIds
                        + "&cookieName=" + cookieName;

                    $.ajax({
                        type: "GET",
                        url: url,
                        async: true,
                        dataType: "json",
                        contentType: 'application/json',
                        success: function (data) {
                            var dataStr = data.message;
                            //loading(dataStr);
                            layer.msg(dataStr, {
                                icon: 16,
                                shade: 0.4,
                                time: false //取消自动关闭
                            });
                        },
                        error: function () {
                        }
                    });

                    window.setTimeout(function () {
                        $("#btnExport").attr("disabled", false);
                    }, 10000);
                }
            }
        }
    };

    // function loading(dataStr) {
    //     var load = layer.msg(dataStr, {
    //         icon: 16,
    //         shade: [0.1, '#fff'],
    //         time: false  //取消自动关闭
    //     });
    //
    //     window.setTimeout(function () {
    //         layer.close(load);
    //     }, 3000);//3秒后关闭加载层
    // }

    function reloadData(optionsData) {
        tableRender();
        var index = layer.load(2);
        var organization = $("#orgYear").val();
        // $.ajax({
        //     url: BASE_PATH + '/pmlsLinkDyDetail/getDateStage',
        //     data: {organization: organization},
        //     type: "POST",
        //     async: false,
        //     dataType: "json",
        //     success: function (data) {
        //         var colsArray = setCols(data);
        //         console.log(colsArray);
        //         table.reload('contentReload', {
        //             url: BASE_PATH + '/pmlsLinkDyDetail/queryLinkZjcbDetailList',
        //             cols: colsArray,
        //             //height: window.innerHeight - $(".layui-table-view").offset().top - 30,
        //             where: optionsData,
        //             page: {
        //                 curr: 1 //重新从第 1 页开始
        //             },
        //             done: function () {
        //                 layer.close(index);
        //             }
        //         });
        //     },
        //     error: function () {
        //     }
        // });

        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsLinkDyDetail/queryLinkZjcbDetailList',
            cols: setCols(),
            //height: window.innerHeight - $(".layui-table-view").offset().top - 30,
            where: optionsData,
            page: {
                curr: 1 //重新从第 1 页开始
            },
            done: function () {
                layer.close(index);
            }
        });

    }

    function setCols() {
        var orgYear = $("#orgYear").val();
        if (orgYear <= 2019) {
            return setCols2019();
        } else {
            return setCols2020()
        }
    }

    function setCols2019() {
        var row1 = [], row2 = [];
        row1.push(
            {type: 'numbers', title: '序号', width: 60, align: 'center', rowspan: 2},
            {field: 'areaCityName', title: '业绩归属城市', width: 150, align: 'center', rowspan: 2},
            {field: 'cityGroupName', title: '业绩所在城市', width: 150, align: 'center', rowspan: 2},
            {field: 'centerGroupName', title: '业绩归属中心', width: 150, align: 'center', rowspan: 2},
            {field: 'projectCityName', title: '项目归属城市', width: 150, align: 'center', rowspan: 2},

            {field: 'projectNo', title: '项目编号', width: 150, align: 'center', rowspan: 2},
            {field: 'estateNm', title: '项目名称', width: 150, align: 'center', rowspan: 2, style: 'text-align: left'},
            {field: 'reportId', title: '报备编号', width: 150, align: 'center', rowspan: 2},
            {field: 'buildingNo', title: '楼室号', width: 150, align: 'center', rowspan: 2},

            {field: '', title: '资金成本', width: 150, align: 'center', colspan: 3},

            {field: '', title: '1月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '2月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '3月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '4月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '5月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '6月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '7月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '8月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '9月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '10月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '11月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '12月', width: 150, align: 'center', colspan: 3}
        );

        row2.push(
            {field: 'totalAmount', title: '总累计', width: 130, align: 'center', style: 'text-align: right'},
            {field: 'preTotal', title: '当年以前', width: 130, align: 'center', style: 'text-align: right'},
            {field: 'thisTotal', title: '当年新增', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dyjan', title: '超3个月垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkjan', title: '超3个月垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbjan', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dyfeb', title: '超3个月垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkfeb', title: '超3个月垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbfeb', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dymar', title: '超3个月垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkmar', title: '超3个月垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbmar', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dyapr', title: '超3个月垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkapr', title: '超3个月垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbapr', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dymay', title: '超3个月垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkmay', title: '超3个月垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbmay', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dyjun', title: '超3个月垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkjun', title: '超3个月垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbjun', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dyjul', title: '超3个月垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkjul', title: '超3个月垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbjul', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dyaug', title: '超3个月垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkaug', title: '超3个月垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbaug', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dysep', title: '超3个月垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhksep', title: '超3个月垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbsep', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dyoct', title: '超3个月垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkoct', title: '超3个月垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcboct', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dynov', title: '超3个月垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhknov', title: '超3个月垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbnov', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dydec', title: '超3个月垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkdec', title: '超3个月垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbdec', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'}
        );

        var cols = [];
        cols.push(row1);
        cols.push(row2);
        return cols;
    }

    function setCols2020() {
        var row1 = [], row2 = [];
        row1.push(
            {type: 'numbers', title: '序号', width: 60, align: 'center', rowspan: 2},
            {field: 'areaCityName', title: '业绩归属城市', width: 150, align: 'center', rowspan: 2},
            {field: 'cityGroupName', title: '业绩所在城市', width: 150, align: 'center', rowspan: 2},
            {field: 'centerGroupName', title: '业绩归属中心', width: 150, align: 'center', rowspan: 2},
            {field: 'projectCityName', title: '项目归属城市', width: 150, align: 'center', rowspan: 2},

            {field: 'projectNo', title: '项目编号', width: 150, align: 'center', rowspan: 2},
            {field: 'estateNm', title: '项目名称', width: 150, align: 'center', rowspan: 2, style: 'text-align: left'},
            {field: 'reportId', title: '报备编号', width: 150, align: 'center', rowspan: 2},
            {field: 'buildingNo', title: '楼室号', width: 150, align: 'center', rowspan: 2},

            {field: '', title: '2019年', width: 150, align: 'center', colspan: 3},
            {field: '', title: '资金成本', width: 150, align: 'center', colspan: 3},

            {field: '', title: '1月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '2月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '3月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '4月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '5月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '6月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '7月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '8月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '9月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '10月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '11月', width: 150, align: 'center', colspan: 3},
            {field: '', title: '12月', width: 150, align: 'center', colspan: 3}
        );

        row2.push(
            {field: 'dy2019beflj', title: '累计垫佣', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'hk2019beflj', title: '累计回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcb2019beflj', title: '累计资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'totalAmount', title: '总累计', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'preTotal', title: '当年以前', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'thisTotal', title: '当年新增', width: 150, align: 'center', style: 'text-align: right'},

            {field: 'dyjan', title: '垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkjan', title: '垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbjan', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dyfeb', title: '垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkfeb', title: '垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbfeb', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dymar', title: '垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkmar', title: '垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbmar', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dyapr', title: '垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkapr', title: '垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbapr', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dymay', title: '垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkmay', title: '垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbmay', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dyjun', title: '垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkjun', title: '垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbjun', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dyjul', title: '垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkjul', title: '垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbjul', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dyaug', title: '垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkaug', title: '垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbaug', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dysep', title: '垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhksep', title: '垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbsep', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dyoct', title: '垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkoct', title: '垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcboct', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dynov', title: '垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhknov', title: '垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbnov', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'dydec', title: '垫佣金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'dyhkdec', title: '垫佣回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'zjcbdec', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'}
        );

        var cols = [];
        cols.push(row1);
        cols.push(row2);
        return cols;
    }

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    element.on("tab(myTab)", function (data) {
        if (data.index == 0) {
            window.location.href = BASE_PATH + '/pmlsLinkMarginDetail';
        }
        else if (data.index == 1) {
            return;
        }
    });

    //校验
    function valid() {
        var organization = $("#orgYear").val();
        var areaCityCodes = getXMSelectOutIds(formSelects.value('areaCity'));//归属城市
        var region = getXMSelectOutIds(formSelects.value('region'));//归属区域
        var city = getXMSelectOutIds(formSelects.value('city'));//所在城市
        return checkComm(organization, areaCityCodes, region, city);
    }

    function fileInterval() {
        var url = BASE_PATH + '/expendReport/fileIntervalByName/' + "linkZjcbDetail_filepath";

        $.ajax({
            type: "GET",
            url: url,
            async: true,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                var fileId = data.fileId;
                console.log("fileId:" + fileId);
                if (fileId != undefined && fileId != null && fileId != '' && fileId != 0) {
                    downLoadExcel2(fileId);
                }
            },
            error: function () {
            }
        });

        window.setTimeout(fileInterval, 30000);

    };

    function downLoadExcel2(fileId) {
        console.log("fileId=" + fileId);
        var url = BASE_PATH + '/expendReport/downLoadExcel2?fileId=' + fileId;
        window.parent.location.href = url;
        var url = BASE_PATH + '/expendReport/queryReportSize/' + "linkZjcbDetail_filepath";
        $.ajax({
            type: "GET",
            url: url,
            async: true,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                $("#reportSize").text(data.userReportSize);
                layer.closeAll();
                if ("0" == data.userReportSize) {
                    $("#exportMsg").hide();
                } else {
                    $("#exportMsg").show();
                }
            },
            error: function () {
            }
        });
    };

});

