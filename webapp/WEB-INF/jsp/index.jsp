<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/extjs/4.2.1/resources/css/ext-all.css"/>
	
	<!-- App Files -->
    <link rel="stylesheet" type="text/css" href="resources/portal.css">
	
	<script type="text/javascript" src="/extjs/4.2.1/bootstrap.js"></script>
	<script type="text/javascript" src="/extjs/4.2.1/locale/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="/app3.js?20"></script>
  </head>
  
  <body>
    
  </body>
</html>
