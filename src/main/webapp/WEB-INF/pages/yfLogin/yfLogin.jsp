<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <style>
         .w90 {
             width: 90px !important;
         }

         /*.layui-form-label {*/
         /*    width: 100px;*/
         /*}*/

         body{padding: 0px; /*overflow-y: scroll;*/}
         .layui-btn-mini {
               height: 22px;
               line-height: 22px;
               padding: 0 5px;
               font-size: 12px;
            }
    </style>
</head>

<body>

<div>
    <div class="layui-card">
        <!-- Main content -->
        <div class="layui-card-body">
            <div class="layui-form">
                <input type="hidden" name="userCode" id="userCode" value="${userInfo.userCode}">
                <input type="hidden" name="yftLoginUrl" id="yftLoginUrl" value="${yftLoginUrl}">
                <input type="hidden" name="desSign" id="desSign" value="${desSign}">
            </div>
        </div>
    </div>
</div>




<script type="text/javascript" src="${ctx}/meta/pmls/js/yfLogin/yfLogin.js?_v=${vs}"></script>

</body>
</html>