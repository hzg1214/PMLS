layui.use(['element', 'laydate', 'layedit', 'form', 'table', 'layer', 'upload'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        layedit = layui.layedit,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        formSelects = layui.formSelects,
        upload = layui.upload,
        $ = layui.$;

    init();

    function init() {
        loadImageList("storePicTabItem", storeDetailInfo.fileDtos, false);
        loadImageList("storeDecorationPicTabItem", storeDetailInfo.fileDecorationDtos, false);
    }

    var active = {
        operationUpdate: function () {
            operationUpdateToDialog();
        }
        , relateStore: function () {
            relateStoreDialog();
        }
//        , relateStoreUser: function () {
//            relateStoreUserDialog();
//        }
        , goback: function () {
            parent.redirectTo('delete', null, null);
        }
    }

    $(".layui-btn").on('click', function () {
        var type = $(this).attr('data-type');
        active[type] ? active[type].call(this) : '';
    });


    function operationUpdateToDialog() {
        parent.layer.open({
            type: 2
            , title: '运营维护门店信息'
            , content: BASE_PATH + '/store/toOperationUpdate'
            , area: ['800px', '600px']
            , btn: ['确定', '取消']
            , resize: false
            , yes: function (index, layero) {

                //确认的回调
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                var formData = iframeWin.getFormData();
                if (formData != null) {

                    parent.layer.close(index);
                    // 提交修改
                    submitOperationUpdate(formData)
                }
            }
        });
    }

    /**
     * 门店修改
     */
    function relateStoreDialog() {
    	var storeId = $('#storeId').val();
    	var pmlsCenterId = $('#pmlsCenterId').val();
    	var storeNo = $('#storeNo').val();
    	var contractStatus = $('#contractStatus').val();
    	var gpContractStatus = $('#gpContractStatus').val();
    	var acCityNo = $('#acCityNo').val();
        parent.layer.open({
            type: 2
            , title: '修改门店信息'
            , content: BASE_PATH + '/pmlsStore/toStoreUpdate?storeId='+storeId+'&storeNo='+storeNo
            +'&contractStatus='+contractStatus+'&gpContractStatus='+gpContractStatus
            +'&pmlsCenterId='+pmlsCenterId+'&acCityNo='+acCityNo
            , area: ['800px', '600px']
            , btn: ['保存', '取消']
            , resize: false
            , yes: function (index, layero) {

                //确认的回调
                var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                var formData = iframeWin.getStroeData();
                if (formData != null) {

                    parent.layer.close(index);
                    // 提交修改
                    submitOperationUpdate(formData)
                }
            }
        });
    }

    

    /**
     * 门店修改提交
     */
    function submitOperationUpdate(parms) {

    	var brandType = parms.isFyBrand;
    	if(brandType==1){
    		brandType=29401;
    	}
    	var pmlsCenterId = parms.pmlsCenterId;
    	var pmlsCneterName = parms.pmlsCenterName;
    	var pmlsMaintainCode = parms.pmlsMaintainCode;
    	var pmlsMaintainName = parms.pmlsMaintainName;
    	var storeId = $('#storeId').val();
    	var storeNo = $('#storeNo').val();
    	var saveUrl = BASE_PATH + '/pmlsStore/saveStoreData';
		var param = {
			"storeId" : storeId,
			"storeNo" : storeNo,
			"brandType" : brandType,
			"pmlsCenterId" : pmlsCenterId,
			"pmlsCneterName" : pmlsCneterName,
			"pmlsMaintainCode" : pmlsMaintainCode,
			"pmlsMaintainName" : pmlsMaintainName
		};
		// 保存选择的维护人
		$.ajax({
			url : saveUrl, // action目标
			type : 'POST',
			data : param,
			success : function(data) {
				var returndata = eval("(" + data + ")");
				if (returndata.returnCode != '200') {
					parent.layer.alert(returndata.returnMsg, {
						icon : 2,
						title : '提示'
					});
				} else {
					parent.layer.alert(returndata.returnMsg, {
						icon : 1,
						title : '提示'
					}, function() {
						parent.layer.closeAll();
						var backurl = BASE_PATH
								+ '/pmlsStore/storeDetail?id='
								+ storeId;
						window.location = backurl;
					});
				}
			}
		});
    }

});

var storeDetail = {}

storeDetail.showPicture = function () {
    parent.layer.open({
        type: 2
        , title: '信息修改说明'
        , content: BASE_PATH + '/pmlsStore/storeInfoEditRule'
        , area: ['780px', '380px']
        , resize: false
    });
}


//分配维护人
function relateStoreUser(storeId, storeCenterId) {
	var acCityNo = $('#acCityNo').val();
	parent.layer.open({
		type : 2,
		title : '选择维护人',
		area : [ '800px', '660px' ],
		content : '/pmlsStore/relateStoreUser?storeId=' + storeId
				+ "&storeCenterId=" + storeCenterId+ "&acCityNo=" + acCityNo,
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
//				parent.layer.load(2);
				//保存维护人信息
				var pmlsCenterId = formData.centerId;
		    	var pmlsCneterName = formData.centerName;
				var maintainer = formData.maintainer;
				var maintainerName = formData.maintainerName;
				var saveUrl = BASE_PATH + '/pmlsStoreMaintainer/savemt';
				var param = {
					"storeId" : storeId,
					"maintainer" : maintainer,
					"maintainerName" : maintainerName,
					"pmlsCenterId" : pmlsCenterId,
					"pmlsCneterName" : pmlsCneterName
				};
				// 保存选择的维护人
				$.ajax({
					url : saveUrl, // action目标
					type : 'POST',
					data : param,
					success : function(data) {
						var returndata = eval("(" + data + ")");
						if (returndata.returnCode != '200') {
							parent.layer.alert(returndata.returnMsg, {
								icon : 2,
								title : '提示'
							});
						} else {
							parent.layer.alert(returndata.returnMsg, {
								icon : 1,
								title : '提示'
							}, function() {
								parent.layer.closeAll();
								var backurl = BASE_PATH
										+ '/pmlsStore/storeDetail?id='
										+ storeId;
								window.location = backurl;
							});
						}
//						parent.layer.closeAll();
					}
				});
			}
		}
	});
}
