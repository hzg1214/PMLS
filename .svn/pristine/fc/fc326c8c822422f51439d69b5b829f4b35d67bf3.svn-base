var layer;
var id=window.id;
var jsonDto={};
var partnerLists={};
var form,upload;
layui.config({
    base: "/meta/layui/lay/modules/"
}).extend({
    treeSelect: "treeSelect"
});
layui.use(['tree', 'layer', 'form','treeSelect','upload'], function() {
    layer = layui.layer,
        $ = layui.jquery;
    form = layui.form;
    var treeSelect = layui.treeSelect;
    
    if(id!=null && id !=''){
        if(window.developerDto!=''){
//            jsonDto=eval('(' + window.developerDto + ')');
            jsonDto=eval('(' + window.developerDto.replace(/[\r\n]/g,"\\n") + ')');
            $("#developerName").val(jsonDto.developerName);
            $("#businessLicenseNo").val(jsonDto.businessLicenseNo);
            $("#realityCityNo").val(jsonDto.cityNo);
            $("#districtNo").val(jsonDto.districtNo);
            $("#address").val(jsonDto.address);
            $("#legalPerson").val(jsonDto.legalPerson);
            $("#contactNumber").val(jsonDto.contactNumber);
            $("#dockingPeo").val(jsonDto.dockingPeo);
            $("#dockingPeoTel").val(jsonDto.dockingPeoTel);
            $("#channelType").val(jsonDto.channelType);
//            $("#developerBrandId").val(jsonDto.developerBrandId);
            $("#developerBrandId").val(jsonDto.developerBrandName);
            $("#remark").val(jsonDto.remark);
            $(".blockTitle").text('编辑合作方');
            
            //加载大客户和垫佣
//            getCustomerAndYjDy(jsonDto.developerBrandCode);
            setValue(jsonDto.bigCustomerFlag,jsonDto.isYjDy)
            
            // 初始化城市选项
            cityLinkageIsService(jsonDto.cityNo,function () {
                form.render();
                //初始化区域
                districtLinkageIsService(jsonDto.cityNo,jsonDto.districtNo,function () {
                    form.render();
                });
            });

            //存在合同关联合作方不允许修改合作方名称和统一社会信用代码
            if(jsonDto.eatateDeveloperName!=null && jsonDto.eatateDeveloperName!='' ){
                $("#developerName").attr('disabled',"");
                $("#businessLicenseNo").attr('disabled',"");
            }
            
            //eatateDeveloperName 项目关联开发商-开发商名称  eatateHzfName 项目关联合作方-合作方名称
            if((jsonDto.eatateDeveloperName!=null && jsonDto.eatateDeveloperName!='') 
            	|| (jsonDto.eatateHzfName!=null && jsonDto.eatateHzfName!='')){
                $(".brandIdInputBlock").html('<input type="text" class="layui-input" disabled value="'+jsonDto.developerBrandName+'">');
                $("#editDeveloperBrandId").val(jsonDto.developerBrandId);
                $("#chooseBtn").attr("style","display:none;");
            }
        }
    }else{
        cityLinkageIsService(null,function () {
            form.render();
        });
    }
    
    //初始化-合作类型
    if(window.partnerList!=null && window.partnerList!=''){
        //var partnerLists = eval('(' + window.partnerList + ')');
        partnerLists=eval('(' + window.partnerList.replace(/[\r\n]/g,"\\n") + ')');
        var partner;
        if(jsonDto!=null && jsonDto.partner!=null && jsonDto.partner!=''){
        	partner = jsonDto.partner;
        }
        initPartner(partnerLists,partner);
    }
    
    
    //城市选择事件
    form.on('select(realityCityNo)', function (data) {
        console.log(data);
        $("#address").val("");
        if(data.value!=null && data.value!=''){
            districtLinkageIsService(data.value,null,function () {
                form.render();
            });
        }
    });
    

    //初始化合作方品牌选项
//    treeSelect.render({
//        // 选择器
//        elem: '#developerBrandId',
//        // 数据
//        data: ctx+'/developerBrand/getDeveloperBrandList2',
//        // 异步加载方式：get/post，默认get
//        type: 'post',
//        // 占位符
//        placeholder: '请选择',
//        // 是否开启搜索功能：true/false，默认false
//        search: true,
//        // 一些可定制的样式
//        style: {
//            folder: {
//                enable: false
//            },
//            line: {
//                enable: true
//            }
//        },
//        // 点击回调
//        click: function(d){
//            console.log(d);
//        },
//        // 加载完成后的回调函数
//        success: function (d) {
//            console.log(d);
//            //选中节点，根据id筛选
//            if(jsonDto!=null && jsonDto.developerBrandId!=null && jsonDto.developerBrandId!=''){
//                treeSelect.checkNode('tree', jsonDto.developerBrandId);
//            }
//
//            console.log($('#developerBrandId').val());
//            //获取zTree对象，可以调用zTree方法
//            var treeObj = treeSelect.zTree('tree');
//            console.log(treeObj);
//            //刷新树结构
//            treeSelect.refresh('tree');
//            if(jsonDto.eatateDeveloperName!=null && jsonDto.eatateDeveloperName!='' ){
//                $(".brandIdInputBlock").html('<input type="text" class="layui-input" disabled value="'+jsonDto.developerBrandName+'">');
//                $("#editDeveloperBrandId").val(jsonDto.developerBrandId);
//            }
//        },click:function(d){
//        	var developerBrandCode = d.current.developerBrandCode;
//        	//联动是否大客户、是否垫佣、合作类型
//        	getCustomerAndYjDy(developerBrandCode);
//        	
//        }
//    });
            
    
    
  
    
  
    

});
//初始化--大客户和垫佣
function setValue(bigCustomerFlag,isYjDy) {
	var result = "";
	var result1 = "";
	for(var i=1;i<2;i++){
		//大客户
		if('22601' == bigCustomerFlag){
			result+="<input type='radio' name='bigCustomer' value='22601' disabled title='是' checked=''>";
			result+="<input type='radio' name='bigCustomer' value='22602' disabled title='否' >";
		}else{
			result+="<input type='radio' name='bigCustomer' value='22601' disabled title='是'>";
			result+="<input type='radio' name='bigCustomer' value='22602' disabled title='否'  checked=''>";
		}
		//垫佣
		if('1' == isYjDy){
			result1+="<input type='radio' name='isYjDy' value='1' disabled title='是'  checked=''>";
			result1+="<input type='radio' name='isYjDy' value='0' disabled title='否' >";
		}else{
			result1+="<input type='radio' name='isYjDy' value='1' disabled title='是'>";
			result1+="<input type='radio' name='isYjDy' value='0' disabled title='否'  checked=''>";
		}
	}
	$("#bigCustomerFlag").html('');
	$("#bigCustomerFlag").append(result);
	$("#isYjDy").html('');
	$("#isYjDy").append(result1);
}
//合作方品牌事件获取垫佣+大客户+合作类型
function getCustomerAndYjDy(developerBrandCode) {
	var data = {};
	data.developerBrandCode= developerBrandCode;
    $.ajax({
        url: ctx+'/developer/getCustomerAndYjDy',
        type: 'post',
        dataType: 'json',
        data:data,
        success: function (data) {
            parent.layer.closeAll();
            console.log(data);
            if(data.returnCode=='200'){
                if(data.returnData!=null){
                	var result = data.returnData;
                	var bigCustomerFlag = result.bigCustomerFlag;
                	var isYjDy = result.isYjDy;
                	var bigCustomerId = result.bigCustomerId;
                	var mattressNailId = result.mattressNailId;
                	var partner = result.partner;
                	var partnerAbbreviationNm = result.partnerAbbreviationNm;
                	var bigCustomerName = result.bigCustomerName;
                	var mattressNailName = result.mattressNailName;
                	$("#bigCustomerName").val(bigCustomerName);
                	$("#mattressNailName").val(mattressNailName);
                	$("#mattressNailId").val(mattressNailId);
                	$("#bigCustomerId").val(bigCustomerId);
                	$("#partnerAbbreviationNm").val(partnerAbbreviationNm);
                	setValue(bigCustomerFlag,isYjDy);
                	initPartner(partnerLists,partner)
                	form.render();
                }
            }else{
                parent.layer.alert('数据加载失败');
            }
        },fail:function () {
            parent.layer.closeAll();
            parent.layer.alert('请求异常');
        }
    });
}


