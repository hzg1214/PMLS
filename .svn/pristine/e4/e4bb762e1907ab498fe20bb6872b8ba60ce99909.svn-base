layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;

    followListInit();

    function followListInit() {

        tableRender();
    }

    function tableRender() {
        table.render({
            elem: '#followListTable'
            , cols: setCols()
            , url: BASE_PATH + '/pmlsFollow/queryFollowList/' + $("#storeId").val()
            , id: 'contentReload'
            , height: "full"
            , page: false
            , limits: [10, 20, 30]
            , limit: 10 //默认采用60
            , method: 'post'
            , loading: false
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
        var row = [
            {field: 'title', title: '跟进主题', width: 180},
            {field: 'dateCreate', title: '跟进时间', width: 180, align: 'center'},
            {field: 'userNameCreate', title: '跟进人', width: 280, align: 'center'},
            {field: 'followTypeName', title: '跟进类型', width: 220, align: 'center'},
            {title: '操作', align: 'center', width: 180,
                templet: function (d) {
                    return "<a class='layui-btn layui-btn-xs' onclick='showDetail(\"" + d.followId + "\",\"" + d.storeId + "\")'>查看</a>";
                }
            }
        ];
        var cols = [];
        cols.push(row);
        return cols;
    }

});

/**
 * 查看
 * @param id
 * @param storeId
 * @returns
 */
function showDetail(id, storeId) {
    parent.layer.open({
        type: 2,
        title: '跟进详情',
        area: ['800px', '700px'],
        content: BASE_PATH + '/pmlsFollow/followView/' + id + '/' + storeId
//        , scrollbar: true
//        , resize: false
        , btn: ['关闭']
        , yes: function (index, layero) {
            parent.layer.close(index);
        }
    });
}

