<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/js/common/viewerBox/viewer.min.css">
<script type="text/javascript" src="${ctx}/meta/js/common/viewerBox/viewer.min.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css?_v=${vs}">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<style>
    ul {
        margin-bottom: 0px;
    }
</style>
<div class="container theme-hipage ng-scope" role="main" style="width: 800px;">
    <span class="" style="float:right"><a href="javascript:StoreLog.close();"
                                          class="btn btn-default">&times;</a></span>
    <div class="row">
        <div class="page-content">
            <h4 class="border-bottom pdb10"><strong>门店停业撤销申请</strong></h4>
            <strong style="padding-left: 10px;">基本信息</strong>
<form id="storeStopCancelForm">
    <input type="hidden" id="storeId" name="storeId" value="${storeId}"/>
    <input type="hidden" name="storeNo" id="storeNo" value="${store.storeNo}">
    <input type="hidden" id="fileRecordMainIds" name="fileRecordMainIds">
            <ul class="list-inline form-inline">
                <li>
                    <div class="form-group" style="margin-left: 65px;">
                        <label class="fw-normal w200 text-right">门店编号:</label>
                        <span style="padding-left:10px;"></span>
                        ${store.storeNo}
                    </div>
                </li>
            </ul>
            <ul class="list-inline form-inline">
                <li>
                    <div class="form-group" style="margin-left: 65px;">
                        <label class="fw-normal w200 text-right">门店名称:</label>
                        <span style="padding-left:10px;"></span>
                        ${store.name}
                    </div>
                </li>
            </ul>
            <ul class="list-inline form-inline">
                <li>
                    <div class="form-group" style="margin-left: 65px;">
                        <label class="fw-normal w200 text-right">门店地址:</label>
                        <span style="padding-left:10px;"></span>
                        ${store.addressDetail}
                    </div>
                </li>
            </ul>
            <ul class="list-inline form-inline">
                <li>
                    <div class="form-group" style="margin-left: 38px;">
                        <label class="fw-normal w200 text-right">门店所属中心:</label>
                        <span style="padding-left:10px;"></span>
                        ${store.centerName}
                    </div>
                </li>
            </ul>
            <ul class="list-inline form-inline">
                <li>
                    <div class="form-group" style="margin-left: 53px;">
                        <label class="fw-normal w200 text-right">门店维护人:</label>
                        <span style="padding-left:10px;"></span>
                        ${store.maintainerName}
                    </div>
                </li>
            </ul>
            <ul class="list-inline form-inline" style="margin-top: 0px;">
                <li>
                    <div class="form-group" style="margin-left: 30px;">
                        <label class="fw-normal w200 text-right" style="vertical-align:top;"><i>*</i>停业撤销原因:</label>
                        <span style="padding-left:10px;"></span>
                        <textarea data-options="required:true" type="textarea" notnull="true" maxlength="1024" required="true" class="vera validatebox-text validatebox-invalid" id="cancelReason" name="cancelReason" style="width:453px;height: 100px;padding:0px;"></textarea>
                        <span class="fc-warning" style="vertical-align:top;"></span>
                    </div>
                </li>
            </ul>
</form>
            <div class="theme-hipage ng-scope" role="main">
                <p><strong>附件</strong></p>
            </div>
            <div class="theme-hipage ng-scope" role="main">
                <div class="row">
                    <div style="    padding: 0px 10px;">
                        <h4 class="border-bottom" style="font-size: small;">
                            <i>*</i>门店照片
                        </h4>
                        <div class="thumb-xs-box" id="storePhoto">
                            <div class="thumb-xs-list item-photo-add" >
                                <input type="hidden" name="limitSize" value="10">
                                <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                    <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                    <input type="hidden" name="fileTypeId" value="1049" />
                                    <input type="hidden" name="fileSourceId" value="14" />
                                    <input type="hidden" name ="companyId" value="">
                                </a>
                            </div>
                        </div>
                        <i class="fontset">注：照片清晰，应拍到门店招牌，经服人员和门店店东需同时入镜。</i>
                    </div>
                </div>
            </div>
            <div class="theme-hipage ng-scope" role="main">
                <div class="row">
                    <div class="pd10">
                        <h4 class="border-bottom" style="font-size: small;">
                            <i>*</i>正常营业说明书
                        </h4>
                        <div class="thumb-xs-box" id="thumbXsBox">
                            <div class="thumb-xs-list item-photo-add" >
                                <input type="hidden" name="limitSize" value="10">
                                <a href="javascript:void(0);" class="thumbnail" title="添加附件">
                                    <input type="file" class="btn-flie file-control" data-limit="10" multiple="multiple"  data-name="photo-shinei" >
                                    <input type="hidden" name="fileTypeId" value="1050" />
                                    <input type="hidden" name="fileSourceId" value="14" />
                                    <input type="hidden" name ="companyId" value="">
                                </a>
                            </div>
                        </div>
                        <i class="fontset">注：需店东签字，盖章。</i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        var options = {
            "url":BASE_PATH + '/file/uploadCommonFile',
            "isDeleteImage":false,//删除时校验checkEditImage
            "isAddImage":false,   //添加时校验checkEditImage
            "isCommonFile":true  //公共上传文件
        };
        photoUploader(options,null,null,null);
    });
</script>
