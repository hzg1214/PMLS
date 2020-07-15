<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>借佣详情</title>
    <%@include file="../common/common.jsp" %>
    <style type="text/css">
        .lable-left{
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            padding: 10px 0;
            width: 200px;
        }
        .lable-right{
            font-weight: 400;
            line-height: 20px;
            text-align: left;
            padding: 10px 0;
            width: 300px;
        }
        .layui-tab-item .layui-table thead>tr{
            color:#999999;
        }
        .layui-btn-mini {
            height: 22px;
            line-height: 22px;
            padding: 0 5px;
            font-size: 12px;
        }
        .progressItem span{
            color:#bdbdbd;
        }
        .progressItem .blockTitle{
            font-weight: bold;
            line-height: 32px;
            margin-left: 10px;
        }
        .progressItem.active span{
            color:#3b91ff;
        }
        .progressItem .image01{
            width: 32px;
            height: 32px;
            background-image: url(/meta/pmls/images/01.png);
            background-size: 100%;
            background-repeat: no-repeat;
            float:left;
        }
        .progressItem.active .image01{
            background-image: url(/meta/pmls/images/1.png);
        }

        .progressItem .image02{
            width: 32px;
            height: 32px;
            background-image: url(/meta/pmls/images/02.png);
            background-size: 100%;
            background-repeat: no-repeat;
            float:left;
        }
        .progressItem.active .image02{
            background-image: url(/meta/pmls/images/2.png);
        }

        .progressItem .image03{
            width: 32px;
            height: 32px;
            background-image: url(/meta/pmls/images/03.png);
            background-size: 100%;
            background-repeat: no-repeat;
            float:left;
        }
        .progressItem.active .image03{
            background-image: url(/meta/pmls/images/3.png);
        }


        .progressItem .image04{
            width: 32px;
            height: 32px;
            background-image: url(/meta/pmls/images/04.png);
            background-size: 100%;
            background-repeat: no-repeat;
            float:left;
        }
        .progressItem.active .image04{
            background-image: url(/meta/pmls/images/4.png);
        }


        .progressItem .image05{
            width: 32px;
            height: 32px;
            background-image: url(/meta/pmls/images/05.png);
            background-size: 100%;
            background-repeat: no-repeat;
            float:left;
        }
        .progressItem.active .image05{
            background-image: url(/meta/pmls/images/5.png);
        }

        .layui-form-item .layui-input-inline{
            width: 180px;
        }
        
        .fontClass{
            color: #a1a1a1;
        }

    </style>
</head>
<script type="application/javascript">
    var borrowMoneyDto='${borrowMoneyDto}';
