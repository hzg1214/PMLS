var laydate;
var form;
var layer;
layui.use(['element', 'laydate', 'form', 'table', 'layer'], function () {
    var element = layui.element,
        table = layui.table,
        formSelects = layui.formSelects,
        $ = layui.$;
    layer = layui.layer;
    laydate = layui.laydate;
    form = layui.form;
    
    init();
    
    function init() {
    	initDateToMonth();
    	dateIndex = getCurrDateToMonth();
    	$('#month-date').val(dateIndex);
    	getEchartData('month',getCurrDateToMonth());
    }

    /**
     * 切换周、月、季、年发送ajax获取数据
     */
    element.on("tab(PCDataTab)", function (data) {
        var dateType = "week";
        if (data.index == 0) {//周
        	dateType = "week";
        	$('#week-date-div').removeClass('layui-hide');
        	$('#week-date-div').addClass('layui-show');
        	$('#month-date-div').addClass('layui-hide');
        	$('#quarter-date-div').addClass('layui-hide');
        	$('#year-date-div').addClass('layui-hide');
//        	var dateIndex = $('#week-date-hidden').val();
        	var weekDate = $('#week-date-show').val();
        	var dateIndex;
        	if(!isNull(weekDate) ){//有值 2020年第53周
        		dateIndex = weekDate.slice(0,4) +'-'+ weekDate.slice(6,8);
        		getEchartData(dateType,dateIndex);
        	}else{
        		//默认为当前时间，获取当前时间在本年第几周 2020-15
        		dateIndex = getCurrDateToWeek();
        		var nowDate=dateIndex.slice(0,4)+'年第'+dateIndex.slice(5,7)+'周';
        		$('#week-date-show').val(nowDate);
        		getEchartData(dateType,dateIndex);
        	}
        }else if (data.index == 1) {//月
        	dateType = "month";
        	$('#month-date-div').removeClass('layui-hide');
        	$('#month-date-div').addClass('layui-show');
        	$('#week-date-div').addClass('layui-hide');
        	$('#quarter-date-div').addClass('layui-hide');
        	$('#year-date-div').addClass('layui-hide');
        	var dateIndex = $('#month-date').val();
            initDateToMonth();
            console.log("month-date   "+dateIndex);
            if(!isNull(dateIndex)){//有值
            	getEchartData(dateType,dateIndex);
            }else{
            	//默认为当前时间，获取当前时间在本年月份 2020-04
            	dateIndex = getCurrDateToMonth();
//            	var nowDate=dateIndex.slice(0,4)+'年第'+dateIndex.slice(5,7);
            	$('#month-date').val(dateIndex);
            	getEchartData(dateType,dateIndex);
            }
            
        }else if (data.index == 2) {//季
        	dateType = "quarter";
        	$('#quarter-date-div').removeClass('layui-hide');
        	$('#quarter-date-div').addClass('layui-show');
        	$('#week-date-div').addClass('layui-hide');
        	$('#month-date-div').addClass('layui-hide');
        	$('#year-date-div').addClass('layui-hide');
        	var quarterDate = $('#quarter-date').val();
        	var dateIndex;
        	//初始化季度日期控件
        	renderSeasonDate(document.getElementById('quarter-date'), 1);
        	if(!isNull(quarterDate)){//有值
        		dateIndex = quarterDate.slice(0,4) +'-0'+ quarterDate.slice(5,6);
            	getEchartData(dateType,dateIndex);
            }else{//默认当前季度
            	dateIndex = getCurrDateToQuarter();
            	var detQuarter = getDefaultQuarter();
            	$('#quarter-date').val(detQuarter);
            	getEchartData(dateType,dateIndex);
            }
        }else if (data.index == 3) {//年
        	dateType = "year";
        	$('#year-date-div').removeClass('layui-hide');
        	$('#year-date-div').addClass('layui-show');
        	$('#week-date-div').addClass('layui-hide');
        	$('#month-date-div').addClass('layui-hide');
        	$('#quarter-date-div').addClass('layui-hide');
        	initDateToYear();
        	var yearDate = $('#year-date').val();
        	if(!isNull(yearDate)){//有值
        		var dateIndex = yearDate.slice(0,4);
            	getEchartData(dateType,dateIndex);
            }else{//默认当前年
            	$('#year-date').val(new Date().getFullYear()+'年');
            	getEchartData(dateType,new Date().getFullYear());
            }
        }
    });
    
});

