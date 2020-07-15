var formSelects;
var form;

layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;
    form = layui.form;
    formSelects = layui.formSelects;

    init();
    var qArr = ['1', '2', '3', '4', '1;2', '1;2;3', '1;2;3;4', '2;3', '2;3;4', '3;4'];

    function init() {

        // 组织架构
        organizationLinkAge();

        yearlyInit();

        form.render('select'); //刷新select选择框渲染

        //tableRender();

        /*form.on('select(serachKey)', function (data) {
            var searchKey = data.value;
            if (searchKey == 'y') {
                $("#divYearly").show();
                //$("#divYearly").attr("style", "width:190px!important;");
                $("#divQuarter").hide();
            } else if (searchKey == 'q') {
                $("#divYearly").show();
                //$("#divYearly").attr("style", "width:90px!important;");
                $("#divQuarter").show();
                //$("#divQuarter").attr("style", "width:90px!important;");
            }
            form.render('select'); //刷新select选择框渲染
        });*/

        /*formSelects.on('quarter', function (id, vals, val, isAdd, isDisabled) {
            var ids = [];
            if (isAdd) {
                vals.push(val);
            }
            if (vals.length > 0) {
                for (var i = 0; i < vals.length; i++) {
                    if (isAdd == true || vals[i].value != val.value) {
                        ids.push(vals[i].value)
                    }
                }
            }

            ids.sort();
            var tmp_quarters = ids.join(';');
            if (qArr.indexOf(tmp_quarters) < 0) {
                parent.layer.closeAll();
                parent.layer.alert('请选择连续的季度！', {icon: 2, title: '提示'});
                return false;
            }

        });*/
    }


    function yearlyInit() {
        var defaultYear = new Date().getFullYear();
        var end = defaultYear;
        var start = 2017;

        var result = "";
        for (var i = end; i >= start; i--) {
            var value = i;
            if (i == defaultYear) {
                result += "<option value='" + value + "' selected>" + i + "</option>";
            } else {
                result += "<option value='" + value + "'>" + i + "</option>";
            }
        }
        $("#yearly").empty().append(result);

        form.render('select')
    }

    function tableRender() {
        table.render({
            elem: '#mainTable'
            , cols: setCols()
            //, url: BASE_PATH + '/sceneTrade/qSceneEstate'
            , id: 'contentReload'
            , page: true
            , height: "full"
            , limits: [10, 20, 30]
            , limit: 10 //默认采用60
            , even: false //开启隔行背景
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
            , done: function (res, curr, count) {

                console.info(res);
                console.info(curr);
                console.info(count);
                //style="font-weight:bold"
            }

        });
    }

    function setCols() {
        var yearly = $("#yearly").val();
        var row1 = [
            {type: 'numbers', title: '序号', width: 60, rowspan: 2},
            {field: 'gsRegion', title: '归属区域', width: 90, align: 'center', rowspan: 2},
            {field: 'gsCity', title: '归属城市', width: 90, align: 'center', rowspan: 2},
            {field: 'projectNo', title: '项目编号', width: 120, align: 'center', rowspan: 2},
            {field: 'estateName', title: '项目名称', width: 150, align: 'center', rowspan: 2},
            {field: '', title: yearly + '年当年', width: 1200, align: 'center', colspan: 8},
            {field: '', title: yearly + '年第一季度', width: 1200, align: 'center', colspan: 8},
            {field: '', title: yearly + '年第二季度', width: 1200, align: 'center', colspan: 8},
            {field: '', title: yearly + '年第三季度', width: 1200, align: 'center', colspan: 8},
            {field: '', title: yearly + '年第四季度', width: 1200, align: 'center', colspan: 8}
        ];

        var row2 = [
            {
                field: 'dqJzdd', title: '结转大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqJzdd = isEmpty(d.dqJzdd) ? "0.00" : formatMoney(d.dqJzdd);
                    return dqJzdd;
                }
            },
            {
                field: 'dqXzdd', title: '新增大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqXzdd = isEmpty(d.dqXzdd) ? "0.00" : formatMoney(d.dqXzdd);
                    return dqXzdd;
                }
            },
            {
                field: 'dqLjdd', title: '累计大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqLjdd = isEmpty(d.dqLjdd) ? "0.00" : formatMoney(d.dqLjdd);
                    return dqLjdd;
                }
            },
            {
                field: 'dqXzcx', title: '新增成销', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqXzcx = isEmpty(d.dqXzcx) ? "0.00" : formatMoney(d.dqXzcx);
                    return dqXzcx;
                }
            },
            {field: 'dqJzcxRate', title: '结转成销转化率', width: 150, align: 'center'},
            {field: 'dqXzcxRate', title: '新增成销转化率', width: 150, align: 'center'},
            {field: 'dqZtcxRate', title: '整体成销转化率', width: 150, align: 'center'},
            {field: 'dqDdzcxzq', title: '大定转成销周期', width: 150, align: 'center'},
            {
                field: 'dqJzddQ1', title: '结转大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqJzdd = isEmpty(d.dqJzddQ1) ? "0.00" : formatMoney(d.dqJzddQ1);
                    return dqJzdd;
                }
            },
            {
                field: 'dqXzddQ1', title: '新增大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqXzdd = isEmpty(d.dqXzddQ1) ? "0.00" : formatMoney(d.dqXzddQ1);
                    return dqXzdd;
                }
            },
            {
                field: 'dqLjddQ1', title: '累计大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqLjdd = isEmpty(d.dqLjddQ1) ? "0.00" : formatMoney(d.dqLjddQ1);
                    return dqLjdd;
                }
            },
            {
                field: 'dqXzcxQ1', title: '新增成销', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqXzcx = isEmpty(d.dqXzcxQ1) ? "0.00" : formatMoney(d.dqXzcxQ1);
                    return dqXzcx;
                }
            },
            {field: 'dqJzcxRateQ1', title: '结转成销转化率', width: 150, align: 'center'},
            {field: 'dqXzcxRateQ1', title: '新增成销转化率', width: 150, align: 'center'},
            {field: 'dqZtcxRateQ1', title: '整体成销转化率', width: 150, align: 'center'},
            {field: 'dqDdzcxzqQ1', title: '大定转成销周期', width: 150, align: 'center'},
            {
                field: 'dqJzddQ2', title: '结转大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqJzdd = isEmpty(d.dqJzddQ2) ? "0.00" : formatMoney(d.dqJzddQ2);
                    return dqJzdd;
                }
            },
            {
                field: 'dqXzddQ2', title: '新增大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqXzdd = isEmpty(d.dqXzddQ2) ? "0.00" : formatMoney(d.dqXzddQ2);
                    return dqXzdd;
                }
            },
            {
                field: 'dqLjddQ2', title: '累计大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqLjdd = isEmpty(d.dqLjddQ2) ? "0.00" : formatMoney(d.dqLjddQ2);
                    return dqLjdd;
                }
            },
            {
                field: 'dqXzcxQ2', title: '新增成销', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqXzcx = isEmpty(d.dqXzcxQ2) ? "0.00" : formatMoney(d.dqXzcxQ2);
                    return dqXzcx;
                }
            },
            {field: 'dqJzcxRateQ2', title: '结转成销转化率', width: 150, align: 'center'},
            {field: 'dqXzcxRateQ2', title: '新增成销转化率', width: 150, align: 'center'},
            {field: 'dqZtcxRateQ2', title: '整体成销转化率', width: 150, align: 'center'},
            {field: 'dqDdzcxzqQ2', title: '大定转成销周期', width: 150, align: 'center'},
            {
                field: 'dqJzddQ3', title: '结转大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqJzdd = isEmpty(d.dqJzddQ3) ? "0.00" : formatMoney(d.dqJzddQ3);
                    return dqJzdd;
                }
            },
            {
                field: 'dqXzddQ3', title: '新增大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqXzdd = isEmpty(d.dqXzddQ3) ? "0.00" : formatMoney(d.dqXzddQ3);
                    return dqXzdd;
                }
            },
            {
                field: 'dqLjddQ3', title: '累计大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqLjdd = isEmpty(d.dqLjddQ3) ? "0.00" : formatMoney(d.dqLjddQ3);
                    return dqLjdd;
                }
            },
            {
                field: 'dqXzcxQ3', title: '新增成销', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqXzcx = isEmpty(d.dqXzcxQ3) ? "0.00" : formatMoney(d.dqXzcxQ3);
                    return dqXzcx;
                }
            },
            {field: 'dqJzcxRateQ3', title: '结转成销转化率', width: 150, align: 'center'},
            {field: 'dqXzcxRateQ3', title: '新增成销转化率', width: 150, align: 'center'},
            {field: 'dqZtcxRateQ3', title: '整体成销转化率', width: 150, align: 'center'},
            {field: 'dqDdzcxzqQ3', title: '大定转成销周期', width: 150, align: 'center'},
            {
                field: 'dqJzddQ4', title: '结转大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqJzdd = isEmpty(d.dqJzddQ4) ? "0.00" : formatMoney(d.dqJzddQ4);
                    return dqJzdd;
                }
            },
            {
                field: 'dqXzddQ4', title: '新增大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqXzdd = isEmpty(d.dqXzddQ4) ? "0.00" : formatMoney(d.dqXzddQ4);
                    return dqXzdd;
                }
            },
            {
                field: 'dqLjddQ4', title: '累计大定', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqLjdd = isEmpty(d.dqLjddQ4) ? "0.00" : formatMoney(d.dqLjddQ4);
                    return dqLjdd;
                }
            },
            {
                field: 'dqXzcxQ4', title: '新增成销', width: 150, align: 'center', style: 'text-align:right',
                templet: function (d) {
                    var dqXzcx = isEmpty(d.dqXzcxQ4) ? "0.00" : formatMoney(d.dqXzcxQ4);
                    return dqXzcx;
                }
            },
            {field: 'dqJzcxRateQ4', title: '结转成销转化率', width: 150, align: 'center'},
            {field: 'dqXzcxRateQ4', title: '新增成销转化率', width: 150, align: 'center'},
            {field: 'dqZtcxRateQ4', title: '整体成销转化率', width: 150, align: 'center'},
            {field: 'dqDdzcxzqQ4', title: '大定转成销周期', width: 150, align: 'center'}
        ];

        var cols = [];
        cols.push(row1);
        cols.push(row2);
        return cols;
    }

    var active = {
        reload: function () {
            if (true == valid()) {
                var optionsData = {};
                optionsData.organization = $("#orgYear").val();                             // 架构年份
                // optionsData.serachKey = $("#serachKey").val();                              // 查询维度
                optionsData.yearly = $("#yearly").val();                                    // 查询周期-年度
                // optionsData.quarter = formSelects.value('quarter', 'valStr');           // 查询周期-季度
                optionsData.regionCodes = formSelects.value('region', 'valStr');         // HBL归属区域
                optionsData.areaCityCodes = formSelects.value('areaCity', 'valStr');     // HBL归属板块
                optionsData.cityIds = formSelects.value('city', 'valStr');                 // HBL归属城市
                optionsData.estateNmKey = $("#estateNmKey").val();

                reloadData(optionsData);
            }
        },
        export: function () {
            if (true == valid()) {

                var organization = $("#orgYear").val();                             // 架构年份
                // var serachKey = $("#serachKey").val();                              // 查询维度
                var yearly = $("#yearly").val();                                    // 查询周期-年度
                // var quarters = formSelects.value('quarter', 'valStr');           // 查询周期-季度
                var regionCodes = formSelects.value('region', 'valStr');         // HBL归属区域
                var areaCityCodes = formSelects.value('areaCity', 'valStr');     // HBL归属板块
                var cityIds = formSelects.value('city', 'valStr');                 // HBL归属城市
                var estateNmKey = $("#estateNmKey").val();

                var cookieName = guid();
                window.location.href = BASE_PATH + '/pmlsLinkConversionRate/export?'
                    + "organization=" + organization
                    // + "&serachKey=" + serachKey
                    + "&yearly=" + yearly
                    // + "&quarter=" + quarters
                    + "&regionCodes=" + regionCodes
                    + "&areaCityCodes=" + areaCityCodes
                    + "&cityIds=" + cityIds
                    + "&estateNmKey=" + estateNmKey
                    + "&cookieName=" + cookieName;
            }
        }
    };

    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
        var index = layer.load(2);
        tableRender();
        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsLinkConversionRate/queryList',
            cols: setCols(),
            where: optionsData,
            page: {
                curr: 1 //重新从第 1 页开始
            },
            //height: window.innerHeight - $(".layui-table-view").offset().top - 10,
            done: function (res, curr, count) {
                var data = res.returnData;
                var mainTBody = $("div[lay-id='contentReload'] tbody");

                for (var i = 0; i < data.length; i++) {
                    var d = data[i];
                    if ("合计" == d.estateName) {
                        $(mainTBody).find("tr[data-index='" + i + "']").attr("style", "font-weight:bold");
                    }
                }

                layer.close(index);
            }
        })
        ;
    }

    function valid() {

        var organization = $("#orgYear").val();                             // 架构年份
        // var serachKey = $("#serachKey").val();                              // 查询维度
        // var yearly = $("#yearly").val();                                    // 查询周期-年度
        // var quarters = formSelects.value('quarter', 'valStr');           // 查询周期-季度
        var regionCodes = formSelects.value('region', 'valStr');         // HBL归属区域
        var areaCityCodes = formSelects.value('areaCity', 'valStr');     // HBL归属板块
        var cityIds = formSelects.value('city', 'valStr');               // HBL归属城市

        if (false == checkComm(organization, areaCityCodes, regionCodes, cityIds)) {
            return false;
        }

        /*if (serachKey == 'q') {
            if (isEmpty(quarters)) {
                parent.layer.closeAll();
                parent.layer.alert('请选择季度！', {icon: 2, title: '提示'});
                return false;
            }
            var str = quarters.split(',');
            str.sort();
            var tmp_quarters = str.join(';');
            if (qArr.indexOf(tmp_quarters) < 0) {
                parent.layer.closeAll();
                parent.layer.alert('请选择连续的季度！', {icon: 2, title: '提示'});
                return false;
            }
        }*/


        return true;
    }

});