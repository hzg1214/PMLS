var all;
var register;
var watch;
var qk;
var commission;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        formSelects = layui.formSelects,
        $ = layui.$;
    layer = layui.layer;

    laydate.render({
        elem: '#dyDateStart',
        type: 'date',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        done: function (value, date, endDate) {
            var startDate = new Date(value).getTime();
            if($('#dyDateEnd').val()!=''){
                var endTime = new Date($('#dyDateEnd').val()).getTime();
                if (endTime < startDate) {
                    layer.msg('开始时间不能大于结束时间');
                    $('#dyDateStart').val('');
                }
            }
        }
    });

    laydate.render({
        elem: '#dyDateEnd',
        type: 'date',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        done: function (value, date, endDate) {
            if($('#dyDateStart').val()!=""){
                var startDate = new Date($('#dyDateStart').val()).getTime();
                var endTime = new Date(value).getTime();
                if (endTime < startDate) {
                    layer.msg('结束时间不能小于开始时间');
                    $('#dyDateEnd').val('');
                }
            }
        }
    });

    laydate.render({
        elem: '#cooperationDtStart',
        type: 'date',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        done: function (value, date, endDate) {
            var startDate = new Date(value).getTime();
            if($('#cooperationDtEnd').val()!=''){
                var endTime = new Date($('#cooperationDtEnd').val()).getTime();
                if (endTime < startDate) {
                    layer.msg('开始时间不能大于结束时间');
                    $('#cooperationDtStart').val('');
                }
            }
        }
    });

    laydate.render({
        elem: '#cooperationDtEnd',
        type: 'date',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        done: function (value, date, endDate) {
            if($('#cooperationDtStart').val()!=""){
                var startDate = new Date($('#cooperationDtStart').val()).getTime();
                var endTime = new Date(value).getTime();
                if (endTime < startDate) {
                    layer.msg('结束时间不能小于开始时间');
                    $('#cooperationDtEnd').val('');
                }
            }
        }
    });

    laydate.render({
        elem: '#recordDate',
        type: 'date',
        format: 'yyyy-MM-dd',
        trigger: 'click'
    });

    laydate.render({
        elem: '#lastpayDate',
        type: 'date',
        format: 'yyyy-MM-dd',
        trigger: 'click'
    });

    laydate.render({
        elem: '#recordDateTwo',
        type: 'date',
        format: 'yyyy-MM-dd',
        trigger: 'click'
    });


    // 画面初始化
    initialization();

    function initialization() {

        form.render('select');   // 刷新单选框渲染


        // 项目
        $('#allTabItem .toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            all.active[type] ? all.active[type].call(this) : '';
        });

        // 合同
        $('#registerTabItem .toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            register.active[type] ? register.active[type].call(this) : '';
        });

        // 借佣
        $('#watchTabItem .toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            watch.active[type] ? watch.active[type].call(this) : '';
        });
        
        // 请款
        $('#qkTabItem .toolbar .layui-btn').on('click', function () {
            var type = $(this).data('type');
            qk.active[type] ? qk.active[type].call(this) : '';
        });


    }



});

/**
 * 更新
 */
function oaFrameContract(kbn) {
    var url=BASE_PATH + '/jszylnk/oaFrameContract';
//    var url=BASE_PATH + '/test/oaFrameContract';
    var contractNo =$('#contractNo').val();
    if(contractNo == ''){
        $("#oaFrameContractError").empty().html("请输入合同编号!");
        return false;
    }
    var params={
        contractNo:$('#contractNo').val(),
        oaType:kbn,
    };
    $.ajax({
        url:url,
        data:params,
        type: 'POST',
        dataType:'json',
        success: function(data){
            if(data.returnCode == '200'){
                $("#oaFrameContractError").empty().html("执行成功!");
            }else if (data.returnCode == '-1'){
                $("#oaFrameContractError").empty().html("执行失败!");
            }
        },
        error:function(){
        }
    });
};