/**
 * 获取当当前事件的默认季度
 * @returns
 */
function getDefaultQuarter(){
	var now = new Date();
	var nowYear = now.getFullYear(); 
	var nowMonth = now.getMonth()+1; //当前月 
	var detQuarter;
	if(nowMonth > 0 && nowMonth<4){
		detQuarter = nowYear+'年1季度';
	}else if(nowMonth > 3 && nowMonth<7){
		detQuarter = nowYear+'年2季度';
	}else if(nowMonth > 6 && nowMonth<10){
		detQuarter = nowYear+'年3季度';
	}else if(nowMonth > 9){
		detQuarter = nowYear+'年4季度';
	}
	return detQuarter;
}

//初始化-月
function initDateToMonth(){
	laydate.render({
		elem: '#month-date',
		btns:['now','confirm'],
		type: 'month',
		format: 'yyyy-MM',
		trigger: 'click',
//		value:new Date(),
		done: function (value, date) {
			//选择月之后触发事件产生数据
			if(JSON.stringify(date) !== '{}'){
				
				var year = date.year;
				var month = date.month;
				var dateIndex;
				if(month<10){
					dateIndex= year + "-0" + month;
				}else{
					dateIndex= year + "-" + month;
				}
				getEchartData('month',dateIndex);
			}else{
				getEchartData('month');
				
			}
		}
	});
}

//初始化-年
function initDateToYear(){
    laydate.render({
    	elem: '#year-date',
    	type: 'year',
    	format: 'yyyy年',
    	btns:['now','confirm'],
    	trigger: 'click',
//    	value: new Date().getFullYear()+'年',
    	max: getNowFormatDate(),
    	done: function (value, date) {
    		//选择年之后触发事件产生数据
    		getEchartData('year',date.year);
    	}
    });
}

//年份-日期check
function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	+ " " + date.getHours() + seperator2 + date.getMinutes()
	+ seperator2 + date.getSeconds();
	return currentdate;
}

/** 
 * 初始化-季度
 * @param ohd 季度input dom对象非jquery对象 
 * @param sgl 有值单个，无值默认范围
 */
function renderSeasonDate(ohd, sgl) {
	var ele = $(ohd);
	var maxDate =  new Date()+"";
	laydate.render({
		elem: ohd,
		type: 'month',
		format: 'yyyy年M季度',
		range: sgl ? null : '~',
				min: "2016-1-1",
//				max: maxDate,
				btns: ['confirm'],
				ready: function (value, date, endDate) {
					var hd = $("#layui-laydate" + ele.attr("lay-key"));
					if (hd.length > 0) {
						hd.click(function () {
							ren($(this));
						});
					}
					ren(hd);
				},
				done: function (value, date) {
					if(JSON.stringify(date) !== '{}'){
						var year =date.year;
						var quarter = date.month;
						var dateIndex = year+"-0"+quarter;
						getEchartData('quarter',dateIndex);
					}else{
						getEchartData('quarter');
						
					}
				}
	});
	var ren = function (thiz) {
		var mls = thiz.find(".laydate-month-list");
		mls.each(function (i, e) {
			$(this).find("li").each(function (inx, ele) {
				var cx = ele.innerHTML;
				if (inx < 4) {
					ele.innerHTML = cx.replace(/月/g, "季度");
				} else {
					ele.style.display = "none";
				}
			});
		});
	}
}


function isNull(s) {
    if (s == null || typeof (s) == "undefined" || s == "") return true;
    return false;
}

