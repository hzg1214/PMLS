layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        $ = layui.$;
    var currentUrl = BASE_PATH + '/businessManagerController/businessManagerList';
    var defTab = "all";
    var backTab = $("#backTab").val();

    var editPermission = NullToZero($("#editPermission").val());
    var myself_userId =  NullToZero($("#myself_userId").val());

    var all = {
        init: function () {
            // 表格数据
            this.tableRender();
        },
        tableRender: function () {
            table.render({
                elem: '#all-contentTable'
                , cols: all.setCols()
                , data: []
                , loading: false
                , id: 'all-contentReload'
                , height: "full"
                , page: true
                , limits: [10, 20, 30]
                , limit: 10 //默认采用60
                , method: 'post'
                , request: {
                    pageName: 'curPage' //页码的参数名称，默认：page
                    , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
                }
                , where: {}
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
                    field: 'companyNo', title: '编号', fixed: 'left', width: 120, align: 'center', templet: function (d) {
                        return "<a class='layui-table-link' onclick='javascript:businessManagerList.showDetail(\"" + d.id + "\")'>" + d.companyNo + "</a>";
                    }
                },
                {field: 'companyName',title: '经纪公司',fixed: 'left',width: 250,align: 'center',style: 'text-align: left'},
                {field: 'brandName', title: '品牌', width: 100, align: 'center'},
                {
                    field: 'brandType', title: '业务类型', width: 160, align: 'center', templet: function (d) {
                        var retStr;
                        //  渠道类型: 29401-分销,29402-其它
                        if (d.brandType == '29401') {
                            // 是否借佣，0-否，1-是
                            if (d.isProcuration == '1') {
                                retStr = "分销渠道、借佣渠道";
                            } else {
                                retStr = "分销渠道";
                            }
                        } else {
                            retStr = "其他";
                        }

                        return retStr;
                    }
                },
                {field: 'address', title: '注册地址', width: 250, align: 'center', style: 'text-align: left'},
                {field: 'userCreateName', title: '创建人', width: 80, align: 'center'},
                {field: 'dateCreate', title: '创建日期', width: 120, align: 'center'},
                {
                    title: '操作', align: 'left', width: 110, fixed: 'right', templet: function (d) {
                        var showContent = '';
                        showContent += '<a class="layui-btn layui-btn-xs" onclick="businessManagerList.showDetail(' + d.id + ')">查看</a>';
                        // 渠道区分：29501 - 大渠道（来源于PMLS）,29502 -非大渠道（来源于CRM'）
                        if (d.arteryType == '29501' && (editPermission == "1" || d.userCreate  == myself_userId )) {
                            showContent += '<a class="layui-btn layui-btn-xs layui-btn-normal " onclick="businessManagerList.update(' + d.id + ')">编辑</a>';
                        }
                        return showContent;
                    }
                }
            ];
            var cols = [];
            cols.push(row);
            return cols;
        },
        active: {
            reload: function () {
                if (all.valid()) {
                    var optionsData = all.getParams();
                    sessionStorage.removeItem('BUSINESS_MANAGER_SEARCH_ALL');
                    all.reloadData(optionsData);
                }
            }
            , reset: function () {
                $("#all-companyNo").val("");
                $("#all-brandName").val("");
                $("#all-brandType").val("");
                form.render('select');
                all.active.reload();
            }
        },
        reloadData: function (optionsData) {
            var index = layer.load(2, {shade: 0.1});

            var sessionData = sessionStorage.getItem('BUSINESS_MANAGER_SEARCH_ALL');

            var pageIndex = 1;
            if (sessionData != null && sessionData != '') {
                optionsData = JSON.parse(sessionData);
                pageIndex = optionsData.curr;
                $("#all-companyNo").val(optionsData.companyNo);
                $("#all-brandName").val(optionsData.brandName);
                if (optionsData.isProcuration == "1") {
                    $("#all-brandType").val(optionsData.isProcuration);
                } else {
                    $("#all-brandType").val(optionsData.brandType);
                }
                form.render('select');
            }

            table.reload('all-contentReload', {
                url: BASE_PATH + '/businessManagerController/getBusinessManagerList',
                cols: all.setCols(),
                height: window.innerHeight - $("#allTabItem .layui-table-view").offset().top - 40,
                where: optionsData,
                page: {
                    curr: pageIndex //重新从第 1 页开始
                },
                done: function (res, curr, count) {
                    layer.close(index);
                    if (!optionsData) {
                        optionsData = {};
                    }
                    optionsData.curr = curr;
                    sessionStorage.setItem("BUSINESS_MANAGER_SEARCH_ALL", JSON.stringify(optionsData));
                }
            });
        },
        valid: function () {
            return true;
        },
        getParams: function () {
            var params = {};
            params.companyNo = trimBlankStr($("#all-companyNo").val());
            params.brandName = trimBlankStr($("#all-brandName").val());
            var type = $("#all-brandType").val();
            if (type == '1') {
                params.brandType = ''
                params.isProcuration = '1'
            } else {
                params.brandType = type
                params.isProcuration = ''
            }
            return params;
        }
    };
    var channel = {
        init: function () {
            // 表格数据
            this.tableRender();
        },
        tableRender: function () {
            table.render({
                elem: '#channel-contentTable'
                , cols: channel.setCols()
                , data: []
                , loading: false
                , id: 'channel-contentReload'
                , height: "full"
                , page: true
                , limits: [10, 20, 30]
                , limit: 10 //默认采用60
                , method: 'post'
                , request: {
                    pageName: 'curPage' //页码的参数名称，默认：page
                    , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
                }
                , where: {
                    brandType: '29401'
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
                    field: 'companyNo', title: '编号', fixed: 'left',  width: 120, align: 'center', templet: function (d) {
                        return "<a class='layui-table-link' onclick='javascript:businessManagerList.showDetail(\"" + d.id + "\")'>" + d.companyNo + "</a>";
                    }
                },
                {field: 'companyName', title: '经纪公司', fixed: 'left', width: 250, align: 'center', style: 'text-align: left'},
                {field: 'brandName', title: '品牌', width: 100, align: 'center'},
                {
                    field: 'brandType', title: '业务类型', width: 160, align: 'center', templet: function (d) {
                        var retStr;
                        //  渠道类型: 29401-分销,29402-其它
                        if (d.brandType == '29401') {
                            // 是否借佣，0-否，1-是
                            if (d.isProcuration == '1') {
                                retStr = "分销渠道、借佣渠道";
                            } else {
                                retStr = "分销渠道";
                            }
                        } else {
                            retStr = "其他";
                        }

                        return retStr;
                    }
                },
                {field: 'userCreateName', title: '创建人', width: 80, align: 'center'},
                {field: 'dateCreate', title: '创建日期', width: 120, align: 'center'},
                {
                    title: '操作', align: 'left', width: 110, fixed: 'right', templet: function (d) {
                        var showContent = '';
                        showContent += '<a class="layui-btn layui-btn-xs" onclick="businessManagerList.showDetail(' + d.id + ')">查看</a>';
                        // 渠道区分：29501 - 大渠道（来源于PMLS）,29502 -非大渠道（来源于CRM'）
                        if (d.arteryType == '29501' && (editPermission == "1" || d.userCreate  == myself_userId )) {
                            showContent += '<a class="layui-btn layui-btn-xs layui-btn-normal " onclick="businessManagerList.update(' + d.id + ')">编辑</a>';
                        }
                        return showContent;
                    }
                }
            ];
            var cols = [];
            cols.push(row);
            return cols;
        },
        active: {
            reload: function () {
                if (channel.valid()) {
                    var optionsData = channel.getParams();
                    sessionStorage.removeItem('BUSINESS_MANAGER_SEARCH_CHANNEL');
                    channel.reloadData(optionsData);
                }
            }
            , reset: function () {
                $("#channel-companyNo").val("");
                $("#channel-brandName").val("");
                $("#channel-isProcuration").val("");
                form.render('select');
                channel.active.reload();
            }
            , addBusiness:function () {//新增商户
                parent.redirectTo('append', '/businessManagerController/addBusinessPage', '新建借佣渠道');
            }
        },
        reloadData: function (optionsData) {
            var index = layer.load(2, {shade: 0.1});

            if (!optionsData) {
                var optionsData = {};
                optionsData.brandType = '29401';
            }
            var sessionData = sessionStorage.getItem('BUSINESS_MANAGER_SEARCH_CHANNEL');
            var pageIndex = 1;
            if (sessionData != null && sessionData != '') {
                optionsData = JSON.parse(sessionData);
                pageIndex = optionsData.curr;
                $("#channel-companyNo").val(optionsData.companyNo);
                $("#channel-brandName").val(optionsData.brandName);
                $("#channel-isProcuration").val(optionsData.isProcuration);
                form.render('select');
            }

            table.reload('channel-contentReload', {
                url: BASE_PATH + '/businessManagerController/getBusinessManagerList',
                cols: channel.setCols(),
                height: window.innerHeight - $("#channelTabItem .layui-table-view").offset().top - 40,
                where: optionsData,
                page: {
                    curr: pageIndex //重新从第 1 页开始
                },
                done: function (res, curr, count) {
                    layer.close(index);
                    if (!optionsData) {
                        optionsData = {};
                    }
                    optionsData.curr = curr;
                    sessionStorage.setItem("BUSINESS_MANAGER_SEARCH_CHANNEL", JSON.stringify(optionsData));
                }
            });


        },
        valid: function () {
            return true;
        },
        getParams: function () {
            var params = {};
            params.companyNo = trimBlankStr($("#channel-companyNo").val());
            params.brandName = trimBlankStr($("#channel-brandName").val());
            params.brandType = '29401';
            params.isProcuration = $("#channel-isProcuration").val();
            return params;
        }

    };

    // 画面初始化
    initialization();

    function initialization() {
        var sessionData_all = sessionStorage.getItem('BUSINESS_MANAGER_SEARCH_ALL');
        var sessionData_channel = sessionStorage.getItem('BUSINESS_MANAGER_SEARCH_CHANNEL');

        all.init();         // 大定待审核
        channel.init();          // 大定已审核

        act = {
            all: function () {
                all.reloadData()
            },
            channel: function () {
                channel.reloadData()
            }
        }

        element.on("tab(businessTab)", function (data) {
            var type = "all";
            if (data.index == 0) {
                type = "all";
            }
            else if (data.index == 1) {
                type = "channel";
            }

            act[type] ? act[type].call(this) : '';
            var url = currentUrl + "?backTab=" + type;
            parent.breadcrumbArray[parent.breadcrumbArray.length - 1].url = url;

        });

        // 所有公司
        $('#allTabItem .layui-btn').on('click', function () {
            var type = $(this).data('type');
            all.active[type] ? all.active[type].call(this) : '';
        });

        // 渠道公司
        $('#channelTabItem .layui-btn').on('click', function () {
            var type = $(this).data('type');
            channel.active[type] ? channel.active[type].call(this) : '';
        });

        var tab = isEmpty(backTab) ? defTab : backTab;
        element.tabChange('businessTab', tab);
    }

});

var businessManagerList = {};

//查看
businessManagerList.showDetail = function (id) {
    var url = ctx + "/businessManagerController/businessDetailPage?id=" + id;
    parent.redirectTo('append', url, '公司详情');
}
//修改
businessManagerList.update = function (id) {
    var url = ctx + "/businessManagerController/addBusinessPage?id=" + id;
    parent.redirectTo('append', url, '编辑借佣渠道');
}
