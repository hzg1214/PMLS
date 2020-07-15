<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>


 <div class="" role="main">
 
  <form id = "resetPwdForm">
  
        <div class="row">
            <div class="page-content" style="">
                <h4><strong>重置密码</strong></h4>
                <p></p>
                
                <ul class="list-inline form-inline mgt20">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right" for="userCode">用户编号 </label>
                            <input type="text" class="form-control w300" id="userCode" name="userCode" value="${userInfo.userCode}" readonly="readonly">
                        </div>
                    </li>
                </ul>
                
                <ul class="list-inline form-inline mgt20">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right" for="authCode"><i>* </i>验证码</label>
                            <input type="text" class="form-control w300" id="authCode" name="authCode"  notnull="true">
                            <!-- <span class="fc-warning"></span> -->
                        </div>
                    </li>
                    <li>
				      <a id="captcha" href="javascript:User.getAuthCode();" class="captcha">发送验证码</a>
				      <a id="resend" href="#" class="resend" style="display:none;">(<font id="second">60</font>秒后重发)</a>
				    </li>
				    <li id = "authCodeTip"><b class="tip" ></b></li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right" for="password"><i>* </i>新密码</label>
                            <input type="password" class="form-control w300" id="newPwd" name="newPwd" notnull="true">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                  <!-- <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right"></label>
                            <span>为了保证您的安全，新密码为6~16个字符，须同时包含数字，字母，特殊字符</span>
                        </div>
                    </li>
                </ul> -->
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right" for="confirmPwd"><i>* </i>确认密码</label>
                            <input type="password" class="form-control w300" id="confirmPwd" name="confirmPwd" notnull="true" pwdCompare = "newPwd">
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
                
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" id="errorTip">
                            <label class="fw-normal w100 text-right"></label>
                            <span class="fc-warning"></span>
                        </div>
                    </li>
                </ul>
              
            </div>
            
        </div>
        
       </form> 
</div>