<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<body>
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
                onclick="javascript:BizStop.close();">
            <span>&times;</span>
        </button>
        <h4 class="modal-title">门店停业上报详情</h4>
    </div>
    <div class="container">
        <div class="row article">
            <div class="col-md-12 content">
                <div class="page-content">
                    <h4>
	                    <strong>门店信息</strong>
	                    <c:if test="${detail.status==21001}">
	                        <a type="button" class="btn btn-primary" href="javascript:void(0)" onclick="BizStopAud.auditReturn(${detail.stopId},${detail.storeId},'${detail.name}',${detail.userCreate},'${detail.address}');" style="margin-right:10px;">驳回</a>
	                        <a type="button" class="btn btn-primary" href="javascript:void(0)" onclick="BizStopAud.auditPass(${detail.stopId},${detail.storeId},'${detail.name}',${detail.userCreate},'${detail.address}');" style="margin-right:10px;">审核通过</a>
	                    </c:if>
                    </h4>
                    <table class="table-sammary">
                        <col style="width:150px;">
                        <col style="width:auto;">
                        <col style="width:150px;">
                        <col style="width:auto;">
                        <tr>
                            <td class="talabel">门店编号：</td>
                            <td>${detail.storeNo}</td>
                            <td class="talabel">门店名称：</td>
                            <td>${detail.name}</td>
                        </tr>
                        <tr>
                            <td class="talabel">门店地址：</td>
                            <td>${detail.address}</td>
                            <td class="talabel">纪经人数：</td>
                            <td>${detail.storePersonNumName}</td>
                        </tr>

                        <tr>
                            <td class="talabel">门店负责人：</td>
                            <td>${detail.storeManager}</td>
                            <td class="talabel">负责人电话：</td>
                            <td>${detail.storeManagerPhone}</td>
                        </tr>
                        <tr>
                            <td class="talabel">门店所属中心：</td>
                            <td>${detail.groupName}</td>
                            <td class="talabel">门店资质等级：</td>
                            <td>${detail.abTypeStoreName}<c:if test="${detail.bTypeStoreName ne null and detail.bTypeStoreName ne ''}">(${detail.bTypeStoreName})</c:if></td>
                        </tr>
                        <tr>
                            <td class="talabel">所属公司：</td>
                            <td>${detail.companyName}</td>
                            <td class="talabel">合作模式：</td>
                            <td>${detail.contractTypeName}</td>
                        </tr>
                        <tr>
                            <td class="talabel">合同周期：</td>
                            <td>${detail.dateLifeStart}<c:if test="${detail.dateLifeEnd ne null}">至</c:if>${detail.dateLifeEnd}</td>
                            <td class="talabel">装修进度：</td>
                            <td>${detail.decorationStatusName}</td>
                        </tr>
						<tr>
							<td colspan="4">
							     <h4><strong>停业信息</strong></h4>
							</td>
						</tr>
						<tr>
                            <td class="talabel">门店营业状态：</td>
                            <td>${detail.bussinessStatusName}</td>
                            <td class="talabel">停业原因：</td>
                            <td>${detail.stopReasonName}</td>
                        </tr>
						<tr>
                            <td class="talabel">停业描述：</td>
                            <td colspan="3">${detail.followDetail}</td>
                        </tr>
                        <tr>
                            <td class="talabel">门店维护人：</td>
                            <td>${detail.maintainerName}</td>
                            <td class="talabel">上报时间：</td>
                            <td>${detail.dateCreate}</td>
                        </tr>
                        <tr>
                            <td class="talabel">审核人：</td>
                            <td>${detail.auditUserName}</td>
                            <td class="talabel">审核时间：</td>
                            <td>${detail.auditTime}</td>
                        </tr>
                        <c:if test="${detail.status eq 21003}">
	                        <tr>
	                            <td class="talabel">驳回原因：</td>
	                            <td colspan="3">${detail.auditDetail}</td>
	                        </tr>
                        </c:if>
						<tr>
                            <td colspan="4">
                                <div class="" role="main">
                                    <div>
                                        <div class="pd10" name="Viewerbox">
                                            <h4><strong>停业照片</strong></h4>
                                            <div class="thumb-xs-box" id="thumbXsBox">
                                                <c:if test="${not empty detail.storePicList }">
                                                    <c:set var="fileSize" value="0" />
                                                    <c:forEach items="${detail.storePicList}" var="list"
                                                        varStatus="status">
                                                        <c:set var="fileSize" value="${fileSize + 1}" />
                                                        <div class="thumb-xs-list item-photo-list">
                                                            <a href="javascript:void(0);"
                                                                class="thumbnail swipebox" target="_blank"> <span
                                                                class="thumb-img"> <span
                                                                    class="thumb-img-container"> <span
                                                                        class="thumb-img-content"> <img alt="门店图片列表" data-original="${list.middlePictureUrl}"
                                                                            src="${list.smallPictureUrl}" class="empPhoto" />
                                                                    </span>
                                                                </span>
                                                            </span>
                                                            </a> <input type="file" id="file${fileSize}"
                                                                class="btn-flie file-control hide" data-limit="10"
                                                                multiple="multiple" />
                                                        </div>
                                                    </c:forEach>
                                                </c:if>
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
<script type="text/javascript">
$(function(){
    $(document.getElementsByName("Viewerbox")).each(function(index){
        var viewer = new Viewer(this, {
            url: 'data-original',
            show: function (){
                viewer.update();
            }
        });
    })
});
$(document).ready(function(){
    downLoadFile();
});
function downLoadFile(){
    $(document).on("click", ".viewer-flip-download", function(){
        var src = $(".viewer-canvas").find("img").attr("src");
        var fileName = $(".viewer-canvas").find("img").attr("alt");
        //参数格式化
        fileName = formatOptions(fileName);
        if (src) {
            var link = document.createElement('a');
            link.href = BASE_PATH + "/files/downloadFile"+"?fileName=" + fileName + "&fileUrl=" + src;
            link.style.cssText = 'display:none;position:absolute;left:-9999px;top:-9999px;';
            document.body.appendChild(link);
            link.click();
            setTimeout(function () {
                document.body.removeChild(link);
            }, 5000);
        }
        return false;
    });
}
</script>
</html>
