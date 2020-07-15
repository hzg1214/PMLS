/*
 * 合作方品牌js
 * */
var layer;
var parentId=window.parentId;
var id=window.id;
var developerJsonDto;
var checkFlag = 0;

layui.use(['tree', 'layer', 'form'], function() {
    layer = layui.layer,
        $ = layui.jquery;
    var form = layui.form;
    if($('#parentId').val() !='' && (id==null || id =='')){//添加下级
    	$("#partnerDiv").hide();//隐藏合作类型
    	$("#bigCustomerFlagDiv").hide();//隐藏是否大客户
    	$("#bigCustomerDiv").hide();//隐藏大客户简称
    	$("#isYjDyDiv").hide();//隐藏是否垫佣
    	$("#mattressNailDiv").hide();//隐藏垫佣简称
    	checkFlag= 1;
    }

    if($('#parentId').val() ==0 && id!=null && id !=''){//编辑主品牌
    	if(developerBrandDto!=''){
    		//developerJsonDto=eval('(' + window.developerBrandDto + ')');
    		developerJsonDto=eval('(' + window.developerBrandDto.replace(/[\r\n]/g,"\\n") + ')');
    	}
    	$("#partnerDiv").show();//显示合作类型
    	$("#bigCustomerFlagDiv").show();//显示是否大客户
    	var bigCustomerFlag = developerJsonDto.bigCustomerFlag;
    	if(bigCustomerFlag==22601){//是
    		$("#bigCustomerDiv").show();//显示大客户简称
    	}else if(bigCustomerFlag==22602){
    		$("#bigCustomerDiv").hide();//隐藏大客户简称
    	}
    	$("#isYjDyDiv").show();//显示是否垫佣
    	var isYjDy = developerJsonDto.isYjDy;
    	if(isYjDy==1){//是
    		$("#mattressNailDiv").show();//显示垫佣简称
    	}else if(isYjDy==0){
    		$("#mattressNailDiv").hide();//隐藏垫佣简称
    	}
    	$(".layui-field-title").hide();
    	$("#developerBrandName").val(developerJsonDto.developerBrandName);
    }
    if($('#parentId').val() !=0 && id!=null && id !=''){//编辑子集
    	checkFlag= 1;
    	$("#partnerDiv").hide();//隐藏合作类型
    	$("#bigCustomerFlagDiv").hide();//隐藏是否大客户
    	$("#bigCustomerDiv").hide();//隐藏大客户简称
    	$("#isYjDyDiv").hide();//隐藏是否垫佣
    	$("#mattressNailDiv").hide();//隐藏垫佣简称
        if(developerBrandDto!=''){
            //developerJsonDto=eval('(' + window.developerBrandDto + ')');
            developerJsonDto=eval('(' + window.developerBrandDto.replace(/[\r\n]/g,"\\n") + ')');
        }
        $(".layui-field-title").hide();
        $("#developerBrandName").val(developerJsonDto.developerBrandName);
    }
    //初始化-大客户、垫佣
    form.on('radio(bigCustomerFlag)', function(data){
        if(data.value==22601){//大客户
            //是
            $("#bigCustomerDiv").show();
            $("#bigCustomer").val('');
        }else{
        	$("#bigCustomerDiv").hide();
        }
        form.render();
    });
    form.on('radio(isYjDy)', function(data){
    	if(data.value==1){//可垫佣甲方
    		//是
    		$("#mattressNailDiv").show();
    		$("#mattressNail").val('');
    	}else{
    		$("#mattressNailDiv").hide();
    	}
    	form.render();
    });
    //编辑主品牌 ，如果该品牌已经呗合作方绑定而且该节点为根节点，合作方类型和合作方品牌名称置灰
    if($('#parentId').val() ==0 &&  $('#parentFlag').val() ==3){
    	$("input[name='partner']").attr('disabled',"");
    	$("input[name='developerBrandName']").attr('disabled',"");
    }else{
    	$("input[name='partner']").removeAttr('disabled');
    	$("input[name='developerBrandName']").removeAttr('disabled');
    }

    form.render();
});

function getFormData(){
	var obj={};
	var partner;
	if(parentId !=0){//添加子集
		partner = $('#partner').val();
	}else{
		partner = $("input[name='partner']:checked").val();
		if(!partner){
			layer.alert('请选择合作方类型！',{icon: 2, title:'提示'});
			return;
		}
	}
    var developerBrandName= trimStr($("#developerBrandName").val());
//    var remark= trimStr($("#remark").val());
    if(isIncludeBlank(developerBrandName)){
    	layer.alert('合作方品牌名称中间有空格，请重新输入！',{icon: 2, title:'提示'});
    	return;
    }
    if(!developerBrandName){
        layer.alert('请填写合作方品牌名称！',{icon: 2, title:'提示'});
        return;
    }
    if(checkFlag==0){
    	
    	var bigCustomerFlag = $("input[name='bigCustomerFlag']:checked").val();
    	if(!bigCustomerFlag){
    		layer.alert('请选择是否大客户！',{icon: 2, title:'提示'});
    		return;
    	}
    	
    	if(bigCustomerFlag=='22601'){
    		var bigCustomer = $('#bigCustomer').val();
    		var bigCustomerName = $('#bigCustomer option:checked').text();
    		if(!bigCustomer){
    			layer.alert('请选择大客户简称！',{icon: 2, title:'提示'});
    			return;
    		}
    		obj.bigCustomerId=bigCustomer;
    		obj.bigCustomerName=bigCustomerName;
    	}
    	var isYjDy = $("input[name='isYjDy']:checked").val();
    	if(!isYjDy){
    		layer.alert('请选择是否垫佣！',{icon: 2, title:'提示'});
    		return;
    	}
    	if(isYjDy=='1'){
    		var mattressNail = $('#mattressNail').val();
    		var mattressNailName = $('#mattressNail option:checked').text();
    		if(!mattressNail){
    			layer.alert('请选择垫佣甲方简称！',{icon: 2, title:'提示'});
    			return;
    		}
    		obj.mattressNailId=mattressNail;
    		obj.mattressNailName=mattressNailName;
    	}
    	obj.bigCustomerFlag=bigCustomerFlag;
        obj.isYjDy=isYjDy;
    }
    
    obj.developerBrandName=developerBrandName;
    obj.partner=partner;
    obj.parentId=parentId;
    
    if(id!=null && id!=''){
    	obj.orgId = $('#orgId').val();
    	obj.oldBigCustomerFlag=developerJsonDto.bigCustomerFlag;
    	obj.oldBigCustomerId=developerJsonDto.bigCustomerId;
    	obj.oldBigCustomerName=developerJsonDto.bigCustomerName;
    	obj.oldIsYjDy=developerJsonDto.isYjDy;
    	obj.oldMattressNailId=developerJsonDto.mattressNailId;
    	obj.oldMattressNailName=developerJsonDto.mattressNailName;
    	obj.developerBrandCode = developerJsonDto.developerBrandCode;
        obj.id=id;
    }
    return obj;
}

