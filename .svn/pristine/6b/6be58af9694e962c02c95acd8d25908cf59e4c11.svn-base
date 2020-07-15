var active;
var layer;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        formSelects = layui.formSelects,
        $ = layui.$;
    layer = layui.layer;

    init();

    function init() {

        form.render('select'); //刷新select选择框渲染

        tableRender();

    }
    
    function tableRender() {
        table.render({
            elem: '#mainTable'
            , cols: setCols()
            , id: 'contentReload'
            , data: []
            , page: true
            , height: "full"
            , limits: [10, 20, 30, 50, 100]
            , limit: 50 //默认采用60
            , method: 'post'
            , loading: false
            , request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
            , response: {
                statusName: 'returnCode' //数据状态的字段名称，默认：code
                , statusCode: 200 //成功的状态码，默认：0
                , msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                , countName: 'totalCount' //数据总数的字段名称，默认：count
                , dataName: 'returnData' //数据列表的字段名称，默认：data
            }
        });
    }

    function setCols() {
        var row = [];
        row.push(
            {type: 'checkbox', fixed: true},
            {field: 'storeNo', fixed: true, title: '门店编号', width: 150, align: 'center',style:'cursor: pointer;color:#01AAED'
            	, templet: function (d) {
	                return "<a class='layui-table-link' onclick='javascript:showDetail(\""  + d.id +  "\")'>" + d.storeNo + "</a>";
	            }
            },
            {field: 'storeName', fixed: true, title: '门店名称', width: 200, align: 'center', style: 'text-align: left'},
            {field: 'pmlsGroupName', title: '联动维护中心', width: 200, align: 'center'},
            {field: 'addressDetail', title: '门店地址', width: 250, align: 'center', style: 'text-align: left'},
            {field: 'companyNo',title: '公司编号',width:160, align: 'center'},
            {field: 'companyName',title: '所属公司',width:250, align: 'center', style: 'text-align: left'},
            {field: 'isFyStoreStr',title: '房友门店',width:160, align: 'center'},
            {field: 'pmlsMaintainName',title: '联动维护人' ,width:150,align: 'center'},
            {field: 'dateCreate',title: '创建时间' ,width:150,align: 'center',
            	templet: function (d) {
                    if (d.dateCreate == null || d.dateCreate == "") {
                        return "";
                    } else {
                    	return formatDate(d.dateCreate, "yyyy-MM-dd");
                    }
                }
            }
        );

        var cols = [];
        cols.push(row);
        return cols;
    }

    reloadData();//初始化加载表格

    active = {
        reload: function () {
            if (valid()) {
                var optionsData = {};
                optionsData.storeCityNo = $("#storeCityNo").val();//所在城市
                optionsData.isFyStore = $("#isFyStore").val();//是否房友
                optionsData.groupId = $("#groupId").val();//维护中心

                var storeNo = trimStr($("#storeNo").val());//门店
                if(isIncludeBlank(storeNo)){
                	parent.layer.alert('门店中间有空格，请重新输入！', {
                        icon: 2,
                        title: '提示'
                    });
                	return;
                }
                var maintenanceCode = trimStr($("#maintenanceCode").val());//维护人
                if(isIncludeBlank(maintenanceCode)){
                	parent.layer.alert('维护人中间有空格，请重新输入！', {
                		icon: 2,
                		title: '提示'
                	});
                	return;
                }
                optionsData.storeNo = storeNo;
                optionsData.maintenanceCode = maintenanceCode;

                optionsData.objNum = 0;
                if ($("[name = objNum]:checkbox").is(":checked")) {
                    optionsData.objNum = 1;
                }
                reloadData(optionsData);
            }
        },
        reset: function () {
            $("#storeCityNo").val("");
            $("#isFyStore").val("");
            $("#groupId").val("");
            $("#storeNo").val("");
            $("#maintenanceCode").val("");
            $("#objNum").prop("checked", false);
            form.render('select');
            form.render('checkbox')
            active.reload();
        },
        batchChangeMaintenance: function () {
            var checkStatus = table.checkStatus("contentReload"); //获取选中行状态

            var dataList = checkStatus.data; //获取选中行的数据
            var dataLen = checkStatus.data.length; //获取选中行数量
            if (dataLen <= 0) {
                parent.msgAlert("请至少选择一个需要门店维护的记录！")
                return;
            }

            var flag = false;
            var ids = "";

            for (i = 0; i < dataLen; i++) {
                var data = dataList[i];
                ids += data.id + ',';
            }

            if (ids != "") {
            	ids = ids.substr(0, ids.length - 1);
            }
            
            //弹窗
            storeMaintenanceToDialog(ids);
        }

    };

    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
    	tableRender();
        var index = layer.load(2);
        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsStoreMaintenance/queryList',
            cols: setCols(),
            //height: window.innerHeight - $(".layui-table-view").offset().top - 30,
            where: optionsData,
            page:{
                curr: 1 //重新从第 1 页开始
            },
            done: function () {
                layer.close(index);
            }
        });
    }

    function valid() {
        return true;
    }

	//分配维护人
	function storeMaintenanceToDialog(ids) {
		var acCityNo = $('#acCityNo').val();
		parent.layer.open({
			type : 2,
			title : '选择维护人',
			area : [ '800px', '660px' ],
			content : '/pmlsStore/relateStoreUser?acCityNo=' + acCityNo,
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
//    					parent.layer.load(2);
					//保存维护人信息
					var pmlsCenterId = formData.centerId;
			    	var pmlsCneterName = formData.centerName;
					var maintainer = formData.maintainer;
					var maintainerName = formData.maintainerName;
					var saveUrl = BASE_PATH + '/pmlsStoreMaintenance/batchChangeMaintenance';
					var param = {
						"ids" : ids,
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
//									var backurl = BASE_PATH
//											+ '/pmlsStore/storeDetail?id='
//											+ storeId;
//									window.location = backurl;
									reloadData();
								});
							}
						}
					});
				}
			}
		});
	}
});


//查看
function showDetail(id){
    var url=ctx+"/pmlsStore/storeDetail?id="+id;
    parent.redirectTo('append',url,'门店详情');
}