function oaProject(){
    var url=BASE_PATH + '/jszylnk/oaProject';
//    var url=BASE_PATH + '/test/oaProject';
    var oaId =$('#oaId').val();
    if(oaId == ''){
        $("#oaProjectError").empty().html("请输入立项单编号!");
        return false;
    }
    var projectNo =$('#projectNo').val();
    if(projectNo == ''){
        $("#oaProjectError").empty().html("请输入项目编号!");
        return false;
    }
    var hsCode =$('#hsCode').val();
    if(hsCode == ''){
        $("#oaProjectError").empty().html("请输入核算编码!");
        return false;
    }
    var hsName =$('#hsName').val();
    if(hsName == ''){
        $("#oaProjectError").empty().html("请输入核算主体!");
        return false;
    }
    var khCode =$('#khCode').val();
    if(khCode == ''){
        $("#oaProjectError").empty().html("请输入考核主体编码!");
        return false;
    }
    var khName =$('#khName').val();
    if(khName == ''){
        $("#oaProjectError").empty().html("请输入考核主体!");
        return false;
    }
    var yjDyAmount =$('#yjDyAmount').val();
    if(yjDyAmount == ''){
        $("#oaProjectError").empty().html("请输入预计垫佣金额!");
        return false;
    }
    var dykbn =$('#dykbn').val();
    if(dykbn == ''){
        $("#oaProjectError").empty().html("请输入是否垫佣!");
        return false;
    }
    var subscribedThisYear =$('#subscribedThisYear').val();
    if(subscribedThisYear == ''){
        $("#oaProjectError").empty().html("请输入预计当年大定金额!");
        return false;
    }
    var subscribedNextYear =$('#subscribedNextYear').val();
    if(subscribedNextYear == ''){
        $("#oaProjectError").empty().html("请输入预计明年大定金额!");
        return false;
    }
    var dyDateStart =$('#dyDateStart').val();
    if(dyDateStart == ''){
        $("#oaProjectError").empty().html("请输入垫佣开始时间!");
        return false;
    }
    var dyDateEnd =$('#dyDateEnd').val();
    if(dyDateEnd == ''){
        $("#oaProjectError").empty().html("请输入垫佣结束时间!");
        return false;
    }
    var dyAmount =$('#dyAmount').val();
    if(dyAmount == ''){
        $("#oaProjectError").empty().html("请输入垫佣金额!");
        return false;
    }
    var params={
        oaId:oaId,
        projectNo:projectNo,
        hsCode:hsCode,
        hsName:hsName,
        khCode:khCode,
        khName:khName,
        yjDyAmount:yjDyAmount,
        dykbn:dykbn,
        subscribedThisYear:subscribedThisYear,
        subscribedNextYear:subscribedNextYear,
        dyDateStart:dyDateStart,
        dyDateEnd:dyDateEnd,
        dyAmount:dyAmount,
    };
    $.ajax({
        url:url,
        data:params,
        type: 'POST',
        dataType:'json',
        success: function(data){
            if(data.returnCode == '200'){
                $("#oaProjectError").empty().html("执行成功!");
            }else if (data.returnCode == '-1'){
                $("#oaProjectError").empty().html("执行失败!");
            }
        },
        error:function(){
        }
    });
};

