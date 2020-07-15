<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<style>
    .half li {
        width: 85%
    }

    .w200 {
        width: 200px !important;
        margin-right: 20px
    }

    #divs {
        margin: 0 auto;
    }
</style>
<script>
    $(function () {
    });
</script>

<form id="keFuWjAnalyseForm">
    <div class="container theme-hipage ng-scope" role="main" id="divs" style="width: 700px;">
        <div class="row">
            <div class="page-content"><span class="" style="float:right">
                <a href="javascript:keFuWjAnalyse.close();" class="btn btn-default">&times;</a></span>
                <h4 class="border-bottom pdb10"><strong>选择模板名称</strong></h4>
                <div class="form-group">
                    <label class="fw-normal w200 text-right"></label>
                    <span class="fc-warning" id="warningMsg"></span>
                </div>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group" name="group" style = "padding-left: 126px;">
                            <label>模板名称</label>
                            <span class="control-select">
							<select class="form-control" title="" id="wjDetail" name="wjDetail" style="width: 300px;">
                                <option value="" selected="selected">请选择</option>
                                <c:forEach items="${wjDetail}" var="wjDetail">
                                    <option value="${wjDetail.wjCode}">${wjDetail.wjName}</option>
                                </c:forEach>
                            </select>
							<span class="fc-warning" id="newCenterIdStr"></span>
							</span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w200 text-right"></label>
                            <span class="fc-warning" id="oldWarningMsg"></span>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</form>