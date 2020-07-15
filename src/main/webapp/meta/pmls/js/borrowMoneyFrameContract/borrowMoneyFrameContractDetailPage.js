/*
 * 渠道品牌js
 * */
var layer;
var id=window.id;
var jsonDto={};
var jyComList=[];
var form,upload,table;
layui.use(['layer','table'], function() {
    layer = layui.layer;
    table=layui.table;

    setTimeout(function(){
        if(window.frameContractDto!=''){
            jsonDto=eval('(' + window.frameContractDto.replace(/[\r\n]/g,"\\n") + ')');
            //初始化图片

            //初始化加载图片
            loadImageList('fileBusiness',jsonDto.fileBusinessList);
            loadImageList('fileContract',jsonDto.fileContractList);
            loadImageList('fileOther',jsonDto.attachmentFileList);

            //是否显示提交审核按钮
            if(window.oaOperator=='1' && (jsonDto.approveState=='10401' || jsonDto.approveState=='10404')){//草签或审核未通过
                if(jsonDto.submitOAStatus!='21202'){//不等于处理中
                    $(".submitOABtn").show();
                }
            }
        }
    },100)
    if(window.frameContractDto!=''){
            jsonDto=eval('(' + window.frameContractDto.replace(/[\r\n]/g,"\\n") + ')');
            jyComList = jsonDto.jyComList;
    }
    table.render({
        elem: '#contentCompanyTable'
        , cols: setJyComCols()
        , data: jyComList
        ,width:1000
        , height: 'full'
        , id: 'contentCompanyReload'
        , even: false //开启隔行背景
        , page: false
        , limits: [10, 20, 30]
        , limit: 1000 //默认采用60
        , method: 'post'
        , request: {
            pageName: 'curPage' //页码的参数名称，默认：page
            , limitName: 'pageLimit' //每页数据量的参数名，默认：limit
        }
    });

});

function setJyComCols() {

	var row1 = [];
    row1.push(
		{field:'companyNo',title: '公司编号',width:195, align: 'center'},
		{field:'companyName',title: '公司名称',width:400, align: 'center'
			, templet: function (d) {
                return "<div style='text-align: left'>" + d.companyName + "</div>";
            }
		},
        {field:'address',title: '地址',width:400, align: 'center'
        	, templet: function (d) {
                return "<div style='text-align: left'>" + d.address + "</div>";
            }
        }
    );
    var cols = [];
    cols.push(row1);
    return cols;
}

//取消返回上一个页面
function back(){
    parent.redirectTo('delete',null,null);
}

//提交审核
function submitOA(contractId){
    if($("#autoToOa").val()!=1){
    	var accountProject = $("#accountProject").val();
        var accountProjectCode = $("#accountProjectCode").val();
        var borrowMoneyFrameContractDto={};
        borrowMoneyFrameContractDto.accountProject=accountProject;
        borrowMoneyFrameContractDto.accountProjectCode=accountProjectCode;
        borrowMoneyFrameContractDto.id=contractId;
        $.ajax({
            url:BASE_PATH+"/borrowMoneyFrameContract/updateBorrowMoneyFrameContract",
            async: false,
            dataType: 'json',
            contentType: 'application/json;charset=utf-8', //设置请求头信息
            data:JSON.stringify(borrowMoneyFrameContractDto),
            type:"post",
            success:function(data){
            },
            error:function(){
            }
        });
        parent.layer.closeAll();
        submitToOA();
    }
}


function submitToOA(contractId){
	$("#submitToOA").hide();
	ajaxGet(ctx+"/borrowMoneyFrameContract/submitToOA/"+window.id,null,function (data) {
		if(data.returnCode != "200"){
			parent.layer.closeAll();
			parent.msgError(data.returnMsg);
		}else{
//			parent.layer.closeAll();
//			parent.msgAlert("提交成功!");
//			window.location.reload();
			parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'},function(){
                parent.layer.closeAll();
                window.parent.refreshRight();
            });
		}
	},function (data) {
		parent.layer.closeAll();
		if(data.returnMsg){
			parent.msgError(data.returnMsg);
		}
	});
}
