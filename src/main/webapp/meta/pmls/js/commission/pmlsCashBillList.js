var active;
var layer;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        $ = layui.$;
        layer = layui.layer;
    // 楼盘归属项目部
/*    dictionaryLinkageIsService('approveStatus',254,function () {
        form.render('select')
    });*/

    form.render('select'); //刷新select选择框渲染


    table.render({
        elem: '#contentTable'
        , cols: setCols()
        , id: 'contentReload'
        ,data:[]
        , even: false //开启隔行背景
        , page: true
        , height: "full"
        , limits: [10,20,30]
        , limit: 10 //默认采用10
        ,loading:false
        , method: 'post'
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

    function setCols() {
        var row1 = [], row2 = [];
        row1.push(
            {field:'cashBillNo',title: '请款编号',width:150, align: 'center',rowspan: 2, templet: function (d) {
                    return "<a class='layui-table-link' onclick='javascript:showDetail(\"" + d.comParentId + "\",\"" + d.proParentId + "\")'>" + d.cashBillNo + "</a>";
                }
            },
            {field: 'pjsNostr',title: '结算书编号',width:150, align: 'center',rowspan: 2},
            {field: 'companyName',title: '渠道公司',width:150, align: 'center',rowspan: 2,style:"text-align:left"},
            {field: 'estateNm',title: '项目名称',width:150, align: 'center',rowspan: 2,style:"text-align:left"},
            {field: 'amountTotal',title: '请款金额',width:150, align: 'center',rowspan: 2,
                templet: function (d) {
                    return formatMoney2(d.amountTotal);
                }},
            {field: 'approveStatusNm',title: '审核状态',width:105, align: 'center',rowspan: 2},
            {field: 'userName',title: '申请人',width:105, align: 'center',rowspan: 2},

            {field: 'applyTime',title: '申请日期',width:105, align: 'center',rowspan: 2,templet:function (d) {
                if(!isBlank(d.applyTime)){
                   return formatDate(d.applyTime,'yyyy-MM-dd');
                }else {
                    return "";
                }
            }},
            {
                title: '操作', fixed: 'right', align: 'center', width: 205, templet: function (d) {
                    var ret = "<div style='text-align: left'><a class='layui-btn layui-btn-mini' onclick='showDetail(\"" + d.comParentId + "\",\"" + d.proParentId + "\")'>查看</a>";
        /*            if((d.approveStatus==25401 || isBlank(d.approveStatus)) && d.submitOaStatus==21204
                        && ($("#QK_DEL").val()=='1' || $("#userCode").val() == d.userCode)
                    ){
                        ret += "<a class='layui-btn layui-btn-normal layui-btn-mini' onclick='submitTr(\"" + d.estateId + "\",\"" + d.companyId + "\",\"" + d.proParentId + "\")'>提交OA</a>";
                        ret += "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='removeTr(\"" + d.estateId + "\",\"" + d.comParentId + "\",\"" + d.proParentId + "\")'>删除</a>";
                    }*/


                    if(
                        (
                            ((d.approveStatus==25401 || isBlank(d.approveStatus)) && (d.submitOaStatus==21201 || d.submitOaStatus==21204) && "1"!=d.inValid)
                            ||(d.approveStatus==25404 && "1"!=d.inValid)
                        )
                        && ($("#QK_INVALID").val()=='1' || $("#userCode").val() == d.userCode)
                        && !isBlank(d.pjsNostr)
                    ){
                        ret += "<a class='layui-btn layui-btn-normal layui-btn-mini' onclick='edit(\"" + d.comParentId + "\",\"" + d.proParentId + "\")'>编辑</a>";
                        ret += "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='invalidBill(\"" + d.comParentId + "\",\"" + d.proParentId + "\")'>作废</a>";
                    }
                    ret += "</div>";
                    return ret;
                }
            }
        );

        var cols = [];
        cols.push(row1);
        return cols;
    }

    reloadData();//初始化加载表格
    active = {
        reload: function () {
            if (valid()) {
                var optionsData = {};
                var cashValues = trimStr($("#cashValues").val());
                if(isIncludeBlank(cashValues)){
                	parent.layer.alert('编号中间有空格，请重新输入！');
                	return;
                }
                var reportNo = trimStr($("#reportNo").val());
                if(isIncludeBlank(reportNo)){
                    parent.layer.alert('订单编号中间有空格，请重新输入！');
                    return;
                }
                var projectValues = trimStr($("#projectValues").val());
                if(isIncludeBlank(projectValues)){
                	parent.layer.alert('项目中间有空格，请重新输入！');
                	return;
                }
                var companyValues = trimStr($("#companyValues").val());
                if(isIncludeBlank(companyValues)){
                	parent.layer.alert('渠道公司中间有空格，请重新输入！');
                	return;
                }
      /*          var userName = trimStr($("#userName").val());
                if(isIncludeBlank(userName)){
                	parent.layer.alert('申请人中间有空格，请重新输入！');
                	return;
                }*/
                optionsData.cashValues = cashValues;
                optionsData.reportNo = reportNo;
                optionsData.projectValues = projectValues;
                optionsData.companyValues = companyValues;
                //optionsData.userName = userName;
                optionsData.approveStatus = $("#approveStatus").val();
                sessionStorage.removeItem('PMLS_CASH_BILL_SEARCH');
                reloadData(optionsData);
            }
        }
        ,reset: function () {
            console.info("all.reset");

            $("#cashValues").val("");
            $("#reportNo").val("");
            $("#projectValues").val("");
            $("#companyValues").val("");
            //$("#userName").val("");
            $("#approveStatus").val("");
            form.render('select');

            active.reload();

        }
        ,addPmlsCashBill:function () {
            window.parent.redirectTo('append',"/sceneExpent/batchExpentNewJs/0/0",'新建请款单');
        }
    };

    $('.toolbar .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    function reloadData(optionsData) {
        var index =  parent.layer.load(2);

        var sessionData=sessionStorage.getItem('PMLS_CASH_BILL_SEARCH');
        var pageIndex=1;
        if(sessionData!=null && sessionData!=''){
           /* dictionaryLinkageIsService('approveStatus',254,function () {
                $("#approveStatus").val(optionsData.approveStatus);
                form.render('select');
            });*/
            optionsData=JSON.parse(sessionData);
            pageIndex=JSON.parse(sessionData).curr;
            $("#cashValues").val(optionsData.cashValues);
            $("#reportNo").val(optionsData.reportNo);
            $("#projectValues").val(optionsData.projectValues);
            $("#companyValues").val(optionsData.companyValues);
            //$("#userName").val(optionsData.userName);
            $("#approveStatus").val(optionsData.approveStatus);
            form.render('select');
        }

        table.reload('contentReload', {
            url: BASE_PATH + '/pmlsCashBill/getCashBillList',
            cols: setCols(),
            where: optionsData,
            height: window.innerHeight - $(".layui-table-view").offset().top - 10,
            page:{
                curr: pageIndex //重新从第 1 页开始
            },
            done:function (res, curr, count) {
                parent.layer.close(index);
                if(!optionsData){
                    optionsData={};
                }
                optionsData.curr=curr;
                sessionStorage.setItem("PMLS_CASH_BILL_SEARCH",JSON.stringify(optionsData));
            }
        });
    }


    function valid() {
        return true;
    }
});


