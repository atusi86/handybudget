<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<% Boolean isValidPassword = (Boolean)request.getAttribute("isValidPassword");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Page Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
<!--     <link rel="stylesheet" type="text/css" media="screen" href="main.css">
    <script src="main.js"></script> -->
</head>
<body>
    <form  id="login" method="post">
        <div>
            <label for="email">Email:</label>
            <input type="text" name="email" id="email" value="" autocomplete="off" autofocus="autofocus">
        </div>
        <div>
            <label for ="password">Password:</label>
            <input type="password" name="password" value="" id="password">
        </div>
        <input type="submit" value="Log in">
    </form>
    <%if(isValidPassword != null && !isValidPassword){%>
        <span>Access denied</span>
    <%}%>
</body>
</html>