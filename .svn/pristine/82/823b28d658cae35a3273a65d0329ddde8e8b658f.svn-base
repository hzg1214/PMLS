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
            elem: '#roughBackDate',
            type: 'date',
            format: 'yyyy-MM-dd',
            max: nowDate,
            min: switchDate,
            trigger: 'click'
        });
    }

    var active = {
        save: function () {
            if (valid() == true) {
                var optionsData = {};
                optionsData.id = $("#id").val();
                optionsData.reportId = $("#reportId").val();
                optionsData.estateId = $("#estateId").val();

                optionsData.operateDate = $("#roughBackDate").val();

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
    $(' .layui-btn').on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });

    function valid() {

        // 退定日期
        var roughBackDate = $("#roughBackDate").val()
        if (isEmpty(roughBackDate)) {
            parent.msgAlert("请选择退定日期！")
            return false;
        }

        // 关账日期 <= 退定日期 <= 当前日期
        var nowDate = new Date().format('yyyy-MM-dd');
        var switchDate = $("#switchDate").val();
        if (isEmpty(switchDate)) {
            switchDate = "1900-1-1";
        }
        if (!dateCompare(roughBackDate, switchDate)) {
            parent.msgAlert("退定日期不能在关账日期之前！")
            return false;
        }
        if (!dateCompare(nowDate, roughBackDate)) {
            parent.msgAlert("退定日期不能在当前日期之后！")
            return false;
        }

        return true;
    }

    function submitToService(optionsData) {
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        var url = BASE_PATH + "/sceneTrade/tobackRought";
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
            })
    }

});