layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;

    var createDateMinObj = laydate.render({
        elem: '#createDateMin',
        trigger: 'click',
        done: function (value, date, endDate) {
            var minDate  = new Date(value);
            var minTemp  = {
                year: minDate.getFullYear(),
                month: minDate.getMonth(),
                date: minDate.getDate(),
                hours: minDate.getHours(),
                minutes: minDate.getMinutes(),
                seconds: minDate.getSeconds()
            };
            $.extend(true, createDateMaxObj.config, {min: minTemp});
        }
    });

    var createDateMaxObj = laydate.render({
        elem: '#createDateMax',
        trigger: 'click',
        done: function (value, date, endDate) {
            var maxDate  = new Date(value);
            var maxTemp  = {
                year: maxDate.getFullYear(),
                month: maxDate.getMonth(),
                date: maxDate.getDate(),
                hours: maxDate.getHours(),
                minutes: maxDate.getMinutes(),
                seconds: maxDate.getSeconds()
            };
            $.extend(true, createDateMinObj.config, {max: maxTemp});
        }
    });

    form.render('select'); //刷新select选择框渲染

    table.render({
        elem: '#mainTable'
        , cols: setCols()
        , id: 'contentReload'
        , height: 'full'
        , even: false //开启隔行背景
        , page: true
        , limits: [10,20,30,50,100]
        , limit: 10 //默认采用60
        , method: 'post'
        , loading:false
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
    function setCols() {
        var row1 = [];
        row1.push(
            {field: 'contractNo',title: '分销合同编号',width:150, align: 'center',event:'detail',style:'cursor: pointer;color:#01AAED'},
            {field: 'companyNo',title: '渠道公司编号',width:150, align: 'center'},
            {field: 'companyName',title: '渠道公司',width:250, align: 'left'},
            {field: 'projectName',title: '项目名称',width:250, align: 'left'},
            {field: 'approveStateName',title: '审核状态',width:100, align: 'center'},
            {field: 'validStatusNm',title: '生效状态',width:100, align: 'center',templet: function(row) {
                    var showContent='';
                    if(row.approveState=='10403'){
                        showContent=row.validStatusNm;
                    }else{
                        showContent='-';
                    }
                    return showContent; //列渲染
                }},
            {field: 'userNameCreate',title: '创建人',width:100, align: 'center'},
            {field: 'dateCreate',title: '创建日期',width:110, align: 'center'},
            {title: '操作', width: 180, align: 'left',toolbar: '#toolMainTable',fixed: 'right'}
        );
        var cols = [];
        cols.push(row1);
        return cols;
    }

    reloadData();//初始化加载表格

    var active = {
        reload:function(){
            var optionsData={};
            var contractNo = $("#contractNo").val();
            var createDateMin = $("#createDateMin").val();
            var createDateMax = $("#createDateMax").val();
            var companyName = $("#companyName").val();
            var approveState = $("#approveState").val();
            var createUserName = $("#createUserName").val();
            var projectName = $("#projectName").val();
            var oaNo        = $("#oaNo").val();

            optionsData.contractNo = contractNo;
            optionsData.createDateMin = createDateMin;
            optionsData.createDateMax = createDateMax;
            optionsData.companyKey = companyName;
            optionsData.approveState = approveState;
            optionsData.createUserName = createUserName;
            optionsData.projectName = projectName;
            optionsData.oaNo        = oaNo;
            sessionStorage.removeItem('COOPERATION_SEARCH');
            reloadData(optionsData);
        },
        reset:function(){
            document.getElementById("searchForm").reset();
            active['reload'].call(this);
        },
        addPmlsCooperation:function () {
            window.parent.redirectTo('append',"/cooperationController/addCooperationView",'新建联动合作协议');
        }
    };

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    table.on('tool(mainTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
        if(layEvent == 'detail'){
            //查看
            window.parent.redirectTo('append',"/cooperationController/cooperationInfoPage?id="+data.id,'联动分销合同详情');
        }else if(layEvent == 'edit'){
            //编辑
            window.parent.redirectTo('append',"/cooperationController/updateCooperationPage?id="+data.id,'编辑联动分销合同');
        }else if(layEvent == 'invalid'){
            //作废
            window.parent.layer.confirm('确认作废？',{icon: 3, title:'提示'}, function(index){
                layer.close(index);
                var loadIndex = layer.load(2);
                var formData = {
                    id:data.id,
                    approveState:10405
                };
                $.ajax({
                    url: BASE_PATH + '/cooperationController/invalidCooperation',
                    type: 'post',
                    data:formData,
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        layer.close(loadIndex);
                        if (data.returnCode == -1){
                            parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                        } else {
                            parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'},function(){
                                parent.layer.closeAll();
                                active['reload'].call(this);
                            });
                        }
                    }
                });
            });
        }
    });

    function reloadData(optionsData){
        var index = parent.layer.load(2,{shade: 0.1});

        var sessionData=sessionStorage.getItem('COOPERATION_SEARCH');
        var pageIndex=1;
        if(sessionData!=null && sessionData!=''){
            optionsData=JSON.parse(sessionData);
            pageIndex=JSON.parse(sessionData).curr;
            $("#companyKey").val(optionsData.companyKey);
            $("#contractNo").val(optionsData.contractNo);
            $("#createDateMin").val(optionsData.createDateMin);
            $("#createDateMax").val(optionsData.createDateMax);
            $("#approveState").val(optionsData.approveState);
            $("#createUserName").val(optionsData.createUserName);
            $("#projectName").val(optionsData.projectName);
            $("#oaNo").val(optionsData.oaNo);
            form.render();
        }

        table.reload('contentReload', {
            url: BASE_PATH + '/cooperationController/getCooperationList',
            cols:setCols(),
            height: window.innerHeight-$(".layui-table-view").offset().top-10,
            where: optionsData,
            page:{
                curr: pageIndex //重新从第 1 页开始
            },
            done: function(res, curr, count){
                parent.layer.close(index);
                if(!optionsData){
                    optionsData={};
                }
                optionsData.curr=curr;
                sessionStorage.setItem("COOPERATION_SEARCH",JSON.stringify(optionsData));
            }
        });
    }


});

