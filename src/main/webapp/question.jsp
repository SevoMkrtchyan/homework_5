<%--
  Created by IntelliJ IDEA.
  User: sevo
  Date: 21.11.21
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.poll.model.Question" %>
<%@ page import="com.example.poll.model.Poll" %>
<%@ page import="com.example.poll.model.Answer" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questions</title>
</head>
<body>
<%List<Question> questions = (List<Question>) request.getAttribute("questions");%>
<%Poll poll = (Poll) request.getAttribute("poll");%>
<%Map<Integer, Integer> map = new HashMap<>();%>
<h1 style="color: #8c8484"><%=poll.getName()%>
</h1>
<h3><%=poll.getDescription()%>
</h3>
<form action="/answers">
    <input type="hidden" name="pollId" value="<%=poll.getId()%>">
    <%for (Question question : questions) { %>
    <h4><%=question.getText()%>
    </h4>
    <%for (Answer answer : question.getAnswers()) {%>
    <input type="radio" id="radio" name="question<%=question.getId()%>" value="<%=answer.getWeight()%>">
    <label for="radio"><%=answer.getText()%></label><br>
    <br>
    <%}%>
    <%}%>
    <input type="submit" value="Submit">
</form>
</body>
</html>
