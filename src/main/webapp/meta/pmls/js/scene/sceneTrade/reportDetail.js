var reportDetailsJsonDto = {};
var logInfoJsonDto = {};
var brokerageJsonDto = {};
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        $ = layui.$;

    init();


    function init() {
        if (window.reportDetails != '') {
            //reportDetailsJsonDto = eval('(' + window.reportDetails + ')');
            reportDetailsJsonDto = eval('(' + window.reportDetails.replace(/[\r\n]/g,"\\n") + ')');
        }
        if (window.logInfo != '') {
            //logInfoJsonDto = eval('(' + window.logInfo + ')');
            logInfoJsonDto = eval('(' + window.logInfo.replace(/[\r\n]/g,"\\n") + ')');
        }
        if (window.brokerageInfo != '') {
            //brokerageJsonDto = eval('(' + window.brokerageInfo + ')');
            brokerageJsonDto = eval('(' + window.brokerageInfo.replace(/[\r\n]/g,"\\n") + ')');
        }
        var reportId = $("#reportId").val();

        // 进度
        reportProgressTableRender(reportDetailsJsonDto);

        // 日志
        reportLogTableRender(logInfoJsonDto);

        //收入&返佣
        statistcsBrokerageTableRender(brokerageJsonDto);

        // 佣金明细
        yjTableRender();

    }

    function reportProgressTableRender(reportDetailsJsonDto) {
        var newArray = [];
        var latestProgress = $("#latestProgress").val();
        var confirmStatus = $("#confirmStatus").val();
        var brokerageStatus = $("#brokerageStatus").val();
        var brokerageYm = $("#brokerageYm").val();
        var brokerageUptEmpNm = $("#brokerageUptEmpNm").val();
        var brokerageUptDt = $("#brokerageUptDt").val();
        if (reportDetailsJsonDto != null && reportDetailsJsonDto.length > 0) {
            for (var i = 0; i < reportDetailsJsonDto.length; i++) {
                if (reportDetailsJsonDto[i].progress != '13503') {
                    newArray.push(reportDetailsJsonDto[i]);
                    if (reportDetailsJsonDto[i].progress == '13504' || reportDetailsJsonDto[i].progress == '13505') {
                        $("#reportLastDetailId").val(reportDetailsJsonDto[i].id);
                    }
                    if (reportDetailsJsonDto[i].progress == '13504') {
                        $("#modFlagControl").val(reportDetailsJsonDto[i].modFlagControl);
                    }
                }
            }
        }
        //结佣  添加数据
        if (latestProgress == '13505' && confirmStatus == '13601' &&
            (brokerageStatus == '22002' || brokerageStatus == '22003')) {
            var reportDetail = {};
            reportDetail.progressNm = '结佣';
            reportDetail.confirmStatusNm = '有效';
            reportDetail.bizOccurDate = brokerageYm;
            reportDetail.uptEmpName = brokerageUptEmpNm;
            reportDetail.followDateDisPlay = brokerageUptDt;
            reportDetail.progress = '13507';
            reportDetail.brokeragePageFlag = '1';//结佣标志
            newArray.push(reportDetail);
        }

        table.render({
            elem: '#reportProgressTable'
            , cols: setReportProgressCols()
//            , url: BASE_PATH + '/sceneTrade/report/' + reportId + '/progress'
            , data: newArray
            , height: 'full'
            , id: 'progressReload'
            , even: false //开启隔行背景
            , page: false
            , limits: [10, 20, 30]
            , limit: 1000 //默认采用60
            , method: 'post'
            , request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
        });
    }

    //日志
    function reportLogTableRender(logInfoJsonDto) {
        table.render({
            elem: '#reportLogTable'
            , cols: setReportLogCols()
            , data: logInfoJsonDto
            , id: 'logReload'
            , even: false //开启隔行背景
            , page: false
            , limits: [10, 20, 30]
            , limit: 1000 //默认采用60
            , method: 'post'
            , request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
        });
    }

    //进度
    function setReportProgressCols() {

        var row = [];
        row = [
            {field: 'progressNm', title: '进度', width: 120, align: 'center'},
            {field: 'confirmStatusNm', title: '确认状态', width: 120, align: 'center'},
            {
                field: 'bizOccurDate', title: '业务节点发生时间', width: 250, align: 'center',
                templet: function (d) {
                    if (d.bizOccurDate == null || d.bizOccurDate == "") {
                        return "";
                    } else {
                        return formatDate(d.bizOccurDate, "yyyy-MM-dd");
                    }
                }
            },
            {field: 'uptEmpName', title: '操作人', width: 160, align: 'center'},
            {
                field: 'followDateDisPlay', title: '操作时间', width: 300, align: 'center',
                templet: function (d) {
                    if (d.followDateDisPlay == null || d.followDateDisPlay == "") {
                        return "";
                    } else {
                        return formatDate(d.followDateDisPlay, "yyyy-MM-dd hh:mm:ss");
                    }
                }
            },
            {
                title: '操作', align: 'left',
                templet: function (row) {
                    var showContent = '';
                    if (row.brokeragePageFlag == '1') {//结佣
                        showContent += '<a class="layui-btn layui-btn-xs" onclick="toOperDetail(' + row.id + ',' + row.progress + ',' + row.brokeragePageFlag + ')">详情</a>';
                    } else {
                        showContent += '<a class="layui-btn layui-btn-xs" onclick="toOperDetail(' + row.id + ',' + row.progress + ')">查看</a>';
                        if (row.progress == '13504' || row.progress == '13505') {
                            var confirmStatus = $("#confirmStatus").val();
                            var customerFrom = $("#customerFrom").val();
                            var roughAuditStatus = $("#roughAuditStatus").val();
                            var dataSwitch = $("#dataSwitch").val();
                            var brokerageStatus = $("#brokerageStatus").val();
                            var reportId = $("#reportId").val();
                            var estateId = $("#estateId").val();
                            var companyId = $("#companyId").val();
                            var customerId = $("#customerId").val();
                            var relateId = $("#relateId").val();

                            if ((row.progress == '13504' && confirmStatus != '13602' //大定、不等于无效
                                    && (customerFrom == '17402' || customerFrom == '17401')//报备来源：app和crm
                                    && roughAuditStatus == '2')) {//大定审核状态，0待审核，1审核通过，2审核驳回
                                showContent += "<a class='layui-btn layui-btn-xs' onclick='updateDdDetail(\"" + reportId + "\")'>修改</a>";
                            }
                            if (row.progress == '13505' &&
                                brokerageStatus == '22001' && dataSwitch == '0') {//成销、为结佣、未关帐
                                showContent += "<a class='layui-btn layui-btn-xs' onclick='updateCxDetail(\"" + reportId + "\")'>修改</a>";
                            }
                        }
                    }
                    return showContent; //列渲染
                }
            }
        ]
        var cols = [];
        cols.push(row);
        return cols;
    }

    function setReportLogCols() {
        var row = [
            {type: 'numbers', title: '序号', width: 120, align: 'center'},
            {field: 'changeContent', title: '描述', width: 550, align: 'left'},
            {
                field: 'createUserName', title: '操作人(工号)', width: 180, align: 'center',
                templet: function (d) {
                    var userName = d.createUserName == null ? "" : d.createUserName;
                    var userCode = d.createUserCode == null ? "" : d.createUserCode;
                    return userName + "(" + userCode + ")";
                }
            },
            {
                field: 'createDate', title: '操作时间', align: 'center',
                templet: function (d) {
                    if (d.createDate == null || d.createDate == "") {
                        return "";
                    } else {
                        return formatDate(d.createDate, "yyyy-MM-dd hh:mm:ss");
                    }
                }
            }
        ]
        var cols = [];
        cols.push(row);
        return cols;
    }

    //收入&返佣
    function statistcsBrokerageTableRender(brokerageJsonDto) {
        var reportId = $("#reportId").val();
        table.render({
            elem: "#statistcsBrokerageTable"
            , cols: setBrokerageColunm()
            // , url: BASE_PATH + '/sceneTrade/getStatistcsBrokerage/' + reportId
            , data: brokerageJsonDto
            , id: "statistcsBrokerageContent"
            , height: "full"
            , even: false //开启隔行背景
            , page: false
            , limits: [10, 20, 30]
            , limit: 1000 //默认采用60
            , method: 'post'
            , request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
        });
    }

    function setBrokerageColunm() {
        var row = [
            {
                field: 'yssrAmount', title: '应收收入', width: 150, align: 'center',
                templet: function (d) {
                    var yssrAmount = formatCurrency(d.yssrAmount);
                    return yssrAmount;
                }
            },
            {
                field: 'yjssAmount', title: '应计实收', width: 150, align: 'center',
                templet: function (d) {
                    var yjssAmount = formatCurrency(d.yjssAmount);
                    return yjssAmount;
                }
            },
            {
                field: 'yswhAmount', title: '应收未回', width: 150, align: 'center',
                templet: function (d) {
                    var yswhAmount = formatCurrency(d.yswhAmount);
                    return yswhAmount;
                }
            },
            {
                field: 'yjfyAmount', title: '应计返佣', width: 150, align: 'center',
                templet: function (d) {
                    var yjfyAmount = formatCurrency(d.yjfyAmount);
                    return yjfyAmount;
                }
            }, {
                field: 'sjfyAmount', title: '实际返佣', width: 150, align: 'center',
                templet: function (d) {
                    var sjfyAmount = formatCurrency(d.sjfyAmount);
                    return sjfyAmount;
                }
            }, {
                field: 'yjwfAmount', title: '应计未返', width: 150, align: 'center',
                templet: function (d) {
                    var yjwfAmount = formatCurrency(d.yjwfAmount);
                    return yjwfAmount;
                }
            }
        ];
        var cols = [];
        cols.push(row);
        return cols;
    }

    // 佣金明细
    function yjTableRender() {
        var reportId = $("#reportId").val();
        var yjList = [];
        yjList.push({
            elem: '#yjsrTable',
            id: 'yjsrReload',
            url: BASE_PATH + '/sceneTrade/getYJtableList/YJSR/' + reportId,
            flag: 0
        }); // 应计收入
        yjList.push({
            elem: '#yssrTable',
            id: 'yssrReload',
            url: BASE_PATH + '/sceneTrade/getYJtableList/YSSR/' + reportId,
            flag: 0
        }); // 应收收入
        yjList.push({
            elem: '#yjssTable',
            id: 'yjssReload',
            url: BASE_PATH + '/sceneTrade/getYJtableList/YJSS/' + reportId,
            flag: 0
        }); // 应计实收
        yjList.push({
            elem: '#yjfyTable',
            id: 'yjfyReload',
            url: BASE_PATH + '/sceneTrade/getYJtableList/YJFY/' + reportId,
            flag: 1
        }); // 应计返佣
        yjList.push({
            elem: '#sjfyTable',
            id: 'sjfyReload',
            url: BASE_PATH + '/sceneTrade/getYJtableList/SJFY/' + reportId,
            flag: 1
        }); // 实际返佣
        yjList.push({
            elem: '#yjdyTable',
            id: 'yjdyReload',
            url: BASE_PATH + '/sceneTrade/getYJtableList/YJDY/' + reportId,
            flag: 1
        }); // 应计垫佣
        yjList.push({
            elem: '#sjdyTable',
            id: 'sjdyReload',
            url: BASE_PATH + '/sceneTrade/getYJtableList/SJDY/' + reportId,
            flag: 1
        }); // 实际垫佣

        for (var i = 0; i < yjList.length; i++) {
            var item = yjList[i];

            table.render({
                elem: item.elem
                , cols: setYJColunm(item.flag)
                , url: item.url
                , id: item.id
                , height: "full"
                , loading: true
                , method: 'post'
                , limit: 1000
                , totalRow: true
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

    }

    function setYJColunm(flag) {
        var row = [];
        row.push({type: 'numbers', title: '序号', width: 60, totalRowText: '合计'});
        if (flag == 1) {
            row.push({
                field: 'companyName', title: '经纪公司', width: 200, align: 'center', style: 'text-align:left'
            });
        }
        row.push({
            field: 'befTaxAmount', title: '税前', width: 150, align: 'center', totalRow: true,
            templet: function (d) {
                var befTaxAmount = isEmpty(d.befTaxAmount) ? "" : formatCurrency(d.befTaxAmount);
                return befTaxAmount;
            }
        });
        row.push({
            field: 'aftTaxAmount', title: '税后', width: 150, align: 'center', totalRow: true,
            templet: function (d) {
                var aftTaxAmount = isEmpty(d.aftTaxAmount) ? "" : formatCurrency(d.aftTaxAmount);
                return aftTaxAmount;
            }
        });
        row.push({
            field: 'recordDate', title: '日期', width: 120, align: 'center', totalRow: false,
            templet: function (d) {
                if (d.recordDate == null) {
                    return "-";
                } else {
                    return formatDate(d.recordDate, "yyyy-MM-dd");
                }
            }
        });
        row.push({field: 'crtEmpName', title: '创建人', width: 120, align: 'center', totalRow: false});
        row.push({
            field: 'crtDt', title: '创建时间', width: 180, align: 'center', totalRow: false,
            templet: function (d) {
                if (d.crtDt == null) {
                    return "-";
                } else {
                    return formatDate(d.crtDt, "yyyy-MM-dd hh:mm:ss");
                }
            }
        });

        var cols = [];
        cols.push(row);
        return cols;
    }


});

/**
 * 大定修改
 */
function updateDdDetail(reportId) {
    var url = ctx + "/sceneTrade/roughtUpt/" + reportId
    parent.redirectTo('append', url, '大定修改');
}

/**
 * 成销修改
 */
function updateCxDetail(reportId) {
    var url = ctx + "/sceneTrade/succSaleUpt/" + reportId
    parent.redirectTo('append', url, '成销修改');
}

//查看报备
function toOperDetail(id, progress, brokeragePageFlag) {
    var reportId = $('#id').val();
    var reportLastDetailId = $('#reportLastDetailId').val();
    var reportCode = $('#reportId').val();
    var fyFlag = 0;
    var url = "";
    var title = "";
    if (progress == '13501') {
        title = "报备";
    } else if (progress == '13502') {
        title = "带看";
    } else if (progress == '13504') {
        title = "大定";
    } else if (progress == '13505') {
        title = "成销";
        fyFlag = 1;
    } else if (progress == '13507') {
        title = "结佣";
        fyFlag = 2;
    }
    if (!isBlank(brokeragePageFlag)) {//结佣
        url = ctx + '/sceneTrade/toOperDetail?reportId=' + reportId + '&reportDetailId=' + reportLastDetailId + '&fyFlag=' + fyFlag + '&reportCode=' + reportCode + '&brokeragePage=' + 22003
    } else {
        url = ctx + '/sceneTrade/toOperDetail?reportId=' + reportId + '&reportDetailId=' + id + '&fyFlag=' + fyFlag + '&reportCode=' + reportCode + '&brokeragePage=' + 22001
    }
    parent.layer.open({
        type: 2,
//        btn: ['确认', '取消'],
        title: title,

        area: ['900px', '620px'],
        content: url,
        yes: function (index, layero) {
        }
    });

}

//取消返回上一个页面
function goBack() {
    parent.redirectTo('delete', null, null);
}

//客户信息调整
function openDialogEditCustomer() {
    var reportId = $("#reportId").val();
    var estateId = $("#estateId").val();
    var companyId = $("#companyId").val();
    var customerId = $("#customerId").val();
    var relateId = $("#relateId").val();
    var customerNm = $("#customerNm").val();
    var customerTel = $("#customerTel").val();
    var customerNmTwo = $("#customerNmTwo").val();
    var customerTelTwo = $("#customerTelTwo").val();
    parent.layer.open({
        type: 2,
        btn: ['保存', '取消'],
        title: '客户信息调整',
        area: ['600px', '500px'],
        content: ctx + '/sceneTrade/openDialogEditCustomer?reportId=' + reportId + "&estateId=" + estateId
        + "&companyId=" + companyId + "&customerId=" + customerId + "&relateId=" + relateId + "&customerNm=" + customerNm
        + "&customerTel=" + customerTel + "&customerNmTwo=" + customerNmTwo + "&customerTelTwo=" + customerTelTwo,
        yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getCustomerFormData();

            console.log(formData);
            if (formData != null) {
                parent.layer.close(index);//关闭弹窗
                parent.layer.load(2);
                $.ajax({
                    url: ctx + '/sceneTrade/saveCustomerInfoAdjut',
                    type: 'post',
                    data: formData,
                    dataType: 'json',
                    success: function (data) {
                        parent.layer.closeAll();
                        console.log(data);
                        if (data.returnCode != 200) {
                            parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
                        } else {
                            parent.layer.alert(data.returnMsg, {icon: 1, title: '提示'}, function () {
                                parent.layer.closeAll();
                                var backurl = BASE_PATH + "/sceneTrade/reportDetail/" + estateId + '/' + companyId + '/' + customerId + '/' + relateId;
                                window.location = backurl;
                            });
                        }
                    }
                });
            }
        }
    });
}