//校验统一社会信用代码是否存在
function checkDeveloper() {
    var businessLicenseNo= $("#businessLicenseNo").val();
    //营业执照长度满足校验数据库是否存在
    if((businessLicenseNo.length==15 || businessLicenseNo.length==18)){
        checkBusinessLicenseNo('',businessLicenseNo);
    }
}
//校验合作方是否存在
function checkDeveloperName() {
    var developerName= $("#developerName").val();
    if(developerName!=null && developerName!='' ){
        checkBusinessLicenseNo(developerName,'');
    }
}

//校验统一社会信用代码、合作方名称是否存在
function checkBusinessLicenseNo(developerName,businessLicenseNo) {
	var data = {};
	if(!isBlank(businessLicenseNo)){
		data.businessLicenseNo= businessLicenseNo;
	}
	if(!isBlank(developerName)){
		data.developerName= developerName;
	}
    $.ajax({
        url: ctx+'/developer/getDeveloperInfo2',
        type: 'post',
        dataType: 'json',
        data:data,
        success: function (data) {
            parent.layer.closeAll();
            console.log(data);
            if(data.returnCode=='200'){
                if(data.returnData!=null){
                	addDeveloperReleaseCity(data.returnData.id,developerName,businessLicenseNo);//添加公司到对应的城市
                }
            }else{
                parent.layer.alert('数据加载失败');
            }
        },fail:function () {
            parent.layer.closeAll();
            parent.layer.alert('请求异常');
        }
    });
}