//获取当前时间为本年第几周
function getCurrDateToWeek(){
	var today = new Date();
	var firstDay = new Date(today.getFullYear(), 0, 1);
	var dayOfWeek = firstDay.getDay();
	var spendDay = 1;
	if (dayOfWeek != 0) {
		spendDay = 7 - dayOfWeek + 1;
	}
	firstDay = new Date(today.getFullYear(), 0, 1 + spendDay);
	var d = Math.ceil((today.valueOf() - firstDay.valueOf()) / 86400000);
	var result = Math.ceil(d / 7)+1;
	return spiltDate(result);
}

//获取当前时间为本年第几月
function getCurrDateToMonth(){
	var today = new Date();
	var result = today.getMonth()+1;
	return spiltDate(result);
}

//获取当前时间为本年第几季度
function getCurrDateToQuarter(){
	var today = new Date();
	var result = today.getMonth()+1;
	if(result > 0 && result<4){//第一季度
		result=1;
	}else if(result > 3 && result<7){//第二季度
		result=2;
	}else if(result > 6 && result<10){//第三季度
		result=3;
	}else if(result > 9 ){//第四季度
		result=4;
	}
	return spiltDate(result);
}

//拼接
function spiltDate(result){
	var today = new Date();
	if(result<10){
		return today.getFullYear() + "-0" + result;
	}else{
		return today.getFullYear() + "-" + result;
	}
}

//获取饼图数据
function  getEchartData(dateType,dateIndex) {
    $.ajax({
        type: 'post',
        dataType: 'text',
        url: ctx + '/frontPanel/getFrontPanelData',
        data: {dateType:dateType,dateIndex:dateIndex},
        cache: false,
        async: true,
        success: function (data) {
        	var data = eval('(' + data + ')');
        	console.log('data:'+data);
            if (data.returnCode == -1){
                parent.layer.alert(data.returnMsg, {icon: 2, title:'提示'});
            } else {
	        	var frontPanelDto = data.returnData;
	        	//页面数据
	        	setHtmlData(frontPanelDto);
	        	//数据1
	            var data1 = getData1(frontPanelDto);
	            //数据2
	            var data2 = getData2(frontPanelDto);
	            //饼图1
	            getchartData1(data1);
	            //饼图2
	            getchartData2(data2);
            }
        }
    });
}

function setHtmlData(frontPanelDto){
	$('#bb_Num').html(frontPanelDto.bb_Num);//报备
	$('#dk_Num').html(frontPanelDto.dk_Num);//带看
	$('#dd_Num').html(frontPanelDto.dd_Num);//大定
	$('#cx_Num').html(frontPanelDto.cx_Num);//成销
	
	$('#yjsr_lj').html(frontPanelDto.yjsr_lj);//应计收入（累计）      例如：100，表示100万
	$('#yjsr_ljhk').html(frontPanelDto.yjsr_ljhk);//应计收入（累计回款）
	$('#yjsr_ljwhk').html(frontPanelDto.yjsr_ljwhk);//应计收入（累计未回款）
	
	$('#yssr_lj').html(frontPanelDto.yssr_lj);//应收收入（累计）
	$('#yssr_ljhk').html(frontPanelDto.yssr_ljhk);//应收收入（累计回款）
	$('#yssr_ljwhk').html(frontPanelDto.yssr_ljwhk);//应收收入（累计未回款）
}

/**
 * 获取饼图1数据
 * @param frontPanelDto
 * @returns
 */
function getData1(frontPanelDto){
	var data1 = new Array();
    var yjsr_ljhk = frontPanelDto.yjsr_ljhk;
    var yjsrFlag = frontPanelDto.yjsrFlag;
    var yjsr_ljwhk = frontPanelDto.yjsr_ljwhk;
    if(yjsrFlag){
    	yjsr_ljwhk = 0;
    }
    data1[0] = {value:yjsr_ljhk,name:"已回款"};
    data1[1] = {value:yjsr_ljwhk,name:"未回款"};
    return data1;
}

/**
 * 获取饼图2数据
 * @param frontPanelDto
 * @returns
 */
