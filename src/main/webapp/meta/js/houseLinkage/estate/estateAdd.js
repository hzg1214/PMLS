$(function() {
	//市区域板块
	linkage();
	getEmpList();
	// photoUploader();
	getcommissionStatus();
	cityLinkageIsService();
	bigCustomerList();
	getMattressNail();
});


//保存
function add(){
	//保存时审核状态为未提交
	$("#auditStatus").val(12904);
	if(Validator.validFormDataType("estateAddForm")){
		if(checkForAdd()){
			var url = BASE_PATH + '/estate';
			systemLoading("", true);
			httpPost('estateAddForm', url, function(data) {
				location.href = BASE_PATH + "/estate";
			}, function(data) {
				Dialog.alertError(data.returnMsg);
				systemLoaded();
			});
		}
	}
}

//提交
function addSubmit(){
	//提交时审核状态为未审核
	$("#auditStatus").val(12901);

	
	if(Validator.validForm("estateAddForm")){
		if (checkItem(1) == false) {
			return false;
		}
		var estateCityNo =  $("#cityNo").val();
		if(isNullOrEmpty(estateCityNo)) {
			$.ajax({
				url:BASE_PATH + "/cashBill/getLnkAccountProjectList",
				async: false,
				type:"get",
				data:{
					estateCityNo	: estateCityNo
				},
				success:function(data){
					if(data) {
						data = JSON.parse(data);
						var length =  data.returnValue.length +"";
						if("1" ==  length) {
							dataLength = 1;
							$("#lnkAccountProjectCode").val(data.returnValue[0].lnkaccountProjectCode);
							$("#lnkAccountProject").val(data.returnValue[0].lnkAccountProject);
						}else {
							dataLength = 2;
						}
					}
				}
			});
		}
		if( 1 ==  dataLength) {
			submitOA();
		} else {
			//弹窗选择
			var url_a = BASE_PATH+"/estate/toChooseAccountProject";
			var params = {estateCityNo:estateCityNo};
			var dialogOptions = {
					width:600
			};
			Dialog.ajaxOpenDialog(url_a, params, "选择考核主体", function(dialog, resData) {
				EstateType.dialog = dialog;
			}, dialogOptions);
		}
	}
}
EstateType = function() {
	dialog: null;
};

EstateType.close = function() {
	EstateType.dialog.close();
};
EstateType.closePopup = function(){
	EstateType.dialog.close();
}
/**
 * 提交oa
 */
function submitOA(){
	var url = BASE_PATH + '/estate';
	systemLoading("", true);
    httpPost('estateAddForm', url, function(data) {
		location.href = BASE_PATH + "/estate";
	}, function(data) {
		Dialog.alertError(data.returnMsg);
		systemLoaded();
	});
}
/**
 * 选择考核主体
 */
EstateType.chooseAccountProject=function(){ 
	var accountProjectMapping = $("#lnkAccountProjectCode option:selected");
	//考核主体
	var accountProjectCode = accountProjectMapping.val();
	var lnkAccountProject = accountProjectMapping.attr('data-lnkAccountProject');
	if (accountProjectCode==null || accountProjectCode == "" || accountProjectCode == undefined) {
		$("#warning-noCity").html("请选择核算主体！");
		return false;
	}
	EstateType.dialog.close();
	$("#lnkAccountProjectCode").val(accountProjectCode);
	$("#lnkAccountProject").val(lnkAccountProject);
	submitOA();
};