function oaSignOrApproach(){
    var url=BASE_PATH + '/jszylnk/oaSignOrApproach';
//    var url=BASE_PATH + '/test/oaSignOrApproach';

    var projectType = $("input[name='projectType']:checked").val();
    if(projectType == ''||projectType == undefined){
        $("#oaSignOrApproachError").empty().html("请选择类型!");
        return false;
    }
    var oaId =$('#oaIdTwo').val();
    if(oaId == ''){
        $("#oaSignOrApproachError").empty().html("请输入立项单编号!");
        return false;
    }
    var projectNo =$('#projectNoTwo').val();
    if(projectNo == ''){
        $("#oaSignOrApproachError").empty().html("请输入项目编号!");
        return false;
    }
    var rtnCommissionMemo =$('#rtnCommissionMemo').val();
    if(rtnCommissionMemo == ''){
        $("#oaSignOrApproachError").empty().html("请输入返佣结算条件!");
        return false;
    }
    var rtnCommission =$('#rtnCommission').val();
    if(rtnCommission == ''){
        $("#oaSignOrApproachError").empty().html("请输入返佣标准!");
        return false;
    }
    var commissionMemo =$('#commissionMemo').val();
    if(commissionMemo == ''){
        $("#oaSignOrApproachError").empty().html("请输入收入结算描述!");
        return false;
    }
    var commissionConditionVal =$('#commissionConditionVal').val();
    if(commissionConditionVal == ''){
        $("#oaSignOrApproachError").empty().html("请输入收入结算条件!");
        return false;
    }
    var incomeSubject =$('#incomeSubject').val();
    if(incomeSubject == ''){
        $("#oaSignOrApproachError").empty().html("请输入收入标的!");
        return false;
    }
    var cooperationDtStart =$('#cooperationDtStart').val();
    if(cooperationDtStart == ''){
        $("#oaSignOrApproachError").empty().html("请输入合作期开始!");
        return false;
    }
    var cooperationDtEnd =$('#cooperationDtEnd').val();
    if(cooperationDtEnd == ''){
        $("#oaSignOrApproachError").empty().html("请输入合作期结束!");
        return false;
    }
    if(projectType=='1'){
        var signKbn =$("input[name='signKbn']:checked").val();
        if(signKbn == ''||signKbn == undefined){
            $("#oaSignOrApproachError").empty().html("请选择新签续签!");
            return false;
        }
    }
    var hsCode =$('#hsCodeTwo').val();
    if(hsCode == ''){
        $("#oaSignOrApproachError").empty().html("请输入核算编码!");
        return false;
    }
    var hsName =$('#hsNameTwo').val();
    if(hsName == ''){
        $("#oaSignOrApproachError").empty().html("请输入核算主体!");
        return false;
    }
    if(projectType=='1'){
        var khCode =$('#khCodeTwo').val();
        if(khCode == ''){
            $("#oaSignOrApproachError").empty().html("请输入考核主体编码!");
            return false;
        }
        var khName =$('#khNameTwo').val();
        if(khName == ''){
            $("#oaSignOrApproachError").empty().html("请输入考核主体!");
            return false;
        }
    }
    var wsqyzt =$('#wsqyzt').val();
    if(wsqyzt == ''){
        $("#oaSignOrApproachError").empty().html("请输入我司签约主体!");
        return false;
    }
    var developersCode =$('#developersCode').val();
    if(developersCode == ''){
        $("#oaSignOrApproachError").empty().html("请输入客户Code!");
        return false;
    }
    var developersName =$('#developersName').val();
    if(developersName == ''){
        $("#oaSignOrApproachError").empty().html("请输入客户名!");
        return false;
    }

    var params={
        oaId:oaId,
        projectNo:projectNo,
        projectType:projectType,
        rtnCommissionMemo:rtnCommissionMemo,
        rtnCommission:rtnCommission,
        commissionMemo:commissionMemo,
        commissionConditionVal:commissionConditionVal,
        incomeSubject:incomeSubject,
        cooperationDtStart:cooperationDtStart,
        cooperationDtEnd:cooperationDtEnd,
        signKbn:signKbn,
        hsCode:hsCode,
        hsName:hsName,
        khCode:khCode,
        khName:khName,
        wsqyzt:wsqyzt,
        developersCode:developersCode,
        developersName:developersName,
    };
    $.ajax({
        url:url,
        data:params,
        type: 'POST',
        dataType:'json',
        success: function(data){
            if(data.returnCode == '200'){
                $("#oaSignOrApproachError").empty().html("执行成功!");
            }else if (data.returnCode == '-1'){
                $("#oaSignOrApproachError").empty().html("执行失败!");
            }
        },
        error:function(){
        }
    });
};