//添加合作方城市关系表
function addDeveloperReleaseCity(id,developerName,businessLicenseNo){
    $.ajax({
        url: ctx+'/developer/addDeveloperReleaseCity',
        type: 'post',
        dataType: 'json',
        data:{
            id:id
        },
        success: function (data) {
            console.log(data);
            if(data.returnCode=='200'){
            	if(id!=null && id!=''){
            		parent.layer.alert('合作方信息已存在，请返回列表页面进行修改操作！',{icon: 2, title:'提示'},function () {
            			parent.layer.closeAll();
            			back();
            		});
            	}else{
            		 if(!isBlank(developerName)){
            			
            			parent.layer.alert('您输入的合作方名称已存在，请返回列表页面进行修改操作！',{icon: 2, title:'提示'},function () {
            				parent.layer.closeAll();
            				back();
            			});
            		}else if(!isBlank(businessLicenseNo)){
            			parent.layer.alert('您输入的统一社会信用代码已存在，请返回列表页面进行修改操作！',{icon: 2, title:'提示'},function () {
            				parent.layer.closeAll();
            				back();
            			});
            			
            		}
            	}

            }else{
                parent.layer.alert('数据加载失败');
            }
        },fail:function () {
            parent.layer.closeAll();
            parent.layer.alert('请求异常');
        }
    });
}
//取消返回上一个页面
function back(){
	parent.redirectTo('delete',null,null);
}

