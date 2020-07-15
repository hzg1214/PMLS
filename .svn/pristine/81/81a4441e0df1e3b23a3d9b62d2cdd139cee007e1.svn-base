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
    <a class="layui-btn layui-btn-xs" lay-event="del">上架</a>
</script>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <div class="layui-form-item">

                    <div class="layui-inline">
                        <label class="layui-form-label">项目编号</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="employeeNumber"  lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">项目名称</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="employeeName"  lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">审核状态</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="employeeName"  lay-verify="">
                        </div>
                    </div>

                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">发布状态</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="employeeNumber"  lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">项目归属部</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="employeeName"  lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">楼盘城市</label>
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
                <button class="layui-btn" data-type="addUser">新建项目</button>
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
                {field: 'projectNo',title: '项目编号',width:150, align: 'center'},
                {field: 'projectName',title: '楼盘名称',width:200, align: 'center'},
                {field: 'projectCity',title: '楼盘城市',width:150, align: 'center'},
                {field: 'projectAddress',title: '楼盘地址',width:250, align: 'center'},
                {field: 'partnerName',title: '合作方名称',width:250, align: 'center'},
                {field: 'auditStatus',title: '审核状态',width:100, align: 'center'},
                {field: 'releaseStatus',title: '发布状态',width:100, align: 'center'},
                {field: 'projectStatus',title: '项目状态',width:100, align: 'center'},
                {field: 'createUser',title: '创建人',width:100, align: 'center'},
                {field: 'createDate',title: '创建时间',width:200, align: 'center'},
                {title: '操作', width: 200, align: 'center',toolbar: '#toolMainTable',fixed: 'right'}
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
                    projectNo:'XM0000001',
                    projectName:'碧桂园凤凰城',
                    projectCity:'上海',
                    projectAddress:'上海金山区隆安东路138弄6-8号',
                    partnerName:'上海碧通房地产开发有限公司',
                    auditStatus:'通过',
                    releaseStatus:'未发布',
                    projectStatus:'立项',
                    createUser:'李晓东',
                    createDate:'2019-10-24 13:40:15',
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

