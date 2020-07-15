
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;

    laydate.render({
        elem: '#dateStart',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        done: function (value, date, endDate) {
            var startDate = new Date(value).getTime();
            if($('#dateEnd').val()!=''){
                var endTime = new Date($('#dateEnd').val()).getTime();
                if (endTime < startDate) {
                    layer.msg('开始时间不能大于结束时间');
                    $('#dateStart').val('');
                }
            }
        }
    })
    laydate.render({ //结束时间
        elem: '#dateEnd',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        done: function (value, date, endDate) {
            if($('#dateStart').val()!=""){
                var startDate = new Date($('#dateStart').val()).getTime();
                var endTime = new Date(value).getTime();
                if (endTime < startDate) {
                    layer.msg('结束时间不能小于开始时间');
                    $('#dateEnd').val('');
                }
            }
        }
    })

    form.render('select'); //刷新select选择框渲染
    table.render({
        elem: '#mainTable'
        , cols: setCols()
        , id: 'contentReload'
        , height: 'full'
        , even: false //开启隔行背景
        , page: true
        , limits: [10,20,30,50,100]
        , limit: 10 //默认采用10
        , method: 'post'
        ,loading:false
        ,request: {
            pageName: 'curPage' //页码的参数名称，默认：page
            ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
        }
        ,response: {
            statusName: 'returnCode' //数据状态的字段名称，默认：code
            ,statusCode: 200 //成功的状态码，默认：0
            ,msgName: 'returnMsg' //状态信息的字段名称，默认：msg
            ,countName: 'totalCount' //数据总数的字段名称，默认：count
            ,dataName: 'returnData' //数据列表的字段名称，默认：data
        }
    });
    function setCols() {
        var row1 = [];
        row1.push(
            {field: 'contractNo',title: '框架协议编号',width:180, align: 'center' ,event:'detail',style:'cursor: pointer;color:#01AAED'},
            {field: 'companyNo',title: '公司编号',width:100, align: 'center'},
            {field: 'companyName',title: '公司名称',width:300, align: 'center'
            	, templet: function (d) {
                    return "<div style='text-align: left'>" + d.companyName + "</div>";
                }
            },
            {field: 'approveStatusNm',title: '审核状态',width:100, align: 'center'},
            {field: 'validStatusNm',title: '生效状态',width:100, align: 'center',templet: function(row) {
                    var showContent='';
                    if(row.approveState=='10403'){
                        showContent=row.validStatusNm;
                    }else{
                        showContent='-';
                    }
                    return showContent; //列渲染
                }},
            {field: 'userName',title: '创建人',width:100, align: 'center'},
            {field: 'format2',title: '创建日期',width:150, align: 'center'},
            {title: '操作', width:200,align: 'left',fixed:'right',templet: function(row) {
                    var showContent='';
                    showContent+='<a class="layui-btn layui-btn-mini" onclick="showDetail(' + row.id + ')">查看</a>';
                    if(((row.approveState=='10401' || row.approveState=='10404') && (row.submitOAStatus == '21201' || row.submitOAStatus == '21204'))){
                        showContent+='<a class="layui-btn layui-btn-normal layui-btn-mini" onclick="update(' + row.id + ')">编辑</a>';
                        showContent+='<a class="layui-btn layui-btn-danger layui-btn-mini" onclick="cancel(' + row.id + ','+row.companyNo+')">作废</a>';
                    }

                    return showContent; //列渲染
            }}
        );
        var cols = [];
        cols.push(row1);
        return cols;
    }

    reloadData();//初始化加载表格

    var active = {
        reload:function(){
            var optionsData={};
            var searchValues=$("#searchValues").val();
            var dateStart=$("#dateStart").val();
            var dateEnd=$("#dateEnd").val();
            var companyName=$("#companyName").val();
            var approveState=$("#approveState").val();
            var userName=$("#userName").val();
            optionsData.searchValues=searchValues;
            optionsData.dateStart=dateStart;
            optionsData.dateEnd=dateEnd;
            optionsData.companyName=companyName;
            optionsData.approveState=approveState;
            optionsData.userName=userName;
            console.log(optionsData);
            sessionStorage.removeItem('BORROWMONEY_FRAME_CONTRACT_SEARCH');
            reloadData(optionsData);
        },
        addContract:function () {//新增合同
            var url=ctx+"/borrowMoneyFrameContract/addBorrowMoneyFrameContractPage";
            parent.redirectTo('append',url,'新建借佣框架协议');
        },
        reset:function () {
            $("#searchValues").val('');
            $("#dateStart").val('');
            $("#dateEnd").val('');
            $("#companyName").val('');
            $("#approveState").val('');
            $("#userName").val('');
            form.render();
            active['reload'].call(this);
        }
    };

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData){
        var index = parent.layer.load(2,{shade: 0.1});

        var sessionData=sessionStorage.getItem('BORROWMONEY_FRAME_CONTRACT_SEARCH');
        var pageIndex=1;
        if(sessionData!=null && sessionData!=''){
            optionsData=JSON.parse(sessionData);
            pageIndex=JSON.parse(sessionData).curr;
            $("#companyName").val(optionsData.companyName);
            $("#searchValues").val(optionsData.searchValues);
            $("#dateStart").val(optionsData.dateStart);
            $("#dateEnd").val(optionsData.dateEnd);
            $("#approveState").val(optionsData.approveState);
            $("#userName").val(optionsData.userName);
            form.render();
        }

        table.reload('contentReload', {
            url:ctx + '/borrowMoneyFrameContract/getBorrowMoneyFrameContractList',
            cols:setCols(),
            height: window.innerHeight-$(".layui-table-view").offset().top-10,
            where: optionsData,
            page:{
                curr: pageIndex //重新从第 1 页开始
            },
            done:function (res, curr, count) {
                parent.layer.close(index);
                if(!optionsData){
                    optionsData={};
                }
                optionsData.curr=curr;
                sessionStorage.setItem("BORROWMONEY_FRAME_CONTRACT_SEARCH",JSON.stringify(optionsData));
            }
        });
    }
    table.on('tool(mainTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
        if(layEvent == 'detail'){
            //查看
            showDetail(data.id);
        }
    })
});

//查看
function showDetail(id){
    var url=ctx+"/borrowMoneyFrameContract/borrowMoneyFrameContractDetailPage?id="+id;
    parent.redirectTo('append',url,'借佣框架协议详情');
}
//修改
function update(id){
    var url=ctx+"/borrowMoneyFrameContract/addBorrowMoneyFrameContractPage?id="+id;
    parent.redirectTo('append',url,'编辑借佣框架协议');
}
//作废
function cancel(id,companyNo){
    parent.layer.confirm("确认作废吗？",{icon: 3, title:'提示'},function(){
        parent.layer.load(2);
        $.ajax({
            url: ctx + '/borrowMoneyFrameContract/cancel',
            type: 'post',
            data:{id:id,companyNo:companyNo},
            dataType: 'json',
            success: function (data) {
                parent.layer.closeAll();
                console.log(data);
                if (data.returnCode != 200){
                    parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                }else{
                    parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'},function(){
                        parent.layer.closeAll();
                        window.location.reload();
                    });
                }
            }
        });
    });
}
