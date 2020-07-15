
var layout;
var layer;
layui.use(['tree', 'layer','table', 'form'], function() {
    layer = layui.layer,
        table = layui.table,
        $ = layui.jquery;
    var form = layui.form;

    var addPermission = NullToZero($("#addPermission").val());
    var uptPermission = NullToZero($("#uptPermission").val());
    var delPermission = NullToZero($("#delPermission").val());

     layout = [
        { name: '编号',field: 'developerBrandCode', treeNodes: false, headerClass: 'value_col', colClass: 'value_col', style: 'width: 10%;text-align:center;' },
        { name: '合作方品牌名称',field: 'developerBrandName', treeNodes: true, headerClass: 'value_col', colClass: 'value_col', style: 'width: 22%;' },
        { name: '类型',field: 'partnerStr', treeNodes: false, headerClass: 'value_col', colClass: 'value_col', style: 'width: 8%;text-align:center;' },
        { name: '大客户',field: 'bigCustomerFlag', treeNodes: false, headerClass: 'value_col', colClass: 'value_col', style: 'width: 10%;text-align:center;'
        	, render: function(d)  {
        		if(d.bigCustomerFlag=='22601'){
        			return '是';
        		}else{
        			return '否';
        		}
        			
            }
        },
        { name: '垫佣甲方',field: 'isYjDy', treeNodes: false, headerClass: 'value_col', colClass: 'value_col', style: 'width: 10%;text-align:center;'
        	, render: function(d) {
        		if(d.isYjDy=='1'){
        			return '是';
        		}else{
        			return '否';
        		}
        			
            }
        },
        { name: '创建人',field: 'createUserName', treeNodes: false, headerClass: 'value_col', colClass: 'value_col', style: 'width: 8%;text-align:center;' },
        { name: '创建日期',field: 'dateCreate', treeNodes: false,fixed:'right', headerClass: 'value_col', colClass: 'value_col', style: 'width: 10%;text-align:center;' },
        {
            name: '操作',
            headerClass: 'value_col',
            colClass: 'value_col',
            style: 'width: 28%',
            render: function(row) {
                var showContent='';
                if (addPermission == "1") {
                    showContent+='<a class="layui-btn layui-btn-mini" style="width:60px;" onclick="add(' + row.id + ')">添加下级</a>';
                }
                if (uptPermission == "1") {
                    showContent+='<a class="layui-btn layui-btn-normal layui-btn-mini" style="width:60px;" onclick="update(' + row.id + ','+row.parentId+')">编辑</a>';
                }
                if (delPermission == "1") {
                    showContent+='<a class="layui-btn layui-btn-danger layui-btn-mini" style="width:60px;" onclick="del(' + row.id + ')">删除</a>';
                }
                return showContent; //列渲染
            }
        },
    ];

    reloadTable({});

    var active = {
        addParentMenu: function () {
            add(0);
        },
        reload:function () {
            var optionsData={};
            var developerBrandCode= trimStr($("#developerBrandCode").val());
            if(isIncludeBlank(developerBrandCode)){//中间是否有空格
            	parent.layer.alert('中间有空格，请重新输入！', {icon: 2, title: '提示'});
            	return false;
            }
//            var developerBrandName= trimStr($("#developerBrandName").val());
//            if(isIncludeBlank(developerBrandName)){
//            	parent.layer.alert('中间有空格，请重新输入！', {icon: 2, title: '提示'});
//            	return false;
//            }
            var partner = $("#partner").val();
            optionsData.developerBrandCode=developerBrandCode;
            optionsData.partner=partner;
            console.log(optionsData);
            reloadTable(optionsData);
        },
        reset:function () {
            $("#developerBrandCode").val('');
            $("#partner").val('');
            form.render('select');
            active.reload();
        }
    };

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});

