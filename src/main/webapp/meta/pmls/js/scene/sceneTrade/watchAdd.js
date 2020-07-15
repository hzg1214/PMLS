layui.use(['element', 'laydate', 'form', 'table', 'layer', 'upload'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        upload = layui.upload,
        $ = layui.$;

    init();

    function init() {
        var nowDate = new Date().format('yyyy-MM-dd');
        var switchDate = $("#switchDate").val();
        if (isEmpty(switchDate)) {
            switchDate = "1900-1-1";
        }

        laydate.render({
            elem: '#seeDate',
            type: 'date',
            format: 'yyyy-MM-dd',
            max: nowDate,
            //min: switchDate,
            trigger: 'click'
        });
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
                optionsData.operateDate = $("#seeDate").val();

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
        },
        cancel: function () {
            parent.redirectTo('delete', null, null);
        },

        goback: function () {
            parent.redirectTo('delete', null, null);
        },
    }

    $('.layui-btn').on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });


    function valid() {

        // 客户姓名
        var customerNm = $("#customerNm").val();
        if (isEmpty(customerNm)) {
            parent.msgAlert("请输入客户姓名！");
            return false;
        }
        // 客户手机号
        var customerTel = $("#customerTel").val();
        if (isEmpty(customerTel)) {
            parent.msgAlert("请输入客户手机号！");
            return false;
        }
        if (!checkPhoneNumber(customerTel)) {
            parent.msgAlert("客户手机号不正确！");
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
            parent.msgAlert("客户手机号不正确！");
            return false;
        }

        // 带看日期
        var seeDate = $("#seeDate").val();
        if (isEmpty(seeDate)) {
            parent.msgAlert("请选择带看日期！");
            return false;
        }

        // 关账日期 <= 带看日期 <= 当前日期
        var nowDate = new Date().format('yyyy-MM-dd');
        // var switchDate = $("#switchDate").val();
        // if (isEmpty(switchDate)) {
        //     switchDate = "1900-1-1";
        // }
        // if (!dateCompare(seeDate, switchDate)) {
        //     parent.msgAlert("带看日期不能在关账日期之前！");
        //     return false;
        // }
        if (!dateCompare(nowDate, seeDate)) {
            parent.msgAlert("带看日期不能在当前日期之后！");
            return false;
        }

        // 带看单
        var fileSize = $("#uploadWatchImg .upload_img_list .item_img").size()
        if (fileSize == null || fileSize <= 0) {
            parent.msgAlert("请上传带看单！");
            return false;
        }

        return true;
    }

    function submitToService(optionsData) {
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        var url = BASE_PATH + "/sceneTrade/saveWatch";
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

});
