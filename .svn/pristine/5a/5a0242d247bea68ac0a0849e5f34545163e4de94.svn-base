layui.use(['element', 'laydate', 'layedit', 'form', 'table', 'layer', 'upload'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        layedit = layui.layedit,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        upload = layui.upload,
        $ = layui.$;


    var uploadWatchImg = [], uploadRoughtImg = [], uploadPartFirstImg = [];
    if (reportInfo != null && reportInfo.fileList != null && reportInfo.fileList.length > 0) {
        for (var i = 0; i < reportInfo.fileList.length; i++) {
            if (reportInfo.fileList[i].fileTypeId == '1022') {
                uploadWatchImg.push(reportInfo.fileList[i]);
            } else if (reportInfo.fileList[i].fileTypeId == '1023') {
                uploadRoughtImg.push(reportInfo.fileList[i]);
            } else if (reportInfo.fileList[i].fileTypeId == '1024') {
                uploadPartFirstImg.push(reportInfo.fileList[i]);
            }
        }
    }

    init();

    function init() {
        loadImageList("uploadWatchImg", uploadWatchImg, false);
        loadImageList("uploadRoughtImg", uploadRoughtImg, false)
        loadImageList("uploadPartFirstImg", uploadPartFirstImg, false)
    }


    var active = {
        pass: function () {
            if (checkApprove() == true) {
                passToDialog();
            }

        }
        , refuse: function () {
            refuseToDialog();
        }
        , goback: function () {
            parent.redirectTo('delete', null, null);
        }
    }

    $(".layui-btn").on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });

    function passToDialog() {
        parent.layer.open({
            type: 2
            , title: '收入类型'
            , content: BASE_PATH + '/sceneTrade/roughtAuditPassPopup'
            , area: ['600px', '210px']
            , btn: ['确定', '取消']
            , scrollbar: false
            , resize: false
            , yes: function (index, layero) {

                //确认的回调
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                var formData = iframeWin.getFormData();
                if (formData != null) {
                    var inComeStatus = formData.inComeStatus;

                    var params = {
                        "id": $("#id").val(),
                        "roughAuditStatus": "1",
                        "roughInputDate": $("#roughInputDate").val(),
                        "reportId": $("#reportId").val(),
                        "companyNo": $("#companyNo").val(),
                        "detailId": $("#detailId").val(),
                        "inComeStatus": inComeStatus
                    }
                    parent.layer.close(index);

                    /** 2020-05-20 begin  大定审核根据规则获取最新总控比例
                     * (如果有存到报备表lnk_report，isGlobalControl和dyRatio是什么就存什么,
                     * 如果没有存lnk_report  isGlobalControl 为0-房控  dyRatio为0)
                     */
                        //大定日期 2020-05-09 00:00:00
                    var roughInputDate = $('#roughInputDate').val();
                    //项目编号
                    var projectNo = $('#projectNo').val();
                    //报备编号
                    var reportId = $('#reportId').val();
                    var dyRatioParams = {
                        "projectNo": projectNo,
                        "reportId": reportId,
                        "roughInputDate": roughInputDate
                    }
                    getNewDyRatio(dyRatioParams);
//                    return;
                    //end 
                    // 提交审核通过
                    submitToService(params)
                }
            }
        });
    }

    function getNewDyRatio(dyRatioParams) {
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        var url = BASE_PATH + "/sceneTrade/getNewDyRatio";
        restPost(url, dyRatioParams,
            function (data) {
                parent.layer.close(loadIndex);
            },
            function (data) {
                parent.layer.close(loadIndex);
                parent.msgAlert(data.returnMsg);
            })

    }

    function refuseToDialog() {
        parent.layer.open({
            type: 2
            , title: '意见填写'
            , content: BASE_PATH + '/sceneTrade/roughtAuditRefusePopup'
            , area: ['600px', '270px']
            , scrollbar: false
            , resize: false
            , btn: ['确定', '取消']
            , yes: function (index, layero) {

                //确认的回调
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                var formData = iframeWin.getFormData();

                if (formData != null) {
                    var txtOpinion = formData.txtOpinion;
                    var params = {
                        "id": $("#id").val(),
                        "roughAuditStatus": "2",
                        "roughAuditDesc": txtOpinion
                    }
                    parent.layer.close(index);
                    // 提交审核拒绝
                    submitToService(params)
                }

            }
        });
    }

    function checkApprove() {
        var customerFromHide = $("#customerFromHide").val();
        var id = $("#id").val()
        if (17405 == customerFromHide) {
            var flag = 0;
            var retMsg = "门店未绑定经纪公司，请驳回通知业务人员绑定!";
            var yfUrl = BASE_PATH + '/sceneTrade/getYHApproveCheckArteryType/' + id;
            var loadIndex = parent.layer.load(2, {shade: [0.1]});
            $.ajax({
                type: "GET",
                url: yfUrl,
                async: false,
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    parent.layer.close(loadIndex);
                    if (data.returnCode == '200') {
                        var returnData = data.returnData;
                        retMsg = data.returnMsg;
                        if (returnData == 1) {
                            flag = 1;
                        } else {
                            parent.msgAlert(retMsg);
                        }
                    }
                },
                error: function () {
                    parent.layer.close(loadIndex);
                    parent.msgAlert("操作发生异常，请联系管理人员！");
                }
            });

            if (flag != 1) {
                return false;
            }
        }

        //passFlag为1，大定日期处于关帐月份
        var passFlag = $("#passFlag").val();
        if (passFlag == '1') {
            parent.msgAlert("大定日期已关账，请驳回让驻场人员修改!");
            return false;
        }
        //为1表示未立项
        var isApproval = $("#isApproval").val();
        if (isApproval == '1') {
            parent.msgAlert("该项目OA未立项，不允许大定审核通过！");
            return false;
        }
        var reportId = $("#reportId").val()
        if (false == checkReportCooperation(reportId)) {
            return false;
        }
        var buildingNo = $("#buildingNo").val();
        var flag = auditBuildingNoRepeatCount(buildingNo, reportId, 2);
        if (!flag) {
            return false;
        }

        return true;
    }

    function checkReportCooperation(reportId) {
        var flag = false;
        var url = BASE_PATH + '/sceneTrade/checkReportCooperation/' + reportId;
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        $.ajax({
            type: "GET",
            url: url,
            async: false,
            dataType: "json",
            contentType: 'application/json',
            success: function (data) {
                parent.layer.close(loadIndex);
                if (data.returnCode == '200') {
                    flag = true;
                } else {
                    flag = false;
                    parent.msgError(data.returnMsg);
                }
            },
            error: function () {
                parent.layer.close(loadIndex);
            }
        });
        return flag;
    }

    function submitToService(params) {
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        var url = BASE_PATH + "/sceneTrade/saveRoughtAudit";
        restPost(url, params,
            function (data) {
                parent.layer.close(loadIndex);
                parent.msgProp("操作成功！");
                parent.redirectTo('delete', null, null);
            },
            function (data) {
                parent.layer.close(loadIndex);
                parent.msgAlert(data.returnMsg);
            })

    }

    function auditBuildingNoRepeatCount(buildingNo, reportId, type) {
        var flag = false;
        var returnData = '';
        var url = BASE_PATH + '/sceneTrade/selectBuildingNoRepeatCount';
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        $.ajax({
            type: "POST",
            url: url,
            async: false,
            data: {buildingNo: buildingNo, reportId: reportId},
            dataType: "json",
            success: function (data) {

                parent.layer.close(loadIndex);
                if (data.returnCode == '200') {
                    returnData = data.returnData;
                    if (returnData == undefined || returnData == null || returnData == '') {
                        flag = true;
                    }
                } else {

                }
            },
            error: function () {

                parent.layer.close(loadIndex);
            }
        });

        if (!flag) {
            msgAlert("该楼室号已报备，请审核驳回！（报备编号：" + returnData + "）");
        }
        return flag;
    }
});