//提交
function submitForm(){
	var obj={};
    var developerName= replaceBrackets(trimStr($("#developerName").val()));
    if(isIncludeBlank(developerName)){
    	parent.layer.alert('合作方名称中间有空格，请重新输入！', {icon: 2, title: '提示'});
    	return;
    }
    var businessLicenseNo= trimStr($("#businessLicenseNo").val());
    if(isIncludeBlank(businessLicenseNo)){
    	parent.layer.alert('统一社会信用代码中间有空格，请重新输入！', {icon: 2, title: '提示'});
    	return;
    }
    var realityCityNo=$("#realityCityNo").val();
    var cityName=$("#realityCityNo option:selected").text();
    var districtNo=$("#districtNo").val();
    var districtName=$("#districtNo option:selected").text();
    var address= replaceBrackets(trimStr($("#address").val()));
    if(isIncludeBlank(address)){
    	parent.layer.alert('详细地址中间有空格，请重新输入！', {icon: 2, title: '提示'});
    	return;
    }
    var legalPerson= trimStr($("#legalPerson").val());
    if(isIncludeBlank(legalPerson)){
    	parent.layer.alert('法人代表人中间有空格，请重新输入！', {icon: 2, title: '提示'});
    	return;
    }
    var contactNumber= trimStr($("#contactNumber").val());
    var dockingPeo= trimStr($("#dockingPeo").val());
    if(isIncludeBlank(dockingPeo)){
    	parent.layer.alert('联系人姓名中间有空格，请重新输入！', {icon: 2, title: '提示'});
    	return;
    }
    var dockingPeoTel= trimStr($("#dockingPeoTel").val());
    var developerBrandId= $("#developerBrandId").val();
    var editDeveloperBrandId= $("#editDeveloperBrandId").val();
    var bigCustomerFlag = $('#bigCustomerFlag input[checked]').val();
    var isYjDy = $('#isYjDy input[checked]').val();
    var remark=trimStr($("#remark").val());

    if(isBlank(bigCustomerFlag)){
    	parent.layer.closeAll();
    	parent.layer.alert('请选择是否大客户！',{icon: 2, title:'提示'});
    	return;
    }
    if(isBlank(isYjDy)){
    	parent.layer.closeAll();
    	parent.layer.alert('请选择是否垫佣！',{icon: 2, title:'提示'});
    	return;
    }
    if(isBlank(developerName)){
        parent.layer.closeAll();
        parent.layer.alert('请输入合作方名称！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(businessLicenseNo)){
        parent.layer.closeAll();
        parent.layer.alert('请输入统一社会信用代码！',{icon: 2, title:'提示'});
        return;
    }else if(businessLicenseNo.length!=15 && businessLicenseNo.length!=18){
        parent.layer.closeAll();
        parent.layer.alert('请输入正确的统一社会信用代码！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(realityCityNo)){
        parent.layer.closeAll();
        parent.layer.alert('请选择城市！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(districtNo)){
        parent.layer.closeAll();
        parent.layer.alert('请选择区域！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(address)){
        parent.layer.closeAll();
        parent.layer.alert('请输入详细地址！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(legalPerson)){
        parent.layer.closeAll();
        parent.layer.alert('请输入法定代表人！',{icon: 2, title:'提示'});
        return;
    }
//    if(isBlank(contactNumber)){
//        parent.layer.closeAll();
//        parent.layer.alert('请输入法人手机号码！',{icon: 2, title:'提示'});
//        return;
//    }else 
    if(!isBlank(contactNumber)){
    	if(!checkPhoneNumber(contactNumber)){
    		parent.layer.closeAll();
    		parent.layer.alert('请输入正确的法人手机号码！',{icon: 2, title:'提示'});
    		return;
    	}
    }
    if(isBlank(dockingPeo)){
        parent.layer.closeAll();
        parent.layer.alert('请输入联系人！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(dockingPeoTel)){
        parent.layer.closeAll();
        parent.layer.alert('请输入联系人手机号码！',{icon: 2, title:'提示'});
        return;
    }else if(!checkPhoneNumber(dockingPeoTel)){
        parent.layer.closeAll();
        parent.layer.alert('请输入正确的联系人手机号码！',{icon: 2, title:'提示'});
        return;
    }
    
    if(jsonDto !=null && (jsonDto.eatateDeveloperName!=null && jsonDto.eatateDeveloperName!='')
    	|| (jsonDto.eatateHzfName!=null && jsonDto.eatateHzfName!='')){
		if(isBlank(editDeveloperBrandId)){
			parent.layer.closeAll();
			parent.layer.alert('合作方品牌不能为空！',{icon: 2, title:'提示'});
			return;
		}
		obj.developerBrandId=editDeveloperBrandId;
    }else{
    	if(isBlank(developerBrandId)){
    		parent.layer.closeAll();
    		parent.layer.alert('请选择合作方品牌！',{icon: 2, title:'提示'});
    		return;
    	}
    	var chooseDeveloperBrandId = $('#chooseDeveloperBrandId').val();
    	obj.developerBrandId=chooseDeveloperBrandId;
    }
    
    var mattressNailId = $("#mattressNailId").val();
	var bigCustomerId = $("#bigCustomerId").val();
	var partner=$("input[name='partner']:checked").val();
	var partnerAbbreviationNm=$('#partnerAbbreviationNm').val();
	
	var bigCustomerName=$('#bigCustomerName').val();
	var mattressNailName=$('#mattressNailName').val();
	
	obj.bigCustomerName=bigCustomerName;
	obj.mattressNailName=mattressNailName;
	
	obj.mattressNailId=mattressNailId;
	obj.bigCustomerId=bigCustomerId;
    obj.isYjDy=isYjDy;
    obj.bigCustomerFlag=bigCustomerFlag;
    obj.developerName=developerName;
    obj.businessLicenseNo=businessLicenseNo;
    obj.cityNo=realityCityNo;
    obj.cityName=cityName;
    obj.districtNo=districtNo;
    obj.districtName=districtName;
    obj.address=address;
    obj.legalPerson=legalPerson;
    obj.contactNumber=contactNumber;
    obj.dockingPeo=dockingPeo;
    obj.dockingPeoTel=dockingPeoTel;
    obj.partner=partner;
    obj.partnerAbbreviationNm=partnerAbbreviationNm;
    obj.remark=remark;

    var reqUrl=ctx + '/developer/addDeveloper';
    if(id!=null && id!=''){//修改合作方
        obj.id=id;
        reqUrl=ctx + '/developer/updateDeveloper';
    }
    console.log(obj);

    $.ajax({
        url: reqUrl,
        type: 'post',
        dataType: 'json',
        data:obj,
        success: function (data) {
            parent.layer.closeAll();
            console.log(data);
            if(data.returnCode=='200'){
                parent.layer.alert(data.returnMsg,{icon: 1, title:'提示'});
                parent.redirectTo('replace',ctx+'/developer','合作方');
            }else{
            	parent.layer.alert(data.returnMsg,{icon: 2, title:'提示'});
            }
        },fail:function () {
            parent.layer.closeAll();
            parent.layer.alert('请求异常');
        }
    });
}


//初始化-合作类型
function initPartner(partnerLists,partner){
	var result = "";
	$.each(partnerLists, function (n, value) {
        if(partner==value.dicCode){
            result+="<input type='radio' disabled name='partner' value="+ value.dicCode + " title='"+value.dicValue+"' checked=''>"
        }else{
            result+="<input type='radio' disabled name='partner' value="+ value.dicCode + " title='"+value.dicValue+"' >"
        }
    });
    $("#partnerType").html('');
    $("#partnerType").append(result);
}

function selectBrand(){
    parent.layer.open({
        type: 2,
        title: '合作方品牌',
        area: ['800px', '660px'],
        content: BASE_PATH + '/developerBrand/openDialogDeveloperBrandList'
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();
            if (formData != null) {
            	var developerBrandCode = formData.developerBrandCode;
            	$("#developerBrandId").val(formData.developerBrandName);
            	$("#chooseDeveloperBrandId").val(formData.id);

                getCustomerAndYjDy(developerBrandCode);
                //关闭弹出层
                parent.layer.close(index);
            }
        }
    });
}