<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@include file="common/tag.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
   <head>
      <title>秒杀列表</title>
      <%@include file="common/head.jsp" %>
      
   </head>
   <body>
    <!-- 页面显示部分 -->  
    <div class="container">
    	<div class="panel panel-default text-center">
    		<div class="panel-heading ">
    		
    		</div>
    		<div class="panel-body">
    		<table class="table table-hover">
    		<thead>
    		<tr>
    		<th>名称</th>
    		<th>库存</th>
    		<th>开始时间</th>
    		<th>结束时间</th>
    		<th>创建时间</th>
    		<th>详情页</th>
    		</tr>
    		</thead>
    		<tbody>
    		<c:forEach var="sk" items="${ list }">
    		<tr>
    		<td>${sk.name }</td>
    		<td>${sk.number}</td>
    		<td>
    		<fmt:formatDate value="${sk.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    		</td>
    		<td>
    		<fmt:formatDate value="${sk.endTime }"/>
    		</td>
    		<td>
    		<fmt:formatDate value="${sk.createTime }"/>
    		</td>
    		<td>
    		<a class="btn btn-info" href="/seckill/${sk.seckillId}/detail " target="_blank">link</a>
    		</td>
    		</tr>
    		</c:forEach>
    		</tbody>
    		</table>
    		
    		</div>
    		</div>
    </div>
 
      <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
 
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   </body>
</html>