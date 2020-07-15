<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>
<div class="box box-primary">
    <div class="s-scoll-x" style="height: 410px;font-size: 12px;">
        <div class="box-body">
            <table class="table table-bordered table-hover table-striped text-center" >
                <tr>
                    <th style="width: 50px;">
                        <input type="checkbox" onclick="swapCheck()"/>
                    </th>
                    <th style="width: 60px;">
                        序号
                    </th>
                    <th style="width: 110px;">
                        工号
                    </th>
                    <th style="width: 110px;">
                        姓名
                    </th>
                    <th style="width: 110px;">
                       模块编号
                    </th>
                    <th style="width: 110px;">
                        模块名称
                    </th>
                    <th style="width: 110px;">
                      是否显示
                    </th>
                    <th style="width: 110px;">
                     是否可点击
                    </th>
                    <th style="width: 110px;">
                       状态
                    </th>
                    <th style="width: 150px;">
                        创建时间
                    </th>
                </tr>
                <c:forEach items="${contentlist}" var="list" varStatus="status">
                    <tr>
                        <td>
                            <input type="checkbox" value="${list.id}" class="selectPeople">
                        </td>
                        <td>${status.index + 1}</td>
                        <td>${list.userCode}</td>
                        <td>${list.userName}</td>
                        <td>${list.functionCode}</td>
                        <td>${list.functionName}</td>
                        <td> <c:if test="${list.isShow == '10'}">
                            是
                        </c:if>
                            <c:if test="${list.isShow == '20'}">
                           否
                            </c:if>
                        </td>

                        <td> <c:if test="${list.isClick == '10'}">
                           是
                        </c:if>
                            <c:if test="${list.isClick == '20'}">
                               否
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${list.status == '0'}">
                                有效
                            </c:if>
                            <c:if test="${list.status == '1'}">
                                无效
                            </c:if>
                        </td>
                        <td>${list.createDate}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
${pageInfo.html}

<c:if test="${fn:length(contentlist) le 0}">
    <div class="nodata">
        <i class="icon-hdd"></i>
        <p>暂无数据...</p>
    </div>
</c:if>