
//选中的开发商对象
var selectDeveloperObj={
    id:estate.developerDto.id,
    developerName:estate.developerDto.developerName,
    partnerAbbreviationNm:estate.developerDto.partnerAbbreviationNm,
    developerCode:estate.developerDto.developerCode,
    developerBrandName:estate.developerDto.developerBrandName,
    developerBrandId:estate.developerDto.developerBrandId,
    isYjDy:estate.developerDto.isYjDy,
    dockingPeo:estate.developerDto.dockingPeo,
    dockingPeoTel:estate.developerDto.dockingPeoTel
    ,mattressNailId:estate.developerDto.mattressNailId
    ,mattressNailName:estate.developerDto.mattressNailName
};

console.log(estate);

var selectPartnerInfoObj;


layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        laydate = layui.laydate,
        form = layui.form,
        table = layui.table,
        layer = layui.layer,
        $ = layui.$;

    var opType = $("#opType").val();
    if(opType=='editPartner'){
        scrollToElement("#partnerCardBody",300);
    }
    //楼盘地址城市
    initRealityCityNoList();
    initDistrictNoList(estate.realityCityNo);
    //初始化垫佣甲方简称
    //initMattressNail();
    //
    changeDeveloper();

    form.on('radio(salesStatus)', function(data){
        if(data.value=='1442' || data.value==1442 ){
            $("#monthRoughAmount").val(0.00);
        }else {
            $("#monthRoughAmount").val('');
        }
        form.render();
    });

    var partner = $("input[name='partner']:checked").val();
    if(partner == '12801'){
        $("#selectPartnerNm").hide();//隐藏选择按钮
    }else {
        $("#selectPartnerNm").show();//选择按钮
    }
    form.render();


    form.on('radio(partner)', function(data){
        //changeDeveloper();
        clearPartnerInfo();
    });

    form.on('select(realityCityNo)', function(data){
        initDistrictNoList(data.value);
    });

    form.on('radio(custodyFlg)', function(data){
        if(data.value==1){
            //是可垫佣甲方
            $(".selectMattressNailInfo").removeClass("hidden");
        }else{
            //不是可垫佣甲方
            $(".selectMattressNailInfo").addClass("hidden");
        }
    });

    laydate.render({
        elem: '#cooperationDtStart',
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
            yjDdMoneyRefresh(value,$('#cooperationDtEnd').val());
        }
    });



    laydate.render({
        elem: '#cooperationDtEnd',
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
            yjDdMoneyRefresh($('#cooperationDtStart').val(),value);
        }
    });

    if(estate!=null && estate!=''){
        yjDdMoneyRefresh(estate.cooperationDtStart,estate.cooperationDtEnd);
        $("#subscribedQuarter1").val(estate.subscribedQuarter1);
        $("#subscribedQuarter2").val(estate.subscribedQuarter2);
        $("#subscribedQuarter3").val(estate.subscribedQuarter3);
        $("#subscribedQuarter4").val(estate.subscribedQuarter4);
        $("#subscribedNextYear").val(estate.subscribedNextYear);
    }
    function yjDdMoneyRefresh(startDate,endDate){
        $("#subscribedQuarter1").val('');
        $("#subscribedQuarter2").val('');
        $("#subscribedQuarter3").val('');
        $("#subscribedQuarter4").val('');
        $("#subscribedNextYear").val('');

        var startMonthStr=new Date(startDate).getMonth();
        var endMonthStr=new Date(endDate).getMonth();

        if(new Date(startDate).getFullYear()==new Date(endDate).getFullYear()){//年份相同，明年大定金额为0，不可操作
            $("#subscribedNextYear").prop('disabled','disabled');
            $("#subscribedNextYear").val(0);
        }else{
            $("#subscribedNextYear").removeProp('disabled');
        }
        //开始月份
        if(startMonthStr>=0 && startMonthStr<3){//一季度
            $("#subscribedQuarter1").removeProp('disabled');
            $("#subscribedQuarter2").removeProp('disabled');
            $("#subscribedQuarter3").removeProp('disabled');
            $("#subscribedQuarter4").removeProp('disabled');
        }else if(startMonthStr>=3 && startMonthStr<6){//二季度
            $("#subscribedQuarter1").prop('disabled','disabled');
            $("#subscribedQuarter2").removeProp('disabled');
            $("#subscribedQuarter3").removeProp('disabled');
            $("#subscribedQuarter4").removeProp('disabled');

            $("#subscribedQuarter1").val(0);
        }else if(startMonthStr>=6 && startMonthStr<9){//三季度
            $("#subscribedQuarter1").prop('disabled','disabled');
            $("#subscribedQuarter2").prop('disabled','disabled');
            $("#subscribedQuarter3").removeProp('disabled');
            $("#subscribedQuarter4").removeProp('disabled');

            $("#subscribedQuarter1").val(0);
            $("#subscribedQuarter2").val(0);
        }else if(startMonthStr>=9 && startMonthStr<12){//四季度
            $("#subscribedQuarter1").prop('disabled','disabled');
            $("#subscribedQuarter2").prop('disabled','disabled');
            $("#subscribedQuarter3").prop('disabled','disabled');
            $("#subscribedQuarter4").removeProp('disabled');

            $("#subscribedQuarter1").val(0);
            $("#subscribedQuarter2").val(0);
            $("#subscribedQuarter3").val(0);
        }
        if(new Date(startDate).getFullYear()==new Date(endDate).getFullYear()){
            //结束月份
            if(endMonthStr>=0 && endMonthStr<3){
                $("#subscribedQuarter2").prop('disabled','disabled');
                $("#subscribedQuarter3").prop('disabled','disabled');
                $("#subscribedQuarter4").prop('disabled','disabled');
                $("#subscribedQuarter2").val(0);
                $("#subscribedQuarter3").val(0);
                $("#subscribedQuarter4").val(0);
            }else if(endMonthStr>=3 && endMonthStr<6){
                $("#subscribedQuarter3").prop('disabled','disabled');
                $("#subscribedQuarter4").prop('disabled','disabled');
                $("#subscribedQuarter3").val(0);
                $("#subscribedQuarter4").val(0);
            }else if(endMonthStr>=6 && endMonthStr<9){
                $("#subscribedQuarter4").prop('disabled','disabled');
                $("#subscribedQuarter4").val(0);
            }else if(endMonthStr>=9 && endMonthStr<12){
                //不做处理
            }
        }


    }


    var active = {
        //选择开发商
        selectDeveloper:function(){
            parent.layer.open({
                type: 2,
                title: '选择开发商',
                area: ['800px', '600px'],
                content:'/pmlsEstate/selectDeveloper?partner=12801&pageType=estate'
                ,scrollbar: false
                ,resize:false
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    console.log(formData);
                    if(formData!=null) {
                        $("#devCompanyId").val(formData.id);
                        $("#bigCustomerFlag").val(formData.bigCustomerFlag);
                        $("#devCompanyText").val(formData.developerName);
                        $("#devCompanyBroker").val(formData.dockingPeo);
                        $("#devCompanyBrokerTel").val(formData.dockingPeoTel);

                        $("#devCompany").val(formData.partnerAbbreviationNm);

                        var bigCustomerFlagStr = "";
                        if(formData.bigCustomerFlag=='22601'){
                            bigCustomerFlagStr = "是";
                        }
                        if(formData.bigCustomerFlag=='22602'){
                            bigCustomerFlagStr = "否";
                        }
                        $("#bigCustomerFlagStr").html(bigCustomerFlagStr);
                        $("#bigCustomerFlagDiv").show();

                        selectDeveloperObj = formData;
                        changeDeveloper();
                        //关闭弹出层
                        parent.layer.close(index);
                    }
                    //end
                }
            });
        },
        selectPartnerNm:function(){
            var partner = $("input[name='partner']:checked").val();
            if(!partner){
                parent.layer.alert('请先选择合作方类型！',{icon: 2, title:'提示'});
                return;
            }
            parent.layer.open({
                type: 2,
                title: '选择合作方',
                area: ['800px', '600px'],
                content: '/pmlsEstate/selectDeveloper?partner='+partner +'&pageType=developer'
                ,scrollbar: false
                ,resize:false
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    console.log(formData);
                    if(formData!=null) {
                        /*$("#devCompanyId").val(formData.id);
                        $("#bigCustomerFlag").val(formData.bigCustomerFlag);
                        $("#devCompanyText").val(formData.developerName);
                        $("#devCompanyBroker").val(formData.dockingPeo);
                        $("#devCompanyBrokerTel").val(formData.dockingPeoTel);

                        $("#devCompany").val(formData.partnerAbbreviationNm);




                        var bigCustomerFlagStr = "";
                        if(formData.bigCustomerFlag=='22601'){
                            bigCustomerFlagStr = "是";
                        }
                        if(formData.bigCustomerFlag=='22602'){
                            bigCustomerFlagStr = "否";
                        }
                        $("#bigCustomerFlagStr").html(bigCustomerFlagStr);*/

                        selectPartnerInfoObj = formData;
                        changeDeveloper();
                        //关闭弹出层
                        parent.layer.close(index);
                    }
                    //end
                }
            });
        },

      //项目负责人
        projectLeaderSel:function(){
        	parent.layer.open({
        		type: 2,
        		title: '选择项目负责人',
        		area: ['800px', '600px'],
        		content: '/pmlsEstate/projectLeaderSel'
        			,scrollbar: false
        			,resize:false
        			,btn: ['确定', '取消']
        	,yes: function(index, layero){
        		//确认的回调
        		var iframeWin = parent.window[layero.find('iframe')[0]['name']];
        		var formData = iframeWin.getFormData();
        		console.log(formData);
        		if(formData!=null) {
        			$("#projectLeaderEmpId").val(formData.userId);
        			$("#projectLeaderName").val(trimStr(formData.userName));
        			$("#projectLeaderTel").val(trimStr(formData.cellphone));
        			//关闭弹出层
        			parent.layer.close(index);
        		}
        		//end
        	}
        	});
        },
        //开发负责人
        projectLeaderChoose:function(){
            parent.layer.open({
                type: 2,
                title: '选择开发负责人',
                area: ['800px', '600px'],
                content: '/pmlsEstate/projectLeaderChoose'
                ,scrollbar: false
                ,resize:false
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    console.log(formData);
                    if(formData!=null) {
                        $("#sceneEmpId").val(formData.userId);
                        $("#sceneEmpName").val(trimStr(formData.userName));
                        $("#empTel").val(trimStr(formData.cellphone));
                        //关闭弹出层
                        parent.layer.close(index);
                    }
                    //end
                }
            });
        },
        submitEstate:function () {
            var id = $("#id").val();
            var formData={
                id:id
                ,estId:id
                ,auditStatus:"12901"
                ,projectStatus:"20305"
                ,cooperationMode:"-1"
            };
            var amountReg = /^(([1-9]\d*)|\d)(\.\d{1,2})?$/;
            var telReg = /^1[3456789]\d{9}$/;
            //
            var estateNm = trimStr($("#estateNm").val());
            if(!estateNm){
                parent.layer.alert('请输入楼盘名称！',{icon: 2, title:'提示'});
                return;
            }
            if(isIncludeBlank(estateNm)){
                parent.layer.alert('楼盘名称含有非法字符！',{icon: 2, title:'提示'});
                return;
            }
            var estatePosition = $("input[name='estatePosition']:checked").val();
            var realityCityNo = $("#realityCityNo").val();
            var districtNo = $("#districtNo").val();
            var countryNo = $("#countryNo").val();
            var address = trimStr($("#address").val());
            if(estatePosition==0){
                countryNo = "CTY_000001";//中国
                if(!realityCityNo){
                    parent.layer.alert('请选择楼盘地址城市！',{icon: 2, title:'提示'});
                    return;
                }
                if(!districtNo){
                    parent.layer.alert('请选择楼盘地址城市区域！',{icon: 2, title:'提示'});
                    return;
                }
            }else{
                if(!countryNo){
                    parent.layer.alert('请选择楼盘地址国家！',{icon: 2, title:'提示'});
                    return;
                }
            }
            if(!address){
                parent.layer.alert('请输入楼盘地址城市区域！',{icon: 2, title:'提示'});
                return;
            }
            var salesStatus = $("input[name='salesStatus']:checked").val();
            if(!salesStatus){
                parent.layer.alert('请选择销售状态！',{icon: 2, title:'提示'});
                return;
            }
            var salePriceUnitMin = trimStr($("#salePriceUnitMin").val());
            var salePriceUnitMax = trimStr($("#salePriceUnitMax").val());
            if(!salePriceUnitMin || !salePriceUnitMax){
                parent.layer.alert('请输入总价段！',{icon: 2, title:'提示'});
                return;
            }
            if(!amountReg.test(salePriceUnitMin) || !amountReg.test(salePriceUnitMax)
                || parseFloat(salePriceUnitMin) > parseFloat(salePriceUnitMax)){
                parent.layer.alert('请输入有效总价段！',{icon: 2, title:'提示'});
                return;
            }

            var vendibilityAmount = $("#vendibilityAmount").val();
            if(!checkIsNumber(vendibilityAmount)){
                layer.alert('请输入合法的剩余可售货量金额！',{icon: 2, title:'提示'});
                return;
            }

            if(!checkNumTwoDecimals(vendibilityAmount)){
                layer.alert('剩余可售货量金额必须是正整数或两位小数！',{icon: 2, title:'提示'});
                return;
            }

            var monthRoughAmount = $("#monthRoughAmount").val();
            if(!checkIsNumber(monthRoughAmount)){
                layer.alert('请输入合法的项目月大定金额！',{icon: 2, title:'提示'});
                return;
            }

            if(!checkNumTwoDecimals(monthRoughAmount)){
                layer.alert('项目月大定金额必须是正整数或两位小数！',{icon: 2, title:'提示'});
                return;
            }


            var devCompanyId = $("#devCompanyId").val();
            var devCompanyText = $("#devCompanyText").val();
            var devCompany = $("#devCompany").val();
            var bigCustomerFlag = $("#bigCustomerFlag").val();
            if(!devCompanyId){
                parent.layer.alert('请选择开发商！',{icon: 2, title:'提示'});
                return;
            }
            var devCompanyBroker = trimStr($("#devCompanyBroker").val());
            if(!devCompanyBroker){
                parent.layer.alert('请输入开发商对接人！',{icon: 2, title:'提示'});
                return;
            }
            var devCompanyBrokerTel = trimStr($("#devCompanyBrokerTel").val());
            if(!devCompanyBrokerTel){
                parent.layer.alert('请输入开发商对接人电话！',{icon: 2, title:'提示'});
                return;
            }
            if(!telReg.test(devCompanyBrokerTel)){
                parent.layer.alert('开发商对接人电话错误，请输入11位电话号码！',{icon: 2, title:'提示'});
                return false;
            }

            var cooperationStatus = $("input[name='cooperationStatus']:checked").val();
            if(!cooperationStatus){
                parent.layer.alert('请选择项目合作意向状态！',{icon: 2, title:'提示'});
                return;
            }

            var projectInfoDesc = $("#projectInfoDesc").val();
            var requiredForSupport = $("#requiredForSupport").val();

            var partner = $("input[name='partner']:checked").val();
            if(!partner){
                parent.layer.alert('请选择合作方类型！',{icon: 2, title:'提示'});
                return;
            }
            var partnerNm = trimStr($("#partnerNm").val());
            if(!partnerNm){
                parent.layer.alert('请选择合作方名称！',{icon: 2, title:'提示'});
                return;
            }
            var custodyFlg = $("input[name='custodyFlg']:checked").val();
            if(!custodyFlg){
                parent.layer.alert('请选择是否可垫佣甲方！',{icon: 2, title:'提示'});
                return;
            }
            var mattressNailId = $("#mattressNailId").val();
            var mattressNailText = $("#mattressNailName").val();
            if(custodyFlg=='1' && !mattressNailId){
                parent.layer.alert('请选择垫佣甲方简称！',{icon: 2, title:'提示'});
                return;
            }
            var directSignFlag = $("input[name='directSignFlag']:checked").val();
            if(!directSignFlag){
                parent.layer.alert('请选择是否直签！',{icon: 2, title:'提示'});
                return;
            }
            var cooperationDtStart = trimStr($("#cooperationDtStart").val());
            if(!cooperationDtStart){
                parent.layer.alert('请选择合作开始日期！',{icon: 2, title:'提示'});
                return;
            }
            var cooperationDtEnd = trimStr($("#cooperationDtEnd").val());
            if(!cooperationDtEnd){
                parent.layer.alert('请选择合作结束日期！',{icon: 2, title:'提示'});
                return;
            }
            var partnerContactNm = trimStr($("#partnerContactNm").val());
            if(!partnerContactNm){
                parent.layer.alert('请输入合作方对接人！',{icon: 2, title:'提示'});
                return;
            }
            var partnerContactTel = trimStr($("#partnerContactTel").val());
            if(!partnerContactTel){
                parent.layer.alert('请输入对接人电话！',{icon: 2, title:'提示'});
                return;
            }
            if(!telReg.test(partnerContactTel)){
                parent.layer.alert('对接人电话错误，请输入11位电话号码！',{icon: 2, title:'提示'});
                return false;
            }
            var cityNo = $("#cityNo").val();
            var cityName = $("#cityNo option:selected").text();
            if(!cityNo){
                parent.layer.alert('请选择项目归属城市！',{icon: 2, title:'提示'});
                return;
            }
            var projectDepartmentId = $("#projectDepartmentId").val();
            if(!projectDepartmentId){
                parent.layer.alert('请选择业绩归属项目部！',{icon: 2, title:'提示'});
                return;
            }
            var businessModel = $("#businessModel").val();
            if(!businessModel){
                parent.layer.alert('请选择业务模式！',{icon: 2, title:'提示'});
                return;
            }
            /*var excuseCommisionFlag = $("input[name='excuseCommisionFlag']:checked").val();
            if(!excuseCommisionFlag){
                parent.layer.alert('请选择是否借佣！',{icon: 2, title:'提示'});
                return;
            }*/
            var brushRaiseFlag = $("input[name='brushRaiseFlag']:checked").val();
            if(!brushRaiseFlag){
                parent.layer.alert('请选择刷筹！',{icon: 2, title:'提示'});
                return;
            }
            var totalFieldFlag = $("input[name='totalFieldFlag']:checked").val();
            if(!totalFieldFlag){
                parent.layer.alert('请选择合作方式！',{icon: 2, title:'提示'});
                return;
            }
            var subscribedQuarter1 = trimStr($("#subscribedQuarter1").val());
            if(!subscribedQuarter1){
                // parent.layer.alert('请输入预计第一季度大定金额！',{icon: 2, title:'提示'});
                // return;
                subscribedQuarter1 = 0;
            }
            if(!amountReg.test(subscribedQuarter1)){
                parent.layer.alert('请输入有效预计第一季度大定金额！',{icon: 2, title:'提示'});
                return false;
            }
            var subscribedQuarter2 = trimStr($("#subscribedQuarter2").val());
            if(!subscribedQuarter2){
                // parent.layer.alert('请输入预计第二季度大定金额！',{icon: 2, title:'提示'});
                // return;
                subscribedQuarter2 = 0;
            }
            if(!amountReg.test(subscribedQuarter2)){
                parent.layer.alert('请输入有效预计第二季度大定金额！',{icon: 2, title:'提示'});
                return false;
            }
            var subscribedQuarter3 = trimStr($("#subscribedQuarter3").val());
            if(!subscribedQuarter3){
                // parent.layer.alert('请输入预计第三季度大定金额！',{icon: 2, title:'提示'});
                // return;
                subscribedQuarter3 = 0;
            }
            if(!amountReg.test(subscribedQuarter3)){
                parent.layer.alert('请输入有效预计第三季度大定金额！',{icon: 2, title:'提示'});
                return false;
            }
            var subscribedQuarter4 = trimStr($("#subscribedQuarter4").val());
            if(!subscribedQuarter4){
                // parent.layer.alert('请输入预计第四季度大定金额！',{icon: 2, title:'提示'});
                // return;
                subscribedQuarter4 = 0;
            }
            if(!amountReg.test(subscribedQuarter4)){
                parent.layer.alert('请输入有效预计第四季度大定金额！',{icon: 2, title:'提示'});
                return false;
            }
            var subscribedNextYear = trimStr($("#subscribedNextYear").val());
            if(!subscribedNextYear){
                // parent.layer.alert('请输入预计明年大定金额！',{icon: 2, title:'提示'});
                // return;
                subscribedNextYear = 0;
            }
            if(!amountReg.test(subscribedNextYear)){
                parent.layer.alert('请输入有效预计明年大定金额！',{icon: 2, title:'提示'});
                return false;
            }
            var sceneEmpId = $("#sceneEmpId").val();
            var sceneEmpName = $("#sceneEmpName").val();
            if(!sceneEmpId){
                parent.layer.alert('请选择开发负责人！',{icon: 2, title:'提示'});
                return;
            }
            var empTel = trimStr($("#empTel").val());
            if(!empTel){
                parent.layer.alert('请输入开发负责人电话！',{icon: 2, title:'提示'});
                return;
            }
            if(!telReg.test(empTel)){
                parent.layer.alert('开发负责人电话错误，请输入11位电话号码！',{icon: 2, title:'提示'});
                return false;
            }
            
          //项目
            var projectLeaderEmpId = $("#projectLeaderEmpId").val();
            var projectLeaderName = $("#projectLeaderName").val();
            if(!projectLeaderEmpId || !projectLeaderName){
            	parent.layer.alert('请选择项目负责人！',{icon: 2, title:'提示'});
            	return;
            }
            var projectLeaderTel = trimStr($("#projectLeaderTel").val());
            if(!projectLeaderTel){
            	parent.layer.alert('请输入项目负责人电话！',{icon: 2, title:'提示'});
            	return;
            }
            if(!telReg.test(projectLeaderTel)){
            	parent.layer.alert('项目负责人电话错误，请输入11位电话号码！',{icon: 2, title:'提示'});
            	return false;
            }
            //物业类型
            var mgtKbnResult = new Array();
            $("[name = mgtKbnCB]:checkbox").each(function () {
                if ($(this).is(":checked")) {
                    mgtKbnResult.push($(this).attr("value"));
                }
            });
            if(mgtKbnResult.length==0){
                parent.layer.alert('请选择物业类型！',{icon: 2, title:'提示'});
                return false;
            }
            var mgtKbn = (mgtKbnResult.join(","));
            //产权年限
            var ownYearKbnResult = new Array();
            $("[name = ownYearKbnCB]:checkbox").each(function () {
                if ($(this).is(":checked")) {
                    ownYearKbnResult.push($(this).attr("value"));
                }
            });
            if(ownYearKbnResult.length==0){
                parent.layer.alert('请选择产权年限！',{icon: 2, title:'提示'});
                return false;
            }
            var ownYearKbn = ownYearKbnResult.join(",");
            //
            formData.estateNm=estateNm;
            formData.estatePosition=estatePosition;
            formData.realityCityNo=realityCityNo;
            formData.districtId=districtNo;
            formData.countryNo=countryNo;
            formData.address=address;
            formData.salesStatus=salesStatus;
            formData.salePriceUnitMin=salePriceUnitMin;
            formData.salePriceUnitMax=salePriceUnitMax;
            formData.mgtKbn=mgtKbn;
            formData.ownYearKbn=ownYearKbn;
            formData.devCompanyId = devCompanyId;
            formData.bigCustomerFlag = bigCustomerFlag;
            formData.devCompanyText = devCompany;
            formData.developerName = devCompanyText;
            formData.devCompanyBroker=devCompanyBroker;
            formData.devCompanyBrokerTel=devCompanyBrokerTel;
            formData.partner=partner;
            formData.partnerNm=partnerNm;
            formData.custodyFlg=custodyFlg;
            formData.mattressNailId=mattressNailId;
            formData.mattressNailText=mattressNailText;
            formData.directSignFlag=directSignFlag;
            formData.cooperationDtStart=cooperationDtStart;
            formData.cooperationDtEnd=cooperationDtEnd;
            formData.partnerContactNm=partnerContactNm;
            formData.partnerContactTel=partnerContactTel;
            formData.cityNo=cityNo;
            formData.cityName=cityName;
            formData.projectDepartmentId=projectDepartmentId;
            formData.businessModel=businessModel;
            formData.excuseCommisionFlag = 27902;//是否借佣默认否
            formData.brushRaiseFlag=brushRaiseFlag;
            formData.totalFieldFlag=totalFieldFlag;
            formData.subscribedQuarter1=subscribedQuarter1;
            formData.subscribedQuarter2=subscribedQuarter2;
            formData.subscribedQuarter3=subscribedQuarter3;
            formData.subscribedQuarter4=subscribedQuarter4;
            formData.subscribedThisYear=Number(subscribedQuarter1)+Number(subscribedQuarter2)+Number(subscribedQuarter3)+Number(subscribedQuarter4);
            formData.subscribedNextYear=subscribedNextYear;
            formData.sceneEmpId=sceneEmpId;
            formData.sceneEmpName=sceneEmpName;
            formData.empTel=empTel;
            
            formData.projectLeaderEmpId=projectLeaderEmpId;
            formData.projectLeaderName=projectLeaderName;
            formData.projectLeaderTel=projectLeaderTel;

            formData.partnerAbbreviationNm = $("#partnerAbbreviationNm").val();
            formData.partnerAbbreviationCode = $("#partnerAbbreviationCode").val();
            formData.developerBrandId = $("#developerBrandId").val();
            formData.developerBrandName = $("#developerBrandName").val();

            formData.vendibilityAmount		=	vendibilityAmount;
            formData.monthRoughAmount       =	monthRoughAmount;
            formData.cooperationStatus      =	cooperationStatus;
            formData.projectInfoDesc        =	projectInfoDesc;
            formData.requiredForSupport     =	requiredForSupport;

            console.log("partnerAbbreviationNm="+$("#partnerAbbreviationNm").val()+
                "partnerAbbreviationCode="+$("#partnerAbbreviationCode").val()+
                "developerBrandId="+$("#developerBrandId").val()+
                "developerBrandName="+$("#developerBrandName").val()
            )
            //
            console.log(formData);
            var loadIndex = layer.load(2);

            $.ajax({
                url:BASE_PATH + "/cashBill/getLnkAccountProjectList",
                type:"get",
                data:{
                    estateCityNo : cityNo
                },
                success:function(data){
                    if(data) {
                        data = JSON.parse(data);
                        var length = data.returnValue.length +"";
                        if("1" ==  length) {
                            formData.lnkAccountProjectCode = data.returnValue[0].lnkAccountProjectCode;
                            formData.lnkAccountProject = data.returnValue[0].lnkAccountProject;
                            submitForm(formData,loadIndex);
                        }else {
                            layer.close(loadIndex);
                            //选择考核主体
                            parent.layer.open({
                                type: 2,
                                title: '选择考核主体',
                                area: ['500px', '400px'],
                                content: '/pmlsEstate/toChooseAccountProject?estateCityNo='+cityNo
                                ,scrollbar: false
                                ,resize:false
                                ,btn: ['确定', '取消']
                                ,yes: function(index, layero){
                                    //确认的回调
                                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                                    var lnkFormData = iframeWin.getFormData();
                                    if(lnkFormData!=null) {
                                        formData.lnkAccountProjectCode = lnkFormData.accountProjectCode;
                                        formData.lnkAccountProject = lnkFormData.lnkAccountProject;
                                        //关闭弹出层
                                        parent.layer.close(index);
                                        loadIndex = layer.load(2);
                                        submitForm(formData, loadIndex);
                                    }
                                    //end
                                }
                            });
                        }
                    }
                }
            });
        },
        backEstateList:function () {
            window.parent.redirectTo('delete');
        }
    };

    $('.estateEditPage .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //初始化楼盘城市
    function initRealityCityNoList() {
        var url = BASE_PATH + "/cityCascade/queryCityListByIsService";
        var params = {};
        ajaxGet(url, params, function (data) {
            var result = "<option value=''>请选择城市</option>";
            $.each(data.returnValue, function(n, value) {
                var selectFlag = "";
                if(value.cityNo == estate.realityCityNo){
                    selectFlag = "selected";
                }
                result += "<option value='" + value.cityNo + "' "+ selectFlag +">"
                    + value.cityName + "</option>";
            });
            $("#realityCityNo").html('');
            $("#realityCityNo").append(result);
            form.render('select'); //刷新select选择框渲染
        }, function () {
        });
    }

    //初始化楼盘城市—区域
    function initDistrictNoList(realityCityNo) {
        var url = BASE_PATH + "/linkages/city/" + realityCityNo;
        var params = {};
        var result = "<option value=''>请选择区域</option>";
        if(realityCityNo){
            ajaxGet(url, params, function (data) {
                $.each(data.returnValue, function(n, value) {
                    var selectFlag = "";
                    if(value.districtNo == estate.districtId){
                        selectFlag = "selected";
                    }
                    result += "<option value='" + value.districtNo + "' " + selectFlag +">"
                        + value.districtName + "</option>";
                });
                $("#districtNo").html('');
                $("#districtNo").append(result);
                form.render('select'); //刷新select选择框渲染
            }, function () {
            });
        }else{
            $("#districtNo").html('');
            $("#districtNo").append(result);
            form.render('select'); //刷新select选择框渲染
        }
    }


    /**
     * 初始化垫佣甲方简称
     */
    /*function initMattressNail(){
        var url = BASE_PATH + "/pmlsEstate/getMattressNail";
        var params = {};
        ajaxGet(url, params, function(data) {
            var result = "<option value=''>请选择</option>";
            $.each(data.returnValue, function(n, value) {
                var selectFlag = "";
                if(value.mattressNailId == estate.mattressNailId){
                    selectFlag = "selected";
                }
                result += "<option value='" + value.mattressNailId + "' "+ selectFlag +">"
                    + value.name + "</option>";
            });
            $("#selectMattressNail").html('');
            $("#selectMattressNail").append(result);
            form.render('select'); //刷新select选择框渲染
        }, function() {
        });
    }*/


    //选择开发商或者切换合作方类型 时处理
    function changeDeveloper() {
        console.log("=============changeDeveloper============");
        console.log(selectDeveloperObj);
        //合作方类型
        var partner = $("input[name='partner']:checked").val();
        if(partner == '12801'){
            //合作方名称
            //$("#partnerNm").prop("readonly","readonly");//
            //$("#partnerNm").attr('disabled',"");
            $("#partnerNm").val(selectDeveloperObj.developerName);

            $("#partnerAbbreviationNm").val(selectDeveloperObj.partnerAbbreviationNm);
            $("#partnerAbbreviationCode").val(selectDeveloperObj.developerCode);


            $("#developerBrandName").val(selectDeveloperObj.developerBrandName);//disabled
            $("#developerBrandId").val(selectDeveloperObj.developerBrandId);
            //是否可垫佣甲方
            if(selectDeveloperObj.isYjDy == 1){
                $("#custodyFlg1").prop("checked",true);
                $("#custodyFlg0").prop("checked",false);
                $(".selectMattressNailInfo").removeClass("hidden");
                $("#mattressNailId").val(selectDeveloperObj.mattressNailId);
                $("#mattressNailName").val(selectDeveloperObj.mattressNailName);
/*                $("#selectMattressNail option").each(function () {
                    if(selectDeveloperObj.developerBrandName!=null && selectDeveloperObj.developerBrandName!=''){
                        if(selectDeveloperObj.developerBrandName.indexOf($(this)[0].innerText)>=0){
                            $(this).prop("selected",true);
                            $("#selectMattressNail").attr('disabled',"disabled");//待改2  add待同步
                            return false;//停止循环
                        }
                    }
                })*/
            }else{
                $("#custodyFlg1").prop("checked",false);
                $("#custodyFlg0").prop("checked",true);
                $(".selectMattressNailInfo").addClass("hidden");
                //$("#selectMattressNail").removeProp('disabled');
            }
            $("#custodyFlg1").prop("disabled","disabled");
            $("#custodyFlg0").prop("disabled","disabled");
            //合作方对接人
            $("#partnerContactNm").prop("readonly","readonly");//
            $("#partnerContactNm").val(selectDeveloperObj.dockingPeo);
            //对接人电话
            $("#partnerContactTel").prop("readonly","readonly");//
            $("#partnerContactTel").val(selectDeveloperObj.dockingPeoTel);

            $("input[name=directSignFlag]").each(function () {
                $(this).prop("disabled","disabled");
                if($(this).val()=='22801'){
                    $(this).prop("checked",true);
                }
            })
        }else{
        /*    $("#partnerNm").removeProp("readonly");
            $("#custodyFlg1").removeProp("disabled");
            $("#custodyFlg0").removeProp("disabled");
            $("#partnerContactNm").removeProp("readonly");
            $("#partnerContactTel").removeProp("readonly");
            $("#selectMattressNail").removeProp('disabled');*/



            console.log("=============selectPartnerInfoObj============");
            console.log(selectPartnerInfoObj);

            if(selectPartnerInfoObj!=undefined && selectPartnerInfoObj!=null && selectPartnerInfoObj!=''){
                //合作方名称
                //$("#partnerNm").prop("readonly","readonly");//
                //$("#partnerNm").attr('disabled',"");
                $("#partnerNm").val(selectPartnerInfoObj.developerName);//disabled

                $("#partnerAbbreviationNm").val(selectPartnerInfoObj.partnerAbbreviationNm);
                $("#partnerAbbreviationCode").val(selectPartnerInfoObj.developerCode);


                $("#developerBrandName").val(selectPartnerInfoObj.developerBrandName);//disabled
                $("#developerBrandId").val(selectPartnerInfoObj.developerBrandId);


                //是否可垫佣甲方
                if(selectPartnerInfoObj.isYjDy == 1){
                    $("#custodyFlg1").prop("checked",true);
                    $("#custodyFlg0").prop("checked",false);
                    $(".selectMattressNailInfo").removeClass("hidden");

                    $("#mattressNailId").val(selectPartnerInfoObj.mattressNailId);
                    $("#mattressNailName").val(selectPartnerInfoObj.mattressNailName);
/*                    $("#selectMattressNail option").each(function () {
                        if(selectPartnerInfoObj.developerBrandName!=null && selectPartnerInfoObj.developerBrandName!=''){
                            if(selectPartnerInfoObj.developerBrandName.indexOf($(this)[0].innerText)>=0){
                                $(this).prop("selected",true);
                                $("#selectMattressNail").attr('disabled',"disabled");//待改2  add待同步
                                return false;//停止循环
                            }
                        }
                    })*/


                }else{
                    $("#custodyFlg1").prop("checked",false);
                    $("#custodyFlg0").prop("checked",true);
                    $(".selectMattressNailInfo").addClass("hidden");
                    //$("#selectMattressNail").removeProp('disabled');
                }
                $("#custodyFlg1").prop("disabled","disabled");
                $("#custodyFlg0").prop("disabled","disabled");
                //合作方对接人
                $("#partnerContactNm").prop("readonly","readonly");//
                $("#partnerContactNm").val(selectPartnerInfoObj.dockingPeo);
                //对接人电话
                $("#partnerContactTel").prop("readonly","readonly");//
                $("#partnerContactTel").val(selectPartnerInfoObj.dockingPeoTel);
            }

        }
        form.render();
    }


    //提交表单
    function submitForm(formData,loadIndex) {
        var id = $("#id").val();
        var estateDb = checkEstateStatus(id);
        if(isEmpty(estateDb) || isEmpty(estateDb.projectStatus) || isEmpty(estateDb.auditStatus)){
            layer.close(loadIndex);
            parent.parent.layer.alert("获取项目状态失败，请重新提交", {icon: 2, title:'提示'});
            return false;
        }else {
            var opType = $("#opType").val();
            if(opType=='editPartner'){
                if(estateDb.releaseStatus != '13001' && estateDb.auditStatus=='12904' && estateDb.projectStatus == '20301'){
                    //过
                }else {
                    layer.close(loadIndex);
                    parent.parent.layer.alert("项目状态发生变化，不能提交", {icon: 2, title:'提示'});
                    return false;
                }
            }else {
                if(estateDb.releaseStatus != '13001' && estateDb.auditStatus!='12903' && estateDb.auditStatus!='12904'
                    && estateDb.projectStatus != '20302' && estateDb.projectStatus != '20303' && estateDb.projectStatus != '20304'
                    && estateDb.projectStatus != '20306'){
                    //过
                }else {
                    layer.close(loadIndex);
                    parent.parent.layer.alert("项目状态发生变化，不能提交", {icon: 2, title:'提示'});
                    return false;
                }
            }
        }


        $.ajax({
            url: BASE_PATH + '/pmlsEstate/update/'+formData.id,
            type: 'post',
            data:formData,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                layer.close(loadIndex);
                if (data.returnCode == -1){
                    parent.parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                } else {
                    parent.parent.layer.alert(data.returnMsg, {icon: 1, title:'提示'},function(){
                        parent.layer.closeAll();
                        active['backEstateList'].call(this);
                    });
                }
            }
        });
    }

    function clearPartnerInfo() {
        var partner = $("input[name='partner']:checked").val();
        if(partner == '12801'){
            $("#selectPartnerNm").hide();//隐藏选择按钮
            changeDeveloper();
        }else {
            $("#selectPartnerNm").show();//选择按钮
            var selectPartnerInfoObj = '';
            $("#partnerNm").val('');//disabled
            $("#partnerAbbreviationNm").val('');
            $("#partnerAbbreviationCode").val('');
            $("#developerBrandName").val('');//disabled
            $("#developerBrandId").val('');
            $("#mattressNailId").val('');
            $("#mattressNailName").val('');
            $("#custodyFlg1").prop("checked",false);
            $("#custodyFlg0").prop("checked",true);//否
            $(".selectMattressNailInfo").addClass("hidden");

            $("input[name=directSignFlag]").each(function () {
                $(this).prop("disabled","disabled");
                if($(this).val()=='22802'){
                    $(this).prop("checked",true);
                }
            })
        }
        form.render();
    }

});