//退房解锁
function openDialogUnlockBack() {
    var reportId = $("#id").val();
    var estateId = $("#estateId").val();
    var companyId = $("#companyId").val();
    var customerId = $("#customerId").val();
    var relateId = $("#relateId").val();
    parent.layer.open({
        type: 2,
        btn: ['保存', '取消'],
        title: '退房解锁',
        area: ['500px', '308px'],
        content: ctx + '/sceneTrade/toUnlockReback?reportId=' + reportId + "&estateId=" + estateId
        + "&companyId=" + companyId + "&customerId=" + customerId + "&relateId=" + relateId,
        yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getUnlockBackData();
            console.log(formData);
            if (formData != null) {
                parent.layer.close(index);//关闭弹窗
                parent.layer.load(2);
                $.ajax({
                    url: ctx + '/sceneTrade/unlockReback',
                    type: 'post',
                    data: formData,
                    dataType: 'json',
//                    processData:false,
                    success: function (data) {
                        parent.layer.closeAll();
                        console.log(data);
                        if (data.returnCode != 200) {
                            parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
                        } else {
                            parent.layer.alert(data.returnMsg, {icon: 1, title: '提示'}, function () {
                                parent.layer.closeAll();
                                var backurl = BASE_PATH + "/sceneTrade/reportDetail/" + estateId + '/' + companyId + '/' + customerId + '/' + relateId;
                                window.location = backurl;
                            });
                        }
                    }
                });
            }
        }
    });
}

