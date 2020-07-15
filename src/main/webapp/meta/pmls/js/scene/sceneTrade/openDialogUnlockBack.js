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
function getUnlockBackData(){
	var obj={};
	var unlockReason=$("#unlockReason").val();
	var relateId=$("#relateId").val();
	if(isEmpty(unlockReason)){
		parent.layer.alert('请输入解锁理由！', {icon: 2, title: '提示'});
		return;
	}
	
	var reportId=$("#reportId").val();
    obj.reportId=reportId;
    obj.relateId=relateId;
    obj.unlockReason=unlockReason;
    return obj;
}

