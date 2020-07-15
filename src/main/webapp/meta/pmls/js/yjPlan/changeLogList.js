layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;

    changeLogListInit();

    function changeLogListInit() {

        tableRender();
    }

    function tableRender() {
        table.render({
            elem: '#changeLogTable'
            , cols: setCols()
            , url: BASE_PATH + '/pmlsEstatePlan/queryChangeLogList/' + $("#id").val()
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
                {field: 'orderId', title: '序号', width: 90, align: 'center'},
                {field: 'changeDetail', title: '描述', width: 550, align: 'center'
                	,templet: function (d) {
                        return "<div style='text-align: left'>" + d.changeDetail + "</div>";
                    }
                },
                {field: 'operatorName', title: '操作人', width: 180, align: 'center',
                	templet: function (d) {
                        if (d.operatorCode !=0) {
                            return d.operatorName+"("+d.operatorCode+")";
                        } else {
                            return d.operatorName;
                        }
                    }
                },
                {field: 'changeDate', title: '操作时间', align: 'center'}
            ]
            var cols = [];
            cols.push(row);
            return cols;
        }

});