function getData2(frontPanelDto){
	var data2 = new Array();
	var yssr_ljhk = frontPanelDto.yssr_ljhk;
    var yssr_ljwhk = frontPanelDto.yssr_ljwhk;
    var yssrFlag = frontPanelDto.yssrFlag;
    if(yssrFlag){
    	yssr_ljwhk = 0;
    }
    data2[0] = {value:yssr_ljhk,name:"已回款"};
    data2[1] = {value:yssr_ljwhk,name:"未回款"};
	return data2;
}

/**
 * 获取第一个饼图数据
 * @returns
 */
function getchartData1(data1){
	
	var myChart1 = echarts.init(document.getElementById('chartData1')); 

    // 指定图表的配置项和数据
    option1 = {
//        title : {
//            text: '某站点用户消费积分',
//            subtext: '纯属虚构',
//            x:'center'
//        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        color: ['rgba(246, 93, 88, 1)', 'rgba(134, 190, 255, 1)'],
     // 重写legend显示样式
        legend: {
            orient: 'vertical', // 布局方式，默认为水平布局，可选为：'horizontal' ¦ 'vertical'
             // 水平安放位置，默认为左侧，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
//            x: 'left',
            // 垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center' | {number}（y坐标，单位px）
//            y: 'center',
            x : '5%',
            y : '50%',
            // 重写legend显示样式
            formatter: function(name) {
                  // 获取legend显示内容
                  let data = option1.series[0].data;
                  let total = 0;
                  let tarValue = 0;
                  for (let i = 0, l = data.length; i < l; i++) {
                      total += data[i].value;
                      if (data[i].name == name) {
                          tarValue = data[i].value;
                      }
                  }
                  let p;
                  if(total==0){
                	  p=0;
                  }else{
                	  p = (tarValue / total * 100).toFixed(2);
                  }
                  return name + ' ' + ' ' + p + '%';
              },
            data: data1
        },
        series : [
            {
                name: '回款统计',
                type: 'pie',
                radius : '55%',
                center: ['70%', '60%'],
                data:data1,
                itemStyle: {
                	normal : {
                        label : {
                          show : false
                        },
                        labelLine : {
                          show : false
                        }
                	},
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    return myChart1.setOption(option1);
}

/**
 * 获取第二个饼图数据
 * @returns
 */
function getchartData2(data2){
	var myChart2 = echarts.init(document.getElementById('chartData2')); 

    // 指定图表的配置项和数据
    option2 = {
//        title : {
//            text: '某站点用户消费积分',
//            subtext: '纯属虚构',
//            x:'center'
//        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        color: ['rgba(246, 93, 88, 1)', 'rgba(134, 190, 255, 1)'],
        legend: {
            orient: 'vertical', // 布局方式，默认为水平布局，可选为：'horizontal' ¦ 'vertical'
             // 水平安放位置，默认为左侧，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
//            x: 'right',
            // 垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center' | {number}（y坐标，单位px）
//            y: 'top',
            x : '5%',
            y : '50%',
            // 重写legend显示样式
            formatter: function(name) {
                  // 获取legend显示内容
                  let data = option2.series[0].data;
                  let total = 0;
                  let tarValue = 0;
                  for (let i = 0, l = data.length; i < l; i++) {
                      total += data[i].value;
                      if (data[i].name == name) {
                          tarValue = data[i].value;
                      }
                  }
                  let p;
                  if(total==0){
                	  p=0;
                  }else{
                	  p = (tarValue / total * 100).toFixed(2);
                  }
                  return name + ' ' + ' ' + p + '%';
              },
            data: data2
        }
        ,
        series : [
            {
                name: '回款统计',
                type: 'pie',
                radius : '55%',
                center: ['70%', '60%'],
                data:data2,
                itemStyle: {
                	normal : {
                        label : {
                          show : false
                        },
                        labelLine : {
                          show : false
                        }
                	},
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    return myChart2.setOption(option2);
}

var tipsi;
function show(id, tipMsg) {
	$("#"+id).hover(function() {
		tipsi = layer.tips(tipMsg, "#" + id + "",
//			{time : 0},
		{tips: [3, "#4794ec"]});
	}, function() {
		layer.close(tipsi);
	});
}
