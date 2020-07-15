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
            elem: '#dealBackDate',
            type: 'date',
            format: 'yyyy-MM-dd',
            trigger: 'click',
            max: nowDate,
            min: switchDate
        });
    }

    var active = {
        save: function () {
            if (valid() == true) {
                var optionsData = {};
                optionsData.id = $("#id").val();
                optionsData.reportId = $("#reportId").val();
                optionsData.estateId = $("#estateId").val();

                optionsData.operateDate = $("#dealBackDate").val();

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
    }
    $('.layui-btn').on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });


    function valid() {

        // 退房日期
        var dealBackDate = $("#dealBackDate").val()
        if (isEmpty(dealBackDate)) {
            parent.msgAlert("请选择退房日期！")
            return false;
        }

        // 关账日期 <= 退房日期 <= 当前日期
        var nowDate = new Date().format('yyyy-MM-dd');
        var switchDate = $("#switchDate").val();
        if (isEmpty(switchDate)) {
            switchDate = "1900-1-1";
        }
        if (!dateCompare(dealBackDate, switchDate)) {
            parent.msgAlert("退房日期不能在关账日期之前！");
            return false;
        }
        if (!dateCompare(nowDate, dealBackDate)) {
            parent.msgAlert("退房日期不能在当前日期之后！");
            return false;
        }

        // 带看单
        var fileSize = $("#uploadDealBackImg .upload_img_list .item_img").size()
        if (fileSize == null || fileSize <= 0) {
            parent.msgAlert("请上传退房资料！")
            return false;
        }

        return true;
    }

    function submitToService(optionsData) {
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        var url = BASE_PATH + "/sceneTrade/tobackSale";
        restPost(url, optionsData,
            function (data) {
                parent.layer.close(loadIndex);
                if (data.returnData == 201 || data.returnData == 202) {
                    parent.msgAlert(data.returnMsg);
                } else {

                    parent.msgProp("操作成功！");
                    parent.redirectTo('delete', null, null);
                }

            },
            function (data) {
                parent.layer.close(loadIndex);
                parent.msgAlert(data.returnMsg);
            }
        );
    }

    var multiple = true;//多图上传

    // 退房资料
    uploadRender('uploadDealBackImg',{fileTypeId:'1065',fileSourceId:'5' ,exts:'jpg|png|jpeg|pdf'});

});