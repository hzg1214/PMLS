<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/staffMaintain/yfCenterUserList.js?_v=${vs}"></script>
</head>

<body>
<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>

<div class="container theme-hipage ng-scope" role="main">
            <h4 class="border-bottom pdb10"><strong>人员维护</strong></h4>
    <div class="row">
    	<ul class="nav nav-tabs nav-tabs-header">
			<li role="presentation" class="active" >
                <a href="${ctx}/yfCenterUser">业务人员维护</a>
            </li>
            <li role="presentation">
                <a href="${ctx}/mCenterUser">管理人员维护</a>
            </li>
        </ul>
        <div class="page-content">
            <form id="yfCenterUserListForm">
                <input type="hidden" name="searchParmCache" value="1">
                <input type="hidden" id="curPageTemp" value="1">
                <input type="hidden" id="sysPageChaneTemp" value="1">

                <div class="panel panel-default" style="margin-bottom:10px;">
                    <div class="panel-body">
                        <div class="form-inline">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label style="margin-left:15px;">归属城市</label>
                                    <select class="form-control" title="" id="cityNo" name="cityNo"
                                            style="width:100px;">
                                        <option value="" selected="selected">请选择</option>
                                        <c:forEach items="${citylist}" var="city">
                                            <option value="${city.cityNo}">${city.cityName}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>中心</label>
                                    <select class="form-control" title="" id="centerId"
                                            name="centerId" style="width:100px;">
                                        <option value="" selected="selected">请选择</option>
                                        <c:if test="${!empty centerList}">
                                            <c:forEach items="${centerList}" var="center">
                                                <option value="${center.centerId}">${center.centerName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                    <input type="hidden" id="authCenterIds" name="authCenterIds" value="${authCenterIds}" />
                                </div>
                                <div class="form-group">
                                    <label>业务人员</label>
                                    <input type="text" class="form-control" id="searchKey" name="searchKey"
                                           placeholder="工号，姓名" value="">
                                </div>

                                <button type="button" class="btn btn-primary" id="J_search"
                                        onclick="javascript:yfCenterUser.search();">查询
                                </button>
                                <button type="button" id="btn-reset" class="btn btn-default"
                                        onclick="javascript:yfCenterUser.reset()">重置
                                </button>

                                <button type="button" class="btn btn-primary" id="J_export"
                                        style="margin-left:40px;"
                                        onclick="javascript:yfCenterUser.create();">人员维护
                                </button>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- 异步加载列表内容 -->
                <div id="load_content">
                    <div class="" style="height: auto;" id="LoadCxt"></div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var searchParamMap = '${searchParamMap}';
    console.log(searchParamMap);
    if (searchParamMap != undefined && searchParamMap != null && searchParamMap != '') {
        yfCenterUser.setSearchParams(searchParamMap);
    }
</script>
</html>


