<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>首页</title>
</head>
<body>
hello ${userName} <br/>
<%= new Date().toString()%> <br/>

<% out.println("IP 地址：" + request.getRemoteAddr());%> <br/>


</body>
</html>
