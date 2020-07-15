var planId=window.planId;
var jsonDto={};

var active;
var layer;
var formSelects;
var addCol;
var addCol01;
var addCol02;
var addCol03;
var addCol04;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        $ = layui.$;
    layer = layui.layer;
    formSelects = layui.formSelects;


    if(window.lnkYjPlanDtoJson!=null && window.lnkYjPlanDtoJson!=''){
        //jsonDto=eval('(' + window.lnkYjPlanDtoJson + ')');
        jsonDto=eval('(' + window.lnkYjPlanDtoJson.replace(/[\r\n]/g,"\\n") + ')');
    }

    laydate.render({
        elem: '#periodBeginDate',
        type: 'date',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        value: formatDate(jsonDto.periodBeginDate,'yyyy-MM-dd'),
        done: function (value, date, endDate) {
            var startDate = new Date(value).getTime();
            if($('#periodEndDate').val()!=''){
                var endTime = new Date($('#periodEndDate').val()).getTime();
                if (endTime < startDate) {
                    layer.msg('开始时间不能大于结束时间');
                    $('#periodBeginDate').val('');
                }
            }
        }
    });

    laydate.render({
        elem: '#periodEndDate',
        type: 'date',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        value: formatDate(jsonDto.periodEndDate,'yyyy-MM-dd'),
        done: function (value, date, endDate) {
            if($('#periodBeginDate').val()!=""){
                var startDate = new Date($('#periodBeginDate').val()).getTime();
                var endTime = new Date(value).getTime();
                if (endTime < startDate) {
                    layer.msg('结束时间不能小于开始时间');
                    $('#periodEndDate').val('');
                }
            }
        }
    });
    form.render();

    initWy();
    function initWy() {
        var result = [];
        $.ajax({
            url: ctx + '/pmlsEstatePlan/querylnkYjWyInfo',
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.returnCode == 200 || data.returnCode == '200'){
                    $.each(data.returnData, function (n, value) {
                        result.push({"name": value.wyTypName, "value": value.wyTypeCode});
                    });
                    formSelects.data('yjWyList', 'local', {arr: result});
                    form.render();

                    var yjPlanWyListArr = [];
                    $.each(jsonDto.yjPlanWyList, function (n, value) {
                        yjPlanWyListArr.push(value.wyTypeCode);
                    });
                    formSelects.value('yjWyList', yjPlanWyListArr);
                    form.render();
                }
            }
        });

    }











    $("#planType0").prop("disabled","disabled");
    $("#planType1").prop("disabled","disabled");

    //收入 返佣
    form.on('radio(planType)', function(data){
        console.log("planType="+data.value);
        if(data.value=='28701'){
            $("#fyPlanTypeDiv").hide();
            $("#companyDiv").hide();
        }else{
            $("#fyPlanTypeDiv").show();

            var fyPlanType = $("input[name='fyPlanType']:checked").val();
            if(fyPlanType=='28801'){
                $("#companyDiv").hide();
            }else {
                $("#companyDiv").show();
            }
        }
    });

    form.on('radio(fyPlanType)', function(data){
        console.log("fyPlanType="+data.value);
        if(data.value=='28801'){
            $("#companyDiv").hide();
        }else {
            $("#companyDiv").show();
        }
    });

    form.on('radio(equationType)', function(data){
        console.log("equationType="+data.value);
        if(data.value=='28101'){
            $("#equationTypeDiv01").show();
            $("#equationTypeDiv02").hide();
            $("#equationTypeDiv03").hide();
            $("#equationTypeDiv04").hide();
        }else if(data.value=='28102'){
            $("#equationTypeDiv01").hide();
            $("#equationTypeDiv02").show();
            $("#equationTypeDiv03").hide();
            $("#equationTypeDiv04").hide();

        }else if(data.value=='28103'){
            $("#equationTypeDiv01").hide();
            $("#equationTypeDiv02").hide();
            $("#equationTypeDiv03").show();
            $("#equationTypeDiv04").hide();
        }else if(data.value=='28104'){
            $("#equationTypeDiv01").hide();
            $("#equationTypeDiv02").hide();
            $("#equationTypeDiv03").hide();
            $("#equationTypeDiv04").show();
        }
    });










    //01
    table.render({
        elem: '#content01Table'
        , cols: setCols01()
        , id: 'content01Reload'
        ,width:705
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
    function setCols01() {
        var row1 = [];
        row1.push(
            {field:'fieldTj',title: '条件',width:202, align: 'center'},
            {field:'fieldYj',title: '佣金',width:500, align: 'center'}
        );
        var cols = [];
        cols.push(row1);
        return cols;
    }




    //02
    table.render({
        elem: '#content02Table'
        , cols: setCols02()
        , id: 'content02Reload'
        ,width:705
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
    function setCols02() {
        var row1 = [];
        row1.push(
            {field:'fieldTj',title: '条件',width:202, align: 'center'},
            {field:'fieldYj',title: '佣金',width:500, align: 'center'}
        );
        var cols = [];
        cols.push(row1);
        return cols;
    }


    //03
    table.render({
        elem: '#content03Table'
        , cols: setCols03()
        , id: 'content03Reload'
        ,width:705
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
    function setCols03() {
        var row1 = [];
        row1.push(
            {field:'fieldTj',title: '条件',width:340, align: 'center'},
            {field:'fieldYj',title: '佣金',width:260, align: 'center'},
            {
                title: '操作', width: 100, align: 'center', templet: function (d) {
                    var ret = "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='removeTr03(this)'>删除</a>";
                    return ret;
                }
            }
        );
        var cols = [];
        cols.push(row1);
        return cols;
    }


    //04
    table.render({
        elem: '#content04Table'
        , cols: setCols04()
        , id: 'content04Reload'
        ,width:705
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
    function setCols04() {
        var row1 = [];
        row1.push(
            {field:'fieldTj',title: '条件',width:340, align: 'center'},
            {field:'fieldYj',title: '佣金',width:260, align: 'center'},
            {
                title: '操作', width: 100, align: 'center', templet: function (d) {
                    var ret = "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='removeTr04(this)'>删除</a>";
                    return ret;
                }
            }
        );
        var cols = [];
        cols.push(row1);
        return cols;
    }




    //05
    table.render({
        elem: '#contentCompanyTable'
        , cols: setCols()
        , id: 'contentCompanyReload'
        ,width:705
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
    //05
    function setCols() {
        var row1 = [];
        row1.push(
            {field:'companyNoSelect',title: '渠道公司名称',width:550, align: 'center'},
            {
                title: '操作', width: 152, align: 'center', templet: function (d) {
                    var ret = "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='removeTr(this)'>删除</a>";
                    return ret;
                }
            }
        );
        var cols = [];
        cols.push(row1);
        return cols;
    }


    form.render();


    if(jsonDto.planType=='28701'){
        $("#fyPlanTypeDiv").hide();
        $("#companyDiv").hide();
        addCol('','','');
    }else{
        $("#fyPlanTypeDiv").show();
        if(jsonDto.fyPlanType=='28801'){
            $("#companyDiv").hide();
            addCol('','','');
        }else {
            $("#companyDiv").show();
        }
    }

    if(jsonDto.equationType=='28101'){
        $("#equationTypeDiv01").show();
        $("#equationTypeDiv02").hide();
        $("#equationTypeDiv03").hide();
        $("#equationTypeDiv04").hide();
        //addCol01('','');
        addCol02('');
        addCol03(0,'','','',0);
        addCol04(0,'','','',0);
    }else if(jsonDto.equationType=='28102'){
        $("#equationTypeDiv01").hide();
        $("#equationTypeDiv02").show();
        $("#equationTypeDiv03").hide();
        $("#equationTypeDiv04").hide();
        addCol01('','');
        //addCol02('');
        addCol03(0,'','','',0);
        addCol04(0,'','','',0);

    }else if(jsonDto.equationType=='28103'){
        $("#equationTypeDiv01").hide();
        $("#equationTypeDiv02").hide();
        $("#equationTypeDiv03").show();
        $("#equationTypeDiv04").hide();
        addCol01('','');
        addCol02('');
        //addCol03(0,'','','',0);
        addCol04(0,'','','',0);
    }else if(jsonDto.equationType=='28104'){
        $("#equationTypeDiv01").hide();
        $("#equationTypeDiv02").hide();
        $("#equationTypeDiv03").hide();
        $("#equationTypeDiv04").show();
        addCol01('','');
        addCol02('');
        addCol03(0,'','','',0);
        //addCol04(0,'','','',0);
    }



    $.each(jsonDto.yjPlanDetailList, function (i, value) {
        var conditionBegin = value.conditionBegin;
        var conditionEnd = value.conditionEnd;
        var fixAmount = value.fixAmount;
        var totalPercentage = value.totalPercentage;
        var isDel = 1;
        if(i==0){
            isDel = 0;
        }
        if(isNullEmpty(conditionEnd)){
            conditionEnd = '';
        }
        if(isNullEmpty(fixAmount)){
            fixAmount = 0;
        }
        if(isNullEmpty(totalPercentage)){
            totalPercentage = 0;
        }
        if(jsonDto.equationType=='28101'){
            addCol01(fixAmount,totalPercentage);
        }else if(jsonDto.equationType=='28102'){
            addCol02(fixAmount);
        }else if(jsonDto.equationType=='28103'){
            addCol03(conditionBegin,conditionEnd,fixAmount,totalPercentage,isDel);
        }else if(jsonDto.equationType=='28104'){
            addCol04(conditionBegin,conditionEnd,fixAmount,totalPercentage,isDel);
        }
    });

    $.each(jsonDto.yjPlanCompanyList, function (i, value) {
        addCol(value.companyNo,value.companyName,value.companyName)
    });
    form.render();
});

function addColPage03() {
    var tbody = $("div[lay-id='content03Reload'] .layui-table-main tbody");
    var trLast = $(tbody).find("tr[tag='detail03Tr']:last");
    var conditionBegin = $(trLast).find("input[name='conditionBegin']").val();
    var conditionEnd = $(trLast).find("input[name='conditionEnd']").val();
    if(!checkIsNumber(conditionEnd) ){
        parent.layer.alert("最后一行条件请输入合法的数字", {icon: 2, title: '提示'});
        return false;
    }

    if(parseFloat(conditionEnd)<=parseFloat(conditionBegin)){
        parent.layer.alert("结束值必须大于开始值", {icon: 2, title: '提示'});
        return false;
    }
    console.log("last conditionBegin="+conditionBegin+"&conditionEnd="+conditionEnd);
    addCol03(conditionEnd,'','','',1);

}

function addColPage04() {
    var tbody = $("div[lay-id='content04Reload'] .layui-table-main tbody");
    var trLast = $(tbody).find("tr[tag='detail04Tr']:last");
    var conditionBegin = $(trLast).find("input[name='conditionBegin']").val();
    var conditionEnd = $(trLast).find("input[name='conditionEnd']").val();
    if(!checkIsNumber(conditionEnd) ){
        parent.layer.alert("最后一行条件请输入合法的数字", {icon: 2, title: '提示'});
        return false;
    }

    if(parseFloat(conditionEnd)<=parseFloat(conditionBegin)){
        parent.layer.alert("结束值必须大于开始值", {icon: 2, title: '提示'});
        return false;
    }
    console.log("last conditionBegin="+conditionBegin+"&conditionEnd="+conditionEnd);
    addCol04(conditionEnd,'','','',1);
}


function addCol01(fixAmount,totalPercentage){
    var contentTrStr = '';
    contentTrStr += '<tr tag="detail01Tr">'

    contentTrStr += '<td data-field="fieldTj" align="center" data-content="">';
    contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-0"><span>每套</span>'
    contentTrStr += '</div></td>';


    contentTrStr += '<td data-field="fieldYj">';
    contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-1"><span>固定</span>'
    contentTrStr += '<input type="text" name="fixAmount" value="' + fixAmount + '" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" style="width: 40%; height:90%;text-align:right" autocomplete="off"/>';
    contentTrStr += '<span>元+总价</span>'
    contentTrStr += '<input type="text" name="totalPercentage" value="' + totalPercentage + '" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" style="width: 30%; height:90%;text-align:right" autocomplete="off"/>';
    contentTrStr += '<span>%</span>'
    contentTrStr += '</div></td>';

    contentTrStr += '</tr>';
    $("div[lay-id='content01Reload'] .layui-table-main tbody").append(contentTrStr);
}

function addCol02(fixAmount){
    var contentTrStr = '';
    contentTrStr += '<tr tag="detail02Tr">'

    contentTrStr += '<td data-field="fieldTj" align="center" data-content="">';
    contentTrStr += '<div class="layui-table-cell laytable-cell-2-0-0"><span>每平米</span>'
    contentTrStr += '</div></td>';


    contentTrStr += '<td data-field="fieldYj">';
    contentTrStr += '<div class="layui-table-cell laytable-cell-2-0-1"><span>固定</span>'
    contentTrStr += '<input type="text" name="fixAmount" value="' + fixAmount + '" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" style="width: 80%; height:90%;text-align:right" autocomplete="off"/>';
    contentTrStr += '<span>元</span>';
    contentTrStr += '</div></td>';

    contentTrStr += '</tr>';
    $("div[lay-id='content02Reload'] .layui-table-main tbody").append(contentTrStr);
}


function addCol03(conditionBegin,conditionEnd,fixAmount,totalPercentage,isDel){
    var contentTrStr = '';
    contentTrStr += '<tr tag="detail03Tr">'

    contentTrStr += '<td data-field="fieldTj" align="left" data-content="">';
    contentTrStr += '<div class="layui-table-cell laytable-cell-3-0-0"><span>' + conditionBegin + '<每套总价(万元)<=</span>'
    contentTrStr += '<input type="hidden" name="conditionBegin" value="' + conditionBegin + '"/>';
    contentTrStr += '<input type="text" name="conditionEnd" value="' + conditionEnd + '" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" style="width: 30%; height:90%;text-align:right" autocomplete="off"/>';
    contentTrStr += '</div></td>';

    contentTrStr += '<td data-field="fieldYj">';
    contentTrStr += '<div class="layui-table-cell laytable-cell-3-0-1"><span>固定</span>'
    contentTrStr += '<input type="text" name="fixAmount" value="' + fixAmount + '" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" style="width: 30%; height:90%;text-align:right" autocomplete="off"/>';
    contentTrStr += '<span>元+总价</span>'
    contentTrStr += '<input type="text" name="totalPercentage" value="' + totalPercentage + '" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" style="width: 18%; height:90%;text-align:right" autocomplete="off"/>';
    contentTrStr += '<span>%</span>'
    contentTrStr += '</div></td>';

    contentTrStr += '<td>';
    contentTrStr += '<div class="layui-table-cell laytable-cell-3-0-2">'
    if(isDel==1) {
        contentTrStr += '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="removeTr03(this)">删除</a>';
    }
    contentTrStr += '</div></td>';

    contentTrStr += '</tr>';
    $("div[lay-id='content03Reload'] .layui-table-main tbody").append(contentTrStr);
}
function removeTr03(obj) {
    var currTr = $(obj).parents('tr');
    currTr.remove();
}



function addCol04(conditionBegin,conditionEnd,fixAmount,totalPercentage,isDel){
    var contentTrStr = '';
    contentTrStr += '<tr tag="detail04Tr">'

    contentTrStr += '<td data-field="fieldTj" align="left" data-content="">';
    contentTrStr += '<div class="layui-table-cell laytable-cell-4-0-0"><span>' + conditionBegin + '<每套总面积(㎡)<=</span>'
    contentTrStr += '<input type="hidden" name="conditionBegin" value="' + conditionBegin + '"/>';
    contentTrStr += '<input type="text" name="conditionEnd" value="' + conditionEnd + '" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" style="width: 30%; height:90%;text-align:right" autocomplete="off"/>';
    contentTrStr += '</div></td>';

    contentTrStr += '<td data-field="fieldYj">';
    contentTrStr += '<div class="layui-table-cell laytable-cell-4-0-1"><span>固定</span>'
    contentTrStr += '<input type="text" name="fixAmount" value="' + fixAmount + '" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" style="width: 30%; height:90%;text-align:right" autocomplete="off"/>';
    contentTrStr += '<span>元+总价</span>'
    contentTrStr += '<input type="text" name="totalPercentage" value="' + totalPercentage + '" oninput="this.value=this.value.replace(/[^\\-?\\d.]/g,\'\')" style="width: 18%; height:90%;text-align:right" autocomplete="off"/>';
    contentTrStr += '<span>%</span>'
    contentTrStr += '</div></td>';

    contentTrStr += '<td>';
    contentTrStr += '<div class="layui-table-cell laytable-cell-4-0-2">'
    if(isDel==1) {
        contentTrStr += '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="removeTr04(this)">删除</a>';
    }
    contentTrStr += '</div></td>';

    contentTrStr += '</tr>';
    $("div[lay-id='content04Reload'] .layui-table-main tbody").append(contentTrStr);
}
function removeTr04(obj) {
    var currTr = $(obj).parents('tr');
    currTr.remove();
}

//公司
function addCol(companyNo,companyName,companyShowName){
    var contentTrStr = '';
    contentTrStr += '<tr tag="fydxTr">'

    contentTrStr += '<td data-field="companyNoSelect" align="left" data-content="">';
    contentTrStr += '<div class="layui-table-cell laytable-cell-5-0-0">'
    contentTrStr += '<input type="hidden" name="companyNo" value="' + companyNo + '"/><input type="hidden" name="companyName" value="' + companyName + '"/>';
    contentTrStr += '<span name="companyShowName">' + companyShowName + '</span><a class="layui-btn layui-btn-xs" style="float: right;margin-top: 5px;" onclick="showSelect(this)">选择</a>';
    contentTrStr += '</div></td>';


    contentTrStr += '<td>';
    contentTrStr += '<div class="layui-table-cell laytable-cell-5-0-1">'
    contentTrStr += '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="removeTr(this)">删除</a>';
    contentTrStr += '</div></td>';

    contentTrStr += '</tr>';
    $("div[lay-id='contentCompanyReload'] .layui-table-main tbody").append(contentTrStr);
}

function removeTr(obj) {
    var currTr = $(obj).parents('tr');
    currTr.remove();
}


function showSelect(obj) {
    parent.layer.open({
        type: 2,
        title: '选择公司',
        area: ['800px', '660px'],
        content: '/pmlsEstatePlan/selCompany/'+$("#projectNo").val()
        , scrollbar: false
        , resize: false
        , btn: ['确定', '取消']
        , yes: function (index, layero) {

            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();

            //确认的回调
            if (formData != null) {
                $(obj).html();
                var $trHtml = $(obj).parent();
                $trHtml.find('input[name="companyNo"]').val(formData.companyNo);
                $trHtml.find('input[name="companyName"]').val(formData.companyName);
                $trHtml.find('span[name="companyShowName"]').html(formData.companyName);

                /*        var w_companyId = $("#companyId").attr("data-companyId");

                        $("#companyId").attr("data-branchId", formData.topbranchId);
                        $("#companyId").attr("data-companyId", formData.id);
                        $("#companyId").attr("data-companyNo", formData.companyNo);
                        $("#companyId").val(formData.companyName);*/

                parent.layer.close(index);
            }
        }
    });
}


//获取参数
function getFormData(){
    var lnkYjPlanDto={};
    var yjPlanDetailList = [];
    var yjPlanWyList = [];
    var yjPlanCompanyList = [];

    var projectNo   =   $("#projectNo").val();
    var planType    =   $("input[name='planType']:checked").val();
    var planName    =   $("#planName").val();
    var fyPlanType  =   $("input[name='fyPlanType']:checked").val();
    var yjPlanWyArr    =   formSelects.value('yjWyList', 'val');
    var periodType  =   $("#periodType").val();
    var periodBeginDate  =   $("#periodBeginDate").val();
    var periodEndDate  =   $("#periodEndDate").val();
    var equationType  =   $("input[name='equationType']:checked").val();

    if(planType=='28702' && isNullEmpty(fyPlanType)){
        parent.layer.alert("请选择返佣方案", {icon: 2, title: '提示'});
        return false;
    }

    if(isNullEmpty(planName)){
        parent.layer.alert("请输入方案名称", {icon: 2, title: '提示'});
        return false;
    }

    if(isNullEmpty(yjPlanWyArr) || yjPlanWyArr.length == 0 ){
        parent.layer.alert("请选择物业类型", {icon: 2, title: '提示'});
        return false;
    }

    if(isNullEmpty(periodType)){
        parent.layer.alert("请选择期间", {icon: 2, title: '提示'});
        return false;
    }

    if(periodBeginDate == '') {
        parent.layer.alert("请选择期间开始日期！", {icon: 2, title: '提示'});
        return false;
    }
    if(periodEndDate == '') {
        parent.layer.alert("请选择期间结束日期！", {icon: 2, title: '提示'});
        return false;
    }
    if(periodBeginDate > periodEndDate) {
        parent.layer.alert("开始日期不能大于结束日期!", {icon: 2, title: '提示'});
        return false;
    }


    for(var i=0;i<yjPlanWyArr.length;i++){
        var lnkYjPlanWyDto = {};
        lnkYjPlanWyDto.wyTypeCode = yjPlanWyArr[i];
        yjPlanWyList.push(lnkYjPlanWyDto);
    }

    var checkFlag = true;
    if(equationType=='28101'){
        var tbody = $("div[lay-id='content01Reload'] .layui-table-main tbody");
        var trLast = $(tbody).find("tr[tag='detail01Tr']:last");
        var fixAmount = $(trLast).find("input[name='fixAmount']").val();
        var totalPercentage = $(trLast).find("input[name='totalPercentage']").val();
        if(!checkIsNumber(fixAmount) && !checkIsNumber(totalPercentage)){
            parent.layer.alert("佣金请输入合法的数字", {icon: 2, title: '提示'});
            return false;
        }
/*        if(!checkIsNumber(totalPercentage)){
            parent.layer.alert("佣金请输入合法的数字", {icon: 2, title: '提示'});
            return false;
        }*/

        var lnkYjPlanDetailDto = {};
        lnkYjPlanDetailDto.fixAmount = fixAmount;
        lnkYjPlanDetailDto.totalPercentage = totalPercentage;
        lnkYjPlanDetailDto.equationType = equationType;
        yjPlanDetailList.push(lnkYjPlanDetailDto);
    }else if(equationType=='28102'){
        var tbody = $("div[lay-id='content02Reload'] .layui-table-main tbody");
        var trLast = $(tbody).find("tr[tag='detail02Tr']:last");
        var fixAmount = $(trLast).find("input[name='fixAmount']").val();
        if(!checkIsNumber(fixAmount)){
            parent.layer.alert("佣金请输入合法的数字", {icon: 2, title: '提示'});
            return false;
        }

        var lnkYjPlanDetailDto = {};
        lnkYjPlanDetailDto.fixAmount = fixAmount;
        lnkYjPlanDetailDto.equationType = equationType;
        yjPlanDetailList.push(lnkYjPlanDetailDto);
    }else if(equationType=='28103'){
        var trSize = $("div[lay-id='content03Reload'] .layui-table-main tbody").find("tr[tag='detail03Tr']").length;
        $("div[lay-id='content03Reload'] .layui-table-main tbody").find("tr[tag='detail03Tr']").each(function (i) {
            var conditionBegin = $(this).find("input[name='conditionBegin']").val();
            var conditionEnd = $(this).find("input[name='conditionEnd']").val();
            var fixAmount = $(this).find("input[name='fixAmount']").val();
            var totalPercentage = $(this).find("input[name='totalPercentage']").val();
            if(!checkIsNumber(conditionEnd) && trSize!=(i+1) ){
                parent.layer.alert("第"+(i+1)+"行条件请输入合法的数字", {icon: 2, title: '提示'});
                checkFlag = false;
                return false;
            }
            if(!isNullEmpty(conditionEnd) && trSize==(i+1) ){
                parent.layer.alert("最后一行条件请不要输入任何值", {icon: 2, title: '提示'});
                checkFlag = false;
                return false;
            }

            if(!checkIsNumber(fixAmount)  && !checkIsNumber(totalPercentage)){
                parent.layer.alert("第"+(i+1)+"行佣金请输入合法的数字", {icon: 2, title: '提示'});
                checkFlag = false;
                return false;
            }
  /*          if(!checkIsNumber(totalPercentage)){
                parent.layer.alert("第"+(i+1)+"行佣金请输入合法的数字", {icon: 2, title: '提示'});
                checkFlag = false;
                return false;
            }*/
            if(parseFloat(conditionEnd)<=parseFloat(conditionBegin)){
                parent.layer.alert("第"+(i+1)+"行结束值必须大于开始值", {icon: 2, title: '提示'});
                checkFlag = false;
                return false;
            }

            var lnkYjPlanDetailDto = {};
            lnkYjPlanDetailDto.conditionBegin = conditionBegin;
            lnkYjPlanDetailDto.conditionEnd = conditionEnd;
            lnkYjPlanDetailDto.fixAmount = fixAmount;
            lnkYjPlanDetailDto.totalPercentage = totalPercentage;
            lnkYjPlanDetailDto.equationType = equationType;
            yjPlanDetailList.push(lnkYjPlanDetailDto);
        });

        if(!checkFlag){
            return false;
        }

        for(var i=0;i<yjPlanDetailList.length;i++){
            var conditionBeginPre = yjPlanDetailList[i].conditionBegin;
            var conditionEndPre = yjPlanDetailList[i].conditionEnd;
            for (var j=0;j<yjPlanDetailList.length;j++) {
                var conditionBeginNext = yjPlanDetailList[j].conditionBegin;
                var conditionEndNext = yjPlanDetailList[j].conditionEnd;
                if((i+1)==(j) && parseFloat(conditionBeginNext) != parseFloat(conditionEndPre) ){
                    parent.layer.alert("第"+(i+1)+"行条件区间不合法", {icon: 2, title: '提示'});
                    checkFlag = false;
                    break;
                }
            }
        }

    }else if(equationType=='28104'){
        var trSize = $("div[lay-id='content04Reload'] .layui-table-main tbody").find("tr[tag='detail04Tr']").length;
        $("div[lay-id='content04Reload'] .layui-table-main tbody").find("tr[tag='detail04Tr']").each(function (i) {
            var conditionBegin = $(this).find("input[name='conditionBegin']").val();
            var conditionEnd = $(this).find("input[name='conditionEnd']").val();
            var fixAmount = $(this).find("input[name='fixAmount']").val();
            var totalPercentage = $(this).find("input[name='totalPercentage']").val();
            if(!checkIsNumber(conditionEnd) && trSize!=(i+1) ){
                parent.layer.alert("第"+(i+1)+"行条件请输入合法的数字", {icon: 2, title: '提示'});
                checkFlag = false;
                return false;
            }
            if(!isNullEmpty(conditionEnd) && trSize==(i+1) ){
                parent.layer.alert("最后一行条件请不要输入任何值", {icon: 2, title: '提示'});
                checkFlag = false;
                return false;
            }

            if(!checkIsNumber(fixAmount)  && !checkIsNumber(totalPercentage)){
                parent.layer.alert("第"+(i+1)+"行佣金请输入合法的数字", {icon: 2, title: '提示'});
                checkFlag = false;
                return false;
            }
/*            if(!checkIsNumber(totalPercentage)){
                parent.layer.alert("第"+(i+1)+"行佣金请输入合法的数字", {icon: 2, title: '提示'});
                checkFlag = false;
                return false;
            }*/
            if(parseFloat(conditionEnd)<=parseFloat(conditionBegin)){
                parent.layer.alert("第"+(i+1)+"行结束值必须大于开始值", {icon: 2, title: '提示'});
                checkFlag = false;
                return false;
            }

            var lnkYjPlanDetailDto = {};
            lnkYjPlanDetailDto.conditionBegin = conditionBegin;
            lnkYjPlanDetailDto.conditionEnd = conditionEnd;
            lnkYjPlanDetailDto.fixAmount = fixAmount;
            lnkYjPlanDetailDto.totalPercentage = totalPercentage;
            lnkYjPlanDetailDto.equationType = equationType;
            yjPlanDetailList.push(lnkYjPlanDetailDto);
        });

        if(!checkFlag){
            return false;
        }

        for(var i=0;i<yjPlanDetailList.length;i++){
            var conditionBeginPre = yjPlanDetailList[i].conditionBegin;
            var conditionEndPre = yjPlanDetailList[i].conditionEnd;
            for (var j=0;j<yjPlanDetailList.length;j++) {
                var conditionBeginNext = yjPlanDetailList[j].conditionBegin;
                var conditionEndNext = yjPlanDetailList[j].conditionEnd;
                if((i+1)==(j) && parseFloat(conditionBeginNext) != parseFloat(conditionEndPre) ){
                    parent.layer.alert("第"+(i+1)+"行条件区间不合法", {icon: 2, title: '提示'});
                    checkFlag = false;
                    break;
                }
            }
        }
    }

    if(isNullEmpty(yjPlanDetailList) || yjPlanDetailList.length == 0){
        parent.layer.alert('佣金最少需要一条', {icon: 2, title: '提示'});
        return false;
    }

    if(!checkFlag){
        return false;
    }


    if(planType=='28702' && fyPlanType=='28802'){
        var fydxAry = [];
        $("div[lay-id='contentCompanyReload'] .layui-table-main tbody").find("tr[tag='fydxTr']").each(function (i) {
            var companyNo = $(this).find("input[name='companyNo']").val();
            if(isNullEmpty(companyNo)){
                parent.layer.alert('第'+(i+1)+"行渠道公司为空 请选择", {icon: 2, title: '提示'});
                checkFlag = false;
                return false;
            }
            var lnkYjPlanCompanyDto = {};
            lnkYjPlanCompanyDto.companyNo = companyNo;
            yjPlanCompanyList.push(lnkYjPlanCompanyDto);
            fydxAry.push(companyNo);
        });
        var repeatCompanyName = isRepeatAry(fydxAry);
        if(!isNullEmpty(repeatCompanyName)){
            parent.layer.alert('渠道公司'+repeatCompanyName+"存在重复，请重新选择", {icon: 2, title: '提示'});
            return false;
        }

        if(!checkFlag){
            return false;
        }

        if(isNullEmpty(yjPlanCompanyList) || yjPlanCompanyList.length == 0){
            parent.layer.alert('渠道公司最少需要一条', {icon: 2, title: '提示'});
            return false;
        }
    }




    if(!checkFlag){
        return false;
    }

    lnkYjPlanDto.id                     =   planId;
    lnkYjPlanDto.projectNo   			= 	projectNo;
    lnkYjPlanDto.planType    		    = 	planType;
    lnkYjPlanDto.planName    		    = 	planName;
    lnkYjPlanDto.periodType  		    = 	periodType;
    lnkYjPlanDto.periodBeginDate  	    = 	periodBeginDate;
    lnkYjPlanDto.periodEndDate  		= 	periodEndDate;
    lnkYjPlanDto.equationType  		    = 	equationType;
    lnkYjPlanDto.yjPlanWyList           =   yjPlanWyList;
    lnkYjPlanDto.yjPlanDetailList       =   yjPlanDetailList;
    if(planType=='28702'){
        lnkYjPlanDto.fyPlanType  		    = 	fyPlanType;
        lnkYjPlanDto.yjPlanCompanyList      =   yjPlanCompanyList;
    }
    return lnkYjPlanDto;
}



function isNullEmpty(obj){
    if(obj == null || obj == "" || obj == undefined){
        return true;
    }else{
        return false;
    }
}