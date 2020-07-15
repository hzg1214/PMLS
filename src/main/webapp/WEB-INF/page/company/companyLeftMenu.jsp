<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

  <div id="leftMenu" class="col-md-2 aside">
                <ul>
                    <li class="text-center" id="leftMenu110401"><a href="${ctx}/companys/${companyId}">基本信息</a></li>
<%--                     <li class="text-center" id="leftMenu110402"><a href="${ctx}/contacts/company/${companyId}/${userCreate}">联系人列表</a></li> --%>
                    <li class="text-center" id="leftMenu110403"><a href="${ctx}/companys/store/${companyId}/${userCreate}">关联门店</a></li>
<%--                     <li class="text-center" id="leftMenu110404"><a href="${ctx}/companys/follow/${companyId}/${userCreate}">跟进列表</a></li> --%>
                    <li class="text-center" id="leftMenu110405"><a href="${ctx}/companys/contract/${companyId}/${userCreate}">合同</a></li>
                    <li class="text-center" id="leftMenu110406"><a href="${ctx}/fangyou/account/${companyId}/${userCreate}">房友账号</a></li>                    
                    <li class="text-center" id="leftMenu110408"><a href="${ctx}/companys/gpstore/${companyId}/${userCreate}">关联公盘门店</a></li>
                    <li class="text-center" id="leftMenu110407"><a href="${ctx}/companys/log/${companyId}/${userCreate}">修改日志</a></li>
                </ul>
  </div>


<script type="text/javascript">
<!--
	var leftMenuSelectId = '${param.leftMenuSelectId}';
	$("#leftMenu>ul>li").each(function(index,item){
	    $(item).click(function(){
	        window.location = $($(this).children()[0]).attr("href");
	    });
	});
//-->
</script>
