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

    // laydate.render({
    //     elem: '#startDate',
    //     type: 'date',
    //     trigger: 'click',
    //     format: 'yyyy-MM',
    //     min: '2017-01-01',
    //     max: $("#countDateEndStr").val(),
    //     done: function (value, date, endDate) {
    //         var startDate = new Date(value).getTime();
    //         if ($('#endDate').val() != '') {
    //             var endTime = new Date($('#endDate').val()).getTime();
    //             if (endTime < startDate) {
    //                 layer.msg('开始时间不能大于结束时间');
    //                 $('#startDate').val('');
    //             }
    //         }
    //     }
    // });
    laydate.render({
        elem: '#endDate',
        type: 'month',
        trigger: 'click',
        format: 'yyyy-MM',
        min: '2019-12-01',
        max: $("#countDateEndStr").val(),
        done: function (value, date, endDate) {
            // if ($('#startDate').val() != "") {
            //     var startDate = new Date($('#startDate').val()).getTime();
            //     var endTime = new Date(value).getTime();
            //     if (endTime < startDate) {
            //         layer.msg('结束时间不能小于开始时间');
            //         $('#endDate').val('');
            //     }
            // }
        }
    });

    fileInterval();


    form.render(); //刷新select选择框渲染
    formSelects.render();
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



    //reloadData();//初始化加载表格

    active = {
        reload: function () {
            if (valid()) {
                var optionsData = getParam();
                reloadData(optionsData);
            }
        },

        reset: function () {

        },

        export: function () {
            if (valid()) {
                var optionsData = getParam();
                var cookieName = guid();

                var url = LOC_RES_BASE_PATH + '/pmlsLinkProjectTrace/exportLinkProjectTraceAjax?'
                    + "cityNo=" + optionsData.cityNo
                    + "&projectNo=" + optionsData.projectNo
//                    + "&startDate=" + optionsData.startDate
                    + "&endDate=" + optionsData.endDate
                    + "&statisticsNum=" + optionsData.statisticsNum
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
    };


    function valid() {
        // var startDate = $("#startDate").val();
        // var endDate = $("#endDate").val();
        //
        // if(!startDate && !endDate){
        //     return true;
        // }
        //
        // if(startDate && !endDate){
        //     parent.layer.alert("结束年月必填!", {icon: 2, title: '提示'});
        //     return false;
        // }
        //
        // if(!startDate && endDate){
        //     parent.layer.alert("开始年月必填!", {icon: 2, title: '提示'});
        //     return false;
        // }
        //
        // if(startDate == endDate){
        //     parent.layer.alert("结束年月应大于开始年月!", {icon: 2, title: '提示'});
        //     return false;
        // }

        return true;
    }



    function getParam() {
        var optionsData = {};
        optionsData.projectNo = trimStr($("#projectNo").val());
        // optionsData.startDate = $("#startDate").val();
        optionsData.endDate = $("#endDate").val();
        if (isBlank(optionsData.endDate)) {
            optionsData.endDate = $("#countDateEndStr").val();
        }

        optionsData.cityNo = formSelects.value('cityNo','valStr');

        //var statisticsNum = $("input:radio[name='statisticsNum']:checked").val();
        var statisticsNum = $("#statisticsNum").val();
        if(isEmpty(statisticsNum)){
            statisticsNum = 0;
        }
        optionsData.statisticsNum = statisticsNum;

        return optionsData;
    }

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
        tableRender();
        var index =  parent.layer.load(2);
        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsLinkProjectTrace/querylinkProjectTraceList',
            cols: setCols(),
            height: window.innerHeight - $(".layui-table-view").offset().top - 30,
            where: optionsData,
            page:{
                curr: 1 //重新从第 1 页开始
            },
            done: function (res, curr, count) {
                parent.layer.close(index);
            }
        });
    }



    function setCols() {
        var row1 = [], row2 = [], row3 = [];
        var endDate = $("#endDate").val();
        if (isBlank(endDate)){
            endDate = $("#countDateEndStr").val();
        }

        row1.push(
            {field: 'cityName',fixed: true, title: '城市', width: 150, align: 'center', colspan: 1,rowspan: 3}
            ,{field: 'accountProjectNo',fixed: true, title: '核算主体编码', width: 150, align: 'center', colspan: 1,rowspan: 3}
            ,{field: 'accountProject',fixed: true, title: '核算主体', width: 150, align: 'center', colspan: 1,rowspan: 3}
            ,{field: 'qyName',fixed: true, title: '区域', width: 150, align: 'center', colspan: 1,rowspan: 3}
            ,{field: 'projectNo', title: '项目编号', width: 150, align: 'center', colspan: 1,rowspan: 3}
            ,{field: 'projectName', title: '项目名称', width: 150, align: 'center', colspan: 1,rowspan: 3}
            ,{field: 'accountProjectStr', title: '项目归属核算', width: 150, align: 'center', colspan: 1,rowspan: 3}
            ,{field: 'partnerNm', title: '甲方名称', width: 150, align: 'center', colspan: 1,rowspan: 3}
            ,{field: 'devCompany', title: '开发商名称', width: 150, align: 'center', colspan: 1,rowspan: 3}
            ,{field: 'isBigCustomer', title: '是否大客户', width: 150, align: 'center', colspan: 1,rowspan: 3}
            ,{field: 'bigCustomer', title: '大客户简称', width: 150, align: 'center', colspan: 1,rowspan: 3}
            ,{field: '', title: '概况', width: 150, align: 'center', colspan: 7,rowspan: 1}
            ,{field: '', title: '当年实际', width: 150, align: 'center', colspan: 10,rowspan: 1}
            , {field: '', title: '往年应计', width: 150, align: 'center', colspan: 5, rowspan: 1}
            ,{field: '', title: '总累计', width: 150, align: 'center', colspan: 12,rowspan: 1}
            ,{field: '', title: '回款账龄分析('+endDate+')', width: 150, align: 'center', colspan: 18,rowspan: 1}
            ,{field: '', title: '开票账龄('+endDate+')', width: 150, align: 'center', colspan: 7,rowspan: 1}

            , {field: '', title: '有垫佣的应计未回账龄(' + endDate + ')', width: 150, align: 'center', colspan: 6, rowspan: 1}
            , {field: '', title: '应计未转应收账龄(' + endDate + ')', width: 150, align: 'center', colspan: 7, rowspan: 1}
        );


        row2.push(
            {field: 'projectCrtDt', title: 'CRM建项日期', width: 150, align: 'center', colspan: 1,rowspan: 2
                ,templet:function (d) {
                    if (d.projectNo=='-'){
                        return "-";
                    }else{
                        return d.projectCrtDt
                    }
                }
            }
            ,{field: 'saleStatusNm', title: '销售状态', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'signStatusNm', title: '项目状态', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'budgetDate', title: 'OA立项完成日期', width: 150, align: 'center', colspan: 1,rowspan: 2
                ,templet:function (d) {
                    if (d.projectNo=='-'){
                        return "-";
                    }else{
                        return d.budgetDate
                    }
                }
            }
            ,{field: 'commissionConditionNm', title: '甲方结算节点', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'htDate', title: '合同周期', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'isPadCom', title: '是否垫佣', width: 150, align: 'center', colspan: 1,rowspan: 2}
            //当年实际
            ,{field: 'actRoughAmt', title: '大定金额', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'actDealAmt', title: '成销金额', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'actAtIncome', title: '税前收入', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'actRecieveIncome', title: '应收收入', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'actRealIncome', title: '应计实收', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'actAtRebate', title: '税前返佣', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'actFinanceIncome', title: '实际返佣', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'actPtNetIncome', title: '税前净收', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'actAtDyIncome', title: '税前垫佣', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'actAtSjDyIncome', title: '实际垫佣', width: 150, align: 'center', colspan: 1,rowspan: 2}
            //往年应计
            , {field: 'wnwhAmonut', title: '未回余额', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'dqhkAmonut', title: '当期回款', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'dqyjAmonut', title: '当期调整应计', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'dqhzAmonut', title: '当期调整坏账', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'dqwhAmount', title: '当期未回余额', width: 150, align: 'center', colspan: 1, rowspan: 2}
            //总累计
            ,{field: 'totalRoughNum', title: '大定套数', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'totalRoughAmt', title: '大定金额', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'totalDealNum', title: '成销套数', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'totalDealAmt', title: '成销金额', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'totalIncomesq', title: '税前收入', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'totalRecieveIncome', title: '应收收入', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'totalRecieveRealIncome', title: '应计实收', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'totalRecieveRebate', title: '应计返佣', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'totalRealRecieveRebate', title: '实际返佣', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'totalPtNetIncome', title: '税前净收', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'totalAtDyIncome', title: '税前垫佣', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'totalAtSjDyIncome', title: '实际垫佣', width: 150, align: 'center', colspan: 1,rowspan: 2}
            //回款账龄
            ,{field: '', title: '应计未回款', width: 150, align: 'center', colspan: 6,rowspan: 1}
            ,{field: '', title: '垫佣未回款', width: 150, align: 'center', colspan: 6,rowspan: 1}
            ,{field: '', title: '应收未回款（含已开票）', width: 150, align: 'center', colspan: 6,rowspan: 1}
            ,{field: 'kpAmountAll', title: '账龄合计', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'kpAmount13', title: '1-3个月', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'kpAmount46', title: '4-6个月', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'kpAmount79', title: '7-9个月', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'kpAmount1012', title: '10-12个月', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'kpAmount1324', title: '13-24个月', width: 150, align: 'center', colspan: 1,rowspan: 2}
            ,{field: 'kpAmount2499', title: '24个月以上', width: 150, align: 'center', colspan: 1,rowspan: 2}


            , {field: 'dyyjwhAll', title: '账龄合计', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'dyyjwh13', title: '1-3个月', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'dyyjwh46', title: '4-6个月', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'dyyjwh79', title: '7-9个月', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'dyyjwh1012', title: '10-12个月', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'dyyjwh1399', title: '13个月以上', width: 150, align: 'center', colspan: 1, rowspan: 2}

            , {field: 'yjysAll', title: '账龄合计', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'yjys13', title: '1-3个月', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'yjys46', title: '4-6个月', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'yjys79', title: '7-9个月', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'yjys1012', title: '10-12个月', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'yjys1324', title: '13-24个月', width: 150, align: 'center', colspan: 1, rowspan: 2}
            , {field: 'yjys2599', title: '24个月以上', width: 150, align: 'center', colspan: 1, rowspan: 2}

        );



        row3.push(
            {field: 'yjAmountAll', title: '账龄合计', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'yjAmount13', title: '1-3个月', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'yjAmount46', title: '4-6个月', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'yjAmount79', title: '7-9个月', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'yjAmount1012', title: '10-12个月', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'yjAmount1399', title: '一年以上', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'dyAmountAll', title: '账龄合计', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'dyAmount13', title: '1-3个月', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'dyAmount46', title: '4-6个月', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'dyAmount79', title: '7-9个月', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'dyAmount1012', title: '10-12个月', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'dyAmount1399', title: '一年以上', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'ysAmountAll', title: '账龄合计', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'ysAmount13', title: '1-3个月', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'ysAmount46', title: '4-6个月', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'ysAmount79', title: '7-9个月', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'ysAmount1012', title: '10-12个月', width: 150, align: 'center', colspan: 1,rowspan: 1}
            ,{field: 'ysAmount1399', title: '一年以上', width: 150, align: 'center', colspan: 1,rowspan: 1}
        );
        var cols = [];
        cols.push(row1);
        cols.push(row2);
        cols.push(row3);
        return cols;
    }


    function fileInterval() {
        var url = BASE_PATH + '/expendReport/fileIntervalByName/' + "linkProjectTrace_filepath";

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
        var url = BASE_PATH + '/expendReport/queryReportSize/' + "linkProjectTrace_filepath";
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

