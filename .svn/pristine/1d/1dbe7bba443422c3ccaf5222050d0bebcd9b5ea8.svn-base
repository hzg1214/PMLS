<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="/WEB-INF/pages/common/common.jsp" %>
    <script src="http://api.map.baidu.com/api?v=2.0&ak=${sysConfig.baiduApiKey}" type="text/javascript"></script>
    <title>跟进详情</title>
    <style type="text/css">
        body {
            padding: 0px;
            overflow: hidden;
        }

        dl {
            max-height: 180px !important;
        }

        .lable-left{
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
        }
        .lable-right{
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            padding: 10px 0;
        }

    </style>
<body>
<script type="application/javascript">
    var followDetail = ${followDetail};
</script>

<div class="layui-card">
        <div class="layui-card-body">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                    <legend>基本信息</legend>
                </fieldset>
                <div class="layui-tab-content" >
	                <div class="layui-row">
	                    <div class="layui-col-xs2 lable-right">跟进主题：</div>
	                    <div class="layui-col-xs4 lable-left">${followDetail.title}</div>
	                    <div class="layui-col-xs2 lable-right">跟进人：</div>
	                    <div class="layui-col-xs4 lable-left">${followDetail.userNameCreate}</div>
	                </div>
	                <div class="layui-row">
	                    <div class="layui-col-xs2 lable-right">跟进日期：</div>
	                    <div class="layui-col-xs4 lable-left">${followDetail.dateCreate}</div>
	                    <div class="layui-col-xs2 lable-right">跟进类型：</div>
	                    <div class="layui-col-xs4 lable-left">${followDetail.followTypeName}</div>
	                </div>
	                <c:if test="${followDetail.signTime != null and not empty followDetail.signTime}">
		                <div class="layui-row">
		                    <div class="layui-col-xs2 lable-right">签到时间：</div>
		                    <div class="layui-col-xs10 lable-left">${followDetail.signTime}</div>
		                </div>
		                <div id="signTimeImg">
			                <div class="layui-row">
			                    <div class="layui-col-xs2 lable-right">签到图片：</div>
						                <div class="layui-col-xs10 lable-left">
						                    <div class="layui-upload-list upload_img_list">
						
						                    </div>
						                </div>
					        </div>
		                </div>
	                </c:if>
	                <c:if test="${followDetail.signOutTime != null and not empty followDetail.signOutTime}">
		                <div class="layui-row">
		                    <div class="layui-col-xs2 lable-right">签退时间：</div>
		                    <div class="layui-col-xs10 lable-left">${followDetail.signOutTime}</div>
		                </div>
		                <div class="layui-row">
		                    <div class="layui-col-xs2 lable-right">签退图片：</div>
		                    <div id="signOutTimeImg">
					                <div class="layui-col-xs10 lable-left">
					                    <div class="layui-upload-list upload_img_list">
					
					                    </div>
					                </div>
					        </div>
		                </div>
	                </c:if>
	                <c:if test="${followDetail.longitude != null and not empty followDetail.longitude}">
		                <div class="layui-row">
		                	<input type="hidden" id="longitude" value="${followDetail.longitude}">
		                    <input type="hidden" id="latitude" value="${followDetail.latitude}">
		                    <div class="layui-col-xs2 lable-right">跟进位置：</div>
		                    <div class="layui-col-xs10 lable-left">
		                    	<div style="width:600px;height:200px;border:#ccc solid 1px;border-radius: 4px;font-size:12px;display:inline-block" id="map"></div>
		                    </div>
		                </div>
	                </c:if>
	                <div class="layui-row">
	                    <div class="layui-col-xs2 lable-right">跟进门店：</div>
	                    <div class="layui-col-xs4 lable-left">${followDetail.storeName}</div>
	                    <div class="layui-col-xs2 lable-right">跟进内容：</div>
	                    <div class="layui-col-xs4 lable-left">${followDetail.content}</div>
	                </div>
	            </div>
    </div>
</div>
<script>
    layui.use(['element', 'laydate', 'layedit', 'form', 'table', 'layer', 'upload'], function () {
        var element = layui.element,
            form = layui.form,
            layer = layui.layer,
            upload = layui.upload,
            $ = layui.$;
        var signTime = followDetail.signTime;
        var signOutTime = followDetail.signOutTime;
		if(signTime!='' && signTime!=null && signTime!=undefined){
	        loadImageList("signTimeImg", followDetail.signTimeFiles, false);
		}
		if(signOutTime!='' && signOutTime!=null && signOutTime!=undefined){
        	loadImageList("signOutTimeImg", followDetail.signOutTimeFiles, false);
		}

    });
</script>
<script type="text/javascript" src="${ctx}/meta/pmls/js/store/followMap.js?_v=${vs}"></script>
</body>
</html>
