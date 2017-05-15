<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@include file="common/tag.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
   <head>
      <title>秒杀详情</title>
      <%@include file="common/head.jsp" %>
   </head>
   <body>
    <div class="container">
    	<div class="panel panel-default text-center">
    		<div class="panel-heading " >
    		${seckill.name }
    		
    		</div>
    		<div class="panel-body"></div>
    		</div>
    	
 
      <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
 
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   </body>
</html>