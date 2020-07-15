layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;

    initRealityCityNoList();

    form.on('select(realityCityNo)', function(data){
        initDistrictNoList(data.value);
    });

    laydate.render({
        elem: '#dateStart',
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
    });

    laydate.render({
        elem: '#dateEnd',
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
            {field: 'projectNo',title: '项目编号',width:120, align: 'center',event:'detail',style:'cursor: pointer;color:#01AAED'},
            {field: 'estateNm',title: '楼盘名称',width:250, align: 'left'},
            {field: 'realityCityNm',title: '楼盘城市',width:90, align: 'center'
                ,templet: function(d){
                    var str = "";
                    if(d.estatePosition==0){
                        str = d.realityCityNm;
                    }
                    if(d.estatePosition==1){
                        str = d.countryNm;
                    }
                    return str;
                }
            },
            {field: 'partnerNm',title: '合作方名称',width:300, align: 'left'},
            {field: 'projectStatus',title: '项目状态',width:90, align: 'center'
                 ,templet: function(d){
                    var statusName = "";
                    if(d.projectStatus == "20301"){
                        statusName = "跟单";
                    }else if(d.projectStatus == "20302"){
                        statusName = "签约";
                    }else if(d.projectStatus == "20303"){
                        statusName = "结案";
                    }else if(d.projectStatus == "20304"){
                        statusName = "取消跟单";
                    }else if(d.projectStatus == "20305"){
                        statusName = "";
                    }else if(d.projectStatus == "20306"){
                        statusName = "立项";
                    }
                    return statusName;
                }
            },
            {field: 'auditStatusName',title: '审核状态',width:90, align: 'center'
                ,templet: function(d){
                    var auditStatusName = "";
                    if(d.auditStatus == "12904"){
                        auditStatusName = "-";
                    }else {
                        auditStatusName = d.auditStatusName;
                    }
                    return auditStatusName;
                }
            },
            {field: 'empName',title: '创建人',width:90, align: 'center'},
            {field: 'crtDt',title: '创建日期',width:110, align: 'center'
                ,templet: function(d){
                    var crtDtStr = "";
                    if(d.crtDt){
                        crtDtStr = d.crtDt.substring(0,10);
                    }
                    return crtDtStr;
                }
            },
            {title: '操作', width: 350, align: 'left',toolbar: '#toolMainTable',fixed: 'right'}
        );
        var cols = [];
        cols.push(row1);
        return cols;
    }

    reloadData();//初始化加载表格

    var active = {
        reload:function(){
            var optionsData={};

            var projectDepartmentId = $("#projectDepartmentId").val();
            var estateNm = $("#estateNm").val();
            var auditStatus = $("#auditStatus").val();
            var projectStatus = $("#projectStatus").val();
            var partnerNm = $("#partnerNm").val();

            optionsData.projectDepartmentId = projectDepartmentId;
            optionsData.estateNm = estateNm;
            optionsData.auditStatus = auditStatus;
            optionsData.projectStatus = projectStatus;
            optionsData.partnerNm = partnerNm;
            sessionStorage.removeItem('ESTATE_LIST_SEARCH');
            reloadData(optionsData);
        },
        reset:function(){
            document.getElementById("searchForm").reset();
            active['reload'].call(this);
        },
        addPmlsEstate:function () {
            window.parent.redirectTo('append',"/pmlsEstate/addPmlsEstateView",'新建项目');
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
            window.parent.redirectTo('append',"/pmlsEstate/"+data.id+"?type=detail&projectNo="+data.projectNo,'项目详情');
        }else if(layEvent == 'edit'){
            //编辑
            window.parent.redirectTo('append',"/pmlsEstate/u/"+data.id +"/edit",'编辑项目');
        }else if(layEvent == 'editInfo'){
            //编辑项目楼盘信息
            window.parent.redirectTo('append',"/pmlsEstate/estateEditInfo/"+data.id,'编辑项目楼盘信息');
        }else if(layEvent == 'editPartner'){
            //合作信息维护
            window.parent.redirectTo('append',"/pmlsEstate/u/"+data.id +"/editPartner",'合作信息维护');
        }else if(layEvent == 'publish'){
            //发布
            var id =data.id;
            parent.layer.open({
                type: 2,
                title: '发布项目',
                area: ['550px', '570px'],
                content: '/pmlsEstate/torelease?id='+id
                ,scrollbar: false
                ,resize:false
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    console.log(formData);
                    if(formData!=null) {
                        formData.id = id;
                        var loadIndex = parent.layer.load(2);
                        $.ajax({
                            url: BASE_PATH + '/pmlsEstate/release/'+id,
                            type: 'post',
                            data:formData,
                            dataType: 'json',
                            success: function (data) {
                                console.log(data);
                                parent.layer.close(loadIndex);
                                if (data.returnCode == -1){
                                    parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                                } else {
                                    parent.layer.close(index);
                                    parent.layer.alert("发布项目成功！", {icon: 1, title:'提示'},function(){
                                        parent.layer.closeAll();
                                        active['reload'].call(this);
                                    });
                                }
                            }
                        });
                    }
                    //end
                }
            });
        }else if(layEvent == 'unPublish'){
            //下架
            var id = data.id;
            window.parent.layer.prompt({
                formType: 2,
                value: '',
                title: '下架说明',
                area: ['500px', '300px'], //自定义文本域宽高
                maxlength: 400
            }, function(value, index, elem){
                window.parent.layer.close(index);
                var formData = {releaseOffMemo:value};
                var loadIndex = parent.layer.load(2);
                $.ajax({
                    url: BASE_PATH + '/pmlsEstate/down/'+id,
                    type: 'post',
                    data:formData,
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        parent.layer.close(loadIndex);
                        if (data.returnCode == -1){
                            parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                        } else {
                            parent.layer.alert("下架项目成功！", {icon: 1, title:'提示'},function(){
                                parent.layer.closeAll();
                                active['reload'].call(this);
                            });
                        }
                    }
                });
            });
        }else if(layEvent == 'revocation'){
            //撤回
            window.parent.layer.confirm('确认撤回？',{icon: 3, title:'提示'}, function(index){
                layer.close(index);
                var loadIndex = parent.layer.load(2);
                $.ajax({
                    url: BASE_PATH + '/pmlsEstate/backoff/'+data.id,
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        parent.layer.close(loadIndex);
                        if (data.returnCode == -1){
                            parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                        } else {
                            parent.layer.alert("撤回成功！", {icon: 1, title:'提示'},function(){
                                parent.layer.closeAll();
                                active['reload'].call(this);
                            });
                        }
                    }
                });
            });
        }else if(layEvent == 'startProject'){
            //跟单
            window.parent.layer.confirm('确认跟单？',{icon: 3, title:'提示'}, function(index){
                layer.close(index);
                var loadIndex = parent.layer.load(2);
                $.ajax({
                    url: BASE_PATH + '/pmlsEstate/startProject/'+data.id,
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        parent.layer.close(loadIndex);
                        if (data.returnCode == -1){
                            parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                        } else {
                            parent.layer.alert("跟单成功！", {icon: 1, title:'提示'},function(){
                                parent.layer.closeAll();
                                active['reload'].call(this);
                            });
                        }
                    }
                });
            });
        }else if(layEvent == 'cancelProject'){
            //取消跟单
            window.parent.layer.confirm('确认取消跟单？',{icon: 3, title:'提示'}, function(index){
                layer.close(index);
                var loadIndex = parent.layer.load(2);
                $.ajax({
                    url: BASE_PATH + '/pmlsEstate/startCancel/'+data.id,
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        parent.layer.close(loadIndex);
                        if (data.returnCode == -1){
                            parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                        } else {
                            parent.layer.alert("取消跟单成功！", {icon: 1, title:'提示'},function(){
                                parent.layer.closeAll();
                                active['reload'].call(this);
                            });
                        }
                    }
                });
            });
        }else if(layEvent == 'closeProject'){
            //结案
            window.parent.layer.confirm('确认结案？',{icon: 3, title:'提示'}, function(index){
                layer.close(index);
                var loadIndex = parent.layer.load(2);
                $.ajax({
                    url: BASE_PATH + '/pmlsEstate/endProject/'+data.id,
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        parent.layer.close(loadIndex);
                        if (data.returnCode == -1){
                            parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                        } else {
                            parent.layer.alert("结案成功！", {icon: 1, title:'提示'},function(){
                                parent.layer.closeAll();
                                active['reload'].call(this);
                            });
                        }
                    }
                });
            });
        }else if(layEvent == 'cancelCloseProject'){
            //取消结案
            window.parent.layer.confirm('确认取消结案？',{icon: 3, title:'提示'}, function(index){
                layer.close(index);
                var loadIndex = parent.layer.load(2);
                $.ajax({
                    url: BASE_PATH + '/pmlsEstate/endCancel/'+data.id,
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        parent.layer.close(loadIndex);
                        if (data.returnCode == -1){
                            parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                        } else {
                            parent.layer.alert("取消结案成功！", {icon: 1, title:'提示'},function(){
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

        var sessionData=sessionStorage.getItem('ESTATE_LIST_SEARCH');
        var pageIndex=1;
        if(sessionData!=null && sessionData!=''){
            optionsData=JSON.parse(sessionData);
            pageIndex=JSON.parse(sessionData).curr;
            $("#projectDepartmentId").val(optionsData.projectDepartmentId);
            $("#estateNm").val(optionsData.estateNm);
            $("#auditStatus").val(optionsData.auditStatus);
            $("#projectStatus").val(optionsData.projectStatus);
            $("#partnerNm").val(optionsData.partnerNm);
            form.render();
        }

        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsEstate/list',
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
                sessionStorage.setItem("ESTATE_LIST_SEARCH",JSON.stringify(optionsData));
            }
        });
    }


    //初始化楼盘城市
    function initRealityCityNoList() {
        var url = BASE_PATH + "/cityCascade/queryCityListByIsService";
        var params = {};
        ajaxGet(url, params, function (data) {
            var result = "<option value=''>请选择城市</option>";
            $.each(data.returnValue, function(n, value) {
                result += "<option value='" + value.cityNo + "'>"
                    + value.cityName + "</option>";
            });
            $("#realityCityNo").html('');
            $("#realityCityNo").append(result);
            form.render('select'); //刷新select选择框渲染
        }, function () {
        });
    }

    //初始化楼盘城市—区域
    function initDistrictNoList(realityCityNo) {
        var url = BASE_PATH + "/linkages/city/" + realityCityNo;
        var params = {};
        var result = "<option value=''>请选择区域</option>";
        if(realityCityNo){
            ajaxGet(url, params, function (data) {
                $.each(data.returnValue, function(n, value) {
                    result += "<option value='" + value.districtNo + "'>"
                        + value.districtName + "</option>";
                });
                $("#districtNo").html('');
                $("#districtNo").append(result);
                form.render('select'); //刷新select选择框渲染
            }, function () {
            });
        }else{
            $("#districtNo").html('');
            $("#districtNo").append(result);
            form.render('select'); //刷新select选择框渲染
        }
    }


});

