<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
<%--     <%@ include file="/WEB-INF/page/common/jsCssLnk.jsp"%> --%>
    <script type="text/javascript" src="${ctx}/meta/js/report/commonReport.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/storeinformation/storeInformationDetail.js?_v=${vs}"></script>
    <script type="text/javascript" src="${ctx}/meta/js/common/DatePicker/WdatePicker.js?_v=${vs}"></script>
    <style type="text/css">
        .form-inline {
            overflow: inherit;
        }
        
        .text-overflow{
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
			width:100%;
		}
    </style>
</head>

<body>

<!-- 头部 -->
<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
    <jsp:param value="${headMenuIdSelect}" name="navSelectId"/>
</jsp:include>


<div class="container theme-hipage ng-scope" role="main" id="contentAll">
    <div class="row">
        <div class="page-content">
            <h4 class="border-bottom pdb10"><strong>门店信息明细表</strong></h4>
            <form id="storeInformationDetailListForm">

                <div class="panel panel-default" style="width: 1170px;">
                    <div class="panel-body">
                        <div class="form-inline">
                        <div>
                        	<div class="form-group" id="city" style="padding-left: 40px;">
								<label >归属城市&nbsp;</label>
								<div class="multi-select" id="cityId" name="cityId">
									<input type="hidden" id="cityCode" class="multi-select-value" readonly="readonly">
									<input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
										   style="width: 180px;">
									<ul class="multi-select-list">
										<li class="multi-select-item">
											<label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
										</li>
									</ul>
								</div>
							</div>
							<div class="form-group" id="center" style="padding-left: 120px;">
								<label id="centerLabel" style="padding-left:10px;">所属中心&nbsp;</label>
								<div class="multi-select" id="centerId" name="centerId">
									<input type="hidden" class="multi-select-value" readonly="readonly">
									<input type="text" class="multi-select-text" readonly="readonly" placeholder="请选择"
										   style="width: 180px;">
									<ul class="multi-select-list">
										<li class="multi-select-item">
											<label><input type="checkbox" class="multi-select-checkall" value=""><span>全部</span></label>
										</li>
									</ul>
								</div>
							</div>
							<div class="form-group" style="padding-left: 120px;">
                                <label>门店城市&nbsp;</label>
                                <input type="text" class="form-control w200" id="storeCityName" name="storeCityName"
                                       placeholder="门店城市" style="width:180px;">
                            </div>
                            </div>
                        </div>

                        <div class="form-inline" style="margin-left: 28px;">
                            <div class="form-group" style="padding-left: 40px;">
                                <label>门店&nbsp;</label>
                                <input type="text" class="form-control w200" id="storeNo" name="storeNo"
                                       placeholder="门店编号、门店名称" style="width:180px;">
                            </div>
                            <div class="form-group" style="padding-left: 138px;">
                                <label>公司&nbsp;</label>
                                <input type="text" class="form-control w200" id="companyNo" name="companyNo"
                                       placeholder="公司编号、公司名称" style="width:180px;">
                            </div>
                            <div class="form-group" style="padding-left: 134px;">
                                <label>维护人&nbsp;</label>
                                <input type="text" class="form-control w200" id="maintainerId" name="maintainerId"
                                       placeholder="姓名、工号" style="width:180px;">
                            </div>

                        </div>

                        <div class="form-inline" >
                            <div class="form-group" style="padding-left: 40px;">
                                <label>创建时间&nbsp;</label>
                                <input type="text" class="calendar-icon form-control w100" name="dealDateStart"
                                       id="dealDateStart" style="width: 120px"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                                <span>-</span>
                                <input type="text" class="calendar-icon form-control w100" name="dealDateEnd"
                                       id="dealDateEnd" style="width: 120px"
                                       onFocus="WdatePicker({isShowClear:true, readOnly:true, dateFmt:'yyyy-MM-dd'})"
                                       readonly="readonly" class="ipttext Wdate"/>
                            </div>

                            <div class="pull-right">
                                <button type="button" class="btn btn-primary" id="J_search"
                                        onclick="javascript:storeInformationDetail.search();">查询
                                </button>
                                &nbsp;&nbsp;
                                 <a type="button" class="btn btn-default" onclick="javascript:storeInformationDetail.reset('${list_search_page}');">重置</a>
                                &nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary" id="btn-reset"
                                        onclick="javascript:storeInformationDetail.export()">导出
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 异步加载列表内容 -->
                <div id="load_content">
                    <div id="LoadCxt"></div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>

</html>