//添加在售户型
function addSellingHouseType(data){
	var num = "";//添加在售户型的户型图尾部数字8,9,10,11
	var temNum = "";//添加在售户型的样板图尾部数字81,91,101,111
	
	//判断新添户型顺序，不存在则在下方添加
	if($("#countF8").length <= 0){
		num = "8";
		temNum = "81";
	}else if($("#countF9").length <= 0){
		num = "9";
		temNum = "91";
	}else if($("#countF10").length <= 0){
		num = "10";
		temNum = "101";
	}else if($("#countF11").length <= 0){
		num = "11";
		temNum = "111";
	}else{
		Dialog.alertInfo("最多添加五套户型");
		return false;
	}
	//添加新户型
	$(data).before(
	'<ul class="list-inline form-inline">'+
         '<li>'+
           ' <div class="form-group">'+
                '<label class="fw-normal w100 text-right">主力户型：</label>'+
                '<input type="radio" value="1" name="countFlg'+num+'" ><label class="fon-normal small">是</label>'+
                '<input type="radio" value="0" name="countFlg'+num+'" ><label class="fon-normal small">否</label>'+
                '<span class="fc-warning"></span>'+
                '<a  href="javascript:void(0)" onclick="delSellingHouseType(this,'+num+');" id="delSellingHouseType'+num+'" style="padding-left: 35px ">-删除</a>'+
            '</div>'+
        '</li>'+
    '</ul>'+
    '<ul class="list-inline form-inline">'+
		'<li class="houseType">'+
		    '<div class="form-group">'+
		        '<label class="fw-normal w100 text-right">户型：</label>'+
		        '<input type="text" class="form-control w50" name="countF'+num+'" id="countF'+num+'" placeholder="" maxlength="2" dataType="posNumWithZero">室'+
		        '<input type="text" class="form-control w50" name="countT'+num+'" id="countT'+num+'" placeholder="" maxlength="2" dataType="posNumWithZero">厅'+
		        '<input type="text" class="form-control w50" name="countW'+num+'" id="countW'+num+'" placeholder="" maxlength="2" dataType="posNumWithZero">卫'+
		        '<span class="fc-warning"></span>'+
		    '</div>'+
		'</li>'+
		'<li>'+
		    '<div class="form-group">'+
		        '<label class="fw-normal w100 text-right">面积：</label>'+
		        '<input type="text" class="form-control w80" name="buildSquare'+num+'" id="buildSquare'+num+'" placeholder="" maxlength="4" dataType="normal">㎡'+
		        '<span class="fc-warning"></span>'+
		    '</div>'+
		'</li>'+
	'</ul>'+
    '<ul class="list-inline form-inline">'+
		'<li>'+
		    '<div class="form-group">'+
		        '<label class="fw-normal w100 text-right" for="directionKbn'+num+'">朝向<i>*</i>：</label>'+
		    		'<input type="radio" value="1511" id="1511" name="directionKbn'+num+'" ><label class="fon-normal small">东</label>'+
		    		'<input type="radio" value="1512" id="1512" name="directionKbn'+num+'" ><label class="fon-normal small">西</label>'+
		    		'<input type="radio" value="1513" id="1513" name="directionKbn'+num+'" ><label class="fon-normal small">南</label>'+
		    		'<input type="radio" value="1514" id="1514" name="directionKbn'+num+'" ><label class="fon-normal small">北</label>'+
		    		'<input type="radio" value="1515" id="1515" name="directionKbn'+num+'" ><label class="fon-normal small">东西</label>'+
		    		'<input type="radio" value="1516" id="1516" name="directionKbn'+num+'" ><label class="fon-normal small">东南</label>'+
		    		'<input type="radio" value="1517" id="1517" name="directionKbn'+num+'" ><label class="fon-normal small">东北</label>'+
		    		'<input type="radio" value="1518" id="1518" name="directionKbn'+num+'" ><label class="fon-normal small">西南</label>'+
		    		'<input type="radio" value="1519" id="1519" name="directionKbn'+num+'" ><label class="fon-normal small">西北</label>'+
		    		'<input type="radio" value="1510" id="1510" name="directionKbn'+num+'" ><label class="fon-normal small">南北</label>'+
		        '<span class="fc-warning"></span>'+
		    '</div>'+
		'</li>'+
	'</ul>'+
    '<ul class="list-inline form-inline">' +
		'<li>' +
		    '<div class="form-group">'+
		        '<label class="fw-normal w100 text-right" style="vertical-align: top;">户型图：</label>'+
				'<ul class="list_photo_uploader" style="display:inline-block">'+
					'<li class="item_photo_uploader item_photo_uploader_add"><s class="icon s_add_btn"></s> <input '+
						'type="file" onchange="addPhoto(this);" class="file_control btn-add-photo" data-limit="10" data-name="houseTypePhotos'+num+'" '+
						'capture="camera"></li>'+
				'</ul>'+
				'<div class="cor_9" style="display:inline-block">最多上传10张</div>'+
			'</div>'+
		'</li>'+
	'</ul>'+
    '<ul class="list-inline form-inline">'+
		'<li>'+
		    '<div class="form-group">'+
		        '<label class="fw-normal w100 text-right" style="vertical-align: top;">样板间：</label>'+
				'<ul class="list_photo_uploader" style="display:inline-block">'+
					'<li class="item_photo_uploader item_photo_uploader_add"><s class="icon s_add_btn"></s> <input '+
						'type="file" onchange="addPhoto(this);" class="file_control btn-add-photo" data-limit="10" data-name="templetPhotos'+temNum+'" '+
						'capture="camera"></li>'+
				'</ul>'+
				'<div class="cor_9" style="display:inline-block">最多上传10张</div>'+
			'</div>'+
		'</li>'+
	'</ul>'
	);
	//绑定全局框事件
	 Validator.onblur($(document));
	//判断是否需要隐藏
	 $(".houseType").show();
	 $("[name = mgtKbnCB]:checkbox").each(function () {
	        if ($(this).is(":checked")) {
	            var optionVal = $(this).val();
	            if("13405" == optionVal){
	            	//物业类型为商铺
	            	$(".houseType").hide();
	            }
	        }
	    });
}

