<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div id="leftMenu" class="col-md-2 aside">
	<ul>
		<li class="text-center" id="leftMenu110401"><a
			href="${ctx}/contract/${contractId}/${contractStatus}">基本信息</a></li>
		<li class="text-center" id="leftMenu110404"><a
				href="${ctx}/contractChangeNew/list/${contractId}/${contractStatus}">合同变更</a></li>
		<li class="text-center" id="leftMenu110403"><a
			href="${ctx}/achievement/cancel/${contractId}/${contractStatus}">合同撤销</a></li>
		<li class="text-center" id="leftMenu110402"><a
			href="${ctx}/contract/changeRecord/${contractId}/${contractStatus}">合同终止</a></li>
	</ul>
</div>
<script type="text/javascript">
	var leftMenuSelectId = '${param.leftMenuSelectId}';
	$("#leftMenu>ul>li").each(function(index,item){
        $(item).click(function(){
            window.location = $($(this).children()[0]).attr("href");
        });
    });
</script>
