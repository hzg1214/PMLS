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
            {field: 'jyhkplanNo',title: '渠道还款计划编号',width:160, align: 'center',event:'detail',style:'cursor: pointer;color:#01AAED'},
            {field: 'jyapplyNo',title: '借佣申请编号',width:160, align: 'center'},
            {field: 'companyName',title: '渠道公司',width:250, align: 'left'},
            {field: 'projectName',title: '楼盘名称',width:250, align: 'left'},
            {title: '结算金额(元)',width:150, align: 'right',templet:function (row) {
                    return formatMoney(row.jsTotalAmount);
                }},
            {title: '借佣金额(元)',width:150, align: 'right',templet:function (row) {
                    return formatMoney(row.jyTotalAmount);
                }},
            {field: 'jyMonths',title: '借佣周期(月)' ,width:150,align: 'center'},
            {field: 'jkDate',title: '借款日' ,width:150,align: 'center'},
            {field: 'yhkDate',title: '应还款日' ,width:150,align: 'center'},
            {field: 'yqFlag',title: '是否逾期' ,width:150,align: 'center',templet: function(row) {
                    var showContent='';
                    if(row.yqFlag==0){
                        showContent='否';
                    }else{
                        showContent='是';
                    }
                    return showContent; //列渲染
                }},
            {field: 'planStatusName',title: '还款状态' ,width:150,align: 'center'},
            {title: '操作',width:100,align: 'center',fixed:'right',templet: function(row) {
                    var showContent='';
                    showContent+='<a class="layui-btn layui-btn-mini" onclick="showDetail(' + row.id + ')">查看</a>';
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
            var jyapplyNo=$("#jyapplyNo").val();
            var companyName=$("#companyName").val();
            var projectName=$("#projectName").val();
            var yqFlag=$("#yqFlag").val();
            var planStatus=$("#planStatus").val();
            optionsData.jyapplyNo=jyapplyNo;
            optionsData.companyName=companyName;
            optionsData.projectName=projectName;
            optionsData.yqFlag=yqFlag;
            optionsData.planStatus=planStatus;
            console.log(optionsData);
            sessionStorage.removeItem('HK_PLAN_SEARCH');
            reloadData(optionsData);
        },
        reset:function () {
            $("#jyapplyNo").val('');
            $("#companyName").val('');
            $("#projectName").val('');
            $("#yqFlag").val('');
            $("#planStatus").val('');
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

        var sessionData=sessionStorage.getItem('HK_PLAN_SEARCH');
        var pageIndex=1;
        if(sessionData!=null && sessionData!=''){
            optionsData=JSON.parse(sessionData);
            pageIndex=JSON.parse(sessionData).curr;
            $("#jyapplyNo").val(optionsData.jyapplyNo);
            $("#companyName").val(optionsData.companyName);
            $("#projectName").val(optionsData.projectName);
            $("#yqFlag").val(optionsData.yqFlag);
            $("#planStatus").val(optionsData.planStatus);
            form.render();
        }

        table.reload('contentReload', {
            url:ctx + '/borrowMoneyController/getHkPlanList',
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
                sessionStorage.setItem("HK_PLAN_SEARCH",JSON.stringify(optionsData));
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
    var url=ctx+"/borrowMoneyController/hkPlanDetailPage?id="+id;
    parent.redirectTo('append',url,'渠道还款计划详情');
}
