<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common/taglib.jsp"%>
<script>
$(function() {
	// 初始化查询
	EstateAdd.initList();
});
</script>
<div class="" role="main">

	<span style="position: absolute;right:10px;display:block"><a href="javascript:EstateAdd.close();" class="btn btn-default">&times;</a></span>

    <div class="row">
        <div class="page-content">
        	<h4><strong>选择楼盘</strong></h4>
            
            <!-- 搜索条件区域 -->
            <form id="selectFromOpForm">
                <input type="hidden" name="estatePosition" value="${estatePosition}">
             <ul class="list-inline form-inline pdb20">
                 <li>
                    <div class="form-group">
                        <label class="">楼盘名称</label>
                        <input type="text" class="form-control" id="estateName" name="estateName">
                    </div>
                 	<div class="form-group">
                        <label class="">楼盘地址</label>
                        <input type="text" class="form-control" id="addr" name="addr">
                    </div>
                     <button type="button" class="btn btn-primary" onclick="javascript:EstateAdd.search();">搜索</button>
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