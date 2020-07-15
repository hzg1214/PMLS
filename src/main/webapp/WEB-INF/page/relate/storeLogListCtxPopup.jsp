<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
  <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css">
  <style>
  #divs{
  margin:0 auto;
  }
  </style>
	<div class="container theme-hipage ng-scope" role="main" id="divs">
             <span class="" style="float:right"><a href="javascript:StoreLog.close();" class="btn btn-default">&times;</a></span>
		<div class="row">
			<div class="page-content">
				<h4 class="border-bottom pdb10"><strong>查看</strong></h4>
               	<ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" style="margin-left: 15px;">
                            <label class="fw-normal w200 text-right"><i>*</i>修改项目名称：</label>
                            <span>
                            ${StoreLog.updateIteam}        
					         </span> 
                        </div>
                    </li>
                </ul>
                <c:if test="${StoreLog.changeName == 1 }">
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" style="margin-left: 35px;">
                            <label class="fw-normal w200 text-right">原门店名称：</label>
                             ${StoreLog.oldStoreName }
                        </div>
                    </li>
                </ul>
                
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" style="margin-left: 8px;">
                            <label class="fw-normal w200 text-right">修改后门店名称：</label>
					           ${StoreLog.newStoreName }
                        </div>
                    </li>
                </ul>
                </c:if>
                </div>
                <c:if test="${StoreLog.changeAddress == 1 }">
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" style="margin-left: 30px;">
                            <label class="fw-normal w200 text-right"><i>*</i>原门店地址：</label>
                                ${StoreLog.oldAddressDetail }
                        </div>
                    </li>
                </ul>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" style="margin-left: 8px;">
                            <label class="fw-normal w200 text-right">修改后门店地址：</label>
                              ${StoreLog.newAddressDetail }
                        </div>
                    </li>
                </ul>
                </c:if>
                <ul class="list-inline form-inline">
                    <li>
                        <div class="form-group" style="margin-left: 58px;">
                            <label class="fw-normal w200 text-right"><i>*</i>修改人：</label>
                               ${StoreLog.userName}
                        </div>  
                        <div class="form-group" style="margin-left: 118px;">
                            <label class="fw-normal w200 text-right"><i>*</i>修改时间：</label>
                                 ${StoreLog.updateDate }
                        </div> 
                    </li>
                </ul>  
                </div>
                
                <div class="" role="main">
        <div >
                <div class="pd10">
					<h4 class="thumb-title">
						附件(营业执照等)
					</h4>
					<div class="thumb-xs-box" id="thumbXsBox">
					<c:if test="${not empty storeDetail.storePicList }">
						<c:set  var="fileSize" value="0"/>
		                <c:forEach items="${storeDetail.storePicList}" var="list" varStatus="status">
		                	<c:set var="fileSize" value="${fileSize + 1}"/>
							<div class="thumb-xs-list item-photo-list">
									   <a href="${list.bigPictureUrl}" class="thumbnail swipebox" target="_blank" >
										   	<span class="thumb-img">
										   		<span class="thumb-img-container">
										   			<span class="thumb-img-content">
										   				<img alt="门店图片列表" src="${list.smallPictureUrl}" class="empPhoto" />
										   			</span>
									   			</span>
								   			</span>
							   		   </a>
							    <input type="file" id="file${fileSize}" class="btn-flie file-control hide" data-limit="10" multiple="multiple" />
							  </div>
		                </c:forEach> 
		                
					</c:if> 
					</div>
				</div>
     </div>
 </div> 	
	</div>