//业绩调整
function openDialogEditAchieve() {
    var reportId = $("#reportId").val();
    var customerNm = $("#customerNm").val();//客户
    var customerTel = $("#customerTel").val();

    var fyCenterId = $("#fyCenterId").val();
    var fyCenterName = $("#fyCenterName").val();//客户
    var htedition = $("#htedition").val();
    var branchId = $("#branchId").val();

    var contactId = $("#contactId").val();//联系人
    var contactNm = $("#contactNm").val();
    var centerGroupId = $("#centerGroupId").val();//原来所属中心
    var centerGroupName = $("#centerGroupName").val();

    var projectNo = $("#projectNo").val();
    var companyNo = $("#companyNo").val();

    var estateId = $("#estateId").val();
    var companyId = $("#companyId").val();
    var customerId = $("#customerId").val();
    var relateId = $("#relateId").val();
    var auditDate = $("#auditDate").val();//配置时间
    var currDate = $("#currDate").val();//当前时间
    var cooperFlag = $("#cooperFlag").val();//是否有分销合同
    var modFlagControl = $("#modFlagControl").val();//是否已关帐  1是 0否
    var customerFrom = $("#customerFrom").val();//友房通
    var auditDateFlag = $("#auditDateFlag").val();//当前时间是否在配置时间内 1 在  -1不在
    if (modFlagControl != null && modFlagControl != undefined && modFlagControl == 1) {
        layer.alert('该报备房源大定日期已关账，不允许调整业绩信息！', {icon: 2, title: '提示'});
        return;
    }
//    if(!tabDate(auditDate,currDate)){//不在配置时间内
//    	if(cooperFlag==0){//没有分销合同
//    		layer.alert('请先新建经纪公司的分销合同！', {icon: 2, title: '提示'});
//    		return;
//    	}
//    }
    //判断时间
    if (auditDateFlag == -1) {//不在配置时间内
        if (cooperFlag == 0) {//没有分销合同
            layer.alert('请先新建经纪公司的分销合同！', {icon: 2, title: '提示'});
            return;
        }
    }
//    if (17405 != customerFrom) {
    parent.layer.open({
        type: 2,
        btn: ['保存', '取消'],
        title: '业绩调整',
        area: ['700px', '550px'],
        content: ctx + '/sceneTrade/openDialogEditAchieve?reportId=' + reportId + "&estateId=" + estateId
        + "&companyId=" + companyId + "&customerId=" + customerId + "&relateId=" + relateId + "&customerNm=" + customerNm
        + "&customerTel=" + customerTel + "&contactId=" + contactId + "&contactNm=" + contactNm + "&centerGroupId=" + centerGroupId
        + "&centerGroupName=" + centerGroupName + "&fyCenterId=" + fyCenterId + "&fyCenterName=" + fyCenterName
        + "&htedition=" + htedition + "&branchId=" + branchId + "&companyNo=" + companyNo + "&projectNo=" + projectNo
        + "&auditDate=" + auditDate + "&currDate=" + currDate + "&cooperFlag=" + cooperFlag,
        yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getAchieveFormData();

            console.log(formData);
            if (formData != null) {
                parent.layer.close(index);//关闭弹窗
                parent.layer.load(2);
                $.ajax({
                    url: ctx + '/sceneTrade/saveAchievementAdjut',
                    type: 'post',
                    data: formData,
                    dataType: 'json',
                    success: function (data) {
                        parent.layer.closeAll();
                        console.log(data);
                        if (data.returnCode != 200) {
                            parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
                        } else {
                            parent.layer.alert(data.returnMsg, {icon: 1, title: '提示'}, function () {
                                parent.layer.closeAll();
                                var backurl = BASE_PATH + "/sceneTrade/reportDetail/" + estateId + '/' + companyId + '/' + customerId + '/' + relateId;
                                window.location = backurl;
                            });
                        }
                    }
                });
            }
        }
    });
