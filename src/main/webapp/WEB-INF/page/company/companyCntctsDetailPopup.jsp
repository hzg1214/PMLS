<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>

 <div class="container theme-hipage ng-scope" role="main">
 
 	<span class="" style="float:right"><a href="javascript:Contacts.close();" class="btn btn-default">&times;</a></span>
 
        <div class="row">
            <div class="page-content">
                <h4 class="border-bottom pdb10"><strong>查看联系人</strong></h4>
                <p><strong>主要信息</strong></p>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">姓名：</label>${info.name}
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">性别：</label>${info.sexName}
                        </div>
                    </li>  
                </ul>
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">手机：</label>${info.mobilePhone}
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">邮件：</label>${info.email}
                        </div>
                    </li>  
                </ul>
              
                <ul class="list-inline half form-inline">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">微信：</label>${info.wechat}
                            
                        </div>
                    </li>
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">QQ：</label>${info.qQ}
                            
                        </div>
                    </li>
                     
                </ul>
              
                <ul class="list-inline half form-inline">
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">联系地址：</label>${info.address}
                        </div>
                    </li>
                     <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">创建时间：</label>${info.dateCreate}
                            
                        </div>
                    </li>
                </ul>
                <ul class="list-inline half form-inline pdb20">
                    <li>
                        <div class="form-group">
                            <label class="fw-normal w100 text-right">备注：</label>${info.remark}
                            
                        </div>
                    </li>
                   
                </ul>
                
               <!--  <div class="text-center">
                  <button type="button" class="btn btn-primary" onclick="javascript:Contacts.back();">返回</button>  
                </div> -->
            </div>
        </div>
    </div>

