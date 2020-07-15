<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/meta/css/upload.css?_v=${vs}">
<script type="text/javascript" src="${ctx}/meta/js/common/upload.js?_v=${vs}"></script>
<form id="keFuOrderReplyForm" style="height: auto;width: 800px;" onkeydown="if(event.keyCode==13){return false;}">
	<input type="hidden" name="orderId" value="${reqMap.id}" >
	<input type="hidden" name="userCode" value="${reqMap.userCode}" >
	<input type="hidden" name="fileRecordMainIds" id="fileRecordMainIds"  />
	<div role="main">
		<div class="row">
			<div class="page-content">
				<a href="javascript:keFuOrder.closeReplyPopup();" class="btn btn-default" style="float: right;margin-top: -10px;">&times;</a>
				<h4 class="border-bottom pdb10" style="padding-left:20px"><strong>工单回复</strong></h4>
				<span class="fc-warning" id="errorReplayMsg" style="padding-left:20px"></span>
				<table class="table-sammary">
					<tr class="list-inline form-inline">
						<td>
							<label class="fw-normal w125x text-right" style="vertical-align: top;margin-left: 35px"  for="replyDesc"><i>* </i>回复内容：</label>
							<textarea name="replyDesc" id="replyDesc" notnull="true" style="width:550px; height:150px"></textarea>
							<span class="fc-warning"  id="replyDescWarningMsg"></span>
						</td>
					</tr>
					<tr>
							<td>
								<div class="" role="main">
									<div>
										<div class="pd10" style="padding-left: 41px;">
											<label class="fw-normal w125x text-right" style="vertical-align: top;margin-left: 35px"  for="fileAccountChange">附件：</label>
											<div class="thumb-xs-box" id="fileAccountChange"  style="margin-left: 79px;">
												<div class="thumb-xs-list item-photo-add">
													<input type="hidden" name="limitSize" value="10">
													<a href="javascript:void(0);" class="thumbnail" title="添加附件">
														<input type="file" class="btn-flie file-control" data-limit="10"  multiple="multiple">
														<input type="hidden" name="fileTypeId" value="1059" />
														<input type="hidden" name="fileSourceId" value="17" />
														<input type="hidden" name ="companyId" value="">
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
					<tr>
						<td>
							  <label class="fw-normal w125x " style="margin-left: 35px"  for="dealStatus"><i>* </i>问题状态：</label>
							  <select class="form-control" title="" id="dealStatus" notnull="true" name="dealStatus" required readonly style="width: 148px;">
								  <option value="">请选择</option>
								  <option value="24202">处理中</option>
								  <option value="24203">处理完成</option>
							  </select>
							  <span class="fc-warning" id="dealStatusWarningMsg"></span>
						 </td>
                    </tr>
				</table>

				<div class="text-center">
                <a href="javascript:keFuOrder.doSave('${reqMap.id}')" class="btn btn-primary mgt20">　保存　</a>
				<a href="javascript:keFuOrder.closeReplyPopup()" class="btn btn-default mgt20 mgl50">　取消　</a>
            	</div>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	//上传开始
    var options = {
        "url":BASE_PATH + '/file/uploadCommonFile',
        "isDeleteImage":false,//删除时校验checkEditImage
        "isAddImage":true,   //添加时校验checkEditImage
        "isCommonFile":true  //公共上传文件
    };
    photoUploader(options,null,null,null);
    
});
function checkEditImage(files){
    var bol = true;
    var fileSize = 0;

    for( var i = 0 ; i < files.length ; i++ ){
        fileSize = files[i].size;
        var photoExt = files[i].name.substr(files[i].name.lastIndexOf(".")).toLowerCase();// 获得文件后缀名
        if (photoExt != '.jpg' && photoExt != '.png') {
            Dialog.alertInfo("请上传后缀名为jpg、png的文件!");
            systemLoaded();
            bol = false;
            return bol;
        }

        if (fileSize > 5000*1024) {
            Dialog.alertInfo("所选文件不能大于5M！");
            self.value = null;
            systemLoaded();
            bol = false;
            return bol;
        }
    }

    return bol;
}

</script>