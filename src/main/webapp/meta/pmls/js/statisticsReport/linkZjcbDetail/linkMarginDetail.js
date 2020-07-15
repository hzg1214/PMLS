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

    var defaultYear = new Date().getFullYear();

    // 组织架构
    organizationLinkAge(2018, '', '', function () {
        initCost();
    })

    //初始化-成本中心
    initCost();

    fileInterval();

    //初始化-成本中心
    function initCost() {
        var url = BASE_PATH + "/pmlsLinkMarginDetail/getCostList";
        var organization = $("#orgYear").val();
        var params = {organization: organization};
        ajaxGet(url, params, function (data) {
            var costList = data.returnValue;
            var result = [];
            var aLLCostCodes = [];
            if (costList != null && costList.length > 0) {
                for (var i = 0; i < costList.length; i++) {
                    result.push({"name": costList[i].costName, "value": costList[i].costCode});
                    aLLCostCodes.push(costList[i].costCode);
                }
            }
            formSelects.data('costCodes', 'local', {arr: result});

            formSelects.btns('costCodes', [
                {
                    icon: "xm-iconfont icon-quanxuan",
                    name: '全选',
                    click: function (id) {
                        //点击后的操作
                        layui.formSelects.value(id, aLLCostCodes);
                    }
                }, {
                    icon: "xm-iconfont icon-qingkong",
                    name: '清空',
                    click: function (id) {
                        formSelects.value(id, [])
                    }
                }
            ]);
        }, function () {
        });
    }

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

    var active = {
        reload: function () {
            if (valid()) {
                var optionsData = getParam();
                if (optionsData != null) {
                    reloadData(optionsData);
                }
            }
        },
        export: function () {
            if (valid()) {
                var kh_name = trimStr($("#kh_name").val());
                if (isIncludeBlank(kh_name)) {
                    parent.layer.alert('核算主体中间有空格，请重新输入！', {icon: 2, title: '提示'});
                    return;
                }
                var hs_name = trimStr($("#hs_name").val());
                if (isIncludeBlank(hs_name)) {
                    parent.layer.alert('考核主体中间有空格，请重新输入！', {icon: 2, title: '提示'});
                    return;
                }
                var estateNm = trimStr($("#estateNm").val());
                if (isIncludeBlank(estateNm)) {
                    parent.layer.alert('项目名称中间有空格，请重新输入！', {icon: 2, title: '提示'});
                    return;
                }
                var organization = $("#orgYear").val();
                var marginTypeCode = $("#marginTypeCode").val();

                var regionCodes = getXMSelectOutIds(formSelects.value('region'));
                var areaCityCodes = getXMSelectOutIds(formSelects.value('areaCity'));
                var costCodes = getXMSelectOutIds(formSelects.value('costCodes'));
                var cookieName = guid();

                url = LOC_RES_BASE_PATH + '/pmlsLinkMarginDetail/exportLinkMarginDetailAjax?'
                    + "organization=" + organization
                    + "&regionCodes=" + regionCodes
                    + "&areaCityCodes=" + areaCityCodes
                    + "&marginTypeCode=" + marginTypeCode
                    + "&estateNm=" + estateNm
                    + "&costCodes=" + costCodes
                    + "&kh_name=" + kh_name
                    + "&hs_name=" + hs_name
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
                            time:false //取消自动关闭
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
        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsLinkMarginDetail/queryLinkMarginDetailList',
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
        var row1 = [], row2 = [];
        row1.push(
            {type: 'numbers', title: '序号', width: 60, align: 'center', rowspan: 2},
            {field: 'kh_name', title: '考核主体', width: 150, align: 'center', rowspan: 2},
            {field: 'hs_name', title: '核算主体', width: 150, align: 'center', rowspan: 2},
            {field: 'costName', title: '成本中心', width: 150, align: 'center', rowspan: 2},
            {field: 'gsCityName', title: '项目归属城市', width: 150, align: 'center', rowspan: 2},
            {field: 'projectNo', title: '项目编号', width: 150, align: 'center', rowspan: 2},
            {field: 'projectName', title: '项目名称', width: 150, align: 'center', rowspan: 2, style: 'text-align: left'},

            {field: 'oaNo', title: '预付款单号', width: 150, align: 'center', rowspan: 2},
            {field: 'ksName', title: '客商/供应商', width: 150, align: 'center', rowspan: 2},
            {field: 'marginTypeName', title: '类型', width: 150, align: 'center', rowspan: 2},
            {
                field: 'yfhkDate', title: '预计归还日期', width: 150, align: 'center', rowspan: 2,
                templet: function (d) {
                    if (d.yfhkDate == null) {
                        return "-";
                    } else {
                        return formatDate(d.yfhkDate, "yyyy-MM-dd");
                    }
                }
            },
            {field: 'oaAmount', title: '预付款金额', width: 150, align: 'center', style: 'text-align: right', rowspan: 2},
            {
                field: 'sjfkAmountTotal',
                title: '实际付款金额',
                width: 150,
                align: 'center',
                style: 'text-align: right',
                rowspan: 2
            },

            {field: '', title: '还款金额', width: 150, align: 'center', colspan: 2},
            {
                field: 'cyAmountTotal',
                title: '未收回金额',
                width: 150,
                align: 'center',
                style: 'text-align: right',
                rowspan: 2
            },
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

        var year = $("#orgYear").val();
        row2.push(
            {field: 'hkAmountTotal', title: '正常回款', width: 130, align: 'center', style: 'text-align: right'},
            {field: 'hxAmount', title: '核销', width: 130, align: 'center', style: 'text-align: right'},
            {field: 'totalAmount', title: '总累计', width: 130, align: 'center', style: 'text-align: right'},
            {field: 'preTotal', title: year + '以前', width: 130, align: 'center', style: 'text-align: right'},
            {field: 'thisTotal', title: year + '新增', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'marginjan', title: '超3个月保证金金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginhkjan', title: '超3个月保证金回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginCbjan', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'marginfeb', title: '超3个月保证金金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginhkfeb', title: '超3个月保证金回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginCbfeb', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'marginmar', title: '超3个月保证金金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginhkmar', title: '超3个月保证金回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginCbmar', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'marginapr', title: '超3个月保证金金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginhkapr', title: '超3个月保证金回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginCbapr', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'marginmay', title: '超3个月保证金金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginhkmay', title: '超3个月保证金回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginCbmay', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'marginjun', title: '超3个月保证金金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginhkjun', title: '超3个月保证金回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginCbjun', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'marginjul', title: '超3个月保证金金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginhkjul', title: '超3个月保证金回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginCbjul', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'marginaug', title: '超3个月保证金金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginhkaug', title: '超3个月保证金回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginCbaug', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'marginsep', title: '超3个月保证金金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginhksep', title: '超3个月保证金回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginCbsep', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'marginoct', title: '超3个月保证金金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginhkoct', title: '超3个月保证金回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginCboct', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'marginnov', title: '超3个月保证金金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginhknov', title: '超3个月保证金回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginCbnov', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'},

            {field: 'margindec', title: '超3个月保证金金额', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginhkdec', title: '超3个月保证金回款', width: 150, align: 'center', style: 'text-align: right'},
            {field: 'marginCbdec', title: '资金成本', width: 130, align: 'center', style: 'text-align: right'}
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
            return;

        }
        else if (data.index == 1) {
            window.location.href = BASE_PATH + '/pmlsLinkDyDetail';
        }
    });


    //校验
    function valid() {
        var organization = $("#orgYear").val();
        if (organization == '2018') {
            var regionCodes = getXMSelectOutIds(formSelects.value('region'));
            if (!isBlank(regionCodes)) {
                var areaCitys = getXMSelectOutIds(formSelects.value('areaCity'));
                if (isBlank(areaCitys)) {
                    parent.layer.alert('归属城市必选！', {icon: 2, title: '提示'});
                    return false;
                }
            }
        }
        return true;
    }

    //获取参数
    function getParam() {
        var optionsData = {};
        var kh_name = trimStr($("#kh_name").val());
        if (isIncludeBlank(kh_name)) {
            parent.layer.alert('核算主体中间有空格，请重新输入！', {icon: 2, title: '提示'});
            return;
        }
        var hs_name = trimStr($("#hs_name").val());
        if (isIncludeBlank(hs_name)) {
            parent.layer.alert('考核主体中间有空格，请重新输入！', {icon: 2, title: '提示'});
            return;
        }
        var estateNm = trimStr($("#estateNm").val());
        if (isIncludeBlank(estateNm)) {
            parent.layer.alert('项目名称中间有空格，请重新输入！', {icon: 2, title: '提示'});
            return;
        }
        optionsData.organization = $("#orgYear").val();
        optionsData.marginTypeCode = $("#marginTypeCode").val();
        optionsData.kh_name = kh_name;
        optionsData.hs_name = hs_name;
        optionsData.estateNm = estateNm;

        var regionCodes = getXMSelectOutIds(formSelects.value('region'));
        var areaCityCodes = getXMSelectOutIds(formSelects.value('areaCity'));
        var costCodes = getXMSelectOutIds(formSelects.value('costCodes'));

        optionsData.regionCodes = regionCodes;
        optionsData.areaCityCodes = areaCityCodes;
        optionsData.costCodes = costCodes;
        return optionsData;
    }

    function fileInterval() {
        var url = BASE_PATH + '/expendReport/fileIntervalByName/' + "linkMarginDetail_filepath";

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
        var url = BASE_PATH + '/expendReport/queryReportSize/' + "linkMarginDetail_filepath";
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

