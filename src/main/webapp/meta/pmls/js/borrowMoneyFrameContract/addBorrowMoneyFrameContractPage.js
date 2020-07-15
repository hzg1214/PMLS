/*
 * 借佣框架协议js
 * */
var layer;
var id=window.id;
var jsonDto={};
var treeObj;
var form,upload,laydate;
var selectBankInfo;//选择的开户信息
var formSelects;
var jyComList=[];
var table;
layui.use(['element','tree', 'layer', 'form','table','upload','laydate'], function() {
	var element = layui.element,
    layer = layui.layer,
        $ = layui.jquery;
    form = layui.form;
    upload = layui.upload;
    laydate=layui.laydate;
    formSelects = layui.formSelects;
    table = layui.table;

    laydate.render({
        elem: '#dateLifeStart',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        done: function (value, date, endDate) {
            var startDate = new Date(value).getTime();
            if($('#dateLifeEnd').val()!=''){
                var endTime = new Date($('#dateLifeEnd').val()).getTime();
                if (endTime < startDate) {
                    layer.msg('开始时间不能大于结束时间');
                    $('#dateLifeStart').val('');
                }
            }
        }
    });
    laydate.render({
        elem: '#dateLifeEnd',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        done: function (value, date, endDate) {
            if($('#dateLifeStart').val()!=""){
                var startDate = new Date($('#dateLifeStart').val()).getTime();
                var endTime = new Date(value).getTime();
                if (endTime < startDate) {
                    layer.msg('结束时间不能小于开始时间');
                    $('#dateLifeEnd').val('');
                }
            }
        }
    });
    laydate.render({
        elem: '#signDate',
        format: 'yyyy-MM-dd',
        trigger: 'click'
    });

    if(id!=null && id !=''){
        if(window.frameContractDto!=''){
            jsonDto=eval('(' + window.frameContractDto.replace(/[\r\n]/g,"\\n") + ')');

            $("#companyNo").val(jsonDto.companyNo);
            $("#companyName").val(jsonDto.companyName);
            $("#dateLifeStart").val(formatDate(jsonDto.dateLifeStart,'yyyy-MM-dd'));
            $("#dateLifeEnd").val(formatDate(jsonDto.dateLifeEnd,'yyyy-MM-dd'));
            $("#signDate").val(formatDate(jsonDto.signDate,'yyyy-MM-dd'));
            $("#accountNm").val(jsonDto.accountNm);
            $("#accountProvinceNo").val(jsonDto.accountProvinceNo);
            $("#accountCityNo").val(jsonDto.accountCityNo);
            $("#bankAccountNm").val(jsonDto.bankAccountNm);
            $("#bankAccount").val(jsonDto.bankAccount);
            $("#partyBNm").val(jsonDto.partyBNm);
            $("#partyBTel").val(jsonDto.partyBTel);
            $("#contractNote").val(jsonDto.contractNote);

            $(".selectBusinessBtn").hide();//隐藏选择渠道按钮
            $(".blockTitle").text('编辑借佣框架协议');
            form.render();

            //初始化区域选项
            initProviceSelect(jsonDto.accountProvinceNo,function () {
                form.render();
                //初始化城市
                initCitySelect(jsonDto.accountProvinceNo,jsonDto.accountCityNo,function () {
                    form.render();
                });
            });

            jyComList = jsonDto.jyComList;
            //初始化加载图片
            loadImageList('fileBusiness',jsonDto.fileBusinessList,true);
            loadImageList('fileContract',jsonDto.fileContractList,true);
            loadImageList('fileOther',jsonDto.attachmentFileList,true);

        }
    }else{
        initProviceSelect(null,function () {
            form.render();
        });
        form.render();
    }
    
    tableRender(jyComList);
    
    
    var active = {
        //选择公司名称
        selectBusiness: function () {
            parent.layer.open({
                type: 2,
                title: '选择公司名称',
                area: ['800px', '600px'],
                content: '/borrowMoneyFrameContract/selectCompanyPage?companyType='+1
                ,scrollbar: false
                ,resize:false
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    console.log(formData);
                    if(formData!=null) {
                        selectBusinessInfo=formData;
                        $("#companyNo").val(formData.companyNo);
                        $("#companyName").val(formData.companyName);

                        //获取最新的开户信息
                        $.ajax({
                            url: ctx + '/borrowMoneyFrameContract/getOldBMFCBankInfo',
                            type: 'get',
                            data:{companyNo:formData.companyNo},
                            dataType: 'json',
                            success: function (data) {
                                if(data!=null){
                                    $("#accountNm").val(data.accountNm);
                                    $("#bankAccountNm").val(data.bankAccountNm);
                                    $("#bankAccount").val(data.bankAccount);

                                    //初始化区域选项
                                    initProviceSelect(data.accountProvinceNo,function () {
                                        form.render();
                                        //初始化城市
                                        initCitySelect(data.accountProvinceNo,data.accountCityNo,function () {
                                            form.render();
                                        });
                                    });
                                }
                            }
                        });
                        parent.layer.close(index);
                    }
                }
            });
        },
        //选择开户行
        selectBankInfo:function () {
            var companyNo=$("#companyNo").val();
            if(companyNo==null || companyNo==''){
                parent.layer.alert('请先选择公司！',{icon: 2, title:'提示'});
                return;
            }
            parent.layer.open({
                type: 2,
                title: '选择开户信息',
                area: ['800px', '600px'],
                content: '/borrowMoneyFrameContract/selectBankInfoPage?companyNo='+companyNo
                ,scrollbar: false
                ,resize:false
                ,btn: ['确定', '取消']
                ,yes: function(index, layero){
                    //确认的回调
                    var iframeWin = parent.window[layero.find('iframe')[0]['name']];
                    var formData = iframeWin.getFormData();
                    console.log(formData);
                    if(formData!=null) {
                        selectBankInfo=formData;
                        $("#accountNm").val(formData.accountNm);
                        $("#bankAccountNm").val(formData.bankAccountNm);
                        $("#bankAccount").val(formData.bankAccount);

                        //初始化区域选项
                        initProviceSelect(formData.accountProvinceNo,function () {
                            form.render();
                            //初始化城市
                            initCitySelect(formData.accountProvinceNo,formData.accountCityNo,function () {
                                form.render();
                            });
                        });
                        parent.layer.close(index);
                    }
                }
            });
        }
    };
    $('.layui-form .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //省份选择事件
    form.on('select(accountProvinceNo)', function (data) {
        console.log(data);
        if(data.value!=null && data.value!=''){
            initCitySelect(data.value,null,function () {
                form.render();
            });
        }
    });

    form.render();


    uploadRender('fileBusiness',{fileTypeId:'1072',fileSourceId:'26',exts:'jpg|png|jpeg|pdf'});
    uploadRender('fileContract',{fileTypeId:'1073',fileSourceId:'26',exts:'jpg|png|jpeg|pdf'});
    uploadRender('fileOther',{fileTypeId:'1074',fileSourceId:'26',exts:'jpg|png|jpeg|pdf'});

});