function reloadTable(optionsData){
    parent.layer.load(2);
    $.ajax({
        url: ctx + '/developerBrand/getDeveloperBrandList',
        type: 'post',
        dataType: 'json',
        data:optionsData,
        success: function (data) {
            parent.layer.closeAll();
            console.log(data);
            if(data.returnCode=='200'){
                loadTable(data.returnData);
            }else{
                parent.layer.alert('数据加载失败');
            }
        },fail:function () {
            parent.layer.closeAll();
            parent.layer.alert('请求异常');
        }
    });
}

//刷新数据
function loadTable(data){
    //重置div
    $(".treeTable").html('<div id=\"menuTable\" ></div>');
    layui.treeGird({
        elem: '#menuTable', //传入元素选择器
        nodes: data,
        layout: layout,
    });
    
    for(var i=0;i<data.length;i++){
        var elem=$("#menuTable tr[id="+data[i].id+"] td")[1];
        $(elem).addClass('fontWeight');
    }
}
//添加
function add(id){
    parent.layer.open({
        type: 2,
        btn: ['确认', '取消'],
        title: '新建合作方品牌',
        area: ['700px', '520px'],
        scrollbar: false,
        resize: false,
        content: ctx + '/developerBrand/addDeveloperBrandPage?parentId='+id+"&flag=0",
        yes: function(index, layero){
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            console.log(formData);
            if(formData!=null){
                parent.layer.close(index);//关闭弹窗

                parent.layer.load(2);
                $.ajax({
                    url: ctx + '/developerBrand/addDeveloperBrand',
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
                                reloadTable();
                            });
                        }
                    }
                });
            }
        }
    });
}
//修改
function update(id,parentId){
	var flag = 0;
	//判断是否可以编辑(是否有合作方选择了该品牌),排除根节点编辑
		$.ajax({
			url: ctx + '/developerBrand/updateCheck',
			type: 'post',
			data:{id:id},
			dataType: 'json',
			async: false,
			success: function (data) {
				console.log(data);
				if (data.returnCode == 200){
					var count = data.returnData;
					if(count>0 && parentId!=0){
						parent.layer.alert('已新建该品牌的合作方，不允许修改该合作方品牌信息！', {icon: 2, title:'提示'});
						flag=1;
					}else if(parentId==0 && count>0){//合作方已绑定
						flag=3;
					}
				}
			}
		});
	 if(flag==1){
		 return;
	 }else{
//		 parent.layer.confirm("确认修改吗？子集也随之修改",{icon: 3, title:'提示'},function(){
			    parent.layer.open({
			        type: 2,
			        btn: ['确认', '取消'],
			        title: '编辑合作方品牌',
			        area: ['700px', '520px'],
                    content: ctx + '/developerBrand/addDeveloperBrandPage?id='+id+"&flag="+flag,
			        yes: function(index, layero){
			            //确认的回调
			            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
			            var formData = iframeWin.getFormData();
			
			            console.log(formData);
			            if(formData!=null){
			                parent.layer.close(index);//关闭弹窗
			                parent.layer.load(2);
			                $.ajax({
			                    url: ctx + '/developerBrand/updateDeveloperBrand',
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
			                                reloadTable();
			                            });
			                        }
			                    }
			                });
			            }
			        }
			    });
//			});
	 }
	
}
//删除
function del(id){
	var flag = 0;
	//判断是否可以编辑
	 $.ajax({
         url: ctx + '/developerBrand/updateCheck',
         type: 'post',
         data:{id:id},
         dataType: 'json',
         async: false,
         success: function (data) {
             console.log(data);
             if (data.returnCode == 200){
            	 var count = data.returnData;
            	 if(count>0){
            		 parent.layer.alert('已新建该品牌的合作方，不允许删除该合作方品牌信息！', {icon: 2, title:'提示'});
            		 flag=1;
            	 }
             }
         }
     });
	 if(flag==1){
		 return false;
	 }else{
//		 parent.layer.confirm("确认删除吗？子集也随之删除",{icon: 3, title:'提示'},function(){
		        parent.layer.load(2);
		        $.ajax({
		            url: ctx + '/developerBrand/deleteDeveloperBrand',
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
		                        reloadTable();
		                    });
		                }
		            }
		        });
//		    });
	 }
    
}
