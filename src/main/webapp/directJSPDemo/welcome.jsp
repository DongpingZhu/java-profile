<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body>
<%
    String name = request.getParameter("username");
    String url = request.getParameter("url");
    out.print("欢迎" + name + "，我们的网址是：" + url);
%>
</body>
</html>