var params = {
    data: function () {
        var params = {
            cashValues : $("#cashValues").val(),
            reportNo : $("#reportNo").val(),
            projectValues : $("#projectValues").val(),
            companyValues : $("#companyValues").val(),
            //userName : $("#userName").val(),
            approveStatus : $("#approveStatus").val()
        };
        return params;
    }
};

function edit(comParentId,proParentId) {
    window.parent.redirectTo('append',"/sceneExpent/batchExpentNewJs/"+comParentId+"/"+proParentId,'编辑请款单');
};


function invalidBill(comParentId,proParentId) {
    if(!isBlank(comParentId)&& !isBlank(proParentId)) {
        var params = {
            comParentId : comParentId,
            proParentId :proParentId
        };

        parent.layer.confirm("是否确定作废这条记录?",{icon: 3, title:'提示'},function(){
            parent.layer.load(2);
            restPost(BASE_PATH + "/sceneExpent/invalidBill", params, function(data) {
                parent.layer.closeAll();
                active.reload();
            }, function(data) {
                parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'},function(){
                    parent.layer.closeAll();
                });
            });
        });
    }
};


function submitTr(estateId,companyId,proParentId) {
    if(!isBlank(estateId) && !isBlank(companyId)&& !isBlank(proParentId)) {
        parent.layer.load(2);
        $.ajax({
            url:BASE_PATH+"/pmlsCashBill/listToSubmitOa",
            data:$.param({
                estateId:estateId,
                companyId:companyId,
                proParentId:proParentId
            }),
            type:"post",
            success:function(data){
                parent.layer.closeAll();
                if(data){
                    data = JSON.parse(data);
                    var returnCode = data.returnCode;
                    if("-1"==returnCode){
                        parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                    }else {
                        parent.msgProp(data.returnMsg);
                        active.reload();
                    }
                }
            },
            error:function(){
                parent.layer.alert("提交失败", {icon: 2, title:'提示'},function () {
                    parent.layer.closeAll();
                });
            }
        })
    }

};

//查看
function showDetail(comParentId,proParentId){
    var url= BASE_PATH + "/pmlsCashBill/getCashBillDeatilById/" + comParentId + "/" + proParentId
    parent.redirectTo('append',url,'请款详情');
}
