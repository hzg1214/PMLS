<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>合作方详情</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .lable-left{
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            padding: 10px 0;
        }
        .lable-right{
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
        }
        .myForm .layui-form-label2{
            margin-left: 150px;
            min-height: 36px;
            margin-right: 20px;
            width: 450px;
            float:left
        }
        .myForm .layui-input-block{
            margin-left: 150px;
            min-height: 36px;
            margin-right: 20px;
            width: 450px;
        }
        .layui-tab-item .layui-table thead>tr{
            color:#999999;
        }
    </style>
</head>
<script type="application/javascript">
    var developerDto='${developerDto}';
</script>
<body>
<div>
    <div class="layui-card" style="height: 625px;">
        <div class="layui-card-body">
            <div style="margin: 0 auto;">
            	<div class="layui-row blockBody">
                    <div class="layui-col-xs6">
                        <div class="blockTitle">合作方详情</div>
                    </div>
                    <div class="layui-col-xs6 blockBtn">
                        <button type="button" class="layui-btn layui-btn-primary" onclick="back()">返回</button>
                    </div>
                </div>
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                    <legend>基本信息</legend>
                </fieldset>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">合作方编号：</div>
                    <div class="layui-col-xs10 lable-right">${developerDto.developerCode}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">合作方名称：</div>
                    <div class="layui-col-xs4 lable-right">${developerDto.developerName}</div>
                    <div class="layui-col-xs2 lable-left">统一社会信用代码：</div>
                    <div class="layui-col-xs4 lable-right">${developerDto.businessLicenseNo}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">注册地址：</div>
                    <div class="layui-col-xs10 lable-right">${developerDto.addressDetail}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">法定代表人：</div>
                    <div class="layui-col-xs4 lable-right">${developerDto.legalPerson}</div>
                    <div class="layui-col-xs2 lable-left">法人手机号码：</div>
                    <div class="layui-col-xs4 lable-right">${developerDto.contactNumber}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">联系人：</div>
                    <div class="layui-col-xs4 lable-right">${developerDto.dockingPeo}</div>
                    <div class="layui-col-xs2 lable-left">联系人手机号码：</div>
                    <div class="layui-col-xs4 lable-right">${developerDto.dockingPeoTel}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">合作方品牌：</div>
                    <div class="layui-col-xs4 lable-right">${developerDto.developerBrandName}</div>
                    <div class="layui-col-xs2 lable-left">是否大客户：</div>
                    <div class="layui-col-xs4 lable-right">
	                    <c:choose>
							<c:when test="${'22601' eq developerDto.bigCustomerFlag}">
								是
							</c:when>
							<c:otherwise>
								否
							</c:otherwise>
						</c:choose>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">是否垫佣甲方：</div>
                    <div class="layui-col-xs4 lable-right">
	                    <c:choose>
							<c:when test="${'1' eq developerDto.isYjDy}">
								是
							</c:when>
							<c:otherwise>
								否
							</c:otherwise>
						</c:choose>
                    </div>
                    <div class="layui-col-xs2 lable-left">合作方类型：</div>
                    <div class="layui-col-xs4 lable-right">${developerDto.partnerStr}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">备注：</div>
                    <div class="layui-col-xs6 lable-right">${developerDto.remark}</div>
                </div>
            </div>
        </div>
    </div>
</div>


</div>
<script>
//取消返回上一个页面
function back(){
    parent.redirectTo('delete',null,null);
}
</script>

</body>
</html>
