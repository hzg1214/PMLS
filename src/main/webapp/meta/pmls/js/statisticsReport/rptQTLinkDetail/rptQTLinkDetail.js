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

    function init() {

        laydate.render({
            elem: '#dealDateStart',
            type: 'date',
            trigger: 'click',
            format: 'yyyy-MM-dd',
            done: function (value, date, endDate) {
                var startDate = new Date(value).getTime();
                if ($('#dealDateEnd').val() != '') {
                    var endTime = new Date($('#dealDateEnd').val()).getTime();
                    if (endTime < startDate) {
                        layer.msg('开始时间不能大于结束时间');
                        $('#dealDateStart').val('');
                    }
                }
            }
        });
        laydate.render({
            elem: '#dealDateEnd',
            type: 'date',
            trigger: 'click',
            format: 'yyyy-MM-dd',
            done: function (value, date, endDate) {
                if ($('#dealDateStart').val() != "") {
                    var startDate = new Date($('#dealDateStart').val()).getTime();
                    var endTime = new Date(value).getTime();
                    if (endTime < startDate) {
                        layer.msg('结束时间不能小于开始时间');
                        $('#dealDateEnd').val('');
                    }
                }
            }
        });

        yearlyInit();

        initAccountProject();

        // 组织架构
        organizationLinkAge();

        form.render('select'); //刷新select选择框渲染

    }

    //初始化-核算主体
    function initAccountProject() {
        var url = BASE_PATH + "/pmlsLinkDetail/getAccountProject";
        var params = {};
        ajaxGet(url, params, function (data) {
            var list = data.returnData;
            var allAccountPorjectList = [];
            var result = [];
            if (list != null && list.length > 0) {
                for (var i = 0; i < list.length; i++) {
                    result.push({"name": list[i].accountProject, "value": list[i].accountProjectNo});
                    allAccountPorjectList.push(list[i].accountProjectNo);
                }
            }
            formSelects.data('accountProject', 'local', {arr: result});

            formSelects.btns('accountProject', [
                {
                    icon: "xm-iconfont icon-quanxuan",
                    name: '全选',
                    click: function (id) {
                        //点击后的操作
                        layui.formSelects.value(id, allAccountPorjectList);

                    }
                }, {
                    icon: "xm-iconfont icon-qingkong",
                    name: '清空',
                    click: function (id) {
                        //点击后的操作
                        formSelects.value(id, [])
                    }
                }
            ]);

        }, function () {
        });

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
        var row1 = [
            {type: 'numbers', title: '序号', width: 60, rowspan: 3},                    // 1
            {field: '', title: '业绩信息', align: 'center', colspan: 9},               // 2-10
            {field: '', title: '案件信息', align: 'center', colspan: 10},               // 11-19    ==》+1
            {field: '', title: '返佣信息', align: 'center', colspan: 22},              // 20-41
            {field: '', title: '应计收入（总累计）', align: 'center', colspan: 6},    // 42-47
            {field: '', title: '当年以前应计收入', align: 'center', colspan: 6},      // 48-53
            {field: '', title: '当年应计收入', align: 'center', colspan: 6},          // 54-59
            {field: '', title: '1月应计收入', align: 'center', colspan: 6},           // 60-65
            {field: '', title: '2月应计收入', align: 'center', colspan: 6},           // 66-71
            {field: '', title: '3月应计收入', align: 'center', colspan: 6},           // 72-77
            {field: '', title: '4月应计收入', align: 'center', colspan: 6},           // 78-83
            {field: '', title: '5月应计收入', align: 'center', colspan: 6},           // 84-89
            {field: '', title: '6月应计收入', align: 'center', colspan: 6},           // 90-95
            {field: '', title: '7月应计收入', align: 'center', colspan: 6},           // 96-101
            {field: '', title: '8月应计收入', align: 'center', colspan: 6},           // 102-107
            {field: '', title: '9月应计收入', align: 'center', colspan: 6},           // 108-113
            {field: '', title: '10月应计收入', align: 'center', colspan: 6},           // 114-119
            {field: '', title: '11月应计收入', align: 'center', colspan: 6},           // 120-125
            {field: '', title: '12月应计收入', align: 'center', colspan: 6},           // 126-131


            {field: '', title: '应收收入（总累计）', align: 'center', colspan: 6},   // 132-137
            {field: '', title: '当年以前应收收入', align: 'center', colspan: 6},   // 138-143
            {field: '', title: '当年应收收入', align: 'center', colspan: 6},           // 144-149
            {field: '', title: '1月应收收入', align: 'center', colspan: 9},           // 150-158
            {field: '', title: '2月应收收入', align: 'center', colspan: 9},           // 159-167
            {field: '', title: '3月应收收入', align: 'center', colspan: 9},           // 168-176
            {field: '', title: '4月应收收入', align: 'center', colspan: 9},           // 177-185
            {field: '', title: '5月应收收入', align: 'center', colspan: 9},           // 186-194
            {field: '', title: '6月应收收入', align: 'center', colspan: 9},           // 195-203
            {field: '', title: '7月应收收入', align: 'center', colspan: 9},           // 204-212
            {field: '', title: '8月应收收入', align: 'center', colspan: 9},           // 213-221
            {field: '', title: '9月应收收入', align: 'center', colspan: 9},           // 222-230
            {field: '', title: '10月应收收入', align: 'center', colspan: 9},           // 231-239
            {field: '', title: '11月应收收入', align: 'center', colspan: 9},           // 240-248
            {field: '', title: '12月应收收入', align: 'center', colspan: 9},           // 249-257


            {field: '', title: '实际收付（总累计）', align: 'center', colspan: 6},   // 258-263
            {field: '', title: '当年以前实际收付', align: 'center', colspan: 6},   // 264-269
            {field: '', title: '当年实际收付', align: 'center', colspan: 6},           // 270-275
            {field: '', title: '1月实际收付', align: 'center', colspan: 6},           // 276-281
            {field: '', title: '2月实际收付', align: 'center', colspan: 6},           // 282-287
            {field: '', title: '3月实际收付', align: 'center', colspan: 6},           // 288-293
            {field: '', title: '4月实际收付', align: 'center', colspan: 6},           // 294-299
            {field: '', title: '5月实际收付', align: 'center', colspan: 6},           // 300-305
            {field: '', title: '6月实际收付', align: 'center', colspan: 6},           // 306-311
            {field: '', title: '7月实际收付', align: 'center', colspan: 6},           // 312-317
            {field: '', title: '8月实际收付', align: 'center', colspan: 6},           // 318-323
            {field: '', title: '9月实际收付', align: 'center', colspan: 6},           // 324-329
            {field: '', title: '10月实际收付', align: 'center', colspan: 6},           // 330-335
            {field: '', title: '11月实际收付', align: 'center', colspan: 6},           // 336-341
            {field: '', title: '12月实际收付', align: 'center', colspan: 6},           // 342-347

            {field: '', title: '应计佣金小计', align: 'center', colspan: 2}            // 348-349

        ];

        var row2 = [

            {field: 'accountProjectNo', title: '核算主体-编码', align: 'center', width: 150, rowspan: 2},       // 2
            {field: 'accountProject', title: '核算主体-名称', align: 'center', width: 150, rowspan: 2},         // 3
            {field: 'yjgsqyName', title: '业绩归属区域', align: 'center', width: 150, rowspan: 2},             // 4
            {field: 'yjgscsName', title: '业绩归属城市', align: 'center', width: 150, rowspan: 2},              // 5
            {field: 'yjszcsName', title: '业绩所在城市', align: 'center', width: 150, rowspan: 2},              // 6
            {field: 'yjgszxName', title: '业绩归属中心', align: 'center', width: 150, rowspan: 2},              // 7
            {field: 'srgscsName', title: '收入归属城市', align: 'center', width: 150, rowspan: 2},              // 8
            {field: 'srgszxName', title: '收入归属中心', align: 'center', width: 150, rowspan: 2},              // 9
            {field: 'srgsbmName', title: '收入归属项目部', align: 'center', width: 150, rowspan: 2},            // 10
            {field: 'kfsName', title: '开发商名称', align: 'center', width: 150, rowspan: 2},                    // 11
            {field: 'htjfName', title: '合同甲方名称', align: 'center', width: 150, rowspan: 2},                // 12
            {field: 'reportNo', title: '报备编号', align: 'center', width: 200, rowspan: 2},                    // 13
            {field: 'num', title: '套数', align: 'center', width: 60, rowspan: 2},
            {field: 'projectNo', title: '项目编号', align: 'center', width: 150, rowspan: 2},                   // 14
            {field: 'estateName', title: '项目名称', align: 'center', width: 150, rowspan: 2},                  // 15
            {field: 'reportDate', title: '报备日期', align: 'center', width: 150, rowspan: 2},                  // 16
            {field: 'dealDate', title: '成销日期', align: 'center', width: 150, rowspan: 2},                    // 17
            {field: 'qkdzrq', title: '全款到账日期', align: 'center', width: 150, rowspan: 2},                  // 18
            {field: 'srlxName', title: '收入类型', align: 'center', width: 150, rowspan: 2},                    // 19
            {field: 'kscqfyje', title: '扣税差前返佣金额', align: 'center', width: 150, rowspan: 2, style: 'text-align:right'},           // 20
            {field: 'fysc', title: '返佣税差', align: 'center', width: 150, rowspan: 2, style: 'text-align:right'},                        // 21

            {field: '', title: '返佣对象名称', align: 'center', width: 150, colspan: 20},                       // 22-41

            {
                field: 'yjsrtotal_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 42
            {
                field: 'yjsrtotal_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 43
            {
                field: 'yjfytotal_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 44
            {
                field: 'yjfytotal_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 45
            {
                field: 'cxjstotal_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 46
            {
                field: 'cxjstotal_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 47
            {
                field: 'yjsrbef_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 48
            {
                field: 'yjsrbef_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 49
            {
                field: 'yjfybef_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 50
            {
                field: 'yjfybef_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 51
            {
                field: 'cxjsbef_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 52
            {
                field: 'cxjsbef_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 53
            {
                field: 'yjsrthis_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 54
            {
                field: 'yjsrthis_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 55
            {
                field: 'yjfythis_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 56
            {
                field: 'yjfythis_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 57
            {
                field: 'cxjsthis_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 58
            {
                field: 'cxjsthis_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 59
            {
                field: 'yjsrjan_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 60
            {
                field: 'yjsrjan_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 61
            {
                field: 'yjfyjan_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 62
            {
                field: 'yjfyjan_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 63
            {
                field: 'cxjsjan_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 64
            {
                field: 'cxjsjan_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 65
            {
                field: 'yjsrfeb_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 66
            {
                field: 'yjsrfeb_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 67
            {
                field: 'yjfyfeb_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 68
            {
                field: 'yjfyfeb_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 69
            {
                field: 'cxjsfeb_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 70
            {
                field: 'cxjsfeb_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 71
            {
                field: 'yjsrmar_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 72
            {
                field: 'yjsrmar_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 73
            {
                field: 'yjfymar_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 74
            {
                field: 'yjfymar_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 75
            {
                field: 'cxjsmar_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 76
            {
                field: 'cxjsmar_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 77
            {
                field: 'yjsrapr_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 78
            {
                field: 'yjsrapr_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 79
            {
                field: 'yjfyapr_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 80
            {
                field: 'yjfyapr_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 81
            {
                field: 'cxjsapr_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 82
            {
                field: 'cxjsapr_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 83
            {
                field: 'yjsrmay_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 84
            {
                field: 'yjsrmay_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 85
            {
                field: 'yjfymay_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 86
            {
                field: 'yjfymay_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 87
            {
                field: 'cxjsmay_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 88
            {
                field: 'cxjsmay_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 89
            {
                field: 'yjsrjun_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 90
            {
                field: 'yjsrjun_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 91
            {
                field: 'yjfyjun_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 92
            {
                field: 'yjfyjun_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 93
            {
                field: 'cxjsjun_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 94
            {
                field: 'cxjsjun_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 95
            {
                field: 'yjsrjul_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 96
            {
                field: 'yjsrjul_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 97
            {
                field: 'yjfyjul_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 98
            {
                field: 'yjfyjul_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 99
            {
                field: 'cxjsjul_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 100
            {
                field: 'cxjsjul_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 101
            {
                field: 'yjsraug_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 102
            {
                field: 'yjsraug_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 103
            {
                field: 'yjfyaug_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 104
            {
                field: 'yjfyaug_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 105
            {
                field: 'cxjsaug_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 106
            {
                field: 'cxjsaug_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 107
            {
                field: 'yjsrsep_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 108
            {
                field: 'yjsrsep_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 109
            {
                field: 'yjfysep_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 110
            {
                field: 'yjfysep_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 111
            {
                field: 'cxjssep_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 112
            {
                field: 'cxjssep_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 113
            {
                field: 'yjsroct_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 114
            {
                field: 'yjsroct_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 115
            {
                field: 'yjfyoct_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 116
            {
                field: 'yjfyoct_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 117
            {
                field: 'cxjsoct_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 118
            {
                field: 'cxjsoct_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 119
            {
                field: 'yjsrnov_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 120
            {
                field: 'yjsrnov_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 121
            {
                field: 'yjfynov_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 122
            {
                field: 'yjfynov_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 123
            {
                field: 'cxjsnov_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 124
            {
                field: 'cxjsnov_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 125
            {
                field: 'yjsrdec_sq',
                title: '成销收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 126
            {
                field: 'yjsrdec_sh',
                title: '成销收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 127
            {
                field: 'yjfydec_sq',
                title: '返佣金额（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 128
            {
                field: 'yjfydec_sh',
                title: '返佣金额（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 129
            {
                field: 'cxjsdec_sq',
                title: '成销净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 130
            {
                field: 'cxjsdec_sh',
                title: '成销净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 131
            {
                field: 'yssrtotal_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 132
            {
                field: 'yssrtotal_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 133
            {
                field: 'yffytotal_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 134
            {
                field: 'yffytotal_sh',
                title: '应付返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 135
            {
                field: 'yfjstotal_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 136
            {
                field: 'yfjstotal_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 137
            {
                field: 'yssrbef_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 138
            {
                field: 'yssrbef_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 139
            {
                field: 'yffybef_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 140
            {
                field: 'yffybef_sh',
                title: '应返返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 141
            {
                field: 'yfjsbef_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 142
            {
                field: 'yfjsbef_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 143
            {
                field: 'yssrthis_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 144
            {
                field: 'yssrthis_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 145
            {
                field: 'yffythis_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 146
            {
                field: 'yffythis_sh',
                title: '应返返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 147
            {
                field: 'yfjsthis_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 148
            {
                field: 'yfjsthis_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 149
            {
                field: 'yssrjan_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 150
            {
                field: 'yssrjan_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 151
            {
                field: 'yffyjan_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 152
            {
                field: 'yffyjan_sh',
                title: '应返返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 153
            {
                field: 'yfjsjan_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 154
            {
                field: 'yfjsjan_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 155
            {field: 'yssrqryj_jan', title: '应收收入 确认依据', align: 'center', width: 150, rowspan: 2},                                 // 156
            {field: 'jssfcsj_jan', title: '结算书 发出时间', align: 'center', width: 150, rowspan: 2},                                    // 157
            {field: 'jssshsj_jan', title: '结算书 收回时间', align: 'center', width: 150, rowspan: 2},                                    // 158
            {
                field: 'yssrfeb_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 159
            {
                field: 'yssrfeb_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 160
            {
                field: 'yffyfeb_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 161
            {
                field: 'yffyfeb_sh',
                title: '应返返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 162
            {
                field: 'yfjsfeb_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 163
            {
                field: 'yfjsfeb_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },        // 164
            {field: 'yssrqryj_feb', title: '应收收入 确认依据', align: 'center', width: 150, rowspan: 2},                                // 165
            {field: 'jssfcsj_feb', title: '结算书 发出时间', align: 'center', width: 150, rowspan: 2},                                   // 166
            {field: 'jssshsj_feb', title: '结算书 收回时间', align: 'center', width: 150, rowspan: 2},                                   // 167
            {
                field: 'yssrmar_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 168
            {
                field: 'yssrmar_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 169
            {
                field: 'yffymar_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 170
            {
                field: 'yffymar_sh',
                title: '应返返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 171
            {
                field: 'yfjsmar_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 172
            {
                field: 'yfjsmar_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 173
            {field: 'yssrqryj_mar', title: '应收收入 确认依据', align: 'center', width: 150, rowspan: 2},                                // 174
            {field: 'jssfcsj_mar', title: '结算书 发出时间', align: 'center', width: 150, rowspan: 2},                                   // 175
            {field: 'jssshsj_mar', title: '结算书 收回时间', align: 'center', width: 150, rowspan: 2},                                   // 176
            {
                field: 'yssrapr_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 177
            {
                field: 'yssrapr_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 178
            {
                field: 'yffyapr_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 179
            {
                field: 'yffyapr_sh',
                title: '应返返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 180
            {
                field: 'yfjsapr_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 181
            {
                field: 'yfjsapr_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 182
            {field: 'yssrqryj_apr', title: '应收收入 确认依据', align: 'center', width: 150, rowspan: 2},                                // 183
            {field: 'jssfcsj_apr', title: '结算书 发出时间', align: 'center', width: 150, rowspan: 2},                                   // 184
            {field: 'jssshsj_apr', title: '结算书 收回时间', align: 'center', width: 150, rowspan: 2},                                   // 185
            {
                field: 'yssrmay_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 186
            {
                field: 'yssrmay_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 187
            {
                field: 'yffymay_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 188
            {
                field: 'yffymay_sh',
                title: '应返返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 189
            {
                field: 'yfjsmay_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 190
            {
                field: 'yfjsmay_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 191
            {field: 'yssrqryj_may', title: '应收收入 确认依据', align: 'center', width: 150, rowspan: 2},                                // 192
            {field: 'jssfcsj_may', title: '结算书 发出时间', align: 'center', width: 150, rowspan: 2},                                   // 193
            {field: 'jssshsj_may', title: '结算书 收回时间', align: 'center', width: 150, rowspan: 2},                                   // 194
            {
                field: 'yssrjun_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 195
            {
                field: 'yssrjun_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 196
            {
                field: 'yffyjun_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 197
            {
                field: 'yffyjun_sh',
                title: '应返返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 198
            {
                field: 'yfjsjun_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 199
            {
                field: 'yfjsjun_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },       // 200
            {field: 'yssrqryj_jun', title: '应收收入 确认依据', align: 'center', width: 150, rowspan: 2},                                // 201
            {field: 'jssfcsj_jun', title: '结算书 发出时间', align: 'center', width: 150, rowspan: 2},                                  // 202
            {field: 'jssshsj_jun', title: '结算书 收回时间', align: 'center', width: 150, rowspan: 2},                                  // 203
            {
                field: 'yssrjul_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 204
            {
                field: 'yssrjul_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 205
            {
                field: 'yffyjul_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 206
            {
                field: 'yffyjul_sh',
                title: '应返返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 207
            {
                field: 'yfjsjul_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 208
            {
                field: 'yfjsjul_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 209
            {field: 'yssrqryj_jul', title: '应收收入 确认依据', align: 'center', width: 150, rowspan: 2},                                // 210
            {field: 'jssfcsj_jul', title: '结算书 发出时间', align: 'center', width: 150, rowspan: 2},                                   // 211
            {field: 'jssshsj_jul', title: '结算书 收回时间', align: 'center', width: 150, rowspan: 2},                                   // 212
            {
                field: 'yssraug_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 213
            {
                field: 'yssraug_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 214
            {
                field: 'yffyaug_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 215
            {
                field: 'yffyaug_sh',
                title: '应返返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 216
            {
                field: 'yfjsaug_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 217
            {
                field: 'yfjsaug_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 218
            {field: 'yssrqryj_aug', title: '应收收入 确认依据', align: 'center', width: 150, rowspan: 2},                               // 219
            {field: 'jssfcsj_aug', title: '结算书 发出时间', align: 'center', width: 150, rowspan: 2},                                  // 220
            {field: 'jssshsj_aug', title: '结算书 收回时间', align: 'center', width: 150, rowspan: 2},                                  // 221
            {
                field: 'yssrsep_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 222
            {
                field: 'yssrsep_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 223
            {
                field: 'yffysep_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 224
            {
                field: 'yffysep_sh',
                title: '应返返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 225
            {
                field: 'yfjssep_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 226
            {
                field: 'yfjssep_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 227
            {field: 'yssrqryj_sep', title: '应收收入 确认依据', align: 'center', width: 150, rowspan: 2},                               // 228
            {field: 'jssfcsj_sep', title: '结算书 发出时间', align: 'center', width: 150, rowspan: 2},                                  // 229
            {field: 'jssshsj_sep', title: '结算书 收回时间', align: 'center', width: 150, rowspan: 2},                                  // 230
            {
                field: 'yssroct_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 231
            {
                field: 'yssroct_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 232
            {
                field: 'yffyoct_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 233
            {
                field: 'yffyoct_sh',
                title: '应返返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 234
            {
                field: 'yfjsoct_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 235
            {
                field: 'yfjsoct_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 236
            {field: 'yssrqryj_oct', title: '应收收入 确认依据', align: 'center', width: 150, rowspan: 2},                               // 237
            {field: 'jssfcsj_oct', title: '结算书 发出时间', align: 'center', width: 150, rowspan: 2},                                  // 238
            {field: 'jssshsj_oct', title: '结算书 收回时间', align: 'center', width: 150, rowspan: 2},                                  // 239
            {
                field: 'yssrnov_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 240
            {
                field: 'yssrnov_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 241
            {
                field: 'yffynov_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 242
            {
                field: 'yffynov_sh',
                title: '应返返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 243
            {
                field: 'yfjsnov_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 244
            {
                field: 'yfjsnov_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 245
            {field: 'yssrqryj_nov', title: '应收收入 确认依据', align: 'center', width: 150, rowspan: 2},                               // 246
            {field: 'jssfcsj_nov', title: '结算书 发出时间', align: 'center', width: 150, rowspan: 2},                                  // 247
            {field: 'jssshsj_nov', title: '结算书 收回时间', align: 'center', width: 150, rowspan: 2},                                  // 248
            {
                field: 'yssrdec_sq',
                title: '应收收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 249
            {
                field: 'yssrdec_sh',
                title: '应收收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 250
            {
                field: 'yffydec_sq',
                title: '应付返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 251
            {
                field: 'yffydec_sh',
                title: '应返返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 252
            {
                field: 'yfjsdec_sq',
                title: '应收净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 253
            {
                field: 'yfjsdec_sh',
                title: '应收净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 254
            {field: 'yssrqryj_dec', title: '应收收入 确认依据', align: 'center', width: 150, rowspan: 2},   // 255
            {field: 'jssfcsj_dec', title: '结算书 发出时间', align: 'center', width: 150, rowspan: 2},      // 256
            {field: 'jssshsj_dec', title: '结算书 收回时间', align: 'center', width: 150, rowspan: 2},      // 257
            {
                field: 'yjsstotal_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },    // 258
            {
                field: 'yjsstotal_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },    // 259
            {
                field: 'sjfytotal_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },    // 260
            {
                field: 'sjfytotal_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },    // 261
            {
                field: 'sjjstotal_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },    // 262
            {
                field: 'sjjstotal_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },    // 263
            {
                field: 'yjssbef_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 264
            {
                field: 'yjssbef_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 265
            {
                field: 'sjfybef_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 266
            {
                field: 'sjfybef_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 267
            {
                field: 'sjjsbef_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 268
            {
                field: 'sjjsbef_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 269
            {
                field: 'yjssthis_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },    // 270
            {
                field: 'yjssthis_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },    // 271
            {
                field: 'sjfythis_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },    // 272
            {
                field: 'sjfythis_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },    // 273
            {
                field: 'sjjsthis_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },    // 274
            {
                field: 'sjjsthis_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },    // 275
            {
                field: 'yjssjan_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 276
            {
                field: 'yjssjan_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 277
            {
                field: 'sjfyjan_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 278
            {
                field: 'sjfyjan_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 279
            {
                field: 'sjjsjan_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 280
            {
                field: 'sjjsjan_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 281
            {
                field: 'yjssfeb_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 282
            {
                field: 'yjssfeb_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 283
            {
                field: 'sjfyfeb_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 284
            {
                field: 'sjfyfeb_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 285
            {
                field: 'sjjsfeb_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 286
            {
                field: 'sjjsfeb_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 287
            {
                field: 'yjssmar_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 288
            {
                field: 'yjssmar_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 289
            {
                field: 'sjfymar_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 290
            {
                field: 'sjfymar_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 291
            {
                field: 'sjjsmar_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 292
            {
                field: 'sjjsmar_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 293
            {
                field: 'yjssapr_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 294
            {
                field: 'yjssapr_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 295
            {
                field: 'sjfyapr_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 296
            {
                field: 'sjfyapr_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 297
            {
                field: 'sjjsapr_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 298
            {
                field: 'sjjsapr_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 299
            {
                field: 'yjssmay_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 300
            {
                field: 'yjssmay_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 301
            {
                field: 'sjfymay_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 302
            {
                field: 'sjfymay_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 303
            {
                field: 'sjjsmay_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 304
            {
                field: 'sjjsmay_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 305
            {
                field: 'yjssjun_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 306
            {
                field: 'yjssjun_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 307
            {
                field: 'sjfyjun_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 308
            {
                field: 'sjfyjun_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 309
            {
                field: 'sjjsjun_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 310
            {
                field: 'sjjsjun_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 311
            {
                field: 'yjssjul_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 312
            {
                field: 'yjssjul_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 313
            {
                field: 'sjfyjul_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 314
            {
                field: 'sjfyjul_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 315
            {
                field: 'sjjsjul_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 316
            {
                field: 'sjjsjul_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 317
            {
                field: 'yjssaug_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 318
            {
                field: 'yjssaug_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 319
            {
                field: 'sjfyaug_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 320
            {
                field: 'sjfyaug_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 321
            {
                field: 'sjjsaug_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 322
            {
                field: 'sjjsaug_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 323
            {
                field: 'yjsssep_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 324
            {
                field: 'yjsssep_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 325
            {
                field: 'sjfysep_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 326
            {
                field: 'sjfysep_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 327
            {
                field: 'sjjssep_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 328
            {
                field: 'sjjssep_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 329
            {
                field: 'yjssoct_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 330
            {
                field: 'yjssoct_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 331
            {
                field: 'sjfyoct_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 332
            {
                field: 'sjfyoct_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 333
            {
                field: 'sjjsoct_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 334
            {
                field: 'sjjsoct_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 335
            {
                field: 'yjssnov_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 336
            {
                field: 'yjssnov_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 337
            {
                field: 'sjfynov_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 338
            {
                field: 'sjfynov_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 339
            {
                field: 'sjjsnov_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 340
            {
                field: 'sjjsnov_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 341
            {
                field: 'yjssdec_sq',
                title: '实际收入（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 342
            {
                field: 'yjssdec_sh',
                title: '实际收入（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 343
            {
                field: 'sjfydec_sq',
                title: '实际返佣（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 344
            {
                field: 'sjfydec_sh',
                title: '实际返佣（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 345
            {
                field: 'sjjsdec_sq',
                title: '实际净收（税前）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 346
            {
                field: 'sjjsdec_sh',
                title: '实际净收（税后）',
                align: 'center',
                width: 150,
                rowspan: 2,
                style: 'text-align:right'
            },      // 347
            {field: 'gwyjtotal', title: '岗位佣金', align: 'center', width: 150, rowspan: 2, style: 'text-align:right'},                // 348
            {field: 'tjyjtotal', title: '团奖', align: 'center', width: 150, rowspan: 2, style: 'text-align:right'}                     // 349

        ];

        var row3 = [

            {field: 'obj01', title: '对象1', align: 'center', width: 150},    // 22
            {field: 'obj02', title: '对象2', align: 'center', width: 150},    // 23
            {field: 'obj03', title: '对象3', align: 'center', width: 150},    // 24
            {field: 'obj04', title: '对象4', align: 'center', width: 150},    // 25
            {field: 'obj05', title: '对象5', align: 'center', width: 150},    // 26
            {field: 'obj06', title: '对象6', align: 'center', width: 150},    // 27
            {field: 'obj07', title: '对象7', align: 'center', width: 150},    // 28
            {field: 'obj08', title: '对象8', align: 'center', width: 150},    // 29
            {field: 'obj09', title: '对象9', align: 'center', width: 150},    // 30
            {field: 'obj10', title: '对象10', align: 'center', width: 150},    // 31
            {field: 'obj11', title: '对象11', align: 'center', width: 150},    // 32
            {field: 'obj12', title: '对象12', align: 'center', width: 150},    // 33
            {field: 'obj13', title: '对象13', align: 'center', width: 150},    // 34
            {field: 'obj14', title: '对象14', align: 'center', width: 150},    // 35
            {field: 'obj15', title: '对象15', align: 'center', width: 150},    // 36
            {field: 'obj16', title: '对象16', align: 'center', width: 150},    // 37
            {field: 'obj17', title: '对象17', align: 'center', width: 150},    // 38
            {field: 'obj18', title: '对象18', align: 'center', width: 150},    // 39
            {field: 'obj19', title: '对象19', align: 'center', width: 150},    // 40
            {field: 'obj20', title: '对象20', align: 'center', width: 150},    // 41

        ];

        var cols = [];
        cols.push(row1);
        cols.push(row2);
        cols.push(row3);
        return cols;
    }

    var active = {
        reload: function () {
            if (true == valid()) {
                var optionsData = {};
                optionsData.organization = $("#orgYear").val();                                        // 架构年份
                optionsData.accountProjectNos = formSelects.value('accountProject', 'valStr');    // 核算主体
                optionsData.regionCodes = formSelects.value('region', 'valStr');                    // HBL归属区域
                optionsData.areaCityCodes = formSelects.value('areaCity', 'valStr');                // HBL归属板块
                optionsData.cityIds = formSelects.value('city', 'valStr');                           // HBL归属城市
                optionsData.centerIds = formSelects.value('centerGroup', 'valStr');                 // HBL归属中心

                optionsData.project = $("#project").val();

                optionsData.reportId = $("#reportId").val();
                optionsData.dealDateStart = $("#dealDateStart").val();
                optionsData.dealDateEnd = $("#dealDateEnd").val();

                reloadData(optionsData);
            }
        },

        export: function () {
            if (true == valid()) {
                var param = {};

                param.organization = $("#orgYear").val();                                        // 架构年份
                param.accountProjectNos = formSelects.value('accountProject', 'valStr');    // 核算主体
                param.regionCodes = formSelects.value('region', 'valStr');                    // HBL归属区域
                param.areaCityCodes = formSelects.value('areaCity', 'valStr');                // HBL归属板块
                param.cityIds = formSelects.value('city', 'valStr');                           // HBL归属城市
                param.centerIds = formSelects.value('centerGroup', 'valStr');                 // HBL归属中心
                param.project = $("#project").val();
                param.reportId = $("#reportId").val();
                param.dealDateStart = $("#dealDateStart").val();
                param.dealDateEnd = $("#dealDateEnd").val();

                var loadIndex = parent.layer.load(2, {shade: [0.1]});

                var url = BASE_PATH + "/rptQTLinkDetail/export";
                restPost(url, param,
                    function (data) {
                        parent.layer.close(loadIndex);
                        if (data.returnCode == 200) {
                            window.location.href = data.returnData;
                        } else {
                            parent.msgAlert(data.returnMsg);
                        }
                    },
                    function (data) {
                        parent.layer.close(loadIndex);
                        parent.msgAlert(data.returnMsg);
                    }
                );
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
            url: BASE_PATH + '/rptQTLinkDetail/queryList',
            cols: setCols(),
            where: optionsData,
            page: {
                curr: 1 //重新从第 1 页开始
            },
            done: function (res, curr, count) {
                layer.close(index);
            }
        });
    }

    function valid() {

        var organization = $("#orgYear").val();                               // 架构年份

        var regionCodes = formSelects.value('region', 'valStr');           // HBL归属区域
        var areaCityCodes = formSelects.value('areaCity', 'valStr');       // HBL归属板块
        var cityIds = formSelects.value('city', 'valStr');                  // HBL归属城市
        var centerIds = formSelects.value('centerGroup', 'valStr');        // HBL归属中心

        if (false == checkComm(organization, areaCityCodes, regionCodes, cityIds)) {
            return false;
        }

        return true;
    }

});