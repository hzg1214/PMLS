<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/page/common/meta.jsp" %>
    <%@ include file="/WEB-INF/page/common/jsCss.jsp" %>
</head>
<body class="hold-transition fixed skin-blue sidebar-mini">

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"
            onclick="javascript:FollowDetail.close();">
        <span>&times;</span>
    </button>
    <h4 class="modal-title">签到详情</h4>
</div>
<div class="modal-body" style="width:700px;height:450px;overflow-y:scroll; padding-top:0;">
    <div class="box-body" style="padding-top:0;">
        <table class="table-sammary">
            <col style="width:75px;">
            <col style="width:auto;">
            <col style="width:75px;">
            <col style="width:auto;">

            <tr>
                <td class="talabel">门店编号：</td>
                <td>${contentlist.storeNo}</td>
                <td class="talabel">店招编号：</td>
                <td>${contentlist.signageNo}</td>
            </tr>

            <tr>
                <td class="talabel">门店名称：</td>
                <td>${contentlist.storeName}</td>
                <td class="talabel">门店地址：</td>
                <td>${contentlist.storeAddress}</td>
            </tr>

            <tr>
                <td class="talabel">跟进人：</td>
                <td>${contentlist.follower}</td>
                <td class="talabel">跟进时间：</td>
                <td>${contentlist.followDate}</td>
            </tr>

            <tr>
                <td class="talabel">跟进目的：</td>
                <td colspan="3">${contentlist.followAim}</td>
            </tr>

            <tr>
                <td class="talabel">跟进描述：</td>
                <td colspan="3">${contentlist.followDesc}</td>
            </tr>

            <tr>
                <td colspan="4" style="border-bottom: 1px solid #e5e5e5;color:#666;padding-left: 3px;">跟进图片：</td>
            </tr>
            <tr>
                <td colspan="4">
                    <span id="showImg">
                        <c:if test="${empty contentlist.followPictureList}">
                            <a href="javascript:void(0)"
                               target=" _blank"
                               style="display: inline-block;  height: auto; vertical-align: -webkit-baseline-middle;">

                                <img src="${ctx}/meta/images/noPIC.jpg" alt="" class="img"> <br/>
                            </a>
                        </c:if>

                        <c:if test="${not empty contentlist.followPictureList}">
                            <table class="table-sammary">
                                <col style="width:200px;">
                                <col style="width:200px;">
                                <col style="width:200px;">
                                <tr>
                                    <c:forEach items="${contentlist.followPictureList}" var="list" varStatus="status">
                                    <td>
                                        <a href="<c:if test="${empty list.bigPictureUrl}">javascript:void(0)</c:if><c:if test="${not empty list.bigPictureUrl}">${list.bigPictureUrl}</c:if>"
                                           target=" _blank"
                                           style="display: inline-block;  height: auto; vertical-align: -webkit-baseline-middle;">
                                            <img src="<c:if test="${empty list.smallPictureUrl}">${ctx}/meta/images/noPIC.png</c:if><c:if test="${not empty list.smallPictureUrl}">${list.smallPictureUrl}</c:if>"
                                                 alt="" class="img"> <br/>
                                        </a>
                                    </td>
                                    <c:if test="${status.count % 3 == 0}" var="test" scope="page">
                                </tr>
                                <tr>
                                    </c:if>
                                    </c:forEach>
                                </tr>
                            </table>
                        </c:if>
                    </span>
                </td>
            </tr>

            <c:if test="${contentlist.decorateStatus == '16304'}">
            <tr>
                <td colspan="4" style="border-bottom: 1px solid #e5e5e5;color:#666;padding-left: 3px;">翻牌后图片：</td>
            </tr>
            <tr>
                <td colspan="4">
                    <span id="showImg">
                        <c:if test="${empty contentlist.decorationPictureList}">
                            <a href="javascript:void(0)"
                               target=" _blank"
                               style="display: inline-block;  height: auto; vertical-align: -webkit-baseline-middle;">

                                <img src="${ctx}/meta/images/noPIC.jpg" alt="" class="img"> <br/>
                            </a>
                        </c:if>

                        <c:if test="${not empty contentlist.decorationPictureList}">
                            <table class="table-sammary">
                                <col style="width:200px;">
                                <col style="width:200px;">
                                <col style="width:200px;">
                                <tr>
                                    <c:forEach items="${contentlist.decorationPictureList}" var="list" varStatus="status">
                                    <td>
                                        <a href="<c:if test="${empty list.bigPictureUrl}">javascript:void(0)</c:if><c:if test="${not empty list.bigPictureUrl}">${list.bigPictureUrl}</c:if>"
                                           target=" _blank"
                                           style="display: inline-block;  height: auto; vertical-align: -webkit-baseline-middle;">
                                            <img src="<c:if test="${empty list.smallPictureUrl}">${ctx}/meta/images/noPIC.png</c:if><c:if test="${not empty list.smallPictureUrl}">${list.smallPictureUrl}</c:if>"
                                                 alt="" class="img"> <br/>
                                        </a>
                                    </td>
                                    <c:if test="${status.count % 3 == 0}" var="test" scope="page">
                                </tr>
                                <tr>
                                    </c:if>
                                    </c:forEach>
                                </tr>
                            </table>
                        </c:if>
                    </span>
                </td>
            </tr>
            </c:if>

        </table>

    </div>
</div>

<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="FollowDetail.close();">返回</button>
</div>

</body>
</html>
