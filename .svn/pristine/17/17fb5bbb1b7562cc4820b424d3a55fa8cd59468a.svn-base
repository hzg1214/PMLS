<%@page pageEncoding="UTF-8"%>
<%@page session="true" import="cn.com.eju.deal.base.support.SystemParam"%>
<%@ page import="cn.com.eju.deal.core.util.StringUtil" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="common/common.jsp" %>
    <title>房友新房分销系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/meta/pmls/css/login.css" >
    <style type="text/css">
            .spanwarn{
                position: absolute;
                padding-top: 2px;
                color: #ff0000;
                line-height: 22px;
                height: 22px;
                font-size: 13px;
                display: block
            }
    </style>

        <script>


                if(window.top!=window.self){
                    window.parent.location =  BASE_PATH + "/app/login";
                }


        </script>
</head>
<body>

<div class="loginMainDiv">
    <div class="loginTitle">
    </div>
    <div class="loginMain">
        <div class="loginMainTop">
        </div>
        <div class="loginIcon">
    <%--<div class="layui-row loginDiv">--%>
        <%--<div class="leftDiv">--%>
            <%--<span class="appTitleSpan">易居房友新房分销系统</span>--%>
        <%--</div>--%>
        <% String LoginModeFlag = SystemParam.getWebConfigValue("LoginModeFlag");
            if (StringUtil.isEmpty(LoginModeFlag)) {
                LoginModeFlag = "0";
            }
            String tr="1",fa="0";
        %>
        <div class="login" >
            <input type="hidden" value="<%=LoginModeFlag %>" id="LoginModeFlag">
            <div >
                <div class="loginQrCode" ></div>
                <div  style="display: inline-block" >
                    <form lay-filter="loginForm" class="layui-form" action="" method="post" >

                    <table>
                        <tr>
                            <td >
                                <div class="loginDivFor">
                                    <i class="icon icon_username"></i>
                                    <input class="loginInput" type="text"  name="userCode" id="userCode" placeholder="请输入用户工号">
                                    <span class="spanwarn" id="userCodeSpan"></span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td  >
                                <div class="loginDivFor">
                                    <i class="icon icon_password"></i>
                                <c:if test="<%=LoginModeFlag.equals(tr)%>">
                                    <input class="password loginInput"  name="password" id="password" placeholder="验证码" style="width: 90px;">
                                    <button id="idCodeBtn" name="idCodeBtn" type="button" style="width: 80px;height: 30px;"onclick="getIdCode()" class="loginBtn">获取验证码</button>
<%--                                    <label onclick="" disabled id="identityCode" class="normal" style="position: absolute; margin-left: 35px;margin-top: -31px;">获取验证码</label>--%>
                                </c:if>
                                <c:if test="<%=LoginModeFlag.equals(fa)%>">
                                        <input class="password loginInput" type="password" required  name="password" id="password" placeholder="请输入密码">
                                </c:if>
                                    <span class="spanwarn" id="passwordSpan"></span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td ><button type="button" class="loginBtn" lay-submit  lay-filter="loginForm">登录</button></td>
                        </tr>
                    </table>
                </form>
                </div>
            </div>
        </div>
        </div>
        </div>
        <%--<div class="loginMainFoot" style="display: none;">
        </div>--%>
        <div class="copyRight">版权归易居所有</div>
    </div>

