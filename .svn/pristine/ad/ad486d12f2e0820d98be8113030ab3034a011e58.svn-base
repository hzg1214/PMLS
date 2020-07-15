<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/page/common/taglib.jsp" %>

<div class="s-scoll-y" style="font-size: 12px;">
    <div class="box box-primary">
        <div class="box-body">
            <table class="table table-bordered table-hover table-striped text-center">

                <tr>
                    <c:forEach items="${columnlist}" var="list">
                        <c:if test="${not empty list.colTitle}">
                            <th colspan="${list.mergeColNum}">${list.colTitle}</th>
                        </c:if>
                    </c:forEach>
                </tr>

                <c:forEach items="${contentlist}" var="list" varStatus="status">
                    <tr>
                        <c:if test="${status.index == 0}">
                            <td rowspan="39" style="vertical-align: middle">${list.centerName}</td>
                            <td rowspan="18" style="vertical-align: middle">${list.bizType}</td>
                            <td colspan="2">${list.bizStage}</td>
                        </c:if>
                        <c:if test="${status.index == 1}">
                            <td rowspan="3" style="vertical-align: middle">${list.bizStage}</td>
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index >= 2 && status.index <= 3}">
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 4}">
                            <td rowspan="4" style="vertical-align: middle">${list.bizStage}</td>
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index >= 5 && status.index <= 7}">
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 8}">
                            <td rowspan="2" style="vertical-align: middle">${list.bizStage}</td>
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 9}">
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 10}">
                            <td rowspan="4" style="vertical-align: middle">${list.bizStage}</td>
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index >= 11 && status.index <= 13}">
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 14}">
                            <td rowspan="2" style="vertical-align: middle">${list.bizStage}</td>
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 15}">
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 16}">
                            <td rowspan="2" style="vertical-align: middle">${list.bizStage}</td>
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 17}">
                            <td>${list.subjName}</td>
                        </c:if>

                        <c:if test="${status.index == 18}">
                            <td rowspan="3" style="vertical-align: middle">${list.bizType}</td>
                            <td colspan="2">${list.bizStage}</td>
                        </c:if>
                        <c:if test="${status.index >= 19 && status.index <= 20}">
                            <td colspan="2">${list.subjName}</td>
                        </c:if>

                        <c:if test="${status.index == 21}">
                            <td rowspan="8" style="vertical-align: middle">${list.bizType}</td>
                            <td colspan="2">${list.bizStage}</td>
                        </c:if>
                        <c:if test="${status.index >= 22 && status.index <= 28}">
                            <td colspan="2">${list.subjName}</td>
                        </c:if>

                        <c:if test="${status.index == 29}">
                            <td rowspan="10" style="vertical-align: middle">${list.bizType}</td>
                            <td colspan="2">${list.bizStage}</td>
                        </c:if>
                        <c:if test="${status.index == 30}">
                            <td colspan="2">${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 31}">
                            <td rowspan="2" style="vertical-align: middle">${list.bizStage}</td>
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 32}">
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 33}">
                            <td rowspan="2" style="vertical-align: middle">${list.bizStage}</td>
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 34}">
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 35}">
                            <td rowspan="2" style="vertical-align: middle">${list.bizStage}</td>
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 36}">
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 37}">
                            <td rowspan="2" style="vertical-align: middle">${list.bizStage}</td>
                            <td>${list.subjName}</td>
                        </c:if>
                        <c:if test="${status.index == 38}">
                            <td>${list.subjName}</td>
                        </c:if>


                        <c:if test="${length >= 1}">
                            <c:if test="${list.col1 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col1 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col1}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col1}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 2}">
                            <c:if test="${list.col2 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col2 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col2}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col2}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 3}">
                            <c:if test="${list.col3 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col3 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col3}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col3}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 4}">
                            <c:if test="${list.col4 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col4 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col4}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col4}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 5}">
                            <c:if test="${list.col5 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col5 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col5}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col5}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 6}">
                            <c:if test="${list.col6 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col6 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col6}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col6}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 7}">
                            <c:if test="${list.col7 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col7 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col7}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col7}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 8}">
                            <c:if test="${list.col8 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col8 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col8}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col8}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 9}">
                            <c:if test="${list.col9 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col9 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col9}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col9}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 10}">
                            <c:if test="${list.col10 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col10 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col10}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col10}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 11}">
                            <c:if test="${list.col11 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col11 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col11}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col11}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 12}">
                            <c:if test="${list.col12 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col12 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col12}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col12}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 13}">
                            <c:if test="${list.col13 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col13 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col13}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col13}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 14}">
                            <c:if test="${list.col14 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col14 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col14}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col14}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 15}">
                            <c:if test="${list.col15 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col15 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col15}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col15}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 16}">
                            <c:if test="${list.col16 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col16 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col16}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col16}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 17}">
                            <c:if test="${list.col17 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col17 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col17}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col17}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>

                        <c:if test="${length >= 18}">
                            <c:if test="${list.col18 == '0.00'}">
                                <td style="text-align: right">-</td>
                            </c:if>
                            <c:if test="${list.col18 != '0.00'}">
                                <c:if test="${list.rowType == 'int'}">
                                    <td style="text-align: right"><fmt:parseNumber integerOnly="true" value="${list.col18}"/></td>
                                </c:if>
                                <c:if test="${list.rowType == 'money'}">
                                    <td style="text-align: right"><fmt:formatNumber value="${list.col18}" pattern="#,##0.00"/></td>
                                </c:if>
                            </c:if>
                        </c:if>
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