</script>
<body>
<div>
    <div class="layui-card">
        <div class="layui-card-body">
            <div style="margin: 0 auto;">
                <div class="layui-row blockBody">
                    <div class="layui-col-xs6">
                        <div class="blockTitle">借佣申请详情</div>
                    </div>
                    <div class="layui-col-xs6 blockBtn">
                        <button type="button" class="layui-btn layui-btn-primary" onclick="back()">返回</button>
                    </div>
                </div>

                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 5px;">
                    <legend>基本信息</legend>
                </fieldset>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">申请编号：</div>
                    <div class="layui-col-xs10 lable-right">${borrowMoneyDto.jyapplyNo}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">渠道公司：</div>
                    <div class="layui-col-xs4 lable-right">${borrowMoneyDto.companyName}</div>
                    <div class="layui-col-xs2 lable-left">楼盘名称：</div>
                    <div class="layui-col-xs4 lable-right">${borrowMoneyDto.projectName}</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">借佣订单数：</div>
                    <div class="layui-col-xs4 lable-right">${borrowMoneyDto.detailNum}</div>
                    <div class="layui-col-xs2 lable-left">结算金额：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:mf2(borrowMoneyDto.jsTotalAmount)}元</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">借佣比例：</div>
                    <div class="layui-col-xs4 lable-right">${borrowMoneyDto.jyBl*100}%</div>
                    <div class="layui-col-xs2 lable-left">借佣金额：</div>
                    <div class="layui-col-xs4 lable-right">${sdk:mf2(borrowMoneyDto.jyTotalAmount)}元</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">借佣周期：</div>
                    <div class="layui-col-xs4 lable-right">${borrowMoneyDto.jyMonths}个月</div>
                    <div class="layui-col-xs2 lable-left">增值税税率：</div>
                    <div class="layui-col-xs4 lable-right">${borrowMoneyDto.taxRate*100}%</div>
                </div>
                <div class="layui-row">
                    <div class="layui-col-xs2 lable-left">资方：</div>
                    <div class="layui-col-xs4 lable-right">${borrowMoneyDto.zfName}</div>
                    <div class="layui-col-xs2 lable-left">借佣进度：</div>
                    <div class="layui-col-xs4 lable-right">${borrowMoneyDto.progressName}</div>
                </div>
            </div>

        </div>
    </div>
    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>借佣进度</legend>
            </fieldset>
            <div class="layui-form-item" style="margin-left: 60px;">
                <div class="layui-inline">
                    <div class="layui-input-inline progressItem
                    <c:if test="${borrowMoneyDto.progress >= 28201}">
	    	 		 	active
	    	 		</c:if>
                    ">
                        <div style="width:180px;height:80px;float:left;">
                            <div class="image01"></div>
                            <lable class="blockTitle">提交申请</lable>
                            <span class="">—————</span><br/>
                            <lable class="progressDate fontClass">
                                ${borrowMoneyDto.progressList[0].progressDate}
                            </lable>
                        </div>
                    </div>
                    <div class="layui-input-inline progressItem
                    <c:if test="${borrowMoneyDto.progress >= 28202}">
	    	 		 	active
	    	 		</c:if>">
                        <div style="width:180px;height:80px;float:left;">
                            <div class="image02"></div>
                            <lable class="blockTitle">业绩确认</lable>
                            <span class="">—————</span><br/>
                            <lable class="progressDate fontClass">
                                ${borrowMoneyDto.progressList[1].progressDate}
                            </lable>
                        </div>
                    </div>
                    <div class="layui-input-inline progressItem
                    <c:if test="${borrowMoneyDto.progress >= 28203}">
	    	 		 	active
	    	 		</c:if>">
                        <div style="width:180px;height:80px;float:left;">
                            <div class="image03"></div>
                            <lable class="blockTitle">借佣签约</lable>
                            <span class="">—————</span><br/>
                            <lable class="progressDate fontClass">
                                ${borrowMoneyDto.progressList[2].progressDate}
                            </lable>
                        </div>
                    </div>
                    <div class="layui-input-inline progressItem
                    <c:if test="${borrowMoneyDto.progress >= 28204}">
	    	 		 	active
	    	 		</c:if>">
                        <div style="width:180px;height:80px;float:left;">
                            <div class="image04"></div>
                            <lable class="blockTitle">请款</lable>
                            <span class="">———————</span><br/>
                            <lable class="progressDate fontClass">
                                ${borrowMoneyDto.progressList[3].progressDate}
                            </lable>
                        </div>
                    </div>
                    <div class="layui-input-inline progressItem
                    <c:if test="${borrowMoneyDto.progress >= 28205}">
	    	 		 	active
	    	 		</c:if>">
                        <div style="width:180px;height:80px;float:left;">
                            <div class="image05"></div>
                            <lable class="blockTitle">放款</lable><br/>
                            <lable class="progressDate fontClass">
                                ${borrowMoneyDto.progressList[4].progressDate}
                            </lable>
                        </div>
                    </div>
                </div>
            </div>

            <%--<div id="progressTable" lay-filter="progressTable"></div>--%>
            <table class="layui-table">
                <colgroup>
                    <col width="100">
                    <col width="200">
                    <col >
                </colgroup>
                <thead>
                <tr>
                    <th style="text-align: center;">序号</th>
                    <th style="text-align: center;">进度</th>
                    <th>备注</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${borrowMoneyDto.progressList}" var="dto" varStatus="index">
                    <tr>
                        <td style="text-align: center;">${dto.rowNum}</td>
                        <td style="text-align: center;">${dto.progressName}</td>
                        <td>${dto.remark}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:if test="${empty borrowMoneyDto.progressList}"><div class="nodata">暂无数据</div></c:if>
        </div>
    </div>
    <div class="layui-card">
        <div class="layui-card-body">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>借佣房源</legend>
            </fieldset>

            <div id="houseDetailTable" lay-filter="houseDetailTable"></div>
        </div>
    </div>
</div>


</div>
<script src="${pageContext.request.contextPath}/meta/pmls/js/borrowMoney/borrowMoneyDetailPage.js?v=${vs}"></script>

</body>
</html>
