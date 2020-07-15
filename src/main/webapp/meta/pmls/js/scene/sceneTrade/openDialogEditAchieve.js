var layer;
var jsonDto={};
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer;
        $ = layui.$;
    formSelects = layui.formSelects;
    
    var auditDate = $("#auditDate").val();//配置时间
    var currDate = $("#currDate").val();//当前时间
    var cooperFlag = $("#cooperFlag").val();//是否有分销合同
    var dateFlag = $("#dateFlag").val();////判断当前时间是否在配置时间内(1:在         -1:不在)
    if(dateFlag==1){//在配置时间内
    	if(cooperFlag==1){//有分销合同
    	}else{//没有分销合同，隐藏分销合同
    		$("#isContract").attr("style","display:none;");//隐藏div
    		$("#htedition").val(28302);
    	}
    }
 
//    getFyCenterIdService(null, null);
    form.render('select');
    
//    form.on('select(newCenterId)', function (data) {
//    	var newCenterCode = data.value;
//    	var newCenterName = data.elem[data.elem.selectedIndex].text;
//    	$("#newCenterCode").val(newCenterCode);
//    	$("#newCenterName").val(newCenterName);
//    });
    
    
    var active = {
            //选择业绩归属人
    		selUser:function(){
    			var projectNo = $("#projectNo").val();
    			var projectCityNos = $("#projectCityNos").val();
            	parent.layer.open({
                    type: 2,
                    title: '选择业绩归属人',
                    area: ['800px', '660px'],
                    content: '/sceneTrade/selUser?projectCityNos='+projectCityNos
                    ,scrollbar: false
                    ,resize:false
                    ,btn: ['确定', '取消']
                    ,yes: function(index, layero){
                        //确认的回调
                        var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                        var formData = iframeWin.getFormData();
                        if(formData!=null) {
                            $("#linkUserCode").val(formData.maintainer);
                            $("#linkUserName").val(formData.maintainerName);
                            $("#centerName").val(formData.centerName);
                            $("#newCenterCode").val(formData.centerId);
                            $("#newCenterName").val(formData.centerName);
                            $("#inputLinkUserName").val(formData.maintainerName+"("+formData.maintainer+")");
//                            var userCode= $("#linkUserName").val();
//                            var centerIds = [];
//                            centerIds.push("fyCenterId");
//                            // 房友中心
//                            fyCenterIdService(userCode ,function () {
//                                form.render('select');
//                            }, centerIds);
//                            form.render('select');
                            //关闭弹出层
                            parent.layer.close(index);
                        }
                    }
                });
            },
            selContract:function(){//合作协议
            	var projectNo = $("#projectNo").val();
            	var companyNo = $("#companyNo").val();
		    	parent.layer.open({
			    		type: 2,
			    		title: '选择分销合同',
			    		area: ['800px', '660px'],
			    		content: ctx + '/sceneTrade/selContract?projectNo='+projectNo+"&companyNo="+companyNo
		    			,scrollbar: false
		    			,resize:false
		    			,btn: ['确定', '取消']
			    	,yes: function(index, layero){
			    		//确认的回调
			    		var iframeWin = parent.window[layero.find('iframe')[0]['name']];
			    		var formData = iframeWin.getFormData();
			    		if(formData!=null) {
			    			$("#dateLifeStart").val(formData.dateLifeStart);
			    			$("#dateLifeEnd").val(formData.dateLifeEnd);
			    			$("#htType").val(formData.htType);
			    			$("#contractNo").val(formData.contractNo);
			    			$("#contractName").val(formData.contractName);
			    			$("#contract").val(formData.contractNo);
			    			$("#htedition").val(formData.htedition);
			    			if(formData.htedition == 28301){//PMLS
			    				$("#isFyCenter").attr("style","display:none;");//隐藏div
			    			}
			    			else if(formData.htedition == 28302){
			    				$("#isFyCenter").attr("style","display:block;margin-left: 45px;");//显示div
			    			}
			    			//关闭弹出层
			    			parent.layer.close(index);
			    		}
			    	}
		    	});
            },
            //选择房友中心
    		selFyUser:function(){
    			var projectCityNos = $("#projectCityNos").val();
            	parent.layer.open({
                    type: 2,
                    title: '选择房友中心',
                    area: ['800px', '660px'],
                    content: '/sceneTrade/selFyUser?projectCityNos='+projectCityNos
                    ,scrollbar: false
                    ,resize:false
                    ,btn: ['确定', '取消']
                    ,yes: function(index, layero){
                        //确认的回调
                        var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                        var formData = iframeWin.getFormData();
                        if(formData!=null) {
//                            $("#newFyCenterId").val(formData.centerId);
//                            $("#newFyCenterName").val(formData.centerName);
//                            $("#fyCenterId").val(formData.centerName);
                            //关闭弹出层
                            parent.layer.close(index);
                        }
                    }
                });
            },
    }
    
    $('.achievePage .layui-btn').on('click', function () {
    	var type = $(this).data('type');
    	active[type] ? active[type].call(this) : '';
    });
});

/** 房友中心 */
function getFyCenterIdService(checkValue, callback) {
	var url = BASE_PATH + '/businessManagerController/getMaintainerList';
    var params = {
    	cooperationSelect: "user",
    	projectCityNos : $("#projectCityNos").val(),
    	fyFlag : "fyFlag",
    	centerName:""
    };
    $.ajax({
        url: url,
        type: 'post',
        data:params,
        async:false,
        dataType: 'json',
		success : function(data) {
			console.log(data);
			if (data.returnCode == '200') {
				var result = "<option value=''>请选择</option>";
		        $.each(data.returnData, function (n, value) {
		            if (checkValue != null && checkValue == value.centerId) {
		                result += "<option value='" + value.centerId + "' selected>" + value.centerName + "</option>";
		            } else {
		                result += "<option value='" + value.centerId + "'>" + value.centerName + "</option>";
		            }
		        });
		        $("#fyCenterId").html('');
		        $("#fyCenterId").append(result);

		        callback ? callback() : $.noop();
			} else {
				parent.layer.alert(data.returnMsg, {
					icon : 1,
					title : '提示'
				});
			}
		}
    });

}

