<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<%@page session="true" import="cn.com.eju.deal.base.support.SystemParam"%>
<%@ page import="cn.com.eju.deal.core.util.StringUtil" %>
<!DOCTYPE HTML>
<html>

<head>

<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript"
	src="${ctx}/meta/js/user/login.js?_v=${vs}"></script>
<script type="text/javascript">
	var r = /login$/;
	if (!r.test(window.location.href)) {
		window.location.href = BASE_PATH + '/login';
	}
</script>
<script type="text/javascript">
$(document).ready(function(){
  /* $(".codeBtn").focus(function(){
    $(".codeBtn").css("background-color","#FFFFCC");
  }); */
  $("#idCodeBtn").click(function(){
    $(".codeBtn").focus();
  });  
});
</script>
</head>
<style type="text/css">
	.panel{
		background-color:transparent;
		border:none;
		width:520px;
		min-height:215px;
		left:50%;
		top:50%;
		margin-left:-260px;
		margin-top:-150px;
		margin-bottom:0;
		
	}
	.panel-body{
		padding: 40px 0 0 18px;
	    height: 255px;
	    float: right;
	    border-left: 1px solid #ededed;
	}
	.text-center{
		color:#fff;
	}
	input.form-control{
		display:inline-block;
		width:auto;
		height:28px;
		width:270px;
	}
	.fon-normal{
	width:45px;
	text-align:right;
	padding-left:0!important
	}
	.logo-group label,
	.randCodeLi-group label{
    width: 25px;
    height: 25px;
    margin-bottom: 0;
    position: absolute;
    left: 0px;
    padding:0;
	}
	.logo-group{
    width: 280px;
    margin: 0 auto;
    position: relative;
    overflow:hidden;
    font-size:0;
	}
	.logo-group label i,
	.randCodeLi-group label i{
		display: block;
	    width: 16px;
	    height: 16px;
	    background-image: url('/PMLSWeb/meta/images/login-step-bg.png');
	    margin: 6px 0 0 6px;
	    background-size: 16px 32px;
	}
	.logo-group label.user-name i{
		background-position: 0 0;
	}
	.randCodeLi-group label.user-password i{
		background-position: 0 -16px;
	}
	.logo-group .put-control{
		border-radius: 0;
    background-color: #fff;
    padding-left: 30px;
    border: none;
    line-height: 28px;
    width: 278px;
    height: 28px;
    display: block;
    border: 1px solid #dedede;
    box-sizing: border-box;
    font-size: 14px;
    color: #333!important;
	}
	.randCodeLi-group{
		width:280px;
		margin:0 auto;
		overflow:hidden;
		font-size:0;
		position:relative;
	}
	.randCodeLi-group .put-control{
		border: 1px solid #dedede;
	    line-height: 28px;
	    width: 165px;
	    height: 28px;
	    font-size: 14px;
	    color: #333!important;
	    padding-left: 30px;
	    float:left;
	}
	.randCodeLi-group .btn-primary{
		padding:0 6px;
		height:28px;
		line-height:28px;
		margin-left:25px;
		width:88px;
	}
	.qr-code{
		margin-top:29px;
		float:left;
		overflow: hidden;
	}
	.qr-code img{
		width:158px;
	}
	.qr-code span{
		font-size:12px;
		display:block;
		text-align:center;
		line-height:28px;
	}
	.login-box{
		background-color: #fff;
	    overflow: hidden;
	    padding: 0 20px;
	}
	.text-error{
		color:#ff0000;
		line-height:22px;
		height:22px;
		font-size:12px;
		visibility:hidden;
		display:block;
	}
	/* 去掉登录页黄色背景 */
	 input:-webkit-autofill {
	  -webkit-box-shadow: 0 0 0px 1000px white inset;
	  -webkit-text-fill-color: #333;
	}
	.reminder-text{
		font-size:12px;
		margin-top:10px;
		color:#999;
		width:280px;
		/*visibility: hidden;*/
	}
	.icon-info{
		line-height: 17px;
		width: 16px;
	    height: 16px;
	    display: inline-block;
	    vertical-align: -3px;
	    position: relative;
	    text-align: center;
	    color: #fff;
	    font-style: normal;
	    font-size: 10px;
	}
	.icon-info::before {
	    content: "\20";
	    border-radius: 100%;
	    background-color: #dedede;
	}
	.icon-info::after {
	    content: "i";
	    position: absolute;
	    top: 50%;
	    left: 50%;
	    margin-top: -8px;
	    margin-left: -8px;
	}
	.icon-info::before, .icon-info::after {
	    display: block;
	    width: 100%;
	    height: 100%;
	}
	#message{
		font-size: 12px;
	    margin-top: -18px;
	    margin-bottom: 4px;
	    height: 20px;
	    width: 280px;
	}
</style>
<body style="background:url('${ctx}/meta/images/login-bg.jpg') no-repeat;">
	<div class="panel panel-primary login">
		<h3 class="text-center" style="margin-bottom:20px;">
			PMLS客户关系管理
		</h3>
		<div class="login-box">
			<div class="qr-code">
				<img src="${ctx}/meta/images/qrcode-img.png">
				<span>关注微信企业号查看验证码</span>
			</div>
			<div class="panel-body">
				<form id="loginForm" method="POST">
					<div class="form-group logo-group">
						<label class="user-name"><i></i></label>
						<input type="text" class="put-control" id="username" name="username" placeholder="工号" >
						<span class="text-error" id="userNameError">未找到该工号</span>
					</div>
					<% String LoginModeFlag = SystemParam.getWebConfigValue("LoginModeFlag");
						if (StringUtil.isEmpty(LoginModeFlag)) {
							LoginModeFlag = "0";
						}
						String tr="1",fa="0";
					%>
					<input type="hidden" value="<%=LoginModeFlag %>" id="LoginModeFlag">
					<c:if test="<%=LoginModeFlag.equals(tr)%>">
						<div class="form-group randCodeLi-group">
							<input type="text" class="put-control codeBtn" id="" name="password" placeholder="验证码" style="padding-left:5px;">
							<button id="idCodeBtn" name="idCodeBtn" type="button" onclick="getIdCode()" class="btn btn-primary">获取验证码</button>
							<span class="text-error" id="passwordError">验证码输入错误</span>
						</div>
					</c:if>
					<c:if test="<%=LoginModeFlag.equals(fa)%>">
						<div class="form-group randCodeLi-group">
							<label class="user-password" style="padding-left:0"><i></i></label>
							<input type="password" class="put-control" name="password" placeholder="密码" style="width:278px;float:none;">
							<span class="text-error" id="passwordError"></span>
						</div>
					</c:if>

					<!-- <div class="form-group" id="randCodeLi" style="display:none">
						<label for="exampleInputRandCode" class="fon-normal fs14">验证码</label>
						<input type="text" class="form-control" id="randCode1" name="randCode" placeholder="请输入验证码" style="width:193px;">
						<img id="randCode" alt="" src="${ctx}/meta/js/common/code.jsp"
								align="middle" width="70" height="30" onclick="changeRandCode()">
					</div> -->
				</form>
				<div class="form-group" style="margin:0 auto; width:280px;">
					<button type="button" class="btn btn-primary" onclick="Login.login();" style="width:100%;">登录</button>
				</div>
				<div class="reminder-text">

				</div>
			</div>		
		</div>
	</div>
</body>

</html>