function oaDistribution(kbn){
    var url=BASE_PATH + '/jszylnk/oaDistribution';
//    var url=BASE_PATH + '/test/oaDistribution';

    var oaNo =$('#oaNo').val();
    if(oaNo == ''){
        $("#oaDistributionError").empty().html("请输入oa编号!");
        return false;
    }
    var htType =$('#htType').val();
    if(htType == ''){
        $("#oaDistributionError").empty().html("请输入合同类型!");
        return false;
    }
    var htCategory =$('#htCategory').val();
    if(htCategory == ''){
        $("#oaDistributionError").empty().html("请输入合同类别!");
        return false;
    }
    var riskWarning =$('#riskWarning').val();
    if(riskWarning == ''){
        $("#oaDistributionError").empty().html("请输入风险预警!");
        return false;
    }
    var khCode =$('#khCodeThere').val();
    if(khCode == ''){
        $("#oaDistributionError").empty().html("请输入考核Code!");
        return false;
    }
    var khName =$('#khNameThere').val();
    if(khName == ''){
        $("#oaDistributionError").empty().html("请输入考核名称!");
        return false;
    }

    var params={
        oaNo:oaNo,
        htType:htType,
        htCategory:htCategory,
        riskWarning:riskWarning,
        khCode:khCode,
        khName:khName,
        oaType:kbn,
    };
    $.ajax({
        url:url,
        data:params,
        type: 'POST',
        dataType:'json',
        success: function(data){
            if(data.returnCode == '200'){
                $("#oaDistributionError").empty().html("执行成功!");
            }else if (data.returnCode == '-1'){
                $("#oaDistributionError").empty().html("执行失败!");
            }
        },
        error:function(){
        }
    });
};

function oaStatement(kbn) {
    var url=BASE_PATH + '/jszylnk/oaStatement';
//    var url=BASE_PATH + '/test/oaStatement';
    var jyNo =$('#jyNo').val();
    if(jyNo == ''){
        $("#oaStatementError").empty().html("请输入结算书编号!");
        return false;
    }
    var params={
        jyNo:jyNo,
        oaType:kbn,
    };
    $.ajax({
        url:url,
        data:params,
        type: 'POST',
        dataType:'json',
        success: function(data){
            if(data.returnCode == '200'){
                $("#oaStatementError").empty().html("执行成功!");
            }else if (data.returnCode == '-1'){
                $("#oaStatementError").empty().html("执行失败!");
            }
        },
        error:function(){
        }
    });
};

function oaAgreement(kbn) {
    var url=BASE_PATH + '/jszylnk/oaAgreement';
//    var url=BASE_PATH + '/test/oaAgreement';
    var jyNo =$('#jyNoTwo').val();
    if(jyNo == ''){
        $("#oaAgreementError").empty().html("请输入借佣编号!");
        return false;
    }
    var params={
        jyNo:jyNo,
        oaType:kbn,
    };
    $.ajax({
        url:url,
        data:params,
        type: 'POST',
        dataType:'json',
        success: function(data){
            if(data.returnCode == '200'){
                $("#oaAgreementError").empty().html("执行成功!");
            }else if (data.returnCode == '-1'){
                $("#oaAgreementError").empty().html("执行失败!");
            }
        },
        error:function(){
        }
    });
};