function tableRender(jyComList){
    table.render({
    	elem: '#contentCompanyTable'
    		, cols: setCols()
    		, id: 'contentCompanyReload'
    			, data: jyComList
    			,width:1000
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
    	var row1 = [];
    	row1.push(
    			{field:'companyNo',title: '公司编号',width:120, align: 'center'},
    			{field:'companyName',title: '公司名称',width:300, align: 'center'
    				, templet: function (d) {
    					return "<div style='text-align: left'>" + d.companyName + "</div>";
    				}
    			},
    			{field:'address',title: '地址',width:400, align: 'center'
    				, templet: function (d) {
    					return "<div style='text-align: left'>" + d.address + "</div>";
    				}
    			},
    			{
    				title: '操作',align: 'center', width:175,templet: function (d) {
    					var ret = "<a class='layui-btn layui-btn-danger layui-btn-xs' onclick='removeTr(this,\""+d.companyNo+"\")'>移除</a>";
    					return ret;
    				}
    			}
    	);
    	var cols = [];
    	cols.push(row1);
    	return cols;
    }
//    console.log("data1-----"+JSON.stringify(table.cache));
}

//初始化省份和城市选项
function initProviceSelect(checkValue, callback){
    var url = BASE_PATH + "/pmlsFrameContract/province";
    var params = {};
    ajaxGet(url, params, function(data) {
        var result = "<option value=''>请选择省份</option>";
        $.each(data.returnData, function(n, value) {
            if (checkValue != null && checkValue == value.provinceNo) {
                result += "<option value='" + value.provinceNo + "' selected>" + value.provinceName + "</option>";
            } else {
                result += "<option value='" + value.provinceNo + "'>" + value.provinceName + "</option>";
            }
        });
        $("#accountProvinceNo").html('');
        $("#accountProvinceNo").append(result);

        callback ? callback() : $.noop();
    }, function() {
    });

}

//初始化城市选项
function initCitySelect(accountProvinceNo, checkValue, callback){
    var url = BASE_PATH + "/cityCascade/city?provinceNo=" + accountProvinceNo;
    var params = {};

    ajaxGet(url, params, function(data) {
        var result = "<option value=''>请选择城市</option>";
        $.each(data.returnValue, function(n, value) {
            if (checkValue != null && checkValue == value.cityNo) {
                result += "<option value='" + value.cityNo + "' selected>" + value.cityName + "</option>";
            } else {
                result += "<option value='" + value.cityNo + "'>" + value.cityName + "</option>";
            }
        });
        $("#accountCityNo").html('');
        $("#accountCityNo").append(result);
        callback ? callback() : $.noop();
    }, function() {
    });
}


//取消返回上一个页面
function back(){
    parent.redirectTo('delete',null,null);
}
function submitForm(){
    parent.layer.load(2);
    var companyNo=$("#companyNo").val();
    var companyName=$("#companyName").val();
    var dateLifeStart=$("#dateLifeStart").val();
    var dateLifeEnd=$("#dateLifeEnd").val();
    var signDate=$("#signDate").val();
    var accountNm=$("#accountNm").val();
    var accountProvinceNo=$("#accountProvinceNo").val();
    var accountProvinceNm = $("#accountProvinceNo").find("option:selected").text();
    var accountCityNo=$("#accountCityNo").val();
    var accountCityNm = $("#accountCityNo").find("option:selected").text();
    var bankAccountNm=$("#bankAccountNm").val();
    var bankAccount=$("#bankAccount").val();
    var partyBNm=$("#partyBNm").val();
    var partyBTel=$("#partyBTel").val();
    var contractNote=$("#contractNote").val();

    //获取上传图片的id
    var fileIds='';
    $(".layui-upload .item_img").each(function () {
        fileIds+=$(this).data("id")+',';
    });
    if(fileIds!=''){
        fileIds=fileIds.substring(0,fileIds.length-1);
    }


    if(isBlank(companyNo)){
        parent.layer.closeAll();
        parent.layer.alert('请选择公司名称！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(dateLifeStart)){
        parent.layer.closeAll();
        parent.layer.alert('请输入合同开始日期！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(dateLifeEnd)){
        parent.layer.closeAll();
        parent.layer.alert('请输入合同结束日期！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(signDate)){
        parent.layer.closeAll();
        parent.layer.alert('请输入合同签订日期！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(accountNm)){
        parent.layer.closeAll();
        parent.layer.alert('请输入开户名！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(accountProvinceNo)){
        parent.layer.closeAll();
        parent.layer.alert('请选择开户省份！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(accountCityNo)){
        parent.layer.closeAll();
        parent.layer.alert('请选择开户城市！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(bankAccountNm)){
        parent.layer.closeAll();
        parent.layer.alert('请输入开户行！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(bankAccount)){
        parent.layer.closeAll();
        parent.layer.alert('请输入银行账号！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(partyBNm)){
        parent.layer.closeAll();
        parent.layer.alert('请输入乙方授权代表！',{icon: 2, title:'提示'});
        return;
    }
    if(isBlank(partyBTel)){
        parent.layer.closeAll();
        parent.layer.alert('请输入乙方联系方式！',{icon: 2, title:'提示'});
        return;
    }else if(!checkPhoneNumber(partyBTel)){
        parent.layer.closeAll();
        parent.layer.alert('请输入正确的乙方手机号码！',{icon: 2, title:'提示'});
        return;
    }
    if($("#fileBusiness .item_img").length==0){
        parent.layer.closeAll();
        parent.layer.alert('请上传营业执照！',{icon: 2, title:'提示'});
        return;
    }
    if($("#fileContract .item_img").length==0){
        parent.layer.closeAll();
        parent.layer.alert('请上传合同！',{icon: 2, title:'提示'});
        return;
    }

    
    var jyComAry = [];
    console.log("提交:jyComList-----"+jyComList);
    if(null != jyComList && jyComList.length>0 ){
    	for(var i=0;i<jyComList.length;i++) {
    		var jyCompanyDto = {};
          jyCompanyDto.companyNo = jyComList[i].companyNo;
          if(companyNo==jyComList[i].companyNo){
        	  parent.layer.closeAll();
              parent.layer.alert('公司名称:'+companyName+"不能与关联公司重复，请去除重新选择", {icon: 2, title: '提示'});
              return;
          }
          jyCompanyDto.companyName = jyComList[i].companyName;
          jyCompanyDto.address = jyComList[i].address;
          jyComAry.push(jyComList[i].companyNo);
    	}
    }
    var repeatCompanyName = isRepeatAry(jyComAry);
    if(!isNullEmpty(repeatCompanyName)){
    	parent.layer.closeAll();
        parent.layer.alert('公司:'+repeatCompanyName+"存在重复，请移除重复项，重新选择", {icon: 2, title: '提示'});
        return;
    }

    if(isNullEmpty(jyComList) || jyComList.length == 0){
    	parent.layer.closeAll();
        parent.layer.alert('公司最少需要一条', {icon: 2, title: '提示'});
        return;
    }
    var borrowMoneyFrameContractDto={};
    borrowMoneyFrameContractDto.companyNo=companyNo;
    borrowMoneyFrameContractDto.companyName=companyName;
    borrowMoneyFrameContractDto.dateLifeStart=dateLifeStart;
    borrowMoneyFrameContractDto.dateLifeEnd=dateLifeEnd;
    borrowMoneyFrameContractDto.signDate=signDate;
    borrowMoneyFrameContractDto.accountNm=accountNm;
    borrowMoneyFrameContractDto.accountProvinceNo=accountProvinceNo;
    borrowMoneyFrameContractDto.accountProvinceNm=accountProvinceNm;
    borrowMoneyFrameContractDto.accountCityNo=accountCityNo;
    borrowMoneyFrameContractDto.accountCityNm=accountCityNm;
    borrowMoneyFrameContractDto.bankAccountNm=bankAccountNm;
    borrowMoneyFrameContractDto.bankAccount=bankAccount;
    borrowMoneyFrameContractDto.partyBNm=partyBNm;
    borrowMoneyFrameContractDto.partyBTel=partyBTel;
    borrowMoneyFrameContractDto.contractNote=contractNote;
    borrowMoneyFrameContractDto.fileRecordMainIds=fileIds;
    borrowMoneyFrameContractDto.jyComList=jyComList;
    console.log(borrowMoneyFrameContractDto);

    
    var reqUrl=ctx + '/borrowMoneyFrameContract/addBorrowMoneyFrameContract';
    if(id!=null && id!=''){//修改
    	borrowMoneyFrameContractDto.id=id;
    	borrowMoneyFrameContractDto.frameContractId=id;
    	borrowMoneyFrameContractDto.contractNo=$('#contractNo').val();
    	borrowMoneyFrameContractDto.oldfileRecordMainIds=jsonDto.fileRecordMainIds;
        reqUrl=ctx + '/borrowMoneyFrameContract/updateBorrowMoneyFrameContract';
    }
    console.log(borrowMoneyFrameContractDto);
    $.ajax({
        url: reqUrl,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json;charset=utf-8', //设置请求头信息
        data:JSON.stringify(borrowMoneyFrameContractDto),
        success: function (data) {
            parent.layer.closeAll();
            console.log(data);
            if(data.returnCode=='200'){
                parent.layer.alert(data.returnMsg,{icon: 1, title:'提示'});
                parent.redirectTo('replace',ctx+'/borrowMoneyFrameContract','借佣框架协议');
            }else{
                parent.layer.alert('数据加载失败');
            }
        },fail:function () {
            parent.layer.closeAll();
            parent.layer.alert('请求异常');
        }
    });
}

/**
 * 添加公司
 * @returns
 */
function addCompany(){
	var companyNo=$("#companyNo").val();
	if(isBlank(companyNo)){
        parent.layer.closeAll();
        parent.layer.alert('请选择公司名称！',{icon: 2, title:'提示'});
        return;
    }
	parent.layer.open({
        type: 2,
        title: '选择公司名称',
        area: ['800px', '600px'],
        content: '/borrowMoneyFrameContract/selectCompanyPage?companyType='+2+"&parentCompanyNo="+companyNo
        ,scrollbar: false
        ,resize:false
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            //确认的回调
            var iframeWin = parent.window[layero.find('iframe')[0]['name']];
            var formData = iframeWin.getFormData();
            console.log(formData);
            if(formData!=null) {
            	var companyNo = formData.companyNo;
            	var companyName = formData.companyName;
            	var address = formData.address;
            	var addJyComList = JSON.stringify(jyComList);
            	if(addJyComList.indexOf(companyNo)!=-1){
            		parent.layer.closeAll();
                    parent.layer.alert('公司名称:'+companyName+"存在重复，请重新选择", {icon: 2, title: '提示'});
                    return;
            	}
//            	addCol(companyNo,companyName,address);
            	var jyCompanyDto = {};
                jyCompanyDto.companyNo = companyNo;
                jyCompanyDto.companyName = companyName;
                jyCompanyDto.address = address;
                jyComList.push(jyCompanyDto);
                tableRender(jyComList);
                console.log("添加之后table集合数据---"+JSON.stringify(jyComList));
                parent.layer.close(index);
            }
        }
    });
}

/**
 * 编辑展示公司
 * @param companyNo
 * @param companyName
 * @param address
 * @returns
 */
//function setCompanyCol(list){
//	
//	var contentTrStr = '';
//	if(null != list  ){
//		for(var i=0;i<list.length;i++) {
//			
//		contentTrStr += '<tr tag="fydxTr">'
//			
//		contentTrStr += '<td data-field="companyNo" align="center" data-content="">';
//		contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-0">'
//		contentTrStr += '<input type="hidden" name="companyNo" value="'+jyComList[i].companyNo+'"/>';
//		contentTrStr += '<span>'+jyComList[i].companyNo+'</span>';
//		contentTrStr += '</div></td>';
//		
//		contentTrStr += '<td data-field="companyName" align="left" data-content="">';
//		contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-1">'
//		contentTrStr += '<input type="hidden" name="companyName" value="'+jyComList[i].companyName+'"/>';
//		contentTrStr += '<span name="companyName">'+jyComList[i].companyName+'</span>';
//		contentTrStr += '</div></td>';
//		
//		contentTrStr += '<td data-field="address" align="left" data-content="">';
//		contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-2">'
//		contentTrStr += '<input type="hidden" name="address" value="'+jyComList[i].address+'"/>';
//		contentTrStr += '<span name="address">'+jyComList[i].address+'</span>';
//		contentTrStr += '</div></td>';
//		
//		
//		contentTrStr += '<td>';
//		contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-3" style="text-align: center;">'
//		contentTrStr += '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="removeTr(this)">移除</a>';
//		contentTrStr += '</div></td>';
//		
//		contentTrStr += '</tr>';
//		}
//	}
//    $("div[lay-id='contentCompanyReload'] .layui-table-main tbody").append(contentTrStr);
//}
//
///**
// * 添加公司
// * @returns
// */
//function addCol(companyNo,companyName,address){
//    var contentTrStr = '';
//    contentTrStr += '<tr tag="fydxTr">'
//
//	contentTrStr += '<td data-field="companyNo" align="center" data-content="">';
//    contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-0">'
//	contentTrStr += '<input type="hidden" name="companyNo" value="'+companyNo+'"/>';
//    contentTrStr += '<span>'+companyNo+'</span>';
//    contentTrStr += '</div></td>';
//    
//	contentTrStr += '<td data-field="companyName" align="left" data-content="">';
//    contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-1">'
//    contentTrStr += '<input type="hidden" name="companyName" value="'+companyName+'"/>';
//    contentTrStr += '<span name="companyName">'+companyName+'</span>';
//    contentTrStr += '</div></td>';
//    
//    contentTrStr += '<td data-field="address" align="left" data-content="">';
//    contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-2">'
//    contentTrStr += '<input type="hidden" name="address" value="'+address+'"/>';
//    contentTrStr += '<span name="address">'+address+'</span>';
//    contentTrStr += '</div></td>';
//
//
//    contentTrStr += '<td>';
//    contentTrStr += '<div class="layui-table-cell laytable-cell-1-0-3" style="text-align: center;">'
//    contentTrStr += '<a class="layui-btn layui-btn-danger layui-btn-xs" onclick="removeTr(this)">移除</a>';
//    contentTrStr += '</div></td>';
//
//    contentTrStr += '</tr>';
//    $("div[lay-id='contentCompanyReload'] .layui-table-main tbody").append(contentTrStr);
//}

/**
 * 删除一行
 * @param obj
 * @returns
 */
function removeTr(obj,companyNo) {
    var currTr = $(obj).parents('tr');
    currTr.remove();
//    var removeUrl=ctx + '/borrowMoneyFrameContract/remove';
//    //编辑的时候移除直接删除库里对应公司信息
//    if(id!=null && id !=''){
//    	var contractNo = $('#contractNo').val();
//    	var parentCompanyNo = $('#companyNo').val();
//    	$.ajax({
//    		url: removeUrl,
//    		type: 'get',
//    		dataType: 'json',
//    		data:{companyNo:companyNo,
//    			contractNo:contractNo,
//    			parentCompanyNo:parentCompanyNo},
//    		success: function (data) {
//    		},fail:function () {
//    		}
//    	});
//    }
    
    var newJyComList = [];
//    jyComList.splice(jyComList.indexOf(companyNo),1);
    if(null != jyComList && jyComList.length>0 ){
    	for(var i=0;i<jyComList.length;i++) {
    		if(jyComList[i].companyNo != companyNo){
    			newJyComList.push(jyComList[i]);
    		}
    	}
    }
    jyComList.length=0;
    jyComList = [].concat(newJyComList);
    console.log("移除之后table集合数据---"+JSON.stringify(jyComList));
}


function isNullEmpty(obj){
    if(obj == null || obj == "" || obj == undefined){
        return true;
    }else{
        return false;
    }
}