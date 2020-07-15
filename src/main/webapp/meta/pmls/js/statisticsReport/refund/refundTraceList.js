var formSelects;
var form;
var weekInfoListObj;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;
    form = layui.form;
    formSelects = layui.formSelects;

    laydate.render({
        elem: '#yearMonth',
        type: 'month',
        trigger: 'click',
        format: 'yyyy-MM'
        ,min:'2020-01-01'
        //,max:$("#year").val() + "-" +$("#month").val() +"-01"
        //,value: $("#year").val() + "-" +$("#month").val()   //初始值
        ,done: function (value, date) {
            console.log("dateDone:"+date.year+"&"+date.month);
            $("#switchYear").val(date.year);
            $("#switchMonth").val(date.month);
            //初始化-周
            initWeek(date.year,date.month);
        }
    });

    init();
    fileInterval();
    function init() {
        /*$("#switchYear").val($("#year").val());
        $("#switchMonth").val(parseInt($("#month").val()));
        initWeek($("#switchYear").val(),$("#switchMonth").val());*/  //初始值
        form.render();
        form.render('select');
        formSelects.render();

    }

    function tableRender() {
        table.render({
            elem: '#mainTable'
            , cols: setCols()
            , id: 'contentReload'
            , page: true
            , height: "full"
            , limits: [10, 20, 30]
            , limit: 10 //默认采用60
            , even: false //开启隔行背景
            , method: 'post'
            , loading: true
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
        var cols = [];
        cols.push(getRow1(weekInfoListObj));
        cols.push(getRow2(weekInfoListObj));
        cols.push(getRow3(weekInfoListObj));
        return cols;
    }
    
    function getRow1(weekInfoListObj) {
        var row1 = [];
        row1.push({type: 'numbers', title: '序号', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A2', title: '城市', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A3', title: '类型', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A4', title: '项目名称', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A5', title: '合作方', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A6', title: '合作方简称', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A7', title: '开发商', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A8', title: '开发商简称', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A9', title: '所属大客户', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A10', title: '累计应计收入', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A11', title: '累计应收收入', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A12', title: '累计应计实收', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A13', title: '累计财务实收', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A14', title: '累计实际返佣', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A15', title: '累计实际垫佣', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A16', title: '累计垫佣已回款', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A17', title: '截止19年12月31日应计未回账龄合计', align: 'center', width: 260, colspan: 1, rowspan: 3});
        row1.push({field: 'A18', title: '1-3个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A19', title: '4-6个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A20', title: '7-9个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A21', title: '10-12个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A22', title: '一年以上', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A23', title: '应计未回账龄合计', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A24', title: '1-3个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A25', title: '4-6个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A26', title: '7-9个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A27', title: '10-12个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A28', title: '一年以上', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A29', title: '应计未回且未转应收账龄合计', align: 'center', width: 230, colspan: 1, rowspan: 3});
        row1.push({field: 'A30', title: '1-3个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A31', title: '4-6个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A32', title: '7-9个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A33', title: '10-12个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A34', title: '一年以上', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A35', title: '应收未回账龄合计', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A36', title: '1-3个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A37', title: '4-6个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A38', title: '7-9个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A39', title: '10-12个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A40', title: '一年以上', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A41', title: '垫佣未回账龄合计', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A42', title: '1-3个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A43', title: '4-6个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A44', title: '7-9个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A45', title: '10-12个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A46', title: '一年以上', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A47', title: '开票未回账龄合计', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A48', title: '1-3个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A49', title: '4-6个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A50', title: '7-9个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A51', title: '10-12个月', align: 'center', width: 150, colspan: 1, rowspan: 3});
        row1.push({field: 'A52', title: '一年以上', align: 'center', width: 150, colspan: 1, rowspan: 3});

        var month = 0;
        $.each(weekInfoListObj, function (n, value) {
            if(month!=value.month){
                month = value.month;
                var yjTitle = value.month+'月预计回款';
                var yjColspan = 1+(value.monthWeekNum*3);
                var sjTitle = value.month+'月实际回款';
                var sjColspan = 1+(value.monthWeekNum*3);
                row1.push({field: '', title: yjTitle, align: 'center', width: 150, colspan: yjColspan, rowspan: 1});
                row1.push({field: '', title: sjTitle, align: 'center', width: 150, colspan: sjColspan, rowspan: 1});
            }
        });
        row1.push({field: 'schemeMemo', title: '回款难点及解决措施', align: 'center', width: 180, colspan: 1, rowspan: 3});
        return row1;
    }

    function getRow2(weekInfoListObj) {
        var row2 = [];
        var month = 0;

        var yjRow2 = [];
        var sjRow2 = [];
        $.each(weekInfoListObj, function (n, value) {
            if(month!=value.month){
                row2 = row2.concat(yjRow2).concat(sjRow2);
                yjRow2 = [];
                sjRow2 = [];
                var yjFieldMonth = 'yj_monthSum'+value.month;
                var sjFieldMonth = 'sj_monthSum'+value.month;
                var yjTitleMonth = value.month+'月预计回款合计';
                var sjTitleMonth = value.month+'月实际回款合计';
                yjRow2.push({field: yjFieldMonth, title: yjTitleMonth, align: 'center', width: 150, colspan: 1, rowspan: 2});
                sjRow2.push({field: sjFieldMonth, title: sjTitleMonth, align: 'center', width: 150, colspan: 1, rowspan: 2});

                month = value.month;
            }

            var weekTitle = value.weekStartDateStr+'-'+value.weekEndDateStr;
            yjRow2.push({field: '', title: weekTitle, align: 'center', width: 150, colspan: 3, rowspan: 1});
            sjRow2.push({field: '', title: weekTitle, align: 'center', width: 150, colspan: 3, rowspan: 1});
        });
        row2 = row2.concat(yjRow2).concat(sjRow2);
        return row2;
    }

    function getRow3(weekInfoListObj) {
        var row3 = [];
        var month = 0;

        var yjRow3 = [];
        var sjRow3 = [];
        $.each(weekInfoListObj, function (n, value) {
            if(month!=value.month){
                row3 = row3.concat(yjRow3).concat(sjRow3);
                yjRow3 = [];
                sjRow3 = [];
                month = value.month;
            }

            var yj_weekSum_field = 'yj_weekSum'+value.month+'_'+value.weekNo;
            var yj_xjAccount_field = 'yj_xjAccount'+value.month+'_'+value.weekNo;
            var yj_dfAccount_field = 'yj_dfAccount'+value.month+'_'+value.weekNo;
            yjRow3.push({field: yj_weekSum_field, title: '合计', align: 'center', width: 150, colspan: 1, rowspan: 1});
            yjRow3.push({field: yj_xjAccount_field, title: '现金', align: 'center', width: 150, colspan: 1, rowspan: 1});
            yjRow3.push({field: yj_dfAccount_field, title: '抵房', align: 'center', width: 150, colspan: 1, rowspan: 1});


            var sj_weekSum_field = 'sj_weekSum'+value.month+'_'+value.weekNo;
            var sj_xjAccount_field = 'sj_xjAccount'+value.month+'_'+value.weekNo;
            var sj_dfAccount_field = 'sj_dfAccount'+value.month+'_'+value.weekNo;
            sjRow3.push({field: sj_weekSum_field, title: '合计', align: 'center', width: 150, colspan: 1, rowspan: 1});
            sjRow3.push({field: sj_xjAccount_field, title: '现金', align: 'center', width: 150, colspan: 1, rowspan: 1});
            sjRow3.push({field: sj_dfAccount_field, title: '抵房', align: 'center', width: 150, colspan: 1, rowspan: 1});
        });
        row3 = row3.concat(yjRow3).concat(sjRow3);
        return row3;

    }
    
    
    
    var active = {
        reload: function () {
            if (valid()) {
                var optionsData = getParamData();
                getYearAllWeek(optionsData.year);
                reloadData(optionsData);
            }
        },
        export: function () {
            if (valid()) {
                var param = getParamData();
                //var loadIndex = parent.layer.load(2, {shade: [0.1]});

                var url = BASE_PATH + "/pmlsRefundTraceController/export";
                restPost(url, param,
                    function (data) {
                       /* parent.layer.close(loadIndex);
                        if (data.returnCode == 200) {
                            window.location.href = data.returnData;
                        } else {
                            parent.msgAlert(data.returnMsg);
                        }*/
                        var dataStr = data.returnMsg;
                        loading(dataStr);
                    },
                    function (data) {
                        parent.msgError(data.returnMsg);
                    }
                );

                window.setTimeout(function () {
                    $("#btnExport").attr("disabled", false);
                }, 10000);
            }
        }
    }


    function loading(dataStr) {
        var load = layer.msg(dataStr, {
            icon: 16,
            shade: 0.4,
            time: false  //取消自动关闭
        });

        // window.setTimeout(function () {
        //     layer.close(load);
        // }, 5000);//5秒后关闭加载层
    }

    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
        //var index = parent.layer.load(2);
        tableRender();
        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsRefundTraceController/queryList',
            //data: getData(),
            cols: setCols(),
            where: optionsData,
            page: {
                curr: 1 //重新从第 1 页开始
            },
            done: function (res, curr, count) {
                parent.layer.closeAll();
            }
        });
    }

    function valid() {
        var yearMonth = $('#yearMonth').val();
        var weekNo = $("#weekNo").val();

        if(isBlank(yearMonth)){
            parent.layer.alert("请选择截止日期!", {icon: 2, title: '提示'});
            return false;
        }

        if(isBlank(weekNo)){
            parent.layer.alert("请选择关账周!", {icon: 2, title: '提示'});
            return false;
        }
        return true;
    }

    function getParamData() {
        var param = {};
        param.cityNo = formSelects.value('cityNo', 'valStr');
        param.project = $("#project").val();
        param.yearMonth = $("#yearMonth").val();
        param.year = $("#switchYear").val();
        param.month = $("#switchMonth").val();
        param.weekNo = $("#weekNo").val();
        param.sortNo = $('#weekNo').find("option:selected").attr("tagSortNo");
        return param;
    }



    //初始化-周
    function initWeek(year,month){
        console.log("intWeek:"+year+"&"+month)
        var url = BASE_PATH + "/pmlsPerformSwitchWeek/getWeeks";
        var params ={year:year,month:month};
        ajaxGetSync(url, params, function (data) {
            var result = "<option value=''>请选择</option>";
            $.each(data.returnData, function (n, value) {
                if(n==0){
                    result += "<option value='" + value.weekNo + "' tagSortNo='" + value.sortNo + "' selected>第"+ value.weekNo + "周</option>";
                }else {
                    result += "<option value='" + value.weekNo + "' tagSortNo='" + value.sortNo + "'>第"+ value.weekNo + "周</option>";
                }
            });
            $("#weekNo").html('');
            $("#weekNo").append(result);
            form.render();
        }, function () {
        });
    }

    //初始化-周
    function getYearAllWeek(year){
        console.log("intWeek:"+year)
        var url = BASE_PATH + "/pmlsPerformSwitchWeek/getWeeks";
        var params ={year:year};
        ajaxGetSync(url, params, function (data) {
            var result = "<option value=''>请选择</option>";
            weekInfoListObj = data.returnData;
        }, function () {
        });
    }



    function fileInterval() {
        var url = BASE_PATH + '/expendReport/fileIntervalByNameCSV/' + "linkProjectPartAWeek_filepath";

        $.ajax({
            type: "GET",
            url: url,
            async: true,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                var filePath = data.filePath;
                var userReportSize = data.userReportSize;
                console.log("filePath=" + filePath+"###userReportSize="+userReportSize);
                if (filePath != undefined && filePath != null && filePath != ''
                    && filePath.length>5 && (filePath.substring(0,4)=='http' || filePath.substring(0,5)=='https')) {
                    layer.closeAll();
                    $("#reportSize").text(data.userReportSize);
                    window.location.href = filePath;
                    if ("0" == userReportSize) {
                        $("#exportMsg").hide();
                    } else {
                        $("#exportMsg").show();
                    }
                }
            },
            error: function () {
            }
        });

        window.setTimeout(fileInterval, 30000);

    };

});