var active;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$,
        form = layui.form;


    init();

    function init() {
        tableRender();
    }

    function tableRender() {
        table.render({
            elem: '#contentTable'
            , cols: setCols()
            , url: BASE_PATH + '/sceneTrade/queryAccountProjectList'
            , id: 'contentReload'
            , even: false //开启隔行背景
            , loading: false
            , page: true
            , limits: [10, 20, 30]
            , limit: 10 //默认采用60
            , method: 'post'
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
            {field: 'cityName', title: '归属城市', width: 180, align: 'center'},
            {field: 'accountProjectNo', title: '核算主体编号', width: 150, align: 'center'},
            {field: 'accountProject', title: '核算主体', width: 150, align: 'center'},
            {field: 'userNameCreate', title: '创建人', width: 150, align: 'center'},
            {
                field: 'dateCreate', title: '创建时间', width: 120, align: 'center',
                templet: function (d) {
                    if (d.dateCreate == null) {
                        return "-";
                    } else {
                        return formatDate(d.dateCreate, "yyyy-MM-dd");
                    }
                }
            },
            {
                title: '操作', align: 'center', width: 120,
                templet: function (d) {
                    var ret = "";
                    ret += "<a class='layui-btn layui-btn-xs' onclick='javascript:accountProject.edit(\"" + d.id + "\",\"" + d.cityNo + "\",\"" + d.accountProjectNo + "\")'>编辑</a>";
                    ret += "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='javascript:accountProject.delete(\"" + d.id + "\")'>删除</a>";
                    return ret;
                }
            }
        ];
        var cols = [];
        cols.push(row);
        return cols;
    }


    active = {
        reload: function () {
            var optionsData = {};
            optionsData.cityNo = trimStr($("#cityNo").val());
            optionsData.searchKey = trimStr($("#searchKey").val());
            reloadData(optionsData);
        },
        reset: function () {
            $("#cityNo").val("");
            $("#searchKey").val("");
            form.render('select'); // 刷新单选框渲染
            active.reload();
        },
        add: function () {
            accountProject.add();
        }
    };


    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
        var index = layer.load(2);

        table.reload('contentReload', {
            url: BASE_PATH + '/sceneTrade/queryAccountProjectList',
            cols: setCols(),
            where: optionsData,
            page:{
                curr: 1 //重新从第 1 页开始
            },
            done: function () {
                layer.close(index);
            }
        });
    }

    function valid() {
        return true;
    }

});


accountProject = {}
accountProject.add = function () {

    parent.layer.open({
        type: 2
        , title: '核算主体维护'
        , content: BASE_PATH + '/sceneTrade/accountProjectAddPopup'
        , area: ['450px', '420px']
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {

            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            if (formData != null) {
                var cityNo = formData.cityNo;
                var accountProjectNos = formData.accountProjectNos;

                parent.layer.close(index);

                var url = BASE_PATH + '/sceneTrade/saveAccountProject';
                var params = {
                    cityNo: cityNo,
                    accountProjectNos: accountProjectNos
                }
                var loadIndex = layer.load(2, {shade: [0.1]});
                restPost(url, params, function () {
                    parent.layer.close(loadIndex);
                    active.reload();
                }, function (data) {
                    parent.msgError(data.returnMsg);
                });
            }
        }
    });
}
accountProject.edit = function (id, cityNo, accountProjectNo) {
    parent.layer.open({
        type: 2
        , title: '核算主体维护'
        , content: BASE_PATH + '/sceneTrade/accountProjectUptPopup/' + cityNo + '/' + accountProjectNo
        , area: ['450px', '420px']
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {

            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            if (formData != null) {

                var accountProjectNos = formData.accountProjectNos;
                parent.layer.close(index);
                var url = BASE_PATH + '/sceneTrade/updateAccountProject';
                var params = {
                    id: id,
                    cityNo: cityNo,
                    accountProjectNos: accountProjectNos
                }
                var loadIndex = parent.layer.load(2, {shade: [0.1]});
                restPost(url, params, function () {
                    parent.msgProp("更新成功！")
                    active.reload();
                    parent.layer.close(loadIndex);
                }, function (data) {
                    parent.layer.close(loadIndex);
                    parent.msgError(data.returnMsg);
                });
            }
        }
    });
}
accountProject.delete = function (id) {

    parent.msgConfirm("是否确定删除此信息？", function () {
        var params = {
            id: id
        };
        var url = BASE_PATH + "/sceneTrade/deleteAccountProject";
        var loadIndex = parent.layer.load(2, {shade: [0.1]});
        restPost(url, params, function (data) {
            parent.layer.close(loadIndex);
            parent.msgProp("删除成功！")
            active.reload();
        }, function (data) {
            parent.layer.close(loadIndex);
            parent.msgError(data.returnMsg);
        });
    });
}
