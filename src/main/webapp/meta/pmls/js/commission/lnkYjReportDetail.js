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

    init();

    function init() {
        tableRender();
    }

    function tableRender() {
        var listData = window.companyMatins;

        table.render({
            elem: '#contentTable'
            , cols: setCols()
            , data: listData
            , id: 'contentReload'
            , height: "full"
            , limit:1000
            , page: false
            , loading: false
            , method: 'post'
            , request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
            , where: {}
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
        var row = [
            {type: 'numbers', title: '序号', width: 60},
            {field: 'userName', title: '维护人', width: 120, align: 'center'},
            {
                field: 'crtDt', title: '维护时间', width: 160, align: 'center'
                , templet: function (d) {
                    if (d.crtDt == null) {
                        return "-";
                    } else {
                        return formatDate(d.crtDt, "yyyy-MM-dd");
                    }
                }
            },
            {
                field: 'content', title: '变更内容', align: 'center'
                , templet: function (d) {
                    return "<div style='text-align: left'>" + d.content + "</div>";
                }
            }
        ];
        var cols = [];
        cols.push(row);
        return cols;
    }

    var active = {
        goback: function () {
            parent.redirectTo('delete', null, null);
        }
    }

    $('.layui-btn').on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });

});