//    } else {
//        layer.alert('报备来源于有房通的数据，如需调整业绩信息，需先在CRM中维护联动人员中心，然后在友房通后台更新业绩信息!', {icon: 2, title: '提示'});
//    }
}





//退房解锁
function uptPreBack(id,preBack) {
    var estateId = $("#estateId").val();
    var companyId = $("#companyId").val();
    var customerId = $("#customerId").val();
    var relateId = $("#relateId").val();
    var reportId = $("#reportId").val();
    var confirmMsg = "";
    if(preBack=='1'){
        confirmMsg = "确认对记录进行预退操作吗？";
    }else {
        confirmMsg = "确认对记录进行取消预退操作吗？";
    }
    parent.layer.confirm(confirmMsg,{icon: 3, title:'提示'},function(){
        parent.layer.load(2);
        var url = BASE_PATH + "/sceneTrade/uptPreBack/"+id+"/"+preBack+"/"+reportId;
        $.ajax({
            type: "POST",
            url: url,
            contentType: 'application/json;charset=utf-8', //设置请求头信息
            dataType: "json",
            success: function (data) {
                parent.layer.closeAll();
                console.log(data);
                if (data.returnCode != 200) {
                    parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
                } else {
                    parent.layer.alert(data.returnMsg, {icon: 1, title: '提示'}, function () {
                        parent.layer.closeAll();
                        var backurl = BASE_PATH + "/sceneTrade/reportDetail/" + estateId + '/' + companyId + '/' + customerId + '/' + relateId;
                        window.location = backurl;
                    });
                }
            },
            error: function () {
                parent.layer.closeAll();
                parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'});
            }
        });
    });


}
