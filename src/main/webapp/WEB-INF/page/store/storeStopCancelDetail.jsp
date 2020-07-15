<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<head>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
    <script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css?_v=${vs}">
    <script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
</head>
<html>
<body>
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                onclick="javascript:BizStop.close();">
            <span>&times;</span>
        </button>
        <h4 class="modal-title">门店停业撤销申请</h4>
    </div>
    <div class="container">
        <div class="row article">
            <div class="col-md-12 content">
                <div class="page-content">
                    <c:if test="${detail.approveStatus eq 21001}">
                        <shiro:hasPermission name="/store:CRM_SCC_AUD">
                        <h4>
                            <a type="button" class="btn btn-primary" href="javascript:void(0)" onclick="BizStopAud.auditReturn(${detail.id},${detail.storeId},'${detail.storeNo}','${detail.storeName}',${detail.userCreate},'${detail.addressDetail}');" style="margin-right:10px;">驳回</a>
                            <a type="button" class="btn btn-primary" href="javascript:void(0)" onclick="BizStopAud.auditPass(${detail.id},${detail.storeId},'${detail.storeNo}','${detail.storeName}',${detail.userCreate},'${detail.addressDetail}');" style="margin-right:10px;">审核通过</a>
                        </h4>
                        </shiro:hasPermission>
                    </c:if>
                    <strong style="padding-left: 10px;">基本信息</strong>
                    <table class="table-sammary">
                        <col style="width:150px;">
                        <col style="width:auto;">
                        <col style="width:150px;">
                        <col style="width:auto;">
                        <tr>
                            <td class="talabel">撤销编号：</td>
                            <td>${detail.cancelNo}</td>
                        </tr>
                        <tr>
                            <td class="talabel">门店编号：</td>
                            <td>${detail.storeNo}</td>
                            <td class="talabel">门店名称：</td>
                            <td>${detail.storeName}</td>
                        </tr>
                        <tr>
                            <td class="talabel">门店地址：</td>
                            <td>${detail.addressDetail}</td>
                            <td class="talabel">门店维护人：</td>
                            <td>${detail.maintainerName}</td>
                        </tr>
                        <tr>
                            <td class="talabel">所属中心：</td>
                            <td>${detail.centerName}</td>
                        </tr>
                        <tr>
                            <td class="talabel">停业撤销原因：</td>
                            <td colspan="3">${detail.cancelReason}</td>
                        </tr>
                        <tr>
                            <td class="talabel">申请人：</td>
                            <td>${detail.userCreateNm}</td>
                            <td class="talabel">申请时间：</td>
                            <td>${detail.dateCreate}</td>
                        </tr>
                        <tr>
                            <td class="talabel">审核状态：</td>
                            <td>${detail.approveStatusNm}</td>
                            <c:if test="${detail.approveStatus ne 21001}">
                                <td class="talabel">审核时间：</td>
                                <td>${detail.approveDate}</td>
                            </c:if>
                        </tr>
                        <c:if test="${detail.approveStatus eq 21003}">
                            <tr >
                                <td class="talabel">驳回原因：</td>
                                <td style="color: red">${detail.approveDesc}</td>
                            </tr>
                        </c:if>
						<tr>
                            <td colspan="4">
                                <div class="" role="main">
                                    <div>
                                        <div class="pd10" style="padding: 0px 10px;" name="Viewerbox">
                                            <h4 class="thumb-title" style="border-bottom: 1px dashed #e1e1e1;padding-top: 0px;padding-bottom: 0px;line-height: 20px;">
                                                门店照片
                                            </h4>
                                            <div class="thumb-xs-box">
                                                <c:forEach items="${detail.fileList}" var="list" varStatus="status">
                                                    <c:if test="${list.fileTypeId eq 1049}">
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
                                                            <span class="thumb-img">
                                                                <span class="thumb-img-container">
                                                                    <span class="thumb-img-content">
                                                                        <img alt="其他" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                    </span>
                                                                </span>
                                                            </span>
                                                        </a>
                                                    </div>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <div class="" role="main">
                                    <div>
                                        <div class="pd10" style="padding: 0px 10px;" name="Viewerbox">
                                            <h4 class="thumb-title" style="border-bottom: 1px dashed #e1e1e1;padding-top: 0px;padding-bottom: 0px;line-height: 20px;">
                                                正常营业说明书
                                            </h4>
                                            <div class="thumb-xs-box">
                                                <c:forEach items="${detail.fileList}" var="list" varStatus="status">
                                                    <c:if test="${list.fileTypeId eq 1050}">
                                                    <div class="thumb-xs-list item-photo-list">
                                                        <a href="javascript:void(0);" class="thumbnail swipebox"
                                                           target="_blank">
                                                            <span class="thumb-img">
                                                                <span class="thumb-img-container">
                                                                    <span class="thumb-img-content">
                                                                        <img alt="其他" data-original="${list.url50}" src="${list.fileAbbrUrl}" class="empPhoto"/>
                                                                    </span>
                                                                </span>
                                                            </span>
                                                        </a>
                                                    </div>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
