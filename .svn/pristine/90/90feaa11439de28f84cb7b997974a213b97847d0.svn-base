var table;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        layer = layui.layer,
        formSelects = layui.formSelects,
        $ = layui.$;
    table = layui.table;

    initCityListService();
    /**
     * 初始化-人员权限城市列表
     * @param checkValue
     * @param callback
     * @returns
     */
    function initCityListService(cityNo,userJsonDto,callback) {
    	var url = BASE_PATH + "/personnelPermissions/getCityList";
    	var params ="";
    	if(cityNo!=null){
    		params = {cityNo:cityNo};
    	}
    	ajaxGet(url, params, function (data) {
    		var result = "<option value=''>请选择</option>";
    		$.each(data.returnData, function (n, value) {
    			if(userJsonDto!=null && cityNo==value.cityNo){
    				result += "<option value='" + value.cityNo + "' selected>"+ value.cityName + "</option>";
    			}else{
    				result += "<option value='" + value.cityNo + "'>"+ value.cityName + "</option>";
    			}
    		});
    		$("#cityNo").html('');
    		$("#cityNo").append(result);
    		form.render();
//    		callback ? callback() : $.noop();
    	}, function () {
    	});
    }

    form.render('select');

    form.on('select(cityNo)', function (data) {
        console.log("form.on('select(cityNo)')");
        initCenterGroup(data.value);
    });

    //初始化-归属中心
    function initCenterGroup(cityNo) {
    	var url = BASE_PATH + "/personnelPermissions/getCenterListByCityNo";
        var params = {cityNo: cityNo};
        ajaxGet(url, params, function (data) {
            var list = data.returnData;
            var allCenterIds = [];
            var result = [];
            if (list != null && list.length > 0) {
                for (var i = 0; i < list.length; i++) {
                    result.push({"name": list[i].centerName,"value": list[i].centerId});
                    allCenterIds.push(list[i].centerId);
                }
            }
            formSelects.data('centerGroup', 'local', {arr: result});

            formSelects.btns('centerGroup', [
                {
                    icon: "xm-iconfont icon-quanxuan",
                    name: '全选',
                    click: function (id) {
                        //点击后的操作
                    	layui.formSelects.value(id, allCenterIds);

                    }
                }, {
                    icon: "xm-iconfont icon-qingkong",
                    name: '清空',
                    click: function (id) {
                        //点击后的操作
                        formSelects.value(id, [])
                    }
                }
            ]);

        }, function () {
        });

    }

    form.render('select'); //刷新select选择框渲染
    table.render({
        elem: '#mainTable'
        , cols: setCols()
        , id: 'contentReload'
        , height: 'full'
        , even: false //开启隔行背景
        , page: true
        , limits: [10,20,30]
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

    reloadData({});//初始化加载表格

    var active = {
        reload:function(){
            var optionsData=getParam();
            console.log(optionsData);
            reloadData(optionsData);
        },
        addUser:function () {//新增人员
            addUserInfo();
        },
        reset:function () {
            $("#userCode").val('');
            $("#cityNo").val('');
            resetValue('centerGroup');
            form.render('select');
            active.reload();
        }
    };

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

  //获取参数
    function getParam() {
    	var optionsData = {};
    	var cityNo = $("#cityNo").val();//城市
        var centerGroup = getXMSelectOutIds(formSelects.value('centerGroup'));//中心
        var userCode = trimStr($("#userCode").val());//人员编号、姓名
        if(isIncludeBlank(userCode)){
        	parent.layer.alert('人员工号、姓名中间有空格，请重新输入！', {
                icon: 2,
                title: '提示'
            });
        	return;
        }
        optionsData.cityNo = cityNo;
        optionsData.centerGroup = centerGroup;
        optionsData.userCode = userCode;
        return optionsData;
    }

    //重置
    function resetValue(id) {
        formSelects.data(id, 'local', {arr: []});
    }
});

//加载列表
function setCols() {
    var row1 = [];
    row1.push(
    	{type:'numbers',title: '序号',width:80, align: 'center'},
        {field: 'userCode',title: '人员工号',width:120, align: 'center'},
        {field: 'userName',title: '人员姓名',width:120, align: 'center'
        },
        {field: 'cityName',title: '业绩城市',width:120, align: 'center'},
        {field: 'centerName',title: '归属中心',width:180, align: 'center'
        },
        {field: 'userIdCreateName',title: '创建人',width:150, align: 'center'
        },
        {field: 'dateCreate',title: '创建日期',width:150, align: 'center'},
        {title: '操作', width:200,align: 'center',fixed:'right',templet: function(row) {
                var showContent='';
                showContent+='<a class="layui-btn layui-btn-normal layui-btn-mini" style="width:60px;" onclick="update(' + row.id + ')">编辑</a>';
                showContent+='<a class="layui-btn layui-btn-danger layui-btn-mini" style="width:60px;" onclick="deleteUser(' + row.id + ')">删除</a>';
                return showContent; //列渲染
        }}
    );
    var cols = [];
    cols.push(row1);
    return cols;
}

//加载数据
function reloadData(optionsData){
    var index = parent.layer.load(2,{shade: 0.1});
    table.reload('contentReload', {
        url:ctx + '/personnelPermissions/queryPersonnelPermissionsList',
        cols:setCols(),
        where: optionsData,
        page:{
            curr: 1 //重新从第 1 页开始
        },
        done:function () {
            parent.layer.close(index);
        }
    });
}

//新建
function addUserInfo(){
    parent.layer.open({
        type: 2,
        btn: ['确认', '取消'],
        title: '新建人员权限',
        area: ['500px', '421px'],
        content: ctx + '/personnelPermissions/addUserPage'
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            if(formData!=null){
                var loadIndex = parent.layer.load(2, {shade: [0.1]});
                $.ajax({
                    url: ctx + '/personnelPermissions/addUser',
                    type: 'post',
                    data:formData,
                    dataType: 'json',
                    success: function (data) {
                        parent.layer.close(loadIndex);
                        if (data.returnCode != 200){
                                parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                        }else{
                                parent.msgProp("操作成功！");
                                parent.layer.close(index);
                                reloadData();
                        }
                    }
                });
            }
        }
    });
}

//删除
function deleteUser(id){
	parent.layer.confirm("确认删除吗？",{icon: 3, title:'提示'},function(){
        parent.layer.load(2);
        $.ajax({
            url: ctx + '/personnelPermissions/deleteUser',
            type: 'post',
            data:{id:id},
            dataType: 'json',
            success: function (data) {
                parent.layer.closeAll();
                console.log(data);
                if (data.returnCode != 200){
                    parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                }else{
                    parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'},function(){
                        parent.layer.closeAll();
                        reloadData();
                    });
                }
            }
        });
    });
}

//修改
function update(id){
	parent.layer.open({
        type: 2,
        btn: ['确认', '取消'],
        title: '编辑人员权限',
        area: ['500px', '421px'],
        content: ctx + '/personnelPermissions/addUserPage?id='+id,
//        skin:'.to-fix-select',
        yes: function(index, layero){
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            console.log(formData);
            if(formData!=null){
                parent.layer.close(index);//关闭弹窗
                parent.layer.load(2);
                $.ajax({
                    url: ctx + '/personnelPermissions/updateUser',
                    type: 'post',
                    data:formData,
                    dataType: 'json',
                    success: function (data) {
                        parent.layer.closeAll();
                        console.log(data);
                        if (data.returnCode != 200){
                            parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                        }else{
                            parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'},function(){
                                parent.layer.closeAll();
                                reloadData();
                            });
                        }
                    }
                });
            }
        }
    });
}
