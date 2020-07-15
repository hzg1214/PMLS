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
        .layui-btn-mini {
            height: 22px;
            line-height: 22px;
            padding: 0 5px;
            font-size: 12px;
        }
    </style>
</head>
<body>

<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-form">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">渠道编号</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="employeeNumber"  lay-verify="">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">渠道名称</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id="employeeName"  lay-verify="">
                        </div>
                    </div>

                    <div class="layui-inline toolbar">
                        <button class="layui-btn" data-type="reload">搜索</button>
                    </div>

                </div>
            </div>
            <div class="layui-btn-group toolbar">
                <button class="layui-btn" data-type="addParentMenu">添加渠道</button>
            </div>

            <div class="treeTable">
                <div id="menuTable"></div>
            </div>
        </div>
    </div>

</div>

</div>


<script>

    var layout = [
        { name: '渠道名称',field: 'qdName', treeNodes: true, headerClass: 'value_col', colClass: 'value_col', style: 'width: 20%' },
        { name: '渠道编号',field: 'qdCode', treeNodes: false, headerClass: 'value_col', colClass: 'value_col', style: 'width: 10%' },
        { name: '备注',field: 'remark', treeNodes: false, headerClass: 'value_col', colClass: 'value_col', style: 'width: 20%' },
        { name: '创建人',field: 'createUser', treeNodes: false, headerClass: 'value_col', colClass: 'value_col', style: 'width: 10%' },
        { name: '创建时间',field: 'createDate', treeNodes: false, headerClass: 'value_col', colClass: 'value_col', style: 'width: 10%' },
        {
            name: '操作',
            headerClass: 'value_col',
            colClass: 'value_col',
            style: 'width: 30%',
            render: function(row) {
                var showContent='';
                showContent+='<a class="layui-btn layui-btn-mini" onclick="add(' + row.id + ')">添加子集</a>';
                showContent+='<a class="layui-btn layui-btn-normal layui-btn-mini" onclick="update(' + row.id + ')">修改</a>';
                showContent+='<a class="layui-btn layui-btn-danger layui-btn-mini" onclick="del(' + row.id + ')">删除</a>';
                return showContent; //列渲染
            }
        },
    ];

    var layer;
    layui.use(['tree', 'layer','table', 'form'], function() {
        layer = layui.layer,
            table = layui.table,
            $ = layui.jquery;
        var form = layui.form;

        reloadTable();

        var active = {
            addParentMenu: function () {
                add(0);
            }
        };

        $('.toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });

    function reloadTable(){
        /*$.ajax({
            url: ctx + '/menuController/getMenuList',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                console.log(data);
                loadTable(data);
            }
        });*/
        var data=[{
            "id": "1",
            "createUser": "张三",
            "createDate": "2018-12-10 20:29:08",
            "parentId": "0",
            "qdName": "链家",
            "name":"链家",
            "qdCode": "QD0001",
            "remark":"这是一个备注",
            "children": [{
                "id": "2",
                "createUser": "张三",
                "createDate": "2018-12-10 20:29:08",
                "parentId": "0",
                "qdName": "上海",
                "name":"上海",
                "remark":"这是一个备注",
                "qdCode": "QD0001-001"

            }, {
                "id": "3",
                "createUser": "张三",
                "createDate": "2018-12-10 20:29:08",
                "parentId": "0",
                "qdName": "杭州",
                "name":"杭州",
                "remark":"这是一个备注",
                "qdCode": "QD0001-002"
            },{
                "id": "4",
                "createUser": "张三",
                "createDate": "2018-12-10 20:29:08",
                "parentId": "0",
                "qdName": "郑州",
                "name":"郑州",
                "remark":"这是一个备注",
                "qdCode": "QD0001-003"
            }]
        }, {
            "id": "5",
            "createUser": "张三",
            "createDate": "2018-12-10 20:29:08",
            "parentId": "0",
            "qdName": "房多多",
            "name":"房多多",
            "qdCode": "QD0002",
            "remark":"这是一个备注",
            "children": [{
                "id": "6",
                "createUser": "张三",
                "createDate": "2018-12-10 20:29:08",
                "parentId": "0",
                "qdName": "华东",
                "name":"华东",
                "qdCode": "QD0002-001",
                "remark":"这是一个备注",
                "children": [{
                    "id": "7",
                    "createUser": "张三",
                    "createDate": "2018-12-10 20:29:08",
                    "parentId": "0",
                    "qdName": "上海",
                    "name":"上海",
                    "remark":"这是一个备注",
                    "qdCode": "QD0002-001-001"

                }, {
                    "id": "8",
                    "createUser": "张三",
                    "createDate": "2018-12-10 20:29:08",
                    "parentId": "0",
                    "qdName": "杭州",
                    "name":"杭州",
                    "remark":"这是一个备注",
                    "qdCode": "QD0002-001-002"
                },{
                    "id": "9",
                    "createUser": "张三",
                    "createDate": "2018-12-10 20:29:08",
                    "parentId": "0",
                    "qdName": "郑州",
                    "name":"郑州",
                    "remark":"这是一个备注",
                    "qdCode": "QD0002-001-003"
                }]
            }],
        }];
        loadTable(data);
    }


    function loadTable(data){
        //重置div
        $(".treeTable").html('<div id=\"menuTable\"></div>');
        layui.treeGird({
            elem: '#menuTable', //传入元素选择器
            nodes: data,
            layout: layout
        });
    }
    //添加菜单信息
    function add(id){
        //
    }
    //修改菜单信息
    function update(id){
        //
    }
    //删除菜单
    function del(id){
        //
    }
</script>
</body>
</html>
