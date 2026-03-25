<%@page import="com.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
    body {
        margin: 0;
        font-family: Arial, sans-serif;
        background-color: #121212;
        color: #ffffff;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    header {
        width: 100%;
        background-color: #1f1f1f;
        padding: 15px;
        text-align: center;
        font-size: 22px;
        font-weight: bold;
        color: #00adb5;
        box-shadow: 0 2px 5px rgba(0,0,0,0.5);
    }

    .card {
        margin-top: 60px;
        background-color: #1e1e1e;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0,0,0,0.6);
        width: 320px;
        text-align: center;
    }

    h2 {
        margin-bottom: 20px;
        color: #00adb5;
    }

    h3 {
        margin: 10px 0;
        font-weight: normal;
    }

    a {
        display: inline-block;
        margin-top: 20px;
        padding: 10px 20px;
        text-decoration: none;
        color: #ffffff;
        background-color: #00adb5;
        border-radius: 8px;
        transition: 0.3s;
    }

    a:hover {
        background-color: #007b80;
        transform: scale(1.05);
    }
</style>

</head>
<body>

<header>
    User Dashboard
</header>



<div class="card">
<%
User user = (User) session.getAttribute("session");
%>
    <h2>Welcome</h2>
    
    <h3>Name: <%=user.getName() %></h3>
    <h3>Age: <%=user.getAge() %></h3>
    <h3>Email: <%=user.getEmail() %></h3>
    <h3>City: <%=user.getCity() %></h3>
    
    <a href="logout">Logout</a>
</div>

</body>
</html>