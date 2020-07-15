var layer;
var form;
var zNodes;
var active;
var formSelects;
layui.use(['element', 'laydate', 'form', 'table', 'layer','tree'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        table = layui.table,
        tree = layui.tree;
    formSelects = layui.formSelects;
    layer = layui.layer;
    form = layui.form;
    form.render();

    laydate.render({
        elem: '#yearMonth',
        type: 'month',
        trigger: 'click',
        format: 'yyyy-MM',
        max:$("#year").val() + "-" +$("#month").val()
        ,value: $("#year").val() + "-" +$("#month").val()
        ,done: function (value, date) {
            /* console.log("change:"+value.substring(0,4)+"&"+value.substring(5,7));
                 $("#switchYear").val(value.substring(0,4));
                 $("#switchMonth").val(value.substring(5,7));
                 initWeek(value.substring(0,4),value.substring(5,7));*/
            console.log("dateDone:"+date.year+"&"+date.month);
            $("#switchYear").val(date.year);
            $("#switchMonth").val(date.month);
            //初始化-周
            initWeek(date.year,date.month);
        }
    });
    form.render();

    init();
    function init(){
        $("#switchYear").val($("#year").val());
        $("#switchMonth").val(parseInt($("#month").val()));
        initWeek($("#switchYear").val(),$("#switchMonth").val());
    }




    var yearMonth = $("#yearMonth").val();
    if(!isBlank(yearMonth)){
   /*     $("#switchYear").val(yearMonth.substring(0,4));
        $("#switchMonth").val(yearMonth.substring(5,7));*/
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
            $("#yearMonth").val($("#year").val() + "-" +$("#month").val());
            formSelects.value('searchCityNo', []);
            form.render('select');
            var searchCityNo = JSON.stringify([]);
          /*  $("#switchYear").val($("#year").val());
            $("#switchMonth").val(parseInt($("#month").val()));*/
            init();
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
        var weekNo = $("#weekNo").val();

        if(isBlank(yearMonth)){
            parent.layer.alert("请选择关账年月!", {icon: 2, title: '提示'});
            return false;
        }

        if(isBlank(weekNo)){
            parent.layer.alert("请选择关账周!", {icon: 2, title: '提示'});
            return false;
        }
        return true;
    }






    function reloadData(searchCityNo) {
        var index =  parent.layer.load(2);
        var switchYear = $("#switchYear").val();
        var switchMonth = $("#switchMonth").val();
        var weekNo = $("#weekNo").val();
        var sortNo = $('#weekNo').find("option:selected").attr("tagSortNo");
        // var searchCityNo = getXMSelectIds(formSelects.value('searchCityNo'));

        console.log("reloadData"+switchYear+"&"+switchMonth);
        var url = BASE_PATH + '/pmlsPerformSwitchWeek/getList?year='+switchYear+"&month="+switchMonth+"&weekNo="+weekNo+"&sortNo="+sortNo+"&relateSystem="+29301;
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
        var weekNo = $("#weekNo").val();
        var sortNo = $('#weekNo').find("option:selected").attr("tagSortNo");
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
                var pmlsPerformSwitchWeekDto ={
                    cityNo:nodes[i].cityNo,
                    year:switchYear,
                    month:switchMonth,
                    weekNo:weekNo,
                    switchState:27501,
                    relateSystem:29301
                    ,sortNo:sortNo
                };
                list.push(pmlsPerformSwitchWeekDto);
            }
        }

        parent.layer.confirm("确认选择区域进行"+switchYear+"年"+switchMonth+"月第"+weekNo+"周回款跟踪开账操作吗？",{icon: 3, title:'提示'},function(){
            parent.layer.load(2);
            var url = BASE_PATH + "/pmlsPerformSwitchWeek/openCloseSwitch";
            $.ajax({
                type: "POST",
                url: url,
                contentType: 'application/json;charset=utf-8', //设置请求头信息
                data: JSON.stringify(list),
                dataType: "json",
                success: function (data) {
                    parent.layer.closeAll();
                    if(""!=data.returnMsg){
                        parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
                    }else{
                        var searchCityNo = JSON.stringify(formSelects.value('searchCityNo', 'val'));
                        reloadData(searchCityNo);
                    }
                },
                error: function () {
                    parent.layer.closeAll();
                    parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'});
                }
            });
        });
    };


    /**
     * 关账
     */
    function closeSwitch() {
        var switchYear = $("#switchYear").val();
        var switchMonth = $("#switchMonth").val();
        var weekNo = $("#weekNo").val();
        var sortNo = $('#weekNo').find("option:selected").attr("tagSortNo");

        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
            nodes = zTree.getCheckedNodes(true);
        if(nodes.length==0){
            parent.layer.alert("请选择要关账的城市!", {icon: 2, title: '提示'});
            return false;
        }

        //组装数据
        var list=[];
        for (var i = 0, l = nodes.length; i < l; i++) {
            if(nodes[i].cityNo!=null){
                var pmlsPerformSwitchWeekDto ={
                    cityNo:nodes[i].cityNo,
                    year:switchYear,
                    month:switchMonth,
                    weekNo:weekNo,
                    switchState:27502,
                    relateSystem:29301
                    ,sortNo:sortNo
                };
                list.push(pmlsPerformSwitchWeekDto);
            }
        }

        parent.layer.confirm("确认选择区域进行"+switchYear+"年"+switchMonth+"月第"+weekNo+"周回款跟踪关账操作吗？",{icon: 3, title:'提示'},function(){
            parent.layer.load(2);
            var url = BASE_PATH + "/pmlsPerformSwitchWeek/openCloseSwitch";
            $.ajax({
                type: "POST",
                url: url,
                contentType: 'application/json;charset=utf-8', //设置请求头信息
                data: JSON.stringify(list),
                dataType: "json",
                success: function (data) {
                    parent.layer.closeAll();
                    if(""!=data.returnMsg){
                        parent.layer.alert(data.returnMsg, {icon: 2, title: '提示'});
                    }else{
                        var searchCityNo = JSON.stringify(formSelects.value('searchCityNo', 'val'));
                        reloadData(searchCityNo);
                    }
                },
                error: function () {
                    parent.layer.closeAll();
                    parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'});
                }
            });
        });
    };



});


//初始化-周
function initWeek(year,month){
    console.log("intWeek:"+year+"&"+month)
    var url = BASE_PATH + "/pmlsPerformSwitchWeek/getWeeks";
    var params ={year:year,month:month};
    ajaxGetSync(url, params, function (data) {
        var result = "<option value=''>请选择</option>";
        $.each(data.returnData, function (n, value) {
            if(n==0){
                result += "<option value='" + value.weekNo + "' tagSortNo='" + value.sortNo + "' selected>第"+ value.weekNo + "周</option>";
            }else {
                result += "<option value='" + value.weekNo + "' tagSortNo='" + value.sortNo + "'>第"+ value.weekNo + "周</option>";
            }
        });
        $("#weekNo").html('');
        $("#weekNo").append(result);
        form.render();
    }, function () {
    });
}