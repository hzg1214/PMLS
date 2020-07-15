layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;

    logListInit();

    function logListInit() {

        tableRender();
    }

    function tableRender() {
        table.render({
            elem: '#logListTable'
            , cols: setCols()
            , url: BASE_PATH + '/pmlsStore/queryStoreLogList/' + $("#storeId").val()
            , id: 'contentReload'
            , height: "full"
            , page: true
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
            {type: 'numbers', title: '序号', width: 60},
            {field: 'updateIteam', title: '修改项目', width: 300, align: 'center'},
            {
                field: 'updateDate', title: '修改时间', width: 200, align: 'center',
                templet: function (d) {
                    if (d.updateDate == null) {
                        return "-";
                    } else {
                        return formatDate(d.updateDate, "yyyy-MM-dd");
                    }
                }
            },
            {
                field: 'userName', title: '修改人(工号)', width: 200, align: 'center',
                templet: function (d) {
                    var userName = d.userName;
                    var userCode = d.userCode;
                    if (userName == null) {
                        return "-";
                    }
                    else if (userCode == null) {
                        return userName;
                    }
                    else {
                        return userName + "(" + userCode + ")";
                    }
                }
            },
            {
                title: '操作', align: 'center', width: 150,
                templet: function (d) {
                    return "<a class='layui-btn layui-btn-xs' onclick='javascript:storeLogList.showDetail(\"" + d.id + "\",\"" + d.storeId + "\")'>查看</a>";
                }
            }
        ];
        var cols = [];
        cols.push(row);
        return cols;
    }

});

var storeLogList = {}
storeLogList.showDetail = function (id, storeId) {
    parent.layer.open({
        type: 2,
        title: '修改日志',
        area: ['700px', '500px'],
        content: BASE_PATH + '/pmlsStore/storeLogView/' + id + '/' + storeId
        , scrollbar: true
        , resize: false
        , btn: ['关闭']
        , yes: function (index, layero) {
            parent.layer.close(index);
        }
    });
}
