layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;

    yjfyPlanListInit();

    function yjfyPlanListInit() {

        tableRender();
    }

    function tableRender() {
        table.render({
            elem: '#yjfyTable'
            , cols: setCols()
            , url: BASE_PATH + '/pmlsEstatePlan/queryYjfyList/' + $("#projectNo").val()
            , id: 'contentReload'
            , height: 500
            , page: true
            , limits: [10, 20, 30]
            , limit: 10 //默认采用60
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
//            ,done: function(res, curr, count){
//	            $(".layui-table-main tr").each(function (index ,val) {
//	            	$(".layui-table-fixed").each(function () {
//	            		$($(this).find(".layui-table-body tbody tr")[index]).height($(val).height());
//	            	});
//	            });
//	            $(".layui-table-header tr").each(function (index ,val) {
//	            	$(".layui-table-fixed").each(function () {
//	            		$($(this).find(".layui-table-header thead tr")[index]).height($(val).height());
//	            	});
//	            });
//            }
        });
    }

    function setCols() {
            var row = [
                {field: 'planName', title: '方案名称', width: 87, align: 'center'},
                {field: 'fyPlanTypeName', title: '返佣方案类型', width: 120, align: 'center'},
                {field: 'companyNameStr', title: '经纪公司', width: 300, align: 'center'
                	, templet: function (d) {
                		if(d.companyNameStr == null || d.companyNameStr ==""){
                			return "-";
                		}else{
                			return "<div style='text-align: left'>" + d.companyNameStr + "</div>";
                		}
                    }
                },
                {field: 'planInfoDes', title: '方案描述', width: 620,align: 'center'
                	, templet: function (d) {
                        return "<div style='text-align: left'>" + d.planInfoDes + "</div>";
                    }
                },
                {field: 'isEnable', title: '启用状态',width: 90, align: 'center',
                	templet: function (d) {
                        if (d.isEnable) {
                            return "是";
                        } else {
                            return "否";
                        }
                    }
                },
                {field: 'userName', title: '创建人',width: 90, align: 'center'},
                {field: 'createDate', title: '创建日期',width: 105, align: 'center',
                	templet: function (d) {
                        if (d.createDate == null || d.createDate == "") {
                            return "";
                        } else {
                            return formatDate(d.createDate, "yyyy-MM-dd");
                        }
                    }
                },
                {field: 'isEnable', title: '操作',width: 150, align: 'center',
                	templet: function (d) {
                		var ret ="";
                		var type=-1;
                		if(d.isEdit=="1"){
                			ret += "<a class='layui-btn layui-btn-xs' onclick='editPlanNew(" + d.id +',' + d.isEdit + ")'>编辑</a>";
                		}
                		if (d.isEnable) {
                			type=0;
                			ret += "<a class='layui-btn layui-btn-xs layui-btn-danger' onclick='toIsEnable(" + d.id +',' + type + ")'>禁用</a>";
                			return ret;
                		} else {
                			type=1;
                			ret += "<a class='layui-btn layui-btn-xs' onclick='toIsEnable(" + d.id +',' + type + ")'>启用</a>";
                			return ret;
                		}
                	}
                }
            ]
            var cols = [];
            cols.push(row);
            return cols;
        }

});

function toIsEnable(id,type) {
    var projectNo = $("#projectNo").val();
    var str = "确认要禁用吗？";
    if(type==0){
        str = "确认要禁用吗？"
    }else{
        str = "确认要启用吗？";
    }
    parent.msgConfirm(str, function () {
        var url = BASE_PATH + "/pmlsEstatePlan/toIsEnable/"+id+"/"+projectNo+"/"+type;

        $.ajax({
            url: url,
            type: 'post',
            dataType: 'json',
            success: function (data) {
                console.log(data);
                if (data.returnCode == -1){
                    parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                } else {
                    parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'},function(){
                        parent.layer.closeAll();
                        window.parent.refreshRight();
                    });
                }
            }
        });
    });
}

