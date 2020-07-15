layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;

    maintainerListInit();

    function maintainerListInit() {

        tableRender();
    }

    function tableRender() {
        table.render({
            elem: '#maintainerListTable'
            , cols: setCols()
            , url: BASE_PATH + '/pmlsStoreMaintainer/queryMaintainerHis/' + $("#storeId").val()
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
            {type: 'userCode', title: '维护人(工号)', width: 200,align: 'center',
            	templet: function (d) {
                    if (d.userCode == null || d.userCode == "") {
                        return "";
                    } else {
                        return d.userName+'('+d.userCode+')';
                    }
                }
            },
            {field: 'cellphone', title: '手机号', width: 200, align: 'center'},
            {field: 'email', title: '邮箱', width: 280, align: 'center'},
            {field: 'dateMtcStart', title: '开始维护时间', width: 220, align: 'center',
                templet: function (d) {
                    if (d.dateMtcStart == null) {
                        return "-";
                    } else {
                        return formatDate(d.dateMtcStart, "yyyy-MM-dd hh:mm:ss");
                    }
                }
            },
            {field: 'setUserCode',title: '设置人(工号)', align: 'center', 
                templet: function (d) {
                    if (d.setUserCode == null || d.setUserCode == "") {
                        return "";
                    } else {
                        return d.setUserName+'('+d.setUserCode+')';
                    }
                }
            }
        ];
        var cols = [];
        cols.push(row);
        return cols;
    }

});

