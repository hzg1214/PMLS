<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/page/common/meta.jsp"%>
<%@ include file="/WEB-INF/page/common/jsCss.jsp"%>
<script type="text/javascript" src="${ctx}/meta/js/user/user.js?_v=${vs}"></script>
</head>

<body>

	<!-- 头部 -->
	<jsp:include page="/WEB-INF/page/common/header.jsp" flush="true">
		<jsp:param value="${headMenuIdSelect}" name="navSelectId" />
	</jsp:include>

        <div class="container theme-hipage ng-scope" role="main">
        
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>个人中心</strong></h4>
                <p><strong style>基本信息</strong></p>
                <!-- <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">用户头像:</label>
                            <img src="store.png" alt="" class="img">
                        </div>
                    </li>
                </ul> -->
                <!-- <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right"></label>
                            <div class="form-group">
                                <input type="file" id="exampleInputFile">
                            </div>
                        </div>
                    </li>
                </ul> -->
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <span class="w120 text-right" style="display: inline-block;vertical-align: top;">用户名 : </span>
                            <span>&nbsp;${userInfo.userName}</span>
                        </div>
                    </li>
                </ul>
             <!--    <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">修改密码:</label>
                            <input type="text" class="form-control w300" id="estateNm" name="estateNm" placeholder="修改密码" value="" >
                        </div>
                    </li>
                </ul> -->
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                        <span class="w120 text-right" style="display: inline-block;vertical-align: top;">部　门 : </span>
                        <span>&nbsp;${userInfo.groupName}</span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                        <span class="w120 text-right" style="display: inline-block;vertical-align: top; ">职　位 : </span>
                        <span>&nbsp;${userInfo.postList[0].postName}</span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <span class="w120 text-right" style="display: inline-block;vertical-align: top;">性　别 : </span>
                            <span>&nbsp;${userInfo.sexName}</span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                        	<span class="w120 text-right" style="display: inline-block;vertical-align: top;">邮　箱 : </span>
                            <span>&nbsp;${userInfo.email}</span>
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
	                        <span class="w120 text-right" style="display: inline-block;vertical-align: top;">手　机 : </span>
	                        <span>&nbsp;${userInfo.cellphone}</span>
                        </div>
                    </li>
                </ul>
               <!--  <div class="text-center">
                  <button type="button" class="btn btn-primary" onclick="javascript:User.toResetPwd();">重置密码</button>  
                </div> -->
            </div>
        </div>
    </div>
    
      
       <%-- <form  id="userCenterForm">
            
            <input type="hidden" name="_method" value="put"/>
            <input type = "hidden"  id = "userCode"   name = "userCode" value = "${userInfo.userCode}"/>
 
               
               <p class="text-danger hide"><i class="fa fa-times-circle"></i></p>
            
                <div class="form-group">
                    <label for="exampleInputEmail1">用 户 名</label>${userInfo.userName}
                </div>
                
                 <div class="form-group">
                    <label for="exampleInputEmail1">部们</label>
                </div>
                
                 <div class="form-group">
                    <label for="exampleInputEmail1">职务</label>
                </div>
                 <div class="form-group">
                    <label for="exampleInputEmail1">性别</label>${userInfo.sex}
                </div>
                
                 <div class="form-group">
                    <label for="exampleInputEmail1">邮箱</label>
                </div>
                
                 <div class="form-group">
                    <label for="exampleInputEmail1">手机</label>${userInfo.cellphone}
                </div>
                
                <div class="form-group">
                    <label for="exampleInputPassword1">获取验证码</label>
                    <a id="btn" href="javascript:void(0)" onClick="User.getAuthCode()" class="getcoding">获取验证码</a>
                </div>
                
                
                <div class="form-group">
                    <label for="exampleInputPassword1">修改密 码</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="password">
                </div>
            </form>
            
            <div class="text-center">
                <button type="button" class="btn btn-primary" onclick="User.resetPassword();">重置密码</button>
            </div> --%>
      
        </div>

</body>

</html>
