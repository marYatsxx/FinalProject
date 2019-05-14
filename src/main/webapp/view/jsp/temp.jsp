<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="header">
        <div class="header_resize">
            <div class="title"><h1><a href="">Online-pharmacy</a></h1></div>
            <div class="menu_nav">
                <ul>
                    <li><a href="about.html"><span>RU</span></a></li>
                    <li><a href="blog.html"><span>EN</span></a></li>
                </ul>
            </div>
            <div class="clr"></div>
        </div>
    </div>
    <div class="content">
        <h1>Welcome to Online-pharmacy!</h1>
        <p>Please, log in or create new account.</p>
        <form name="loginForm" method="POST" action="pharmacy">
            Login:<br/>
            <input type="text" name="login" value=""/>
            <br/>Password:<br/>
            <input type="password" name="password" value=""/>
            <br/>
            <input type="submit" value="Log in"/>
            <input type="submit" value="Sign up"/>
        </form>
        <hr/>
    </div>

</body>
</html>