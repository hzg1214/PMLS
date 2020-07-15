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
    $('#isFyBrand').attr("disabled","disabled");
    form.render('select');
    var active = {
    		//选择维护人
    		selPmlsMaintain:function(){
    			var storeId = $('#storeId').val();
    			var acCityNo = $('#acCityNo').val();
    			parent.layer.open({
    				type : 2,
    				title : '选择维护人',
    				area : [ '800px', '660px' ],
    				content : '/pmlsStore/relateStoreUser?storeId=' + storeId
    						+ "&acCityNo=" + acCityNo,
    				scrollbar : false,
    				resize : false,
    				btn : [ '确定', '取消' ],
    				yes : function(index, layero) {
    					//确认的回调
    					var iframeWin = parent.window[layero.find('iframe')[0]['name']];
    					var formData = iframeWin.getFormData();
    					console.log(formData);
    					if (formData != null) {
    						parent.layer.close(index);//关闭弹窗
    						//保存维护人信息
    						var maintainer = formData.maintainer;
    						$('#pmlsMaintainCode').val(maintainer);
    						var maintainerName = formData.maintainerName;
    						$('#pmlsMaintain').val(maintainerName);
    						$('#pmlsMaintainName').val(maintainerName);
    						var centerId = formData.centerId;
    						$('#pmlsCenterId').val(centerId);
    						var centerName = formData.centerName;
    						$('#pmlsCenterName').val(centerName);
    						$('#pmlsCenter').val(centerName);
    					}
    				}
    			});
            }
    }
    
    $('.storeUpdatePopupPage .layui-btn').on('click', function () {
    	var type = $(this).data('type');
    	active[type] ? active[type].call(this) : '';
    });
});


//获取参数
function getStroeData(){
	var obj={};
	var storeId=$("#storeId").val();//门店
	var isFyBrand=$("#isFyBrand").val();//是否门店
	var pmlsMaintainCode=$("#pmlsMaintainCode").val();
	var pmlsMaintainName=$("#pmlsMaintainName").val();
	var pmlsCenterId=$("#pmlsCenterId").val();
	var pmlsCenterName=$("#pmlsCenterName").val();
	if(isEmpty(isFyBrand)){
		parent.layer.alert('请选择是否渠道！', {
			icon : 2,
			title : '提示'
		});
		return;
	}
	if(isEmpty(pmlsMaintainCode) || isEmpty(pmlsMaintainName)){
		parent.layer.alert('请选择维护人！', {
			icon : 2,
			title : '提示'
		});
		return;
	}
    
    obj.storeId=storeId;
    obj.isFyBrand=isFyBrand;
    obj.pmlsMaintainCode=pmlsMaintainCode;
    obj.pmlsMaintainName=pmlsMaintainName;
    obj.pmlsCenterId=pmlsCenterId;
    obj.pmlsCenterName=pmlsCenterName;
    return obj;
}

