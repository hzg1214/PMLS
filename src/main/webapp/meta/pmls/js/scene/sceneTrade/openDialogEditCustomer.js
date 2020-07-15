var layer;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer;
        $ = layui.$;
    formSelects = layui.formSelects;
    
});



//获取参数
function getCustomerFormData(){
	var obj={};
	var customerNm = trimStr($("#newCustomerNm").val());//客户1
	var customerNmTwo = trimStr($("#newCustomerNmTwo").val());//客户2
    if(isIncludeBlank(customerNm)){
    	parent.layer.alert('客户中间有空格，请重新输入！', {
            icon: 2,
            title: '提示'
        });
    	return;
    }
    if(!isEmpty(customerNmTwo)){
    	if(isIncludeBlank(customerNmTwo)){
        	parent.layer.alert('客户中间有空格，请重新输入！', {
                icon: 2,
                title: '提示'
            });
        	return;
        }
    }
	var customerTel=$("#newCustomerTel").val();
	
	var customerTelTwo=$("#newCustomerTelTwo").val();
	var userCode=$("#userCode").val();
	var reportId=$("#reportId").val();
	var relateId=$("#relateId").val();
	var userName=$("#userName").val();
	var userIdCreate=$("#userIdCreate").val();
	
	var oldCustomerNm=$("#oldCustomerNm").val();
	var oldCustomerTel=$("#oldCustomerTel").val();
	var oldCustomerNmTwo=$("#oldCustomerNmTwo").val();
	var oldCustomerTelTwo=$("#oldCustomerTelTwo").val();
	if(oldCustomerNm == customerNm && oldCustomerTel == customerTel && oldCustomerNmTwo== customerNmTwo&& oldCustomerTelTwo == customerTelTwo ){
		layer.alert('客户信息未做更改',{icon: 2, title:'提示'});
		return;
	}
	if(isEmpty(customerNm) || isEmpty(customerTel)){
		layer.alert('请输入客戶姓名和电话！',{icon: 2, title:'提示'});
		return;
	}
	if(!isEmpty(customerNm)) {
		if (isEmpty(customerTel)) {
			layer.alert('客户已填写，客户手机必须填写！',{icon: 2, title:'提示'});
			return;
		}
	}
	if(!isEmpty(customerNmTwo)) {
		if (isEmpty(customerTelTwo)) {
			layer.alert('客户已填写，客户手机必须填写！',{icon: 2, title:'提示'});
			return;
		}
	}
	if(!isEmpty(customerTel)) {
		if(!checkPhoneNumber(customerTel)){
			layer.alert('请输入正确格式号码',{icon: 2, title:'提示'});
			return;
		}
		if (isEmpty(customerNm)) {
			layer.alert('客户手机已填写，客户必须填写！',{icon: 2, title:'提示'});
			return;
		}
	}
	if(!isEmpty(customerTelTwo)) {
		if(!checkPhoneNumber(customerTelTwo)){
			layer.alert('请输入正确格式号码',{icon: 2, title:'提示'});
			return;
		}
		if (isEmpty(customerNmTwo)) {
			layer.alert('客户手机已填写，客户必须填写！',{icon: 2, title:'提示'});
			return;
		}
	}
    obj.userCode=userCode;
    obj.reportId=reportId;
    obj.relateId=relateId;
    obj.userName=userName;
    obj.userIdCreate=userIdCreate;
    
    obj.oldCustomerNm=oldCustomerNm;
    obj.oldCustomerTel=oldCustomerTel;
    obj.oldCustomerNmTwo=oldCustomerNmTwo;
    obj.oldCustomerTelTwo=oldCustomerTelTwo;
    
    obj.customerNm=customerNm;
    obj.customerTel=customerTel;
    obj.customerNmTwo=customerNmTwo;
    obj.customerTelTwo=customerTelTwo;
    
    return obj;
}

