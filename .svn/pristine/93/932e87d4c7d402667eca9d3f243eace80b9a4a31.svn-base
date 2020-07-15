<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
    <script type="text/javascript" src="${ctx}/meta/js/followDetail/followMap.js?_v=${vs}"></script>
</head>
<body class="hold-transition fixed skin-blue sidebar-mini">

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"
            onclick="javascript:FollowDetail.close();">
        <span>&times;</span>
    </button>
    <h4 class="modal-title">签到地图</h4>
</div>
<div class="modal-body"
     style="width:650px;height:400px;overflow-y:scroll; padding-top:0;">
    <div class="box-body" style="padding-top: 20px;">
        <table style="width:100%;text-aling:center">
            <col style="width:100px;">
            <col style="width:auto;">

            <input id="list" type="hidden" value="${contentlist}"/>

            <tr>
                <td>
                    <div style="width:600px;height:350px;border:#ccc solid 1px;border-radius: 4px;font-size:12px;display:inline-block" id="mapDiv"></div>
                </td>
            </tr>
        </table>

    </div>
</div>

<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="FollowDetail.close();">返回</button>
</div>

</body>
</html>
