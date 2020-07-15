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
        selectCompanyInfo2: function () {
            selectCompany("inputCompanyName2")
        },
        clearCompanyInfo2: function () {
            clearCompany("inputCompanyName2")
        },
        selectCompanyInfo3: function () {
            selectCompany("inputCompanyName3")
        },
        clearCompanyInfo3: function () {
            clearCompany("inputCompanyName3")
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
        projectNo: $("#projectNo").val()
        , reportId: $("#reportId").val()

        , inputCompanyNo1: $("#inputCompanyName1").attr("data-companyNo").trim()
        , inputCompanyName1: $("#inputCompanyName1").val().trim()

        , inputCompanyNo2: $("#inputCompanyName2").attr("data-companyNo").trim()
        , inputCompanyName2: $("#inputCompanyName2").val().trim()

        , inputCompanyNo3: $("#inputCompanyName3").attr("data-companyNo").trim()
        , inputCompanyName3: $("#inputCompanyName3").val().trim()

    };

    return result;
}