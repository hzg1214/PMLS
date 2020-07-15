/*
 * 借佣详情js
 * */
var layer;
var id=window.id;
var jsonDto={};

var form,upload,table;
layui.use(['layer','table'], function() {
    layer = layui.layer;
    table=layui.table;

    setTimeout(function(){
        if(window.hkPlanDto!=''){
            //jsonDto=eval('(' + window.hkPlanDto + ')');
            jsonDto=eval('(' + window.hkPlanDto.replace(/[\r\n]/g,"\\n") + ')');
            //初始化图片
        }
        if(jsonDto.sjhkDate!=null && jsonDto.sjhkDate!=''){
            $("#hkButton").hide();
        }

        //初始化利息表格
        table.render({
            elem: '#interestTable'
            ,url:ctx + '/borrowMoneyController/getHkPlanInterestList'
            ,where:{hkplanId:jsonDto.id}
            ,cellMinWidth: 150
            ,limit:1000
            ,totalRow: true
            ,cols: [[
                {field:'rowNum',width:100,  title: '序号',align: 'center'}
                ,{field:'theMonth',width:200, title: '年月',align: 'center', totalRowText: '合计'}
                ,{field:'interest',width:200,  title: '利息',align: 'center',totalRow: true,templet:function (row) {
                        return formatMoney(row.interest);
                    }}
            ]]
            , even: false //开启隔行背景
            , page: false
            , method: 'post'
            ,loading:true
            ,request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
            ,response: {
                statusName: 'returnCode' //数据状态的字段名称，默认：code
                ,statusCode: 200 //成功的状态码，默认：0
                ,msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                ,countName: 'totalCount' //数据总数的字段名称，默认：count
                ,dataName: 'returnData' //数据列表的字段名称，默认：data
            },done:function (data) {
                if(data.returnData.length==0){
                    $(".interestLayuiCard").hide();
                }
            }
        });

        //初始化房源明细表格
        table.render({
            elem: '#houseDetailTable'
            ,url:ctx + '/borrowMoneyController/getBorrowMoneyDetailList'
            ,where:{jssId:jsonDto.jssId}
            ,cellMinWidth: 150
            ,limit:1000
            ,totalRow: true
            ,cols: [[
                {field:'rowNum', width:80, title: '序号',align: 'center'}
                ,{field:'reportId',width:180,  title: '订单编号',align: 'center'}
                ,{field:'customerName', width:150, title: '客户姓名',align: 'center', totalRowText: '合计'}
                ,{field:'buildingNo',width:200,  title: '楼室号',align: 'center'}
                ,{field:'cxArea', width:100, title: '销售面积',align: 'center'}
                ,{field:'cxAmount',width:180,  title: '成销总价(元)',align: 'right',totalRow: true,templet:function (row) {
                        return formatMoney(row.cxAmount);
                    }}
                ,{field:'jsAmount',width:120, title: '结算金额(元)',align: 'right',totalRow: true,templet:function (row) {
                        return formatMoney(row.jsAmount);
                    }}
                ,{field:'kpAmount',width:120,  title: '开票金额(元)',align: 'right',totalRow: true,templet:function (row) {
                        return formatMoney(row.kpAmount);
                    }}
                ,{ field:'kpTaxAmount',width:120, title: '税额(元)',align: 'right',totalRow: true,templet:function (row) {
                        return formatMoney(row.kpTaxAmount);
                    }}
                ,{field:'jyAmount',width:120,  title: '借佣金额(元)',align: 'right',totalRow: true,templet:function (row) {
                        return formatMoney(row.jyAmount);
                    }}
            ]]
            , even: false //开启隔行背景
            , page: false
            , method: 'post'
            ,loading:true
            ,request: {
                pageName: 'curPage' //页码的参数名称，默认：page
                ,limitName: 'pageLimit' //每页数据量的参数名，默认：limit
            }
            ,response: {
                statusName: 'returnCode' //数据状态的字段名称，默认：code
                ,statusCode: 200 //成功的状态码，默认：0
                ,msgName: 'returnMsg' //状态信息的字段名称，默认：msg
                ,countName: 'totalCount' //数据总数的字段名称，默认：count
                ,dataName: 'returnData' //数据列表的字段名称，默认：data
            }
        });
    },100)

});
//取消返回上一个页面
function back(){
    parent.redirectTo('delete',null,null);
}

//还款
function updateHkPlan(){
    parent.layer.open({
        type: 2,
        btn: ['确认', '取消'],
        title: '还款',
        area: ['500px', '600px'],
        content: ctx + '/borrowMoneyController/updateHkPlanPage?id='+jsonDto.id+'&jkDate='+jsonDto.jkDate,
        yes: function(index, layero){
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            console.log(formData);
            if(formData!=null){
                parent.layer.close(index);//关闭弹窗

                parent.layer.load(2);
                $.ajax({
                    url: ctx + '/borrowMoneyController/updateHkPlan',
                    type: 'post',
                    data:formData,
                    dataType: 'json',
                    success: function (data) {
                        parent.layer.closeAll();
                        console.log(data);
                        if (data.returnCode != 200){
                            parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                        }else{
                            parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'},function(){
                                parent.layer.closeAll();
                                window.location.reload();
                            });
                        }
                    }
                });
            }

        }
    });
}
