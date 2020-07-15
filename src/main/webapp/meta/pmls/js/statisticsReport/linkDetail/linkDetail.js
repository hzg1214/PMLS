var formSelects;
var form;
layui.use(['element', 'laydate','table', 'form', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;
    form = layui.form;
    formSelects = layui.formSelects;

    laydate.render({
        elem: '#reportDateStart',
        type: 'date',
        trigger: 'click',
        format: 'yyyy-MM-dd',
        done: function (value, date, endDate) {
            var startDate = new Date(value).getTime();
            if ($('#reportDateEnd').val() != '') {
                var endTime = new Date($('#reportDateEnd').val()).getTime();
                if (endTime < startDate) {
                    layer.msg('开始时间不能大于结束时间');
                    $('#reportDateStart').val('');
                }
            }
        }
    });
    laydate.render({
        elem: '#reportDateEnd',
        type: 'date',
        trigger: 'click',
        format: 'yyyy-MM-dd',
        done: function (value, date, endDate) {
            if ($('#reportDateStart').val() != "") {
                var startDate = new Date($('#reportDateStart').val()).getTime();
                var endTime = new Date(value).getTime();
                if (endTime < startDate) {
                    layer.msg('结束时间不能小于开始时间');
                    $('#reportDateEnd').val('');
                }
            }
        }
    });

    laydate.render({
        elem: '#roughDateStart',
        type: 'date',
        trigger: 'click',
        format: 'yyyy-MM-dd',
        done: function (value, date, endDate) {
            var startDate = new Date(value).getTime();
            if ($('#roughDateEnd').val() != '') {
                var endTime = new Date($('#roughDateEnd').val()).getTime();
                if (endTime < startDate) {
                    layer.msg('开始时间不能大于结束时间');
                    $('#roughDateStart').val('');
                }
            }
        }
    });
    laydate.render({
        elem: '#roughDateEnd',
        type: 'date',
        trigger: 'click',
        format: 'yyyy-MM-dd',
        done: function (value, date, endDate) {
            if ($('#roughDateStart').val() != "") {
                var startDate = new Date($('#roughDateStart').val()).getTime();
                var endTime = new Date(value).getTime();
                if (endTime < startDate) {
                    layer.msg('结束时间不能小于开始时间');
                    $('#roughDateEnd').val('');
                }
            }
        }
    });

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

    var defaultYear = new Date().getFullYear();

    //初始化-查询维度
    initSerachKeyList();
    //初始化-核算主体
    initAccountProject();

    // 组织架构
    organizationLinkAge()

    fileInterval();

    //初始化-查询维度
    function initSerachKeyList() {
        var result = [];
        var allInitSerachKeyList = [];
        result.push({"name": '业绩', "value": 'YJ'});
        result.push({"name": '收入', "value": 'SR'});
        allInitSerachKeyList.push('YJ');
        allInitSerachKeyList.push('SR');
        formSelects.data('serachKey', 'local', {arr: result});
        formSelects.on('serachKey', function (id, vals, val, isAdd, isDisabled) {
            var cityIds = [];
            if (isAdd) {
                vals.push(val);
            }
            if (vals.length > 0) {
                for (var i = 0; i < vals.length; i++) {
                    if (isAdd == true || vals[i].value != val.value) {
                        cityIds.push(vals[i].value)
                    }
                }
            }
            if (!isBlank(cityIds)) {
                initSearchKey(cityIds.join());
            }
        });
        formSelects.btns('serachKey', [
            {
                icon: "xm-iconfont icon-quanxuan",
                name: '全选',
                click: function (id) {
                    //点击后的操作
                    layui.formSelects.value(id, allInitSerachKeyList);
                    initSearchKey(allInitSerachKeyList.join());
                }
            }, {
                icon: "xm-iconfont icon-qingkong",
                name: '清空',
                click: function (id) {
                    formSelects.value(id, []);
                    formSelects.undisabled("centerGroup");
                }
            }
        ]);
        var defaultYjValue = "YJ,业绩";
        formSelects.value('serachKey', defaultYjValue.split(','));
    }

    //如选择的包含收入，归属中心为灰色
    function initSearchKey(searchKey) {
        //  查询维度 = 业绩
        if (searchKey.search("SR") != -1) {
            // 归属中心置灰
            formSelects.disabled('centerGroup');
            // 查询维度 = 收入
        } else {
            formSelects.undisabled("centerGroup");
        }
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
                if(optionsData !=null){
                	reloadData(optionsData);
                }
            }
        },
        export: function () {
            var flag = checkExport('linkDetail_filepath');
            if(!flag){
                parent.layer.alert('您的联动明细报表正在排队生成中，不能重复导出!', {
                    icon: 2,
                    title: '提示'
                });
                return false;
            }
            if (valid()) {
                var optionsData = getParam();
                if(optionsData !=null){
	                var cookieName = guid();
	
	                url = LOC_RES_BASE_PATH + '/pmlsLinkDetail/exportLinkDetailAjax?'
	                    + "organization=" + optionsData.organization
	                    + "&serachKey=" + optionsData.serachKey
	                    + "&regionCodes=" + optionsData.regionCodes
	                    + "&areaCityCodes=" + optionsData.areaCityCodes
	                    + "&cityIds=" + optionsData.cityIds
	                    + "&centerIds=" + optionsData.centerIds
	
	                    + '&customerFromId=' + optionsData.customerFromId
	                    + "&estateId=" + optionsData.estateId
	                    + "&estateNm=" + optionsData.estateNm
	                    + "&reportId=" + optionsData.reportId
	                    + "&storeNo=" + optionsData.storeNo
	
	                    + "&reportDateStart=" + optionsData.reportDateStart
	                    + "&reportDateEnd=" + optionsData.reportDateEnd
	                    + "&roughDateStart=" + optionsData.roughDateStart
	                    + "&roughDateEnd=" + optionsData.roughDateEnd
	                    + "&dealDateStart=" + optionsData.dealDateStart
	                    + "&dealDateEnd=" + optionsData.dealDateEnd
	                    + "&accountProjectNos=" + optionsData.accountProjectNos
	                    + "&cookieName=" + cookieName;
	                $.ajax({
	                    type: "GET",
	                    url: url,
	                    async: true,
	                    dataType: "json",
	                    contentType: 'application/json',
	                    success: function (data) {
	                        var dataStr = data.message;
	                        //parent.msgProp(dataStr);
                            layer.msg(dataStr, {
                                icon: 16,
                                shade: 0.4,
                                time:false //取消自动关闭
                            });

                        },
	                    error: function (data) {
	                        parent.msgError(data.message);
	                    }
	                });
	
	                window.setTimeout(function () {
	                    $("#btnExport").attr("disabled", false);
	                }, 10000);
                }
            }
        },
        import: function () {

        }
    };

    //校验
    function valid() {
        var organization = $("#orgYear").val();
        var areaCityCodes = getXMSelectOutIds(formSelects.value('areaCity'));//归属城市
        var region = getXMSelectOutIds(formSelects.value('region'));//归属区域
        var city = getXMSelectOutIds(formSelects.value('city'));//所在城市
        return checkComm(organization, areaCityCodes, region, city);
    }

    //获取参数
    function getParam() {
        var optionsData = {};
        var organization = $("#orgYear").val();
        var serachKey = getXMSelectOutIds(formSelects.value('serachKey'));//业绩、收入
        var accountProjectNos = getXMSelectOutIds(formSelects.value('accountProject'));//核算主体
        var regionCodes = getXMSelectOutIds(formSelects.value('region'));//区域
        var areaCityCodes = getXMSelectOutIds(formSelects.value('areaCity'));//归属城市
        var cityIds = getXMSelectOutIds(formSelects.value('city'));//所在城市
        var centerGroupIds = getXMSelectOutIds(formSelects.value('centerGroup'));//归属中心
        var customerFromId = $("#customerFromId").val();//报备来源
        var estateId = trimStr($("#estateId").val());//项目编号
        var estateNm = trimStr($("#estateNm").val());//楼盘名称
        var reportId = trimStr($("#reportId").val());//报备编号
        var storeNo = trimStr($("#storeNo").val());//门店编号
        if (isIncludeBlank(estateId)) {
            parent.layer.alert('项目编号中间有空格，请重新输入！', {
                icon: 2,
                title: '提示'
            });
            return ;
        }
        if (isIncludeBlank(estateNm)) {
            parent.layer.alert('楼盘名称中间有空格，请重新输入！', {
                icon: 2,
                title: '提示'
            });
            return ;
        }
        if (isIncludeBlank(reportId)) {
            parent.layer.alert('报备编号中间有空格，请重新输入！', {
                icon: 2,
                title: '提示'
            });
            return ;
        }
        if (isIncludeBlank(storeNo)) {
            parent.layer.alert('门店编号中间有空格，请重新输入！', {
                icon: 2,
                title: '提示'
            });
            return ;
        }
        var reportDateStart = $("#reportDateStart").val();//报备日期
        var reportDateEnd = $("#reportDateEnd").val();
        var roughDateStart = $("#roughDateStart").val();//大定日期
        var roughDateEnd = $("#roughDateEnd").val();
        var dealDateStart = $("#dealDateStart").val();//成销日期
        var dealDateEnd = $("#dealDateEnd").val();

        optionsData.organization = organization;
        optionsData.serachKey = serachKey;
        optionsData.accountProjectNos = accountProjectNos;
        optionsData.regionCodes = regionCodes;
        optionsData.areaCityCodes = areaCityCodes;
        optionsData.cityIds = cityIds;
        optionsData.centerIds = centerGroupIds;
        optionsData.customerFromId = customerFromId;
        optionsData.estateId = estateId;
        optionsData.estateNm = estateNm;
        optionsData.reportId = reportId;
        optionsData.storeNo = storeNo;

        optionsData.reportDateStart = reportDateStart;
        optionsData.reportDateEnd = reportDateEnd;
        optionsData.roughDateStart = roughDateStart;
        optionsData.roughDateEnd = roughDateEnd;
        optionsData.dealDateStart = dealDateStart;
        optionsData.dealDateEnd = dealDateEnd;

        return optionsData;
    }

    function reloadData(optionsData) {
    	tableRender();
        var index = layer.load(2);
        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsLinkDetail/queryLinkDetailList',
            cols: setCols(),
            //height: window.innerHeight - $(".layui-table-view").offset().top - 30,
            where: optionsData,
            page:{
                curr: 1 //重新从第 1 页开始
            },
            done: function () {
                layer.close(index);
            }
        });
    }

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function setCols() {
        var row1 = [], row2 = [], row3 = [];
        var year = $("#orgYear").val();
        row1.push(
            {type: 'numbers', title: '序号', width: 60, align: 'center', rowspan: 3},
            {field: '', title: '业绩信息', width: 150, align: 'center', colspan: 13,rowspan: 1},
            {field: '', title: '案件信息', width: 150, align: 'center', colspan: 36,rowspan: 1},
            {field: '', title: '总累计应计', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '当年以前应计', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: year + '应计', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '1月应计', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '2月应计', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '3月应计', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '4月应计', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '5月应计', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '6月应计', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '7月应计', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '8月应计', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '9月应计', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '10月应计', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '11月应计', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '12月应计', width: 150, align: 'center', colspan: 8,rowspan: 1},

            {field: '', title: '总累计应收收入', width: 150, align: 'center', rowspan: 1, colspan: 2},
            {field: '', title: '当年以前应收收入', width: 150, align: 'center', rowspan: 1, colspan: 2},
            {field: '', title: year + '年应收收入合计', width: 150, align: 'center', rowspan: 1, colspan: 2},
            {field: '', title: year + '年应收收入', width: 150, align: 'center', colspan: 24,rowspan: 1},

            {field: '', title: '总累计实际', width: 150, align: 'center', colspan: 10,rowspan: 1},
            {field: '', title: '当年以前实际', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: year + '实际', width: 150, align: 'center', colspan: 8,rowspan: 1},

            {field: '', title: '1月实际', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '2月实际', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '3月实际', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '4月实际', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '5月实际', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '6月实际', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '7月实际', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '8月实际', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '9月实际', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '10月实际', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '11月实际', width: 150, align: 'center', colspan: 8,rowspan: 1},
            {field: '', title: '12月实际', width: 150, align: 'center', colspan: 8,rowspan: 1},

            {field: 'totalNPTCommission', title: '岗位佣金小计', width: 150, align: 'center',colspan:1, rowspan: 3, style:'text-align: right'},
            {field: 'totalNATCommission', title: '团佣小计', width: 150, align: 'center',colspan:1, rowspan: 3, style:'text-align: right'},
            {field: 'remark', title: '备注', width: 150, align: 'center',colspan:1, rowspan: 3},

            {field: '', title: '人员信息', width: 150, align: 'center', colspan: 6,rowspan:1},

            {field: 'fyObject1', title: '返佣对象一', width: 150, align: 'center', colspan:1,rowspan: 3, style:'text-align: left'},
            {field: 'befYJFY1', title: '应计返佣税前', width: 150, align: 'center',colspan:1, rowspan: 3, style:'text-align: right'},
            {field: 'aftYJFY1', title: '应计返佣税后', width: 150, align: 'center',colspan:1, rowspan: 3, style:'text-align: right'},

            {field: 'fyObject2', title: '返佣对象二', width: 150, align: 'center',colspan:1, rowspan: 3, style:'text-align: left'},
            {field: 'befYJFY2', title: '应计返佣税前', width: 150, align: 'center',colspan:1, rowspan: 3, style:'text-align: right'},
            {field: 'aftYJFY2', title: '应计返佣税后', width: 150, align: 'center',colspan:1, rowspan: 3, style:'text-align: right'},

            {field: 'fyObject3', title: '返佣对象三', width: 150, align: 'center',colspan:1, rowspan: 3, style:'text-align: left'},
            {field: 'befYJFY3', title: '应计返佣税前', width: 150, align: 'center',colspan:1, rowspan: 3, style:'text-align: right'},
            {field: 'aftYJFY3', title: '应计返佣税后', width: 150, align: 'center',colspan:1, rowspan: 3, style:'text-align: right'}
        );

        row2.push(
            {field: 'accountProjectNo', title: '核算主体编号', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'accountProject', title: '核算主体', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'regionName', title: '业绩归属区域', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'areaCityName', title: '业绩归属城市', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'cityGroupName', title: '业绩所在城市', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'areaGroupName', title: '业绩归属事业部', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'centerGroupName', title: '业绩归属中心', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'groupName', title: '业绩归属组', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'expenderName', title: '业绩归属人', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'expenderCode', title: '业绩归属人工号', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'projectCityName', title: '项目归属城市', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'projectDepartmentName', title: '项目归属部门', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'srCityName', title: '收入归属城市', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'customerFromName', title: '来源', width: 150, align: 'center', rowspan: 2,colspan: 1},

            {field: '',title: '经纪公司', width: 150, align: 'center', colspan: 8,rowspan:1},
            {field: '',title: '客户信息', width: 150, align: 'center', colspan: 3,rowspan:1},

            {field: 'estateId', title: '系统编号', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'projectNo', title: '项目编号', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'estateNm', title: '楼盘名', width: 150, align: 'center', rowspan: 2,colspan: 1, style:'text-align: left'},
            {field: 'partnerNm', title: '合作方名称', width: 150, align: 'center', rowspan: 2,colspan: 1, style:'text-align: left'},
            {field: 'bigCustomerFlagStr', title: '是否大客户', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'bigCustomerName', title: '大客户简称', width: 150, align: 'center', rowspan: 2,colspan: 1, style:'text-align: left'},

            {field: 'prepaidName', title: '是否垫佣', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'reportId', title: '报备编号', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'reportDate', title: '报备时间', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'seeDate', title: '带看时间', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'pledgedDate', title: '认筹时间', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'buildingNo', title: '楼室号', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'suitNum', title: '套数', width: 150, align: 'center', rowspan: 2,colspan: 1},
            {field: 'preBack', title: '预退', width: 150, align: 'center', rowspan: 2,colspan: 1
                ,templet: function (d) {
                    var preBackStr = "";
                    if("1"==d.preBack || 1==d.preBack){
                        preBackStr = "预退定";
                    }else if("2"==d.preBack || 2==d.preBack){
                        preBackStr = "预退房";
                    }
                    return preBackStr;
                }
            },

            {field: '', title: '大定', width: 150, align: 'center', colspan: 5,rowspan: 1},
            {field: '', title: '成销', width: 150, align: 'center', colspan: 5,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},//总累计应计
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},//当年以前应计
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},//2019应计
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},//1月应计
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '成销收入', width: 150, align: 'center', colspan: 2,rowspan: 1},//12月应计
            {field: '', title: '返佣金额', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '成销净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: 'totalRBPTIncome', title: '税前', width: 150, align: 'center',rowspan:2,colspan:1, style:'text-align: right'},//总累计应收
            {field: 'totalRBATIncome', title: '税后', width: 150, align: 'center',rowspan:2,colspan:1, style:'text-align: right'},

            {field: 'beforeRBPTIncome', title: '税前', width: 150, align: 'center',rowspan:2,colspan:1, style:'text-align: right'},//当年以前应收
            {field: 'beforeRBATIncome', title: '税后', width: 150, align: 'center',rowspan:2,colspan:1, style:'text-align: right'},

            {field: 'thisRBPTIncome', title: '税前', width: 150, align: 'center',rowspan:2,colspan:1, style:'text-align: right'},//当年应收
            {field: 'thisRBATIncome', title: '税后', width: 150, align: 'center',rowspan:2,colspan:1, style:'text-align: right'},

            {field: '', title: '1月应收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '2月应收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '3月应收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '4月应收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '5月应收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '6月应收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '7月应收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '8月应收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '9月应收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '10月应收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '11月应收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '12月应收', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2,rowspan: 1},//总累计实际
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '垫佣回款', width: 150, align: 'center', colspan: 1,rowspan: 1},
            {field: '', title: '当前仍处于垫付状态的垫佣', width: 150, align: 'center', colspan: 1,rowspan: 1},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2,rowspan: 1},//当年以前实际
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2,rowspan: 1},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2,rowspan: 1},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2},//2019实际
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2},//1月实际
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2},

            {field: '', title: '实际收入', width: 150, align: 'center', colspan: 2},//12月实际
            {field: '', title: '实际返佣', width: 150, align: 'center', colspan: 2},
            {field: '', title: '实际净收', width: 150, align: 'center', colspan: 2},
            {field: '', title: '达垫佣节点的实付垫佣', width: 150, align: 'center', colspan: 2},

            {field: '', title: '拓展维护人员', width: 150, align: 'center', colspan: 2,rowspan:1},
            {field: '', title: '拓展维护经理', width: 150, align: 'center', colspan: 2,rowspan:1},
            {field: '', title: '拓展维护总监', width: 150, align: 'center', colspan: 2,rowspan:1}
        );
        row3.push(
            {field: 'storeNo', title: '门店编号', width: 150, align: 'center'},
            {field: 'storeName', title: '门店名称', width: 150, align: 'center', style:'text-align: left'},
            {field: 'companyName', title: '公司名称', width: 150, align: 'center', style:'text-align: left'},
            {field: 'addressDetail', title: '门店地址', width: 150, align: 'center', style:'text-align: left'},
            {field: 'storeSize', title: '门店规模', width: 150, align: 'center'},
            {field: 'contractTypeName', title: '合作模式', width: 150, align: 'center'},
            {field: 'saleName', title: '业务员姓名', width: 150, align: 'center'},
            {field: 'salePhone', title: '业务员电话', width: 150, align: 'center'},

            {field: 'customerNm', title: '客户姓名', width: 150, align: 'center', style:'text-align: left'},
            {field: 'customerTel', title: '联系电话', width: 150, align: 'center'},
            {field: 'customerNum', title: '人数', width: 150, align: 'center'},

            {field: 'roughArea', title: '大定面积', width: 150, align: 'center'},
            {field: 'roughPrice', title: '大定单价', width: 150, align: 'center', style:'text-align: right'},
            {field: 'roughAmount', title: '大定总价', width: 150, align: 'center', style:'text-align: right'},
            {field: 'roughDate', title: '大定日期', width: 150, align: 'center'},
            {field: 'roughAuditTime', title: '大定审核通过日期', width: 150, align: 'center'},

            {field: 'dealArea', title: '成销面积', width: 150, align: 'center'},
            {field: 'dealPrice', title: '成销单价', width: 150, align: 'center', style:'text-align: right'},
            {field: 'dealAmount', title: '成销总价', width: 150, align: 'center', style:'text-align: right'},
            {field: 'dealDate', title: '成销日期', width: 150, align: 'center'},
            {field: 'incomeStatusStr', title: '收入类型', width: 150, align: 'center'},

            {field: 'totalEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//总累计应计
            {field: 'totalEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'totalEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'totalEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'totalEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'totalEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'totalEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'totalEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'beforeEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//当年以前应计
            {field: 'beforeEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'beforeEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'beforeEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'beforeEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'beforeEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'beforeEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'beforeEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},


            {field: 'thisEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//当年应计
            {field: 'thisEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'thisEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'thisEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'thisEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'thisEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'thisEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'thisEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},


            {field: 'janEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//1月应计
            {field: 'janEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'janEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'janEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'janEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'janEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'janEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'janEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'febEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//2月应计
            {field: 'febEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'febEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'febEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'febEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'febEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'febEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'febEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'marEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//3月应计
            {field: 'marEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'marEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'marEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'marEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'marEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'marEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'marEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'aprEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//4月应计
            {field: 'aprEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'aprEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'aprEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'aprEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'aprEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'aprEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'aprEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'mayEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//5月应计
            {field: 'mayEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'mayEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'mayEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'mayEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'mayEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'mayEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'mayEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'junEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//6月应计
            {field: 'junEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'junEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'junEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'junEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'junEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'junEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'junEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'julEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//7月应计
            {field: 'julEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'julEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'julEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'julEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'julEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'julEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'julEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'augEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//8月应计
            {field: 'augEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'augEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'augEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'augEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'augEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'augEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'augEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'sepEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//9月应计
            {field: 'sepEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sepEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sepEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sepEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sepEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sepEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sepEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'octEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//10月应计
            {field: 'octEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'octEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'octEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'octEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'octEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'octEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'octEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'novEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//11月应计
            {field: 'novEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'novEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'novEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'novEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'novEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'novEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'novEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'decEPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//12月应计
            {field: 'decEATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'decEPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'decEATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'decEPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'decEATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'decEPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'decEATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            /*{field: 'totalRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//总累计应收
            {field: 'totalRBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'beforeRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//当年以前应收
            {field: 'beforeRBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'thisRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//当年应收
            {field: 'thisRBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},*/

            {field: 'janRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//1月应收
            {field: 'janRBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'febRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//2
            {field: 'febRBBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'marRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'marRBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'aprRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'aprRBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'mayRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'mayRBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'junRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'junRBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'julRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'julRBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'augRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'augRBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'sepRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sepRBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'octRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'octRBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'novRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'novRBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'decRBPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//12月应收
            {field: 'decRBATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'totalAPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//总累计实际
            {field: 'totalAATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'totalAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'totalAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'totalAPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'totalAATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'totalAPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'totalAATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            { field: 'totalAPTReceive', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'totalAPTBalance', title: '税前', width: 250, align: 'center', style:'text-align: right'},

            {field: 'beforeAPTIncome ', title: '税前', width: 150, align: 'center', style:'text-align: right'},//当年以前实际
            {field: 'beforeAATIncome ', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'beforeAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'beforeAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'beforeAPTProfit ', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'beforeAATProfit ', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'beforeAPTPrepaid ', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'beforeAATPrepaid ', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'thisAPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//2019实际
            {field: 'thisAATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'thisAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'thisAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'thisAPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'thisAATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'thisAPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'thisAATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'janAPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//1月实际
            {field: 'janAATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'janAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'janAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'janAPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'janAATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'janAPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'janAATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'febAPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'febAATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'febAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'febAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'febAPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'febAATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'febAPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'febAATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'marAPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'marAATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'marAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'marAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'marAPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'marAATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'marAPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'marAATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'aprAPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'aprAATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'aprAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'aprAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'aprAPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'aprAATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'aprAPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'aprAATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'mayAPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'mayAATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'mayAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'mayAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'mayAPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'mayAATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'mayAPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'mayAATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'junAPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'junAATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'junAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'junAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'junAPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'junAATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'junAPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'junAATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'julAPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'julAATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'julAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'julAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'julAPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'julAATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'julAPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'julAATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'augAPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'augAATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'augAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'augAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'augAPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'augAATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'augAPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'augAATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'sepAPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sepAATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sepAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sepAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sepAPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sepAATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sepAPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sepAATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'octAPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'octAATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'octAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'octAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'octAPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'octAATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'octAPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'octAATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'novAPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'novAATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'novAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'novAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'novAPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'novAATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'novAPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'novAATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'decAPTIncome', title: '税前', width: 150, align: 'center', style:'text-align: right'},//12月实际
            {field: 'decAATIncome', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'decAPTCommission', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'decAATCommission', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'decAPTProfit', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'decAATProfit', title: '税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'decAPTPrepaid', title: '税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'decAATPrepaid', title: '税后', width: 150, align: 'center', style:'text-align: right'},

            {field: 'expenderName', title: '姓名', width: 150, align: 'center'},
            {field: 'expenderCode', title: '工号', width: 150, align: 'center'},
            {field: 'groupLeaderName', title: '姓名', width: 150, align: 'center'},
            {field: 'groupLeaderCode', title: '工号', width: 150, align: 'center'},
            {field: 'areaLeaderName', title: '姓名', width: 150, align: 'center'},
            {field: 'areaLeaderCode', title: '工号', width: 150, align: 'center'}
        );
        var cols = [];
        cols.push(row1);
        cols.push(row2);
        cols.push(row3);
        return cols;
    }

    function fileInterval() {
        var url = BASE_PATH + '/expendReport/fileIntervalByName/' + "linkDetail_filepath";

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
        var url = BASE_PATH + '/expendReport/queryReportSize/' + "linkDetail_filepath";
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