</div>
<script type="text/javascript">
    var layer;
    var wait=60;
    var timer = null;
    layui.use('form', function(){
        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
        layer = layui.layer;
        form.render();

        //判断是否存在用户信息
        if(localStorage.getItem("userInfo")){
            var userInfo = JSON.parse(localStorage.getItem("userInfo"));
            if(userInfo!=null && userInfo!=undefined){
                $("#userCode").val(userInfo.userCode);
                $("#password").val(userInfo.password);
//                $("#rememberPassword").attr("checked", true);
                form.render();
            }
        }


        //提交
        form.on('submit(loginForm)', function(obj){
            var loadIndex = layer.load(2,{shade: 0.1});//弹出加载中
            if(!valid()){
                layer.close(loadIndex);//关闭加载中
                return false;
            }
            $.ajax({
                url: '${pageContext.request.contextPath}/login',
                type: 'post',
                dataType: 'json',
                data:obj.field,
                success: function (data) {
                    layer.close(loadIndex);//关闭加载中

                    if (data.returnCode != 200){
                        //layer.alert(data.returnMsg, {icon: 2, title:'提示'});
                        $('#passwordSpan').empty().html(data.returnMsg);
                        $("#passwordSpan").css('color','#ff0000');
                    }else{
                        //登录成功保存用户信息
//                        if(obj.field.rememberPassword=='on'){
//                            localStorage.setItem("userInfo",JSON.stringify(obj.field));
//                        }else{
//                            localStorage.removeItem("userInfo");
//                        }
                        //登入成功的提示与跳转
                        /*layer.msg('登录成功', {
                            offset: '15px'
                            ,icon: 1
                            ,time: 1000
                        }, function(){
                            location.href = '${pageContext.request.contextPath}/app/index'; //后台主页
                        });*/
                        location.href = '${pageContext.request.contextPath}/app/index'; //后台主页
                    }
                }
            });

        });

        function valid() {
            var userCode = $("#userCode").val();
            var password = $("#password").val();
            var LoginModeFlag = $("#LoginModeFlag").val();

            if (isBlank(userCode)) {
                $("#userCodeSpan").empty().html("请输入用户工号");
                return false;
            } else {
                $("#userCodeSpan").empty().html("");
            }

            if (isBlank(password)) {
                if(LoginModeFlag == "1"){
                    $("#passwordSpan").empty().html("验证码不能为空！");
                    $("#passwordSpan").css('color','#ff0000');
                    return false;
                }else{
                    $("#passwordSpan").empty().html("密码不能为空！");
                    $("#passwordSpan").css('color','#ff0000');
                    return false;
                }
            } else {
                $("#passwordSpan").empty().html("");
            }
            return true;
        }
    });



    function getIdCode(){
        if($("#userCode").val()!=null && $("#userCode").val()!=''){
            time($("#idCodeBtn"));
            var postUrl=BASE_PATH + "/getIdCode";
            $.ajax(
                {
                    url:postUrl,
                    data :{"userName":$("#userCode").val()},
                    type : 'POST',
                    dataType : 'json',
                    success: function (data) {
                        if(data.code==200){
                            $("#passwordSpan").empty().html("验证码发送成功");
                            $("#passwordSpan").css('color','#000000');
                        }else{
                            $("#passwordSpan").empty().html(data.msg);
                            $("#passwordSpan").css('color','#ff0000');
                            stopTimer($("#idCodeBtn"));  //开启按钮
                        }
                    }
                }
            );
        }else{
            $("#userCodeSpan").empty().html("请输入用户工号");
        }
    }


    function time(o) {
        if (wait == 0) {
            o.removeClass("loginWechartPasswordBtn")
            o.addClass("loginBtn")
            o.removeAttr("disabled");
            o.html("获取验证码");
            wait = 60;
        } else {
            o.removeClass("loginBtn")
            o.addClass("loginWechartPasswordBtn")
            o.attr("disabled", true);
            o.html("重发(" + wait + "秒)");
            wait--;
            timer = setTimeout(function() {
                    time(o)
                },
                1000)
        }
    }
    function stopTimer(o){
        if (wait <= 0 || timer == null) return;
        o.removeClass("loginWechartPasswordBtn")
        o.addClass("loginBtn")
        $("#idCodeBtn").removeAttr("disabled");
        $("#idCodeBtn").html("获取验证码");
        wait = 60;
        clearTimeout(timer);
    }

</script>
</body>
</html>
