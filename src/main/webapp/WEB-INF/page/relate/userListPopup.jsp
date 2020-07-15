<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>


<script>

$(function() {

	// 初始化查询
	RelateUser.initList();
});

/** ***************************************************** */

</script>



  <!--  <div class="fs14 tanchuang-pannel " role="main"> -->
   <div class="" role="main">
   
   <span class="" style="float:right"><a href="javascript:RelateUtil.close();" class="btn btn-default">&times;</a></span>
   
        <div class="row">
            <div class="page-content" style="">
                <h4><strong>员工信息</strong></h4>
                
                <!-- 搜索条件区域 -->
                <form id="relateUserForm">
                
                    <!-- 默认排序字段、排序类型 -->
                    <input type="hidden" id="orderName" name="orderName" value="dateCreate" >
    				<input type="hidden" id="orderType" name="orderType" value="DESC" >
                
	                <ul class="list-inline form-inline pdb20">
	                    <li>
	                        <div class="form-group">
                                <label class="">员工编号</label>
                                <input type="text" class="form-control" id="userCode" name="userCode">
                            </div>
                            <div class="form-group">
                                <label class="">员工姓名</label>
                                <input type="text" class="form-control" id="userName" name="userName">
                            </div>
                            <button type="button" class="btn btn-primary" onclick="javascript:RelateUser.search();">搜索</button>
	                    </li>
	                </ul>
                
               
               <!-- 动态加载区域块  begin -->
                              <!-- 异步加载列表内容 -->
                <div id="load_content">
		           <div id="LoadCxt"></div>
		        </div>
               </form>
            </div>
            
        </div>
    </div>