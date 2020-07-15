layui.use(['element', 'laydate', 'form', 'table', 'layer', 'upload'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        upload = layui.upload,
        $ = layui.$;


    var uploadWatchImg = [];
    if (reportInfo != null && reportInfo.fileList != null && reportInfo.fileList.length > 0) {
        for (var i = 0; i < reportInfo.fileList.length; i++) {
            if (reportInfo.fileList[i].fileTypeId == '1022') {
                uploadWatchImg.push(reportInfo.fileList[i]);
            }
        }
    }

    init();

    function init() {

        var projectNo = $("#projectNo").val();

        var seeDate = $("#seeDate").val();
        if (isEmpty(seeDate)) {
            seeDate = "1900-1-1";
        }
        var nowDate = new Date().format('yyyy-MM-dd');
        var switchDate = $("#switchDate").val();
        if (isEmpty(switchDate)) {
            switchDate = "1900-1-1";
        }
        if (dateCompare(seeDate, switchDate)) {
            switchDate = seeDate;
        }
        laydate.render({
            elem: '#roughInputDate',
            type: 'date',
            format: 'yyyy-MM-dd',
            max: nowDate,
            min: switchDate,
            trigger: 'click'
        });
        form.render('select');

        form.on('radio(isWrap)', function (data) {

            $("#buildingNo").val("");
            $("#buildingNo").attr("data-buildingNoId", "");
            $("#roughArea").val("");
            //$("#roughAmount").val("");

            if (data.value == '1') {
                $("#divbuildingNo").show();
                $("#roughArea").attr("readonly", true);
                $("#buildingNo").attr("readonly", true);
                $("#roughArea").attr("disabled", true);
                $("#buildingNo").attr("disabled", true);

                $("#buildingNo").val($("#newChange_buildingNo").val());
                $("#buildingNo").attr("data-buildingNoId", $("#newChange_buildingNoId").val());
                $("#roughArea").val($("#newChange_roughArea").val());
            }
            // 选择-否
            else {
                $("#divbuildingNo").hide();
                $("#roughArea").removeAttr("readonly");
                $("#buildingNo").removeAttr("readonly");
                $("#roughArea").removeAttr("disabled");
                $("#buildingNo").removeAttr("disabled");
            }
        });

        loadImageList("uploadWatchImg", uploadWatchImg, true);
    }

    form.render('select'); // 刷新单选框渲染


    var active = {
        save: function () {
            if (valid() == true) {
                var optionsData = {};
                optionsData.id = $("#id").val();
                optionsData.reportId = $("#reportId").val();
                optionsData.estateId = $("#estateId").val();
                optionsData.customerNm = trimStr($("#customerNm").val());
                optionsData.customerTel = $("#customerTel").val();
                optionsData.customerNmTwo = trimStr($("#customerNmTwo").val());
                optionsData.customerTelTwo = $("#customerTelTwo").val();

                // 是否包销房源
                var isWrap = $("input:radio[name='isWrap']:checked").val()
                optionsData.isWrap = isWrap;
                // 楼室号
                optionsData.buildingNo = trimStr($("#buildingNo").val());
                optionsData.buildingNoId = $("#buildingNo").attr("data-buildingNoId");

                optionsData.wyTypeCode = $("#ddlWYType").val(); // 物业类型

                optionsData.roughArea = $("#roughArea").val();
                optionsData.roughAmount = $("#roughAmount").val();
                optionsData.operateDate = $("#roughInputDate").val();

                //获取上传图片的id
                var fileIds = '';
                $(".layui-upload .item_img").each(function () {
                    fileIds += $(this).data("id") + ',';
                });
                if (fileIds != '') {
                    fileIds = fileIds.substring(0, fileIds.length - 1);
                }

                optionsData.fileRecordMainIds = fileIds;

                submitToService(optionsData)
            }
        }
        , cancel: function () {
            parent.redirectTo('delete', null, null);
        }
        , goback: function () {
            parent.redirectTo('delete', null, null);
        }
        , selectBuildingNo: function () {
            parent.layer.open({
                type: 2,
                title: '选择楼室号',
                area: ['800px', '660px'],
                content: BASE_PATH + '/sceneTrade/selectBuildingNoPage/' + $("#projectNo").val() + '/' + 0
                , scrollbar: false
                , resize: false
                , btn: ['确定', '取消']
                , yes: function (index, layero) {
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    if (formData != null) {

                        $("#buildingNo").attr("data-buildingNoId", formData.buildingNoCode);
                        $("#buildingNo").val(formData.buildingNoName);
                        $("#roughArea").val(formData.roughArea);
//                        $("#roughAmount").val(formData.roughAmount);需要手动填写

                        $("#newChange_buildingNoId").val(formData.buildingNoCode);
                        $("#newChange_buildingNo").val(formData.buildingNoName);
                        $("#newChange_roughArea").val(formData.roughArea);

                        //关闭弹出层
                        parent.layer.close(index);
                    }
                }
            });

        }
    }
    $('.layui-btn').on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });

    function valid() {
        // 客户姓名
        var customerNm = $("#customerNm").val();
        if (isEmpty(customerNm)) {
            parent.msgAlert("请输入客户姓名！")
            return false;
        }
        // 客户手机号
        var customerTel = $("#customerTel").val();
        if (isEmpty(customerTel)) {
            parent.msgAlert("请输入客户手机号！")
            return false;
        }
        if (!checkPhoneNumber(customerTel)) {
            parent.msgAlert("客户手机号不正确！")
            return false;
        }
        // 客户手机号2
        var customerNmTwo = $("#customerNmTwo").val();
        var customerTelTwo = $("#customerTelTwo").val();
        if (!isEmpty(customerNmTwo) && isEmpty(customerTelTwo)) {
            parent.msgAlert("客户已填写，客户手机必须填写！")
            return false;
        }
        if (isEmpty(customerNmTwo) && !isEmpty(customerTelTwo)) {
            parent.msgAlert("客户手机已填写，客户必须填写！")
            return false;
        }
        if (!isEmpty(customerTelTwo) && !checkPhoneNumber(customerTelTwo)) {
            parent.msgAlert("客户手机号不正确！")
            return false;
        }


        var isWrap = $("input:radio[name='isWrap']:checked").val()
        // 楼室号
        var buildingNo = $("#buildingNo").val();
        if (isEmpty(buildingNo)) {
            parent.msgAlert("请输入楼室号！")
            return false;
        }
        if (isIncludeBlank(buildingNo)) {
            parent.msgAlert("楼室号不正确！")
            return false;
        }

        var reportId = $("#reportId").val();
        var flag = buildingNoRepeatCount(buildingNo, reportId, 2);
        if (!flag) {
            return false;
        }

        var wyTypeCode = $("#ddlWYType").val(); // 物业类型
        if (isEmpty(wyTypeCode)) {
            parent.msgAlert("请选择物业类型！")
            return false;
        }

        // 大定面积
        var roughArea = $("#roughArea").val();
        if (isEmpty(roughArea)) {
            parent.msgAlert("请输入大定面积！")
            return false;
        }
        if (!checkIsNumber(roughArea)) {
            parent.msgAlert("大定面积不正确！")
            return false;
        }

        // 大定总价
        var roughAmount = $("#roughAmount").val();
        if (isEmpty(roughAmount)) {
            parent.msgAlert("请输入大定总价！")
            return false;
        }
        if (!checkIsNumber(roughAmount)) {
            parent.msgAlert("大定总价不正确！")
            return false;
        }

        // 大定日期
        var roughDate = $("#roughInputDate").val();
        if (isEmpty(roughDate)) {
            parent.msgAlert("请选择大定日期！")
            return false;
        }


        // 关账日期 <= 大定日期 <= 当前日期
        var seeDate = $("#seeDate").val();
        if (isEmpty(seeDate)) {
            seeDate = "1900-1-1";
        }
        var nowDate = new Date().format('yyyy-MM-dd');
        var switchDate = $("#switchDate").val();
        if (isEmpty(switchDate)) {
            switchDate = "1900-1-1";
        }
        if (!dateCompare(roughDate, seeDate)) {
            parent.msgAlert("大定日期不能在带看日期之前！");
            return false;
        }
        if (!dateCompare(roughDate, switchDate)) {
            parent.msgAlert("大定日期不能在关账日期之前！");
            return false;
        }
        if (!dateCompare(nowDate, roughDate)) {
            parent.msgAlert("大定日期不能在当前日期之后！");
            return false;
        }


        // 带看单
        var fileSize1 = $("#uploadWatchImg .upload_img_list .item_img").size()
        if (fileSize1 == null || fileSize1 <= 0) {
            parent.msgAlert("请上传带看单！")
            return false;
        }

        // 大定单
        var fileSize2 = $("#uploadRoughtImg .upload_img_list .item_img").size()
        if (fileSize2 == null || fileSize2 <= 0) {
            parent.msgAlert("请上传大定单！")
            return false;
        }

        // 甲方项目负责人名片
        var fileSize3 = $("#uploadPartFirstImg .upload_img_list .item_img").size()
        if (fileSize3 == null || fileSize3 <= 0) {
            parent.msgAlert("请上传甲方项目负责人名片！")
            return false;
        }
        return true;
    }

    function submitToService(optionsData) {
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        var url = BASE_PATH + "/sceneTrade/saveRought";
        restPost(url, optionsData,
            function (data) {
                parent.layer.close(loadIndex);
                if (data.returnData == 0 || data.returnData == 201 || data.returnData == 202) {
                    parent.msgAlert(data.returnMsg);
                } else {
                    parent.msgProp("操作成功！");
                    parent.redirectTo('delete', null, null);
                }
            },
            function (data) {
                parent.layer.close(loadIndex);
                parent.msgAlert(data.returnMsg);
            })
    }


    // 带看单
    uploadRender('uploadWatchImg', {fileTypeId: '1022', fileSourceId: '5', exts: 'jpg|png|jpeg|pdf'});

    // 大定单
    uploadRender('uploadRoughtImg', {fileTypeId: '1023', fileSourceId: '5', exts: 'jpg|png|jpeg|pdf'});

    // 甲方项目负责人名片
    uploadRender('uploadPartFirstImg', {fileTypeId: '1024', fileSourceId: '5', exts: 'jpg|png|jpeg|pdf'});


});