//删除在售户型
function delSellingHouseType(data,num){
	var uploadPhotoId = "uploadPhotoId"+num;
	var uploadPhotoId2 = "uploadPhotoId"+num+"1";
	//清空图片id集合
	$("input[name="+uploadPhotoId+"]").attr("value","");
	$("input[name="+uploadPhotoId2+"]").attr("value","");
	
	$(data).closest("ul").next().next().next().next().remove();
	$(data).closest("ul").next().next().next().remove();
	$(data).closest("ul").next().next().remove();
	$(data).closest("ul").next().remove();
	$(data).closest("ul").remove();
	//绑定全局框事件
	 Validator.onblur($(document));
}

function reportAdd() {
	systemLoading("", true);
	var centerGroupId = $("#achieveCenter").find("option:selected").val();
	var centerGroupName = $("#achieveCenter").find("option:selected").text();
	$("#centerGroupId").val(centerGroupId);
	$("#centerGroupName").val(centerGroupName);
	
	if($("#estateId").val()==undefined || $("#estateId").val()==null || $("#estateId").val()=="" 
		|| $("#estateNm").val()==undefined || $("#estateNm").val()==null || $("#estateNm").val()==""){
		Dialog.alertError("请选择项目！");
		systemLoaded();
		return false;
	}
	if($("#reportCompanyId").val()==undefined || $("#reportCompanyId").val() == null  || $("#reportCompanyId").val() == ""){
		Dialog.alertError("请选择公司！");
		systemLoaded();
		return false;
	}
	if($("#storeNm").val()==undefined || $("#storeNm").val()==null || $("#storeNm").val()==""){
		Dialog.alertError("请选择门店！");
		systemLoaded();
		return false;
	}
	if($("#centerGroupId").val()==undefined || $("#centerGroupId").val()==null || $("#centerGroupId").val()==""){
		Dialog.alertError("请选择业绩归属中心！");
		systemLoaded();
		return false;
	}
	
	if($("#customerName").val() !="") {
		$("#customerName").val($.trim($("#customerName").val()));
		var len = 0;
		var val = $("#customerName").val();
	    for (var i=0; i<val.length; i++) {
	        if (val.charCodeAt(i)>127 || val.charCodeAt(i)==94) {
	             len += 2;
	         } else {
	             len ++;
	         }
	    }
	    if (len > 60) {
	    	Dialog.alertError("客户姓名过长，不能超过60个字符！");
			systemLoaded();
			return false;
	    }
	}
	
	if($("#reportAgent").val() !="") {
		$("#reportAgent").val($.trim($("#reportAgent").val()));
		var len = 0;
		var val = $("#reportAgent").val();
	    for (var i=0; i<val.length; i++) {
	        if (val.charCodeAt(i)>127 || val.charCodeAt(i)==94) {
	             len += 2;
	         } else {
	             len ++;
	         }
	    }
	    if (len > 20) {
	    	Dialog.alertError("报备经纪人过长，不能超过20个字符！");
			systemLoaded();
			return false;
	    }
	}
	
	if($("#customerNmTwo").val() !="") {
		$("#customerNmTwo").val($.trim($("#customerNmTwo").val()));
		var len = 0;
		var val = $("#customerNmTwo").val();
	    for (var i=0; i<val.length; i++) {
	        if (val.charCodeAt(i)>127 || val.charCodeAt(i)==94) {
	             len += 2;
	         } else {
	             len ++;
	         }
	    }
	    if (len > 60) {
	    	Dialog.alertError("客户姓名过长，不能超过60个字符！");
			systemLoaded();
			return false;
	    }
	}
	
	if($("#customerNmTwo").val() !="") {
	    if ($("#customerTelTwo").val()==undefined || $("#customerTelTwo").val()==null || $("#customerTelTwo").val()=='') {
	    	Dialog.alertError("客户已填写，客户手机必须填写！");
			systemLoaded();
			$("#customerTelTwo").focus();
			return false;
	    }
	}
	if($("#customerTelTwo").val() !="") {
		if ($("#customerNmTwo").val()==undefined || $("#customerNmTwo").val()==null || $("#customerNmTwo").val()=='') {
			Dialog.alertError("客户手机已填写，客户必须填写！");
			systemLoaded();
			$("#customerNmTwo").focus();
			return false;
		}
	}
	
	if ($("#reportDate").val() != null && $("#reportDate").val() != "" 
			&& $("#validRelationDate").val() != null && $("#validRelationDate").val() != ""
			&& compareTo($("#reportDate").val(), $("#validRelationDate").val()) < 0) {
		Dialog.alertError("带看时间必须大于报备日期！");
		systemLoaded();
		return false;
	}

	if(!handlerFileInfo()){
		return false;
	}
	
	var url = BASE_PATH + '/estate/report';
	$("#reportPcAddBtn").hide();
	// 校验输入信息
	if (Validator.validForm("reportAddForm")) {
		systemLoading("", true);
		httpPost('reportAddForm', url, function(data) {
			//location.href = BASE_PATH + "/estate";
			$("#reportCompanyId").val("");
			$("#reportCompanyNm").val("");
			$("#storeNm").val("");
			$("#storeNm").empty();
			$("#customerName").val("");
			$("#customerPhone").val("");
			$("#validRelationDate").val("");
			$("#reportDate").val("");
			$("#customerNum").val("");
			$("#tmpContactId").val("");
            $("#tmpContactNm").val("");
            
            $("#centerGroupId").val("");
            $("#centerGroupName").val("");
            $("#achieveCenter").val("");
            $("#achieveCenter").empty();
            
            $("#reportAgent").val("");
			$("#reportAgentTel").val("");
			$("#customerNmTwo").val("");
            $("#customerTelTwo").val("");
            var imgLength = $("#thumbXsBox>div").length;
            $("#thumbXsBox>div").each(function (index,item) {
				if(imgLength - 1 != index){
					$(item).remove();
				}
            });
            $("#fileRecordMainIds").val("");

        	$("#reportPcAddBtn").show();
			Dialog.alertInfo("报备成功！");
		}, function(data) {

			$("#reportPcAddBtn").show();
			var msg =data.returnMsg;
			if(!msg){
				msg = "报备失败";
			}
			Dialog.alertError(msg);
			systemLoaded();
		});
		systemLoaded();
	} else {
		systemLoaded();
	}

};
function handlerFileInfo(){
    var bol = true;
    var fileRecordMainIds = "";
    $("input[name=fileRecordMainIdHidden]").each(function(){
        if($(this).val()==""){
            Dialog.alertError("图片上传出现异常，请删除重新上传。");
            bol = false;
            return false;
        }
        fileRecordMainIds += ","+$(this).val();
    })
    if(fileRecordMainIds!=""){
        fileRecordMainIds = fileRecordMainIds.substring(1);
    }
    $("#fileRecordMainIds").val(fileRecordMainIds);

    return bol;
}

