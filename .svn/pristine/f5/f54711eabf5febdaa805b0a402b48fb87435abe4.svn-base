var layer;
var zNodes;
var active;
var formSelects;
layui.use(['element', 'laydate', 'form', 'table', 'layer','tree'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        tree = layui.tree;
        formSelects = layui.formSelects;
        layer = layui.layer;
    form.render('select'); //刷新select选择框渲染
    laydate.render({
        elem: '#yearMonth',
        type: 'month',
        trigger: 'click',
        format: 'yyyyMM',
        max:$("#year").val() + "" +$("#month").val()
        ,value: $("#year").val() + "" +$("#month").val()
        ,change: function(value, date, endDate){
            console.log(value.substring(0,4));console.log(value.substring(4,6));
            $("#switchYear").val(value.substring(0,4));
            $("#switchMonth").val(value.substring(4,6));
        }
    });

    var yearMonth = $("#yearMonth").val();
    if(!isBlank(yearMonth)){
        $("#switchYear").val(yearMonth.substring(0,4));
        $("#switchMonth").val(yearMonth.substring(4,6));
        var searchCityNo = JSON.stringify([]);
        reloadData(searchCityNo);
    }

    active = {
        reload: function () {
            if (valid()) {
                try {
                    var searchCityNo = JSON.stringify(formSelects.value('searchCityNo', 'val'));
                    reloadData(searchCityNo);
                }
                catch(err){
                    console.log("catch init")
                    var searchCityNo = JSON.stringify(formSelects.value('searchCityNo', 'val'));
                    reloadData(searchCityNo);
                }

            }
        },
        reset: function () {
            console.info("all.reset");
            $("#yearMonth").val($("#year").val() + "" +$("#month").val());
            $("#switchYear").val($("#year").val());
            $("#switchMonth").val($("#month").val());
            formSelects.value('searchCityNo', []);
            form.render('select');
            var searchCityNo = JSON.stringify([]);
            reloadData(searchCityNo);


        },
        closeSwitch: function () {
            if (valid()) {
                closeSwitch();
            }
        },
        openSwitch: function () {
            if (valid()) {
                openSwitch();
            }
        }
    };

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    function valid() {
        var yearMonth = $('#yearMonth').val();

        if(isBlank(yearMonth)){
            parent.layer.alert("请选择关账年月!", {icon: 2, title: '提示'});
            return false;
        }
        return true;
    }






function reloadData(searchCityNo) {
    var index =  parent.layer.load(2);
    var switchYear = $("#switchYear").val();
    var switchMonth = $("#switchMonth").val();
   // var searchCityNo = getXMSelectIds(formSelects.value('searchCityNo'));

    console.log("reloadData"+switchYear+""+switchMonth);
    var url = BASE_PATH + '/pmlsSwitch/q?switchYear='+switchYear+"&switchMonth="+switchMonth;
    $.ajax({
        type: "POST",
        url: url,
        dataType:"json",
        data:searchCityNo,
        contentType: 'application/json',
        success: function (data) {
            parent.layer.closeAll();
            if (data.returnCode == 200 || data.returnCode == '200') {
                zNodes = data.returnData;
                initzTree();
            } else {
                parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
                return false;
            }

        },
        error: function () {
            parent.layer.alert("查询数据失败", {icon: 2, title: '提示'},function () {
                parent.layer.closeAll();
            });
            return false;
        }
    });
};


function initzTree() {
    if(!zNodes)return false;
    var setting = {
        check: {
            enable: true,
            chkStyle: "checkbox",
            autoCheckTrigger: true,
            chkboxType: {"Y":"ps","N" : "ps"}
        },
        view: {
            fontCss: getFont,
            nameIsHTML: true,
            showIcon: false
        }
    };
    // init zTree
    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes).expandAll(true);
    });
    function getFont(treeId, node) {
        return node.font ? node.font : {};
    }
};




