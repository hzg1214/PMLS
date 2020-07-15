<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div id="leftMenu" class="col-md-2 aside">
	<ul>
		<li class="text-center" id="leftMenu110401"><a
			href="${ctx}/crm/index">拓店审核</a></li>
		<li class="text-center" id="leftMenu110404"><a
				href="${ctx}/storeAuthCheck">授牌验收审核</a></li>
		<li class="text-center" id="leftMenu110402"><a
           href="${ctx}/storeBizStop">停业上报审核</a></li>
		<li class="text-center" id="leftMenu110403"><a
				href="${ctx}/StoreStopCancel">停业撤销审核</a></li>
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
