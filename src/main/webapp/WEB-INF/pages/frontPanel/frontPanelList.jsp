<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"
	trimDirectiveWhitespaces="true" language="java"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<%@include file="/WEB-INF/pages/common/common.jsp"%>
<title>工作台</title>
<style>
.titleClass {
	border-bottom-color: #f6f6f6;
	text-align: right;
	padding-right: 300px;
}

.w200 {
	width: 200px;
	height:34px;
}

.shujuCss {
	position: absolute;
	left: 5px;
	z-index: 10;
	height: 40px;
	line-height: 40px;
}

.dateClass {
	position: absolute;
	right: 50px;
	top: 0;
	z-index: 10;
}

.weekDateClass {
	color: black;
	height: 38px;
	width: 182px;
}

.report {
	display: flex;
}

.report li:first-child {
	width: 28%;
}

.report li {
	width: 33%;
	border: 1px solid #dcdcdc;
	min-height: 50px;
	margin: 0 1%;
}

.report1 {
	overflow: hidden;
	width: 98%;
	margin: 0 auto;
}

.report1>div {
	float: left;
	width: 50%;
	text-align: center;
	padding: 20px 0;
}

.report2 {
	width: 98%;
	display: table;
	margin: 0 auto;
}

.report2>div {
	display: table-cell;
	text-align: center;
	padding: 20px 0;
	width: 33.33%;
}

.chart {
	width: 100%;
	text-align: left;
	margin-top: -30px;
}


</style>

</head>
<body>
<script type="application/javascript">

	function weekFunc(){
		//文本框显示值
		$('#week-date-show').val($dp.cal.getP('y')+'年第'+$dp.cal.getP('W','WW')+'周');
		console.log("week-date-show   "+$('#week-date-show').val());
		console.log("选择周的值   "+$dp.cal.getP('y')+"-"+$dp.cal.getP('W','WW'));
		$('#week-date-hidden').val($dp.cal.getP('y')+"-"+$dp.cal.getP('W','WW'));
		console.log("week-date-hidden   "+$('#week-date-hidden').val());
		//获取周数据 2016-01  表示2016年第一周
		getEchartData("week",$dp.cal.getP('y')+"-"+$dp.cal.getP('W','WW'));
	}
</script>

	<div>
		<div class="layui-card">
			<div class="layui-card-body">
				<div class="layui-tab layui-tab-brief" lay-filter="PCDataTab"
					style="position: relative;">

					<div class="shujuCss">
						<label style="font-size: 20px;"><b>数据</b></label>
					</div>
					<ul class="layui-tab-title titleClass">

						<li lay-id="weekTab" act-type="weekTab">周</li>
						<li lay-id="monthTab" act-type="monthTab" class="layui-this">月</li>
						<li lay-id="quarterTab" act-type="quarterTab">季</li>
						<li lay-id="yearTab" act-type="yearTab">年</li>
					</ul>
					<div class="w200 dateClass layui-hide"
						id="week-date-div">
						<div onclick="WdatePicker({el:'week-date',autoUpdateOnChanged:true,isShowClear:false,dateFmt:'yyyy-MM-dd',readOnly:true,isShowWeek:true,onpicked:weekFunc,firstDayOfWeek:1,errDealMode:-1})">
							<input id="week-date" name="week-date" type="hidden" /> 
							<input id="week-date-hidden" name="week-date-hidden" type="hidden" /> 
							<div>
								<input id="week-date-show" name="week-date-show" type="text" style="display: block;height:34px; width: 182px;">
							</div >
<!-- 							<div id="divSpace" style="float: right;width:50px;height:34px;margin-right: -20px;" >	 -->
<!-- 								<img src="../../meta/My97DatePicker/skin/datePicker.gif" width="16" height="22" style="margin-top:8px;" align="absmiddle"/> -->
<!-- 							</div> -->
						</div>	
					</div>
					<div class="layui-input-inline w90 dateClass layui-show"
						id="month-date-div">
						<input type="text" name="month-date" id="month-date"
							lay-verify="month-date" autocomplete="off"
							lay-filter="month-date" class="layui-input">
					</div>
					<div class="layui-input-inline w90 dateClass layui-hide"
						id="quarter-date-div">
						<input type="text" name="quarter-date" id="quarter-date"
							lay-verify="quarter-date" autocomplete="off"
							lay-filter="quarter-date" class="layui-input">
					</div>
					<div class="layui-input-inline w90 dateClass layui-hide"
						id="year-date-div">
						<input type="text" name="year-date" id="year-date"
							lay-verify="year-date" autocomplete="off" lay-filter="year-date"
							class="layui-input">
					</div>
					<div class="layui-tab-content" style="padding-top: 0px">
					</div>
				</div>
				<ul class="report">
					<li>
						<div class="report1">
							<div>
								<p id="bb_Num"></p>
								<p >
									报备数&nbsp;<i class="icon-explain" id="bbNum" onmouseover="show('bbNum','发起报备的数量')"></i>
								</p>
							</div>
							<div>
								<p id="dk_Num"></p>
								<p>
									带看数&nbsp;<i class="icon-explain" id="dkNum" onmouseover="show('dkNum','发起带看的数量')"></i>
								</p>
							</div>
							<div style="margin-top: 50px;">
								<p id="dd_Num"></p>
								<p >
									大定数&nbsp;<i class="icon-explain" id="ddNum" onmouseover="show('ddNum','发起的大定的数量，退定后大定数扣减')"></i>
								</p>
							</div>
							<div style="margin-top: 50px;">
								<p id="cx_Num"></p>
								<p >
									成销数&nbsp;<i class="icon-explain" id="cxNum" onmouseover="show('cxNum','确认成销的数量，退房后大定、成销数扣减')"></i>
								</p>
							</div>
						</div>
					</li>
					<li>
						<div class="report2">
							<div>
								<p id="yjsr_lj"></p>
								<p >
									应计收入(万)&nbsp;<i class="icon-explain" id="yjsr" onmouseover="show('yjsr','成销时间在筛选时间内的订单累计应计收入金额（税前）')"></i>
								</p>
							</div>
							<div>
								<p id="yjsr_ljhk"></p>
								<p>已回款(万)</p>
							</div>
							<div>
								<p id="yjsr_ljwhk"></p>
								<p>未回款(万)</p>
							</div>
						</div>
						<div class="chart">
							<div id="chartData1" style="width: 100%; height: 200px;"></div>
						</div>
					</li>
					<li>
						<div class="report2">
							<div>
								<p id="yssr_lj"></p>
								<p >
									应收收入(万)&nbsp;<i class="icon-explain" id="yssr" onmouseover="show('yssr','成销时间在筛选时间内的订单累计应收收入金额（税前）')"></i>
								</p>
							</div>
							<div>
								<p id="yssr_ljhk"></p>
								<p>已回款(万)</p>
							</div>
							<div>
								<p id="yssr_ljwhk"></p>
								<p>未回款(万)</p>
							</div>
						</div>
						<div class="chart">
							<div id="chartData2" style="width: 100%; height: 200px;"></div>
						</div>
					</li>
				</ul>
			</div>
		</div>

	</div>
	<script src="${ctx}/meta/pmls/js/frontPanel/frontPanelList.js?v=${vs}"></script>
</body>
</html>
