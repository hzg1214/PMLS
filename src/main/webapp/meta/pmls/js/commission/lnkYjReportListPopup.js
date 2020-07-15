layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        layer = layui.layer,
        $ = layui.$,
        table = layui.table;

    init();

    function init() {

    }

    var active = {
        selectCompanyInfo: function () {
            selectCompany("inputCompanyName")
        },
        selectCompanyInfoTwo: function () {
            selectCompany("inputCompanyNameTwo")
        },
        clearCompanyInfoTwo: function () {
            clearCompany("inputCompanyNameTwo")
        }
    };

    $('.dialogForm .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function selectCompany(id) {
        parent.layer.open({
            type: 2,
            title: '选择经纪公司',
            area: ['800px', '660px'],
            content: '/pmlsFrameContract/selectBusinessPage'
            , scrollbar: false
            , resize: false
            , btn: ['确定', '取消']
            , yes: function (index, layero) {

                //确认的回调
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                var formData = iframeWin.getFormData();

                //确认的回调
                if (formData != null) {

                    $("#" + id).attr("data-companyId", formData.id);
                    $("#" + id).attr("data-companyNo", formData.companyNo);
                    $("#" + id).val(formData.companyName);
                    parent.layer.close(index);
                }
            }
        });
    }

    function clearCompany(id) {
        $("#" + id).attr("data-companyId", "");
        $("#" + id).attr("data-companyNo", "");
        $("#" + id).val("");
    }
});

function getFormData() {
    var result = {
        ids: $("#ids").val()
        , projectNo: $("#projectNo").val()
        , reportIds: $("#reportIds").val()

        , inputCompanyId: $("#inputCompanyName").attr("data-companyId").trim()
        , inputCompanyNo: $("#inputCompanyName").attr("data-companyNo").trim()
        , inputCompanyName: $("#inputCompanyName").val().trim()

        , inputCompanyIdTwo: $("#inputCompanyNameTwo").attr("data-companyId").trim()
        , inputCompanyNoTwo: $("#inputCompanyNameTwo").attr("data-companyNo").trim()
        , inputCompanyNameTwo: $("#inputCompanyNameTwo").val().trim()

    };

    return result;
}