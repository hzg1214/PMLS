<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

<div id="leftMenu" class="col-md-2 aside">
	<ul>
		<li class="text-center" id="leftMenu110401"><a
			href="${ctx}/store/${storeId}">基本信息</a></li>
		<li class="text-center" id="leftMenu110405"><a
			href="${ctx}/contacts/store/${storeId}/${userInfo.userId}">联系人</a></li>
		<li class="text-center" id="leftMenu110404"><a
			href="${ctx}/store/follow/${storeId}">跟进</a></li>
		<li class="text-center" id="leftMenu110402"><a
			href="${ctx}/store/contract/${storeId}">门店签约</a></li>
		<li class="text-center" id="leftMenu110409"><a
			href="${ctx}/fangyouAccount/toFangyouAccountView/${storeId}">房友账号</a></li>
		<li class="text-center" id="leftMenu110403"><a
			href="${ctx}/storeMaintainer/maintainerHis/${storeId}">门店维护人</a></li>
		<li class="text-center" id="leftMenu110406"><a
			href="${ctx}/storeDecoration/toView/${storeId}">门店装修</a></li>
        <li class="text-center" id="leftMenu110410"><a
                href="${ctx}/keFuOrder/getKeFuOrderListByStoreId/${storeId}">客服督导</a></li>
        <li class="text-center" id="leftMenu110407"><a
                href="${ctx}/store/toServiceFrameContract/${storeId}">交易服务框架协议</a></li>
        <li class="text-center" id="leftMenu110408"><a
                href="${ctx}/store/log/${storeId}">修改日志</a></li>
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
