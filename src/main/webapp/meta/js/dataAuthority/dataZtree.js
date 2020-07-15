var setting = {
    view: {
        showIcon: true,

    },
    check: {
        enable: true,
        chkStyle: "checkbox",
        chkboxType: { "Y": "s", "N": "s" }
    },
    data: {
        simpleData: {
            enable: true,//是否之用简单数据
            idKey: 'id', //对应json数据中的ID
            pIdKey: 'parentid' //对应json数据中的父ID
        },
    },
    async: {
        enable: true,//是否为异步加载
        url: "/dataAuthority/queryCityData",//相关的请求网址
        type:"post"
    },
    callback : {
        onCheck : function(e,treeId,treeNode) {
            var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
                nodes=treeObj.getCheckedNodes(true),
                name="";
                id="";
            var list = [];
            for(var i=0;i<nodes.length;i++){
                // 判断是否父节点
                if(nodes[i].isParent!=true){

                    var cityObj = {};
                    cityObj.id =nodes[i].id ;
                    cityObj.name = nodes[i].name;
                    list.push(cityObj);
                    console.log(cityObj);

                    console.log("节点id:"+id+"节点名称"+name); //获取选中节点的值
                    console.log(list);
                }

            }
            $("#cityList").val(JSON.stringify(list));

        },
//捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
        onAsyncError : zTreeOnAsyncError,
        onAsyncSuccess : function(event, treeId, treeNode, msg){

        }
    }
};
// 加载错误提示
function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
    alert("加载错误：" + XMLHttpRequest);
};

// 渲染
$(document).ready(function() {
    $.fn.zTree.init($("#treeDemo"), setting);
});