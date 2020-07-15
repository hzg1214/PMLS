var table;
var form;
var cityNoList;
var formSelects;
var partnerLists;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        layer = layui.layer,
        $ = layui.$;
    form = layui.form;
    table = layui.table;
    formSelects = layui.formSelects;

    if(citylist!=''){
    	//cityNoList=eval('(' + window.citylist + ')');
        cityNoList=eval('(' + window.citylist.replace(/[\r\n]/g,"\\n") + ')');
    }
//    if(partnerList!=''){
//    	partnerLists=eval('(' + window.partnerList + ')');
//    }
    
    //初始化-城市
    initCityNo(cityNoList);
    
    //初始化-合作类型
//    initPartner(partnerList);
    
    /**
     * 初始化城市
     * @returns
     */
    function initCityNo(cityNoList){
        var allCityList = [];
        var result = [];
        if (cityNoList != null && cityNoList.length > 0) {
            for (var i = 0; i < cityNoList.length; i++) {
                result.push({"name": cityNoList[i].cityName, "value": cityNoList[i].cityNo});
                allCityList.push(cityNoList[i].cityNo);
            }
        }
        formSelects.data('cityNo', 'local', {arr: result});

        formSelects.btns('cityNo', [
            {
                icon: "xm-iconfont icon-quanxuan",
                name: '全选',
                click: function (id) {
                    //点击后的操作
                    layui.formSelects.value(id, allCityList);

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
    }
    
    /**
     * 初始化-合作类型
     * @returns
     */
//    function initPartner(partnerLists){
//    	var result = "<option value=''>请选择</option>";
//		$.each(partnerLists, function (n, value) {
//			result += "<option value='" + value.dicCode + "'>"+ value.dicValue + "</option>";
//		});
//		$("#partner").html('');
//		$("#partner").append(result);
//		form.render();
//    }
    
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

    reloadData();//初始化加载表格

    var active = {
        reload:function(){
            var optionsData={};
            var developerCode= trimStr($("#developerCode").val());
            var developerName= trimStr($("#developerName").val());
            var developerBrandCode= trimStr($("#developerBrandCode").val());
            var cityNos = getXMSelectIds(formSelects.value('cityNo'));//城市
            optionsData.cityNoStr = formSelects.value('cityNo', 'val');
            var partner= $("#partner").val();
            if(isIncludeBlank(developerCode)){
            	parent.layer.alert('中间有空格，请重新输入！',{icon: 2, title:'提示'});
            	return false;
            }
            if(isIncludeBlank(developerName)){
            	parent.layer.alert('中间有空格，请重新输入！',{icon: 2, title:'提示'});
            	return false;
            }
            if(isIncludeBlank(developerBrandCode)){
            	parent.layer.alert('中间有空格，请重新输入！',{icon: 2, title:'提示'});
            	return false;
            }
            optionsData.developerCode=developerCode;
            optionsData.developerName=developerName;
            optionsData.developerBrandCode=developerBrandCode;
            optionsData.cityNos=cityNos;
            optionsData.partner=partner;
            console.log(optionsData);
            sessionStorage.removeItem('DEVELOPER_SEARCH');
            reloadData(optionsData);
        },
        addDeveloper:function () {//新增合作方
            parent.redirectTo('append','/developer/addDeveloperPage','新建合作方');
        },
        reset:function () {
            $("#developerCode").val('');
            $("#developerName").val('');
            $("#partner").val('');
            $("#developerBrandCode").val('');
//            resetValue('cityNo');
            formSelects.value('cityNo', []);
            form.render('select');
            active.reload();
        }
    };

  //重置
    function resetValue(id) {
        formSelects.data(id, 'local', {arr: []});
        initCityNo(cityNoList);
    }
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
            showDetail(data.id);
        }
    })
});

function reloadData(optionsData){
	var index = parent.layer.load(2,{shade: 0.1});
	
	  var sessionData=sessionStorage.getItem('DEVELOPER_SEARCH');
      var pageIndex=1;
      if(sessionData!=null && sessionData!=''){
          optionsData=JSON.parse(sessionData);
          pageIndex=JSON.parse(sessionData).curr;
          $("#developerCode").val(optionsData.developerCode);
          $("#developerName").val(optionsData.developerName);
          $("#developerBrandCode").val(optionsData.developerBrandCode);
          $("#partner").val(optionsData.partner);
          formSelects.value('cityNo', optionsData.cityNoStr);
          form.render();
      }
	
	table.reload('contentReload', {
		url:ctx + '/developer/getDeveloperList',
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
            sessionStorage.setItem("DEVELOPER_SEARCH",JSON.stringify(optionsData));
		}
	});
}

function setCols() {
    var row1 = [];
    row1.push(
        {field: 'developerCode',title: '合作方编号',width:100, align: 'center',event:'detail',style:'cursor: pointer;color:#01AAED'},
        {field: 'developerName',title: '合作方名称',width:260, align: 'center'
        	, templet: function (d) {
                return "<div style='text-align: left'>" + d.developerName + "</div>";
            }
        },
        {field: 'developerBrandName',title: '合作方品牌',width:180, align: 'center'},
        {field: 'partnerStr',title: '合作方类型',width:180, align: 'center'},
        {field: 'addressDetail',title: '注册地址',width:250, align: 'center'
        	, templet: function (d) {
                return "<div style='text-align: left'>" + d.addressDetail + "</div>";
            }
        },
        {field: 'userCreateName',title: '创建人',width:80, align: 'center'},
        {field: 'dateCreate',title: '创建日期',width:120, align: 'center'},
        {title: '操作', width:200,align: 'left',fixed:'right',templet: function(row) {
                var showContent='';
                showContent+='<a class="layui-btn layui-btn-mini"  onclick="showDetail(' + row.id + ')">查看</a>';
                showContent+='<a class="layui-btn layui-btn-normal layui-btn-mini"  onclick="update(' + row.id + ')">编辑</a>';
                showContent+='<a class="layui-btn layui-btn-danger layui-btn-mini"  onclick="del(' + row.id + ')">删除</a>';
                return showContent; //列渲染
        }}
    );
    var cols = [];
    cols.push(row1);
    return cols;
}


//查看
function showDetail(id){
    var url =ctx+"/developer/developerDetailPage?id="+id;
    parent.redirectTo('append',url,'合作方详情');
}
//编辑
function update(id){
    var url =ctx+"/developer/addDeveloperPage?id="+id;
    parent.redirectTo('append',url,'编辑合作方');
}

//删除
function del(id){
    parent.layer.confirm("确认删除吗？",{icon: 3, title:'提示'},function(){
        parent.layer.load(2);
        $.ajax({
            url: ctx + '/developer/deleteDeveloper',
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