function compareTo(start, end) {  
	var beginTime = start;  
    var endTime    = end;  
    var beginTimes = beginTime.substring(0,10).split('-');  
    var endTimes   =  endTime.substring(0,10).split('-');  

    beginTime = beginTimes[1]+'-'+beginTimes[2]+'-'+beginTimes[0]+' '+beginTime.substring(10,19);  
    endTime    = endTimes[1]+'-'+endTimes[2]+'-'+endTimes[0]+' '+endTime.substring(10,19);  
    var a =(Date.parse(endTime)-Date.parse(beginTime))/3600/1000;   
    if(a<0){
        return -1;
    }else if (a>0){
    	return 1;
    }else if (a==0){
    	return 0; 
    }
}

LinkAchieveChange = function () {
    dialog: null;
};
LinkAchieveChange.close = function () {

    LinkAchieveChange.dialog.close();
};
LinkAchieveChange2 = function () {
	dialog: null;
};
LinkAchieveChange2.close = function () {
	
	LinkAchieveChange2.dialog.close();
};
/**
 * 选择业绩归属人弹窗
 */
function achieveMentChange(){
    var url = BASE_PATH + '/linkAchieveChange/linkUser';
    var title = '选择变更后业绩归属人';
    
    if(!$("#reportCompanyNm").val() || !$("#storeNm").val()){
    	Dialog.alertInfo("请先选择公司和门店");
    	return ;
    }

    var params = {
    };
    var dialogOptions = {
        width : 800,
        ok : function(data) {
        	var selectRadio = $("input.selectCenterId:checked");
            var selectCenterId = selectRadio.val();
            if(isBlank(selectCenterId)){
                $("#errorMsg").text("请先选择一个业绩归属人!");
                $("#errorMsg").css("visibility","initial");
                return false;
            }

            var userCode = selectRadio.parent().next().text();
            var userName = selectRadio.parent().next().next().text();
            $("#tmpContactId").val(userCode);
            $("#tmpContactNm").val(userName);
        },
        okVal : '确定',
        cancel : true,
        cancelVal : '取消'
    };

    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

        dialog.position('50%', '25%');
        LinkAchieveChange.dialog = dialog;

    }, dialogOptions);

}
function achieveMentChange2(){
    var url = BASE_PATH + '/linkAchieveChange/toLinkUserList2';
    var title = '选择变更后业绩归属人';
    
    if(!$("#reportCompanyNm").val() || !$("#storeNm").val()){
    	Dialog.alertInfo("请先选择公司和门店");
    	return ;
    }

    var params = {
    };
    var dialogOptions = {
        width : 600,
        ok : function(data) {
        	var selectRadio = $("input.selectCenterId:checked");
            var selectUser = selectRadio.val();
            if(isBlank(selectUser)){
                $("#errorMsg").text("请先选择一个业绩归属人!");
                $("#errorMsg").css("visibility","initial");
                return false;
            }
            var userCode = $(':radio[name="selectUser"]:checked').attr("data-userCode");
            var userName = $(':radio[name="selectUser"]:checked').attr("data-userName");
            $('#tmpContactId').val(userCode);
            $("#tmpContactNm").val(userName);
            $('#tmpContactId').blur();
        },
        okVal : '确定',
        cancel : true,
        cancelVal : '取消'
    };

    Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

        dialog.position('50%', '25%');
        LinkAchieveChange2.dialog = dialog;

    }, dialogOptions);

}
//显示字体变化
function openTimeChange(obj){
	var openTime = $(obj).val();
	/*$("#opentimeShow").html('<i>*</i>');*/
	if(openTime==1441){
		$("#opentimeShow").removeClass("w130");
		$("#opentimeShow").addClass("w100");
		$("#opentimeShow").html('开盘日期：');
		
	}
	if(openTime==1442){
		$("#opentimeShow").removeClass("w100");
		$("#opentimeShow").addClass("w130");
		$("#opentimeShow").html('预计开盘日期：');
		
	}
};

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
//例子：
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.format = function (fmt) { //author: meizz
	 var o = {
	     "M+": this.getMonth() + 1, //月份
	     "d+": this.getDate(), //日
	     "h+": this.getHours(), //小时
	     "m+": this.getMinutes(), //分
	     "s+": this.getSeconds(), //秒
	     "q+": Math.floor((this.getMonth() + 3) / 3), //季度
	     "S": this.getMilliseconds() //毫秒
	 };
	 if (/(y+)/.test(fmt))
	     fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	 for (var k in o)
	     if (new RegExp("(" + k + ")").test(fmt))
	         fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	 return fmt;
};

