
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<% out.print("Hello，编程帮！网址是：http://www.biancheng.net！");%>
</br>

<%! int num = 0; %> <!-- 声明变量 -->
<% out.print(num);%>
</br>

当前时间: <%= java.util.Calendar.getInstance().getTime() %>
</br>



</body>
</html>
