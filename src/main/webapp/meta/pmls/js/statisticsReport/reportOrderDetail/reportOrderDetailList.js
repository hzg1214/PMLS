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
        elem: '#dateStart',
        type: 'date',
        trigger: 'click',
        format: 'yyyy-MM-dd',
        done: function (value, date, endDate) {
            var startDate = new Date(value).getTime();
            if ($('#dateEnd').val() != '') {
                var endTime = new Date($('#dateEnd').val()).getTime();
                if (endTime < startDate) {
                    layer.msg('开始时间不能大于结束时间');
                    $('#dateStart').val('');
                }
            }
        }
    });
    laydate.render({
        elem: '#dateEnd',
        type: 'date',
        trigger: 'click',
        format: 'yyyy-MM-dd',
        done: function (value, date, endDate) {
            if ($('#dateStart').val() != "") {
                var startDate = new Date($('#dateStart').val()).getTime();
                var endTime = new Date(value).getTime();
                if (endTime < startDate) {
                    layer.msg('结束时间不能小于开始时间');
                    $('#dateEnd').val('');
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
    organizationLinkAge();

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
            var flag = checkExport('reportOrderDetail_filepath');
            if(!flag){
                parent.layer.alert('您的订单明细报表正在排队生成中，不能重复导出!', {
                    icon: 2,
                    title: '提示'
                });
                return false;
            }
            if (valid()) {
                var optionsData = getParam();
                if(optionsData !=null){
	                var cookieName = guid();
	
	                url = LOC_RES_BASE_PATH + '/pmlsReportOrderDetail/exportAjax?'
	                    + "organization=" + optionsData.organization
	                    + "&serachKey=" + optionsData.serachKey
	                    + "&regionCodes=" + optionsData.regionCodes
	                    + "&areaCityCodes=" + optionsData.areaCityCodes
	                    + "&cityIds=" + optionsData.cityIds
	                    + "&centerIds=" + optionsData.centerIds
	
	                    + '&customerFromId=' + optionsData.customerFromId
	                    + "&estateId=" + optionsData.estateId
	                    + "&reportId=" + optionsData.reportId

                        + '&ywType=' + optionsData.ywType
	                    + "&dateStart=" + optionsData.dateStart
	                    + "&dateEnd=" + optionsData.dateEnd
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
        var estateId = trimStr($("#estateId").val());//项目
        var reportId = trimStr($("#reportId").val());//报备编号

        if (isIncludeBlank(estateId)) {
            parent.layer.alert('项目中间有空格，请重新输入！', {
                icon: 2,
                title: '提示'
            });
            return ;
        }
        if (isIncludeBlank(reportId)) {
            parent.layer.alert('订单编号中间有空格，请重新输入！', {
                icon: 2,
                title: '提示'
            });
            return ;
        }
        var ywType = $("#ywType").val();//日期
        var dateStart = $("#dateStart").val();//日期
        var dateEnd = $("#dateEnd").val();


        optionsData.organization = organization;
        optionsData.serachKey = serachKey;
        optionsData.accountProjectNos = accountProjectNos;
        optionsData.regionCodes = regionCodes;
        optionsData.areaCityCodes = areaCityCodes;
        optionsData.cityIds = cityIds;
        optionsData.centerIds = centerGroupIds;
        optionsData.estateId = estateId;
        optionsData.reportId = reportId;
        optionsData.customerFromId = customerFromId;
        optionsData.ywType = ywType;
        optionsData.dateStart = dateStart;
        optionsData.dateEnd = dateEnd;

        return optionsData;
    }

    function reloadData(optionsData) {
    	tableRender();
        var index = layer.load(2);
        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsReportOrderDetail/queryList',
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
            {type:'numbers',title: '序号',width:60, align: 'center',rowspan: 2},
            {field:'',title: '业绩归属',width:150, align: 'center',colspan: 16,rowspan: 1},
            {field:'',title: '基本信息',width:150, align: 'center',colspan: 17,rowspan: 1},
            {field:'',title: '报备信息',width:150, align: 'center',colspan: 3,rowspan: 1},
            {field:'',title: '带看信息',width:150, align: 'center',colspan: 3,rowspan: 1},
            {field:'',title: '大定信息',width:150, align: 'center',colspan: 3,rowspan: 1},
            {field:'',title: '成销信息',width:150, align: 'center',colspan: 3,rowspan: 1},
            {field:'',title: '收入',width:150, align: 'center',colspan: 11,rowspan: 1},
            {field:'',title: '返佣',width:150, align: 'center',colspan: 11,rowspan: 1},
            {field:'',title: '内佣',width:150, align: 'center',colspan: 2,rowspan: 1},
            {field:'',title: '其他',width:150, align: 'center',colspan: 3,rowspan: 1}
        )

        row2.push(
            {field: 'accountProjectNo', title: '核算主体编号', width: 150, align: 'center'},
            {field: 'accountProject', title: '核算主体', width: 150, align: 'center'},
            {field: 'org_regionName', title: '业绩归属区域', width: 150, align: 'center'},
            {field: 'org_areaCityName', title: '业绩归属城市', width: 150, align: 'center'},
            {field: 'cityGroupName', title: '业绩所在城市', width: 150, align: 'center'},
            {field: 'areaGroupName', title: '业绩归属事业部', width: 150, align: 'center'},
            {field: 'centerGroupName', title: '业绩归属中心', width: 150, align: 'center'},
            {field: 'groupName', title: '业绩归属组', width: 150, align: 'center'},
            {field: 'expenderName', title: '业绩归属人姓名', width: 150, align: 'center'},
            {field: 'expenderCode', title: '业绩归属人工号', width: 150, align: 'center'},
            {field: 'projectCityName', title: '项目归属城市', width: 150, align: 'center'},
            {field: 'projectDepartmentName', title: '项目归属部门', width: 150, align: 'center'},
            {field: 'projectKfName', title: '项目开发负责人', width: 150, align: 'center'},
            {field: 'projectKfCode', title: '项目开发负责人工号', width: 180, align: 'center'},
            {field: 'projectFzName', title: '项目负责人', width: 150, align: 'center'},
            {field: 'projectFzCode', title: '项目负责人工号', width: 150, align: 'center'},
            {field: 'reportNo', title: '订单编号', width: 150, align: 'center'},
            {field: 'reportStatus', title: '订单状态', width: 100, align: 'center'},
            {field: 'confirmStatus', title: '确认状态', width: 100, align: 'center'},
            {field: 'customerNm', title: '客户姓名', width: 150, align: 'center'},
            {field: 'customerTel', title: '客户手机号', width: 150, align: 'center'},
            {field: 'buildingNo', title: '楼室号', width: 150, align: 'center'},
            {field: 'projectNo', title: '项目编号', width: 150, align: 'center'},
            {field: 'estateNm', title: '楼盘名称', width: 150, align: 'center', style:'text-align: left'},
            {field: 'companyNo', title: '报备经纪公司编号', width: 150, align: 'center'},
            {field: 'companyNm', title: '报备经纪公司名称', width: 150, align: 'center', style:'text-align: left'},
            {field: 'topbrandName', title: '所属渠道名称', width: 150, align: 'center', style:'text-align: left'},
            {field: 'storeNo', title: '报备门店编号', width: 150, align: 'center'},
            {field: 'storeName', title: '报备门店名称', width: 150, align: 'center', style:'text-align: left'},
            {field: 'reportAgent', title: '报备经纪人姓名', width: 150, align: 'center'},
            {field: 'reportAgentTel', title: '经纪人手机号', width: 150, align: 'center'},
            {field: 'reportType', title: '报备类型', width: 150, align: 'center'},
            {field: 'customerFromName', title: '报备来源', width: 150, align: 'center'},
            {field: 'reportDtlStatus', title: '报备状态', width: 100, align: 'center'},
            {field: 'reportDate', title: '报备发起日期', width: 165, align: 'center', style:'text-align: left'},
            {field: 'reportAuditDate', title: '报备审核时间', width: 165, align: 'center', style:'text-align: left'},
            {field: 'seeDtlStatus', title: '带看状态', width: 100, align: 'center'},
            {field: 'seeDate', title: '带看日期', width: 150, align: 'center'},
            {field: 'seeCrtDate', title: '带看操作日期', width: 150, align: 'center'},
            {field: 'roughInputDate', title: '大定日期', width: 150, align: 'center'},
            {field: 'roughAmount', title: '大定总价', width: 150, align: 'center', style:'text-align: right'},
            {field: 'roughAuditTime', title: '大定过审日期', width: 150, align: 'center'},
            {field: 'dealDate', title: '成销日期', width: 150, align: 'center'},
            {field: 'dealAmount', title: '成销总价', width: 150, align: 'center', style:'text-align: right'},
            {field: 'area', title: '成销面积', width: 150, align: 'center', style:'text-align: right'},
            {field: 'yjsr_BefTaxAmount', title: '应计收入税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'yjsr_AftTaxAmount', title: '应计收入税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'yssr_BefTaxAmount', title: '应收收入税前', width: 150, align: 'center', style:'text-align: right'},
            {field: 'yssr_AftTaxAmount', title: '应收收入税后', width: 150, align: 'center', style:'text-align: right'},
            {field: 'yiHuiKuanAmount', title: '已回款金额', width: 150, align: 'center', style:'text-align: right'},
            {field: 'yjJiWeiHuiKuanAt', title: '应计未回款金额', width: 150, align: 'center', style:'text-align: right'},
            {field: 'yiShouWeiHuiKuanAt', title: '应收未回款金额', width: 150, align: 'center', style:'text-align: right'},
            {field: 'kpzt', title: '开票主体', width: 150, align: 'center'},
            {field: 'kpAmount', title: '开票总金额', width: 150, align: 'center'},
            {field: 'lastKpDate', title: '最后一次开票日期', width: 150, align: 'center'},
            {field: 'lastHkDate', title: '最近回款日期', width: 150, align: 'center'},
            {field: 'yjdy_BefTaxAmount', title: '应计垫佣', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sjdy_BefTaxAmount', title: '实际垫佣', width: 150, align: 'center', style:'text-align: right'},
            {field: 'shengYuDyAt', title: '剩余垫佣', width: 150, align: 'center', style:'text-align: right'},
            {field: 'yjfy_BefTaxAmount', title: '应计返佣', width: 150, align: 'center', style:'text-align: right'},
            {field: 'sjfy_BefTaxAmount', title: '实际返佣', width: 150, align: 'center', style:'text-align: right'},
            {field: 'shengYuFyAt', title: '剩余返佣', width: 150, align: 'center', style:'text-align: right'},
            {field: 'requestAmount', title: '请款总金额', width: 150, align: 'center', style:'text-align: right'},
            {field: 'lastQkApproveTime', title: '最近请款日期', width: 150, align: 'center'},
            {field: 'yiZhiFuAt', title: '已支付总金额', width: 150, align: 'center'},
            {field: 'recordDate', title: '最近支付日期', width: 150, align: 'center'},
            {field: 'shengYuWeiShouPiaoAt', title: '剩余未收票金额', width: 150, align: 'center', style:'text-align: right'},
            {field: 'yjny_postAmount', title: '应计内佣_岗位佣金', width: 150, align: 'center', style:'text-align: right'},
            {field: 'yjny_totalAmount', title: '应计内佣_团佣', width: 150, align: 'center', style:'text-align: right'},
            {field: 'pledgedBackAt', title: '认筹金额', width: 150, align: 'center', style:'text-align: right'},
            {field: 'roughBackDate', title: '最近退定日期', width: 150, align: 'center'},
            {field: 'dealBackDate', title: '最近退房日期', width: 150, align: 'center'}
        );
        var cols = [];
        cols.push(row1);
        cols.push(row2);
        return cols;
    }

    function fileInterval() {
        var url = BASE_PATH + '/expendReport/fileIntervalByName/' + "reportOrderDetail_filepath";

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
        var url = BASE_PATH + '/expendReport/queryReportSize/' + "reportOrderDetail_filepath";
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