function oaReceivables(kbn) {
    var url=BASE_PATH + '/jszylnk/oaReceivables';
//    var url=BASE_PATH + '/test/oaReceivables';
    var jyNo =$('#jyNoThere').val();
    if(jyNo == ''){
        $("#oaReceivablesError").empty().html("请输入借佣编号!");
        return false;
    }
    var receiveBankName =$('#receiveBankName').val();
    if(receiveBankName == ''){
        $("#oaReceivablesError").empty().html("请输入收款银行!");
        return false;
    }
    var receiveBankAccountCardCode =$('#receiveBankAccountCardCode').val();
    if(receiveBankAccountCardCode == ''){
        $("#oaReceivablesError").empty().html("请输入卡号!");
        return false;
    }
    var receiveBankAccountName =$('#receiveBankAccountName').val();
    if(receiveBankAccountName == ''){
        $("#oaReceivablesError").empty().html("请输入收款人户名!");
        return false;
    }
    var receiveBankProvinceName =$('#receiveBankProvinceName').val();
    if(receiveBankProvinceName == ''){
        $("#oaReceivablesError").empty().html("请输入省（银行）!");
        return false;
    }
    var receiveBankCityName =$('#receiveBankCityName').val();
    if(receiveBankCityName == ''){
        $("#oaReceivablesError").empty().html("请输入市（银行）!");
        return false;
    }
    var receiveBankSerialNo =$('#receiveBankSerialNo').val();
    if(receiveBankSerialNo == ''){
        $("#oaReceivablesError").empty().html("请输入银行序号!");
        return false;
    }
    var recordDate =$('#recordDate').val();
    if(recordDate == ''){
        $("#oaReceivablesError").empty().html("请输入入账日期!");
        return false;
    }
    var lastpayDate =$('#lastpayDate').val();
    if(lastpayDate == ''){
        $("#oaReceivablesError").empty().html("请输入最后付款日期!");
        return false;
    }
    var params={
        jyNo:jyNo,
        receiveBankName:receiveBankName,
        receiveBankAccountCardCode:receiveBankAccountCardCode,
        receiveBankAccountName:receiveBankAccountName,
        receiveBankProvinceName:receiveBankProvinceName,
        receiveBankCityName:receiveBankCityName,
        receiveBankSerialNo:receiveBankSerialNo,
        recordDate:recordDate,
        lastpayDate:lastpayDate,
        oaType:kbn,
    };
    $.ajax({
        url:url,
        data:params,
        type: 'POST',
        dataType:'json',
        success: function(data){
            if(data.returnCode == '200'){
                $("#oaReceivablesError").empty().html("执行成功!");
            }else if (data.returnCode == '-1'){
                $("#oaReceivablesError").empty().html("执行失败!");
            }
        },
        error:function(){
        }
    });
};

function oaExpenditure(kbn) {
    var url=BASE_PATH + '/jszylnk/oaExpenditure';
//    var url=BASE_PATH + '/test/oaExpenditure';
    var jyNo =$('#jyNoFour').val();
    if(jyNo == ''){
        $("#oaExpenditureError").empty().html("请输入借佣编号!");
        return false;
    }
    var recordDate =$('#recordDateTwo').val();
    if(recordDate == ''){
        $("#oaExpenditureError").empty().html("请输入入账日期!");
        return false;
    }
    var params={
        jyNo:jyNo,
        recordDate:recordDate,
        oaType:kbn,
    };
    $.ajax({
        url:url,
        data:params,
        type: 'POST',
        dataType:'json',
        success: function(data){
            if(data.returnCode == '200'){
                $("#oaExpenditureError").empty().html("执行成功!");
            }else if (data.returnCode == '-1'){
                $("#oaExpenditureError").empty().html("执行失败!");
            }
        },
        error:function(){
        }
    });
};

function oaQk(kbn) {
    var url=BASE_PATH + '/jszylnk/oaQk';
//    var url=BASE_PATH + '/test/oaQk';
    var oaNo =$('#oaNoTwo').val();
    if(oaNo == ''){
        $("#oaQkError").empty().html("请输入oa编号!");
        return false;
    }
    var params={
        oaNo:oaNo,
        oaType:kbn,
    };
    $.ajax({
        url:url,
        data:params,
        type: 'POST',
        dataType:'json',
        success: function(data){
            if(data.returnCode == '200'){
                $("#oaQkError").empty().html("执行成功!");
            }else if (data.returnCode == '-1'){
                $("#oaQkError").empty().html("执行失败!");
            }else if(data.returnCode == '-10'){
            	$("#oaQkError").empty().html(data.returnMsg);
            }
        },
        error:function(){
        }
    });
};