EstateAdd = function() {
};

/**
 * 初始化查询
 */
EstateAdd.initList = function() {

	httpGet('selectFromOpForm', 'LoadCxt', BASE_PATH + '/estate/selectFromOp/q', function() {

		pageInfo("selectFromOpForm", function() {
			EstateAdd.initList();
		});

	});
};

/**
 * 查询
 * 
 */
EstateAdd.search = function() {
	$('#curPage').val('1');
	EstateAdd.initList();
};

EstateAdd.selectFromOp = function() {
	var url = BASE_PATH + '/estate/selectFromOp';
	var title = '关联项目';
	
	var params = {estatePosition:$("input[name='estatePosition']:checked").val()};

	var dialogOptions = {
		width : 800,
		ok : function() {
			EstateAdd.dealVal();
		},
		okVal : '确定',
		cancel : true,
		cancelVal : '取消'
	};

	Dialog.ajaxOpenDialog(url, params, title, function(dialog, resData) {

		dialog.position('50%', '25%');

		EstateAdd.dialog = dialog;

	}, dialogOptions);
};
EstateAdd.close = function() {
	EstateAdd.dialog.close();
};
EstateAdd.dealVal=function(){
	var selected = $("input[name='estateId']:checked");
	if(selected.length == 0){
		return ;
	}
	var opEstateId = selected.val();
	var opEstateNm = selected.data("estatenm");
	
	var checkUrl = BASE_PATH + '/estate/selectFromOpCheck';
	ajaxGet(checkUrl,{"opEstateId":opEstateId},function(data){
		$("#opEstateId").val(opEstateId);
		$("#opEstateNm").val(opEstateNm);
		$("#estateNm").val(opEstateNm);

		//国家
		var countryNm=selected.data("countrynm");
		if($("input[name='estatePosition']:checked").val() == "1"){
            $("#countryNo").find("option[countryNm="+countryNm+"]").attr("selected",true);
		}
		
		//城市
		var cityNo = selected.data("cityno");
		if(cityNo != ""){
			$('.selectpicker').selectpicker('val',cityNo);
			//$("#realityCityNo").trigger("change");

			//此处需要同步加载
            var url = BASE_PATH + "/linkages/city/" + $("#realityCityNo").val();
            var params = {};

            $.ajax({
                url : url,
                data : params,
				async : false,
                type : 'GET',
                dataType : 'json',
                success : function(data) {
                    if (data && data.returnCode == '200') {
                        var result = "<option value=''>请选择行政区</option>";
                        $.each(data.returnValue, function(n, value) {
                            result += "<option value='" + value.districtNo + "'>"
                                + value.districtName + "</option>";
                        });
                        $("#districtNo").html('');
                        $("#districtNo").append(result);
                    }
                }
            });
		}
        //区域
        var districtId = selected.data("districtid");
        if(districtId != ""){
            $("#districtNo").find("option[value="+districtId+"]").attr("selected",true);
        }
		
		$("#address").val(selected.data("address"));
		//开盘日期
		var openTime = new Date();
		if(selected.data("opentime") != ""){
			openTime.setTime(selected.data("opentime"));
			
			var openTime2 = new Date();
			openTime2=new Date(selected.data("opentime"));
			$("#openTime").val(openTime2.format("yyyy-MM"));
		}

        //交房日期
        var houseTransferTime = new Date();
        if(selected.data("housetransfertime") != ""){
            houseTransferTime=new Date(selected.data("housetransfertime"));
            $("#houseTransferTime").val(houseTransferTime.format("yyyy-MM"));
        }

		//合作期
		var cooperationDtStart = new Date();
        if(selected.data("cooperationdtstart") != ""){
            cooperationDtStart.setTime(selected.data("cooperationdtstart"));
            $("#cooperationDtStart").val(cooperationDtStart.format("yyyy-MM-dd"));
        }
        var cooperationDtEnd = new Date();
        if(selected.data("cooperationdtend") != ""){
            cooperationDtEnd.setTime(selected.data("cooperationdtend"));
            $("#cooperationDtEnd").val(cooperationDtEnd.format("yyyy-MM-dd"));
        }

		//总价段
		$("#salePriceUnitMin").val(selected.data("salepriceunitmin"));
		$("#salePriceUnitMax").val(selected.data("salepriceunitmax"));
		//标签
		var markHtml = '<input type="text" class="form-control w125" name="mark[0]" placeholder="" maxlength="4" value="{markVal}">'
		    + '<span class="btn btn-link btn-add-input-tag" onclick="delMark(this)"> 删除</span>';
		$.each(selected.data("mark").split(";"),function(i,n){
			if(n == ""){
				return false;
			}
			if(i>1){
				var tempHtml = markHtml.replace(/mark\[0\]/g, "mark[" + i + "]").replace("{markVal}",n);
				$("#mark").parent().append(tempHtml);
			}else{
				$("#mark").val(n);
			}
		});
		//预售许可
		$("#preSalePermitKbn"+selected.data("presalepermitkbn")).attr("checked",true);
		//项目简介
		$("#projectDescription").val(selected.data("projectdescription"));
		
		/*//产权年限
		$.each(selected.data("ownyearkbnstr").split(";"),function(i,n){
			if(n == ""){
				return false;
			}
			$("input[ownYearNm='"+n+"']").attr("checked",true);
		});
		//装修情况，无法处理
		$("#decorationKbnCB").val(selected.data("decorationkbnstr"));
		//物业类型
		$("#typeKbnCB").val(selected.data("typekbnstr"));*/
		
		$("#houseCnt").val(selected.data("housecnt"));
		$("#parkCnt").val(selected.data("parkcnt"));
		$("#mgtCompany").val(selected.data("mgtcompany"));
		$("#rateFAR").val(selected.data("ratefar"));
		$("#rateGreen").val(selected.data("rategreen"));
		$("#mgtPrice").val(selected.data("mgtprice"));
		$("#"+selected.data("salesstatus")).attr("checked",true);

		//合作方
        $("#"+selected.data("partner")).attr("checked",true);
        $("#partnerNm").val(selected.data("partnernm"));
        $("#partnerContractNm").val(selected.data("partnercontractnm"));
        $("#partnerContractTel").val(selected.data("partnercontracttel"));
        //开发商
		$("#devCompany").val(selected.data("devcompany"));
		//选择否了devCompany 数据录入developerNameBigNo
		$("#developerNameBigNo").val(selected.data("devcompany"));
		//户型
		/*$("#countF").val(selected.data("countf"));
        $("#countT").val(selected.data("countt"));
        $("#countW").val(selected.data("countw"));
        $("#buildSquare").val(selected.data("buildsquare"));
        $("#"+selected.data("directionkbn")).attr("checked",true);*/
		if(selected.data("roomlen")>0){
            $("#countF").val(selected.data("0-countf"));
            $("#countT").val(selected.data("0-countt"));
            $("#countW").val(selected.data("0-countw"));
            $("#buildSquare").val(selected.data("0-buildsquare"));
            $("#"+selected.data("0-directionkbn")).attr("checked",true);
		}
	},function(data){
		$("#opEstateId").val("");
		$("#opEstateNm").val("");
		$("#estateNm").val("");
		Dialog.alertError(data.returnMsg);
		systemLoaded();
	})
};

