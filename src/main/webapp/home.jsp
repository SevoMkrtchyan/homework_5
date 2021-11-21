<%@ page import="com.example.poll.model.Poll" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Poll</title>
</head>
<body>
<%List<Poll> all = (List<Poll>) request.getAttribute("all");%>
<h1 style="color: #8c8484">Выберите Опрос</h1>
<ul>
    <% for (Poll poll : all) {%>
    <li><a href="/questions?id=<%=poll.getId()%>"><%=poll.getName()%></a>
    </li>
    <% }%>
</ul>
</body>
</html>