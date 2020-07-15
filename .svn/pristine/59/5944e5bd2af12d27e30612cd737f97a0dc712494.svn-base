var layer;
var id=window.id;
var userJsonDto;
var formSelects;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer;
        $ = layui.$;
    formSelects = layui.formSelects;
    
    if(id!=null && id !=''){
        if(userDto!=''){
        	//userJsonDto=eval('(' + window.userDto + ')');
            userJsonDto=eval('(' + window.userDto.replace(/[\r\n]/g,"\\n") + ')');
        }
        $("#cityNo").val(userJsonDto.cityName);
        $("#userName").val(userJsonDto.userName);
        $("#userCode").val(userJsonDto.userCode);
        //$("#isPmlsCenter").val(userJsonDto.isPmlsCenter);
        $("#userCode").attr({ readonly: 'true' });
        $("#userName").attr({ readonly: 'true' });
//        $("#userCode").css({"background": "#DDD",});
//        $("#userName").css({"background": "#DDD"});
        $("#userCode").css({"border": "1px solid #DDD","background-color": "#F5F5F5","color": "#ACA899","opacity": "1"});
        $("#userName").css({"border": "1px solid #DDD","background-color": "#F5F5F5","color": "#ACA899","opacity": "1"});
        initCityListService(userJsonDto.cityNo,function () {
            form.render();
            //初始化中心选项（单选）
	        initCenterIdService(userJsonDto.cityNo,userJsonDto,function () {
	            form.render();
	        });
        });
    }else{
        $("#userCode").removeAttr("readonly");
        $("#userName").removeAttr("readonly");
        //初始换多选中心选项
        initCityListService(null,function () {
            form.render();
        });
    }
    
    form.on('select(cityNo)', function (data) {
        console.log("form.on('select(cityNo)')");
        if(id!=null && id !=''){
        	initCenterIdService(data.value,null,function () {
                form.render();
            });
        }else{
        	initCenterGroup(data.value);
        }
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
	            formSelects.data('addCenterIds', 'local', {arr: result});
	
	            formSelects.btns('addCenterIds', [
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
            form.render('select','addCenterIds');

        }, function () {
        });
    }
});

/**
 * 初始化-人员权限城市列表
 * @param checkValue
 * @param callback
 * @returns
 */
function initCityListService(cityNo,callback) {
	var url = BASE_PATH + "/personnelPermissions/getCityList";
	var params ={};
	ajaxGet(url, params, function (data) {
		var result = "<option value=''>请选择</option>";
		$.each(data.returnData, function (n, value) {
			if(cityNo==value.cityNo){
				result += "<option value='" + value.cityNo + "' selected>"+ value.cityName + "</option>";
			}else{
				result += "<option value='" + value.cityNo + "'>"+ value.cityName + "</option>";
			}
		});
		$("#cityNo").html('');
		$("#cityNo").append(result);
		
		callback ? callback() : $.noop();
	}, function () {
	});
}
/**
 * 初始化-人员权限中心列表
 * @param checkValue
 * @param callback
 * @returns
 */
function initCenterIdService(cityNo,userJsonDto,callback) {
	var url = BASE_PATH + "/personnelPermissions/getCenterListByCityNo";
	var params ="";
	if(cityNo!=null){
		params = {cityNo:cityNo};
	}
	ajaxGet(url, params, function (data) {
		var result = "<option value=''>请选择</option>";
		$.each(data.returnData, function (n, value) {
			if(userJsonDto!=null && userJsonDto.centerId==value.centerId){
				result += "<option value='" + value.centerId + "' selected>"+ value.centerName + "</option>";
			}else{
				result += "<option value='" + value.centerId + "'>"+ value.centerName + "</option>";
			}
		});
		$("#centerGroup").html('');
		$("#centerGroup").append(result);
		
		callback ? callback() : $.noop();
	}, function () {
	});
}


//获取参数
function getFormData(){
	var obj={};
    var cityNo=$("#cityNo").val();
    var centerId = "";
    var centerIds= "";
    var userCode= trimStr($("#userCode").val());
    var userName= trimStr($("#userName").val());
    if(isIncludeBlank(userCode)){
    	parent.layer.alert('人员工号中间有空格，请重新输入！', {icon: 2, title: '提示'});
    	return;
    }
    if(isIncludeBlank(userName)){
    	parent.layer.alert('人员姓名中间有空格，请重新输入！', {icon: 2, title: '提示'});
    	return;
    }
    //var isPmlsCenter=$("#isPmlsCenter").val();
    
    if(isBlank(cityNo)){
        layer.alert('请选择城市！',{icon: 2, title:'提示'});
        return;
    }
    if(id!=null && id!=''){
    	obj.id=id;
    	centerId = $("#centerGroup").val();//修改中心
    	if(isBlank(centerId)){
    		layer.alert('请选择中心！',{icon: 2, title:'提示'});
    		return;
    	}
    }else{
    	centerIds = getXMSelectOutIds(formSelects.value('addCenterIds'));//新增中心
    	if(isBlank(centerIds)){
    		layer.alert('请选择中心！',{icon: 2, title:'提示'});
    		return;
    	}
    }
    if(isBlank(userCode)){
    	layer.alert('请输入人员工号！',{icon: 2, title:'提示'});
    	return;
    }
    if(isBlank(userName)){
    	layer.alert('请输入人员姓名！',{icon: 2, title:'提示'});
    	return;
    }
    // if(isBlank(isPmlsCenter)){
    // 	layer.alert('请选择是否PMLS！',{icon: 2, title:'提示'});
    // 	return;
    // }
    obj.cityNo=cityNo;
    obj.centerId=centerId;
    obj.centerIds=centerIds;
    obj.userCode=userCode;
    obj.userName=userName;
    //obj.isPmlsCenter=isPmlsCenter;
    
    return obj;
}