EstateAdd.fnChangePosition=function(obj){
	window.location.href=BASE_PATH+"/estate/c?estatePosition="+$(obj).val();
}
function bigCustomerList(callbackCity, callbackDist){
	var url = BASE_PATH + "/estate/getBigCustomerList";
	var params = {};
	ajaxGet(url, params, function(data) {
		var result = "<option value=''>请选择</option>";
		$.each(data.returnValue, function(n, value) {
			result += "<option value='" + value.bigCustomerId + "'>"
					+ value.name + "</option>";
		});
		$("#selectDevCompany").html('');
		$("#selectDevCompany").append(result);
		
		if($('.selectpicker2').length > 0){
			$('.selectpicker2').selectpicker('val', '');  
	        $('.selectpicker2').selectpicker('refresh');
	        $('.selectpicker2').selectpicker('render');
		}
		callbackCity ? callbackCity() : $.noop();
	}, function() {
	});
	$("#selectDevCompany").change(function() {
		var selectDevCompanyNm = $("#selectDevCompany").find("option:selected").text();
		$("#devCompanyText").val(selectDevCompanyNm);
	});
}

/**
 * 获取垫佣甲方简称
 * @param callbackCity
 * @param callbackDist
 * @returns
 */
function getMattressNail(callbackCity, callbackDist){
	var url = BASE_PATH + "/estate/getMattressNail";
	var params = {};
	ajaxGet(url, params, function(data) {
		var result = "<option value=''>请选择</option>";
		$.each(data.returnValue, function(n, value) {
			result += "<option value='" + value.mattressNailId + "'>"
			+ value.name + "</option>";
		});
		$("#selectMattressNail").html('');
		$("#selectMattressNail").append(result);
		
		if($('.selectpicker3').length > 0){
			$('.selectpicker3').selectpicker('val', '');  
			$('.selectpicker3').selectpicker('refresh');
			$('.selectpicker3').selectpicker('render');
		}
		callbackCity ? callbackCity() : $.noop();
	}, function() {
	});
	$("#selectMattressNail").change(function() {
		var selectselectMattressNailNm = $("#selectMattressNail").find("option:selected").text();
		$("#mattressNailText").val(selectselectMattressNailNm);
	});
}
function isNullOrEmpty(obj){
	if(obj == null || obj == "" || obj == undefined){
		return false;
	}else{
		return true;
	}
}