function openSwitch() {
    var switchYear = $("#switchYear").val();
    var switchMonth = $("#switchMonth").val();

    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getCheckedNodes(true);
    if(nodes.length==0){
        parent.layer.alert("请选择要开账的城市!", {icon: 2, title: '提示'});
        return false;
    }
    //组装数据
    var list=[];
    for (var i = 0, l = nodes.length; i < l; i++) {
        if(nodes[i].cityNo!=null){
            var obj={
                cityNo:nodes[i].cityNo,
                switchYear:switchYear,
                switchMonth:switchMonth,
                switchState:27501,
                relateSystem:27601
            };
            list.push(obj);
        }
    }

    parent.layer.confirm("确认选择区域进行"+switchYear+"年"+switchMonth+"月业绩开账操作吗？",{icon: 3, title:'提示'},function(){
        parent.layer.load(2);
        var json=JSON.stringify(list);
        var url = BASE_PATH + "/pmlsSwitch/openCloseSwitch";
        restPost(url, "estateList="+json,function(data) {
            parent.layer.closeAll();
            if(""!=data.returnMsg){
                parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
            }else{
                var searchCityNo = JSON.stringify(formSelects.value('searchCityNo', 'val'));
                reloadData(searchCityNo);
            }
        }, function(data) {
            parent.layer.closeAll();
            parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'});
        });
    });
};
/**
 * 关账
 */
function closeSwitch() {
    var switchYear = $("#switchYear").val();
    var switchMonth = $("#switchMonth").val();

    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = zTree.getCheckedNodes(true);
    if(nodes.length==0){
        parent.layer.alert("请选择要关账的城市!", {icon: 2, title: '提示'});
        return false;
    }

    //组装数据
    var list=[];
    var cityNoStr ="";
    for (var i = 0, l = nodes.length; i < l; i++) {
        if(nodes[i].cityNo!=null){
            cityNoStr += nodes[i].cityNo+",";
            var obj={
                cityNo:nodes[i].cityNo,
                switchYear:switchYear,
                switchMonth:switchMonth,
                switchState:27502,
                relateSystem:27601
            };
            list.push(obj);
        }
    }

    /**
     * 发送ajax查看所选月份的城市的大定待审核的是否还有，存在 即不能关帐
     */
    var flag = true;
    if(cityNoStr != null && cityNoStr != "" || cityNoStr != undefined){
        cityNoStr = cityNoStr.substr(0,cityNoStr.length-1);
        var checkUrl = BASE_PATH + "/pmlsSwitch/checkRoughToValid";
        var cityNameStr="";
        $.ajax({
            url:checkUrl,
            async:false,
            type:"get",
            data:{
                cityNoStr:cityNoStr,
                switchYear:switchYear,
                switchMonth:switchMonth
            },
            success:function(data){
                if(data.length > 0 && data[0].cityName != null){
                    data = JSON.parse(data);
                    cityNameStr = data[0].cityName;
                    flag = false;
                }
            }
        });
        if(!flag){
            parent.layer.alert(cityNameStr+"新房联动有大定数据未审核，不允许关账！", {icon: 2, title: '提示'});
            return false;
        }

        // var skCheckUrl = BASE_PATH + "/pmlsSwitch/checkSkAllocate";
        // $.ajax({
        //     url: skCheckUrl,
        //     async: false,
        //     type: "get",
        //     dataType: 'json',
        //     data: {
        //         cityNoStr: cityNoStr,
        //         switchYear: switchYear,
        //         switchMonth: switchMonth
        //     },
        //     success: function (data) {
        //         if (data && data.returnCode == '200') {
        //             flag = true;
        //         } else {
        //             flag = false;
        //             cityNameStr = data.returnData;
        //         }
        //     }
        // });
        //
        // if (!flag) {
        //     parent.layer.alert("[" +cityNameStr + "]存在收款单的入账日期处于关账月份且未拆分完毕，不允许关账！", {icon: 2, title: '提示'});
        //     return false;
        // }

    }



    parent.layer.confirm("确认选择区域进行"+switchYear+"年"+switchMonth+"月业绩关账操作吗？",{icon: 3, title:'提示'},function(){
        parent.layer.load(2);
        var json=JSON.stringify(list);
        var url = BASE_PATH + "/pmlsSwitch/openCloseSwitch";
        restPost(url, "estateList="+json,function(data) {
            parent.layer.closeAll();
            if(""!=data.returnMsg){
                parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
            }else{
                var searchCityNo = JSON.stringify(formSelects.value('searchCityNo', 'val'));
                reloadData(searchCityNo);
            }
        }, function(data) {
            parent.layer.closeAll();
            parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
        });
    });
};

});