/** 房友中心 */
function fyCenterIdService(usercode, callback, ids) {
    var url = BASE_PATH + '/businessManagerController/getMaintainerList';

    var params = {
    	maintainerName: usercode
    };

    if (ids == null) {
        ids = [];
        ids.push("centerId")
    }
    
    $.ajax({
        url: url,
        type: 'post',
        data:params,
        async:false,
        dataType: 'json',
		success : function(data) {
			console.log(data);
			if (data.returnCode == '200') {
				var result = "<option value=''>请选择</option>";
				var selected = "";
				if (!isEmpty(data) && !isEmpty(data.returnData)
						&& data.returnData.length == 1) {
					selected = " selected ";
				}
				$.each(data.returnData, function(n, value) {
					result += "<option value='" + value.centerId + "'"
							+ selected + "> " + value.centerName + "</option>";
				});
				$("#"+ids).html('');
		        $("#"+ids).append(result);
			} else {
				parent.layer.alert(data.returnMsg, {
					icon : 1,
					title : '提示'
				});
			}
		}
    });
}

//获取参数
function getAchieveFormData(){
	var obj={};
	var linkUserCode=$("#linkUserCode").val();
	var linkUserName=$("#linkUserName").val();
	
	var newCenterCode=$("#newCenterCode").val();
	var newCenterName=$("#newCenterName").val();
	
	var userCode=$("#userCode").val();
	var reportId=$("#reportId").val();
	var userName=$("#userName").val();
	var userIdCreate=$("#userIdCreate").val();
	var relateId=$("#relateId").val();
	
	var oldContactId=$("#oldContactId").val();
	var oldContactNm=$("#oldContactNm").val();
	var oldCenterGroupId=$("#oldCenterGroupId").val();
	var oldCenterGroupName=$("#oldCenterGroupName").val();
	
	
	var contractName=$("#contractName").val();
	var contractNo=$("#contractNo").val();
	var contract=$("#contract").val();
	var cooperFlag=$("#cooperFlag").val();
	var auditDate = $("#auditDate").val();//配置时间
    var currDate = $("#currDate").val();//当前时间
    var dateFlag = $("#dateFlag").val();////判断当前时间是否在配置时间内(1:在         -1:不在)
	if(dateFlag==1){//在配置时间内
		if(cooperFlag==1){
			if(isEmpty(contract) || isEmpty(contractName)){
				parent.layer.alert('请选择分销合同！', {
					icon : 2,
					title : '提示'
				});
				return;
			}
		}
	}
	if(dateFlag==-1){//不在配置时间内
			if(isEmpty(contract) || isEmpty(contractName)){
				parent.layer.alert('请选择分销合同！', {
					icon : 2,
					title : '提示'
				});
				return;
			}
	}
	var dateLifeStart=$("#dateLifeStart").val();
	var dateLifeEnd=$("#dateLifeEnd").val();
	var htType=$("#htType").val();
	var changeReason=trimStr($("#changeReason").val());
	
	var oldFyCenterId=$("#oldFyCenterId").val();
	var oldFyCenterName=$("#oldFyCenterName").val();//房友中心
	var htedition=$("#htedition").val();//版本   pmls、房友
	
	if(isEmpty(linkUserCode) || isEmpty(linkUserName)){
		parent.layer.alert('请选业绩归属人！', {
			icon : 2,
			title : '提示'
		});
		return;
	}
//	if(htedition == 28302){//房友
//		var newFyCenterId=$("#fyCenterId").val();
//		var newFyCenterName = $("#fyCenterId").find("option:selected").text();
//		if(isEmpty(newFyCenterId)){
//			parent.layer.alert('请选择房友中心！', {
//				icon : 2,
//				title : '提示'
//			});
//			return;
//		}
//		obj.newFyCenterId=newFyCenterId;//房友中心
//		obj.newFyCenterName=newFyCenterName;
//	}
	
	
	if(isEmpty(changeReason)){
		parent.layer.alert('请输入变更内容！', {
			icon : 2,
			title : '提示'
		});
		return;
	}
	
	obj.contractNo=contractNo;
	obj.contractName=contractName;
	obj.dateLifeStart=dateLifeStart;
	obj.dateLifeEnd=dateLifeEnd;
	obj.htType=htType;
	
	obj.oldFyCenterId=oldFyCenterId;
	obj.oldFyCenterName=oldFyCenterName;
	obj.htedition=htedition;
	
    obj.userCode=userCode;
    obj.reportId=reportId;
    obj.relateId=relateId;
    obj.userName=userName;
    obj.userIdCreate=userIdCreate;
    
    obj.oldContactId=oldContactId;
    obj.oldContactNm=oldContactNm;
    obj.oldCenterGroupId=oldCenterGroupId;
    obj.oldCenterGroupName=oldCenterGroupName;
    
    obj.linkUserCode=linkUserCode;
    obj.linkUserName=linkUserName;
    obj.newCenterCode=newCenterCode;
    obj.newCenterName=newCenterName;
    
    obj.changeReason=changeReason;
    
    return obj;
}

