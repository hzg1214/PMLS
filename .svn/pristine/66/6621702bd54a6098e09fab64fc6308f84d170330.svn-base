<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房友新房分销系统</title>
    <%@include file="common/common.jsp" %>

    <style>
        body{padding: 0px; /*overflow-y: scroll;*/}
    </style>
</head>
<body>
<script type="text/html" id="toolMainTable">
    <a class="layui-btn layui-btn-xs" lay-event="edit">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="update">修改</a>
    <a class="layui-btn layui-btn-xs" lay-event="del">开通</a>
</script>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <div class="layui-form-item">

                    <div class="layui-inline">
                        <label class="layui-form-label">商户编号</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="employeeNumber"  lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">商户名称</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="employeeName"  lay-verify="">
                        </div>
                    </div>

                    <div class="layui-inline toolbar">
                        <button class="layui-btn" data-type="reload">搜索</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<div>
    <div class="layui-card">
        <div class="layui-card-header">
            <div class="layui-btn-group toolbar">
                <button class="layui-btn" data-type="addUser">新建商户</button>
            </div>
        </div>
        <div class="layui-card-body">
            <div id="mainTable" lay-filter="mainTable"></div>
        </div>
    </div>
</div>

</div>

<script>
    layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
        var element = layui.element,
            laydate = layui.laydate,
            form = layui.form,
            table = layui.table,
            layer = layui.layer,
            $ = layui.$;

        form.render('select'); //刷新select选择框渲染
        table.render({
            elem: '#mainTable'
            , cols: setCols()
            , id: 'contentReload'
            , height: 'full'
            , even: true //开启隔行背景
            , page: true
            , limits: [10,20,30]
            , limit: 10 //默认采用60
            , method: 'post'
            ,request: {
                pageName: 'page' //页码的参数名称，默认：page
                ,limitName: 'rows' //每页数据量的参数名，默认：limit
            }
            ,response: {
                statusName: 'code' //数据状态的字段名称，默认：code
                ,statusCode: 200 //成功的状态码，默认：0
                ,msgName: 'msg' //状态信息的字段名称，默认：msg
                ,countName: 'records' //数据总数的字段名称，默认：count
                ,dataName: 'results' //数据列表的字段名称，默认：data
            }
        });
        function setCols() {
            var row1 = [];
            row1.push(
                {field: 'rowNum' ,title: '序号',width:60, align: 'center',fixed:true},
                {field: 'bussinessNo',title: '商户编号',width:150, align: 'center'},
                {field: 'bussinessName',title: '商户名称',width:250, align: 'center'},
                {field: 'bussinessBrand',title: '商户品牌',width:150, align: 'center'},
                {field: 'registeredAddress',title: '注册地址',width:250, align: 'center'},
                {field: 'accountStatus',title: '账号状态',width:200, align: 'center'},
                {field: 'createUser',title: '创建人',width:200, align: 'center'},
                {field: 'createDate',title: '创建时间',width:200, align: 'center'},
                {title: '操作', width: 200, align: 'center',toolbar: '#toolMainTable',fixed:'right'}
            );
            var cols = [];
            cols.push(row1);
            return cols;
        }

        reloadData();//初始化加载表格

        var active = {
            reload:function(){
                var optionsData={};
                reloadData(optionsData);
            },
            addUser:function () {
                //
            }

        };

        $('.toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });


        function reloadData(optionsData){
            var data=[];
            for(var i=1;i<100;i++){
                data.push({
                    rowNum:i,
                    bussinessNo:'SH0000001',
                    bussinessName:'上海链家经纪服务有限公司',
                    bussinessBrand:'链家',
                    registeredAddress:'上海虹口广粤路439弄3号',
                    accountStatus:'已开通',
                    createUser:'李晓东',
                    createDate:'2019-10-24 17:13:33',
                })
            }
            //var index = parent.layer.load(2,{shade: 0.1,time: 5*1000});
            table.reload('contentReload', {
                //url:ctx + '/sysUserController/getEmployeeAllList',
                data:data,
                cols:setCols(),
                height: window.innerHeight-$(".layui-table-view").offset().top-10,
                page:{
                    curr: 1 //重新从第 1 页开始
                },
                where: optionsData
            });
        }
    });
</script>
</body>
</html>

