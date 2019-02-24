<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<% String msg = (String)request.getAttribute("msg");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
<!--     <link rel="stylesheet" type="text/css" media="screen" href="main.css">
    <script src="main.js"></script> -->
</head>
<body>
    <form  id="registration" method="post">
        <div>
            <label for="name">Name:</label>
            <input type="text" name="name" id="name" value="" autocomplete="off" autofocus="autofocus">
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="text" name="email" id="email" value="" autocomplete="off" autofocus="autofocus">
        </div>
        <div>
            <label for ="password">Password:</label>
            <input type="password" name="password" value="" id="password">
        </div>
        <div>
            <label for ="password_again">Password again:</label>
            <input type="password" name="password_again" value="" id="password_again">
        </div>
        <input type="submit" value="Sign up">
    </form>
    <%if(msg != null){%>
        <span><%=msg%></span>
    <%}%>
</body>
</html>