<%@ page import="com.example.poll.model.Result" %><%--
  Created by IntelliJ IDEA.
  User: sevo
  Date: 22.11.21
  Time: 01:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<%Result result = (Result) request.getAttribute("result");%>
<h1 style="color: #8c8484">Результаты опроса</h1>
<h3>
    <%if (result!= null){%>
    <%=result.getExplanation()%>
    <%} else {%>
    <p>Result</p>
    <%}%>
</h3>

<button style="background-color: chocolate" onclick="window.location.href='/'">К списку опросов</button>
</body>
</html>
