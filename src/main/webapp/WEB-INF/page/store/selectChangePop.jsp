<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<div class="" role="main" style="width: 400px;">
    <span class="" style="float:right">
        <a href="javascript:StoreLog.close();" class="btn btn-default">&times;</a>
    </span>

	<div class="row">
		<div class="page-content" style="clear: initial;">
			<h4><strong style="margin-left: 20px;">请选择合同变更类型</strong></h4>
			<ul>
				<li style="padding-left: 100px;">
					<input type="radio" id="changeType1" name="changeType" value="17002">
					<span style="padding-left: 20px;">乙类转甲类</span>
				</li>
				<br>
				<li style="padding-left: 100px;">
					<input type="radio" id="changeType2" name="changeType" value="17003">
					<span style="padding-left: 20px;">三方变更</span>
				</li>
				<br>
				<li style="padding-left: 100px;">
					<input type="radio" id="changeType3" name="changeType" value="17004">
					<span style="padding-left: 20px;">门店迁址</span>
				</li>
				<input type="hidden" id="hiddenInput" data-contractchange = "${contractchange}"
						data-iscancel="${iscancel}"
						data-storeid="${storeid}"
						data-contractid="${contractid}"
						data-contractstatus="${contractstatus}"
						data-abtypestore="${abtypestore}"
					   data-relocationstatus="${relocationstatus}"
					   data-partychangestatus="${partychangestatus}"
					   data-b2achangestatus="${b2achangestatus}"
				>
			</ul>
		</div>
		<div  id="errorMsg" >
              <span class="fc-warning"  style="margin-left: 30px;font-size:  12px;"></span>
        </div>
	</div>
</div>
<script type="application/javascript">
	$(function(){
		$("#errorMsg").find(".fc-warning").empty().html("");
        var data = $("#hiddenInput").data();
        if(data.abtypestore == 19901){
            $("#changeType1").attr("disabled","disabled");
		}

        if(data.relocationstatus == 1){
            $("#changeType3").attr("disabled","disabled");
        }

        if(data.partychangestatus == 1){
            $("#changeType2").attr("disabled","disabled");
        }

        if(data.b2achangestatus == 1){
            $("#changeType1").attr("disabled","disabled");
        }
	})
</script>
